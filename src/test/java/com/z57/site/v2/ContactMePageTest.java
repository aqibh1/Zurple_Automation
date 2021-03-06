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
public class ContactMePageTest extends PageTest{
	ContactMePage page;
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
			page = new ContactMePage(getDriver());
			page.setUrl("");
			page.setDriver(getDriver());
			driver=getDriver();
		}
		return page;
	}
	
	public Page getPage(String pUrl) {
		if(page == null){
			page = new ContactMePage(getDriver());
			page.setUrl(pUrl);
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
	public void testCaptureLeadFromContactMePage(String pFolderLocation) throws InterruptedException {
		getPage("/contact-me");
		RegisterUserData registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);

    	String lName=registerUserData.getUserName();
    	String lEmail=registerUserData.getUserEmail();
    	String lPhone=registerUserData.getUserPhoneNumber();
    	String lComments=registerUserData.getComments();
    	
		closeBootStrapModal();
//		PageHeader pageHeader = new PageHeader(driver);
//		assertTrue(pageHeader.clickOnContact(),"Unable to click on Contact Me under services menu.");
		contactMeCaptureLeadForm(lName, lEmail, lPhone, lComments);
		
		assertTrue(page.isContactMePage(), "Page Title not found");
		
	}

}
