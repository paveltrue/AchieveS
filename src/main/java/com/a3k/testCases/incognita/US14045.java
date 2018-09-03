package com.a3k.testCases.incognita;

import com.a3k.BasicTestCase;
import com.a3k.pages.AdminPage;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.utils.db.DatabaseReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.refresh;
import static com.codeborne.selenide.WebDriverRunner.url;

public class US14045 extends BasicTestCase {

	private LoginPage loginPage;
	private HomePage homePage;
	private MyLessons myLessons;
	
	
	@DataProvider
	private Object[][] getAdmin() {
		return new Object[][] { { "kb.ref", "kb.ref", "KidBiz3000", "" } };
	}

	@DataProvider
	private Object[][] getUsers() {
		return new Object[][] { { "ussa14045.pooh", "ussa14045.pooh", "KidBiz3000", "" },
				{"uksa14045.pooh", "uksa14045.pooh", "KidBiz3000", ""},
				{"kb.ref", "kb.ref", "KidBiz3000", ""}
								};
	}

	@Test(dataProvider = "getUsers", groups = {"My Lessons", "All", "Incognita"}, invocationCount = 1)
	private void verifyShowClasses_US14045(@Optional String username, @Optional String password, @Optional String studentProgram, @Optional String selectedClass) {

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		myLessons = new MyLessons(driver);
		AdminPage adminPage = new AdminPage(driver);

		loginPage.loginWithClassAndProgramIfNeeded(username, password, studentProgram, selectedClass);
		homePage.goToMyLessonsPage();

		if (username.equals("kb.ref")) {
			myLessons.clickOnChangeDistrictLink();
			adminPage.selectDistrictByValue("3143");
			homePage.changeGradeTo(3);
		}
		myLessons.showClassesFirstCourse();

		softAssert.assertTrue(myLessons.getCourseClassesSaveButton().getAttribute("class").contains("disabledBtn"), "Button isn't disabled!");

		myLessons.openFirstActiveSchoolShowClasses();

		for (int i = 0; i < 3; i++) { // clicks 3 times, checkbox statuses don't change otherwise
			myLessons.clickSelectAllCheckboxCourseClasses();
		}

        softAssert.assertFalse(myLessons.getCourseClassesSaveButton().getAttribute("class").contains("disabledBtn"),
                "Button is disabled!");

		myLessons.clickCourseClassesSaveButton();
        softAssert.assertTrue(myLessons.isElementPresent(myLessons.getCourseClassesChangeWarning()),
                "No change warning appears");

		myLessons.clickChangesWarningYesButton();
        myLessons.clickOkOnBeSurePopup();

		myLessons.clickCourseClassesCancelLink();
		refresh();
		myLessons.showClassesFirstCourse();

		myLessons.openFirstActiveSchoolShowClasses();

		for (int i = 0; i < 3; i++) { // clicks 3 times, checkbox statuses don't change otherwise
			myLessons.clickSelectAllCheckboxCourseClasses();
		}

		if (username.equals("kb.ref")) {
			softAssert.assertTrue(myLessons.areAllShowClassesCheckboxesCheckedKba(),
					"Not all checkboxes are checked");
		} else {
			softAssert.assertTrue(myLessons.areAllShowClassesCheckboxesChecked(),
					"Not all checkboxes are checked");
		}

        softAssert.assertTrue(myLessons.getSelectedClassesAmount().equals(myLessons.getClassesTotalAmount()),
                "Selected classes field doesn't increment correctly");

		myLessons.clickCourseClassesSaveButton();

        softAssert.assertTrue(myLessons.isElementPresent(myLessons.getCourseClassesChangeWarning()),
                "No change warning appeared");

		myLessons.clickChangesWarningNoButton();
		myLessons.clickCourseClassesCancelLink();



		softAssert.assertAll();
	}

	private int getCoursesAmount() {
		DatabaseReader dbReader = new DatabaseReader(url());
		List<String> idList = dbReader.queryArray("select cc.course_name, ccm.* \n" + 
												  "from content_course_mapping ccm \n" + 
												  "join content_courses cc using(course_id)\n" + 
												  "where (location_code = 'NJ' or location_code = '') \n" + 
												  "and district_id in(0, 3143)\n" + "and school_id in(0, 26392)\n" + 
												  "and program_id in(0, 9, 10,12,13,14,15)\n" + "and grade = 3\n" + 
												  "/*and optional = 0*/\n" + 
												  "and (ccm.end_date = 0 or ccm.end_date >=now());", 
												  "course_id");
		Set<String> idSet = new HashSet<>();
		for (String id : idList) {
			idSet.add(id);
		}
		return idSet.size();
	}



}
