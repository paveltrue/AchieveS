package com.a3k.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SetupWizardPage extends Page {

	@FindBy(xpath="//*[@background='/images/wizard/teacher/header_bg.gif']")
	private WebElement backgroundImage;

	public SetupWizardPage(WebDriver driver){
		this.driver = driver;
		//PageFactory.initElements(driver, this);
	}
	public void waitUntilPageIsLoaded(){
        logger.info("Wait until page is loaded");
//        $(backgroundImage).shouldBe(Condition.visible);
	}
	public boolean isBackgroundVisible(){
		return isDisplayed(backgroundImage);
	}
}
