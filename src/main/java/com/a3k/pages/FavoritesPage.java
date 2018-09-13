package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FavoritesPage extends Page {

    private By iconsOfFavoriteOnLessonBy = By.xpath(".//table//div[contains(@class, 'favorite')]");
    private  ElementsCollection titleOfFavoriteLessons = $$(By.xpath(".//a[@class = 'title']"));
    private  By titleOfFavoriteLessonsBy = By.xpath(".//a[@class = 'title']");
    private By emptyPageMessageBy = By.xpath(".//div[@class = 'resultsContainer no_results']");
    private  WebElement titleOfPage = $(By.xpath(".//div[@class = 'title']"));



    public FavoritesPage(WebDriver driver){
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public void unchekedAllLessonOnFavorites() {
        logger.info("Uncheked all lesson On Favorites page");
        ElementsCollection temp = findEls(iconsOfFavoriteOnLessonBy);
        if(!temp.isEmpty()){
            for(WebElement el : temp){
                $(el).click();
                //waitUntilElementStale(el, 1);
            }
        }
    }

    public List<String> getTitleOfFavoriteLessonsByList() {
        List<String> result = getTextFromWebElementsByList(titleOfFavoriteLessons);
        return result;
    }

    public void clickFavoriteIconOnRequiredLessonByTitle(String nameOfLesson) {
        for(WebElement el : findEls(titleOfFavoriteLessonsBy)){
            if(nameOfLesson.equals($(el).getText())){
                WebElement temp = el.findElement(By.xpath("./../../div[contains(@class, 'favorite')]"));
                $(temp).click();
                //waitUntilElementStale(temp, 1);
            }
        }
    }

    public boolean checkPresenceOfLessonOnFavoritesPage(String nameOfLesson) {
//		waitUntilDisplayedBy(By.xpath(".//tr[contains(@style, 'none')]"), 3);
//		return getTextFromWebElementsByListBy(By.xpath(".//tr[not(contains(@style, 'none'))]//a[@class = 'title']")).contains(nameOfLesson);
        return isElementDisappears(By.xpath(String.format(".//tr[not(contains(@style, 'none'))]//a[contains(text(), '%s')]", nameOfLesson)));
    }

    public List<String> getTextOfTitleFavoriteLessons(){
        return getTextFromWebElementsByList(titleOfFavoriteLessons);
    }

    public void clickOnFirstLessonOnFavoriteSPage(){
        for(WebElement el : titleOfFavoriteLessons){
            $(el).click();
            break;
        }
    }

    public String getTextOfEmptyPageMessage() {
        waitForJSandJQueryToLoad();
        return getTextBy(emptyPageMessageBy).replace("\n", " ");
    }

    public WebElement getTitleOfPage() {
        return titleOfPage;
    }

}
