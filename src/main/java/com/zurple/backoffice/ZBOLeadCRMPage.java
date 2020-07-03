/**
 * 
 */
package com.zurple.backoffice;

import org.hamcrest.core.IsSame;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class ZBOLeadCRMPage extends Page{
	
	@FindBy(xpath="//h3[text()='Leads']")
	WebElement leads_heading;
	
	@FindBy(id="leadsInputName")
	WebElement lead_input_name;
	
	@FindBy(id="leads-grid-filter-button")
	WebElement search_button;
	
	@FindBy(xpath="//select[@name='lead_status']")
	WebElement leads_status_dropdown;
	
	@FindBy(id="leads-table_processing")
	WebElement processing;
	
	@FindBy(xpath="//table[@id='leads-table']/descendant::div[@class='lead-owner']")
	WebElement lead_owner_crm;
	
	String lead_name_element = "//table[@id='leads-table']/descendant::div/a[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	public ZBOLeadCRMPage() {
		
	}
	public ZBOLeadCRMPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isLeadCRMPage() {
		return ActionHelper.waitForElementToBeVisible(driver, leads_heading, 30);
	}
	public boolean typeLeadNameOrEmail(String pNameEmail) {
		return ActionHelper.ClearAndType(driver, lead_input_name, pNameEmail);
	}
	public boolean clickOnSearchButton() {
		ActionHelper.staticWait(5);
		if(ActionHelper.Click(driver, search_button)) {
			ActionHelper.staticWait(15);
			return true;
		}else {
			return false;
		}
	}
	public String getLeadProsepctSelectedValue() {
		return ActionHelper.getSelectedOption(driver, leads_status_dropdown, "");
	}
	public boolean searchLead(String pLeadName) {
		boolean isLeadSelected = false;
		if(typeLeadNameOrEmail(pLeadName) && clickOnSearchButton()) {
			ActionHelper.waitForElementToBeDisappeared(driver, processing, 60);
			isLeadSelected = ActionHelper.isElementVisible(driver, ActionHelper.getDynamicElement(driver, lead_name_element, pLeadName));
		}
		return isLeadSelected;
	}
	public boolean verifyAgentName(String pAgentName) {
		return ActionHelper.getText(driver, lead_owner_crm).contains(pAgentName);
	}
}
