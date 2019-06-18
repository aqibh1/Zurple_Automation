/**
 * 
 */
package com.z57.propertypulse;

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
public class PPEmailCampaignEditorCustom extends Page{
	
	@FindBy(id="feature_title")
	WebElement emailCampaignManagerTitle;
	
	@FindBy(id="email_subject")
	WebElement emailSubject_input;
	
	@FindBy(id="template_select")
	WebElement selectTemplate;
	
	@FindBy(id="s2id_autogen3")
	WebElement selectIndividualLeads;

	private ActionHelper actionHelper;
	
	public PPEmailCampaignEditorCustom() {
		
	}
	public PPEmailCampaignEditorCustom(WebDriver pWebDriver) {
		driver = pWebDriver;
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeEmailSubject(String pEmail) {
		return actionHelper.ClearAndType( emailSubject_input, pEmail);
	}
	public boolean isEmailCampaignManagerPage() {
		return actionHelper.waitForElementToBeVisible( emailCampaignManagerTitle, 30);
	}
	public boolean selectTemplate(String pTemplate) {
		boolean isSuccess = actionHelper.selectDropDownOption( selectTemplate, "", pTemplate);
		actionHelper.waitForAjaxToBeCompleted(driver);
		return isSuccess;
	}
	
	public boolean typeIndividualLead(String pLeadEmail) {
		boolean isSuccess = false;
		if(actionHelper.Type(selectIndividualLeads, pLeadEmail)) {
			actionHelper.waitForAjaxToBeCompleted(driver);
			actionHelper.Wait(3);
			isSuccess = actionHelper.Type( selectIndividualLeads, Keys.ENTER);
		}
		return isSuccess;
	}
	
}
