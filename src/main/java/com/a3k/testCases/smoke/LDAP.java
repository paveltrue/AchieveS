package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class LDAP extends BasicTestCase {

    @Issue("DE20861")
    @Parameters({"login", "password"})
    @Test(groups = {"Smoke", "LDAP", "All"}, invocationCount = 1)
    public void testLDAP(@Optional("qaldap") String login,
                         @Optional("hXbS9wAT") String password) {
        if ((!browserUrl.contains("-portal"))
                && (!browserUrl.contains("bugfix")
                && !browserUrl.contains("qa"))) {
            open(browserUrl + "/sso/achieve");
            login(login, password);
            HomePage homePage = new HomePage(driver);
            softAssert.assertTrue(homePage.isSearchButtonExist(),
                    "Search button does not present");

        }
        softAssert.assertAll();
    }

    @Step("Login with username {login}, password {password}")
    private void login(String login, String password) {
        new LoginPage(driver).loginWithRandomClassAndProgramIfNeeded(login, password);
    }

}
