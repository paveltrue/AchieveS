package com.a3k.testCases.incognita.part3;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.PreReportPage;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class US13715_AND_US13716 extends BasicTestCase {

	
	private LoginPage loginPage;
	private HomePage homePage;
	private boolean result;
	private PreReportPage preReportPage;
	private String actReportTitle;
	private String expReportTitle;

	@Parameters({"login", "password", "program", "selectedClass"})
	@Test(groups = {"Key Insights", "Data Panel", "Archived", "Incognita", "All"})
	public void check_US13715_AND_US13716(@Optional("usteach13715.alexdemo") String login,
										  @Optional("usteach13715.alexdemo") String password,
										  @Optional("") String program,
										  @Optional("") String classToSelect){
		
		loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, classToSelect);
		loginPage.afterLoginCheck(classToSelect);
		loginPage.afterLoginCheckWithProgram(program);
		
		homePage = new HomePage(driver);
		homePage.refreshPage();
		homePage.closeWalkmeNew(5);

		homePage.clickOnMetricCompletingTheMostActivities();
		
		result = homePage.isTogglesDisplayed();
		softAssert.assertFalse(result, "The language toggles are displayed on 'Most Activities' tab on Key Insight data panel.");
		
		homePage.clickOnMoreButton();
		
		preReportPage = new PreReportPage(driver);
		
		actReportTitle = preReportPage.getReportTitleText().replace("\n", " ");
		expReportTitle = "How are my students progressing towards Achieve3000's 40-activity usage goal?";
		softAssert.assertEquals(actReportTitle, expReportTitle, "The title of pre-report page is wrong.");
		
		
		result = preReportPage.isWhichLanguageDDLDisplayed();
		softAssert.assertFalse(result, "The 'Which Language DDL' is displayed on '40-activity usage goal' pre report page.");

		softAssert.assertAll();
	}
	
	@Parameters({"login", "password", "program", "selectedClass"})
	@Test(groups = {"Key Insights", "Data Panel", "Archived", "Incognita", "All"})
	public void checkUK(@Optional("ukteach13715.alexdemo") String login,
						@Optional("ukteach13715.alexdemo") String password,
						@Optional("TeenBiz3000") String program,
						@Optional("Uk Auto Qa Demo Alex 6") String selectedClass){
		
		loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
		loginPage.afterLoginCheck(selectedClass);
		loginPage.afterLoginCheckWithProgram(program);
		
		homePage = new HomePage(driver);
		homePage.refreshPage();
		homePage.closeWalkmeNew(5);
		homePage.clickOnMetricCompletingTheMostActivities();
		
		
		result = homePage.isTogglesDisplayed();
		softAssert.assertFalse(result, "The language toggles are displayed on 'Most Activities' tab on Key Insight data panel UK.");
		
		
		homePage.clickOnMoreButton();		
		preReportPage = new PreReportPage(driver);
		
		actReportTitle = preReportPage.getReportTitleText().replace("\n", " ");
		expReportTitle = "How are my students progressing towards Achieve3000's 40-activity usage goal?";
		softAssert.assertEquals(actReportTitle, expReportTitle, "The title of pre-report page is wrong UK.");
		
		
		result = preReportPage.isWhichLanguageDDLDisplayed();
        softAssert.assertFalse(result, "The 'Which Language DDL' is displayed on '40-activity usage goal' pre report page UK.");
		
		softAssert.assertAll();
	}		
}
