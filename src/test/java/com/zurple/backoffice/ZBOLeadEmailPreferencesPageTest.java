package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.DataConstants;

public class ZBOLeadEmailPreferencesPageTest extends PageTest{
	
	private ZBOLeadEmailPreferencesPage page;
	private WebDriver driver;
	private JSONObject dataObject;
	public HashMap<String, String> updatedLeadData = new HashMap<String, String>();	

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadEmailPreferencesPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		page = null;
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadEmailPreferencesPage(driver);
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
	@Parameters({"userSettings"})
	public void testVerifyLeadEmailPreferences(String pDataFile) {
		AutomationLogger.startTestCase("Verify lead email preferences");
		getPage();
//		String lLead = "3015660"
		String lLead = ModuleCommonCache.getElement(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId);
		getPage("/lead/edit/user_id/"+lLead);
		dataObject = getDataFile(pDataFile);
		String lStreet = dataObject.optString(DataConstants.Street);
		String lCity = dataObject.optString(DataConstants.City);
		String lState = dataObject.optString(DataConstants.State);
		String lZip = dataObject.optString(DataConstants.Zip);     
		String lBed = dataObject.optString(DataConstants.Beds);
		String lBath = dataObject.optString(DataConstants.Baths);
		String lSqFeet = dataObject.optString(DataConstants.Squarefeet);
		
		assertTrue(page.isLeadUpdatePage(), "Lead update page is not visible..");
		assertTrue(page.allHeadingsDisplayed(), "All headings are not displayed..");
		assertTrue(page.isEmailUpdated(ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail)), "Unable to verify updated email..");
		assertTrue(page.verifyStreet(lStreet), "Unable to verify lead Street..");
		assertTrue(page.verifyCity(lCity), "Unable to verify Lead City..");
		assertTrue(page.verifyState(lState), "Unable to verify lead state..");
		assertTrue(page.verifyZip(lZip), "Unable to verify lead Zip..");
		assertTrue(page.verifyBeds(lBed), "Unable to verify lead beds..");
		assertTrue(page.verifyBaths(lBath), "Unable to verify lead baths..");
		assertTrue(page.verifySqFeet(lSqFeet), "Unable to verify lead square feet..");

		assertTrue(page.verifyLocationZipCode(dataObject.optString(DataConstants.Zip_Criteria)), "Unable to verify zip criteria..");
		assertTrue(page.verifyLocationCounty(dataObject.optString(DataConstants.County)), "Unable to verify County..");
		assertTrue(page.verifyLocationNeighborhood(dataObject.optString(DataConstants.Neighborhood)), "Unable to verify neighborhood..");
//		assertTrue(page.verifyLocationSchoolDistrict(dataObject.optString(DataConstants.SchoolDistrict)), "Unable to verify school district..");
		
		assertTrue(page.verifyBPMinPrice(dataObject.optString(DataConstants.MinPrice)), "Unable to verify minimum price..");
		assertTrue(page.verifyBPMaxPrice(dataObject.optString(DataConstants.MaxPrice)), "Unable to verify maximum price..");
		assertTrue(page.verifyBPBedsMin(dataObject.optString(DataConstants.Beds_Criteria)), "Unable to verify beds count..");
		assertTrue(page.verifyBPBathMin(dataObject.optString(DataConstants.Baths_Criteria)), "Unable to verify baths count..");
		assertTrue(page.verifyBPSqFeetMin(dataObject.optString(DataConstants.Squarefeet_Criteria)), "Unable to verify square feet criteria..");
		assertTrue(page.verifyBPLotSizeMin(dataObject.optString(DataConstants.LotSize)), "Unable to verify lot size..");
		assertTrue(page.verifyBPFeatures(dataObject.optString(DataConstants.Features)), "Unable to verify features..");
		assertTrue(page.verifyBPView(dataObject.optString(DataConstants.Views)), "Unable to verify views..");
		assertTrue(page.verifyBPStyle(dataObject.optString(DataConstants.Style)), "Unable to verify style..");
		
		AutomationLogger.endTestCase();
	}
	
	@Test
	@Parameters({"userSettings"})
	public void testUpdateLeadInfo(String pDataFile) {
		AutomationLogger.startTestCase("Verify lead email preferences");
		getPage();
//		String lLead = "3015660"
		String lLead = ModuleCommonCache.getElement(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId);
		getPage("/lead/edit/user_id/"+lLead);
		dataObject = getDataFile(pDataFile);

		String lCity = dataObject.optString(DataConstants.City);
		String lState = dataObject.optString(DataConstants.State);
		String lZip = dataObject.optString(DataConstants.Zip);     
	
		assertTrue(page.isLeadUpdatePage(), "Lead update page is not visible..");
		assertTrue(page.allHeadingsDisplayed(), "All headings are not displayed..");
		
		assertTrue(page.typeCity(lCity), "Unable to type lead city..");
		assertTrue(page.typeState(lState), "Unable to type Lead City..");
		assertTrue(page.typeZip(lZip), "Unable to type Lead City..");
		
		assertTrue(page.clickOnSaveButton(), "Unable to click on save button..");
		
		getPage("/lead/edit/user_id/"+lLead);
		
		assertTrue(page.verifyCity(lCity), "Unable to verify lead city..");
		assertTrue(page.verifyState(lState), "Unable to verify lead state..");
		assertTrue(page.verifyZip(lZip), "Unable to verify lead Zip..");
		
		assertTrue(page.clickOnSaveButton(), "Unable to click on save button..");
		
		assertTrue(new ZBOLeadDetailPage(driver).isLeadDetailPage(), "Lead Detail Page is not visible..");
		assertTrue(new ZBOLeadDetailPage(driver).isNotesEmpty(), "Lead notes are not empty..");
		
		AutomationLogger.endTestCase();
	}
	
	@Test
	public void testVerifySubscriptionUnsubscription() {
		getPage();
//		String lLead = "3015660"
		String lLead = ModuleCommonCache.getElement(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId);
		getPage("/lead/edit/user_id/"+lLead);
		assertTrue(page.toggleMassEmail(false), "Unable to toggle mass email checkbox..");
		assertTrue(page.toggleAgentEmail(false), "Unable to toggle Receive Agent email checkbox..");
		assertTrue(page.clickOnSaveButton(), "Unable to click on save button..");
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.verifyNavButtonIsDisabled("Send Email"), "Send Email button is not disabled..");
		assertTrue(leadDetailPage.verifyNavButtonIsDisabled("Send Text Message"), "Send Text Message button is not disabled..");
		assertTrue(leadDetailPage.verifyNavButtonIsDisabled("Enroll in Campaign"), "Enroll in Campaign button is not disabled..");
		assertTrue(leadDetailPage.verifyNavButtonIsDisabled("Send CMA Report"), "Send CMA Report button is not disabled..");
		assertTrue(leadDetailPage.clickOnMyMessagesTab(), "Unable to click on my messages tab..");
		ActionHelper.staticWait(5);
		assertTrue(leadDetailPage.isEnrollInCampaignButtonDisabled(), "Enroll in campaign button is not disabled in my messages tab..");
	}
	
	public void testUpdateLeadInfoForZapier(String pDataFile) {
		AutomationLogger.startTestCase("Verify lead email preferences");
		getPage();
		ZBOAddLeadPage manualLead = new ZBOAddLeadPage(driver);
		String lLead = ModuleCommonCache.getElement(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId);
		getPage("/lead/edit/user_id/"+lLead);
		dataObject = getDataFile(pDataFile);

		String Email = dataObject.optString("email");
		String lState = dataObject.optString("state");
		String lZip = dataObject.optString("zip");
		String lCity = dataObject.optString("city");
		String lCell = dataObject.optString("cell");
		String lPhone = dataObject.optString("phone");
	    Email = updateEmail(Email);
		assertTrue(page.isLeadUpdatePage(), "Lead update page is not visible..");
		assertTrue(page.allHeadingsDisplayed(), "All headings are not displayed..");
		
		assertTrue(manualLead.typeEmail(Email), "Unable to type lead email..");
		assertTrue(page.typeState(lState), "Unable to type Lead state..");
		assertTrue(page.typeZip(lZip), "Unable to type Lead zip..");
		assertTrue(page.typeCity(lCity), "Unable to type Lead City..");
		assertTrue(manualLead.typePhone(lPhone), "Unable to type Lead phone..");
		assertTrue(manualLead.typeCell(lCell), "Unable to type Lead cell..");
		assertTrue(page.clickOnSaveButton(), "Unable to click on save button..");
		updatedLeadData.put("email",Email);
		updatedLeadData.put("state",lState);
		updatedLeadData.put("zip",lZip);
		updatedLeadData.put("city",lCity);
		updatedLeadData.put("cell",lCell);
		updatedLeadData.put("phone",lPhone);

		AutomationLogger.endTestCase();
	}


}
