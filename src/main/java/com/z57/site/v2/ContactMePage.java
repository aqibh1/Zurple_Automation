package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

public class ContactMePage extends Page{
	
	@FindBy(xpath="//h1[@class='entry-title title_prop' and contains(text(),'Contact Me')]")
	WebElement pageTitle;
	
	@FindBy(xpath="//div[@class='single-content listing-content']/h2")
	WebElement nameDesignation_heading;
	
	@FindBy(xpath="//div[@class='single-content listing-content']/h3[2]")
	WebElement phoneNumber_heading;
	
	public ContactMePage(WebDriver pWebDriver) {
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isContactMePage() {
		return ActionHelper.waitForElementToBeLocated(driver, "//h1[@class='entry-title title_prop' and contains(text(),'Contact')]", 20);
	}

	@Override
	public WebElement getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement getBrand() {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean verifyDesignation(String pDesignation) {
		boolean isSuccess = false;
		if(ActionHelper.waitForElementToBeVisible(driver, nameDesignation_heading, 30)) {
			AutomationLogger.info("Expected Designation: "+pDesignation);
			String lDesignation = ActionHelper.getText(driver, nameDesignation_heading).split(", ")[1].trim();
			AutomationLogger.info("Designation on Page: "+lDesignation);
			isSuccess = pDesignation.equalsIgnoreCase(lDesignation)?true:false;
		}
		if(!isSuccess) {
			AutomationLogger.error(driver.getCurrentUrl());
		}
		return isSuccess;
	}
	
	public boolean verifyPhone(String pPhone) {
		String lPhone = ActionHelper.getText(driver, phoneNumber_heading).trim();
		boolean isSuccess = pPhone.equalsIgnoreCase(lPhone)?true:false;
		return isSuccess;
	}

}
