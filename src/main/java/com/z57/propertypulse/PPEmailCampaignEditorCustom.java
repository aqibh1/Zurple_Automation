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
public class PPEmailCampaignEditorCustom extends Page{
	
	@FindBy(id="feature_title")
	WebElement emailCampaignManagerTitle;
	
	@FindBy(id="email_subject")
	WebElement emailSubject_input;
	
	@FindBy(id="template_select")
	WebElement selectTemplate;
	
//	@FindBy(id="s2id_autogen3")
//	WebElement selectIndividualLeads;
	
	@FindBy(xpath="//div[@id='s2id_leads_select']/descendant::input")
	WebElement selectIndividualLeads;
	
	public PPEmailCampaignEditorCustom() {
		
	}
	public PPEmailCampaignEditorCustom(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeEmailSubject(String pEmail) {
		return ActionHelper.ClearAndType(driver, emailSubject_input, pEmail);
	}
	public boolean isEmailCampaignManagerPage() {
		return ActionHelper.waitForElementToBeVisible(driver, emailCampaignManagerTitle, 30);
	}
	public boolean selectTemplate(String pTemplate) {
		boolean isSuccess = ActionHelper.selectDropDownOption(driver, selectTemplate, "", pTemplate);
		ActionHelper.waitForAjaxToBeCompleted(driver);
		return isSuccess;
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
	
}
