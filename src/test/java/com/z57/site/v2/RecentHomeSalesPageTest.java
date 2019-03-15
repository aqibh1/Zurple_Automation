/**
 * 
 */
package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.data.z57.RegisterUserData;
import resources.forms.z57.SearchSoldHomesForm;

/**
 * @author adar
 *
 */
public class RecentHomeSalesPageTest extends PageTest{
	
	RecentHomeSalesPage page;
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
			page = new RecentHomeSalesPage(getDriver());
			page.setUrl("");
			driver=getDriver();
			page.setDriver(driver);	
		}
		return page;
	}
	public Page getPage(String pURL) {
		if(page == null){
			page = new RecentHomeSalesPage(getDriver());
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
	public void testCaptureLeadFromRecentHomeSalesPage(String pDataFile1) {
		getPage();
		RegisterUserData registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pDataFile1);

    	String lName=registerUserData.getUserName();
    	String lEmail=registerUserData.getUserEmail();
    	String lPhone=registerUserData.getUserPhoneNumber();
    	String lComments=registerUserData.getComments();
    	
		closeBootStrapModal();
		PageHeader pageHeader = new PageHeader(driver);
		assertTrue(pageHeader.clickOnLocalHomeValues(), "Unable to click on Local Home Values");
		
		closeBootStrapModal();
		
		assertTrue(page.isGoogleMapDisplayed(), "The Google Map is not displayed on Listing Page");
		
		assertTrue(page.isRecentHomeSalesPage(), "Page Title not found");
		
		SearchSoldHomesForm searchSoldHomesForm = new SearchSoldHomesForm(driver);
		
		assertTrue(searchSoldHomesForm.typeZip("93216"),"Unable to type Zip");
		
		assertTrue(searchSoldHomesForm.clickSubmitButton(),"Unable to type Zip");
		
		captureLead(lName, lEmail, lPhone, lComments);
		
		assertTrue(page.isSearchSuccessful(), "The search was not successful");
		
	}
	
	@Test
	public void testVerifyPaginationOnSoldListingPage() {
		getPage("/recent-home-sales");
		if(page.getPagination().isPaginationAvailable()) {
			assertTrue(page.getPagination().verifyAllPaginationButtonsWorkingRHS(),"Pagination buttons not working on Listing Page");
			
		}else {
			assertTrue(true,"Pagination actions are not applicable on current page.");
		}
	}

}
