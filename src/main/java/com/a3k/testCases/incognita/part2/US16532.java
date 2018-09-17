package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class US16532 extends BasicTestCase {

    private HomePage homePage;
    private MyLessons myLessons;
    private LoginPage loginPage;

    private static int popupCount = 0;

	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] { 
			{ "ussa16532.alex", "ussa16532.alex", "KidBiz3000", "" },
			{ "usda16532.alex", "usda16532.alex", "KidBiz3000", "" },
			{ "uksa16532.alex", "uksa16532.alex", "KidBiz3000", "" },
			{ "ukda16532.alex", "ukda16532.alex", "KidBiz3000", "" },
			{ "kb.ref", "kb.ref", "KidBiz3000", "" }
			};
		return data;
	}

    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"Build Lesson Collections", "Incognita", "All"})
    public void check_US16532_Day(@Optional() String login,
                                  @Optional() String password,
                                  @Optional() String program,
                                  @Optional() String selectedClass) {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        myLessons = new MyLessons(driver);

        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
        homePage.goToMyLessonsPage();
     
        myLessons.goToDayView();        
        myLessons.clickOnCollectionDDL();

        //softAssert.assertTrue(myLessons.isEditCollectionDatesPopUpExist(), "The edit collection dates Pop up is absent.");

        if (myLessons.isEditCollectionDatesPopUpExist()){
            popupCount++;
        }

        softAssert.assertEquals(1, popupCount);

        myLessons.clickOkOnEditCollectionDatesPopUp();        
        myLessons.goToWeekView();
        
        myLessons.clickOnCollectionDDL();
        softAssert.assertFalse(myLessons.isEditCollectionDatesPopUpExist(),
                "The edit collection dates Pop up is absent.");

        softAssert.assertAll();
    }

    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"Build Lesson Collections", "Incognita", "All"})
    public void check_US16532_Week(@Optional() String login,
                                   @Optional() String password,
                                   @Optional() String program,
                                   @Optional() String selectedClass) {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        myLessons = new MyLessons(driver);

        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
        loginPage.afterLoginCheck(selectedClass);
        homePage.goToMyLessonsPage();
        
        myLessons.goToWeekView();        
        myLessons.clickOnCollectionDDL();

        myLessons.waitUntilPageLoaded();

       // softAssert.assertTrue(myLessons.isEditCollectionDatesPopUpExist(), "The edit collection dates Pop up is absent.");

        if (myLessons.isEditCollectionDatesPopUpExist()){
            popupCount++;
        }

        softAssert.assertEquals(1, popupCount);

        myLessons.clickOkOnEditCollectionDatesPopUp();        
        myLessons.goToMonthView();
        
        myLessons.clickOnCollectionDDL();
        softAssert.assertTrue(myLessons.isEditCollectionDatesPopUpAbsent(),
                "The edit collection dates Pop up is not absent.");

        softAssert.assertAll();
    }
    
    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"Build Lesson Collections", "Incognita", "All"})
    public void check_US16532_Month(@Optional() String login,
                                    @Optional() String password,
                                    @Optional() String program,
                                    @Optional() String selectedClass) {

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        myLessons = new MyLessons(driver);

        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
        loginPage.afterLoginCheck(selectedClass);


        homePage.goToMyLessonsPage();
        
        myLessons.goToMonthView();        
        myLessons.clickOnCollectionDDL();
        
//        softAssert.assertTrue(myLessons.isEditCollectionDatesPopUpExist(), "The edit collection dates Pop up is absent.");
        if (myLessons.isEditCollectionDatesPopUpExist()){
            popupCount++;
        }

        softAssert.assertEquals(1, popupCount);
        myLessons.clickOkOnEditCollectionDatesPopUp();        
        myLessons.goToWeekView();
        
        myLessons.clickOnCollectionDDL();        
        softAssert.assertFalse(myLessons.isEditCollectionDatesPopUpExist(), "The edit collection dates Pop up is absent.");       


        softAssert.assertAll();
    }
}