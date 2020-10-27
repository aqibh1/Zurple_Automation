/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.zurple.my.PageTest;
import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOLoginPageTest extends PageTest{
	
	ZBOLoginPage page;
	private WebDriver driver;
	private JSONObject dataObject;
	String ld_zurpleUsername = "";
	String ld_zurplePassword ="";
	String lZurpleUserName = "";
	String lZurplePassword = "";
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test(priority=-1,groups="testBackOfficeLogin")
	@Parameters({"dataFile"})
	public void testBackOfficeLogin(@Optional String pDataFile) {
		getPage();
		dataObject = getDataFile(pDataFile);
		setUserNamePassword();
		if(page.isLoginPage()) {
			//		assertTrue(page.isLoginPage(),"Zurple Back office login page is not visible..");
			assertTrue(page.typeUserName(lZurpleUserName),"Unable to type the user name");
			assertTrue(page.typePassword(lZurplePassword),"Unable to type the user name");
			assertTrue(page.isForgotPasswordLinkExists(),"Forgot password link doesn't exist on login page..");
			assertTrue(page.clickLoginButton(),"Unable to click on Login button..");
			assertTrue(page.isLoginSuccessful(),"Login Failed..");
		}else {
			assertTrue(page.isLoginSuccessful(),"Login is not successful..");
		}
		AutomationLogger.endTestCase();
	}

	@Test(priority=-1,groups="testBackOfficeAPILogin")
	public void testBackOfficeAPILogin() {
		getPage();	
		String lZurpleUserName = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user");
		String lZurplePassword =EnvironmentFactory.configReader.getPropertyByName("zurple_bo_pass");
		if(page.isLoginPage()) {
			//		assertTrue(page.isLoginPage(),"Zurple Back office login page is not visible..");
			assertTrue(page.typeUserName(lZurpleUserName),"Unable to type the user name");
			assertTrue(page.typePassword(lZurplePassword),"Unable to type the user name");
			assertTrue(page.isForgotPasswordLinkExists(),"Forgot password link doesn't exist on login page..");
			assertTrue(page.clickLoginButton(),"Unable to click on Login button..");
			assertTrue(page.isLoginSuccessful(),"Login Failed..");
		}else {
			assertTrue(page.isLoginSuccessful(),"Login is not successful..");
		}
		if(page.isLoginSuccessful()) {
			String lCookies = "";
//			Cookie cks = getDriver().manage().getCookies();
			for(Cookie ck : driver.manage().getCookies())                           
            {         
				String lCookie = ck.getName()+"="+ck.getValue()+";";
				lCookies = lCookies+lCookie;
            }  
			ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.Cookie, lCookies);
		}

		AutomationLogger.endTestCase();
	}
	private void setUserNamePassword() {
		if(getIsProd()){
			ld_zurpleUsername = dataObject.optString("zurple_username");
			ld_zurplePassword = dataObject.optString("zurple_password");
		}else {
			ld_zurpleUsername = dataObject.optString("zurple_username_stage");
			ld_zurplePassword = dataObject.optString("zurple_password_stage");
		}
		
		if(ld_zurpleUsername.isEmpty()) {
			lZurpleUserName = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user");
			lZurplePassword =EnvironmentFactory.configReader.getPropertyByName("zurple_bo_pass");
		}else {
			lZurpleUserName = ld_zurpleUsername;
			lZurplePassword = ld_zurplePassword;
		}	
	}
}
