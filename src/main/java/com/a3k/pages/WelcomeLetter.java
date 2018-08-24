package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$$;

public class WelcomeLetter extends Page {

    private ElementsCollection levelSetHeaders = $$(By.xpath("//img[contains(@src,'ls_header.jpg')]"));
    private ElementsCollection userInformation = $$(By.xpath("//span[@class='user_password']// ancestor::table[1]"));
    private ElementsCollection userMessages = $$(By.xpath("//td[@height='505']"));
    private ElementsCollection KBTBEMHeaders = $$(By.xpath("//img[contains(@src,'../images/') and contains(@src,'_header.jpg')]"));



    public WelcomeLetter(WebDriver driver){
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public ElementsCollection getAllLevelSetHeaders(){
        return levelSetHeaders;
    }

    public ElementsCollection getAllUsersInformatio(){
        return userInformation;
    }

    public String getFirstMessageText(){
        return $$(userMessages).get(0).getText();
    }

    public ElementsCollection getAllKBTBEMHeaders(){
        return KBTBEMHeaders;
    }

}
