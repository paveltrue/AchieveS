package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

public class Thumbnails_teacher extends BasicTestCase {
	SoftAssert softAssert = new SoftAssert();
	List<String> string = new ArrayList<String>();
	int pagecounter = 0;
	boolean isNext = true;

	@Parameters({ "loginTeacher", "passwordTeacher", "program", "classToSelectTeacher" })
	@Test(groups = { "Smoke", "Teacher", "Thumbnails","All" })
	public void testTeacherThumbnails(
			@Optional("autospanish.teach") String login,
			@Optional("autospanish.teach") String password,
			@Optional("") String program,
			@Optional("Kidbiz Class") String classToSelect)
	{

		LoginPage loginPage = new LoginPage(driver);

		loginPage.loginWithClassAndProgramIfNeeded(login, password, program, classToSelect);

		HomePage main = new HomePage(driver);

        main.goToMyLessonsByLinkNew();

		MyLessons myLessons = new MyLessons(driver);

		myLessons.goToFirstPage();

        softAssert.assertTrue(myLessons.verfiyLessonsPresence(),
                "No lessons found on the " + pagecounter + " page");

		string = myLessons.checkThumbnailsPresense();

        softAssert.assertNotNull(string,
                "The img in " + string.toString() + " lessons not found");

        softAssert.assertNotNull(myLessons.checkThumbnails(),
                "The img in " + string.toString() + " lessons are lock");

		isNext = myLessons.goToNextPage();

		softAssert.assertAll();
	}
}
