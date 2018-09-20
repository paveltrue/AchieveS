package com.a3k.pages;


import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class PreReportPage extends Page {

    private WebElement whichProgramDDL = $(By.xpath("//select[@name='rpt_program_id']"));
    private WebElement whichClassDDL = $(By.xpath(".//select[@name='class_id']"));
    private By whichClassDDLBy = By.xpath(".//select[@name='class_id']");
    private WebElement whichUserDDL = $(By.xpath("//select[@name='user_id']"));
    private WebElement whichLanguageDDL = $(By.xpath("//select[@name='force_language' or @name = 'rpt_language']"));
    protected By whichLanguageDDLBy = By.xpath(".//select[@name='force_language' or @name = 'rpt_language']");
    private WebElement backToMenu = $(By.xpath("//a[@href='/options/teacher/' and @class='label']"));
    private ElementsCollection listOfClasses = $$(By.xpath("//select[@name='class_id']/option[ not(@value='ALL') and not(@value='0') and not(@value='')]"));
    private ElementsCollection listOfUsers = $$(By.xpath("//select[@name='user_id']/option[ not(@value='ALL') and not(@value='0') and not(@value='')]"));
    private WebElement viewReportButton = $(By.id("rptSubmit101"));
    //@FindBy(xpath="//a[@name='rep']")
    private WebElement reportTitle = $(By.xpath(".//*[@class = 'report_text'  or @name='rep']"));
    private By reportTitleBy = By.xpath(".//*[@class = 'report_text'  or @name='rep']");
    private WebElement startDateMonthDDL = $(By.xpath(".//select[@name = 'start_month']"));
    private By startDateMonthDDLBy = By.xpath(".//select[@name = 'start_month']");
    private WebElement startDateDayDDL = $(By.xpath(".//select[@name = 'start_day']"));
    private By startDateDayDDLBy = By.xpath(".//select[@name = 'start_day']");
    private By startDatedayDDLBy = By.xpath(".//select[@name = 'start_day']");
    private WebElement startDateYearInput = $(By.xpath(".//input[@name = 'start_year']"));
    private WebElement endDateMonthDDL = $(By.xpath(".//select[@name = 'end_month']"));
    private By endDateMonthDDLBy = By.xpath(".//select[@name = 'end_month']");
    private WebElement endDateDayDDL = $(By.xpath(".//select[@name = 'end_day']"));
    private WebElement endDateYearInput = $(By.xpath(".//input[@name = 'end_year']"));
    private WebElement whichActivityTypes = $(By.xpath(".//select[@name='lexileOp']"));
    private ElementsCollection promptsLabels = $$(By.xpath(".//*[@class='report_options_header']"));
    private WebElement allActivityTypesDDL = $(By.xpath(".//select[@name = 'atype_stype']"));
    private ElementsCollection summariseDDLItems = $$(By.xpath(".//*[@class='report_options_header']//*[contains(text(),'summari')]/../following-sibling::*[2]//option"));

    public Set<String> getSummariseDDLItems() {
        HashSet<String> result = new HashSet<>();
        for (WebElement el : summariseDDLItems) {
            result.add($(el).getText());
        }
        return result;
    }


    public Set<String> getPromptsLabelsText() {
        HashSet<String> result = new HashSet<>();
        for (WebElement el : promptsLabels) {
            result.add($(el).getText());
        }
        return result;
    }


    public WebElement getWhichActivityTypes() {
        return whichActivityTypes;
    }

    public WebElement getStartDateMonthDDL() {
        return startDateMonthDDL;
    }

    public String getStartDateMonth() {
        return getTextOfActiveItemFromSelectBy(startDateMonthDDLBy);
    }

    public WebElement getStartDateDayDDL() {
        return startDateDayDDL;
    }

    public String getStartDateDay() {
        return getTextOfActiveItemFromSelectBy(startDateDayDDLBy);
    }

    public WebElement getStartDateYearInput() {
        return startDateYearInput;
    }

    public String getValueFromStartDateYearInput() {
        return $(startDateYearInput).getAttribute("value");
    }

    public WebElement getEndDateMonthDDL() {
        return endDateMonthDDL;
    }

    public String getEndDateMonth() {
        return getTextOfActiveItemFromSelectBy(endDateMonthDDLBy);
    }

    public WebElement getEndDateDayDDL() {
        return endDateDayDDL;
    }

    public WebElement getEndDateYearInput() {
        return endDateYearInput;
    }

    public String getValueFromEndDateYearInput() {
        return $(endDateYearInput).getAttribute("value");
    }


    public PreReportPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    private boolean isAllOptionsFromDDL(WebElement ddl) {

        WebElement all = getSelectedOptionFromSelectAsWebElement(ddl);

        String value = all.getAttribute("value");

        return value.equals("0") || value.equals("ALL");
    }

    public boolean isAllOptionsPresentInDDL(String name) {

        switch (name) {

            case "programs":
                return isAllOptionsFromDDL(whichProgramDDL);

            case "class":
                return isAllOptionsFromDDL(whichClassDDL);

            case "users":
                return isAllOptionsFromDDL(whichUserDDL);
        }

        return false;
    }

    private boolean isOtherOptionsPresentInDDL(WebElement ddl) {

        List<WebElement> options = getOptionsFromSelect(ddl);
        boolean check = true;

        for (WebElement option : options) {

            String value = $(option).getAttribute("value");

            boolean isemptyOptions = value.equals("0") || value.equals("ALL") || value.equals("");

            if (!isemptyOptions) {

                if ($(option).getText().equals("")) {
                    check = false;
                    break;
                }
            }
        }

        return check;
    }

    public boolean isDDLHasOtherOptions(String name) {

        switch (name) {

            case "programs":
                return isOtherOptionsPresentInDDL(whichProgramDDL);

            case "class":
                return isOtherOptionsPresentInDDL(whichClassDDL);

            case "users":
                return isOtherOptionsPresentInDDL(whichUserDDL);
        }

        return false;
    }

    public void clickOnBackToMenu() {
        backToMenu.click();
    }

    public List<String> getListOfClasses() {

        List<String> listOfClassNames = new ArrayList<String>();

        for (WebElement classOption : listOfClasses)
            listOfClassNames.add($(classOption).getText());

        return listOfClassNames;
    }

    public List<String> getListOfUsers() {

        List<String> listOfUserNames = new ArrayList<String>();

        for (WebElement userOption : listOfUsers)
            listOfUserNames.add($(userOption).getText());

        return listOfUserNames;
    }

    public void selectProgramByValueFromDDL(String value) {

        selectFromDDLbyValue(whichProgramDDL, value);

        waitUntilLoaded();
    }

    public void selectClassFromDDL(String name) {

        selectFromDDL(whichClassDDL, name);

        waitUntilLoaded();
    }

    public void selectClassFromDDLByIndex(int index) {

        selectFromDDLByIndex(whichClassDDL, index);

        waitUntilLoaded();
    }

    public void selectUserFromDDL(String name) {

        waitUntilPageLoaded();

        selectFromDDL(whichUserDDL, name);

        waitUntilLoaded();
    }

    public void selectLanguageByValueFromDDL(String value) {

        selectFromDDLbyValue(whichLanguageDDL, value);

        waitUntilLoaded();
    }

    public void clickViewReportButton() {
        $(viewReportButton).click();
    }

    public String getReportTitleText() {
        waitUntilAttributeToBeNotEmpty(reportTitleBy, "textContent");
        return getText(reportTitleBy).trim();
    }

    public WebElement getReportTitle() {
        return reportTitle;
    }


    public WebElement getViewReportButton() {
        return viewReportButton;
    }

    public WebElement getWhichClassDDL() {
        //waitUntilLoaded();
        return $(whichClassDDL);
    }

    public By getWhichClassDDLBy() {
        return whichClassDDLBy;
    }


    public void chooseItemInAllActivityTypeDDL(String text) {
        Select sel = new Select(allActivityTypesDDL);
        sel.selectByVisibleText(text);
    }

    public void chooseItemInAllActivityTypeDDLByValue(String value) {
        Select sel = new Select(allActivityTypesDDL);
        sel.selectByValue(value);
    }

    public WebElement getWhichLanguageDDL() {
        return $(whichLanguageDDL);
    }

    public String getDefaultValueOfWhichClassDDL() {
        return getDefaultValueFromDDL(whichClassDDL);
    }

    public String getDefaultValueOfStartDateMonthDDL() {
        waitUntilElementClickableBy(startDateMonthDDLBy);
        return getDefaultValueFromDDLBy(startDateMonthDDLBy);
    }

    public String getDefaultValueOfStartDateDayDDL() {
        waitUntilElementClickableBy(startDatedayDDLBy);
        return getDefaultValueFromDDLBy(startDatedayDDLBy);
    }

    public boolean isWhichLanguageDDLDisplayed() {
        return isDisplayedBy(whichLanguageDDLBy);
    }


}
