package com.zurple.admin;

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
public class ZAPackageManagerPage extends Page{
		
	@FindBy(id="full_name")
	WebElement full_name;
	
	@FindBy(id="phone")
	WebElement phone;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="company_id")
	WebElement company_id;
	
	@FindBy(id="setup_fee")
	WebElement setup_fee;
	
	@FindBy(id="path")
	WebElement path;
	
	@FindBy(id="zrm_client_id")
	WebElement zrm_client_id;
	
	@FindBy(id="subscription_start_date")
	WebElement subscription_start_date;
	
	@FindBy(id="additional_admins")
	WebElement additional_admins;
	
	@FindBy(id="payers")
	WebElement payers;
	
	String feature_bundles = "ms-selected";
	
	public ZAPackageManagerPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean verifyFullName(String pExpected) {
		return ActionHelper.getAttribute(full_name, "value").equalsIgnoreCase(pExpected.trim());
	}
	
	public boolean verifyPhone(String pExpected) {
		return pExpected.contains(ActionHelper.getAttribute(phone, "value"));
	}
	
	public boolean verifyEmail(String pExpected) {
		return ActionHelper.getAttribute(email,"value").contains(pExpected.trim());
	}
	
	public boolean verifyCompany(String pExpected) {
		return ActionHelper.getAttribute(company_id, "value").contains(pExpected.trim());
	}
	
	public boolean verifySetupFee() {
		return !ActionHelper.getAttribute(setup_fee,"value").isEmpty();
	}
	
	public boolean verifyURLPath(String pExpected) {
		String adfsf = ActionHelper.getAttribute(path,"value");
		String afdjalkf = pExpected.trim();
		AutomationLogger.info(adfsf);
		AutomationLogger.info(afdjalkf);
		return ActionHelper.getAttribute(path,"value").contains(pExpected.trim());
	}
	
	public boolean verifyZRMClientId() {
		return !ActionHelper.getAttribute(zrm_client_id,"value").isEmpty();
	}
	
	public boolean verifySubscriptionDate(String pExpected) {
		return ActionHelper.getAttribute(subscription_start_date,"value").contains(pExpected.trim());
	}
	
	public boolean verifyAdditionalAdmins(String pExpected) {
		return ActionHelper.getAttribute(additional_admins,"value").contains(pExpected.trim());
	}
	
	public boolean verifyPayers(String pExpected) {
		return ActionHelper.getAttribute(payers,"value").contains(pExpected.trim());
	}

	public boolean verifyFeatureBundles(String pExpected,int pIndex) {
		return ActionHelper.getTextByIndex(driver, feature_bundles, pIndex).contains(pExpected.trim());
	}
}