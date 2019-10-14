package com.zurple.backoffice;

import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class ZBOLeadPage extends Page{
	
	@FindBy(xpath="//h3[@class='header-title' and contains(text(),'Leads')]")
	WebElement lead_heading;
	
	@FindBy(id="leadsInputName")
	WebElement lead_input;
	
	@FindBy(id="leads-grid-filter-button")
	WebElement lead_search_button;
	
	@FindBy(id="DataTables_Table_0_processing")
	WebElement procession_notfication;
	
	String lead_row = "//div[@id='DataTables_Table_0_wrapper']/descendant::td/a[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	//Sorting Headers
	
	String sorting_column_ascending_xpath = "//th[@aria-label='"+FrameworkConstants.DYNAMIC_VARIABLE+": activate to sort column ascending']";
	
	String sorting_column_descending_xpath = "//th[@aria-label='"+FrameworkConstants.DYNAMIC_VARIABLE+": activate to sort column descending']";

	@FindBy(xpath="//div[@id='leads-grid']/descendant::div[text()='Processing...']")
	WebElement processing_alert;
	
	@FindBy(xpath="//select[@id='location-parent-1']")
	WebElement filter_dropdown;
	
	@FindBy(xpath="//select[@id='location-child-1']")
	WebElement filter_child_dropdown;
	
	
	public ZBOLeadPage() {
		
	}
	public ZBOLeadPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isLeadPage() {
		return ActionHelper.waitForElementToBeVisible(driver, lead_heading, 30);
	}
	
	public boolean typeLeadNameToSearch(String pName) {
		return ActionHelper.Type(driver, lead_input, pName);
	}

	public boolean clickOnSearchButton() {
		return ActionHelper.Click(driver, lead_search_button);
	}
	
	public boolean isLeadExist(String pLeadName) {
		boolean isLeadExist= false;
		pLeadName = WordUtils.capitalizeFully(pLeadName);
		if(!typeLeadNameToSearch(pLeadName)) {
			return false;
		}
		if(!clickOnSearchButton()) {
			return false;
		}
		if(ActionHelper.waitForElementToBeDisappeared(driver, procession_notfication,30)) {
			ActionHelper.staticWait(5);
			isLeadExist = ActionHelper.isElementVisible(driver, ActionHelper.getDynamicElement(driver, lead_row,pLeadName));
		}
		return isLeadExist;
	}
	public boolean verifyNameSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Name");
	}
	public boolean verifyEmailSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Email");
	}
	public boolean verifySearchLocationSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Search Location");
	}
	public boolean verifyMaxPriceSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Max Price");
	}
	public boolean verifyDateCreatedSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Date Created");
	}
	public boolean verifyAgentSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Agent");
	}
	public boolean verifyLastModifiedSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Last Modified");
	}
	public boolean verifyLastVisitSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Last Visit");
	}
	public boolean verifyPriorityRankSortingWorking() {
		return isSortingWorking(sorting_column_ascending_xpath, sorting_column_descending_xpath, "Priority Rank");
	}
	public boolean isProcessingComplete() {
		return ActionHelper.waitForElementToBeDisappeared(driver, procession_notfication,30);
	}
	
	private boolean isSortingWorking(String pXpathAscending, String pXpathDescending,String pDynamicVariable) {
		boolean isWorking = false;
		ActionHelper.staticWait(3);
		if(ActionHelper.Click(driver, ActionHelper.waitAndGetDynamicElement(driver,pXpathAscending,pDynamicVariable))) {
			isWorking = ActionHelper.waitForElementToBeVisible(driver, ActionHelper.waitAndGetDynamicElement(driver, pXpathDescending, pDynamicVariable), 15);		
		}
		return isWorking;
	}
}
