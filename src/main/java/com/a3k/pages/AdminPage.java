package com.a3k.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AdminPage extends Page {


    private By backToMenuLinkBy = By.xpath(".//*[@class = 'label']");
    private ElementsCollection listOfUsersRows = $$(By.xpath(".//*[@id='edit_users']//tr[ ./td[@class='row1' or @class='row2']]"));
    private WebElement editClassInformation = $(By.xpath("//a[@href='/options/teacher/class/edit_classes.php']"));
    private WebElement editClassInformation1 = $(By.xpath("//*[@id=\"appContentStart\"]/div[2]/div/div[4]/a[2]"));
    private WebElement saveChangesEditClassInformation = $(By.xpath("//*[@name='btnSave']"));
    private WebElement districtName = $(By.xpath("(//*[@class='report_text' and not(./b)])[1]"));
    private WebElement districtName1 = $(By.xpath("//*[@id=\"site_content\"]/table/tbody/tr[1]/td[2]/form/table[1]/tbody/tr[1]/td[2]/table/tbody/tr[1]/td[2]"));
    private WebElement schoolName = $(By.xpath("(//*[@class='report_text' and not(./b)])[2]"));
    private WebElement schoolName1 = $(By.xpath("//*[@id=\"site_content\"]/table/tbody/tr[1]/td[2]/form/table[1]/tbody/tr[1]/td[2]/table/tbody/tr[2]/td[2]"));
    private ElementsCollection listOfEditUsersLinks = $$(By.xpath(".//*[contains(@href,'/user/edit_users.php')]"));
    private By buttonSearchBy = By.xpath(".//*[@id='user-search-btn'] | .//*[@id='user-search-btn']");
    private By editStudentAndTeacherBy = By.xpath("(.//a[contains(@href,'user/edit_users.php')])[1]");
    private By selectClassDDLBy = By.id("class_id");
    private By submitButtonBy = By.xpath(".//*[(@type='submit' or @type='button') and (@value = 'Submit' or @value='Enviar' or @value='Save Changes')]");
    private By removeFromClassBy = By.xpath(".//*[@onclick='javascript:doSubmit(2);']");
    private WebElement userProfile = $(By.id("user_profile"));
    private By createNewClassBy = By.xpath(".//a[contains(@href, 'task=1')]");
    private ElementsCollection listOfNumbersBeforeUsers = $$(By.xpath(".//*[@id='edit_users']//tr[ ./td[@class='row1' or @class='row2']]//b[text()]"));





    public AdminPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public void clickBackToMenu() {
        logger.info("Click 'Back to Menu'");
        $(backToMenuLinkBy).click();
    }

    public ElementsCollection getListOfUserRows() {
        return listOfUsersRows;
    }

    public void clickOnEditClassInformation() {
        //$editClassInformation.click();
        //$(editClassInformation).shouldBe(Condition.enabled).click();
        closeWalkmeNew();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        closeWalkmeNew();
        closeWalkmeNew();
        $(byText("Edit class information")).click();
    }

    public void waitUntilSaveChangesEditInforButtonAppears() {
        $(saveChangesEditClassInformation).shouldBe(Condition.visible);
    }

    public String getDisctrictName() {
        //return $(districtName).getText();

        String asd = $(By.xpath("//*[@id=\"site_content\"]/table/tbody/tr[1]/td[2]/form/table[1]/tbody/tr[1]/td[2]/table/tbody/tr[1]/td[2]")).getText();
        return asd;
    }

    public String getSchoolName() {
        //String text = $(By.xpath("//*[@id=\"site_content\"]/table/tbody/tr[1]/td[2]/form/table[1]/tbody/tr[1]/td[2]/table/tbody/tr[2]/td[2]")).getText();
        //String text = $(By.xpath("(//*[@class=\"report_text\" and not(./b)])[2]")).getText();
        String text = $(By.cssSelector("#site_content > table > tbody > tr:nth-child(1) > td:nth-child(2) > form > table:nth-child(3) > tbody > tr:nth-child(1) > td.title > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText();
        return text;
    }

    public ElementsCollection getListOfEditUserLink() {
        return listOfEditUsersLinks;
    }

    public void waitPageLoad() {
        waitElement(buttonSearchBy);
    }

    public void openEditStudentAndTeacher() {
        logger.info("Open 'Edit student and teacher' Editor");
        clickJS(editStudentAndTeacherBy);
    }

    public void selectClass(String className) {
        waitElement(selectClassDDLBy);
        Select selectClass = new Select(findEl(selectClassDDLBy));
        selectClass.selectByVisibleText(className);
    }

    public void clickSubmitButton() {
        $(submitButtonBy).click();
    }

    public void clickOnRemoveFromClassButton() {
        clickJS(removeFromClassBy);
    }

    public void waitUntilUserProfileShown() {
        $(userProfile).shouldBe(Condition.visible);
    }

    public void clickOnCreateNewClass() {
        logger.info("Opening 'Create New Class' wizard");
        closeWalkme();
        closeWalkmeNew();
        closeWalkmeNew();
        $(createNewClassBy).click();
    }

    public ElementsCollection getListOfNumbersBeforeUsers() {
        return listOfNumbersBeforeUsers;
    }

    public void clickCreateNewClass() {
        logger.info("Click on 'Create New Class'");
        waitUntilElementClickableBy(createNewClassBy);
        clickJS(createNewClassBy);
    }

}
