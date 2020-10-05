/**
 * 
 */
package com.zurple.backoffice;

import java.util.List;

import org.hamcrest.core.IsSame;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.forms.zurple.backoffice.ZBOAddNotesForm;
import resources.forms.zurple.backoffice.ZBOAddReminderForm;
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
	
	@FindBy(id="check_uncheck_all")
	WebElement bulk_select;
	
	@FindBy(id="select-all-leads")
	WebElement select_all;
	
	@FindBy(className="swal2-confirm")
	WebElement confirm_select_all;
	
	@FindBy(xpath="//li[@title='Return']")
	WebElement return_label;
	@FindBy(xpath="//li[@title='Browsing']")
	WebElement browsing_label; 
	
	@FindBy(xpath="//div[@data-lead-id]/i[@class='fas fa-sticky-note fa-2x']")
	WebElement note_button;
	
	String leads_name_list = "//table[@id='leads-table']/descendant::div[@class='full_name']/a";
	
	@FindBy(xpath="//div[@data-lead-id]/i[@class='fas fa-bell fa-2x']")
	WebElement reminder_button;
	
	private ZBOAddNotesForm addNoteForm;
	private ZBOAddReminderForm addReminderForm;
	
	public ZBOLeadCRMPage() {
		
	}
	public ZBOLeadCRMPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setAddNoteForm();
		setAddReminderForm();
		PageFactory.initElements(driver, this);
	}
	
	public ZBOAddNotesForm getAddNoteForm() {
		return addNoteForm;
	}
	public void setAddNoteForm() {
		this.addNoteForm = new ZBOAddNotesForm(driver);
	}
	public ZBOAddReminderForm getAddReminderForm() {
		return addReminderForm;
	}
	public void setAddReminderForm() {
		this.addReminderForm = new ZBOAddReminderForm(driver);
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
	
	public boolean searchLeadByEmailForBulkUpdate(String pLeadEmail) {
		boolean isLeadFound = false;
		if(typeLeadNameOrEmail(pLeadEmail) && clickOnSearchButton()) {
			ActionHelper.waitForElementToBeDisappeared(driver, processing, 60);
			ActionHelper.Click(driver, bulk_select);
			ActionHelper.Click(driver, select_all);
			ActionHelper.Click(driver, confirm_select_all);
			isLeadFound = true;
		}
		return isLeadFound;
	}
	
	public boolean isReturnHotBehaviorVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, return_label, 30);
	}
	public boolean isBrowsingHotBehaviorVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, browsing_label, 30);
	}
	public boolean clickOnNoteButton() {
		return ActionHelper.Click(driver, note_button);
	}
	public String getLeadName() {
		ActionHelper.staticWait(10);
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, leads_name_list);
		int l_index = generateRandomInt(list.size());
		String l_leadName = ActionHelper.getText(driver, list.get(l_index));
		String l_leadId = ActionHelper.getAttribute(list.get(l_index), "href").split("lead/")[1];
		return l_leadName+","+l_leadId;
	}
	public boolean clickOnReminderButton() {
		return ActionHelper.Click(driver, reminder_button);
	}
}
