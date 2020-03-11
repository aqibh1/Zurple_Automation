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
import resources.utility.AutomationLogger;
import resources.utility.DataConstants;

public class ZBOAgentsPageTest extends PageTest {
	WebDriver driver;
	ZBOAgentsPage page;
	JSONObject dataObject;
	int currentAgentsCount;
	
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
	
	public void testAgentsCount(int count) {
		assertTrue(page.verifyAgentsCount(count));
	}
	
	@Test
	public void testAgentsPageLabel() throws InterruptedException {
		AutomationLogger.startTestCase("Manage Agents");
		getPage("/agents");
		assertEquals("Manage Agents", page.verifyPageTitle());
		currentAgentsCount = page.agentsList.size();
		testAgentsCount(currentAgentsCount);
		Thread.sleep(4000);
		AutomationLogger.endTestCase();
	}
	
	
	@Test
	@Parameters({"manageAgentsDataFile"})
	public void testCreateAgents(String pDataFile) {
		AutomationLogger.startTestCase("Create Agents");
		page=null;
		getPage("/agent/create");
		addUpdateAgent(pDataFile);
		String agentURL = page.getURL();
		page=null;
		getPage("/agents");
		testAgentsCount(currentAgentsCount+1);
		page=null;
		getPage(agentURL);
		deleteAgent();
		testAgentsCount(currentAgentsCount-1);
		AutomationLogger.endTestCase();
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

		page.addAgentButton();
		page.confirmAgent();
	}
	
	private void deleteAgent() {
		page.delAgent();
		page.confirmDelAgent();
	}
}
