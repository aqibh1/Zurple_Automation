package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBODashboard extends Page{
	@FindBy(className="z-lead-phone")
	WebElement phoneNumber;
	
	public ZBODashboard(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public String getPhoneNumberText() {
		String pNumText = ActionHelper.getText(driver, phoneNumber);
		return pNumText;
	}
}
