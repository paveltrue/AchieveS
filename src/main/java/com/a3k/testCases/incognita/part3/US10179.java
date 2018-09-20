package com.a3k.testCases.incognita.part3;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.PreReportPage;
import com.a3k.pages.ReportPage;
import com.a3k.utils.db.DatabaseReader;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static com.codeborne.selenide.WebDriverRunner.url;

public class US10179 extends BasicTestCase {
	
	private LoginPage loginPage;
	private HomePage homePage;
	private String actThirdMetricText;
	private String expThirdMetricText;
	private LocalDate today;
	private LocalDate firstDayOfLastMonthPeriod;
	private LocalDate lastDayOfLastMonthPeriod;
	private String startOfPeriodDate;
	private String endOfPeriodDate;
	private String dateRangeLabel;
	private String actTextOfDateRangeLabel;
	private String expTextOfDateRangeLabel;
	private String dateRange;
	private String actDateRangeValue;
	private String expDateRangeValue;
	private String actTextOfMoreButton;
	private String expTextOfMoreButton;
	private String[] startOfPeriodDateSplitter;
	private String[] endOfPeriodDateSplitter;
	private Actions builder;
	private PreReportPage preReportPage;
	private String actReportTitle;
	private String expReportTitle;
	private String actSelectedClassInDDL;
	private String expSelectedClassInDDL;
	private DatabaseReader databaseReader;
	private ReportPage reportPage;
	private String actUrl;
	private String schoolId;
	private String startSchoolYearDate;
	private String actDayOfStartSchoolYear;
	private DateTimeFormatter formatter;	
	private MonthDay startSchoolYearDateByLocalDate;
	private String actMonthOfStartSchoolYear;
	private String expDateOfStartSchoolYear;
	private String actDateOfStartSchoolYear;
	private String actYearOfStartSchoolYear;
	private String actDayOfEndSchoolYear;
	private String actMonthOfEndSchoolYear;
	private String actYearOfEndSchoolYear;
	private String expStartDateOfSchoolYear;
	private String expEndDateOfSchoolYear;	
	private String actDateFromReport;	
	private String expDateFromReport;
	private List<Float> heightOfBarsValue;
	private List<String> lexileValues;
	private List<String> heightOfBars;
	private int temp;
	private String actTextNoDataMessage;
	private String expTextNoDataMessage;
	
	
	
	public final static HashMap<String, String> monthsShort = new HashMap<>();
	static {	
		monthsShort.put("Jan", "ene.");
		monthsShort.put("Feb", "feb.");
		monthsShort.put("Mar", "mar.");
		monthsShort.put("Apr", "abr.");
		monthsShort.put("May", "may.");
		monthsShort.put("June", "jun.");
		monthsShort.put("July", "jul.");
		monthsShort.put("Aug", "ago.");
		monthsShort.put("Sept", "sep.");
		monthsShort.put("Oct", "oct.");
		monthsShort.put("Nov", "nov.");
		monthsShort.put("Dec", "dic.");
	
	}
	

	@Parameters({"login", "password", "program", "selectedClass"})
	@Test(invocationCount = 1)
    public void check_US10179(@Optional("usteach13443.alexdemo") String login,
                              @Optional("usteach13443.alexdemo") String password,
                              @Optional("") String program,
                              @Optional("") String selectedClass) {
				
		databaseReader = new DatabaseReader(url());
		loginPage = new LoginPage(driver);
		
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
		loginPage.afterLoginCheckWithProgram(program);
		loginPage.afterLoginCheck(selectedClass);
		homePage = new HomePage(driver);
		
				
		actThirdMetricText = homePage.getMetricHighestLexileGains().getText();
		expThirdMetricText = "Students with the highest Lexile gains";
        softAssert.assertEquals(actThirdMetricText, expThirdMetricText,
                "The text of 3-rd metric is incorrect.");

        sleep(1500);
        homePage.closeWalkmeNew(5);
		homePage.clickOnMetricHighestLexileGains();			
		today = LocalDate.now();
		
		firstDayOfLastMonthPeriod = today.minusMonths(1).withDayOfMonth(1);		
		lastDayOfLastMonthPeriod = firstDayOfLastMonthPeriod.withDayOfMonth(firstDayOfLastMonthPeriod.lengthOfMonth());		
		
		startOfPeriodDate = firstDayOfLastMonthPeriod.format(DateTimeFormatter.ofPattern("MMM d"));
		endOfPeriodDate = lastDayOfLastMonthPeriod.format(DateTimeFormatter.ofPattern("MMM d"));
		
		dateRangeLabel = homePage.getTextFromElement(homePage.getDateRangeLabel(), 10);

        actTextOfDateRangeLabel = dateRangeLabel.substring(0,
                dateRangeLabel.lastIndexOf('(')).trim();
		expTextOfDateRangeLabel = "Last Month";
        softAssert.assertEquals(actTextOfDateRangeLabel, expTextOfDateRangeLabel,
                "The text Of Date Range Label is not correct");
		
		actDateRangeValue = dateRangeLabel.substring(dateRangeLabel.lastIndexOf('(')+1, dateRangeLabel.lastIndexOf(')')).trim();
        expDateRangeValue = startOfPeriodDate + " - " + endOfPeriodDate;
			
		actTextOfMoreButton = homePage.getTextOfMoreButton();
		expTextOfMoreButton = "More";
        softAssert.assertEquals(actTextOfMoreButton, expTextOfMoreButton,
                "The text of \"more\"-button is not correct");
				
		
		
		lexileValues = homePage.getLexileValuesAboveBars();
		heightOfBars = homePage.getHeightOfBars();
		
		if(lexileValues.size() == 0){
			actTextNoDataMessage = homePage.getNoDataMessageInKeyInsught().getText().trim();
			expTextNoDataMessage = "No Data Available.";
            softAssert.assertEquals(actTextNoDataMessage, expTextNoDataMessage,
                    "The no data message is wrong.");
		}else{
		temp = 0;		
		for(int i = 0;i < lexileValues.size(); i++){
			boolean result = lexileValues.get(i).contains("+");
            softAssert.assertTrue(result, "The number of lexile value is not contain \\+ . " +
                    "Found [" + lexileValues.get(i) + "]");
			if(i==0) {
				heightOfBarsValue = homePage.convertListsStringIntoFloat(heightOfBars);
				temp =(int) Math.round(heightOfBarsValue.get(i));
				continue;
			}
			boolean result2 = temp >= ((int) Math.round(heightOfBarsValue.get(i)));
            softAssert.assertTrue(result2,
                    "The privious bar in not higher or equal to current bar.");
		}
		}
		
		
		expSelectedClassInDDL = homePage.getActiveClassName();
		
		
		homePage.clickOnMoreButton();		
		preReportPage = new PreReportPage(driver);
		
		actReportTitle =  preReportPage.getReportTitleText().replace("\n", "");
		expReportTitle = "How has Lexile performance changed over time?";
        softAssert.assertEquals(actReportTitle, expReportTitle,
                "The \"more\"-button opened wrong report page.");
				
		
		actSelectedClassInDDL = preReportPage.getSelectedOptionFromSelect(preReportPage.getWhichClassDDL());
		//expSelectedClassInDDL = selectedClass;
		
		if(expSelectedClassInDDL.length()<27){
            softAssert.assertEquals(actSelectedClassInDDL, expSelectedClassInDDL,
                    "The selected class in \"Which Class?\" DDL is wrong.");
		} else {
            softAssert.assertTrue(actSelectedClassInDDL.contains(expSelectedClassInDDL),
                    "The selected class in \"Which Class?\" DDL is wrong.");
		}
		
		
		actDayOfStartSchoolYear = preReportPage.getSelectedOptionFromSelect(preReportPage.getStartDateDayDDL());
		actMonthOfStartSchoolYear = preReportPage.getSelectedOptionFromSelect(preReportPage.getStartDateMonthDDL());
		actYearOfStartSchoolYear = preReportPage.getStartDateYearInput().getAttribute("value").trim();
				

		actDayOfEndSchoolYear = preReportPage.getSelectedOptionFromSelect(preReportPage.getEndDateDayDDL());		
		actMonthOfEndSchoolYear = preReportPage.getSelectedOptionFromSelect(preReportPage.getEndDateMonthDDL());
		actYearOfEndSchoolYear = preReportPage.getEndDateYearInput().getAttribute("value").trim();
		
		
		expStartDateOfSchoolYear = actMonthOfStartSchoolYear + " " + actDayOfStartSchoolYear + ", " + actYearOfStartSchoolYear;
		expEndDateOfSchoolYear = actMonthOfEndSchoolYear + " " + actDayOfEndSchoolYear + ", " + actYearOfEndSchoolYear;		
		
		
		
		$(preReportPage.getViewReportButton()).click();
		preReportPage.switchToNextWindow();
		
		reportPage = new ReportPage(driver);
		
		actDateFromReport = reportPage.getTextOfDateLineInReport();
		expDateFromReport = expStartDateOfSchoolYear + " - " + expEndDateOfSchoolYear;
        softAssert.assertEquals(actDateFromReport, expDateFromReport,
                "The DATE from report is incorrect.");


		actUrl = url();
		schoolId = actUrl.substring(actUrl.indexOf("school_id")+10,actUrl.indexOf("&sid"));
		
		
		String sqlQuery = "select school_start from schools where school_id=" + schoolId;		
		
		reportPage.switchBack();

		preReportPage.changeLangTo("spanishSupport");

		builder = new Actions(getWebDriver());



		// ****************************************************** SET SPANISH LANGUAGE  ****************************************************************************



		homePage.changeLangTo("spanish");
//		driver.navigate().refresh();
		
		actThirdMetricText = homePage.getMetricHighestLexileGains().getText();
		expThirdMetricText = "Estudiantes con el mayor incremento en el nivel Lexile";
		softAssert.assertEquals(actThirdMetricText, expThirdMetricText, "The text of 3-rd metric is incorrect IN SPANISH LANGUAGE.");
		
		
		homePage.clickOnMetricHighestLexileGains();	
		
		
		startOfPeriodDate = firstDayOfLastMonthPeriod.format(DateTimeFormatter.ofPattern("d MMM"));
		endOfPeriodDate = lastDayOfLastMonthPeriod.format(DateTimeFormatter.ofPattern("d MMM"));
		
		startOfPeriodDateSplitter = startOfPeriodDate.split(" ");
		endOfPeriodDateSplitter = endOfPeriodDate.split(" ");		

		dateRangeLabel = homePage.getTextFromElement(homePage.getDateRangeLabel(), 10);

		actTextOfDateRangeLabel = dateRangeLabel.substring(0, dateRangeLabel.lastIndexOf('(')).trim();
		expTextOfDateRangeLabel = "Mes anterior";
		softAssert.assertEquals(actTextOfDateRangeLabel, expTextOfDateRangeLabel, "The text Of Date Range Label is not correct IN SPANISH LANGUAGE.");
		
		dateRange = dateRangeLabel.substring(dateRangeLabel.lastIndexOf('(')+1, dateRangeLabel.lastIndexOf(')')).trim();		
		
		actDateRangeValue = dateRangeLabel.substring(dateRangeLabel.lastIndexOf('(')+1, dateRangeLabel.lastIndexOf(')')).trim();
        expDateRangeValue = startOfPeriodDateSplitter[0] +" de "+ monthsShort.get(startOfPeriodDateSplitter[1])  + " - " + endOfPeriodDateSplitter[0] + " de "+ monthsShort.get(endOfPeriodDateSplitter[1]);
	//	softAssert.assertEquals(actDateRangeValue, expDateRangeValue, "The value of Date range is incorrect IN SPANISH LANGUAGE.");
		
		
		
		actTextOfMoreButton = homePage.getTextOfMoreButton();
		expTextOfMoreButton = "Más";
		softAssert.assertEquals(actTextOfMoreButton, expTextOfMoreButton, "The text of \"more\"-button is not correct IN SPANISH LANGUAGE.");
		
		
		
		lexileValues = homePage.getLexileValuesAboveBars();
		heightOfBars = homePage.getHeightOfBars();
		
		if(heightOfBars.size() == 0){
			actTextNoDataMessage = homePage.getNoDataMessageInKeyInsught().getText().trim();
			expTextNoDataMessage = "No hay datos.";
			softAssert.assertEquals(actTextNoDataMessage, expTextNoDataMessage, "The no data message is wrong IN SPANISH LANGUAGE.");
			
		
		}else{
			
			boolean result3 = heightOfBars.size() <= 5;
			softAssert.assertTrue(result3, "The privious bar in not higher or equal to current bar IN SPANISH LANGUAGE.");
		
			temp = 0;		
			for(int i = 0;i < lexileValues.size(); i++){
				boolean result = lexileValues.get(i).contains("+");			
				softAssert.assertTrue(result, "The number of lexile value is not contain \\+ . Found [" + lexileValues.get(i) +"]");
				if(i==0) {
					heightOfBarsValue = homePage.convertListsStringIntoFloat(heightOfBars);
					temp =(int) Math.round(heightOfBarsValue.get(i));
					continue;
				}
				boolean result2 = temp >= ((int) Math.round(heightOfBarsValue.get(i)));
				softAssert.assertTrue(result2, "The privious bar in not higher or equal to current bar IN SPANISH LANGUAGE.");
			}
		}	
		
		
		expSelectedClassInDDL = homePage.getActiveClassName();		
		homePage.clickOnMoreButton();
		
		
		actReportTitle =  preReportPage.getReportTitleText().replace("\n", " ");
		expReportTitle = "¿Cómo ha cambiado el desempeño en el nivel de lectura Lexile con el tiempo?";
		softAssert.assertEquals(actReportTitle, expReportTitle, "The \"more\"-button opened wrong report page IN SPANISH LANGUAGE.");		
		
		
		actSelectedClassInDDL = preReportPage.getSelectedOptionFromSelect(preReportPage.getWhichClassDDL());
		//expSelectedClassInDDL = selectedClass;
		
		if(expSelectedClassInDDL.length()<27){
			 softAssert.assertEquals(actSelectedClassInDDL, expSelectedClassInDDL, "The selected class in \"Which Class?\" DDL is wrong IN SPANISH LANGUAGE.");
		} else {
			 softAssert.assertTrue(actSelectedClassInDDL.contains(expSelectedClassInDDL), "The selected class in \"Which Class?\" DDL is wrong IN SPANISH LANGUAGE.");
		}
		
		
		
		actDayOfStartSchoolYear = preReportPage.getSelectedOptionFromSelect(preReportPage.getStartDateDayDDL());
		actMonthOfStartSchoolYear = preReportPage.getSelectedOptionFromSelect(preReportPage.getStartDateMonthDDL());
		actYearOfStartSchoolYear = preReportPage.getStartDateYearInput().getAttribute("value").trim();
				

		actDayOfEndSchoolYear = preReportPage.getSelectedOptionFromSelect(preReportPage.getEndDateDayDDL());		
		actMonthOfEndSchoolYear = preReportPage.getSelectedOptionFromSelect(preReportPage.getEndDateMonthDDL());
		actYearOfEndSchoolYear = preReportPage.getEndDateYearInput().getAttribute("value").trim();
		
		
		expStartDateOfSchoolYear = actDayOfStartSchoolYear + " de " + actMonthOfStartSchoolYear + " de " + actYearOfStartSchoolYear;
		expEndDateOfSchoolYear = actDayOfEndSchoolYear + " de " + actMonthOfEndSchoolYear + " de " + actYearOfEndSchoolYear;		
		
		
		$(preReportPage.getViewReportButton()).click();
		preReportPage.switchToNextWindow();		
		
		
		actDateFromReport = reportPage.getTextOfDateLineInReport();
		expDateFromReport = expStartDateOfSchoolYear + " - " + expEndDateOfSchoolYear;				
		softAssert.assertEquals(actDateFromReport, expDateFromReport, "The DATE from report is incorrect IN SPANISH LANGUAGE.");	
		
		
		reportPage.switchBack();
		
		softAssert.assertAll();		
	}
}
