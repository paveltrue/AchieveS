package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MyLessonsActivitiesPage extends Page {

    private By whichSchoolDDLBy = By.xpath("//select[@name='school_id']");
    private By dateStartElementsBy = By.xpath("//select[@name = 'start_month']/ancestor::td[@class='report_menu_label']/*");
    private By dateEndElementsBy = By.xpath("//select[@name = 'start_month']/ancestor::td[@class='report_menu_label']/*");
    private WebElement startMonthDdl = $(By.name("start_month"));
    private WebElement startDayDdl = $(By.name("start_day"));
    private WebElement startYearDdl = $(By.name("start_year"));
    private WebElement endMonthDdl = $(By.name("end_month"));
    private WebElement endDayDdl = $(By.name("end_day"));
    private WebElement endYearDdl = $(By.name("end_year"));
    private WebElement viewReportButton = $(By.id("rptSubmit101"));


    public MyLessonsActivitiesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectSchool(String schoolName) {
        Select sel = new Select($(whichSchoolDDLBy));
        sel.selectByVisibleText(schoolName);
        waitUntilJQueryToLoad();
        waitUntilPageLoaded();
    }

    public String getStartDate() {

        String date = "";
        for (WebElement element : $$(dateStartElementsBy)) {
            if ($(element).getTagName().equals("select")) {
                date += new Select(element).getFirstSelectedOption().getText() + " ";
            } else {
                date += element.getAttribute("value") + " ";
            }
        }
        return date.trim();
    }

    public String getEndDate() {
        String date = "";
        for (WebElement element : $$(dateEndElementsBy)) {
            if (element.getTagName().equals("select")) {
                date += new Select(element).getFirstSelectedOption().getText() + " ";
            } else {
                date += element.getAttribute("value") + " ";
            }
        }
        return date.trim();
    }

    public void setStartDate(String day, int month, String year) {
        setStartDay(day);
        setStartMonth(month);
        setStartYear(year);
        logger.info("Set start date: " + day + " " + month + year);
    }

    public void setStartDay(String day) {
        new Select(startDayDdl).selectByVisibleText(day);
    }

    public void setStartMonth(int number) {
        new Select(startMonthDdl).selectByIndex(number);
    }

    public void setStartYear(String year) {
        $(startYearDdl).setValue(year);
    }

    public void setEndDate(String day, int month, String year) {
        setEndDay(day);
        setEndMonth(month);
        setEndYear(year);
    }

    public void setEndDay(String day) {
        new Select(endDayDdl).selectByVisibleText(day);
    }

    public void setEndMonth(int number) {
        new Select(endMonthDdl).selectByIndex(number);
    }

    public void setEndYear(String year) {
        $(endYearDdl).setValue(year);
    }

    public void clickViewReportButton() {
        $(viewReportButton).click();
    }

}
