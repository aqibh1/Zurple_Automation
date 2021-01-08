package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.PageTest;
import com.zurple.website.ZWRegisterUserPageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;

public class ZBOEditDistributionPageTest extends PageTest{
	
	private ZBOEditDistributionPage page;
	private WebDriver driver;
	ZBOAgentsPage agentPageObject;
	public String lAgentId;
	String IAgentLeadCount;
	String lAgentName;
	ZWRegisterUserPageTest register = new ZWRegisterUserPageTest();
	ZBOLoginPageTest login = new ZBOLoginPageTest();
	
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
	
	@Test(groups="testVerifyAgentInfo")
	public void testVerifyAgentInfo() {
		getPage("/agents");
		agentPageObject = new ZBOAgentsPage(driver);
		assertTrue(agentPageObject.verifyPageTitle(), "Manage agents page title is not visible..");
		HashMap<String,String> agentInfo = agentPageObject.getAgentNameAndLeadCount();
		lAgentId = agentInfo.get("agent_url").split("admin_id/")[1];
		IAgentLeadCount = agentInfo.get("agent_lead_count");
		lAgentName = agentInfo.get("agent_name");
	}
	
	@Test(dependsOnGroups="testVerifyAgentInfo", groups="testVerifyAndSelectAgentForDistribution")
	public void testVerifyAndSelectAgentForDistribution() {
		String siteURL=EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url");
		if(!getIsProd()) {
			siteURL = siteURL.split("stage01.")[1];
		}else {
			siteURL = siteURL.split("www.")[1];
		}
		page=null;
		getPage("/agents/distribution");
		assertEquals(page.editDistributionPageTitle(),"Edit Distribution: "+siteURL);
		if(page.clickOnByPercentage()==false) {
			page=null;
			getPage("/agents/distribution");
			page.clickOnByPercentage();
		}
		assertTrue(page.typeDistributionPercentage(lAgentId,"100"),"Unable to provide percentage to agent");
		assertTrue(page.saveEditedDistribution(),"Unable to save edited distribution");
		assertEquals(page.confirmationModalTitle(),"Update Distribution Rules?");
		assertTrue(page.confrimUpdate(),"Unable to confirm update");
	}
	
	@Test(dependsOnGroups="testVerifyAndSelectAgentForDistribution", groups="testRegister")
	@Parameters({"registerUserDataFile"})
	public void testRegister(String pDataFile) {
		new ZWRegisterUserPageTest().testRegisterUser(pDataFile);
	}
	
	@Test(dependsOnGroups="testRegister")
	public void testAgentCountAfterLeadRegister() {
		page=null;
		getPage("/agents");
		assertTrue(agentPageObject.verifyPageTitle(), "Manage agents page title is not visible..");
		assertTrue(!agentPageObject.verifyAgentName(lAgentName).equalsIgnoreCase(IAgentLeadCount), "Lead count has not changed..");
		page=null;
		getPage("/agents/distribution");
		assertTrue(page.clickOnToMe(),"Unable to click on by all radio button");
	}
}
