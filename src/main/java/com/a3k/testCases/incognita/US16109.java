package com.a3k.testCases.incognita;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.pages.SearchWidgetPage;
import com.a3k.utils.db.DatabaseReader;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;

public class US16109 extends BasicTestCase {

	private HomePage homePage;
	private MyLessons myLessons;
	private DatabaseReader databaseReader;
	private long id;
	private String collectionName;
	private SearchWidgetPage searchWidget;
	private List<String> lessonCollections;
	private String idOfCollection;

	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] { 
			{ "ussa16109.alexnoclass", "ussa16109.alexnoclass", "KidBiz3000", "" }, 
			{ "uksa16109.alexnoclass", "uksa16109.alexnoclass", "KidBiz3000", "" } 
			};
		return data;
	}

	@Parameters({ "login", "password", "program", "selectedClass" })
    @Test(dataProvider = "getUsers", groups = {"My Lessons", "Incognita", "All"}, invocationCount = 1)
	public void check_US16109(@Optional String login, @Optional String password, @Optional String program, @Optional String selectedClass) {

		login(login, password, program, selectedClass);

		homePage = new HomePage(driver);
		homePage.goToMyLessonsPage();

		myLessons = new MyLessons(driver);
		myLessons.deleteAllCollections();

		createCollectionWithoutAssignToClass("Test Collection - ");

		myLessons.selectFirstClass();

		databaseReader = new DatabaseReader(url());

		lessonCollections = checkSharedStatusOfCollection(login);
		idOfCollection = myLessons.getIdOfSpecifiedCollection(collectionName);

        softAssert.assertFalse(lessonCollections.contains(idOfCollection),
                "The collection: " + collectionName + " is present in result from db, but shouldn't be.");

		checkCollectionAndApprove();

		softAssert.assertAll();
	}

    @Step
    private void uncheckAndCancel() {
        myLessons.editSpecifiedCollection(collectionName);
        myLessons.clickOnCancelCollectionsButton();
    }

    @Step
    private void uncheckCollectionWithoutApproving() {
        myLessons.uncheckSpecifiedCollection(collectionName);

        myLessons.clickOnSaveCollectionButtonBy();
        myLessons.clickOnNoButtonOnCollectionWarning();
    }

    @Step
    private void checkCollectionAndApprove() {
        myLessons.editSpecifiedCollection(collectionName);
        myLessons.clickOnSaveCollectionButtonBy();

        myLessons.clickOnYesButtonOnCollectionWarning();
        myLessons.clickOkButtonOnPopUpChangesSaved();
    }

    @Step
    private void login(String login, String password, String program, String selectedClass) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
    }

    @Step
    private List<String> checkSharedStatusOfCollection(String loginOfUser) {
        databaseReader = new DatabaseReader(url());

        String sql = "select distinct collection_id from lesson_collection_classes where date_modified >= CURDATE()";
        return databaseReader.queryArray(sql, "collection_id");
    }

    @Step
    private void createCollectionWithoutAssignToClass(String name) {
        logger.info("Create new collection");

        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickOnCreateCollectionBy();

        id = System.currentTimeMillis();

        collectionName = name + id;

        myLessons.enterTextInCollectionNameInput(collectionName);
        myLessons.enterTextInDescriptionInput("Test");

        myLessons.clickShowClasses();

        myLessons.clickOnSelectIndividualClassRadioButton();
        myLessons.clickOnContinueButtonInShowClassPopUp();

        myLessons.saveCollection();
        sleep(100);
        myLessons.clickOkButtonOnPopUpChangesSaved();

        logger.info("Collection created with name: " + collectionName);
    }
}
