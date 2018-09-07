package com.a3k.testCases.incognita;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.a3k.pages.SearchWidgetPage;
import com.codeborne.selenide.ElementsCollection;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$$;

public class US14588 extends BasicTestCase {

    private LoginPage loginPage;
    private HomePage homePage;
    private MyLessons myLessons;
    private SearchWidgetPage searchWidgetPage;


    @DataProvider
    private Object[][] getAdmins() {
        return new Object[][]{

                {"uksa14588.alexnoclass", "uksa14588.alexnoclass", "KidBiz3000", ""},
                {"ussa14588.alexnoclass", "ussa14588.alexnoclass", "KidBiz3000", ""},
                {"usda14588.alexnoclass", "usda14588.alexnoclass", "KidBiz3000", ""},
                {"ukda14588.alexnoclass", "ukda14588.alexnoclass", "KidBiz3000", ""},
                {"kb.ref", "kb.ref", "KidBiz3000", ""}
        };
    }


    @Test(dataProvider = "getAdmins", groups = {"Lesson List", "Incognita", "All"})
    private void verifyDeletingLessons_US14588(@Optional String username,
                                               @Optional String password,
                                               @Optional String studentProgram,
                                               @Optional String selectedClass) {

        logger.debug("Started verifying lessons deletion from collection");

        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        searchWidgetPage = new SearchWidgetPage(driver);

        loginPage.loginWithClassAndProgramIfNeededWithAlert(username, password, studentProgram, selectedClass);

        myLessons = homePage.goToMyLessonsPage();

        myLessons.clickOnBuiltLessonCollectionBy();
        myLessons.clickOnCreateCollectionBy();

        myLessons.clickOnAddLessonButton();
        searchWidgetPage.enterTextInSearchBar("money");

        myLessons.clickSearch();
        int beforeAdd = myLessons.getLessonsAmount();

        int counterBeforeAdding = myLessons.getLessonsCount();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        searchWidgetPage.dragAndDropFirstNotAddedLessonBy();

        verifyNumbers();

        int afterAdd = myLessons.getLessonsAmount();
        softAssert.assertTrue(afterAdd > beforeAdd,
                "Lesson haven't been added");

        int counterAfterAdding = myLessons.getLessonsCount();
        softAssert.assertTrue(counterAfterAdding > counterBeforeAdding,
                "Counter doesn't increment");

        myLessons.deleteFirstLessonFromCollection();
        verifyNumbers();

        int counterAfterDeleting = myLessons.getLessonsCount();
        softAssert.assertTrue(counterAfterDeleting <= counterAfterAdding,
                "Counter doesn't decrement");

        int afterDelete = myLessons.getLessonsAmount();
        softAssert.assertTrue(afterDelete < afterAdd,
                "Lesson haven't been removed");


        softAssert.assertAll();
    }

    //C88166
    private void verifyNumbers() {
        ElementsCollection numList = myLessons.getLessonNumbers();
        for (int i = 0; i < $$(numList).size(); i++) {
            int num = Integer.parseInt(numList.get(i).getText());
            softAssert.assertTrue(num == (i + 1), "Lesson number is wrong");
        }
    }


}
