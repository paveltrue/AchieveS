package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import static com.codeborne.selenide.Selenide.$;

public class CurriculumScheduler extends Page {

    private By setDatesTab = By.cssSelector("div#setDate");

    private By schedulerTab = By.cssSelector("div#scheduler");

    private By startDateSetDatesTab = By.cssSelector("input#startDate");

    private By endDateSetDatesTab = By.cssSelector("input#endDate");

    @FindBy(css = "select#selectFreq")
    private Select lessonFrequency;

    private By setScheduleButton = By.cssSelector("div.setSchedule");

    private By nextButton = By.cssSelector("div.button");

    private By gradeSelector = By.xpath("//select[@name='c_grade']");

    private By classSelector = By.xpath("//select[@name='cid']");

    private By expandAllButton = By.id("expandAll");

    private By collapseAllButton = By.id("collapseAll");

    private By allDatesSchedulerTab = By.cssSelector("input#Date[2].dDate.hasDatepicker");

    private By firstLessonInScheduler = By.xpath("//*[@id='Date[1]']");

    private By yesButtonAlertDialog = By.xpath("//div[contains(@class,'alert_dialog')]/input[@class='submit-button' and @value='YES']");

    private By noButtonAlertDialog = By.xpath("//input[@class='submit-button' and @value='NO']");

    private By okButtonAlertDialog = By.xpath("//span[@class='ui-button-text' and text()='OK']");

    private By deleteLesson = By.cssSelector("img.delete");

    private By restoreDeletedLessonsButton = By.cssSelector("div.restore");

    private By deliverLessonCalendar = By.cssSelector("input#restoreDate.date.hasDatepicker");

    private By restoreButtonAlert = By.xpath("//table[@id='restoreTable']/../input[@class='submit-button' and @value='RESTORE']");

    private By cancelButtonAlert = By.xpath("//input[@class='submit-button' and @value='CANCEL']");

    private By firstLessonToRestore = By.xpath("//td[contains(@class,'restoreTD')]");

    private By cancelChangesButton = By.xpath("//input[@class='submit-button' and @value='CANCEL']");

    private By saveChangesButton = By.xpath("//input[@name='saveBtn' and @value='SAVE']");

    private By adjustChangesButton = By.xpath("//input[@name='readjustBtn' and @value='ADJUST']");

    private By unitScheduleDate = By.cssSelector("span.scheduleDate");

    private By okButtonRestoreDialog = By.xpath("//input[@class='submit-button' and @value='OK']");

    private By submitScheduleButton = By.xpath("//input[@class='submit-button' and @value='Set Schedule']");

    public CurriculumScheduler(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }

    public String getUnitScheduleDateText() {
        return findEl(unitScheduleDate).getText();
    }

    public void openSetDatesTab() {
        clickJS(setDatesTab);
    }

    public void openSchedulerTab() {
        clickJS(schedulerTab);
    }

    public void clickOnLessonDateSchedulerTab() {
        //clickActions(driver.findElement(firstLessonInScheduler));
        clickActions($(firstLessonInScheduler));
    }

    public void clickOnRestoreDeletedLessonsButton() {
        clickActions(restoreDeletedLessonsButton);
    }

    public void clickOnSubmitScheduleButton() {
        clickJS(submitScheduleButton);
    }

    /**
     * deletes first found lesson
     */
    public void deleteLesson() {
        clickJS(deleteLesson);
        if (isTextOnPagePresent("Are you sure you want to delete:")) {
            clickJS(yesButtonAlertDialog);
        }
        if (isTextOnPagePresent("Please select how you would like your curriculum to be delivered.")) {
            clickJS(okButtonAlertDialog);
        }
    }

    public void changeLessonDate(int dayShift) {
        clickOnLessonDateSchedulerTab();
        MyLessons ml = new MyLessons(driver);
        ml.setDateAheadOnCalendar(dayShift);
        if (isTextOnPagePresent("You have made changes to lesson")) {
            clickJS(yesButtonAlertDialog);
            if (isTextOnPagePresent("Please select how you would like your curriculum to be delivered.")) {
                clickJS(okButtonAlertDialog);
            }
        }
    }

    public void goToAdminSettings() {
        goToNewUrl("/n/admin/");
    }

    public String getTextStartDateSetDatesTab() {
        return findEl(startDateSetDatesTab).getAttribute("value");
    }

    public String getTextEndDateSetDatesTab() {
        return findEl(endDateSetDatesTab).getAttribute("value");
    }

    public String getDateSchedulerTab() {
        WebElement dateEl = $(firstLessonInScheduler);
        return dateEl.getAttribute("value");
    }

    public void expandAllLessonsAndChapters() {
        clickJS(expandAllButton);
    }

    public void collapseAllLessonsAndChapters() {
        clickJS(collapseAllButton);
    }

    private void selectLessonToRestore() {
        clickJS(firstLessonToRestore);
    }

    public void restoreSelectedLesson() {
        selectLessonToRestore();
        clickActions(restoreButtonAlert);
        setDeliverDate();

    }

    private void openDeliverLessonCalendar() {
        clickActions(deliverLessonCalendar);

    }

    private void setDeliverDate() {
        openDeliverLessonCalendar();
        MyLessons ml = new MyLessons(driver);
        ml.setDateAheadOnCalendar(7);
        clickJS(okButtonRestoreDialog);
    }

    public void selectGrade(String grade) {
        Select gradeDDL = new Select(findEl(gradeSelector));
        gradeDDL.selectByVisibleText(grade);

    }

    public void selectClass(String className) {
        Select classDDL = new Select(findEl(classSelector));
        classDDL.selectByVisibleText(className);
    }


}
