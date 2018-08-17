package com.a3k.testCases.smoke;

import com.a3k.BasicTestCase;
import com.a3k.pages.*;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.$;

public class A3k_SparkAdmin extends BasicTestCase {


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


    @Parameters({"loginTeacher", "passwordTeacher", "classToSelectTeacher", "language", "testName"})
    @Test(groups = {"Smoke", "Admin", "All"})
    public void testAdmin_spark(
                                //@Optional("sparkteacher.one.1") String login,
                                @Optional("sparkteacher.one") String login,
                                @Optional("sparkteacher.one") String password,
                                @Optional("First Spark Class") String classToSelect,
                                @Optional("english") String language,
                                @Optional("spark") String testName) {
        testName = "spark";
        setUpLanguage(language, testName);
        classToSelect = "Spark Second Class";

        login(login, password, classToSelect);

        adminPage = goToAdminPage();

        setupWizardPage = openCreateNewClassWizard();

        setupWizardPage.waitUntilPageIsLoaded();

        setupWizardPage.switchBack();

        verifyClassEditor();

        logger.info("Return back to menu");
        adminPage.clickBackToMenu();

        editStudentAndTeacher(classToSelect);

        ElementsCollection we = $(adminPage.getListOfUserRows().get(0)).findAll(By.xpath(".//td"));

        verifyUserInfo(we);

        removeUserFromClass();

        editPage = openEditPage(we);
        verifyEditPage(classToSelect);

        allReportsPage = openReportsPage();

        verifyReports();

        softAssert.assertAll();


    }

    @Step
    public void verifyClassEditor() {
        logger.info("Verify Class editor");
        adminPage.clickOnEditClassInformation();
        adminPage.waitUntilSaveChangesEditInforButtonAppears();


        logger.info("Verify district name");
        softAssert.assertFalse(adminPage.getDisctrictName().isEmpty(), "district name is empty");
        logger.info("Verify school name ");
        softAssert.assertFalse(adminPage.getSchoolName().isEmpty(), "school name is empty");

        adminPage.getListOfEditUserLink().get(0).click();

        softAssert.assertTrue(adminPage.getListOfUserRows().size() > 1, "there is no users in a class");

        logger.info("Class Editor verified successfully");

    }

    @Step
    private void editStudentAndTeacher(String classToSelect) {
        logger.info("Trying to edit student and teacher");
        adminPage.openEditStudentAndTeacher();

        logger.info("Change class to " + classToSelect);
        adminPage.selectClass(classToSelect);
        adminPage.clickSubmitButton();

        logger.info("Student's class changed successfully");
    }

    @Step
    private void verifyUserInfo(ElementsCollection webElements) {
        logger.info("Verify main user fields");
        softAssert.assertTrue(adminPage.getListOfNumbersBeforeUsers().size() > 0, "numbers before users are not shown");

        logger.info("Verify username");
        softAssert.assertFalse($(webElements.get(2)).getText().isEmpty(), "user name is not shown");

        logger.info("Verify user"); // ?!
        softAssert.assertFalse($(webElements.get(3)).getText().isEmpty(), "user is not shown");


        logger.info("Verify p2p email");
        softAssert.assertTrue($(webElements.get(8)).getText().contains(noImages), "peer to peer email is not shown");
    }

    @Step
    private void removeUserFromClass() {
        WebElement user = $(adminPage.getListOfUserRows().get(1)).findAll(By.xpath(".//td")).get(1);
        logger.info("Remove user " + user);
        $(user).click();
        adminPage.clickOnRemoveFromClassButton();
        adminPage.waitAndCancelAlert();

        logger.info("User removed");
    }

    @Step
    private EditPage openEditPage(ElementsCollection we) {

        logger.info("Opening Edit page");
        ElementsCollection buffer = adminPage.getListOfUserRows();
        we = $(buffer.get(buffer.size() - 1)).findAll(By.xpath(".//td"));
        $(we.get(2)).click();

        adminPage.waitUntilUserProfileShown();

        return new EditPage(driver);
    }

    @Step
    private void verifyEditPage(String classToSelect) {

        verifyStudentInfo(classToSelect);

    }

    @Step("Verify Student's info from {classToSelect} class")
    private void verifyStudentInfo(String classToSelect) {
        logger.info("Verify student info");
        softAssert.assertFalse(editPage.getSchoolName().isEmpty(),
                "schoolname is empty");
        softAssert.assertTrue(editPage.getClassNames().contains(classToSelect),
                "class doesn't contain expected class");

    }

    @Step
    private void verifyReports() {
        verifyStudentWork();
        verifyUsageReports();
        verifyPerformanceReport();
        verifyAssessment();
    }

    @Step
    private void verifyAssessment() {
        logger.info("Verify assessment");
        allReportsPage.openAssessmentTools();

        softAssert.assertEquals(allReportsPage.getWhichOfMyStudentsLexilesAdjustedText(),
                WhichOfMyStudentsLexilesAdjusted,
                "Actual 'which of my students lexile adjusted' text does not equal to expected." +
                        "Expected: " + WhichOfMyStudentsLexilesAdjusted +
                        " . Actual: " + allReportsPage.getWhichOfMyStudentsLexilesAdjustedText());

        softAssert.assertEquals(allReportsPage.getStudentsWithDecreasingLexileText(),
                StudentsWithDecreasingLexile,
                "Actual 'students with decreasing lexile' text does not equal to expected." +
                        "Expected: " + StudentsWithDecreasingLexile +
                        " . Actual: " + allReportsPage.getStudentsWithDecreasingLexileText());

    }

    @Step
    private void verifyPerformanceReport() {
        allReportsPage.openPerformanseReports();

        softAssert.assertEquals(allReportsPage.getHowLikelyMyStudentsText(), HowLikelyMyStudents,
                "Actual 'how likely my students' text does not equal to expected." +
                        "Expected: " + HowLikelyMyStudents +
                        " . Actual: " + allReportsPage.getHowLikelyMyStudentsText());

        softAssert.assertEquals(allReportsPage.getHowHasLexileText(), HowHasLexile,
                "Actual 'how has lexile' text does not equal to expected." +
                        "Expected: " + HowHasLexile +
                        " . Actual: " + allReportsPage.getHowHasLexileText());

        softAssert.assertEquals(allReportsPage.getHowAreMyStudentsPerformingOnActivitiesText(),
                HowAreMyStudentsPerformingOnActivities,
                "Actual 'how are my students performing on activities' text does not equal to expected." +
                        "Expected: " + HowAreMyStudentsPerformingOnActivities +
                        " . Actual: " + allReportsPage.getHowAreMyStudentsPerformingOnActivitiesText());

        softAssert.assertEquals(allReportsPage.getHowAreMyStudentsPerformingOnStandardsText(),
                HowAreMyStudentsPerformingOnStandards,
                "Actual 'how are my students performing on standards' text does not equal to expected." +
                        "Expected: " + HowAreMyStudentsPerformingOnStandards +
                        " . Actual: " + allReportsPage.getHowAreMyStudentsPerformingOnStandardsText());

        softAssert.assertEquals(allReportsPage.getHowAreMyStudentsMasteringText(),
                HowAreMyStudentsMastering,
                "Actual 'how are my students mastering' text does not equal to expected." +
                        "Expected: " + HowAreMyStudentsMastering +
                        " . Actual: " + allReportsPage.getHowAreMyStudentsMasteringText());
        softAssert.assertEquals(allReportsPage.getHowAreMyStudentsPerforminNCLBText(),
                HowAreMyStudentsPerforminNCLB,
                "Actual 'how are my students performing NCLB' text does not equal to expected." +
                        "Expected: " + HowAreMyStudentsPerforminNCLB +
                        " . Actual: " + allReportsPage.getHowAreMyStudentsPerforminNCLBText());

    }

    @Step
    private void verifyUsageReports() {
        logger.info("Verify Usage reports");
        allReportsPage.expandUsageReports();
        softAssert.assertEquals(allReportsPage.getWhichOfMyStudentUsingTheProgramText(),
                whichOfMyStudentUsingTheProgram,
                "Actual The program student does not equal to expected." +
                        "Expected: " + whichOfMyStudentUsingTheProgram +
                        " . Actual: " + allReportsPage.getWhichOfMyStudentUsingTheProgramText());

        softAssert.assertEquals(allReportsPage.getHowAreMyStudentProgressingText(),
                howAreMyStudentProgressing,
                "Actual student progress does not equal to expected." +
                        "Expected: " + howAreMyStudentProgressing +
                        " . Actual: " + allReportsPage.getHowAreMyStudentProgressingText());

        softAssert.assertEquals(allReportsPage.getWhichMyStudentsAreUsingTheProgramAfterText(),
                whichMyStudentsAreUsingTheProgramAfter,
                "Actual 'student using the program after school' report does not equal to expected." +
                        "Expected: " + whichMyStudentsAreUsingTheProgramAfter +
                        " . Actual: " + allReportsPage.getWhichMyStudentsAreUsingTheProgramAfterText());

    }

    @Step
    private void verifyStudentWork() {
        logger.info("Verify student work");
        allReportsPage.openStudentWork();

        softAssert.assertTrue(allReportsPage.isAuthentisAssesmentPortfolionShown(),
                "Authentic assessment link is not shown");
        softAssert.assertTrue(allReportsPage.isEmailAndStep1Shown(),
                "email and step 1 link is not shown");

    }

    @Step
    private AllReportsPage openReportsPage() {
        logger.info("Go to Home Page");
        editPage.goToHomePage();

        logger.info("Go to All Reports Page");

        editPage.goToReportsPage();

        return new AllReportsPage(driver);
    }

    @Step
    private SetupWizardPage openCreateNewClassWizard() {
        logger.info("Open Create new class Wizard");
        adminPage.clickOnCreateNewClass();

        adminPage.waitAndSwitchToFrame();
        logger.info("'Create new class' wizard opened");
        return new SetupWizardPage(driver);
    }

    @Step("Login with username {login}, password {password}, " +
            "and class {classToSelect}")
    private void login(String username, String password, String classToSelect) {
        new LoginPage(driver).loginIntoWithClass(username, password, classToSelect);

    }

    @Step
    private AdminPage goToAdminPage() {
        new HomePage(driver).goToAdminPage();

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

            myLessonsTitle = "My Lesson";
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

                HowLikelyMyStudents = "How likely are my students to be on track for College and Career when CC-ELA is administered?";//How likely are my students to be on track for College and Career when LevelSet Post Test is administered?
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
            biologyTitle = "Biologí­a";

            whichOfMyStudentUsingBiologyModule = "¿Cuáles de mis estudiantes están usando el módulo de biología?";
            whichOfMyStudentUsingTheProgram = "¿Cuáles de mis estudiantes están usando el programa?";
            whichParentsUsingTheProgram = "¿Cuáles de los padres o tutores están usando el programa?";
            howAreMyStudentProgressing = "¿Cómo están avanzando mis estudiantes hacia el objetivo de completar 40 actividades de Achieve3000?";
            howAreMyStudentsSpending = "¿Cómo están usando su tiempo mis estudiantes?";
            howAreMyStudentsSpendingTimeAfterSchool = "¿Cómo utilizan mis estudiantes su tiempo después del horario de la escuela?";
            //TODO: fix translate spanish
            //whichMyStudentsAreUsingTheProgramAfter = "¿Cuáles de mis estudiantes usan el programa después de las horas de clase?";
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
            //PossibleInvalidLevelSet = "Administraciones de LevelSet posiblemente incorrectas";
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
