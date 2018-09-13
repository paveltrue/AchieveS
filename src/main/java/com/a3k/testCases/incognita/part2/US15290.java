package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class US15290 extends BasicTestCase {


    private HomePage homePage;
    private MyLessons myLessons;
    private long id;
    private String expCollectionName;
    private SearchWidgetPage searchWidget;
    private List<String> expLessonsName;
    private PrintCollectionPage printPage;
    private MyLessons.Product expProductValue;
    private String expSubjectValue;
    private String expStartDate;
    private String expEndDate;
    private String expDays;
    private String expKeepLessonsLiveDays;
    private String actProductValue;
    private String actSubjectValue;
    private List<String> actLessonsName;
    private String actStartDate;
    private String actEndDate;
    private String actDays;
    private String actKeepLessonsLiveDays;
    private String actCollectionName;
    private String collectionNameEdit;
    private String expDescriptionCollection;
    private String actDescriptionCollection;

    @DataProvider
    public Object[][] getUsers() {
        Object[][] data = new Object[][]{
                {"ussa15290.alex", "ussa15290.alex", "KidBiz3000", ""},
                {"usda15290.alex", "usda15290.alex", "KidBiz3000", ""},
                {"uksa15290.alex", "uksa15290.alex", "KidBiz3000", ""},
                {"ukda15290.alex", "ukda15290.alex", "KidBiz3000", ""},
                {"kb.ref", "kb.ref", "KidBiz3000", ""}
        };
        return data;
    }

    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"Build Lesson Collections", "Print", "Incognita", "All"})
    public void check_US15290(@Optional() String login, @Optional() String password, @Optional() String program, @Optional() String selectedClass) {

        expProductValue = MyLessons.Product.PRO;
        expSubjectValue = "English Language Arts/Reading";
        expDays = "Sunday, Monday, Tuesday";
        expKeepLessonsLiveDays = "2";

        expDescriptionCollection = "Test collection description";


        login(login, password, program, selectedClass);

        homePage = new HomePage(driver);
        homePage.goToMyLessonsPage();

        myLessons = new MyLessons(driver);
        myLessons.deleteAllCollections();

        createCollectionWithAddedLesson("Test Collection - ", "ENGLISH");

        myLessons.clickOnPrintButtonOfCollectionBy();
        myLessons.switchToNextWindowWhenExistOnly2();

        printPage = new PrintCollectionPage(driver);

        getWebDriver().manage().window().maximize();

        actCollectionName = printPage.getNameOfCollection();
        softAssert.assertEquals(actCollectionName, expCollectionName, "The name of collection is incorrect.");

        actDescriptionCollection = printPage.getDescriptionOfCollection();
        softAssert.assertEquals(actDescriptionCollection, expDescriptionCollection, "The description of collection is incorrect.");

        actProductValue = printPage.getValueOfNeededRow("Product");
        logger.info("EXPECT PRODUCT  = " + expProductValue + "//" + expProductValue.toString());
        softAssert.assertEquals(actProductValue, expProductValue.toString(), "The value of product is incorrect.");

        actSubjectValue = printPage.getValueOfNeededRow("Subject");
        softAssert.assertEquals(actSubjectValue, expSubjectValue, "The value of subject is incorrect.");

        actLessonsName = printPage.getNameFromLessonsList();
        softAssert.assertEquals(actLessonsName, expLessonsName, "The value of lessons is incorrect.");

        actStartDate = printPage.getValueOfNeededRowFromDeliveryTable("Start Date");
        softAssert.assertEquals(actStartDate, expStartDate, "The value of start date is incorrect.");

        actEndDate = printPage.getValueOfNeededRowFromDeliveryTable("End Date");
        softAssert.assertEquals(actEndDate, expEndDate, "The value of subject is incorrect.");

        actDays = printPage.getValueOfNeededRowFromDeliveryTable("Assign new lesson");
        softAssert.assertEquals(actDays, expDays, "The value of days is incorrect.");

        actKeepLessonsLiveDays = printPage.getValueOfNeededRowFromDeliveryTable("Keep lessons live");
        softAssert.assertEquals(actKeepLessonsLiveDays, expKeepLessonsLiveDays, "The value of Keep lessons live is incorrect.");

        softAssert.assertAll();
        getWebDriver().close();
    }


    @Step
    private void login(String login, String password, String program, String selectedClass) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
    }

    @Step
    private void createCollectionWithAddedLesson(String name, String language) {
        logger.info("Create new collection");

        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickOnCreateCollectionBy();

        id = System.currentTimeMillis();
        expCollectionName = name + id;

        myLessons.enterTextInCollectionNameInput(expCollectionName);
        myLessons.enterTextInDescriptionInput(expDescriptionCollection);

        myLessons.selectInProductDDL(expProductValue);
        myLessons.selectInSubjectsDDL(expSubjectValue);

        addLessonInCollection();

        saveCollectionWithLessons();
        logger.info("Collection created with name: " + expCollectionName);
    }

    @Step
    private void addLessonInCollection() {
        logger.info("Add lesson into collection");
        myLessons.clickOnAddLessonButton();

        searchWidget = new SearchWidgetPage(driver);
        searchWidget.clearTextInputInSearchBar();

        searchWidget.addSomeLessons(3);
        expLessonsName = myLessons.getNamesOfLessonsFromLessonListArea();
    }

    @Step
    private void saveCollectionWithLessons() {

        LocalDate today = LocalDate.now();

        String dateOfToday = today.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

        String dateOfTodaySpletter[] = dateOfToday.split("-");
        String dayOfToday = dateOfTodaySpletter[2];
        String monthOfToday = dateOfTodaySpletter[1];
        String yearOfToday = dateOfTodaySpletter[0];

        LocalDate collectionDateEnd = today.plusDays(7);

        String dateOfCollectionEnd = collectionDateEnd.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

        String dateOfCollectionEndSplitter[] = dateOfCollectionEnd.split("-");
        String dayOfCollectionEnd = dateOfCollectionEndSplitter[2];
        String monthOfCollectionEnd = dateOfCollectionEndSplitter[1];
        String yearOfCollectionEnd = dateOfCollectionEndSplitter[0];

        myLessons.scrollDown();

        myLessons.setDateOnCalendarBy(yearOfToday, monthOfToday, dayOfToday, myLessons.getCollectionStartDateInputBy());
        myLessons.setDateOnCalendarBy(yearOfCollectionEnd, monthOfCollectionEnd, dayOfCollectionEnd, myLessons.getCollectionEndDateInputBy());

        myLessons.enterTextInAssignNewLessonEveryInput("1");

        // check 2 days from start of week ( e.g sunday, monday)
        myLessons.checkSomeDaysOfWeek(3);
        myLessons.enterTextInKeepLessonsLiveForInput(expKeepLessonsLiveDays);

        expStartDate = myLessons.getSelectedStartDate();

        expEndDate = myLessons.getSelectedEndDate();

        myLessons.saveCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();

    }
}
