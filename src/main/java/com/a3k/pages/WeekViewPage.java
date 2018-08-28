package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WeekViewPage extends Page {
    private ElementsCollection daysInAWeek = $$(By.xpath(".//*[@class = 'fc-first fc-last']/th"));
    private WebElement dateOfWeek = $(By.className("date"));
    private WebElement year = $(By.cssSelector("span.year_header"));
    private WebElement arrowBack = $(By.className("datePrev"));
    private WebElement arrowNext = $(By.className("dateNext"));
    private ElementsCollection lessonsForTheWeek = $$(By.xpath(".//*[contains(@class, 'fc-event fc-event-hori fc-event-draggable')]"));
    private WebElement seacrhForMoreLessonsButton = $(By.className("rolloverAnchor side_panel"));
    private ElementsCollection viewsOfMyLessonsPage = $$(By.xpath(".//*[@class = 'view' or @class = 'viewPick']"));
    private WebElement monthViewLink = $(By.xpath(".//*[@class = 'view'][3]"));

    public WeekViewPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isThereAllDaysOfWeek() {
        return $$(daysInAWeek).size() == 7;
    }

    public boolean isThereLessonForTheWeek() {
        return this.isElementsExist(lessonsForTheWeek);
    }

    public boolean isDateDisplayed() {
        return this.isElementExist(dateOfWeek) && this.isElementExist(year);
    }

    public boolean isArrowBackWorksCorrectly() {
        String presentWeek = $(dateOfWeek).getText().trim();
        $(arrowBack).click();
        String lastWeek = $(dateOfWeek).getText().trim();
        return !presentWeek.equals(lastWeek);
    }

    public boolean isArrowNextWorksCorrectly() {
        String presentWeek = $(dateOfWeek).getText().trim();
        $(arrowNext).click();
        String nextWeek = $(dateOfWeek).getText().trim();
        return !presentWeek.equals(nextWeek);
    }

    public boolean isViewsOfMyLessonsPresent() {
        return this.isElementsExist(viewsOfMyLessonsPage);
    }

    public MonthViewPage clickOnMonthView() {
        $(monthViewLink).click();
        return new MonthViewPage(driver);
    }

    public String getWeekDates() {
        return $(dateOfWeek).getText().trim();
    }

    public String getYear() {
        return $(year).getText();
    }
}
