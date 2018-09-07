package com.a3k.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

public class MyLessons extends Page {

    private By doubleBackArrowBy = By.xpath("//img[contains(@src,'doubleback')]");
    By nextArrowOnListViewBy = By.xpath("//img[contains(@src,'arrow_next')]");
    By notStartedLessonsBy = By.xpath("//tr[contains(@id,'lesson-') and not(contains(.,'N/A')) and not(contains(.,'Song'))  and not(contains(.,'Strategy Development')) and not(.//div[contains(@class,'Bonus')]) and not(.//*[contains(text(),'Challenge')] or .//*[contains(text(),'Solo para m')]) and not(.//a[@title!=''])]//a[@class='title']");
    private WebElement doubleBackArrow = $(By.xpath("//img[contains(@src,'doubleback')]"));
    private ElementsCollection lessonsList = $$(By.xpath("//tr[contains(@id,'lesson-')]"));
    WebElement nextArrowOnListView = $(By.xpath("//img[contains(@src,'arrow_next')]"));
    protected ElementsCollection lessonsTr = $$(By.xpath("//*[contains(@id,'lesson-')]"));
    protected By lessonTitle = By.xpath(".//*[contains(@class,'title')]");
    protected By avatarImage = By.xpath(".//*[@class='student-assigned']");
    protected By dateLabel = By.className("datelabel");
    protected By dataText = By.className("datecontent");
    protected By doubleBackNavigationButton = By.xpath(".//*[contains(@src,'_doubleback')]");
    protected By backNavigationButton = By.xpath(".//*[contains(@src,'_back')]");
    protected By doubleNextNavigationButton = By.xpath(".//*[contains(@src,'_doublenext')]");
    protected By nextNavigationButton = By.xpath(".//*[contains(@src,'_next')]");
    protected By navigatingNambers = By.xpath(".//*[contains(@class,'pageNumber')]");
    protected By firstSelectedPageNumber = By.xpath(".//*[position() = 1 and contains(@class,'Pick')]");
    protected By lastSelectedPageNumber = By.xpath(".//*[position() = last() and contains(@class,'Pick')]");
    protected By placeHolderTitle = By.xpath(".//*[contains(@class,'placeholder')]");
    protected By lessonSummary = By.xpath(".//*[@class='summary']/div");
    protected By lessonImage = By.xpath(".//*[@class='media-image' or @class='image']/img");
    protected By lessonType = By.xpath(".//*[@class='media-image']/div[@class!='']");
    protected By articleImage = By.xpath(".//*[@class='media-image']/div[contains(@class,'Article')]");
    protected By link = By.xpath(".//a");
    protected By lessonTopic = By.xpath(".//*[@class='titletopic']");
    protected ElementsCollection myLessonsNavigation = $$(By.className("pagenumbersContainer"));
    protected WebElement myLessonsHeader = $(By.xpath("//*[@class='header']/*[@class='title']"));
    private By deleteButtonsOfAllCollectionsBy = By.xpath(".//a[contains(@class, 'deleteCollection')]");
    private By deleteCollectionPopUpButtonYesBy = By.xpath(".//*[@class = 'ui-dialog-buttonset']//button/span[text() = 'Yes' or text() = 'Sí']");
    private WebElement buildLessonCollection = $(By.id("buildButton"));
    private By createCollectionBy = By.id("createCollection");
    private By collectionNameInputBy = By.id("collection_name");
    private WebElement descriptionInput = $(By.id("description"));
    private By programDDLBy = By.xpath(".//*[@id='program_id' and @name='program_id']");
    private By addLessonButtonBy = By.id("addLessonButton");
    protected By allTabsBy = By.xpath(".//div[@class = 'viewSelector']/div[contains(@class, 'ml_view')]");
    private By actualYearOnCalendarBy = By.xpath(".//*[@id='ui-datepicker-div']//span[contains(@class, 'year')]");
    private By collectionEndDateInputBy = By.id("end_date");
    private By collectionStartDateInputBy = By.id("start_date");
    private By assignNewLessonEveryInputBy = By.id("week_interval");
    private By daysOfWeekBy = By.xpath(".//*[contains(@class, 'days-of-week-container')]//input");
    private By keepLessonsLiveForInputBy = By.id("lesson_duration");
    private By saveCollectionButtonBy = By.xpath("//*[@id='saveButton' and @type='submit']");
    private WebElement changesSavedPopUpButton = $(By.xpath(".//*[@class = 'ui-dialog-buttonset']//button"));
    private By changesSavedPopUpButtonBy = By.xpath(".//*[@class = 'ui-dialog-buttonset']//button");
    private By yearViewButtonBy = By.xpath(".//*[contains(@href,'/my_lessons/year')]");
    protected WebElement selectedElementInPortfolioDDL = $(By.xpath(".//*[@class = 'mdropdown']"));
    protected ElementsCollection headerOf5Steps = $$(By.xpath("//*[contains(@class , 'headerColumn5steps headerColumn ')]"));
    protected ElementsCollection elementsOfPortfolioDDL = $$(By.xpath("//*[@id='my-lessons-portfolio-dropdown']//a"));
    protected By selectedElementInPortfolioDDLBy = By.xpath(".//*[@class = 'mdropdown']");
    protected By elementsOfPortfolioDDLBy = By.xpath("//*[@id='my-lessons-portfolio-dropdown']//a");
    protected By lessonsTrBy = By.xpath("//*[contains(@id,'lesson-')]");
    private WebElement okButtonOnCalendar = $(By.xpath(".//*[@data-text = 'OK']"));
    private By searchForMoreLessonsBy = By.xpath(".//*[@href='#lessons_side_panel']");
    private WebElement dateOfLesson = $(By.id("datecontent1"));
    private ElementsCollection calendars = $$(By.className("ui-datepicker-calendar"));
    private WebElement startOnCalendar = $(By.xpath(".//*[@id='startCal']/div[1]"));
    private WebElement endOnCalendar = $(By.xpath(".//*[@id='endCal']/div[1]"));
    private WebElement removeButton = $(By.xpath(".//*[@class='remove-button submit-button rolloverButton']"));
    private WebElement dateColumn = $(By.xpath(".//a[contains(@href,'start_date')]"));
    private WebElement lessonColumn = $(By.xpath(".//a[contains(@href,'sort=lesson')]"));
    private WebElement topicColumn = $(By.xpath(".//a[contains(@href,'sort=topic')]"));
    private WebElement strategyColumn = $(By.xpath(".//a[contains(@href,'sort=focus')]"));
    private ElementsCollection descriptionOfTheLesson = $$(By.xpath(".//div[@class = 'summary']/div"));
    protected ElementsCollection titlesOflessonsOnWeekView = $$(By.xpath(".//*[contains(@id, 'calendar')]//span[contains(@class, 'event-title')]"));
    private ElementsCollection titlesOfLesson = $$(By.xpath(".//a[@class = 'title']"));
    private WebElement dayButton = $(By.xpath(".//*[contains(@href,'/my_lessons/day')]"));
    private WebElement nextArrowCalendar = $(By.xpath(".//*[contains(@class, 'datepicker-next')]"));
    private WebElement changeDistrictLink = $(By.xpath(".//a[contains(@class, 'changeDistrict')]"));
    private By showClassesButtonListBy = By.xpath(".//*[@type='button' and contains(@class, 'showClasses')]");
    private WebElement courseClassesSaveButton = $(By.xpath(".//*[contains(@class, 'schoolLevelSave')]"));
    private By selectAllCheckboxCourseClassesBy = By.xpath("//*[@class='selectAllOption']");
    private WebElement selectAllCheckboxCourseClasses = $(By.xpath("//*[@class='selectAllOption']"));
    private By activeSchoolShowClassesBy = By.xpath(".//*[not(contains(@class, 'disabledSchool'))]/*[@class='schoolLabel']");
    private WebElement courseClassesChangeWarning = $(By.xpath("//*[contains(@class, 'changeWarning')]"));
    private WebElement courseClassesChangeWarningYesButton = $(By.xpath("//*[contains(@class, 'changeWarning')]//*[@id='course-all-classes-warning-yes-btn']"));
    private By courseClassesCancelLinkBy = By.xpath(".//*[@id='cancel-link']/a");
    private ElementsCollection showClassesCheckboxListKba = $$(By.xpath(".//*[@id='showClasses-dialog']//*[contains(@class, 'schoolChoice expanded')]/following-sibling::*//*[@type='checkbox']"));
    private ElementsCollection showClassesCheckboxListCheckedKba = $$(By.xpath(".//*[@id='showClasses-dialog']//*[contains(@class, 'schoolChoice expanded')]/following-sibling::*//*[@type='checkbox' and @checked='checked']"));
    private ElementsCollection showClassesCheckboxList = $$(By.xpath(".//*[@id='showClasses-dialog']//*[@type='checkbox']"));
    private ElementsCollection showClassesCheckboxListChecked = $$(By.xpath(".//*[@id='showClasses-dialog']//*[@type='checkbox' and @checked='checked']"));
    private WebElement selectedClasses = $(By.xpath(".//*[contains(@class, 'selected_classes')]"));
    private WebElement totalClasses = $(By.xpath(".//*[@class = 'total_classes']"));
    private WebElement courseClassesChangeWarningNoButton = $(By.xpath("//*[contains(@class, 'changeWarning')]//*[@id='course-all-classes-warning-no-btn']"));
    private By buildLessonCollectionBy = By.id("buildButton");
    private ElementsCollection sectionHeaders = $$(By.xpath(".//*[@class = 'collection_arrow']/.."));
    private By showClassesButtonBy = By.xpath(".//*[@id='show_classes' and @type='button']");
    private By showClassesDialogBy = By.id("showClasses-dialog");
    private By deleteCollectionYesButtonBy = By.xpath("//*[contains(@class, 'buttonset')]//*[contains(text(), 'Yes') or contains(text(), 'Sí')]");
    private By couldntDeleteButtonBy = By.xpath("//*[contains(@class, 'buttonset')]//*[contains(text(), 'OK')]");
    private By sectionHeadersBy = By.xpath(".//*[@class = 'collection_arrow']/..");
    private WebElement selectedCollectionGrade = $(By.xpath(".//*[@id='grade_level']/*[@selected='']"));
    private By showClassesDialogBy2 = By.xpath("//*[contains(@class, 'showClasses-dialog')]");
    private By continueButtonClassPopupBy = By.id("continueBtn");
    private WebElement showClassesCloseButton = $(By.xpath(".//*[@id='showClasses-dialog']/..//*[@role='button']"));
    private By clearClassesButtonBy = By.xpath("//*[contains(@class, 'cancelLink')]");
    private WebElement selectedClassesShowClasses = $(By.xpath("//*[@class='selected_classes']"));
    private WebElement selectAllShowClassesRadio = $(By.xpath("//*[@type='radio' and contains(@class, 'allClasses')]"));
    private WebElement someClassesCheckbox = $(By.xpath("//*[contains(@class, 'showClasses-dialog')]//*[contains(@class, 'someClasses')]"));
    private ElementsCollection showClassesClassList = $$(By.xpath("//*[contains(@class, 'showClasses-dialog')]//*[@type='checkbox' and contains(@name, 'class')]"));
    private By continueButtonInShowClassPopUpBy = By.id("continueBtn");
    private ElementsCollection showClassesCheckedClasses = $$(By.xpath("//*[contains(@class, 'showClasses-dialog')]//*[@type='checkbox' and contains(@name, 'class') and @checked='']"));
    private By disabledClassCheckboxListBy = By.xpath("//*[@class='className disabledClass']");
    private ElementsCollection collectionList = $$(By.xpath(".//*[@class='collectionTable']//*[@class='courseName']"));
    private WebElement collectionValidationAlert = $(By.xpath("//*[@class='validationAlert']"));
    private WebElement collectionNameInput = $(By.id("collection_name"));
    private By searchBtnBy = By.xpath("//div[@class='searchButton']//a");
    private WebElement lessonCounter = $(By.xpath(".//*[contains(@class, 'lesson-counter')]"));
    private By deleteLessonButtonListBy = By.xpath("//*[@class='closeBtn']");
    private By lessonNumbersBy = By.xpath("//*[@class='lessonTitle']/span[@class='lessonNum']");
    private By shareCollectionButtonBy = By.id("shareButton");
    private By collectionListBy = By.xpath(".//*[@class='collectionTable']//*[@class='courseName']");
    private WebElement alertDialog = $(By.xpath("//*[contains(@class, 'alert_dialog')]"));
    private By shareCollectionContinueButtonBy = By.xpath("//*[contains(@class, 'ui-dialog')]//*[contains(@class, 'buttonset')]//*[contains(text(), 'Continue') or contains(text(), 'Continuar')]");
    private By quickLookButtonBy = By.xpath("//a[@data-rollover='Quick Look' or @data-rollover='Vista rápida']");
    private ElementsCollection infoButtonsOnLessonCell = $$(By.xpath(".//*[contains(@class, 'detailIcon')]"));
    private By dayViewButtonBy = By.xpath(".//*[contains(@href,'/my_lessons/day')]");
    protected ElementsCollection lessonsOnDayView = $$(By.xpath("//*[contains(@id,'dayLesson')]//div[@class='lessonTitle day' and not (contains(text(),' Placeholder '))]"));
    protected ElementsCollection lessonsOnWeekView = $$(By.xpath("//*[@id=\"calendar-2788860\"]/div/div/div/div"));
    protected ElementsCollection lessonsOnMonthView = $$(By.xpath("//*[@id=\"calendar\"]/div/div/div/div"));
    private By lessonsOnDayViewBy = By.xpath("//*[contains(@id,'dayLesson')]");
    private WebElement selectedLessonsTitleFromView = $(By.xpath("//*[@class='title' and @onclick]"));
    private By weekViewButtonBy = By.xpath(".//*[contains(@href,'/my_lessons/week')]");
    private By loadingHoverBy = By.xpath("(//*[contains(@class,'loading')]/img)[1]");
    protected By titlesOflessonsOnWeekViewBy = By.xpath(".//*[contains(@id, 'calendar')]//span[contains(@class, 'event-title')]");
    private By monthViewButtonBy = By.xpath(".//*[contains(@href,'/my_lessons/month')]");
    private WebElement deleteCollectionYesButton = $(By.xpath("//*[contains(@class, 'buttonset')]//*[contains(text(), 'Yes') or contains(text(), 'Sí')]"));
    private By deleteCollectionNoButtonBy = By.xpath("//*[contains(@class, 'buttonset')]//*[contains(text(), 'No') or contains(text(), 'NO')]");
    private By continueButtonAssignCollectionBy = By.xpath("//*[contains(@class, 'buttonset')]//*[contains(text(), 'Continue') or contains(text(), 'Continuar')]");
    private By importCollectionButtonBy = By.id("importCollection");
    private By advancedOptionsBy = By.xpath("//div[@class='advancedOptionsBar']/a");
    private ElementsCollection daysOfWeek = $$(By.xpath(".//*[contains(@class, 'days-of-week-container')]//input"));
    private WebElement collectionGrade = $(By.id("grade_level"));
    private By okButtonOfLessonCannotBeAddedMessageBy = By.xpath(".//*[@class='ui-dialog-buttonset']/button");
    private WebElement courseDDL = $(By.id("course_id"));
    private By addedLessonsBy = By.xpath(".//*[contains(@id, 'collectionLesson')]//*[@class = 'lessonTitle']");
    private By collectionNameBy = By.xpath(".//*[@id='collectionTable']//*[@class = 'courseName']");
    private By assingCollectionPopUpButtonContinueBy = By.xpath(".//*[@class = 'ui-dialog-buttonset']//button[*[text() = 'Continue' or text() = 'Continuar']]");
    private By saveAndAssignButtonBy = By.id("shareButton");
    protected WebElement lessonListArea = $(By.xpath("//*[contains(@class, 'mylessonsContentContainer')]"));
    private By cancelCollectionsButtonBy = By.xpath(".//*[@id='cancelButton' or @id='collectionCancelButton']");
    protected By noButtonOnCollectionWarningBy = By.id("collection-warning-no-btn");
    protected By yesButtonOnCollectionWarningBy = By.id("collection-warning-yes-btn");
    private By selectIndividualClassRadioButtonBy = By.xpath("(.//*[@id='showClassesForm']//*[contains(@class, 'selectToggle')])[2]");



    // TODO: create normal pattern for date in format M/DD/YY
    Pattern datePattern = Pattern.compile(
            "^(([13578]|10|12)\\/(([1-9])|([1-9])|([12])([0-9]?)|(3[01]?))\\/((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1}))|([2469]|11)\\/(([1-9])|([1-9])|([12])([0-9]?)|(3[0]?))\\/((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1})))$");
    Pattern datePattern2 = Pattern.compile(
            "^((0?[13578]|10|12)(-|\\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[01]?))(-|\\/)((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1}))|(0?[2469]|11)(-|\\/)(([1-9])|(0[1-9])|([12])([0-9]?)|(3[0]?))(-|\\/)((19)([2-9])(\\d{1})|(20)([01])(\\d{1})|([8901])(\\d{1})))$");

    private Pattern datePatternSp = Pattern
            .compile("([a-zA-Z]{3}/[1-3]?[0-9]/[0-9]{4})|([1-3]?[0-9]-[a-zA-Z]{3}-[0-9]{2})");

    Pattern percents = Pattern.compile("(([0-9])|([1-9][0-9])|100)%");



    public MyLessons(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public boolean verifyCompletedLesson(String lessonId) {
        WebElement lesson = findLesson(lessonId);

        if (lesson == null) {
            return false;
        } else {
            return true;
        }
    }

    public WebElement findLesson(String lessonId) {
        By neededLesson = By.xpath("//tr[contains(@id,'lesson-" + lessonId + "') and count(.//img[contains(@src, 'progress_complete')])=5]");

        if (isElementAbsentBy(neededLesson)) {
            if ($(doubleBackArrowBy).exists()) {
                $(doubleBackArrowBy).click();
            }
            while ($(nextArrowOnListViewBy).exists()) {
                if ($(neededLesson).exists()) {
                    return findEl(neededLesson);
                }
                $(nextArrowOnListViewBy).click();
            }
        } else {
            return findEl(neededLesson);
        }
        return null;
    }

    public String openAnyNotStartedLesson() {
        String id = "";
        if (isElementsExist(notStartedLessonsBy)) {
            WebElement lesson = findEls(notStartedLessonsBy).get(0);
            id = getAttribute(lesson, "href").split("id=")[1].split("&c")[0];
            clickJSWebEl(lesson);
            logger.info("Open lesson with id = " + id);
            waitForPageToLoad();
            if (isDisplayedBy(By.xpath("//span[contains(@class, 'closethick')]"))) {
                $(By.xpath("//span[contains(@class, 'closethick')]")).click();
            }
            return id;
        } else {
            if ($(doubleBackArrowBy).exists()) {
                $(doubleBackArrowBy).click();
            }
            int pageCount = 16;
            while ($(nextArrowOnListViewBy).exists() && (pageCount > 0)) {
                if ($(notStartedLessonsBy).exists()) {
                    WebElement lesson = findEls(notStartedLessonsBy).get(0);
                    id = getAttribute(lesson, "href").split("id=")[1].split("&c")[0];
                    clickJSWebEl(lesson);
                    logger.info("Open lesson with id = " + id);
                    waitForPageToLoad();
                    if (isDisplayedBy(By.xpath("//span[contains(@class, 'closethick')]"))) {
                        $(By.xpath("//span[contains(@class, 'closethick')]")).click();
                    }
                    return id;
                }
                $(nextArrowOnListViewBy).click();
                pageCount--;
            }
        }
        return id;
    }

    public void sortStep1DescendingAndGotoPageNum(String pageNum) {
        super.goToNewUrl(String.format("/my_lessons?sort=step1&sort=focus&sort_dir=desc&page_index=%s", pageNum));
    }

    public void goToFirstPage() {
        logger.info("Go To First Page");
        if ($(doubleBackArrow).exists()) {
            $(doubleBackArrow).click();
            waitUntilLoaded();
        }
    }

    public boolean verfiyLessonsPresence() {
        return (isElementsExist(lessonsList));
    }

    public List<String> checkThumbnailsPresense() {

        List<String> lessons = new ArrayList<String>();

        for (WebElement row : lessonsList) {
            if (row.findElements(By.xpath("td[3]//div[@class='media-image']/img")).size() == 0)
                lessons.add($(row).find(By.xpath("td[3]//a")).getText() + " ,");
        }
        return lessons;
    }

    public List<String> checkThumbnails() {

        List<String> lessons = new ArrayList<String>();

        for (WebElement row : lessonsList) {

            String src = $(row).find(By.xpath("td[3]//div[@class='media-image']/img")).getAttribute("src");
            if (src.contains("lesson_image_lock.png"))
                lessons.add($(row).find(By.xpath("td[3]//a")).getText() + " ,");
        }
        return lessons;
    }

    public boolean goToNextPage() {
        logger.info("Go to Next Page");
        if ($(nextArrowOnListView).isDisplayed()) {
            $(nextArrowOnListView).click();
            waitUntilLoaded();
            return true;
        } else
            return false;
    }

    public boolean checkLessonsOnLessonProgressPage(String language, int pageNumber) {

        boolean result = true;
        if (lessonsTr.size() > 0) {
            List<ElementsCollection> allTdOfAllLessons = new ArrayList<>();
            for (WebElement el : lessonsTr)
                allTdOfAllLessons.add($(el).findAll(By.xpath(".//td")));
            boolean isFutureLesson = false;
            int i = 0;

            for (ElementsCollection cells : allTdOfAllLessons) {
                String lessonName = $(cells.get(1)).find(lessonTitle).getText();

                logger.info("Verifying Lesson '" + lessonName + "'");

                if (!isFutureLesson)
                    isFutureLesson = $(cells.get(1)).getAttribute("class").contains("Disabled");
                boolean isAssingnedForStudent = $(cells.get(1)).findAll(avatarImage).size() > 0;

                logger.info("\tChecking date cell");
                checkDatesCellOnLessonProgressPage($(cells.get(0)), language, isAssingnedForStudent);
                logger.info("\tChecking Lesson cell");
                checkLessonCell($(cells.get(1)), isFutureLesson);
                logger.info("\tChecking Topic cell");
                checkTopicCell(cells.get(2));
                logger.info("\tChecking 5Step Column");
                result = check5StepColumnsLessonProgress(cells, isFutureLesson, language);
                i++;
                if (i == 1) {
                    return result;
                }
            }

        } else {
            logger.error("There is no lesson displayed on Lesson Progress page, number = " + pageNumber);
            result = false;
        }
        return result;
    }

    private boolean checkDatesCellOnLessonProgressPage(WebElement cell, String language,
                                                       boolean isAssingnedForStudent) {
        boolean result = true;
        if (isAssingnedForStudent) {
            String textFromDateLabel = "";
            if (language.equalsIgnoreCase("english")) {
                textFromDateLabel = "Start:";
            } else {
                textFromDateLabel = "Desde:";
            }
            String text = $(cell).find(dateLabel).getText().trim();
            String date = $(cell).find(dataText).getText().trim();
            if (!text.equals(textFromDateLabel)) {
                logger.error("Date Label " + text + " does not equal to Date Text " + textFromDateLabel);
                result = false;
            } else if (!datePattern.matcher(date).matches()) {
                logger.error("Date " + date + "does not match pattern " + datePattern.toString());
                result = false;
            }
        } else {
            String textFromDateLabel = "";
            if (language.equalsIgnoreCase("english")) {
                textFromDateLabel = "Start:\nEnd:";
            } else {
                textFromDateLabel = "Desde:\nHasta:";
            }
            String text = $(cell).find(dateLabel).getText();
            String[] dates = $(cell).find(dataText).getText().split("\n");

            if (!text.equals(textFromDateLabel)) {
                logger.error("Date Label " + text + " does not equal to Date Text " + textFromDateLabel);
                result = false;
            } else if (!(datePattern.matcher(dates[0]).matches() && datePattern2.matcher(dates[0]).matches())) {
                logger.error("Date " + dates[0] + "does not match pattern " + datePattern2.toString());
                result = false;
            } else if (!(datePattern.matcher(dates[1]).matches() && datePattern2.matcher(dates[1]).matches())) {
                logger.error("Date " + dates[1] + "does not match pattern " + datePattern2.toString());
                result = false;
            }
        }
        return result;
    }
    private boolean checkLessonCell(WebElement cell, boolean isFutureLesson) {

        boolean title = false;
        boolean img = false;
        boolean isDisable = false;
        boolean summary = !cell.findElement(lessonSummary).getText().isEmpty();
        boolean result = true;

        if (!isFutureLesson) {
            title = $(cell).find(lessonTitle).getTagName().equals("a");
            img = $(cell).findAll(lessonImage).size() == 1;

            if ($(cell).find(lessonImage).getAttribute("src").contains("nophoto.jpg"))
                summary = true;
        } else {
            title = $(cell).find(lessonTitle).getTagName().equals("span");
            img = $(cell).find(lessonImage).getAttribute("class").equals("locked");
            isDisable = $(cell).getAttribute("class").contains("Disable");
        }
        if (!title) {
            logger.error("Can't find the title of the lesson");
            return false;
        } else if (!img) {
            logger.error("Cant find the img of the lesson");
            return false;
        } else if (!summary) {
            logger.error("Cant find the summary of the lesson");
            return false;
        } else if (isDisable != isFutureLesson) {
            logger.error("The lesson is not disabled as should be");
            return false;
        }
        return result;
    }

    private boolean checkTopicCell(WebElement cell) {
        String text = $(cell).find(lessonTopic).getText();
        boolean topic = !text.isEmpty();
        boolean containsSeparator = text.contains(":");
        boolean subTopic = false;

        boolean result = true;
        if (containsSeparator) {
            subTopic = $(cell).findAll(By.tagName("br")).size() > 0;
        }
        if (!topic) {
            logger.error("Can't find the topic of the lesson");
            return false;
        } else if (containsSeparator != subTopic) {
            logger.error("Subtopic does not contain separator");
            return false;
        }
        return result;
    }

    private boolean check5StepColumnsLessonProgress(ElementsCollection cells, boolean isFutureLesson, String language) {
        boolean result = true;
        String[] textOflinks;

        if (language.equalsIgnoreCase("english")) {
            textOflinks = new String[]{"", "Work Submitted", "Work in Progress"};
        } else {
            textOflinks = new String[]{"", "Trabajo enviado", "Trabajo en proceso"};

        }

        if (cells.size() == 8) { // what is 8?
            String isArticleOrWritingoOrBilogyLesson = isArticleOrWritingoOrBilogyLesson(cells);
            int i = 1;
            for (int index = 3; index < 8; ++index, ++i) {
                if ((("article|writing".contains(isArticleOrWritingoOrBilogyLesson) && i != 2)
                        || ("biology".contains(isArticleOrWritingoOrBilogyLesson) && i != 3 && i != 2))
                        || isFutureLesson) {

                    boolean didHasLink = cells.get(index).findElements(link).size() == 0;
                    String text = $$(cells).get(index).getText();
                    if (!(didHasLink || text.equals("N/A"))) {
                        logger.error("There is no link. Step = " + i);
                        result = false;
                    }
                } else {
                    if (!$$(cells).get(index).getText().contains("N/A")) {
                        String text = $($$(cells).get(index)).find(link).getAttribute("title");
                        boolean contains = false;
                        for (String textToEquals : textOflinks) {
                            if (textToEquals.equals(text)) {
                                contains = true;
                                break;
                            }
                        }
                        if (!contains) {
                            logger.error("There is no link. Step = " + i);
                            result = false;
                        }
                    }
                }
            }
        } else {
            logger.error("Invalid number of steps");
            result = false;
        }
        return result;
    }

    private String isArticleOrWritingoOrBilogyLesson(ElementsCollection cells) {
        boolean isArticle = $($$(cells).get(1)).findAll(articleImage).size() == 1;
        if (isArticle)
            return "article";
        String text = $$(cells).get(2).getText();
        boolean isWriting = text.contains("Writing:\nSubject-Area Prompts");
        if (isWriting)
            return "writing";

        boolean isBiology = text.contains("Biology:");
        if (isBiology)
            return "biology";

        return "null";
    }

    public void goToFirstPageAndWait() {
        WebElement topNavigationBlock = $$(myLessonsNavigation).get(0);
        if (!isFirstPageSelected()) {
            topNavigationBlock.findElement(doubleBackNavigationButton).click();
            isElementPresent(myLessonsHeader);
        }
    }

    public boolean isFirstPageSelected() {
        WebElement topNavigatoin = $$(myLessonsNavigation).get(0);
        return $(topNavigatoin).findAll(firstSelectedPageNumber).size() > 0;
    }

    public void deleteAllCollections() {
        logger.info("Try to delete all exist collections on My Lessons page");
        if (isElementsExist(deleteButtonsOfAllCollectionsBy)) {
            closeWalkmeNew();
            for (WebElement el : $$(deleteButtonsOfAllCollectionsBy)) {
                waitUntilElementClickableBy(deleteButtonsOfAllCollectionsBy);
                //$(el).click();
                clickAfterClosePopup(el);
                clickYesButtonOnPopUpDeleteCollection();
                sleep(100);
                clickYesButtonByText();
                sleep(1000);
                //waitUntilElementStale(el);
            }
        }
    }

    public void clickAfterClosePopup(By by){
        for(int i = 0; i < 10; i++) {
            closeWalkmeNew();
        }
        closeWalkmeNew();
        closeWalkmeNew();
        closeWalkmeNew();
        closeWalkmeNew();
        $(by).click();
    }

    public void clickYesButtonOnPopUpDeleteCollection() {
        waitUntilJQueryToLoad();
        waitUntilJSandToLoad();
    }

    public void waitUntilElementStale(WebElement element) {
       // waitUntilElementStale(element);
        closeWalkmeNew();
        $(deleteCollectionPopUpButtonYesBy).click();
        while (!$(deleteCollectionPopUpButtonYesBy).isDisplayed()){
            closeWalkmeNew();
        }
        if (!$(deleteCollectionPopUpButtonYesBy).isDisplayed()){
            waitUntilElementStale(element);
        }

    }

    public void waitUntilElementStale(By by) {
        // waitUntilElementStale(element);
        while (!$(deleteCollectionPopUpButtonYesBy).isDisplayed()){
            closeWalkmeNew();
        }
        if (!$(deleteCollectionPopUpButtonYesBy).isDisplayed()){
            waitUntilElementStale(by);
        }
        $(deleteCollectionPopUpButtonYesBy).click();
    }

    public void clickOnBuiltLessonCollection() {
        closeWalkmeNew();
        closeWalkmeNew();
        clickAfterClosePopup(buildLessonCollection);
        //$(buildLessonCollection).click();
    }

    public void clickOnCreateCollectionBy() {
        logger.info("Clicked Create Collection Button");
        $(createCollectionBy).click();
        sleep(1000);
    }

    public void enterTextInCollectionNameInput(String str) {
        logger.info("Enter Text In Collection Name Input: " + str);
        waitUntilElementClickableBy(collectionNameInputBy);
        $(findEl(collectionNameInputBy)).clear();
        enterTextInInput(collectionNameInputBy, str);
    }

    public void enterTextInDescriptionInput(String str) {
        logger.info("Enter Text In Description Input: " + str);
        enterTextInInput(descriptionInput, str);
    }

    public void selectInProductDDL(Product program) {
        waitUntilAppearsBy(programDDLBy);
        Select select = new Select(refEl(programDDLBy));
        select.selectByVisibleText(program.toString().trim());
    }

    public enum Product {
        PRO("Pro"),
        ALL_PRODUCTS("All Products"),
        ALL_PRODUCTS_ESP("Todos los productos"),
        ACHIEVE_INTENSIVE("Achieve Intensive"),
        ESPANOL("Español"),
        BOOST("Boost"),
        ACCESS("Access");

        private String text;

        Product(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    public SearchWidgetPage clickOnAddLessonButton() {
        logger.info("Click On Add Lesson Button");
        waitUntilElementClickableBy(addLessonButtonBy);
            clickJS(addLessonButtonBy);
            sleep(1000);

        return new SearchWidgetPage(driver);
    }

    public void setDateOnCalendarBy(String year, String month, String day, By dateInputBy) {
        waitUntilElementClickableBy(dateInputBy);
        clickActions(dateInputBy);
        for (int i = 0; i < 4 && !isDisplayedBy(actualYearOnCalendarBy); i++ ) {
            clickJS(dateInputBy);
        }

        setDateOnCalendar(year, month, day);
    }

    public By getCollectionStartDateInputBy() {
        return collectionStartDateInputBy;
    }

    public By getCollectionEndDateInputBy() {
        return collectionEndDateInputBy;
    }

    public void enterTextInAssignNewLessonEveryInput(String str) {
        $(findEl(assignNewLessonEveryInputBy)).clear();
        waitForJSandJQueryToLoad();
        enterTextWithJS(assignNewLessonEveryInputBy, str);
        logger.debug("Entered " + str + " in 'Assign new lesson' field");
    }

    public void checkSomeDaysOfWeekStartFrom(int start, int count) {
        clickOnSomeElementsInListStartFromBy(daysOfWeekBy, start, count);
    }

    public void enterTextInKeepLessonsLiveForInput(String str) {
        $(findEl(keepLessonsLiveForInputBy)).clear();
        enterTextWithJS(keepLessonsLiveForInputBy, str);
        logger.debug("Entered " + str + " in 'Keep lessons live for' field");
    }

    public void clickOnSaveCollectionButtonBy() {
        waitUntilJSandJQLoaded();
        clickJS(saveCollectionButtonBy);
        logger.debug("Saving collection");
    }

    public void clickOkButtonOnPopUpChangesSaved() {
        waitUntilJQueryToLoad();
        waitUntilJSandToLoad();
        if (isDisplayedBy(changesSavedPopUpButtonBy)) {
            clickJS(changesSavedPopUpButtonBy);
        }
        sleep(1000);
        if (isDisplayedBy(changesSavedPopUpButtonBy)){
            $(changesSavedPopUpButtonBy).click();
        }
        sleep(1000);
    }

    public String getUncheckedCourseName() {
        By xpath = By.xpath(".//*[@class='courseName' and not(contains(text(), '('))]");
        for(int i = 0; i < 10; i++) {
            if ($(By.xpath("/html/body/div[20]/div[3]/div/button/span")).isDisplayed()) {
                $(By.xpath("/html/body/div[20]/div[3]/div/button/span")).click();
                break;
            }
        }
        closeWalkmeNew();
        String courseName = $($$(findEls(xpath)).get(0)).getText();

        logger.info("Course name is: " + courseName);
        return courseName;
    }

    public void goToYearView() {
        //$(yearViewButtonBy).click();
        closeWalkmeNew();
        clickAfterClosePopup(yearViewButtonBy);
    }

    public boolean checkPortfolio(String language) {
        logger.info("Checking portfolio");
        waitForPageToLoad();
        String defaultElement;
        String[] options;
        boolean result = true;

        if (language.equalsIgnoreCase("english")) {
            defaultElement = "Lesson Progress";
            options = new String[]{"Lesson Progress", "Activities", "Math", "Thought Questions & Writing"};
        } else {
            defaultElement = "Mi progreso";
            options = new String[]{"Mi progreso", "Actividades", "Matemáticas", "Pregunta y Escritura"};
        }
        boolean isDefaultSelected = $(selectedElementInPortfolioDDL).getText().equals(defaultElement);
        if (!isDefaultSelected) {
            logger.error("problem with default option, it should be " + defaultElement + ", but on page "
                    + $(selectedElementInPortfolioDDL).getText());
            result = false;
        }
        if (isDefaultSelected) {
            if ($$(headerOf5Steps).size() != 5) {
                logger.error("5 tabs doesn't displayed by default");
                result = false;
            }
        }
        selectedElementInPortfolioDDL.click();
        if ($$(elementsOfPortfolioDDL).size() == 4) {
            int i = 0;
            for (WebElement option : elementsOfPortfolioDDL) {
                String text = $(option).getText();
                if (!text.equals(options[i])) {
                    logger.error("problem with option " + (i + 1) + ", it should be " + options[i] + ", but on page "
                            + $(option).getText());
                    result = false;
                }
                ++i;
            }
        }
        $(selectedElementInPortfolioDDL).click();

        return result;
    }

    public boolean checkLessonsOnLessonProgressPage(String language) {
        boolean result = true;
        if (!isTextOnPagePresent("There are no activities available.")) {
            List<ElementsCollection> allTdOfAllLessons = new ArrayList<>();
            for (WebElement el : lessonsTr)
                allTdOfAllLessons.add($(el).findAll(By.xpath(".//td")));
            boolean isFutureLesson = false;
            int i = 0;
            for (ElementsCollection cells : allTdOfAllLessons) {
                String lessonName = $($$(cells).get(1)).find(lessonTitle).getText();

                logger.info("Verifying Lesson '" + lessonName + "'");
                if (!isFutureLesson)
                    isFutureLesson = $($$(cells).get(1)).getAttribute("class").contains("Disabled");

                boolean isAssingnedForStudent = $($$(cells).get(1)).findAll(avatarImage).size() > 0;

                logger.info("\tChecking date cell");
                checkDatesCellOnLessonProgressPage($$(cells).get(0), language, isAssingnedForStudent);
                logger.info("\tChecking Lesson cell");
                checkLessonCell($$(cells).get(1), isFutureLesson);
                logger.info("\tChecking Topic cell");
                checkTopicCell($$(cells).get(2));
                logger.info("\tChecking 5Step Column");
                result = check5StepColumnsLessonProgress($$(cells), isFutureLesson, language);

                i++;
                if (i == 1) {
                    return result;
                }
            }

        } else {
            logger.error("There is no lesson displayed on Lesson Progress page");
            result = false;
        }
        return result;
    }

    public void clickOnOptionInPortfolioDDLAndWait(int index) {
        $(selectedElementInPortfolioDDLBy).click();
        findEls(elementsOfPortfolioDDLBy).get(index - 1).click();
        isElementPresentBy(selectedElementInPortfolioDDLBy);
    }

    public boolean checkLessonsOnActivitiesPage(String language) {
        boolean result = true;
        waitUntilElementsAppearsLocatedBy(lessonsTrBy);
        ElementsCollection elements = findEls(lessonsTrBy);

        if (!isTextOnPagePresent("There are no activities available.")) {
            List<ElementsCollection> allTdOfAllLessons = new ArrayList<>();
            for (WebElement el : elements) {
                allTdOfAllLessons.add($(el).findAll(By.xpath(".//td")));
            }

            int i = 0;
            for (ElementsCollection cells : allTdOfAllLessons) {
                String lessonName = $$(cells).get(1).find(lessonTitle).getText();

                logger.info("Verifying Lesson '" + lessonName + "'");

                logger.info("\tChecking Dates cells");
                checkDatesCell($$(cells).get(0), language);
                logger.info("\tChecking Lesson cell");
                checkLessonCell($$(cells).get(1), false);
                logger.info("\tChecking Topic cell");
                checkTopicCell($$(cells).get(2));
                logger.info("\tChecking Parts cells");
                checkPartCellsOnActivities($$(cells), language);
                logger.info("\tChecking Class cells");
                result = checkClassCell($$(cells).get(6));
                i++;
                if (i == 1) {
                    return result;
                }
            }
        } else {
            logger.error("There is no lesson displayed on Activities view on Lessons Page");
            result = false;
        }
        return result;
    }

    private boolean checkDatesCell(WebElement cell, String language) {
        String text = $(cell).getText();
        boolean result = true;
        if (language.equalsIgnoreCase("english")) {
            if (!datePattern.matcher(text).matches() && !datePattern2.matcher(text).matches()) {
                logger.error("Patterns does not match date.");
                result = false;
            }
        } else {
            if (!datePatternSp.matcher(text).matches()) {
                logger.error("Patterns does not match date.");
                result = false;
            }
        }
        return result;
    }

    private boolean checkPartCellsOnActivities(ElementsCollection cells, String language) {
        String partOneTry1 = $$(cells).get(3).getText();
        String partOneTry2 = $$(cells).get(4).getText();
        boolean result = true;
        if (!(partOneTry1.isEmpty() || partOneTry2.isEmpty())
                ) {
            result = false;
            return result;
        }
        if (language.equalsIgnoreCase("english")) {
            if (partOneTry1.contains("Try"))
                partOneTry1 = partOneTry1.split("Try")[1].trim();

            if (partOneTry2.contains("Try"))
                partOneTry2 = partOneTry2.split("Try")[1].trim();
        } else {

            if (partOneTry1.contains("intento"))
                partOneTry1 = partOneTry1.split("intento")[1].trim();

            if (partOneTry2.contains("intento"))
                partOneTry2 = partOneTry2.split("intento")[1].trim();
        }

        if (!percents.matcher(partOneTry1).matches()) {
            logger.error("Percents " + percents + " don't match " + partOneTry1);
            result = false;
        } else if ((!percents.matcher(partOneTry2).matches())) {
            logger.error("Percents " + percents + " don't match " + partOneTry2);
            result = false;
        }
        return result;
    }

    private boolean checkClassCell(WebElement cell) {

        boolean summary = !$(cell).getText().isEmpty();

        if (!summary) {
            logger.error("Summary text is empty");
        }
        return summary;
    }

    public boolean checkLessonsOnMathPage(String language) {

        boolean result = true;
        if (!isTextOnPagePresent("There are no activities available.")) {
            List<ElementsCollection> allTdOfAllLessons = new ArrayList<>();
            for (WebElement el : lessonsTr)
                allTdOfAllLessons.add($(el).findAll(By.xpath(".//td")));
            int i = 0;
            for (ElementsCollection cells : allTdOfAllLessons) {

                String lessonName = $$(cells).get(1).findElement(lessonTitle).getText();

                logger.info("Verifying Lesson '" + lessonName + "'");

                logger.info("\tChecking Dates cells");
                checkDatesCell($$(cells).get(0), language);
                logger.info("\tChecking Lesson cell");
                checkLessonCell($$(cells).get(1), false);
                logger.info("\tChecking Topic cell");
                checkTopicCell($$(cells).get(2));
                logger.info("\tChecking Parts cells");
                checkPartCellsOnActivities($$(cells), language);
                logger.info("\tChecking Class cells");
                result = checkClassCell($$(cells).get(5));
                i++;
                if (i == 1) {
                    return result;
                }
            }

        } else {
            logger.error("There is no lesson displayed on Maths Page");
            result = false;
        }
        return result;
    }

    public boolean checkLessonsOnThougtQuestionsAndWritingPage(String language) {

        boolean result = true;
        if (!isTextOnPagePresent("There are no activities available.")) {
            List<ElementsCollection> allTdOfAllLessons = new ArrayList<>();
            for (WebElement el : lessonsTr)
                allTdOfAllLessons.add($(el).findAll(By.xpath(".//td")));
            int i = 0;
            for (ElementsCollection cells : allTdOfAllLessons) {

                String lessonName = $$(cells).get(1).find(lessonTitle).getText();

                logger.info("Verifying Lesson '" + lessonName + "'");

                logger.info("\tChecking Dates cells");
                checkDatesCell($$(cells).get(0), language);
                logger.info("\tChecking Lesson cell");
                checkLessonCell($$(cells).get(1), false);
                logger.info("\tChecking Topic cell");
                checkTopicCell($$(cells).get(2));
                logger.info("\tChecking Score cells");
                checkScoreCell($$(cells).size());
                logger.info("\tChecking Class cells");
                result = checkClassCell($$(cells).get(4));
                i++;
                if (i == 1) {
                    return result;
                }
            }

        } else {
            logger.error("There is no lesson displayed on Thought Question & Writing Page");
            result = false;
        }
        return result;
    }

    private boolean checkScoreCell(int size) {
        return size == 5;
    }

    public void clickOnOkButtonOnCalendar() {
        $(okButtonOnCalendar).click();
    }

    public void clickSearchForMoreLessons() {
        logger.info("Search for More Lessons");
        $(searchForMoreLessonsBy).click();
    }

    public void clickStartDate() {
        $(dateOfLesson).click();
    }

    public boolean isCalendarPresent() {
        return isElementsExist(calendars);
    }

    public boolean isStartOnCalendarPresent() {
        return $(startOnCalendar).exists();
    }

    public boolean isEndOnCalendarPresent() {
        return $(endOnCalendar).exists();
    }

    public boolean isRemoveButtonOnCalendarPresent() {
        return $(removeButton).exists();
    }

    public boolean isDateColumnPresent() {
        return this.isElementExist(dateColumn);
    }

    public boolean isLessonColumnPresent() {
        return this.isElementExist(lessonColumn);
    }

    public boolean isTopicColumnPresent() {
        return this.isElementExist(topicColumn);
    }

    public boolean isStrategyColumnPresent() {
        return this.isElementExist(strategyColumn);
    }

    public boolean isLessonNameAndSummaryPresent() {
        return this.isElementsExist(descriptionOfTheLesson) && this.isElementsExist(titlesOfLesson);
    }

    public DayViewPage clickOnDayButton() {
        refresh();
        $(dayButton).click();
        return new DayViewPage(driver);
    }

    public void setDateAheadOnCalendar(int daysAhead) {
        ElementsCollection dateList = getAvailableCalendarDays();
        int daysAvailable = $$(dateList).size();
        if (daysAvailable <= daysAhead) {
            clickNextMonth();
            dateList = getAvailableCalendarDays();
            dateList.get(daysAhead - daysAvailable).click();
        } else {
            dateList.get(daysAhead).click();
        }
        logger.debug("Setting date " + daysAhead + " ahead");
    }

    public ElementsCollection getAvailableCalendarDays() {
        return findEls(By.xpath(".//*[@id='ui-datepicker-div']//*[@href='#']"));
    }

    public void clickNextMonth() {
        $(nextArrowCalendar).click();
    }

    public void clickOnChangeDistrictLink() {
        logger.info("Click on change district link on My Lessons page");
        $(changeDistrictLink).click();
    }

    public void showClassesFirstCourse() {
        clickJS(showClassesButtonListBy);
        logger.debug("Opened first course classes");
    }

    public WebElement getCourseClassesSaveButton() {
        return $(courseClassesSaveButton);
    }

    public void openFirstActiveSchoolShowClasses() {
        if (isElementAbsentBy(selectAllCheckboxCourseClassesBy)) {
            $(activeSchoolShowClassesBy).click();
        }
    }

    public void clickSelectAllCheckboxCourseClasses() {
        $(selectAllCheckboxCourseClasses).click();
        logger.debug("Clicked on select all classes checkbox");
    }

    public void clickCourseClassesSaveButton() {
        $(courseClassesSaveButton).click();
    }

    public WebElement getCourseClassesChangeWarning() {
        return $(courseClassesChangeWarning);
    }

    public void clickChangesWarningYesButton() {
        $(courseClassesChangeWarningYesButton).click();
    }

    public void clickOkOnBeSurePopup() {
        clickJSWebEl(changesSavedPopUpButton);
    }

    public void clickCourseClassesCancelLink() {
        clickScroll(courseClassesCancelLinkBy);
    }

    public boolean areAllShowClassesCheckboxesCheckedKba() {
        return $$(showClassesCheckboxListKba).size() == $$(showClassesCheckboxListCheckedKba).size() + 1;
    }

    public boolean areAllShowClassesCheckboxesChecked() {
        return $$(showClassesCheckboxList).size() == $$(showClassesCheckboxListChecked).size() + 1;
    }

    public String getSelectedClassesAmount() {
        return $(selectedClasses).getText();
    }

    public String getClassesTotalAmount() {
        return $(totalClasses).getText();
    }

    public void clickChangesWarningNoButton() {
        $(courseClassesChangeWarningNoButton).click();
    }

    public void clickOnBuiltLessonCollectionBy() {
        logger.info("Clicked Build Lesson Collection Button");
        sleep(1000);
        waitUntilElementClickableBy(buildLessonCollectionBy);
        closeWalkmeNew();
        closeWalkmeNew();
        $(buildLessonCollectionBy).click();
    }

    public void saveCollection() {
        clickJS(saveCollectionButtonBy);
        //waitUntilElementStale(saveCollectionButtonBy);
        logger.debug("Saving collection");
        sleep(1000);
    }

    public String getCollectionDescriptionByName(String colName) {
        return ($(By.xpath("//*[@class='collectionTable']//*[@class='courseName' and contains(text()," +
                        " '" + colName + "')]//following-sibling::*")).getText());
    }

    public void editSpecifiedCollection(String str) {
        waitForPageToLoad();
        By path = By.xpath("//*[contains(@id, 'collectionRow')]//*[@class = 'courseName' and contains(text(), '" + str + " ')]/../..//a[contains(@href, 'edit')]");
        clickJS(path);
    }

    public void clickShowClasses() {
        expandSectionHeaders();
        waitUntilAppearsBy(showClassesButtonBy);
        waitUntilElementClickableBy(showClassesButtonBy);
        logger.debug("Clicked Show Classes button");
        int i = 0;
        while (!isDisplayedBy(showClassesDialogBy)) {
            clickJS(showClassesButtonBy);
            i++;
            if (i > 50) {
                break;
            }
        }
    }

    public void expandSectionHeaders() {
        logger.info("Expand  sections headers");
        for (WebElement el : sectionHeaders) {
            if (!getAttribute(el, "class").contains("expanded")) {
                $(el).click();
            }
        }
    }

    public void deleteSpecifiedCollection(String str) {
        By path = By.xpath("//*[contains(@id, 'collectionRow')]//*[@class = 'courseName' and contains(text(), '" + str + "')]/../..//a[contains(@class, 'deleteCollection')]");
        if (isElementExist(path)) {
            clickJS(path);
        }
        sleep(500);
    }

    public void clickDeleteCollectionYesButton() {
        $(deleteCollectionYesButtonBy).click();
        sleep(500);
        if (isElementExist(couldntDeleteButtonBy)) {
            $(couldntDeleteButtonBy).click();
        }
        sleep(500);
    }

    public List<Boolean> isSectionHeadersOpen() {
        List<Boolean> list = new ArrayList<>();
        for (String str : getClassNamesOfSectionHeaders()) {
            list.add(str.contains("expanded"));
        }
        return list;
    }

    public List<String> getClassNamesOfSectionHeaders() {
        return getAttributesFromItemsOfListBy(sectionHeadersBy, "class");
    }

    public void collapseSectionHeaders() {
        logger.info("Collapse  sections headers");
        for (WebElement el : sectionHeaders) {
            if (getAttribute(el, "class").contains("expanded")) {
                $(el).click();
            }
        }
    }

    public String getSelectedCollectionGrade() {
        return $(selectedCollectionGrade).getText().trim();
    }

    public boolean isPopupPresent() {
        return isDisplayedBy(showClassesDialogBy2);
    }

    public void clickContinueButtonClassesPopup() {
        clickUntilAnElementDisplayed(continueButtonClassPopupBy, continueButtonClassPopupBy, 10);
//    	click(continueButtonClassPopup);
    }

    public void closeShowClassesDialog() {
        logger.debug("Closing show classes dialog");
        $(showClassesCloseButton).click();
    }

    public void clearClasses() {
        waitUntilElementClickableBy(clearClassesButtonBy);
        $(clearClassesButtonBy).click();
    }

    public String getSelectedClassesShowClasses() {
        return $(selectedClassesShowClasses).getText();
    }

    public void clickSelectAllShowClassesRadio() {
        $(selectAllShowClassesRadio).click();
        logger.debug("Clicked All Classes checkbox in Show Classes popup");
    }

    public void clickSomeClassesCheckbox() {
        $(someClassesCheckbox).click();
    }

    public ElementsCollection getShowClassesClassList() {
        return showClassesClassList;
    }

    public void clickOnContinueButtonInShowClassPopUp() {
        clickScroll(continueButtonInShowClassPopUpBy);
    }

    public ElementsCollection getshowClassesCheckedClasses() {
        return showClassesCheckedClasses;
    }

    public boolean isClassCheckboxDisabled() {
        try {
            ElementsCollection temp = getDisabledClassCheckboxList();
            return temp.size() != 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ElementsCollection getDisabledClassCheckboxList() {
        return findEls(disabledClassCheckboxListBy);
    }

    public ElementsCollection getCollectionList() {
        return collectionList;
    }

    public boolean isValidationAlertPresent() {
        return isElementPresent(collectionValidationAlert);
    }

    public void enterTextInCollectionNameInputNoClear(String str) {
        logger.info("Enter Text In Collection Name Input: " + str);
        enterTextInInput(collectionNameInput, str);
    }

    public String getCollectionNameInput() {
        return $(collectionNameInput).getText().trim();
    }

    public String getCollectionDescription() {
        return $(descriptionInput).getText().trim();
    }

    public void clearCollectionInputFields() {
        $(collectionNameInput).clear();
        logger.trace("Cleared collection name input");
        $(descriptionInput).clear();
        logger.trace("Cleared collection description input");
    }

    public void clickSearch() {
        logger.debug("Search button clicked");
        $(searchBtnBy).click();
    }

    public int getLessonsAmount() {
        int result = findEls(By.xpath("//*[contains(@class, 'mylessonsContentContainer')]//*[contains(@class, 'lessonTitle')]")).size();
        return result;
    }

    public int getLessonsCount() {
        return Integer.parseInt($(lessonCounter).getText().split("\\s+")[0]);
    }

    public void deleteFirstLessonFromCollection() {
        findEls(deleteLessonButtonListBy).get(0).click();
    }

    public ElementsCollection getLessonNumbers() {
        return findEls(lessonNumbersBy);
    }

    public void openStartCalendar() {
        while (isElementAbsentBy(By.xpath(".//*[@id='ui-datepicker-div']//*[@href='#']"))) {
            $(collectionStartDateInputBy).click();
            waitUntilAppearsBy(By.xpath(".//*[@id='ui-datepicker-div']"));
        }
        logger.debug("Opened start date calendar");
    }

    public void setClosestOnCalendar() {
        waitUntilAppearsBy(By.xpath(".//*[@id='ui-datepicker-div']//*[@href='#']"));
        ElementsCollection startList = getAvailableCalendarDays();
        $($$(startList).get(0)).click();
    }

    public void openEndCalendar() {
        $(collectionEndDateInputBy).click();
        logger.debug("Opened end date calendar");
    }

    public boolean isShareButtonDisabled() {
        return !findEl(shareCollectionButtonBy).isEnabled();
    }

    public boolean isCollectionAssigned(String colName) {
       // waitUntilElementsAppearsBy(collectionListBy);
        return isElementPresentBy(By.xpath(".//*[@class='courseName' and contains(text(), '" + colName + "')" +
                "]/../following-sibling::*//*[@alt='Assigned Lesson Collection' or @alt='Colección de lecciones asignada']"));
    }

    public void clickSaveAndAssignCollection() {
        waitUntilElementClickableBy(shareCollectionButtonBy);
//        click(shareCollectionButtonBy);
        clickScroll(shareCollectionButtonBy);
        logger.debug("Clicked save and assign collection button");
    }

    public boolean isAlertDialogPresent() {
        return isElementPresent(alertDialog);
    }

    public void continueAssignCollection() {
        clickJS(shareCollectionContinueButtonBy);
    }

    public String getIdOfSpecifiedCollection(String str) {
        By path = By.xpath("//*[contains(@id, 'collectionRow')]//*[@class = 'courseName' and contains(text(), '" + str + " ')]/../..//a[contains(@href, 'edit')]");
        String id = getAttributeBy(path, "href");
        String tmp = id.substring(0, id.lastIndexOf('/'));
        return tmp.substring(tmp.lastIndexOf('/') + 1);
    }

    public boolean isQuickLookPresent() {
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return isElementPresentBy(quickLookButtonBy);
    }

    public void closeSearchWidgetPanel() {
        waitForJSandJQueryToLoad();
        //Actions actions = new Actions(driver);
        actions().sendKeys(Keys.ESCAPE).build().perform();
    }

    public void clickFirstInfoButton() {
        clickJS(infoButtonsOnLessonCell.get(0));
    }

    public void closeInfoPopUp() {
        clickJS(By.xpath(".//div[@class = 'ui-widget-overlay ui-front' and contains(@style, 'opacity:')]"));
    }

    public void goToDayView() {
        waitUntilElementClickableBy(dayViewButtonBy);
        clickJS(dayViewButtonBy);
    }

    public boolean isDayViewEmpty() {
        return $$(lessonsOnDayView).isEmpty();
    }

    public boolean isWeekViewEmpty() {
        return $$(lessonsOnWeekView).isEmpty();
    }

    public boolean isMonthViewEmpty() {
        return $$(lessonsOnMonthView).isEmpty();
    }

    public void clickOnLessonDayView(int index) {
        //waitUntilElementClickableBy(getLessonsOnDayViewBy());
        $(getLessonOnDayView(index)).click();
    }

    public By getLessonsOnDayViewBy() {
        return lessonsOnDayViewBy;
    }

    public WebElement getLessonOnDayView(int index) {
        ElementsCollection elements = findEls(lessonsOnDayViewBy);
        if ($$(elements).size() == 0) {
            Assert.fail("No lessons on day view");
        }
        return $$(elements).get(index);
    }

    public void clickOnSelectedLessonFromView() {
        $(selectedLessonsTitleFromView).click();
    }

    public void clickEscapeOnSelectedLessonFromView() {
        $(selectedLessonsTitleFromView).pressEscape();
    }

    public void goToWeekView() {
        waitUntilElementClickableBy(weekViewButtonBy);
        clickJS(weekViewButtonBy);
        //waitUntilElementStale(loadingHoverBy);
    }

    public void clickOnlessonOnWeekView(int number) {
        clickActions(findEls(titlesOflessonsOnWeekViewBy).get(number));
    }

    public void goToMonthView() {
        waitUntilElementClickableBy(monthViewButtonBy);
        $(monthViewButtonBy).click();
       // waitUntilElementStale(loadingHoverBy);
    }

    public void clickOnlessonOnMonthView(int number) {
        clickActions(findEls(titlesOflessonsOnWeekViewBy).get(number));
    }

    public void openSearchWidgetPanel() {
        executeJavaScript("window.scrollBy(0,-1250)", "");
        int count = 0;
        while (!findEl(By.xpath(".//*[@id='moreLessonsContainer']")).getCssValue("display").contains("block")) {
            if (count == 100) {
                break;
            }
            clickOnSeachForMoreLessonsByJS();

            if (!isDisplayedBy(By.xpath(".//div[@id = 'moreLessonsContainer']//div[contains(@class, 'ml_view')]"))) {
                continue;
            }

            if (findEl(By.xpath(".//*[@id='moreLessonsContainer']")).getCssValue("display").contains("block")) {
                break;
            }
            count++;
        }
    }

    public void clickQuickLookButton() {
        $(quickLookButtonBy).click();
    }

    public boolean isDeleteAlertPresent() {
        return isElementPresent(deleteCollectionYesButton); //if button is present then popup's present as well
    }

    public void clickDeleteCollectionNoButton() {
        $(deleteCollectionNoButtonBy).click();
    }

    public boolean isCollectionPresentByName(String colName) {
        By path = By.xpath(".//*[@id='collectionTable']//*[contains(text(), '" + colName + "')]");
        return isElementPresentBy(path);
    }

    public void clickContinueButtonAssignCollection() {
        $(continueButtonAssignCollectionBy).click();
    }

    public void clickImportCollectionButton() {
        logger.info("Clicked Import Collection Button");
        $(importCollectionButtonBy).click();
    }

    public boolean isImportCollectionPresentByValue(String value) {
        By path = By.xpath(".//*[@id='importListContainer']//*[@value='" + value + "']");
        return isElementExist(path);
    }

    public void expandAdvancedOptions() {
        clickOnTab("Search");
        waitUntilElementClickableBy(advancedOptionsBy);
        $(advancedOptionsBy).click();
    }

    public void checkEveryDaysOfWeek() {
        clickOnEachElementsInList(daysOfWeek);
    }

    public void changeCollectionGrade(int grade) {
        logger.info("Changing grade to " + grade);
//        Select select = new Select(collectionGrade);
//        select.selectByValue(String.valueOf(grade));
        $(collectionGrade).selectOptionByValue(String.valueOf(grade));
    }

    public void clickOkButtonOfLessonCannotBeAddedMessagePresent() {
        $(okButtonOfLessonCannotBeAddedMessageBy).click();
    }

    public void deleteFirstCollectionLesson() {
        waitUntilElementClickableBy(deleteLessonButtonListBy);
        findEls(deleteLessonButtonListBy).get(0).click();
        logger.debug("First lesson deleted");
    }

    public void selectInCourseDDL(String course) {
        Configuration.pageLoadStrategy = "normal";
        Select select = new Select(courseDDL);
        try {
            Thread.sleep(1000);
            select.selectByVisibleText(course.trim());
        } catch (Exception e){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                e.printStackTrace();
            }
        }
        //$(courseDDL).selectOption(course.trim());
        logger.debug("Selected " + course + " in course DDL");
    }

    public String getCollectionName() {
        return getTextBy(collectionNameBy).trim();
    }

    public int getCountOfAddedLessons() {
        waitForJSandJQueryToLoad();
        return findEls(addedLessonsBy).size();
    }

    public void clickOnSaveAndAssignButton() {
        clickJS(saveAndAssignButtonBy);
    }

    public void clickContinueButtonOnPopUpAssignCollection() {
        if (isDisplayedBy(assingCollectionPopUpButtonContinueBy)) {
            clickJS(assingCollectionPopUpButtonContinueBy);
        }
    }

    public boolean isNeededCollectionPresent(String str) {
        By rowPath = By.xpath(".//*[contains(@id, 'collectionRow')]//*[@class = 'courseName' and contains(text(), '" + str + "')]/../..");
        if ($(rowPath).isDisplayed()) {
            waitUntilAttributeToBeNotEmptyBy(rowPath, "textContent");
        }
        return isElementExist(rowPath);
    }

    public WebElement getLessonListArea() {
        return lessonListArea;
    }

    public void clickOnCancelCollectionsButton() {
        waitUntilElementClickableBy(cancelCollectionsButtonBy);
        $(cancelCollectionsButtonBy).click();
        waitUntilElementClickableBy(buildLessonCollectionBy);
    }

    public void uncheckSpecifiedCollection(String collectionName) {
        By path = By.xpath(".//*[contains(@id, 'collectionRow')]//*[@class = 'courseName' and contains(text(), '" + collectionName + "')]/../..//*[@class='assignedCheck']");
        if (isCheckboxChecked($(path))) {
            $(path).click();
        }
    }

    public void clickOnNoButtonOnCollectionWarning() {
        clickUntilAnElementDisplayed(noButtonOnCollectionWarningBy, noButtonOnCollectionWarningBy, 20);
    }

    public void clickOnYesButtonOnCollectionWarning() {
        clickUntilAnElementDisplayed(yesButtonOnCollectionWarningBy, yesButtonOnCollectionWarningBy, 20);
    }

    public void clickOnSelectIndividualClassRadioButton() {
        $(selectIndividualClassRadioButtonBy).click();
    }




}
