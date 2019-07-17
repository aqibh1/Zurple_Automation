/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	@FindBy(xpath="//input[@name='settings[accelerator_idx_posts_zip]']")
	WebElement zip_input;
	
	@FindBy(xpath="//select[@name='settings[accelerator_idx_posts_price_min]']")
	WebElement minimum_price;
	
	@FindBy(xpath="//select[@name='settings[accelerator_idx_posts_price_max]']")
	WebElement maximum_price;
	
	@FindBy(xpath="//div[text()='Twitter']/following::select[@name='settings[accelerator_idx_posts_tw]']")
	WebElement twitterIdxPostDropdown;
	
	public PPAutomationPage() {
		// TODO Auto-generated constructor stub
	}
	PPAutomationPage(WebDriver pWebDriver){
		setPageObject(pWebDriver, this);
	}
	public boolean isAutomationSettingsPage() {
		return actionHelper.waitForElementToBeVisible( automationSettings_heading, 30);
	}
	public boolean selectFacebookIdxDropdown(String pOption) {
		return actionHelper.selectDropDownOption( facebookIdxPostDropdown, "", pOption);
	}
	public boolean clickOnSaveButton() {
		boolean result = false;
		if(actionHelper.Click( saveButton)) {
			actionHelper.waitForAjaxToBeCompleted(driver);
			result = actionHelper.waitForElementToBeVisible( successMessage, 30);
		}
		return result;
	}
	public boolean isFacebookIdxPostOptionEnabled(String pOption) {
		return  actionHelper.isOptionSelected( facebookIdxPostDropdown,pOption);
	}
	
	public boolean typeZip(String pZip) {
		return actionHelper.ClearAndType(zip_input, pZip);
	}
	public boolean selectMinPrice(String pPrice) {
		return actionHelper.selectDropDownOption(minimum_price, "", pPrice);
	}
	public boolean selectMaxPrice(String pPrice) {
		return actionHelper.selectDropDownOption(maximum_price, "", pPrice);
	}
	public boolean selectTwitterIdxDropdown(String pOption) {
		return actionHelper.selectDropDownOption(twitterIdxPostDropdown, "", pOption);
	}
}
