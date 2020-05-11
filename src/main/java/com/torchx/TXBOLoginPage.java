/**
 * 
 */
package com.torchx;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class TXBOLoginPage extends Page{
	
	@FindBy(xpath="//h3[contains(text(),' TorchX Log In')]")
	WebElement zurpleLoginHeading;
	
	@FindBy(id="username")
	WebElement username_input;

	@FindBy(id="passwd")
	WebElement password_input;
	
	@FindBy(xpath="//button[contains(text(),'Login')]")
	WebElement login_button;
	
	@FindBy(xpath="//a[contains(text(),'I Forgot')]")
	WebElement forgot_password_link;

	@FindBy(xpath="//img[@src='img/torchx/torchx_logo.png']")
	WebElement torchX_logo;
	
	@FindBy(xpath="//p[@class='alert alert-danger' and contains(text(),'This Admin account is not active')]")
	WebElement incorrect_error_alert;
	
	@FindBy(id="footer")
	WebElement footer;
	
	public TXBOLoginPage() {
		
	}
	public TXBOLoginPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isLoginPage() {
		return ActionHelper.waitForElementToBeVisible(driver, zurpleLoginHeading, 30);
	}
	public boolean isTorchXLogoVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, torchX_logo, 10);
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
		return !ActionHelper.waitForElementToBeVisible(driver, incorrect_error_alert, 10);
		
	}
	public boolean verifyTorchXYellowIsDisplayed() {
		String lColorValue = footer.getCssValue("background-color");
		return lColorValue.equalsIgnoreCase("rgba(250, 160, 26, 1)");
	}
	public boolean clickOnForgotPasswordLink() {
		return ActionHelper.Click(driver, forgot_password_link);
	}

}
