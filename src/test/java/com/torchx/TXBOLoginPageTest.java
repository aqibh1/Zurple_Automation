/**
 * 
 */
package com.torchx;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

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
		assertTrue(page.verifyTorchXYellowIsDisplayed(), "Yellow TorchX brancding color is not correct in footer of the page..");
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
	
	@Test
	public void testVerifyForgotPassword() {
		getPage();
		String lEmailToResetPassword = "aqib.dar@z57.com";
	
		assertTrue(page.isLoginPage(),"TorchX Back office login page is not visible..");
		assertTrue(page.isTorchXLogoVisible(),"TorchX logo is not visible..");
		assertTrue(page.verifyTorchXYellowIsDisplayed(), "Yellow TorchX brancding color is not correct in footer of the page..");
		assertTrue(page.clickOnForgotPasswordLink(), "Unable to click on Forgot password link button..");

		TXBOForgotPasswordPage forgotPasswordPage = new TXBOForgotPasswordPage(driver);
		assertTrue(forgotPasswordPage.isForgotPasswordPage(), "Forgot password page is not visible..");
		assertTrue(forgotPasswordPage.verifyTorchXYellowIsDisplayed(), "Yellow TorchX brancding color is not correct in footer of the forgot password page..");
		assertTrue(forgotPasswordPage.isCorrectPageURL(), "URL is not correct..");
		assertTrue(forgotPasswordPage.typeEmailAddress(lEmailToResetPassword), "URL is not correct..");
		assertTrue(forgotPasswordPage.clickOnSubmitButton(), "Unable to click on submit button..");
		assertTrue(forgotPasswordPage.isEmailSent(), "Forgot password Email is not sent..");
		assertTrue(forgotPasswordPage.verifyTorchXYellowIsDisplayed(), "Yellow TorchX brancding color is not correct in footer of the forgot password page..");
		assertTrue(forgotPasswordPage.isUrlChangedToSubmit(), "URL is not changed to submit..");

	}
	

}
