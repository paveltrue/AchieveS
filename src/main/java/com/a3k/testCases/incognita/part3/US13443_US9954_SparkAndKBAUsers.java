package com.a3k.testCases.incognita.part3;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class US13443_US9954_SparkAndKBAUsers extends BasicTestCase {

	private LoginPage loginPage;
	private HomePage homePage;
	private List<String> actTeacherDDL;
	private List<String> expTeacherDDL;
	private NewReportsPage newReportsPage;
	private String headerAddresOfReportsPage;
	private String actResult;
	private String expResult;
	private Set<String> actReportSectionsStatus;
	private Set<String> expReportSectionsStatus;
	private EditUserInfo editUserInfoPage;
	private String headerAddresOfEditUserInfoPage;
	private HelpPage helpPage;
	private String actTittleOfHelpPage;
	private String expTittleOfHelpPage;
	private boolean result;
	private String actStatusOfSection;
	private String expStatusOfSection;
	private PreReportPage preReportPage;
	private String actReportTitle;
	private String expReportTitle;
	private List<String> expResults;
	private List<String> actResults;
	private List<String> actNamesAndOrderOfReportsSection;
	private List<String> expNamesAndOrderOfReportsSection;
	private List<String> expTitlesOfReport;
	private List<String> actTitlesOfReport;

	private By reportDDLs = By.xpath(".//*[@id='submenu_2']//select");
	private ElementsCollection allDDL;
	private List<String> allLabelsOfDDL;


	@DataProvider
	public Object[][] getUsers(){
		Object[][] data = new Object [][]{
				{"usteach13443.alexspark", "usteach13443.alexspark", "Spark3000", ""},
				{"ussa13443.alexspark", "ussa13443.alexspark", "Spark3000", ""},
				{"jbonilla", "23lamb", "KidBiz3000", ""},
				{"usteach13443.alexspark", "usteach13443.alexspark", "", ""}
		};
		return data;
	}



	@Parameters({ "login", "password","program", "selectedClass" })
	@Test(dataProvider = "getUsers",groups = {"Reports", "Avatar", "Incognita", "Archived", "All"})
	public void check_US13443_US9954_SparkAndKBAUsers (@Optional() String login,
                                                       @Optional() String password,
                                                       @Optional() String program,
                                                       @Optional() String selectedClass) {

		loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program,  selectedClass);
		sleep(100);
		loginPage.afterLoginCheckWithProgram(password);
		
		homePage = new HomePage(driver);
		homePage.clickOnTeachersName();
		homePage.closeDoneButtonIfExist();

		actTeacherDDL = homePage.getTextFromWebElementsByList(homePage.getTeacherSettingsDDL());
		expTeacherDDL = Arrays.asList("Edit Profile", "Admin Settings", "Reports", "Help", "Log Out");
		softAssert.assertEquals(actTeacherDDL, expTeacherDDL, "The teacher DDL has wrong order.");

		homePage.goToReportsPage();

		newReportsPage = new NewReportsPage(driver);

		if (login.equals("kb.ref") | login.equals("jbonilla") ){
            //Automation Only District - BTS2018 - (do not change to non NJ)
			newReportsPage.selectDistrictByValue("3143");
		}		

		if (login.equals("uskba13443.alexdemo")){
			//A3K Employee Demo District (NJ) -- Current
			newReportsPage.selectDistrictByValue("156");
		}		

		headerAddresOfReportsPage = newReportsPage.getHeaderOfReportsPage().getAttribute("src");

		actResult = headerAddresOfReportsPage.substring(headerAddresOfReportsPage.lastIndexOf('/') + 1, headerAddresOfReportsPage.lastIndexOf('.'));
		expResult = "reports_header";
		softAssert.assertEquals(actResult, expResult, "The header of reports page is wrong.");		
		
		
		actNamesAndOrderOfReportsSection = newReportsPage.getTextFromWebElementsByList(newReportsPage.getReportSections());		
		if(!login.contains(".alexspark")){
			expNamesAndOrderOfReportsSection = Arrays.asList("Student Work","Usage Reports","Performance Reports","Assessment Tools","Home Communications");
		} else {
			expNamesAndOrderOfReportsSection = Arrays.asList("Learner Work","Usage Reports","Performance Reports","Assessment Tools");		}
		
		softAssert.assertEquals(actNamesAndOrderOfReportsSection, expNamesAndOrderOfReportsSection, "The names or order of reports section is wrong.");		
		
		

		actReportSectionsStatus = newReportsPage.getAttributesFromItemsOfListBySet(newReportsPage.getReportSections(), "class");
		expReportSectionsStatus = new HashSet<>();
		expReportSectionsStatus.add("report_category");
		softAssert.assertEquals(actReportSectionsStatus, expReportSectionsStatus, "The report section are not all collapsed.");		
		 
		
		for(int i = 0; i < newReportsPage.getReportSections().size(); i++){
			newReportsPage.closeDoneButtonIfExist();
			$(newReportsPage.getReportSections().get(i)).click();
			List<String> tempArray = newReportsPage.getAttributesFromItemsOfList(newReportsPage.getReportSections(), "class");
			
			for(int j = 0; j < tempArray.size(); j++){
				if(j!= i){
					boolean tempResult = tempArray.get(j).equals("report_category");
					softAssert.assertTrue(tempResult, "The report section - "+ newReportsPage.getReportSections().get(j).getText() + " is not collapsed.");					
				}
			}			
			$(newReportsPage.getReportSections().get(i)).click();
		}	
		
		
		preReportPage = new PreReportPage(driver);
		$(newReportsPage.getStudentWorkSectionHeader()).click();
		
		allDDL = newReportsPage.getAllDDLinStudentWorkSection();
		allLabelsOfDDL = newReportsPage.getTextFromWebElementsByList(newReportsPage.getLabelsOfDDL());
		
		expTitlesOfReport = getTitlesFromLabelsAndItemsDDL(allDDL, allLabelsOfDDL);		
		actTitlesOfReport = getTitlesOfPageByListOfSelects(reportDDLs,expTitlesOfReport );
		softAssert.assertEquals(actTitlesOfReport, expTitlesOfReport, "The reports of DDL in Student Work are wrong");	
		
		

		newReportsPage.goToHomePage();
		homePage.clickOnTeachersName();

		homePage.clickOnEditTeacherProfileLink();
		editUserInfoPage = new EditUserInfo(driver);

		headerAddresOfEditUserInfoPage = editUserInfoPage.getHeaderOfEditUserInfoPage().getAttribute("src");

		actResult = headerAddresOfEditUserInfoPage.substring(headerAddresOfEditUserInfoPage.lastIndexOf('/') + 1, headerAddresOfEditUserInfoPage.lastIndexOf('.'));
		expResult = "admin_header2";
		softAssert.assertEquals(actResult, expResult, "The header of Edit User page is wrong");

		
		editUserInfoPage.goToHomePage();		
		homePage.clickOnTeachersName();

		$(homePage.getHelpLink()).click();
		homePage.switchToNextWindow();

		helpPage = new HelpPage(driver);

		actTittleOfHelpPage = helpPage.getTitleOfPage().getText();
		expTittleOfHelpPage = "Help Center";
		softAssert.assertEquals(actTittleOfHelpPage, expTittleOfHelpPage, "The Help link open incorrect page.");

		helpPage.switchBack();
		homePage.clickOnTeachersName();			
		
		homePage.goToReportsPage();

		actResult = headerAddresOfReportsPage.substring(headerAddresOfReportsPage.lastIndexOf('/') + 1, headerAddresOfReportsPage.lastIndexOf('.'));
		expResult = "reports_header";
		softAssert.assertEquals(actResult, expResult, "The header of reports page is wrong AFTER TRANSITION FROM ADMIN SETTINGS PAGE.");

		newReportsPage.goToHomePage();
		homePage.clickOnHamburgerMenu();
		$(homePage.getPerformanceLinkInHumburgerMenu()).click();

		actResult = headerAddresOfReportsPage.substring(headerAddresOfReportsPage.lastIndexOf('/') + 1, headerAddresOfReportsPage.lastIndexOf('.'));
		expResult = "reports_header";
		softAssert.assertEquals(actResult, expResult, "The header of reports page is wrong AFTER TRANSITION FROM HUMBURGER MENU.");

		actStatusOfSection = newReportsPage.getPerformanceSectionHeader().getAttribute("class");
		expStatusOfSection = "report_category_selected";
		softAssert.assertEquals(actStatusOfSection, expStatusOfSection, "The performance section of reports page is not opening.");

		newReportsPage.clickOnHamburgerMenu();
		$(newReportsPage.getStudentWorkLinkInHumburgerMenu()).click();
		

		if (!login.equals("usda13443.alexdemo") & !login.equals("usda13443.alex") ) {			
			$(newReportsPage.getEmailAndStep1()).click();

			actReportTitle = preReportPage.getReportTitleText();
			expReportTitle = "Email and Step 1";
			softAssert.assertEquals(actReportTitle, expReportTitle, "The report title of Email and Step is wrong.");

			preReportPage.clickOnBackToMenu();
		}

		if (!login.contains(".alexspark")) {

			$(newReportsPage.getPointsAndAchivements()).click();

			actReportTitle = preReportPage.getReportTitleText();
			expReportTitle = "Points and Achievements";
			softAssert.assertEquals(actReportTitle, expReportTitle, "The report title of Points and Achievements is wrong.");

			preReportPage.clickOnBackToMenu();

		}

		$(newReportsPage.getAuthenticAssesmentPortfolio()).click();

		actReportTitle = preReportPage.getReportTitleText();
		expReportTitle = "Authentic Assessment Portfolio";
		softAssert.assertEquals(actReportTitle, expReportTitle, "The report title of Authentic Assessment Portfolio is wrong.");

		preReportPage.clickOnBackToMenu();

		newReportsPage.clickOnHamburgerMenu();
		$(newReportsPage.getUsageLinkInHumburgerMenu()).click();

		actStatusOfSection = newReportsPage.getUsageReportsSectionHeader().getAttribute("class");
		expStatusOfSection = "report_category_selected";
		softAssert.assertEquals(actStatusOfSection, expStatusOfSection, "The Usage Reports section of reports page is not opening.");

		expResults = newReportsPage.getUsageReportsSectionLinks();
		actResults = getTitlesOfPageByLinks(newReportsPage.getUsageReportsSectionLinks());
		

		for (int i = 0; i < expResults.size(); i++) {
			softAssert.assertEquals(actResults.get(i).replaceAll(" ", ""), expResults.get(i).replaceAll(" ", ""), "The link from Usage Reports section is wrong.");
		}

		newReportsPage.clickOnHamburgerMenu();
		$(newReportsPage.getPerformanceLinkInHumburgerMenu()).click();

		expResults.clear();

		expResults = newReportsPage.getPerformanceSectionLinks();
		actResults = getTitlesOfPageByLinks(newReportsPage.getPerformanceSectionLinks());

		for (int i = 2; i < expResults.size(); i++) {
			softAssert.assertEquals(actResults.get(i).replaceAll(" ", ""), expResults.get(i).replaceAll(" ", ""), "The link from Performance Reports section is wrong.");

		}

		if (!login.contains(".alexspark")) {

			newReportsPage.clickOnHamburgerMenu();
			$(newReportsPage.getHomeCommunicationsLinkInHumburgerMenu()).click();

			expResults.clear();

			expResults = newReportsPage.getHomeCommunicationsSectionLinks();
			actResults = getTitlesOfPageByLinks(newReportsPage.getHomeCommunicationsSectionLinks());

			for (int i = 0; i < expResults.size(); i++) {
				if (!expResults.get(i).equals("Welcome letter and Home Edition setup instructions (additional languages)")) {
					softAssert.assertEquals(actResults.get(i).replaceAll(" ", ""), expResults.get(i).replaceAll(" ", ""), "The link from Home Communications section is wrong.");
				} else {
					softAssert.assertTrue(actResults.get(i).equals("Home Edition set up instructions for additional languages"), "The link from Home Communications section is wrong 2.");
				}
			}
		}

		newReportsPage.goToHomePage();

		homePage.clickOnTeachersName();
		$(homePage.getLogOutLink()).click();

		result = loginPage.isElementExist(loginPage.getLoginForm());
		softAssert.assertTrue(result, "The login page is incorrect.");

		softAssert.assertAll();
	}

	public List<String> getTitlesOfPageByLinks(List<String> elements) {
		List<String> results = new ArrayList<String>();
		for (String str : elements) {
			if (!str.equals("How are my teachers participating in online professional development?") & !str.equals("¿Cuál es la participación de mis maestros en la capacitación profesional en línea? (Inglés)")) {
				
				str = newReportsPage.apostropheHandlerForStringXpath(str);				
				newReportsPage.findEl(By.xpath(".//td[contains(@id,'menu_')]/../following-sibling::tr//*[contains(@id, 'submenu_')]//a[contains(text(), '"+str+"')]")).click();

				results.add(preReportPage.getReportTitleText().replaceAll("\n", " ").trim());			
				back();
				
			} else if (str.equals("How are my teachers participating in online professional development?")) {
				results.add("How are my teachers participating in online professional development?");

			} else if (str.equals("¿Cuál es la participación de mis maestros en la capacitación profesional en línea? (Inglés)")) {
				results.add("¿Cuál es la participación de mis maestros en la capacitación profesional en línea? (Inglés)");
			}
		}
		return results;

	}
	
	public List<String> getTitlesFromLabelsAndItemsDDL (ElementsCollection listOfDDL, List<String> listOfLabelsOfDDL){
		ArrayList<String> result = new ArrayList<>();
		Select select;
		List<WebElement> itemsOfSelect;
		
		for(int i = 0; i < listOfLabelsOfDDL.size(); i++){			
			select = new Select(listOfDDL.get(i));				
			itemsOfSelect = select.getOptions();				
			for(WebElement el : itemsOfSelect){	
				if(!el.getText().trim().isEmpty()){
					String temp = listOfLabelsOfDDL.get(i) + " " +el.getText();
					result.add(temp);
				}
			}
		}		
		return result;		
	}
	
	
	public List<String> getTitlesOfPageByListOfSelects(By elements, List<String> fullNames) {
		ArrayList<String> result = new ArrayList<>();
		ElementsCollection temp;
		int count = 0;
		for(int j = 0; j < $$(elements).size(); j++){
			for(int i = 0; i < newReportsPage.getReportNamesFromReportDDL($$(elements).get(j)).size(); i++){
				String str = newReportsPage.getReportNamesFromReportDDL($$(elements).get(j)).get(i);
				String str2 = fullNames.get(count);				
				newReportsPage.selectFromDDL($$(elements).get(j), str);
				
				if(!str2.equals("Units Written Responses") & !str2.equals("Unidades Respuestas por escrito")){
					result.add(preReportPage.getReportTitleText().replaceAll("\n", " ").trim());
				} else if (str2.equals("Units Written Responses")) {
					result.add("Units Written Responses");
				} else if (str2.equals("Unidades Respuestas por escrito")) {
					result.add("Unidades Respuestas por escrito");
				}				
				count++;
				preReportPage.clickOnBackToMenu();
			}			
		}		
		return result;			
	}
}
