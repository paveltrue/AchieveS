package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class AssesmentPage extends Page {

    private WebElement containerOfTextOnStartPage = $(By.className("content"));
    private WebElement nextButton = $(By.id("btn_next"));
    protected By nextButtonBy = By.xpath(".//*[@class = 'button_ls']/span[@id = 'txt_next']");
    protected By questionNumberStringBy = By.xpath(".//*[@class = 'questionNumber']");
    private WebElement questionNumberString = $(By.className("questionNumber"));
    private By privateMessageSumbitBy = By.xpath("//div[@class='ui-dialog-buttonset']/button[1]");
    private By firstAnswerButtonBy = By.xpath(".//*[contains(@class , 'activity-option ')][1]");



    public AssesmentPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isContainerOfTextOnStartPagePresent(){
        return isElementPresent(containerOfTextOnStartPage);
    }

    public boolean isNextButtonPresent(){
        return isElementPresent(nextButton);
    }

    public void clickOnNextButtonOnFirstPage(){
        $(nextButtonBy).click();
    }

    public boolean isQuestionNumberStringPresent(){
        return isElementPresentBy(questionNumberStringBy);
    }

    public String getTextFromQuestionNumberString() {
        return $(questionNumberString).getText();
    }

    public void clickOnAnswerAndWait(){
        if (!isElementAbsentBy(privateMessageSumbitBy)) {
            $(privateMessageSumbitBy).click();
        }
        $(firstAnswerButtonBy).click();
    }


}
