package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.GregorianCalendar;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class YearViewPage extends Page {
    private WebElement startOfLesson = $(By.className("lessonStart"));
    private WebElement listOfTopics = $(By.id("cat"));
    private WebElement currentStudyYear = $(By.className("dateSelector"));
    private ElementsCollection daysInMonths = $$(By.className("fc-year-monthly-td"));
    By daysForOneMonth = By.xpath(".//td[not(contains(@class,'fc-other-month'))]//*[@class='fc-day-number']");
    private WebElement firstSubCategory = $(By.xpath("//*[@class='subcategory']/div[@class='collapsedArrow']"));
    private WebElement firstCategory = $(By.xpath("//*[@class='category']/div[@class='collapsedArrow']"));
    private ElementsCollection months = $$(By.xpath(".//*[contains(@class , 'fc-year-monthly-name')]/a"));
    private WebElement lesson = $(By.xpath("//li[@class='lesson']"));
    private WebElement infoIconLessonSelected = $(By.xpath("//li[@class='lesson selected']/div[@class='infoIcon']"));
    private WebElement startDateLessonBar = $(By.xpath("//p[@class='dates']/a[1]"));
    private WebElement endDateLessonBar = $(By.xpath("//p[@class='dates']/a[2]"));

    public YearViewPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isAmountOfDaysInMonthCorrect() {
        int error = 0;
        int amountOfDays = 0;
        for (WebElement month : months) {
            String titleMonth = $(month).getText();

            String nameOfMonth = titleMonth.split(" ")[0];
            switch (nameOfMonth) {
                case "June":
                    amountOfDays = $($$(daysInMonths).get(0)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 30)
                        error++;
                    break;
                case "July":
                    amountOfDays = $($$(daysInMonths).get(1)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 31)
                        error++;
                    break;
                case "August":
                    amountOfDays = $($$(daysInMonths).get(2)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 31)
                        error++;
                    break;
                case "September":
                    amountOfDays = $($$(daysInMonths).get(3)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 30)
                        error++;
                    break;
                case "October":
                    amountOfDays = $($$(daysInMonths).get(4)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 31)
                        error++;
                    break;
                case "November":
                    amountOfDays = $($$(daysInMonths).get(5)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 30)
                        error++;
                    break;
                case "December":
                    amountOfDays = $($$(daysInMonths).get(6)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 31)
                        error++;
                    break;
                case "January":
                    amountOfDays = $($$(daysInMonths).get(7)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 31)
                        error++;
                    break;
                case "February":
                    amountOfDays = $($$(daysInMonths).get(8)).findAll(daysForOneMonth).size();
                    GregorianCalendar calendar = new GregorianCalendar();

                    int year = Integer.valueOf(titleMonth.split(" ")[1]);
                    if (calendar.isLeapYear(year)) {
                        if (amountOfDays != 29)
                            error++;
                    } else if (amountOfDays != 28)
                        error++;
                    break;
                case "March":
                    amountOfDays = $($$(daysInMonths).get(9)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 31)
                        error++;
                    break;
                case "April":
                    amountOfDays = $($$(daysInMonths).get(10)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 30)
                        error++;
                    break;
                case "May":
                    amountOfDays = $($$(daysInMonths).get(11)).findAll(daysForOneMonth).size();
                    if (amountOfDays != 31)
                        error++;
                    break;

            }
        }
        return error == 0;
    }

    public boolean isCurrentYearPresent() {
        return this.isElementExist(currentStudyYear);
    }

    public boolean isListOfTopicsPresent() {
        return this.isElementExist(listOfTopics);
    }

    public boolean isIconStartOfLessonPresent() {
        return this.isElementExist(startOfLesson);
    }

    public String getStudyYearsDate() {
        return $(currentStudyYear).getText().trim();
    }

    public void clickOnFirstCollapsedSubcategory() {
        $(firstSubCategory).click();
    }

    public void expandFirstCategory() {
        $(firstCategory).click();
    }

    public void selectLesson()
    {
        $(lesson).click();

        $(infoIconLessonSelected).click();

    }

    public String getStartDateLessonBar() {
        return $(startDateLessonBar).getText();
    }

    public String getEndDateLessonBar() {
        return $(endDateLessonBar).getText();
    }

}
