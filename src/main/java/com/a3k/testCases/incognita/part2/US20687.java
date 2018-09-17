package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.utils.db.DatabaseReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class US20687 extends BasicTestCase {

    private LoginPage loginPage;
    private HomePage homePage;
    private MyLessons myLessons;
    private List<String> lessonsFromCollection;
    private List<String> lessonsIDs;
    private Set<String> lessonsFromMonthView;
    private String collectionID;
    private DatabaseReader databasereader;
    private List<String> dbResponse;


    @DataProvider
    public Object[][] getUsers() {
        Object[][] data = new Object[][]{
                {"ukda20687.alexnoclass", "ukda20687.alexnoclass", "KidBiz3000", ""},
                {"usda20687.alexnoclass", "usda20687.alexnoclass", "KidBiz3000", ""}
        };
        return data;
    }

    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"My Lessons", "Incognita", "All"}, invocationCount = 1)
    public void check_US20687(@Optional() String login, @Optional() String password, @Optional() String program, @Optional() String selectedClass) {

        loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
        loginPage.afterLoginCheck(selectedClass);

        homePage = new HomePage(driver);
        homePage.changeGradeTo(6);
        homePage.goToMyLessonsPage();

        myLessons = new MyLessons(driver);
        myLessons.deleteAllCollections();

        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickImportCollectionButton();

        String collectionName = "Workforce Readiness";
        myLessons.importCollectionByName(collectionName);

        LocalDate today = LocalDate.now();
        String dateOfToday = today.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

        String dateOfTodaySpletter[] = dateOfToday.split("-");
        String dayOfToday = dateOfTodaySpletter[2];
        String monthOfToday = dateOfTodaySpletter[1];
        String yearOfToday = dateOfTodaySpletter[0];

        LocalDate nextDate = today.plusDays(24);
        String nextDateFull = nextDate.format(DateTimeFormatter.ofPattern("uuuu-MMMM-d", Locale.US));

        String nextDateSplitter[] = nextDateFull.split("-");
        String dayOfNextDay = nextDateSplitter[2];
        String monthOfNextDate = nextDateSplitter[1];
        String yearOfNextDate = nextDateSplitter[0];

        myLessons.scrollDown();

        myLessons.setDateOnCalendarBy(yearOfToday, monthOfToday, dayOfToday, myLessons.getCollectionStartDateInputBy());
        myLessons.setDateOnCalendarBy(yearOfNextDate, monthOfNextDate, dayOfNextDay, myLessons.getCollectionEndDateInputBy());

        myLessons.enterTextInAssignNewLessonEveryInput("1");
        myLessons.checkEveryDaysOfWeek();
        myLessons.enterTextInKeepLessonsLiveForInput("10");


        lessonsFromCollection = myLessons.getCollectionLessonsTitles().stream().sorted().collect(Collectors.toList());
        lessonsIDs = myLessons.getIDsOfColectionLessons();

        Map<String, String> lessonWithIDs = IntStream.range(0, lessonsIDs.size()).boxed()
                .collect(Collectors.toMap(i -> lessonsIDs.get(i), i -> lessonsFromCollection.get(i)));


        myLessons.clickOnSaveAndAssignButton();
        myLessons.clickContinueButtonOnPopUpAssignCollection();
        myLessons.clickOkButtonOnPopUpChangesSaved();

        collectionID = myLessons.getFirstCollectionID();


        myLessons.goToMonthView();
        
        
        myLessons.selectInCollectionDDLByNameContains(collectionName);       


        lessonsFromMonthView = myLessons.getNamesOfLessonsFromMonthView();

        if(!monthOfToday.equalsIgnoreCase(monthOfNextDate) | Integer.parseInt(dayOfToday) < 5){
            myLessons.clickOnNextArrow();

            lessonsFromMonthView.addAll(myLessons.getNamesOfLessonsFromMonthView());
        }

        softAssert.assertEquals(lessonWithIDs.values().stream().collect(Collectors.toSet()), lessonsFromMonthView, "The lessons are different between creation collection and month view.");

        databasereader = new DatabaseReader(browserUrl);
        String query = "select * from lesson_collection_lessons where collection_id=" + collectionID;
        dbResponse = databasereader.queryArray(query, "category_id");

        HashSet<String> expDbResponse = new HashSet<>();

        if(login.contains("uk")) {
            expDbResponse.add("90");
        } else {
            expDbResponse.add("89");
        }

        softAssert.assertEquals(expDbResponse, dbResponse.stream().collect(Collectors.toSet()), "The category_ID of the lessons are wrong in the DB.");

        softAssert.assertAll();
    }
}
