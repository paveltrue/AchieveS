package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

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




    public List<String> invalidAlertsPopupText;
    private List<String> messagesAfterSubmitCorrectAnswer;
    private List<String> messagesAfterSubmitCorrectAnswerSp;




    public Lesson(WebDriver driver, String name) {
        super(name, By.xpath("//div[contains(@class,'lessonContainer')]"), driver);
        //this.driver = driver;
        PageFactory.initElements(driver, this);

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
            click(submitBtnOnActivityTabBy);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }





}
