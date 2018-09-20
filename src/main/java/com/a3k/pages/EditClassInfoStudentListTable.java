package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;

public class EditClassInfoStudentListTable extends EditClassInfo {

	public EditClassInfoStudentListTable(WebDriver driver) {
		super(driver);		
	}

	private WebElement countOfStudent = $(By.xpath(".//td[contains(text(), 'All students listed above') or contains(text(), 'Todos los estudiantes')]/../td[1] "));
	private WebElement nextPageLink = $(By.xpath(".//a[contains(@href, '/options/teacher/user/edit_users.php?school_id=')]"));
	private WebElement firstStudentInList = $(By.xpath("(//a[contains(text(), 'stud')])[1]"));
	private WebElement noSTTSupportInDDL = $(By.xpath(".//a[contains(@href,'12039477')]/../..//input[contains(@name,'permission_options') and (@value=7)]/.."));
	private WebElement BRPSTTSupportInDDL = $(By.xpath(".//a[contains(@href,'12039477')]/../..//input[contains(@name,'permission_options') and (@value=8)]/.."));
	private WebElement RCSTTSupportInDDL = $(By.xpath(".//a[contains(@href,'12039477')]/../..//input[contains(@name,'permission_options') and (@value=9)]/.."));
	private WebElement ARPSTTSupportInDDL = $(By.xpath(".//a[contains(@href,'12039477')]/../..//input[contains(@name,'permission_options') and (@value=10)]/.."));
	private WebElement TQSTTSupportInDDL = $(By.xpath(".//a[contains(@href,'12039477')]/../..//input[contains(@name,'permission_options') and (@value=11)]/.."));
	private WebElement noSTTSupportCheckboxInDDL = $(By.xpath(".//a[contains(@href,'12039477')]/../..//input[contains(@name,'permission_options') and (@value=7)]"));
	private WebElement BRPSTTSupportCheckboxInDDL = $(By.xpath(".//a[contains(@href,'12039477')]/../..//input[contains(@name,'permission_options') and (@value=8)]"));
	private WebElement RCSTTSupportCheckboxInDDL = $(By.xpath(".//a[contains(@href,'12039477')]/../..//input[contains(@name,'permission_options') and (@value=9)]"));
	private WebElement ARPSTTSupportCheckboxInDDL = $(By.xpath(".//a[contains(@href,'12039477')]/../..//input[contains(@name,'permission_options') and (@value=10)]"));
	private WebElement TQSTTSupportCheckboxInDDL = $(By.xpath(".//a[contains(@href,'12039477')]/../..//input[contains(@name,'permission_options') and (@value=11)]"));
	
	public void expandSTTDDL(String id){
		$(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=7)]/../../..")).click();
	}
	
	public String getTextNoSTTInDDL(String id){
		return getTextBy(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=7)]/.."));
	}
	
	public String getTextBRPSTTInDDL(String id){
		return getTextBy(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=8)]/.."));
	}
	
	public String getTextRCSTTInDDL(String id){
		return getTextBy(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=9)]/.."));
	}
	
	public String getTextARPSTTInDDL(String id){
		return getTextBy(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=10)]/.."));
	}
	
	public String getTextTQSTTInDDL(String id){
		return getTextBy(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=11)]/.."));
	}
	
	public boolean isCheckboxNoSTTcheckedInDDL(String id){
		return isCheckboxChecked($(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=7)]")));
	}
	
	public boolean isCheckboxBRPSTTcheckedInDDL(String id){
		return isCheckboxChecked($(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=8)]")));
	}
	
	public boolean isCheckboxRCSTTcheckedInDDL(String id){
		return isCheckboxChecked($(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=9)]")));
	}
	
	public boolean isCheckboxARPSTTcheckedInDDL(String id){
		return isCheckboxChecked($(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=10)]")));
	}
	
	public boolean isCheckboxTQSTTcheckedInDDL(String id){
		return isCheckboxChecked($(By.xpath(".//a[contains(@href,'"+id+"')]/../..//input[contains(@name,'permission_options') and (@value=11)]")));
	}
	
	
	public void clickFirstStudentInList() {
		$(firstStudentInList).click();
	}

	
	public WebElement getCountOfStudent() {
		return countOfStudent;
	}
	
	public String getNumberOfStudent(){
		if(isElementPresent(nextPageLink)){
			$(nextPageLink).click();
		}
		return $(countOfStudent).getText();
	}
	
	

}
