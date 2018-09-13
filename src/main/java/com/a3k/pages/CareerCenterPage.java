package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CareerCenterPage extends Page {

	private WebElement CareerCenterTitle = $(By.className("title"));
	private WebElement lexileMeasureImage = $(By.className("meterFill"));
	private WebElement printIcon = $(By.cssSelector("img[src*='print']"));
	private WebElement findCareersButton = $(By.id("btn_findcareer"));
	private WebElement findNewCareersButton = $(By.cssSelector(".saveTheseResults>a"));
	private WebElement expandedSection = $(By.xpath("//div[contains(@id,'cluster') and contains(@class,'active')]"));
	private WebElement meterContainer = $(By.className("meterContainer"));
	private ElementsCollection careerSections = $$(By.xpath("//div[contains(@id,'cluster')]"));
	private By labelJobPulse = By.xpath("//a[@href='/kb/career/jobPulse']");
	private By careerSectionsBy = By.xpath(".//div[contains(@id,'cluster')]");
	private By careerItemsOfActiveSectionBy = By.xpath(".//div[contains(@id,'cluster') and contains(@class,'active')]/following-sibling::div[1]//input[contains(@id,'career')]");
	private By careerItemsOfActiveSectionExcludeCheckedBy = By.xpath(".//div[contains(@id,'cluster') and contains(@class,'active')]/following-sibling::div[1]//input[contains(@id,'career') and not (@checked)]");
	private By titleOfCareerItemsOfActiveSectionExcludeCheckedBy = By.xpath(".//div[contains(@id,'cluster') and contains(@class,'active')]/following-sibling::div[1]//input[contains(@name,'career') and not (@checked)]/../..//input[contains(@name,'ctitle') and not (@checked)]");
	private By checkedCareerItemsOfActiveSectionBy = By.xpath(".//div[contains(@id,'cluster') and contains(@class,'active')]/following-sibling::div[1]//input[contains(@id,'career') and @checked]");
	private By findCareerButtonBy = By.id("btn_findcareer");
	private By careerGoalLexileBy = By.xpath(".//*[@class='careerGoal']");
	private By currentLexileBy = By.xpath(".//*[@class='currentLexile']");
	private By markAsTopCareerButtonBy = By.xpath(".//*[@class = 'markAsTopCareer']//a");


	public void markAsTopCareer(String nameOfCareer){
		By xpath = By.xpath(".//*[@class='resultsContainer']//strong[normalize-space() = '"+nameOfCareer
				             +"']/../following-sibling::*[@class = 'markAsTopCareer']//a");
		$(xpath).click();
	}

	public int getAmountOfCurrentLexile() {
		return amountOfElements(currentLexileBy);
	}

	public int getAmountOfCareerGoal() {
		return amountOfElements(careerGoalLexileBy);
	}

	public String getCurrentLexileValue(){
	    return getTextBy(currentLexileBy);
	}

	public boolean isCareerGoalLexileDisplayed(){
	  return isDisplayedBy(careerGoalLexileBy);
	}

	public void clickOnFindCareerButton(){
		$(findCareerButtonBy).click();
	}

	public void uncheckAllCheckedCareerItemsOnEachSection(){
		ElementsCollection els = findEls(careerSectionsBy);
		for(int i = 0; i < els.size(); i ++){
            clickJS(els.get(i));
			if(findEls(checkedCareerItemsOfActiveSectionBy).size() != 0){
				clickOnEachElementsInListBy(checkedCareerItemsOfActiveSectionBy);
			}
		}
	}

	public boolean doesJobPulseExist() {
		return isDisplayedBy(labelJobPulse);
	}

	public void clickJobPulse() {
		clickJS(labelJobPulse);
	}

	public void checkOneCareerItemOnSectionByNumber(int careerSectionNumber, int careerItemNumber){
		$(findEls(careerSectionsBy).get(careerSectionNumber-1)).click();
		$(findEls(careerItemsOfActiveSectionExcludeCheckedBy).get(careerItemNumber-1)).click();
	}

	public List<String> checkSomeCareerItemsOnSectionByNumberAndReturnTitles(int careerSectionNumber, int countOfItems){
		$(findEls(careerSectionsBy).get(careerSectionNumber-1)).click();
		clickOnSomeElementsInListStartFromBy(careerItemsOfActiveSectionExcludeCheckedBy, 0, countOfItems-1);
		return getAttributeOfSomeElementsInListStartFromBy(titleOfCareerItemsOfActiveSectionExcludeCheckedBy, "value", 0, countOfItems-1);
	}

	public CareerCenterPage(WebDriver driver){
		this.driver = driver;
		//PageFactory.initElements(driver, this);
	}
	
	public boolean isCareerCenterTitlePresent(){
		String title = $(CareerCenterTitle).getText();
		boolean english = title.equals("Career Center");
		boolean spanish = title.equals("Centro laboral");
		return english || spanish;
	}
	
	public String getCareerCenterTitle(){
		waitElement(CareerCenterTitle);
		return $(CareerCenterTitle).getText();
	}
	
	public List<String> getCareerSectionsName(){
		List<String> clastersNames = new ArrayList<String>();
		for(WebElement c : careerSections){
			WebElement name = $(c).find(By.className("clusterName"));
			clastersNames.add($(name).getText());
		}
		return clastersNames;
	}
	
	public void expandCareerSection(int index){
		careerSections.get(index).click();
	}

	public void expandCareerSectionBy(int index){
		$(findEls(careerSectionsBy).get(index)).click();
	}
	
	public List<String> getProfessionsFromExpandedSection(){
		List<String> professions = new ArrayList<String>();
		ElementsCollection professionElements = getProfessionsElementsFromExpandedSection();
		for(WebElement pe : professionElements) {
			professions.add(pe.getText());
		}
		return professions;
	}
	
	public void markProfessionsFromExpandedSection(List<String> prof){
		ElementsCollection professionElements = getProfessionsElementsFromExpandedSection();
		for(WebElement pe: professionElements){
			WebElement checkbox = $(pe).find(By.xpath("td[1]"));
			String name = $(pe).find(By.xpath("td[2]")).getText();
			if(prof.contains(name)){
				$(checkbox).click();
			}
		}
	}
	
	public void clickFindCareers(){
		$(findCareersButton).click();
	}
	
	public boolean isLexileMesurePresent(){
		
		return isElementExist(lexileMeasureImage);
	}
	
	public boolean isPrintButtonPresent(){
		return isElementExist(printIcon);
	}
	
	private ElementsCollection getProfessionsElementsFromExpandedSection(){
		String expandedSectionId = $(expandedSection).getAttribute("id");
		ElementsCollection professionElements = $$(By.xpath("//div[@aria-labelledby='"+expandedSectionId+"']//td[@class='career']//tr"));
		return professionElements;
	}
	
}
