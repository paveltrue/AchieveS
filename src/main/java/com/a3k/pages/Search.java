package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Search extends Page {

    private ElementsCollection favoritesIconsInTable = $$(By.xpath(".//tr[td[contains(@class, 'row')]]//a[@class = 'titletopic' and not( text() = 'Writing')]/..//..//div[contains(@class, 'favorite')]"));


    public Search(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public String getValueOfPositionFromFirstLessonInList() {
        return $$(favoritesIconsInTable).get(0).getCssValue("float");
    }

    public String getCssValueFromFirstFavoriteIcon(String attribute) {
        return $$(getFavoritesIconsInTable()).get(0).getCssValue(attribute);
    }

    public ElementsCollection getFavoritesIconsInTable() {
        return favoritesIconsInTable;
    }

    public void clickOnFirstFavoriteIcon() {
        $(getFavoritesIconsInTable().get(0)).click();
    }

}
