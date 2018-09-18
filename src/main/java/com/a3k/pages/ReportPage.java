package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ReportPage extends Page {

    private WebElement title = $(By.xpath("//td[@class='report_title']"));
    private By dateWelcomeLetterBy = By.xpath("//table[@class='text']//td[contains(text(), ',')]");
    private By dateLineInReportBy = By.xpath(".//td[@class = 'report_dateline']");
    private By buttonOpenClassWithDateBy = By.xpath("//td[@class = 'report_row' and contains(text(), '%')]/ancestor::tr//input");
    private By classActivityDate = By.xpath("//td[contains(text(), '/')  and contains(text(), ':')]");
    private By listRowStartDateBy = By.xpath("//tr[@class='report_row_footer']//td[@class='report_row'][3]");
    private By listRowEndDateBy = By.xpath("//tr[@class='report_row_footer']//td[@class='report_row'][4]");
    private By plusButtonClassWithDateBy = By.xpath("//tr[contains(@class, 'report_row')]//td[contains(text(), '-')]/ancestor::tr/following-sibling::tr//input");
    private By dateOfClass = By.xpath("//tr[contains(@class, 'report_row')]//td[contains(text(), '-')]");
    private By listTitleDate = By.xpath("//td[contains(text(),'Total Students')]/following-sibling::td[br and not(contains(text(), 'Initial')) and not(contains(text(),'Current')) ]");
    private By asignButtonBy = By.xpath("//table[@id]//input[@value='assign']");
    private By listDateAssignActivitesBy = By.xpath("//select[@name='day']/ancestor::td[@class]/*");

    public ReportPage(WebDriver driver){
        this.driver=driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isTitlePresent(){
        return $(title).isDisplayed();
    }

    public boolean checkContainsYear(String year){
        switchToNextWindow();
        logger.debug("find element with text: " + year);
        WebElement report = $(By.xpath("//*[@id='table53']//td[contains(text(), '" +year+ "')]"));
        return isElementExist(report);
    }

    public String getDateWelcomeLetter() {
        return getTextBy(dateWelcomeLetterBy);
    }

    public String getTextOfDateLineInReport(){
        waitUntilAttributeToBeNotEmpty(dateLineInReportBy, "textContent");
        return getTextBy(dateLineInReportBy).trim();
    }

    public void openClassWithDate() {
        if (!waitElementIsClickable(buttonOpenClassWithDateBy)) {
            Assert.fail("No class with date");
        }
        $(buttonOpenClassWithDateBy).click();
    }

    public String getActivityDate() {
        //waitUntilVisibleAllElementsLocatedBy(classActivityDate, 5);
        return getTextBy(classActivityDate);
    }

    public List<String> getListStartDate() {
        List<String> listDate = new ArrayList<>();
        for (WebElement element : $$(listRowStartDateBy)) {
            listDate.add($(element).getAttribute("innerHTML"));
        }
        return listDate;
    }

    public List<String> getListEndDate() {
        List<String> listDate = new ArrayList<>();
        for (WebElement element : $$(listRowEndDateBy)) {
            listDate.add($(element).getText());
        }
        return listDate;
    }

    public String getClassDate() {
        $(plusButtonClassWithDateBy).click();
        return getTextBy(dateOfClass);
    }

    public List<String> getListTitleRowDate() {
        List<String> listDate = new ArrayList<>();
        for (WebElement element : $$(listTitleDate)) {
            listDate.add($(element).getText());
        }
        return listDate;
    }

    public void clickAsignButton() {
        clickJS(asignButtonBy);
    }

    public String getDateActivite() {
        String date = "";
        for (WebElement element : $$(listDateAssignActivitesBy)) {
            date += new Select(element).getFirstSelectedOption().getText() + " ";
        }

        return date.substring(0, date.length() - 1);
    }

}
