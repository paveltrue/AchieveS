package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.refresh;

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
                waitUntilElementStale(el);
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
        for(int i = 0; i < 10; i ++) {
            closeWalkmeNew();
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
        int i = 0;
        while (!isDisplayedBy(allTabsBy)) {
            clickJS(addLessonButtonBy);
            if (i > 50) {
                break;
            }
            i++;
        }
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
    }

    public String getUncheckedCourseName() {
        By xpath = By.xpath(".//*[@class='courseName' and not(contains(text(), '('))]");
        for(int i = 0; i < 10; i++) {
            if ($(By.xpath("/html/body/div[20]/div[3]/div/button/span")).isDisplayed()) {
                $(By.xpath("/html/body/div[20]/div[3]/div/button/span")).click();
                break;
            }
        }
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




}
