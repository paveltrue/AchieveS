package com.a3k.testCases.incognita.part4;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import com.a3k.utils.DateHelper;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class US25260 extends BasicTestCase {

    @DataProvider
    public Object[][] getUsersForOneLesson(){
        Object[][] objects ={
                {"samasoni.asaeli", "good", "KidBiz3000", "english (us) ", "Faga'itua High School", "Lexiles", ""},
                {"ukteach11858.alex", "ukteach11858.alex", "KidBiz3000", "english (uk)", "", "", ""},
                {"samasoni.asaeli", "good", "KidBiz3000", "english (us) ", "Faga'itua High School", "Lexeli change", ""},
                {"kaylla.turituri.1","kid","", "spanish", "", "", ""},
                {"kb.ref", "kb.ref", "KidBiz3000", "english (us) ", "AUTO QA School - do not use", "", ""}
                    };
        return objects;
    }

    private String dateReports;
    private String dateFormatPreReport;
    private String dateFormatReportTitle;
    private String dateFormatReport;
    private Locale locale;

    private ReportPage reportPage;
    private static String reportTypeLexiles = "Lexiles";

    @Test(dataProvider = "getUsersForOneLesson")
    public void checkReportsData(@Optional("kb.ref") String login,
                                 @Optional("joke") String password,
                                 @Optional("KidBiz3000") String program,
                                 @Optional("english") String language,
                                 @Optional("") String school,
                                 @Optional("") String reportType,
                                 @Optional("") String nameClass) {

        init(login);

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, nameClass);

        HomePage homePage = new HomePage(driver);

        homePage.closeDoneButtonIfExist();
        homePage.changeLanguageIfNeed(language);

        homePage.goToReportsPage();

        openPreReportPage(login, reportType);

        if (login.equals("kbst.t")) {
            reportPage = new ReportPage(driver);
            reportPage.switchToNextWindowWhenExistOnly2();
            verifyDate(dateFormatReport, reportPage.getDateWelcomeLetter(), locale);
            return;
        }

        MyLessonsActivitiesPage myLessonsActivitiesPage = new MyLessonsActivitiesPage(driver);

        if (!school.isEmpty()) {
            myLessonsActivitiesPage.selectSchool(school);
        }
//        if (login.equals("uk.t")) {
//            myLessonsActivitiesPage.setYeardDDL("6th Year");
//        }

        logger.info(myLessonsActivitiesPage.getStartDate() + " Start date");

        verifyDate(dateFormatPreReport, myLessonsActivitiesPage.getStartDate(), locale);
        logger.info(myLessonsActivitiesPage.getEndDate() + " Stop date");

        Calendar calendarStart = Calendar.getInstance(locale);

        calendarStart.add(Calendar.MONTH, -2);
        Calendar calendarEnd = Calendar.getInstance(locale);
        calendarEnd.add(Calendar.DAY_OF_MONTH, -2);

        verifyDate(dateFormatPreReport, myLessonsActivitiesPage.getEndDate(), locale);

        myLessonsActivitiesPage.setStartDate(calendarStart.get(Calendar.DAY_OF_MONTH) + "",
                calendarStart.get(Calendar.MONTH) + 1, calendarStart.get(Calendar.YEAR) + "");
        myLessonsActivitiesPage.setEndDate(calendarEnd.get(Calendar.DAY_OF_MONTH) + "",
                calendarEnd.get(Calendar.MONTH) + 1, calendarEnd.get(Calendar.YEAR) + "");

        myLessonsActivitiesPage.closeDoneButtonIfExist();
        myLessonsActivitiesPage.clickViewReportButton();

        myLessonsActivitiesPage.switchToNextWindowWhenExistOnly2();

        reportPage = new ReportPage(driver);
        dateReports = reportPage.getTextOfDateLineInReport();

        String[] dateParts = dateReports.split(" - ");

        String startDate = dateParts[0];
        String endDate = dateParts[1];

        verifyDate(dateFormatReportTitle, startDate, locale);
        verifyDate(dateFormatReportTitle, endDate, locale);

        SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormatReportTitle, locale);

        if (reportType.equals("Lexile change")) {
            calendarStart.set(Calendar.DAY_OF_MONTH, 1);
        }

        checkDateOnReportsPage(login, reportType);
        softAssert.assertAll();
        getWebDriver().close();
    }

    private void init(String login) {
        switch (login) {
            case ("kbst.t"):
                dateFormatReport = "MMMM d, yyyy";
                locale = Locale.US;
                break;
            case ("kb.ref"):
                dateFormatPreReport = "MMMM d yyyy";
                dateFormatReport = "M/d/yyyy K:m a";
                dateFormatReportTitle = "MMMM d, yyyy";
                locale = Locale.US;
                break;
            case ("kaylla.turituri.1"):
                dateFormatPreReport = "d MMMM yyyy";
                dateFormatReport = "MMM-d";
                dateFormatReportTitle = "d 'de' MMMM 'de' yyyy";
                locale = new Locale("es", "ES");
                break;
            default: //("samasoni.asaeli") :
                dateFormatPreReport = "d MMMM yyyy";
                dateFormatReport = "MMMM yyyy";
                dateFormatReportTitle = "d MMMM yyyy";
                locale = Locale.US;
                break;
        }
    }

    @Step
    private void checkDateOnReportsPage(String login, String reportType) {
        switch (login) {
            case ("kb.ref"):
                reportPage.openClassWithDate();
                logger.info(reportPage.getTextOfDateLineInReport());
                verifyDate(dateFormatReport, reportPage.getActivityDate(), locale);
                break;
            case ("kaylla.turituri.1"):
                verifyDate(dateFormatReport, reportPage.getListStartDate().get(0), locale);
                verifyDate(dateFormatReport, reportPage.getListEndDate().get(0), locale);

                break;
            case ("samasoni.asaeli"): {
                if (reportType.equals(reportTypeLexiles)) {
                    verifyDate("d-MMM-yy", reportPage.getClassDate(), locale);
                } else {
                    for (String date : reportPage.getListTitleRowDate()) {
                        verifyDate(dateFormatReport, date, locale);
                    }
                }
                break;
            }
            case ("ukteach11858.alex"): {
                reportPage.clickAsignButton();
                reportPage.switchToNextWindowWhenExistOnly2();
                verifyDate(dateFormatReportTitle, reportPage.getDateActivite(), locale);
            }
        }
    }

    @Step
    private void openPreReportPage(String login, String reportType) {
        switch (login) {
            case ("kb.ref"):
                preReportAdmin();
                break;
            case ("kaylla.turituri.1"):
                goToUsageReports();
                break;
            case ("samasoni.asaeli"):
                if (reportType.equals(reportTypeLexiles)) {
                    goToReportsAdjustedLexiles();
                } else {
                    goToPerformanseReportElement("How has Lexile performance changed over time?");
                }
                break;
            case ("ukteach11858.alex"):
                goToPerformanseReportElement("How are my students performing on standards?");
                break;
            case ("kbst.t"):
                goToWelcomeLetter();
                break;

        }
    }

    private void goToPerformanseReportElement(String text) {
        AllReportsPage allReportsPage = new AllReportsPage(driver);
        allReportsPage.openPerformanseReports();
        allReportsPage.clickLinkByText(text);
    }

    private void goToWelcomeLetter() {
        AllReportsPage allReportsPage = new AllReportsPage(driver);
        allReportsPage.openHomeCommunication();
        allReportsPage.clickLinkByText("Welcome letter");
        allReportsPage.selectClassByNameWithoutClick("TB Access Gr 7");
        allReportsPage.selectUserForReport("S, Tbacs");
        allReportsPage.selectLanguageDDL("Students' Default Language");
        allReportsPage.clickViewReportButton();
    }

    private void goToUsageReports() {
        AllReportsPage allReportsPage = new AllReportsPage(driver);
        allReportsPage.openUsageReports();
        allReportsPage.clickLinkByText("¿Cuáles de mis estudiantes están usando el programa?");
    }

    private void goToReportsAdjustedLexiles() {
        AllReportsPage allReportsPage = new AllReportsPage(driver);
        allReportsPage.openAssessmentTools();
        allReportsPage.clickLinkByText("Lexiles have been adjusted?");
    }

    private void preReportAdmin() {
        AddDistrictPage addDistrictPage = new AddDistrictPage(driver);
        addDistrictPage.selectDistrictByValue("3143");
        AllReportsPage allReportsPage = new AllReportsPage(driver);
        allReportsPage.openStudentWork();
        allReportsPage.chooseItemInMyLessonsDdl("Activities");
    }

    @Step
    private void verifyDate(String format, String date, Locale locale) {
        boolean valid;

        if((date.trim()).equals("") || (date.trim()).equals(" ")){
            return;
        }
        valid = DateHelper.isValidFormat(format,
                date, locale);
        softAssert.assertTrue(valid, "Date doesn't match expected. " +
                "Expected date should be matching format " + format + ". " +
                "Actual date: " + date);
    }
}
