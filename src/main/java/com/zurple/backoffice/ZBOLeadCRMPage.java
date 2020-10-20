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
import resources.forms.zurple.backoffice.ZBOSendEmailForm;
import resources.forms.zurple.backoffice.ZBOSendSMSForm;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
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
	
	@FindBy(className="full_name")
	WebElement lead_name;
	
	@FindBy(id="leads-grid-filter-button")
	WebElement search_button;
	
	@FindBy(xpath="//select[@name='lead_status']")
	WebElement leads_status_dropdown;
	
	@FindBy(id="leads-table_processing")
	WebElement processing;
	
	@FindBy(xpath="//table[@id='leads-table']/descendant::div[@class='lead-owner']")
	WebElement lead_owner_crm;
	
	String lead_name_element = "//table[@id='leads-table']/descendant::div/a[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	String lead_name_element_customized_ist = "//table[@id='DataTables_Table_0']/descendant::td/a[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
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
	
	@FindBy(xpath="//div[@data-lead-id]/div[@class='reminder-label']")
	WebElement reminder_notification;
	
	String leads_email_phone = "//table[@id='leads-table']/descendant::div[@class='lead-contacts']";
	
	@FindBy(xpath="//div[@data-email]/i[@class='fas fa-envelope fa-2x']")
	WebElement email_button;
	
	String lead_sms_list = "//div[@data-phone]/i[@class='fas fa-sms fa-2x' and not(@disabled)]";
	
	@FindBy(xpath="//div[@title='Auto-Conversation Emails Sent & Opened']/div[@class='messages-col']/i[@class='fas fa-paper-plane']/preceding-sibling::div")
	WebElement autoconvo_sent_count;
	@FindBy(xpath="//div[@title='Mass & Campaign Emails Sent & Opened']/div[@class='messages-col']/i[@class='fas fa-paper-plane']/preceding-sibling::div")
	WebElement massemail_sent_count;
	
	private ZBOAddNotesForm addNoteForm;
	private ZBOAddReminderForm addReminderForm;
	private ZBOSendEmailForm sendEmailForm;
	private ZBOSendSMSForm sendSMSForm;
	private int global_index;
	
	public ZBOLeadCRMPage() {
		
	}
	public ZBOLeadCRMPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setAddNoteForm();
		setAddReminderForm();
		setSendEmailForm();
		setSendSMSForm();
		PageFactory.initElements(driver, this);
	}
	
	public ZBOAddNotesForm getAddNoteForm() {
		return addNoteForm;
	}
	public void setAddNoteForm() {
		this.addNoteForm = new ZBOAddNotesForm(driver);
	}
	public ZBOSendEmailForm getSendEmailForm() {
		return sendEmailForm;
	}
	public void setSendEmailForm() {
		this.sendEmailForm = new ZBOSendEmailForm(driver);
	}
	public ZBOSendSMSForm getSendSMSForm() {
		return sendSMSForm;
	}
	public void setSendSMSForm() {
		this.sendSMSForm = new ZBOSendSMSForm(driver);
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
			ActionHelper.waitForElementToBeDisappeared(driver, processing, 60);
			isLeadSelected = ActionHelper.isElementVisible(driver, ActionHelper.getDynamicElement(driver, lead_name_element, pLeadName));
		}
		return isLeadSelected;
	}
	
	public boolean searchLeadCustomizedList(String pLeadName) {
		boolean isLeadSelected = false;
		if(typeLeadNameOrEmail(pLeadName) && clickOnSearchButton()) {
			ActionHelper.waitForElementToBeDisappeared(driver, processing, 60);
			String leadName = ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, lead_name_element_customized_ist, pLeadName));
			if(leadName.equalsIgnoreCase(pLeadName)) {
				isLeadSelected = true;
			}
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
		global_index = l_index; 
		String l_leadName = ActionHelper.getText(driver, list.get(l_index));
		String l_leadId = ActionHelper.getAttribute(list.get(l_index), "href").split("lead/")[1];
		return l_leadName+","+l_leadId;
	}
	public String getEmail() {
		ActionHelper.staticWait(10);
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, leads_email_phone);
		String l_EmailPhone = ActionHelper.getText(driver, list.get(global_index));
		return l_EmailPhone;
	}
	public boolean clickOnReminderButton() {
		return ActionHelper.Click(driver, reminder_button);
	}
	public boolean verifyReminderNotification(int pExpectedNotifications) {
		return Integer.parseInt(ActionHelper.getText(driver, ActionHelper.getElementByXpath(driver, "//div[@data-lead-id]/div[@class='reminder-label']")))==pExpectedNotifications;
	}
	public boolean clickOnEmailButton() {
		return ActionHelper.Click(driver, email_button);
	}
	public boolean clickOnSMSButton() {
		boolean isClicked = false;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, lead_sms_list);
		int l_index = generateRandomInt(list.size());
		if(l_index>0) {
			isClicked = ActionHelper.Click(driver, list.get(l_index));
		}else {
			isClicked = false;
			AutomationLogger.error("Unablle to get the list of leads with phone numbers..");
		}
		return isClicked;
	}
	
	public boolean clickSearchedLeadName() {
		return ActionHelper.Click(driver, lead_name);
	}
	
	public boolean verifyAutoConvoCount(int pCount) {
		return Integer.parseInt(ActionHelper.getText(driver, autoconvo_sent_count))==pCount;
	}
	public boolean verifyMassEmailCount() {
		return Integer.parseInt(ActionHelper.getText(driver, massemail_sent_count))!=0;
	}
}
