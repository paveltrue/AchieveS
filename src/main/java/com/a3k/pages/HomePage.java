package com.a3k.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class HomePage extends Page {

    ElementsCollection teachermaterialsButtons = $$(By.xpath("//*[@style='display: block;' and @class='instructionalMaterial']//a"));
    WebElement lessonImg = $(By.xpath("//*[@class='photoContainer' and @style='']//img[1]"));
    WebElement lessonSummary = $(By.xpath("//*[@style='']/*[contains(@class, 'summary') and text()]"));
    WebElement lessonTitle = $(By.xpath("(//*[@aria-hidden='false']//*[contains(@class,'lessonTitle')])" +
            "[count(//*[@aria-hidden='false']/*[@class='card panel flip'])+1]/a | //*[@style='']/*[contains(@class, 'title')]"));
    By startLessonButtonCSS = By.cssSelector(".startButton.button,a#home-button");
    private By lessonTitleBy = By.xpath("//*[@class='lessonHeader']/*[@class='title']/div[@class='titleText']");
    private WebElement lessonTitleOfWritingLesson = $(By.className("article_title"));
    WebElement gradeArrow = $(By.xpath("//*[@class='gradeContainer']//*[@class='classArrow'] | //*[@class='gradeName']//img"));
    ElementsCollection allGrades = $$(By.xpath("//*[@id='grade-dropdown']//a"));
    WebElement activeGrade = $(By.className("gradeName"));
    WebElement arcticleTitle = $(By.className("article_title"));
    ElementsCollection lessonThumbnauls = $$(By.xpath("//*[normalize-space(@class)='thumb'] | //*[@class='slick-dots']/*"));
    WebElement classArrow = $(By.xpath("//*[@class='classContainer']//*[@class='classArrow']"));
    ElementsCollection allClasses = $$(By.xpath("//*[@data-color]"));
    WebElement languageButton = $(By.xpath("//*[@data-dropdown='#teacher-languages-dropdown']"));
    ElementsCollection elementsOfLanguageDDl = $$(By.xpath("//*[@action='/change_language']//a"));
    WebElement arrowNearAvatar1 = $(By.xpath("//*[@class='name' or @class='ribbonTop']//img"));
    WebElement arrowNearAvatar2 = $(By.xpath("//*[@class='selectName']"));

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

}
