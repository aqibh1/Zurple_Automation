/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
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
import resources.utility.ActionHelper;

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
	
	//40320
	@Test
	@Parameters({"dataFile1"})
	public void testVerifyLeadCityIsPopulatedInActiveListingSection(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		String l_defaultCity = "San Diego";
		assertTrue(page.isALCityCheckboxChecked(), "City checkbox is not checked..");
		assertTrue(page.getActiveListingAddress().contains(l_defaultCity), "Unable to verify the City");
	}
	
	//40315
	@Test
	@Parameters({"dataFile1"})
	public void testVerifyAddressValidationIsWorking(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
//		getPage("/cma/email/lead/3022010",true);
		dataObject = getDataFile(pDataFile);
		assertTrue(page.typeMinPrice("10000"), "Unable to type minimum price..");
		assertTrue(page.typeMaxPrice("90000"), "Unable to type minimum price..");
		ActionHelper.staticWait(5);
		addActiveAndSoldListings();
		clickOnSubmitFormButton();
		assertTrue(page.isAddressAlertVisible(), "Unable to verify the City");
		assertTrue(page.isStateAlertVisible(), "Unable to verify the City");
		assertTrue(page.isZipAlertVisible(), "Unable to verify the City");
		assertTrue(page.isCityAlertVisible(), "Unable to verify the City");
	}
	//40316
	@Test
	@Parameters({"dataFile"})
	public void testVerifyPriceValidationIsWorking(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
//		getPage("/cma/email/lead/3022010",true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(5);
		addActiveAndSoldListings();
		clickOnSubmitFormButton();
		assertTrue(page.isMaxPriceAlertVisible(), "Maximum price alert is not visible..");
		assertTrue(page.isMinPriceAlertVisible(), "Minimum price alert is not visible..");
	}

	//C40326
	@Test
	@Parameters({"dataFile"})
	public void testVerifyErrorAlertIsDisplayedWhenActiveListingIsNotSelected(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(5);
		addSoldListings();
		clickOnSubmitFormButton();
		assertTrue(page.verifyActiveListingAlert(), "Active Listing Alert not visible..");
		assertTrue(page.isActiveListingSpanTurnsRed(), "Active Listing Span border doesn't turns red..");
		assertTrue(page.isSelectedListingsTurnsRed(), "Selected Listing heading doesn't turns red..");
		
	}
	
	@Test //C40346
	@Parameters({"dataFile"})
	public void testVerifyErrorAlertIsDisplayedWhenSoldListingIsNotSelected(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(5);
		addActiveListings();
		clickOnSubmitFormButton();
		assertTrue(page.verifySoldListingAlert(), "Sold Listing Alert not visible..");
		assertTrue(page.isSoldListingSpanTurnsRed(), "Sold Listing Span border doesn't turns red..");
		assertTrue(page.isSoldListingsTurnsRed(), "Sold Selected Listing heading doesn't turns red..");
	}
	
	@Test //C40342
	@Parameters({"dataFile"})
	public void testVerifyThreeSoldPropertiesAdded(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(10);
		addSoldListings();
		assertTrue(page.getSelectedSoldPropsCount()==3, "Sold Properties count is not 3..");
		assertTrue(page.getRemoveSoldPropsCount()==3, "Remove button fol sold listings not visible..");
		assertTrue(page.is3SoldPropsSelected(), "3/3 sold listings selected label is not visible..");
	}
	
	@Test //C40345
	@Parameters({"dataFile"})
	public void testVerifyAddButtonIsChangedToAddedTextSoldProps(String pDataFile) {
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(5);
		assertTrue(page.getSoldPropertiesAddedLabelCount()==3, "Add button is changed to Added label for Sold Properties");
		assertTrue(page.verifyAddedLabelIsNotClickableSoldProps(), "After Adding Sold Properties label is still clickable");
	}
	
	@Test //C40343
	@Parameters({"dataFile"})
	public void testVerifyErrorAlertWhenMoreThanThreeSoldPropsSelected(String pDataFile) {
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(5);
		assertTrue(page.clickOnNextButtonSoldProps(), "Unable to click on Next button for Sold Properties..");
		ActionHelper.staticWait(5);
		assertFalse(page.clickAddButtonFor3SoldListing(), "More than 3 Sold Props have been added..");
		assertTrue(page.getGenericAlert().isOnly3SoldListingAlertVisible(), "Only 3 Sold Listings can be added alert is not visible..");
		assertTrue(page.getGenericAlert().clickOnOkButton(), "Unable to click on OK button..");
	}
	
	@Test //C40344
	@Parameters({"dataFile"})
	public void testVerifySelectedSoldPropsCanBeRemoved(String pDataFile) {
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(5);
		isThreePropsSoldPropsSelected();
		assertTrue(page.removeSoldProperties(), "Unable to remove sold properties..");
	}
	
	@Test //C40328
	@Parameters({"dataFile"})
	public void testVerifyThreeActiveListingsAdded(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(10);
		addActiveListings();
		assertTrue(page.getSelectedActiveListingsCount()==3, "Active listing count is not 3..");
		assertTrue(page.getRemoveActiveListingsCount()==3, "Remove button fol Active listings not visible..");
		assertTrue(page.is3ActiveListingsSelected(), "3/3 Active listings selected label is not visible..");
	}
	
	@Test //C40331
	@Parameters({"dataFile"})
	public void testVerifyAddButtonIsChangedToAddedTextActiveListing(String pDataFile) {
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(5);
		assertTrue(page.getActiveListingsAddedLabelCount()==3, "Add button is changed to Added label for Active Listings");
		assertTrue(page.verifyAddedLabelIsNotClickable(), "After Adding Active Listings label is still clickable");
	}
	
	@Test //C40329
	@Parameters({"dataFile"})
	public void testVerifyErrorAlertWhenMoreThanThreeActiveListigsSelected(String pDataFile) {
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(5);
		assertTrue(page.clickOnNextButtonActiveProps(), "Unable to click on Next button for Active Listings..");
		ActionHelper.staticWait(5);
		assertFalse(page.clickAddButtonFor3ActiveListing(), "More than 3 Active Listings have been added..");
		assertTrue(page.getGenericAlert().isOnly3ActiveListingAlertVisible(), "Only 3 Active Listings can be added alert is not visible..");
		assertTrue(page.getGenericAlert().clickOnOkButton(), "Unable to click on OK button..");
	}
	
	@Test //C40330
	@Parameters({"dataFile"})
	public void testVerifySelectedActiveListingsCanBeRemoved(String pDataFile) {
		getPage();
		String lead_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(5);
		isThreeActiveListingsSelected();
		assertTrue(page.removeActiveListings(), "Unable to remove sold properties..");
	}
	
	@Test //C40321
	@Parameters({"dataFile"})
	public void testVerifyResultsAreDisplayedUnderActiveListingSection(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(2);
		assertTrue(page.isAtiveListingRadioOptionSelected(), "None option is selected for input search by default..");
		assertTrue(page.isActiveListingsDisplayed(), "Active listings are not displayed..");
	}
	
	@Test //C40322
	@Parameters({"dataFile"})
	public void testVerifyResultsAreDsiplayedByAddressUnderActiveListing(String pDataFile) {
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		String l_address = dataObject.optString("active_listing_search_address");
		ActionHelper.staticWait(2);
		assertTrue(page.selectAtiveListingRadioOption("Address"), "None option is selected for input search by default..");
		assertTrue(page.typeInputActiveListing(l_address), "Active listings are not displayed..");
		assertTrue(page.clickOnSearchButtonForActiveListing(), "Unable to click on search button..");
		page.waitForProcessingAlertToDisappear();
		assertTrue(page.verifyResultsOfActiveListing(l_address, "city"), "Unable to verify the city from the results.."+l_address);
		assertTrue(page.verifyResultsOfActiveListing(l_address, "state"), "Unable to verify the state from the results.."+l_address);
	}
	
	@Test //C40323
	@Parameters({"dataFile"})
	public void testVerifyResultsAreDsiplayedByCityUnderActiveListing(String pDataFile) {
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		String l_city = dataObject.optString("city");
		ActionHelper.staticWait(2);
		assertTrue(page.selectAtiveListingRadioOption("City"), "None option is selected for input search by default..");
		assertTrue(page.typeInputActiveListing(l_city), "Active listings are not displayed..");
		assertTrue(page.clickOnSearchButtonForActiveListing(), "Unable to click on search button..");
		page.waitForProcessingAlertToDisappear();
		assertTrue(page.verifyResultsOfActiveListing(l_city, "city"), "Unable to verify the city from the results.."+l_city);
	}
	
	@Test //C40324
	@Parameters({"dataFile"})
	public void testVerifyResultsAreDsiplayedByZipUnderActiveListing(String pDataFile) {
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		String l_zip = dataObject.optString("zip");
		ActionHelper.staticWait(2);
		assertTrue(page.selectAtiveListingRadioOption("Zip"), "None option is selected for input search by default..");
		assertTrue(page.typeInputActiveListing(l_zip), "Active listings are not displayed..");
		assertTrue(page.clickOnSearchButtonForActiveListing(), "Unable to click on search button..");
		page.waitForProcessingAlertToDisappear();
		assertTrue(page.verifyResultsOfActiveListing(l_zip, "zip"), "Unable to verify the zip from the results.."+l_zip);
	}
	
	@Test //C40325
	@Parameters({"dataFile"})
	public void testVerifyResultsAreDsiplayedByMLSUnderActiveListing(String pDataFile) {
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		String l_mls = dataObject.optString("mls");
		ActionHelper.staticWait(2);
		assertTrue(page.selectAtiveListingRadioOption("Zip"), "None option is selected for input search by default..");
		assertTrue(page.typeInputActiveListing(l_mls), "Active listings are not displayed..");
		assertTrue(page.clickOnSearchButtonForActiveListing(), "Unable to click on search button..");
		page.waitForProcessingAlertToDisappear();
		assertTrue(page.verifyMLSID(l_mls), "Unable to verify the MLS ID from the results.."+l_mls);
	}
	
	@Test //C40327
	@Parameters({"dataFile"})
	public void testVerifyResultsAreWithAllInformationActiveListing(String pDataFile) {
		getPage();
		assertTrue(page.verifyActiveListingResultsThumbnailsImageAreDisplayed(), "Unable to verify thumnails images of Active Listing from the results..");
		assertTrue(page.verifyActiveListingViewListingLink(), "Unable to verify View Listing Link of Active Listing from the results..");
		assertTrue(page.verifyActiveListingPropAddressIsDisplayed(), "Unable to verify property address of Active Listing from the results..");
		assertTrue(page.verifyActiveListingPriceIsDisplayed(), "Unable to verify Price of Active Listing from the results..");
		assertTrue(page.verifyActiveListingDistanceIsDisplayed(), "Unable to verify Distance of Active Listing from the results..");
		assertTrue(page.verifyActiveListingDetailIsDisplayed(), "Unable to verify details of Active Listing from the results..");

	}
	///////////////////////
	@Test //C40336
	@Parameters({"dataFile"})
	public void testVerifyResultsAreDisplayedUnderSoldPropertiesSection(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(10);
		assertTrue(page.isSoldPropsRadioOptionSelected(), "None option is selected for input search by default for Sold Props..");
		assertTrue(page.isSoldPropsDisplayed(), "Sold Props are not displayed..");
	}
	
	@Test //C40322
	@Parameters({"dataFile"})
	public void testVerifyResultsAreDsiplayedByAddressUnderSoldPropertiesSection(String pDataFile) {
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		String l_address = dataObject.optString("active_listing_search_address");
		ActionHelper.staticWait(2);
		assertTrue(page.selectSoldPropsRadioOption("Address"), "None option is selected for input search by default Sold Props..");
		assertTrue(page.typeInputSoldProps(l_address), "Sold Props are not displayed..");
		assertTrue(page.clickOnSearchButtonForSoldProps(), "Unable to click on search button Sold Props..");
		ActionHelper.staticWait(1);
		page.waitForProcessingAlertToDisappear();
		assertTrue(page.verifyResultsOfSoldProps(l_address, "city"), "Unable to verify the city from the results Sold Props.."+l_address);
		assertTrue(page.verifyResultsOfSoldProps(l_address, "state"), "Unable to verify the state from the results Sold Props.."+l_address);
	}
	
	@Test //C40323
	@Parameters({"dataFile"})
	public void testVerifyResultsAreDsiplayedByCityUnderSoldPropertiesSection(String pDataFile) {
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		String l_city = dataObject.optString("city");
		ActionHelper.staticWait(2);
		assertTrue(page.selectSoldPropsRadioOption("City"), "None option is selected for input search by default Sold Props..");
		assertTrue(page.typeInputSoldProps(l_city), "Sold Props are not displayed..");
		assertTrue(page.clickOnSearchButtonForSoldProps(), "Unable to click on search button Sold Props..");
		ActionHelper.staticWait(1);
		page.waitForProcessingAlertToDisappear();
		assertTrue(page.verifyResultsOfSoldProps(l_city, "city"), "Unable to verify the city from the results Sold Props.."+l_city);
	}
	
	@Test //C40324
	@Parameters({"dataFile"})
	public void testVerifyResultsAreDsiplayedByZipUnderSoldPropertiesSection(String pDataFile) {
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		String l_zip = dataObject.optString("zip");
		ActionHelper.staticWait(2);
		assertTrue(page.selectSoldPropsRadioOption("Zip"), "None option is selected for input search by default Sold Props..");
		assertTrue(page.typeInputSoldProps(l_zip), "Sold Props are not displayed..");
		assertTrue(page.clickOnSearchButtonForSoldProps(), "Unable to click on search button Sold Props..");
		ActionHelper.staticWait(1);
		page.waitForProcessingAlertToDisappear();
		ActionHelper.staticWait(10);
		assertTrue(page.verifyResultsOfSoldProps(l_zip, "zip"), "Unable to verify the zip from the results.."+l_zip);
	}
	
	@Test //C40327
	@Parameters({"dataFile"})
	public void testVerifyResultsAreWithAllInformationSoldPropertiesSection(String pDataFile) {
		getPage();
		assertTrue(page.verifySoldPropsPropAddressIsDisplayed(), "Unable to verify thumnails images of Sold Prop from the results..");
		assertTrue(page.verifySoldPropsPriceIsDisplayed(), "Unable to verify View Listing Link of Sold Prop from the results..");
		assertTrue(page.verifySoldPropsDistanceIsDisplayed(), "Unable to verify property address of Sold Prop from the results..");
		assertTrue(page.verifySoldPropsDetailIsDisplayed(), "Unable to verify Price of Sold Prop from the results..");
	}
	
	@Test //40349
	@Parameters({"dataFile"})
	public void testVerifyUserIsRedirectedToStep2WhenEmailDetailsButtonIsClicked(String pDataFile) {
		//Pre Condition
		addLead(pDataFile);
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		dataObject = getDataFile(pDataFile);
		ActionHelper.staticWait(10);
		fillStep1RequiredFields();
		assertTrue(page.isStep2Visible(), "Step 2 is not displayed....");
	}
	
	@Test //C40351
	public void testVerifyUserCanAddAdditionalCommentsStep2CMA() {
		//Pre Condition
		getPage();
		String lead_id =ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		getPage("/cma/email/lead/"+lead_id,true);
		ActionHelper.staticWait(2);
		assertTrue(page.typeAdditionalComments(updateName("additional comments")), "Step 2 is not displayed....");
	}
	//Pre Condition
	public void addLead(String pDataFile) {
		try {
			ZBOAddLeadPageTest addleadpagetest = new ZBOAddLeadPageTest();
			addleadpagetest.testAddLead(pDataFile);
		}catch(Exception ex) {
			throw new SkipException("PreCondition failed. Unable to add lead..");
		}
	}
	private void addActiveAndSoldListings() {
		if(!page.clickAddButtonFor3ActiveListing() || !page.clickAddButtonFor3SoldListing()) {
			throw new SkipException("PreCondition failed. Unable to add active and sold listings..");
		}
	}private void addSoldListings() {
		if(!page.clickAddButtonFor3SoldListing()) {
			throw new SkipException("PreCondition failed. Unable to add sold listings..");
		}
	}private void addActiveListings() {
		if(!page.clickAddButtonFor3ActiveListing()) {
			throw new SkipException("PreCondition failed. Unable to add active listings..");
		}
	}
	private void clickOnSubmitFormButton() {
		if(!page.clickOnSubmitButton()){
			throw new SkipException("PreCondition failed. Unable to click on Submit Form button..");

		}
	}
	private void isThreePropsSoldPropsSelected() {
		if(!page.is3SoldPropsSelected()) {
			throw new SkipException("PreCondition failed. 3 Sold Listings are not added..");
		}
	}
	private void isThreeActiveListingsSelected() {
		if(!page.is3ActiveListingsSelected()) {
			throw new SkipException("PreCondition failed. 3 Active Listings are not added..");
		}
	}
	private void fillStep1RequiredFields() {
		if(!page.typeMinPrice("10000") || !page.typeMaxPrice("90000")) {
			throw new SkipException("PreCondition failed. Unable to type price..");
		}
		addActiveAndSoldListings();
		if(!page.clickOnStep2SendEmailButton()) {
			throw new SkipException("PreCondition failed. Unable to type price..");
		}
	}
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
