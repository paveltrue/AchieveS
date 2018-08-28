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

public class A3k_Thumbnails extends BasicTestCase {

    private static MyLessons myLessonsPage;

    @Parameters({"login", "password", "program", "classToSelect", "language"})
    @Test(dataProvider = "getUsers", groups = {"Smoke", "MyLessons",
            "Thumbnails", "Student", "All"}, invocationCount = 1)
    public void testStudentThumbnails(
            @Optional("kidbizstud.one") String login,
            @Optional("kidbizstud.one") String password,
            @Optional("") String program,
            @Optional("Access Class 3g") String classToSelect,
            @Optional("english") String language) {

        login(login, password, program, classToSelect);

        openMyLessonPage();

        verifyLessonsDisplayedCorrectly(language);

        softAssert.assertAll();
    }

    @Step
    private void verifyLessonsDisplayedCorrectly(String language) {
        int pageNumber = 1;
        softAssert.assertTrue(myLessonsPage.checkLessonsOnLessonProgressPage(language, pageNumber),
                "Lessons displayed incorrectly on My Lesson " + pageNumber + " page");

    }

    @Step("Login with username {login}, password {password}, program " +
            "{program} " +
            "and class {classToSelect}")
    private void login(String login, String password, String program, String classToSelect) {
        logger.info(String.format("Login with credentials %s/%s to class %s", login, password, classToSelect));
        new LoginPage(driver).loginWithClassAndProgramIfNeeded(login, password, program, classToSelect);
    }

    @Step
    private void openMyLessonPage() {
        logger.info("Open My Lessons Page");
        new HomePage(driver).goToMyLessonsByLink();

        myLessonsPage = new MyLessons(driver);
        myLessonsPage.goToFirstPageAndWait();

    }

    @DataProvider
    public Object[][] getUsers(ITestContext context) {

        String suiteName = context.getCurrentXmlTest().getSuite().getName();
        Object[][] data;

        switch (suiteName) {

            case "SmokeTest":
                data = new Object[][]{{"empowerstud.one", "empowerstud.one", "", "Empower Class 10g", "english"},
                        {"teenbizstud.one", "teenbizstud.one", "", "Pro Class 6g", "english"},
                        {"kidbizstud.one", "kidbizstud.one", "", "Access Class 3g", "english"}
                };
                break;

            case "SmokeTestUK":
                data = new Object[][]{{"teenbizstud.uk", "teenbizstud.uk", "", "", "english"},
                        {"empowerstud.uk", "empowerstud.uk", "", "", "english"},
                        {"kidbizstud.uk", "kidbizstud.uk", "", "", "english"}
                };
                break;

            case "SpanishTEst":
                data = new Object[][]{{"autoempowersp.stud", "autoempowersp.stud", "", "Empower Class", "spanish"},
                        {"autoteenbizsp.stud", "autoteenbizsp.stud", "", "Second Teenbiz Class", "spanish"},
                        {"autokidbizsp.stud", "autokidbizsp.stud", "", "Kidbiz Class", "spanish"}
                };
                break;

            case "SparkTest":
                data = new Object[][]{{"sparkstudent.one", "sparkstudent.one", "", "Spark First Class", "english"},
                        {"sparkspanish.stud", "sparkspanish.stud", "", "Spark First Class", "spanish"}
                };
                break;

            default:
                data = new Object[][]{{"empowerstud.one", "empowerstud.one", "", "Empower Class 10g", "english"},
                        {"teenbizstud.one", "teenbizstud.one", "", "Pro Class 6g", "english"},
                        {"kidbizstud.one", "kidbizstud.one", "", "Access Class 3g", "english"}
                };
        }
        return data;
    }
}
