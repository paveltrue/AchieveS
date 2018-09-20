package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Selenide.$;

public class
HelpPage extends Page {

	protected WebElement achieveHubButton = $(By.xpath("//*[@class='button achieveHub' or @class='button achieve3000Hub']"));
	protected WebElement teacherResourcesButton = $(By.xpath("//*[@class='button teacherResources']"));
	protected WebElement quickHelpButton = $(By.xpath("//*[@class='button quickHelp']"));
	protected WebElement professionalLearningButton = $(By.xpath("//*[@class='button professionalLearning']"));
	protected WebElement customerSupportButton = $(By.xpath("//*[@class='button customerSupport']"));
	protected WebElement spanishResources = $(By.xpath("//*[@class='button spanishResources']"));
	protected WebElement titleOfPage = $(By.xpath("//*[@class='title glow']"));
	
	private By title = By.xpath("./*[@class='title']");
	
	public HelpPage(WebDriver driver){
		this.driver = driver;
	}
	
	public boolean isQuickHelpButtonPresent(){
		return isElementPresent(quickHelpButton);
	}
	
	public void clickOnAchieveHubButton(){
		$(achieveHubButton).click();
	}
	
	public void clickOnTeacherResourcesButton(){
		$(teacherResourcesButton).click();
	}
	
	public void clickOnQuickHelpButton(){
		$(quickHelpButton).click();
	}
	
	public void clickOnProfessionalLearningButton(){
		$(professionalLearningButton).click();
	}

	public void clickOnCustomerSupportButton(){
		$(customerSupportButton).click();
	}
	
	public void clickOnSpanishResourcesButton(){
		$(spanishResources).click();
	}
	
	public String getTitleOfAchieveHubButton(){
		return getTitle(achieveHubButton).replace('\n', ' ');
	}
	
	public String getTitleOfTeacherResourcesButton(){
		return getTitle(teacherResourcesButton).replace('\n', ' ');
	}
	
	public String getTitleOfQuickHelpButton(){
		return getTitle(quickHelpButton).replace('\n', ' ');
	}
	
	public String getTitleOfProfessionalLearningButton(){
		return getTitle(professionalLearningButton).replace('\n', ' ');
	}
	
	public String getTitleOfCustomerSupportButton(){
		return getTitle(customerSupportButton).replace('\n', ' ');
	}
	
	public String getTitleOfSpanishResourcesButton(){
		return getTitle(spanishResources).replace('\n', ' ');
	}
	
	public String getSubtitleOfAchieveHubButton(){
		return getSubtitle(achieveHubButton);
	}
	
	public String getSubtitleOfTeacherResourcesButton(){
		return getSubtitle(teacherResourcesButton);
	}
	
	public String getSubtitleOfQuickHelpButton(){
		return getSubtitle(quickHelpButton);
	}
	
	public String getSubtitleOfProfessionalLearningButton(){
		return getSubtitle(professionalLearningButton);
	}
	
	public String getSubtitleOfCustomerSupportButton(){
		return getSubtitle(customerSupportButton);
	}
	
	public String getSubtitleOfSpanishResourcesButton(){
		return getSubtitle(spanishResources).replace('\n', ' ');
	}
	
	public String getPageTitle(){
		return titleOfPage.getText();
	}
	
	private String getTitle(WebElement el){
		return $(el).find(title).getText();
	}

	private String getSubtitle(WebElement el){
		return $(el).getText().replace(getTitle(el)+"\n", "").replace("\n", " ");
	}
	
	public String getHoverOfAchieveHubButton(){
		return getHover(achieveHubButton);
	}
	
	public String getHoverOfTeacherResourcesButton(){
		return getHover(teacherResourcesButton);
	}
	
	public String getHoverOfQuickHelpButton(){
		return getHover(quickHelpButton);
	}
	
	public String getHoverOfProfessionalLearningButton(){
		return getHover(professionalLearningButton);
	}
	
	public String getHoverOfCustomerSupportButton(){
		return getHover(customerSupportButton);
	}
	
	public String getHoverOfSpanishResourcesButton(){
		return getHover(spanishResources);
	}
	
	private String getHover(WebElement el){
		Actions a = new Actions(driver);
		a.moveToElement(el).build().perform();
		return el.getCssValue("background-image");
	}

	public WebElement getTitleOfPage() {
		return titleOfPage;
	}
}
