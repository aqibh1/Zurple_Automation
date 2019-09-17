/**
 * 
 */
package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.data.z57.OurCommunityData;
import resources.data.z57.RegisterUserData;

/**
 * @author adar
 *
 */
public class WhatsNearbyPageTest extends PageTest{
	WhatsNearbyPage page;
	WebDriver driver;
	
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
		if(page == null){
			page = new WhatsNearbyPage(getDriver());
			page.setUrl("");
			page.setDriver(getDriver());
			driver=getDriver();
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testCaptureLeadFromWhatsNearbyPage(String pFolderLocation) {
		getPage();
		RegisterUserData registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);

    	String lName=registerUserData.getUserName();
    	String lEmail=registerUserData.getUserEmail();
    	String lPhone=registerUserData.getUserPhoneNumber();
    	String lComments=registerUserData.getComments();
    	
		closeBootStrapModal();
		PageHeader pageHeader = new PageHeader(driver);
		assertTrue(pageHeader.clickOnWhatsNearby(), "Unable to click on Whats nearby");
		captureLead(lName, lEmail, lPhone, lComments);
		
		assertTrue(page.isPointOfIntrestsPage(), "Page Title not found");
		
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testSearchAndVerifyStatsOnPOIPage(String pFolderLocation) {
		getPage();
		OurCommunityData ourCommunityData = new OurCommunityData();
		ourCommunityData=ourCommunityData.setOurCommunityData(pFolderLocation);
		
		String lAddress=ourCommunityData.getAddress();
		String lCity = ourCommunityData.getCity();
		String lState = ourCommunityData.getState();
		String lZip = ourCommunityData.getZip();
    	
		closeBootStrapModal(driver);
		
		PageHeader pageHeader = new PageHeader(driver);
		assertTrue(pageHeader.clickOnWhatsNearby(), "Unable to click on Whats Nearby");
		
		closeBootStrapModal(driver);
		
		assertTrue(page.isPointOfIntrestsPage(), "Page Title not found");
		searchResultsFromCommunityPages(lAddress, lCity, lState, lZip);
		
		PropertyListingPage propertyListing = new PropertyListingPage(driver);
		assertTrue(propertyListing.isWhatsNearbyDisplayed(),"Google Map is not displayed");
		assertTrue(page.isResultsCorrect(lState), "POI inrests are not correct");
		assertTrue(page.verifyPagination(), "Pagination is not working for POI Page");
		
		
	}



}
