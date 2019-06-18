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
public class PPUserSettingsPage extends Page{
	
	@FindBy(xpath="//h1[@class='z57-theme-page-topic'and text()='Settings']")
	WebElement settings_heading;
	
	@FindBy(xpath="//input[@name='settings[agent_designation]']")
	WebElement designation_input;
	
	@FindBy(id="settings_phone_agent")
	WebElement phoneAgent_input;

	@FindBy(id="submit_button")
	WebElement save_button;
	
	@FindBy(xpath="//div[@class='alert alert-success']/descendant::strong[text()='Details successfully updated']")
	WebElement updateSuccessMessage;

	private ActionHelper actionHelper;
	
	PPUserSettingsPage(){
		
	}
	PPUserSettingsPage(WebDriver pWebDriver){
		driver = pWebDriver;
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver,this);
	}
	
	public boolean isSettingsPage() {
		return actionHelper.waitForElementToBeVisible(settings_heading, 30);
	}
	
	public boolean typeDesignation(String pDesignation) {
		boolean isSuccess = actionHelper.ClearAndType(designation_input, pDesignation);
		actionHelper.Wait(4);
		return isSuccess;
	}
	public boolean typePhone(String pPhoneAgent) {
		boolean isSuccess = false;
		if(actionHelper.Clear(phoneAgent_input)) {
			actionHelper.Wait(5);
			driver.switchTo().alert().accept();
			actionHelper.waitForAjaxToBeCompleted(driver);
			isSuccess = actionHelper.ClearAndType(phoneAgent_input, pPhoneAgent);
		}
		return isSuccess;
	}
	public boolean clickOnSaveButton() {
		boolean clickSuccess = actionHelper.Click(save_button);
		return clickSuccess;
	}
	public boolean isDetailUpdatedSuccessfully() {
		actionHelper.Wait(30);
		return actionHelper.waitForElementToBeVisible(updateSuccessMessage, 30);
	}
	
	

}
