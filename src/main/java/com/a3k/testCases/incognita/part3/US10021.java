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

import static com.codeborne.selenide.Selenide.sleep;

public class US10021 extends BasicTestCase {

    private HomePage homePage;
    private Search search;
	private String expNoResultMessage;


	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] {
			{"usteach10021.alex", "usteach10021.alex","", ""},
			{"ussa10021.alex", "ussa10021.alex","KidBiz3000", "Auto Kidbiz 3g"},
			{"usda10021.alex", "usda10021.alex", "KidBiz3000", ""},
			{"kb.ref", "kb.ref", "KidBiz3000", ""},
			{"ukteach10021.alex", "ukteach10021.alex", "", ""}
		};
		return data;
	}
	

	@Parameters({"login", "password", "program", "selectedClass"})
	@Test(dataProvider = "getUsers", groups = {"Search Results page", "Incognita", "All"})
	public void check_US10021(@Optional String login,
							  @Optional String password,
							  @Optional String program,
							  @Optional String selectedClass) {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
		loginPage.afterLoginCheck(selectedClass);
		loginPage.afterLoginCheckWithProgram(program);
				
	    homePage = new HomePage(driver);
	    search = new Search(driver);
	    sleep(1500);
	    loginPage.closeWalkmeNew(5);
		verifyTextInputQwerty();	    
		verifyEmptyMessageEnter();		
		
		verifyEnglish();		
	    verifyOnlyLessons();	
	    
	    verifyOnlyTeacherResources();	    

		softAssert.assertAll();
	}

	@Step
	private void verifyOnlyTeacherResources() {		
		homePage.clickOnSearchButton();
		homePage.search("Rubric writing genre specific summary");		
				
		softAssert.assertFalse(search.isAdvancedOptionsExpand(), "The 'Advanced Options' section is expanded. ONLY TEACHER RESOURCES");		
		softAssert.assertTrue(search.isLessonsTabActive(), "The 'Lessons' tab is not highlighted. ONLY TEACHER RESOURCES");
				
		softAssert.assertFalse(search.isTeacherResourcesTabActive(), "The 'Teacher Resources' tab is highlighted. ONLY TEACHER RESOURCES");		
	    expNoResultMessage = "Your search did not produce any results.";
	    softAssert.assertEquals(search.getTextOfNoResultsMessageLessons(), expNoResultMessage, "The text of no results message is incorect. ONLY LESSONS");
	    
	    		
	    search.clickOnTeacherResources();
	    
	    
	    softAssert.assertFalse(search.isAdvancedOptionsExpand(), "The 'Advanced Options' section is expanded. ONLY TEACHER RESOURCES");	    
	    softAssert.assertFalse(search.isLessonsTabActive(), "The 'Lessons' tab is highlighted. ONLY TEACHER RESOURCES");
		
	    softAssert.assertTrue(search.isTeacherResourcesTabActive(), "The 'Teacher Resources' tab is not highlighted. ONLY TEACHER RESOURCES");	    
	    softAssert.assertTrue(search. isTeacherResourcesResultsDisplayed(), "The results table of 'Teacher Resources'  is not displayed. ONLY TEACHER RESOURCES");
	}

	@Step
	private void verifyOnlyLessonsSpanish() {		
		homePage.clickOnSearchButton();		
		homePage.search("technology");
		
		
		softAssert.assertFalse(search.isAdvancedOptionsExpand(), "The 'Advanced Options' section is expanded. ONLY LESSONS SPANISH");		
		softAssert.assertTrue(search.isLessonsTabActive(), "The 'Lessons' tab is not highlighted. ONLY LESSONS SPANISH");
		
		softAssert.assertTrue(search.isLessonsDisplayed(), "The Lessons content is displayed. ONLY LESSONS SPANISH");		
		softAssert.assertFalse(search.isTeacherResourcesTabActive(), "The 'Teacher Resources' tab is highlighted. ONLY LESSONS SPANISH");
	    
		
	    search.clickOnTeacherResources();
	    
	    
	    softAssert.assertFalse(search.isLessonsTabActive(), "The 'Lessons' tab is highlighted. ONLY LESSONS SPANISH");		
	    softAssert.assertTrue(search.isTeacherResourcesTabActive(), "The 'Teacher Resources' tab is not highlighted. ONLY LESSONS SPANISH");	    
	   	   
	    expNoResultMessage = "No hay resultados para su búsqueda.";
	    softAssert.assertEquals(search.getTextOfNoResultsMessageTeacherResources(), expNoResultMessage, "The text of no results message is incorect. ONLY LESSONS SPANISH");
	}

	@Step
	private void verifyOnlyLessons() {		
		homePage.clickOnSearchButton();			
		homePage.search("technology");
		
		
		softAssert.assertFalse(search.isAdvancedOptionsExpand(), "The 'Advanced Options' section is expanded. ONLY LESSONS");		
		softAssert.assertTrue(search.isLessonsTabActive(), "The 'Lessons' tab is not highlighted. ONLY LESSONS");
		
		softAssert.assertTrue(search.isLessonsDisplayed(), "The Lessons content is displayed. ONLY LESSONS");		
		softAssert.assertFalse(search.isTeacherResourcesTabActive(), "The 'Teacher Resources' tab is highlighted. ONLY LESSONS");
	    
		
	    search.clickOnTeacherResources();
	    
	    
	    softAssert.assertFalse(search.isLessonsTabActive(), "The 'Lessons' tab is highlighted. ONLY LESSONS");		
	    softAssert.assertTrue(search.isTeacherResourcesTabActive(), "The 'Teacher Resources' tab is not highlighted. ONLY LESSONS");	    
	    	   
	    expNoResultMessage = "Your search did not produce any results.";
	    softAssert.assertEquals(search.getTextOfNoResultsMessageTeacherResources(), expNoResultMessage, "The text of no results message is incorect. ONLY LESSONS");
	}

	@Step
	private void verifyTextInputQwertySpanish() {		
		homePage.clickOnSearchButton();		
		homePage.search("QWERTYUIO");
		
		
		softAssert.assertFalse(search.isAdvancedOptionsExpand(), "The 'Advanced Options' section is expanded. SPANISH ENTER QWERTY");		
		softAssert.assertFalse(search.isSearchResultsAreDisplayed(), "The Search Results is displayed when no message entered. SPANISH ENTER QWERTY");
		
		expNoResultMessage = "No hay resultados para su búsqueda.  Para obtener mejores resultados: Limite el número de filtros que aplique a su búsqueda. "
						   + "Revise que la palabra clave esté escrita correctamente. Procure no usar términos muy específicos. Si usa términos más generales,"
						   + " obtendrá artículos similares y relacionados con el tema.";		
		softAssert.assertEquals(search.getTextOfNoResultsMessage(), expNoResultMessage, "The text of no results message is incorect. SPANISH ENTER QWERTY");
	}

	@Step
	private void verifyTextInputQwerty() {		
		homePage.clickOnSearchButton();
		homePage.closeWalkmeNew(2);
		homePage.search("QWERTYUIO");			
		
		softAssert.assertFalse(search.isAdvancedOptionsExpand(), "The 'Advanced Options' section is expanded. ENTER QWERTY");		
		softAssert.assertFalse(search.isSearchResultsAreDisplayed(), "The Search Results is displayed when no message entered. ENTER QWERTY");
		
		expNoResultMessage = "Your search did not produce any results.  To improve your search results: Limit the number of filters you apply to your search. "
								  + "Double-check the spelling of keywords. Be less specific in your terminology. Using a more general term(s) is likely to lead you to similar and related articles.";		
		softAssert.assertEquals(search.getTextOfNoResultsMessage(), expNoResultMessage, "The text of no results message is incorect. ENTER QWERTY");
	}

	@Step
	private void verifyEmptyMessageEnter() {		
		homePage.clickOnSearchButton();		
		homePage.search("");	
		
		
		softAssert.assertFalse(search.isAdvancedOptionsExpand(), "The 'Advanced Options' section is expanded. EMPTY MESSAGE");		
		softAssert.assertFalse(search.isSearchResultsAreDisplayed(), "The Search Results is displayed when no message entered. EMPTY MESSAGE");
	}

	@Step
	private void verifySpanish() {		
		search.changeLangToSpanish();		
		homePage.clickOnSearchButton();		
		homePage.search("lección");
		
		
		softAssert.assertTrue(search.isSearchResultsLabelDisplayed(), "The 'Search Results' label is not displayed. SPANISH");		
		softAssert.assertTrue(search.isLessonsTabDisplayed(), "The 'Lessons' tab is not displayed. SPANISH");
		
		softAssert.assertTrue(search.isLessonsTabActive(), "The 'Lessons' tab is not highlighted. SPANISH");		
		softAssert.assertTrue(search.isTeacherResourcesTabDisplayed(), "The 'Teacher Resources' tab is not displayed. SPANISH");
			
		
		softAssert.assertEquals(search.getTextOfTopicColumnHeader(),"Tema", "The text of header 'Topic' column on results table is wrong. SPANISH");
		softAssert.assertEquals(search.countOfTopPaginationControls(), 10, "The count of top pagination controls is wrong. SPANISH");
		
		softAssert.assertEquals(search.countOfBottomPaginationControls(), 10, "The count of bottom pagination controls is wrong. SPANISH");
		softAssert.assertTrue(search.isFirstPageButtonHiglighted(), "The 1-st page button is not highlighted. SPANISH");
			
		
		search.clickOnElementInPaginationControls(8);
		
		softAssert.assertTrue(search.isPageButtonHiglightedBy("8"), "The 8-st page button is not highlighted. SPANISH");		
		softAssert.assertEquals(search.countOfBottomPaginationControls(), 12, "The count of bottom pagination controls is wrong after click by 8 button. SPANISH");
		
		
		search.clickOnTeacherResources();		
		softAssert.assertTrue(search.isTeacherResourcesTabActive(), "The 'Teacher Resources' tab is not highlighted. SPANISH");
	}

	@Step
	private void verifyEnglish() {		
		homePage.clickOnSearchButton();		
		homePage.search("test");		
		
		
		softAssert.assertTrue(search.isSearchResultsLabelDisplayed(), "The 'Search Results' label is not displayed.");		
		softAssert.assertTrue(search.isLessonsTabDisplayed(), "The 'Lessons' tab is not displayed.");
		
		softAssert.assertTrue(search.isLessonsTabActive(), "The 'Lessons' tab is not highlighted.");		
		softAssert.assertTrue(search.isTeacherResourcesTabDisplayed(), "The 'Teacher Resources' tab is not displayed.");		
		
		
		softAssert.assertEquals(search.getTextOflessonColumnHeader(),"Lesson", "The text of header 'Lesson' column on results table is wrong.");		
		softAssert.assertEquals(search.getTextOfTopicColumnHeader(),"Topic", "The text of header 'Topic' column on results table is wrong.");
					
		
		softAssert.assertEquals(search.countOfTopPaginationControls(), 10, "The count of top pagination controls is wrong.");		
		softAssert.assertEquals(search.countOfBottomPaginationControls(), 10, "The count of bottom pagination controls is wrong.");		
		softAssert.assertTrue(search.isFirstPageButtonHiglighted(), "The 1-st page button is not highlighted.");
			
		
		search.clickOnElementInPaginationControls(8);		
		
		softAssert.assertTrue(search.isPageButtonHiglightedBy("8"), "The 8-st page button is not highlighted.");		
		softAssert.assertEquals(search.countOfBottomPaginationControls(), 12, "The count of bottom pagination controls is wrong after click by 8 button.");
		
		
		search.clickOnTeacherResources();		
		
		softAssert.assertEquals(search.getTextOfResourceColumnHeader(),"Resource", "The text of header 'Resource' column on results table is wrong.");		
		softAssert.assertTrue(search.isTeacherResourcesTabActive(), "The 'Teacher Resources' tab is not highlighted.");
	}
}