/**
 * 
 */
package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.data.z57.RegisterUserData;

/**
 * @author adar
 *
 */
public class SchoolReportsPageTest extends PageTest{
	SchoolReportsPage page;
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
			page = new SchoolReportsPage(getDriver());
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
	public void testCaptureLeadFromSchoolReportsPage(String pFolderLocation) {
		getPage();
		RegisterUserData registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);

    	String lName=registerUserData.getUserName();
    	String lEmail=registerUserData.getUserEmail();
    	String lPhone=registerUserData.getUserPhoneNumber();
    	String lComments=registerUserData.getComments();
    	
		closeBootStrapModal();
		PageHeader pageHeader = new PageHeader(driver);
		assertTrue(pageHeader.clickOnSchoolReports(), "Unable to click on School Reports");
		captureLead(lName, lEmail, lPhone, lComments);
		
		assertTrue(page.isSchoolReportsPage(), "Page Title not found");
		assertTrue(page.isGoogleMapsDisplayed(), "Google map is not displayed on the page");
		
	}

}
