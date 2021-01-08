package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.zurple.backoffice.ZBOReassignLeadAlert;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

public class ZBOLeadPageTest extends PageTest{
	WebDriver driver;
	ZBOLeadPage page;
	private JSONObject dataObject;
	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		return null;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadPage(driver);
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
	public void testSearchAndVerifyLead() {
		AutomationLogger.startTestCase("Search and Verify lead");
		getPage("/leads");
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		assertTrue(page.isLeadPage(), "Lead page is not visible..");
		assertTrue(page.isLeadExist(lLeadName), "Unable to find lead on lead page..");
		AutomationLogger.endTestCase();
		
	}
	
	@Test
	public void testVerifySortingIsWorking() {
		AutomationLogger.startTestCase("Verifying Sorting on Leads page");
		getPage("/leads");
		assertTrue(page.isLeadPage(), "Lead Page is not visible..");
		assertTrue(page.isProcessingComplete(), "Processing is not completed..");
		assertTrue(page.verifyNameSortingWorking(), "Sorting for Name column is not working...");
		assertTrue(page.verifyEmailSortingWorking(), "Sorting for Email column is not working...");
		assertTrue(page.verifySearchLocationSortingWorking(), "Sorting for Search Location column is not working...");
		assertTrue(page.verifyMaxPriceSortingWorking(), "Sorting for Max Price column is not working...");
		assertTrue(page.verifyDateCreatedSortingWorking(), "Sorting for Date Created column is not working...");
		assertTrue(page.verifyAgentSortingWorking(), "Sorting for Agent column is not working...");
//		assertTrue(page.verifyLastModifiedSortingWorking(), "Sorting for Last Modified column is not working...");
		assertTrue(page.verifyLastVisitSortingWorking(), "Sorting for Last Visit column is not working...");
		AutomationLogger.endTestCase();
	}
	
	@Test
	@Parameters({"filterDataFile"})
	public void testVerifyFilterIsWorking(String pDataFile) throws JSONException, ParseException {
		AutomationLogger.startTestCase("Verifying filters on Leads page");
		getPage("/leads");
		JSONObject jObject = getDataFile(pDataFile);
		JSONArray jArray = jObject.getJSONArray("filterNameVals");
		for(int i=0;i<jArray.length();i++) {
			applyAndVerifyFilter(jArray.getJSONObject(i).optString("key"),jArray.getJSONObject(i).optString("value"));
		}
		
	}
	
	@Test
	public void testSearchAndSelectLead() {
		AutomationLogger.startTestCase("Search and Verify lead");
		getPage("/leads");
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		assertTrue(page.isLeadPage(), "Lead page is not visible..");
		assertTrue(page.selectLead(lLeadName), "Unable to find lead on lead page..");
		String lLeadId = driver.getCurrentUrl().split("lead/")[1];
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), lLeadName, lLeadId);
		AutomationLogger.endTestCase();
		
	}
	
	@Test
	public void testAssignLeadToAgent() {
		getPage("/leads");
		ZBOReassignLeadAlert reassignlead_alert = new ZBOReassignLeadAlert(driver);
		ZBOSucessAlert successAlert = new ZBOSucessAlert(driver);
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		HashMap<String,String> agent_info_map  = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAgentsInfo);
		String lAgentName = agent_info_map.get("agent_name");
		
		assertTrue(page.isLeadPage(), "Lead page is not visible..");
		assertTrue(page.checkInputLead(lLeadName), "Unable to find lead on lead page..");
		
		assertTrue(page.selectAction("Reassign Leads"), "Unable to select resassign leads");
		assertTrue(reassignlead_alert.isReassignAlert(), "Reassign alert is not visible..");
		assertTrue(reassignlead_alert.clickAndSelectAgent(lAgentName), "Unable to select the agent");
		assertTrue(reassignlead_alert.clickOnReassignLeadButton(), "Unable to click on reassign button..");
		assertTrue(successAlert.clickOnAssignButton(), "Unable to click on assign button..");
		assertTrue(successAlert.isSuccessMessageVisible(), "Success message is not visible..");
		assertTrue(successAlert.clickOnOkButton(), "Unable to click on OK button..");		
	}
	
	@Test
	@Parameters({"column"})
	public void testAssignLeadToAgentFromCRMPage(@Optional String pDataFile) {
		getPage("/leads/crm");
		ZBOReassignLeadAlert reassignlead_alert = new ZBOReassignLeadAlert(driver);
		ZBOSucessAlert successAlert = new ZBOSucessAlert(driver);
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		HashMap<String,String> agent_info_map  = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAgentsInfo);
		String lAgentName = agent_info_map.get("agent_name");
		ZBOLeadCRMPage leadCrmPage = new ZBOLeadCRMPage(driver);
		assertTrue(page.isLeadPage(), "Lead page is not visible..");
		assertTrue(leadCrmPage.searchAndSelectLead(lLeadName), "Unable to find lead on lead page..");
		if(pDataFile!=null) {
			assertTrue(leadCrmPage.clickOnAgentAssignmentButton(), "Unable to click on agent assignment button..");
		}else {
			assertTrue(page.selectAction("Reassign Leads"), "Unable to select resassign leads");
		}
		assertTrue(reassignlead_alert.isReassignAlert(), "Reassign alert is not visible..");
		assertTrue(reassignlead_alert.clickAndSelectAgent(lAgentName), "Unable to select the agent");
		assertTrue(reassignlead_alert.clickOnReassignLeadButton(), "Unable to click on reassign button..");
		assertTrue(successAlert.clickOnAssignButton(), "Unable to click on assign button..");
		assertTrue(successAlert.isSuccessMessageVisible(), "Success message is not visible..");
		assertTrue(successAlert.clickOnOkButton(), "Unable to click on OK button..");		
	}
	
	@Test
	public void testVerifyLeadAssignment() {
		page = null;
		getPage("/leads");
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		HashMap<String,String> agent_info_map  = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAgentsInfo);
		String lAgentName = agent_info_map.get("agent_name");
		String lLeadCount = agent_info_map.get("agent_lead_count");
		
		//Verification from Leads Page
		assertTrue(page.isLeadPage(), "Lead page is not visible..");
		assertTrue(page.checkInputLead(lLeadName), "Unable to find lead on lead page..");
		assertTrue(page.isLeadAssignedToAgent(lAgentName), "Agent is not assigned to lead on Leads Page");
		
		//Verification from leads detail page
		page=null;
		getPage("/leads");
		assertTrue(page.isLeadPage(), "Lead page is not visible..");
		assertTrue(page.clickOnLead(lLeadName), "Unable to find lead on lead page..");
		ZBOLeadDetailPage lead_detail_page = new ZBOLeadDetailPage(driver);
		assertTrue(lead_detail_page.isLeadDetailPage(), "Lead detail page is not visible..");
		assertTrue(lead_detail_page.verifyLeadAssignedToAgent(lAgentName), "Agent not assigned to lead.."+lAgentName);
		
		//Verification from CRM page
		page=null;
		getPage("/leads/crm");
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not visible..");
		assertTrue(leadCRMPage.searchLead(lLeadName), "Unable to find lead on lead page..");
		assertTrue(leadCRMPage.verifyAgentName(lAgentName), "Agent not assigned to lead on CRM .."+lAgentName);
		
		//Verification from Manage agents page
		page = null;
		getPage("/agents");
		ZBOAgentsPage manageAgentsPage = new ZBOAgentsPage(driver);
		assertTrue(manageAgentsPage.verifyPageTitle(), "Manage agents page is not visible..");
		assertTrue(!manageAgentsPage.verifyAgentName(lAgentName).equalsIgnoreCase(lLeadCount), "Lead count has not changed..");

	}
	@Test
	public void testVerifyAndSearchLead() {
		page = null;
		getPage("/leads");
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);

		//Verification from CRM page
		page=null;
		getPage("/leads/crm");
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not visible..");
		assertTrue(leadCRMPage.searchLead(lLeadName), "Unable to find lead on lead page..");
		
	}
	@Test
	public void testMarkAllLeadAsInActive() {
		page=null;
		getPage("/leads/crm");
		ZBOLeadCRMPage crm = new ZBOLeadCRMPage(driver);
		ZBOSucessAlert successAlert = new ZBOSucessAlert(driver);
		assertTrue(crm.searchLeadByEmailForBulkUpdate("mailinator.com"),"Unable to search lead");
		assertTrue(page.selectAction("Update Lead Status"), "Unable to select update status from Actions dropdown");
		assertTrue(page.selectLeadProspect("Inactive - Stop All Communications"), "Unable to select the status -> Inactive");
		assertTrue(page.updateLeadProspect(), "Unable to update lead status");
		assertTrue(successAlert.clickOnPermanentStatusUpdate(), "Unable to click on Temporary button..");
		ActionHelper.RefreshPage(driver);
	}
	private boolean applyAndVerifyFilter(String pFilterName, String pFilterValue) throws ParseException {
		boolean isSuccess = false;
		assertTrue(page.isLeadPage(),"Lead Page is not found..");
		assertTrue(page.clickAndSelectFilterName(pFilterName),"Unable to select the filter type "+pFilterName);
		ActionHelper.staticWait(30);
		assertTrue(page.clickAndSelectFilterValue(pFilterValue),"Unable to select the filter value "+pFilterValue);
		assertTrue(page.clickOnSearchButton(),"Unable to click on search button..");
		ModuleCommonCache.updateCacheForModuleObject("LeadPage","LeadPage.URL", EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url"));

		return page.verifyFilter(pFilterName,pFilterValue);
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadsHotBehavior(String pDataFile) {
		page = null;
		getPage("/leads");
		dataObject = getDataFile(pDataFile);
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName).toString();
	
		String lAlertType = dataObject.optString("alert_type");
		//Verification from CRM page
		page=null;
		getPage("/leads/crm");
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not visible..");
		assertTrue(leadCRMPage.searchLead(lLeadName), "Unable to find lead on lead page..");

		switch(lAlertType) {
		case "High Activity":
			assertTrue(leadCRMPage.isBrowsingHotBehaviorVisible(), "Browsing Hot Behavior is not updated..");
			break;
		case "High Return":
			assertTrue(leadCRMPage.isReturnHotBehaviorVisible(), "Return Hot Behavior is not updated..");
			break;
		default:
			break;
		}


	}

}
