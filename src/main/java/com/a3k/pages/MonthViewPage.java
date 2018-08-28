package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.GregorianCalendar;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MonthViewPage extends Page {
    private ElementsCollection datesOfMonth = $$(By.xpath("//td[@data-date and not(contains(@class,'fc-other-month'))]//*[@class='fc-day-number']"));
    private WebElement month = $(By.className("date"));
    private WebElement year = $(By.className("year_header"));
    private ElementsCollection days = $$(By.xpath(".//*[@class = 'fc-first fc-last']/th"));
    private WebElement searchForMoreLessonsButton = $(By.className("rolloverAnchor side_panel"));
    private WebElement arrowBack = $(By.className("datePrev"));
    private WebElement arrowNext = $(By.className("dateNext"));
    private ElementsCollection viewsOfMyLessonsPage = $$(By.xpath(".//*[@class = 'view' or @class = 'viewPick']"));
    private ElementsCollection lessonsForTheMonth = $$(By.xpath(".//*[contains(@class, 'fc-event fc-event-hori fc-event-draggable')]"));
    private WebElement yearView = $(By.xpath(".//*[@class = 'view'][4]"));

    public MonthViewPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isLessonForMonthPresent() {
        return this.isElementsExist(lessonsForTheMonth);
    }

    public boolean isViewsOfMyLessonPagePresent() {
        return this.isElementsExist(viewsOfMyLessonsPage);
    }

    public boolean isDatePresent() {
        return this.isElementExist(year) && this.isElementExist(month);
    }

    public boolean isCalendarCorrect() {
        String currentMonth = $(month).getText().trim();
        boolean isCorrect;
        GregorianCalendar calendar = new GregorianCalendar();
        int amountOfDaysInMonth = $$(datesOfMonth).size();
        switch (currentMonth) {
            case "January":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "February":

                if (calendar.isLeapYear(Integer.valueOf($(year).getText()))) {
                    isCorrect = amountOfDaysInMonth == 29;
                } else {
                    isCorrect = amountOfDaysInMonth == 28;
                }
                break;
            case "March":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "April":
                isCorrect = amountOfDaysInMonth == 30;
                break;
            case "May":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "June":
                isCorrect = amountOfDaysInMonth == 30;
                break;
            case "July":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "August":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "September":
                isCorrect = amountOfDaysInMonth == 30;
                break;
            case "October":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "November":
                isCorrect = amountOfDaysInMonth == 30;
                break;
            case "December":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "enero":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "febrero":

                if (calendar.isLeapYear(Integer.valueOf($(year).getText()))) {
                    isCorrect = amountOfDaysInMonth == 29;
                } else {
                    isCorrect = amountOfDaysInMonth == 28;
                }
                break;
            case "marzo":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "abril":
                isCorrect = amountOfDaysInMonth == 30;
                break;
            case "mayo":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "junio":
                isCorrect = amountOfDaysInMonth == 30;
                break;
            case "julio":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "agosto":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "septiembre":
                isCorrect = amountOfDaysInMonth == 30;
                break;
            case "octubre":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            case "noviembre":
                isCorrect = amountOfDaysInMonth == 30;
                break;
            case "diciembre":
                isCorrect = amountOfDaysInMonth == 31;
                break;
            default:
                isCorrect = false;
        }

        return isCorrect;
    }

    public boolean isArrowBackWorksCorrect() {
        boolean isCorrect;
        String currentMonth = $(month).getText();
        arrowBack.click();
        String lastMonth = $(month).getText();
        switch (currentMonth) {
            case "January":
                isCorrect = lastMonth.equals("December");
                break;
            case "February":
                isCorrect = lastMonth.equals("January");
                break;
            case "March":
                isCorrect = lastMonth.equals("February");
                break;
            case "April":
                isCorrect = lastMonth.equals("March");
                break;
            case "May":
                isCorrect = lastMonth.equals("April");
                break;
            case "June":
                isCorrect = lastMonth.equals("May");
                break;
            case "July":
                isCorrect = lastMonth.equals("June");
                break;
            case "August":
                isCorrect = lastMonth.equals("July");
                break;
            case "September":
                isCorrect = lastMonth.equals("August");
                break;
            case "October":
                isCorrect = lastMonth.equals("September");
                break;
            case "November":
                isCorrect = lastMonth.equals("October");
                break;
            case "December":
                isCorrect = lastMonth.equals("November");
                break;
            case "enero":
                isCorrect = lastMonth.equals("diciembre");
                break;
            case "febrero":
                isCorrect = lastMonth.equals("enero");
                break;
            case "marzo":
                isCorrect = lastMonth.equals("febrero");
                break;
            case "abril":
                isCorrect = lastMonth.equals("marzo");
                break;
            case "mayo":
                isCorrect = lastMonth.equals("abril");
                break;
            case "junio":
                isCorrect = lastMonth.equals("mayo");
                break;
            case "julio":
                isCorrect = lastMonth.equals("junio");
                break;
            case "agosto":
                isCorrect = lastMonth.equals("julio");
                break;
            case "septiembre":
                isCorrect = lastMonth.equals("agosto");
                break;
            case "octubre":
                isCorrect = lastMonth.equals("September");
                break;
            case "noviembre":
                isCorrect = lastMonth.equals("octubre");
                break;
            case "diciembre":
                isCorrect = lastMonth.equals("noviembre");
                break;
            default:
                isCorrect = false;
        }
        return isCorrect;
    }

    public boolean isArrowNextWorksCorrect() {

        boolean isCorrect;
        String currentMonth = $(month).getText();
        $(arrowNext).click();
        String nextMonth = $(month).getText();
        switch (currentMonth) {
            case "January":
                isCorrect = nextMonth.equals("February");
                break;
            case "February":
                isCorrect = nextMonth.equals("March");
                break;
            case "March":
                isCorrect = nextMonth.equals("April");
                break;
            case "April":
                isCorrect = nextMonth.equals("May");
                break;
            case "May":
                isCorrect = nextMonth.equals("June");
                break;
            case "June":
                isCorrect = nextMonth.equals("July");
                break;
            case "July":
                isCorrect = nextMonth.equals("August");
                break;
            case "August":
                isCorrect = nextMonth.equals("September");
                break;
            case "September":
                isCorrect = nextMonth.equals("October");
                break;
            case "October":
                isCorrect = nextMonth.equals("November");
                break;
            case "November":
                isCorrect = nextMonth.equals("December");
                break;
            case "December":
                isCorrect = nextMonth.equals("January");
                break;
            case "enero":
                isCorrect = nextMonth.equals("febrero");
                break;
            case "febrero":
                isCorrect = nextMonth.equals("marzo");
                break;
            case "marzo":
                isCorrect = nextMonth.equals("abril");
                break;
            case "abril":
                isCorrect = nextMonth.equals("mayo");
                break;
            case "mayo":
                isCorrect = nextMonth.equals("junio");
                break;
            case "junio":
                isCorrect = nextMonth.equals("julio");
                break;
            case "julio":
                isCorrect = nextMonth.equals("agosto");
                break;
            case "agosto":
                isCorrect = nextMonth.equals("septiembre");
                break;
            case "septiembre":
                isCorrect = nextMonth.equals("octubre");
                break;
            case "octubre":
                isCorrect = nextMonth.equals("noviembre");
                break;
            case "noviembre":
                isCorrect = nextMonth.equals("diciembre");
                break;
            case "diciembre":
                isCorrect = nextMonth.equals("enero");
                break;
            default:
                isCorrect = false;
        }
        return isCorrect;

    }

    public boolean isThereAllDaysOfWeek() {
        return $$(days).size() == 7;
    }

    public YearViewPage clickYearView() {
        $(yearView).click();
        return new YearViewPage(driver);
    }

    public String getYear() {
        return $(year).getText();
    }

    public String getDate() {
        return $(month).getText();
    }
}
