package com.a3k.pages;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class SetupWizardPage extends Page {

	@FindBy(xpath="//*[@background='/images/wizard/teacher/header_bg.gif']")
	private WebElement backgroundImage;
	
	public SetupWizardPage(WebDriver driver){
		PageFactory.initElements(getWebDriver(), this);
	}
	public void waitUntilPageIsLoaded(){
        logger.info("Wait until page is loaded");
        $(backgroundImage).shouldBe(Condition.visible);
	}
	public boolean isBackgroundVisible(){
		return isDisplayed(backgroundImage);
	}
}
