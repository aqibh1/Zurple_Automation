package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ContactMePage extends Page{
	
	@FindBy(xpath="//h1[@class='entry-title title_prop' and contains(text(),'Contact Me')]")
	WebElement pageTitle;
	
	@FindBy(xpath="//div[@class='single-content listing-content']/h2")
	WebElement nameDesignation_heading;
	
	@FindBy(xpath="//div[@class='single-content listing-content']/h3[2]")
	WebElement phoneNumber_heading;
	
	public ContactMePage(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);
	}
	
	public boolean isContactMePage() {
		return actionHelper.waitForElementToBeLocated("//h1[@class='entry-title title_prop' and contains(text(),'Contact')]", 20);
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
		if(actionHelper.waitForElementToBeVisible(nameDesignation_heading, 30)) {
			String lDesignation = actionHelper.getText(nameDesignation_heading).split(", ")[1].trim();
			isSuccess = pDesignation.equalsIgnoreCase(lDesignation)?true:false;
		}
		return isSuccess;
	}
	
	public boolean verifyPhone(String pPhone) {
		String lPhone = actionHelper.getText(phoneNumber_heading).trim();
		boolean isSuccess = pPhone.equalsIgnoreCase(lPhone)?true:false;
		return isSuccess;
	}

}
