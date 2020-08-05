/**
 * 
 */
package com.zurple.backoffice;

import java.util.List;

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
	
	@FindBy(xpath="//table[@id='leads-table']/descendant::input[@class='lead-check']")
	WebElement lead_input_checkbox;
	
	@FindBy(xpath="//div[@class='lead-owner']/span[@data-lead-id]")
	WebElement lead_agent_assignment_button;
	
	String enrolled_text = "//p[@class='messages-label' and text()='Enrolled']";
	
	@FindBy(id="leads-table_info")
	WebElement leads_info_table;
	
	@FindBy(xpath="//div[@class='campaign-icon' and @style='cursor: not-allowed;']")
	WebElement enrollment_disable_button;
	
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
;			ActionHelper.waitForElementToBeDisappeared(driver, processing, 60);
			isLeadSelected = ActionHelper.isElementVisible(driver, ActionHelper.getDynamicElement(driver, lead_name_element, pLeadName));
		}
		return isLeadSelected;
	}
	public boolean searchAndSelectLead(String pLeadName) {
		boolean isLeadSelected = false;
		if(typeLeadNameOrEmail(pLeadName) && clickOnSearchButton()) {
			ActionHelper.waitForElementToBeDisappeared(driver, processing, 60);
			if(ActionHelper.isElementVisible(driver, ActionHelper.getDynamicElement(driver, lead_name_element, pLeadName))) {
				isLeadSelected = ActionHelper.Click(driver, lead_input_checkbox);
			}
		}
		return isLeadSelected;
	}
	public boolean verifyAgentName(String pAgentName) {
		return ActionHelper.getText(driver, lead_owner_crm).contains(pAgentName);
	}
	public boolean clickOnAgentAssignmentButton() {
		return ActionHelper.Click(driver, lead_agent_assignment_button);
	}
	public boolean isLeadEnrolledInCampaign() {
		boolean isEnrolled = false;
		List<WebElement> elements = ActionHelper.getListOfElementByXpath(driver, enrolled_text);
		if(elements!=null && elements.size()>0) {
			isEnrolled =  ActionHelper.waitForElementToBeVisible(driver, elements.get(1), 15);
		}else {
			isEnrolled = false;
		}
		return isEnrolled;
	}
	public boolean searchLeadByEmail(String pLeadEmail) {
		boolean isLeadFound = false;
		if(typeLeadNameOrEmail(pLeadEmail) && clickOnSearchButton()) {
			ActionHelper.waitForElementToBeDisappeared(driver, processing, 60);
			isLeadFound = ActionHelper.getText(driver, leads_info_table).equalsIgnoreCase("Showing 1 to 1 of 1 entries");
		}
		return isLeadFound;
	}
	public boolean isEnrollmentIconDisabled() {
		return ActionHelper.waitForElementToBeVisible(driver, enrollment_disable_button, 15);
	}
}
