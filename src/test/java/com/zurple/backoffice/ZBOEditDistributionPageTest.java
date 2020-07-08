package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;

public class ZBOEditDistributionPageTest extends PageTest{
	
	private ZBOEditDistributionPage page;
	private WebDriver driver;
	ZBOAgentsPage agentPageObject;
	public String lAgentId;
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
		if(agentPageObject==null) {
			driver = getDriver();
			agentPageObject = new ZBOAgentsPage(driver);
			agentPageObject.setUrl("");
			agentPageObject.setDriver(driver);
		}
		getPage("/agents");
		assertTrue(agentPageObject.verifyPageTitle(), "Manage agents page title is not visible..");
		HashMap<String,String> agentInfo = agentPageObject.getAgentNameAndLeadCount();
		lAgentId = agentInfo.get("agent_url").split("admin_id/")[1];
		String IAgentLeadCount = agentInfo.get("agent_lead_count");
		page=null;
		getPage("/agents/distribution");
		assertEquals(page.editDistributionPageTitle(),"Edit Distribution: zengtest6.us");
	}
	
	@Test
	public void testSelectAgentForDistribution() {
		assertTrue(page.clickOnByPercentage(),"Unable to click on by percentage radio button");
		assertTrue(page.typeDistributionPercentage(lAgentId,"100"),"Unable to provide percentage to agent");
		assertTrue(page.saveEditedDistribution(),"Unable to save edited distribution");
		assertEquals(page.confirmationModalTitle(),"Update Distribution Rules?");
		assertTrue(page.confrimUpdate(),"Unable to confirm update");
	}
}
