package com.a3k.testCases.incognita.part3;

import com.a3k.BasicTestCase;
import com.a3k.pages.AdminPage;
import com.a3k.pages.CoursesPage;
import com.a3k.pages.HomePage;
import com.a3k.pages.LoginPage;
import com.a3k.utils.db.DatabaseReader;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.WebDriverRunner.url;

public class US10709_NewIntroductoryText extends BasicTestCase {

    String state;
    String ELA;
    String stateAssessmentAbbr;
    String fullStateName;
    String activeGrade;
    String activeLanguage;

    String expectedIntroTextHeader;
    Set<String> expectedSubheadersInText;
    Set<String> expectedItemsOfParagraphs;
    Set<String> expectedFootnotesOfParagraphs;
    String expectedIntroTextBottom;
    Set<String> expectedColorOfSubheadersKidbiz;
    Set<String> expectedColorOfSubheadersTeenbiz;
    Set<String> expectedColorOfSubheadersEmpower;
    Set<String> expectedIconUrlInfoKidbiz;
    Set<String> expectedIconUrlInfoTeenbiz;
    Set<String> expectedIconUrlInfoEmpower;
    List<Boolean> expectedIsArrowsDisplayed;
    List<String> expectedArrowsAttribute;


    String introTextHeader;
    Set<String> subheadersInText;
    Set<String> itemsOfParagraphs;
    Set<String> footnotesOfParagraphs;
    String introTextBottom;
    Set<String> colorOfSubheaders;
    Set<String> iconUrlInfo;
    List<Boolean> isArrowsDiplayed;
    List<String> arrowsAttribute;


    @Parameters({"login", "password", "program", "language"})
    @Test(groups = {"Home Page", "Courses Page", "Archived","Incognita", "All"}, invocationCount = 5)
    public void check_US10709(@Optional("kb.ref")String login,
                              @Optional("kb.ref")String password,
                              @Optional("KidBiz3000")String program,
                              @Optional("english") String language){
        DatabaseReader databaseReader = new DatabaseReader(url());

        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, "");

        HomePage homePage = new HomePage(driver);
        activeLanguage = homePage.getLanguage();

        homePage.closeWalkmeNew();
        homePage.clickOnCoursesButton();
        CoursesPage coursesPage = new CoursesPage(driver);

        state = coursesPage.getStateCode();
        fullStateName = coursesPage.getFullStateName(activeLanguage);

        ELA = databaseReader.query("select * from standard_type_title where standard_type_id=2 and local_code='" + state + "'", "title_text");


        expectedIntroTextHeader = "Designed for " + fullStateName + " Success!\n"
                + "Your Achieve3000 " + fullStateName + " Edition is specifically designed to help all of your students meet " + fullStateName + "'s academic standards, practice for assessments, and reach college and career readiness targets."
                + " The customized curriculum automatically delivers cross-curricular lessons aligned to " + fullStateName + "'s grade-level standards for language arts, science, and social studies, all while providing daily opportunities to build the strong literacy and critical-thinking skills needed for success.";


        expectedSubheadersInText = new HashSet<>();
        expectedSubheadersInText.add("Build Strong Reading & Writing Skills in Language Arts");
        expectedSubheadersInText.add("Develop Disciplinary Literacy in the Content Areas");

        expectedItemsOfParagraphs = new HashSet<>();
        if (ELA.contains(fullStateName))
            expectedItemsOfParagraphs.add("Custom curriculum is designed around engaging, high-interest lessons aligned to the " + ELA + " for each grade level.");
        else {
            //TODO ELA for NY fix by hardcode
            ELA = ELA + " Standards";
            expectedItemsOfParagraphs.add("Custom curriculum is designed around engaging, high-interest lessons aligned to the " + fullStateName + " " + ELA + " for each grade level.");
        }
        expectedItemsOfParagraphs.add("5-Step Literacy Routine teaches academic vocabulary, comprehension strategies, critical-thinking skills, and evidence-based writing.");
        expectedItemsOfParagraphs.add("Customization tools make it easy to add, remove, or reschedule lessons to match your exact scope and sequence.");
        expectedItemsOfParagraphs.add("Optional textbook courses* reinforce and extend your current language arts curriculum to maximize learning gains.");
        expectedItemsOfParagraphs.add("Custom courses support students' mastery of the " + fullStateName + " science and social studies standards for each grade level.");
        expectedItemsOfParagraphs.add("Each lesson delivers the same grade-appropriate, nonfiction content while automatically tailoring to each student's individual reading level to ensure all students can engage in grade-level, standards-aligned instruction.");
        expectedItemsOfParagraphs.add("Build students' disciplinary knowledge and content-area vocabulary while simultaneously improving their literacy skills.");
        expectedItemsOfParagraphs.add("Point-of-use teacher resources support all teachers, including content-area teachers, in literacy instruction.");

        expectedFootnotesOfParagraphs = new HashSet<>();
        expectedFootnotesOfParagraphs.add("*Additional purchase required. Please contact your Achieve3000 representative for more information.");

        expectedIntroTextBottom = "Your current list of courses is displayed below. You can add, remove, or edit courses for this class, as well as customize the lesson schedule and instructional content delivered to your students.";


        expectedIconUrlInfoKidbiz = new HashSet<>();
        expectedIconUrlInfoKidbiz.add("kidbiz");

        expectedIconUrlInfoTeenbiz = new HashSet<>();
        expectedIconUrlInfoTeenbiz.add("teenbiz");

        expectedIconUrlInfoEmpower = new HashSet<>();
        expectedIconUrlInfoEmpower.add("empower");


        expectedColorOfSubheadersKidbiz = new HashSet<>();
        expectedColorOfSubheadersKidbiz.add("rgba(217, 90, 43, 1)");

        expectedColorOfSubheadersTeenbiz = new HashSet<>();
        expectedColorOfSubheadersTeenbiz.add("rgba(0, 102, 0, 1)");

        expectedColorOfSubheadersEmpower = new HashSet<>();
        expectedColorOfSubheadersEmpower.add("rgba(51, 102, 153, 1)");


        expectedIsArrowsDisplayed = new ArrayList<>();
        expectedIsArrowsDisplayed.add(true);
        expectedIsArrowsDisplayed.add(true);

        isArrowsDiplayed = coursesPage.isArrowsDisplayed();
        softAssert.assertEquals(isArrowsDiplayed, expectedIsArrowsDisplayed,
                "Arrows are not displayed correct");


        // Expand All paragraphs
        coursesPage.clickOnAllSubheaders();
        softAssert.assertEquals(coursesPage.isParagraphsDisplayed(), true,
                "Paragraphs are not displayed but should be ");

        // When paragraphs expanded arrows should have word "expanded" in image file name
        expectedArrowsAttribute = new ArrayList<>();
        expectedArrowsAttribute.add("course_arrow_expanded");
        expectedArrowsAttribute.add("course_arrow_expanded");

        arrowsAttribute = coursesPage.getAttributeOfArrows();
        softAssert.assertEquals(arrowsAttribute, expectedArrowsAttribute,
                "Type of arrows is not correct when paragraphs expanded");


        //Collapse All paragraphs
        coursesPage.clickOnAllSubheaders();
        softAssert.assertEquals(coursesPage.isParagraphsDisplayed(), false,
                "Paragraphs are displayed but should not be ");

        // When paragraphs collapsed arrows should have the word "collapsed" in image file name
        expectedArrowsAttribute.clear();
        expectedArrowsAttribute.add("course_arrow_collapsed");
        expectedArrowsAttribute.add("course_arrow_collapsed");

        arrowsAttribute.clear();
        arrowsAttribute = coursesPage.getAttributeOfArrows();
        softAssert.assertEquals(arrowsAttribute, expectedArrowsAttribute,
                "Type of arrows is not correct  when paragraphs collapsed");


        //Expand All paragraph
        coursesPage.clickOnAllSubheaders();

        //Collapse Second paragraph
        coursesPage.clickOnSecondSubheader();
        softAssert.assertEquals(coursesPage.isFirstParagraphDisplayed(), true,
                "The First Paragraph is not displayed but should be ");

        //Expand Second paragraph
        coursesPage.clickOnSecondSubheader();


        introTextHeader = coursesPage.getIntroTextHeader();
        subheadersInText = coursesPage.getSubheadersInText();
        itemsOfParagraphs = coursesPage.getItemsOfParagraphs();
        footnotesOfParagraphs = coursesPage.getFootnotesOfParagraphs();
        introTextBottom = coursesPage.getIntroTextBottom();


        softAssert.assertEquals(introTextHeader, expectedIntroTextHeader,
                "Intro text header is incorrect");
        softAssert.assertEquals(subheadersInText, expectedSubheadersInText,
                "Subheaders are incorrect");
        softAssert.assertEquals(itemsOfParagraphs, expectedItemsOfParagraphs,
                "Items of paragraph are incorrect");
        softAssert.assertEquals(footnotesOfParagraphs, expectedFootnotesOfParagraphs,
                "Footnotes of paragraphs are incorrect");
        softAssert.assertEquals(introTextBottom, expectedIntroTextBottom,
                "Intro text bottom is incorrect");

        coursesPage.changeGradeTo(1);

        softAssert.assertEquals(coursesPage.getIconsUrlInfo(), expectedIconUrlInfoKidbiz,
                "Icon of paragraph in KidBiz is incorrect");

        coursesPage.changeGradeTo(6);
        sleep(1500);
        coursesPage.closeWalkmeCivics();
        coursesPage.clickOnAllSubheaders();

        softAssert.assertEquals(coursesPage.getIconsUrlInfo(), expectedIconUrlInfoTeenbiz,
                "Icon of paragraph in TeenBiz is incorrect");

        coursesPage.changeGradeTo(9);
        coursesPage.clickOnAllSubheaders();

        softAssert.assertEquals(coursesPage.getIconsUrlInfo(), expectedIconUrlInfoEmpower, "Icon of paragraph in Empower is incorrect");


//*********************************************************************** Set School and Class ***************************************************************************************


        coursesPage.clickOnLinkNearDistrictName();
        AdminPage adminPage = new AdminPage(driver);

        //Achieve3000 QA District (NY) -- Current;
        adminPage.selectDistrictByValue("1528");
        coursesPage.changeGradeTo(3);
        coursesPage.selectSchool("BEAP & eScience & Summer & LSSA 2015 School");
        coursesPage.selectClass("Access Class 3g");
        sleep(500);
        coursesPage.clickOnSubmitButton();


        activeGrade = coursesPage.getActiveGrade();
        stateAssessmentAbbr = databaseReader.query("select * from state_assessment where state_code='" + state + "' and grade_level=" + activeGrade, "assessment_abrev");


        expectedIntroTextHeader = "Designed for " + fullStateName + " Success!\n"
                + "Your Achieve3000 " + fullStateName + " Edition is specifically designed to help all of your students meet " + fullStateName + "'s academic standards, practice for " + stateAssessmentAbbr + " assessments, and reach college and career readiness targets."
                + " The customized curriculum automatically delivers cross-curricular lessons aligned to " + fullStateName + "'s grade-level standards for language arts, science, and social studies, "
                + "all while providing daily opportunities to build the strong literacy and critical-thinking skills needed for " + stateAssessmentAbbr + " success.";

        // Expand All paragraphs
        coursesPage.clickOnAllSubheaders();


        introTextHeader = coursesPage.getIntroTextHeader();
        subheadersInText = coursesPage.getSubheadersInText();
        itemsOfParagraphs = coursesPage.getItemsOfParagraphs();
        footnotesOfParagraphs = coursesPage.getFootnotesOfParagraphs();
        introTextBottom = coursesPage.getIntroTextBottom();


        expectedSubheadersInText.add("Targeted Practice for " + stateAssessmentAbbr);

        expectedItemsOfParagraphs.add("Daily practice for " + stateAssessmentAbbr + " includes responding to questions that mirror those on " + stateAssessmentAbbr + " as well as evidence-based writing tasks.");
        expectedItemsOfParagraphs.add(stateAssessmentAbbr + " Challenge courses provide targeted and specific practice leading up to " + stateAssessmentAbbr + " assessment.");
        expectedItemsOfParagraphs.add("Integrates technology-enhanced items to help students achieve even greater depths of knowledge.");
        expectedItemsOfParagraphs.add("Features drag-and-drop sequencing, click-to-highlight evidence tasks, multi-part items, and multi-select multiple-choice questions.");


        softAssert.assertEquals(footnotesOfParagraphs, expectedFootnotesOfParagraphs, "Footnotes of paragraphs are incorrect after set any school and class");
        softAssert.assertEquals(introTextBottom, expectedIntroTextBottom, "Intro text bottom is incorrect after set any school and class");

        softAssert.assertAll();
    }
}
