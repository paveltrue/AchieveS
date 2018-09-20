package com.a3k.testCases.incognita.part4;

import com.a3k.BasicTestCase;
import com.a3k.pages.CurriculumScheduler;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.utils.DateHelper;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

public class US24608_InternationalTeacher extends BasicTestCase {
    private HomePage homePage;
    private CurriculumScheduler scheduler;

    @Test
    public void verifyDatesFormatOnScheduler() {
        homePage = login("stu.esclen.1", "song99082");
        scheduler = homePage.goToCurriculumScheduler();
        scheduler.openSetDatesTab();
        verifySetDatesTab();
        scheduler.openSchedulerTab();
        scheduler.expandAllLessonsAndChapters();
        verifyDateSchedulerTab();
        scheduler.collapseAllLessonsAndChapters();
        verifyUnitDate();
        scheduler.expandAllLessonsAndChapters();
        verifyDeletedLesson();
        verifyDateSchedulerTab();
        softAssert.assertAll();
    }

    @Step
    private void verifyDeletedLesson() {
        scheduler.deleteLesson();
        scheduler.clickOnRestoreDeletedLessonsButton();
        scheduler.restoreSelectedLesson();
    }

    @Step
    private void verifySetDatesTab() {
        String startDate = scheduler.getTextStartDateSetDatesTab();
        String endDate = scheduler.getTextEndDateSetDatesTab();

        DateHelper.isValidFormat("d-MMM-yyyy", startDate);
        DateHelper.isValidFormat("d-MMM-yyyy", endDate);
    }

    @Step
    private void verifyUnitDate() {
        String unitDate = scheduler.getUnitScheduleDateText();
        unitDate = StringUtils.substringAfter(unitDate, "-").trim();

        DateHelper.isValidFormat("d-MMM-yyyy", unitDate);
    }

    @Step
    private void verifyDateSchedulerTab() {
        String date = scheduler.getDateSchedulerTab();

        DateHelper.isValidFormat("mm/d/yyyy", date);


        scheduler.changeLessonDate(7);


    }

    @Step("Login with username {login}, password {password}, program " +
            "{program} " +
            "and class {classToSelect}")
    private HomePage login(String login,
                           String password) {
        logger.info(String.format("Login with credentials %s\\%s", login, password));
        new LoginPage(driver).loginIntoWithoutClasses(login, password);
        return new HomePage(driver);

    }
}