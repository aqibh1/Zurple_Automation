package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.zurple.backoffice.marketing.ZBOCampaignPage;
import com.zurple.my.PageTest;
import com.zurple.website.ZWCommunityReportsPageTest;
import com.zurple.website.ZWPointOfIntrestsReportsPageTest;
import com.zurple.website.ZWRegisterUserPageTest;
import com.zurple.website.ZWSchoolsReportsPageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.CacheFilePathsConstants;
import resources.utility.DataConstants;
import resources.utility.Mailinator;

public class ZBOLeadDetailPageTest extends PageTest{

	private WebDriver driver;
	private ZBOLeadDetailPage page;
	private JSONObject dataObject;
	public String ld_leadId = "";
	public String ld_leadEmail = "";

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
	
	@Test(dependsOnGroups = "testVerifyPropertyDetails")
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
		ActionHelper.staticWait(10);
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

		JSONObject cacheObject = new JSONObject();
		cacheObject.put("lead_id", lLead);
		emptyFile(CacheFilePathsConstants.AccountSettingsLeadCache, "");
		writeJsonToFile(CacheFilePathsConstants.AccountSettingsLeadCache, cacheObject);
		
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
	
	//This test is not being used anywehere
	@Test(priority = 150 , dependsOnMethods = {"testVerifyValidEmail"}, retryAnalyzer = resources.RetryFailedTestCases.class)
	public void testVerifyAlerts() {
		AutomationLogger.startTestCase("Verify Alerts");
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
//		if(!getIsProd()) {
//			//Process email queue
//			getPage("/admin/processemailqueue");
//			new ZAProcessEmailQueuesPage(driver).processAlertQueue();
//			new ZAProcessEmailQueuesPage(driver).processImmediateResponderQueue();
//			page =null;
//		}
		getPage("/lead/"+lLeadId);
		String lAddress = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleProp);
		assertTrue(page.verifySignupAlert(), "Unable to verify sign up alert..");
		assertTrue(page.verifyLeadReqShowingActivityInAlerts(lAddress), "Lead request showing activity is not tracked in alerts");
		assertTrue(page.isQuickQuestionEmailGenerated(), "Email not generated with Subjectg quick question..");
		AutomationLogger.endTestCase();
		
	}
	
	@Test
	@Parameters({"userSettings"})
	public void verifyUserSettingsAlerts(String pDataFile) {
		getPage();
		dataObject = getDataFile(pDataFile);
		JSONObject cacheObject = new JSONObject();
		cacheObject = getDataFile(CacheFilePathsConstants.AccountSettingsLeadCache);
//		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		String lLeadId = cacheObject.getString("lead_id");
		page = null;
//		if(!getIsProd()) {
//			//Process email queue
//			getPage("/admin/processemailqueue");
//			new ZAProcessEmailQueuesPage(driver).processAlertQueue();
//			new ZAProcessEmailQueuesPage(driver).processImmediateResponderQueue();
//			page =null;
//		}
		getPage("/lead/"+lLeadId);
//		getPage("/lead/4581389");
		ActionHelper.staticWait(10);
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
		
//		assertTrue(page.isQuickQuestionEmailGenerated(), "Email not generated with Subjectg quick question..");
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
			if(!lead_prospect.equalsIgnoreCase("Inactive - Stop All Communications")) {
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
			}
			page = null;
			getPage("/lead/"+lLeadId);
		}
		ActionHelper.staticWait(5);
		ActionHelper.RefreshPage(driver);
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
	
	@Test(retryAnalyzer = resources.RetryFailedTestCases.class)
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
	public void testAddAndVerifyReminder() throws ParseException {
		getPage("/leads/crm");
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		
		Mailinator mailinatorObj = new Mailinator(driver);
		if(getIsProd()) {
			mailinatorObj.activateProductionInbox();
		}else {
			mailinatorObj.activateStagingInbox();
		}
		
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not visible..");
		
		AutomationLogger.info("Applying filter to get valid lead");
		String lFilterName = "By Agent,By Email Verification";
		String lFilterValue = getIsProd()?"Aqib Production Testing,Valid Emails":"Aqib Site Owner,Valid Emails";
		applyMultipleFilter(lFilterName, lFilterValue);
		
		String lead_name_id = leadCRMPage.getLeadName();
		String l_leadName = lead_name_id.split(",")[0].trim();
		String l_leadId = lead_name_id.split(",")[1].trim();
		AutomationLogger.info("Lead ID::"+l_leadId);
		AutomationLogger.info("Lead ID::"+l_leadName);
		
		String lLeadId = l_leadId;//ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);//"4744411";//
		String lLeadName = l_leadName;//ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);//"0520202021 Buyersearch";
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
		
		JSONObject cacheObject = new JSONObject();
		cacheObject.put("email_subject", lSubjectToVerify);
		
		emptyFile(CacheFilePathsConstants.ReminderEmailLeadDetailCache, "");
		writeJsonToFile(CacheFilePathsConstants.ReminderEmailLeadDetailCache, cacheObject);
		
	}

	@Test
	public void testVerifyAgentReminderEmail() {
		getPage();	
//		processReminderEmailQueue();
		JSONObject lc_object = getDataFile(CacheFilePathsConstants.ReminderEmailLeadDetailCache);
		Mailinator mailinatorObj = new Mailinator(driver);
		String lAgentEmail = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user").split("@")[0];
		assertTrue(mailinatorObj.verifyEmail(lAgentEmail, lc_object.optString("email_subject"), 5), "Unable to verify reminder email");
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
//		assertTrue(leadDetailPage.verifyNavButtonIsDisabled("Send Text Message"), "Send Text Message button is not disabled..");
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
	
	@Test(groups= {"testEnrollLeadInCampaign"}, dependsOnGroups= {"testCreateCampaign"}, priority=477)
	@Parameters({"dataFile","leadFile"})
	public void testEnrollLeadInCampaign(String pDataFile, String leadDataFile) {
		page=null;
		getPage();
		dataObject = getDataFile(pDataFile);
		ZBOSendReportPageTest addLead = new ZBOSendReportPageTest();
		addLead.addLead(leadDataFile);
		ld_leadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
//		if(getIsProd()) {
//			ld_leadId = dataObject.optString("leadid");
//		}else {
//			ld_leadId = dataObject.optString("leadid_stage");
//		}
		page=null;
		getPage("/lead/"+ld_leadId);
		
		ZBOSucessAlert successAlert = new ZBOSucessAlert(driver);
		assertTrue(page.clickAndSelectLeadProspect("Prospect - Communicated with Me"), "Unable to select the status -> Prospect - Communicated with Me");
		assertTrue(successAlert.clickOnTemporaryButton(), "Unable to click on Temporary button..");
		ActionHelper.staticWait(2);
		ActionHelper.RefreshPage(driver);
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCampaignName, "AutoTestCampaign");
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCampaignID, "99");
		
		String lc_campaignName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleCampaignName);
		ld_leadEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not diplayed..");
		boolean isAlreadyEnrolledInCampaign = isLeadEnrolledInCampaign();
		if(!isAlreadyEnrolledInCampaign) {
			assertTrue(page.enrollUnenrollInCampaign(lc_campaignName,true), "Unable to enroll in campaign");
			assertTrue(page.isCampaignNameVisibleInMyMessages(lc_campaignName), "Campaign Name not visible in My Messages..");
		}
		page = null;
		getPage("/leads/crm");
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not displayed");
		assertTrue(leadCRMPage.searchLeadByEmail(ld_leadEmail), "Lead not found on CRM page...");
		assertTrue(leadCRMPage.isLeadEnrolledInCampaign(), "Lead enrollment is not displayed on CRM page");
		if(!isAlreadyEnrolledInCampaign) {
			page = null;
			getPage("/campaigns");
			ZBOCampaignPage campaignPage = new ZBOCampaignPage(driver);
			assertTrue(campaignPage.isLeadAddedInCampaign(lc_campaignName), "Lead is not added in campaign..");
		}
	}	
	
	@Test(dependsOnGroups= {"testEnrollLeadInCampaign"}, groups= {"testUnenrollLeadFromCampaign"}, priority=478)
	@Parameters({"dataFile"})
	public void testUnenrollLeadFromCampaign(String pDataFile) {
		page=null;
		getPage("/lead/"+ld_leadId);
		dataObject = getDataFile(pDataFile);
//		String ld_leadId = "";
//		if(getIsProd()) {
//			ld_leadId = dataObject.optString("leadid");
//		}else {
//			ld_leadId = dataObject.optString("leadid_stage");
//		}
		String lc_campaignName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleCampaignName);
		//String ld_leadEmail =dataObject.optString("lead_email");
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not diplayed..");
		boolean isAlreadyEnrolledInCampaign = !page.getCampaignNameFromMyMessagesNone().equalsIgnoreCase("None");
		if(isAlreadyEnrolledInCampaign) {
			assertTrue(page.enrollUnenrollInCampaign(lc_campaignName,false), "Unable to enroll in campaign");
			assertFalse(page.isCampaignNameVisibleInMyMessages(lc_campaignName), "Campaign Name not visible in My Messages..");
		}	
		page = null;
		getPage("/leads/crm");
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not displayed");
		assertTrue(leadCRMPage.searchLeadByEmail(ld_leadEmail), "Lead not found on CRM page...");
		assertFalse(leadCRMPage.isLeadEnrolledInCampaign(), "Lead enrollment is not displayed on CRM page");
//		page = null;
//		getPage("/campaigns");
//		ZBOCampaignPage campaignPage = new ZBOCampaignPage(driver);
//		assertFalse(campaignPage.isLeadAddedInCampaign(lc_campaignName), "Lead is not added in campaign..");
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
		ActionHelper.ScrollDownByPixels(driver, "800");
		assertTrue(page.verifyNoteAndTime(lComment_one), "Unable to verify note and time..");
		assertTrue(page.verifyNoteAndTime(lComment_two), "Unable to verify note and time..");
		assertTrue(page.isEmailVerified(), "Email address is not verified..");
//		if(!getIsProd()) {
//			page = null;
//			//Process email queue
//			getPage("/admin/processemailqueue");
//			new ZAProcessEmailQueuesPage(driver).processAlertQueue();
//		}
		page=null;
		getPage("/lead/"+lLeadId);
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not visible..");
		assertTrue(page.verifyHomeEvaluationAlert("Homeowner Asked for a CMA"), "Homeowner Asked for a CMA alert is not verified");
		assertTrue(page.verifyEmailPreferences("Sold Property Updates", "Yes"), "Sold Property Updates is not set Yes");
	}
	
	@Test
	public void testVerifyNoteIsAddedForLeadCapturedFromHomeEvaluationPage() {
		getPage();
		dataObject = getDataFile(CacheFilePathsConstants.HomeValuationLeadCache);
		String lLeadId  = dataObject.optString("lead_id");
		String lComment_one = "System subscribed lead to Sold Property Updates";
		String lComment_two = "Please provide me with a home value estimate.";
//		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
		getPage("/lead/"+lLeadId);
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not visible..");
		ActionHelper.ScrollDownByPixels(driver, "800");
		assertTrue(page.verifyNoteAndTime(lComment_one), "Unable to verify note and time..");
		assertTrue(page.verifyNoteAndTime(lComment_two), "Unable to verify note and time..");
	}
	
	@Test
	public void testVerifyHomeEvaluationAlertIsReceived() {
		getPage();
		dataObject = getDataFile(CacheFilePathsConstants.HomeValuationLeadCache);
		String lLeadId  = dataObject.optString("lead_id");
		getPage("/lead/"+lLeadId);
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not visible..");
		assertTrue(page.verifyHomeEvaluationAlert("Homeowner Asked for a CMA"), "Homeowner Asked for a CMA alert is not verified");
	}
	
	@Test
	public void testVerifySoldPropertyUpdatesIsSetToYes() {
		getPage();
		dataObject = getDataFile(CacheFilePathsConstants.HomeValuationLeadCache);
		String lLeadId  = dataObject.optString("lead_id");
		getPage("/lead/"+lLeadId);
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not visible..");
		assertTrue(page.verifyEmailPreferences("Sold Property Updates", "Yes"), "Sold Property Updates is not set Yes");
	}
	
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyActivityAlerts(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		String lAlertType = dataObject.optString("alert_type");
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		String lLeadName = ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.ZurpleLeadName);
		page = null;
		getPage("/lead/"+lLeadId);
		switch(lAlertType) {
		case "High Activity":
			assertTrue(page.verifyActivityAlert("Lots of Browsing"), "High Activity alert is not displayed in Alerts tab..");
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
		case "Saved to Favorites Alert":
			HashMap<String,String> propKeyValue = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleProp);
			assertTrue(page.verifyFavoritesAlert(propKeyValue.get("property-city_name"),propKeyValue.get("property-address"),propKeyValue.get("property-price")), "Unable to verify property in Favorites tab..");
			break;
		case "High Value":
			assertTrue(page.verifyActivityAlert("Expensive Properties"), "Seller Inquiry alert is not displayed in Alerts tab..");
			assertTrue(page.isExpensivePropHotBehaviorVisible(), "Browsing Hot Behavior is not updated..");
			page = null;
			getPage("/leads/crm");
			ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
			assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not visible..");
			assertTrue(leadCRMPage.searchLead(lLeadName), "Unable to search lead by name.."+lLeadName);
			ActionHelper.staticWait(10);
			assertTrue(page.isExpensivePropHotBehaviorVisible(), "Expensive Hot Behavior is not updated on CRM page..");
//			assertTrue(leadCRMPage.verifyAutoConvoCount(1), "Unable to verify Auto Conversation count on CRM page..");
			break;
		default:
			break;
		}
	}
	@Test
	public void testVerifyScheduleShowingSignupAlert() {
		getPage();
		dataObject = getDataFile(CacheFilePathsConstants.ScheduleShowingCache);
		String lLeadId = dataObject.optString("lead_id");
//		processAlertQueue();
		page=null;
		getPage("/lead/"+lLeadId);
		assertTrue(page.verifySignupAlert(), "Unable to verify sign up alert For Lead ID ["+lLeadId+"]");	
	}
	@Test
	public void testRequestShowingActivityInAlert() {
		getPage();
		dataObject = getDataFile(CacheFilePathsConstants.ScheduleShowingCache);
		String lLeadId = dataObject.optString("lead_id");
//		processAlertQueue();
		page=null;
		getPage("/lead/"+lLeadId);
		String lAddress = dataObject.optString("prop_address");
		assertTrue(page.verifyLeadReqShowingActivityInAlerts(lAddress), "Lead request showing activity is not tracked in alerts For Lead ID ["+lLeadId+"] and Address ["+lAddress+"]");
	
	}
	
	@Test
	public void testVerifQuickQuestionEmailIsGenerated() {
		getPage();
		dataObject = getDataFile(CacheFilePathsConstants.ScheduleShowingCache);
		String lLeadId = dataObject.optString("lead_id");
//		processAlertQueue();
		page=null;
		getPage("/lead/"+lLeadId);
		assertTrue(page.isQuickQuestionEmailGenerated(), "Email not generated with Subject [Quick Question] for Lead ID ["+lLeadId+"]..");
	}

	@Test //39969
	public void testVerifSendCMAReportButtonIsVisible() {
		getPage("/leads/crm");
		clickOnLeadNamePreCond();
		assertTrue(page.verifySendCMAReportButtonIsVisible(), "Send CMA report button is not visible..");
	}
	
	@Test
	public void testClickOnCMAReportButtonIsWorking() {
		getPage("/leads/crm");
		clickOnLeadNamePreCond();
		assertTrue(page.clickOnCMAReportButton(), "Send CMA report button is not working..");
		ZBOSendCMAReportPage sendCMAReportPage = new ZBOSendCMAReportPage(driver);
		assertTrue(sendCMAReportPage.isCMAReportHeadingVisible(), "CMA Report heading is not visible..");
		assertTrue(driver.getCurrentUrl().contains("cma/email/lead"), "URL of CMA Report is not changed..");
	}
	
	//39970
	@Test
	public void testVerifyCMAReportIsDisabledForInvalidLead() {
		//Pre Condition
		selectInvalidLead("By Email Verification","Invalid Emails");
		ActionHelper.staticWait(5);
		ActionHelper.switchToSecondWindow(driver);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.isSendCMAReportButtonDisabled(), "Send CMA Report button is not disabled..");
	}
	
	@Test
	public void testVerifyCommunityReportsSearchIsDisplayed() {
		getPage();
		searchCommunityResultsPreCond();
		String l_lead_id = getLeadIDPreCondition();
		page= null;
		getPage("/lead/"+l_lead_id);
		clickOnSearchTab();
		ActionHelper.staticWait(5);
		assertTrue(page.verifyLocalInfoType("Community"), "Community search entry not found in Local Search..");
	}
	@Test
	public void testVerifySchoolReportsSearchIsDisplayed() {
		getPage();
		searchSchoolResultsPreCond();
		String l_lead_id = getLeadIDPreCondition();
		page= null;
		getPage("/lead/"+l_lead_id);
		clickOnSearchTab();
		ActionHelper.staticWait(5);
		assertTrue(page.verifyLocalInfoType("Schools"), "Schools search entry not found in Local Search..");
	}
	@Test
	public void testVerifyPOIReportsSearchIsDisplayed() {
		getPage();
		searchPOIResultsPreCond();
		String l_lead_id = getLeadIDPreCondition();
		page= null;
		getPage("/lead/"+l_lead_id);
		clickOnSearchTab();
		ActionHelper.staticWait(5);
		assertTrue(page.verifyLocalInfoType("Points of Interest"), "Points of Interest search entry not found in Local Search..");
	}
	@Test
	public void testVerifyCorrectZipIsDisplayedFroCommunityReports() {
		String l_zip = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleCommunityReportsZip);
		ActionHelper.staticWait(5);
		assertTrue(page.verifyLocalInfoZip("Community", l_zip), "Community Zip is not matched in Local Search..");
	}
	@Test
	public void testVerifyCorrectZipIsDisplayedForSchoolReports() {
		String l_zip = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleSchoolsReportsZip);
		ActionHelper.staticWait(5);
		assertTrue(page.verifyLocalInfoZip("Schools", l_zip), "School Zip is not matched in Local Search..");
	}
	@Test
	public void testVerifyCorrectZipIsDisplayedForPOIReports() {
		String l_zip = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurplePOIReportsZip);
		ActionHelper.staticWait(5);
		assertTrue(page.verifyLocalInfoZip("Points of Interest", l_zip), "POI Zip is not matched in Local Search..");
	}
	
	/**
	 * Verify Lead captured from Seller Lead ads has correct source
	 * 45805
	 */
	@Test
	public void testVerifySellerLeadHasCorrectLeadSource() {
		getPage("/leads/crm");
		String lead_name =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		ZBOLeadCRMPage leadCrmPage = new ZBOLeadCRMPage(driver);
		String lead_id = leadCrmPage.searchAndGetLeadId(lead_name.split(" ")[0]);
		page = null;
		getPage("/lead/"+lead_id);
		String lead_source = page.getLeadSource();
//		assertTrue(lead_source.contains("(fbseller-fb_lead_form)"), "Unable to verify lead source");
		assertTrue(lead_source.contains("Zurple Seller"), "Unable to verify lead source");
	}
	
	/**
	 * Verify Lead captured from Seller Lead ads has correct Note added
	 * 45806
	 */
	@Test
	public void testVerifySellerLeadHasCorrectNoteAdded() {
		getPage();
		boolean isNoteVerified = page.verifyNoteAndTime("Lead was successfully imported from Facebook.");
		assertTrue(isNoteVerified, "Unable to verify Note and date");
		
	}
	
	/**
	 * @param pDataFile
	 * Verify Lead captured from Seller lead ads has a correct address
	 * 45807
	 */
	@Test
	@Parameters({"datafile"})
	public void testVerifySellerLeadHasCorrectAddress(String pDataFile){
		getPage();
		dataObject = getDataFile(pDataFile);
		String street_address = dataObject.optString("street_address");
		String zip_code = dataObject.optString("zip_code");
		String city = dataObject.optString("city");
		String state =dataObject.optString("state");
		boolean isAddressVerified = page.verifyLeadAddress(street_address, city, state, zip_code);
		assertTrue(isAddressVerified, "Unable to verify lead address..");
	}
	
	/**
	 * Verify Lead captured from Seller lead ads has a correct phone number
	 * 45808
	 */
	@Test
	public void testVerifySellerLeadHasCorrectPhoneNumber() {
		getPage();
		String ld_phone = dataObject.optString("phone");
		String phone = page.getPhoneNum();
		assertTrue(ld_phone.equalsIgnoreCase(phone), "Unable to verify lead phone..");
	}
	
	/**
	 * Verify Lead captured from Seller lead ads has a correct location
	 * 45809
	 */
	@Test
	public void testVerifySellerLeadHasCorrectLocation() {
		String city = dataObject.optString("city");
		String state =dataObject.optString("state");
		assertTrue(page.verifyIconsListText(city), "Unable to verify City");
		assertTrue(page.verifyIconsListText(state), "Unable to verify State");
	}
	
	/**
	 * Verify Lead captured from Seller lead ads has a correct square feet value
	 * 45810
	 */
	@Test
	public void testVerifySellerLeadHasCorrectSquareFeetValue() {
		String sqft = dataObject.optString("sqft");
		Integer myInt = new Integer(sqft);
		String output = NumberFormat.getNumberInstance(Locale.US).format(Integer.valueOf(myInt));
		assertTrue(page.verifyIconsListText(output), "Unable to verify City");
	}
	
	/**
	 * Verify Lead captured from Seller lead ads has a correct bath count
	 * 45811
	 */
	@Test
	public void testVerifySellerLeadHasCorrectBathCount() {
		String ld_bath_count = dataObject.optString("baths");
		assertTrue(page.verifyIconsListText(ld_bath_count), "Unable to verify bath count");
	}
	
	/**
	 * Verify Lead captured from Seller lead ads has a correct bath count
	 * 45812
	 */
	@Test
	public void testVerifySellerLeadHasCorrectBedsCount() {
		String ld_beds_count = dataObject.optString("beds");
		assertTrue(page.verifyIconsListText(ld_beds_count), "Unable to verify bath count");
	}
	
	/**
	 * Verify Lead captured from Seller lead ads has a correct transaction goals
	 * 45813
	 */
	@Test
	public void testVerifySellerLeadHasCorrectTransactionGoals() {
		String transaction_goals = "Seller Goals";
		assertTrue(page.getTransactionGoalsValue().contains(transaction_goals), "Unable to verify Transaction goals");
	}
	
	/**
	 * Verify Lead captured from Seller lead ads has 'Develop' priority ranking
	 * 45814
	 */
	@Test
	public void testVerifySellerLeadHasCorrectPriorityRanking() {
		String priority_ranking = "Target";
		assertTrue(page.verifyLeadPriorityRanking(priority_ranking), "Unable to verify priority ranking");
	}
	
	/**
	 * Verify Lead captured from Seller lead ads has a correct Email Preferences
	 * 45815
	 */
	@Test
	public void testVerifySellerLeadHasCorrectEmailPreferences() {
		String ld_prop_updates_value = "";
		if(dataObject.optInt("property_updates_flag")==1) {
			ld_prop_updates_value = "Yes";
		}else {
			ld_prop_updates_value = "No";
		}
		assertTrue(page.verifyEmailPreferences("Property Updates", ld_prop_updates_value), "Unable to verify Property Updates value");
	}
	
	/**
	 * Verify Lead captured from Seller lead ads has Buyer Search entry under Searches tab
	 * 45816
	 */
	@Test
	public void testVerifySellerLeadHasBuyerSearchEntry() {
		String ld_location = dataObject.optString("city");
		assertTrue(page.clickOnSearchTabButton(), "Unable to click on search tab button..");
		ActionHelper.staticWait(10);
		assertTrue(page.getLeadDetailSearchBlock().verifyBuyerSearchLocation(ld_location), "Unable to verify Buyer search location");
	}
	
	/**
	 * Verify the lead capture from learn more link is assigned to the same agent
	 * 47699
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadHasSameAgentAssigned(@Optional String pDataFile) {
		getPage();
		gotoWebsiteAdPage();
		String lLeadId = getLeadId(pDataFile);
		String l_agent_name = getAgentName(EnvironmentFactory.configReader.getPropertyByName("sub_admin_id"));
		gotoLeadDetailPage(lLeadId);
		assertTrue(page.verifyLeadAssignedToAgent(l_agent_name), "Agent not assigned to lead.."+l_agent_name);
		
	}
	
	/**
	 * Verify the lead source shows as 'Paid Social' when the lead gets captured from learn more link
	 * 47700
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadSourceIsPaidSocial(@Optional String pDataFile) {
		getPage();
		gotoWebsiteAdPage();
		String lLeadId = getLeadId(pDataFile);	
		gotoLeadDetailPage(lLeadId);
		assertTrue(page.getLeadSource().contains("Paid Social"), "Unable to verify the lead source ");
		
	}
	
	/**
	 * Verify lead source of lead registered from Website is "Zurple Traffic" on lead detail page
	 * 48940
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadSourceCapturedFromWebsite(String pDataFile) {
		getPage();
		ZWRegisterUserPageTest registerUserObject = new ZWRegisterUserPageTest();
		registerUserObject.testRegisterUser(pDataFile);
		String lc_lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		gotoLeadDetailPage(lc_lead_id);
		assertTrue(page.getLeadSource().contains("Zurple Traffic"), "Lead source is not Zurple Traffic");		
	}
	
	private String getLeadId(String pDataFile) {
		String lLeadId = ModuleCommonCache.getElement(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId);
		if(lLeadId==null) {
			ZWRegisterUserPageTest registerLead = new ZWRegisterUserPageTest();
			registerLead.testRegisterUser(pDataFile);	
			lLeadId = ModuleCommonCache.getElement(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId);
		}
		return lLeadId;
	}
	private void clickOnLeadNamePreCond() {
		ZBOLeadCRMPage leadcrmpage = new ZBOLeadCRMPage(driver);
		if(!leadcrmpage.applyFilterAndSelectlead("By Date Created", "last 7 days")) {
			throw new SkipException("Pre condition failed. Unabled to select lead..");
		}
		ActionHelper.switchToSecondWindow(driver);
	}
	//PreCondition
	private void selectInvalidLead(String pFilterName, String pFilterValue) {
		try {
			ZBOLeadCRMPageTest addleadpagetest = new ZBOLeadCRMPageTest();
			addleadpagetest.getPage("/leads/crm");
			addleadpagetest.applyFilter(pFilterName, pFilterValue);
			driver = getDriver();
			ActionHelper.staticWait(15);
			ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
			leadCRMPage.clickSearchedLeadName();
		}catch(Exception ex) {
			throw new SkipException("PreCondition failed. Unable to add lead..");
		}
	}
//	private void processAlertQueue() {	
//		if(!getIsProd()) {
//			//Process email queue
//			page = null;
//			getPage("/admin/processemailqueue");
//			new ZAProcessEmailQueuesPage(driver).processAlertQueue();
//			new ZAProcessEmailQueuesPage(driver).processImmediateResponderQueue();
//			page =null;
//		}
//	}
//	public void processReminderEmailQueue() {
//        if(!getIsProd()) {
//            page=null;
//            getPage("/admin/processemailqueue");
//            new ZAProcessEmailQueuesPage(driver).processReminderQueue();
//        }
//    }
	private void searchCommunityResultsPreCond() {
		ZWCommunityReportsPageTest comreportsTest = new ZWCommunityReportsPageTest();
		try {
			comreportsTest.testSearchCommunityReports();
		}catch(Exception ex) {
			throw new SkipException("Skipping the test as pre condition as unable to search community reports");
		}
	}
	public String getLeadIDPreCondition() {
		String l_lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		if(l_lead_id.isEmpty() || l_lead_id==null) {
			throw new SkipException("Lead is not registered. Lead id is empty [Skipping]");
		}
		return l_lead_id;
	}
	public void clickOnSearchTab() {
		if(!page.clickOnSearchTabButton()) {
			throw new SkipException("Skipping the test as pre condition failed. Unable to click on Search tab button");
		}
	}
	private void searchSchoolResultsPreCond() {
		ZWSchoolsReportsPageTest schoolReportsTest = new ZWSchoolsReportsPageTest();
		try {
			schoolReportsTest.testSearchSchoolsReports();;
		}catch(Exception ex) {
			throw new SkipException("Skipping the test as pre condition as unable to search School reports");
		}
	}
	private void searchPOIResultsPreCond() {
		ZWPointOfIntrestsReportsPageTest comreportsTest = new ZWPointOfIntrestsReportsPageTest();
		try {
			comreportsTest.testSearchPOIReports();
		}catch(Exception ex) {
			throw new SkipException("Skipping the test as pre condition as unable to search POI reports");
		}
	}
	private boolean isLeadEnrolledInCampaign() {
		boolean isAlreadyEnrolledInCampaign = false;
		if(page.clickOnMyMessagesTab()) {
			isAlreadyEnrolledInCampaign = !page.getCampaignNameFromMyMessagesNone().equalsIgnoreCase("None");
		}
		return isAlreadyEnrolledInCampaign;
	}
	private void gotoLeadDetailPage(String pLeadId) {
		String lUpdatedUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+pLeadId;
		driver.navigate().to(lUpdatedUrl);
	}
	private void gotoWebsiteAdPage() {
		String l_subadmin_id = EnvironmentFactory.configReader.getPropertyByName("sub_admin_id");
		String l_paid_ad_url = "/search?source=paid_social&admin_id="+l_subadmin_id; 
		String lUpdatedUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")+l_paid_ad_url;
		driver.navigate().to(lUpdatedUrl);
	}
	private String getAgentName(String pAdminId) {
		String l_agent_profile_url = "/agent/edit/admin_id/"+pAdminId; 
		String lUpdatedUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+l_agent_profile_url;
		driver.navigate().to(lUpdatedUrl);
		ZBOAgentsPage zboAgentPage = new ZBOAgentsPage(driver);
		return zboAgentPage.getAgentFirstName()+" "+zboAgentPage.getAgentLastName();
	}
}
