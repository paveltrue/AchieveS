package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import io.qameta.allure.Flaky;
import io.qameta.allure.Step;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class MyLessonsTeacher extends BasicTestCase {

	private static MyLessons myLessonsPage;

	@Flaky
	@Parameters({ "loginTeacher", "passwordTeacher", "program", "classToSelectTeacher" })
	@Test(groups = { "MyLessons", "Teacher", "Smoke", "All" }, invocationCount = 1)
	public void doTeacher(
			@Optional("autospanish.teach") String login,
			@Optional("autospanish.teach") String password,
			@Optional("") String program,
			@Optional("Kidbiz Class") String classToSelect)
			throws InterruptedException {

		login(login, password, program, classToSelect);

		myLessonsPage = openMyLessonsPage();

		verifyTodaysLessons();

		verifyCalendarElements();

		myLessonsPage.clickOnOkButtonOnCalendar();
		myLessonsPage.refreshPage();

		myLessonsPage.clickSearchForMoreLessons();

		verifySearchWidget();

		verifyDayView();
		verifyWeekView();
		verifyMonthView();

		verifyYearView();

		softAssert.assertAll();
	}

	@Step
	private void verifyCalendarElements() {
		logger.info("Verify UI elements on calendar");
		myLessonsPage.clickStartDate();
		softAssert.assertTrue(myLessonsPage.isCalendarPresent(), "Calendars aren't present.");
		softAssert.assertTrue(myLessonsPage.isStartOnCalendarPresent(), "Start isn't present on calendar.");
		softAssert.assertTrue(myLessonsPage.isEndOnCalendarPresent(), "End isn't present on calendar.");
		softAssert.assertTrue(myLessonsPage.isRemoveButtonOnCalendarPresent(), "Remove button isn't present on calendar.");

		logger.info("Calendar has been verified");
	}

	@Step
	private void verifyTodaysLessons() {
		logger.info("Verify Todays Lessons view");

		softAssert.assertTrue(myLessonsPage.isDateColumnPresent(), "Date column isn't present.");
		softAssert.assertTrue(myLessonsPage.isLessonColumnPresent(), "Lesson column isn't present.");
		softAssert.assertTrue(myLessonsPage.isTopicColumnPresent(), "Topic column isn't present.");
		softAssert.assertTrue(myLessonsPage.isStrategyColumnPresent(), "Strategy column isn't present.");
		softAssert.assertTrue(myLessonsPage.isLessonNameAndSummaryPresent(), "Lesson's name or summary isn't present");
	}

	@Step
	private void verifySearchWidget() {
		logger.info("Verify Search widget");
		SearchWidgetPage searchWidget = new SearchWidgetPage(driver);
		softAssert.assertTrue(searchWidget.isSearchBarPresent(), "Search bar isn't present.");
		softAssert.assertTrue(searchWidget.isSearchButtonPresent(), "Search button isn't present.");
		softAssert.assertTrue(searchWidget.isAdvancedOptionsSectionPresent(), "Advanced Options section isn't present.");

		logger.info("Search widget has been verified");

	}

	@Step
	private void verifyDayView() {
		logger.info("Verify Day View");

		DayViewPage dayView = myLessonsPage.clickOnDayButton();
		softAssert.assertTrue(dayView.isTodayDatePresent(), "Today date isn't present.");
		softAssert.assertTrue(dayView.isLessonForTodayPresent(), "There are no lessons for today.");
		softAssert.assertFalse(dayView.isArrowBackWorkCorrectly(),
                "Arrow Back doesn't work correctly.");
		softAssert.assertFalse(dayView.isArrowNextWorkCorrectly(),
                "Arrow Next doesn't work correctly.");

		logger.info("Day View has been verified");

		logger.info("Go to Week View");

		dayView.clickOnWeekButton();

	}

	@Step
	private void verifyWeekView() {
		logger.info("Verify Week View");
		WeekViewPage weekView = new WeekViewPage(driver);
		softAssert.assertTrue(weekView.isThereAllDaysOfWeek(), "There are no all days of week.");
		softAssert.assertTrue(weekView.isThereLessonForTheWeek(), "There are no lessons for the week.");
		softAssert.assertTrue(weekView.isDateDisplayed(), "Date doesn't displayed");
		softAssert.assertTrue(weekView.isArrowBackWorksCorrectly(), "Arrow Back doesn't work correctly 2.");
		softAssert.assertTrue(weekView.isArrowNextWorksCorrectly(), "Arrow Next doesn't work correctly 2.");
		softAssert.assertTrue(weekView.isViewsOfMyLessonsPresent(), "There are no views of my lessons page.");

		logger.info("Week View has been verified");

		logger.info("Go to Month View");

		weekView.clickOnMonthView();

	}

	@Step
	private void verifyMonthView() {
		logger.info("Verify Month View");

		MonthViewPage monthView = new MonthViewPage(driver);
		softAssert.assertTrue(monthView.isLessonForMonthPresent(), "There are no lessons for the month.");
		softAssert.assertTrue(monthView.isDatePresent(), "Date isn't present.");
		softAssert.assertTrue(monthView.isViewsOfMyLessonPagePresent(), "There are no views of my lessons page.");
		softAssert.assertTrue(monthView.isCalendarCorrect(), "Calendar isn't correct;");
		softAssert.assertTrue(monthView.isThereAllDaysOfWeek(), "There are no all days of week.");

		logger.info("Month View has been verified");

		logger.info("Go to Year View");
		monthView.clickYearView();

	}

	@Step
	private void verifyYearView() {
		logger.info("Verify Year View");

		YearViewPage yearView = new YearViewPage(driver);
		softAssert.assertTrue(yearView.isAmountOfDaysInMonthCorrect(), "Amount of days in month is incorrect.");
		softAssert.assertTrue(yearView.isCurrentYearPresent(), "Current year isn't present.");
		softAssert.assertTrue(yearView.isListOfTopicsPresent(), "List of topics isn't present.");
		softAssert.assertTrue(yearView.isIconStartOfLessonPresent(), "Icon start of lesson isn't present.");

		logger.info("Year View has been verified");

	}

	@Step("Login with username {login}, password {password}, program " +
			"{program} " +
			"and class {classToSelect}")
	private void login(String login, String password,String program, String classToSelect) {
		logger.info(String.format("Login with credentials %s/%s to class %s", login, password, classToSelect));
		new LoginPage(driver).loginWithClassAndProgramIfNeededWithAlert(login, password, program, classToSelect);
	}

	@Step
	private MyLessons openMyLessonsPage() {
		logger.info("Opening My Lessons page");
        new HomePage(driver).goToMyLessonsByLinkNew();

		return new MyLessons(driver);

	}
}
