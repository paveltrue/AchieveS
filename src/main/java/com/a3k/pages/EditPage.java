package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditPage extends Page {

    private By schoolBy = By.xpath(".//*[@name='school_id']/.. | //tr[2]//td[@class='report_text'][2]");
    private By classNameBy = By.xpath(".//*[@name='class_id[]']/.. | //tr[3]//td[@class='report_text'][2]");
    private By loginNameBy = By.id("login_name");


    public EditPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public String getSchoolName() {
        return getTextBy(schoolBy);
    }

    public String getClassNames() {
        return getTextBy(classNameBy);
    }

    public String getLoginName() {
        return getAttributeBy(loginNameBy, "value");
    }

}
