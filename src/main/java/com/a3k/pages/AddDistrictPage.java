package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.$;

public class AddDistrictPage extends Page {

	private WebElement orderSelect = $(By.id("order_detail_id"));
	private WebElement districtNameInput = $(By.id("district_name"));
	private WebElement stateSelect = $(By.id("state"));
	private By saveChangesButtonBy = By.xpath("//input[@value='Save Changes']");
	private WebElement districtSettingsMenu = $(By.id("menu_link_2"));
	private By KBTBESettingsBy = By.id("access_menu_link_3");

	private String checkBoxEnableJobGradeXPath = "//input[@class='job-pulse-option' and @value=%s]";

	private By linkEnableJobForKidBizBy = By.xpath("//input[@class='job-pulse-permission']");
	private WebElement achieve3000AdministratorMenu = $(By.id("menu_link_6"));
	private WebElement productLanguageSelect = $(By.id("product_lang"));
	private WebElement programSelect = $(By.id("program_id[0]"));
	private WebElement startMonthSelect = $(By.id("start_month[0]"));
	private WebElement startDaySelect = $(By.id("start_day[0]"));
	private WebElement startYearInput = $(By.id("start_year[0]"));
	private WebElement endMonthSelect = $(By.id("end_month[0]"));
	private WebElement endDaySelect = $(By.id("end_day[0]"));
	private WebElement endYearInput = $(By.id("end_year[0]"));
	private WebElement scaffoldsCheckbox = $(By.id("se[0]"));
	private WebElement submitButton = $(By.xpath("//input[@type='submit']"));
	
	public AddDistrictPage(WebDriver driver){
		this.driver = driver;
	}
	
	public void enterDistrictName(String districtName){
		$(districtNameInput).sendKeys(districtName);
	}
	
	public void selectState(String state){
		selectFromDDLbyValue(stateSelect, state.toUpperCase());
	}

	public void openKBTBESettings() {
		clickJS(KBTBESettingsBy);
	}

	public void saveChanges() {
		clickJS(saveChangesButtonBy);
	}

	private String getGradeValue(String grade) {
		String value = "";
		switch (grade) {
			case ("Grade 5") : value = "9"; break;
			case ("Grade 2") : value = "6"; break;
		}
		return value;
	}

	public boolean isSelectedJobForGrade(String grade) {
		return $(By.xpath(String.format(checkBoxEnableJobGradeXPath, getGradeValue(grade)))).isSelected();
	}

	public void clickOnCheckBoxJobForGrade(String grade) {
		clickJS(By.xpath(String.format(checkBoxEnableJobGradeXPath, getGradeValue(grade))));
	}
	
	public void expandAchieve3000AdministratorMenu(){
		$(achieve3000AdministratorMenu).click();
	}
	
	public void clickOnSubmitButton(){
		submitButton.click();
	}
	
	public boolean isProductLanguageDDLExist(){
		return isElementExist(productLanguageSelect);
	}
	
	public String getProductLanguage(){
		return getSelectedOptionFromSelect(productLanguageSelect);
	}

	public void openDistrictSettings() {
		clickJS(districtSettingsMenu);
	}
	
	public void selectProductLanguage(String language){
		
		switch(language.toLowerCase()){
		case "us":
			selectFromDDLbyValue(productLanguageSelect, "1");
			break;
		case "uk":
			selectFromDDLbyValue(productLanguageSelect, "7");
			break;
		}
	}
	
	public void selectTemoraryOrder(){
		selectFromDDLbyValue(orderSelect, "39098");
		$(submitButton).click();
	}
	
	public void selectPrograms(String[] programs){
		for(String program : programs){
			selectFromDDLbyValue(programSelect, program);
		}
		
	}
	
	public void selectScaffoldsCheckbox(){
		if(!scaffoldsCheckbox.isSelected())
			$(scaffoldsCheckbox).click();
	}
	
	public void selectStartMonth(String month){
		selectFromDDLbyValue(startMonthSelect, month);
	}
	
	public void selectStartDay(String day){
		selectFromDDLbyValue(startDaySelect, day);
	}
	
	public void enterStartYear(String year){
		$(startYearInput).sendKeys(year);
		
	}
	
	public void selectEndMonth(String month){
		selectFromDDLbyValue(endMonthSelect, month);
	}
	
	public void selectEndDay(String day){
		selectFromDDLbyValue(endDaySelect, day);
	}
	
	public void enterEndYear(String year){
		$(endYearInput).sendKeys(year);
		
	}
	
	public void setupSubscriptionStart(Date date){
		DateFormat format = new SimpleDateFormat("M-d-yyyy");
		
		String[] stringDate = format.format(date).split("-");
	
		selectStartMonth(stringDate[0]);
		selectStartDay(stringDate[1]);
		enterStartYear(stringDate[2]);
	}
	
	public void setupSubscriptionEnd(Date date){
		
		DateFormat format = new SimpleDateFormat("M-d-yyyy");
		
		String[] stringDate = format.format(date).split("-");
		selectEndMonth(stringDate[0]);
		selectEndDay(stringDate[1]);
		enterEndYear(stringDate[2]);
	}
}
