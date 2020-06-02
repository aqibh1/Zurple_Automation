package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
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
