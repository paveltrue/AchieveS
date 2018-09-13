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

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.url;

public class US15349 extends BasicTestCase {

    private LoginPage loginPage;
    private HomePage homePage;
    private MyLessons myLessons;
    private SearchWidgetPage searchWidgetPage;


    @DataProvider
    private Object[][] getUsers() {
        return new Object[][]{
                {"ussa15349.pooh", "ussa15349.pooh", "KidBiz3000", ""},
                {"usda15349.pooh", "usda15349.pooh", "KidBiz3000", ""}
        };
    }


    @Test(dataProvider = "getUsers", groups = {"Build Lesson Collections", "Collection Details", "Incognita", "All"})
    private void verifyDeletion_US15349(@Optional String username, @Optional String password, @Optional String studentProgram, @Optional String selectedClass) {

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        myLessons = new MyLessons(driver);
        searchWidgetPage = new SearchWidgetPage(driver);

        loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);
        homePage.goToMyLessonsPage();

        myLessons.deleteAllCollections();

        createCollectionToDelete();
        myLessons.deleteSpecifiedCollection("Collection to delete 15349");

        softAssert.assertTrue(myLessons.isDeleteAlertPresent(), "No delete dialog present!");

        myLessons.clickDeleteCollectionNoButton();

        String collectionID = myLessons.getIdOfSpecifiedCollection("Collection to delete 15349");
        softAssert.assertTrue(myLessons.isCollectionPresentByName("Collection to delete 15349"), "Collection have been deleted");

        myLessons.deleteSpecifiedCollection("Collection to delete 15349");

        myLessons.clickDeleteCollectionYesButton();

        refresh();
        softAssert.assertFalse(myLessons.isCollectionPresentByName("Collection to delete 15349"),
                "Collection haven't been deleted.");

        softAssert.assertTrue(isCollectionDeletedDB(collectionID), "The status of deleted collection is wrong.");

        softAssert.assertAll();
    }


    @Test(dataProvider = "getUsers")
    private void verifyDeletionAssigned_US15349(@Optional String username, @Optional String password, @Optional String studentProgram, @Optional String selectedClass) {

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        myLessons = new MyLessons(driver);
        searchWidgetPage = new SearchWidgetPage(driver);

        loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);
        homePage.goToMyLessonsPage();

        myLessons.deleteAllCollections();

        createCollectionToDelete();

        myLessons.editSpecifiedCollection("Collection to delete 15349");
        myLessons.clickSaveAndAssignCollection();

        myLessons.clickContinueButtonAssignCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();

        myLessons.deleteSpecifiedCollection("Collection to delete 15349");
        softAssert.assertTrue(myLessons.isDeleteAlertPresent(),
                "No delete dialog present!");

        myLessons.clickDeleteCollectionNoButton();
        String collectionID = myLessons.getIdOfSpecifiedCollection("Collection to delete 15349");
        softAssert.assertTrue(myLessons.isCollectionPresentByName("Collection to delete 15349"),
                "Collection have been deleted");


        myLessons.deleteSpecifiedCollection("Collection to delete 15349");
        myLessons.clickDeleteCollectionYesButton();

        refresh();

        softAssert.assertFalse(myLessons.isCollectionPresentByName("Collection to delete 15349"),
                "Collection haven't been deleted.");
        softAssert.assertTrue(isCollectionDeletedDB(collectionID),
                "Collection was not deleted in DB. Collection ID:" + collectionID);

        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickImportCollectionButton();

        softAssert.assertFalse(myLessons.isImportCollectionPresentByValue(collectionID),
                "Collection still can be " +
                        "exported");

        softAssert.assertAll();
    }

    @Test(dataProvider = "getUsers")
    private void verifyDeletionFuture_US15349(@Optional String username, @Optional String password, @Optional String studentProgram, @Optional String selectedClass) {

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        myLessons = new MyLessons(driver);
        searchWidgetPage = new SearchWidgetPage(driver);

        loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);
        homePage.goToMyLessonsPage();

        myLessons.deleteAllCollections();

        createCollectionToDelete();

        myLessons.editSpecifiedCollection("Collection to delete 15349");
        myLessons.openStartCalendar();
        myLessons.setDateAheadOnCalendar(2);
        myLessons.openEndCalendar();
        myLessons.setDateAheadOnCalendar(4);
        myLessons.clickSaveAndAssignCollection();

        myLessons.clickContinueButtonAssignCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();
        myLessons.deleteSpecifiedCollection("Collection to delete 15349");
        softAssert.assertTrue(myLessons.isDeleteAlertPresent(), "No delete dialog present!");
        myLessons.clickDeleteCollectionNoButton();

        String collectionID = myLessons.getIdOfSpecifiedCollection("Collection to delete 15349");
        softAssert.assertTrue(myLessons.isCollectionPresentByName("Collection to delete 15349"), "Collection have been deleted");

        myLessons.deleteSpecifiedCollection("Collection to delete 15349");

        myLessons.clickDeleteCollectionYesButton();

        refresh();

        softAssert.assertFalse(myLessons.isCollectionPresentByName("Collection to delete 15349"), "Collection haven't been deleted.");
        softAssert.assertTrue(isCollectionDeletedDB(collectionID), "The status of deleted collection is wrong.");

        softAssert.assertAll();
    }


    @Step
    private boolean isCollectionDeletedDB(String collectionID) {
        DatabaseReader databaseReader = new DatabaseReader(url());
        String colStatus = databaseReader.query("select status from lesson_collections where collection_id=" + collectionID, "status");
        return colStatus.equals("1");
    }

    @Step
    private void createCollectionToDelete() {
        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickOnCreateCollectionBy();

        myLessons.enterTextInCollectionNameInput("Collection to delete 15349");
        myLessons.enterTextInDescriptionInput("I am to be deleted :(");

        myLessons.clickOnAddLessonButton();
        myLessons.clickOnTab("Search");
        myLessons.expandAdvancedOptions();

        searchWidgetPage.selectInCourseDDL(SearchWidgetPage.Courses.NEWS.toString());
        searchWidgetPage.clickOnSearchButtonByText();
        searchWidgetPage.addFirstLesson();

        myLessons.enterTextInAssignNewLessonEveryInput("1");
        myLessons.enterTextInKeepLessonsLiveForInput("5");

        myLessons.checkEveryDaysOfWeek();
        myLessons.openStartCalendar();
        myLessons.setClosestOnCalendar();

        myLessons.openEndCalendar();
        myLessons.setClosestOnCalendar();
        myLessons.saveCollection();

        myLessons.clickOkButtonOnPopUpChangesSaved();
    }
}
