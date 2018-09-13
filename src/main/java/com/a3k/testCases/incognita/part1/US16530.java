package com.a3k.testCases.incognita.part1;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class US16530 extends BasicTestCase {

	private LoginPage loginPage;
	private HomePage homePage;
	private MyLessons myLessons;
	

	@DataProvider
	private Object[][] getUsers() {
		return new Object[][] { { "kb.ref", "kb.ref", "KidBiz3000", "" } };
	}


    @Test(dataProvider = "getUsers", groups = {"Lesson Page", "Lexile", "Loader", "Incognita", "All"})
	private void verifyActivating_US16530(@Optional String username, @Optional String password, @Optional String studentProgram, @Optional String selectedClass) {

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);

		loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);
		homePage.selectLexileByValue("1380");
		
		softAssert.assertTrue(homePage.getLexileText().contains("1380"), "Lexile is not 1380");
		homePage.changeLangToSpanish();
		
		softAssert.assertTrue(homePage.getLexileText().contains("1350"), "Lexile is not 1350");

		softAssert.assertAll();
	}

    @Test(dataProvider = "getUsers", groups = {"Lesson Page", "Lexile", "Loader", "Incognita", "All"}, invocationCount = 1)
	private void verifyDescriptors_US16530(@Optional String username, @Optional String password, @Optional String studentProgram, @Optional String selectedClass) {

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);

		loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);
		homePage.openLexileDDL();
		
		ElementsCollection lexileList = homePage.getListOfElementsOfLexileDDL();
		softAssert.assertTrue(checkLexileTextPresence(lexileList), "Lexile text doesn't match the pattern");
		softAssert.assertAll();
	}

	private boolean checkLexileTextPresence(ElementsCollection list) {
		for (WebElement element : list) {
			if (!$(element).getText().matches(".+\\((.+)\\)")) {
				return false;
			}
		}
		return true;
	}
}
