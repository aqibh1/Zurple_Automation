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
public class TXBOForgotPasswordPage extends Page{
	
	@FindBy(xpath="//h3[text()=' Forgot Password']")
	WebElement forgot_password_heading;
	
	@FindBy(id="email")
	WebElement email_input;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submit_button;
	
	@FindBy(id="footer")
	WebElement footer;
	
	@FindBy(xpath="//p[contains(text(),'An email has been sent')]")
	WebElement email_is_sent_text;
	
	public TXBOForgotPasswordPage() {
		
	}
	public TXBOForgotPasswordPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyTorchXYellowIsDisplayed() {
		String lColorValue = footer.getCssValue("background-color");
		return lColorValue.equalsIgnoreCase("rgba(250, 160, 26, 1)");
	}
	public boolean isForgotPasswordPage() {
		return ActionHelper.waitForElementToBeVisible(driver, forgot_password_heading, 20);
	}
	public boolean isCorrectPageURL() {
		return driver.getCurrentUrl().contains("forgot");
	}
	public boolean typeEmailAddress(String pEmail) {
		return ActionHelper.Type(driver, email_input, pEmail);
	}
	public boolean isEmailSent() {
		return ActionHelper.waitForElementToBeVisible(driver, email_is_sent_text, 20);
	}
	public boolean isUrlChangedToSubmit() {
		return driver.getCurrentUrl().contains("forgot/submit");
	}
	public boolean clickOnSubmitButton() {
		ActionHelper.staticWait(2);
		return ActionHelper.Click(driver, submit_button);
	}
}
