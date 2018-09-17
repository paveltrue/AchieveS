package com.a3k.pages;

import com.codeborne.selenide.Configuration;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertTrue;


public class LoginPage extends Page {

    private By loginCellBy = By.id("login_name1");
    private By passwordCellBy = By.id("password1");
    private By loginButtonBy = By.xpath(".//*[@id = 'button' and @type = 'submit']");
    private By popupViewBy = By.xpath("//div[@role='dialog' and not(contains(@style,'display: none'))]//button[@title='close']");
    private By emailNoticeContainerBy = By.xpath("//div[@id='emailNoticeDiv']/iframe");
    private WebElement emailNoticeContainer = $(By.xpath("//div[@id='emailNoticeDiv']/iframe"));
    private WebElement chooseClassCombo = $(By.id("active_class"));
    private WebElement chooseClassComboPY = $(By.xpath("//*[@id=\"active_class\"]"));
    private WebElement chooseClassOrProgramm =$(By.xpath("//label[contains(@for,'active_')]"));
    private WebElement chooseProgramCombo = $(By.id("active_pgm"));
    private By emailInputBy = By.xpath("//table[@class='emailAlert']//input[@name='email']");
    private By confirmEmailInputBy = By.xpath("//table[@class='emailAlert']//input[@name='confirm_email']");
    private By submitEmailButtonBy = By.xpath("//table[@class='emailAlert']//input[@name='btn1']");
    private By incorectPassowrdMessageBy = By.xpath("//div//alert");
    private By chooseClassComboBy = By.id("active_class");
    private WebElement chooseClassComboXpath = $(By.xpath("//*[@id=\"programs_selector\"]/label"));
    private By levelSetApproachingNoteCloseButtonBy = By.xpath(".//*[@class='textRed']");
    private By chooseProgramComboBy = By.id("active_pgm");
    private By nextButtonOfSurveyBy = By.xpath(".//button[contains(@class, 'begin-response-button') or contains(@class, 'next-page-button') or contains(@class, 'md-button')]");
    private By checkboxesOfSurveyBy = By.xpath(".//div[@class = 'md-icon' or @class = 'md-off']");
    private By continueButtonBy = By.xpath("//*[@class='md-button md-primary md-raised']");



    public LoginPage(WebDriver driver){
        this.driver = driver;
        //PageFactory.initElements(driver, this);
        }

    public void login(String login, String password, String... parametrs) {
        loginWithoutRefresh(login, password, parametrs);
        refresh();
    }

    public String loginWithTwoPasswordsWithDefaultClass(String login, String password1, String password2) {
        String validPassword = "";
        logger.info(String.format("Login with two password. Username: %s, password1: %s, password2: %s", login,
                password1, password2));

        validPassword = password1;
        setUser(login);
        setPassword(password1);

        logger.info(String.format("Login attempt #1. Username: %s, password2: %s", login, password1));
        clickLoginButtonWithTwoPasswords();

        if (isIncorrectMessagePresent()) {
            logger.info("'Incorrect password' message is present");
            logger.info(String.format("Login attempt #2. Username: %s, password2: %s", login, password2));

            validPassword = password2;
            super.waitForJSandJQueryToLoad();
            setUser(login);
            setPassword(password2);
            clickLoginButtonWithTwoPasswords();
        }

        if ($(chooseClassComboBy).isDisplayed()) {
            clickLoginButtonWithTwoPasswords();
        }

        logger.info("Logged in successfully with password " + validPassword);
        return validPassword;
    }

    public void loginIntoWithoutClasses(String login, String password) {
        setUser(login);
        setPassword(password, login);
        clickLoginButton();
    }

    public void setPassword(String password, String login){
        password = replacePasswordIfNeed(login, password);
        setPassword(password);
    }

    public void setUser(String user){
        logger.info("Type in username: " + user);
        $(loginCellBy).clear();
        $(loginCellBy).setValue(user);
    }

    public void setUserAfterAlert(String user){
        logger.info("Type in username: " + user);
        if (isAlertPresent()){
            confirm();
        }
        $(loginCellBy).setValue(user);
    }

    public void setPassword(String password) {
        $(passwordCellBy).clear();
        logger.info("Type in password: " + password);
        $(passwordCellBy).setValue(password);
    }

    public void clickLoginButtonWithTwoPasswords() {
        $(loginButtonBy).click();

        if (!$(loginButtonBy).isDisplayed()) {
            closeWalkme();
            waitAndCLickIfExist(popupViewBy);

            if ($(emailNoticeContainerBy).isDisplayed()){
                submitEmail();
            }
            if ($(levelSetApproachingNoteCloseButtonBy).isDisplayed()) {
                $(levelSetApproachingNoteCloseButtonBy).click();
            }
        }
    }

    public void submitEmail() {
        String email = "test@test.com";
        if (!$(emailNoticeContainer).isDisplayed()){
            return;
        }
        switchTo().frame(emailNoticeContainer);

        $(emailInputBy).clear();
        $(emailInputBy).setValue(email);

        $(confirmEmailInputBy).setValue(email);
        $(submitEmailButtonBy).click();

        switchTo().defaultContent();
    }

    public boolean isIncorrectMessagePresent() {
        return $(incorectPassowrdMessageBy).isDisplayed();
    }

    public void loginWithoutRefresh(String login, String password, String... parametrs) {
        logger.info(String.format("Login with credentials %s\\%s ",
                login, password));

        setUser(login);
        setPassword(password);

        clickLoginButton();
        if (isIncorrectMessagePresent() & !login.contains("[a-z]*[0-9]")){
            setUser(login);
            setPassword(password);

            clickLoginButton();

            if (isIncorrectMessagePresent()) {
                setUser(login);
                setPassword(password, login);
                clickLoginButton();
                if (isIncorrectMessagePresent()) {
                    assertTrue(false, "The user/or password is incorrect.");
                }
            }
        }
        if (parametrs.length > 0) {

            if (parametrs[0].isEmpty() && parametrs.length == 1) {
                if ($(By.xpath("//*[@href='/mail/' or (@class='message' and @title) or @href='/n/my_lessons/']")).isDisplayed()) {
                    return;
                }
                Select select = null;

                boolean combo = false;
                try {
                    if ($(chooseClassOrProgramm).isDisplayed())
                        return;
                }catch (Exception e) {
                    e.printStackTrace();
                }

                if ($(chooseClassCombo).isDisplayed()) {
                    select = new Select(chooseClassCombo);
                    combo = true;
                } else {
                    if ($(chooseProgramCombo).isDisplayed()) {

                        select = new Select(chooseProgramCombo);
                        combo = true;
                    }
                }

                if (combo) {
                    try {
                        if (parametrs[0].isEmpty() && parametrs.length == 2) {
                            select.selectByVisibleText(parametrs[1]);
                        } else {
                            select.selectByVisibleText(parametrs[0]);
                            if (parametrs.length == 2) {
                                select = new Select(chooseClassCombo);
                                select.selectByVisibleText(parametrs[1]);
                            }
                        }
                    } catch (NoSuchElementException e) {
                        logger.trace("Unable to locate element " + e.getMessage());
                    }
                    clickLoginButton();
                }
            }

            logger.info("Close teacher surv");

            if (url().contains("home")) {
                return;
            }
            closeTeachersSurv();
            //waitForPageToLoad();
            if ($(loginButtonBy).isDisplayed()){
                clickLoginButton();
            }
            waitForJSandJQueryToLoad();
        }
    }

    public void loginWithoutRefreshNew(String login, String password, String classToSelect) {
        logger.info(String.format("Login with credentials %s\\%s ",
                login, password));

        setUser(login);
        setPassword(password);
        clickLoginButton();

        if (isIncorrectMessagePresent() & !login.contains("[a-z]*[0-9]")){
            setUser(login);
            setPassword(password);
            clickLoginButton();

            if (isIncorrectMessagePresent()) {
                if (isIncorrectMessagePresent()) {
                    setUser(login);
                    setPassword(password, login);
                    clickLoginButton();
                    if (isIncorrectMessagePresent()) {
                        assertTrue(false, "The user/or password is incorrect.");
                    }
                }
            }
        }

        if (isDisplayedBy(chooseClassComboBy) || isDisplayedBy(chooseClassComboXpath)) {
            if ($(chooseClassComboBy).isDisplayed()) {
                Select selectClass = new Select(findEl(chooseClassComboBy));
                selectClass.selectByVisibleText(classToSelect);
                sleep(500);
                clickGoNext();
            } else if ($(chooseClassComboXpath).isDisplayed()){
                Select selectClass = new Select($(By.xpath("//*[@id=\"active_pgm\"]")));
                selectClass.selectByVisibleText(classToSelect);
                sleep(500);
                clickGoNext();
            }
        }
        afterLoginCheck(classToSelect);

        sleep(1000);
        if (!$(loginCellBy).isDisplayed()) {
            return;
        } else {
            loginWithoutRefreshNew(login, password, classToSelect);
        }


    }



    public void closeTeachersSurv() {
        if (isTextOnPagePresent("Tell us a little more")) {
            open(url().split(".com", 1)[0] + ".com");
        }
    }

    public void clickLoginButton(){
        $(loginButtonBy).click();
        //waitUntilJQueryToLoad();
        isElementDisappears(passwordCellBy);

        //waitForPageToLoad();
        if (url().contains("home") || url().contains("levelset/welcome")) {
            logger.info("Stop login");
            return;
        }
        logger.debug(url().contains("home") + url());


        if (!$(loginButtonBy).isDisplayed() && !$(nextButtonLevelSetBy).isDisplayed()) {
            if (findEls(walkmePopupBy).size() > 1) {
                closeWalkMeIfDisplayedBy(walkmePopupBy);
            } else {
                closeWalkme();
            }
            waitAndCLickIfExist(popupViewBy);

            if ($(emailNoticeContainerBy).exists()) {
                submitEmail();
            }
            if ($(levelSetApproachingNoteCloseButtonBy).exists()) {
                $(levelSetApproachingNoteCloseButtonBy).click();
            }
        }
    }

    public void loginWithClassAndProgramIfNeeded(String login, String password, String program, String classToSelect){
        logger.info(String.format("Login with credentials %s\\%s", login, password));
        Configuration.pageLoadStrategy = "normal";
        Configuration.timeout = 10000;
        setUser(login);
        setPassword(password);
        clickLoginButton();
        waitForPageToLoad();
        if ((url().contains("home") || url().contains("levelset/welcome")) && !$(loginButtonBy).isDisplayed()) {
            closeAllPopUpAfterLogin();
            return;
        }
        if (isIncorrectMessagePresent() & !checkLoginWithRegExp(login)) {
            refresh();
            setUser(login);
            setPassword(password, login);
            clickLoginButton();

            if (isIncorrectMessagePresent()) {
                assertTrue(false, "The user = " + login + "/or password = " + password + " is incorrect.");
            }
        }
        Configuration.pageLoadStrategy = "normal";
        if (isDisplayedBy(chooseProgramComboBy)) {
            Configuration.pageLoadStrategy = "normal";
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Select selectProgram = new Select(findEl(chooseProgramComboBy));
            selectProgram.selectByVisibleText(program);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isDisplayedBy(chooseClassComboBy)) {
                Configuration.pageLoadStrategy = "normal";
                Select selectClass = new Select(findEl(chooseClassComboBy));
                selectClass.selectByVisibleText(classToSelect);
                //clickLoginButtonNew();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Configuration.pageLoadStrategy = "normal";
                clickGoNext();
            }else{
                clickGoNext();
                //clickLoginButtonNew();
            }
        } else if (isDisplayedBy(chooseClassComboBy)){
            waitUntilAppearsBy(chooseClassComboBy);
            if(!classToSelect.isEmpty()){
                Select selectClass = new Select(findEl(chooseClassComboBy));
                selectClass.selectByVisibleText(classToSelect);
            }
            clickGoNext();
            //clickLoginButtonNew();
        } else {
            closeAllPopUpAfterLogin();
        }

        while ($(loginButtonBy).isDisplayed()){
            $(loginButtonBy).click();
        }
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 4000;
    }

    public void loginWithClassAndProgramIfNeededNew(String login, String password, String program, String classToSelect){
        logger.info(String.format("Login with credentials %s\\%s", login, password));
        Configuration.timeout = 10000;
        setUser(login);
        setPassword(password);
        clickLoginButton();
        if ((url().contains("home") || url().contains("levelset/welcome")) && !$(loginButtonBy).isDisplayed()) {
            closeAllPopUpAfterLogin();
            return;
        }
        if (isIncorrectMessagePresent() & !checkLoginWithRegExp(login)) {
            refresh();
            setUser(login);
            setPassword(password, login);
            clickLoginButton();

            if (isIncorrectMessagePresent()) {
                assertTrue(false, "The user = " + login + "/or password = " + password + " is incorrect.");
            }
        }
        Configuration.pageLoadStrategy = "normal";
        if (isDisplayedBy(chooseProgramComboBy)) {
            Configuration.pageLoadStrategy = "normal";
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Select selectProgram = new Select(findEl(chooseProgramComboBy));
            selectProgram.selectByVisibleText(program);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (isDisplayedBy(chooseClassComboBy)) {
                Configuration.pageLoadStrategy = "normal";
                Select selectClass = new Select(findEl(chooseClassComboBy));
                selectClass.selectByVisibleText(classToSelect);
                //clickLoginButtonNew();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Configuration.pageLoadStrategy = "normal";
                clickGoNext();
            }else{
                clickGoNext();
                //clickLoginButtonNew();
            }
        } else if (isDisplayedBy(chooseClassComboBy)){
            waitUntilAppearsBy(chooseClassComboBy);
            if(!classToSelect.isEmpty()){
                Select selectClass = new Select(findEl(chooseClassComboBy));
                selectClass.selectByVisibleText(classToSelect);
            }
            clickGoNext();
            //clickLoginButtonNew();
        } else {
            closeAllPopUpAfterLogin();
        }

        while ($(loginButtonBy).isDisplayed()){
            $(loginButtonBy).click();
        }
        Configuration.pageLoadStrategy = "eager";
        Configuration.timeout = 4000;
    }

    public void loginWithClassAndProgramIfNeededWithAlert(String login, String password, String program, String classToSelect){
        logger.info(String.format("Login with credentials %s\\%s", login, password));
        Configuration.pageLoadStrategy = "normal";
        setUserAfterAlert(login);
        setPassword(password);
        clickLoginButton();
        waitForPageToLoad();
        //if ((url().contains("home") || url().contains("levelset/welcome")) && !$(loginButtonBy).isDisplayed()) {
        if ((url().contains("home") || url().contains("levelset/welcome"))) {
            closeAllPopUpAfterLogin();
            return;
        }

        if (isIncorrectMessagePresent()){
            refresh();
            setUserAfterAlert(login);
            setPassword(password);
            clickLoginButton();
        }
        if (isIncorrectMessagePresent() & !checkLoginWithRegExp(login)) {
            refresh();
            setUserAfterAlert(login);
            setPassword(password, login);
            clickLoginButton();

            if (isIncorrectMessagePresent()) {
                assertTrue(false, "The user = " + login + "/or password = " + password + " is incorrect.");
            }
        }
        Configuration.pageLoadStrategy = "normal";
        if (isDisplayedBy(chooseProgramComboBy)) {
            Configuration.pageLoadStrategy = "normal";
            sleep(500);
            Select selectProgram = new Select(findEl(chooseProgramComboBy));
            selectProgram.selectByVisibleText(program);
            sleep(500);
            if (isDisplayedBy(chooseClassComboBy)) {
                Configuration.pageLoadStrategy = "normal";
                Select selectClass = new Select(findEl(chooseClassComboBy));
                selectClass.selectByVisibleText(classToSelect);
                //clickLoginButtonNew();
                sleep(500);
                Configuration.pageLoadStrategy = "normal";
                clickGoNext();
            }else{
                clickGoNext();
                //clickLoginButtonNew();
            }
        } else if (isDisplayedBy(chooseClassComboBy)){
            waitUntilAppearsBy(chooseClassComboBy);
            if(!classToSelect.isEmpty()){
                Select selectClass = new Select(findEl(chooseClassComboBy));
                selectClass.selectByVisibleText(classToSelect);
            }
            clickGoNext();
            //clickLoginButtonNew();
        } else {
            closeAllPopUpAfterLogin();
        }

        if ($(loginButtonBy).isDisplayed()){
            $(loginButtonBy).click();
        }
        if (isAlertPresent()){
            confirm();
            Select selectProgram = new Select(findEl(chooseProgramComboBy));
            selectProgram.selectByVisibleText(program);
            clickGoNext();
        }

        if (!(url().contains("home") || !url().contains("levelset/welcome"))) {
            Select selectProgram = new Select(findEl(chooseProgramComboBy));
            selectProgram.selectByVisibleText(program);
            clickGoNext();
            if (isDisplayedBy(chooseClassComboBy)) {
                Configuration.pageLoadStrategy = "normal";
                Select selectClass = new Select(findEl(chooseClassComboBy));
                selectClass.selectByVisibleText(classToSelect);
                sleep(500);
                Configuration.pageLoadStrategy = "normal";
                clickGoNext();
            }
        }
        if (isIncorrectMessagePresent()) {
            refresh();
            setUserAfterAlert(login);
            setPassword(password, login);
            clickLoginButton();
        }
        sleep(1500);
        if (isDisplayedBy(chooseClassComboBy)) {
            Configuration.pageLoadStrategy = "normal";
            Select selectClass = new Select(findEl(chooseClassComboBy));
            selectClass.selectByVisibleText(classToSelect);
            sleep(500);
            Configuration.pageLoadStrategy = "normal";
            clickGoNext();
        }
    }

    public void afterLoginCheck(String classToSelect){
        if (isDisplayedBy(chooseClassComboBy)) {
            Select selectClass = new Select(findEl(chooseClassComboBy));
            selectClass.selectByVisibleText(classToSelect);
            sleep(500);
            clickGoNext();
        }
    }

    public void closeAllPopUpAfterLogin(){

        closeWalkmeNew();

        waitAndCLickIfExist(popupViewBy);

        if ($(emailNoticeContainerBy).isDisplayed()){
            submitEmail();
        }
        if ($(levelSetApproachingNoteCloseButtonBy).isDisplayed()) {
            $(levelSetApproachingNoteCloseButtonBy).click();
        }
    }

    public void clickGoNext() {
        $(loginButtonBy).click();

        waitForPageToLoad();
        if(url().contains("home")  || url().contains("levelset/welcome")) {
            logger.info(" Stop login ");
            return;
        }

        if (!isElementAbsentBy(nextButtonOfSurveyBy)) {

            $(nextButtonOfSurveyBy).click();

            while (!isElementAbsentBy(nextButtonOfSurveyBy)) {
                $(findEls(checkboxesOfSurveyBy).get(0)).click();
                $(nextButtonOfSurveyBy).click();
            }
            $(continueButtonBy).click();
        }

        if (isElementAbsentBy(loginButtonBy)) {

            if (!url().contains("home")|| url().contains("levelset/welcome")) {
                closeAllPopUpAfterLogin();
            }
        }
    }

    public static boolean checkLoginWithRegExp(String login){
        Pattern p = Pattern.compile("[a-z]*[0-9]");
        Matcher m = p.matcher(login);
        return m.matches();
    }

    public void loginIntoWithClass(String login, String password, String classToSelect) {
        logger.info(String.format("Login with credentials %s\\%s to class %s", login, password, classToSelect));
        setUser(login);
        setPassword(password, login);
        clickLoginButton();
        if (!classToSelect.isEmpty()) {
            Configuration.pageLoadStrategy = "normal";
            Select select = new Select($(By.xpath("//*[@id=\"active_class\"]")));
           select.selectByVisibleText(classToSelect);
//            $(chooseClassCombo).selectOptionByValue(classToSelect);
            clickLoginButton();
        }
        closeTeachersSurv();
        if (getWebDriver().getWindowHandles().size() > 1) {
            switchToNextWindowWhenExistOnly2();
            getWebDriver().close();
            switchBackAfterClose();
        }
    }

    public void loginWithRandomClassAndProgramIfNeeded(String login, String password){
        setUser(login);
        setPassword(password, login);
        clickLoginButtonNew();

        if ($(chooseProgramComboBy).exists()) {
            waitUntilAppearsBy(chooseProgramComboBy);
            Select selectProgram = new Select(findEl(chooseProgramComboBy));
            selectProgram.selectByIndex(1);

            if ($(chooseClassComboBy).exists()) {
                waitUntilAppearsBy(chooseClassComboBy);
                Select selectClass = new Select(findEl(chooseClassComboBy));
                selectClass.selectByIndex(0);
                clickLoginButtonNew();
            } else {
                clickLoginButtonNew();
            }
        } else {
            if ($(chooseClassCombo).exists()) {
                waitUntilAppearsBy(chooseClassComboBy);
                Select selectClass = new Select(findEl(chooseClassComboBy));
                selectClass.selectByIndex(0);
            } else {
                clickLoginButtonNew();
            }
        }
    }

    public void clickLoginButtonNew() {
        waitUntilElementClickableBy(loginButtonBy);
        $(loginButtonBy).click();

        waitForPageToLoad();
        if(url().contains("home")  || url().contains("levelset/welcome")) {
            logger.info(" Stop login ");
            return;
        }
        if (isIncorrectMessagePresent()) {
            Assert.assertTrue(false,"The user/or password is incorrect.");
        }

        if (!isElementAbsentBy(nextButtonOfSurveyBy)) {

            $(nextButtonOfSurveyBy).click();

            while (!isElementAbsentBy(nextButtonOfSurveyBy)) {
                $(findEls(checkboxesOfSurveyBy).get(0)).click();
                $(nextButtonOfSurveyBy).click();
            }
            $(continueButtonBy).click();
        }

        if (isElementAbsentBy(loginButtonBy)) {

            if (!url().contains("home")|| url().contains("levelset/welcome")) {
                closeAllPopUpAfterLogin();
            }
        }
    }

    public void loginWithProgram(String login, String password, String program) {
        setUser(login);
        setPassword(password, login);
        clickLoginButton();

        if (!program.isEmpty()) {
            //$(chooseProgramCombo).shouldBe(Condition.visible);
            $(By.xpath("//*[@id=\"active_pgm\"]")).selectOptionContainingText(program);
           // Select select = new Select(chooseProgramCombo);
          //  select.selectByVisibleText(program);
            clickLoginButton();
        }
    }

    public void clickIfDisplayedSubmitButton(){
        Configuration.pageLoadStrategy = "normal";
        while ($(loginButtonBy).isDisplayed()){
            $(loginButtonBy).click();
        }
        Configuration.pageLoadStrategy = "eager";
    }

}
