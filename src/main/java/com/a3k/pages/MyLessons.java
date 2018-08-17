package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class MyLessons extends Page {

    private By doubleBackArrowBy = By.xpath("//img[contains(@src,'doubleback')]");
    By nextArrowOnListViewBy = By.xpath("//img[contains(@src,'arrow_next')]");
    By notStartedLessonsBy = By.xpath("//tr[contains(@id,'lesson-') and not(contains(.,'N/A')) and not(contains(.,'Song'))  and not(contains(.,'Strategy Development')) and not(.//div[contains(@class,'Bonus')]) and not(.//*[contains(text(),'Challenge')] or .//*[contains(text(),'Solo para m')]) and not(.//a[@title!=''])]//a[@class='title']");


    public MyLessons(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean verifyCompletedLesson(String lessonId) {
        WebElement lesson = findLesson(lessonId);

        if (lesson == null) {
            return false;
        } else {
            return true;
        }
    }

    public WebElement findLesson(String lessonId) {
        By neededLesson = By.xpath("//tr[contains(@id,'lesson-" + lessonId + "') and count(.//img[contains(@src, 'progress_complete')])=5]");

        if (isElementAbsentBy(neededLesson)) {
            if ($(doubleBackArrowBy).exists()) {
                $(doubleBackArrowBy).click();
            }
            while ($(nextArrowOnListViewBy).exists()) {
                if ($(neededLesson).exists()) {
                    return findEl(neededLesson);
                }
                $(nextArrowOnListViewBy).click();
            }
        } else {
            return findEl(neededLesson);
        }
        return null;
    }

    public String openAnyNotStartedLesson() {
        String id = "";
        if (isElementsExist(notStartedLessonsBy)) {
            WebElement lesson = findEls(notStartedLessonsBy).get(0);
            id = getAttribute(lesson, "href").split("id=")[1].split("&c")[0];
            clickJSWebEl(lesson);
            logger.info("Open lesson with id = " + id);
            waitForPageToLoad();
            if (isDisplayedBy(By.xpath("//span[contains(@class, 'closethick')]"))) {
                $(By.xpath("//span[contains(@class, 'closethick')]")).click();
            }
            return id;
        } else {
            if ($(doubleBackArrowBy).exists()) {
                $(doubleBackArrowBy).click();
            }
            int pageCount = 16;
            while ($(nextArrowOnListViewBy).exists() && (pageCount > 0)) {
                if ($(notStartedLessonsBy).exists()) {
                    WebElement lesson = findEls(notStartedLessonsBy).get(0);
                    id = getAttribute(lesson, "href").split("id=")[1].split("&c")[0];
                    clickJSWebEl(lesson);
                    logger.info("Open lesson with id = " + id);
                    waitForPageToLoad();
                    if (isDisplayedBy(By.xpath("//span[contains(@class, 'closethick')]"))) {
                        $(By.xpath("//span[contains(@class, 'closethick')]")).click();
                    }
                    return id;
                }
                $(nextArrowOnListViewBy).click();
                pageCount--;
            }
        }
        return id;
    }

    public void sortStep1DescendingAndGotoPageNum(String pageNum) {
        super.goToNewUrl(String.format("/my_lessons?sort=step1&sort=focus&sort_dir=desc&page_index=%s", pageNum));
    }
}
