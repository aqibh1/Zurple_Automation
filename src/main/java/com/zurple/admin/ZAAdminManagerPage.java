package com.zurple.admin;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * 
 * @author habibaaq
 *
 */
public class ZAAdminManagerPage extends Page{
		
	@FindBy(id="first_name")
	WebElement first_name;
	
	@FindBy(id="last_name")
	WebElement last_name;
	
	@FindBy(id="phone")
	WebElement phone;
	
	@FindBy(id="alias_phone_number")
	WebElement alias_phone;
	
	@FindBy(id="agent_code")
	WebElement agent_id;
	
	@FindBy(id="feed_id")
	WebElement feed_id;
	
	@FindBy(id="cms_flag")
	WebElement cms_flag;
	
	@FindBy(id="lead_flag")
	WebElement lead_flag;
	
	@FindBy(id="billing_access_flag")
	WebElement billing_flag;
	
	@FindBy(id="property_flag")
	WebElement prop_flag;
	
	@FindBy(id="email")
	WebElement login_email;
	
	@FindBy(id="alt_email")
	WebElement alt_email;
	
	@FindBy(id="forward1")
	WebElement forward_email;
	
	@FindBy(id="time_zone")
	WebElement time_zone;
	
	@FindBy(id="office_name")
	WebElement office_name;
	
	@FindBy(id="office_phone")
	WebElement office_phone;
	
	@FindBy(id="office_address")
	WebElement office_address;
	
	@FindBy(id="package_id")
	WebElement package_id;
	
	@FindBy(id="sms_enabled_flag")
	WebElement sms_flag;
	
	@FindBy(id="sms_notification_enabled")
	WebElement sms_notifications;
	
	@FindBy(id="owner_flag")
	WebElement owner_id;
	
	@FindBy(xpath="//div[@class='form-element-input']/descendant::option[@value='1' and @selected]")
	WebElement selected_feed; 
	
	public ZAAdminManagerPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyFirstName(String pExpected) {
		return pExpected.contains(ActionHelper.getText(driver, first_name));
	}
	
	public boolean verifyLastName(String pExpected) {
		return pExpected.contains(ActionHelper.getText(driver, last_name));
	}
	
	public boolean verifyPhone(String pExpected) {
		return ActionHelper.getText(driver, phone).contains(pExpected);
	}
	
	public boolean verifyAliasPhone() {
		return !ActionHelper.getText(driver, alias_phone).isEmpty();
	}
	
	public boolean verifyAgentCode(String pExpected) {
		return ActionHelper.getText(driver, agent_id).contains(pExpected);
	}
	
	public boolean verifyFeed(String pExpected) {
		return ActionHelper.getAttribute(feed_id, "value").contains(pExpected);
	}
	
	public boolean verifyCMSFlag() {
		return ActionHelper.isElementSelected(driver, cms_flag);
	}
	
	public boolean verifyLeadFlag() {
		return ActionHelper.isElementSelected(driver, lead_flag);
	}
	
	public boolean verifyBillingFlag() {
		return ActionHelper.isElementSelected(driver, billing_flag);
	}
	
	public boolean verifyPropFlag() {
		return ActionHelper.isElementSelected(driver, prop_flag);
	}

	public boolean verifyLoginEmail(String pExpected) {
		return ActionHelper.getText(driver, login_email).contains(pExpected);
	}

	public boolean verifyAltEmail(String pExpected) {
		return ActionHelper.getText(driver, alt_email).contains(pExpected);
	}

	public boolean verifyFowardEmail(String pExpected) {
		return ActionHelper.getText(driver, forward_email).contains(pExpected);
	}

	public boolean verifyTimeZone(String pExpected) {
		return ActionHelper.getText(driver, time_zone).contains(pExpected);
	}

	public boolean verifyOfficeName(String pExpected) {
		return ActionHelper.getText(driver, office_name).contains(pExpected);
	}

	public boolean verifyOfficePhone(String pExpected) {
		return ActionHelper.getText(driver, office_phone).contains(pExpected);
	}

	public boolean verifyOfficeAddress(String pExpected) {
		return ActionHelper.getText(driver, office_address).contains(pExpected);
	}

	public boolean verifyPackageId(String pExpected) {
		return ActionHelper.getText(driver, package_id).contains(pExpected);
	}

	public boolean verifySMSFlag() {
		return ActionHelper.isElementSelected(driver, sms_flag); 
	}

	public boolean verifySMSNotification() {
		return ActionHelper.isElementSelected(driver, sms_notifications); 
	}
	
	public boolean verifyOwnerId(String pExpected) {
		return ActionHelper.getAttribute(owner_id, "value").equalsIgnoreCase(pExpected);
	}
	
}
