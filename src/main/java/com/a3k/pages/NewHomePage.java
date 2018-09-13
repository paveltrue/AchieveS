package com.a3k.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

import static com.codeborne.selenide.Selenide.$;

public class NewHomePage extends HomePage {

    private WebElement hamburgerMenu = $(By.xpath("//div[@class='hamburger']"));
    private WebElement logo = $(By.xpath("//img[@alt='Achieve3000']"));
    private By logoBy = By.xpath("//img[@alt='Achieve3000']");
    private WebElement topNavBanner = $(By.xpath("//*[@class='navbar-anchor topNav']"));
    private WebElement stateEdition = $(By.xpath("//div[@class='leftNav']/div[@class='subContain']/span[@class='stateEdition']"));
    private WebElement currentClass = $(By.xpath("//span[@class='selectClass']"));
    private WebElement backgroundImage = $(By.xpath("//section[@class='slick-slide slick-active hero']/div[@class='containImg']/div[contains(@style,'background-image')]"));
    private WebElement startButtonEn = $(By.xpath("//section[@class='slick-slide slick-active hero']//a[contains(@class,'startButton')]"));
    private WebElement startButtonSp = $(By.xpath("//a[@class='startButton' and @title='Comenzar']"));
    private WebElement lessonTitle = $(By.xpath("//div[@class='heroContent']/h1"));
    private WebElement lessonDescription = $(By.xpath("//div[@class='heroContent']/p[@class='description']"));
    private WebElement lessonExpirationDate = $(By.xpath("//div[@class='heroContent']/p[@class='expires']"));
    private WebElement currentDate = $(By.xpath("//section[@data-index = '0']//div[@class='date']/span[@class='month']"));
    private WebElement currentDayOfWeek = $(By.xpath("//section[@data-index = '0']//div[@class='date']/span[@class='day']"));
    private WebElement points = $(By.id("topPointsContainer"));
    private WebElement awardsBadge = $(By.xpath("//div[contains(@class,'middleNav')]//*[@class='awards']/span"));
    private WebElement activities = $(By.xpath("//div[contains(@class,'middleNav')]/div[@class='activities']/span[@class='title']"));
    private WebElement activitiesPoints = $(By.xpath("//*[contains(@class,'middleNav')]//div[@class='activities']//span[@class='details']"));
    private WebElement topCareer = $(By.xpath("//*[contains(@class,'middleNav')]//div[contains(@class,'career')]//span[@class='details']"));
    private WebElement carrerCenterTitle = $(By.xpath("//div[@class='title' and (contains(text(),'Career Center') or contains(text(),'Centro laboral'))]"));
    private By logoDownBy = By.xpath(".//*[@class = 'logo1']");
    private By itemsOfHamburgerMenuBy = By.xpath(".//*[contains(@class, 'hamburgerMenu')]//span");
    private By lexileSelectorBy = By.xpath(".//*[@class = 'selectorLabelText' and @title='Lexile']");
    private By lexileDDLBy = By.xpath(".//*[@class='selectorMenu' and @title='Lexile']");
    private By languageSelectorBy = By.xpath(".//*[@class = 'selectorLabelText' and @title='Language']");
    private By languageDDLBy = By.xpath(".//*[@class='selectorMenu' and @title='Language']");
    private By studentsSelectorBy = By.xpath(".//*[@class='selectorLabelText' and contains(@title, 'student')]");
    private By itemsOfStudentsDDLBy = By.xpath(".//*[@class='selectorMenu' and contains(@title, 'student')]/*");
    private By motivationMenuArrowBy = By.id("motivationDropdownArrow");
    private By lexileToggleBy = By.xpath(".//*[@id='lexile-toggle']//*[contains(@class,'career-slider')]");
    private By lexileValueBy = By.xpath(".//*[@id='lexile-details']//*[@class = 'lexile-number']");
    private By careerTitleBy = By.xpath(".//*[@class='career-type-title']");
    private By careerGoalLexileValueBy = By.xpath(".//*[@id='lexile-details']//*[contains(text(), 'Goal')]/../following-sibling::*[@class = 'lexile-number']");
    private By currentLexileValueBy = By.xpath(".//*[@id='lexile-details']//*[contains(text(), 'Current')]/../following-sibling::*[@class = 'lexile-number']");
    private By careerCenterButtonBy = By.xpath(".//button[contains(@value, 'careerCenterButton')]");
    private By seeTheRulesButtonBy = By.xpath(".//button[contains(@value, '.seeTheRules')]");
    private By topCareerBy = By.xpath(".//*[contains(@class, 'middleNav')]//div[contains(@class,'career')]//span[@class='details']");


    public String getTopCareerValue(){
        return getTextBy(topCareerBy);
    }

    public void clickOnSeeTheRulesButton(){
        $(seeTheRulesButtonBy).click();
    }

    public void clickOnCareerCenterButton(){
        $(careerCenterButtonBy).click();
    }

    public String getCurrentLexileValue() {
        if (!$(currentLexileValueBy).isDisplayed()){
            checkLexile();
        }
        return getTextBy(currentLexileValueBy);
    }

    public String getCareerGoalLexileValue(){
        return getTextBy(careerGoalLexileValueBy);
    }

    public boolean isCareerGoalLexileValueDisplayed(){
        return isDisplayedBy(careerGoalLexileValueBy);
    }

    public String getCareerTitle(){
        return getTextBy(careerTitleBy);
    }

    public boolean isCareerTitleDisplayed(){
      return isDisplayedBy(careerTitleBy);
    }

    public void openLexileDetails(){
        if(!isLexileValueDisplayed()){
            clickOnLexileToggle();
        }
    }

    public String getLexileValue(){
        try {
            if (!$(lexileValueBy).isDisplayed()){
                checkLexile();
            }
            return getTextBy(lexileValueBy);
        } catch (NoSuchElementException e){
            return null;
        }
    }

    public boolean isLexileValueDisplayed(){
        return isDisplayedBy(lexileValueBy);
    }


    public boolean isLexileToggleDisplayed(){
      return isDisplayedBy(lexileToggleBy);
    }

    public void clickOnLexileToggle(){
        waitElement(lexileToggleBy);
        clickActions(lexileToggleBy);
    }

    public void clickOnMotivationMenuArrow(){
        clickJS(motivationMenuArrowBy);
    }

    public void chooseStudentByNameContains(String nameContains){
        By xpath = By.xpath(".//*[@class='selectorMenu' and contains(@title, 'student')]/*[(contains(translate(text()," +
                            "'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), '"+nameContains+"'))] ");
        waitUntilElementClickableBy(xpath);
        $(xpath).click();
    }

    public void clickStudentsSelector(){
        $(studentsSelectorBy).click();
    }

    public boolean isLanguageDdlDisplayed(){
        return isDisplayedBy(languageDDLBy);
    }

    public void clickOnLanguageDDL(){
        $(languageSelectorBy).click();
    }


    public boolean isLexileDdlDisplayed(){
        return isDisplayedBy(lexileDDLBy);
    }

    public void clickOnLexileDDL(){
        $(lexileSelectorBy).click();
    }


    public Set<String> getAllItemsFromHamburgerMenu(){
        return getTextFromWebElementsBySet(itemsOfHamburgerMenuBy);
    }

    public NewHomePage(WebDriver driver) {
        super(driver);
        //PageFactory.initElements(driver, this);
    }

    public boolean isLogoPresent() {
        return $(logo).isDisplayed();
    }

    public boolean isLogoDownPresent() {
        return isDisplayedBy(logoDownBy);
    }

    public String getLogoSrc() {
        return $(logo).getAttribute("src");
    }

    public boolean isStartEnButtonPresent() {
        return isElementExist(startButtonEn);
    }

    public boolean isStartSpButtonPresent() {
        return $(startButtonSp).isDisplayed();
    }

    public boolean isTopNavPresent() {
        return $(topNavBanner).isDisplayed();
    }

    public String getEdiiton() {
        return $(stateEdition).getText();
    }

    public boolean isBackgroundImagePresent() {
        return isElementExist(backgroundImage);
    }

    public String getCurrentDate() {
        return $(currentDate).getText();
    }

    public String getCurrentDayOfWeek() {
        return $(currentDayOfWeek).getText();
    }

    public void clickStartButton(String lang) {
        if (lang.equalsIgnoreCase("spanish")){
            $(startButtonSp).click();
        } else{
            $(startButtonEn).click();
        }
    }

    public String getLessonTitle() {
        return $(lessonTitle).getText();
    }

    public String getLessonDescription() {
        return $(lessonDescription).getText();
    }

    public String getLessonExpirationDate() {
        return $(lessonExpirationDate).getText();
    }

    public String getPoints() {
        return $(points).getText();
    }

    public String getAwardsBadge() {
        return $(awardsBadge).getText();
    }

    public String getActivitiesPoints() {
        return $(activitiesPoints).getText();
    }

    public String getTopCareer() {
        return $(topCareer).getText();
    }

    public void scrollPageDownToLogo(){
        scrollToEl(logoDownBy);
    }

    public boolean isCareerCenterOpened() {
        return isElementVisible(carrerCenterTitle);
    }

    public void checkLexile(){
        while (!$(currentLexileValueBy).isDisplayed()){
            clickActions(lexileToggleBy);
        }
    }
}
