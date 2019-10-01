package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

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
		page = null;
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
		
		String lUpdatedUrl = driver.getCurrentUrl().replace("dashboard", "lead/"+lLeadId+"");
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
		assertTrue(page.verifySchoolDistrict(dataObject.optString(DataConstants.SchoolDistrict)), "Unable to verify School District in Notes..");
		assertTrue(page.verifyZipCode(dataObject.optString(DataConstants.Zip_Criteria)), "Unable to verify zip code in Notes..");
		assertTrue(page.verifyCounty(dataObject.optString(DataConstants.County)), "Unable to verify zip code in Notes..");
		assertTrue(page.verifyFeatures(dataObject.optString(DataConstants.Features)), "Unable to verify features in Notes..");
		assertTrue(page.verifyPropView(dataObject.optString(DataConstants.Views)), "Unable to verify views in Notes..");
		assertTrue(page.verifyPropStyle(dataObject.optString(DataConstants.Style)), "Unable to verify style in Notes..");

		AutomationLogger.endTestCase();
		
	}

}
