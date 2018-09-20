package com.a3k.testCases.incognita.part4;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.utils.DateHelper;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class US22477_InternationalTeacher extends BasicTestCase {

    private HomePage homePage;

    @Test
    public void validateDateFormatsInMyLessons() {

        homePage = login("uk.inter","uk.inter", "Escience");
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
        new LoginPage(driver).loginIntoWithClass(login, password, program);
        return new HomePage(driver);
    }

    @Step
    private void openMyLessonsEscience() {
        homePage.goToNewUrl("/n/my_lessons/");
    }

    @Step
    private void verifyDateMyLessons() {
        By identifier = By.xpath("//tr[@class='row1']/td//following-sibling::td");
        String script = "return arguments[0].innerHTML";
        String date = (String) executeJavaScript(script, $(identifier));

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
