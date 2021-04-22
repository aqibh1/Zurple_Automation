package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.ZBOLeadDetailPage;

import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.DataConstants;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

public class ZWAccountSettingsPageTest extends PageTest{
	
	private WebDriver driver;
	private ZWAccountSettingsPage page;
	private JSONObject dataObject;
	
	@Override
	public void testTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testBrand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page getPage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Page getPage(String pUrl) {
		page = null;
		if(page == null){
			driver = getDriver();
			page = new ZWAccountSettingsPage(driver);
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
	public void testUpdateAccountSettings(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		getPage("/my");
		String lEmail= updateEmail(dataObject.optString(DataConstants.Email));
		if(!getIsProd()) {
			lEmail = lEmail.replace("@", "_ZurpleQA@");
		}
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail, lEmail);
		
		assertTrue(page.isMyAccountPage(),"My Account page is not visible..");
		assertTrue(page.clickOnChangeEmail(),"Unable to click on Change Email link button..");
		assertTrue(page.typeEmailAddrss(lEmail), "Unable to type email address..");
		assertTrue(page.clickOnUpdateEmailButton(), "Unable to click on updat email button..");
		assertTrue(page.isChangedEmailSuccessful(lEmail), "Email is not updated in preferences..");
		
		assertTrue(page.clickOnChangeAddress(), "Unable to click on change address button..");
		assertTrue(page.typeStreet(dataObject.optString(DataConstants.Street)), "Unable to type street..");
		assertTrue(page.typeCity(dataObject.optString(DataConstants.City)), "Unable to type city..");
		assertTrue(page.typeState(dataObject.optString(DataConstants.State)), "Unable to type state..");
		assertTrue(page.typeZip(dataObject.optString(DataConstants.Zip)), "Unable to type zip..");
		assertTrue(page.selectBeds(dataObject.optString(DataConstants.Beds)), "Unable to select bed from dropdown..");
		assertTrue(page.selectBath(dataObject.optString(DataConstants.Baths)), "Unable to select baths from dropdown..");
		assertTrue(page.selectSquareFeet(dataObject.optString(DataConstants.Squarefeet)), "Unable to select bed from dropdown..");
		assertTrue(page.clickOnUpdateAddressButton(), "Unable to click on update address button..");
		assertTrue(page.isChangedAddressSuccessful(dataObject.optString(DataConstants.Street), 
				dataObject.optString(DataConstants.City), 
				dataObject.optString(DataConstants.State), 
				dataObject.optString(DataConstants.Zip)), "address not changed successfully..");
		
		assertTrue(page.clickOnChangeCriteriaLink(), "Unable to click on Change criteria link");
		assertTrue(page.typeAndSelectZip(dataObject.optString(DataConstants.Zip_Criteria)), "Unable to type and select zip..");
		assertTrue(page.typeAndSelectNeighborhood(dataObject.optString(DataConstants.Neighborhood)), "Unable to type and select zip..");
//		assertTrue(page.typeAndSelectSchoolDistrict(dataObject.optString(DataConstants.SchoolDistrict)), "Unable to type and select school district");
		assertTrue(page.isDrawButtonsVisible(),"Draw buttons not visble..");
		assertTrue(page.typeAndSelectCounty(dataObject.optString(DataConstants.County)),"Unable to type and select county..");
		assertTrue(page.selectMinPrice(dataObject.optString(DataConstants.MinPrice)),"Unable to select minimum price..");
		assertTrue(page.selectMaxPrice(dataObject.optString(DataConstants.MaxPrice)),"Unable to select maximum price..");
		assertTrue(page.selectMinBedrooms(dataObject.optString(DataConstants.Beds_Criteria)),"Unable to select minimum number of beds..");
		assertTrue(page.selectMinBathrooms(dataObject.optString(DataConstants.Baths_Criteria)),"Unable to select minimum number of baths..");
		assertTrue(page.selectSquareFeetCriteria(dataObject.optString(DataConstants.Squarefeet_Criteria)),"Unable to select square feet criteria..");
		assertTrue(page.selectLotSize(dataObject.optString(DataConstants.LotSize)),"Unable to select lot size..");
		assertTrue(page.selectFeatures(dataObject.optString(DataConstants.Features).split(",")),"Unable to select features..");
		assertTrue(page.selectView(dataObject.optString(DataConstants.Views).split(",")),"Unable to select views..");
		assertTrue(page.selectStyle(dataObject.optString(DataConstants.Style).split(",")),"Unable to select style..");
		assertTrue(page.clickCriteriaUpdateButton(), "Unable to click on update criteria button..");
		
		assertTrue(page.clickOnChangeCriteriaLink(), "Unable to click on Change criteria link");
		assertTrue(page.selectPropertyType(dataObject.optString(DataConstants.PropertyTypes).split(",")), "Unable to type and select zip..");
		assertTrue(page.clickPropertyTypeUpdateButton(), "Unable to click on update property type button..");

		assertTrue(page.verifyChangeCriteriaText("Price: min",dataObject.optString(DataConstants.MinPrice)), "Unable to verify updated min price..");
		assertTrue(page.verifyChangeCriteriaText("max:",dataObject.optString(DataConstants.MaxPrice)), "Unable to verify updated max price..");
		assertTrue(page.verifyChangeCriteriaText("Bed:",dataObject.optString(DataConstants.Beds_Criteria).replace("+", "")), "Unable to verify updated beds criteria..");
		assertTrue(page.verifyChangeCriteriaText("Bath:",dataObject.optString(DataConstants.Baths_Criteria).replace("+", "")), "Unable to verify updated baths criteria..");
		assertTrue(page.verifyChangeCriteriaText("Sq. Ft.:",dataObject.optString(DataConstants.Squarefeet_Criteria)), "Unable to verify updated squarefeet criteria..");
		assertTrue(page.verifyChangeCriteriaText("Lot Size:",dataObject.optString(DataConstants.LotSize)), "Unable to verify updated lot size..");
		assertTrue(page.verifyChangeCriteriaText("Features:",dataObject.optString(DataConstants.Features)), "Unable to verify updated features..");
		assertTrue(page.verifyChangeCriteriaText("View:",dataObject.optString(DataConstants.Views)), "Unable to verify updated views");
		assertTrue(page.verifyChangeCriteriaText("Style:",dataObject.optString(DataConstants.Style)), "Unable to verify updated style");
		assertTrue(page.verifyChangeCriteriaText("Property Type:",dataObject.optString(DataConstants.PropertyTypes)), "Unable to verify updated property types");

	}
	
	@Test
	@Parameters({"userSettings"})
	public void testVerifyAccountSettings(String pDataFile) {
		//getPage("/login");
		getPage("/my");
		dataObject = getDataFile(pDataFile);
		String lEnv = "";
		String lEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		if(lEnv.equalsIgnoreCase("prod")) {

		}else {
			lEmail = lEmail.replace("@", "_ZurpleQA@");
		}
		//new ZWLoginPage(driver).doLogin(lEmail);
		
		assertTrue(page.isAddressUpdated("", 
				dataObject.optString(DataConstants.City), 
				dataObject.optString(DataConstants.State), 
				dataObject.optString(DataConstants.Zip)), "address not changed successfully..");
	}
	
	@Test
	public void testVerifyEmailsSubscriptionStatus() {
		getPage("/my");
		assertTrue(page.isMyAccountPage(), "Account Setting page is not visible..");
		assertTrue(page.verifySubscriptionUnsubscriptionStatus("Agent Emails", "Unsubscribed"));
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyPropertyUpdatesEmailsSubscriptionStatus(String pDataFile) {
		getPage("/my");
		String l_lead_id = getLeadIDPreCondition();
		dataObject = getDataFile(pDataFile);
		assertTrue(page.isMyAccountPage(), "Account Setting page is not visible..");
		clickOnChangeEmailSubscriptionLinkPreCond();
		togglePropertyUpdatePreCond(dataObject.optBoolean("property_updates"));
		clickUpdateButtonEmailSubscriptionPreCond();
		String prop_update_sub = dataObject.optBoolean("property_updates")?"Subscribed":"Unsubscribed";
		String prop_update_bo = dataObject.optBoolean("property_updates")?"Yes":"No";
		assertTrue(page.verifySubscriptionUnsubscriptionStatus("New Property Updates", prop_update_sub));
		verificationFromBackOffice(l_lead_id, "Property Updates", prop_update_bo);
	}
	
	private void verificationFromBackOffice(String pLeadId, String pPrefToVerify, String pPrefValueToVerify) {
		String l_lead_detais_url = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+pLeadId;
		driver.navigate().to(l_lead_detais_url);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.verifyEmailPreferences(pPrefToVerify, pPrefValueToVerify), "Unable to verify lead email preference"+pLeadId);
	}
	public void clickOnChangeEmailSubscriptionLinkPreCond() {
		if(!page.clickOnEmailSubscriptionLink()) {
			throw new SkipException("Unable to click on Change Email Link button [Skipping]");
		}
	}public void togglePropertyUpdatePreCond(boolean pToggle) {
		if(!page.clickOnPropUpdateToggleBuuton(pToggle)) {
			throw new SkipException("Unable to toggle Property Update button [Skipping]");
		}
	}public void clickUpdateButtonEmailSubscriptionPreCond() {
		if(!page.clickOnUpdateButtonEmailSubscription()) {
			throw new SkipException("Unable to click on Update button [Skipping]");
		}
	}public String getLeadIDPreCondition() {
		String l_lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		if(l_lead_id.isEmpty() || l_lead_id==null) {
			throw new SkipException("Lead is not registered. Lead id is empty [Skipping]");
		}
		return l_lead_id;
	}
}
