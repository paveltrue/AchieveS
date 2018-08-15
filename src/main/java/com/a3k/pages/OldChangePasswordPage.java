package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class OldChangePasswordPage extends Page {

    WebElement oldPasswordInput = $(By.xpath("//*[@name='password' or @name='current_password']"));
    WebElement newPasswordInput = $(By.name("new_password"));
    WebElement confirmPasswordInput = $(By.xpath("//*[@name='confirm_password' or @name='new_password_confirm']"));
    WebElement submitButton = $(By.xpath("//*[@type='submit' or @id='reset_pwd_submit']"));
    WebElement wrongPasswordPopupOkButton = $(By.xpath("//div[@class='ui-dialog-buttonset']/button"));
    private WebElement closeButton = $(By.id("responseButton"));

    public void changePassword(String oldPassword, String newPassword){

        waitForPageToLoad();
        $(oldPasswordInput).clear();
        $(oldPasswordInput).setValue(oldPassword);
        $(newPasswordInput).clear();
        $(newPasswordInput).setValue(newPassword);
        $(confirmPasswordInput).clear();
        $(confirmPasswordInput).setValue(newPassword);
        $(submitButton).click();
    }

    public void closeWrongPasswordPopup() {
        wrongPasswordPopupOkButton.click();
    }

    public void clickCloseButton(){
        closeButton.click();
    }

}
