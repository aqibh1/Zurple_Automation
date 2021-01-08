/**
 * 
 */
package com.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import us.zengtest1.Page;

/**
 * @author adar
 *
 */
public class ZWRegisterUserPage extends Page{
	
	@FindBy(xpath="//input[@name='register_button']")
	WebElement register_button;
	
	@FindBy(id="first_name")
	WebElement firstName;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="phone")
	WebElement phone;
	
	@FindBy(id="subscribe")
	WebElement subscribeCheckbox;
	
	@FindBy(xpath="//h1[contains(text(),'Thank you for registering')]")
	WebElement thankyouForRegistering;
	
	@FindBy(xpath="//h1[contains(text(),'Thank you for submitting your request')]")
	WebElement thankyouForSubmittingYourRequest;
	
	public ZWRegisterUserPage() {
		
	}
	public ZWRegisterUserPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isRegisterPage() {
		return ActionHelper.waitForElementToBeVisible(driver, register_button, 30);
	}
	public boolean typeName(String pName) {
		return ActionHelper.ClearAndType(driver, firstName, pName);
	}
	public boolean typeEmail(String pEmail) {
		return ActionHelper.ClearAndType(driver, email, pEmail);
	}
	public boolean typePhone(String pPhone) {
		return ActionHelper.ClearAndType(driver, phone, pPhone);
	}
	public boolean isTermsAndCondCheckboxChecked() {
		return ActionHelper.getAttribute(subscribeCheckbox, "value").equalsIgnoreCase("1");
	}
	public boolean clickRegisterButton() {
		return ActionHelper.Click(driver, register_button);
	}
	public boolean isRegisterSuccessfully() {
		return ActionHelper.waitForElementToBeVisible(driver, thankyouForRegistering, 60);
	}
	public boolean isRequestSubmittedSuccessfully() {
		return ActionHelper.waitForElementToBeVisible(driver, thankyouForSubmittingYourRequest, 60);
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
