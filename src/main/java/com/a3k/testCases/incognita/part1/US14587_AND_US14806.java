package com.a3k.testCases.incognita.part1;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.pages.SearchWidgetPage;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;


public class US14587_AND_US14806 extends BasicTestCase {


    private HomePage homePage;
    private SearchWidgetPage searchWidget;
    private MyLessons myLessons;
    private List<Boolean> actStatusOfHeaders;
    private List<Boolean> expStatusOfHeaders;

    @DataProvider
    public Object[][] getUsers() {
        Object[][] data = new Object[][]{
                {"ussa14587.alexnoclass", "ussa14587.alexnoclass", "KidBiz3000", ""},
                {"kb.ref", "kb.ref", "KidBiz3000", ""}
        };
        return data;
    }


    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"My Lessons", "Incognita", "All"}, invocationCount = 1)
    public void check_US14587_AND_US14806(@Optional("ussa10023.alex") String login,
                                          @Optional("ussa10023.alex") String password,
                                          @Optional("") String program,
                                          @Optional("") String selectedClass) {

        login(login, password, program, selectedClass);
        goToMyLessonsPageAndCreateCollection();

        checkingOfCollapsingAndExpandingOfSectionHeaders();
        checkingAdvancedOptionsDDLOnSearchWidgetButton();

        checkingFavoritesTab();

        softAssert.assertAll();
    }

    @Step
    private void checkingFavoritesTab() {
        searchWidget.clickOnFavoritesTab();
        softAssert.assertTrue(searchWidget.isAllFavoritesDDLDisplayedBy(),
                "The favorites tab is not open / open incorrect.");

        searchWidget.clickOnNewForYouTab();
        softAssert.assertTrue(searchWidget.isPeriodDDLDisplayedBy(),
                "The New For You tab is not open / open incorrect.");
    }

    @Step
    private void checkingAdvancedOptionsDDLOnSearchWidgetButton() {
        searchWidget = new SearchWidgetPage(driver);
        searchWidget.clickOnTab("Search");
        searchWidget.enterTextInSearchBar("earth");

        searchWidget.clickOnSearchButtonByText();
        softAssert.assertFalse(searchWidget.isAdvancedOptionsDDLsDisplayedBy(),
                "The advanced options are displayed on search widget," +
                        " but shouldn't be.");


        searchWidget.clickOnAdvancedOptionsBy();
        softAssert.assertTrue(searchWidget.isAdvancedOptionsDDLsDisplayedBy(),
                "The advanced options are not displayed on search widget," +
                        " but should be.");


        searchWidget.selectOptionFromAllGradesDDL("2");
        searchWidget.clickOnSearchButtonByText();

        searchWidget.clickOnAdvancedOptionsBy();
        softAssert.assertTrue(searchWidget.isAdvancedOptionsDDLsDisplayedBy(),
                "The advanced options are  displayed after click on search button.");
    }

    @Step
    private void
    checkingOfCollapsingAndExpandingOfSectionHeaders() {
        myLessons.collapseSectionHeaders();
        actStatusOfHeaders = myLessons.isSectionHeadersOpen();

        expStatusOfHeaders = Arrays.asList(false, false, false);
        softAssert.assertEquals(actStatusOfHeaders, expStatusOfHeaders,
                "The sections of headers is/are expanded, but should be collapsed.");


        myLessons.expandSectionHeaders();
        actStatusOfHeaders = myLessons.isSectionHeadersOpen();

        expStatusOfHeaders = Arrays.asList(true, true, true);
        softAssert.assertEquals(actStatusOfHeaders, expStatusOfHeaders,
                "The sections of headers is/are collapsed, but should be expanded.");


        myLessons.clickOnAddLessonButton();
        actStatusOfHeaders = myLessons.isSectionHeadersOpen();

        expStatusOfHeaders = Arrays.asList(false, true, true);
        softAssert.assertEquals(actStatusOfHeaders, expStatusOfHeaders,
                "The status of header section is wrong." +
                        " The 1-st section should be collapsed," +
                        " the other should be expanded.");
    }

    @Step
    private void login(String login, String password, String program, String selectedClass) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeeded(login, password, program, selectedClass);
    }

    @Step
    private void goToMyLessonsPageAndCreateCollection() {
        homePage = new HomePage(driver);
        myLessons = homePage.goToMyLessonsPage();

        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickOnCreateCollectionBy();
    }
}
