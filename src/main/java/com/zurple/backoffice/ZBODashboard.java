package com.zurple.backoffice;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

public class ZBODashboard extends Page{
	@FindBy(className="z-lead-phone")
	WebElement phoneNumber;
	
	@FindBy(xpath="//li[@class='menu-main-item dropdown' and @role='presentation']/a[@role='button'  and not(@id)]")
	WebElement siteOwner_dropdown;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/li/a[text()='Logout']")
	WebElement logout_button;
	
	@FindBy(id="username")
	WebElement username_input;
	
	public ZBODashboard(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyPhoneNumberText(String pPhoneNumber) {
		String pNumText = ActionHelper.getText(driver, phoneNumber);
		// pNumText = pNumText.replace(' ', '-');
		AutomationLogger.info("Fetching phone number");
		return pNumText.equalsIgnoreCase(pPhoneNumber);
	}
	
	public boolean verifyPhoneAlert() {
		boolean isVerified = false;
		
		ActionHelper.resizeWindow(driver, 444, 562);
		ActionHelper.RefreshPage(driver);
		ActionHelper.Click(driver, phoneNumber);

		isVerified = ActionHelper.isAlertPresent(driver);
		return isVerified;
	}
	public boolean doLogout() {
		boolean isLogoutSuccessful = false;
		if(ActionHelper.MouseHoverOnElement(driver, siteOwner_dropdown) && ActionHelper.Click(driver, logout_button)) {
			isLogoutSuccessful = ActionHelper.waitForElementToBeVisible(driver, username_input, 60);
		}
		return isLogoutSuccessful;
	}
}
