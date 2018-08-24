package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class CreateNewUsersPage extends Page {

    private ElementsCollection firstNameCell = $$(By.xpath(".//input[@name = 'first_name[0]']"));
    private WebElement lastNameCell = $(By.xpath(".//input[@name = 'last_name[0]']"));
    private By lastNameCellBy = By.xpath(".//input[@name = 'last_name[0]']");
    private By passwordCellBy = By.xpath(".//input[@name = 'password[0]']");
    private WebElement saveChangesButton = $(By.xpath("(.//a[@class = 'toolbar'])[2]"));


    private static final String mCHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    private static final int STR_LENGTH = 5;
    Random random = new Random();

    public CreateNewUsersPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public String createANewUser(){
        $($$(firstNameCell).get(0)).setValue(createRandomString());
        $(lastNameCell).setValue(createRandomString());
        String firstName = $$(firstNameCell).get(0).getAttribute("value");
        String lastName = $(lastNameCell).getAttribute("value");

        return firstName + "." + lastName;
    }

    public String createRandomString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < STR_LENGTH; i++) {
            int number = random.nextInt(mCHAR.length());
            char ch = mCHAR.charAt(number);
            builder.append(ch);
        }
        return builder.toString();
    }

    public boolean isPasswordGeneratedAutomatically(){
        boolean isPassword = false;
        $(lastNameCellBy).click();
        String password = getAttributeBy(passwordCellBy, "value");
        if(!password.equals(""))
            isPassword = true;
        return isPassword;
    }

    public void clickSaveChanges(){
        logger.info("Save Changes");
        $(saveChangesButton).click();
    }

    public void closeAlert(){
//        Alert alert = driver.switchTo().alert();
//        alert.accept();
        confirm();
    }

}
