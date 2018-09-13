package com.a3k.testCases.incognita.part1;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.pages.SearchWidgetPage;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class US15621 extends BasicTestCase {


    private MyLessons myLessons;
    private HomePage homePage;
    private String collectionName;
    private long id;
    private SearchWidgetPage searchWidget;


    private String regular = "News";
    private String fixed = "Nonfiction Literacy";
    private String nameAfterEditing;
    private int expCountOfLessons;


    @DataProvider
    public Object[][] getUsers() {
        Object[][] data = new Object[][]{
                {"ussa15621.alexnoclass", "ussa15621.alexnoclass", "KidBiz3000", ""}
        };
        return data;
    }

    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"My Lessons", "Incognita", "All"}, invocationCount = 1)
    public void check_US15621(
            @Optional("ussa16050.alexnoclass") String login,
            @Optional("ussa16050.alexnoclass") String password,
            @Optional("") String program,
            @Optional("") String selectedClass) {

        login(login, password, program, selectedClass);

        homePage = new HomePage(driver);
        homePage.goToMyLessonsPage();

        myLessons = new MyLessons(driver);
        myLessons.deleteAllCollections();

        searchWidget = new SearchWidgetPage(driver);

        createCollection();
        editCollection();

        nameAfterEditing = myLessons.getCollectionName();
        softAssert.assertNotEquals(collectionName, nameAfterEditing, "The changes wasn't save after editing collection.");

        myLessons.editSpecifiedCollection(nameAfterEditing);
        softAssert.assertEquals(myLessons.getCountOfAddedLessons(), expCountOfLessons, "The recently added lessons are absent.");

        softAssert.assertAll();
    }

    @Step
    private void editCollection() {
        myLessons.editSpecifiedCollection(collectionName);
        myLessons.enterTextInCollectionNameInput("Editing " + collectionName);
        myLessons.enterTextInDescriptionInput("Test");

        selectLessonAndDragAndDrop(SearchWidgetPage.LessonTypes.STRATEGIC_LESSONS);
        myLessons.clickOkOnBeSurePopup();

        expCountOfLessons = myLessons.getCountOfAddedLessons();
        saveCollectionWithLessons();
    }

    @Step
    private void createCollection() {
        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickOnCreateCollectionBy();

        id = System.currentTimeMillis();

        collectionName = "Test collection" + id;
        myLessons.enterTextInCollectionNameInput(collectionName);
        myLessons.clickOnSaveCollectionButtonBy();
        myLessons.clickOkButtonOnPopUpChangesSaved();
    }

    @Step
    private void saveCollectionWithLessons() {


        LocalDate today = LocalDate.now();

        String startDate = today.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

        String dateOfTodaySplitter[] = startDate.split("-");
        String dayOfToday = dateOfTodaySplitter[2];
        String monthOfToday = dateOfTodaySplitter[1];
        String yearOfToday = dateOfTodaySplitter[0];

        LocalDate collectionDateEnd = today.plusDays(4);

        String dateOfCollectionEnd = collectionDateEnd.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

        String dateOfCollectionEndSplitter[] = dateOfCollectionEnd.split("-");
        String dayOfCollectionEnd = dateOfCollectionEndSplitter[2];
        String monthOfCollectionEnd = dateOfCollectionEndSplitter[1];
        String yearOfCollectionEnd = dateOfCollectionEndSplitter[0];

        logger.debug("Try add start date and end date");

        myLessons.setDateOnCalendarBy(yearOfToday, monthOfToday, dayOfToday,
                myLessons.getCollectionStartDateInputBy());

        myLessons.waitForJSandJQueryToLoad();

        myLessons.setDateOnCalendarBy(yearOfCollectionEnd, monthOfCollectionEnd,
                dayOfCollectionEnd, myLessons.getCollectionEndDateInputBy());

        myLessons.enterTextInAssignNewLessonEveryInput("1");
        myLessons.checkEveryDaysOfWeek();
        myLessons.enterTextInKeepLessonsLiveForInput("10");

        myLessons.clickOnSaveAndAssignButton();
        myLessons.clickContinueButtonOnPopUpAssignCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();
    }

    @Step
    private void selectLessonAndDragAndDrop(SearchWidgetPage.LessonTypes lessonType) {
        myLessons.clickOnAddLessonButton();
        sleep(500);
        searchWidget.expandAdvancedOptions();
        sleep(500);
        searchWidget.selectFromLessonTypeDDLFirstItemContains(lessonType);
        sleep(1200);
        searchWidget.clickOnSearchButtonByText();
        sleep(500);

        searchWidget.addLessonsOnSinglePage(1);
        sleep(500);

    }

    @Step
    private void login(String login, String password, String program, String selectedClass) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
    }

    public void sleep(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
