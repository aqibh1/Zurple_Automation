package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.data.z57.RegisterUserData;
import resources.data.z57.SearchFormData;
import resources.utility.AutomationLogger;

public class ListingPageTest extends PageTest{
	ListingPage page;
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
			page = new ListingPage(getDriver());
			page.setUrl("");
			page.setDriver(getDriver());
			driver=getDriver();
		}
		return page;
	}
	public Page getPage(String pURL) {
		if(page == null){
			page = new ListingPage(getDriver());
			page.setUrl(pURL);
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
	public void testCaptureLeadFromListingPage(String pFolderLocation) {
		getPage();
		RegisterUserData registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);

    	String lName=registerUserData.getUserName();
    	String lEmail=registerUserData.getUserEmail();
    	String lPhone=registerUserData.getUserPhoneNumber();
    	String lComments=registerUserData.getComments();
    	
		closeBootStrapModal();
		PageHeader pageHeader = new PageHeader(driver);
		assertTrue(pageHeader.clickOnFeaturedProperties(), "Unable to click on Featured Properties");
		captureLead(lName, lEmail, lPhone, lComments);
		
		assertTrue(page.isGoogleMapDisplayed(), "The Google Map is not displayed on Listing Page");
		
		assertTrue(page.isListingPage(), "Page Title not found");
		
	}
	@Test
	public void testPaginationOnListingsPage() {
		getPage("/listings");
		if(page.getPagination().isPaginationAvailable()) {
			assertTrue(page.getPagination().verifyAllPaginationButtonsWorking(),"Pagination buttons not working on Listing Page");
			
		}else {
			assertTrue(true,"Pagination actions are not applicable on current page.");
		}
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testListingsRequestInfo(String pDataFile) {
		AutomationLogger.startTestCase("Home search Request Info");
		JSONObject lJsonDataObj = getDataFile(pDataFile);
		getPage("/listings");
		closeBootStrapModal();
		HomeSearchPage homeSearchPage = new HomeSearchPage(driver);
		assertTrue(homeSearchPage.clickOnRequestInfoButton(), "Unable to click on Request Info button...");
		HomeSearchPageTest homeSearchPageTest = new HomeSearchPageTest();
		homeSearchPageTest.setPageDriver(driver);
		homeSearchPageTest.requestInfoFormFill(lJsonDataObj);
		AutomationLogger.endTestCase();
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testListingsScheduleShowing(String pDataFile) {
		AutomationLogger.startTestCase("Home search Request Info");
		JSONObject lJsonDataObj = getDataFile(pDataFile);
		getPage("/listings");
		closeBootStrapModal();
		HomeSearchPage homeSearchPage = new HomeSearchPage(driver);
		assertTrue(homeSearchPage.clickOnScheduleShowingButton(), "Unable to click on Schedule Showing button...");
		HomeSearchPageTest homeSearchPageTest = new HomeSearchPageTest();
		homeSearchPageTest.setPageDriver(driver);
		homeSearchPageTest.scheduleShowingFormFill(lJsonDataObj);
		AutomationLogger.endTestCase();
	}

}
