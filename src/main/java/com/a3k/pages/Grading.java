package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class Grading extends Page{

    private WebElement byStudentTab = $(By.xpath("//div[@title='By Student']"));

    public Grading(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isByStudentPresent() {
        return byStudentTab.isDisplayed();
    }

}
