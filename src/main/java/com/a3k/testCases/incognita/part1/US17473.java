package com.a3k.testCases.incognita.part1;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.pages.SearchWidgetPage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

public class US17473 extends BasicTestCase {

	private LoginPage loginPage;
	private HomePage homePage;
	private MyLessons myLessons;
	private SearchWidgetPage searchWidgetPage;

	@DataProvider
	private Object[][] getUsers() {
		return new Object[][] { 
			{ "usda17473.pooh", "usda17473.pooh", "KidBiz3000", "" },
//			{ "ukda17473.pooh", "ukda17473.pooh", "KidBiz3000", "" },
			{ "ussasummer17473", "ussasummer17473", "TeenBiz3000", "" }
		};
	}

    @Test(dataProvider = "getUsers", groups = {"Delivery Schedule", "Incognita", "All"}, invocationCount = 1)
	private void verifyRestrictedLessons_US17473(@Optional String username, @Optional String password, @Optional String studentProgram, @Optional String selectedClass) {
		logger.debug("Started checking adding restricted lessons to collection for English users");

		loginPage = new LoginPage(driver);
		homePage = new HomePage(driver);
		myLessons = new MyLessons(driver);
		searchWidgetPage = new SearchWidgetPage(driver);


		loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);
		homePage.goToMyLessonsPage();

		myLessons.clickOnBuiltLessonCollectionBy();
		myLessons.clickOnCreateCollectionBy();

		if (username.contains("summer")) {
			checkIncorrectProduct(username, MyLessons.Product.PRO, "Achieve Intensive (Grade 6)");
		} else {
			checkIncorrectProduct(username, MyLessons.Product.PRO, "Fluency (Grades 2-12)");
		}

		dragAcceptableLesson("News");

		checkAllProducts(username);
		selectClasses(username);

		softAssert.assertAll();
	}

    @Step
    private void selectClasses(String username) {
        logger.debug("Started checking adding lessons with class restrictions");

        /**
         * Situational variables, not worthy of declaring in MyLessons
         */

        By summerSchoolPath;
        By summerSchoolRadioPath;
        By summerSchoolProClassPath;

        if (username.contains("us")) {
            summerSchoolPath = By.xpath(".//*[@id = 'showClasses-dialog']//*[@class = 'schoolLabel']/text()[contains(.,'Summer')]//parent::*");
            summerSchoolRadioPath = By.xpath(".//*[@id = 'showClasses-dialog']//*[@class = 'schoolLabel']/text()" + "[contains(.,'Summer')]/../../following-sibling::*//*[@type='radio' and contains(@class, 'someClasses')]");
            summerSchoolProClassPath = By.xpath(".//*[@id = 'showClasses-dialog']//*[@class = 'schoolLabel']/text()[contains(.,'Summer')]/../../following-sibling::*//*[@class='classListContainer']//*/text()[contains(.,'Pro 12')]//parent::*");
        } else {
            summerSchoolPath = By.xpath(".//*[@id = 'showClasses-dialog']//*[@class = 'schoolLabel']/text()[contains(.,'DD test')]//parent::*");
            summerSchoolRadioPath = By.xpath(".//*[@id = 'showClasses-dialog']//*[@class = 'schoolLabel']/text()" + "[contains(.,'DD test')]/../../following-sibling::*//*[@type='radio' and contains(@class, 'someClasses')]");
            summerSchoolProClassPath = By.xpath(".//*[@id = 'showClasses-dialog']//*[@class = 'schoolLabel']/text()[contains(.,'DD test')]/../../following-sibling::*//*[@class='classListContainer']//*/text()[contains(.,'Pro')]//parent::*");
        }

        if (username.contains("uk")) {
            myLessons.changeCollectionGrade(8);
        } else {
            myLessons.changeCollectionGrade(12); // change back to 12?
        }

        myLessons.clickShowClasses();
        myLessons.clearClasses();

        if (username.contains("da")) {
            myLessons.click(summerSchoolPath);
        }
        myLessons.click(summerSchoolRadioPath);
        myLessons.click(summerSchoolProClassPath);
        myLessons.clickOnContinueButtonInShowClassPopUp();

        myLessons.clickOnAddLessonButton();
        myLessons.expandAdvancedOptions();

        if (homePage.isAdminLanguageSelectorPresent()) {
            if (homePage.getLanguage().contains("nglish")) {
                if (username.contains("summer")) {
                    searchWidgetPage.selectInCourseDDL("Achieve Intensive (Grades 10-12)");
                } else {
                    if (username.contains("uk")) {
                        searchWidgetPage.selectInCourseDDL("Fluency (Years 2-12)");
                    } else {
                        searchWidgetPage.selectInCourseDDL("Fluency (Grades 2-12)");
                    }
                }
            } else {
                if (username.contains("summer")) {
                    searchWidgetPage.selectInCourseDDL("Achieve Intensivo (Grados 10-12)");
                } else {
                    searchWidgetPage.selectInCourseDDL("Fluidez (Grados 2-12)");
                }
            }
        } else {
            if (username.contains("summer")) {
                searchWidgetPage.selectInCourseDDL("Achieve Intensive (Grades 10-12)");
            } else {
                if (username.contains("uk")) {
                    searchWidgetPage.selectInCourseDDL("Fluency (Years 2-12)");
                } else {
                    searchWidgetPage.selectInCourseDDL("Fluency (Grades 2-12)");
                }
            }
        }
        myLessons.clickSearch();

        int numberBefore = myLessons.getLessonsAmount();
        searchWidgetPage.addFirstLesson();
        softAssert.assertTrue(myLessons.isAlertDialogPresent(), "No popup present");

        myLessons.clickOkButtonOfLessonCannotBeAddedMessagePresent();
        int numberAfter = myLessons.getLessonsAmount();

        softAssert.assertEquals(numberAfter, numberBefore, "selectClasses Failed");
        myLessons.expandSectionHeaders();

    }

    @Step
    private void checkAllProducts(String username) {
	    if (homePage.isAdminLanguageSelectorPresent()) {
            if (homePage.getLanguage().contains("nglish")) {
                myLessons.selectInProductDDL(MyLessons.Product.ALL_PRODUCTS);
            } else {
                myLessons.selectInProductDDL(MyLessons.Product.ALL_PRODUCTS_ESP);
            }
        } else {
            myLessons.selectInProductDDL(MyLessons.Product.ALL_PRODUCTS);

        }


        myLessons.clickOnAddLessonButton();
        myLessons.expandAdvancedOptions();
        if (homePage.isAdminLanguageSelectorPresent()) {
            if (homePage.getLanguage().contains("nglish")) {
                if (username.contains("summer")) {
                    searchWidgetPage.selectInCourseDDL("Achieve Intensive (Grade 6)");
                } else {
                    if (username.contains("uk")) {
                        searchWidgetPage.selectInCourseDDL("Fluency (Years 2-12)");
                    } else {
                        searchWidgetPage.selectInCourseDDL("Fluency (Grades 2-12)");
                    }
                }
            } else {
                if (username.contains("summer")) {
                    searchWidgetPage.selectInCourseDDL("Achieve Intensivo (Grado 6)");
                } else {
                    searchWidgetPage.selectInCourseDDL("Fluidez (Grados 2-12)");
                }
            }
        } else {
            if (username.contains("summer")) {
                searchWidgetPage.selectInCourseDDL("Achieve Intensive (Grade 6)");
            } else {
                if (username.contains("uk")) {
                    searchWidgetPage.selectInCourseDDL("Fluency (Years 2-12)");
                } else {
                    searchWidgetPage.selectInCourseDDL("Fluency (Grades 2-12)");
                }
            }
        }
        myLessons.clickSearch();

        int numberBefore = myLessons.getLessonsAmount();
        searchWidgetPage.addFirstLesson();

        checkDialog();
        int numberAfter = myLessons.getLessonsAmount();
        softAssert.assertNotEquals(numberAfter, numberBefore, "Lesson amount is wrong");

        myLessons.deleteFirstCollectionLesson();
        myLessons.expandSectionHeaders();
    }

    @Step
    private void checkDialog() {
        softAssert.assertFalse(myLessons.isElementPresentBy(By.xpath("//*[contains(@class, 'alert_dialog')]")), "Popup is present");
    }

    @Step
    private void dragAcceptableLesson(String courseName) {
        myLessons.clickOnAddLessonButton();
        myLessons.expandAdvancedOptions();

        myLessons.selectInCourseDDL(courseName);
        myLessons.clickSearch();

        int numberBefore = myLessons.getLessonsAmount();
        searchWidgetPage.addFirstLesson();

        int numberAfter = myLessons.getLessonsAmount();
        softAssert.assertNotEquals(numberAfter, numberBefore, "Lesson amount is wrong");

        myLessons.deleteFirstCollectionLesson();
        myLessons.expandSectionHeaders();
    }

    @Step
    private void checkIncorrectProduct(String username, MyLessons.Product product, String course) {
        logger.debug("Checking for incorrect product");
        myLessons.selectInProductDDL(product);
        myLessons.clickOnAddLessonButton();

        myLessons.clickOnTab("Search");
        myLessons.expandAdvancedOptions();

        if (username.contains("uk")) {
            course = course.replace("Grade", "Year");
        }

        searchWidgetPage.selectInCourseDDL(course);

        myLessons.clickSearch();
        int numberBefore = myLessons.getLessonsAmount();

        searchWidgetPage.addFirstLesson();
        softAssert.assertTrue(myLessons.isAlertDialogPresent(), "There's no popup");

        myLessons.clickOkButtonOfLessonCannotBeAddedMessagePresent();
        int numberAfter = myLessons.getLessonsAmount();

        softAssert.assertEquals(numberAfter, numberBefore, "Restricted lesson adds");
        myLessons.expandSectionHeaders();
    }
}
