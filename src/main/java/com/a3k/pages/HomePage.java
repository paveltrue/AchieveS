package com.a3k.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class HomePage extends Page {

    private ElementsCollection teachermaterialsButtons = $$(By.xpath("//*[@style='display: block;' and @class='instructionalMaterial']//a"));
    private WebElement lessonImg = $(By.xpath("//*[@class='photoContainer' and @style='']//img[1]"));
    private WebElement lessonSummary = $(By.xpath("//*[@style='']/*[contains(@class, 'summary') and text()]"));
    private WebElement lessonTitle = $(By.xpath("(//*[@aria-hidden='false']//*[contains(@class,'lessonTitle')])" +
            "[count(//*[@aria-hidden='false']/*[@class='card panel flip'])+1]/a | //*[@style='']/*[contains(@class, 'title')]"));
    private By startLessonButtonCSS = By.cssSelector(".startButton.button,a#home-button");
    private By lessonTitleBy = By.xpath("//*[@class='lessonHeader']/*[@class='title']/div[@class='titleText']");
    private WebElement lessonTitleOfWritingLesson = $(By.className("article_title"));
    private WebElement gradeArrow = $(By.xpath("//*[@class='gradeContainer']//*[@class='classArrow'] | //*[@class='gradeName']//img"));
    private ElementsCollection allGrades = $$(By.xpath("//*[@id='grade-dropdown']//a"));
    private WebElement activeGrade = $(By.className("gradeName"));
    private WebElement arcticleTitle = $(By.className("article_title"));
    private ElementsCollection lessonThumbnauls = $$(By.xpath("//*[normalize-space(@class)='thumb'] | //*[@class='slick-dots']/*"));
    private WebElement classArrow = $(By.xpath("//*[@class='classContainer']//*[@class='classArrow']"));
    private ElementsCollection allClasses = $$(By.xpath("//*[@data-color]"));
    private WebElement languageButton = $(By.xpath("//*[@data-dropdown='#teacher-languages-dropdown']"));
    private ElementsCollection elementsOfLanguageDDl = $$(By.xpath("//*[@action='/change_language']//a"));
    private WebElement arrowNearAvatar1 = $(By.xpath("//*[@class='name' or @class='ribbonTop']//img"));
    private WebElement arrowNearAvatar2 = $(By.xpath("//*[@class='selectName']"));
    private WebElement lexileButton = $(By.xpath("//*[@data-dropdown='#teacher-lexile-dropdown']"));
    private ElementsCollection elementsOfLexileDDl = $$(By.xpath("//*[@action='/change_lexile']//a"));
    private By searchBoxBy = By.id("search");
    private WebElement metricCompletingTheMostActivities = $(By.id("metric-4"));
    private ElementsCollection listOfStudentsComplitingTheMostActivities = $$(By.xpath(".//*[name() = 'svg']//*[name() = 'g' and @id = 'yaxis'] //*[name() = 'g' and @class = 'tick']//*[name() = 'text']"));
    private WebElement metricCircle = $(By.xpath(".//*[@id='populationSize']/.."));
    private ElementsCollection numbersOnScaleOfMetricComplitingTheMostActivities = $$(By.xpath(".//*[name() = 'svg']//*[name() = 'g' and @id = 'xaxis'] //*[name() = 'g' and contains(@class, 'tick')]//*[name() = 'text']"));
    private By dateRangeLabelBy = By.xpath("//div[@class = 'dateRange']");
    private WebElement moreButton = $(By.id("dataPanel-moreBtn"));
    private WebElement metricHighestLexileGains = $(By.id("metric-3"));
    private ElementsCollection languageTogglesOnKeyInsightDataPanel = $$(By.xpath(".//div[@class = 'languageToggleContainer']/a"));
    private ElementsCollection labelsBelowBars = $$(By.xpath(".//*[name() = 'svg']//*[@class = 'bottom-txt-label']"));
    private WebElement languageToggleSP = $(By.xpath(".//div[@class = 'languageToggleContainer']/a[contains(@class, 'right')]"));
    private WebElement languageToggleENG = $(By.xpath(".//div[@class = 'languageToggleContainer']/a[contains(@class, 'left')]"));
    private By visitNowButtonBy = By.id("button");
    By coursesButtonBy = By.id("view-courses-btn");



    public HomePage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean isTeachermaterialsButtonsExist() {
        if (teachermaterialsButtons.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkWorkOfStartLessonButton() {
        if (isLessonPresent()) {
            String lessonName = lessonTitle.getText();
            openLesson();
            logger.info("Opened lesson " + lessonName);
            refresh();
            Lesson lesson = new Lesson(getWebDriver());
            String opendLessonName = "";
            if (lesson.isWritingTitleExists()) {
                opendLessonName = lesson.getWritingTitleText();
            } else {
                opendLessonName = lesson.getLessonName().split("\n")[0];
            }

            lesson.goToHomePage();
            return opendLessonName.contains(lessonName);
        } else {
            return false;
        }
    }

    public boolean isLessonPresent() {
        return isLessonImgPresent() && isLessonSummaryPresent() && isLessonTitlePresent();
    }

    public boolean isLessonImgPresent() {
        return $(lessonImg).isDisplayed();
    }

    public boolean isLessonSummaryPresent() {
        return $(lessonSummary).isDisplayed();
    }

    public boolean isLessonTitlePresent() {
        return $(lessonTitle).isDisplayed();
    }

    public void openLesson() {
        logger.info("Opening Lesson");
        waitThreadSleep(1000);
        clickActions(startLessonButtonCSS);
    }

    public String getLessonName() {
        if ($(lessonTitleBy).exists())
            return lessonTitle.getText();
        else
            return lessonTitleOfWritingLesson.getText();
    }

    public boolean checkElementsOfGradeDdlTeacher() {
        if (!$(gradeArrow).shouldBe(Condition.visible).isEnabled())
            return true;
        gradeArrow.click();
        String firstClassName = allGrades.get(0).getText();
        activeGrade.click();
        if (firstClassName.equals("All Grades") || firstClassName.equals("") || firstClassName.equals("All Grados")) {
            return false;
        } else {
            return true;
        }
    }

    public void changePassword(String oldPassword, String newPassword) {
        logger.info("Change password from " + oldPassword + " to " + newPassword);
        clickOnChangePasswordLink();
        OldChangePasswordPage oldChangePasswordPage = new OldChangePasswordPage(driver);
        oldChangePasswordPage.changePassword(oldPassword, newPassword);

        String confirmError = "Your confirm password does not match your new password.";
        String passwordsNotMatch = "The passwords do not match. Please try again.";

        waitAndAcceptAlert();
        if (isTextOnPagePresent(confirmError) || isTextOnPagePresent(passwordsNotMatch)
                || isTextOnPagePresent("Favor de intentar de nuevo.")) {
            oldChangePasswordPage.closeWrongPasswordPopup();
            oldChangePasswordPage.changePassword(newPassword, oldPassword);
        } else {
            oldChangePasswordPage.clickCloseButton();
        }
    }

    public void clickOnChangePasswordLink() {
        super.goToNewUrl("/change_password");
        switchTo().defaultContent();
    }

    public boolean isArrowNearAvatarPresent() {
        if($(arrowNearAvatar1).isDisplayed() || $(arrowNearAvatar2).isDisplayed())
            return true;
        return false;
    }

    public String getLessonTitleText() {
        return $(lessonTitle).getText();
    }

    public String getArticleTitleText() {
        return $(arcticleTitle).getText();
    }

    public boolean isLessonChangeCorrect() {
        if ($$(lessonThumbnauls).size() > 1) {
            String oldLessonTitle = $(lessonTitle).getText();
            logger.info("Lesson title before change " + oldLessonTitle);
            String oldLessonSummary = $(lessonSummary).getText();
            logger.info("Lesson summary before change " + oldLessonSummary);
            $(lessonThumbnauls.get(1)).click();

            String newLessonTitle = $(lessonTitle).getText();
            logger.info("Lesson title after change " + newLessonTitle);
            String newLessonSummary = $(lessonSummary).getText();
            logger.info("Lesson summary after change " + newLessonSummary);

            boolean changeTitle = !oldLessonTitle.equals(newLessonTitle);
            boolean changeSummary = !oldLessonSummary.equals(newLessonSummary);
            return changeSummary && changeTitle;
        } else {
            return true;
        }
    }

    public boolean checkElementsOfClassDdlTeacher() {
        $(classArrow).click();
        String firstClassName = $(allClasses.get(0)).getText();
        $(activeClass).click();
        if (firstClassName.equals("All Classes") || firstClassName.equals("Todas las clases")) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkWorkClassDDLForTeacher() {
        if (!isClassArrowPresent())
            return true;
        $(classArrow).click();

        String activeClassName = $(activeClass).getText();
        String selectedClassName = "";
        for (WebElement someClass : $$(allClasses)) {
            selectedClassName = someClass.getText();
            if (!selectedClassName.equals(activeClassName)) {
                someClass.click();
                break;
            }
        }
        activeClassName = $(activeClass).getText();

        if (selectedClassName.length() > 26) {
            selectedClassName = selectedClassName.substring(0, 27);
        }
        return activeClassName.equals(selectedClassName);
    }

    public boolean isClassArrowPresent() {
        return $(classArrow).shouldBe(Condition.visible).isEnabled();
    }

    public boolean checkLanguageDDl() {
        changeStandardToLanguage();
        String activeLexile = languageButton.getText();
        languageButton.click();
        String selectLexile = "";
        int size = elementsOfLanguageDDl.size();
        for (WebElement element : elementsOfLanguageDDl) {
            selectLexile = element.getText().trim();
            if (!selectLexile.equals(activeLexile)) {
                element.click();
                break;
            }
        }
        activeLexile = languageButton.getText().trim();
        languageButton.click();
        elementsOfLanguageDDl.get(0).click();
        return activeLexile.equals(selectLexile) && (size == 4 || (size == 3 && getLogoType().equals("spark")));
    }

    public boolean isSearchButtonExist() {
        return isElementPresentBy(searchButtonBy);
    }

    public CurriculumScheduler goToCurriculumScheduler() {
        goToNewUrl("/n/scheduler/");
        return new CurriculumScheduler(driver);
    }

    public void selectLexileByValue(String value) {
        closeWalkmeNew();
        $(lexileButton).click();
        $(By.xpath("//*[@action='/change_lexile']//*[@data-value='" + value + "']")).click();
        logger.debug("Selected " + value + " in lexile DDL");
        closeWalkmeNew(2);
    }

    public String getLexileText() {
        return $(lexileButton).getText();
    }

    public void openLexileDDL() {
        $(lexileButton).click();
    }

    public ElementsCollection getListOfElementsOfLexileDDL() {
        return elementsOfLexileDDl;
    }

    public void search(String forSearch) {
        if (!isDisplayedBy(findButtonBy)) {
            click(searchButtonBy);
        }
       // findEl(searchBoxBy).sendKeys(forSearch);
        $(findEl(searchBoxBy)).setValue(forSearch);
        clickActions(findButtonBy);
        sleep(1000);
    }

    public String getTextOfMetricCompletingTheMostActivities() {
        waitForJSandJQueryToLoad();
        return $(metricCompletingTheMostActivities).getText();
    }

    public void clickOnMetricCompletingTheMostActivities() {
        closeWalkmeNew();
        waitForJSandJQueryToLoad();
        closeWalkmeNew(3);
        $(metricCompletingTheMostActivities).click();
    }

    public int getNumberOfStudentsCompletingMostActivities() {
        return $$(listOfStudentsComplitingTheMostActivities).size();
    }

    public String getTextFromMetricCircle() {
        return $(metricCircle).getText();
    }

    public String getFirstNumberOnScaleOfMetricCompletingMostActivities() {
        return getTextFromWebElementsByList(numbersOnScaleOfMetricComplitingTheMostActivities).get(0);
    }

    public String getTextOfDateRangeLabel() {
        waitForJSandJQueryToLoad();
        waitUntilAttributeToBeNotEmpty(dateRangeLabelBy, "textContent");
        return getTextBy(dateRangeLabelBy);
    }

    public String getColorOfFirstNumberOnScaleOfMetricCompletingMostActivities() {
        return $$(numbersOnScaleOfMetricComplitingTheMostActivities).get(0).getCssValue("fill");
    }

    public String getColorOfThresholdValue() {
        String result;
        for (WebElement el : numbersOnScaleOfMetricComplitingTheMostActivities) {
            if ("40".equals($(el).getText()) | "80".equals($(el).getText())) {
                return el.getCssValue("fill");
            }
        }
        return result = "";
    }

    public String getTextOfMoreButton() {
        return $(moreButton).getText();
    }

    public void clickOnMoreButton() {
        waitForJSandJQueryToLoad();
        $(getMoreButton()).click();
    }

    public WebElement getMoreButton() {
        return moreButton;
    }

    public void clickOnMetricHighestLexileGains() {
        closeWalkmeNew(5);
        $(metricHighestLexileGains).click();
    }

    public ElementsCollection getLanguageTogglesOnKeyInsightDataPanel() {
        return languageTogglesOnKeyInsightDataPanel;
    }

    public ElementsCollection getLabelsBelowBars() {
        return labelsBelowBars;
    }

    public WebElement getLanguageToggleSP() {
        return languageToggleSP;
    }

    public WebElement getLanguageToggleENG() {
        return languageToggleENG;
    }

    public WebElement getMetricHighestLexileGains() {
        return $(metricHighestLexileGains);
    }

    public void ckickOnVisitNowButton() {
        closeWalkmeNew();
        $(visitNowButtonBy).click();
    }

    public void clickOnCoursesButton() {
        // TODO: TEMPORARY WALK ME CLOSE METHOD
        // closeWalkmeTmp();
        closeWalkmeNew();
        waitUntilElementClickableBy(coursesButtonBy);
        $(coursesButtonBy).click();
    }

}
