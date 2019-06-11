/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.z57.LoginForm;
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
	
	PPUserSettingsPage(){
		
	}
	PPUserSettingsPage(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isSettingsPage() {
		return ActionHelper.waitForElementToBeVisible(driver, settings_heading, 30);
	}
	
	public boolean typeDesignation(String pDesignation) {
		boolean isSuccess = ActionHelper.ClearAndType(driver, designation_input, pDesignation);
		ActionHelper.staticWait(4);
		return isSuccess;
	}
	public boolean typePhone(String pPhoneAgent) {
		boolean isSuccess = false;
		if(ActionHelper.Clear(driver, phoneAgent_input)) {
			ActionHelper.staticWait(5);
			driver.switchTo().alert().accept();
			ActionHelper.waitForAjaxToBeCompleted(driver);
			isSuccess = ActionHelper.ClearAndType(driver, phoneAgent_input, pPhoneAgent);
		}
		return isSuccess;
	}
	public boolean clickOnSaveButton() {
		boolean clickSuccess = ActionHelper.Click(driver, save_button);
//		ActionHelper.staticWait(5);
//		driver.switchTo().alert().accept();
		return clickSuccess;
	}
	public boolean isDetailUpdatedSuccessfully() {
		ActionHelper.staticWait(30);
		return ActionHelper.waitForElementToBeVisible(driver, updateSuccessMessage, 30);
	}
	
	

}
