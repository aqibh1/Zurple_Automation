package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PPLoginPage extends Page{
	
	@FindBy(xpath="//input[@id='inputEmail']")
	WebElement username_input;
	
	@FindBy(xpath="//input[@id='inputPassword']")
	WebElement password_input;
	
	@FindBy(xpath="//button[@id='submit']")
	WebElement login_button;
	
	@FindBy(xpath="//div[@class='login-error']/descendant::span[@class='login-error-message']")
	WebElement invalid_password_error;
	
	@FindBy(xpath="//div[@id='member-nav']/descendant::a[@class='btn dropdown-toggle']")
	WebElement settings_button;
	
	public PPLoginPage() {
	}
	public PPLoginPage(WebDriver pWebDriver) {
//		driver = pWebDriver;
//		actionHelper = new ActionHelper(driver);
//		PageFactory.initElements(driver,this);
		setPageObject(pWebDriver, this);
	}
	
	public boolean typeUsername(String pUserName) {
		return actionHelper.ClearAndType(username_input, pUserName);
	}
	public boolean typePassword(String pPassword) {
		return actionHelper.ClearAndType(password_input, pPassword);
	}
	public boolean clickOnLoginButton() {
		return actionHelper.Click(login_button);
	}
	
	public boolean isLoginPageVisible() {
		return actionHelper.isElementVisible(username_input);
	}
	
	public boolean isLoginFailed() {
		return actionHelper.isElementVisible(invalid_password_error);
	}
	
	public boolean isLoginSuccessful(String pUsername) {
		boolean isUserLoggedIn = false;	

		if(actionHelper.waitForElementToBeVisible(settings_button, 60)) {
			isUserLoggedIn = true;
		}
		return isUserLoggedIn;
	}
}
