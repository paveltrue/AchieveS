package com.a3k.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

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
    private WebElement whichOfMyStudentsLexilesAdjusted = $(By.xpath(".//*[@href='/options/reports/?section=6&report_id=38']"));
    private WebElement studentsWithDecreasingLexile = $(By.xpath(".//*[@href='util/decreasing_lexiles.php']"));
    private WebElement howLikelyMyStudents = $(By.xpath(".//*[@href='/options/reports/?report_id=31&section=4']"));
    private WebElement howHasLexile = $(By.xpath(".//*[@href='/options/reports/?report_id=37&section=4']"));
    private WebElement howAreMyStudentsPerformingOnActivities = $(By.xpath(".//*[@href='/options/reports/?report_id=18&section=4']"));
    private WebElement howAreMyStudentsPerformingOnStandards = $(By.xpath(".//*[@href='/options/reports/?report_id=34&section=4']"));
    private WebElement usageReport = $(By.id("menu_link_3"));
    private WebElement whichOfMyStudentUsingTheProgram = $(By.xpath(".//*[@href='/options/reports/?report_id=3&section=3']"));
    private WebElement howAreMyStudentProgressing = $(By.xpath(".//*[@href='/options/reports/?report_id=17&section=3']"));
    private WebElement whichMyStudentsAreUsingTheProgramAfter = $(By.xpath(".//*[@href='/options/reports/?report_id=6&section=3']"));






    public AllReportsPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
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
        boolean bool = false;
        $(authenticAssesmentPortfolio).shouldBe(Condition.visible);
        bool = $(authenticAssesmentPortfolio).isDisplayed();
        return bool;
    }

    public boolean isEmailAndStep1Shown() {
        return $(emailAndStep1).isDisplayed();
    }

    public boolean isPointsAndAchivementsShown() {
        return $(pointsAndAchivements).isDisplayed();
    }

    public String getMyLessonsTitleText() {
        return $(myLessonsTitle).getText();
    }

    public String getWhichOfMyStudentsLexilesAdjustedText() {
        return $(whichOfMyStudentsLexilesAdjusted).getText();
    }

    public String getStudentsWithDecreasingLexileText() {
        return $(studentsWithDecreasingLexile).getText();
    }

    public String getHowLikelyMyStudentsText() {
        return $(howLikelyMyStudents).getText();
    }

    public String getHowHasLexileText() {
        return $(howHasLexile).getText();
    }

    public String getHowAreMyStudentsPerformingOnActivitiesText() {
        return $(howAreMyStudentsPerformingOnActivities).getText();
    }

    public String getHowAreMyStudentsPerformingOnStandardsText() {
        return $(howAreMyStudentsPerformingOnStandards).getText();
    }

    public void expandUsageReports() {
        logger.info("Opening Usage Reports");
        if (!$(usageReport).getAttribute("class").contains("selected")) {
            $(usageReport).click();
        }
    }

    public String getWhichOfMyStudentUsingTheProgramText() {
        return $(whichOfMyStudentUsingTheProgram).getText();
    }

    public String getHowAreMyStudentProgressingText() {
        return $(howAreMyStudentProgressing).getText();
    }

    public String getWhichMyStudentsAreUsingTheProgramAfterText() {
        return $(whichMyStudentsAreUsingTheProgramAfter).getText();
    }

}
