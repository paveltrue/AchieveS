package com.a3k.testCases.incognita.part4;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import com.a3k.utils.DateHelper;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class US22477_USteacher extends BasicTestCase {

    private HomePage homePage;
    private MyLessons myLessonsPage;
    private DayViewPage dayViewPage;
    private WeekViewPage weekViewPage;
    private MonthViewPage monthViewPage;
    private YearViewPage yearViewPage;

    @Test
    public void validateDateFormatsInMyLessons() {

        homePage = login("teenbizteach.one",
                "teenbizteach.one",
                "Teenbiz Class 6g");
        myLessonsPage = openMyLessonsPage();
        if (getWebDriver().getWindowHandles().size() > 1) {
            myLessonsPage.switchToNextWindowWhenExistOnly2();
            myLessonsPage.closeWindow();
            myLessonsPage.switchBackAfterClose();
        }
        verifyDateFormatForSchoolYearDates();

        verifyLessonStartDate();

        verifyLessonEndDate();

        verifyDateInLessonInfo();

        verifyDateInDayView();

        verifyDatesInWeekView();

        verifyDatesInMonthView();

        verifyDatesInYearView();

        verifyDatesInSearchResult();

        softAssert.assertAll();
    }

    @Step("Login with username {login}, password {password}, program " +
            "{program} " +
            "and class {classToSelect}")
    private HomePage login(String login,
                           String password,
                           String program) {
        logger.info(String.format("Login with credentials %s\\%s", login, password));
        new LoginPage(driver).login(login, password, program);
        return new HomePage(driver);
    }

    @Step
    private MyLessons openMyLessonsPage() {
        homePage.goToMyLessonsByLinkNew();
        return new MyLessons(driver);
    }

    @Step
    private void verifyDateFormatForSchoolYearDates() {
        String schoolYears = myLessonsPage.getSchoolDates();
        String schoolYear = StringUtils.substringBefore(schoolYears, "-")
                .trim();

        String format = "MMMMMMM d, yyyy";

        verifyDate(format, schoolYear);
    }

    @Step
    private void verifyDate(String format, String date) {
        boolean valid;
        valid = DateHelper.isValidFormat(format,
                date);
        softAssert.assertTrue(valid, "Date doesn't match expected. " +
                "Expected date should be matching format " + format + ". " +
                "Actual date: " + date);
    }

    @Step
    private void verifyLessonStartDate() {

        String startLessonDate = StringUtils.substringBefore(myLessonsPage.getStartEndLessonDate(),
                "\n");

        verifyDate("M/d/yy", startLessonDate);
    }

    @Step
    private void verifyLessonEndDate() {
        String endLessonsDate = StringUtils.substringAfter(myLessonsPage.getStartEndLessonDate(),
                "\n");

        verifyDate("M/d/yy", endLessonsDate);
    }

    @Step
    private void verifyDateInLessonInfo() {
        myLessonsPage.clickFirstInfoButton();

        String startDateInPopupLessonDate = myLessonsPage
                .getLessonStartDateInPopup();

        verifyDate("M/d/yy", startDateInPopupLessonDate);

        String endDateInPopupLessonDate = myLessonsPage
                .getLessonEndDateInPopup();

        verifyDate("M/d/yy", endDateInPopupLessonDate);
    }

    @Step
    private void verifyDateInDayView() {
        dayViewPage = myLessonsPage.clickOnDayButton();
        verifyDate("EEEEE, MMMMM d, yyyy", dayViewPage.getTodayDate());

        verifyDatesInLessonBar();
        dayViewPage.refreshPage();
    }

    @Step
    private void verifyDatesInLessonBar() {
        dayViewPage.clickOnLessonBar();

        verifyDate("M/d/yy", dayViewPage.getStartDateLessonBar());

        verifyDate("M/d/yy", dayViewPage.getEndDateLessonBar());
        dayViewPage.refreshPage();
    }

    @Step
    private void verifyDatesInWeekView() {
        weekViewPage = dayViewPage.clickOnWeekButton();

        String date = weekViewPage.getWeekDates().concat(" ").concat
                (weekViewPage.getYear());
        date = StringUtils.substringAfter(date,"-").trim();
        verifyDate("MMMMM d yyyy", date);
        dayViewPage.refreshPage();
    }

    @Step
    private void verifyDatesInMonthView() {
        monthViewPage = weekViewPage.clickOnMonthView();

        String date = monthViewPage.getDate().concat(" ").concat
                (monthViewPage.getYear());

        verifyDate("MMMMM yyyy", date);

        weekViewPage.refreshPage();
    }

    @Step
    private void verifyDatesInYearView() {
        yearViewPage = monthViewPage.clickYearView();

        String date = StringUtils.substringBefore(yearViewPage
                .getStudyYearsDate(),"-");
        verifyDate("yyyy", date);

        verifyDateInLessonInfoYearView();

        monthViewPage.refreshPage();
    }

    @Step
    private void verifyDateInLessonInfoYearView() {
        yearViewPage.expandFirstCategory();
        yearViewPage.clickOnFirstCollapsedSubcategory();
        yearViewPage.selectLesson();

        verifyDate("M/d/yy", yearViewPage.getStartDateLessonBar());

        verifyDate("M/d/yy", yearViewPage.getEndDateLessonBar());
    }

    @Step
    private void verifyDatesInSearchResult() {

        searchForLesson();

        verifyDate("M/d/yy", myLessonsPage
                .getLessonStartDateInSearchSideBar());
        verifyDate("M/d/yy", myLessonsPage
                .getLessonEndDateInSearchSideBar());
    }

    @Step
    private void searchForLesson() {
        myLessonsPage.clickOnSeachForMoreLessonsByJS();
        myLessonsPage.inputTextToSearchField("Elephants On the Move");
        myLessonsPage.clickSearchButtonOnSideBar();
        myLessonsPage.clickOnFoundLessonSideBar();
    }


}
