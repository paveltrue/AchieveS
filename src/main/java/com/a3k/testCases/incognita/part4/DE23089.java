package com.a3k.testCases.incognita.part4;

import com.a3k.BasicTestCase;
import com.a3k.pages.AllReportsPage;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.ReportPage;
import io.qameta.allure.Step;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DE23089 extends BasicTestCase {

    private AllReportsPage allReportsPage;
    private LoginPage loginPage;
    private HomePage mainPage;
    private ReportPage reportPage;

    @Parameters({"loginTeacherEmpower", "passwordTeacherEmpower", "program", "classToSelectTeacherEmpower"})
    @Test(groups = {"Smoke", "Usage Reports", "Teacher", "All"})
    public void checkYearPerformanceReports(
            @Optional("ukteach11858.alex") String login,
            @Optional("ukteach11858.alex") String password,
            @Optional("KidBiz3000") String program,
            @Optional("Uk Auto Class 3g") String classToSelect) {

        loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, classToSelect);
        mainPage = new HomePage(driver);

        mainPage.goToReportsPage();

        openReportPageAndSelectReport();
        mainPage.closeDoneButtonIfExist();
        checkWordAndSelectYear();

        softAssert.assertAll();
    }

    @Step("Open page with reports and select 'How are my students performing on activities?")
    private void openReportPageAndSelectReport(){
        allReportsPage = new AllReportsPage(driver);

        softAssert.assertTrue(allReportsPage.checkPresenceOfPerformanceReports(),
                "Performance Reports section is NOT present");

        allReportsPage.closeDoneButtonIfExist();
        allReportsPage.openPerformanseReports();

        allReportsPage.closeDoneButtonIfExist();
        softAssert.assertTrue(allReportsPage.checkIfPerformanceReportsExpand(),
                "Performance Reports section is NOT expanded");

        allReportsPage.openHowAreMyStudentsPerformingOnStandards();

        softAssert.assertTrue(allReportsPage.checkSchoolName(),
                "School name is present");
    }

    @Step("Select Year on the report page and open view report page")
    private void checkWordAndSelectYear(){

        logger.debug("Check word Year on the page");
        allReportsPage.closeDoneButtonIfExist();
        allReportsPage.clickViewReportButton();

        if (!allReportsPage.isTextOnPagePresent("There is no data")) {
            softAssert.assertTrue(allReportsPage.checkWordYearInReport(),
                    "Word 'Year' on the page is NOT present");
        }
    }

    @Step("Check word: {word} on the view page")
    private void checkWordOnReportPage(String word){
        reportPage = new ReportPage(driver);

        softAssert.assertTrue(reportPage.checkContainsYear(word),
                "words " + word + " NOT present");
    }
}
