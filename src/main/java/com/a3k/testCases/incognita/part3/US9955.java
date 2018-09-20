package com.a3k.testCases.incognita.part3;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.pages.NewAdminSettingsPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;

public class US9955 extends BasicTestCase {


    private LoginPage loginPage;
    private HomePage homePage;
    private NewAdminSettingsPage adminPage;
    private String textOfHeader;
    private List<String> actNamesAndOrderOfAdminSettingsSection;
    private List<String> expNamesAndOrderOfAdminSettingsSection;
    private Set<String> actAdminSettingsSectionsStatus;
    private Set<String> expAdminSettingsSectionsStatus;
    private boolean result;
    private int y1;
    private int y2;
    private String actTextOfMessageForTeacherWithoutClass;
    private String expTextOfMessageForTeacherWithoutClass;
    private List<String> actLinksOnAdminPage;
    private List<String> expLinksOnAdminPage;
    private String actDefaultTextOfFindUserInput;
    private String expDefaultTextOfFindUserInput;


    @DataProvider
    public Object[][] getUsers() {

        Object[][] data = new Object[][]{
                {"usteach13443.alex", "usteach13443.alex", "KidBiz3000", "Alex Auto Kidbiz 3 Pro"},
                {"ussa13443.alex", "ussa13443.alex", "KidBiz3000", "Alex Auto Kidbiz 3 Pro"},
                {"usda13443.alex", "usda13443.alex", "KidBiz3000", ""},
                {"kb.ref", "kb.ref", "KidBiz3000", ""},
                {"usda13443.alexdemo", "usda13443.alexdemo", "KidBiz3000", ""},
                {"usteach13443.alexspark", "usteach13443.alexspark", "Spark3000", ""},
                {"ussa13443.alexspark", "ussa13443.alexspark", "Spark3000", ""},
                {"jbonilla", "23lamb", "KidBiz3000", ""}
        };
        return data;

    }


    @Parameters({"login", "password", "program", "selectedClass"})
    @Test(dataProvider = "getUsers", groups = {"Admin", "Archived","Incognita", "All"})
    public void check_US9955(@Optional String login,
                             @Optional String password,
                             @Optional String program,
                             @Optional String selectedClass) {

        loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, selectedClass);
        loginPage.afterLoginCheck(selectedClass);
        loginPage.afterLoginCheckWithProgram(program);

        homePage = new HomePage(driver);
        homePage.clickOnTeachersName();

        homePage.clickAdminButton();
        adminPage = new NewAdminSettingsPage(driver);


        if (login.equals("kb.ref") | login.equals("jbonilla")) {
            adminPage.selectDistrictByValue("3143");
        }

        if (login.equals("uskba13443.alexdemo")) {
            //adminPage.selectDistrict("A3K Employee Demo District (NJ) -- Current");
            adminPage.selectDistrictByValue("156");
        }


        actDefaultTextOfFindUserInput = adminPage.getAttribute(adminPage.getFindUserInput(), "placeholder").trim();
        expDefaultTextOfFindUserInput = "Find a user";
        softAssert.assertEquals(actDefaultTextOfFindUserInput, expDefaultTextOfFindUserInput, "The default text of find user input is wrong");


        textOfHeader = adminPage.getHeaderOfAdminSettingsPage().getText();
        softAssert.assertTrue(textOfHeader.contains("Admin Settings"), "The text of headers Admin Settings page is wrong. [" + textOfHeader + "]");


        actNamesAndOrderOfAdminSettingsSection = adminPage.getTextFromWebElementsByList(adminPage.getAdminSettingsSections());
        
        if (!login.contains("teach") & !login.contains("spark") & !login.contains("ussa")) {
            expNamesAndOrderOfAdminSettingsSection = Arrays.asList("User", "Class", "School and District");
        } else if (login.contains("ussa")) {
            expNamesAndOrderOfAdminSettingsSection = Arrays.asList("User", "Class", "School");
        } else {
            expNamesAndOrderOfAdminSettingsSection = Arrays.asList("User", "Class", "School");
        }

        softAssert.assertEquals(actNamesAndOrderOfAdminSettingsSection, expNamesAndOrderOfAdminSettingsSection, "The names or order of Admin Settings section is wrong.");


        actAdminSettingsSectionsStatus = adminPage.getAttributesFromItemsOfListBySet(adminPage.getAdminSettingsSections(), "class");
        expAdminSettingsSectionsStatus = new HashSet<>();
        expAdminSettingsSectionsStatus.add("sectionHeader toggleView expanded");
        softAssert.assertEquals(actAdminSettingsSectionsStatus, expAdminSettingsSectionsStatus, "The admin settings section are collapsed.");


        adminPage.collapseAllAdminSettingsSections();


        for (int i = 0; i < adminPage.getAdminSettingsSections().size(); i++) {
            $(adminPage.getAdminSettingsSections().get(i)).click();
            List<String> tempArray = adminPage.getAttributesFromItemsOfList(adminPage.getAdminSettingsSections(), "class");

            for (int j = 0; j < tempArray.size(); j++) {
                if (j != i) {
                    boolean tempResult = tempArray.get(j).equals("sectionHeader toggleView");
                    softAssert.assertTrue(tempResult, "The admin setting section - " + adminPage.getAdminSettingsSections().get(j).getText() + " is not collapsed.");
                }
            }
            adminPage.getAdminSettingsSections().get(i).click();
        }


        y1 = adminPage.getHeaderOfAdminSettingsPage().getLocation().getY();
        y2 = adminPage.getSubheaderOfAdminSettingsPage().getLocation().getY();
        result = y2 > y1;
        softAssert.assertTrue(result, "The subheader is not under header of the page.");

        softAssert.assertAll();
    }
}
