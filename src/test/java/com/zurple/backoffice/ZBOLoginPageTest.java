/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;
import com.zurple.website.ZWHomeSearchPage;

import resources.AbstractPage;
import resources.ConfigReader;
import resources.EnvironmentFactory;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOLoginPageTest extends PageTest{
	
	ZBOLoginPage page;
	private WebDriver driver;
	
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
	
	@Test
	public void testBackOfficeLogin() {
		getPage();
		
		AutomationLogger.startTestCase("Zurple Back Office Login");
		
		String lZurpleUserName = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user");
		String lZurplePassword =EnvironmentFactory.configReader.getPropertyByName("zurple_bo_pass");
		
		assertTrue(page.isLoginPage(),"Zurple Back office login page is not visible..");
		assertTrue(page.typeUserName(lZurpleUserName),"Unable to type the user name");
		assertTrue(page.typePassword(lZurplePassword),"Unable to type the user name");
		assertTrue(page.isForgotPasswordLinkExists(),"Forgot password link doesn't exist on login page..");
		assertTrue(page.clickLoginButton(),"Unable to click on Login button..");
		assertTrue(page.isLoginSuccessful(),"Login Failed..");
		
		AutomationLogger.endTestCase();
		
	}

}
