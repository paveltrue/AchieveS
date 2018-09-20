package com.a3k.testCases.incognita.part3;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import io.qameta.allure.Step;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.sleep;

public class US11858 extends BasicTestCase {
	
	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] {
			{"usteach11858.alex", "usteach11858.alex","", ""},
			{"ussa11858.alex", "ussa11858.alex","KidBiz3000", "Auto Kidbiz 3g"},
			{"usda11858.alex", "usda11858.alex", "KidBiz3000", ""},
			{"kb.ref", "kb.ref", "KidBiz3000", ""},
			{"ukteach11858.alex", "ukteach11858.alex", "", ""}
		};
		return data;
	}
	
	private String testName = "Testname";
	private String specialSymbols = "!@#$%&*@";
	
	
	@Parameters({"login", "password", "program", "selectedClass"})
	@Test(dataProvider="getUsers",groups = {"Incognita", "All"})
	public void check_US11858(@Optional String login,
							  @Optional String password,
							  @Optional String program,
							  @Optional String selectedClass) {
		
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
		loginPage.afterLoginCheckWithProgram(program);
		loginPage.afterLoginCheck(selectedClass);
				
		HomePage homePage = new HomePage(driver);
		sleep(1000);
		homePage.closeWalkmeNew(5);
		homePage.clickOnEditTeacherProfileLink();
		
		AdminPage adminPage = new AdminPage(driver);
		
		if (login.equals("kb.ref")){
            //Automation Only District - BTS2018 - (do not change to non NJ)
			adminPage.selectDistrictByValue("3143");
			adminPage.clickOnEditTeacherProfileLink();
		}	
			
		EditUserInfo editUserPage = new EditUserInfo(driver);
				
		checkingInfoPopUp(editUserPage);		
		
		if(login.equals("kb.ref") ){
			verifyPenName(adminPage, editUserPage);		
		}
		
		if(!login.equals("kb.ref") ){
			verifyTeDisplayNameInput(editUserPage);		
		}
			
		softAssert.assertAll();
	}

    @Step
	private void verifyPenName(AdminPage adminPage, EditUserInfo editUserPage) {
		editUserPage.goToAdminPage();		
		adminPage.clickOnEditStudentAndTeacherInformation();
		
		adminPage.selectSchoolByValue("25206");		
		adminPage.selectClassByValue("2788860");
		
		adminPage.clickSubmitButton();		
		EditClassInfoStudentListTable editClassInfoTable = new EditClassInfoStudentListTable(driver);
		
		editClassInfoTable.clickFirstStudentInList();
		
		String actTextOfLabel = editUserPage.getTextFromTeDisplayNameLabelBy();
		String expTextOfLabel = "Pen Name";
		softAssert.assertEquals(actTextOfLabel, expTextOfLabel, "The label of Pen Name is wrong.");
	}

    @Step
	private void checkingInfoPopUp(EditUserInfo editUserPage) {
		
		editUserPage.clickOnInformationButtonTeDisplayName();		
		
		String actTextFromInfoPopUp = editUserPage.getTextFromInfoPopUpTeDisplayName();		
		String expTextFromInfoPopUp = "Enter the name you want displayed within the Teacher Edition (e.g., Ms. Jones). If left blank, first name will display.";		
		softAssert.assertEquals(actTextFromInfoPopUp, expTextFromInfoPopUp, "The text from info pop up is wrong.");		
		
		editUserPage.closeInfoPopUpTeDisplayName();
		
		String actTextOfLabel = editUserPage.getTextFromTeDisplayNameLabelBy();
		String expTextOfLabel = "TE Display Name";
		softAssert.assertEquals(actTextOfLabel, expTextOfLabel, "The label of TE Display Name is wrong.");	
	}

    @Step
	private void verifyTeDisplayNameInput(EditUserInfo editUserPage) {
		
		editUserPage.clearTeDisplayNameInput();		
		editUserPage.setTeDisplayName(testName);		
		editUserPage.clickOnSubmitButton();
		
		
		String displayName = editUserPage.getValueOfTeDisplayNameInput();		
		String teacherName = editUserPage.getTeacherName();		
		softAssert.assertEquals(teacherName, displayName, "The teacher's name is not equal to 'TE Display Name' but should to be.");
		
						
		editUserPage.clearTeDisplayNameInput();
		editUserPage.clickOnSubmitButton();	
		
				
		String firstNameTeacher = editUserPage.getValueOfFirstNameInput();
		teacherName = editUserPage.getTeacherName();		
		softAssert.assertEquals(teacherName, firstNameTeacher, "The teacher's name on header is not the same with the name from Edit User profile page.");
		
				
		editUserPage.clearTeDisplayNameInput();		
		editUserPage.setTeDisplayName(specialSymbols);		
		editUserPage.clickOnSubmitButton();	
				
		
		firstNameTeacher = editUserPage.getValueOfFirstNameInput();	
		teacherName = editUserPage.getTeacherName();				
		softAssert.assertEquals(teacherName, firstNameTeacher, "The teacher's name on header is not the same with the name from Edit User profile page after entering special symbols.");
	}	
}
