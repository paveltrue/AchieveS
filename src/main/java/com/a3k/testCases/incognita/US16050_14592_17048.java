package com.a3k.testCases.incognita;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import com.a3k.utils.db.DatabaseReader;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.WebDriverRunner.url;

public class US16050_14592_17048 extends BasicTestCase {

	private HomePage homePage;
	private MyLessons myLessons;
	private String collectionName;
	private long id;
	private AdminPage adminPage;
	private SearchWidgetPage searchWidget;
	private String collectionNameEdit;
	private DatabaseReader databaseReader;
	private String expResultAssigned = "1";
	private String expResultUnassigned = "0";

	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] { 
			{"ussa16050.alexnoclass", "ussa16050.alexnoclass", "KidBiz3000", "" },
			{"usda16050.alexnoclass", "usda16050.alexnoclass", "KidBiz3000", ""},
			{"uksa16050.alexnoclass", "uksa16050.alexnoclass", "KidBiz3000", ""},
			{"ukda16050.alexnoclass", "ukda16050.alexnoclass", "KidBiz3000", ""},
			{"uskba.alex", "uskba.alex", "KidBiz3000", ""}
		};
		return data;
	}

	@Parameters({ "login", "password", "program", "selectedClass" })
    @Test(dataProvider = "getUsers", groups = {"My Lessons", "Build Lesson Collections", "Incognita", "All"})
    public void check_US16050_US14592_US17048(@Optional String login,
                                              @Optional String password,
                                              @Optional String program,
                                              @Optional String selectedClass) {

		login(login, password, program, selectedClass);

		homePage = new HomePage(driver);
		homePage.goToMyLessonsPage();		

		myLessons = new MyLessons(driver);
		myLessons.deleteAllCollections();

		createCollectionWithAddedLesson("Test Collection - ", "ENGLISH");
		
		softAssert.assertEquals(checkSharedStatusOfCollection(), expResultUnassigned, "The collection " + collectionName + " has wrong share status for UNASSIGNED in db. ENGLISH" );

		if(login.contains("sa")){
			checkStatusForSingleClass("ENGLISH");
		}		

		editNameOfCollection("ENGLISH");		
		setCollectionAssigned();		
	
		softAssert.assertEquals(checkSharedStatusOfCollection(), expResultAssigned, "The collection " + collectionName + " has wrong share status for ASSIGNED in db. ENGLISH" );

		adminPage = new AdminPage(driver);

		if (login.contains("kba")) {
			myLessons.clickOnChangeDistrictLink();

			// Achieve3000 QA District (NJ) -- Current
			adminPage.selectDistrictByValue("1528");
            softAssert.assertFalse(myLessons.isNeededCollectionPresent(collectionName),
                    "The collection " + collectionName + " is present on different district page, but shouldn't be.");
		}		


		softAssert.assertAll();
	}	
	

	@Step
    private void deleteSpecifiedCollection() {
		myLessons.deleteSpecifiedCollection(collectionName);
		myLessons.clickYesButtonOnPopUpDeleteCollection();		
	}

	@Step
	private void checkStatusForSingleClass(String language) {
		myLessons.selectFirstClass();
		softAssert.assertEquals(checkSharedStatusOfCollection(), expResultUnassigned, "The collection " + collectionName + " has wrong share status for UNASSIGNED in db FOR SINGLE CLASS." + language );
		
		myLessons.selectAllClasses();
	}

	@Step
	private String checkSharedStatusOfCollection() {
		
		databaseReader = new DatabaseReader(url());
		String idOfCollection = myLessons.getIdOfSpecifiedCollection(collectionName);

		String sql = "select * from lesson_collections where collection_id = " + idOfCollection;
		return databaseReader.query(sql, "shared");	
	}

	@Step
	private void setCollectionAssigned() {
		myLessons.editSpecifiedCollection(collectionName);

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
	private void editNameOfCollection(String language) {
		logger.info("Edit collection name");

		myLessons.editSpecifiedCollection(collectionName);
		id = System.currentTimeMillis();

		collectionNameEdit = "Edit Test Collection - " + id;
		myLessons.enterTextInCollectionNameInput(collectionNameEdit);

		myLessons.saveCollection();
		myLessons.clickOkButtonOnPopUpChangesSaved();

		softAssert.assertTrue(myLessons.isNeededCollectionPresent(collectionNameEdit), "The collection " + collectionNameEdit + " is not present after editing, but should be." + language);
		logger.info("Collection name changed from: " + collectionName + " - to - " + collectionNameEdit);

		collectionName = collectionNameEdit;
	}

	@Step
	private void createCollectionWithAddedLesson(String name, String language) {
		logger.info("Create new collection");

		myLessons.clickOnBuiltLessonCollectionBy();
		myLessons.clickOnCreateCollectionBy();

		id = System.currentTimeMillis();
		collectionName = name + id;

		myLessons.enterTextInCollectionNameInput(collectionName);
		myLessons.enterTextInDescriptionInput("Test");

		addLessonInCollection();
		sleep(200);

		myLessons.saveCollection();
		sleep(200);
		myLessons.clickOkButtonOnPopUpChangesSaved();

		softAssert.assertTrue(myLessons.isNeededCollectionPresent(collectionName), "The collection " + collectionName + " is not present page, but should be. " + language);

		logger.info("Collection created with name: " + collectionName);
	}

	@Step
	private void addLessonInCollection() {
		logger.info("Add lesson into collection");

		myLessons.clickOnAddLessonButton();

		searchWidget = new SearchWidgetPage(driver);
		sleep(1000);
		searchWidget.clearTextInputInSearchBar();
		sleep(1000);

		searchWidget.enterTextInSearchBar("earth");
		sleep(1000);
		searchWidget.clickOnSearchButtonByText();
		sleep(1200);

		searchWidget.dragAndDrop(searchWidget.getFirstLessonSearchTab(), myLessons.getLessonListArea());
		sleep(1000);
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
