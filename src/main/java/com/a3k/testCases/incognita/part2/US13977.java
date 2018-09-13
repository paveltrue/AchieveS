package com.a3k.testCases.incognita.part2;


import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class US13977 extends BasicTestCase {


    private LoginPage loginPage;
    private HomePage homePage;
	private Search searchPage;
	private String actPositionValue;
	private String expPositionValue;
	private String urlOfSrc;
	private String actValueOfBackgroundUrl;
	private String expValueOfBackgroundUrl;
	private Lesson lesson;
	private String expTitleOfLesson;
	private FavoritesPage favoritesPage;
	private boolean result;
	
	
	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] {
				{"usteach13977.alex", "usteach13977.alex","", ""},
				{"ussa13977.alex", "ussa13977.alex","KidBiz3000", ""},
				{"usda13977.alex", "usda13977.alex", "KidBiz3000", ""},				
				{"kb.ref", "kb.ref", "KidBiz3000", ""}				
				};
		return data;
	}
	
	@Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"Favorites Page", "Favorites", "Search", "Archived", "Incognita", "All"})
	public void check_US13977(@Optional String login, @Optional String password, @Optional String program, @Optional String selectedClass){
		
		loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
		
		homePage = new HomePage(driver);
		homePage.goToFavoritePage();		
		
		favoritesPage = new FavoritesPage(driver);		
		favoritesPage.unchekedAllLessonOnFavorites();
		
		favoritesPage.goToHomePage();		
		
		homePage.clickOnSearchIcon();		
		homePage.search("lesson");	
		
		searchPage = new Search(driver);		
		
		actPositionValue = searchPage.getValueOfPositionFromFirstLessonInList();
		expPositionValue = "right";		
		softAssert.assertEquals(actPositionValue, expPositionValue, "The position of favorite icon is wrong.");	

		
		if(searchPage.getCssValueFromFirstFavoriteIcon("background-image").contains("filled")){
			searchPage.clickOnFirstFavoriteIcon();			
		}	
		
		
		urlOfSrc = searchPage.getFavoritesIconsInTable().get(0).getCssValue("background-image");		
		actValueOfBackgroundUrl = replacerFavoriteIconUrl(urlOfSrc);
	    expValueOfBackgroundUrl = "whiteBg_heart";
		softAssert.assertEquals(actValueOfBackgroundUrl, expValueOfBackgroundUrl, "The value of url of background is wrong.");			
		
		
		searchPage.clickOnFirstFavoriteIcon();		
		
		
		urlOfSrc = searchPage.getCssValueUntilAttributeContains(searchPage.getFavoritesIconsInTable().get(0), "background-image", "filled");		
		actValueOfBackgroundUrl = replacerFavoriteIconUrl(urlOfSrc);
	    expValueOfBackgroundUrl = "whiteBg_heart_filled";
		softAssert.assertEquals(actValueOfBackgroundUrl, expValueOfBackgroundUrl, "The value of url of background is wrong after click.");

		searchPage.getFavoritesIconsInTable().get(0).$(By.xpath("./following-sibling::div[@class = 'summary']/a")).click();
						
		lesson = new Lesson(driver);

		if(lesson.isElementExist(lesson.getCloseLessonPopUp()) & lesson.isElementVisible(lesson.getCloseLessonPopUp())){
			lesson.getCloseLessonPopUp().click();
		}		
		
		urlOfSrc = lesson.getFavoriteIconNearTitleOfLesson().getCssValue("background-image");
		actValueOfBackgroundUrl = replacerFavoriteIconUrl(urlOfSrc);
		expValueOfBackgroundUrl = "coloredBg_heart_filled";
		softAssert.assertEquals(actValueOfBackgroundUrl, expValueOfBackgroundUrl, "The value of url of background of favorite icon is wrong on lesson page.");

        expTitleOfLesson = lesson.getTitleOfLesson().getText().trim();

		searchPage.getFavoritesIcon().click();
		
		favoritesPage = new FavoritesPage(driver);

        favoritesPage.logOut();
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
        homePage.goToFavoritePage();
		result = favoritesPage.getTitleOfFavoriteLessonsByList().contains(expTitleOfLesson);		
		softAssert.assertTrue(result, "The required lesson is absent on favorite page.");		
		
		
		favoritesPage.clickFavoriteIconOnRequiredLessonByTitle(expTitleOfLesson);		
		sleep(1000);
		
		result = favoritesPage.checkPresenceOfLessonOnFavoritesPage(expTitleOfLesson);
		sleep(1000);
		softAssert.assertTrue(result, "The required lesson still is present on favorite page after unchecked favorite icon.");

		softAssert.assertAll();	
	
	}	
	
	public String replacerFavoriteIconUrl(String url){
		String result = url.substring(url.lastIndexOf("/")+1,url.lastIndexOf(".")); 		
		return result;
	}	
}
