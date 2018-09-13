package com.a3k.testCases.incognita.part1;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Selenide.title;

public class US15148 extends BasicTestCase {

	private LoginPage loginPage;
	private HomePage homePage;
	private MyLessons myLessons;
	private SearchWidgetPage searchWidgetPage ;
	
	

	@DataProvider
	private Object[][] getSA() {
		return new Object[][] { 
			{ "uksa15148.pooh", "uksa15148.pooh", "KidBiz3000", "" }, 
			{ "ussa15148.alex", "ussa15148.alex", "KidBiz3000", "" },
			{ "ukda15148.pooh", "ukda15148.pooh", "KidBiz3000", "" }, 
			{ "usda15148.pooh", "usda15148.pooh", "KidBiz3000", "" }, 
			{ "kb.ref", "kb.ref", "KidBiz3000", "" } 
			};
	}

	@DataProvider
	private Object[][] getTeach() {
		return new Object[][]{
			{"teach15148.pooh", "teach15148.pooh", "KidBiz3000", ""},
			{ "ukteach15148.pooh", "ukteach15148.pooh", "KidBiz3000", "" } 
			};
	}


    @Test(dataProvider = "getSA", groups = {"Build Lesson Collections", "Incognita", "All"})
    private void verifyQuickLookButton_US15148(
            @Optional String username,
            @Optional String password,
            @Optional String studentProgram,
            @Optional String selectedClass) {

		logger.debug("Started verifying Quick Look button ENG");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		myLessons = new MyLessons(driver);
		searchWidgetPage = new SearchWidgetPage(driver);

		loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);
        homePage.goToMyLessonsByLink();

		logger.info("Opened: collections page");
		myLessons.clickOnBuiltLessonCollectionBy();
		myLessons.clickOnCreateCollectionBy();

		myLessons.clickOnAddLessonButton();
		sleep(200);
		openFirstInSearchResults();
		sleep(200);

		logger.info("Opened:  search results tab");
		softAssert.assertTrue(myLessons.isQuickLookPresent(), "Quick look is absent on Search tab.");
		checkPopupWindow();
		checkTabInFocus("Search");

		openFirstInFavoritesTab();
		logger.info("Opened: favorites tab");
		softAssert.assertTrue(searchWidgetPage.getClassFavoritesTab().contains("viewPick"), "The favorites tab is not in focus");

		if (!username.contains("ussa")) {

            try {
                openFirstInNewTab();
                logger.info("Opened: new for you tab");
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                softAssert.assertTrue(myLessons.isQuickLookPresent(), "Quick look is absent on New For You tab.");
                checkPopupWindow();
                checkTabInFocus("New for You");
                logger.debug("Finished verifying Quick Look button ENG");
            } catch (org.openqa.selenium.TimeoutException e) {
                logger.trace("No 'New For You lessons.");
            }
		}


		softAssert.assertAll();
	}

    @Test(dataProvider = "getTeach", groups = {"Build Lesson Collections", "Incognita", "All"})
    private void verifyNoQuickLook_US15148(
            @Optional String username,
            @Optional String password,
            @Optional String studentProgram,
            @Optional String selectedClass) {
		logger.debug("Started verifying Quick Look button absence ENG");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);

		myLessons = new MyLessons(driver);
		searchWidgetPage = new SearchWidgetPage(driver);

		loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);
		homePage.goToMyLessonsPage();

		logger.info("Opened: my lessons page");
		checkOnMyLessonsPage();
		checkInListView();

		checkInSearchTab();
		logger.debug("Finished verifying Quick Look Button absence ENG");

		myLessons.closeSearchWidgetPanel();

		softAssert.assertAll();
	}

    @Test(dataProvider = "getTeach", groups = {"Build Lesson Collections", "Incognita", "All"})
    private void verifyPrintingOptions_US15148(
            @Optional String username,
            @Optional String password,
            @Optional String studentProgram,
            @Optional String selectedClass) {
		logger.debug("Started verifying printing options ENG");
		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);

		myLessons = new MyLessons(driver);
		Lesson lesson = new Lesson(driver);

		loginPage.loginWithClassAndProgramIfNeeded(username, password, studentProgram, selectedClass);
        homePage.goToMyLessonsByLinkNew();
		myLessons.openAnyNotStartedLesson();

		lesson.clickPrintButton();
		lesson.clickOnOkButtonFromPrintPopup();

		lesson.switchToNextWindow();
		lesson.switchBack();

		logger.debug("Finished verifying printing options ENG");

	}

	@Step
	private void checkInListView() {
		myLessons.clickFirstInfoButton();
		logger.debug("First info button pressed");
		checkNoQuickLook();
		myLessons.closeInfoPopUp();
	}

	@Step
	private void openFirstInSearchResults() {
		searchWidgetPage.clickOnSearchTab();
		searchWidgetPage.enterTextInSearchBar("money");
		myLessons.clickSearch();
		searchWidgetPage.clickFirstLessonOnSearchTab();
		sleep(1000);
	}

	@Step
	private void openFirstInFavoritesTab() {
		searchWidgetPage.clickOnFavoritesTab();
	}

	@Step
	private void openFirstInNewTab() {
		searchWidgetPage.clickOnNewForYouTab();
        searchWidgetPage.clickOnSearchButtonOnNewForYouTab();
		searchWidgetPage.clickFirstLessonOnNewForYouTab();
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Step
	private void checkOnMyLessonsPage() {
		myLessons.goToDayView();
		if (myLessons.isDayViewEmpty()) {
			myLessons.clickOnSeachForMoreLessons();
			new SearchWidgetPage(driver).addSomeLessons(1);
		}
		myLessons.clickOnLessonDayView(0);
		checkNoQuickLook();
		myLessons.clickEscapeOnSelectedLessonFromView();

		myLessons.goToWeekView();
		if (myLessons.isWeekViewEmpty()){
			myLessons.clickOnSeachForMoreLessons();
			new SearchWidgetPage(driver).addSomeLessons(1);
		}
            myLessons.clickOnlessonOnWeekView(0);
            checkNoQuickLook();
		myLessons.clickEscapeOnSelectedLessonFromView();

		myLessons.goToMonthView();
		if (myLessons.isMonthViewEmpty()){
			myLessons.clickOnSeachForMoreLessons();
			new SearchWidgetPage(driver).addSomeLessons(1);
		}
		myLessons.clickOnlessonOnMonthView(0);
		checkNoQuickLook();
		myLessons.clickEscapeOnSelectedLessonFromView();

		homePage.goToMyLessonsPage();
	}

	@Step
	private void checkInSearchTab() {

        myLessons.openSearchWidgetPanel();

		openFirstInSearchResults();
		checkNoQuickLook();

	}

	@Step
	private void checkPopupWindow() {
		myLessons.clickQuickLookButton();

		myLessons.switchToNextWindow();
		
		myLessons.waitUntilPageLoaded();	

		checkLessonSteps();
		myLessons.switchBack();
		logger.info("Closed new window");
	}

	//TODO FIX
	@Step
	private void checkTabInFocus(String tabTitle) {
		logger.trace("Checking tab in focus");
		By tab = By.xpath(".//*[@id='moreLessonsContainer']//*[@title='" + tabTitle + "']");
		softAssert.assertTrue(searchWidgetPage.getAttributeBy(tab, "class").contains("viewPick"), tabTitle + " tab not in focus");
	}

	@Step
	private void checkNoQuickLook() {
		logger.trace("Checking quick look button presence");
		softAssert.assertFalse(myLessons.isQuickLookPresent(), "Quick button is present (it shouldn't be)");
	}

	@Step
	private void checkLessonSteps() {
		softAssert.assertTrue(title().contains("Achieve3000: "), "The title of new opened page incorrect.");
		logger.trace("TITLE OF NEW PAGE: " + title());
		logger.debug("Checking if snapshot's opened");
	}

}
