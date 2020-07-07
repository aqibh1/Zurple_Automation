package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

public class ZBOEditDistributionPageTest extends PageTest{
	
	private ZBOEditDistributionPage page;
	private WebDriver driver;
	ZBOLoginPage zbo = new ZBOLoginPage(driver);
	
	public AbstractPage getPage() {
    	page=null;
    	if(page == null){
        	driver = getDriver();
			page = new ZBOEditDistributionPage(driver);
			page.setUrl("");
			page.setDriver(driver);
        }
        return page;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBOEditDistributionPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
        }
        return page;
    }

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
	}
	
	@Test
	public void testVerifyDistributionPage() {
		getPage("/agents/distribution");
		assertEquals(page.editDistributionPageTitle(),"Edit Distribution: zengtest6.us");
	}
	
	@Test
	public void testSelectAgentForDistribution() {
		assertTrue(page.clickOnByPercentage(),"Unable to click on by percentage radio button");
		assertTrue(page.typeDistributionPercentage("100"),"Unable to provide percentage to agent");
		assertTrue(page.saveEditedDistribution(),"Unable to save edited distribution");
		assertEquals(page.confirmationModalTitle(),"Update Distribution Rules?");
		assertTrue(page.confrimUpdate(),"Unable to confirm update");
		testAgentBackOfficeLogin();
	}
	
	public void testAgentBackOfficeLogin() {
		getPage();
		String agentEmail = "automation_agent1@mailinator.com";
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleAgentsInfo, agentEmail);
		HashMap<String,String> agent_info_map  = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAgentsInfo);
		String lZurpleUserName = agent_info_map.get("agent_email");
		String lZurplePassword ="12345";
		if(zbo.isLoginPage()) {
			//		assertTrue(page.isLoginPage(),"Zurple Back office login page is not visible..");
			assertTrue(zbo.typeUserName(lZurpleUserName),"Unable to type the user name");
			assertTrue(zbo.typePassword(lZurplePassword),"Unable to type the user name");
			assertTrue(zbo.isForgotPasswordLinkExists(),"Forgot password link doesn't exist on login page..");
			assertTrue(zbo.clickLoginButton(),"Unable to click on Login button..");
			assertTrue(zbo.isLoginSuccessful(),"Login Failed..");
		}else {
			assertTrue(zbo.isLoginSuccessful(),"Login is not successful..");
		}			
	}
	
}
