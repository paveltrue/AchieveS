package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class Search extends Page {

    private ElementsCollection favoritesIconsInTable = $$(By.xpath(".//tr[td[contains(@class, 'row')]]//a[@class = 'titletopic' and not( text() = 'Writing')]/..//..//div[contains(@class, 'favorite')]"));
    private WebElement advancedOptions = $(By.xpath(".//div[@class = 'advancedContainer']"));
    private By lessonsLabelTabBy = By.xpath(".//*[contains(@class , 'searchResults') and @id='tab-1']");
    private By teacherResourcesLabelTabBy = By.xpath(".//*[contains(@class , 'searchResults') and @id='tab-2']");
    protected By noResultsMessageLessonsBy = By.xpath(".//*[@id='tab-1-no-results']//*");
    protected By teacherResourcesResultsTableBy = By.xpath(".//*[@id='tab-2-div']//td[contains(@class, 'row')]");
    private  By titleOfLessonsBy = By.xpath(".//a[@class = 'title']");
    protected By noResultsMessageTeacherResourcesBy = By.xpath(".//*[@id='tab-2-no-results']//*");
    protected By resultsContainerBy = By.xpath(".//div[@class = 'resultsContainer']");
    protected By noResultsMessageBy = By.xpath(".//div[@class = 'resultsContainer no_results']");
    private By searchResultsLabelBy = By.xpath(".//*[@class = 'searchResults' and not(@id)]");
    private By topicColumnBy = By.xpath(".//*[@id = 'tab-1-div']/tr[1]/td[2]");
    private By topPaginationControlsBy = By.xpath(".//tbody[not(@id)]//*[@id = 'tab-1-pages']/a");
    private By bottomPaginationControlsBy = By.xpath(".//tbody[@id='tab-1-div']//*[@id = 'tab-1-pages']/a");
    private By lessonColumnBy = By.xpath(".//*[@id = 'tab-1-div']/tr[1]/td[1]");
    private WebElement resourceColumn = $(By.xpath(".//*[@id='tab-2-div']/tr[1]/td"));
    private By advancedOptionsTitleBy = By.xpath(".//div[contains(@class,'advancedContainer')]/div[1]");
    protected By advancedOptionsAllResouceTypesLabelBy = By.xpath(".//*[contains(@class, 'resource_type_id')]");
    private By advancedOptionsAllResouceTypesDDLBy = By.id("resource_type_id");
    private By advancedOptionsItemsBy = By.xpath( ".//*[@class = 'advancedOptions']//span[not(contains(@style,'none'))]");
    protected By notExpandedOptionsBy = By.xpath(".//*[@class = 'advancedOptions']//li[span[not(contains(@class, 'Expanded'))]]/span");
    protected By advancedOptionsAllGradesLabelBy = By.xpath(".//*[@class = 'advancedOptions']//span[contains(@class, 'content_range_id')]");
    protected By advancedOptionsAllCoursesLabelBy = By.xpath(".//*[@class = 'advancedOptions']//span[contains(@class, 'course_id')]");
    protected By advancedOptionsAllTopicsLabelBy = By.xpath(".//*[@class = 'advancedOptions']//span[contains(@class, 'category_id')]");
    protected By advancedOptionsAllContentLabelBy = By.xpath(".//*[@class = 'advancedOptions']//span[contains(@class, 'content_area_id')]");
    protected By advancedOptionsAllLessonTypesLabelBy = By.xpath(".//*[@class = 'advancedOptions']//span[contains(@class, 'lesson_type_id')]");
    protected By advancedOptionsAllStandardsLabelBy = By.xpath(".//*[@class = 'advancedOptions']//span[contains(@class, 'standard_id')]");
    protected By advancedOptionsSearchByDateBy = By.xpath(".//*[@class = 'advancedOptions']//span[contains(@class, 'date_range')]");
    private By titleOfFirstItemInTableBy = By.xpath("(.//*[@class= 'summary'])[1]/a");
    private By descriptionOfFirstItemInTableBy = By.xpath("(.//*[@class= 'summary'])[1]/div");
    private By advancedOptionsAllCoursesDDLBy = By.id("course_id");


    public Search(WebDriver driver) {
        this.driver = driver;
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

    public boolean isAdvancedOptionsExpand() {
        return isWebElementContainsVulueOfAttributte(advancedOptions, "style", " block");
    }

    public boolean isLessonsTabActive(){
        return getAttributeBy(lessonsLabelTabBy, "class").contains(" pick");
    }

    public boolean isTeacherResourcesTabActive(){
        return getAttributeBy(teacherResourcesLabelTabBy, "class").contains(" pick");
    }

    public String getTextOfNoResultsMessageLessons() {
        return getTextBy(noResultsMessageLessonsBy).replace("\n", " ").trim();
    }

    public void clickOnTeacherResources() {
        $(teacherResourcesLabelTabBy).click();
    }

    public boolean isTeacherResourcesResultsDisplayed() {
        return isDisplayedByNEW(teacherResourcesResultsTableBy);
    }

    public boolean isLessonsDisplayed() {
        return isDisplayedByNEW(titleOfLessonsBy);
    }

    public String getTextOfNoResultsMessageTeacherResources() {
        return getTextBy(noResultsMessageTeacherResourcesBy).replace("\n", " ").trim();
    }

    public boolean isSearchResultsAreDisplayed() {
        return isDisplayedBy(resultsContainerBy);
    }

    public String getTextOfNoResultsMessage() {
        return getTextBy(noResultsMessageBy).replace("\n", " ").trim();
    }

    public boolean isSearchResultsLabelDisplayed() {
        return isDisplayedBy(searchResultsLabelBy);
    }

    public boolean isLessonsTabDisplayed() {
        return isDisplayedBy(lessonsLabelTabBy);
    }

    public boolean isTeacherResourcesTabDisplayed() {
        return isDisplayedBy(teacherResourcesLabelTabBy);
    }

    public String getTextOfTopicColumnHeader() {
        return getTextBy(topicColumnBy).trim();
    }

    public int countOfTopPaginationControls() {
        return findEls(topPaginationControlsBy).size();
    }

    public int countOfBottomPaginationControls() {
        waitUntilAttributeToBeNotEmpty(bottomPaginationControlsBy, "class");
        return findEls(bottomPaginationControlsBy).size();
    }

    public boolean isFirstPageButtonHiglighted() {
        return getAttribute(findEls(bottomPaginationControlsBy).get(0), "class").contains(" pick");
    }

    public void clickOnElementInPaginationControls(int i){
        clickOnWebElementInListBy(bottomPaginationControlsBy, i);
    }

    public boolean isPageButtonHiglightedBy(String str) {
        By temp =By.id("page8");
      //  waitUntilElementStaleBy(temp, 4);
//		waitUntilAttributeContains(temp, "class", " pick", 8);
        for(WebElement el: findEls(bottomPaginationControlsBy)){
            if($(el).getText().equals(str)){
                return getAttribute(el, "class").contains(" pick");
            }
        }
        return false;
    }

    public String getTextOflessonColumnHeader() {
        return getTextBy(lessonColumnBy).trim();
    }

    public String getTextOfResourceColumnHeader() {
        return getText(resourceColumn).trim();
    }

    public void openAdvancedOptions() {
        waitUntilElementClickableBy(advancedOptionsTitleBy);
        clickActions(advancedOptionsTitleBy);
    }

    public List<String> getItemsOfAllResourceTypeDDL() {
        openAllResourceTypeDDL();
        return getTextOfAllOptionsFromSelect(advancedOptionsAllResouceTypesDDLBy);
    }

    public void openAllResourceTypeDDL() {
        if(!getAttributeBy(advancedOptionsAllResouceTypesLabelBy, "class").contains(" subtitleExpanded")){
            $(advancedOptionsAllResouceTypesLabelBy).click();
        }
    }

    public void selectItemFromAllResourcesDDL(String str) {
        waitUntilElementClickableBy(advancedOptionsAllResouceTypesDDLBy);
        selectFromDDLBy(advancedOptionsAllResouceTypesDDLBy, str);
    }

    public boolean isAllGradesActive() {
        return !getAttributeBy(advancedOptionsAllGradesLabelBy, "class").contains("noExpand");
    }

    public boolean isAllCoursesActive() {
        return !getAttributeBy(advancedOptionsAllCoursesLabelBy, "class").contains("noExpand");
    }

    public boolean isAllTopicsActive() {
        return !getAttributeBy(advancedOptionsAllTopicsLabelBy, "class").contains("noExpand");
    }

    public boolean isAllContentActive() {
        return !getAttributeBy(advancedOptionsAllContentLabelBy, "class").contains("noExpand");
    }

    public boolean isAllLessonTypesActive() {
        return !getAttributeBy(advancedOptionsAllGradesLabelBy, "class").contains("noExpand");
    }

    public boolean isAllStandardslActive() {
        return !getAttributeBy(advancedOptionsAllStandardsLabelBy, "class").contains("noExpand");
    }

    public boolean isSearchByDateActive() {
        return !getAttributeBy(advancedOptionsSearchByDateBy, "class").contains("noExpand");
    }

    public void clickSearch() {
        $(searchButtonBy).click();
        waitElement(searchButtonBy);
    }

    public void clickSearchNew() {
        $(searchButtonXpath).click();
    }

    public boolean isTitleOfFirstItemPresent() {
        return isDisplayedBy(titleOfFirstItemInTableBy);
    }

    public boolean isDescriptionOfFirstItemPresent() {
        return isDisplayedBy(descriptionOfFirstItemInTableBy);
    }

    public void openAllCoursesDDL() {
        if(!getAttributeBy(advancedOptionsAllCoursesLabelBy, "class").contains(" subtitleExpanded")){
            $(advancedOptionsAllCoursesLabelBy).click();
        }
    }

    public void selectItemFromAllCoursesDDL(String str) {
        selectFromDDLBy(advancedOptionsAllCoursesDDLBy, str);
    }

    public String getSelectedOptionFromAllResouceTypesDDL() {
        return getSelectedValueFromDDLBy(advancedOptionsAllResouceTypesDDLBy);
    }

}
