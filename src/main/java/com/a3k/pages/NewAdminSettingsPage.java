package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NewAdminSettingsPage extends Page {
	
	public NewAdminSettingsPage(WebDriver driver){
		this.driver = driver;
	}
	

	private WebElement headerOfAdminSettingsPage = $(By.xpath(".//div[@class='title']"));
	private WebElement selectDistrictDDL = $(By.id("district"));
	private WebElement selectDistrictButton = $(By.xpath("//input[@type='submit']"));
	private ElementsCollection adminSettingsSections = $$(By.xpath(".//div[contains(@class, 'sectionHeader')]"));;
	private WebElement changeDistrictLink = $(By.xpath(".//div[@class='title']//a"));;
	private WebElement subheaderOfAdminSettingsPage = $(By.xpath("//div[@class='subTitle']"));;
	private WebElement messageForTeacherWithoutClass = $(By.xpath(".//div[@class = 'wizardAlert']"));;
	private ElementsCollection allLinksOnAdminSettingsPage = $$(By.xpath(".//div[@class = 'adminSettingsContent']//a"));
	private WebElement findUserInput = $(By.id("search_text"));
	
	
	public WebElement getFindUserInput() {
		return findUserInput;
	}

	public ElementsCollection getAllLinksOnAdminSettingsPage() {
		return allLinksOnAdminSettingsPage;
	}

	public WebElement getMessageForTeacherWithoutClass() {
		return messageForTeacherWithoutClass;
	}

	public WebElement getSubheaderOfAdminSettingsPage() {
		return subheaderOfAdminSettingsPage;
	}

	public WebElement getChangeDistrictLink() {
		return changeDistrictLink;
	}

	public ElementsCollection getAdminSettingsSections() {
		return adminSettingsSections;
	}

	public WebElement getHeaderOfAdminSettingsPage() {
		return headerOfAdminSettingsPage;
	}	
	
	public void selectDistrict(String districtName){
		waitElement(selectDistrictDDL);
		Select selectDistrict = new Select(selectDistrictDDL);
		selectDistrict.selectByVisibleText(districtName);
		$(selectDistrictButton).click();
	}
	
	public void selectDistrictByValue(String districtValue){
		waitElement(selectDistrictDDL);
		Select selectDistrict = new Select(selectDistrictDDL);
		selectDistrict.selectByValue(districtValue);
		$(selectDistrictButton).click();
	}

	public void collapseAllAdminSettingsSections(){
		for(WebElement el : adminSettingsSections){
			if(el.getAttribute("class").equals("sectionHeader toggleView expanded")){
				$(el).click();
			}			
		}
	}
}
