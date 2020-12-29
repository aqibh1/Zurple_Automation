/**
 * 
 */
package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOLoginPage extends Page{
	
	@FindBy(xpath="//h3[contains(text(),' Zurple Log In')]")
	WebElement zurpleLoginHeading;
	
	@FindBy(id="username")
	WebElement username_input;

	@FindBy(id="passwd")
	WebElement password_input;
	
	@FindBy(xpath="//button[contains(text(),'Login')]")
	WebElement login_button;
	
	@FindBy(xpath="//a[contains(text(),'I Forgot')]")
	WebElement forgot_password_link;
	
//	@FindBy(xpath="//input[@name='name']")
//	WebElement searchLead_input;
	
	String searchLead_input = "//input[@name='name']";
	
	public ZBOLoginPage() {
		
	}
	public ZBOLoginPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isLoginPage() {
		return ActionHelper.waitForElementToBeVisible(driver, zurpleLoginHeading, 30);
	}
	public boolean typeUserName(String pUsername) {
		return ActionHelper.Type(driver, username_input, pUsername);
	}
	public boolean typePassword(String pPassword) {
		return ActionHelper.Type(driver, password_input, pPassword);
	}
	public boolean clickLoginButton() {
		return ActionHelper.Click(driver, login_button);
	}
	public boolean isForgotPasswordLinkExists() {
		return ActionHelper.waitForElementToBeVisible(driver, forgot_password_link, 5);
	}
	public boolean isLoginSuccessful() {
		return ActionHelper.waitForElementsToBeFound(driver, searchLead_input);
	}
}
