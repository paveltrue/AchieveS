package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.PreReportPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;

public class US10180 extends BasicTestCase {

	
	private LoginPage loginPage;
	private HomePage homePage;
	private String actTextOfMetricTheMostActivities;
	private String expTextOfMetricTheMostActivities;
	private int actNumberOfStudents;
	private int expNumberOfStudents;
	private String actTextFromMetricCircle;
	private String expTextFromMetricCircle;
	private String actFirstValueOfScale;
	private String expFirstValueOfScale;
	private String colorOfSimpleNumber;
	private String colorOfThresholdNumber;
	private String actTextOfMoreButton;
	private String expTextOfMoreButton;
	private PreReportPage preReportPage;
	private String currentUrl;
	private String actTitleOfPreReportPage;
	private String expTitleOfPreReportPage;
	private boolean result;
	private String actClassValue;
	private String expClassValue;	
	private String actStartMonth;
	private String expStartMonth;
	private String actStartDay;
	private String expStartDay;
	private String textOfDateRangeLabel;
	
	
	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] {				
				{"usteach13443.alexdemo", "usteach13443.alexdemo", "", ""}
				};
		return data;
	}
	

	@Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"Home Page", "Key Insights", "Data Panel", "Archived", "Incognita", "All"}, invocationCount = 1)
    public void check_US10180(@Optional("usteach13443.alexdemo") String login,
                              @Optional("usteach13443.alexdemo") String password,
                              @Optional("") String program,
                              @Optional("Access Class 3g") String selectedClass) {
		
		loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
		
		homePage = new HomePage(driver);
		
	    actTextOfMetricTheMostActivities = homePage.getTextOfMetricCompletingTheMostActivities();
	    expTextOfMetricTheMostActivities = "Students completing the most activities";
        softAssert.assertEquals(actTextOfMetricTheMostActivities, expTextOfMetricTheMostActivities,
                "The title of metric 'Students completing the most activities' is wrong.");

		if(homePage.waitForJSandJQueryToLoad()){
			homePage.clickOnMetricCompletingTheMostActivities();
		}

		sleep(1500);
		//if(homePage.waitForJSandJQueryToLoad()){
			actNumberOfStudents = homePage.getNumberOfStudentsCompletingMostActivities();
		//}
		expNumberOfStudents = 5;
        softAssert.assertEquals(actNumberOfStudents, expNumberOfStudents,
                "The number of students is wrong.");
	    
		actTextFromMetricCircle = homePage.getTextFromMetricCircle().replace("\n", " ");
		expTextFromMetricCircle = "20 Student(s)";
        softAssert.assertEquals(actTextFromMetricCircle, expTextFromMetricCircle,
                "The text from metric circle is wrong.");
		
		
		actFirstValueOfScale = homePage.getFirstNumberOnScaleOfMetricCompletingMostActivities();
		expFirstValueOfScale = "0";
        softAssert.assertEquals(actFirstValueOfScale, expFirstValueOfScale,
                "The first value on scale of Metric Completing Most Activities is wrong. ");

		textOfDateRangeLabel = homePage.getTextOfDateRangeLabel();
        result = textOfDateRangeLabel.contains("Jul 9, 2018");
        softAssert.assertTrue(result, "The date range label is incorrect. " +
                "Actual date: " + textOfDateRangeLabel);

		colorOfSimpleNumber = homePage.getColorOfFirstNumberOnScaleOfMetricCompletingMostActivities();
		colorOfThresholdNumber = homePage.getColorOfThresholdValue();
		if(!(colorOfThresholdNumber.equals("")) & !(colorOfSimpleNumber.equals(""))){
            softAssert.assertNotEquals(colorOfSimpleNumber, colorOfThresholdNumber,
                    "The color of simple number and  on scale");
		} else {
            softAssert.assertTrue(false,
                    "The color of simple number or color of threshold number is/are wrong.");
		}
		
		actTextOfMoreButton = homePage.getTextOfMoreButton();
		expTextOfMoreButton = "More";
        softAssert.assertEquals(actTextOfMoreButton, expTextOfMoreButton,
                "The text of 'More' button is wrong.");

		homePage.clickOnMoreButton();
		
		preReportPage = new PreReportPage(driver);
		
		currentUrl = url();
		result = currentUrl.contains("report_id=17");		
		softAssert.assertTrue(result,"The opened page is wrong.");		
		
		actTitleOfPreReportPage = preReportPage.getReportTitleText().replace("\n", " ");
		expTitleOfPreReportPage = "How are my students progressing towards Achieve3000's 40-activity usage goal?";
		softAssert.assertEquals(actTitleOfPreReportPage, expTitleOfPreReportPage, "The title of prereport page is wrong.");

		actClassValue = preReportPage.getDefaultValueOfWhichClassDDL();
		expClassValue = "Alex Auto Demo Kidbiz 3 Access";
		//expClassValue = selectedClass;
		softAssert.assertEquals(actClassValue, expClassValue, "The default value of which class DDL is wrong.");

		actStartMonth = preReportPage.getDefaultValueOfStartDateMonthDDL();
		expStartMonth = "July";
		softAssert.assertEquals(actStartMonth, expStartMonth, "The default value of start date month DDL is wrong.");
		
		
		actStartDay = preReportPage.getDefaultValueOfStartDateDayDDL();
		expStartDay = "9";
		softAssert.assertEquals(actStartMonth, expStartMonth, "The default value of start date day DDL is wrong.");
		
		preReportPage.goToHomePage();
		
		softAssert.assertAll();		
	}
}


