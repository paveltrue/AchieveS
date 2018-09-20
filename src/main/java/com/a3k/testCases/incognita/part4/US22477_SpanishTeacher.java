package com.a3k.testCases.incognita.part4;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import com.a3k.utils.DateHelper;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class US22477_SpanishTeacher extends BasicTestCase {


    private HomePage homePage;
    private MyLessons myLessonsPage;
    private DayViewPage dayViewPage;
    private WeekViewPage weekViewPage;
    private MonthViewPage monthViewPage;
    private YearViewPage yearViewPage;

    @Test
    public void validateDateFormatsInMyLessons(){

        homePage = login("autospanish.teach", "autospanish.teach", "Kidbiz Class");
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
        new LoginPage(driver).loginWithoutRefreshNew(login, password, program);
        return new HomePage(driver);
    }

    @Step
    private MyLessons openMyLessonsPage() {
        homePage.goToMyLessonsByLinkNew();
        return new MyLessons(driver);
    }

    @Step
    private void verifyDateFormatForSchoolYearDates() {
        sleep(1000);
        String schoolYears = myLessonsPage.getSchoolDates();
        String schoolYear = StringUtils.substringBefore(schoolYears, "-")
                .trim();

        String format = "d 'de' MMMMM 'de' yyyy";

        verifyDate(format, schoolYear);
    }

    @Step
    private void verifyDate(String format, String date) {
        boolean valid;
        valid = DateHelper.isValidFormatSpanish(format,
                date);
        softAssert.assertTrue(valid, "Date doesn't match expected. " +
                "Expected date should be matching format " + format + ". " +
                "Actual date:" + date);
    }

    @Step
    private void verifyLessonStartDate() {

        String startLessonDate = StringUtils.substringBefore(myLessonsPage.getStartEndLessonDate(),
                "\n");

        verifyDate("d-MMM-yy", startLessonDate);
    }

    @Step
    private void verifyLessonEndDate() {
        String endLessonsDate = StringUtils.substringAfter(myLessonsPage.getStartEndLessonDate(),
                "\n");

        verifyDate("d-MMM-yy", endLessonsDate);
    }

    @Step
    private void verifyDateInLessonInfo() {
        myLessonsPage.clickFirstInfoButton();

        String startDateInPopupLessonDate = myLessonsPage.getLessonStartDateInPopup();

        verifyDate("d-MMM-yy", startDateInPopupLessonDate);

        String endDateInPopupLessonDate = myLessonsPage.getLessonEndDateInPopup();

        verifyDate("d-MMM-yy", endDateInPopupLessonDate);
    }

    @Step
    private void verifyDateInDayView() {
        dayViewPage = myLessonsPage.clickOnDayButton();
        verifyDate("EEEEE, d 'de' MMMMM 'de' yyyy", dayViewPage.getTodayDate
                ());

        verifyDatesInLessonBar();
        dayViewPage.refreshPage();
    }

    @Step
    private void verifyDatesInLessonBar() {
        dayViewPage.clickOnLessonBar();

        verifyDate("d-MMM-yy", dayViewPage.getStartDateLessonBar());

        verifyDate("d-MMM-yy", dayViewPage.getEndDateLessonBar());
        dayViewPage.refreshPage();
    }

    @Step
    private void verifyDatesInWeekView() {
        weekViewPage = dayViewPage.clickOnWeekButton();

        String date = weekViewPage.getWeekDates().concat(" ").concat
                (weekViewPage.getYear());
        date = StringUtils.substringAfter(date,"-").trim();
        verifyDate("d 'de' MMMMM yyyy", date);
        dayViewPage.refreshPage();
    }

    @Step
    private void verifyDatesInMonthView() {
        monthViewPage = weekViewPage.clickOnMonthView();

        String date = monthViewPage.getDate().concat(" ").concat(monthViewPage.getYear());

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

        verifyDate("d-MMM-yy", yearViewPage.getStartDateLessonBar());

        verifyDate("d-MMM-yy", yearViewPage.getEndDateLessonBar());
    }

    @Step
    private void verifyDatesInSearchResult() {
        searchForLesson();

        verifyDate("dd-MMM-yy", myLessonsPage.getLessonStartDateInSearchSideBar());
        verifyDate("dd-MMM-yy", myLessonsPage.getLessonEndDateInSearchSideBar());
    }

    @Step
    private void searchForLesson() {
        myLessonsPage.clickOnSeachForMoreLessonsByJS();
        myLessonsPage.inputTextToSearchField("Toma uno y deja otro");
        myLessonsPage.clickSearchButtonOnSideBar();
        myLessonsPage.clickOnFoundLessonSideBar();
    }
}
