package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MailboxPage;
import io.qameta.allure.Issue;
import io.qameta.allure.Step;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class A3k_Mail extends BasicTestCase {

    @Step("Login with username {login}, password {password}, program " +
            "{program} " +
            "and class {classToSelect}")
    private void login(String login, String password, String program, String classToSelect) {
        new LoginPage(getWebDriver()).loginWithClassAndProgramIfNeededWithAlert(login, password, program, classToSelect);
    }

    @Issue("DE25998")
    @Parameters({"loginTeacher", "passwordTeacher", "program", "classToSelectTeacher"})
    @Test(dataProvider = "getUsers", groups = {"Smoke", "Mail", "Student",
            "All"}, invocationCount = 1)
    public void studentMail(@Optional("autokidbizsp.stud") String login, @Optional("autokidbizsp.stud") String password, @Optional("") String program, @Optional("Kidbiz Class") String classToSelect)
            throws InterruptedException {
        login(login, password, program, classToSelect);

        HomePage homePage = new HomePage(driver);
        homePage.goToMailboxPage();

        MailboxPage mailbox = new MailboxPage(driver);
        mailbox.openWriteEmailSection();

        String to = login.substring(login.indexOf(".") + 1);
        to += ", " + login.substring(0, login.indexOf("."));
        mailbox.fillAllInWriteEmailAndSend(to);
        softAssert.assertTrue(mailbox.checkAlertAfterSendOnWriteEmail(),
                "Alert isn't displayed");

        mailbox.openSentEmailsFolder();
        mailbox.openWriteEmailSection();

        mailbox.fillAllInWriteEmail(to);
        mailbox.openEmailSection();

        boolean res = mailbox.checkPresenceOfMessageThatDisplayedAfterNotSavingSending();
        softAssert.assertTrue(res, "The following message appears: "
                + "\"Your email was saved in the Drafts folder.\" OK");

        mailbox.openDraftsFolder();
        softAssert.assertTrue(mailbox.checkPresenceMessageInFolder(),
                "The draft letter NOT displays in the Drafts folder");
        mailbox.clickOnDeleteButtonAndAcceptAlertIfExists();

        mailbox.openTrashFolder();
        softAssert.assertTrue(mailbox.checkPresenceMessageInFolder(),
                "The deleted letter NOT displays in the Trash folder");

        mailbox.openEmailGroupSection();
        mailbox.clickNewGroupButton();

        mailbox.fillAllOnEmailGroupsAndCreate();
        softAssert.assertTrue(mailbox.checkPresenceOfCreatedGroup(),
                "The Email Group NOT appeared in the list of the Email "
                        + "Groups tab");

        mailbox.clickOnManageFoldersLink();
        mailbox.openInboxFolder();

        mailbox.clickOnCheckAllLink();

        int amountOfSystemLessons = mailbox.amountOfSystemLessons();
        logger.info("Remove all email items");
        mailbox.clickOnDeleteButtonAndAcceptAlertIfExists();
        int amountOfSystemLessonsAfterDeleting = mailbox.amountOfSystemLessons();
        mailbox.waitForPageToLoad();
        if (!mailbox.isMailboxEmpty()) {
            softAssert.assertTrue(
                    amountOfSystemLessons ==
                            amountOfSystemLessonsAfterDeleting,
                    "All emails are NOT deleted,"
                            + " except the letter from the system");
        }
        softAssert.assertAll();
    }

    @Parameters({"loginTeacher", "passwordTeacher", "program", "classToSelectTeacher"})
    @Test(groups = {"Smoke", "Mail", "Teacher", "All"})

    public void teacherMail(@Optional("autospanish.teach") String login,
                            @Optional("autospanish.teach") String password,
                            @Optional("") String program,
                            @Optional("Empower Class") String classToSelect) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeeded(login, password, program, classToSelect);

        HomePage mainpage = new HomePage(driver);
        mainpage.goToMailboxPage();

        MailboxPage mailbox = new MailboxPage(driver);
        mailbox.openInboxFolder();

        mailbox.openSentEmailsFolder();
        mailbox.openWriteEmailSection();

        String to = login.substring(login.indexOf(".") + 1);
        to += ", " + login.substring(0, login.indexOf("."));
        mailbox.fillAllInWriteEmailAndSend(to);
        softAssert.assertTrue(mailbox.checkAlertAfterSendOnWriteEmail(), "Alert isn't displayed");

        mailbox.openSentEmailsFolder();
        softAssert.assertAll();

    }

    @DataProvider
    public Object[][] getUsers(ITestContext context) {
        String suiteName = context.getCurrentXmlTest().getSuite().getName();
        Object[][] data;
        switch (suiteName) {
            case "SmokeTest":
                data = new Object[][]{{"teenbizstud.one", "teenbizstud.one", "", "Pro Class 6g"},
                        {"empowerstud.one", "empowerstud.one", "", "Empower Class 10g"},
                        {"kidbizstud.one", "kidbizstud.one", "", "Access Class 3g"}
                };
                break;
            case "SmokeTestUK":
                data = new Object[][]{{"teenbizstud.uk", "teenbizstud.uk", "", ""},
                        {"empowerstud.uk", "empowerstud.uk", "", ""},
                        {"kidbizstud.uk", "kidbizstud.uk", "", ""}
                };
                break;
            case "SpanishTest":
                data = new Object[][]{{"autoteenbizsp.stud", "autoteenbizsp.stud", "", "Second Teenbiz Class"},
                        {"autoempowersp.stud", "autoempowersp.stud", "", "Empower Class"},
                        {"autokidbizsp.stud", "autokidbizsp.stud", "", "Kidbiz Class"}
                };
                break;

            case "SparkTest":
                data = new Object[][]{{"sparkstudent.one", "sparkstudent" +
                        ".one","", "Spark First Class"},
                        {"sparkspanish.stud", "sparkspanish.stud","","Spark " +
                                "First Class"}
                };
                break;

            default:
                data = new Object[][]{{"teenbizstud.one", "teenbizstud.one", "", "Pro Class 6g"},
                        {"empowerstud.one", "empowerstud.one", "", "Empower Class 10g"},
                        {"kidbizstud.one", "kidbizstud.one", "", "Access Class 3g"}};
                break;
        }
        return data;
    }
}
