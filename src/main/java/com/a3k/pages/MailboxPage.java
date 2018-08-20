package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Calendar;

import static com.codeborne.selenide.Selenide.*;

public class MailboxPage extends Page {

    public MailboxPage(WebDriver driver) {
        this.driver = driver;
        //PageFactory.initElements(driver, this);
    }


    private String subjectTextFromWriteEmail = "";
    private String groupText;

    private By writeEmailSectionBy = By.xpath("//td[contains(@background,'write')]/a");
    private WebElement fieldTo = $(By.xpath("//*[@class='selectM25' and @type='text']"));
    private WebElement subjectTextboxOnWriteEmail = $(By.xpath("//input[@name='subject']"));
    private WebElement messageTextboxOnWriteEmail = $(By.id("body"));
    private WebElement sendButtonOnWriteEmail = $(By.xpath("//a[contains(@onclick,'doDraft=false')]"));
    private WebElement sentEmailsFolder = $(By.xpath("//*[@href='/mail/?f=3' and text()]"));
    private WebElement emailSection = $(By.xpath("//td[contains(@background,'email')]/a"));
    private By messageThatEmailSavedInTheDraftBy = By.xpath("//font[@class='kidsTypeBlack']");
    private By draftsFolderBy = By.xpath("//*[@href='/mail/?f=2' and text()]");
    private ElementsCollection linksOfUnreadMessageSubject = $$(By.xpath("//a[@class='unread_message_text']"));
    private By checkboxOfTheMessage = By.xpath(".//ancestor::tr[1]//input");
    private WebElement deleteButton = $(By.xpath("//*[@href='javascript:doSubmit(3)']"));
    private WebElement trashFolder = $(By.xpath("//*[@href='/mail/?f=4' and text()]"));
    private WebElement emailGroupsSection = $(By.xpath("//td[contains(@background,'groups')]/a"));
    private WebElement newGroupButton = $(By.xpath("//a[@href='group.php']"));
    private WebElement emailGroupTextbox = $(By.xpath("//input[@name='email_group_name']"));
    private WebElement firstOptionOfMembersSelect = $(By.xpath("//select[@name='users[]']/option[3]"));
    private WebElement createGroupButton = $(By.xpath("//a[@href='javascript:checkForm()']"));
    private ElementsCollection listOfEmailGroups = $$(By.xpath("//a[contains(@href,'group.php?id=')]"));
    private WebElement manageFoldersLink = $(By.xpath("//a[@href='/mail/folder/']"));
    private WebElement inboxFolder = $(By.xpath("//*[@class='nav_text' and contains(@href,'f=1') and text()]"));
    private WebElement checkAllLink = $(By.xpath("//*[@onclick='javascript:checkBoxes(1)']"));
    private ElementsCollection systemLetters = $$(By.xpath("//tr[@class='broadcast_message']"));
    private WebElement noEmailsMessage = $(By.xpath("//*[@class='read_message_text']"));
    private WebElement deleteMailFolderButton = $(By.xpath("//a[(@class='mail_text') and contains (@href,'javascript:doSubmit')]"));
    private WebElement emptyTrashButton = $(By.xpath("//a[(@class='button') and contains (@href,'javascript:doSubmit(2)')]"));
    private WebElement deleteSentEmailsButton = $(By.xpath("//a[(@class='button') and contains (@href,'javascript:doSubmit(3)')]"));
    private WebElement checkAllSentEmailsButton = $(By.xpath("//a[(@class='mail_text') and contains (@onclick,'javascript:checkBoxes(1)')]"));





    public void openWriteEmailSection() {
        waitUntilAppearsBy(writeEmailSectionBy);
        logger.info("Opening 'Write email' section");
        clickJS(writeEmailSectionBy);
        waitElement(writeEmailSectionBy);
    }

    public String fillAllInWriteEmailAndSend(String to) {
        //selectWhoSendToOnWriteEmail();
        setRecipient(to);
        String subject = fillSubjectOnWriteEmail();
        fillMessageTextbox();
        clickSendButtonOnWriteEmail();
        return subject;
    }

    public void setRecipient(String text) {
        logger.info("Set recipient to " + text);
        $(fieldTo).clear();
        $(fieldTo).setValue(text);
    }

    public String fillSubjectOnWriteEmail() {
        Calendar c = Calendar.getInstance();
        subjectTextFromWriteEmail = "Test-" + String.valueOf(c.getTimeInMillis());
        logger.info("Fill subject with value '" + subjectTextFromWriteEmail +
                "'");
        $(subjectTextboxOnWriteEmail).setValue(subjectTextFromWriteEmail);
        return subjectTextFromWriteEmail;
    }

    public String getSubjectText() {
        String text = $(subjectTextboxOnWriteEmail).getAttribute("value").trim();
        return text;
    }

    public void fillMessageTextbox() {
        logger.info("Fill message text box");
        $(messageTextboxOnWriteEmail).setValue("Test message text");
    }

    public void clickSendButtonOnWriteEmail() {
        logger.info("Send email");
        $(sendButtonOnWriteEmail).click();
    }

    public boolean checkAlertAfterSendOnWriteEmail() {
        try {
            switchTo().alert().accept();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void openSentEmailsFolder() {
        logger.info("Open 'Sent' folder");
        scrollUp();
        clickJSWebEl(sentEmailsFolder);
    }

    public void fillAllInWriteEmail(String to) {
        setRecipient(to);
        fillSubjectOnWriteEmail();
        fillMessageTextbox();
    }

    public void openEmailSection() {
        logger.info("Opening Email section");
        $(emailSection).click();
    }

    public boolean checkPresenceOfMessageThatDisplayedAfterNotSavingSending(){
        boolean isPresent = false;

       // waitThreadSleep(1500);

        switchToNextWindow();

        if (isElementPresentBy(messageThatEmailSavedInTheDraftBy)) {
            isPresent = true;
            switchBack();
            return isPresent;
        }
        switchToNextWindow();
        return isPresent;
    }

    public void openDraftsFolder() {
        logger.info("Open 'Drafts' folder");
        $(draftsFolderBy).click();
    }

    public boolean checkPresenceMessageInFolder() {
        boolean isFound = false;
        for (WebElement el : linksOfUnreadMessageSubject) {
            if (el.getText().trim().equals(subjectTextFromWriteEmail)) {
                isFound = true;
                $(el).find(checkboxOfTheMessage).click();
                break;
            }
        }
        return isFound;
    }
    public void clickOnDeleteButtonAndAcceptAlertIfExists() {
        $(deleteButton).click();
        waitAndAcceptAlert();
        waitElementWebEl(emailSection);
    }

    public void openTrashFolder() {
        logger.info("Open 'Trash' folder");
        closeWalkmeNew();
        $(trashFolder).click();
    }

    public void openEmailGroupSection() {
        logger.info("Open Email group section");
        clickJSWebEl(emailGroupsSection);
        waitElementWebEl(emailGroupsSection);
    }

    public void clickNewGroupButton() {
        logger.info("Click on 'New Group' button");
        $(newGroupButton).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitElementWebEl(emailGroupsSection);
    }

    public void fillAllOnEmailGroupsAndCreate() {
        fillEmailGroupTextbox();
        fillDescriptionTextbox();
        selectFirstOptionInMembersSelect();
        clickCreateGroupButton();
    }

    public void fillEmailGroupTextbox() {
        Calendar c = Calendar.getInstance();
        groupText = "Test Email Group " + String.valueOf(c.getTimeInMillis());
        logger.info("Fill email group text with value: " + groupText);
        $(emailGroupTextbox).setValue(groupText);
    }

    public boolean checkPresenceOfEmailGroupTextbox() {
        return isElementPresent(emailGroupTextbox);
    }

    public void fillDescriptionTextbox() {
        logger.info("Fill description: " + "Description " + groupText);
        $(emailGroupTextbox).setValue("Description " + groupText);
    }

    public void selectFirstOptionInMembersSelect() {
        logger.info("Select first option in members list");
        $(firstOptionOfMembersSelect).click();
    }

    public void clickCreateGroupButton() {
        logger.info("Open 'Create Group' wizard");
        createGroupButton.click();
        waitElementWebEl(emailGroupsSection);
    }

    public boolean checkPresenceOfCreatedGroup() {
        boolean isPresent = false;
        for (WebElement group : listOfEmailGroups) {
            if ($(group).getText().split("Description")[0].trim().equals(groupText) ||
                    $(group).getText().split("Description")[1].trim().equals(groupText) ) {
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    public void clickOnManageFoldersLink() {
        logger.info("Open 'Manage Folders");
        closeWalkmeNew();
        waitElementWebEl(manageFoldersLink);
        $(manageFoldersLink).click();
        waitElementWebEl(manageFoldersLink);
    }

    public void openInboxFolder() {
        logger.info("Open 'Inbox' folder");
        clickJSWebEl(inboxFolder);
    }

    public void clickOnCheckAllLink() {
        logger.info("Click on 'Check All' link");
        $(checkAllLink).click();
    }

    public int amountOfSystemLessons() {
        return $$(systemLetters).size();
    }

    public boolean isMailboxEmpty() {
        return isElementPresent(noEmailsMessage);
    }

    public void deleteAllUsersMailFolders() {
        while ($(deleteMailFolderButton).exists()) {
            $(deleteMailFolderButton).click();
            acceptAlert();
        }
    }

    public void emptyTrashFolder() {
        while ($(emptyTrashButton).exists()) {
            $(emptyTrashButton).click();
            acceptTwoAlerts();
        }
    }

    public void emptySentEmailFolder() {

        while ($(deleteSentEmailsButton).exists()) {
            $(checkAllSentEmailsButton).click();
            $(deleteSentEmailsButton).click();
        }
    }


}
