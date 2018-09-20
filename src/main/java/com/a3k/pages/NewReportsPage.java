package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NewReportsPage extends Page {
	
	public NewReportsPage(WebDriver driver) {
		this.driver = driver;
	}

	private WebElement headerOfReportsPage = $(By.xpath(".//tr[@id = 'headerRow']//img"));
	private ElementsCollection reportSections = $$(By.xpath(".//a[contains(@id, 'menu_link')]"));
	private WebElement selectDistrictDDL = $(By.id("district"));
	private WebElement selectDistrictButton = $(By.xpath("//input[@type='submit']"));
	private WebElement studentWorkSectionHeader = $(By.id("menu_link_2"));
	private WebElement performanceSectionHeader = $(By.id("menu_link_4"));
	private WebElement usageReportsSectionHeader = $(By.id("menu_link_3"));
	private WebElement assessmentToolsSectionHeader = $(By.id("menu_link_6"));
	private WebElement homeCommunicationsSectionHeader = $(By.id("menu_link_7"));
	private ElementsCollection usageReportsSectionLinks = $$(By.xpath(".//td[@id='menu_3']/../following-sibling::tr//*[@id='submenu_3']//a"));
	private By usageReportsSectionLinksBy = By.xpath(".//td[@id='menu_3']/../following-sibling::tr//*[@id='submenu_3']//a");
	private ElementsCollection performanceSectionLinks = $$(By.xpath(".//td[@id='menu_4']/../following-sibling::tr//*[@id='submenu_4']//a"));
	private By performanceSectionLinksBy = By.xpath(".//td[@id='menu_4']/../following-sibling::tr//*[@id='submenu_4']//a");
	private ElementsCollection assessmentToolsSectionLinks = $$(By.xpath(".//td[@id='menu_6']/../following-sibling::tr//*[@id='submenu_6']//a"));
	private By assessmentToolsSectionLinksBy = By.xpath(".//td[@id='menu_6']/../following-sibling::tr//*[@id='submenu_6']//a");
	private ElementsCollection homeCommunicationsSectionLinks = $$(By.xpath(".//td[@id='menu_7']/../following-sibling::tr//*[@id='submenu_7']//a"));
	private By homeCommunicationsSectionLinksBy = By.xpath(".//td[@id='menu_7']/../following-sibling::tr//*[@id='submenu_7']//a");
	private WebElement emailAndStep1 = $(By.xpath(".//*[@href='/options/reports/?report_id=1&section=2']"));
	private By emailAndStep1By = By.xpath(".//*[@href='/options/reports/?report_id=1&section=2']");
	private WebElement pointsAndAchivements = $(By.xpath(".//*[@href='/options/reports/?report_id=15&section=2']"));
	private WebElement authenticAssesmentPortfolio = $(By.xpath(".//*[@href='/options/reports/?report_id=2&section=2']"));
	private WebElement whichOfMyStudentUsingTheProgram = $(By.xpath(".//*[@href='/options/reports/?report_id=3&section=3']"));
	private WebElement whichParentsGuardiansUsingTheProgram = $(By.xpath(".//*[@href='/options/reports/?report_id=10&section=3']"));
	private WebElement howAreMyStudentsSpendingTheirTimeLink = $(By.xpath(".//a[contains(@href,'report_id=29')]"));
	private WebElement howHasUsageChanged = $(By.xpath(".//*[@href='/options/reports/?report_id=35&section=3']"));
	private WebElement howAreMyStudentProgressing = $(By.xpath(".//*[@href='/options/reports/?report_id=17&section=3']"));
	private WebElement whichMyStudentsAreUsingTheProgramAfter = $(By.xpath(".//*[@href='/options/reports/?report_id=6&section=3']"));
	private WebElement howAreMyStudentsSpendingTimeAfterSchool = $(By.xpath(".//*[@href='/options/reports/?report_id=30&section=3']"));
	private WebElement howLikelyMyStudents = $(By.xpath(".//*[@href='/options/reports/?report_id=31&section=4']"));
	private WebElement howHasLexile = $(By.xpath(".//*[@href='/options/reports/?report_id=37&section=4']"));
	private WebElement howAreMyStudentsPerformingOnActivities = $(By.xpath(".//*[@href='/options/reports/?report_id=18&section=4']"));
	private WebElement howAreMyStudentsPerformingOnStandards = $(By.xpath(".//*[@href='/options/reports/?report_id=34&section=4']"));
	private WebElement howAreMyStudentsPerforminNCLB = $(By.xpath(".//*[@href='/options/reports/?report_id=19&section=4']"));
	private ElementsCollection allDDLinStudentWorkSection = $$(By.xpath(".//*[@id='submenu_2']//select"));
	private ElementsCollection labelsOfDDL = $$(By.xpath(".//*[@id='submenu_2']//div[@class = 'report_link']"));
	private ElementsCollection allItemsOfAllDDL = $$(By.xpath(".//*[@id='submenu_2']//option[ string-length( text()) >1]"));

	
	public ElementsCollection getAllItemsOfAllDDL() {
		return allItemsOfAllDDL;
	}

	public ElementsCollection getLabelsOfDDL() {
		return labelsOfDDL;
	}

	public WebElement getStudentWorkSectionHeader() {
		return studentWorkSectionHeader;
	}

	public ElementsCollection getAllDDLinStudentWorkSection() {
		return allDDLinStudentWorkSection;
	}

	public WebElement getAssessmentToolsSectionHeader() {
		return assessmentToolsSectionHeader;
	}

	public WebElement getHomeCommunicationsSectionHeader() {
		return homeCommunicationsSectionHeader;
	}

	public List<String> getUsageReportsSectionLinks() {
		return getTextFromWebElementsByListBy(usageReportsSectionLinksBy);
	}

	public List<String> getPerformanceSectionLinks() {
		return getAttributesFromItemsOfListBy(performanceSectionLinksBy, "textContent");
	}

	public List<String> getAssessmentToolsSectionLinks() {
		return getTextFromWebElementsByListBy(assessmentToolsSectionLinksBy);		
	}

	public List<String> getHomeCommunicationsSectionLinks() {
		return getTextFromWebElementsByListBy(homeCommunicationsSectionLinksBy);		
	}

	public WebElement getHowLikelyMyStudents() {
		return howLikelyMyStudents;
	}

	public WebElement getHowHasLexile() {
		return howHasLexile;
	}

	public WebElement getHowAreMyStudentsPerformingOnActivities() {
		return howAreMyStudentsPerformingOnActivities;
	}

	public WebElement getHowAreMyStudentsPerformingOnStandards() {
		return howAreMyStudentsPerformingOnStandards;
	}

	public WebElement getHowAreMyStudentsPerforminNCLB() {
		return howAreMyStudentsPerforminNCLB;
	}

	public WebElement getWhichOfMyStudentUsingTheProgram() {
		return whichOfMyStudentUsingTheProgram;
	}

	public WebElement getWhichParentsGuardiansUsingTheProgram() {
		return whichParentsGuardiansUsingTheProgram;
	}

	public WebElement getHowAreMyStudentsSpendingTheirTimeLink() {
		return howAreMyStudentsSpendingTheirTimeLink;
	}

	public WebElement getHowHasUsageChanged() {
		return howHasUsageChanged;
	}

	public WebElement getHowAreMyStudentProgressing() {
		return howAreMyStudentProgressing;
	}

	public WebElement getWhichMyStudentsAreUsingTheProgramAfter() {
		return whichMyStudentsAreUsingTheProgramAfter;
	}

	public WebElement getHowAreMyStudentsSpendingTimeAfterSchool() {
		return howAreMyStudentsSpendingTimeAfterSchool;
	}

	public WebElement getUsageReportsSectionHeader() {
		return usageReportsSectionHeader;
	}

	public WebElement getEmailAndStep1() {
		return $(emailAndStep1);
	}


	public WebElement getPointsAndAchivements() {
		return pointsAndAchivements;
	}

	public WebElement getAuthenticAssesmentPortfolio() {
		return authenticAssesmentPortfolio;
	}

	public WebElement getPerformanceSectionHeader() {
		return performanceSectionHeader;
	}

	public ElementsCollection getReportSections() {
		return reportSections;
	}


	public WebElement getHeaderOfReportsPage() {
		waitUntilAttributeToBeNotEmpty(headerOfReportsPage, "src");
		return headerOfReportsPage;
	}
	
	public void collapseAllReportsSection(){
		for(WebElement el : reportSections){
			if($(el).getAttribute("class").equals("report_category_selected")){
				$(el).click();
			}

		}
	}

	public List<String> getReportNamesFromReportDDL(WebElement reportDDL){
		
		List<String> listOfReports = new ArrayList<String>();

		List<WebElement> options = getOptionsFromSelect(reportDDL);
		
		for(WebElement report : options){
			if(!$(report).getAttribute("value").equals(""))
				listOfReports.add($(report).getText());
		}
		return listOfReports;
	}
	
	public void selectDistrict(String districtName){
		waitElement(selectDistrictDDL);
		Select selectDistrict = new Select(selectDistrictDDL);
		selectDistrict.selectByVisibleText(districtName);
		$(selectDistrictButton).click();
	}
}
