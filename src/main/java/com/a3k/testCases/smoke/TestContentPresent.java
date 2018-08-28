package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestContentPresent extends BasicTestCase {

    private static HomePage homePage;
    private static MyLessons myLessons;

    @Parameters({"login", "password", "className", "program"})
    @Test(dataProvider = "getStudents", groups = {"Smoke", "All"}, invocationCount = 1)
    public void checkNotificationsCenterContent(
            String login,
            String password,
            String className,
            String program) {
        homePage = new HomePage(driver);

        login(login, password, program, className);

        openStudentNotifications();
        verifyNotificationsContent();

    }

    @Test(groups = {"Smoke", "All",
            "Content", "Collections"})
    public void checkCoursesAndCollectionsContent() {
        new LoginPage(driver).loginWithProgram(
                "uskba.alex", "uskba.alex", "KidBiz3000");
        myLessons = new MyLessons(driver);
        homePage = new HomePage(driver);

        openCollections();
        verifyCollectionsTableContent();

        openDayView();
        verifyContentInDayView();

        openWeekView();

        verifyContentInWeekView();
        softAssert.assertAll();
    }

    @Test(groups = {"Smoke", "All", "Grading", "Content"})
    public void checkGrading() {
        login("superteacher.multi", "superteacher.multi", "KidBiz3000",
                "Access Class 3g");
        openGrading();
        verifyGradingLink();
        softAssert.assertAll();
    }

    @Step
    private void verifyNotificationsContent() {
        Assert.assertTrue(new NotificationPage(driver).isNotificationPresent(),
                "Content does not present on Notifications Center page");
        Assert.assertTrue(new NotificationPage(driver).isDateFormatCorrect("us"),
                "Date format on notification page is incorrect");
    }

    @Step
    private void openStudentNotifications() {
        homePage.goToNewUrl("/student_notifications");
    }


    @Step
    private void verifyContentInWeekView() {
        By path = By.xpath("//table[@class='fc-border-separate']");
        Assert.assertTrue(myLessons.isElementPresentBy(path),
                "Problems with content for Week view in Collections");
    }

    @Step
    private void openWeekView() {
        homePage.goToNewUrl("/my_lessons/week");
    }

    @Step
    private void verifyContentInDayView() {
        By path = By.xpath("//*[@class='dayCol currentDay']");
        Assert.assertTrue(myLessons.isElementPresentBy(path),
                "Problems with content for Day view in Collections");
    }

    @Step
    private void openDayView() {
        homePage.goToNewUrl("/my_lessons/day");
    }

    @Step
    private void verifyCollectionsTableContent() {
        By path = By.xpath("//*[@class='collectionTable']");
        Assert.assertTrue(myLessons.isElementPresentBy(path),
                "Problems with content for courses view in Collections");
    }

    @Step
    private void openCollections() {
        homePage.goToNewUrl("/collections");
    }


    @Step
    private void openGrading() {
        new HomePage(driver).goToGradingPage();
    }

    @Step
    private void verifyGradingLink() {
        Grading grading = new Grading(driver);
        softAssert.assertTrue(grading.isByStudentPresent(),
                "Verify that grading link works correct");
    }


    @Step("Login with username {login}, password {password}, program " +
            "{program} " +
            "and class {classToSelect}")
    protected void login(String login,
                         String password,
                         String program,
                         String classToSelect) {
        logger.info(String.format("Login with credentials %s\\%s", login, password));
        new LoginPage(driver).loginWithClassAndProgramIfNeeded(login, password, program, classToSelect);
    }

    @DataProvider
    public Object[][] getStudents(ITestContext context) {
        Object[][] data;

        data = new Object[][]{
                {"teenbizstud.one", "teenbizstud.one", "", "Pro Class 6g"},
                {"empowerstud.one", "empowerstud.one", "", "Empower Class 10g"},
        };

        return data;


    }


    @DataProvider
    public Object[][] getTeachers(ITestContext context) {
        Object[][] data;

        data = new Object[][]{
                {"teenbizteach.one", "teenbizteach.one", "", "Pro Class 6g"},
                {"empowerteach.one", "empowerteach.one", "", "Empower Class " +
                        "10g"},
                {"kidbizteach.one", "kidbizteach.one", "", "Access Class 3g"}
        };

        return data;


    }
}
