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
public class PPAutomationPage extends Page{
	
	@FindBy(xpath="//form[@id='automation_settings']/h1[text()='Automation Settings']")
	WebElement automationSettings_heading;
	
	@FindBy(xpath="//div[text()='Facebook']/following::select[@name='settings[accelerator_idx_posts]']")
	WebElement facebookIdxPostDropdown;
	
	@FindBy(id="save_automation_setings")
	WebElement saveButton;
	
	@FindBy(id="response_message_success")
	WebElement successMessage;
	
	public PPAutomationPage() {
		// TODO Auto-generated constructor stub
	}
	PPAutomationPage(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isAutomationSettingsPage() {
		return ActionHelper.waitForElementToBeVisible(driver, automationSettings_heading, 30);
	}
	public boolean selectFacebookIdxDropdown(String pOption) {
		return ActionHelper.selectDropDownOption(driver, facebookIdxPostDropdown, "", pOption);
	}
	public boolean clickOnSaveButton() {
		boolean result = false;
		if(ActionHelper.Click(driver, saveButton)) {
			ActionHelper.waitForAjaxToBeCompleted(driver);
			result = ActionHelper.waitForElementToBeVisible(driver, successMessage, 30);
		}
		return result;
	}
	public boolean isFacebookIdxPostOptionEnabled(String pOption) {
		return  ActionHelper.isOptionSelected(driver, facebookIdxPostDropdown,pOption);
	}
}
