/**
 * 
 */
package com.torchx;

import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import resources.AbstractPage;
import resources.EnvironmentFactory;

/**
 * @author adar
 *
 */
public class TXBOLoginPageTest extends PageTest{

	TXBOLoginPage page;
	private WebDriver driver;
	JSONObject dataObject;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new TXBOLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	@Test
	@Parameters({"invalid_credentials"})
	public void testTorchXBackOfficeLogin(@Optional String pIncorrectPassword) {
		getPage();
		boolean lIncorrectPassword = false;
		if(pIncorrectPassword!=null) {
			lIncorrectPassword = true;
		}
		String lTorchXUserName = EnvironmentFactory.configReader.getPropertyByName("torchx_bo_user");
		String lTorchXPassword =EnvironmentFactory.configReader.getPropertyByName("torchx_bo_pass");
		
		if(lIncorrectPassword) {
			 lTorchXUserName = "aqib.dar@mailinator.com";
			 lTorchXPassword ="Messi$10";
		}

		assertTrue(page.isLoginPage(),"TorchX Back office login page is not visible..");
		assertTrue(page.isTorchXLogoVisible(),"TorchX logo is not visible..");
		assertTrue(page.typeUserName(lTorchXUserName),"Unable to type the user name");
		assertTrue(page.typePassword(lTorchXPassword),"Unable to type the user name");
		assertTrue(page.isForgotPasswordLinkExists(),"Forgot password link doesn't exist on login page..");
		assertTrue(page.clickLoginButton(),"Unable to click on Login button..");
		
		boolean isLoginSuccessful = page.isLoginSuccessful();
		
		if(lIncorrectPassword && isLoginSuccessful) {
			assertTrue(false, "Login successfully with incorrect credentials..");
		}else if(lIncorrectPassword && !isLoginSuccessful) {
			assertTrue(true, "Login was unsuccessfull with incorrect credentials..");
		}else if(!lIncorrectPassword && isLoginSuccessful) {
			assertTrue(true, "Login was successful with correct credentials..");
			assertTrue(driver.getCurrentUrl().contains("dashboard"), "URL is not changed to Dashboard..");
		}else {
			assertTrue(false, "Login was unsuccessful with correct credentials..");
		}
		
	}
	

}
