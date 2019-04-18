package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.utility.ActionHelper;

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
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeUsername(String pUserName) {
		return ActionHelper.ClearAndType(driver, username_input, pUserName);
	}
	public boolean typePassword(String pPassword) {
		return ActionHelper.ClearAndType(driver, password_input, pPassword);
	}
	public boolean clickOnLoginButton() {
		return ActionHelper.Click(driver, login_button);
	}
	
	public boolean isLoginPageVisible() {
		return ActionHelper.isElementVisible(driver, username_input);
	}
	
	public boolean isLoginFailed() {
		return ActionHelper.isElementVisible(driver, invalid_password_error);
	}
	
	public boolean isLoginSuccessful(String pUsername) {
		boolean isUserLoggedIn = false;	

		if(ActionHelper.waitForElementToBeVisible(driver, settings_button, 60)) {
			isUserLoggedIn = true;
		}
		return isUserLoggedIn;
	}
}
