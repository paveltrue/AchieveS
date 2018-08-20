package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.switchTo;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Lesson  extends Page{

    private By activityTabBy = By.xpath("//li[contains(@onclick,'view_page(14, 1)')]/div");
    private By viewResultsButtonBy = By.xpath(".//button[contains(@class, 'view-results')]");
    private By nextButtonBy = By.xpath("//div[contains(@class,'next_tab')]/a");
    private By submitButtonARTBy = By.id("pollSubmit-step15page1");
    private By OKButtonBy = By.xpath("//div[@class='ui-dialog-buttonset']/button");
    private By newDraftButtonBy = By.id("newtab-step16page1q1");
    public WebElement mathTab = $(By.xpath("//li[contains(@onclick,'view_page(18, 1)')]/div[@title]"));
    private By mathTabBy = By.xpath("//li[contains(@onclick,'view_page(18, 1)')]/div[@title]");
    private By wordsLinksBy = By.xpath("//a[@class='dict-word']");
    private By imageBy = By.xpath("//div[@class='media-image']//img[not(@class='magnify')]");
    private By page2onArcticleBy = By.id("link-step11page2");
    private By page2onStretchArcticleBy = By.id("link-step19page2");
    private By credentialsBy = By.xpath("//p[@class='credits']");
    private By printBy = By.xpath("//a[@class='printLink' or @title='Print']");
    private By questionTQBy = By.xpath("//div[contains(@id,'question')]/p/span");
    private By pollQuestionBy = By.xpath("//*[@class='pollQuestion']");
    private By radiobuttonsBy = By.xpath(".//*[contains(@class,'wordwrap')]");
    private By radioBy = By.xpath("//li[contains(@class,'wordwrap')]");
    WebElement radioWeb = $(By.xpath("//li[contains(@class,'wordwrap')]"));
    private By submitButtonLiteracyBy = By.xpath("//*[@ng-click='submitHandler()']");
    private By answersNewBy = By.xpath("//div[@class='activity-option ng-scope']");
    private By answersBy = By.xpath(".//div[@class='activity-option ng-scope']");
    private By pointsBy = By.id("topPointsContainer");
    private By currentAndTotalQuestionBy = By.xpath("//*[@class='footer progressBarFooter']/span");
    private By allAnswersBy = By.xpath("//*[contains(@class,'activity-option ng-scope') and not(contains(@class,'incorrect'))] | //div[@class='question mc-question active']//li[not(contains(@class,'choice_disabled'))]");
    private By selectedAnswerBy = By.xpath("//*[contains(@class,'selected')]");
    private By submitBtnOnActivityTabBy = By.xpath("//*[contains(@class,'submit-button submit')]");
    private By messageAfterSubmitAnswerBy = By.xpath("//div[@class='cheer'] | //span[contains(@id,'feedback')]");
    private By nextQuestionButtonInActivityTabBy = By.xpath("//*[contains(@class,'next-question')] | //input[contains(@id,'next_question')]");
    private WebElement nextQuestionButtonInActivityTab = $(By.xpath("//*[contains(@class,'next-question')] | //input[contains(@id,'next_question')]"));
    private By smthgWrongOkButtonBy = By.xpath("//div[@class='ui-dialog-buttonset']//span");
    private By nextQuestionButtonLiteracyBy = By.xpath("//*[@ng-click='nextQuestionHandler()']");
    private By viewResultsButtonLiteracyBy = By.xpath("//button[@ng-click='showActivityResult()']");
    private By notSelectedAnswersBy = By.xpath("//*[@class='activity-option ng-scope']");
    private By tryAgainMessageBy = By.xpath("//*[@class='wrong']/div[@class='']/div[not(contains(@class,'hide'))]");
    private By activityPart2LiteracyPickedBy = By.xpath("//a[contains(@ng-class,'activityPartIndex === 2') and @class='pick']");
    private WebElement viewResultsButtonLiteracy = $(By.xpath("//button[@ng-click='showActivityResult()']"));
    private WebElement nextQuestionButtonLiteracy = $(By.xpath("//*[@ng-click='nextQuestionHandler()']"));
    private ElementsCollection sentecesInChart = $$(By.xpath("//*[@class = 'sentence ng-binding']"));
    private WebElement chartWithSentences = $(By.className("target-container"));
    private ElementsCollection allAnswers = $$(By.xpath("//*[contains(@class,'activity-option ng-scope') and not(contains(@class,'incorrect'))] | //div[@class='question mc-question active']//li[not(contains(@class,'choice_disabled'))]"));
    private ElementsCollection listOfSequencingElements = $$(By.xpath("//*[@class='item-wrapper wrapper ng-scope' and ./p[@data-dnd-item-id]]"));
    private ElementsCollection listOfSourceDNDItems = $$(By.xpath("//div[contains(@class,'item-') and @data-dnd-item-id]"));
    private ElementsCollection listOfTargetDNDItems = $$(By.xpath("//div[contains(@class,'target ui-droppable') and @data-dnd-target-id]"));
    private WebElement currentAndTotalQuestion = $(By.xpath("//*[@class='footer progressBarFooter']/span"));
    private WebElement smthgWrongOkButton = $(By.xpath("//div[@class='ui-dialog-buttonset']//span"));
    public By listOfAllSentencesInChartForPart = By.xpath(".//*[@class = 'sentence ng-binding']");
    public By chartWithSentenceForPart = By.xpath(".//*[@class='target-container']");
    public By listOfNotSelectedAnswersForPart = By.xpath(".//*[@class='activity-option ng-scope']");
    public By listOfAllAnswersForPart = By.xpath(".//*[contains(@class,'activity-option ng-scope') and not(contains(@class,'incorrect'))] | //div[@class='question mc-question active']//li[not(contains(@class,'choice_disabled'))]");
    public By listOfSequenceElementsForPart = By.xpath(".//*[@class='item-wrapper wrapper ng-scope' and ./p[@data-dnd-item-id]]");
    public By listOfSourceDNDItemsForPart = By.xpath(".//div[contains(@class,'item-') and @data-dnd-item-id]");
    public By listOfTargetDNDItemsForPart = By.xpath(".//div[contains(@class,'target ui-droppable') and @data-dnd-target-id]");
    public WebElement partBContainer = $(By.xpath(".//div[@class='ng-scope' and @ng-repeat='section in containerController.activityState.currentQuestion.collection'][2]"));
    public WebElement partAContainer = $(By.xpath(".//div[@class='ng-scope' and @ng-repeat='section in containerController.activityState.currentQuestion.collection'][1]"));
    public ElementsCollection questionContainers = $$(By.xpath(".//div[@class='ng-scope' and @ng-repeat='section in containerController.activityState.currentQuestion.collection']"));
    private By viewResultButtonInActivityTabBy = By.xpath("//button[contains(@class,'view-results')] | //input[contains(@id,'view_results-')]");
    private By submitButtonBRTBy = By.id("pollSubmit-step10page1");
    private By nextPageArrowButtonBy = By.xpath(".//*[contains(@class , 'nextContainer')]/*[@class = 'next']/a");
    private By ARPAnswerBy = By.xpath("//li[contains(@onclick,'a3k.poll.select_poll_choice')]");
    private By textAreaBRPARPBy = By.xpath(".//*[(@name='response') or (@id='tinymce') or (@id='textarea-step10page1response1')]");
    private By textAreaBRPframeBy = By.id("textarea-step10page1response1_ifr");
    private By readingConnectionsBy = By.xpath("//div[@class='readingConnContainer']");
    private By closePopupButtonBy = By.xpath("//div[contains(@class,'alert_dialog')]/..//button");
    private WebElement resultTable = $(By.id("results"));
    private By stretchActivityTabBy = By.xpath("//li[contains(@onclick,'view_page(20, 1)')]/div[@title]");
    private By stretchBlockBy = By.xpath(".//*[@classs = 'htmlBlock']");
    private By finishLaterButtonBy = By.id("btn1-step16page1q1");
    private By textFieldBy = By.xpath(".//*[@class='tiny submission_textarea' or @id = 'tinymce']");
    private By draft1FrameBy = By.id("textarea-step16page1q1response1_ifr");
    private By submitButtonTQBy = By.id("btn2-step16page1q1");
    private By selectedFrameBy = By.xpath("//div[@class='responseContainer' or @class='responseContainer ']//iframe");
    private ElementsCollection activityQuestion = $$(By.xpath("//div[@class='scoredActivity'] | //div[@class='activity-options']"));
    private WebElement submitButtonMath = $(By.xpath("//*[contains(@id,'submit')]"));
    private ElementsCollection answersMath = $$(By.xpath("//ul[@class='ulChoice']/li[not(contains(@class,'disabled')) and not(contains(@class,'selected'))]"));
    private WebElement viewResultsButton = $(By.xpath("//input[contains(@id,'view_results-')]"));
    private WebElement tryAgainButton = $(By.xpath("//input[contains(@id,'try_again-')]"));
    private WebElement feedbackMath = $(By.xpath("//*[contains(@id,'feedback')]"));
    private WebElement tryAgainButtonMath = $(By.xpath("//*[contains(@id,'try_again')]"));
    private WebElement writingLessonTitle = $(By.className("article_title"));
    private By lessonTitleBy = By.xpath("//*[@class='lessonHeader']/*[@class='title']/div[@class='titleText']");
    private WebElement lessonTitle = $(By.xpath("//*[@class='lessonHeader']/*[@class='title']/div[@class='titleText']"));
    private WebElement lessonTitleOfWritingLesson = $(By.className("article_title"));
    private WebElement addSubmitButton = $(By.xpath("//*[@id='step14activity']/div/lesson-activity/form/div/div[2]/div/button"));







    public List<String> invalidAlertsPopupText;
    private List<String> messagesAfterSubmitCorrectAnswer;
    private List<String> messagesAfterSubmitCorrectAnswerSp;



    public Lesson(WebDriver driver) {
        super();
        this.driver = driver;
        //PageFactory.initElements(driver, this);

        invalidAlertsPopupText = new ArrayList<>();
        invalidAlertsPopupText.add("Oops, you may have answered without thinking things through. Next time, make sure to read the whole question. Then pick your answer. This will help your reading.");
        invalidAlertsPopupText.add("Oops, it looks like you may have answered without giving a lot of thought to your answers. Next time, try reading the whole question before choosing your answer. That will help you become a better reader.");
        invalidAlertsPopupText.add("Oops, it looks like you may have responded without carefully considering your answers. Next time, try reading the whole question before choosing your answer. Practicing like this will help you become a better reader.");

        invalidAlertsPopupText.add("Oops, you may have answered without thinking things through. Next time, make sure to read the whole question. Then pick your answer. This will help your reading.");
        invalidAlertsPopupText.add("You finished those questions really fast. Did you read each question? How about each answer choice? It’s important to do that. This will help your reading scores get better.");
        invalidAlertsPopupText.add("You finished those questions really fast. Did you read each question as well as all the answer choices? If you take the time to read the material, you're likely to get a better reading score.");
        invalidAlertsPopupText.add("You finished those questions really fast. Did you read each question as well as all the answer choices? If you take the time to read the material, you're likely to" +
                " get a better reading score.");

        invalidAlertsPopupText.add("Oops, those questions took a long time. You shouldn’t rush. But you want to keep moving. Also make sure to think as you read. This will help your reading get better.");
        invalidAlertsPopupText.add("Oops, you took a long time to finish those questions. You shouldn’t rush, but you want to make sure to keep moving. But make sure to pay attention. This will help your reading get better.");
        invalidAlertsPopupText.add("Oops, you took a long time to finish those questions. While it’s important not to rush, try to focus on the task and keep a steadier pace next time. Giving your full attention will help your reading to improve.");

        invalidAlertsPopupText.add("Olvidaste revisar bien tus respuestas. La prГіxima vez, asegГєrate de leer bien toda la pregunta. Luego, escoge tu respuesta. Esto te ayudarГЎ en tu lectura.");
        invalidAlertsPopupText.add("Olvidaste revisar bien tus respuestas. La prГіxima vez, intenta leer bien toda la pregunta antes de escoger tu respuesta. Eso te ayudarГЎ a convertirte en un mejor lector.");
        invalidAlertsPopupText.add("Olvidaste revisar bien tus respuestas. La prГіxima vez, intenta leer bien toda la pregunta antes de seleccionar tu respuesta. Practicar esto te ayudarГЎ a convertirte en un mejor lector.");

        invalidAlertsPopupText.add("Terminaste las preguntas muy rГЎpido. ВїLeГ­ste cada pregunta? Вї Y cada respuesta? Es importante hacerlo.  Eso te ayudarГЎ a mejorar tu puntaje de lectura.");
        invalidAlertsPopupText.add("Terminaste las preguntas muy rГЎpido. ВїLeГ­ste cada pregunta y cada posible respuesta? Si tomas mГЎs tiempo para leer todo muy bien, seguro tendrГЎs un mejor puntaje de lectura.");
        invalidAlertsPopupText.add("Terminaste las preguntas demasiado rГЎpido. ВїLeГ­ste cada pregunta y posible respuesta? Si tomas mГЎs tiempo para leer todo cuidadosamente, seguro obtendrГЎs un mejor puntaje de lectura.");

        invalidAlertsPopupText.add("Esas preguntas te tomaron mucho tiempo. No debes apresurarte. Pero debes seguir avanzando. TambiГ©n, debes poner atenciГіn. Eso te ayudarГЎ a mejorar tu lectura.");
        invalidAlertsPopupText.add("Te tardaste mucho en terminar las preguntas. No debes hacerlo a la carrera, pero debes avanzar a un buen ritmo. TambiГ©n debes poner atenciГіn. Eso te ayudarГЎ a mejorar tu lectura.");
        invalidAlertsPopupText.add("Esas preguntas te tomaron mucho tiempo. Aunque es importante no apresurarse, la prГіxima vez, procura enfocarte mГЎs y mantener un buen ritmo de trabajo. Poner toda tu atenciГіn en la actividad te ayudarГЎ a mejorar tu lectura.");

        messagesAfterSubmitCorrectAnswer = new ArrayList<>();
        messagesAfterSubmitCorrectAnswer.add("Great!");
        messagesAfterSubmitCorrectAnswer.add("Terrific!");
        messagesAfterSubmitCorrectAnswer.add("Wow!");
        messagesAfterSubmitCorrectAnswer.add("Very nice!");
        messagesAfterSubmitCorrectAnswer.add("Fantastic!");
        messagesAfterSubmitCorrectAnswer.add("Super!");
        messagesAfterSubmitCorrectAnswer.add("Very good!");
        messagesAfterSubmitCorrectAnswer.add("Excellent!");
        messagesAfterSubmitCorrectAnswer.add("Way to go!");
        messagesAfterSubmitCorrectAnswer.add("Wonderful!");
        messagesAfterSubmitCorrectAnswer.add("Good job!");
        messagesAfterSubmitCorrectAnswer.add("Well done!");
        messagesAfterSubmitCorrectAnswer.add("Keep it up!");
        messagesAfterSubmitCorrectAnswer.add("Nice!");
        messagesAfterSubmitCorrectAnswer.add("Amazing!");
        messagesAfterSubmitCorrectAnswer.add("Awesome!");
        messagesAfterSubmitCorrectAnswer.add("Correct!");
        messagesAfterSubmitCorrectAnswer.add("Cool!");

        messagesAfterSubmitCorrectAnswerSp = new ArrayList<>();
        messagesAfterSubmitCorrectAnswerSp.add("¡MagnГ­fico!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Fabuloso!");
        messagesAfterSubmitCorrectAnswerSp.add("¡SГєper!");
        messagesAfterSubmitCorrectAnswerSp.add("¡IncreГ­ble!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Impresionante!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Fenomenal!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Correcto!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Excelente!");
        messagesAfterSubmitCorrectAnswerSp.add("¡FantГЎstico!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Buen trabajo!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Sigue asГ­!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Bien!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Estupendo!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Muy bien!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Perfecto!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Bien hecho!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Maravilloso!");

    }

    public Lesson(WebDriver driver, String name) {
        super(name, By.xpath("//div[contains(@class,'lessonContainer')]"), driver);
        //this.driver = driver;
        //PageFactory.initElements(driver, this);

        invalidAlertsPopupText = new ArrayList<>();
        invalidAlertsPopupText.add("Oops, you may have answered without thinking things through. Next time, make sure to read the whole question. Then pick your answer. This will help your reading.");
        invalidAlertsPopupText.add("Oops, it looks like you may have answered without giving a lot of thought to your answers. Next time, try reading the whole question before choosing your answer. That will help you become a better reader.");
        invalidAlertsPopupText.add("Oops, it looks like you may have responded without carefully considering your answers. Next time, try reading the whole question before choosing your answer. Practicing like this will help you become a better reader.");

        invalidAlertsPopupText.add("Oops, you may have answered without thinking things through. Next time, make sure to read the whole question. Then pick your answer. This will help your reading.");
        invalidAlertsPopupText.add("You finished those questions really fast. Did you read each question? How about each answer choice? It’s important to do that. This will help your reading scores get better.");
        invalidAlertsPopupText.add("You finished those questions really fast. Did you read each question as well as all the answer choices? If you take the time to read the material, you're likely to get a better reading score.");
        invalidAlertsPopupText.add("You finished those questions really fast. Did you read each question as well as all the answer choices? If you take the time to read the material, you're likely to" +
                " get a better reading score.");

        invalidAlertsPopupText.add("Oops, those questions took a long time. You shouldn’t rush. But you want to keep moving. Also make sure to think as you read. This will help your reading get better.");
        invalidAlertsPopupText.add("Oops, you took a long time to finish those questions. You shouldn’t rush, but you want to make sure to keep moving. But make sure to pay attention. This will help your reading get better.");
        invalidAlertsPopupText.add("Oops, you took a long time to finish those questions. While it’s important not to rush, try to focus on the task and keep a steadier pace next time. Giving your full attention will help your reading to improve.");

        invalidAlertsPopupText.add("Olvidaste revisar bien tus respuestas. La prГіxima vez, asegГєrate de leer bien toda la pregunta. Luego, escoge tu respuesta. Esto te ayudarГЎ en tu lectura.");
        invalidAlertsPopupText.add("Olvidaste revisar bien tus respuestas. La prГіxima vez, intenta leer bien toda la pregunta antes de escoger tu respuesta. Eso te ayudarГЎ a convertirte en un mejor lector.");
        invalidAlertsPopupText.add("Olvidaste revisar bien tus respuestas. La prГіxima vez, intenta leer bien toda la pregunta antes de seleccionar tu respuesta. Practicar esto te ayudarГЎ a convertirte en un mejor lector.");

        invalidAlertsPopupText.add("Terminaste las preguntas muy rГЎpido. ВїLeГ­ste cada pregunta? Вї Y cada respuesta? Es importante hacerlo.  Eso te ayudarГЎ a mejorar tu puntaje de lectura.");
        invalidAlertsPopupText.add("Terminaste las preguntas muy rГЎpido. ВїLeГ­ste cada pregunta y cada posible respuesta? Si tomas mГЎs tiempo para leer todo muy bien, seguro tendrГЎs un mejor puntaje de lectura.");
        invalidAlertsPopupText.add("Terminaste las preguntas demasiado rГЎpido. ВїLeГ­ste cada pregunta y posible respuesta? Si tomas mГЎs tiempo para leer todo cuidadosamente, seguro obtendrГЎs un mejor puntaje de lectura.");

        invalidAlertsPopupText.add("Esas preguntas te tomaron mucho tiempo. No debes apresurarte. Pero debes seguir avanzando. TambiГ©n, debes poner atenciГіn. Eso te ayudarГЎ a mejorar tu lectura.");
        invalidAlertsPopupText.add("Te tardaste mucho en terminar las preguntas. No debes hacerlo a la carrera, pero debes avanzar a un buen ritmo. TambiГ©n debes poner atenciГіn. Eso te ayudarГЎ a mejorar tu lectura.");
        invalidAlertsPopupText.add("Esas preguntas te tomaron mucho tiempo. Aunque es importante no apresurarse, la prГіxima vez, procura enfocarte mГЎs y mantener un buen ritmo de trabajo. Poner toda tu atenciГіn en la actividad te ayudarГЎ a mejorar tu lectura.");

        messagesAfterSubmitCorrectAnswer = new ArrayList<>();
        messagesAfterSubmitCorrectAnswer.add("Great!");
        messagesAfterSubmitCorrectAnswer.add("Terrific!");
        messagesAfterSubmitCorrectAnswer.add("Wow!");
        messagesAfterSubmitCorrectAnswer.add("Very nice!");
        messagesAfterSubmitCorrectAnswer.add("Fantastic!");
        messagesAfterSubmitCorrectAnswer.add("Super!");
        messagesAfterSubmitCorrectAnswer.add("Very good!");
        messagesAfterSubmitCorrectAnswer.add("Excellent!");
        messagesAfterSubmitCorrectAnswer.add("Way to go!");
        messagesAfterSubmitCorrectAnswer.add("Wonderful!");
        messagesAfterSubmitCorrectAnswer.add("Good job!");
        messagesAfterSubmitCorrectAnswer.add("Well done!");
        messagesAfterSubmitCorrectAnswer.add("Keep it up!");
        messagesAfterSubmitCorrectAnswer.add("Nice!");
        messagesAfterSubmitCorrectAnswer.add("Amazing!");
        messagesAfterSubmitCorrectAnswer.add("Awesome!");
        messagesAfterSubmitCorrectAnswer.add("Correct!");
        messagesAfterSubmitCorrectAnswer.add("Cool!");

        messagesAfterSubmitCorrectAnswerSp = new ArrayList<>();
        messagesAfterSubmitCorrectAnswerSp.add("¡MagnГ­fico!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Fabuloso!");
        messagesAfterSubmitCorrectAnswerSp.add("¡SГєper!");
        messagesAfterSubmitCorrectAnswerSp.add("¡IncreГ­ble!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Impresionante!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Fenomenal!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Correcto!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Excelente!");
        messagesAfterSubmitCorrectAnswerSp.add("¡FantГЎstico!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Buen trabajo!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Sigue asГ­!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Bien!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Estupendo!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Muy bien!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Perfecto!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Bien hecho!");
        messagesAfterSubmitCorrectAnswerSp.add("¡Maravilloso!");
    }

    public void goToActivityTab() {
        closeWalkmePopup();
        clickJS(activityTabBy);
        waitUntilAppearsBy(activityTabBy);
    }

    public void clickNextTabButton() {
        if (isPopupVisible()) {
            closePopup();
        }
        if (isDisplayedBy(viewResultsButtonBy)) {
            $(viewResultsButtonBy).click();
        }
        $(nextButtonBy).click();
        if (isPopupVisible()) {
            closePopup();
        }
    }

    public void clickSubmitButtonARP() {
        clickJS(submitButtonARTBy);
        waitAndCLickIfExist(OKButtonBy);
    }

    public void createNewDraft() {
        $(newDraftButtonBy).click();
    }

    public void goToMathTab() {
        $(mathTabBy).click();
    }

    public boolean verifyPresenceWordsLinks() {
        return isElementsExist(wordsLinksBy);
    }

    public boolean verifyPresenceImage() {
        if ($(imageBy).exists())
            return true;
        else if ($(page2onArcticleBy).exists()) {
            $(page2onArcticleBy).click();
            return $(imageBy).isDisplayed();
        } else if ($(page2onStretchArcticleBy).exists()) {
            $(page2onStretchArcticleBy).click();
            return $(imageBy).exists();
        }
        return false;
    }

    public boolean verifyPresenceCredentials() {
        return $(credentialsBy).exists();
    }

    public boolean verifyPresencePrintButton() {
        return $(printBy).exists();
    }

    public void checkWordLink() {
        $(findEls(wordsLinksBy).get(0)).click();
        closePopup();
    }

    public boolean verifyPresenceQuestionTQ() {
        return isElementsExist(questionTQBy);
    }

    public boolean verifyPresenceQuestion() {
        if ($(pollQuestionBy).exists()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyPresenceRadiobuttons() {
        return $(radiobuttonsBy).exists();
    }

    public boolean verifyPresenceSubmitButtonART() {
        return $(submitButtonARTBy).exists();
    }

    public boolean checkRadiobuttonsClickable() {
        $(radioWeb).click();
        String status = getAttribute(radioWeb, "class");

        if (status.equals("wordwrap choice_pick")) {
            return true;
        } else {
            return false;
        }
    }

    public void waitSubmitButon() {
        waitElementBy(submitButtonLiteracyBy);
    }

    public int getPresenceFourAnswerOptions() {
        if (isElementsExist(answersNewBy)) {
            return findEls(answersNewBy).size();
        } else {
            return findEls(answersBy).size();
        }
    }

    public int currentPoints() {
        return Integer.parseInt(findEl(pointsBy).getText());
    }

    public String currentQuestion() {
        return findEl(currentAndTotalQuestionBy).getAttribute("data-current-question");
    }

    public boolean selectFirstAnswer() {
        try {
            waitUntilCollectionAppears(findEls(allAnswersBy));
            WebElement answer = findEls(allAnswersBy).get(0);
            $(answer).click();
            if (!isAnyAnswerSelected()) {
                answer = findEls(allAnswersBy).get(0);
                $(answer).click();
            }
            String highlight = getAttribute(answer, "class");
            return highlight.contains("selected");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean isAnyAnswerSelected() {
        waitUntilAppearsBy(selectedAnswerBy);
        return isElementPresentBy(selectedAnswerBy);
    }

    public void clickActivitySubmitButton() {
        try {
            if($(submitBtnOnActivityTabBy).isDisplayed()) {
                $(submitBtnOnActivityTabBy).click();
            } else if($(addSubmitButton).isDisplayed()){
                $(addSubmitButton).click();
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public boolean isAnswerWasInccorect() {
        //TODO fix answerWasIncorect\
        boolean result = $$(findEls(answersBy)).size() == 3;
        return result;
    }

    public boolean verifyVisibleMotivationMessage() {
        return isDisplayedBy(messageAfterSubmitAnswerBy);
    }

    public void clickNextQuestionButton() {
        if($(nextQuestionButtonInActivityTabBy).isDisplayed()) {
            $(nextQuestionButtonInActivityTabBy).click();
        } else if ($(addSubmitButton).isDisplayed()){
            $(addSubmitButton).click();
        }
    }

    public boolean verifyVisibleNextQuestionButton() {
        return $(nextQuestionButtonInActivityTab).isDisplayed();
    }

    public void completeTeiActivity() throws InterruptedException {
        completePart1OfTeiActivity("normal");

        if (isViewResultsButtonVisible()) {
            return;
        }
        completePart2OfTeiActivity();

        if (isPopupVisible()) {
            closePopup();
        }
    }

    public void completePart1OfTeiActivity(String speed) throws InterruptedException {
        int part1Time = 0;

        if (speed.equals("slowly"))
            part1Time = 780 / 7;
        else if (speed.equals("slowlysp") || speed.equals("slowlyinterv"))
            part1Time = 1560 / 7;
        else if (speed.equals("normal"))
            part1Time = 7 / 7;

        int i = 0;
        while (!isDisplayedBy(activityPart2LiteracyPickedBy) &&
                !isViewResultsButtonVisible()) {
            choseAnswer(i, part1Time);
        }
    }

    private void choseAnswer(int i, int part1Time) throws InterruptedException {
        clickOnAnswer(i);
        clickOnSubmitButton();

        if (isTryAgainMessageDisplayed()) {
            clickOnAnswer(i);
            clickOnSubmitButton();
        }
        if (isPopupVisible()) {
            closePopup();
            return;
        }
        if (!isViewResultsButtonVisible()) {
            waitNextQuestionButton();
            Thread.sleep(part1Time * 1000);
            clickOnNextQuestionButton();
            if (isElementPresentBy(smthgWrongOkButtonBy)) {
                $(smthgWrongOkButtonBy).click();
                choseAnswer(i, part1Time);
            }
            ++i;
        }
    }

    public void clickOnNextQuestionButton() {
        if (isElementPresentBy(nextQuestionButtonLiteracyBy))
            $(nextQuestionButtonLiteracyBy).click();
    }

    public void waitNextQuestionButton() {
        waitElement(nextQuestionButtonLiteracyBy);
    }

    public boolean isViewResultsButtonVisible() {
        return isDisplayedBy(viewResultsButtonLiteracyBy);
    }

    public void clickOnSubmitButton() {
        waitForActivityAnswers();
        waitUntilElementClickableBy(submitButtonLiteracyBy);
        waitForJSandJQueryToLoad();
        clickJS(submitButtonLiteracyBy);
    }

    public void waitForActivityAnswers() {
        waitUntilCollectionAppears(findEls(notSelectedAnswersBy));
    }

    public void clickOnAnswer(int number) {
        ElementsCollection elements = null;
        waitUntilCollectionAppears(findEls(notSelectedAnswersBy));
        int countOfAnswer = $$(findEls(notSelectedAnswersBy)).size();
        if (countOfAnswer <= 0) {
            return;
        }
        number = (number > countOfAnswer ? number % countOfAnswer : number == 0 ? number : number - 1);

        //todo: fix IndexOutOfBoundsException
        if (number >= 0) {
            logger.info("Click on answer " + number);
            elements = findEls(notSelectedAnswersBy);
            for (int tries = 4; tries >= 0; tries--) { //????
                while ($$(elements).size() == 0) {
                    elements = findEls(notSelectedAnswersBy);
                }
            }
            $(elements.get(number)).click();
            waitSelectedAnswer();
        }
     //   waitSubmitButon();
    }

    public boolean isTryAgainMessageDisplayed() {
        return isDisplayedBy(tryAgainMessageBy);
    }

    public void completePart2OfTeiActivity() throws InterruptedException {
        while (!isViewResultsButtonVisible()) {
            if ($$(questionContainers).size() == 2) {
                int totalQuestions = getTotalQuestionNumber();
                int currentQuestion = getCurrentQuestionNumber();
                int part2Time = (int) (70 / ((totalQuestions - currentQuestion) + 0.5));
                completeCurrentPartOfActivityLocal(partAContainer, 1);
                completeCurrentPartOfActivityLocal(partBContainer, 1);
                clickOnSubmitButton();
                if (isPopupVisible()) {
                    closePopup();
                    break;
                }
                if (isTryAgainMessageDisplayed()) {
                    completeCurrentPartOfActivityLocal(partAContainer, 2);
                    completeCurrentPartOfActivityLocal(partBContainer, 2);
                    clickOnSubmitButton();
                    if (isElementPresent(smthgWrongOkButton)) {
                        $(smthgWrongOkButton).click();
                    }

                }
                if (!isViewResultsButtonVisible()) {
                    waitNextQuestionButton();
                    Thread.sleep(part2Time * 1000);
                    clickOnNextQuestionButton();
                }
            } else {
                //
                int totalQuestions = getTotalQuestionNumber();
                int currentQuestion = getCurrentQuestionNumber();
                int part2Time = (int) (70 / ((totalQuestions - currentQuestion) + 0.5));
                while (!$(viewResultsButtonLiteracy).isDisplayed()) {

                    if (isDNDDisplayed()) {
                        dragAndDrop($$(listOfSourceDNDItems).get(1), $$(listOfTargetDNDItems).get(0));

                        clickOnSubmitButton();
                        if (isTryAgainMessageDisplayed()) {
                            dragAndDrop($$(listOfSourceDNDItems).get(0), $$(listOfTargetDNDItems).get(1));
                            clickOnSubmitButton();
                        }
                    } else if (isSequencingDisplayed()) {
                        changeSequence(0, 1);
                        clickOnSubmitButton();
                        if (isTryAgainMessageDisplayed()) {
                            changeSequence(2, 3);
                            clickOnSubmitButton();
                        }
                    } else if (isListOfAnswersDisplayed()) {
                        clickOnAnswer(1);
                        clickOnAnswer(2);

                        clickOnSubmitButton();

                        if (isTryAgainMessageDisplayed()) {
                            clickOnAnswer(1);
                            clickOnSubmitButton();
                        }
                    } else if (isChartWithSentencesDisplayed()) {
                        selectSomeSentences();
                        clickOnSubmitButton();
                        if (isTryAgainMessageDisplayed()) {
                            selectSomeSentences();
                            clickOnSubmitButton();
                        }
                    }

                    if (!$(viewResultsButtonLiteracy).isDisplayed() && !isPopupVisible()) {
                        waitElementWebEl(nextQuestionButtonLiteracy);
                        Thread.sleep(part2Time * 1000);
                        clickOnNextQuestionButton();
                    }
                }
            }
        }
    }

    public void selectSomeSentences() {
        $(sentecesInChart.get(sentecesInChart.size() / 2)).click();
        $(sentecesInChart.get(sentecesInChart.size() - 1)).click();
    }

    public boolean isChartWithSentencesDisplayed() {
        if (isDisplayed(chartWithSentences))
            if ($$(sentecesInChart).size() > 0)
                return true;
            else return false;
        else return false;
    }

    public boolean isListOfAnswersDisplayed() {
        return $$(allAnswers).size() > 0;
    }

    public void changeSequence(int posOfSourceElement, int posOfTargetElement) {
        changeSequence($$(listOfSequencingElements).get(posOfSourceElement),
                $$(listOfSequencingElements).get(posOfTargetElement));
    }

    public boolean isSequencingDisplayed(ElementsCollection listOfSequence) {
        return $$(listOfSequence).size() > 0;
    }

    public boolean isSequencingDisplayed() {
        return $$(listOfSequencingElements).size() > 0;
    }

    public boolean isDNDDisplayed() {
        return $$(listOfSourceDNDItems).size() > 0 && $$(listOfTargetDNDItems).size() > 0;
    }

    public int getTotalQuestionNumber() {
        waitElementWebEl(currentAndTotalQuestion);
        return Integer.valueOf($(currentAndTotalQuestion).getAttribute("data-total-questions"));
    }

    public boolean isCurrentQuestion(int number) {
        return getCurrentQuestionNumber() == number;
    }

    public int getCurrentQuestionNumber() {
        return Integer.valueOf($(currentAndTotalQuestion).getAttribute("data-current-question"));
    }

    public void completeCurrentPartOfActivityLocal(WebElement part, int attempt) {

        ElementsCollection listOfSourceDNDItemsForCurrentPart = null;
        ElementsCollection listOfTargetDNDItemsForCurrentPart = null;
        ElementsCollection listOfSequenceElements = null;
        ElementsCollection listOfNotSelectedAnswers = null;
        ElementsCollection AllSentencesInChart = null;

        if (checkListOfElementsExistInWebElement(part, listOfSourceDNDItemsForPart) && checkListOfElementsExistInWebElement(part, listOfTargetDNDItemsForPart)) {
            listOfSourceDNDItemsForCurrentPart = $(part).findAll(listOfSourceDNDItemsForPart);
            listOfTargetDNDItemsForCurrentPart = $(part).findAll(listOfTargetDNDItemsForPart);
            dragAndDrop($$(listOfSourceDNDItemsForCurrentPart).get(1), $$(listOfTargetDNDItemsForCurrentPart).get(0));
        } else if (checkListOfElementsExistInWebElement(part, listOfSequenceElementsForPart)) {
            listOfSequenceElements = $(part).findAll(listOfSequenceElementsForPart);
            changeSequence(listOfSequenceElements, 0, 1);
        } else if (checkListOfElementsExistInWebElement(part, listOfAllAnswersForPart)
                && checkListOfElementsExistInWebElement(part, listOfNotSelectedAnswersForPart)) {
            listOfNotSelectedAnswers = $(part).findAll(listOfNotSelectedAnswersForPart);
            if (!(attempt == 2)) {
                clickOnAnswer(listOfNotSelectedAnswers, 1);
                clickOnAnswer(listOfNotSelectedAnswers, 2);
                clickOnAnswer(listOfNotSelectedAnswers, 3);

            }
        } else if (checkListOfElementsExistInWebElement(part, listOfAllSentencesInChartForPart) &&
                checkElementExistInWebElement(part, chartWithSentenceForPart)) {
            AllSentencesInChart = $(part).findAll(listOfAllSentencesInChartForPart);
            selectSomeSentences(AllSentencesInChart);
        } else return;
    }

    public boolean checkListOfElementsExistInWebElement(WebElement parent, By by){
        ElementsCollection temp = null;
        try {
            temp = $(parent).findAll(by);
        } catch (Exception e) {
        }
        if ($$(temp).size() > 0)
            return true;
        else
            return false;
    }

    public boolean checkElementExistInWebElement(WebElement parent, By by){
        try {
            $(parent).find(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void clickOnAnswer(ElementsCollection notSelectedAnswersInListOfAnswers, int number) {
        waitUntilCollectionAppears(notSelectedAnswersInListOfAnswers);
        int countOfAnswer = notSelectedAnswersInListOfAnswers.size();
        number = (number > countOfAnswer ? number % countOfAnswer : number == 0 ? number : number - 1);
        WebElement selAns = notSelectedAnswersInListOfAnswers.get(number);
        $(selAns).click();
        waitSelectedAnswer(selAns);
        waitSubmitButon();
    }

    public void waitSelectedAnswer() {
        try {
            waitElement(selectedAnswerBy);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void waitSelectedAnswer(WebElement selectedAns) {
        try {
            waitElementWebEl(selectedAns);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void changeSequence(ElementsCollection listOfSequence, int posOfSourceElement, int posOfTargetElement) {
        changeSequence(listOfSequence.get(posOfSourceElement), listOfSequence.get(posOfTargetElement));
    }

    public void selectSomeSentences(ElementsCollection sentencesInChartInDiv) {
        $(sentencesInChartInDiv.get($$(sentencesInChartInDiv).size() / 2)).click();
        $(sentencesInChartInDiv.get($$(sentencesInChartInDiv).size() - 1)).click();
    }

    public void waitForViewResultButton() {
        waitUntilAppearsBy(viewResultButtonInActivityTabBy);
    }

    public void clickSubmitButtonBRT() {
//        waitForSubmitButton();
        clickJS(submitButtonBRTBy);
        waitAndCLickIfExist(OKButtonBy);
        while (isDisplayedBy(nextPageArrowButtonBy)) {
            clickJS(nextPageArrowButtonBy);
        }
        clickJS(nextButtonBy);
    }

    public String chooseARPAnswer() {
        $(ARPAnswerBy).click();
        String answerId = $(findEl(ARPAnswerBy)).find(By.xpath(".//div")).getAttribute("id");
        return answerId;
    }

    public void switchBackToWindow() {
        switchTo().defaultContent();
    }

    public void typeTextInTextboxBRP(String text) {
        boolean iframe = false;
        waitForPageToLoad();
        super.waitElement(textAreaBRPARPBy);
        if (isDisplayedBy(By.xpath(".//*[@id='textarea-step10page1response1_ifr']"))) {
            iframe = true;
            switchTo().defaultContent(); // you are now outside both frames
            switchTo().frame("textarea-step10page1response1_ifr");
        }
        waitForPageToLoad();

        $(findEl(textAreaBRPARPBy)).setValue(text);

        if (iframe) {
            switchTo().defaultContent();
        }
    }

    public void switchToBrpTextArea() {
        switchTo().frame(findEl(textAreaBRPframeBy));
    }

    public void fillReadingConnections() {
        WebElement rc = findEls(readingConnectionsBy).get(0);

        String containerId = getAttribute(rc, "id");
        $(rc).click();

        ElementsCollection sections = $(rc).findAll(By.xpath("div/a/div"));
        if (sections.size() < 2) {
            waitUntilCollectionAppears(sections);
            sections = $(rc).findAll(By.xpath("div/a/div"));
            if ($$(sections).size() < 2) {
                logger.error("There are no 3 sections above reading connections");
                return;
            }
        }

        String testMessage = "Test Reading Connections";

        for (WebElement s : sections) {
            String message = "Trying to click on element " + s;
            message = message.replaceFirst("\\[(.*?)\\]", "");
            //message = message.substring(0, message.lastIndexOf("]"));
            logger.debug(message);
            logger.debug("Opening section " + s);
            $(s).click();



            WebElement textArea = $(By.xpath("//div[@id='" + containerId + "_child" + (sections.indexOf(s) + 1) + "-content']//textarea"));

            logger.info("Type text '" + testMessage + "' to input");
            $(textArea).setValue(testMessage);

            WebElement saveButton = $(By.xpath("//div[@id='" + containerId + "_child" + (sections.indexOf(s) + 1) + "-content']//div[@class='saveButton']"));

            logger.info("Saving...");
            $(saveButton).click();
        }
    }

    public boolean waitUntilAppearsVocabularyList() {
        try {
            waitAndCLickIfExist(closePopupButtonBy);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyPresenceResultsTable() {
        return isElementPresent(resultTable);
    }

    public void goToStretchActivityTab() {
        $(stretchActivityTabBy).click();
    }

    public boolean isStretchTabBlocked() {
        return $(stretchBlockBy).exists();
    }

    public void clickViewResultsButton() {
        waitElement(viewResultButtonInActivityTabBy);
        clickJS(viewResultButtonInActivityTabBy);
    }

    public String checkFinishLaterButton(String text) {

//        String mainWin = driver.getWindowHandle();
        switchTo().frame(findEl(draft1FrameBy));
        $(findEl(textFieldBy)).sendKeys(text);

        switchTo().defaultContent();
        $(finishLaterButtonBy).click();

        //waitUntilPopupAppears(10);
        String popup = getPopupContent();

        closePopup();
        return popup;
    }

    public String checkSubmitButtonTQ() {
        typeTextInDraft("String text");
        $(submitButtonTQBy).click();

        //waitUntilPopupAppears(5);
        String popup = getPopupContent();

        closePopup();
        return popup;
    }

    public void typeTextInDraft(String text) {
        String mainWin = getWebDriver().getWindowHandle();

        switchTo().frame(findEl(selectedFrameBy));
        $(findEl(textFieldBy)).setValue(text);

        switchTo().defaultContent();
    }

    public boolean verifyPrecenceAcitivityQuestion() {
        return isElementsExist(activityQuestion);
    }

    public boolean verifyPresenceActivitySubmitButtonMath() {
        return $(submitButtonMath).exists();
    }

    public int getPresenceFourAnswerOptionsForMath() {
        return $$(answersMath).size();
    }

    public boolean selectFirstAnswerMath() {
        try {
            waitUntilCollectionAppears(answersMath);
            WebElement answer = $$(answersMath).get(0);

            $(answer).click();
            if (!isAnyAnswerSelected()) {
                answer = $$(answersMath).get(0);
                $(answer).click();
            }
            String highlight = getAttribute(answer, "class");
            return highlight.contains("selected");
        } catch (Exception e) {
            System.out.print("");
        }
        return false;
    }

    public void clickMathSubmitButton() {
        $(submitButtonMath).click();
    }

    public void clickViewResultsButtonMath() {
        waitAndCLickIfExist(viewResultsButton);
    }

    public boolean isTryAgainButtonVisible() {
        return $(tryAgainButton).isDisplayed();
    }

    public boolean verifyVisibleMotivationMessageMath() {
        return $(feedbackMath).isDisplayed();
    }

    public void clickTryAgainButtonMath() {
        $(tryAgainButtonMath).click();
    }

    public boolean isWritingTitleExists() {
        return isDisplayed(writingLessonTitle);
    }

    public String getWritingTitleText() {
        return writingLessonTitle.getText();
    }

    public String getLessonName() {
        waitUntilAppearsBy(lessonTitleBy);
        if ($(lessonTitleBy).exists())
            return $(lessonTitle).getText();
        else
            return $(lessonTitleOfWritingLesson).getText();
    }


}
