package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.utility.ActionHelper;

public class PPHeader extends Page{
	
	@FindBy(xpath="//a[@id='nav-leads']")
	WebElement leadsTab;
	
	public PPHeader() {
		// TODO Auto-generated constructor stub
	}
	PPHeader(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
		
	}

	public boolean clickOnLeads() {
		return ActionHelper.Click(driver, leadsTab);
	}
}
