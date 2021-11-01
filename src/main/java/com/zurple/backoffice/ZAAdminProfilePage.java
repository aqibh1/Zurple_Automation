package com.zurple.backoffice;

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
public class ZAAdminProfilePage extends Page{
		
	@FindBy(id="first_name")
	WebElement first_name;
	
	@FindBy(id="last_name")
	WebElement last_name;
	
	@FindBy(id="phone")
	WebElement phone;
	
	@FindBy(id="dre")
	WebElement license_number;
	
	@FindBy(id="office_name")
	WebElement brokerage_name;
	
	@FindBy(id="office_address")
	WebElement brokerage_address;
	
	@FindBy(id="office_phone")
	WebElement brokerage_phone;
	
	@FindBy(id="email")
	WebElement login_email;
	
	@FindBy(id="alias_email")
	WebElement alias_email;
	
	@FindBy(id="email_display_name")
	WebElement display_name;
	
	@FindBy(id="email_unique_sign_off")
	WebElement unique_sign_off;
	
	@FindBy(id="email_display_brokerage_logo")
	WebElement display_brokerage_logo;
	
	@FindBy(id="email_display_profile_image")
	WebElement display_profile_image;
	
	@FindBy(id="task_alert_enabled")
	WebElement suggested_tasks;
	
	@FindBy(id="sms_notif_user_reply")
	WebElement sms_reply;
	
	String timeZone = "//select[@id='time_zone']/option";
	
	@FindBy(id="zillow_email")
	WebElement zillow_email;
	
	String zillow_connection = "//select[@id='zillow_live_connection']/option";
			
	public ZAAdminProfilePage(WebDriver pWebDriver) {
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
	
	public boolean verifySMSReplyFlag() {
		return ActionHelper.isElementSelected(driver, sms_reply);
	}
	
	public boolean verifySuggestedTaskFlag() {
		return ActionHelper.isElementSelected(driver, suggested_tasks);
	}
	
	public boolean verifyDisplayProfileImageFlag() {
		return ActionHelper.isElementSelected(driver, display_profile_image);
	}
	
	public boolean verifyDisplayBrokerageLogoFlag() {
		return ActionHelper.isElementSelected(driver, display_brokerage_logo);
	}
	
	public boolean verifyUniqueSignOff(String pExpected) {
		return ActionHelper.getText(driver, unique_sign_off).contains(pExpected);
	}
	
	public boolean verifyDisplayName(String pExpected) {
		return ActionHelper.getText(driver, display_name).contains(pExpected);
	}

	public boolean verifyLoginEmail(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(login_email, "value"));
	}

	public boolean verifyAliasEmail(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(alias_email, "value"));
	}

	public boolean verifyLicenseNumber(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(license_number, "value"));
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
	
	public boolean verifyZillowConnection(String pExpected) {
		String attribute = "";
		List<WebElement> elem = ActionHelper.getListOfElementByXpath(driver, zillow_connection);
		for(WebElement e:elem) {
			if(ActionHelper.isElementSelected(driver, e)) {
				attribute = ActionHelper.getAttribute(e, "label");
				break;
			}
		}
		return pExpected.contains(attribute.trim());
	}

	public boolean verifyZillowEmail(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(zillow_email, "value"));
	}

	public boolean verifyBrokeragePhone(String pExpected) {
		return ActionHelper.getText(driver, brokerage_phone).contains(pExpected);
	}

	public boolean verifyBrokerageAddress(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(brokerage_address, "value"));
	}

	public boolean verifyBrokerageName(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(brokerage_name, "value"));
	}
	
}
