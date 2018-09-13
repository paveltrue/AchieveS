package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LeadershipEditionPage;
import com.a3k.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class US16821 extends BasicTestCase {

    private LoginPage loginPage;
    private HomePage homePage;
    private LeadershipEditionPage leadershipEditionPage;
    private List<String> actualResults;
    private List<String> expectedResults;


    @DataProvider
    public Object[][] getUsers() {
        Object[][] data = new Object[][] {
                {"usda16821.alexnoclass", "usda16821.alexnoclass", "KidBiz3000", "" }
        };
        return data;
    }

    @Parameters({ "login", "password", "program", "selectedClass" })
    @Test(dataProvider = "getUsers", groups = {"LE - Login Page", "Logout", "Incognita", "All"})
    public void check_US16821(@Optional() String login, @Optional() String password, @Optional() String program, @Optional() String selectedClass) {

        loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);

        homePage = new HomePage(driver);
        homePage.closeWalkmeNew(3);
        homePage.ckickOnVisitNowButton();

        leadershipEditionPage = new LeadershipEditionPage(driver);
        expectedResults = leadershipEditionPage.getInformationFromAverageScoreCard();

        leadershipEditionPage.clickLogoutButtonJS();
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, "LeadershipEdition", selectedClass);

        actualResults = leadershipEditionPage.getInformationFromAverageScoreCard();

        softAssert.assertEquals(expectedResults, actualResults, "The leadership pages is not the same.");

        softAssert.assertAll();
    }
}
