/**
 * 
 */
package com.zurple.admin;

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
public class ZACreateActivityAlertPage extends Page{
	
	@FindBy(xpath="//h1[text()='Create Activity Alert']")
	WebElement activity_alert_heading;
	
	@FindBy(id="alerttype")
	WebElement alertType_dropdown_button;
	
	@FindBy(id="package")
	WebElement package_dropdown_button;
	
	@FindBy(id="admin")
	WebElement admin_dropdown_button;
	
	@FindBy(id="user")
	WebElement user_dropdown_button;
	
	@FindBy(id="triggerdate")
	WebElement triggerdate_input;
	
	@FindBy(id="submit")
	WebElement create_alert_button;
	
	public ZACreateActivityAlertPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isActicityAlertPage() {
		return ActionHelper.waitForElementToBeVisible(driver, activity_alert_heading, 30);
	}
	public boolean selectAlertType(String pAlertType) {
		return ActionHelper.selectDropDownOption(driver, alertType_dropdown_button, "", pAlertType);
	}
	public boolean selectPackage(String pPackageId) {
		return ActionHelper.clickAndSelectFromDropdownByValue(driver, package_dropdown_button, pPackageId);
	}
	public boolean selectAdmin(String pAdminId) {
		boolean isSelected = false;
		if(ActionHelper.waitforDropdownToBePopulated(driver, admin_dropdown_button, 10)) {
			isSelected = ActionHelper.clickAndSelectFromDropdownByValue(driver,admin_dropdown_button, pAdminId);
		}
		return isSelected;
	}
	public boolean selectUser(String pUserID) {
		boolean isSelected = false;
		if(ActionHelper.waitforDropdownToBePopulated(driver, user_dropdown_button, 10)) {
			isSelected = ActionHelper.clickAndSelectFromDropdownByValue(driver,user_dropdown_button, pUserID);
		}
		return isSelected;
	}
	public boolean typeTriggerDate(String pTriggerDate) {
		return ActionHelper.Type(driver, triggerdate_input, pTriggerDate);
	}
	public boolean clickOnCreateAlertButton() {
		return ActionHelper.Click(driver, create_alert_button);
	}
}
