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
	
	@FindBy(id="s2id_autogen4")
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
		setPageObject(pWebDriver, this);
	}
	
	public boolean typeEmailSubject(String pEmail) {
		return actionHelper.ClearAndType(emailSubject_input, pEmail);
	}
	public boolean isEmailCampaignManagerPage() {
		return actionHelper.waitForElementToBeVisible(emailCampaignManagerTitle, 30);
	}
	public boolean selectListing(String pListing) {
		boolean isSuccess = false;
		boolean isClick = actionHelper.Click(select_Listing);
		if(isClick && actionHelper.Type(selectListing_input, pListing)) {
			actionHelper.waitForAjaxToBeCompleted(driver);
			actionHelper.Wait(5);
			List<WebElement> list_of_suggested_listings = actionHelper.getListOfElementByXpath(suggestedResults);
			isSuccess = actionHelper.Click(list_of_suggested_listings.get(0));
		}
		return isSuccess;
	}
	public boolean selectTitleInHeader(String pHeader) {
		return actionHelper.selectDropDownOption(titleInHeader_dropdown,"", pHeader);
	}
	public boolean typeIndividualLead(String pLeadEmail) {
		boolean isSuccess = false;
		if(actionHelper.Type(selectIndividualLeads, pLeadEmail)) {
			actionHelper.waitForAjaxToBeCompleted(driver);
			actionHelper.Wait(3);
			isSuccess = actionHelper.Type(selectIndividualLeads, Keys.ENTER);
		}
		return isSuccess;
	}
	public boolean typeTestEmail(String pEmail) {
		return actionHelper.Type(testEmail_input, pEmail);
	}
	public boolean clickOnSendButton() {
		boolean isSuccess = actionHelper.Click(send_button);
		actionHelper.Wait(20);
		return isSuccess;
	}
	public boolean typeAndAddDate(String pDate) {
		boolean isSuccess = false;
		if(actionHelper.ClearAndType(scheduleDatepicker, pDate)) {
			actionHelper.waitForAjaxToBeCompleted(driver);
			actionHelper.Type(testEmail_input, "");
			isSuccess = actionHelper.Click(add_button);
		}
		return isSuccess;
	}
}
