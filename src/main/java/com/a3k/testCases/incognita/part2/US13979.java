package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.FavoritesPage;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class US13979 extends BasicTestCase {

    private LoginPage loginPage;
    private HomePage homePage;
    private String actIconText;
    private String expIconText;
    private FavoritesPage favoritesPage;
    private String actTextOfTitlePage;
    private String expTextOfTitlePage;


    @DataProvider
    public Object[][] getUsers() {
        Object[][] data = new Object[][]{
                {"kidbizteach.one", "kidbizteach.one", "", "Access Class 3g"},
                {"ussa13443.alex", "ussa13443.alex", "KidBiz3000", "Alex Auto Kidbiz 3 Pro"},
                {"usda13443.alex", "usda13443.alex", "KidBiz3000", ""},
                {"kb.ref", "kb.ref", "KidBiz3000", ""}
        };
        return data;
    }

    @DataProvider
    public Object[][] getUsersUK() {
        Object[][] data = new Object[][]{
                {"ukteach9597.alex", "ukteach9597.alex", "", "Uk Auto Class 3g"},

        };
        return data;
    }

    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"Favorites Page", "Incognita", "All"})
    public void check_US13979(@Optional String login,
                              @Optional String password,
                              @Optional String program,
                              @Optional String selectedClass) {

        loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
        loginPage.afterLoginCheck(selectedClass);

        homePage = new HomePage(driver);
        actIconText = homePage.getFavoritesIcon().getAttribute("title").trim();

        expIconText = "Favorites";

        softAssert.assertEquals(actIconText, expIconText, "The icon text is wrong.");

        homePage.clickFavoritesIcon();
        favoritesPage = new FavoritesPage(driver);

        actTextOfTitlePage = favoritesPage.getTitleOfPage().getText().trim();

        expTextOfTitlePage = "Favorites";

        softAssert.assertEquals(actTextOfTitlePage, expTextOfTitlePage, "The title of Favorites page is wrong.");

        favoritesPage.goToHomePage();
        softAssert.assertAll();

    }

    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsersUK", groups = {"Favorites Page", "Incognita", "All"})
    public void check_US13979_UK(@Optional String login,
                                 @Optional String password,
                                 @Optional String program,
                                 @Optional String selectedClass) {

        loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
        loginPage.afterLoginCheck(selectedClass);

        homePage = new HomePage(driver);
        actIconText = homePage.getFavoritesIcon().getAttribute("title").trim();

        expIconText = "Favourites";

        softAssert.assertEquals(actIconText, expIconText, "The icon text is wrong.");

        homePage.clickFavoritesIcon();
        favoritesPage = new FavoritesPage(driver);

        actTextOfTitlePage = favoritesPage.getTitleOfPage().getText().trim();

        expTextOfTitlePage = "Favourites";

        softAssert.assertEquals(actTextOfTitlePage, expTextOfTitlePage, "The title of Favorites page is wrong.");

        favoritesPage.goToHomePage();
        softAssert.assertAll();

    }


}

