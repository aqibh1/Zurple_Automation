package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;
import resources.utility.DataConstants;

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
		assertTrue(page.verifyMinPrice(dataObject.optString(DataConstants.MinPrice)), "Unable to verify minimum price in Notes..");
		assertTrue(page.verifyMaxPrice(dataObject.optString(DataConstants.MaxPrice)), "Unable to verify maximum price in Notes..");
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
	
	@Test 
	public void testVerifyMassEmailInMyMessages() {
		AutomationLogger.startTestCase("Verify Alerts");
		getPage();
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		page = null;
		getPage("/lead/"+lLeadId);
		if(page.isEmailVerified()) {
			if(!getIsProd()) {
				page = null;
				//Process email queue
				getPage("/admin/processemailqueue");
				new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
				page =null;
				getPage("/lead/"+lLeadId);
			}
			assertTrue(page.verifyMyMessages(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject)));
		}else {
			assertTrue(false, "Unable to verify email...");
		}
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

}
