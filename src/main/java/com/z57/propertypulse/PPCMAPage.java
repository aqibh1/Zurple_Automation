/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPCMAPage extends Page{
	
	@FindBy(id="cma_lead_info_name")
	WebElement leadName_input;
	
	@FindBy(id="cma_lead_info_phone")
	WebElement leadPhone_input;
	
	@FindBy(id="cma_lead_info_email")
	WebElement leadEmail_input;
	
	@FindBy(id="cma_address_form")
	WebElement CMAAddress_form;
	
	@FindBy(id="submit_cma_request")
	WebElement submit_button;
	
	@FindBy(id="cma_agent_info")
	WebElement agentInfo;
	
	public PPCMAPage(){
		
	}
	public PPCMAPage(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean typeName(String pName) {
		return ActionHelper.Type(driver, leadName_input, pName);
	}
	public boolean typePhone(String pPhone) {
		return ActionHelper.Type(driver, leadPhone_input, pPhone);
	}
	public boolean typeEmail(String pEmail) {
		return ActionHelper.Type(driver, leadEmail_input, pEmail);
	}
	public boolean clickOnSubmitButton() {
		return ActionHelper.Click(driver, submit_button);
	}
	public boolean isSellerLandingPage() {
		return ActionHelper.waitForElementToBeVisible(driver, CMAAddress_form, 20);
	}
	public boolean isRegistrationSuccessful() {
		return ActionHelper.waitForElementToBeVisible(driver, agentInfo, 30);
	}
}
