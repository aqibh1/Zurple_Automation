package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.DataConstants;

public class ZBOAgentsPageTest extends PageTest {
	WebDriver driver;
	ZBOAgentsPage page;
	JSONObject dataObject;
	int currentAgentsCount = 0;
	String agentURL = "";
	int count=0;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAgentsPage(driver);
			page.setUrl("");
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAgentsPage(driver);
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
	public void testAgentsPageLabel() {
		AutomationLogger.startTestCase("Manage Agent");
		getPage("/agents");
		assertTrue(page.verifyPageTitle(), "Agents page title not found..");
		AutomationLogger.endTestCase();
	}
	
	@Test
	public void testAgentsListCount() {
		AutomationLogger.startTestCase("Create Agents");
		ActionHelper.staticWait(10);
		int lAgentCount = page.getAgentsCount();
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.Zurple_Agents_Count, lAgentCount);
		count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.Zurple_Agents_Count);
		assertTrue(page.verifyAgentsCount(count));
		AutomationLogger.endTestCase();
	}
	
	@Test
	@Parameters({"manageAgentsDataFile"})
	public void testCreateAgents(String pDataFile) {
		AutomationLogger.startTestCase("Create Agents");
		
		page=null;
		getPage("/agent/create");
		addUpdateAgent(pDataFile);
		agentURL = page.getURL();
		
		AutomationLogger.endTestCase();
	}
	
	@Test
	public void testCountAfterAddAgent() {
		page=null;
		getPage("/agents");
		ActionHelper.staticWait(10);
		assertTrue(page.verifyAgentsCount(count+1));
	}
	
	@Test
	private void testDelAgent() {
		AutomationLogger.startTestCase("Create Agents");	
		driver.navigate().to(agentURL);
		deleteAgent();
		ActionHelper.staticWait(5);
		AutomationLogger.endTestCase();
	}
	
	@Test
	public void testCountAfterDelAgent() {
		page=null;
		getPage("/agents");
		ActionHelper.staticWait(10);
		assertTrue(page.verifyAgentsCount(count));
	}
	
	@Test
	public void testVerifyLeadCount() {
		getPage("/agents");
		assertTrue(page.verifyPageTitle(), "Manage agents page title is not visible..");
		//Fetch the hash map of agent info
		HashMap<String,String>  agent_info = page.getAgentNameAndLeadCount();
		driver.navigate().to(agent_info.get("agent_url"));
		ZBOEditAgentPage editAgentPage = new ZBOEditAgentPage(driver);
		assertTrue(editAgentPage.isEditAgentPage(), "Edit Agent Page is not visible..");
		String lAgentEmail = editAgentPage.getAgentEmail();
		agent_info.put("agent_email", lAgentEmail);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleAgentsInfo, agent_info);

	}
	@Test
	public void testEditAgentProfile() {
		getPage("/agents");
		String lAgent_lastname = updateName("");
		assertTrue(page.verifyPageTitle(), "Manage agents page title is not visible..");
		//Fetch the hash map of agent info
		HashMap<String,String>  agent_info = page.getAgentNameAndLeadCount();
		driver.navigate().to(agent_info.get("agent_url"));
		ZBOEditAgentPage editAgentPage = new ZBOEditAgentPage(driver);
		assertTrue(editAgentPage.isEditAgentPage(), "Edit Agent Page is not visible..");
		assertTrue(editAgentPage.typeAgentLastName(lAgent_lastname), "Unable to type agent last name..");
		assertTrue(editAgentPage.clickOnSaveButton(), "Unable to click on save button....");
		assertTrue(editAgentPage.isAgentInfoUpdated(), "Agent Info updated successfully message is not displayed....");
		String lAgentNameToVerify = agent_info.get("agent_name").split(" ")[0];
		lAgentNameToVerify = lAgentNameToVerify+" "+lAgent_lastname;
		page = null;
		getPage("/agents");
		assertTrue(!page.verifyAgentName(lAgentNameToVerify).isEmpty(), "Agent updated successfully");
	}
	
	@Test
	public void testEditSitOwnerProfile() {
		page=null;
		getPage("/agent/edit/admin_id/"+EnvironmentFactory.configReader.getPropertyByName("zurple_bo_default_agent_id"));
		String lAgentPhone = updateName("");
		lAgentPhone = lAgentPhone.substring(1,11);
		//Fetch the hash map of agent info
		ZBOEditAgentPage editAgentPage = new ZBOEditAgentPage(driver);
		assertTrue(editAgentPage.isEditAgentPage(), "Edit Agent Page is not visible..");
		assertTrue(editAgentPage.updateAgentOfficePhone(lAgentPhone), "Edit Agent Page is not visible..");
		assertTrue(editAgentPage.clickOnSaveButton(), "Unable to click on save button....");
		assertTrue(editAgentPage.isAgentInfoUpdated(), "Agent Info updated successfully message is not displayed....");
		assertTrue(editAgentPage.verifyOfficePhone(lAgentPhone), "Edit Agent Page is not visible..");
	}
	
	private void addUpdateAgent(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		
		String fName = dataObject.optString(DataConstants.FirstName);
		assertTrue(page.typeAgentFirstName(updateName(fName).replaceAll("\\s+","")), "Unable to type Agent fName..");
		
		String lName = dataObject.optString(DataConstants.LastName);
		assertTrue(page.typeAgentLastName(updateName(lName).replaceAll("\\s+","")), "Unable to type Agent lName..");
		
		String agentEmail = dataObject.optString(DataConstants.Email);
		assertTrue(page.typeAgentEmail(updateEmail(agentEmail).replaceAll("\\s+","")), "Unable to type Agent email..");
		
		String agentPass = dataObject.optString(DataConstants.Password);
		assertTrue(page.typeAgentPassword(agentPass), "Unable to type Agent password..");
		
		String confirmPass = dataObject.optString(DataConstants.ConfirmPassword);
		assertTrue(page.typeAgentConfirmPassword(confirmPass), "Unable to type Agent confirm password..");

		assertTrue(page.addAgentButton(), "Unable to click add agent button..");
		assertTrue(page.confirmAgent(), "Unable to confirm agent from confirmation modal..");
	}
	
	private void deleteAgent() {
		page.delAgent();
		ActionHelper.staticWait(5);
		page.confirmDelAgent();
	}
}
