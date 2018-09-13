package com.a3k.testCases.incognita.part2;

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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;


public class US18551 extends BasicTestCase {
	
	private MyLessons myLessons;
	private HomePage homePage;
	private String collectionName;
	private long id;
	private SearchWidgetPage searchWidget;
	private HashMap<String, String> map = new HashMap<String, String>();

	private String regular = "News";
	private String fixed = "Nonfiction Literacy";
	private String courseType;
	private String allowedLessonColorStatus = "rgb(255, 255, 255)";
	private String allowedLessonColorStatusRegEx = "rgba?\\(255, 255, 255(, 1)?\\)";
	private HashSet <String> allowedStatusOfLessons;
	
	

	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] { 		
			{"usda18551.alexnoclass", "usda18551.alexnoclass", "KidBiz3000", ""}
		};
		return data;
	}

	@Parameters({ "login", "password", "program", "selectedClass" })
	@Test(dataProvider = "getUsers", groups = {"Build Lesson Collections", "Lesson List", "Incognita", "All"})
	public void check_US18551(@Optional String login,
							  @Optional String password,
							  @Optional String program,
							  @Optional String selectedClass) {

		login(login, password, program, selectedClass);

		homePage = new HomePage(driver);
		homePage.goToMyLessonsPage();		

		myLessons = new MyLessons(driver);
		myLessons.deleteAllCollections();

		searchWidget = new SearchWidgetPage(driver);
		
		myLessons.clickOnBuiltLessonCollectionBy();
		myLessons.clickOnCreateCollectionBy();

		id = System.currentTimeMillis();

		collectionName = "Test collection" + id;

		myLessons.enterTextInCollectionNameInput(collectionName);
		myLessons.enterTextInDescriptionInput("Test");

		selectLessonAndDragAndDrop(SearchWidgetPage.Courses.ACHIEVE_INTENSIVE);

		selectLessonAndDragAndDrop(SearchWidgetPage.Courses.NEWS);
		selectLessonAndDragAndDrop(SearchWidgetPage.Courses.NON_FICTION_LITERACY);

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.PRO);
		checkExistLessonsPro("creation collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ESPANOL);
		checkExistLessonsBoostAccessEspanol("Español", "creation collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.BOOST);
		checkExistLessonsBoostAccessEspanol("Boost", "creation collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACCESS);
		checkExistLessonsBoostAccessEspanol("Access", "creation collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACHIEVE_INTENSIVE);
		checkExistLessonsSummer("creation collection");

		myLessons.deleteAllLessonsFromLessonListArea();		
		checkAddingSummer("creation collection");		

		myLessons.deleteAllLessonsFromLessonListArea();		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.PRO);
		checkAddingPro("creation collection");				

		myLessons.deleteAllLessonsFromLessonListArea();		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.BOOST);
		checkAddingBoostAndEspanolAndAccess("Boost", "creation collection");
		
		myLessons.deleteAllLessonsFromLessonListArea();		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACCESS);
		checkAddingBoostAndEspanolAndAccess("Access", "creation collection");				

		myLessons.deleteAllLessonsFromLessonListArea();
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ESPANOL);
		checkAddingBoostAndEspanolAndAccess("Español", "creation collection");		

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ALL_PRODUCTS);
		selectLessonAndDragAndDrop(SearchWidgetPage.Courses.ACHIEVE_INTENSIVE);
		
		saveCollectionWithLessons();
		
		myLessons.editSpecifiedCollection(collectionName);

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.PRO);
		checkExistLessonsPro("editing collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ESPANOL);
		checkExistLessonsBoostAccessEspanol("Español", "editing collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.BOOST);
		checkExistLessonsBoostAccessEspanol("Boost", "editing collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACCESS);
		checkExistLessonsBoostAccessEspanol("Access", "editing collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACHIEVE_INTENSIVE);
		checkExistLessonsSummer("editing collection");

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ALL_PRODUCTS);

		myLessons.clickOnSaveCollectionButtonBy();
		myLessons.clickOkButtonOnPopUpChangesSaved();

		myLessons.clickOnBuiltLessonCollectionBy();		
		myLessons.clickImportCollectionButton();		
		myLessons.importCollectionByName(collectionName);

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.PRO);
		checkExistLessonsPro("import collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ESPANOL);
		checkExistLessonsBoostAccessEspanol("Español", "import collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.BOOST);
		checkExistLessonsBoostAccessEspanol("Boost", "import collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACCESS);
		checkExistLessonsBoostAccessEspanol("Access", "import collection");		
		
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACHIEVE_INTENSIVE);
		checkExistLessonsSummer("import collection");

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ALL_PRODUCTS);
		
		myLessons.clickOnSaveCollectionButtonBy();
		myLessons.clickOkButtonOnPopUpChangesSaved();

		allowedStatusOfLessons = new HashSet<>();
		allowedStatusOfLessons.add(allowedLessonColorStatus);

		importCourse("Achieve Intensive");		
		checkAfterImportSummer();
		
		importCourse(fixed);	
		checkAfterImportFixed();

		softAssert.assertAll();
	}

	@Step
	private void importCourse(String typeOfCourse) {
		myLessons.clickOnBuiltLessonCollectionBy();
		myLessons.clickImportCollectionButton();
		myLessons.importCourseByName(typeOfCourse);
	}

	@Step
	private void checkAfterImportFixed() {
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACHIEVE_INTENSIVE);
		checkAllLessonsAreNotAllowed("import "+fixed, "Achieve Intensive");

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.PRO);
		checkAllLessonsAreAllowed("import "+fixed, "Pro");

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ESPANOL);
		checkAllLessonsAreAllowed("import "+fixed, "Español");

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.BOOST);
		checkAllLessonsAreAllowed("import "+fixed, "Boost");

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACCESS);
		checkAllLessonsAreAllowed("import "+fixed, "Access");

		myLessons.clickOnCancelCollectionsButton();
	}

	@Step
	private void checkAfterImportSummer() {
		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.PRO);
		checkAllLessonsAreNotAllowed("import Summer", "Pro");

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ESPANOL);
		checkAllLessonsAreNotAllowed("import Summer", "Español");

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.BOOST);
		checkAllLessonsAreNotAllowed("import Summer", "Boost");

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACCESS);
		checkAllLessonsAreNotAllowed("import Summer", "Access");

		selectTypeOfProgramAndClickVerifyButton(MyLessons.Product.ACHIEVE_INTENSIVE);
		checkAllLessonsAreAllowed("import Summer", "Achieve Intensive");

		myLessons.clickOnCancelCollectionsButton();
	}

	@Step
	private void checkAllLessonsAreNotAllowed(String description, String programType) {
		Set<String> actStatusOfLessons = myLessons.getStatusOfLessonsInCollection();	
		softAssert.assertNotEquals(actStatusOfLessons, allowedStatusOfLessons , "There are not of all lessons outlined in red. After " +description+" course, when selected "+programType+" -program.");
	}

	@Step
	private void checkAllLessonsAreAllowed(String description, String programType) {
		Set<String> actStatusOfLessons = myLessons.getStatusOfLessonsInCollection();
		softAssert.assertEquals(actStatusOfLessons, allowedStatusOfLessons , "Not all of lessons are allowed. After " +description+" course, when selected "+programType+" -program.");
	}

	@Step
	private void checkExistLessonsSummer(String description) {
		courseType = SearchWidgetPage.Courses.NEWS.toString();
		softAssert.assertTrue(isLessonAllowed(getStatusOfLessonByType(SearchWidgetPage.Courses.NEWS.toString())), "The lesson " + map.get(SearchWidgetPage.Courses.NEWS.toString()) + " is not allowed. Achieve Intensive " + description);

		courseType = SearchWidgetPage.Courses.NON_FICTION_LITERACY.toString();
		softAssert.assertFalse(isLessonAllowed(getStatusOfLessonByType(SearchWidgetPage.Courses.NON_FICTION_LITERACY.toString())), "The lesson " + map.get(SearchWidgetPage.Courses.NON_FICTION_LITERACY.toString()) + " is allowed. Achieve Intensive " + description);
	}

	@Step
	private void checkExistLessonsBoostAccessEspanol(String programType, String description) {
		courseType = SearchWidgetPage.Courses.NEWS.toString();
		softAssert.assertTrue(isLessonAllowed(getStatusOfLessonByType(SearchWidgetPage.Courses.NEWS.toString())), "The lesson " + map.get(SearchWidgetPage.Courses.NEWS.toString())+" is not allowed. " +programType+" " + description);

		courseType = SearchWidgetPage.Courses.NON_FICTION_LITERACY.toString();
		softAssert.assertTrue(isLessonAllowed(getStatusOfLessonByType(SearchWidgetPage.Courses.NON_FICTION_LITERACY.toString())), "The lesson " + map.get(SearchWidgetPage.Courses.NON_FICTION_LITERACY.toString())+" is not allowed. " +programType+" " + description);
	}

	@Step
	private void checkExistLessonsPro(String description) {
		courseType = SearchWidgetPage.Courses.NEWS.toString();
		softAssert.assertTrue(isLessonAllowed(getStatusOfLessonByType(courseType)), "The lesson " + map.get(courseType)+" is not allowed. PRO " + description);

		courseType = SearchWidgetPage.Courses.NON_FICTION_LITERACY.toString();
		softAssert.assertTrue(isLessonAllowed(getStatusOfLessonByType(courseType)), "The lesson " + map.get(courseType)+" is not allowed. PRO " + description);
	}

	@Step
	private void checkAddingBoostAndEspanolAndAccess(String programType, String description) {
		courseType = SearchWidgetPage.Courses.NEWS.toString();
		softAssert.assertTrue(dragAndDropAndCheckLessonAllowedToAdd(SearchWidgetPage.Courses.NEWS), "The 'lesson cannot be added' message is appeared after adding the lesson - " + map.get(courseType)+". " +programType+" " + description);

		courseType = SearchWidgetPage.Courses.NON_FICTION_LITERACY.toString();
		softAssert.assertTrue(dragAndDropAndCheckLessonAllowedToAdd(SearchWidgetPage.Courses.NON_FICTION_LITERACY), "The 'lesson cannot be added' message is appeared after adding the lesson - " + map.get(courseType)+". " +programType+" " + description);
	}

	@Step
	private void checkAddingPro(String description) {
		courseType = SearchWidgetPage.Courses.NEWS.toString();
		softAssert.assertTrue(dragAndDropAndCheckLessonAllowedToAdd(SearchWidgetPage.Courses.NEWS), "The 'lesson cannot be added' message is appeared after adding the lesson - " + map.get(courseType)+". PRO " + description);

		courseType = SearchWidgetPage.Courses.NON_FICTION_LITERACY.toString();
		softAssert.assertTrue(dragAndDropAndCheckLessonAllowedToAdd(SearchWidgetPage.Courses.NON_FICTION_LITERACY), "The 'lesson cannot be added' message is appeared after adding the lesson - " + map.get(courseType)+". PRO " + description);
	}

	@Step
	private void checkAddingSummer(String description) {
		courseType = SearchWidgetPage.Courses.NEWS.toString();
		softAssert.assertTrue(dragAndDropAndCheckLessonAllowedToAdd(SearchWidgetPage.Courses.NEWS), "The 'lesson cannot be added' message is appeared after adding the lesson - " + map.get(courseType)+". Achieve Intensive " + description);

		courseType = SearchWidgetPage.Courses.NON_FICTION_LITERACY.toString();
		softAssert.assertFalse(dragAndDropAndCheckLessonAllowedToAdd(SearchWidgetPage.Courses.NON_FICTION_LITERACY), "The 'lesson cannot be added' message is not appeared after adding the lesson - " + map.get(courseType)+". Achieve Intensive " + description);
	}

	@Step
	private void saveCollectionWithLessons() {	

		LocalDate today = LocalDate.now();

		String dateOfToday = today.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

		String dateOfTodaySpletter[] = dateOfToday.split("-");
		String dayOfToday = dateOfTodaySpletter[2];
		String monthOfToday = dateOfTodaySpletter[1];
		String yearOfToday = dateOfTodaySpletter[0];

		LocalDate oneMonthAfter = today.plusMonths(1);

		String dateOfOneMonthAfterToday = oneMonthAfter.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

		String dateOfOneMonthAfterTodaySpletter[] = dateOfOneMonthAfterToday.split("-");
		String dayOfOneMonthAfter = dateOfOneMonthAfterTodaySpletter[2];
		String monthOfOneMonthAfter = dateOfOneMonthAfterTodaySpletter[1];
		String yearOfOneMonthAfter = dateOfOneMonthAfterTodaySpletter[0];

		myLessons.scrollDown();

		myLessons.setDateOnCalendarBy(yearOfToday, monthOfToday, dayOfToday, myLessons.getCollectionStartDateInputBy());
		myLessons.setDateOnCalendarBy(yearOfOneMonthAfter, monthOfOneMonthAfter, dayOfOneMonthAfter, myLessons.getCollectionEndDateInputBy());

		myLessons.enterTextInAssignNewLessonEveryInput("1");
		myLessons.checkEveryDaysOfWeek();
		myLessons.enterTextInKeepLessonsLiveForInput("10");
		
		myLessons.clickOnSaveAndAssignButton();
		myLessons.clickContinueButtonOnPopUpAssignCollection();
		myLessons.clickOkButtonOnPopUpChangesSaved();
	}

	@Step
	private boolean dragAndDropAndCheckLessonAllowedToAdd(SearchWidgetPage.Courses typeOflesson) {
		selectLessonAndDragAndDrop(typeOflesson);
		if(myLessons.isLessonCannotBeAddedMessagePresent()){
			myLessons.clickOkButtonOfLessonCannotBeAddedMessagePresent();
			return false;
		} 
		return true;		
	}

	@Step
	private void selectTypeOfProgramAndClickVerifyButton(MyLessons.Product typeOfProgram) {
		myLessons.expandSectionHeaders();		
		myLessons.selectInProductDDL(typeOfProgram);		
		myLessons.clickVerifyScheduleButtonBy();
	}

	@Step
	private void selectLessonAndDragAndDrop(SearchWidgetPage.Courses lessonType) {

		myLessons.clickOnAddLessonButton();

		searchWidget.expandAdvancedOptions();
		searchWidget.selectFromCourseDDLFirstItemContainsBy(lessonType);
		searchWidget.clickOnSearchButtonByText();

		map.put(lessonType.toString(), searchWidget.getNameOfFirstLesson());

		searchWidget.dragAndDrop(searchWidget.getFirstLessonSearchTab(), myLessons.getLessonListArea());
	}

	@Step
	private String getStatusOfLessonByType(String typeOfLesson) {
		String lessonName = map.get(typeOfLesson);
		return myLessons.getStatusOfLessonInCollection(lessonName);
	}

	@Step
	private void login(String login, String password, String program, String selectedClass) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
	}

	@Step
	private boolean isLessonAllowed(String colorStatus) {
		return Pattern.matches(allowedLessonColorStatusRegEx, colorStatus);
//		return allowedLessonColorStatus.equals(colorStatus);
	}
}