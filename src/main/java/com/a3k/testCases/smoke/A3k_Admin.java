package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selenide.$;

public class A3k_Admin extends BasicTestCase {

    static String findUser;
    static String createNewClass;
    static String editClassInfo;
    static String editStudentTeacherInfo;
    static String editParentInfo;
    static String addUsersToClass;
    static String removeUsers;
    static String printClassList;

    static String noImages;
    static String vocabulary;

    static String intervention;
    static String multiple;
    static String lang;
    static String englishLang;
    static String spanishLang;

    static String loginNameField;
    static String passwordField;
    static String firstNameField;
    static String lastNamefield;
    static String penNameField;
    static String studentIDField;
    static String gradeLevelField;
    static String lexileLevelField;
    static String disableAvatarSectionl;
    static String genderSection;
    static String ethnicitySection;
    static String raceSection;
    static String ellSection;
    static String sesSection;
    static String firstLanguageSection;
    static String specialClassificationSpecifySection;
    static String specialClassificationSection;

    static String myLessonsOptions;
    static String biologyOptions;
    static String unitsOptions;
    static String writingOptions;

    static String myLessonsTitle;
    static String unitsTitle;
    static String writingTitle;
    static String biologyTitle;

    static String whichOfMyStudentUsingBiologyModule;
    static String whichOfMyStudentUsingTheProgram;
    static String whichParentsUsingTheProgram;
    static String howAreMyStudentProgressing;
    static String howAreMyStudentsSpending;
    static String howAreMyStudentsSpendingTimeAfterSchool;
    static String whichMyStudentsAreUsingTheProgramAfter;
    static String howHasUsageChanged;

    static String HowLikelyMyStudents;
    static String HowHasLexile;
    static String HowAreMyStudentsPerformingOnActivities;
    static String HowAreMyStudentsPerformingOnStandards;
    static String HowAreMyStudentsMastering;
    static String HowAreMyStudentsPerforminNCLB;
    static String HowCanIDifferentiate;

    static String WhichOfMyStudentsHaveNotTakeLevelSet;
    static String PossibleInvalidLevelSet;
    static String WhichOfMyStudentsAreDevelopingReaders;
    static String WhichOfMyStudentsLexilesAdjusted;
    static String StudentsWithDecreasingLexile;

    static String welcomeLetter;
    static String homeEditionSetupInstruction;
    static String homeLexileLetter;

    static AdminPage adminPage;
    static SetupWizardPage setupWizardPage;
    static EditPage editPage;
    static AllReportsPage allReportsPage;

    @Parameters({"loginTeacher", "passwordTeacher", "program", "classToSelectTeacher", "language", "testName"})
    @Test(groups = {"Smoke", "All", "Admin"}, invocationCount = 1)
    public void testAdmin(@Optional("kidbizteach.one") String login,
                          @Optional("kidbizteach.one") String password,
                          @Optional("") String program,
                          @Optional("Boost Class 3g") String classToSelect,
                          @Optional("english") String language,
                          @Optional("spanish") String testName) {

        setUpLanguage(language, testName);

        login(login, password, program, classToSelect);
        adminPage = goToAdminPage();

        setupWizardPage = openCreateNewClassWizard();
        setupWizardPage.waitUntilPageIsLoaded();

        setupWizardPage.switchBack();
        verifyClassEditor();

        logger.info("Return back to menu");
        adminPage.clickBackToMenu();

        editStudentAndTeacher(classToSelect);

        ElementsCollection we = adminPage.getListOfUserRows().get(0).$$(By.xpath(".//td"));

        verifyUserInfo(we);

        if (!testName.equals("spark")) {
            String walkMeTmp = "//*[@class='walkme-question-mark walkme-override " +
                    "walkme-css-reset']";
            if (adminPage.isElementPresentBy(By.xpath(walkMeTmp))) {
                adminPage.removeUIElementFromPageWithJS($(By.xpath(walkMeTmp)));
            }

            removeUserFromClass();
        }

        editPage = openEditPage(we);
        verifyEditPage(classToSelect, testName);

        allReportsPage = openReportsPage();
        verifyReports(testName);

        softAssert.assertAll();
    }

    @Step
    public void verifyClassEditor() {
        logger.info("Verify Class editor");

        logger.info("Open 'Class Information' editor");
        adminPage.clickOnEditClassInformation();
        //adminPage.waitUntilSaveChangesEditInforButtonAppears();

        logger.info("Verify district name is not empty");
        //softAssert.assertFalse(adminPage.getDisctrictName().isEmpty(), "district name is empty");
        $(adminPage.getDisctrictName()).shouldNotBe(empty);

        logger.info("Verify school name is not empty");

        adminPage.getListOfEditUserLink().get(0).click();

        //adminPage.waitPageLoad();
        logger.info("Verify amount of users in class is more than 1");
        softAssert.assertTrue(adminPage.getListOfUserRows().size() > 1, "there is no users in a class");
       // adminPage.getListOfUserRows().shouldHaveSize(4);

        logger.info("Class Editor verified successfully");

    }

    @Step
    private void editStudentAndTeacher(String classToSelect) {
        logger.info("Trying to edit student and teacher");

        adminPage.waitPageLoad();

        logger.info("Open Student and Teacher editor");
        adminPage.openEditStudentAndTeacher();

        logger.info("Change class to " + classToSelect);
        adminPage.selectClass(classToSelect);

        logger.info("Submit");
        adminPage.clickSubmitButton();

        logger.info("Student's class changed successfully");
    }

    @Step
    private void verifyUserInfo(ElementsCollection webElements) {
        logger.info("Verify main user fields");

        logger.info("Verify start time is present");
        softAssert.assertFalse(webElements.get(4).$(By.id("levelset_start[0]")).getAttribute("value").isEmpty(), "start time is not shown");

        logger.info("Verify end time is present");
        softAssert.assertFalse(webElements.get(6).$(By.id("levelset_end[0]")).getAttribute("value").isEmpty(), "end time is not shown");

        logger.info("Verify p2p email is present");
        softAssert.assertTrue(webElements.get(8).getText().contains(noImages), "peer to peer email is not shown");
    }

    @Step
    private void removeUserFromClass() {
        WebElement user = adminPage.getListOfUserRows().get(1).$$(By.xpath(".//td")).get(1);
        logger.info("Remove user " + user + " from class");
        adminPage.clickJSWebEl(user);

        adminPage.clickOnRemoveFromClassButton();
        adminPage.waitAndCancelAlert();

        logger.info("User removed");
    }

    @Step
    private EditPage openEditPage(ElementsCollection we) {

        logger.info("Opening Edit page");
        ElementsCollection buffer = adminPage.getListOfUserRows();
        we = buffer.get(buffer.size() - 1).$$(By.xpath(".//td"));
        $(we.get(2).$(By.xpath(".//a"))).click();

        //adminPage.waitUntilUserProfileShown();
        return new EditPage(driver);
    }

    @Step
    private void verifyEditPage(String classToSelect, String testName) {
        verifyStudentInfo(classToSelect);
    }

    @Step
    private void verifyStudentInfo(String classToSelect) {
        logger.info("Verify student info");

        logger.info("Verify school name is present");
        softAssert.assertFalse(editPage.getSchoolName().isEmpty(),
                "school name is empty");

        logger.info("Verify expected class (" + classToSelect + ") is present");
        softAssert.assertTrue(editPage.getClassNames().contains(classToSelect),
                "class doesn't contain expected class");

        logger.info("Verify login is present");
        softAssert.assertFalse(editPage.getLoginName().isEmpty(),
                "login name is empty");
    }

    @Step
    private void verifyReports(String testName) {
        verifyStudentWork(testName);
        verifyPerformanceReport(testName);
        verifyAssessment(testName);
    }

    @Step
    private void verifyAssessment(String testName) {
        logger.info("Verify assessment");
        allReportsPage.openAssessmentTools();

        if (!testName.equals("spark")) {
            allReportsPage.openHomeCommunication();
            softAssert.assertEquals(allReportsPage.getWelcomeLetterText(), welcomeLetter,
                    "Actual 'welcome letter' text does not equal to expected."
                            + "Expected: " + welcomeLetter +
                            " . Actual: " + allReportsPage.getWelcomeLetterText());

            softAssert.assertEquals(allReportsPage.getHomeEditionSetupInstructionText(),
                    homeEditionSetupInstruction,
                    "Actual 'home edition setup instruction' text does not equal to expected."
                            + "Expected: " + homeEditionSetupInstruction +
                            " . Actual: " + allReportsPage.getHomeEditionSetupInstructionText());

            softAssert.assertEquals(allReportsPage.getHomeLexileLetterText(), homeLexileLetter,
                    "Actual 'home lexile letter' text does not equal to expected."
                            + "Expected: " + homeLexileLetter +
                            " . Actual: " + allReportsPage.getHomeLexileLetterText());
        }
    }

    @Step
    private void verifyPerformanceReport(String testName) {
        allReportsPage.openPerformanseReports();

        if (!testName.equals("spanish")) {
            softAssert.assertEquals(allReportsPage.getHowAreMyStudentsMasteringText(),
                    HowAreMyStudentsMastering,
                    "Actual 'how are my students mastering' text does not equal to expected."
                            + "Expected: " + HowAreMyStudentsMastering +
                            " . Actual: " + allReportsPage.getHowAreMyStudentsMasteringText());
            softAssert.assertEquals(allReportsPage.getHowAreMyStudentsPerforminNCLBText(), HowAreMyStudentsPerforminNCLB,
                    "Actual 'how are my students performing NCLB' text does not equal to expected."
                            + "Expected: " + HowAreMyStudentsPerforminNCLB +
                            " . Actual: " + allReportsPage
                            .getHowAreMyStudentsPerforminNCLBText());

            if (!testName.equals("spark") && !testName.equals("uk")) {
                softAssert.assertEquals(allReportsPage.getHowCanIDifferentiateText(), HowCanIDifferentiate,
                        "Actual 'how can I differentiate' text does not equal to expected."
                                + "Expected: " + HowCanIDifferentiate
                                + " . Actual: " + allReportsPage.getHowCanIDifferentiateText());
            }
        }
    }

    @Step
    private void verifyStudentWork(String testName) {
        logger.info("Verify student work");
        allReportsPage.openStudentWork();

       // $(allReportsPage.isAuthentisAssesmentPortfolionShown()).shouldBe(visible);
        softAssert.assertTrue(allReportsPage.isAuthentisAssesmentPortfolionShown(), "Authentic assessment link is not shown");
        softAssert.assertTrue(allReportsPage.isEmailAndStep1Shown(), "email and step 1 link is not shown");

        if (!testName.equals("spark"))
            softAssert.assertTrue(allReportsPage.isPointsAndAchivementsShown(),
                    "points and achievements link is not shown");

        logger.info("Verify Titles");
        softAssert.assertEquals(allReportsPage.getMyLessonsTitleText(), myLessonsTitle,
                "My Lesson title is invalid. "
                        + "Expected: " + myLessonsTitle
                        + ", Actual: " + allReportsPage.getMyLessonsTitleText());
        softAssert.assertTrue(allReportsPage.isTextOnPagePresent(unitsTitle),
                "Units title is not present");
        softAssert.assertTrue(allReportsPage.isTextOnPagePresent(writingTitle),
                "Writing title is not present");
    }

    @Step
    private AllReportsPage openReportsPage() {
        editPage.goToHomePage();
        editPage.goToReportsPage();
        return new AllReportsPage(driver);
    }

    @Step
    private SetupWizardPage openCreateNewClassWizard() {
        logger.info("Open Create new class Wizard");
        adminPage.clickOnCreateNewClass();

        adminPage.waitAndSwitchToFrame();
        logger.info("'Create new class'" +
                " wizard opened");
        return new SetupWizardPage(driver);
    }

    @Step("Login with username {login}, password {password}, program " +
            "{program} " +
            "and class {classToSelect}")
    protected void login(String login, String password, String program, String classToSelect) {
        logger.info(String.format("Login with credentials %s\\%s", login, password));
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginWithClassAndProgramIfNeededWithAlert(login, password, program, classToSelect);
    }

    @Step
    private AdminPage goToAdminPage() {
        Configuration.pageLoadStrategy = "normal";
        new HomePage(driver).goToAdminPage();

        Configuration.pageLoadStrategy = "eager";
        return new AdminPage(driver);
    }

    @Step
    private static void setUpLanguage(String language, String testName) {

        if (language.equals("english")) {

            findUser = "Find a User";
            createNewClass = "Create a new class";
            editClassInfo = "Edit class information";

            addUsersToClass = "Add users to my class";
            removeUsers = "Remove users";
            printClassList = "Print class list";

            noImages = "No images";
            vocabulary = "Vocabulary";

            intervention = "Intervention";
            multiple = "Multiple";
            lang = "Language";

            if (!testName.equals("uk"))
                englishLang = "English (US)";
            else
                englishLang = "English (UK)";

            spanishLang = "Full Spanish";

            loginNameField = "Login Name";
            passwordField = "Password";
            firstNameField = "First Name";
            lastNamefield = "Last Name";
            penNameField = "Pen Name";

            gradeLevelField = "Grade Level";
            lexileLevelField = "Lexile Level";
            disableAvatarSectionl = "Disable Avatar Selection";
            genderSection = "Gender";
            ethnicitySection = "Ethnicity";
            raceSection = "Race";
            ellSection = "English Language Learner (ELL)";
            sesSection = "Socioeconomic Status (SES)";
            firstLanguageSection = "First (Native) Language";
            specialClassificationSpecifySection = "Special Classification (specify)";
            specialClassificationSection = "Special Classification";

            myLessonsOptions = "ActivitiesThoughtQuestionsMathProblemsReadAloudReadingConnections";
            biologyOptions = "ActivitiesThoughtQuestions";
            unitsOptions = "EmailMessagesActivitiesWrittenResponses";
            writingOptions = "WrittenResponsesActivitiesWritingProcessAssignmentTimedWriting";

            myLessonsTitle = "My Lessons";
            unitsTitle = "Units";
            writingTitle = "Writing";
            biologyTitle = "Biology";

            whichParentsUsingTheProgram = "Which parents/guardians are using the program?";

            howHasUsageChanged = "How has usage changed over time?";

            if (!testName.equals("spark")) {
                editStudentTeacherInfo = "Edit student and teacher information";
                editParentInfo = "Edit parent information";

                studentIDField = "Student ID";

                whichOfMyStudentUsingBiologyModule = "Which of my students are using the biology module?";
                whichOfMyStudentUsingTheProgram = "Which of my students are using the program?";
                howAreMyStudentProgressing = "How are my students progressing towards Achieve3000's 40-activity usage goal?";
                howAreMyStudentsSpending = "How are my students spending their time?";
                howAreMyStudentsSpendingTimeAfterSchool = "How are my students spending their time after school?";
                whichMyStudentsAreUsingTheProgramAfter = "Which of my students are using the program after school?";

                HowLikelyMyStudents = "How likely are my students to be on track for College and Career when the high stakes test is administered?";// How
                // likely
                // are
                // my
                // students
                // to
                // be
                // on
                // track
                // for
                // College
                // and
                // Career
                // when
                // LevelSet
                // Post
                // Test
                // is
                // administered?
                HowHasLexile = "How has Lexile performance changed over time?";
                HowAreMyStudentsPerformingOnActivities = "How are my students performing on activities?";
                HowAreMyStudentsPerformingOnStandards = "How are my students performing on standards?";
                HowAreMyStudentsMastering = "How are my students mastering biology vocabulary?";
                HowAreMyStudentsPerforminNCLB = "How are my students performing on standards (NCLB subgroups)?";
                HowCanIDifferentiate = "How can I differentiate my instruction based on NWEA MAP assessment results?";

                WhichOfMyStudentsHaveNotTakeLevelSet = "Which of my students have not taken LevelSet?";
                PossibleInvalidLevelSet = "Possibly Invalid LevelSet Administrations";
                WhichOfMyStudentsAreDevelopingReaders = "Which of my students are Developing Readers?";
                WhichOfMyStudentsLexilesAdjusted = "Which of my students' Lexiles have been adjusted?";
                StudentsWithDecreasingLexile = "Students with Decreasing Lexile Levels";

                if (testName.equals("uk")) {
                    HowLikelyMyStudents = "How likely are my students to be on track for College and Career when LevelSet Post Test is administered?";
                }
            } else {
                studentIDField = "Learner ID";

                editStudentTeacherInfo = "Edit learner or teacher information";
                editParentInfo = "";

                whichOfMyStudentUsingBiologyModule = "Which of my learners are using the biology module?";
                whichOfMyStudentUsingTheProgram = "Which of my learners are using the program?";
                howAreMyStudentProgressing = "How are my learners progressing towards Achieve3000's 40-activity usage goal?";
                howAreMyStudentsSpending = "How are my learners spending their time?";
                howAreMyStudentsSpendingTimeAfterSchool = "How are my learners spending their time after school?";
                whichMyStudentsAreUsingTheProgramAfter = "Which of my learners are using the program after school?";

                HowLikelyMyStudents = "How likely are my learners to be on track for College and Career when the high stakes test is administered?";
                HowHasLexile = "How has Lexile performance changed over time?";
                HowAreMyStudentsPerformingOnActivities = "How are my learners performing on activities?";
                HowAreMyStudentsPerformingOnStandards = "How are my learners performing on standards?";
                HowAreMyStudentsMastering = "How are my learners mastering biology vocabulary?";
                HowAreMyStudentsPerforminNCLB = "How are my learners performing on standards (NCLB subgroups)?";
                HowCanIDifferentiate = "How can I differentiate my instruction based on NWEA MAP assessment results?";

                WhichOfMyStudentsHaveNotTakeLevelSet = "Which of my learners have not taken LevelSet?";
                PossibleInvalidLevelSet = "Possibly Invalid LevelSet Administrations";
                WhichOfMyStudentsAreDevelopingReaders = "Which of my learners are Developing Readers?";
                WhichOfMyStudentsLexilesAdjusted = "Which of my learners' Lexiles have been adjusted?";
                StudentsWithDecreasingLexile = "Learners with Decreasing Lexile Levels";
            }

            welcomeLetter = "Welcome letter";
            homeEditionSetupInstruction = "Home Edition setup instructions";
            homeLexileLetter = "Home Lexile Letter";

        } else {
            findUser = "Encontrar un usuario";
            createNewClass = "Crear una clase nueva";
            editClassInfo = "Editar la información de la clase";
            editStudentTeacherInfo = "Editar información de los estudiantes y del maestro";
            editParentInfo = "Editar información de los padres/tutores";
            addUsersToClass = "Añadir usuarios a mi clase";
            removeUsers = "Eliminar usuarios";
            printClassList = "Imprimir lista de clase";

            noImages = "Sin imágenes";
            vocabulary = "vocabulario";

            intervention = "Intervención";
            multiple = "Múltiple";
            lang = "Lenguaje";
            englishLang = "Inglés (EEUU)";
            spanishLang = "Todo en español";

            loginNameField = "Nombre de conexión";
            passwordField = "Contraseña";
            firstNameField = "Nombre";
            lastNamefield = "Apellido";
            penNameField = "Nombre de pluma";
            studentIDField = "Identificación\ndel estudiante";
            gradeLevelField = "Grado";
            lexileLevelField = "Nivel Lexile";
            disableAvatarSectionl = "Desactivar selección de Avatar";
            genderSection = "Sexo";
            ethnicitySection = "Grupo étnico";
            raceSection = "Raza";
            ellSection = "Estudiante que está aprendiendo el idioma inglés";
            sesSection = "Nivel socioeconómico";
            firstLanguageSection = "Lengua materna o primer idioma";
            specialClassificationSpecifySection = "Sectores especiales (epecifique)";
            specialClassificationSection = "Sectores especiales";

            myLessonsOptions = "ActividadesPreguntasdereflexiónProblemasdematemáticasLecturaoralLeoyentiendo";
            biologyOptions = "ActividadesPreguntasdereflexión";
            unitsOptions = "MensajesporcorreoelectrónicoActividadesRespuestasporescrito";
            writingOptions = "RespuestasporescritoActividadesTareadelprocesodelaescrituraRedacciónconlímitedetiempo";

            myLessonsTitle = "Mis lecciones";
            unitsTitle = "Unidades";
            writingTitle = "Escritura";
            biologyTitle = "Biologí";

            whichOfMyStudentUsingBiologyModule = "¿Cuáles de mis estudiantes están usando el módulo de biología?";
            whichOfMyStudentUsingTheProgram = "¿Cuáles de mis estudiantes están usando el programa?";
            whichParentsUsingTheProgram = "¿Cuáles de los padres o tutores están usando el programa?";
            howAreMyStudentProgressing = "¿Cómo están avanzando mis estudiantes hacia el objetivo de completar 40 actividades de Achieve3000?";
            howAreMyStudentsSpending = "¿Cómo están usando su tiempo mis estudiantes?";
            howAreMyStudentsSpendingTimeAfterSchool = "¿Cómo utilizan mis estudiantes su tiempo después del horario de la escuela?";

            whichMyStudentsAreUsingTheProgramAfter = "¿Cuáles de mis estudiantes usan el programa fuera de las horas de clase?";
            howHasUsageChanged = "¿Cómo ha cambiado el uso con el tiempo?";

            HowLikelyMyStudents = "¿Cuál es la posibilidad de que mis estudiantes estén bien encaminados para la universidad y la vida profesional en el momento en que se administre CC-ELA/Regents?";
            HowHasLexile = "¿Cómo ha cambiado el desempeño en el nivel de lectura Lexile con el tiempo?";
            HowAreMyStudentsPerformingOnActivities = "¿Cómo se están desempeñando mis estudiantes en las actividades?";
            HowAreMyStudentsPerformingOnStandards = "¿Cuál es el desempeño de mis estudiantes en los estándares?";
            HowAreMyStudentsMastering = "¿Cómo están dominando mis estudiantes el vocabulario de biología?";
            HowAreMyStudentsPerforminNCLB = "¿Cómo se están desempeñando mis estudiantes en función a NCLB?";
            HowCanIDifferentiate = "How can I differentiate my instruction based on NWEA MAP assessment results?";

            WhichOfMyStudentsHaveNotTakeLevelSet = "¿Cuáles de mis estudiantes no han tomado LevelSet?";

            PossibleInvalidLevelSet = "Administraciones de LevelSet que podrían no ser válidas";
            WhichOfMyStudentsAreDevelopingReaders = "¿Cuáles de mis estudiantes son lectores en desarrollo?";
            WhichOfMyStudentsLexilesAdjusted = "¿A cuáles de mis estudiantes se les ajustó el Lexile?";
            StudentsWithDecreasingLexile = "Estudiantes con niveles Lexile que disminuyeron";

            welcomeLetter = "Carta de bienvenida";
            homeEditionSetupInstruction = "Instrucciones para crear su cuenta de Edición para el hogar";
            homeLexileLetter = "Carta sobre el Lexile para enviar a padres y tutores";

        }
    }




}
