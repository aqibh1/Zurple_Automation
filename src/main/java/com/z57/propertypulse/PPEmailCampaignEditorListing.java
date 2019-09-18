/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPEmailCampaignEditorListing extends Page{
	
	@FindBy(id="feature_title")
	WebElement emailCampaignManagerTitle;
	
	@FindBy(id="email_subject")
	WebElement emailSubject_input;
	
	@FindBy(id="s2id_autogen1_search")
	WebElement selectListing_input;
	
	String suggestedResults = "//ul[@id='select2-results-1']/li";
	
	@FindBy(id="theme_select")
	WebElement titleInHeader_dropdown;
	
//	@FindBy(id="s2id_autogen4")
//	WebElement selectIndividualLeads;
	
	@FindBy(xpath="//div[@id='s2id_leads_select']/descendant::input")
	WebElement selectIndividualLeads;
	
	@FindBy(id="email_test_input")
	WebElement testEmail_input;
	
	@FindBy(xpath="//button[text()='Send']")
	WebElement send_button;
	
	@FindBy(id="add_schedule_link")
	WebElement add_button;
	
	@FindBy(id="schedule_datepicker")
	WebElement scheduleDatepicker;
	
	@FindBy(id="s2id_listing_select")
	WebElement select_Listing;
	
	public PPEmailCampaignEditorListing() {
		
	}
	public PPEmailCampaignEditorListing(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeEmailSubject(String pEmail) {
		return ActionHelper.ClearAndType(driver, emailSubject_input, pEmail);
	}
	public boolean isEmailCampaignManagerPage() {
		return ActionHelper.waitForElementToBeVisible(driver, emailCampaignManagerTitle, 30);
	}
	public boolean selectListing(String pListing) {
		boolean isSuccess = false;
		boolean isClick = ActionHelper.Click(driver, select_Listing);
		if(isClick && ActionHelper.Type(driver, selectListing_input, pListing)) {
			ActionHelper.waitForAjaxToBeCompleted(driver);
			ActionHelper.staticWait(5);
			List<WebElement> list_of_suggested_listings = ActionHelper.getListOfElementByXpath(driver, suggestedResults);
			isSuccess = ActionHelper.Click(driver, list_of_suggested_listings.get(0));
		}
		return isSuccess;
	}
	public boolean selectTitleInHeader(String pHeader) {
		return ActionHelper.selectDropDownOption(driver, titleInHeader_dropdown,"", pHeader);
	}
	public boolean typeIndividualLead(String pLeadEmail) {
		boolean isSuccess = false;
		if(ActionHelper.Type(driver, selectIndividualLeads, pLeadEmail)) {
			ActionHelper.waitForAjaxToBeCompleted(driver);
			ActionHelper.staticWait(3);
			isSuccess = ActionHelper.Type(driver, selectIndividualLeads, Keys.ENTER);
		}
		return isSuccess;
	}
	public boolean typeTestEmail(String pEmail) {
		return ActionHelper.Type(driver, testEmail_input, pEmail);
	}
	public boolean clickOnSendButton() {
		boolean isSuccess = ActionHelper.Click(driver, send_button);
		ActionHelper.staticWait(20);
		return isSuccess;
	}
	public boolean typeAndAddDate(String pDate) {
		boolean isSuccess = false;
		if(ActionHelper.ClearAndType(driver, scheduleDatepicker, pDate)) {
			ActionHelper.waitForAjaxToBeCompleted(driver);
			ActionHelper.Type(driver, testEmail_input, "");
			isSuccess = ActionHelper.Click(driver, add_button);
		}
		return isSuccess;
	}
}
