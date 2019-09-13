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
}
