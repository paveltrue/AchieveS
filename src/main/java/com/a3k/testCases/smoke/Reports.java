package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$$;


public class Reports extends BasicTestCase {

    String[] DDLs = {"programs", "class"};

    List<String> specRepo = new ArrayList<String>();
    List<String> bannedRepo = new ArrayList<String>();

    String welcomeLetterEn = "Dear Parent/Guardian,\nYour child is participating in Achieve3000's LevelSet, an online assessment that utilizes the Lexile® Framework for Reading. It offers a scientific means of matching students to informational text. Developed by Achieve3000 in conjunction with MetaMetrics Inc., this summative assessment provides a measure of student ability to read and comprehend non-fiction texts.\nAvailable in English and Spanish, LevelSet is the only assessment of nonfiction Lexile reading abilities. It enables students to experience expository content that's tailored to their individual levels without extensive, time-consuming tests.\nHere's how it works:\nTo begin the LevelSet assessment, students simply log into Achieve3000 Solutions https://portal.achieve3000.com/ using their pre-assigned usernames and passwords.";

    String welcomeLetterSp = "Estimados padres o tutores:\nSu hijo(a) está participando en el programa LevelSet de Achieve3000, una evaluación en línea que utiliza el método Lexile® Framework para la lectura. Ofrece un método científico para determinar el nivel de textos informativos apropiados para cada estudiante. Desarrollada por Achieve3000 conjuntamente con MetaMetrics Inc., esta evaluación sumativa provee una medición de la habilidad lectora y el nivel de comprensión del estudiante de los textos informativos.\nDisponible tanto en inglés como en español, la prueba LevelSet es la única evaluación para determinar el nivel Lexile y la habilidad lectora de textos informativos. Permite que el estudiante experimente contenidos de lectura expositiva \"hechos a la medida\" de su nivel de aprendizaje individual, sin pruebas extensas que requieren demasiado tiempo.\nAsí es como funciona:\nPara tomar la prueba LevelSet, los estudiantes sencillamente ingresan a Achieve3000 https://portal.achieve3000.com/ usando su nombre de usuario y contraseña.";

    @Step
    @Parameters({"login", "password", "classToSelect"})
    @Test(dataProvider = "", groups = {"Refactoring", "Reports", "All"}, invocationCount = 3)
    public void studentWork(
            @Optional("accessboost.teach") String login,
            @Optional("accessboost.teach") String password,
            @Optional("Access Class 3g") String classToSelect) {


        specRepo.add("Authentic Assessment Portfolio");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoWithClass(login, password, classToSelect);

        HomePage home = new HomePage(driver);

        home.goToReportsPage();

        AllReportsPage allReportsPage = new AllReportsPage(driver);

        allReportsPage.openStudentWork();

        List<String> reportLinks = allReportsPage.getReportLinksInStudentWorkSection();

        for (String reportLink : reportLinks) {
            logger.info("Verifying report " + reportLink);

            allReportsPage.openReportByName(reportLink);

            verifyReport();
        }

        ElementsCollection reportDDLs = allReportsPage.getReportDDLsInStudentWorkSection();


        for (int i = 0; i > reportDDLs.size(); i++) {


            WebElement reportDDL = $$(reportDDLs).get(i);

            List<String> reportsInDDL = allReportsPage.getReportNamesFromReportDDL(reportDDL);

            for (String report : reportsInDDL) {

                logger.info("Verifying report " + report);

                allReportsPage.selectFromDDL(reportDDL, report);

                verifyReport();

                reportDDLs = allReportsPage.getReportDDLsInStudentWorkSection();

                reportDDL = $$(reportDDLs).get(i);

            }

        }

        bannedRepo.clear();
        specRepo.clear();
        softAssert.assertAll();
    }

    @Step
    @Parameters({"login", "password", "classToSelect"})
    @Test(dataProvider = "", groups = {"Refactoring", "Reports", "All"})
    public void usageReports(
            @Optional("accessboost.teach") String login,
            @Optional("accessboost.teach") String password,
            @Optional("Access Class 3g") String classToSelect) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoWithClass(login, password, classToSelect);

        HomePage home = new HomePage(driver);

        home.goToReportsPage();

        AllReportsPage allReportsPage = new AllReportsPage(driver);

        allReportsPage.openUsageReports();

        List<String> reportLinks = allReportsPage.getReportLinksInUsageReportsSection();

        for (String reportLink : reportLinks) {

            logger.info("Verifying report " + reportLink);

            allReportsPage.openReportByName(reportLink);

            verifyReport();
        }

        bannedRepo.clear();
        specRepo.clear();
        softAssert.assertAll();
    }

    @Step
    @Parameters({"login", "password", "classToSelect"})
    @Test(dataProvider = "", groups = {"Refactoring", "Reports", "All"})
    public void performanceReports(@Optional("accessboost.teach") String login,
                                   @Optional("accessboost.teach") String password,
                                   @Optional("Access Class 3g") String classToSelect) {

        bannedRepo.add("How can I differentiate my instruction based on NWEA MAP " +
                "assessment results?");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoWithClass(login, password, classToSelect);

        HomePage home = new HomePage(driver);

        home.goToReportsPage();

        AllReportsPage allReportsPage = new AllReportsPage(driver);

        allReportsPage.openPerformanseReports();

        List<String> reportLinks = allReportsPage.getReportHrefsInPerformanceReportsSection();

        for (String reportLink : reportLinks) {

            logger.info("Verifying report " + reportLink);
            if (!bannedRepo.contains(reportLink)) {

                allReportsPage.openReportByHref(reportLink);
                verifyReport();
            }

            bannedRepo.clear();
            specRepo.clear();
            softAssert.assertAll();
        }
    }

    @Step
    @Parameters({"login", "password", "classToSelect"})
    @Test(dataProvider = "", groups = {"Refactoring", "Reports", "All"})
    public void assessmentTools(@Optional("accessboost.teach") String login,
                                @Optional("accessboost.teach") String password,
                                @Optional("Access Class 3g") String classToSelect) {

        bannedRepo.add("Which of my students have not taken LevelSet?");
        bannedRepo.add("Possibly Invalid LevelSet Administrations");
        bannedRepo.add("Students with Decreasing Lexile Levels");

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginIntoWithClass(login, password, classToSelect);

        HomePage home = new HomePage(driver);

        home.goToReportsPage();

        AllReportsPage allReportsPage = new AllReportsPage(driver);

        allReportsPage.openAssessmentTools();

        List<String> reportLinks = allReportsPage.getReportLinksInAssessmentToolsSection();

        for (String reportLink : reportLinks) {

            logger.info("Verifying report " + reportLink);

            if (!bannedRepo.contains(reportLink)) {

                allReportsPage.openReportByName(reportLink);

                verifyReport();
            }

        }

        bannedRepo.clear();
        specRepo.clear();
        softAssert.assertAll();
    }

    @Step
    private void verifyReport() {

        PreReportPage ppp = new PreReportPage(driver);

        ReportPage rp = new ReportPage(driver);

        String title = ppp.getReportTitleText();

        if (!bannedRepo.contains(title)) {

            for (String ddlName : DDLs) {
                if (!specRepo.contains(title) && !ddlName.equals("class"))
                    softAssert.assertTrue(ppp.isAllOptionsPresentInDDL(ddlName),
                            "All " + ddlName + " option is not present in report " + title);

                softAssert.assertTrue(ppp.isDDLHasOtherOptions(ddlName),
                        "Which " + ddlName + " DDL does not have any other programs in report " + title);

            }

            List<String> listOfClasses = ppp.getListOfClasses();

            for (String className : listOfClasses) {

                ppp.selectClassFromDDL(className);

                List<String> listOfUsers = ppp.getListOfUsers();

                ppp.waitForPageToLoad();

                if (listOfUsers.size() > 0) {
                    ppp.selectUserFromDDL(listOfUsers.get(0));

                }
                ppp.scrollDown();
                ppp.clickViewReportButton();

                ppp.switchToNextWindow();

                int j = 0;
                while (!rp.isTitlePresent()) {
                    rp.switchBack();
                    ppp.clickViewReportButton();
                    ppp.switchToNextWindow();
                    j = j + 1;
                    if (j < 2)
                        break;
                }

                ppp.switchBack();
            }
        }

        ppp.clickOnBackToMenu();
    }

    @Step
    @Parameters({"login", "password", "classToSelect", "program"})
    @Test(dataProvider = "", groups = {"Refactoring", "Reports", "All"})
    public void welcomeLetter(
            @Optional("lssateach.two") String login,
            @Optional("lssateach.two") String password,
            @Optional("LevelSet") String program,
            @Optional("Lssa First Class 6g") String clazz) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.loginWithProgram(login, password, program);

        HomePage home = new HomePage(driver);


        home.goToReportsPage();


        AllReportsPage allReportsPage = new AllReportsPage(driver);

        allReportsPage.openHomeCommunication();

        allReportsPage.openWelcomeLetter();

        PreReportPage ppp = new PreReportPage(driver);

        WelcomeLetter wl = new WelcomeLetter(driver);

        String title = ppp.getReportTitleText();


        for (String ddlName : DDLs) {
            if (!specRepo.contains(title) && !ddlName.equals("class"))
                softAssert.assertTrue(ppp.isAllOptionsPresentInDDL(ddlName),
                        "All " + ddlName + " option is not present in report " + title);

            softAssert.assertTrue(ppp.isDDLHasOtherOptions(ddlName),
                    "Which " + ddlName + " DDL does not have any other programs in report " + title);

        }

        List<String> listOfClasses = ppp.getListOfClasses();

        ppp.selectProgramByValueFromDDL("10");

        List<String> listOfLSSAClasses = ppp.getListOfClasses();

        softAssert.assertTrue(listOfClasses.containsAll(listOfLSSAClasses),
                "All LSSA classes aren't matching");

        ppp.selectClassFromDDL(clazz);

        ppp.selectLanguageByValueFromDDL("1");

        ppp.clickViewReportButton();

        ppp.switchToNextWindow();

        softAssert.assertTrue(wl.getAllLevelSetHeaders().size() == wl.getAllUsersInformatio().size(),
                "The reports' LSSA logos for each student is present [English]");

        softAssert.assertTrue(wl.getFirstMessageText().contains(welcomeLetterEn),
                "The text of the letter is present [English]");

        ppp.switchBack();

        ppp.selectLanguageByValueFromDDL("2");

        ppp.clickViewReportButton();

        ppp.switchToNextWindow();

        softAssert.assertTrue(wl.getAllLevelSetHeaders().size() == wl.getAllUsersInformatio().size(),
                "The reports' LSSA logos for each student is present [Spanish]");

        softAssert.assertTrue(wl.getFirstMessageText().contains(welcomeLetterSp),
                "The text of the letter is present [Spanish]");

        ppp.switchBack();

        ppp.selectProgramByValueFromDDL("15");

        List<String> listOfKBTBEMClasses = ppp.getListOfClasses();

        softAssert.assertTrue(listOfClasses.containsAll(listOfKBTBEMClasses),
                "All KBTBEM classes aren't matching");
        softAssert.assertTrue(listOfClasses.size() > listOfKBTBEMClasses.size(),
                "All KBTBEM classes aren't matching");

        ppp.selectClassFromDDLByIndex(2);

        ppp.clickViewReportButton();

        ppp.switchToNextWindow();

        softAssert.assertTrue(wl.getAllKBTBEMHeaders().size() == wl.getAllUsersInformatio().size(),
                "The reports' KBTBEM logos for each student is present [English]");

        softAssert.assertAll();
    }

    public boolean isReportTitleCorrect(String act, String exp) {
        return exp.contains(act);
    }
}
