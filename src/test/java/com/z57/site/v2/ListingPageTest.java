package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.data.z57.RegisterUserData;

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

}
