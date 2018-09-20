package com.a3k.testCases.incognita.part3;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.Search;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;

public class US10022 extends BasicTestCase {

    private HomePage homePage;
    private Search search;
	
	private List<String> actResourceTypesItems;
	private List<String> expResourceTypesItems;
	

	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] {
			{"usteach10022.alex", "usteach10022.alex","", ""},
			{"ussa10022.alex", "ussa10022.alex","KidBiz3000", "Auto Kidbiz 3g"},
			{"usda10022.alex", "usda10022.alex", "KidBiz3000", ""},
			{"kb.ref", "kb.ref", "KidBiz3000", ""}
		};
		return data;
	}
	

	@Parameters({"login", "password", "program", "selectedClass"})
	@Test(dataProvider = "getUsers", groups = {"Advanced search options", "Incognita", "All"})
	public void check_US10022(@Optional String login,
							  @Optional String password,
							  @Optional String program,
							  @Optional String selectedClass) {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
		loginPage.afterLoginCheckWithProgram(program);
		loginPage.afterLoginCheck(selectedClass);

	    homePage = new HomePage(driver);
	    search = new Search(driver);

	    sleep(1500);
	    homePage.closeWalkmeNew(3);
	    verifyEnglish(login);
	
	    softAssert.assertAll();
	}


	@Step
	private void verifyEnglish(String login) {
		homePage.clickOnSearchButton();
		homePage.search("");

		search.openAdvancedOptions();

		actResourceTypesItems = search.getItemsOfAllResourceTypeDDL();
		expResourceTypesItems = Arrays.asList("All Resource Types" , "Lessons", "Teacher Resources");
		softAssert.assertEquals(actResourceTypesItems, expResourceTypesItems, "The items of 'All Resource Types' are wrong.");


		search.selectItemFromAllResourcesDDL("Teacher Resources");
		softAssert.assertFalse(search.isAllGradesActive(), "The 'All Grades' option item is active but shouldn't be.");

		if(!login.equals("kb.ref")){
			softAssert.assertFalse(search.isAllCoursesActive(), "The 'All Courses' option item is active but shouldn't be.");
		}else{
			softAssert.assertFalse(search.isSearchByDateActive(), "The 'Search by Date' option item is active but shouldn't be.");
		}

		softAssert.assertFalse(search.isAllTopicsActive(), "The 'All Topics' option item is active but shouldn't be.");
		softAssert.assertFalse(search.isAllContentActive(), "The 'All Content' option item is active but shouldn't be.");

		softAssert.assertFalse(search.isAllLessonTypesActive(), "The 'All Lesson Types' option item is active but shouldn't be.");
		softAssert.assertFalse(search.isAllStandardslActive() , "The 'All Standards' option item is active but shouldn't be.");

		search.clickSearchNew();

		softAssert.assertTrue(search.isTitleOfFirstItemPresent() , "The title of first item is absent.");
		softAssert.assertTrue(search.isDescriptionOfFirstItemPresent() , "The description of first item is absent.");
	}
}
