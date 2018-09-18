package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.pages.SearchWidgetPage;
import io.qameta.allure.Step;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DE22913 extends BasicTestCase {

    private LoginPage loginPage;
    private HomePage mainPage;
    private MyLessons myLessons;
    private SearchWidgetPage searchWidgetPage;

    @Parameters({"loginTeacherEmpower", "passwordTeacherEmpower", "program", "classToSelectTeacherEmpower"})
    @Test(groups = {"Smoke", "Usage Reports", "Teacher", "All"}, invocationCount = 1)
    public void checkCollectionCreated(
            @Optional("uskba.alex") String login,
            @Optional("uskba.alex") String password,
            @Optional("KidBiz3000") String program,
            @Optional("") String classToSelect) {

//        driver.get("https://trunk-portal.achieve3000.com");
        loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, classToSelect);
        mainPage = new HomePage(driver);

        openCollectionsPageAndDeleteOldCollections();

        createNewCollection("Test 5");
        checkCoursesAfterCreated("Test 5");

        softAssert.assertAll();
    }

    @Step("Open collections page and delete old collections")
    private void openCollectionsPageAndDeleteOldCollections() {

        myLessons = openCollectionsPage();

        myLessons.deleteAllCollections();
    }

    private MyLessons openCollectionsPage() {
        mainPage.goToNewUrl("/collections");
        return new MyLessons(driver);
    }

    @Step("Create new collection")
    private void createNewCollection(String collectionName) {
        myLessons.clickOnBuiltLessonCollection();

        myLessons.clickOnCreateCollectionBy();

        myLessons.enterTextInCollectionNameInput(collectionName);

        myLessons.enterTextInDescriptionInput(collectionName);

        myLessons.selectInProductDDL(MyLessons.Product.PRO);

        myLessons.waitForJSandJQueryToLoad();

        myLessons.clickOnAddLessonButton();

        searchWidgetPage = new SearchWidgetPage(driver);

        myLessons.waitForJSandJQueryToLoad();

        searchWidgetPage.clickOnAdvancedOption();

        searchWidgetPage.selectInCourseDDL("News");

        searchWidgetPage.clickOnSearchButton();

        searchWidgetPage.addLessonsOnSinglePage(5);

        myLessons.scrollDown();

        LocalDate today = LocalDate.now();

        String startDate = today.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

        String dateOfTodaySpletter[] = startDate.split("-");
        String dayOfToday = dateOfTodaySpletter[2];
        String monthOfToday = dateOfTodaySpletter[1];
        String yearOfToday = dateOfTodaySpletter[0];

        LocalDate collectionDateEnd = today.plusDays(4);

        String dateOfCollectionEnd = collectionDateEnd.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

        String dateOfCollectionEndSplitter[] = dateOfCollectionEnd.split("-");
        String dayOfCollectionEnd = dateOfCollectionEndSplitter[2];
        String monthOfCollectionEnd = dateOfCollectionEndSplitter[1];
        String yearOfCollectionEnd = dateOfCollectionEndSplitter[0];

        logger.debug("Try add start date and end date");

        myLessons.setDateOnCalendarBy(yearOfToday, monthOfToday, dayOfToday, myLessons.getCollectionStartDateInputBy());

        myLessons.waitForJSandJQueryToLoad();

        myLessons.setDateOnCalendarBy(yearOfCollectionEnd, monthOfCollectionEnd, dayOfCollectionEnd, myLessons.getCollectionEndDateInputBy());

        myLessons.enterTextInAssignNewLessonEveryInput("1");
        myLessons.checkSomeDaysOfWeekStartFrom(1, 5);

        myLessons.enterTextInKeepLessonsLiveForInput("1");

        myLessons.clickOnSaveCollectionButtonBy();
        myLessons.clickOkButtonOnPopUpChangesSaved();


    }

    @Step ("Check courses name: {createdCoursesName}")
    private void checkCoursesAfterCreated(String createdCoursesName){

        String checkName = myLessons.getUncheckedCourseName();

        softAssert.assertEquals(checkName, createdCoursesName,
                "Collection with name " + createdCoursesName + " NOT created");

    }

}
