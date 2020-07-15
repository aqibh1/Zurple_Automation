/**
 * 
 */
package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOEditAgentPage extends Page{
	
	@FindBy(id="delete-admin")
	WebElement delete_button;
	
	@FindBy(id="email")
	WebElement email_input;
	
	@FindBy(id="last_name")
	WebElement agent_last_name;
	
	@FindBy(id="edit-agent-button")
	WebElement save_button;
	
	@FindBy(xpath="//div[@role='alert']/strong[contains(text(),'Agent information updated.')]")
	WebElement agent_updated_message;
	
	@FindBy(id="office_phone")
	WebElement office_phone_input;
	
	public ZBOEditAgentPage() {
		
	}
	public ZBOEditAgentPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isEditAgentPage() {
		return ActionHelper.waitForElementToBeVisible(driver, delete_button, 30);
	}
	public String getAgentEmail() {
		return ActionHelper.getTextByValue(driver, email_input);
	}
	public boolean typeAgentLastName(String pName) {
		return ActionHelper.ClearAndType(driver, agent_last_name, pName);
	}
	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, save_button);
	}
	public boolean isAgentInfoUpdated() {
		return ActionHelper.waitForElementToBeVisible(driver, agent_updated_message, 10);
	}
	public boolean updateAgentOfficePhone(String pPhone) {
		return ActionHelper.ClearAndType(driver, office_phone_input,pPhone);
	}
	public boolean verifyOfficePhone(String pPhone) {
		return ActionHelper.getTextByValue(driver, office_phone_input).equalsIgnoreCase(pPhone);
	}
}
