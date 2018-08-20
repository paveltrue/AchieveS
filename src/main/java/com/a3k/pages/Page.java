package com.a3k.pages;


import com.a3k.utils.UserPasswordsChanger;
import com.a3k.utils.logger.BasicLogger;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

import java.util.ArrayList;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
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
    private By popupBy = By.xpath("//div[not(contains(@class,'supportsDialog ')) and not(contains(@class,'videoDialog')) and @role='dialog' and contains(@style,'block')]");
    protected WebElement walkmePopup = $(By.xpath(".//*[contains(@class, 'wm-close-button') or contains(@class, 'walkme-x-button') or contains(@class, 'walkme-action-close')]"));
    private By popupContentBy = By.xpath("//div[(@role='dialog') and (contains(@style,'display: block;'))]/div[contains(@class,'alert_dialog')]");
    protected By adminButtonBy = By.xpath(".//a[@href='/kb/loader_admin/' or @href='/admin/settings' or @id='adminMenuItem' or contains(@href,'admin_section=1')]");
    protected By searchButtonBy = By.xpath("//*[@href='/kb/search/' or @href='/n/search/' or @data-dropdown='#search-div' or @class='searchIcon']");



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
            if ($(element).exists()) {
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

    public void clickActionsChange(By by) {
        loggerMessageWithXpathBy(by, "Trying to click on locator via (former)Actions(changed method)");
        $(by).click();
    }

    public void loggerMessageWithXpathBy(By by, String message) {
        message = message + " " + by;
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
        try {
            WebElement element = $(by);
            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
            Alert alert = switchTo().alert();
            alert.dismiss();
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
            $(by).shouldBe(visible);
            isClickable = true;

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




}
