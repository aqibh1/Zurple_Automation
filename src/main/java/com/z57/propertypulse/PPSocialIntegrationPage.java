/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.z57.LoginForm;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPSocialIntegrationPage extends Page{
	LoginForm loginForm;
	
	@FindBy(xpath="//div[@class='tab-content']/h1[text()='Social Integration']")
	WebElement socialIntegration_heading;
	
	@FindBy(xpath="//button[contains(@url,'/deauthorize/facebook')]")
	WebElement facebookDeauthorize_button;
	
	@FindBy(xpath="//button[text()='Login with Facebook']")
	WebElement loginWithFacebook_button;
	
	PPSocialIntegrationPage(){
		
	}
	PPSocialIntegrationPage(WebDriver pWebDriver){
		driver = pWebDriver;
		setLoginForm();
		PageFactory.initElements(driver, this);
	}
	
	public LoginForm getLoginForm() {
		return loginForm;
	}
	public void setLoginForm() {
		this.loginForm = new LoginForm(driver);
	}
	public boolean isSocialIntegrationPage() {
		return ActionHelper.waitForElementToBeVisible(driver, socialIntegration_heading, 30);
	}
	
	public boolean clickOnDeauthorizeButton() {
		return ActionHelper.Click(driver, facebookDeauthorize_button);
	}
	
	public boolean clickOnLoginWithFacebookButton() {
		if(ActionHelper.waitForElementToBeVisible(driver, loginWithFacebook_button, 30)) {
			return ActionHelper.Click(driver, loginWithFacebook_button);
		}else {
			return false;
		}
	}
	public boolean isAuthorizationSuccessfull() {
		return ActionHelper.waitForElementToBeVisible(driver, facebookDeauthorize_button, 10);
	}

}
