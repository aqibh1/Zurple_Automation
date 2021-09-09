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
	
	public ZAAdminManagerPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean getFirstName(String pExpected) {
		return ActionHelper.getText(driver, first_name).equalsIgnoreCase(pExpected);
	}
	
}
