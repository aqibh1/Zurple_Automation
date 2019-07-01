package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

public class PPHomePage extends Page{
	
	@FindBy(xpath="//div[@id='member-nav']/descendant::ul[@class='dropdown-menu']")
	WebElement settings_dropdown;
	String settings_dropdown_xpath = "//div[@id='member-nav']/descendant::ul[@class='dropdown-menu']";
	
	@FindBy(id="menu-signed-in-as")
	WebElement userSignedIn;
	
	@FindBy(xpath="//div[@id='member-nav']/descendant::a[@class='btn dropdown-toggle']")
	WebElement settings_button;
	
	String setting_option = "//ul[@class='dropdown-menu']/descendant::a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	public PPHomePage() {
		// TODO Auto-generated constructor stub
	}
	PPHomePage(WebDriver pWebDriver){
		setPageObject(pWebDriver, this);
	}
	public boolean isLoginSuccessful(String pUsername) {
		boolean isUserLoggedIn = false;		
		if(actionHelper.waitForElementToBeVisible(settings_button, 10)) {
			isUserLoggedIn = true;
		}
		return isUserLoggedIn;
	}
	
	public boolean goToSettingsOption(String pOption) {
		boolean result = false;
		AutomationLogger.info("Waiting for Settings button to be visible..");
		boolean isSettingVisible = actionHelper.waitForElementToBeVisible(settings_button, 30);
		if(isSettingVisible) {
			actionHelper.Click(settings_button);
			result = actionHelper.Click(actionHelper.getDynamicElement(setting_option, pOption));
		}
		return result;
	}

}
