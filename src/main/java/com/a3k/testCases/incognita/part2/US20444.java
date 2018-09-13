package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import com.a3k.utils.db.DatabaseReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Set;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class US20444 extends BasicTestCase {


    private NewHomePage newHomePage;
    private LoginPage loginPage;
    private Set<String> actMenuItems;
    private Set<String> expMenuItem;
    private HomePage homePage;
    private CareerCenterPage careerCenterPage;
    private String currentLexileFromCareerCenter;
    private DetailsPage detailsPage;
    private String currentLexileFromDetails;
    private String carrentLexileFromMotivationMenu;


    @DataProvider
    public Object[][] getUsers() {
        Object[][] data = new Object[][]{
                {"parent21431.kbemtb", "parent21431.kbemtb", "teenbiz", "NJ Edition", "Pro Class Alexs"}
        };
        return data;
    }


    @Parameters({"login", "password", "program", "stateEdition", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"All", "Logo", "Home Page", "Incognational"}, invocationCount = 1)
    public void check_US20444(@Optional String login, @Optional String password, @Optional String studentProgram, @Optional String stateEdition, @Optional String selectedClass) {

        loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, studentProgram, selectedClass);

        newHomePage = new NewHomePage(driver);
        newHomePage.clickStudentsSelector();
        newHomePage.chooseStudentByNameContains("emma");

        newHomePage.waitThreadSleep(2000);
        newHomePage.waitForJSandJQueryToLoad();

        newHomePage.clickOnMotivationMenuArrow();
        newHomePage.openLexileDetails();

        softAssert.assertTrue(newHomePage.isLexileToggleDisplayed(), " 1  The Lexile toggle is not displayed.");
        softAssert.assertEquals(newHomePage.getLexileValue(), "No Lexile Available", "  2  The value of Lexile is not correct.");

        softAssert.assertFalse(newHomePage.isCareerTitleDisplayed(), "  3  The Career title is displayed on motivation menu, but shouldn't be.");
        softAssert.assertFalse(newHomePage.isCareerGoalLexileValueDisplayed(), "  4  The Career Goal Lexile is displayed on motivation menu, but shouldn't be.");


        newHomePage.clickOnLexileToggle();
        softAssert.assertFalse(newHomePage.isLexileValueDisplayed(), "  5  The Lexile value information is present after switching lexile details toggle.");

        String result = getPreferenceValueFromDB(login);
        softAssert.assertEquals(result, "0", "  6  The preference value from db is not correct after switching lexile details toggle.");

        newHomePage.clickOnLexileToggle();
        softAssert.assertTrue(newHomePage.isLexileValueDisplayed(), "  7  The Lexile value information is ABSENT after switching BACK the lexile details toggle.");

        result = getPreferenceValueFromDB(login);
        softAssert.assertEquals(result, "1", "  8  The preference value from db is not correct after switching lexile details toggle BACK.");


        newHomePage.clickOnMotivationMenuArrow();

        newHomePage.clickStudentsSelector();
        newHomePage.chooseStudentByNameContains("tina");

        newHomePage.waitThreadSleep(2000);
        newHomePage.waitForJSandJQueryToLoad();

        newHomePage.clickOnMotivationMenuArrow();
        newHomePage.openLexileDetails();

        carrentLexileFromMotivationMenu = newHomePage.getCurrentLexileValue();


        softAssert.assertTrue(newHomePage.isLexileToggleDisplayed(), "  9  The Lexile toggle is not displayed.");
        softAssert.assertTrue(newHomePage.isLexileValueDisplayed(), "  10  The value of Lexile is not correct.");

        softAssert.assertTrue(newHomePage.isCareerTitleDisplayed(), "  11  The Career title is NOT displayed on motivation menu.");
        softAssert.assertTrue(newHomePage.isCareerGoalLexileValueDisplayed(), "  13  The Career Goal Lexile is NOT displayed on motivation menu.");


        newHomePage.clickOnCareerCenterButton();

        careerCenterPage = new CareerCenterPage(driver);
        careerCenterPage.uncheckAllCheckedCareerItemsOnEachSection();

        careerCenterPage.checkOneCareerItemOnSectionByNumber(1, 3);
        careerCenterPage.clickOnFindCareerButton();


        softAssert.assertTrue(careerCenterPage.isCareerGoalLexileDisplayed(), "  14  The Career Lexile Goal is not displayed.");

        currentLexileFromCareerCenter = careerCenterPage.getCurrentLexileValue().replaceFirst(".*:", "").trim();
        softAssert.assertEquals(carrentLexileFromMotivationMenu, currentLexileFromCareerCenter, "  15 The value of current lexile is not the same on different page.");

        newHomePage.clickOnMotivationMenuArrow();
        newHomePage.clickOnSeeTheRulesButton();

        newHomePage.switchToNextWindow();

        detailsPage = new DetailsPage(driver);

        softAssert.assertTrue(detailsPage.isTopCareerTitleDisplayed(), "  16  The Top Career title is not displayed.");
        softAssert.assertTrue(detailsPage.isCareerGoalDisplayed(), "  17  The Career Goal is not displayed.");

        currentLexileFromDetails = detailsPage.getValueOfCurrentLexile().replaceAll(".*[a-z]", "").trim();
        softAssert.assertEquals(carrentLexileFromMotivationMenu, currentLexileFromDetails, "  18  The Current Lexile from details page is not the same as from Motivation Menu.");

        getWebDriver().close();
        softAssert.assertAll();
    }

    private String getPreferenceValueFromDB(String login) {
        DatabaseReader dbReader = new DatabaseReader(url());
        String sql = "select * from subscriber_preferences where user_id =(" +
                "select user_id from subscriber where login_name='" + login + "')";
        return dbReader.query(sql, "preference_value");
    }
}