package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class LeadershipEditionPage extends Page {

    public LeadershipEditionPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    private By dataOfActivityAvereageScoreCard = By.xpath(".//*[@class = 'owl-item']//*[@id='ActivityAverageScore']/preceding-sibling::*/*[contains(@class, 'indicator')]");
    private By logoutButton = By.xpath(".//*[@id='headerDesktop']//a[contains(@ng-click, 'logout') and text()='Logout']");

    public List<String> getInformationFromAverageScoreCard(){
        waitUntilPageLoaded();
        waitUntilAttributeToBeNotEmpty(dataOfActivityAvereageScoreCard, "textContent");
        return getTextFromWebElementsByListBy(dataOfActivityAvereageScoreCard);
    }

    public void clickLogoutButtonJS(){
        waitUntilPageLoaded();
        clickJS(logoutButton);
    }

}
