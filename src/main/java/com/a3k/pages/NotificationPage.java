package com.a3k.pages;

import com.a3k.utils.DateHelper;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class NotificationPage extends Page {

    protected WebElement header = $(By.className("notificationHeaderContainer"));
    private WebElement notificationBox = $(By.className("notificationsContainer"));
    private ElementsCollection dateParts = $$(By.xpath("(//*[@class='date'])[1]/*"));

    public NotificationPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isHeaderPresent() {
        return isElementPresent(header);
    }

    public boolean isNotificationPresent() {
        return isElementPresent(notificationBox);
    }

    public boolean isDateFormatCorrect(String userType) {

        String date = getDateFromNotification().concat("2017").trim();
        String datePattern;

        if (userType.equals("us")) {
            datePattern = "EEEE MMM d yyyy";
        } else {
            datePattern = "EEEE d MMM yyyy";
        }
        logger.info("Actual date: " + date);
        return DateHelper.isValidFormat(datePattern, date);
    }

    private String getDateFromNotification() {
        StringBuilder sb = new StringBuilder("");

        for (WebElement item : dateParts) {
            sb.append(item.getText()).append(" ");
        }
        return sb.toString();
    }
}
