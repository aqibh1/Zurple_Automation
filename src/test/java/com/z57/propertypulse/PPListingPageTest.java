/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.z57.site.v2.PropertyListingPage;
import com.zurple.PageTest;

import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.pp.GetMaximumListingExposureModal;
import resources.data.z57.PPListingData;
import resources.data.z57.PPMLSListingData;
import resources.orm.hibernate.models.z57.Listings;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.ValueMapper;

/**
 * @author adar
 *
 */

public class PPListingPageTest extends PageTest{
	WebDriver driver;
	PPListingPage page;
	PPHeader header;
	private PPListingData listingData;
	
	@Override
	public PPListingPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPListingPage(driver);
			header = new PPHeader(driver);
		}
		return page;
	}
	
	private PPListingPage getPage(String pURL) {
		if(page == null){
			driver = getDriver();
			page = new PPListingPage(driver);
			header = new PPHeader(driver);
			page.setUrl(pURL);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testAddListing(String pDataFile) {
		listingData = new PPListingData(pDataFile).getListingData();
		
		String lAddress = updateName(listingData.getAddress());
		String lStatus = listingData.getStatus();
		String lCity = listingData.getCity();
		String lState = listingData.getState();
		String lZip = listingData.getZip();
		String lCounty = listingData.getCounty();
		
		getPage("/listings");
		
		assertTrue(page.isListingPage(), "Listing Page is not visible");
		assertTrue(page.clickOnManualEntry(), "Unable to click on Manual Entry button");
		
		assertTrue(page.getAddListingForm().typeAddress(lAddress), "Unable to type Listing Address..");
		assertTrue(page.getAddListingForm().selectStatus(lStatus), "Unable to select Listing status..");
		assertTrue(page.getAddListingForm().typeCity(lCity), "Unable to type City..");
		assertTrue(page.getAddListingForm().selectState(lState), "Unable to select state..");
		assertTrue(page.getAddListingForm().typeZip(lZip), "Unable to type Zip..");
		assertTrue(page.getAddListingForm().typeCounty(lCounty), "Unable to type County..");
		assertTrue(page.getAddListingForm().clickOnContinueButton(), "Unable to click on Continue button..");
		
		PPListingDetailPage ppLeadDetailPage = new PPListingDetailPage(driver);
		assertTrue(ppLeadDetailPage.isListingDetailPage(), "Listing Detail Page is not visible");
		
		String lListingId = driver.getCurrentUrl().split("lid=")[1];
		AutomationLogger.info("Current URL :: "+driver.getCurrentUrl());
		AutomationLogger.info("Listing ID :: "+lListingId);
		
		//Listing URL Composing
		String lAddress2 = lAddress.replace(" ","-");
		String lCity2 = lCity.replace(" ","-");
		String listing_url = EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url")+"/listings/"+lListingId+"/"+lAddress2+"-"+lCity2;
		AutomationLogger.info("Listing URL :: "+listing_url);
		
		//Saving the listing urls to be used in listing editing
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(),"listing_url_wp", listing_url);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(),"listing_url_pp", driver.getCurrentUrl());
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(),"listing_id", lListingId);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ListingsAddress, lAddress);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ListingId, lListingId);

		//Verification of DB
		Listings listingObject = getEnvironment().getListingById(Integer.parseInt(lListingId));
		ValueMapper valueMapper = new ValueMapper();
		
		//Verification from website
		driver.navigate().to(listing_url);
		PropertyListingPage propertyListingPage = new PropertyListingPage(driver);
		assertTrue(propertyListingPage.isListingDetailPage(), "Listing Detail Page is not visible");
		
		closeBootStrapModal();
		
		if(!lStatus.isEmpty()) {
			assertTrue(propertyListingPage.getPropertyStatusFromHeader().equalsIgnoreCase(lStatus), "Status of Listing does not match.. Expected ["+lStatus+"]");
			assertTrue(listingObject.getStatusId().equals(valueMapper.getListingStatus(lStatus)), "DB Verification :: Status of Listing does not match.. Expected ["+lStatus+"]");
		}
		
		if(!lAddress.isEmpty()) {
			assertTrue(propertyListingPage.getAddress("Address").equalsIgnoreCase(lAddress), "ADDRESS of Listing does not match.. Expected ["+lAddress+"]");
			assertTrue(listingObject.getAddress().equalsIgnoreCase(lAddress), "DB Verification :: ADDRESS of Listing does not match.. Expected ["+lAddress+"]");
		}
		
		if(!lCity.isEmpty()) {
			assertTrue(propertyListingPage.getAddress("City").equalsIgnoreCase(lCity), "CITY of Listing does not match.. Expected ["+lCity+"]");
			assertTrue(listingObject.getCity().equalsIgnoreCase(lCity), "DB Verification :: CITY of Listing does not match.. Expected ["+lCity+"]");
		}
		
		if(!lState.isEmpty()) {
			assertTrue(propertyListingPage.getAddress("State").equalsIgnoreCase(valueMapper.getState(lState)), "STATE of Listing does not match.. Expected ["+lState+"]");
			assertTrue(listingObject.getState().equalsIgnoreCase(valueMapper.getState(lState)), "DB Verification :: STATE of Listing does not match.. Expected ["+lState+"]");

		}
		
		if(!lZip.isEmpty()) {
			assertTrue(propertyListingPage.getAddress("Zip").equalsIgnoreCase(lZip), "ZIP of Listing does not match.. Expected ["+lZip+"]");
			assertTrue(listingObject.getZip().equalsIgnoreCase(lZip), "DB Verification :: ZIP of Listing does not match.. Expected ["+lZip+"]");

		}
		
		if(!lCounty.isEmpty()) {
			assertTrue(propertyListingPage.getAddress("County").equalsIgnoreCase(lCounty), "COUNTY of Listing does not match.. Expected ["+lCounty+"]");
			assertTrue(propertyListingPage.getAddress("County").equalsIgnoreCase(lCounty), "COUNTY of Listing does not match.. Expected ["+lCounty+"]");

		}
		
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testAddListingForAd(String pDataFile) throws AWTException {
	listingData = new PPListingData(pDataFile).getListingData();
		
		String lAddress = updateName(listingData.getAddress());
		String lStatus = listingData.getStatus();
		String lCity = listingData.getCity();
		String lState = listingData.getState();
		String lZip = listingData.getZip();
		String lCounty = listingData.getCounty();
		String lListingImagesPath=listingData.getImagePath();
		
		getPage("/listings");
		
		ModuleCommonCache.setModuleCommonCache(ModuleCacheConstants.ListingsAddress, lAddress);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(), ModuleCacheConstants.ListingsAddress, lAddress);
		
		assertTrue(page.isListingPage(), "Listing Page is not visible");
		assertTrue(page.clickOnManualEntry(), "Unable to click on Manual Entry button");
		
		assertTrue(page.getAddListingForm().typeAddress(lAddress), "Unable to type Listing Address..");
		assertTrue(page.getAddListingForm().selectStatus(lStatus), "Unable to select Listing status..");
		assertTrue(page.getAddListingForm().typeCity(lCity), "Unable to type City..");
		assertTrue(page.getAddListingForm().selectState(lState), "Unable to select state..");
		assertTrue(page.getAddListingForm().typeZip(lZip), "Unable to type Zip..");
		assertTrue(page.getAddListingForm().typeCounty(lCounty), "Unable to type County..");
		assertTrue(page.getAddListingForm().clickOnContinueButton(), "Unable to click on Continue button..");
		
		PPListingDetailPage ppLeadDetailPage = new PPListingDetailPage(driver);
		assertTrue(ppLeadDetailPage.isListingDetailPage(), "Listing Detail Page is not visible");
		
		String lListingId = driver.getCurrentUrl().split("lid=")[1];
		ModuleCommonCache.setModuleCommonCache("listing_id_for_ad", lListingId);
		
		//Uploading listing images.
		PPListingDetailPage listingDetailPage = new PPListingDetailPage(driver);
		String[] images = lListingImagesPath.split(",");
		for(String image: images) {
			assertTrue(listingDetailPage.clickOnUploadIamgesButton(), "Unable to click on Upload button");
			assertTrue(listingDetailPage.getPpUploadImagesForm().isUploadImagesForm(), "Upload image form is not visible.");
			assertTrue(listingDetailPage.getPpUploadImagesForm().clickOnAddFilesButton(), "Unable to click on Add Files button");
			assertTrue(listingDetailPage.getPpUploadImagesForm().uploadImage(System.getProperty("user.dir")+image), "Unable to upload the image.");
		}		
		
		assertTrue(ppLeadDetailPage.clickOnSaveButton(), "Unable to click on Save button");
		
		assertTrue(page.getGetMaxListingExposure().isGextMaximumListingExposureAlert(), "Feature Listing Alert is not displayed");
		
		assertTrue(page.getGetMaxListingExposure().clickOnFeatureListing(), "Unable to click on Feature Listing button");

	}

	@Test
	@Parameters({"dataFile"})
	public void testAddListingByMLS(String pDataFile) {
		PPMLSListingData listingData = new PPMLSListingData(pDataFile).getListingData();
		
		String lMLS = listingData.getMls();//"190005539";
		String lMLS_board = listingData.getMls_board(); //"San DIego - SANDICOR"
		
		getPage("/listings");
		
		assertTrue(page.isListingPage(), "Listing Page is not visible");
		assertTrue(page.clickOnEasyImportFromMLS(), "Unable to click on Easy Import from MLS button");
		
		PPEasyImportListingPage easyImportListingPage = new PPEasyImportListingPage(driver);
		
		assertTrue(easyImportListingPage.selectIdxMLSBoard(lMLS_board), "Unable to select on MLS board");
		assertTrue(easyImportListingPage.clickOnMLSInputRadioButton(), "Unable to click on MLS input radio button");
		assertTrue(easyImportListingPage.typeMls(lMLS), "Unable to type MLS input field");
		assertTrue(easyImportListingPage.clickOnGenerateListButton(), "Unable to click on Generate List button.");
		assertTrue(easyImportListingPage.isListGeneratedSuccessfully(), "List is not generated successfully..");
		assertTrue(easyImportListingPage.clicOnSelectAllButton(), "Unable to click on Select All button..");
		assertTrue(easyImportListingPage.clickOnImportListingButton(), "Unable to click on Import Listing button..");
		assertTrue(easyImportListingPage.isLoaderDisappeared(), "Ajax loader is strill visible..");
		assertTrue(easyImportListingPage.isImportedSuccessfully(), "MLS listing is not imported successfully..");
		
		//Performing these steps to get Listing id
		GetMaximumListingExposureModal maximumListing = new GetMaximumListingExposureModal(driver);
		assertTrue(maximumListing.isGextMaximumListingExposureAlert(), "Maximum listing alert is not visible");
		assertTrue(maximumListing.clickOnFeatureListing(), "Unable to click on feature Listing");
		
		String lListingId = driver.getCurrentUrl().split("lsid=")[1];
		
		//To get listing address from the title. To compose non-idx listing URL
		String listing_url_idx = EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url")+"/idx/listings/cws/1098/"+lMLS;
		driver.navigate().to(listing_url_idx);
		
		PropertyListingPage propertyListingPage = new PropertyListingPage(driver);
		String lAddress = propertyListingPage.getAddress("Address").replace(" ","-");
		String lCity = propertyListingPage.getAddress("City").replace(" ","-");
		
		String listing_url = EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url")+"/listings/"+lListingId+"/"+lAddress.toLowerCase()+lCity.toLowerCase();
		driver.navigate().to(listing_url);
		
		assertTrue(propertyListingPage.isPropertyTitleVisible(), "Property Listing page is not visible..");
		
		driver.navigate().to(EnvironmentFactory.configReader.getPropertyByName("z57_pp_base_url")+"/listings");
		
		assertTrue(page.typeInpurSearch(lMLS), "Unable to type MLS input..");
		assertTrue(page.deleteListing(lListingId), "Unable to delete the listing..");
		
		driver.navigate().to(listing_url);
		assertFalse(propertyListingPage.isPropertyTitleVisible(), "Property Listing page is still visible after deleting the Listing..");

	}

	@Test
	public void testAddListingByAgentId() {

		getPage("/listings/import");
	
		PPEasyImportListingPage easyImportListingPage = new PPEasyImportListingPage(driver);
		assertTrue(easyImportListingPage.isEasyImportListingPage(), "Easy Import Listing page is not visible..");
		assertTrue(easyImportListingPage.clickOnGenerateListButton(), "Unable to click on Generate List button.");
		assertTrue(easyImportListingPage.isListGeneratedSuccessfully(), "List is not generated successfully..");
		assertTrue(easyImportListingPage.selectRandomListingFromList(), "Unable to select the listing..");
//		assertTrue(easyImportListingPage.clicOnSelectAllButton(), "Unable to click on Select All button..");
		ActionHelper.staticWait(5);
		assertTrue(easyImportListingPage.clickOnImportListingButton(), "Unable to click on Import Listing button..");
		ActionHelper.staticWait(5);
		assertTrue(easyImportListingPage.handleTheAlert(), "Unable to handle alert..");
		assertTrue(easyImportListingPage.isLoaderDisappeared(), "Ajax loader is strill visible..");
		assertTrue(easyImportListingPage.isImportedSuccessfully(), "MLS listing is not imported successfully..");
		
		String lMLS = easyImportListingPage.getListing_mls_id();
		
		//Performing these steps to get Listing id
		GetMaximumListingExposureModal maximumListing = new GetMaximumListingExposureModal(driver);
		if(maximumListing.isGextMaximumListingExposureAlert()) {
			AutomationLogger.info("Listing Exposure Modal is displayed");
		}else {
			AutomationLogger.error("Listing Exposure Modal is not  displayed");
		}
//		assertTrue(maximumListing.isGextMaximumListingExposureAlert(), "Maximum listing alert is not visible");
//		assertTrue(maximumListing.clickOnFeatureListing(), "Unable to click on feature Listing");
		
//		String lListingId = driver.getCurrentUrl().split("lsid=")[1];
		
		//To get listing address from the title. To compose non-idx listing URL
		String listing_url_idx = EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url")+"/idx/listings/cws/1098/"+lMLS;
		driver.navigate().to(listing_url_idx);
		
		PropertyListingPage propertyListingPage = new PropertyListingPage(driver);
		assertTrue(propertyListingPage.isPropertyTitleVisible(), "Property Listing page is not visible..");
		
	}
}
