package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MailboxPage;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class DeleteMailFolders extends BasicTestCase {

    @Parameters({"login", "password", "classToSelect", "language"})
    @Test(dataProvider = "getUsers", groups = {"literacyteam"})

    public void checkPart2OfBonusLesson(@Optional("teenbizstud.one") String login,
                                        @Optional("teenbizstud.one") String password,
                                        @Optional("Pro Class 6g") String classToSelect,
                                        @Optional("english") String language) {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(login, password, classToSelect);

        HomePage homePage = new HomePage(driver);
        homePage.goToMailboxPage();


        MailboxPage mailboxPage = new MailboxPage(driver);
        mailboxPage.clickOnManageFoldersLink();
        mailboxPage.deleteAllUsersMailFolders();

        mailboxPage.openTrashFolder();
        mailboxPage.emptyTrashFolder();

        mailboxPage.openSentEmailsFolder();
        mailboxPage.emptySentEmailFolder();
    }

    @DataProvider
    public Object[][] getUsers(ITestContext context) {

        String testName = context.getCurrentXmlTest().getName();
        Object[][] data = new Object[3][4];

        switch (testName) {
            case "smoke":
                data = new Object[][]{
                        {"teenbizstud.one", "teenbizstud.one", "Pro Class 6g", "english"},
                        {"empowerstud.one", "empowerstud.one", "Empower Class 10g", ""},
                        {"kidbizstud.one", "kidbizstud.one", "Access Class 3g", ""},
                        {"kidbizteach.one", "kidbizteach.one", "Access Class 3g", ""},
                };

                break;

            case "spanish":
                data = new Object[][]{
                        {"autoteenbizsp.stud", "autoteenbizsp.stud", "Second Teenbiz Class", ""},
                        {"autokidbizsp.stud", "autokidbizsp.stud", "Kidbiz Class", ""},
                        {"autoempowersp.stud", "autoempowersp.stud", "Empower Class", ""},
                        {"autospanish.teach", "autospanish.teach", "Kidbiz Class", ""}
                };

                break;

            default:
                data = new Object[][]{
                        {"teenbizstud.one", "teenbizstud.one", "Pro Class 6g", "english"},
                        {"empowerstud.one", "empowerstud.one", "Empower Class 10g", ""},
                        {"kidbizstud.one", "kidbizstud.one", "Access Class 3g", ""},
                        {"kidbizteach.one", "kidbizteach.one", "Access Class 3g", ""},
                };

                break;
        }
        return data;
    }
}
