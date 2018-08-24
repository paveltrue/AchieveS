package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class CreateNewClassPage extends Page {

    private By classNameTextAreaBy = By.id("class_name");
    private By productDdlBy = By.id("product_id");
    private ElementsCollection contentAreaDdl = $$(By.id("content_area_id"));
    private WebElement gradeDdl = $(By.xpath("//select[@id ='grade']"));
    private WebElement programDdl = $(By.id("program_id"));
    private ElementsCollection automaticallyAsignmentDdl = $$(By.id("dv"));
    private ElementsCollection schoolYearDDL = $$(By.id("academic_year_id"));
    private WebElement productDdl = $(By.id("product_id"));
    private WebElement nextButton = $(By.xpath("//a[contains(@href,'doSubmit')]"));
    private WebElement addButton = $(By.xpath(".//input[@class = 'button']"));
    private WebElement yourClassMembers = $(By.xpath(".//select[@name = 'members[]']"));
    private WebElement nextButtonOnClassList = $(By.xpath("(.//a[@class = 'toolbar'])[3]"));
    private ElementsCollection tableOfMembers = $$(By.xpath(".//table[@class = 'confirm2']"));
    private WebElement linkFinish = $(By.xpath(".//div[@id = 'lnkFinish']//span"));
    private WebElement paragraphAfterSetUpClass = $(By.xpath("(.//td[@class = 'label'])[1]/p"));
    private By lickCloseBy = By.xpath("(.//a[@class = 'toolbar'])[2]");


    private String setupClassEn = "Congratulations! You have successfully set up class";
    private String setupClassSp = "Ha configurado correctamente la clase";

    public CreateNewClassPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public void fillNewClassFields(String className) {
        typeTextInClassName(className);
        selectOption("contentArea");

        selectOption("grade");

        selectOption("program");


        selectOption("schoolYear");
        selectOption("product");

        selectOption("automaticalAsignment");

        getWebDriver().manage().window().maximize();
    }

    public void typeTextInClassName(String text) {
        waitUntilAppearsBy(classNameTextAreaBy);
        $(findEl(classNameTextAreaBy)).setValue(text);
    }

    public void selectOption(String nameOfSelect) {
        Select sel;
       // waitUntilElementsAppearsLocatedBy(productDdlBy);
        waitForJSandJQueryToLoad();
        waitForPageToLoad();

        if (nameOfSelect.equals("contentArea")) {
            sel = new Select(contentAreaDdl.get(0));
            sel.selectByIndex(sel.getOptions().size() / 2);
        } else if (nameOfSelect.equals("grade")) {
            waitElementWebEl(gradeDdl);
            waitForJSandJQueryToLoad();
            waitForPageToLoad();
            //waitThreadSleep(2000);
            waitUntilAppearsWebEl(gradeDdl);
            sel = new Select(gradeDdl);
            sel.selectByValue("6");
        } else if (nameOfSelect.equals("program")) {
            waitUntilAppearsWebEl(programDdl);
            sel = new Select(programDdl);
            sel.selectByValue("1");
           // $(programDdl).selectOptionByValue("1");
        } else if (nameOfSelect.equals("product")) {
            waitUntilAppearsWebEl(productDdl);
            sel = new Select(productDdl);
            sel.selectByIndex(2);
           // $(productDdl).selectOption(2);
        } else if (nameOfSelect.equals("automaticalAsignment")) {
            if (isElementsExist(automaticallyAsignmentDdl)) {
                sel = new Select(automaticallyAsignmentDdl.get(0));
                sel.selectByIndex(sel.getOptions().size() / 2);
//                $(automaticallyAsignmentDdl.get(0)).
//                        selectOption($$(automaticallyAsignmentDdl).get(0).getSelectedOptions().size() /2);
            }
        } else if (nameOfSelect.equalsIgnoreCase("schoolYear")) {
            sel = new Select(schoolYearDDL.get(0));
            sel.selectByIndex(sel.getOptions().size() / 2);
            //$(schoolYearDDL.get(0)).selectOption($$(schoolYearDDL).get(0).getSelectedOptions().size() / 2);
        }
    }

    public void clickNext() {
        clickJSWebEl(nextButton);
    }

    public void clickAdd() {
        clickJSWebEl(addButton);
    }

    public boolean isYourClassMembersCorrect() {
        boolean isCorrect = false;
        Select sel = new Select(yourClassMembers);
        //if($(yourClassMembers).getSelectedOptions().size() == 2)
        if (sel.getOptions().size() == 2)
            isCorrect = true;
        return isCorrect;
    }

    public void clickNextOnClassListView() {
        $(nextButtonOnClassList).click();
    }

    public boolean isMembersCorrect() {
        boolean isCorrect = false;
        if (this.isElementsExist(tableOfMembers))
            isCorrect = true;
        return isCorrect;
    }

    public void clickFinish() {
        $(linkFinish).click();
    }

    public boolean isClassWasSetUp(String language) {

        String setUpClass = $(paragraphAfterSetUpClass).getText();

        if (language.equals("english")) {
            return setUpClass.contains(setupClassEn);
        } else {
            return setUpClass.contains(setupClassSp);
        }
    }

    public void clickClose() {
        $(lickCloseBy).click();
    }


}
