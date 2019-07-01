/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	private ActionHelper actionHelper;
	
	PPSocialIntegrationPage(){
		
	}
	PPSocialIntegrationPage(WebDriver pWebDriver){
		setPageObject(pWebDriver, this);
		setLoginForm();
	}
	
	public LoginForm getLoginForm() {
		return loginForm;
	}
	public void setLoginForm() {
		this.loginForm = new LoginForm(driver);
	}
	public boolean isSocialIntegrationPage() {
		return actionHelper.waitForElementToBeVisible(socialIntegration_heading, 30);
	}
	
	public boolean clickOnDeauthorizeButton() {
		return actionHelper.Click(facebookDeauthorize_button);
	}
	
	public boolean clickOnLoginWithFacebookButton() {
		if(actionHelper.waitForElementToBeVisible(loginWithFacebook_button, 30)) {
			return actionHelper.Click(loginWithFacebook_button);
		}else {
			return false;
		}
	}
	public boolean isAuthorizationSuccessfull() {
		return actionHelper.waitForElementToBeVisible(facebookDeauthorize_button, 10);
	}

}
