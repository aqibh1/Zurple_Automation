package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PPHeader extends Page{
	
	@FindBy(xpath="//a[@id='nav-leads']")
	WebElement leadsTab;
	
	@FindBy(id="nav-website")
	WebElement websiteTab;
	
	public PPHeader() {
		// TODO Auto-generated constructor stub
	}
	PPHeader(WebDriver pWebDriver){
		setPageObject(pWebDriver, this);
		
	}

	public boolean clickOnLeads() {
		return actionHelper.Click(leadsTab);
	}
	public boolean clickOnWebsite() {
		return actionHelper.Click(websiteTab);
	}
}
