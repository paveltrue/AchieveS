package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.pages.SearchWidgetPage;
import com.a3k.utils.db.DatabaseReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertTrue;


public class US16528 extends BasicTestCase {

    private HomePage homePage;
    private MyLessons myLessons;
    private LoginPage loginPage;
    private long id;
    private String collectionName;
    private SearchWidgetPage searchWidget;
    private List<String> firstOrderOfLessons;
    private Map<String, String> lessonsOrderBefore;
    private Map<String, String> lessonsOrderAfter;
    private DatabaseReader dbReader;

    @DataProvider
    public Object[][] getUsers() {
        Object[][] data = new Object[][]{
                {"ukda16528.alexnoclass", "ukda16528.alexnoclass", "KidBiz3000", "", "ukteach11858.alex", "ukteach11858.alex"}
        };
        return data;
    }



    @Parameters({"login", "password", "program", "selectedClass", "loginTeacher", "passwordTeacher"})
    @Test(dataProvider = "getUsers", groups = {"Collection Details", "Incognita", "All"}, invocationCount = 5)
    public void check_US16528(@Optional() String login,
                              @Optional() String password,
                              @Optional() String program,
                              @Optional() String selectedClass,
                              @Optional() String loginTeacher,
                              @Optional() String passwordTeacher) {
        loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);

        homePage = new HomePage(driver);
        homePage.changeGradeTo(8);

        homePage.goToMyLessonsPage();
        myLessons = new MyLessons(driver);

        myLessons.deleteAllCollections();


        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickOnCreateCollectionBy();

        logger.info("Create new collection");

        id = System.currentTimeMillis();
        collectionName = "Test collection -- US16528 -- " + id;

        myLessons.enterTextInCollectionNameInput(collectionName);
        myLessons.enterTextInDescriptionInput("Test");

        myLessons.clickShowClasses();

        String classId = myLessons.checkNeededClassAndGetIdOnShowClasses("Uk Auto Class 3g");

        myLessons.clickOnContinueButtonInShowClassPopUp();

        searchWidget = myLessons.clickOnAddLessonButton();

        myLessons.waitForJSandJQueryToLoad();

        searchWidget.clickOnAdvancedOption();

        searchWidget.selectInCourseDDL("News");

        searchWidget.clickOnSearchButtonByText();

        searchWidget.addLessonsOnSinglePage(5);

        LocalDate today = LocalDate.now();

        String startDate = today.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

        String dateOfTodaySpletter[] = startDate.split("-");
        String dayOfToday = dateOfTodaySpletter[2];
        String monthOfToday = dateOfTodaySpletter[1];
        String yearOfToday = dateOfTodaySpletter[0];

        LocalDate collectionDateEnd = today.plusDays(4);

        String dateOfCollectionEnd = collectionDateEnd.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

        String dateOfCollectionEndSplitter[] = dateOfCollectionEnd.split("-");
        String dayOfCollectionEnd = dateOfCollectionEndSplitter[2];
        String monthOfCollectionEnd = dateOfCollectionEndSplitter[1];
        String yearOfCollectionEnd = dateOfCollectionEndSplitter[0];


        myLessons.setDateOnCalendarBy(yearOfToday, monthOfToday, dayOfToday, myLessons.getCollectionStartDateInputBy());
        myLessons.setDateOnCalendarBy(yearOfCollectionEnd, monthOfCollectionEnd, dayOfCollectionEnd, myLessons.getCollectionEndDateInputBy());

        myLessons.enterTextInAssignNewLessonEveryInput("1");
        myLessons.checkSomeDaysOfWeekStartFrom(1, 5);

        myLessons.enterTextInKeepLessonsLiveForInput("1");
        firstOrderOfLessons = myLessons.getCollectionLessonsTitles();

        myLessons.clickSaveAndAssignCollection();
        myLessons.clickContinueButtonOnPopUpAssignCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();

        logger.info("Collection created with name: " + collectionName);

        myLessons.logOut();

        if(myLessons.isAlertPresent()) {
        	myLessons.waitAndAcceptAlert();
        }

        loginPage.waitForJSandJQueryToLoad();

        loginPage.loginWithClassAndProgramIfNeededWithAlert(loginTeacher, passwordTeacher, program, selectedClass);
        myLessons = loginPage.goToMyLessonsByLink("1");

        String lessonIdFromCollection = myLessons.openAnyNotStartedLesson();
        
        if(lessonIdFromCollection.isEmpty()) {
        	assertTrue(false, "The lessons from collection have not been added to teacher's lesson list.");
        }

        myLessons.goToMyLessonsByLink("1");
        myLessons.clickInfoIconOfLesson(lessonIdFromCollection);

        myLessons.openStartCalendarLessonInfo();

        myLessons.setStartDateOnCalendar(today);
        myLessons.setEndDateOnCalendar(collectionDateEnd.plusDays(2));
        myLessons.clickOnOkButtonOnCalendar();

        String query = "select * from my_lessons where owner_id=" + classId + " and lesson_id=" + lessonIdFromCollection;

        softAssert.assertEquals(queryDb(query, "origin_type"), "collection",
                "  Origin type of lesson is not 'collection' in DB.");

        softAssert.assertAll();
    }


    private String queryDb(String query, String returnValue){
        dbReader = new DatabaseReader(url());
        return dbReader.query(query, returnValue);
    }
}