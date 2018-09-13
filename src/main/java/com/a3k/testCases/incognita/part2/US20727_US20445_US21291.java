package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.CareerCenterPage;
import com.a3k.pages.DetailsPage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.NewHomePage;
import com.a3k.utils.db.DatabaseReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class US20727_US20445_US21291 extends BasicTestCase {


    private NewHomePage newHomePage;
    private LoginPage loginPage;
    private CareerCenterPage careerCenterPage;
    private DetailsPage detailsPage;
    private String currentLexileFromDetails;
    private String currentLexileFromMotivationMenu;


    @DataProvider
    public Object[][] getUsers() {
        Object[][] data = new Object[][]{
                {"teenstud20727.career", "teenstud20727.career", "teenbiz", "NJ Edition", "Teenbiz 7 Smoke"},
                {"teenstud20445.summer", "teenstud20445.summer", "teenbiz", "NJ Edition", "Auto Summer Class 7"},
                {"emp.nocareer", "emp.nocareer", "empower", "NJ Edition", "Empower 10 Smoke"}
                
        };
        return data;
    }


    @Parameters({"login", "password", "program", "stateEdition", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"All", "Logo", "Home Page", "Incognational"}, invocationCount = 1)
    public void check_US20727_US20445_US21291(@Optional String login,
                                              @Optional String password,
                                              @Optional String studentProgram,
                                              @Optional String stateEdition,
                                              @Optional String selectedClass) {

        loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, studentProgram, selectedClass);

        newHomePage = new NewHomePage(driver);
        newHomePage.clickOnMotivationMenuArrow();

        newHomePage.openLexileDetails();
        newHomePage.clickOnCareerCenterButton();

        careerCenterPage = new CareerCenterPage(driver);
        careerCenterPage.uncheckAllCheckedCareerItemsOnEachSection();

        int amountOfChoosenCareerTypes = 3;
        List<String> titlesOfChoosenCareer = careerCenterPage.checkSomeCareerItemsOnSectionByNumberAndReturnTitles(1, amountOfChoosenCareerTypes);

        careerCenterPage.clickOnFindCareerButton();

        softAssert.assertEquals(amountOfChoosenCareerTypes,
                careerCenterPage.getAmountOfCurrentLexile(),
                "  1  The amount of Current Lexile is not the same as choosen count of career.");
        softAssert.assertEquals(amountOfChoosenCareerTypes,
                careerCenterPage.getAmountOfCareerGoal(),
                "  2  The amount of  Career Lexile Goal is not the same as chosen count of career.");


        careerCenterPage.markAsTopCareer(titlesOfChoosenCareer.get(0));
        careerCenterPage.waitAndAcceptAlert();

    	while(!newHomePage.isLexileToggleDisplayed()){			
    		newHomePage.clickOnMotivationMenuArrow();
		}
    	
        newHomePage.openLexileDetails();

        String result = getPreferenceValueFromDB(login);
        sleep(1500);
        softAssert.assertEquals(result, "1",
                "  3  The preference value from db is not correct after switching lexile details toggle.");
        sleep(1500);
        newHomePage.clickOnLexileToggle();

        newHomePage.waitForPageToLoad();
        //newHomePage.waitForPageToLoad();

        result = getPreferenceValueFromDB(login);
        sleep(1500);
        softAssert.assertEquals(result, "0",
                "  4  The preference value from db is not correct after switching BACK lexile details toggle.");
        sleep(1500);
        softAssert.assertFalse(newHomePage.isLexileValueDisplayed(),
                "  5  The Lexile value information is present after switching lexile details toggle.");
        sleep(1500);


        newHomePage.clickOnLexileToggle();
        newHomePage.waitForPageToLoad();
        newHomePage.checkLexile();
        currentLexileFromMotivationMenu = newHomePage.getCurrentLexileValue();
        softAssert.assertEquals(titlesOfChoosenCareer.get(0), newHomePage.getCareerTitle(),  "  6  The career title is not the same as choosen one.");

        softAssert.assertTrue(newHomePage.isLexileToggleDisplayed(),
                "  7  The Lexile toggle is not displayed.");
        softAssert.assertTrue(newHomePage.isLexileValueDisplayed(),
                "  8  The value of Lexile is not correct.");

        softAssert.assertTrue(newHomePage.isCareerTitleDisplayed(),
                "  9  The Career title is NOT displayed on motivation menu.");
        softAssert.assertTrue(newHomePage.isCareerGoalLexileValueDisplayed(),
                "  10  The Career Goal Lexile is NOT displayed on motivation menu.");

        softAssert.assertEquals(titlesOfChoosenCareer.get(0), newHomePage.getTopCareerValue(),
                "  11  The TOP career title is not the same as choosen one.");

        newHomePage.clickOnSeeTheRulesButton();
        newHomePage.switchToNextWindow();

        detailsPage = new DetailsPage(driver);

        softAssert.assertTrue(detailsPage.isTopCareerTitleDisplayed(),
                "  12  The Top Career title is not displayed.");
        softAssert.assertTrue(detailsPage.isCareerGoalDisplayed(),
                "  13  The Career Goal is not displayed.");

        currentLexileFromDetails = detailsPage.getValueOfCurrentLexile().replaceAll(".*[a-z]", "").trim();
        softAssert.assertEquals(currentLexileFromMotivationMenu, currentLexileFromDetails, "  14  The Current Lexile from details page is not the same as from Motivation Menu.");
        getWebDriver().close();
        softAssert.assertAll();

    }

    private String getPreferenceValueFromDB(String login) {
        DatabaseReader dbReader = new DatabaseReader(url());
        String sql = "select * from subscriber_preferences where user_id =(" +
                "select user_id from subscriber where login_name='"+login+"')";
        String result = dbReader.query(sql, "preference_value");
        logger.info("Query result:");
        logger.info("\t" + result);
        return result;
    }


}