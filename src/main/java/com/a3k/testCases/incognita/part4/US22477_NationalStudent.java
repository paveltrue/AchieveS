package com.a3k.testCases.incognita.part4;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.utils.DateHelper;
import io.qameta.allure.Step;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class US22477_NationalStudent extends BasicTestCase {

    private HomePage homePage;
    private MyLessons myLessonsPage;

    @Test
    public void validateDateFormatsInMyLessons() {
//        escience.four
        homePage = login("mariana.lee", "kite", "Uk Boost 5 Gr");
        myLessonsPage = openMyLessonsPage();
        if (getWebDriver().getWindowHandles().size() > 1) {
            myLessonsPage.switchToNextWindowWhenExistOnly2();
            myLessonsPage.closeWindow();
            myLessonsPage.switchBackAfterClose();
        }

        verifyLessonStartDate();

        verifyLessonEndDate();

        verifyActivitiesDates();

        softAssert.assertAll();
    }

    @Step("Login with username {login}, password {password}, program " +
            "{program} ")
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
    private void verifyLessonStartDate() {

        String startLessonDate = StringUtils.substringBefore(myLessonsPage.getStudentDateLessonProgress(),
                "\n");

        verifyDate("M/d/yy", startLessonDate);
    }

    @Step
    private void verifyLessonEndDate() {
        String endLessonsDate = StringUtils.substringAfter(myLessonsPage.getStudentDateLessonProgress(),
                "\n");

        verifyDate("M/d/yy", endLessonsDate);
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
    private void verifyActivitiesDates() {
        myLessonsPage.switchToActivities();
        if (!myLessonsPage.checkDatesPortfolio()){
            softAssert.assertTrue(myLessonsPage.checkDatesPortfolio(), "There are no activities available.");
        } else {
            verifyDate("M/dd/yy", myLessonsPage.getDatesPortfolio());
        }
    }
}
