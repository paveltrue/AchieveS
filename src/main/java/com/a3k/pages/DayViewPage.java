package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DayViewPage extends Page {

    private ElementsCollection lessonsForToday = $$(By.xpath(".//*[@class='lessonTitle day']"));
    private WebElement todayDate = $(By.className("date"));
    private WebElement arrowToPreviousDate = $(By.className("arrowBack"));
    private WebElement arrowToNextDate = $(By.className("arrowNext"));
    private WebElement linkWeekView = $(By.xpath(".//*[@class = 'view'][2]"));
    private WebElement lessonBar = $(By.xpath("//*[@class='lessonTitle day']"));
    private WebElement startDateLessonBar = $(By.xpath("//p[@class='dates']/a[1]"));
    private WebElement endDateLessonBar = $(By.xpath("//p[@class='dates']/a[2]"));

    public DayViewPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isTodayDatePresent() {
        return isElementExist(todayDate);
    }

    public boolean isLessonForTodayPresent() {
        return isElementsExist(lessonsForToday);
    }

    public String getTodayDate() {
        return $(todayDate).getText();
    }

    public boolean isArrowBackWorkCorrectly() {
        String today = $(todayDate).getText();
        today = today.split(",")[0];
        $(arrowToPreviousDate).click();
        String prevDay = $(todayDate).getText();
        prevDay = prevDay.split(",")[0];

        return today.equalsIgnoreCase(prevDay);
    }


    public boolean isArrowNextWorkCorrectly() {
        String today = $(todayDate).getText();
        today = today.split(",")[0];
        $(arrowToNextDate).click();

        String nextDay = todayDate.getText();
        nextDay = nextDay.split(",")[0];

        return today.equalsIgnoreCase(nextDay);
    }

    public WeekViewPage clickOnWeekButton() {
        $(linkWeekView).click();
        return new WeekViewPage(driver);
    }

    public void clickOnLessonBar() {
        $(lessonBar).click();
    }

    public String getStartDateLessonBar() {
        return $(startDateLessonBar).getText();
    }

    public String getEndDateLessonBar() {
        return $(endDateLessonBar).getText();
    }
}
