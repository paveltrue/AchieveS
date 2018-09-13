package com.a3k.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.$;

public class PrintCollectionPage extends Page {

	public PrintCollectionPage(WebDriver driver) {
	   this.driver = driver;
	   //PageFactory.initElements(driver, this);
	}
	    
    private By titleOfHeaderBy = By.xpath(".//*[@class='title_header']");
	private WebElement titleOfHeader = $(By.xpath(".//*[@class='title_header']"));
    private By descriptionOfCollectionBy = By.xpath(".//*[@class='title_header']/following-sibling::div[1]");
    private By lessonsListBy = By.xpath(".//*[@id='lessonList']//*[@class = 'row_value']"); 
    
    
    
    public List<String> getNameFromLessonsList(){
    	return getTextFromWebElementsByListBy(lessonsListBy);
    }
    
    
    public String getNameOfCollection(){
    	//waitUntilAttributeToBeNotEmpty(titleOfHeader, "textContant");
    	return getTextTrim(titleOfHeader);
    }
    
    public String getDescriptionOfCollection(){
    	return getTextTrim(descriptionOfCollectionBy);
    }
    
    public String getValueOfNeededRow(String rowsName){    	
    	return getTextTrim(By.xpath("//*[@id='collectionDetails']//*[@class = 'row_label' and contains(text(), '"+rowsName+"')]/following-sibling::td"));
    }
    
    public String getValueOfNeededRowFromDeliveryTable(String rowsName){    	
    	return getTextTrim(By.xpath("//*[@id='deliverySchedule']//*[@class = 'row_label' and contains(text(), '"+rowsName+"')]/following-sibling::td"));
    }

}
