package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.actions;

public class SearchWidgetPage extends Page {

    private WebElement advancedOptionBar = $(By.className("advancedOptionsBar"));
    private WebElement courseDDL = $(By.id("course_id"));
    private By firstLessonOnSearchTabBy = By.xpath(".//div[contains(@class, 'ml_tab moreLessonsSearch')]//*[contains(@class , 'lessonSmall')][1]");
    private By placeToDropLessonsBy = By.xpath("//*[contains(@class, 'mylessonsContentContainer')]");
    private By allLessonsOnSearchTabBy = By.xpath(".//div[contains(@class, 'ml_tab moreLessonsSearch')]//*[contains(@class , 'lessonSmall')]//div[@class = 'lessonTitle']/..");
    private WebElement searchBar = $(By.id("searchBar"));
    private WebElement searchButton = $(By.xpath(".//*[@id='ml_search_form']/*[@class='searchButton']"));



    public SearchWidgetPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public void clickOnAdvancedOption() {
        closeWalkmeNew();
        closeWalkmeNew();
        $(advancedOptionBar).click();
    }

    public void selectInCourseDDL(String course) {
        logger.info("Set course " + course);
        Select select = new Select(courseDDL);
        closeWalkmeNew();
        select.selectByVisibleText(course.toString().trim());
    }

    public void clickOnSearchButton() {
        while (isElementAbsentBy(searchButtonBy)) {
            $(By.id("addLessonButton")).click();
        }
        waitUntilElementClickableBy(searchButtonBy);
        $(searchButtonBy).click();
    }

    public void addLessonsOnSinglePage(int n) {
        waitForPageToLoad();
        waitForPageToLoad();
        addLessonsToCollection(selectSomeElementsInListViaActionsBy(allLessonsOnSearchTabBy, n));
    }

    public void addLessonsToCollection(boolean lessonsSelected) {
        if (lessonsSelected) {
            dragAndDropBy(firstLessonOnSearchTabBy, placeToDropLessonsBy);
            logger.debug("Added lessons");
        }
    }

    public void dragAndDropBy(By sourceElement, By targetElement) {
        //Actions dragAndDrop = new Actions(driver);
        int longTime = 500;
        actions().moveToElement(refEl(sourceElement)).clickAndHold(refEl(sourceElement)).perform();
        //myWait(longTime);
        actions().moveByOffset(-100, 10).build().perform();
        //myWait(longTime);
        actions().moveToElement(refEl(targetElement)).build().perform();
        //myWait(longTime);
        actions().release().build().perform();
        actions().release(refEl(targetElement)).build().perform();
    }

    public boolean selectSomeElementsInListViaActionsBy(By by, int count) {
        //Actions a = new Actions(driver);
        ElementsCollection resultList = findEls(by);

        boolean selected = false;
        if ($$(resultList).size() > 0) {
            actions().keyDown(Keys.SHIFT).build().perform();
            for (int i = 0; i < count; i++) {
                if (!isWebElementContainsVulueOfAttributte(findEls(by).get(i),
                        "class", "ui-state-highlight")) {
                    actions().click(findEls(by).get(i)).perform();
                }
            }
            actions().keyUp(Keys.CONTROL).build().perform();
            selected = true;
        }
        return selected;
    }

    public boolean isSearchBarPresent() {
        return isElementExist(searchBar);
    }
    public boolean isSearchButtonPresent() {
        return isElementExist(searchButton);
    }

    public boolean isAdvancedOptionsSectionPresent() {
       return isElementExist(advancedOptionBar);
    }

}
