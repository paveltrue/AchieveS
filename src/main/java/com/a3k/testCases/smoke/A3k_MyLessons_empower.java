package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import io.qameta.allure.Step;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class A3k_MyLessons_empower extends BasicTestCase {

    static MyLessons myLessonsPage;

    @Parameters({"login", "password", "program", "classToSelect", "language"})
    @Test(dataProvider = "getUsers", groups = {"MyLessons", "Smoke", "All"}, invocationCount = 1)
    public void myLessonsStudent(
            @Optional("kidbizstud.one") String login,
            @Optional("kidbizstud.one") String password,
            @Optional("") String program,
            @Optional("Access Class 3g") String classToSelect,
            @Optional("english") String language) {

        login(login, password, program, classToSelect);
        openMyLessonsPage();
        myLessonsPage = new MyLessons(driver);
        verifyPortfolio(language);
        verifyLessons(language);
        softAssert.assertAll();
    }

    @Step("Login with username {login}, password {password}, program " +
            "{program} " +
            "and class {classToSelect}")
    private void login(String login, String password, String program, String classToSelect) {
        new LoginPage(driver).loginWithClassAndProgramIfNeeded(login, password, program, classToSelect);
    }

    @Step
    private void openMyLessonsPage() {
        logger.info("Opening My Lessons Page");
        new HomePage(driver).goToMyLessonsByLink();
    }

    @Step("Verify portfolio in {language} language")
    private void verifyPortfolio(String language) {
        softAssert.assertTrue(myLessonsPage.checkPortfolio(language),
                "Progress DDl does not contain all necessary elements");
    }

    @Step("Verify lessons in {language} language")
    private void verifyLessons(String language) {

        logger.info("Verify Lessons on Progress Page");
        softAssert.assertTrue(myLessonsPage.checkLessonsOnLessonProgressPage(language),
                "Lessons does not displayed correctly on My Lessons page in 'Lesson Progress' view");

        logger.info("Verify Lessons on Activities Page");
        myLessonsPage.clickOnOptionInPortfolioDDLAndWait(2);
        softAssert.assertTrue(myLessonsPage.checkLessonsOnActivitiesPage(language),
                "Lessons does not displayed correctly on My Lessons page in 'Activities' view");

        logger.info("Verify Lessons on Math page");
        myLessonsPage.clickOnOptionInPortfolioDDLAndWait(3);
        softAssert.assertTrue(myLessonsPage.checkLessonsOnMathPage(language),
                "Lessons does not displayed correctly on My Lessons page in 'Math' view");

        logger.info("Verify Lessons on 'Thought Question & Writing' page");
        myLessonsPage.clickOnOptionInPortfolioDDLAndWait(4);
        softAssert.assertTrue(myLessonsPage.checkLessonsOnThougtQuestionsAndWritingPage(language),
                "Lessons does not displayed correctly on My Lessons page in 'Thought Question & Writting' view");
    }


    @DataProvider
    public Object[][] getUsers(ITestContext context) {

        String suiteName = context.getCurrentXmlTest().getSuite().getName();
        Object[][] data;

        switch (suiteName) {

            case "SmokeTest":
                data = new Object[][]{{"empowerstud.one", "empowerstud.one", "", "Empower Class 10g", "english"},};
                break;

            case "SmokeTestUK":
                data = new Object[][]{{"empowerstud.uk", "empowerstud.uk", "", "", "english"},};
                break;

            case "SpanishTest":
                data = new Object[][]{{"autoempowersp.stud", "autoempowersp.stud", "", "Empower Class", "spanish"},};
                break;

            case "SparkTest":
                data = new Object[][]{{"sparkspanish.stud", "sparkspanish.stud", "", "Spark First Class", "spanish"}};
                break;

            default:
                data = new Object[][]{{"empowerstud.one", "empowerstud.one", "", "Empower Class 10g", "english"},};
        }
        return data;
    }
}
