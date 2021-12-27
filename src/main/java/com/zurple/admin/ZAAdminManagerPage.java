package com.zurple.admin;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

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
	
	String timeZone = "//select[@id='time_zone']/option";
	
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
	
	@FindBy(id="update")
	WebElement update_button;
		
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
		return pExpected.contains(ActionHelper.getAttribute(phone, "value").replaceAll("[()\\s-]+", ""));
	}
	
	public boolean verifyAliasPhone() {
		return !ActionHelper.getAttribute(alias_phone, "value").isEmpty();
	}
	
	public boolean verifyAgentCode(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(agent_id, "value"));
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
		return pExpected.contains(ActionHelper.getAttribute(login_email, "value"));
	}

	public boolean verifyAltEmail(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(alt_email, "value"));
	}

	public boolean verifyFowardEmail(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(forward_email, "value"));
	}

	public boolean verifyTimeZone(String pExpected) {
		String attribute = "";
		List<WebElement> elem = ActionHelper.getListOfElementByXpath(driver, timeZone);
		for(WebElement e:elem) {
			if(ActionHelper.isElementSelected(driver, e)) {
				attribute = ActionHelper.getAttribute(e, "label");
				break;
			}
		}
		return pExpected.contains(attribute.trim());
	}

	public boolean verifyOfficeName(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(office_name, "value"));
	}

	public boolean verifyOfficePhone(String pExpected) {
		return ActionHelper.getText(driver, office_phone).contains(pExpected);
	}

	public boolean verifyOfficeAddress(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(office_address, "value"));
	}

	public boolean verifyPackageId(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(package_id, "value"));
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
	
	public boolean updateFirstName() {
		String fName = updateName(ActionHelper.getAttribute(first_name, "value"));
		return ActionHelper.ClearAndType(driver, first_name, fName);
	}
	
	public boolean updateLastName() {
		String lName = updateName(ActionHelper.getAttribute(last_name, "value"));
		return ActionHelper.ClearAndType(driver, last_name, lName);
	}
	
	public boolean updateEmail() {
		String email = updateEmail(ActionHelper.getAttribute(login_email, "value"));
		return ActionHelper.ClearAndType(driver, login_email, email);
	}
	
	public boolean updatePhone() {
		String Phone = ActionHelper.getAttribute(phone, "value").replace('7', '0');
		return ActionHelper.ClearAndType(driver, phone, Phone);
	}
	
	public boolean updateOfficeName() {
		String officeName = ActionHelper.getAttribute(office_name, "value")+" Updated";
		return ActionHelper.ClearAndType(driver, office_name, officeName);
	}
	
	public boolean clickUpdateButton() {
		return ActionHelper.Click(driver, update_button);
	}
	
}
