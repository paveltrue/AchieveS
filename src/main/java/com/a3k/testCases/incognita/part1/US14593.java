package com.a3k.testCases.incognita.part1;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.pages.SearchWidgetPage;
import com.a3k.utils.db.DatabaseReader;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.url;

public class US14593 extends BasicTestCase {

    private LoginPage loginPage;
    private HomePage homePage;
    private MyLessons myLessons;
    private SearchWidgetPage searchWidgetPage;
    private DatabaseReader dbReader;


    @DataProvider
    private Object[][] getUsers() {
        return new Object[][]{
                {"usda14593.pooh", "usda14593.pooh", "KidBiz3000", ""},
                {"ussa14593.pooh", "ussa14593.pooh", "KidBiz3000", ""}
        };
    }


    @Test(dataProvider = "getUsers", groups = {"Collection Details", "Incognita", "All"})
    private void verifySharingNew_US14593(@Optional String username,
                                          @Optional String password,
                                          @Optional String studentProgram,
                                          @Optional String selectedClass) {

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        searchWidgetPage = new SearchWidgetPage(driver);

        loginPage.loginWithClassAndProgramIfNeeded(username, password, studentProgram, selectedClass);
        myLessons = homePage.goToMyLessonsPage();

        myLessons.deleteAllCollections();

        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickOnCreateCollectionBy();

        myLessons.enterTextInCollectionNameInput("Just a test collection 14593");
        myLessons.enterTextInDescriptionInput("Test description. Lorem ipsum blah-blah-blah");
        myLessons.enterTextInAssignNewLessonEveryInput("1");
        myLessons.enterTextInKeepLessonsLiveForInput("1");

        myLessons.openStartCalendar();
        myLessons.setClosestOnCalendar();

        myLessons.openEndCalendar();
        myLessons.setClosestOnCalendar();
        softAssert.assertTrue(myLessons.isShareButtonDisabled(),
                "Share button isn't disabled!");

        myLessons.checkSomeDaysOfWeekStartFrom(1, 5);

        myLessons.saveCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();

        softAssert.assertFalse(myLessons.isCollectionAssigned(
                "Just a test collection 14593"),
                "Collection have been assigned!");

        myLessons.editSpecifiedCollection("Just a test collection 14593");
        myLessons.clickOnAddLessonButton();
        searchWidgetPage = new SearchWidgetPage(driver);

        myLessons.waitForJSandJQueryToLoad();
        searchWidgetPage.clickOnAdvancedOption();

        searchWidgetPage.selectInCourseDDL("News");

        searchWidgetPage.clickOnSearchButtonByText();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchWidgetPage.addLessonsOnSinglePage(5);
        myLessons.clickOkOnBeSurePopup();
        myLessons.clickSaveAndAssignCollection();

        softAssert.assertTrue(myLessons.isAlertDialogPresent(),
                "Sharing alert isn't present");

        myLessons.continueAssignCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();

        softAssert.assertTrue(myLessons.isCollectionAssigned(
                "Just a test collection 14593"),
                "Collections haven't been assigned");

        softAssert.assertTrue(checkSharedStatusOfCollection(
                "Just a test collection 14593").equals("1"),
                "Collection isn't shared in DB");

        myLessons.deleteSpecifiedCollection("Just a test collection 14593");
        myLessons.clickYesButtonOnPopUpDeleteCollection();

        softAssert.assertAll();
    }

    @Step
    private String checkSharedStatusOfCollection(String collectionName) {
        dbReader = new DatabaseReader(url());
        String idOfCollection = myLessons.getIdOfSpecifiedCollection(collectionName);
        String sql = "select * from lesson_collections where collection_id = " + idOfCollection;
        return dbReader.query(sql, "shared");
    }

}
