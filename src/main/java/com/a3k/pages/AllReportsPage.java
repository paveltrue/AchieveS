package com.a3k.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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
    private ElementsCollection reportLinksInStudentWorkSection = $$(By.xpath("//table[@id='submenu_2']//a"));
    private ElementsCollection reportDDLsInStudentWorkSection = $$(By.xpath("//table[@id='submenu_2']//select"));
    private By usageReportsMenuItemBy = By.id("menu_link_3");
    private ElementsCollection reportLinksInUsageReportsSection = $$(By.xpath("//table[@id='submenu_3']//a"));
    private ElementsCollection reportLinksInPerformanceReportsSection = $$(By.xpath("//table[@id='submenu_4']//a"));
    private ElementsCollection reportLinksInAssessmentToolsSection = $$(By.xpath("//table[@id='submenu_6']//a"));
    private WebElement performanceReport = $(By.id("menu_link_4"));
    private WebElement expandedArrowOfPerformanceReport = $(By.xpath("//a[@id='menu_link_4']//ancestor::tr[1]//img[contains(@src,'minus')]"));
    private WebElement userAndSchoolName = $(By.xpath("//td[@class='menu_bar' and @align='left']"));
    private By viewRerpotButtonBy = By.xpath(".//*[@class = 'button']");
    private WebElement doneButton = $(By.xpath("//*[@id=\"walkme-balloon-3886155\"]/div/div[1]/div[4]/div[2]/div/button/span"));
    private WebElement classSelect = $(By.xpath("//*[@id='class_id' or @name='class_id']"));
    private By userComboBy = By.xpath(".//select[@name='user_id']");
    private By whichLanguageDDLBy = By.xpath("//select[@name='force_language' or @name='rpt_language']");
    private WebElement myLessonsDdl = $(By.xpath("(.//select[@class = 'admin_input'])[1]"));


    private String linkXpath = "//a[contains(text(), '%s')]";

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
        closeDoneButtonIfExist();
        $(performanceReportBy).click();
    }

    public String getHowAreMyStudentsMasteringText() {
        return $(howAreMyStudentsMastering).getText();
    }

    public String getHowAreMyStudentsPerforminNCLBText() {
        return howAreMyStudentsPerforminNCLB.getText();
    }

    public String getHowCanIDifferentiateText() {
        return getText(howCanIDifferentiateBy);
    }

    public void openStudentWork() {
        waitUntilAppearsBy(studentWorkMenuItemBy);
        closeWalkmeNew();
        logger.info("Open Student Work");
        if (!isStudentWorkSectionIsOpened()) {
            closeWalkmeNew();
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

    public List<String> getReportLinksInStudentWorkSection() {

        List<String> reportsLinks = new ArrayList<String>();

        for (WebElement reportLink : reportLinksInStudentWorkSection)
            reportsLinks.add($(reportLink).getText());

        return reportsLinks;
    }

    public void openReportByName(String name) {

        if (name.equals("How are my students progressing towards Achieve3000's 40-activity usage goal?"))
            name = "How are my students progressing towards Achieve3000";
        else if (name.equals("Which of my students' Lexiles have been adjusted?"))
            name = "Lexiles have been adjusted?";

        WebElement report = $(By.xpath("//*[contains(text(),'" + name + "')]"));

        $(report).click();
    }

    public ElementsCollection getReportDDLsInStudentWorkSection() {
        return reportDDLsInStudentWorkSection;
    }

    public List<String> getReportNamesFromReportDDL(WebElement reportDDL) {

        List<String> listOfReports = new ArrayList<String>();

        List<WebElement> options = getOptionsFromSelect(reportDDL);

        for (WebElement report : options) {

            if (!report.getAttribute("value").equals(""))
                listOfReports.add(report.getText());
        }

        return listOfReports;
    }

    public List<WebElement> getOptionsFromSelect(WebElement select) {
        Select sel = new Select(select);
        return sel.getOptions();
//        return $(select).getSelectedOptions();
    }

    public void openUsageReports() {
        if (!getAttributeBy(usageReportsMenuItemBy, "class").contains("_selected")) {
            $(usageReportsMenuItemBy).click();
        }
    }

    public List<String> getReportLinksInUsageReportsSection() {

        List<String> reportsLinks = new ArrayList<String>();

        for (WebElement reportLink : reportLinksInUsageReportsSection)
            reportsLinks.add($(reportLink).getText());

        return reportsLinks;
    }

    public List<String> getReportHrefsInPerformanceReportsSection() {

        List<String> reportsLinks = new ArrayList<String>();

        for (WebElement reportLink : reportLinksInPerformanceReportsSection)
            reportsLinks.add($(reportLink).getAttribute("href").split("/reports/")[1]);

        return reportsLinks;
    }

    public void openReportByHref(String href) {

        closeWalkmeNew();
        WebElement report = $(By.xpath("//a[contains(@href,'" + href + "')]"));
        $(report).click();
    }

    public List<String> getReportLinksInAssessmentToolsSection() {

        List<String> reportsLinks = new ArrayList<String>();

        for (WebElement reportLink : reportLinksInAssessmentToolsSection)
            reportsLinks.add($(reportLink).getText());

        return reportsLinks;
    }

    public void openWelcomeLetter() {
        $(welcomeLetter).click();
    }

    public boolean checkPresenceOfPerformanceReports() {
        return isElementPresent(performanceReport);
    }

    public boolean checkIfPerformanceReportsExpand() {
        sleep(1000);
        closeDoneButtonIfExist();
        return $(expandedArrowOfPerformanceReport).isDisplayed();
    }

    public void openHowAreMyStudentsPerformingOnStandards() {
        $(howAreMyStudentsPerformingOnStandards).click();
    }

    public boolean checkSchoolName() {
        return !$(userAndSchoolName).getText().trim().equals("");
    }

    public void clickViewReportButton() {
        $(viewRerpotButtonBy).click();
    }

    public boolean checkWordYearInReport(){
        logger.debug("Check word Year on the page");
        return isTextOnPagePresent("Year");
    }

    public void closeDoneButtonIfExist(){
        if($(doneButton).isDisplayed()){
            $(doneButton).click();
        }
    }

    public void clickLinkByText(String text) {
        $(By.xpath(String.format(linkXpath, text))).click();
    }

    public void selectClassByNameWithoutClick(String className) {
        Select classes = new Select(classSelect);
        classes.selectByVisibleText(className);
    }

    public void selectUserForReport(String user) {
        Select uCombo = new Select(findEl(userComboBy));
        uCombo.selectByVisibleText(user);
    }

    public void selectLanguageDDL(String text) {
        new Select($(whichLanguageDDLBy)).selectByVisibleText(text);
    }

    public void chooseItemInMyLessonsDdl(String text) {
        Select sel = new Select(myLessonsDdl);
        sel.selectByVisibleText(text);
    }

}
