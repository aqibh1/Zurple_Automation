/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import us.zengtest1.Page;

/**
 * @author adar
 *
 */
public class ZWLoginPage extends Page{

	@FindBy(xpath="//div[@class='box_inner_content']/h1[text()='Member Login']")
	WebElement member_login_heading;
	
	@FindBy(id="username")
	WebElement username_input;
	
	@FindBy(xpath="//div[@id='form-element_signin']/descendant::button[@type='submit']")
	WebElement login_button;
	
	@FindBy(xpath="//div[@id='form']/p[contains(text(),'This email address does not')]")
	WebElement login_unsuccessful_message;
	
	@FindBy(xpath="//div[@id='form']/descendant::a[text()='Sign up here.']")
	WebElement signupHere_link;
	
	public ZWLoginPage() {
		// TODO Auto-generated constructor stub
	}
	public ZWLoginPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isLoginPage() {
		AutomationLogger.testStep("Checking the Login Page");
		return ActionHelper.waitForElementToBeVisible(driver, member_login_heading, 30);
	}
	public boolean typeUsername(String pUsername) {
		AutomationLogger.testStep("Typing username");
		return ActionHelper.ClearAndType(driver, username_input, pUsername);
	}
	public boolean clickLoginButton() {
		AutomationLogger.testStep("Clicking login button");
		return ActionHelper.Click(driver, login_button);
	}
	public boolean isLoginSuccessful() {
		AutomationLogger.testStep("Checking if Login is successful");
		boolean isUrlChanged = !driver.getCurrentUrl().contains("login");
		boolean lAnyErrorMessage = !ActionHelper.isElementVisible(driver, login_unsuccessful_message);
		return (isUrlChanged && lAnyErrorMessage);
	}
	public boolean clickOnSignUpLink() {
		return ActionHelper.Click(driver, signupHere_link);
	}
	//Generic method for login
	public void doLogin(String pUsername) {
		assertTrue(isLoginPage(), "Login Page is not visible..");
		assertTrue(typeUsername(pUsername), "Unable to type username in input field..");
		assertTrue(clickLoginButton(), "Unable to click on Login button..");
		assertTrue(isLoginSuccessful(), "Login is not successfull..");
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
	@Override
	public WebElement getTopMenu() {
		// TODO Auto-generated method stub
		return null;
	}
}
