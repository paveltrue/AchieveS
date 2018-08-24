package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class ReportPage extends Page {

    private WebElement title = $(By.xpath("//td[@class='report_title']"));


    public ReportPage(WebDriver driver){
        this.driver=driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isTitlePresent(){
        return $(title).isDisplayed();
    }

}
