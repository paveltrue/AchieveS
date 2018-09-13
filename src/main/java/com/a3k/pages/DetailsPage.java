package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class DetailsPage extends Page {

	private WebElement textOfSageAchievement = $(By.xpath("//td[.//*[@src='/images/badges/med/sage.gif']]/following-sibling::td"));
	private WebElement detailsHeader = $(By.className("detailsheader"));
	private By topCareerTitleBy = By.xpath(".//*[@class = 'topcareerContainer']//*[@class = 'title']");
	private By careerGoalBy = By.xpath(".//*[@class = 'topcareerContainer']//*[@class = 'careerGoal']");
	private By currentLexileBy = By.xpath(".//*[@class = 'topcareerContainer']//*[@class = 'careerMeter']");


	public boolean isTopCareerTitleDisplayed(){
	  return isDisplayedBy(topCareerTitleBy);
	}

	public boolean isCareerGoalDisplayed(){
		return isDisplayedBy(careerGoalBy);
	}

	public String getValueOfCurrentLexile(){
	    return getTextBy(currentLexileBy);
	}

	public DetailsPage(WebDriver driver){
		this.driver = driver;
		//PageFactory.initElements(driver, this);
	}
	
	public String getAchievement40pointText(){
		return $(textOfSageAchievement).getText().trim();
	}
	
	public boolean isDetailsHeaderPresent(){
		return isElementPresent(detailsHeader);
	}
}
