/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

/**
 * @author darrraqi
 *
 */
public class ZBOSendReportPageTest extends PageTest{

	private WebDriver driver;
	private ZBOSendCMAReportPage page;
	private JSONObject dataObject;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOSendCMAReportPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOSendCMAReportPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl, boolean pSetupForcefully) {
		if(page==null && !pSetupForcefully) {
			driver = getDriver();
			page = new ZBOSendCMAReportPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}else if(page!=null && pSetupForcefully){
			driver = getDriver();
			page = new ZBOSendCMAReportPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	
	@Override
	public void clearPage() {
	
	}
	
	@BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadNameIsDisplayedOnCMAReportPage(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		String l_lead_id = getIsProd()?dataObject.optString("prod_lead_id"):dataObject.optString("stage_lead_id");
		String l_lead_name = getIsProd()?dataObject.optString("prod_lead_name"):dataObject.optString("stage_lead_name");
		getPage("/cma/email/lead/"+l_lead_id,true);
		assertTrue(page.isLeadNameVisible(l_lead_name), "Lead name is not visible..");
	}
	
	@Test
	public void testVerifyPropertyHeadingIsDisplayedOnCMAReportPage() {
		getPage();
		assertTrue(page.verifyPropertyHeadingIsVisible(), "Property Heading is not visible..");
	}
	
	@Test
	public void testVerifyActiveListingHeadingIsDisplayedOnCMAReportPage() {
		getPage();
		assertTrue(page.verifyActivePropertyHeadingIsVisible(), "Active Listing Heading is not visible..");
	}
	
	@Test
	public void testVerifySoldListingHeadingIsDisplayedOnCMAReportPage() {
		getPage();
		assertTrue(page.verifySoldPropertyHeadingIsVisible(), "Sold Property Heading is not visible..");
	}
	
	@Test
	public void testVerifyAllFieldsAreDisplayedInPropertySection() {
		getPage();
		assertTrue(page.isAddressInputVisible(), "Address Input is not visible..");
		assertTrue(page.isCityInputVisible(), "City Input is not visible..");
		assertTrue(page.isStateInputVisible(), "State Input is not visible..");
		assertTrue(page.isZipInputVisible(), "Zip Input is not visible..");
		assertTrue(page.isBedsInputVisible(), "Bed Input is not visible..");
		assertTrue(page.isBathsInputVisible(), "Bath Input is not visible..");
		assertTrue(page.isMinPriceInputVisible(), "Minimum price Input is not visible..");
		assertTrue(page.isMaxPriceInputVisible(), "Maximum Input is not visible..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadAddressIsPopulatedInPropertySection(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_email = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		String l_street = dataObject.optString("street");
		String l_city = dataObject.optString("city");
		String l_state = dataObject.optString("state");
		String l_zip = dataObject.optString("zip");
		String l_beds = dataObject.optString("beds");
		String l_baths = dataObject.optString("baths");
		
		assertTrue(page.verifyAddress(l_street), "Unable to verify address of the lead "+lead_id+"\n Street "+l_street);
		assertTrue(page.verifyCity(l_city), "Unable to verify city of the lead "+lead_id+"\n City "+l_city);
		assertTrue(page.verifyState(l_state), "Unable to verify state of the lead "+lead_id+"\n State "+l_state);
		assertTrue(page.verifyZip(l_zip), "Unable to verify zip of the lead "+lead_id+"\n Zip "+l_zip);
		assertTrue(page.verifyBeds(l_beds.replace("+", "").trim()), "Unable to verify beds of the lead "+lead_id+"\n Beds "+l_beds);
		assertTrue(page.verifyBaths(l_baths.replace("+", "").trim()), "Unable to verify baths of the lead "+lead_id+"\n Baths "+l_baths);
	}

	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadAddressIsPopulatedInActiveListingSection(String pDataFile) {
		getPage();
		dataObject = getDataFile(pDataFile);
		String l_street = dataObject.optString("street");
		String l_city = dataObject.optString("city");
		String l_state = dataObject.optString("state");
		String l_zip = dataObject.optString("zip");
		String l_beds = dataObject.optString("beds");
		String l_baths = dataObject.optString("baths");
		String lAddress = l_street+", "+l_city+", "+l_state+" "+l_zip;
		
		assertTrue(page.isALAddressCheckboxChecked(), "Address checkbox is not checked..");
		assertEquals(page.getActiveListingAddress(),lAddress, "Unable to verify the address");
		assertEquals(page.getActiveListingBeds(),l_beds, "Unable to verify the active listings beds count");
		assertEquals(page.getActiveListingBaths(),l_baths, "Unable to verify the active listing baths count");
	}
	//Pre Condition
	private void addLead(String pDataFile) {
		try {
			ZBOAddLeadPageTest addleadpagetest = new ZBOAddLeadPageTest();
			addleadpagetest.testAddLead(pDataFile);
		}catch(Exception ex) {
			throw new SkipException("PreCondition failed. Unable to add lead..");
		}
	}
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
