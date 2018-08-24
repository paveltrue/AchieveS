package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DataUploadAssistantPage extends Page {

    private WebElement usernameInput = $(By.xpath("//input[@name='username']"));
    private WebElement passwordInput = $(By.xpath("//input[@name='password']"));
    private WebElement signInButton = $(By.xpath("//button[@type='submit']"));
    private WebElement programSubtab = $(By.xpath("//a[contains(@href,'#tabs-program')]"));
    private WebElement syncNowButton = $(By.xpath("//form[@name='manualSyncForm']/fieldset/input"));
    private WebElement duplicateUserAssertionToggle = $(By.xpath("//label[@for='asstn-toggle-2']"));
    private WebElement classChangeAssertionToggle = $(By.xpath("//label[@for='asstn-toggle-3']"));
    private WebElement userChangeAssertionToggle = $(By.xpath("//label[@for='asstn-toggle-4']"));
    private WebElement continueSyncButton = $(By.xpath("//input[@name='continuebtn']"));


    public DataUploadAssistantPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public void fillSignInForm(String username, String password) {
        setUsername(username);
        setPassword(password);
        clickSubmit();
        waitThreadSleep(2000);
        waitForPageToLoad();
    }

    public void setUsername(String username) {
        waitElementWebEl(usernameInput);
        logger.info("Type in username: " + username);
        $(usernameInput).setValue(username);
    }

    public void setPassword(String password) {
        logger.info("Type in password: " + password);
        $(passwordInput).setValue(password);
    }

    public void clickSubmit() {
        waitElementWebEl(signInButton);
        logger.info("Submit");
        $(signInButton).submit();
    }

    public void openDistrictsTab() {
        logger.info("Open Districts tab");
        open("http://prov11.achieve3000.com/districts?sourceid=1");
    }

    public void openDistrict(String districtName) {
        logger.info("Open district " + districtName);
        waitForPageToLoad();
        waitForJSandJQueryToLoad();
        //waitThreadSleep(1000);

        clickScroll($(By.xpath("//a[text()='" + districtName + "']")));
       // waitThreadSleep(1000);
    }

    public void openProgramSubtab() {
        logger.info("Open Program tab");
        $(programSubtab).click();
        //waitThreadSleep(1000);
    }

    public void clickSyncNow() {
        logger.info("Click Sync Now button");
        $(syncNowButton).click();
       // waitThreadSleep(1000);
    }

    public void clickDuplicateUserAssertionToggle() {
        logger.info("Click on Duplicate User Assertion toggle");
        $(duplicateUserAssertionToggle).click();
    }

    public void clickClassChangeAssertionToggle() {
        logger.info("Click on Class Change Assertion toggle");
        $(classChangeAssertionToggle).click();
    }

    public void clickUserChangeAssertionToggle() {
        logger.info("Click on User Change Assertion toggle");
        $(userChangeAssertionToggle).click();
    }

    public void clickContinueBtn() {
        logger.info("Click on Continue button");
        $(continueSyncButton).click();
    }

}
