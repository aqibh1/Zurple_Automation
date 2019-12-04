/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.z57.propertypulse.PPLoginPage;
import com.zurple.my.PageTest;

import resources.EnvironmentFactory;
import resources.utility.AutomationLogger;

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

		}
		return page;
	}
	public PPLoginPage getPage(String pUrl){
		if(page == null){
			driver = getDriver();
			page = new PPLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);

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
		if(page.isLoginPageVisible()) {
			assertTrue(page.isLoginPageVisible(), "Login Page is not visible");
			assertTrue(page.typeUsername(lPPUserName), "Unable to type username on login page");
			assertTrue(page.typePassword(lPPPassword), "Unable to type password on login page");
			assertTrue(page.clickOnLoginButton(), "Unable to click on Login button");
			assertTrue(page.isLoginSuccessful(lPPUserName), "Login Failed.. Incorrect username/password..");
		}else {
			assertTrue(page.isPPLogoVisible(), "User is not logged in..");
			AutomationLogger.info("User is already logged in..");
		}
		
	}
	
	@Test(priority=10)
	void testDisableAdsFromAdmin() {
		page=null;
		getPage("/admin/social?account=44276&ad_id=&num_billed=&ad_type=0&ad_billed=0&ui_step=4&ad_state=live&api_status=PAUSED&test_ads=0&date_start=&date_end=&limit=100");
		assertTrue(new PPAdminAds(driver).disableAllAds(), "Unable to disable all the ads");;
	}
}
