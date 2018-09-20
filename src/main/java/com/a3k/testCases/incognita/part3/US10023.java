package com.a3k.testCases.incognita.part3;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;

public class US10023 extends BasicTestCase {

    private List<String> actTitlesOfAdvancedOptions;
    private List<String> expTitlesOfAdvancedOptions;
    private HomePage homePage;
    private Search search;
    private List<String> actResourceTypesItems;
    private List<String> expResourceTypesItems;
    private MyLessons myLessonsPage;
    private SearchWidgetPage searchWidgetPage;

    @DataProvider
    public Object[][] getUsers() {
        Object[][] data = new Object[][]{
                {"usteach10023.alex", "usteach10023.alex", "", ""},
                {"ussa10023.alex", "ussa10023.alex", "KidBiz3000", "Auto Kidbiz 3g"},
                {"usda10023.alex", "usda10023.alex", "KidBiz3000", ""},
                {"kb.ref", "kb.ref", "KidBiz3000", ""}
        };
        return data;
    }

    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"Advanced search options", "Incognita", "All"})
    public void check_US10023(@Optional String login,
                              @Optional String password,
                              @Optional String program,
                              @Optional String selectedClass) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
        loginPage.afterLoginCheckWithProgram(program);
        loginPage.afterLoginCheck(selectedClass);

        homePage = new HomePage(driver);
        search = new Search(driver);
        myLessonsPage = new MyLessons(driver);
        searchWidgetPage = new SearchWidgetPage(driver);

        sleep(1500);
        homePage.closeWalkmeNew(4);
        verifyEnglish(login);

        softAssert.assertAll();
    }


    @Step
    private void verifyEnglish(String login) {

        homePage.clickOnSearchButton();
        homePage.search("");

        search.openAdvancedOptions();
        softAssert.assertTrue(search.isAllGradesActive(),
                "The 'All Grades' option item is not active but should be.");

        if (!login.equals("kb.ref")) {
            softAssert.assertTrue(search.isAllCoursesActive(),
                    "The 'All Courses' option item is not active but should be.");
        } else {
            softAssert.assertTrue(search.isSearchByDateActive(),
                    "The 'Search by Date' option item is not active but should be.");
        }

        softAssert.assertTrue(search.isAllTopicsActive(),
                "The 'All Topics' option item is not active but shoul be.");
        softAssert.assertTrue(search.isAllContentActive(),
                "The 'All Content' option item is not active but should be.");

        softAssert.assertTrue(search.isAllLessonTypesActive(),
                "The 'All Lesson Types' option item is not active but should be.");
        softAssert.assertTrue(search.isAllStandardslActive(),
                "The 'All Standards' option item is not active but should be.");

        List<String> actResourceTypesItems = search.getItemsOfAllResourceTypeDDL();
        List<String> expResourceTypesItems = Arrays.asList("All Resource Types", "Lessons", "Teacher Resources");
        softAssert.assertEquals(actResourceTypesItems, expResourceTypesItems, "The items of 'All Resource Types' are wrong.");

        search.selectItemFromAllResourcesDDL("Lessons");

        softAssert.assertTrue(search.isAllGradesActive(),
                "The 'All Grades' option item is not active but should be.");

        if (!login.equals("kb.ref")) {
            softAssert.assertTrue(search.isAllCoursesActive(),
                    "The 'All Courses' option item is not active but should be.");
        } else {
            softAssert.assertTrue(search.isSearchByDateActive(), "The 'Search by Date' option item is not active but should be.");
        }

        softAssert.assertTrue(search.isAllTopicsActive(),
                "The 'All Topics' option item is not active but shoul be.");
        softAssert.assertTrue(search.isAllContentActive(),
                "The 'All Content' option item is not active but should be.");

        softAssert.assertTrue(search.isAllLessonTypesActive(),
                "The 'All Lesson Types' option item is not active but should be.");
        softAssert.assertTrue(search.isAllStandardslActive(),
                "The 'All Standards' option item is not active but should be.");

        search.selectItemFromAllResourcesDDL("Teacher Resources");


        softAssert.assertFalse(search.isAllGradesActive(),
                "The 'All Grades' option item is active but shouldn't be.");

        if (!login.equals("kb.ref")) {
            softAssert.assertFalse(search.isAllCoursesActive(),
                    "The 'All Courses' option item is active but shouldn't be.");
        } else {
            softAssert.assertFalse(search.isSearchByDateActive(),
                    "The 'Search by Date' option item is active but shouldn't be.");
        }

        softAssert.assertFalse(search.isAllTopicsActive(),
                "The 'All Topics' option item is active but shouldn't be.");
        softAssert.assertFalse(search.isAllContentActive(), "The 'All Content' option item is active but shouldn't be.");

        softAssert.assertFalse(search.isAllLessonTypesActive(),
                "The 'All Lesson Types' option item is active but shouldn't be.");
        softAssert.assertFalse(search.isAllStandardslActive(),
                "The 'All Standards' option item is active but shouldn't be.");

        search.goToHomePage();


        if (login.contains("teach")) {

            if (!login.contains("teach")) {
                homePage.changeClassTo("Auto Kidbiz 3g");
            }

            homePage.goToMyLessonsPage();

            while (!myLessonsPage.isDisplayedBy(By.xpath(".//div[@id = 'moreLessonsContainer']//div[contains(@class, 'ml_view')]"))) {
                myLessonsPage.openSearchWidgetPanel();
            }

            searchWidgetPage.clickOnAdvancedOptionsBy();

            softAssert.assertFalse(searchWidgetPage.getAdvancedOptionsDefaultValues().contains("All Resources Types"), "The 'All Resources Types' option is present in advanced options.");

            search.goToHomePage();

            homePage.clickOnSearchButton();
            homePage.search("");

            search.openAdvancedOptions();
            search.openAllCoursesDDL();

            search.selectItemFromAllCoursesDDL("News");
            search.clickSearchNew();

            softAssert.assertEquals(search.getSelectedOptionFromAllResouceTypesDDL(), "Lessons", "The selected value of 'All Resources Types' is wrong.");

        }
    }


}
