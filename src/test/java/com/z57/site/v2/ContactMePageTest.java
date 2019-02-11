/**
 * 
 */
package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.data.z57.RegisterUserData;
import resources.forms.z57.ContactMeForm;
import resources.forms.z57.LeadCaptureForm;
import resources.utility.FrameworkConstants;

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

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Parameters({"dataFile"})
	@Test
	public void testCaptureLeadFromContactMePage(String pFolderLocation) throws InterruptedException {
		getPage();
		RegisterUserData registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);

    	String lName=registerUserData.getUserName();
    	String lEmail=registerUserData.getUserEmail();
    	String lPhone=registerUserData.getUserPhoneNumber();
    	String lComments=registerUserData.getComments();
    	
		closeBootStrapModal();
		PageHeader pageHeader = new PageHeader(driver);
		assertTrue(pageHeader.clickOnContact(),"Unable to click on Contact Me under services menu.");
		contactMeCaptureLeadForm(lName, lEmail, lPhone, lComments);
		
		assertTrue(page.isContactMePage(), "Page Title not found");
		
	}
	
	private void contactMeCaptureLeadForm(String pName,String pEmail, String pPhone, String pComments) throws InterruptedException {

		PageHeader pageHeader = new PageHeader(getDriver());
		ContactMeForm contactMeForm = new ContactMeForm(driver);
		
		pName=updateName(pName);
    	assertTrue(contactMeForm.typeName(pName), "Name input field not visible. Unable to type");
    	
    	pEmail=updateEmail(pEmail);
    	assertTrue(contactMeForm.typeEmail(pEmail), "Email input field not visible. Unable to type");
    	
    	if(!pPhone.isEmpty()) {
    		assertTrue(contactMeForm.typePhoneNumber(pPhone), "Phone input field not visible. Unable to type");
    	}
    	if(!pComments.isEmpty()) {
    		assertTrue(contactMeForm.typeComments(pComments), "Comments input field not visible. Unable to type");
    	}
    	
    	assertTrue(contactMeForm.clickOnSendButton(),"Unable to click on Send button.");
    	
    	assertTrue(contactMeForm.isThankyouAlertVisible(),"Thankyou alert is not visible.");
    	
    	pageHeader.refreshPage();
    	
    	DBHelperMethods dbHelperObject = new DBHelperMethods(getEnvironment());
    	//Email Verification
    	 Cookie cks = getDriver().manage().getCookieNamed("zfs_lead_id");
         Integer lead_id = Integer.parseInt(cks.getValue());
         
    	assertTrue(dbHelperObject.verifyLeadInDB(pEmail,lead_id),"Unable to verify Lead in DB");
    	
    	assertTrue(dbHelperObject.verifyEmailIsSent(pEmail, FrameworkConstants.ThanksForConnecting), "Unable to sent email to Lead");

    	assertTrue(dbHelperObject.verifyEmailIsSent(EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email"), FrameworkConstants.YouHaveANewLead), "Unable to sent email to Agent");
    	        	
    	assertTrue(pageHeader.isLeadLoggedIn(),"Lead is not logged in");
	}



}
