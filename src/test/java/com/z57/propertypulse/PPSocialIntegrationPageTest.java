/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.alerts.pp.FacebookAuthorizationAlert;

/**
 * @author adar
 *
 */
public class PPSocialIntegrationPageTest extends PageTest{
	WebDriver driver;
	PPSocialIntegrationPage page;
	FacebookAuthorizationAlert fbAuthorizationAlert;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPSocialIntegrationPage(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pURL) {
		if(page == null){
			driver = getDriver();
			page = new PPSocialIntegrationPage(driver);
			fbAuthorizationAlert = new FacebookAuthorizationAlert(driver);
			page.setUrl(pURL);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testAuthorizeDeAuthorizeFacebookAccount() {
		String lFacebookEmail="z57testuser@gmail.com";
    	String lFacebookPassword="Bcsf08m020@";
		getPage("/account/integrations");
		//Expecting facebook is already authorized
		assertTrue(page.isSocialIntegrationPage(), "Social Integration page is not displayed..");
		assertTrue(page.clickOnDeauthorizeButton(), "Unable to click on Deauthorize button..");
		assertTrue(page.clickOnLoginWithFacebookButton(), "Unable to click on Login with Facebook button..");
		//Authorization of Facebook
		if(page.getLoginForm().isFacebookLoginForm()) {
			assertTrue(page.getLoginForm().typeFacebookEmail(lFacebookEmail), "Unable to type Email on facebook login page.");
			assertTrue(page.getLoginForm().typeFacebookPassword(lFacebookPassword), "Unable to type Password on facebook login page.");
			assertTrue(page.getLoginForm().clickOnFacebookLoginButton2(), "Unable to click on facebook login button.");
		}
		fbAuthorizationAlert.authorizeFacebook();
		assertTrue(page.clickOnLoginWithFacebookButton(), "Unable to click on Login with Facebook button..");
		assertTrue(page.isAuthorizationSuccessfull(), "Authorization of the facebook failed..");
	}

}
