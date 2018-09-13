package com.a3k.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SearchWidgetPage extends Page {

    private WebElement advancedOptionBar = $(By.className("advancedOptionsBar"));
    private WebElement courseDDL = $(By.id("course_id"));
    private By firstLessonOnSearchTabBy = By.xpath(".//div[contains(@class, 'ml_tab moreLessonsSearch')]//*[contains(@class , 'lessonSmall')][1]");
    private WebElement placeToDropLessonsWeb = $(By.xpath("//*[contains(@class, 'mylessonsContentContainer')]"));
    private By placeToDropLessonsBy = By.xpath("//*[contains(@class, 'mylessonsContentContainer')]");
    private By allLessonsOnSearchTabBy = By.xpath(".//div[contains(@class, 'ml_tab moreLessonsSearch')]//*[contains(@class , 'lessonSmall')]//div[@class = 'lessonTitle']/..");
    private WebElement searchBar = $(By.id("searchBar"));
    private WebElement searchButton = $(By.xpath(".//*[@id='ml_search_form']/*[@class='searchButton']"));
    private By searchBarBy = By.id("searchBar");
    private By notAddedLessonBy = By.xpath("//div[@class='ml_tab moreLessonsSearch']//*[contains(@id,'lessonSearch')]//div[contains(@class,'classColor noColor')]/..");
    private By favoritesTabBy = By.xpath(".//div[@id='moreLessonsContainer']//div[@id = 'favorites-link']");
    private By searchTabLableBy = By.xpath("//div[@id='moreLessonsContainer']//div[contains(@class,'ml_view')][1]/a");
    private By favoriteTab = By.xpath(".//div[@id='moreLessonsContainer']//div[@id = 'favorites-link']");
    private WebElement favoritesTab = $(By.xpath(".//div[@id='moreLessonsContainer']//div[@id = 'favorites-link']"));
    private WebElement newForYouTab = $(By.xpath("//div[@id='moreLessonsContainer']//div[contains(@class,'ml_view')][3]/a"));
    private WebElement searchTab = $(By.xpath("//*[@id=\"moreLessonsContainer\"]/div[1]/div[1]/a"));
    private By searchButtonNewForYouBy = By.xpath("//div[@class='ml_tab moreLessonsNewForYou']//div[@class='searchButton']/a");
    private By firstLessonOnNewForYouTabBy = By.xpath("(.//div[contains(@class, 'ml_tab moreLessonsNewForYou')]//*[@class = 'searchResults']//div[@class = 'lessonTitle'])[1]");
    private By advancedOptionBarBy = By.xpath(".//*[@class = 'advancedOptionsBar']");
    private By advancedOptionBy = By.xpath(".//*[@class = 'advancedOptions']");
    private WebElement searchButtonByText = $(byText("Search"));
    private WebElement yesButtonIfLessonAlreadyAssigned = $(By.xpath("//*[@id=\"warn_dialog\"]/div[2]/input[1]"));
    private By favoriteLessonsPeriodDDLBy = By.xpath(".//div[@class = 'favoriteLessons']//select[@name = 'period']");
    private By periodDDLFromNewForYouTabBy = By.xpath("//div[@class='newForYou']//select[@name='period']");
    private By advancedOptionsDDLsBy = By.xpath(".//*[@class='advancedOptions']//select[not(contains(@style, ' none'))]");
    private By allGradesDDLBy = By.id("content_range_id");
    private WebElement lessonTypeDDL = $(By.id("lesson_type_id"));
    private By courseDDLBy = By.id("course_id");
    private By firstLessonFromResultOfActiveTab = By.xpath(".//*[contains(@class, 'ml_tab') and contains(@style, 'block') ]//*[@class = 'searchResults']//*[contains(@id, 'lessonSearch')][1]//*[@class = 'lessonTitle']");





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
        Configuration.pageLoadStrategy = "normal";
        Configuration.timeout = 10000;

        closeWalkmeNew();
        Select select = new Select(courseDDL);
        for (int i = 0; i < 2; i++) {
            try {
                sleep(1000);
                select.selectByVisibleText(course.toString().trim());
            } catch (Exception e) {
                try {
                    sleep(1000);
                } catch (Exception e1) {
                    try {
                        sleep(1000);
                        select.selectByVisibleText(course.toString().trim());
                    } catch (Exception ex) {
                        sleep(1000);
                        select.selectByVisibleText(course.toString().trim());
                    }
                }
                //select.selectByVisibleText(course.toString().trim());
            }
        }
        Configuration.timeout = 4000;
        //$(courseDDL).selectOption(course.toString().trim());
    }

    public void clickOnSearchButton() {
        while (isElementAbsentBy(searchButtonBy)) {
            $(By.id("addLessonButton")).click();
        }
        waitUntilElementClickableBy(searchButtonBy);
        $(searchButtonBy).click();
    }

    public void clickOnSearchButtonByText() {
        while (!$(searchButtonByText).isDisplayed()) {
            $(By.id("addLessonButton")).click();
        }
        $(By.xpath("//*[@id=\"ml_search_form\"]/div[4]/a")).click();
        sleep(500);
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
        int longTime = 600;
        actions().moveToElement(refEl(sourceElement)).clickAndHold(refEl(sourceElement)).perform();
        myWait(longTime);
        actions().moveByOffset(-100, 10).build().perform();
        myWait(longTime);
        actions().moveToElement(refEl(targetElement)).build().perform();
        myWait(longTime);
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

    public void enterTextInSearchBar(String text) {
//        waitUntilElementClickableBy(searchBarBy);
        findEl(searchBarBy).clear();
        logger.info("Enter text " + text + " in search bar.");
        enterTextInInput(searchBarBy, text);
    }

    public void dragAndDropFirstNotAddedLessonBy() {
        waitUntilElementsAppearsLocatedBy(notAddedLessonBy);
        ElementsCollection elements = findEls(notAddedLessonBy);
        sleep(1000);
        dragAndDrop(elements.get(0), placeToDropLessonsWeb);
    }

    public String getClassFavoritesTab() {
        return getAttributeBy(favoritesTabBy, "class");
    }

    public void clickOnSearchTab() {
        logger.info("Click on Search tab on search widget.");
        waitUntilElementClickableBy(searchTabLableBy);
        $(searchTabLableBy).click();
    }

    public void clickFirstLessonOnSearchTab() {
        clickActions(firstLessonOnSearchTabBy);
        waitForJSandJQueryToLoad();
    }

    public void clickOnFavoritesTab() {
        waitForJSandJQueryToLoad();
        if (!getAttribute($(favoriteTab), "class").contains("viewPick")) {
            $(favoritesTab).click();
        }
        waitForJSandJQueryToLoad();
    }

    public void clickOnNewForYouTab() {
        logger.info("Click on New For You tab on search widget.");
        $(newForYouTab).click();
    }

    public void clickOnSearchButtonOnNewForYouTab() {
        waitUntilElementClickableBy(searchButtonNewForYouBy);
        $(searchButtonNewForYouBy).click();
    }

    public void clickFirstLessonOnNewForYouTab() {
        clickActions(firstLessonOnNewForYouTabBy);
        waitForJSandJQueryToLoad();
    }

    public void addSomeLessons(int n) { //WORKS WITH 12 OR LESS
        clickOnAdvancedOptionsBy();
        Courses course = null;
        if (isAdminLanguageSelectorPresent()) {
            if (getLanguage().toLowerCase().contains("english")) {
                course = Courses.NEWS;
            } else if (getLanguage().toLowerCase().contains("español")) {
                course = Courses.NEWS_ESPAN;
            } else {
                Assert.fail("No one right language fiend");
            }
        } else {
            course = Courses.NEWS;
        }

        final Courses selectedCourse = course;
        selectInCourseDDL(course);
        clickOnSearchButtonByText();
        selectSomeElementsInListViaActionsBy(allLessonsOnSearchTabBy, n);

        addFirstLesson();

        sleep(1000);
        if ($(yesButtonIfLessonAlreadyAssigned).isDisplayed()){
            ($(yesButtonIfLessonAlreadyAssigned)).click();
        }
        sleep(1000);
    }

    public void clickOnAdvancedOptionsBy() {
        waitUntilElementClickableBy(advancedOptionBarBy);
        int i = 0;
        while (!isDisplayedBy(advancedOptionBy) && i < 15) {
            i++;
            $(advancedOptionBarBy).click();
        }
    }

    public enum Courses {
        NEWS("News"),
        NEWS_ESPAN("Noticias"),
        CAREER_EXPLORATION("Career Exploration"),
        NON_FICTION_LITERACY("Nonfiction Literacy"),
        ACHIEVE_INTENSIVE("Achieve Intensive"),
        JUST_FOR_ME("Just For Me"),
        CAREER_EXPLORATION_ESPAN("Exploración vocacional"),
        ACHIEVE_INTENSIVE_GRADE_6("Achieve Intensive (Grade 6)"),
        ACHIEVE_INTENSIVE_GRADE_6_ESPAN("Achieve Intensivo (Grado 6)"),
        ACHIEVE_INTENSIVE_GRADE_10_12("Achieve Intensive (Grades 10-12)"),
        ACHIEVE_INTENSIVE_GRADE_10_12_ESPAN("Achieve Intensivo (Grados 10-12)"),
        FLUENCY_YEARS_2_12("Fluency (Years 2-12)"),
        FLUENCY_YEARS_2_12_ESPAN("Fluidez (Grados 2-12)");

        private String name;

        Courses(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public void selectInCourseDDL(Courses course) {
        logger.info("Set course " + course);
//        Select select = new Select(courseDDL);
//        select.selectByVisibleText(course.toString().trim());
        $(courseDDL).selectOption(course.toString().trim());
    }

    public void addFirstLesson() {
        dragAndDropBy(firstLessonOnSearchTabBy, placeToDropLessonsBy);
        logger.debug("Added first lesson");
    }

    public void myWait(int longTime) {
        try {
            Thread.sleep(longTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isAllFavoritesDDLDisplayedBy() {
        return isDisplayedByNEW(favoriteLessonsPeriodDDLBy);
    }

    public boolean isPeriodDDLDisplayedBy() {
        return isDisplayedByNEW(periodDDLFromNewForYouTabBy);
    }

    public boolean isAdvancedOptionsDDLsDisplayedBy() {
        return isDisplayedByNEW(advancedOptionsDDLsBy);
    }

    public void selectOptionFromAllGradesDDL(String value) {
        logger.info("Select option by value from 'All Grades' DDL " + value);
        waitUntilElementClickableBy(allGradesDDLBy);
        selectFromDDLbyValueBy(allGradesDDLBy, value);
    }

    public void clickOnTab(String tab){
        if (tab.equalsIgnoreCase("Search")){
            $(searchTab).click();
        } else if (tab.equalsIgnoreCase("Favorites")){
            $(favoritesTab).click();
        } else if (tab.equalsIgnoreCase("NewForYou")){
            $(newForYouTab).click();
        }
    }

    public enum LessonTypes {
        ALL_LESSON_TYPES("All Lesson Types"),
        ARTICLE_ONLY("Article Only"),
        LESSONS_WITH_TECHNOLOGY_ENHANCED_ITEMS("Lessons with Technology Enhanced Items"),
        NEWS_ARCHIVE_LESSONS("News Archive Lessons"),
        STRATEGIC_LESSONS("Strategic Lessons");

        private String lessonType;

        LessonTypes(String name) {
            this.lessonType = name;
        }

        @Override
        public String toString() {
            return lessonType;
        }
    }

    public void expandAdvancedOptions() {
        if (!$(advancedOptionBy).getAttribute("style").contains("block")) {
            clickJSByClassName("advancedOptionsBar");
        }
    }

    public void selectFromLessonTypeDDLFirstItemContains(com.a3k.pages.SearchWidgetPage.LessonTypes str) {
        selectFirstItemContainsDDL(lessonTypeDDL, str.toString());
    }

    public void clearTextInputInSearchBar() {
        clickOnTab("Search");
        findEl(searchBarBy).clear();
    }

    public WebElement getFirstLessonSearchTab() {
        waitUntilElementClickableBy(firstLessonOnSearchTabBy);
        return findEl(firstLessonOnSearchTabBy);
    }

    public void selectFromCourseDDLFirstItemContainsBy(Courses course) {
        selectFirstItemContainsDDLBy(courseDDLBy, course.toString());
    }

    public String getNameOfFirstLesson() {
        waitUntilAttributeToBeNotEmpty(firstLessonFromResultOfActiveTab, "textContent");
        return getText(firstLessonFromResultOfActiveTab);
    }

    public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
        //Actions dragAndDrop = new Actions(driver);
        int longTime = 500;
        actions().moveToElement(sourceElement).clickAndHold(sourceElement).perform();
        myWait(longTime);
        actions().moveByOffset(-100, 10).build().perform();
        myWait(longTime);
        actions().moveToElement(targetElement).build().perform();
        myWait(longTime);
        actions().release().build().perform();
    }

    public void dragAndDrop(WebElement sourceElement, By targetElement) {
        //Actions dragAndDrop = new Actions(driver);
        int longTime = 500;
        actions().moveToElement(sourceElement).clickAndHold(sourceElement).perform();
        myWait(longTime);
        actions().moveByOffset(-100, 10).build().perform();
        myWait(longTime);
        actions().moveToElement(refEl(targetElement)).build().perform();
        myWait(longTime);
        actions().release().build().perform();
    }

}
