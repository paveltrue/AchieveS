package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.PreReportPage;
import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Selenide.sleep;

public class US13352_US13353_US13747 extends BasicTestCase {

    private LoginPage loginPage;
    private HomePage homePage;
	private ElementsCollection toggles;
	private Map<String, String> actTogglesAttributes;	
	private String attributeOfAllToggle;
	private List<String> actOptionsOfToggle;
	private List<String> expOptionsOfToggle;
	private List<String> expSpanishStudentsName;
	private List<String> allStudentsName;
	private List<String> actSpanishStudentsName;
	private List<String> expEnglishStudentsName;
	private String actReportTitle;
	private String expReportTitle;	
	private PreReportPage preReportPage;
	private String expDefaultValue;
	private String actDefaultValue;
	
		
	
	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] {
				{"usteach13443.alexdemo", "usteach13443.alexdemo","", ""},
				{"ukteach13352.alexdemo", "ukteach13352.alexdemo","TeenBiz3000", "Uk Auto Qa Demo Alex 6"}			
				};
		return data;
	}	
	

	@Parameters({"login", "password", "program", "selectedClass"})
	@Test(dataProvider="getUsers", groups = {"Key Insights", "Archived","Incognita", "All"}, invocationCount = 10)
	public void check_US13352_US13353_US13747(@Optional String login, @Optional String password, @Optional String program, @Optional String selectedClass){
				
		loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
		
		homePage = new HomePage(driver);
		homePage.refreshPage();
		homePage.closeWalkmeNew(3);
		
		if(homePage.waitForJSandJQueryToLoad()){
			homePage.closeWalkmeNew(5);
			homePage.clickOnMetricHighestLexileGains();
		}
		
		
		toggles = homePage.getLanguageTogglesOnKeyInsightDataPanel();
		
		sleep(1000);
		actOptionsOfToggle = homePage.getTextFromWebElementsByList(toggles);
		sleep(1000);
		expOptionsOfToggle = Arrays.asList("ENG", "ALL", "SP");
		softAssert.assertEquals(actOptionsOfToggle, expOptionsOfToggle, "The name of toggles is wrong.");		
		
		
	    actTogglesAttributes = homePage.getAttributesFromItemsOfListByMap(toggles, "class");
		sleep(1000);
		attributeOfAllToggle = actTogglesAttributes.get("ALL");
		sleep(1000);
		softAssert.assertTrue(attributeOfAllToggle.contains("pick"), "The ALL toggle is not chosen by default.");
		
		
		if(homePage.waitForJSandJQueryToLoad()){
			allStudentsName = homePage.getTextFromWebElementsByList(homePage.getLabelsBelowBars());
		}				
			
		
		if(homePage.waitForJSandJQueryToLoad()){
			homePage.closeWalkmeNew();
			homePage.getMoreButton().click();
		}	
		
		
		preReportPage  = new PreReportPage(driver);
		
		
		actReportTitle = preReportPage.getReportTitleText();
		expReportTitle = "How has Lexile performance changed over time?";
		softAssert.assertEquals(actReportTitle ,expReportTitle , "The wrong report name.");	

		
		actDefaultValue = preReportPage.getDefaultValueFromDDL(preReportPage.getWhichLanguageDDL());		
		expDefaultValue = "Students' Achieve3000 Language";
		softAssert.assertEquals(actDefaultValue ,expDefaultValue , "The default value of Which Language DLL is wrong.");
		
				
		preReportPage.goToHomePage();		
		homePage.refreshPage();
		homePage.closeWalkmeNew();
		
		if(homePage.waitForJSandJQueryToLoad()){
			homePage.clickOnMetricHighestLexileGains();
		}
		
		toggles = homePage.getLanguageTogglesOnKeyInsightDataPanel();	
				
		
		if(homePage.waitForJSandJQueryToLoad()){
			homePage.getLanguageToggleSP().click();
		}
		sleep(1000);
	    expSpanishStudentsName = homePage.getTextFromWebElementsByList(homePage.getLabelsBelowBars());
		sleep(1000);
	    actSpanishStudentsName = new ArrayList<>();
		
		for(String str : allStudentsName){			
			if(str.contains("SPANISH")){
				sleep(500);
				actSpanishStudentsName.add(str.replace("SPANISH", ""));
				sleep(500);
			}
		}
		sleep(500);
		softAssert.assertEquals(actSpanishStudentsName, expSpanishStudentsName, "The spanish users shows without the word SPANISH on Key Insights data panel");
		
				
		if(homePage.waitForJSandJQueryToLoad()){
			homePage.getLanguageToggleENG().click();
		}	
		
		sleep(1000);
		expEnglishStudentsName = homePage.getTextFromWebElementsByList(homePage.getLabelsBelowBars());
		sleep(1000);

        softAssert.assertFalse(expEnglishStudentsName.containsAll(expSpanishStudentsName),
                "The english list of student include spanish students.");
		
				
		if(homePage.waitForJSandJQueryToLoad()){
			homePage.getMoreButton().click();
		}	
		
		actReportTitle = preReportPage.getReportTitleText();
		expReportTitle = "How has Lexile performance changed over time?";
		softAssert.assertEquals(actReportTitle ,expReportTitle , "The wrong report name when ENG toggle used.");		
	
		
		actDefaultValue = preReportPage.getDefaultValueFromDDL(preReportPage.getWhichLanguageDDL());		
		
		if(!login.equals("ukteach13352.alexdemo")){
			expDefaultValue = "English (US) and Support";
		}else{
			expDefaultValue = "English (UK)";			
		}

        softAssert.assertEquals(actDefaultValue, expDefaultValue,
                "The default value of Which Language DLL is wrong when ENG toggle used.");
							
		preReportPage.goToHomePage();		
	    homePage.refreshPage();
	    homePage.closeWalkmeNew();
		
		if(homePage.waitForJSandJQueryToLoad()){
			homePage.getMetricHighestLexileGains().click();
		}		
						
		toggles = homePage.getLanguageTogglesOnKeyInsightDataPanel();
		
		
		if(homePage.waitForJSandJQueryToLoad()){
			homePage.getLanguageToggleSP().click();
		}		
		
		
		if(homePage.waitForJSandJQueryToLoad()){
			homePage.getMoreButton().click();
		}		
		
		
		actReportTitle = preReportPage.getReportTitleText();
		expReportTitle = "How has Lexile performance changed over time?";
		softAssert.assertEquals(actReportTitle ,expReportTitle , "The wrong report name when SP toggle used.");			
		
		
		sleep(1000);
		actDefaultValue = preReportPage.getDefaultValueFromDDL(preReportPage.getWhichLanguageDDL());
		sleep(1000);
		expDefaultValue = "Spanish";
		softAssert.assertEquals(actDefaultValue ,expDefaultValue , "The default value of Which Language DLL is wrong when SP toggle used.");

        softAssert.assertAll();
		
	}
}
