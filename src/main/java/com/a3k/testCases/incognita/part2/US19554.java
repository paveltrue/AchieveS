package com.a3k.testCases.incognita.part2;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class US19554 extends BasicTestCase {
	
	private HomePage homePage;
	private MyLessons myLessons;

	@DataProvider
	public Object[][] getUsers() {
		Object[][] data = new Object[][] { 
			{ "ussa19554.alex", "ussa19554.alex", "KidBiz3000", "" }, 
			{ "usda19554.alex", "usda19554.alex", "KidBiz3000", "" },
			{ "uksa19554.alex", "uksa19554.alex", "KidBiz3000", "" },
			{ "ukda19554.alex", "ukda19554.alex", "KidBiz3000", "" },
			{ "kb.ref", "kb.ref", "KidBiz3000", "" }
			};
		return data;
	}

	@Parameters({ "login", "password", "program", "selectedClass" })
    @Test(dataProvider = "getUsers", groups = {"My Lessons", "Incognita", "All"})
	public void check_US19554(@Optional() String login,
							  @Optional() String password,
							  @Optional() String program,
							  @Optional() String selectedClass) {

		login(login, password, program, selectedClass);

		homePage = new HomePage(driver);
		homePage.goToMyLessonsPage();

		myLessons = new MyLessons(driver);
		myLessons.deleteAllCollections();
		
		createCollectionWithoutAssignToClass("Test collection ");
		
		myLessons.clickOnPrintButtonOfCollectionBy();		
		myLessons.switchToNextWindowWhenExistOnly2();
		List<String> tab = new ArrayList<>(getWebDriver().getWindowHandles());


		softAssert.assertTrue(myLessons.getWindowTitle().contains("Achieve3000"), "The wrong page opened after click print button");
		getWebDriver().switchTo().window(tab.get(1)).close();
		sleep(1000);
		getWebDriver().switchTo().window(tab.get(0));
		softAssert.assertAll();


	}
	
	private void createCollectionWithoutAssignToClass(String name) {
		logger.info("Create new collection");

		myLessons.clickOnBuiltLessonCollectionBy();
		myLessons.clickOnCreateCollectionBy();

		long id = System.currentTimeMillis();

		String collectionName = name + id;

		myLessons.enterTextInCollectionNameInput(collectionName);
		myLessons.enterTextInDescriptionInput("Test");

		myLessons.saveCollection();
		myLessons.clickOkButtonOnPopUpChangesSaved();

		logger.info("Collection created with name: " + collectionName);
	}
	
	private void login(String login, String password, String program, String selectedClass) {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
		loginPage.afterLoginCheck(selectedClass);
	}	
}
