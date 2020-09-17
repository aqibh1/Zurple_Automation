package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.backoffice.marketing.ZBOCampaignPage;
import com.zurple.my.PageTest;
import com.zurple.website.ZWAccountSettingsPage;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.DataConstants;
import resources.utility.Mailinator;

public class ZBOLeadDetailPageTest extends PageTest{

	private WebDriver driver;
	private ZBOLeadDetailPage page;
	private JSONObject dataObject;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadDetailPage(driver);
			page.setUrl("");
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadDetailPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	
	@Override
	public void clearPage() {
	
	}
	
	@Test
	public void testVerifyLeadPropTracking() {
		AutomationLogger.startTestCase("Verify lead property tracking");
		getPage();
		String lLeadEmail = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.RegisterFormLeadEmail);
		String lLeadId = ModuleCommonCache.getElement(getThreadId().toString(), lLeadEmail);
		String lLeadAddress = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.ListingsAddress);
		
//		String lUpdatedUrl = driver.getCurrentUrl().replace("dashboard", "lead/"+lLeadId+"");
		String lUpdatedUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+lLeadId;
		driver.navigate().to(lUpdatedUrl);
		
		assertTrue(page.isLeadDetailPage(),"Lead Detail page is not opened..");
		assertTrue(page.isPropertyTracked(lLeadAddress),"Property is not tracked under lead details page.."+lLeadAddress);
		
		AutomationLogger.endTestCase();
	}

	@Test
	public void testVerifyLeadIsAddedInBackOffice() {
		AutomationLogger.startTestCase("Verify lead is added in Back Office");
		getPage();
		String lLeadEmail = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.ZurpleLeadEmail);
		String lLeadId = ModuleCommonCache.getElement(getThreadId().toString(), lLeadEmail);
//		String lUpdatedUrl = "";
		String lUpdatedUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+lLeadId;

//		if(driver.getCurrentUrl().contains("dashboard")) {
//			lUpdatedUrl = driver.getCurrentUrl().replace("dashboard", "lead/"+lLeadId+"");
//		}else {
//			lUpdatedUrl = driver.getCurrentUrl().replace("leads", "lead/"+lLeadId+"");
//		}
		 
		driver.navigate().to(lUpdatedUrl);
		
		assertTrue(page.isLeadDetailPage(),"Lead Detail page is not opened..");
		assertTrue(page.isLeadNameExist(ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.ZurpleLeadName)),"Lead name not found in lead details page..");
		
		AutomationLogger.endTestCase();
	}
	
	@Test
	@Parameters({"userSettings"})
	public void testVerifySettingsInNotes(String pDataFile) {
		AutomationLogger.startTestCase("Verify Settings in Notes");
		getPage();
		page=null;
		String lLead = ModuleCommonCache.getElement(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId);
		getPage("/lead/"+lLead);
		dataObject = getDataFile(pDataFile);
		
		String lStreet = dataObject.optString(DataConstants.Street);
		String lCity = dataObject.optString(DataConstants.City);
		String lState = dataObject.optString(DataConstants.State);
		String lZip = dataObject.optString(DataConstants.Zip);     
		String lBed = dataObject.optString(DataConstants.Beds);
		String lBath = dataObject.optString(DataConstants.Baths);
		String lSqFeet = dataObject.optString(DataConstants.Squarefeet);
		
		assertTrue(page.isLeadDetailPage(),"Lead Detail page is not opened..");
		assertTrue(page.verifyLeadAddress(lStreet, lCity, lState, lZip),"Unable to verify lead address..");
		assertTrue(page.verifyProp("Beds", lBed.replace("+", "")), "Unable to verify Bed count from title..");
		assertTrue(page.verifyProp("Baths", lBath.replace("+", "")), "Unable to verify Baths count from title..");
		assertTrue(page.verifyProp("Sq. Ft.", lSqFeet), "Unable to verify Square Feet from title..");
		
		//Verification from notes
		String lMinPrice = dataObject.optString(DataConstants.MinPrice).replace(",", "").replace("$", "");
		String lMaxPrice = dataObject.optString(DataConstants.MaxPrice).replace(",", "").replace("$", "");
		assertTrue(page.verifyMinPrice(lMinPrice), "Unable to verify minimum price in Notes..");
		assertTrue(page.verifyMaxPrice(lMaxPrice), "Unable to verify maximum price in Notes..");
		assertTrue(page.verifyMinBeds(dataObject.optString(DataConstants.Beds_Criteria)), "Unable to verify Beds in Notes..");
		assertTrue(page.verifyMinBathrooms(dataObject.optString(DataConstants.Baths_Criteria)), "Unable to verify Baths in Notes..");
		assertTrue(page.verifyMinSqFeet(dataObject.optString(DataConstants.Squarefeet_Criteria)), "Unable to verify Square Feet in Notes..");
		assertTrue(page.verifyLotSize(dataObject.optString(DataConstants.LotSize)), "Unable to verify lot size in Notes..");
		assertTrue(page.verifyNeighborhood(dataObject.optString(DataConstants.Neighborhood)), "Unable to verify neighborhood in Notes..");
//		assertTrue(page.verifySchoolDistrict(dataObject.optString(DataConstants.SchoolDistrict)), "Unable to verify School District in Notes..");
		assertTrue(page.verifyZipCode(dataObject.optString(DataConstants.Zip_Criteria)), "Unable to verify zip code in Notes..");
		assertTrue(page.verifyCounty(dataObject.optString(DataConstants.County)), "Unable to verify zip code in Notes..");
		assertTrue(page.verifyFeatures(dataObject.optString(DataConstants.Features)), "Unable to verify features in Notes..");
		assertTrue(page.verifyPropView(dataObject.optString(DataConstants.Views)), "Unable to verify views in Notes..");
		assertTrue(page.verifyPropStyle(dataObject.optString(DataConstants.Style)), "Unable to verify style in Notes..");

		AutomationLogger.endTestCase();
		
	}
	
	@Test
	@Parameters({"addLeadData"})
	public void testVerifyLeadDetails(String pDataFile) {
		AutomationLogger.startTestCase("Verify Lead Details");
		getPage();
		dataObject = getDataFile(pDataFile);
		assertTrue(page.isLeadDetailPage(),"Lead Detail page is not opened..");
		assertTrue(page.isLeadNameExist(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName)),"Unable to verify lead name..");
		assertTrue(page.verifyLeadEmail(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail)),"Unable to verify lead email..");
		if(!dataObject.optString("city_criteria").isEmpty()) {
			assertTrue(page.verifyEmailPreferences("Location", dataObject.optString("city_criteria")),"Unable to verify email preferences.."+dataObject.optString("city_criteria"));
		}
		if(!dataObject.optString("zip_criteria").isEmpty()) {
			assertTrue(page.verifyEmailPreferences("Location", dataObject.optString("city_criteria")),"Unable to verify email preferences.."+dataObject.optString("city_criteria"));
		}
		boolean isWelcomeEmail = !dataObject.optString("welcome_email").isEmpty()?true:false;
		if(isWelcomeEmail) {
			if(page.isEmailVerified()) {
				assertTrue(page.isWelcomeEmailSent(), "Unable to send welcome email..");
			}
		}
		AutomationLogger.endTestCase();

	}
	
	@Test(priority = 100)
	public void testVerifyValidEmail() {
		AutomationLogger.startTestCase("Verify lead email");
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
		getPage("/lead/"+lLeadId);
		assertTrue(page.isEmailVerified(), "Email is not verified..");
		AutomationLogger.endTestCase();
	}
	
	@Test(priority = 150 , dependsOnMethods = {"testVerifyValidEmail"})
	public void testVerifyAlerts() {
		AutomationLogger.startTestCase("Verify Alerts");
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
		if(!getIsProd()) {
			//Process email queue
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processAlertQueue();
			new ZAProcessEmailQueuesPage(driver).processImmediateResponderQueue();
			page =null;
		}
		getPage("/lead/"+lLeadId);
		String lAddress = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleProp);
		assertTrue(page.verifySignupAlert(), "Unable to verify sign up alert..");
		assertTrue(page.verifyLeadReqShowingActivityInAlerts(lAddress), "Lead request showing activity is not tracked in alerts");
		assertTrue(page.isQuickQuestionEmailGenerated(), "Email not generated with Subjectg quick question..");
		AutomationLogger.endTestCase();
		
	}
	
	@Test(priority = 200 , dependsOnMethods = {"testVerifyValidEmail"})
	@Parameters({"userSettings"})
	public void verifyUserSettingsAlerts(String pDataFile) {
		AutomationLogger.startTestCase("Verify Alerts");
		getPage();
		dataObject = getDataFile(pDataFile);
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
		if(!getIsProd()) {
			//Process email queue
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processAlertQueue();
			new ZAProcessEmailQueuesPage(driver).processImmediateResponderQueue();
			page =null;
		}
		getPage("/lead/"+lLeadId);
//		getPage("/lead/4581389");
		
		assertTrue(page.verifySignupAlert(), "Unable to verify sign up alert..");
		
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Updated minimum price: "+dataObject.optString(DataConstants.MinPrice).replace("$", "").replace(",", "")), "Unable to verify minimum price in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Updated maximum price: "+dataObject.optString(DataConstants.MaxPrice).replace("$", "").replace(",", "")), "Unable to verify maximum price in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Updated minimum bedrooms: "+dataObject.optString(DataConstants.Beds_Criteria).replace("+", "")), "Unable to verify Beds in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Updated minimum bathrooms: "+dataObject.optString(DataConstants.Baths_Criteria).replace("+", "")), "Unable to verify Baths in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Updated minimum sqft: "+dataObject.optString(DataConstants.Squarefeet_Criteria)), "Unable to verify Square Feet in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Added lot size: "+dataObject.optString(DataConstants.LotSize).replace("sq ft", "Sq Ft")), "Unable to verify lot size in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Added neighborhood: "+dataObject.optString(DataConstants.Neighborhood)), "Unable to verify neighborhood in Notes..");
//		assertTrue(page.verifySchoolDistrict(dataObject.optString(DataConstants.SchoolDistrict)), "Unable to verify School District in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Added zip code: "+dataObject.optString(DataConstants.Zip_Criteria)), "Unable to verify zip code in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Added county: "+dataObject.optString(DataConstants.County)), "Unable to verify zip code in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Added features: "+dataObject.optString(DataConstants.Features)), "Unable to verify features in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Added property view: "+dataObject.optString(DataConstants.Views).replace("Golf course view", "Golf Course View")), "Unable to verify views in Notes..");
		assertTrue(page.verifyLeadActivityInAlerts("Modified Search Preferences","Added property style: "+dataObject.optString(DataConstants.Style)), "Unable to verify style in Notes..");
		
		assertTrue(page.isQuickQuestionEmailGenerated(), "Email not generated with Subjectg quick question..");
		AutomationLogger.endTestCase();
	}
	
	@Test 
	@Parameters({"addLeadData"})
	public void testUpdateLeadDetails(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		String lLeadEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		
		SoftAssert softAssert = new SoftAssert();
		String [] ld_lead_prospects = lDataObject.optString("lead_prospect").split(",");
		
		ZBOSucessAlert successAlert = new ZBOSucessAlert(driver);
		
		for(String lead_prospect: ld_lead_prospects) {
			assertTrue(page.isLeadDetailPage(), "Lead detail page is not visible..");
			assertTrue(page.clickAndSelectLeadProspect(lead_prospect), "Unable to select the status -> "+lead_prospect);
			assertTrue(successAlert.clickOnTemporaryButton(), "Unable to click on Temporary button..");
			if(lead_prospect.equalsIgnoreCase("Client - Sold")) {
				assertTrue(successAlert.isStatusUpdatedMessageVisible(), "Status updated message is not visible..");	
			}else if(lead_prospect.equalsIgnoreCase("Inactive - Stop All Communications")){
				ActionHelper.staticWait(1);
			}else {
				assertTrue(successAlert.isSuccessMessageVisible(), "Success message is not visible..");
			}
			assertTrue(successAlert.clickOnOkButton(), "Unable to click on OK button..");
			ActionHelper.staticWait(5);
			assertEquals(page.getLeadProspectSelectedOption(), lead_prospect, lead_prospect+ "is not selected..");
			page = null;
			getPage("/leads/crm");
			ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
			assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not visible..");
			assertTrue(successAlert.waitForProcessing(), "Waiting for processing..");
			assertTrue(leadCRMPage.typeLeadNameOrEmail(lLeadEmail), "Unable to type lead email..");
			assertTrue(leadCRMPage.clickOnSearchButton(), "Unable to click on Search button..");
			assertTrue(successAlert.waitForProcessing(), "Waiting for processing..");
			ActionHelper.staticWait(5);
			assertEquals(leadCRMPage.getLeadProsepctSelectedValue(), lead_prospect, lead_prospect+ "is not selected..");
			page = null;
			getPage("/lead/"+lLeadId);
		}
		assertTrue(page.verifyEmailPreferences("Mass Email", "No"), "Unable to verify Mass email");
		assertTrue(page.verifyEmailPreferences("Property Updates", "No"), "Unable to verify Property Updates");
		assertTrue(page.verifyEmailPreferences("Sold Property Updates", "No"), "Unable to verify Sold Property Updates");
		assertTrue(page.verifyEmailPreferences("Automated Agent Emails", "No"), "Unable to verify Automated Agent Emails");
		assertTrue(page.verifyEmailPreferences("Market Snapshot Emails", "No"), "Unable to verify Market Snapshot Emails");
	
	}

	@Test
	public void testAddAndVerifyLeadNotes() {
		getPage();
		String lComment = "This is lead detail note$#";
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
		getPage("/lead/"+lLeadId);
//		getPage("/lead/3016673");
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not visible..");
		assertTrue(page.typeComment(lComment), "Unable to type note..");
		assertTrue(page.clickOnSaveNotesButton(), "Unable to click on save notes button..");
		assertTrue(page.verifyNoteAndTime(lComment), "Unable to verify note and time..");
		
	}
	
	@Test
	@Parameters({"searchPropertyDataFile"})
	public void testVerifyLeadBuyersSearch(String pDataFile) {
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		dataObject = getDataFile(pDataFile);
		page = null;
		getPage("/lead/"+lLeadId);
		assertTrue(page.isLeadDetailPage(), "Lead Detail page is not displayed..");
		assertTrue(page.clickOnSearchTabButton(), "Unable to lick on search tab button..");
		ActionHelper.staticWait(5);
		assertTrue(page.getLeadDetailSearchBlock().verifyBuyerSearchLocation(dataObject.optString("input_search")), "Unable to verify buyer search location..");
		assertTrue(page.getLeadDetailSearchBlock().verifyBuyerSearchDate(), "Unable to verify buyer search date..");
		assertTrue(page.getLeadDetailSearchBlock().verifyBuyerSearchPriceRange(dataObject.optString("minimum_price")), "Unable to verify buyer search price range..");
		assertTrue(page.getLeadDetailSearchBlock().verifyBuyerSearchPriceRange(dataObject.optString("maximum_price")), "Unable to verify buyer search price range..");
		assertTrue(page.getLeadDetailSearchBlock().verifyBuyerSearchBedCount(dataObject.optString("number_of_beds")), "Unable to verify buyer search beds count..");
		assertTrue(page.getLeadDetailSearchBlock().verifyBuyerSearchBathCount(dataObject.optString("number_of_baths")), "Unable to verify buyer search baths count..");
		assertTrue(page.getLeadDetailSearchBlock().verifyBuyerSearchSqFeet(dataObject.optString("square_feet")), "Unable to verify buyer search square feet..");
		assertTrue(page.getLeadDetailSearchBlock().verifyBuyerSearchLotSize(dataObject.optString("lot_size")), "Unable to verify buyer search baths lot size..");
	}
	
	@Test
	@Parameters({"searchPropertyDataFile"})
	public void testVerifyLeadSoldHomesSearch(String pDataFile) {
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		dataObject = getDataFile(pDataFile);
		page = null;
		getPage("/lead/"+lLeadId);
	
		String lMinPrice = dataObject.optString("minimum_price").isEmpty()?"All Price":dataObject.optString("minimum_price");
		String lMaxPrice = dataObject.optString("maximum_price").isEmpty()?"All Price":dataObject.optString("maximum_price");
		String lBedCount = dataObject.optString("number_of_beds").isEmpty()?"0":dataObject.optString("number_of_beds");
		String lSqFeet = dataObject.optString("square_feet").isEmpty()?"0":dataObject.optString("square_feet");

		assertTrue(page.isLeadDetailPage(), "Lead Detail page is not displayed..");
		ActionHelper.staticWait(5);
		assertTrue(page.clickOnSearchTabButton(), "Unable to lick on search tab button..");
		ActionHelper.staticWait(5);
		assertTrue(page.getLeadDetailSearchBlock().verifySoldHomesLocation(dataObject.optString("input_search")), "Unable to verify Sold Homes search location..");
		assertTrue(page.getLeadDetailSearchBlock().verifySoldHomesDate(), "Unable to verify Sold Homes search date..");
		assertTrue(page.getLeadDetailSearchBlock().verifySoldHomesPriceRange(lMinPrice), "Unable to verify buyer search price range..");
		assertTrue(page.getLeadDetailSearchBlock().verifySoldHomesPriceRange(lMaxPrice), "Unable to verify buyer search price range..");
		assertTrue(page.getLeadDetailSearchBlock().verifySoldHomesBedCount(lBedCount), "Unable to verify buyer search beds count..");
		assertTrue(page.getLeadDetailSearchBlock().verifySoldHomesLotSize(lSqFeet), "Unable to verify buyer search square feet..");
	}
	
	@Test
	public void testVerifyLeadLocalInfoSearch() {
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
		getPage("/lead/"+lLeadId);
	
		assertTrue(page.isLeadDetailPage(), "Lead Detail page is not displayed..");
		ActionHelper.staticWait(5);
		assertTrue(page.clickOnSearchTabButton(), "Unable to click on search tab button..");
		ActionHelper.staticWait(5);
		assertTrue(page.getLeadDetailSearchBlock().verifyLocalInformationSearches("Points of Interest", ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurplePOIReportsZip)), "Unable to verify Points of Interest data..");
		assertTrue(page.getLeadDetailSearchBlock().verifyLocalInformationSearches("Schools", ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleSchoolsReportsZip)), "Unable to verify Schools data..");
		assertTrue(page.getLeadDetailSearchBlock().verifyLocalInformationSearches("Community", ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleCommunityReportsZip)), "Unable to verify community reports data..");
	}
	
	@Test
	public void testAddAndVerifyReminder() {
		getPage();
		Mailinator mailinatorObj = new Mailinator(driver);
		if(getIsProd()) {
			mailinatorObj.activateProductionInbox();
		}else {
			mailinatorObj.activateStagingInbox();
		}
		
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);//"4744411";//
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);//"0520202021 Buyersearch";
		String lSubjectToVerify = "Task Reminder - "+lLeadName;
		page = null;
		getPage("/lead/"+lLeadId);
		
		assertTrue(page.isLeadDetailPage(), "Lead Detail page is not displayed..");
		assertTrue(page.clickOnDateReminder(), "Unable to click on page reminder..");
		assertTrue(page.clickOnDateDoneButton(), "Unable to click on date Done button..");
		ActionHelper.staticWait(3);
		assertTrue(page.typeReminderNote("Call this lead"), "Unable to type in lead reminder section..");
		assertTrue(page.clickOnSaveButton(), "Unable to click on save button..");
		assertTrue(new ZBOSucessAlert(driver).isReminderSuccessAlertVisible(), "Reminder success alert is not visible..");
		assertTrue(new ZBOSucessAlert(driver).clickOnOkButton(), "Unable to click OK button..");
		String lAgentEmail = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user").split("@")[0];
		
		assertTrue(mailinatorObj.verifyEmail(lAgentEmail, lSubjectToVerify, 15), "Unable to verify reminder email");
	}
	
	@Test
	public void testUpdateLeadAgentAssignment() {
		getPage();	
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);//"4744411";//
		page = null;
		getPage("/lead/"+lLeadId);
		String lAgentName = "";
		HashMap<String,String> agent_info_map  = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAgentsInfo);
		lAgentName = agent_info_map.get("agent_name");
		ZBOSucessAlert successAlert = new ZBOSucessAlert(driver);
		assertTrue(page.clickAndAssignAgentToLead(lAgentName), "Unable to select agent name from drop down");
		assertTrue(successAlert.clickOnAssignButton(), "Unable to click on assign button..");
		assertTrue(successAlert.isSuccessMessageVisible(), "Success message is not visible..");
		assertTrue(successAlert.clickOnOkButton(), "Unable to click on OK button..");
	}
	
	@Test
	public void testVerifyBouncedEmail() {
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
		getPage("/lead/"+lLeadId);
		assertTrue(page.isEmailBounced(), "Email is not bounced..");
		assertTrue(page.isBouncedEmailErrorVisible(), "Bounced Email error is not visble..");
		assertTrue(page.isBouncedEmailAttentionErrorVisible(), "Bounced email attention error is not visible..");
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.verifyNavButtonIsDisabled("Send Email"), "Send Email button is not disabled..");
		assertTrue(leadDetailPage.verifyNavButtonIsDisabled("Send Text Message"), "Send Text Message button is not disabled..");
		assertTrue(leadDetailPage.verifyNavButtonIsDisabled("Enroll in Campaign"), "Enroll in Campaign button is not disabled..");
		assertTrue(leadDetailPage.verifyNavButtonIsDisabled("Send CMA Report"), "Send CMA Report button is not disabled..");
		assertTrue(leadDetailPage.clickOnMyMessagesTab(), "Unable to click on my messages tab..");
		ActionHelper.staticWait(5);
		assertTrue(leadDetailPage.isEnrollInCampaignButtonDisabled(), "Enroll in campaign button is not disabled in my messages tab..");
		assertTrue(page.verifySubscriptionUnsubscriptionStatus("Mass Email", "No"), "Mass email value is not set to No");
		assertTrue(page.verifySubscriptionUnsubscriptionStatus("Property Updates", "No"), "Property Updates value is not set to No");
		assertTrue(page.verifySubscriptionUnsubscriptionStatus("Sold Property Updates", "No"), "Sold Property Updates value is not set to No");
		assertTrue(page.verifySubscriptionUnsubscriptionStatus("Automated Agent Emails", "No"), "Automated Agent Emails value is not set to No");
		assertTrue(page.verifySubscriptionUnsubscriptionStatus("Market Snapshot Emails", "No"), "Market Snapshot Emails value is not set to No");
	}
	
	@Test(groups= {"testEnrollLeadInCampaign"}, dependsOnGroups= {"testCreateCampaign"})
	@Parameters({"dataFile"})
	public void testEnrollLeadInCampaign(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		String ld_leadId = "";
		getPage();
		if(getIsProd()) {
			ld_leadId = dataObject.optString("leadid");
		}else {
			ld_leadId = dataObject.optString("leadid_stage");
		}
		page=null;
		getPage("/lead/"+ld_leadId);
		
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCampaignName, "AutoTestCampaign");
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCampaignID, "99");
		
		String lc_campaignName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleCampaignName);
		String ld_leadEmail =dataObject.optString("lead_email");
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not diplayed..");
		assertTrue(page.enrollUnenrollInCampaign(lc_campaignName,true), "Unable to enroll in campaign");
		assertTrue(page.isCampaignNameVisibleInMyMessages(lc_campaignName), "Campaign Name not visible in My Messages..");
		page = null;
		getPage("/leads/crm");
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not displayed");
		assertTrue(leadCRMPage.searchLeadByEmail(ld_leadEmail), "Lead not found on CRM page...");
		assertTrue(leadCRMPage.isLeadEnrolledInCampaign(), "Lead enrollment is not displayed on CRM page");
		page = null;
		getPage("/campaigns");
		ZBOCampaignPage campaignPage = new ZBOCampaignPage(driver);
		assertTrue(campaignPage.isLeadAddedInCampaign(lc_campaignName), "Lead is not added in campaign..");
	}
	
	@Test(dependsOnGroups= {"testEnrollLeadInCampaign"}, groups= {"testUnenrollLeadFromCampaign"})
	@Parameters({"dataFile"})
	public void testUnenrollLeadFromCampaign(String pDataFile) {
		page = null;
		dataObject = getDataFile(pDataFile);
		String ld_leadId = "";
		if(getIsProd()) {
			ld_leadId = dataObject.optString("leadid");
		}else {
			ld_leadId = dataObject.optString("leadid_stage");
		}
		getPage("/lead/"+ld_leadId);
		String lc_campaignName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleCampaignName);
		String ld_leadEmail =dataObject.optString("lead_email");
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not diplayed..");
		assertTrue(page.enrollUnenrollInCampaign(lc_campaignName,false), "Unable to enroll in campaign");
		assertFalse(page.isCampaignNameVisibleInMyMessages(lc_campaignName), "Campaign Name not visible in My Messages..");
		page = null;
		getPage("/leads/crm");
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not displayed");
		assertTrue(leadCRMPage.searchLeadByEmail(ld_leadEmail), "Lead not found on CRM page...");
		assertFalse(leadCRMPage.isLeadEnrolledInCampaign(), "Lead enrollment is not displayed on CRM page");
		page = null;
		getPage("/campaigns");
		ZBOCampaignPage campaignPage = new ZBOCampaignPage(driver);
		assertFalse(campaignPage.isLeadAddedInCampaign(lc_campaignName), "Lead is not added in campaign..");
	}
	@Test
	public void testVerifyNewLeadEnrollmentInCampaign() {
		getPage();
		
		String lc_leadEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		String lc_leadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		
		page=null;
		getPage("/lead/"+lc_leadId);
		
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not diplayed..");
		assertTrue(page.clickOnMyMessagesTab(), "Unable to click on my messages tab button");
		assertTrue(page.isEnrollInCampaignButtonDisabled(), "Enroll in Campaign button is not disabled..");
//		assertTrue(page.isSendEmailButtonDisabled(), "Send Email button is not disabled..");
//		assertTrue(page.isSendTextButtonDisabled(), "Send Text button is not disabled..");
		assertTrue(page.isEnrollInCampaignTabButtonDisabled(), "Enrollment in campaign button is not disabled..");
		
		page = null;
		getPage("/leads/crm");
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not displayed");
		assertTrue(leadCRMPage.searchLeadByEmail(lc_leadEmail), "Lead not found on CRM page...");
		assertTrue(leadCRMPage.isEnrollmentIconDisabled(), "Lead enrollment is not displayed on CRM page");
	
	}
	
	@Test
	public void testVerifyLeadCaptureFromHomeEvaluationPage() {
		getPage();
		String lComment_one = "System subscribed lead to Sold Property Updates";
		String lComment_two = "Please provide me with a home value estimate.";

		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
		getPage("/lead/"+lLeadId);
//		getPage("/lead/3016673");
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not visible..");
		assertTrue(page.verifyNoteAndTime(lComment_one), "Unable to verify note and time..");
		assertTrue(page.verifyNoteAndTime(lComment_two), "Unable to verify note and time..");
		assertTrue(page.isEmailVerified(), "Email address is not verified..");
		if(!getIsProd()) {
			page = null;
			//Process email queue
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processAlertQueue();
			page =null;
		}
		getPage("/lead/"+lLeadId);
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not visible..");
		assertTrue(page.verifyHomeEvaluationAlert("Homeowner Asked for a CMA"), "Homeowner Asked for a CMA alert is not verified");
		assertTrue(page.verifyEmailPreferences("Sold Property Updates", "Yes"), "Sold Property Updates is not set Yes");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyActivityAlerts(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		String lAlertType = dataObject.optString("alert_type");
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
		getPage("/lead/"+lLeadId);
		switch(lAlertType) {
		case "High Activity":
			assertTrue(page.verifyActivityAlert(lAlertType), "High Activity alert is not displayed in Alerts tab..");
			assertTrue(page.isBrowsingHotBehaviorVisible(), "Browsing Hot Behavior is not updated..");
			break;
		case "High Return":
			assertTrue(page.verifyActivityAlert(lAlertType), "High Return alert is not displayed in Alerts tab..");
			assertTrue(page.isReturnHotBehaviorVisible(), "Return Hot Behavior is not updated..");
			break;
		case "Agent Inquiry":
			assertTrue(page.verifyActivityAlert(lAlertType), "Agent Inquiry alert is not displayed in Alerts tab..");
			break;
		case "Seller Inquiry":
			assertTrue(page.verifyActivityAlert(lAlertType), "Seller Inquiry alert is not displayed in Alerts tab..");
			break;
		default:
			break;
		}
	}
}
