package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class US13813 extends BasicTestCase {


    private LoginPage loginPage;
    private HomePage homePage;
	private FavoritesPage favoritesPage;
	private MyLessons myLessons;
	private SearchWidgetPage searchWidgetPage;
	private boolean result;
	private String expTitleOfLesson;
	private Lesson lesson;
	private String expNameOfLesson;
	private String actNameOfLesson;
		
	

	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] {
			{"usteach13813.alex", "usteach13813.alex","", ""}
		};
		return data;
	}	
	

	//TODO: REFACTOR THIS WTF ASAP
	@Parameters({"login", "password", "program", "selectedClass"})
	@Test(dataProvider="getUsers", groups = {"Key Insights", "Data Panel","Inognita", "Archived", "All"}, invocationCount = 1)
	public void check_US13813(@Optional String login,
							  @Optional String password,
							  @Optional String program,
							  @Optional String selectedClass){
				
		loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program,  selectedClass);
				
		homePage = new HomePage(driver);
		homePage.goToFavoritePage();		
		
		favoritesPage = new FavoritesPage(driver);
		favoritesPage.unchekedAllLessonOnFavorites();		
		
		favoritesPage.goToHomePage();		
		homePage.goToMyLessonsPage();
		
		myLessons = new MyLessons(driver);
		
		if(login.contains("sa")){
			myLessons.selectFirstClass();		
		}		
				
		while(!myLessons.isDisplayedBy(By.xpath(".//div[@id = 'moreLessonsContainer']//div[contains(@class, 'ml_view')]"))){			
			myLessons.openSearchWidgetPanel();	
		}
		
		myLessons.clickOnAdvancedOptions();
		myLessons.chooseTypeAtSearchForMoreLesson("Strategic Lessons");
		myLessons.clickSearch();
		
		
		searchWidgetPage = new SearchWidgetPage(driver);
		searchWidgetPage.clickFirstLessonOnSearchTab();		
		
		myLessons.markLessonFavorite();		
		expTitleOfLesson = myLessons.getTextOfTitleFromLessonInfoPopUp();
		
		result = myLessons.isCurrentLessonFavorite();
		softAssert.assertTrue(result, "The lesson unmarked favorites icon after click him on favorite tab.");
		
		myLessons.closeInfoPopUp();		
		myLessons.goToFavoritePage();
		
		List<String> actFavoriteLessons = favoritesPage.getTextOfTitleFavoriteLessons();
		softAssert.assertTrue(actFavoriteLessons.contains(expTitleOfLesson), "The lesson which was marked as Favorite is not present on favorites page.");
				
		favoritesPage.clickOnFirstLessonOnFavoriteSPage();		
		lesson = new Lesson(driver);
		
		result = lesson.isTheLessonFavorite();
		softAssert.assertTrue(result, "The lesson is not marked as Favorite after it was opened.");
		
		lesson.goToArticleTab();		
		lesson.waitUntilAppearsVocabularyList();
		
		result = lesson.isTheLessonFavorite();
		softAssert.assertTrue(result, "The lesson is not marked as Favorite after click on Article Tab.");
		
		lesson.goToActivityTab();
		
		result = lesson.isTheLessonFavorite();
		softAssert.assertTrue(result, "The lesson is not marked as Favorite after click on Activity Tab.");
		
		lesson.goToAfterReadingPollSection();
		
		result = lesson.isTheLessonFavorite();
		softAssert.assertTrue(result, "The lesson is not marked as Favorite after click on After Reading Pool Section Tab.");
		
		lesson.goToThoughtQuestionTab();
		
		result = lesson.isTheLessonFavorite();
		softAssert.assertTrue(result, "The lesson is not marked as Favorite after click on Thought Question Tab.");
		
		lesson.goToPollResultsTab();
		
		result = lesson.isTheLessonFavorite();
		softAssert.assertTrue(result, "The lesson is not marked as Favorite after click on Poll Results Tab.");
		
		lesson.goToMathTab();
		
		result = lesson.isTheLessonFavorite();
		softAssert.assertTrue(result, "The lesson is not marked as Favorite after click on Math Tab.");
		
		lesson.goToStretchArticleTab();	
		
		result = lesson.isTheLessonFavorite();
		softAssert.assertTrue(result, "The lesson is not marked as Favorite after click on Stretch Article Tab.");
		
		lesson.goToStretchActivityTab();
		
		result = lesson.isTheLessonFavorite();
		softAssert.assertTrue(result, "The lesson is not marked as Favorite after click on Stretch Activity Tab.");
		
		
		lesson.goToHomePage();		
		homePage.goToMyLessonsPage();
		
		while(!myLessons.isDisplayedBy(By.xpath(".//div[@id = 'moreLessonsContainer']//div[contains(@class, 'ml_view')]"))){			
			myLessons.openSearchWidgetPanel();	
		}
		
		searchWidgetPage.enterTextInSearchBar(expTitleOfLesson);
		
		myLessons.clickSearch();		
		myLessons.unmarkAllLessons();		
		
		myLessons.closeInfoPopUp();		
		myLessons.goToFavoritePage();
		
		result = favoritesPage.getTextOfEmptyPageMessage().isEmpty();
		softAssert.assertFalse(result, "The lesson still placed on favorites page.");

//
//		// ========================================================= DAY VIEW ========================================================================
//
//		myLessons.goToDayView();
//		myLessons.getLessonOnDayView(0).click();
//
//		result = myLessons.isCurrentLessonFavorite();
//		softAssert.assertFalse(result, "The lesson is marked as Favorite but it shouldn't be like this.");
//
//		myLessons.markLessonFavorite();
//
// 		result = myLessons.isCurrentLessonFavorite();
//		softAssert.assertTrue(result, "The lesson is not marked as Favorite after click on favorite icon.");
//
//		expNameOfLesson = myLessons.getLessonNameInPopup();
//
//		myLessons.closeInfoPopUp();
//		myLessons.goToFavoritePage();
//
//		actNameOfLesson = favoritesPage.getTitleOfFirstLesson();
//		softAssert.assertEquals(actNameOfLesson, expNameOfLesson, "The lesson title from Favorite page is not the same with lesson title from MyLessons page DAY VIEW.");
//
//		favoritesPage.back();
//		myLessons.clickOnLessonOnDayViewByName(expNameOfLesson);
//
//		myLessons.unmarkLessonFavorite();
//		result = myLessons.isCurrentLessonFavorite();
//		softAssert.assertFalse(result, "The lesson is  still marked as Favorite after click (unmark) on favorite icon.");
//
//
//		myLessons.closeInfoPopUp();
//		myLessons.goToFavoritePage();
//
//		result = favoritesPage.getTextOfEmptyPageMessage().isEmpty();
//		softAssert.assertFalse(result, "The lesson still placed on favorites page after unmark him.");
//
//		favoritesPage.goToMyLessonsPage();
//
//		//===================================================== WEEK VIEW =======================================================
//
//
//		myLessons.goToWeekView();
//		myLessons.clickOnLessonOnWeekOrMonthViewByName(expNameOfLesson);
//
//		result = myLessons.isCurrentLessonFavorite();
//		softAssert.assertFalse(result, "The lesson on week view is marked as favorite but shouldn't be");
//
//		myLessons.markLessonFavorite();
//
//		result = myLessons.isCurrentLessonFavorite();
//		softAssert.assertTrue(result, "The lesson on week view is not marked as Favorite after click on favorite icon.");
//
//		myLessons.closeInfoPopUp();
//		myLessons.goToFavoritePage();
//
//		actNameOfLesson = favoritesPage.getTitleOfFirstLesson();
//		softAssert.assertEquals(actNameOfLesson, expNameOfLesson, "The lesson title from Favorite page is not the same with lesson title from MyLessons page WEEK VIEW.");
//
//		favoritesPage.back();
//		myLessons.clickOnLessonOnWeekOrMonthViewByName(expNameOfLesson);
//
//		myLessons.unmarkLessonFavorite();
//		result = myLessons.isCurrentLessonFavorite();
//		softAssert.assertFalse(result, "The lesson on week view is  still marked as Favorite after click (unmark) on favorite icon.");
//
//
//		myLessons.closeInfoPopUp();
//		myLessons.goToFavoritePage();
//
//		result = favoritesPage.getTextOfEmptyPageMessage().isEmpty();
//		softAssert.assertFalse(result, "The lesson still placed on favorites page after unmark him on WEEK VIEW My lessons page.");
//
//		favoritesPage.goToMyLessonsPage();
//
//
//		//===================================================== MONTH VIEW =======================================================
//
//
//		myLessons.goToMonthView();
//		myLessons.clickOnLessonOnWeekOrMonthViewByName(expNameOfLesson);
//
//		result = myLessons.isCurrentLessonFavorite();
//		softAssert.assertFalse(result, "The lesson on MONTH VIEW is marked as favorite but shouldn't be");
//
//		myLessons.markLessonFavorite();
//
//		result = myLessons.isCurrentLessonFavorite();
//		softAssert.assertTrue(result, "The lesson on MONTH VIEW is not marked as Favorite after click on favorite icon.");
//
//		myLessons.closeInfoPopUp();
//		myLessons.goToFavoritePage();
//
//		actNameOfLesson = favoritesPage.getTitleOfFirstLesson();
//		softAssert.assertEquals(actNameOfLesson, expNameOfLesson, "The lesson title from Favorite page is not the same with lesson title from MyLessons page MONTH VIEW.");
//
//		favoritesPage.back();
//		myLessons.clickOnLessonOnWeekOrMonthViewByName(expNameOfLesson);
//
//		myLessons.unmarkLessonFavorite();
//		result = myLessons.isCurrentLessonFavorite();
//		softAssert.assertFalse(result, "The lesson on MONTH VIEW is  still marked as Favorite after click (unmark) on favorite icon.");
//
//
//		myLessons.closeInfoPopUp();
//		myLessons.goToFavoritePage();
//
//		result = favoritesPage.getTextOfEmptyPageMessage().isEmpty();
//		softAssert.assertFalse(result, "The lesson still placed on favorites page after unmark him on MONTH VIEW My lessons page.");
//
//
//		favoritesPage.goToMyLessonsPage();
		
		softAssert.assertAll();
	}
}
