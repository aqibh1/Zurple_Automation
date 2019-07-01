/**
 * 
 */
package com.z57.site.v2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class SchoolReportsPage extends Page{

	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement pageTitle;
	
	@FindBy(xpath="//div[@id='z57_schools_map_canvas']")
	WebElement googleMap;
	
	@FindBy(xpath="//a[@id='z57_schools_table_next']")
	WebElement next_button;
	
	@FindBy(xpath="//a[@class='paginate_button previous' and @id='z57_schools_table_previous']")
	WebElement previous_button;
	
	@FindBy(xpath="//div[@id='z57_schools_table_info']")
	WebElement table_entries_info;
	
	String list_of_Schools="//table[@id='z57_schools_table']/descendant::tbody/tr";
	String pin_popup_info_table ="//table[@class='table table-condensed table-striped']/descendant::tbody/tr";
	String list_of_pages_pagination="//div[@id='z57_schools_table_paginate']/span/a[@data-dt-idx='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	public SchoolReportsPage(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);
	}
	
	public boolean isSchoolReportsPage() {
		boolean result = actionHelper.getText(pageTitle).equalsIgnoreCase(FrameworkConstants.SchoolReportsPageTitle)?true:false;
		return result;
	}
	
	public boolean isGoogleMapsDisplayed() {
		return actionHelper.isElementVisible(googleMap);
	}
	
	public boolean isResultsCorrect(String pZip) {
		boolean result = true;
		String lSchoolName="";
		boolean flag = false;
		
		List<WebElement> list_of_elements = driver.findElements(By.xpath(list_of_Schools));
		for(WebElement element: list_of_elements) {
			flag = false;
			lSchoolName = element.findElement(By.tagName("a")).getText();
			actionHelper.Click(element.findElement(By.tagName("a")));
			List<WebElement> table_contents_list = driver.findElements(By.xpath(pin_popup_info_table));
			for(WebElement table_element: table_contents_list) {
				actionHelper.waitForElementToBeVisible(table_element, 10);
				String element_text = table_element.getText();
				if(element_text.contains("Name:")) {
					String pin_school_name = element_text.split("Name:")[1].trim();
					System.out.println(pin_school_name);
					if(lSchoolName.equalsIgnoreCase(pin_school_name)) {
						flag = true;
					}
				}
				if(flag && element_text.contains("City, State Zip:")) {
					String[] city_state_zip= element_text.split("City, State Zip:")[1].split(" ");
					String lZip = city_state_zip[city_state_zip.length-1];
					System.out.println(lZip);
					if(!pZip.equalsIgnoreCase(lZip)) {
						AutomationLogger.error("ZIP Mismatched");
						AutomationLogger.error("SCHOOL :: "+lSchoolName);
						AutomationLogger.error("EXPECTED ZIP :: "+pZip);
						AutomationLogger.error("ACTUAL ZIP :: "+lZip);
						return false;
					}
					
				}
			}
			
			
		}
		return result;
		
	}
	
	public boolean isResultsCorrectForAllPages(String pZip) {
		boolean lResult=false;
		int counter=1;
		do {
			actionHelper.waitForElementToBeVisible(actionHelper.getDynamicElement(list_of_pages_pagination, String.valueOf(counter)), 10);
			clickOnPageNumber(list_of_pages_pagination, String.valueOf(counter));
			lResult = isResultsCorrect(pZip);
			counter++;
			if(!lResult) {
				break;
			}
		}while(actionHelper.isElementVisible(actionHelper.getDynamicElement(list_of_pages_pagination, String.valueOf(counter))));
		
		return lResult;
	}
	
	public boolean verifyPagination() {
		boolean forwardPagination=false,backwardsPagination=false;
		String lCurrent_text = table_entries_info.getText();
		String lPrevious_Text = table_entries_info.getText();
		String total_count = lCurrent_text.split("of ")[1].split(" ")[0];
		int total_page_count = (Integer.parseInt(total_count))/10;
	
		for(int counter=1;counter<=total_page_count+1;counter++) {
			if(counter%2==0) {
				clickOnNextButtonPage();
			}else {
				clickOnPageNumber(list_of_pages_pagination, String.valueOf(counter));
			}
			lPrevious_Text =lCurrent_text;
			lCurrent_text = table_entries_info.getText();
			
			forwardPagination = !lPrevious_Text.equalsIgnoreCase(lCurrent_text);
			if(!forwardPagination) {
				break;
			}
		}
		
		
		while(actionHelper.isElementVisible(previous_button)) {
			actionHelper.Click(previous_button);
			lPrevious_Text =lCurrent_text;
			lCurrent_text = table_entries_info.getText();
			backwardsPagination=lCurrent_text.equalsIgnoreCase(lPrevious_Text)?false:true;
			if(!backwardsPagination) {
				break;
			}
		}
		
		return (forwardPagination && backwardsPagination);
	}
	
	private boolean clickOnNextButtonPage() {
		return actionHelper.Click(next_button);
	}
	
	private boolean clickOnPageNumber(String pElementXpath, String pPageNumber) {
		return actionHelper.Click(actionHelper.getDynamicElement(pElementXpath, pPageNumber));
	}
	@Override
	public WebElement getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement getBrand() {
		// TODO Auto-generated method stub
		return null;
	}

}
