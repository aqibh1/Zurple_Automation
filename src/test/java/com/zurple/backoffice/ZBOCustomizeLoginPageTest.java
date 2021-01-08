/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

/**
 * @author adar
 *
 */
public class ZBOCustomizeLoginPageTest extends PageTest{
	
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
	public void testAgentBackOfficeLogin() {
		getPage();
		HashMap<String,String> agent_info_map  = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAgentsInfo);
		String lZurpleUserName = agent_info_map.get("agent_email");
		String lZurplePassword ="12345";
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
	}

}
