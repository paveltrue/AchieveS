package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.AssesmentPage;
import com.a3k.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class A3k_Assesment extends BasicTestCase {

    @Parameters({"login", "password", "classToSelect", "url"})
    @Test(groups = {"Smoke", "Assessment", "All"})
    public void studentActivities(
            @Optional("usassess.alexsmoke") String login,
            @Optional("usassess.alexsmoke") String password,
            @Optional("") String classToSelect,
            @Optional("http://trunk.qa-esc.portal.achieve3000.com") String url) {

        if (browserUrl.contains(".portal")) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(login, login);

            AssesmentPage assesmentPage = new AssesmentPage(driver);

            softAssert.assertTrue(assesmentPage.isContainerOfTextOnStartPagePresent(),
                    "Welcome message is not displayed");

            assesmentPage.waitAndAcceptAlert();

            Assert.assertTrue(assesmentPage.isNextButtonPresent(),"Next button doesn't displayed");

            assesmentPage.clickOnNextButtonOnFirstPage();
            {
                int i = 1;
                while (assesmentPage.isQuestionNumberStringPresent()) {
                    softAssert.assertEquals(assesmentPage.getTextFromQuestionNumberString(),
                            "Question " + i);
                    assesmentPage.clickOnAnswerAndWait();
                    ++i;
                }
            }
        }
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] getUsers() {
        Object[][] res = new Object[][]
                {
                        {"classstud14295.alexs", "classstud14295.alexs", "Access Class Alexs", "https://portal.aachieve3000"},
                        {"stud14295.alexs", "stud14295.alexs", "Access Class Alexs", "https://portal.aachieve3000"},
                };
        return res;
    }

}