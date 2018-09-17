package com.a3k.testCases.incognita.part4;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.utils.DateHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class US22477_NationalTeacher extends BasicTestCase {

    private HomePage homePage;

    @Test
    public void validateDateFormatsInMyLessons() {

        homePage = login("uk.super", "uk.super", "eScience3000");

        openMyLessonsEscience();
        verifyDateMyLessons();

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
    private void openMyLessonsEscience() {
        homePage.goToNewUrl("/n/my_lessons/");
    }

    @Step
    private void verifyDateMyLessons() {
        String date = homePage.getText(homePage.findEl(By.className
                ("lesson-date")));

        verifyDate("d-MMM-yy", date);
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
}
