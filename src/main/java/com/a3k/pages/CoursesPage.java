package com.a3k.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CoursesPage extends Page {

	protected ElementsCollection buttonsInHeader = $$(By.xpath("//*[@class='coursesHeaderContainer']//a"));
	protected WebElement coursesHeader = $(By.xpath("//*[@class='coursesHeaderContainer']//*[@class='title']"));
	protected WebElement district = $(By.xpath("//*[@class='district']"));
	protected WebElement linkNearDistrict = $(By.xpath("//*[@class='district']//a"));
	protected WebElement errorMessageFromPopup = $(By.xpath("//*[contains(@class,'ui-dialog-content')]"));
	protected WebElement okButtonInPopup = $(By.xpath("//*[@class='ui-dialog-buttonset']/button"));
	protected WebElement submitButton = $(By.id("course-submit-btn"));
	protected WebElement schoolSelect = $(By.id("s_sid"));
	protected WebElement classSelect = $(By.id("s_cid"));
	protected ElementsCollection tableHeader = $$(By.xpath("//*[contains(@class,'headerColumn')]"));
	protected ElementsCollection resultFromTable = $$(By.xpath("//tr[*[contains(@class,'classColor ')]]"));
	protected WebElement text = $(By.className("descriptiveText"));
	protected WebElement linkInText = $(By.xpath("//*[@class='descriptiveText']/a"));
	protected WebElement colorCellInTable = $(By.xpath("//td[contains(@class,'classColor ')]"));
	protected ElementsCollection iconsInParagraphs = $$(By.xpath(".//*[@class = 'descriptiveText']//*[contains(@class,'icon')]"));
	protected WebElement stateButton = $(By.xpath(".//*[@class='header']/*[contains(@class,'state')]"));
	protected WebElement fullStateNameHeader = $(By.xpath(".//*[@class='header']/*[@class='title']"));
	protected WebElement introTextHeader = $(By.xpath(".//p[span[@class='course_intro_header']]"));
	protected ElementsCollection subheadersInText = $$(By.xpath(".//*[contains(@class,'course_intro_subheader')]"));
	protected ElementsCollection paragraphsInText = $$(By.xpath(".//*[contains(@class,'course_intro_text')]/ul/li"));
	protected ElementsCollection footnotesOfParagraphs = $$(By.xpath(".//*[@class = 'course_intro_text']/ul/following-sibling::*"));
	protected WebElement introTextBottom = $(By.xpath(".//*[@class = 'descriptiveText']/*[last()]"));
	protected WebElement editCoursesButton = $(By.xpath(".//*[@data-text = 'EDIT COURSES' or @data-text = 'EDITAR CURSOS']"));
	protected WebElement emptyTableMessage = $(By.xpath("//tr[*[contains(@class,'classColor')]]/*[contains(@class,'row')]"));
	protected ElementsCollection courseArrows = $$(By.xpath(".//*[@class = 'course_arrow']"));
	protected ElementsCollection iconsInLessonListColumn = $$(By.xpath(".//*[@class = 'coursesTable']//img"));
	protected ElementsCollection coursesTitlesOnTable = $$(By.xpath(".//*[@class = 'coursesTable']//td[@class='row1'][1]"));
	protected By coursesTitlesOnTableBy = By.xpath(".//*[@class = 'coursesTable']//td[@class='row1'][1]");
	protected ElementsCollection coursesDetailsOnTable = $$(By.xpath(".//*[@class = 'coursesTable']//td[@class='row1'][2]"));
	protected By coursesDetailsOnTableBy = By.xpath(".//*[@class = 'coursesTable']//td[@class='row1'][2]");


	private String courseTitleXpath = ".//*[@class = 'coursesTable']//td[contains(text(),'%s')]";
	

	public CoursesPage(WebDriver driver) {
		this.driver = driver;
	}

	public List<String> getTitlesOfCourses() {
		return getTextFromWebElementsByList(coursesTitlesOnTable);
	}
	
	public List<String> getDescriptionOfCourses() {
		return getTextFromWebElementsByList(coursesDetailsOnTable);
	}
	
	public String getNeededTitleOfCourse(String pattern) {
		ArrayList<String> titles = new ArrayList<>();
		ElementsCollection elements = findEls(By.xpath(".//*[@class = 'coursesTable']//td[@class='row1' and contains(text(), '"+pattern+"')][1]"));
		titles.addAll(getTextFromWebElementsBySet(elements));		
		//return titles.get(0);
		
		if(!titles.isEmpty()){
			return titles.get(0);
		}else{
			return "";
		}
	}
	
	public boolean isCourseWithTitleExist(String title) {
		return isDisplayedBy(By.xpath(String.format(courseTitleXpath, title)));
	}
	
	
	public String getNeededDescriptionOfCourse(String pattern) {
		By xpath = By.xpath(".//*[@class = 'coursesTable']//td[@class='row1'][2]//*[contains(text(), '"+pattern+"')]");
		ArrayList<String> description = new ArrayList<>();
		ElementsCollection elements = findEls(xpath);
		if(elements.isEmpty()){
			pattern = pattern.toLowerCase();
			xpath = By.xpath(".//*[@class = 'coursesTable']//td[@class='row1'][2]//*[contains(text(), '"+pattern+"')]");
			elements = findEls(xpath);
		}
		description.addAll(getTextFromWebElementsBySet(elements));
		return description.get(0);
	}

	public boolean isCoursesHeaderPresent() {
		return isElementPresent(coursesHeader);
	}

	public ArrayList<String> getTextFromButtonsInHeader() {
		ArrayList<String> result = new ArrayList<>();
		for (int i = buttonsInHeader.size() - 1; i > -1; --i) {
			WebElement el = buttonsInHeader.get(i);
			result.add(el.getText());
		}
		return result;
	}

	public void clickOnButtonInHeader(int index) {
		if (index >= buttonsInHeader.size())
			return;
		$(buttonsInHeader.get(index)).click();
	}

	public String getDistrictName() {
		String text = district.getText();
		text = text.split(":")[1];
		text = text.split("[(][a-z]*[)]")[0];
		return text.trim();
	}

	public String getTextFromLinkNearDistrictName() {
		return $(linkNearDistrict).getText();
	}

	public void clickOnLinkNearDistrictName() {
		$(linkNearDistrict).click();
	}

	public String getErrorMessageFromPopup() {
		return $(errorMessageFromPopup).getText();
	}

	public void clickOnOKButtonInPopup() {
		$(okButtonInPopup).click();
	}

	public void clickOnSubmitButton() {
		scrollDown();
		$(submitButton).click();
	}

	public String getTextFromSubmitButton() {
		return searchButton.getText();
	}

	public void selectSchool(String schoolName) {
		Select select = new Select(schoolSelect);
		select.selectByVisibleText(schoolName);
	}
	
	public void selectSchoolByValue(String schoolName) {
		Select select = new Select(schoolSelect);
		select.selectByValue(schoolName);
	}

	public void selectClass(String className) {
		Select select = new Select(classSelect);
		select.selectByVisibleText(className);
	}
	
	public void selectClassByValue(String className) {
		Select select = new Select(classSelect);
		select.selectByValue(className);
	}
	

	public ArrayList<String> getTabelHeader() {
		ArrayList<String> result = new ArrayList<>();
		for (WebElement el : tableHeader)
			result.add(el.getText());
		return result;
	}

	public boolean doesTableHaveSomeResult() {
		return resultFromTable.size() > 0;
	}

	public boolean isEditCoursesButtonUnavailable() {
		return buttonsInHeader.get(1).getAttribute("class").endsWith("disabled");
	}

	public String getCoursesHeader() {
		return coursesHeader.getText();
	}

	public String getTextFromPage() {
		return text.getText();
	}

	public void clickOnLinkInText() {
		linkInText.click();
	}
		
	public String getActiveGrade(){
		 String grade = super.getActiveGrade();
		 String lang = this.getLanguage();
		 
		 if(lang.equalsIgnoreCase("Español")){
			 grade = grade.replace("Grado", "").trim();
			 return grade;
		 }
		 grade = grade.replace("Grade", "").trim();	 
		 return grade;
	}
	
	public boolean isFirstParagraphDisplayed(){
		return paragraphsInText.get(0).isDisplayed();
		
	}	
	
	public boolean isParagraphsDisplayed(){
		Set<Boolean> result = new HashSet<>();
		for(WebElement el : paragraphsInText){
			result.add(el.isDisplayed());
		}
		if(result.contains(true))return true;
		
		return false;
	}
	
	public Set<String> getIconsUrlInfo(){
		Set<String> result = new HashSet<>();
		for(WebElement el : iconsInParagraphs){	
			String str = el.getCssValue("background-image");			
			String tmp = str.substring(0, str.lastIndexOf('/'));
			tmp = tmp.substring(tmp.lastIndexOf('/') + 1);			
			result.add(tmp);
		}
		return result;
	}
	
	public String getStateCode(){
		String stateCode = stateButton.getText().replace("Edition", " ").trim();		
		return stateCode;
	}
	
	public String getFullStateName(String language){
		String fullStateName;
		if(language.equals("Español")){
			fullStateName = $(fullStateNameHeader).getText().replace("Cursos de", " ").trim();
			return fullStateName;
		}		
		fullStateName = $(fullStateNameHeader).getText().replace("Courses", " ").trim();
		return fullStateName;		
	}
	
	public String getIntroTextHeader() {
		return $(introTextHeader).getText();
	}
	
	public void clickOnAllSubheaders() {
		for (WebElement el : subheadersInText) {
			$(el).click();
		}
	}
	
	public void clickOnSecondSubheader() {
		
		$(subheadersInText.get(1)).click();
	}

	public Set<String> getItemsOfParagraphs() {
		Set<String> result = new HashSet<>();

		for (WebElement el : paragraphsInText) {
			result.add($(el).getText());
		}
		return result;
	}

	public Set<String> getSubheadersInText() {
		Set<String> result = new HashSet<>();

		for (WebElement el : subheadersInText) {
			result.add($(el).getText());
		}
		return result;
	}
	
	public Set<String> getFootnotesOfParagraphs() {
		Set<String> result = new HashSet<>();

		for (WebElement el : footnotesOfParagraphs) {
			result.add($(el).getText());
		}
		return result;
	}
	
	public String getIntroTextBottom() {
		return $(introTextBottom).getText();
	}
	
	public Set<String> getColorOfSubheaders(){
		Set<String> subheaders = new HashSet<>();
		for(WebElement item : subheadersInText){
			subheaders.add($(item).getCssValue("color"));
		}
		return subheaders;
	}
	
	public String getColorOfClass() {
		return $(colorCellInTable).getAttribute("class").split(" ")[1].trim();
	}

	public ArrayList<String> getCoursesList() {
		ArrayList<String> result = new ArrayList<>();
		By needColumn = By.xpath("td[2]");
		for (WebElement row : resultFromTable) {
			result.add($(row).find(needColumn).getText().trim());
		}
		return result;
	}
		
	public void clickOnEditCoursesButton(){
		$(editCoursesButton).click();
	}
	
	public String getEmptyTableMessage(){
		return $(emptyTableMessage).getText();
	}
	
	public List<String> getColumnsNames(){
		ArrayList<String> result = new ArrayList<>();
		for(WebElement el : tableHeader){
			result.add($(el).getText());
		}
		return result;
	}
	
	public List<Boolean> isArrowsDisplayed(){
		ArrayList<Boolean> result = new ArrayList<>();
		
		for(WebElement el : courseArrows){
			result.add($(el).isDisplayed());
		}
		return result;
	}
	
	public List<String> getAttributeOfArrows(){
		ArrayList<String> result = new ArrayList<>();
		String tmp;		
		for(WebElement el : courseArrows){
			String str = $(el).getCssValue("background-image");
			tmp = str.substring(str.lastIndexOf('/')+1, str.lastIndexOf('.'));			
			result.add(tmp);			
		}
		return result;
	}
	
	public Set<Boolean> isIconsInLessonListDisplayed(){
		HashSet<Boolean> result = new HashSet<>();		
		for(WebElement el : iconsInLessonListColumn){
			result.add($(el).isDisplayed());
		}
		return result;	
	}
}