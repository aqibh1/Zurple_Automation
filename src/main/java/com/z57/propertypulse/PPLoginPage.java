package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

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
}
