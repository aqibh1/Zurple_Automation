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
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOSocialIntegrationAndSettingsPage extends Page{
	
	@FindBy(xpath="//h3[text()='Integrations & Settings']")
	WebElement integrationAndSettings_heading;
	
	@FindBy(xpath="//div[@class='row row-center']/descendant::img[@src='/img/zurple/Facebook_connected_200.png']")
	WebElement facebookConnected_image;
	
	@FindBy(xpath="//button[@data-social-network='facebook' and text()='Disconnect']")
	WebElement facebookDisConnect_button;
	
	@FindBy(xpath="//button[text()='OK']")
	WebElement successOk_button;
	
	@FindBy(xpath="//button[@data-social-network='facebook' and text()='Connect']")
	WebElement facebookConnect_button;
	
	//Facebook Elements
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="pass")
	WebElement password;
	
	@FindBy(id="loginbutton")
	WebElement loginButton;
	
	@FindBy(xpath="//span[contains(text(),'Continue as')]")
	WebElement continueAsButton;
	
	public ZBOSocialIntegrationAndSettingsPage() {
		
	}
	public ZBOSocialIntegrationAndSettingsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isSocialIntegrationPage() {
		return ActionHelper.isElementVisible(driver, integrationAndSettings_heading);
	}
	
	public boolean disconnectFacebook() {
		boolean isFacebookDisconnected = false;
		if(ActionHelper.isElementVisible(driver, facebookConnected_image)) {
			ActionHelper.Click(driver, facebookDisConnect_button);
			if(ActionHelper.waitForElementToBeVisible(driver, successOk_button, 30)) {
				isFacebookDisconnected = ActionHelper.Click(driver, successOk_button);
			}
		}else {
			AutomationLogger.info("Facebook is already disconnected..");
			isFacebookDisconnected = true;
		}
		return isFacebookDisconnected;
	}
	
	public boolean connectToFacebook(boolean pForceConnection) {
		boolean isConnected = false;
		if(ActionHelper.isElementVisible(driver, facebookConnected_image)) {
			if(pForceConnection) {
				disconnectFacebook();
				isConnected = connectToFacebook();
			}else {
				AutomationLogger.info("Facebook is already connected..");
			}
		}else {
			isConnected = connectToFacebook();
		}
		return isConnected;
	}
	
	private boolean connectToFacebook() {
		boolean isConnected = false;
		if(ActionHelper.Click(driver, facebookConnect_button)) {
			isConnected = filloutFacebookForm();
		}
		return isConnected;
	}
	
	private boolean filloutFacebookForm() {
		boolean isSuccess = true;
		String lUsername = "z57testuser@gmail.com";
		String lPassword = "Bcsf08m020@";
		if(ActionHelper.waitForElementToBeVisible(driver, loginButton, 30)) {
			if(!ActionHelper.ClearAndType(driver, email, lUsername)) {
				return false;
			}
			if(!ActionHelper.ClearAndType(driver, password, lPassword)) {
				return false;
			}
			if(!ActionHelper.Click(driver, loginButton)) {
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, continueAsButton, 30)) {
				return false;
			}else {
				isSuccess = ActionHelper.Click(driver, continueAsButton);
				if(isSuccess && ActionHelper.isElementVisible(driver, successOk_button)) {
					isSuccess = ActionHelper.Click(driver, successOk_button);
				}
			}
		}else {
			return false;
		}
		return isSuccess;
	}
}
