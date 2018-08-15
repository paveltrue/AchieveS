
package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import io.qameta.allure.Step;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.title;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class A3k_Home extends BasicTestCase {

    protected static String kib = "Career Center Writing Center Stock Market";
    protected static String emp = "Career Center Writing Center Biology Stock Market";
    protected static String x = "Career Center Writing Center Biology Rains Stock Market";
    protected static String spark;

    @Parameters({"login", "password", "classToSelect", "language", "testName"})
    @Test(dataProvider = "getUsers", groups = {"Smoke", "Home Page",
            "Student", "All"})
    public void studentHomePage(
            @Optional("kidbizstud.two") String login,
            @Optional("kidbizstud.two") String password,
            @Optional("Pro Class 6g") String classToSelect,
            @Optional("english") String language,
            @Optional("smoke") String testName) {
        String newPassword = "test1";
        setUpLanguage(language);

        LoginPage loginPage = new LoginPage();
        loginPage.loginWithTwoPasswordsWithDefaultClass(login, password, newPassword);

        HomePage homePage = new HomePage(getWebDriver());


        homePage.openLesson();

        Assert.assertTrue(title().contains("Lesson")
                        || title().contains("Lección"),
                "Lesson page" +
                        " does not opened");

        homePage.clickOnLogo();
        homePage.changePassword(password, newPassword);
        homePage.goToInitialUrl();

        logger.info("Re-login with new password");

        String validPassword = loginPage.loginWithTwoPasswordsWithDefaultClass(login, password, newPassword);

        softAssert.assertTrue(homePage.isArrowNearAvatarPresent(),
                "Failed to re-login with new password \"" + validPassword + "\"");

        softAssert.assertAll();
    }

    @Parameters({"loginTeacher", "passwordTeacher", "classToSelectTeacher", "language", "testName"})
    @Test(dataProvider = "getUsers", groups = {"Smoke", "Home Page",
            "Teacher", "All"})
    public void teacherHomePage(
            @Optional("kidbizteach.one") String login,
            @Optional("kidbizteach.one") String password,
            @Optional("Access Class 3g") String classToSelect,
            @Optional("english") String language,
            @Optional("smoke") String testName) {

        setUpLanguage(language);
        LoginPage loginPage = new LoginPage();
        loginPage.login(login, password, classToSelect);

        HomePage homePage = new HomePage(getWebDriver());

        if (homePage.isTeachermaterialsButtonsExist()) {
            softAssert.assertTrue(homePage.checkWorkOfStartLessonButton(),
                    "Failed to start Lesson from Home Page");
        } else {
            String lessonName = homePage.getLessonTitleText();
            homePage.openLesson();
            softAssert.assertTrue(homePage.getArticleTitleText().contains(lessonName),
                    "wrong lesson is opened." +
                            "Expected: " + lessonName +
                            ". Actual: " + homePage.getArticleTitleText());
            homePage.goToHomePage();
        }

        softAssert.assertTrue(homePage.isLessonChangeCorrect(),
                "Lessons was not changed correctly");

        if (!testName.equals("spark")) {
            logger.info("Verify that Class ddl not contains 'All Class' " + "option");
            softAssert.assertTrue(homePage.checkElementsOfClassDdlTeacher(),
                    "Class ddl not contains \"All Class\" option");

            logger.info("Verify that Grade ddl not contains 'All Grade' " + "option");
            softAssert.assertTrue(homePage.checkElementsOfGradeDdlTeacher(),
                    "Grade ddl not contains 'All Grade' option");
        }

        logger.info("Verify that Class ddl work correct");
        softAssert.assertTrue(homePage.checkWorkClassDDLForTeacher(),
                "Verify that Class ddl work correct");

        if (!testName.equals("spark"))
            logger.info("Verify that Language ddl work correct and contains " + "correct elements");
        softAssert.assertTrue(homePage.checkLanguageDDl(),
                "Language ddl does not contain correct elements");

        softAssert.assertAll();
    }

    @Step("Setup language {language}")
    private static void setUpLanguage(String language) {
        logger.info("Language set up " + language);
        if (language.equals("english")) {
            kib = "Career Center Writing Center Stock Market";
            emp = "Career Center Writing Center Biology Stock Market";
            x = "Career Center Writing Center Biology Rains Stock Market";
            spark = "Career Center Writing Center Biology Stock Market";
        } else {
            kib = "Centro laboral Centro de escritura Bolsa de valores";
            emp = "Centro laboral Centro de escritura Biología Rains Bolsa de valores";
            x = "Centro laboral Centro de escritura Biología Bolsa de valores";
            spark = "Centro laboral Centro de escritura Biología Rains Bolsa de valores";

        }
    }


    @DataProvider
    public Object[][] getUsers(ITestContext context) {

        String suiteName = context.getCurrentXmlTest().getSuite().getName();
        Object[][] data;

        switch (suiteName) {

            case "SmokeTest":
                data = new Object[][]{
                        {"teenbizstud.one", "teenbizstud.one", "Pro Class 6g", "english", "smoke"},
                        {"empowerstud.one", "empowerstud.one", "Empower Class 10g", "english", "smoke"},
                        {"kidbizstud.one", "kidbizstud.one", "Access Class 3g", "english", "smoke"}
                };
                break;

            case "SmokeTestUK":
                data = new Object[][]{
                        {"studentteenbizuk", "studentteenbizuk", "", "english", "smoke"},
                        {"studentempoweruk", "studentempoweruk", "", "english", "smoke"},
                        {"studentkidbizuk", "studentkidbizuk", "", "english", "smoke"}
                };
                break;

            case "SpanishTest":
                data = new Object[][]{
                        {"studentteenbizsp", "studentteenbizsp", "Teenbiz Class", "spanish", "spanish"},
                        {"studentempowersp", "studentempowersp", "Empower Class", "spanish", "spanish"},
                        {"studentkidbizsp", "studentkidbizsp", "Kidbiz Class", "spanish", "spanish"}
                };
                break;

            case "SparkTest":
                data = new Object[][]{
                        {"studentspark", "studentspark", "Spark First Class", "english", "spark"},
                        {"studentsparksp", "studentsparksp", "Spark First Class", "spanish", "spark"}
                };
                break;

            default:
                data = new Object[][]{
                        {"teenbizstud.one", "teenbizstud.one", "Pro Class 6g", "english", "smoke"},
                        {"empowerstud.one", "empowerstud.one", "Empower Class 10g", "english", "smoke"},
                        {"kidbizstud.one", "kidbizstud.one", "Access Class 3g", "english", "smoke"}
                };
        }
        return data;
    }
}
