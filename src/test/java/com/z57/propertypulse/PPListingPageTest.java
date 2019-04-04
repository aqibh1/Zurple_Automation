/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.z57.site.v2.PropertyListingPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCommonCache;
import resources.data.z57.PPListingData;
import resources.orm.hibernate.models.z57.Listings;
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
		ModuleCommonCache.setModuleCommonCache("listing_url_wp", listing_url);
		ModuleCommonCache.setModuleCommonCache("listing_url_pp", driver.getCurrentUrl());
		ModuleCommonCache.setModuleCommonCache("listing_id", lListingId);
		
		//Verification of DB
		Listings listingObject = getEnvironment().getListingById(Integer.parseInt(lListingId));
		ValueMapper valueMapper = new ValueMapper();
		
		//Verification from website
		driver.navigate().to(listing_url);
		PropertyListingPage propertyListingPage = new PropertyListingPage(driver);
		assertTrue(propertyListingPage.isListingDetailPage(), "Listing Detail Page is not visible");
		
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

	

}
