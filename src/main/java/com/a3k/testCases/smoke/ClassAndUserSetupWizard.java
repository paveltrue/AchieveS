package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import io.qameta.allure.Step;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class ClassAndUserSetupWizard extends BasicTestCase {

    String createNewUserPrePageHandle;
    String createNewClassPageHandle;
    static CreateNewClassPage createNewClassPage;


    @Parameters({"loginTeacherCSW", "passwordTeacherCSW", "program", "classToSelectTeacherCSW", "language", "testName"})
    @Test(groups = {"Search", "Smoke", "All"}, invocationCount = 1)
    public void teacherSearch(
            @Optional("cswkidbizen.three") String login,
            @Optional("cswkidbizen.three") String password,
            @Optional("") String program,
            @Optional("Access Class 3g") String classToSelect,
            @Optional("english") String language,
            @Optional("spark") String testName) {

        login(login, password, program, classToSelect);
        new HomePage(driver).goToAdminPage();

        AdminPage adminPage = openAdminPage();
        adminPage.clickCreateNewClass();
        adminPage.switchToNextWindow();

        createNewClass("Testing Class");

        createNewClassPageHandle = getWebDriver().getWindowHandle();
        createNewClassPage.switchToNextWindow();

        createNewUser();

        verifyIsClassListCorrect();

        verifyIsMembersListCorrect();

        verifyIsClassSetupCorrect(language);

        softAssert.assertAll();
    }

    @Step("Login with username {login}, password {password}, program " +
            "{program} " +
            "and class {classToSelect}")
    private void login(String login, String password, String program,
                       String classToSelect) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, classToSelect);
    }

    @Step
    private AdminPage openAdminPage() {

        HomePage mainPage = new HomePage(driver);
        mainPage.goToAdminPage();

        return new AdminPage(driver);
    }

    @Step("Create a new class with name {className}")
    private void createNewClass(String className) {
        createNewClassPage = new CreateNewClassPage(driver);
        createNewClassPage.fillNewClassFields(className);
        createNewClassPage.clickNext();
        createNewClassPage.clickAdd();
    }

    @Step
    private void createNewUser() {
        CreateNewUsersPrePage newUsersPrePage = new CreateNewUsersPrePage(driver);
        createNewUserPrePageHandle = getWebDriver().getWindowHandle();

        newUsersPrePage.clickCreateNewUser();
        newUsersPrePage.switchToNextWindow();

        CreateNewUsersPage newUsersPage = new CreateNewUsersPage(driver);
        newUsersPage.createANewUser();

        if (System.getProperty("os.name").contains("indows")) {
            softAssert.assertTrue(newUsersPage.isPasswordGeneratedAutomatically(),
                    "Password isn't generated automatically.");
        }

        newUsersPage.clickSaveChanges();
        newUsersPrePage.closeAlert();

        newUsersPage.switchBack();
        newUsersPage.switchBack();
    }


    @Step
    private void verifyIsClassListCorrect() {
        softAssert.assertTrue(createNewClassPage.isYourClassMembersCorrect(),
                "Your class members list isn't correct.");
        createNewClassPage.clickNextOnClassListView();
    }

    @Step
    private void verifyIsMembersListCorrect() {
        softAssert.assertTrue(createNewClassPage.isMembersCorrect(),
                "List of members isn't correct.");
        createNewClassPage.clickFinish();
    }

    @Step
    private void verifyIsClassSetupCorrect(String language) {
        softAssert.assertTrue(createNewClassPage.isClassWasSetUp(language),
                "New class wasn't successfully set up.");
        createNewClassPage.clickClose();
    }
}
