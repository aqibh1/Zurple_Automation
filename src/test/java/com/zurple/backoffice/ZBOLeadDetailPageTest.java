package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.my.PageTest;

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
		
		String lUpdatedUrl = driver.getCurrentUrl().replace("dashboard", "lead/"+lLeadId+"");
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
		String lUpdatedUrl = "";
		if(driver.getCurrentUrl().contains("dashboard")) {
			lUpdatedUrl = driver.getCurrentUrl().replace("dashboard", "lead/"+lLeadId+"");
		}else {
			lUpdatedUrl = driver.getCurrentUrl().replace("leads", "lead/"+lLeadId+"");
		}
		 
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
		assertTrue(page.isLeadDetailPage(), "Lead detail page is not visible..");
		assertTrue(page.clickAndSelectLeadProspect(lDataObject.optString("lead_prospect")));
		
	}
	
//	@Test 
//	public void testVerifyMassEmailInMyMessages() {
//		AutomationLogger.startTestCase("Verify Alerts");
//		getPage();
//		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
//		page = null;
//		getPage("/lead/"+lLeadId);
//		if(page.isEmailVerified()) {
//			if(!getIsProd()) {
//				page = null;
//				//Process email queue
//				getPage("/admin/processemailqueue");
//				new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
//				page =null;
//				getPage("/lead/"+lLeadId);
//			}
//			assertTrue(page.verifyMyMessages(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject)));
//		}else {
//			assertTrue(false, "Unable to verify email...");
//		}
//	}

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
		ActionHelper.staticWait(3);
		assertTrue(page.typeReminderNote("Call this lead"), "Unable to type in lead reminder section..");
		assertTrue(page.clickOnSaveButton(), "Unable to click on save button..");
		assertTrue(new ZBOSucessAlert(driver).isReminderSuccessAlertVisible(), "Reminder success alert is not visible..");
		assertTrue(new ZBOSucessAlert(driver).clickOnOkButton(), "Unable to click OK button..");
		String lAgentEmail = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user").split("@")[0];
		
		assertTrue(mailinatorObj.verifyEmail(lAgentEmail, lSubjectToVerify, 15), "Unable to verify reminder email");
	}
}
