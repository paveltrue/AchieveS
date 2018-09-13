package com.a3k.testCases.incognita.part1;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class US14585 extends BasicTestCase {

    private LoginPage loginPage;
    private HomePage homePage;
    private MyLessons myLessons;


    @DataProvider
    private Object[][] getUsers() {
        return new Object[][]{
                {"ussa14585.pooh", "ussa14585.pooh", "KidBiz3000", ""},
                {"uksa14585.pooh", "uksa14585.pooh", "KidBiz3000", ""},
                {"usda14585.pooh", "usda14585.pooh", "KidBiz3000", ""},
                {"ukda14585.pooh", "ukda14585.pooh", "KidBiz3000", ""}
        };
    }


    @Test(dataProvider = "getUsers", groups = {"Collection Details", "All", "Incognita"})
    private void verifyCollectionName_US14585(@Optional String username,
                                              @Optional String password,
                                              @Optional String studentProgram,
                                              @Optional String selectedClass) {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        myLessons = new MyLessons(driver);

        loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);

        homePage.goToMyLessonsPage();

        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickOnCreateCollectionBy();

        checkGrade();
        checkHundredCharacters("name");
        checkHundredCharacters("description");

        String colDesc = "some description";
        myLessons.enterTextInDescriptionInput(colDesc);
        myLessons.saveCollection();
        softAssert.assertTrue(isFieldRequired(myLessons),
                "No callout appeared");

        String colName = "1234^@&%&*™";
        myLessons.enterTextInCollectionNameInput(colName);
        myLessons.saveCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();

        softAssert.assertTrue(checkCollectionName(colName),
                "No collection with input name found");

        softAssert.assertEquals(myLessons.getCollectionDescriptionByName(colName), colDesc,
                "Description isn't equal to input name");


        softAssert.assertAll();
    }

    @Test(dataProvider = "getUsers", groups = {"Collection Details", "All", "Incognita"})
    private void verifyClasses_US14585(@Optional String username,
                                       @Optional String password,
                                       @Optional String studentProgram,
                                       @Optional String selectedClass) {

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);

        loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);

        myLessons = homePage.goToMyLessonsPage();

        if (username.equals("kb.ref")) {
            myLessons.clickOnChangeDistrictLink();
            myLessons.selectDistrictByValue("3143");
            myLessons.changeGradeTo(3);
        }

        myLessons.deleteAllCollections();
        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickOnCreateCollectionBy();

        clickOKButton();
        myLessons.enterTextInCollectionNameInput("US14585");
        myLessons.saveCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();
        clickOKButton();

        myLessons.editSpecifiedCollection("US14585");

        checkExpandable();

        myLessons.backPage();

        myLessons.clickOnBuiltLessonCollectionBy();

        myLessons.clickOnCreateCollectionBy();
        checkClassesPopup();

        myLessons.clickShowClasses();
        checkCheckboxDisability(myLessons);
        homePage.goToMyLessonsPage();
        myLessons.deleteSpecifiedCollection("US14585");
        myLessons.clickDeleteCollectionYesButton();
        myLessons.deleteSpecifiedCollection("testus_auto");
        myLessons.clickDeleteCollectionYesButton();


        softAssert.assertAll();
    }

    @Step
    private void checkExpandable() {
        softAssert.assertTrue(myLessons.isSectionHeadersOpen().get(0) == true, "Collection header isn't expanded");
        myLessons.collapseSectionHeaders();
        softAssert.assertFalse(myLessons.isSectionHeadersOpen().get(0) == true, "Collection header is expanded");
    }

    @Step
    private void checkGrade() {
        softAssert.assertTrue(compareGrades(), "Grades do not match");

        homePage.changeGradeTo(4); // assuming 3 is a default grade

        myLessons.clickOnCreateCollectionBy();
        softAssert.assertTrue(compareGrades(), "Grades do not match");

        homePage.changeGradeTo(3); // assuming 3 is a default grade

        myLessons.clickOnCreateCollectionBy();
    }

    @Step
    private boolean compareGrades() {
        String currentGrade = homePage.getActiveGrade();
        String collectionGrade = myLessons.getSelectedCollectionGrade();
        return currentGrade.equals(collectionGrade);
    }

    @Step
    private void checkClassesPopup() {
        myLessons.clickShowClasses();
        softAssert.assertTrue(myLessons.isPopupPresent(),
                "Popup not present");
        if (myLessons.isPopupPresent()) {
            checkClearClasses();
            myLessons.clickContinueButtonClassesPopup();
        }
        softAssert.assertTrue(!myLessons.isPopupPresent(),
                "Popup doesn't close");
        myLessons.clickShowClasses();
        softAssert.assertTrue(myLessons.isPopupPresent(),
                "Popup not present");
        myLessons.closeShowClassesDialog();
        softAssert.assertTrue(!myLessons.isPopupPresent(),
                "Popup doesn't close");
    }

    @Step
    private void checkClearClasses() {
        myLessons.clearClasses();
        softAssert.assertTrue(Integer.parseInt(myLessons.getSelectedClassesShowClasses()) == 0, "Class counter isn't 0");
        softAssert.assertFalse(isClassCheckboxDisabled(),
                "Class checkboxes are disabled");
        myLessons.clickSelectAllShowClassesRadio();
        softAssert.assertTrue(isClassCheckboxDisabled(),
                "Class checkbox is enabled!");
    }

    @Step
    private void checkCheckboxDisability(MyLessons myLessons) {
        myLessons.clickSomeClassesCheckbox();
        $$(myLessons.getShowClassesClassList()).get(0).click();

        myLessons.clickOnContinueButtonInShowClassPopUp();
        myLessons.enterTextInCollectionNameInput("testus_auto");
        myLessons.saveCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();

        myLessons.editSpecifiedCollection("testus_auto");
        myLessons.clickShowClasses();
        softAssert.assertTrue(myLessons.isElementsExist(myLessons.getshowClassesCheckedClasses()));
        myLessons.closeShowClassesDialog();
    }

    @Step
    private boolean isClassCheckboxDisabled() {
        return myLessons.isClassCheckboxDisabled();
    }

    @Step
    private boolean checkCollectionName(String colname) {
        Configuration.pageLoadStrategy =  "normal";
        clickOKButton();
        ElementsCollection list = myLessons.getCollectionList();
        for (WebElement element : list) {
            clickOKButton();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clickOKButton();
            if (element.getText().equals(colname)) {
                return true;
            }
        }
        return false;
    }

    @Step
    private boolean isFieldRequired(MyLessons myLessons) {
        return myLessons.isValidationAlertPresent();
    }

    @Step
    private void checkHundredCharacters(String field) {
        int limit;
        if (field.toLowerCase().equals("name")) {
            field = "collection_name";
            limit = 100;
        } else {
            field = "description";
            limit = 400;
        }
        for (int i = 0; i < limit / 10; i++) {
            if (field.equals("collection_name")) {
                myLessons.enterTextInCollectionNameInputNoClear("1234567890");
            } else {
                myLessons.enterTextInDescriptionInput("1234567890");
            }
        }

        myLessons.enterTextInCollectionNameInputNoClear("x");
        softAssert.assertFalse(myLessons.getCollectionNameInput().contains("x"),
                "Name field character limit exceeded");
        myLessons.enterTextInDescriptionInput("x");
        softAssert.assertFalse(myLessons.getCollectionDescription().contains("x"),
                "Description field character limit exceeded");
        myLessons.clearCollectionInputFields();
    }

    public void clickOKButton(){
        if($(byText("OK")).isDisplayed()){
            $(byText("OK")).click();
        }
    }




}
