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
}
