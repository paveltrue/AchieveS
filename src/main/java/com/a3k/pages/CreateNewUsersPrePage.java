package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CreateNewUsersPrePage extends Page {

    private ElementsCollection createNewUsersBtn = $$(By.xpath("(.//input[@class = 'button_small'])[3]"));



    public CreateNewUsersPrePage(WebDriver driver) {
        this.driver=driver;
        //PageFactory.initElements(driver, this);
    }

    public void clickCreateNewUser(){
        $($$(createNewUsersBtn).get(0)).click();
    }

    public void closeAlert(){
        waitAndCancelAlert();
    }

}
