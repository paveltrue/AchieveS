package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class AllReportsPage extends Page {

    private By assesmentToolsBy = By.id("menu_link_6");
    private By homeCommunicationsBy = By.id("menu_link_7");
    private WebElement welcomeLetter = $(By.xpath(".//*[@href='/options/reports/?report_id=20&section=7']"));
    private WebElement homeEditionSetupInstruction = $(By.xpath(".//*[@href='/options/reports/?report_id=22&section=7']"));
    private WebElement homeLexileLetter = $(By.xpath(".//*[@href='/options/reports/?report_id=21&section=7']"));
    private By performanceReportBy = By.id("menu_link_4");
    private WebElement howAreMyStudentsMastering = $(By.xpath(".//*[@href='/options/reports/?report_id=5&section=4']"));
    private WebElement howAreMyStudentsPerforminNCLB = $(By.xpath(".//*[@href='/options/reports/?report_id=19&section=4']"));
    private By howCanIDifferentiateBy = By.xpath(".//*[@href='/options/reports/?report_id=50&section=4']");
    private By studentWorkMenuItemBy = By.id("menu_link_2");
    private WebElement authenticAssesmentPortfolio = $(By.xpath(".//*[@href='/options/reports/?report_id=2&section=2']"));
    private WebElement emailAndStep1 = $(By.xpath(".//*[@href='/options/reports/?report_id=1&section=2']"));
    private WebElement pointsAndAchivements = $(By.xpath(".//*[@href='/options/reports/?report_id=15&section=2']"));
    private WebElement myLessonsTitle = $(By.xpath("(.//*[@id='submenu_2']//div[@class='report_link'])[1]"));


    public AllReportsPage(WebDriver driver) {
        PageFactory.initElements(getWebDriver(), this);
    }

    public void openAssessmentTools() {
        logger.info("Opening Assessment Tools");
        $(assesmentToolsBy).click();
    }

    public void openHomeCommunication() {
        logger.info("Opening Home Communication");
        $(homeCommunicationsBy).click();
    }

    public String getWelcomeLetterText() {
        return $(welcomeLetter).getText();
    }

    public String getHomeEditionSetupInstructionText() {
        return $(homeEditionSetupInstruction).getText();
    }

    public String getHomeLexileLetterText() {
        return $(homeLexileLetter).getText();
    }

    public void openPerformanseReports() {
        logger.info("Opening Performance Reports");
        $(performanceReportBy).click();
    }

    public String getHowAreMyStudentsMasteringText() {
        return $(howAreMyStudentsMastering).getText();
    }

    public String getHowAreMyStudentsPerforminNCLBText() {
        return howAreMyStudentsPerforminNCLB.getText();
    }

    public String getHowCanIDifferentiateText() {
        return getTextBy(howCanIDifferentiateBy);
    }

    public void openStudentWork() {
        waitUntilAppearsBy(studentWorkMenuItemBy);
        logger.info("Open Student Work");
        if (!isStudentWorkSectionIsOpened()) {
            $(studentWorkMenuItemBy).click();
        }
    }

    public boolean isStudentWorkSectionIsOpened() {
        waitElement(studentWorkMenuItemBy);
        return getAttributeBy(studentWorkMenuItemBy, "class").contains("selected");
    }

    public boolean isAuthentisAssesmentPortfolionShown() {
        return $(authenticAssesmentPortfolio).isDisplayed();
    }

    public boolean isEmailAndStep1Shown() {
        return $(emailAndStep1).isDisplayed();
    }

    public boolean isPointsAndAchivementsShown() {
        return isDisplayed(pointsAndAchivements);
    }

    public String getMyLessonsTitleText() {
        return myLessonsTitle.getText();
    }

}
