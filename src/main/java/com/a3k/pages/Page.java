package com.a3k.pages;


import com.a3k.utils.UserPasswordsChanger;
import com.a3k.utils.logger.BasicLogger;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashMap;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;


public abstract class Page {

    protected WebDriver driver;
    protected By nextButtonLevelSetBy = By.xpath(".//*[@class = 'button_ls']/a[@id = 'btn_next']");
    protected By homeButtonBy = By.xpath("//*[@href='/home' or contains(@class,'logo-') or @href='/n/']");
    protected By teachersNameBy = By.xpath(".//*[@data-dropdown='#teacher-settings-dropdown']");
    protected By logoBy = By.xpath(".//*[contains(@class , 'logo') and not(./div[@class = 'copyright']) and not(@src)]");
    WebElement activeClass = $(By.xpath("//*[@class='className' or @class='activeClassName']"));
    protected WebElement logo = $(By.xpath("//*[contains(@class , 'logo ')]"));
    private By standardDDL = By.xpath(".//a[@data-dropdown='#teacher-editions-dropdown']");                  //JS
    private By languageInStandardDDL = By.xpath(".//*[@id='teacher-editions-dropdown']//a[@data-value='1']");//JS
    protected By popupBy = By.xpath("//div[not(contains(@class,'supportsDialog ')) and not(contains(@class,'videoDialog')) and @role='dialog' and contains(@style,'block')]");
    protected WebElement walkmePopup = $(By.xpath(".//*[contains(@class, 'wm-close-button') or contains(@class, 'walkme-x-button') or contains(@class, 'walkme-action-close')]"));
    private By popupContentBy = By.xpath("//div[(@role='dialog') and (contains(@style,'display: block;'))]/div[contains(@class,'alert_dialog')]");
    protected By adminButtonBy = By.xpath(".//a[@href='/kb/loader_admin/' or @href='/admin/settings' or @id='adminMenuItem' or contains(@href,'admin_section=1')]");
    protected By searchButtonBy = By.xpath("//*[@href='/kb/search/' or @href='/n/search/' or @data-dropdown='#search-div' or @class='searchIcon']");
    private By actualYearOnCalendarBy = By.xpath(".//*[@id='ui-datepicker-div']//span[contains(@class, 'year')]");
    private By actualMonthOnCalendarBy = By.xpath(".//*[@id='ui-datepicker-div']//span[contains(@class, 'month')] | .//*[@id ='startCal']//*[@class = 'ui-datepicker-month']");
    private By closeButtonOnPopupImportantInformationBy = By.xpath(".//a[@role='button']");
    private ElementsCollection listOfDaysOfCalendar = $$(By.xpath(".//*[@id='ui-datepicker-div']//tbody//a"));
    private By nextButtonOnCalendarBy = By.xpath(".//*[@id='ui-datepicker-div']//a[@title = 'Next' or contains(@title, 'Sig')]");
    private By previousButtonOnCalendarBy = By.xpath(".//*[@id='ui-datepicker-div']//a[@title = 'Prev' or contains(@title, 'Ant')]");
    private By teacherLangSelectorBy = By.xpath(".//*[@data-dropdown='#teacher-languages-dropdown']");
    private WebElement teacherLangSelector = $(By.xpath(".//*[@data-dropdown='#teacher-languages-dropdown']"));
    protected By selectedAnswerBy = By.xpath("//*[contains(@class,'selected')]");
    protected WebElement nextQuestionButton = $(By.xpath("//*[@id=\"step20activity\"]/div/lesson-activity/form/div/div[2]/div/button"));
    protected WebElement selectAnswerChoiceButton = $(By.xpath("/html/body/div[28]/div[3]/div/button"));
    protected WebElement selectAnswerChoiceButtonCopy = $(By.xpath("/html/body/div[29]/div[1]/button/span[1]"));
    protected WebElement OKButton = $(byText("OK"));
    protected WebElement ARPonActivityPage = $(By.xpath("//*[@id=\"step14page2\"]/div[2]/div/a"));
    protected By viewResultsButtonLiteracyBy = By.xpath("//button[@ng-click='showActivityResult()']");
    protected WebElement submitButtonNew1 = $(By.xpath("//*[@id=\"step20activity\"]/div/lesson-activity/form/div/div[2]/div/button"));
    protected WebElement submitButtonNew2 = $(By.xpath("//*[@id=\"step14activity\"]/div/lesson-activity/form/div/div[2]/div/button"));
    protected WebElement submitButtonNew3 = $(By.xpath("/html/body/div[28]/div[3]/div/button"));
    protected WebElement nextQBByText = $(byText("Next Question"));
    private By topNavToolbarBy = By.xpath("//nav[contains(@class, 'navbar-anchor')]");
    private By nextQuestionButtonLiteracyBy = By.xpath("//*[@ng-click='nextQuestionHandler()']");
    private By loginButtonBy = By.xpath(".//*[@id = 'button' and @type = 'submit']");



    protected static Logger logger = BasicLogger.getInstance();

    protected By walkmePopupBy = By.xpath(".//*[contains(@class, 'wm-close-button')" +
                    " or contains(@class, 'walkme-x-button')" +
                    " or " + "contains(@class, 'walkme-action-close')" +
                    " or contains(@id,'walkme-popup-background')]");

    public void closeWalkme() {
        logger.info("Try to execute Close Walkme");
        while (isDisplayedByNEW(walkmePopupBy)) {
           // closeWalkMeIfDisplayedBy(walkmePopupBy);
            closeWalkmeNew();
        }
    }

    public void closeWalkMeIfDisplayedBy(By by) {
        try {
            while (isDisplayedByNEW(by)) {
                ElementsCollection list = findEls(by);
                for (int i = list.size() - 1; i >= 0; i--) {
                    $(list.get(i)).click();
                    waitThreadSleep(250);
                }
            }
        } catch (Exception e) {
            $(findEls(by).get(0)).click();
        }
    }

    public boolean isDisplayedByNEW(By by) {

        ElementsCollection list = findEls(by);
        if (list.size() == 0) {
            return false;
        } else {
            return list.get(0).isDisplayed();
        }
    }

    public ElementsCollection findEls(By by) {
        ElementsCollection res = $$(by);
        return res;
    }

    public ElementsCollection findEls(ElementsCollection element) {
        ElementsCollection res = $$(element);
        return res;
    }

    public void waitThreadSleep(int millis) {
        logger.info("Thread sleep for " + millis + " milliseconds.");
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String replacePasswordIfNeed(String login, String password) {
        String replacedPassword = new UserPasswordsChanger(login).checkAndChangePassword();
        if (!replacedPassword.isEmpty()) {
            password = replacedPassword;
        }
        return password;
    }

    public void waitAndCLickIfExist(By by) {
        try {
            if ($(by).exists()) {
                $(by).click();
            }
        } catch (Exception e) {
            logger.trace("An error occurred: " + e.getLocalizedMessage());
        }
    }

    public void waitAndCLickIfExist(WebElement element) {
        try {
            if ($(element).isDisplayed()) {
                $(element).click();
            }
        } catch (Exception e) {
            logger.trace("An error occurred: " + e.getLocalizedMessage());
        }
    }

    public boolean isElementDisappears(By by) {
        boolean isDisappear = false;
        try {
            if (!$(by).isDisplayed())
            isDisappear = true;
        } catch (Exception e) {
            logger.info("Element not disappears " + by);
        }
        return isDisappear;
    }

    public boolean isTextOnPagePresent(String text) {
        WebElement bodyElement = $(By.tagName("body"));
        return bodyElement.getText().contains(text);
    }

    public boolean waitForJSandJQueryToLoad() {
            //TODO executor?
            executeJavaScript("return jQuery.active");
        logger.info("Wait For JS and JQuery load.");
        try {
            executeJavaScript("return jQuery.active");
            //waitUntilJQueryToLoad();
            //waitUntilJSandToLoad();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void clickActions(By by) {
        logger.info("Click on element " + by);
        if(!$(by).isDisplayed()) {
        //if($(by).isDisplayed()) {
            closeWalkme();
        }
        try {
            clickActionsChange(by);
        } catch (ElementNotInteractableException e) {
            closeWalkme();
            clickActionsChange(by);
        } catch (TimeoutException e) {
            logger.trace("Element " + by + " not found");
        }
    }

    public void clickActions(WebElement element) {
        logger.info("Click on element " + element);
        if(!$(element).isDisplayed()) {
            closeWalkme();
        }
        try {
            clickActionsChange(element);
        } catch (ElementNotInteractableException e) {
            closeWalkme();
            clickActionsChange(element);
        } catch (TimeoutException e) {
            logger.trace("Element " + element + " not found");
        }
    }

    public void clickActionsChange(By by) {
        loggerMessageWithXpathBy(by, "Trying to click on locator via (former)Actions(changed method)");
        $(by).click();
    }

    public void clickActionsChange(WebElement element) {
        loggerMessageWithXpathBy(element, "Trying to click on locator via (former)Actions(changed method)");
        $(element).click();
    }

    public void loggerMessageWithXpathBy(By by, String message) {
        message = message + " " + by;
        message = message.replaceFirst("By.xpath:", "");
        logger.debug(message);
    }

    public void loggerMessageWithXpathBy(WebElement element, String message) {
        message = message + " " + element;
        message = message.replaceFirst("By.xpath:", "");
        logger.debug(message);
    }

    public void loggerMessageWithXpathWebEl(WebElement element, String message) {
        message = message + " " + element;
        message = message.replaceFirst("By.xpath:", "");
        logger.debug(message);
    }

    public void goToHomePage() {
        logger.info("Opening Home Page");
        try {
            clickJS(homeButtonBy);
        } catch (StaleElementReferenceException e) {
            refresh();
            clickJS(homeButtonBy);
        }
        acceptAlert();
        closeWalkme();
    }

    public void clickJS(By by) {
        logger.info("Click on element " + by);
        //waitUntilLoaded();
        if(!isBecomeClickableBy(by)) {
            closeWalkme();
        }
        waitElementIsClickable(by);
        try {
            clickJSChange(by);
        } catch (ElementNotInteractableException e) {
            closeWalkme();
            waitElementIsClickable(by);
            clickJSChange(by);
        }
        //waitUntilLoaded();
    }

    public void clickJSWebEl(WebElement element) {
        logger.info("Click on element " + element);
//        waitUntilLoaded();
        if(!$(element).shouldBe(visible).isEnabled()) {
            closeWalkme();
        }
        try {
            clickJSChangeWebEl(element);
        } catch (ElementNotInteractableException e) {
            closeWalkme();
            clickJSChangeWebEl(element);
        }
       // waitUntilLoaded();
    }

    public void waitUntilLoaded() {
        try {
            if ($(teachersNameBy).shouldBe(visible).isEnabled())
                return;
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickJSChange(By by) {
        loggerMessageWithXpathBy(by, "Trying to click on locator via JS");
        Configuration.pageLoadStrategy = "normal";
        clickIfDisplayedSubmitButton();
        try {
            WebElement element = $(by);
            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            clickIfDisplayedSubmitButton();
            executeJavaScript("arguments[0].click();", element);
            loggerMessageWithXpathBy(by, "Element was clicked via JS");
        } catch (NoSuchElementException | TimeoutException e) {
            logger.trace(e.getMessage());
        }
    }
    public void clickJSChangeWebEl(WebElement element) {
        loggerMessageWithXpathWebEl(element, "Trying to click on locator via JS");
        try {
            WebElement element1 = $(element);
            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
            executeJavaScript("arguments[0].click();", element1);
            loggerMessageWithXpathWebEl(element, "Element was clicked via JS");
        } catch (NoSuchElementException | TimeoutException e) {
            logger.trace(e.getMessage());
        }
    }

    public void acceptAlert() {
        try {
            Wait().until(alertIsPresent());
            confirm();
            switchTo().defaultContent();
        } catch (TimeoutException e) {
           logger.trace("Alert not found");
        }
    }

    public void clickOnLogo() {
        logger.info("Click on logo to go to Home Page");
        $(logoBy).click();
    }

    public void goToNewUrl(String addEnding) {
        String url = url();
        url = url.split("\\.com")[0] + ".com" + addEnding;
        logger.info("Opening new page: " + url);
        try {
            open(url);
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.trace("Failed to open new url due to timeout exception");
        }
        waitForPageToLoad();
    }
    public void goToNewUrlWitoutLoadPage(String addEnding) {
        String url = url();
        url = url.split("\\.com")[0] + ".com" + addEnding;
        logger.info("Opening new page: " + url);
        try {
            open(url);
        } catch (org.openqa.selenium.TimeoutException e) {
            logger.trace("Failed to open new url due to timeout exception");
        }
    }

    public void waitForPageToLoad() {
        executeJavaScript("return document.readyState");
    }

    public void waitAndAcceptAlert() {
        //confirm("The passwords do not match. Please try again.");
        //$(By.xpath("/html/body/div[3]/div[11]/div/button/span")).click();

        //confirm();
        //dismiss();

    }

    public void goToInitialUrl() {
        String url = url().split("\\.com")[0] + ".com";
        open(url);
    }

    public String getLogoType() {
        String logoType = logo.getCssValue("background-image");
        logoType = logoType.split("monarch/")[1].split("/")[0];
        return logoType;
    }

    public void changeStandardToLanguage() {
        clickJS(standardDDL);
        clickJS(languageInStandardDDL);
    }

    public void closePopup() {
        if ($(popupBy).isDisplayed())
        $(popupBy).$(By.xpath("div//button[1]")).click();
    }

    public boolean isPopupVisible() {
        return $(popupBy).isDisplayed();
    }

    public void closeWalkmePopup() {
        if (isDisplayedByNEW(walkmePopupBy)) {
            walkmePopup.click();
        }
    }

    public boolean isElementPresent(WebElement element) {
        if ($(element).isDisplayed())
            return true;
        return false;
    }

    public MyLessons goToMyLessonsByLink() {
        logger.info("Opening My Lesson page");
        goToNewUrl("/my_lessons");
        if (getWebDriver().getWindowHandles().size() > 1) {
            switchToNextWindowWhenExistOnly2();
            //closeWindow();
            getWebDriver().close();
            switchBackAfterClose();
            closeWebDriver();
        }
        return new MyLessons(driver);
    }
    public MyLessons goToMyLessonsByLinkWithoutLoadPage() {
        logger.info("Opening My Lesson page");
        goToNewUrlWitoutLoadPage("/my_lessons");
        if (getWebDriver().getWindowHandles().size() > 1) {
            switchToNextWindowWhenExistOnly2();
            //closeWindow();
            getWebDriver().close();
            switchBackAfterClose();
            closeWebDriver();
        }
        return new MyLessons(getWebDriver());
    }

    public MyLessons goToMyLessonsByLinkNew() {
        logger.info("Opening My Lesson page");
        goToNewUrl("/my_lessons");
        closeWalkmeNew();
        if (getWebDriver().getWindowHandles().size() > 1) {
            switchToNextWindowWhenExistOnly2();
            //closeWindow();
            getWebDriver().close();
            switchBackAfterClose();
            //closeWebDriver();
        }
        return new MyLessons(getWebDriver());
    }

    public void switchToNextWindowWhenExistOnly2() {
        logger.info("Switch to Next window");
        logger.debug("Switching from window " + title());

        waitUntilSecondWindowAppears();
        ArrayList<String> tabs = new ArrayList<>(getWebDriver().getWindowHandles());
        switchTo().window(tabs.get(tabs.size() - 1));

        logger.debug("Switched to window " + title());

        waitUntilPageLoaded();
    }

    public void waitUntilSecondWindowAppears() {
            if (getWebDriver().getWindowHandles().size() < 1) {
               waitUntilSecondWindowAppears();
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            }
    }

    public void waitUntilPageLoaded() {
        try {
            Configuration.pageLoadStrategy = "normal";
        } catch (TimeoutException e) {
            logger.debug("Timeout when waiting for page to load");
        }
    }

    public boolean waitUntilCollectionAppears(ElementsCollection list) {
        int i = 0;
        while (($$(list).size() == 0)) {
            i++;
        }
        if (list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isElementPresentBy(By by) {
        return $(by).isDisplayed();
    }

    public void waitUntilAppearsBy(By by) {
        try {
            $(by).shouldBe(visible).isDisplayed();
        } catch (Exception e) {

        }
    }

    public void waitElement(By element) {
        try {
            $(element).isDisplayed();
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
        }
    }

    public void waitElementWebEl(WebElement element) {
        try {
            $(element).isDisplayed();
        } catch (TimeoutException e) {
            logger.error(e.getMessage());
        }
    }

    public boolean isDisplayedBy(By by) {
        try {
            return $(by).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void dragAndDrop(WebElement sourceElement, WebElement targetElement) {
//        Actions dragAndDrop = new Actions(driver);
////        Action action = dragAndDrop.dragAndDrop(sourceElement, targetElement).build();
////        action.perform();
        actions().dragAndDrop(sourceElement, targetElement).build().perform();
    }

    public void changeSequence(WebElement sourceElement, WebElement targetElement) {
        int ySource = sourceElement.getLocation().getY();
        int yTarget = targetElement.getLocation().getY();
        int yOffset = ySource < yTarget ? 40 : 30;

//        Actions a = new Actions(driver);
//        a.clickAndHold(sourceElement).build().perform();
//        a.moveToElement(targetElement, targetElement.getLocation().getX() / 2, yOffset).build().perform();
//        a.release().perform();
        actions().clickAndHold(sourceElement).build().perform();
        actions().moveToElement(targetElement, targetElement.getLocation().getX() / 2,  yOffset).build().perform();
        actions().release().perform();

    }

    public boolean isDisplayed(WebElement element) {
        return isDisplayedBy(getWebElementLocator(element));
    }

    public By getWebElementLocator(WebElement element) {
        return By.xpath(getWebElementXpath(element));
    }

    private String getWebElementXpath(WebElement element) {
        String returnable = $(element).toString();

        returnable = returnable.replaceFirst("\\[(.*?)\\]", "");

        if (returnable.contains(" -> class name: ")) {
            returnable = returnable.replace(" -> class name: ", "-> xpath = .//*[@class = '");
            returnable = returnable.replace("]", "'] ");
        } else if ((returnable.contains(" -> id: "))) {
            returnable = returnable.replace(" -> id: ", "-> xpath = .//*[@id = '");
            returnable = returnable.replace("]", "'] ");
        }

        returnable = returnable.substring(11, returnable.length() - 1);

        if (returnable.contains("]] -> xpath: ")) {
            returnable = returnable.replace("]] -> xpath: ", "/");
        }
        return returnable.trim();
    }

    public String getPopupContent() {
        waitUntilAttributeToBeNotEmptyBy(popupContentBy, "textContent");
        return $(popupContentBy).getText();
    }

    public void waitUntilAttributeToBeNotEmptyBy(By by, String attribute) {
        try {
            $(by).shouldHave(attribute(attribute));
        } catch (Exception e) {
        }
    }

    public WebElement findEl(By by) {
        loggerMessageWithXpathBy(by, "Try to FIND element ");

        return $(by);
    }

    public boolean isElementAbsentBy(By by) {
        try {
            return !($(findEl(by)).isDisplayed());
        } catch (Exception e) {
            return true;
        }
    }

    public String getAttribute(WebElement webElement, String att) {
        waitUntilAttributeToBeNotEmpty(webElement, att);
        return webElement.getAttribute(att);
    }

    public void waitUntilAttributeToBeNotEmpty(WebElement element, String attribute) {
        try {
            $(element).shouldHave(attribute(attribute));
        } catch (Exception e) {
        }
    }

    public void switchBackAfterClose() {
        logger.info("Switch Back after close");
        ArrayList<String> tabs = new ArrayList<>(getWebDriver().getWindowHandles());
        switchTo().window(tabs.get(tabs.size() - 1));
    }

    public boolean isElementsExist(By by) {
        boolean isExists = false;
        if ($$(by).size() > 0) {
            isExists = true;
        }
        return isExists;
    }

    public boolean isElementsExist(ElementsCollection collection) {
        boolean isExists = false;
        if ($$(collection).size() > 0) {
            isExists = true;
        }
        return isExists;
    }

    private String pageName;

    public Page(String pageName, By element, WebDriver driver) {
        this.driver = driver;
        this.pageName = pageName;
        try {
            $(element).shouldBe(visible);
        } catch (Exception e) {
            logger.info("Page " + pageName + " isn't open");
        }

        logger.info("Page " + pageName + " open");
    }

    public Page() {
    }

    public void switchBack() {
        logger.debug("Closing window handle " + getWebDriver().getWindowHandle());
        getWebDriver().close();
        ArrayList<String> tabs = new ArrayList<String>(getWebDriver().getWindowHandles());
        switchTo().window(tabs.get(tabs.size() - 1));
        logger.debug("Currently on window handle " + getWebDriver().getWindowHandle());
    }

    public void removeUIElementFromPageWithJS(WebElement element) {
        executeJavaScript("arguments[0].parentNode.removeChild(arguments[0])", element);
    }

    public String getTextBy(By by) {
        return refEl(by).getText();
    }

    public WebElement refEl(By locator) {
        boolean condition = true;
        WebElement element = null;
        int count = 0;
        while (condition) {
            count++;
            try {
                element = $(locator);
                condition = false;
                break;
            } catch (StaleElementReferenceException | NoSuchElementException e) {
                    logger.trace("The element by " + locator + " is absent. Count of refresh: " + count);
                    $(locator);
                    condition = false;
                waitThreadSleep(200);
            }
        }
        return element;
    }

    public void waitAndCancelAlert() {
        try {
//            Alert alert = switchTo().alert();
//            alert.dismiss();
            confirm();
            switchTo().defaultContent();
        } catch (Exception e) {
            logger.trace(e.getMessage());
        }
    }

    public String getAttributeBy(By by, String att) {
        waitUntilAttributeToBeNotEmptyBy(by, att);
        return refEl(by).getAttribute(att);
    }

    public void goToReportsPage() {
        logger.info("Opening All Reports Page");
        goToNewUrl("/options/teacher/");
    }

    public void waitAndSwitchToFrame() {
        long currTime = System.currentTimeMillis();
        while (getWebDriver().getWindowHandles().size() <= 1 ) {
        }
        switchToNextWindow();
    }

    public void switchToNextWindow() {
        logger.info("Switch to Next window");
        logger.debug("Switching from handle " + getWebDriver().getWindowHandle());
        logger.debug("Handles before " + getWebDriver().getWindowHandles());

        ArrayList<String> tabs = new ArrayList<>(getWebDriver().getWindowHandles());
        switchTo().window(tabs.get(tabs.size() - 1));
        logger.debug("Handles after" + getWebDriver().getWindowHandles());
        logger.debug("Switched to handle " + getWebDriver().getWindowHandle());

        waitUntilPageLoaded();
    }

    public void goToAdminPage() {
        clickIfDisplayedSubmitButton();
        logger.info("Opening Admin Page");
        if (!isDisplayedBy(adminButtonBy)) {
            clickJS(teachersNameBy);
        }
        clickJS(adminButtonBy);
        waitUntilPageLoaded();
    }

    public boolean waitElementIsClickable(By by) {
        boolean isClickable = false;
        logger.info("Wait until element " + by + " clickable");
        try {
            if($(by).isDisplayed()) {
                isClickable = true;
            }

        } catch (Exception e) {
            logger.info("Element " + by + " not clickable ");
        }
        return isClickable;
    }

    public boolean isBecomeClickableBy(By by) {

            if ($(by).isDisplayed()) {
                return true;
            }
            return false;

    }
    public void closeWalkmeNew(){
        if($(walkmePopupBy).isDisplayed())
            $(walkmePopupBy).click();
    }

    public MailboxPage goToMailboxPage() {
        logger.info("Opening Mailbox Page");
        goToNewUrl("/mail");
        return new MailboxPage(driver);
    }

    public void scrollUp() {
        executeJavaScript("window.scrollTo(0, 0);");
    }

    public void loggerMessageWithXpath(WebElement element, String message) {
        message = message + " " + element;
        message = message.replaceFirst("\\[(.*?)\\]", "");
        if (!message.isEmpty() && message.contains("]")) {
            message = message.substring(0, message.lastIndexOf("]"));
        }
        logger.debug(message);
    }

    public void waitElementBy(By by) {
        loggerMessageWithXpathBy(by, "Wait visibility of element ");
        try {
            $(by).shouldBe(visible);
        } catch (Exception e) {
            loggerMessageWithXpathBy(by, "The element hasn't found ");
        }
    }

    public void waitUntilElementClickableBy(By by) {
        loggerMessageWithXpathBy(by, "Wait until element will be clickable.");
        $(by).shouldBe(Condition.visible);
    }

    public void acceptTwoAlerts() {
        if (isAlertPresent()) {
            //alert.accept();
            confirm();
            if (isAlertPresent()) {
//                Alert nextAlert = switchTo().alert();
//                nextAlert.accept();
                confirm();
            }
            switchTo().defaultContent();
        }
    }

    public boolean isAlertPresent() {
        try {
            switchTo().alert();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void goToGradingPage() {
        goToNewUrl("/teacher_grading");
    }

    public void waitUntilElementsAppearsLocatedBy(By by) {
            $(by).shouldBe(visible);
    }

    public void waitUntilAppearsWebEl(WebElement element) {
       $(element).shouldBe(visible);
    }

    public void refreshPage() {
        logger.info("Refreshing page");
        refresh();
        waitForPageToLoad();
        waitForJSandJQueryToLoad();
    }

    public void clickScroll(WebElement element) {
        loggerMessageWithXpath(element, "Trying to scroll to element and click on element");
        executeJavaScript("arguments[0].scrollIntoView(false);", element);
        $(element).click();
    }

    public void selectFromDDL(WebElement selectDDL, String value) {
        Select select = new Select(selectDDL);
        select.selectByVisibleText(value);
    }

    public String getTextOfActiveItemFromSelectBy(By by) {
        String result = "";
//        new WebDriverWait(driver, timeOutGlobal)
//                .ignoring(InterruptedException.class)
//                .withMessage("Element with locator [ " + by  + " ] can't be clicked ")
//                .until(driver -> isElementExist(by));
        Select select = new Select($(by));
        result = select.getFirstSelectedOption().getText();
        logger.info("Getting text from locator " + by + " text = [ " + result + " ]");
        return result;
    }

    public WebElement getSelectedOptionFromSelectAsWebElement(WebElement select) {
        Select sel = new Select(select);
        return sel.getFirstSelectedOption();
    }

    public ElementsCollection getOptionsFromSelect(WebElement select) {

        //Select sel = new Select(select);
        //return sel.getOptions();
        return $(select).getSelectedOptions();
    }

    public void selectFromDDLbyValue(WebElement selectDDL, String value) {
        Select select = new Select(selectDDL);
        select.selectByValue(value);
    }

    public void selectFromDDLByIndex(WebElement selectDDL, int index) {
        Select select = new Select(selectDDL);
        select.selectByIndex(index);
    }

    public String getDefaultValueFromDDL(WebElement el) {
        Select sel = new Select(el);
        return sel.getFirstSelectedOption().getText();
    }

    public String getDefaultValueFromDDLBy(By by) {
        Select sel = new Select(findEl(by));
        return sel.getFirstSelectedOption().getText();
    }

    public void scrollDown() {
        //JavascriptExecutor js = ((JavascriptExecutor) driver);
        //js.executeScript("window.scrollTo(0, 750)");
        executeJavaScript("window.scrollTo(0, 750)");
    }

    public void waitUntilJQueryToLoad() {
        logger.info("Wait For JQuery load.");

        executeJavaScript("return jQuery.active");
    }

    public void waitUntilJSandToLoad() {
        logger.info("Wait For JS load.");

        executeJavaScript("return document.readyState");
    }

    public void enterTextInInput(WebElement el, String str) {
        $(el).setValue(str);
    }

    public void enterTextInInput(By by, String str) {
        $(by).setValue(str);
    }

    public boolean isWebElementContainsVulueOfAttributte(WebElement element, String attributte, String value) {
        if (getAttribute(element, attributte).contains(value)) {
            return true;
        }
        return false;
    }

    public void setDateOnCalendar(String year, String month, String day) {

        String actualYearInCalendar = getActualYearFromCalendar();
        String actualMonthInCalendar = getActualMonthFromCalendar();

        HashMap<String, Integer> calendar = new HashMap<>();
        HashMap<String, String> calendarTranslator = new HashMap<>();

        if (isAdminLanguageSelectorPresent()) {
            if (!getLanguage().equals("Español")) {
                calendar.put("January", 1);
                calendar.put("February", 2);
                calendar.put("March", 3);
                calendar.put("April", 4);
                calendar.put("May", 5);
                calendar.put("June", 6);
                calendar.put("July", 7);
                calendar.put("August", 8);
                calendar.put("September", 9);
                calendar.put("October", 10);
                calendar.put("November", 11);
                calendar.put("December", 12);
            } else {
                calendar.put("enero", 1);
                calendar.put("febrero", 2);
                calendar.put("marzo", 3);
                calendar.put("abril", 4);
                calendar.put("mayo", 5);
                calendar.put("junio", 6);
                calendar.put("julio", 7);
                calendar.put("agosto", 8);
                calendar.put("septiembre", 9);
                calendar.put("octubre", 10);
                calendar.put("noviembre", 11);
                calendar.put("diciembre", 12);

                calendarTranslator.put("January", "enero");
                calendarTranslator.put("February", "febrero");
                calendarTranslator.put("March", "marzo");
                calendarTranslator.put("April", "abril");
                calendarTranslator.put("May", "mayo");
                calendarTranslator.put("June", "junio");
                calendarTranslator.put("July", "julio");
                calendarTranslator.put("August", "agosto");
                calendarTranslator.put("September", "septiembre");
                calendarTranslator.put("October", "octubre");
                calendarTranslator.put("November", "noviembre");
                calendarTranslator.put("December", "diciembre");

                month = calendarTranslator.get(month);
            }
        } else {
            calendar.put("January", 1);
            calendar.put("February", 2);
            calendar.put("March", 3);
            calendar.put("April", 4);
            calendar.put("May", 5);
            calendar.put("June", 6);
            calendar.put("July", 7);
            calendar.put("August", 8);
            calendar.put("September", 9);
            calendar.put("October", 10);
            calendar.put("November", 11);
            calendar.put("December", 12);
        }

        if (Integer.parseInt(actualYearInCalendar) < Integer.parseInt(year)) {
            int i = 0;
            while (!year.equals(actualYearInCalendar) | !month.equals(actualMonthInCalendar)) {
                i = i + 1;
                clickOnNextButtonOnCalendar();
                actualYearInCalendar = getActualYearFromCalendar();
                actualMonthInCalendar = getActualMonthFromCalendar();
                if (i == 10) {
                    break;
                }
            }
        } else if (Integer.parseInt(actualYearInCalendar) > Integer.parseInt(year)) {
            int i = 0;
            while (!year.equals(actualYearInCalendar) | !month.equals(actualMonthInCalendar)) {
                i = i + 1;
                clickOnPreviousButtonOnCalendar();
                actualYearInCalendar = getActualYearFromCalendar();
                actualMonthInCalendar = getActualMonthFromCalendar();
                if (i == 10) {
                    break;
                }
            }
        }

        if (Integer.parseInt(actualYearInCalendar) == Integer.parseInt(year)) {

            int actualMonthNumber = calendar.get(actualMonthInCalendar);
            int neededMonthNumber = calendar.get(month);

            if (actualMonthNumber > neededMonthNumber) {
                int i = 0;
                while (!month.equals(actualMonthInCalendar)) {
                    i = i + 1;
                    clickOnPreviousButtonOnCalendar();
                    actualMonthInCalendar = getActualMonthFromCalendar();
                    if (i == 10) {
                        break;
                    }
                }
            }

            if (actualMonthNumber < neededMonthNumber) {
                int i = 0;
                while (!month.equals(actualMonthInCalendar)) {
                    i = i + 1;
                    clickOnNextButtonOnCalendar();
                    actualMonthInCalendar = getActualMonthFromCalendar();
                    if (i == 10) {
                        break;
                    }
                }
            }

            if (actualMonthNumber == neededMonthNumber || actualMonthInCalendar.equals(month)) {
                clickOnTheDayOfCalendar(day);
            }
        }

        if (!isElementAbsentBy(closeButtonOnPopupImportantInformationBy)) {
            $(findEl(closeButtonOnPopupImportantInformationBy)).click();
        }
//        Actions actions = new Actions(driver);
        actions().click().release().build().perform();
    }

    public String getActualYearFromCalendar() {
        waitUntilAttributeToBeNotEmptyBy(actualYearOnCalendarBy, "textContent");
        String result = getTextBy(actualYearOnCalendarBy);
        if(result.equals("")) {
            result = getTextBy(actualYearOnCalendarBy);
        }
        return result;
    }

    public String getActualMonthFromCalendar() {
        waitUntilAttributeToBeNotEmptyBy(actualMonthOnCalendarBy, "textContent");
        String result = getTextBy(actualMonthOnCalendarBy);
        if(result.equals("")) {
            result = getTextBy(actualMonthOnCalendarBy);
        }
        return result;
    }

    public void clickOnTheDayOfCalendar(String day) {
        for (WebElement el : listOfDaysOfCalendar) {
            if (el.getText().equals(day)) {
                clickJSWebEl(el);
                break;
            }
        }
    }

    public void clickOnNextButtonOnCalendar() {
        clickJS(nextButtonOnCalendarBy);
    }

    public void clickOnPreviousButtonOnCalendar() {
        $(previousButtonOnCalendarBy).click();
    }

    public boolean isAdminLanguageSelectorPresent() {
        return $(teacherLangSelectorBy).exists();
    }

    public String getLanguage() {
        return $(teacherLangSelector).getText().trim();
    }

    public void enterTextWithJS(By by, String str) {
        executeJavaScript("arguments[0].setAttribute('value', '" + str + "')", findEl(by));
    }

    public void clickOnSomeElementsInListStartFromBy(By by, int start, int count) {
        ElementsCollection temp = findEls(by);
        for (int i = start; i <= count; i++) {
            clickJSWebEl($$(temp).get(i));
        }
    }

    public void waitUntilJSandJQLoaded(){
        logger.info("Waiting until JS and JQuery loaded");
        waitUntilJSandToLoad();
        waitUntilJQueryToLoad();
    }

    public void clickAfterClosePopup(WebElement element){
        closeWalkmeNew();
        closeWalkmeNew();
        closeWalkmeNew();
        if ($(byText("OK")).isDisplayed()) {
            $(byText("OK")).click();
        }
        $(element).click();
    }

    public void clickAfterClosePopupForChoose(WebElement element){
        Configuration.pageLoadStrategy = "normal";
        closeWalkmeNew();

        if (isViewResultsButtonVisible()){
            return;
        }

        if ($(nextQBByText).isDisplayed()) {
            return;
        }

        if ($(element).isDisplayed() && !$(nextQBByText).isDisplayed() && !isViewResultsButtonVisible()){
            Configuration.pageLoadStrategy = "normal";
            if ($(OKButton).isDisplayed()){
                $(OKButton).click();
            }
            if (!$(nextQBByText).isDisplayed() && !isViewResultsButtonVisible()) {
                $(element).click();
            }
        }

    }

    public void pressSelectAnswerChoiceButt(){
            if ($(selectAnswerChoiceButton).isDisplayed()) {
                $(selectAnswerChoiceButton).click();
            } else if ($(selectAnswerChoiceButtonCopy).isDisplayed()) {
                $(selectAnswerChoiceButtonCopy).click();
            } else if ($(byText("OK")).isDisplayed()){
                $(byText("OK")).click();
            }
    }

    public boolean isElementExist(WebElement web) {
        return $(web).exists();
    }

    public void pressOKbutton(){
        if ($(OKButton).isDisplayed()){
            $(OKButton).click();
        }
    }

    public void clickOnSubmitPY(){
        Configuration.pageLoadStrategy = "normal";
        if ($(OKButton).isDisplayed()){
            $(OKButton).click();
        }
        if ($(nextQBByText).isDisplayed()){
            $(nextQBByText).click();
        } else if ($(byText("Submit")).isDisplayed()){
            $(byText("Submit")).click();
        }

        clickOnNextQuestionButton();
        if (isViewResultsButtonVisible()){
            return;
        }
    }

    public boolean isViewResultsButtonVisible() {
        return isDisplayedBy(viewResultsButtonLiteracyBy);
    }

    public void clickPY(WebElement element) {
        logger.info("Click on element ");
        if(!$(element).isDisplayed()) {
            closeWalkme();
        }
        try {
            clickChange(element);
        } catch (Exception e) {
            closeWalkme();
            $(element).isDisplayed();
            clickChange(element);
        }
    }

    public void clickChange(WebElement element) {
        loggerMessageWithXpath(element, "Trying to click on element ");

        if (!isElementAbsentBy(topNavToolbarBy)) {
            executeJavaScript("arguments[0].scrollIntoView(false);", element);
        }
            $(element).click();
    }

    public void clickOnNextQuestionButton() {
        if (isElementPresentBy(nextQuestionButtonLiteracyBy))
            $(nextQuestionButtonLiteracyBy).click();
    }

    public void clickIfDisplayedSubmitButton(){
        Configuration.pageLoadStrategy = "normal";
        while ($(loginButtonBy).isDisplayed() || $(byText("LOG IN")).isDisplayed()){
            $(loginButtonBy).click();
        }
        Configuration.pageLoadStrategy = "eager";
    }






}
