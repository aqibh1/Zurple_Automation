/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.EnvironmentFactory;

/**
 * @author adar
 *
 */
public class LoginPageTest extends PageTest{
	WebDriver driver;
	private PPLoginPage page;

	public PPLoginPage getPage(){
		if(page == null){
			driver = getDriver();
			page = new PPLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
//			page.setUrl("");
			

		}
		return page;
	}

	public void clearPage(){
		page=null;
	}
	
	@Test
	public void testPPLogin() {
		getPage();
		//Fetch this from config file
		String lPPUserName = EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email");
		String lPPPassword =EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_password");
		assertTrue(page.isLoginPageVisible(), "Login Page is not visible");
		assertTrue(page.typeUsername(lPPUserName), "Unable to type username on login page");
		assertTrue(page.typePassword(lPPPassword), "Unable to type password on login page");
		assertTrue(page.clickOnLoginButton(), "Unable to click on Login button");
		assertTrue(page.isLoginSuccessful(lPPUserName), "Login Failed.. Incorrect username/password..");
		
	}
}
