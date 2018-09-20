package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class EditUserInfo extends Page {

	private WebElement noSTTSupport = $(By.id("permOpt7_label"));
	private WebElement BRPSTTSupport = $(By.id("permOpt8_label"));
	private WebElement RCSTTSupport = $(By.id("permOpt9_label"));
	private WebElement ARPSTTSupport = $(By.id("permOpt10_label"));
	private WebElement TQSTTSupport = $(By.id("permOpt11_label"));
	private WebElement noSTTSupportCheckbox = $(By.id("permOpt7"));
	private WebElement BRPSTTSupportCheckbox = $(By.id("permOpt8"));
	private WebElement RCSTTSupportCheckbox = $(By.id("permOpt9"));
	private WebElement ARPSTTSupportCheckbox = $(By.id("permOpt10"));
	private WebElement TQSTTSupportCheckbox = $(By.id("permOpt11"));
	private WebElement schoolNameRow = $(By.xpath("//table[@id='user_profile']//tr[3]"));
	private WebElement classNameRow = $(By.xpath("//table[@id='user_profile']//tr[4]"));
	private WebElement productLanguageRow = $(By.xpath("//table[@id='user_profile']//tr[5]"));
	private WebElement subscriberTypeRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='subscriber_type']]"));
	private WebElement loginNameRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='login_name']]"));
	private WebElement passwordRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@id='password_reveal']]"));
	private WebElement titleRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='title']]"));
	private WebElement firstNameRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='first_name']]"));
	private WebElement lastNameRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='last_name']]"));
	private WebElement penNameRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='pen_name']]"));
	private WebElement studentIdRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='student_id']]"));
	private WebElement gradeLevelRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='grade_level']]"));
	private WebElement mathLevelRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='math_level']]"));
	private WebElement lexileLevelRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='lexile_num_show']]"));
	private WebElement scaffoldsRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@id='edition_div']]"));
	private WebElement genderRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='gender']]"));
	private WebElement ethnicityRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='demographic[7]']]"));
	private WebElement raceRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='demographic[1]']]"));
	private WebElement ellRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='demographic[2]']]"));
	private WebElement sesRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='demographic[4]']]"));
	private WebElement specialEducationPlacementRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='demographic[6]']]"));
	private WebElement firstLanguageRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='demographic[5]']]"));
	private WebElement specialClassificationRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='demographic[3]']]"));
	private WebElement specialClassificationSpecifyRow = $(By.xpath("//table[@id='user_profile']//tr[.//*[@name='demographic[8]']]"));
	private WebElement submitButton = $(By.id("submitBtn"));
	private WebElement teDisplayNameInput = $(By.id("pen_name"));
	protected By teDisplayNameInputBy = By.id("pen_name");
	protected By teDisplayNameLabelBy = By.xpath(".//*[@id='pen_name']/../preceding-sibling::td");
	private WebElement firstNameInput = $(By.id("first_name"));
	protected By firstNameInputBy = By.id("first_name");
	private WebElement informationButtonTeDisplayName = $(By.xpath(".//*[@id='pen_name']/following-sibling::a"));
	protected By informationButtonTeDisplayNameBy = By.xpath(".//*[@id='pen_name']/following-sibling::a");
	protected By closeButtonInfoPopUpTeDisplayNameBy = By.xpath("//*[contains(@id, 'ui-dialog-title-')]/following-sibling::a");
	protected By infoPopUpTeDisplayNameBy = By.xpath(".//*[contains(@id, 'ui-dialog-title-')]/../following-sibling::div[contains(@class, 'ui-widget-content')]");
	private WebElement headerOfEditUserInfoPage = $(By.xpath(".//*[@id='headerRow']//img"));

	public void clickOnBRPSTTCheckbox(){
		$(BRPSTTSupportCheckbox).click();
	}
	
	public void clickOnARPSTTCheckbox(){
		$(ARPSTTSupportCheckbox).click();
	}
	
	public void clickOnRCSTTCheckbox(){
		$(RCSTTSupportCheckbox).click();
	}
	
	public void clickOnTQSTTCheckbox(){
		$(TQSTTSupportCheckbox).click();
	}
	
	public void clickOnNoSTTCheckbox(){
		$(noSTTSupportCheckbox).click();
	}
	
	public String getTextNoSTT(){
		return $(noSTTSupport).getText();
	}
	
	public String getTextBRPSTT(){
		return $(BRPSTTSupport).getText();
	}
	
	public String getTextRCSTT(){
		return $(RCSTTSupport).getText();
	}
	
	public String getTextARPSTT(){
		return $(ARPSTTSupport).getText();
	}
	
	public String getTextTQSTT(){
		return $(TQSTTSupport).getText();
	}
	
	public boolean isCheckboxNoSTTPresent(){
		return isElementPresent(noSTTSupportCheckbox);
	}
	
	public boolean isCheckboxBRPSTTPresent(){
		return isElementPresent(BRPSTTSupportCheckbox);
	}
	
	public boolean isCheckboxRCSTTPresent(){
		return isElementPresent(RCSTTSupportCheckbox);
	}
	
	public boolean isCheckboxARPSTTPresent(){
		return isElementPresent(ARPSTTSupportCheckbox);
	}
	
	public boolean isCheckboxTQSTTPresent(){
		return isElementPresent(TQSTTSupportCheckbox);
	}
	
	public boolean isCheckboxNoSTTchecked(){
		return isCheckboxChecked(noSTTSupportCheckbox);
	}
	
	public boolean isCheckboxBRPSTTchecked(){
		return isCheckboxChecked(BRPSTTSupportCheckbox);
	}
	
	public boolean isCheckboxRCSTTchecked(){
		return isCheckboxChecked(RCSTTSupportCheckbox);
	}
	
	public boolean isCheckboxARPSTTchecked(){
		return isCheckboxChecked(ARPSTTSupportCheckbox);
	}
	
	public boolean isCheckboxTQSTTchecked(){
		return isCheckboxChecked(TQSTTSupportCheckbox);
	}
	
	public String getTextFromInfoPopUpTeDisplayName() {
		return getTextBy(infoPopUpTeDisplayNameBy);	
	}
	
	public String getTextFromTeDisplayNameLabelBy() {
		return getTextBy(teDisplayNameLabelBy);	
	}
	
	public void closeInfoPopUpTeDisplayName() {
		$(closeButtonInfoPopUpTeDisplayNameBy).click();
	}

	public void clickOnInformationButtonTeDisplayName() {
        clickJS(informationButtonTeDisplayNameBy);
	}

	public String getValueOfTeDisplayNameInput() {
		return getAttributeBy(teDisplayNameInputBy, "value");			
	}
	
	public String getValueOfFirstNameInput() {
		return getAttributeBy(firstNameInputBy, "value");			
	}
	
	public void setTeDisplayName(String str) {
		enterTextInInput(teDisplayNameInput, str);
	}
	
	public void clickOnSubmitButton() {
		$(submitButton).click();
		waitAndAcceptAlert();
	}
	
	public void clearTeDisplayNameInput() {
		$(teDisplayNameInput).clear();
	}
	
	public WebElement getHeaderOfEditUserInfoPage() {
		waitUntilAttributeToBeNotEmpty(headerOfEditUserInfoPage, "src");
		return headerOfEditUserInfoPage;
	}

	public EditUserInfo(WebDriver driver) {		
		this.driver = driver;
	}
	
	private boolean isSectionPresenceWithNonEditableText(WebElement title, WebElement text, String titleText){
		
		if(!$(title).getText().equals(titleText))
			return  false;
		
		if($(text).getText().equals(""))
			return false;
		
		return true;
	}
	
	private boolean isSectionPresenceWithEditableText(WebElement title, WebElement field, String titleText){
		
		if(!$(title).getText().equals(titleText))
			return  false;
		
		if(!$(field).isDisplayed())
			return false;
		
		if(titleText.contains("*"))
			if($(field).getAttribute("value").equals(""))
				return false;
		
		return true;
	}
	
	private boolean isSectionPresenceWithEditableDDL(WebElement title, WebElement select, String titleText){
		
		if(!$(title).getText().equals(titleText))
			return  false;
		

		if(getOptionsFromSelect(select).size() <= 1)
			return false;
		
		return true;
	}
	
	private boolean isSectionPresenceWithCheckbox(WebElement title, WebElement checkbox, String titleText){
		
		if(!$(title).getText().equals(titleText))
			return  false;
		
		if(!$(checkbox).isDisplayed())
			return false;
		
		return true;
	}
	
	public boolean isSchoolNameSectionPresenceWithNonEditableText(){
		
		WebElement title = $(schoolNameRow).find(By.xpath("td[1]"));
		WebElement text = $(schoolNameRow).find(By.xpath("td[2]"));
		
		return isSectionPresenceWithNonEditableText(title, text, "School");
	}
	
	public boolean isClassNameSectionPresenceWithNonEditableText(){
		
		WebElement title = $(classNameRow).find(By.xpath("td[1]"));
		WebElement text = $(classNameRow).find(By.xpath("td[2]"));
		
		return isSectionPresenceWithNonEditableText(title, text, "Class");
	}
	
	public boolean isSubscriberTypeSectionPresenceWithNonEditableText(){
		
		WebElement title = $(subscriberTypeRow).find(By.xpath("td[1]"));
		WebElement text = $(subscriberTypeRow).find(By.xpath("td[2]"));
		
		return isSectionPresenceWithNonEditableText(title, text, "Subscriber Type");
	}
	
	public boolean isLoginNameSectionPresenceWithEditableField(){
		
		WebElement title = $(loginNameRow).find(By.xpath("td[1]"));
		WebElement text = $(loginNameRow).find(By.xpath("td[2]/input[@id='login_name']"));
		
		return isSectionPresenceWithEditableText(title, text, "Login Name");
	}
	
	public boolean isPasswordSectionPresenceWithEditableField(){
		
		WebElement title = $(passwordRow).find(By.xpath("td[1]"));
		WebElement text = $(passwordRow).find(By.xpath("td[2]/input[@id = 'password']"));
		
		return isSectionPresenceWithEditableText(title, text, "Password");
	}
	
	public boolean isTitleSectionPresenceWithEditableField(){
		
		WebElement title = $(titleRow).find(By.xpath("td[1]"));
		WebElement text = $(titleRow).find(By.xpath("td[2]/input"));
		
		return isSectionPresenceWithEditableText(title, text, "Title");
	}
	
	public boolean isFirstNameSectionPresenceWithEditableField(){
		
		WebElement title = $(firstNameRow).find(By.xpath("td[1]"));
		WebElement text = $(firstNameRow).find(By.xpath("td[2]/input"));
		
		return isSectionPresenceWithEditableText(title, text, "First Name");
	}
	
	public boolean isLastNameSectionPresenceWithEditableField(){
		
		WebElement title = $(lastNameRow).find(By.xpath("td[1]"));
		WebElement text = $(lastNameRow).find(By.xpath("td[2]/input"));
		
		return isSectionPresenceWithEditableText(title, text, "Last Name");
	}
	
	public boolean isPenNameSectionPresenceWithEditableField(){
		
		WebElement title = $(penNameRow).find(By.xpath("td[1]"));
		WebElement text = $(penNameRow).find(By.xpath("td[2]/input"));
		
		return isSectionPresenceWithEditableText(title, text, "Pen Name");
	}
	
	public boolean isStudentIdSectionPresenceWithEditableField(){
		
		WebElement title = $(studentIdRow).find(By.xpath("td[1]"));
		WebElement text = $(studentIdRow).find(By.xpath("td[2]/input"));
		
		return isSectionPresenceWithEditableText(title, text, "Student ID");
	}
	
	public boolean isGradeLevelSectionPresenceWithEditableDDL(){
		
		WebElement title = $(gradeLevelRow).find(By.xpath("td[1]"));
		WebElement select = $(gradeLevelRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "Grade Level");
	}
	
	public boolean isMathLevelSectionPresenceWithEditableDDL(){
		
		WebElement title = $(mathLevelRow).find(By.xpath("td[1]"));
		WebElement select = $(mathLevelRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "Math Level");
	}
	
	public boolean isLexileLevelSectionPresenceWithEditableField(){
		
		WebElement title = $(lexileLevelRow).find(By.xpath("td[1]"));
		WebElement text = $(lexileLevelRow).find(By.xpath("td[2]/input[@type = 'text']"));
		
		return isSectionPresenceWithEditableText(title, text, "Lexile Level");
	}
	
	public boolean isLexileInterpritationIconNextToLexileField(){
		
		WebElement iLink = $(lexileLevelRow).find(By.xpath("td[2]/a"));
		
		return isElementExist(iLink);
	}
	
	public boolean isScaffoldSectionPresenceWithCheckbox(){
		
		WebElement title = $(scaffoldsRow).find(By.xpath("td[1]"));
		WebElement checkbox = $(scaffoldsRow).find(By.xpath("td[2]/input[@type='checkbox']"));
		
		return isSectionPresenceWithCheckbox(title, checkbox, "Scaffolds");
	}
	
	public boolean isLanguageDDLPresentWithOptions(){
		
		List<String> langValues = new ArrayList<String>();
		
		langValues.add("1");
		langValues.add("2");
		langValues.add("6");
		langValues.add("3");
		
		WebElement language = $(scaffoldsRow).find(By.xpath("td[2]/div/div[3]/input"));
		
		if(!$(language).isSelected())
			$(language).click();
		try{
			WebElement languageDDL = $(scaffoldsRow).find(By.xpath("td[2]/div/div[3]//select"));
			
			List<WebElement> languages = getOptionsFromSelect(languageDDL);
			
			boolean check = true;
			
			for(WebElement lang : languages){
				
				if(!langValues.contains(lang.getAttribute("value")))
					check = false;
			}
			return check;
		}catch(Exception e){
			
			return false;
		}
	}
	
	public boolean isGenderSectionPresenceWithEditableDDL(){
		
		WebElement title = $(genderRow).find(By.xpath("td[1]"));
		WebElement select = $(genderRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "Gender");
	}
	
	public boolean isEthnicitySectionPresenceWithEditableDDL(){
		
		WebElement title = $(ethnicityRow).find(By.xpath("td[1]"));
		WebElement select = $(ethnicityRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "Ethnicity");
	}
	
	public boolean isRaceSectionPresenceWithEditableDDL(){
		
		WebElement title = $(raceRow).find(By.xpath("td[1]"));
		WebElement select = $(raceRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "Race");
	}
	
	public boolean isEnglishLanguageLearnerSectionPresenceWithEditableDDL(){
		
		WebElement title = $(ellRow).find(By.xpath("td[1]"));
		WebElement select = $(ellRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "English Language Learner (ELL)");
	}
	
	public boolean isSocioeconomicStatusSectionPresenceWithEditableDDL(){
		
		WebElement title = $(sesRow).find(By.xpath("td[1]"));
		WebElement select = $(sesRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "Socioeconomic Status (SES)");
	}
	
	public boolean isSpecialEducationPlacementSectionPresenceWithEditableDDL(){
		
		WebElement title = $(sesRow).find(By.xpath("td[1]"));
		WebElement select = $(sesRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "Socioeconomic Status (SES)");
	}
	
	public boolean isFirstLanguageSectionPresenceWithEditableDDL(){
		
		WebElement title = $(firstLanguageRow).find(By.xpath("td[1]"));
		WebElement select = $(firstLanguageRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "First (Native) Language");
	}
	
	public boolean isSpecialClassificationSectionPresenceWithEditableDDL(){
		
		WebElement title = $(specialClassificationRow).find(By.xpath("td[1]"));
		WebElement select = $(specialClassificationRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "Special Classification");
	}
	
	public boolean isSpecialClassificationSpecifySectionPresenceWithEditableDDL(){
		
		WebElement title = $(specialClassificationSpecifyRow).find(By.xpath("td[1]"));
		WebElement select = $(specialClassificationSpecifyRow).find(By.xpath("td[2]/select"));
		
		return isSectionPresenceWithEditableDDL(title, select, "Special Classification (specify)");
	}
	
	public String getProductLanguage(){
		
		WebElement productLanguage = productLanguageRow.findElement(By.xpath("td[2]"));
		String result = $(productLanguage).getText();

		return result;
	}
}
