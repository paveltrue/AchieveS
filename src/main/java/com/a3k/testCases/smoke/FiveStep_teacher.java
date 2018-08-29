package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.HomePage;
import com.a3k.pages.Lesson;
import com.a3k.pages.LoginPage;
import com.a3k.pages.MyLessons;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class FiveStep_teacher extends BasicTestCase {

    protected static String finishLaterPopupContent;
    protected static String submitPopupContainer;

    protected static MyLessons myLessonsPage;
    protected static Lesson my_lesson;
    protected static String lessonID;



    @Parameters({"loginTeacher", "passwordTeacher", "program", "classToSelectTeacher", "language", "testName"})
    @Test(groups = {"Smoke", "FiveStep", "Teacher", "All"}, invocationCount = 1)
    public void verify5StepLessons(
            @Optional("kidbizteach.one") String login,
            @Optional("kidbizteach.one") String password,
            @Optional("") String program,
            @Optional("Access Class 3g") String classToSelect,
            @Optional("english") String language,
            @Optional("smoke") String testName)
            throws InterruptedException {

        setUpLanguage(language);

        login(login, password, program, classToSelect);
        myLessonsPage = openMyLessonsPage();

        Configuration.pageLoadStrategy = "normal";
        myLessonsPage.goToYearView();
        Configuration.pageLoadStrategy = "eager";

        my_lesson = openAnyNotStartedLesson();

        fillBeforeReadingForm(my_lesson);

        verifyReadingConnections(my_lesson);

        verifyArticlePage(my_lesson);

        my_lesson.goToActivityTab();

        try {
            verifyActivityPage(my_lesson, testName);
        } catch (InterruptedException e) {
            logger.trace("An error occurred: " + e.getMessage());
        }

        logger.info("Go to next tab");
        my_lesson.clickNextTabButton();
        verifyAfterReadingPollTab(my_lesson);
        logger.info("Submit After Reading poll");
        my_lesson.clickSubmitButtonARP();


        logger.info("Go to next tab");
        my_lesson.clickNextTabButton();

        verifyFinishLaterButton(my_lesson);
        verifySubmitButton(my_lesson);

        logger.info("Create new draft");
        my_lesson.createNewDraft();

        verifySubmitButton(my_lesson);

        //tmp workaround
        if (my_lesson.isElementPresent(my_lesson.mathTab)) {
            my_lesson.goToMathTab();
            verifyMathTab(my_lesson);
        }

        verifyStretchTab(my_lesson, testName);

        //my_lesson.goToMyLessonsByLinkWithoutLoadPage();
        my_lesson.goToMyLessonsByLink();

        verifyCompletedLesson(my_lesson);

        softAssert.assertAll();
    }

    protected void verifyCompletedLesson(Lesson lesson) {
        logger.info("Verify completed lesson ");
        myLessonsPage.verifyCompletedLesson(lessonID);
    }

    protected static void setUpLanguage(String language) {
        if (language.equals("english")) {
            logger.info("Language is set to English");
            finishLaterPopupContent = "Your work has been saved.";
            submitPopupContainer = "Thank you for submitting your work.";
        } else {
            logger.info("Language is set to Spanish");
            finishLaterPopupContent = "Tu trabajo se ha guardado.";
            submitPopupContainer = "Gracias por enviar tu trabajo.";
        }
    }
    @Step("Login with username {login}, password {password}, program " +
            "{program} " +
            "and class {classToSelect}")
    protected void login(String login, String password, String program, String classToSelect) {
        logger.info(String.format("Login with credentials %s\\%s", login, password));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeeded(login, password, program, classToSelect);
    }

    @Step
    protected MyLessons openMyLessonsPage() {
        new HomePage(driver).goToMyLessonsByLinkNew();
        return new MyLessons(driver);

    }

    @Step
    protected Lesson openAnyNotStartedLesson() {
        logger.info("Opening any not started lesson");
        myLessonsPage.sortStep1DescendingAndGotoPageNum("1");
        lessonID = myLessonsPage.openAnyNotStartedLesson();

        Lesson lesson = new Lesson(driver);

        lesson.waitUntilLessonOpen();
        return lesson;

    }

    @Step
    protected void fillBeforeReadingForm(Lesson lesson) {
        logger.info("Complete 'Before Reading' form");
        if (System.getProperty("os.name").contains("Windows")) {
            lesson.switchToBrpTextArea();
        }
        lesson.typeTextInTextboxBRP("Test String");
        lesson.switchBackToWindow();
        lesson.chooseARPAnswer();

        lesson.clickSubmitButtonBRT();
        logger.info("Before Reading form has been submitted");
    }

    @Step
    protected void verifyReadingConnections(Lesson lesson) {
        logger.info("Verify vocabulary");
        lesson.waitUntilAppearsVocabularyList();

        softAssert.assertTrue(lesson.verifyPresenceReadingConnectionsMarks(),
                "There is no reading connections on Article page");

        logger.info("Verify Reading Connections section");
        lesson.fillReadingConnections(); //
    }

    @Step
    protected void verifyActivityPage(Lesson lesson, String testName) throws InterruptedException {
        logger.info("Verifying Activity Page");

        lesson.waitSubmitButon();

        int answers = lesson.getPresenceFourAnswerOptions();

        softAssert.assertEquals(answers, 4,
                "Must be 4 answers choice, but found " + answers);

        String questionNumber = lesson.currentQuestion();

        boolean highlighting = lesson.selectFirstAnswer();

        softAssert.assertTrue(highlighting,
                "The selected answer is not highlighted");

        lesson.clickActivitySubmitButton();

        if (lesson.isAnswerWasInccorect()) {

            softAssert.assertTrue(lesson.verifyVisibleMotivationMessage(),
                    "Motivation Message is not appeared on Activity Tab");

            //Thread.sleep(2000);
            lesson.selectFirstAnswer();

            lesson.clickActivitySubmitButton();

            lesson.clickNextQuestionButton();
        } else {

            softAssert.assertTrue(lesson.verifyVisibleNextQuestionButton(),
                    "There is no Next Question button on Activity Tab");

            lesson.clickNextQuestionButton();

            String newQuestion = lesson.currentQuestion();

            softAssert.assertNotEquals(newQuestion, questionNumber,
                    "New question is not opened");

        }

        lesson.completeTeiActivity();

        lesson.waitForViewResultButton();
        lesson.clickViewResultsButton();

    }

    @Step
    protected void verifyArticlePage(Lesson lesson) {
        logger.debug("Started verifying article tab");

        softAssert.assertTrue(lesson.verifyPresenceWordsLinks(),
                "There is no words links on Article page");
        softAssert.assertTrue(lesson.verifyPresenceImage(),
                "There is no image on Article page");
        softAssert.assertTrue(lesson.verifyPresenceCredentials(),
                "There is no author credentials on Article page");
        softAssert.assertTrue(lesson.verifyPresencePrintButton(),
                "There is no print button on Article page");

        lesson.checkWordLink();
        logger.debug("Finished verifying article page");
    }

    @Step
    protected void verifyAfterReadingPollTab(Lesson lesson) {
        logger.info("Verifying After Reading Poll tab");

        softAssert.assertTrue(lesson.verifyPresenceQuestion(),
                "The question is not appears on After Reading Poll Tab");
        softAssert.assertTrue(lesson.verifyPresenceRadiobuttons(),
                "Radio buttons are not displayed on After Reading"
                        + " Poll Tab");
        softAssert.assertTrue(lesson.verifyPresenceSubmitButtonART(),
                "The Submit button is not displayed on " +
                        "After Reading Poll Tab");

        softAssert.assertTrue(lesson.checkRadiobuttonsClickable(),
                "Option is not selected");
    }

    @Step
    protected void verifyFinishLaterButton(Lesson lesson) {
        logger.info("Verifying 'Finish later'");
        String popupContent = lesson.checkFinishLaterButton("Test String");
        softAssert.assertEquals(popupContent, finishLaterPopupContent,
                "Expected popup text does not equal to actual."
                        + " Actual: " + popupContent
                        + ". Expected: " + finishLaterPopupContent);
    }

    @Step
    protected void verifySubmitButton(Lesson lesson) {
        logger.info("Verifying 'Submit Button'");
        String popupContent = lesson.checkSubmitButtonTQ();
        softAssert.assertEquals(popupContent, submitPopupContainer,
                "Actual popupContent does not equal to expected."
                        + " Actual: " + popupContent
                        + ". Expected: " + submitPopupContainer);
    }

    @Step
    protected void verifyMathTab(Lesson lesson) {
        logger.info("Verifying Math Tab");
        int totalQuestions = lesson.totalQuestionsMath();

        softAssert.assertTrue(lesson.verifyHighlightActivityWordMath(),
                "'Activity' word is not highlighted");

        softAssert.assertTrue(lesson.verifyDisableResultsWordMath(),
                "'Results' word is not disabled");

        softAssert.assertTrue(lesson.verifyPrecenceAcitivityQuestion(),
                "Text with question is not displayed");

        softAssert.assertTrue(lesson.verifyPresenceActivitySubmitButtonMath(),
                "Submit button is not present");

        int answers = lesson.getPresenceFourAnswerOptionsForMath();
        softAssert.assertTrue(totalQuestions == 1,
                "There is no questions on Math Tab");

        softAssert.assertEquals(answers, 4,
                "Must be 4 answers choice, but found " + answers);

        boolean highlighting = lesson.selectFirstAnswerMath();

        softAssert.assertTrue(highlighting,
                "The selected answer is not highlighted");

        logger.info("Submit Math lesson");
        lesson.clickMathSubmitButton();

        tryAgain(lesson);

        logger.info("Click on 'View Results' button on Math Page");
        lesson.clickViewResultsButtonMath();

        logger.info("Math lesson verified");
    }

    @Step
    protected void tryAgain(Lesson lesson) {

        while (lesson.isTryAgainButtonVisible()) {
            logger.info("Try again to answer the question");
            softAssert.assertTrue(lesson.verifyVisibleMotivationMessageMath(),
                    "Motivation Message is not appeared");

            lesson.clickTryAgainButtonMath();

            lesson.selectFirstAnswerMath();

            lesson.clickMathSubmitButton();

        }
        logger.info("Finally answered correctly");
    }

    @Step
    protected void verifyStretchTab(Lesson lesson, String testName) {
        logger.info("Verify Stretch Tab");
        if (!lesson.isStretchTabBlocked()) {

            try {

                lesson.goToStretchActivityTab();

                verifyActivityPage(lesson, testName);
            } catch (InterruptedException e) {
                logger.trace("An error occurred: " + e.getMessage());
            }
            softAssert.assertTrue(lesson.verifyPresenceResultsTable(),
                    "Result table is not present");

        }
    }
}
