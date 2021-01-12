/**
 * 
 */
package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.data.z57.EmailListingFormData;
import resources.data.z57.RegisterUserData;
import resources.forms.z57.SearchSoldHomesForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

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
    	
    	ActionHelper.staticWait(5);
		closeBootStrapModal();
		
		PageHeader pageHeader = new PageHeader(driver);
		assertTrue(pageHeader.clickOnLocalHomeValues(), "Unable to click on Local Home Values");
		
		ActionHelper.staticWait(5);
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
	@Parameters({"dataFile"})
	public void testRecentHomeSalesEmailSearch(String pDataFile2) {
		getPage("/recent-home-sales");
		
		EmailListingFormData emailListingFormData = new EmailListingFormData(pDataFile2).getEmailListingData();
		
		String lLeadName = updateName(emailListingFormData.getLeadName());
		String lLeadEmail = updateEmail(emailListingFormData.getLeadEmail());
		String lLeadPhone = emailListingFormData.getLeadPhoneNumber();
		String lR1Name = updateName(emailListingFormData.getRecipientOneName());
		String lR1Email = updateEmail(emailListingFormData.getRecipientOneEmail());
		String lR2Name = updateName(emailListingFormData.getRecipientTwoName());
		String lR2Email = updateEmail(emailListingFormData.getRecipientTwoEmail());
		String lAgent_email = EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email");
	
		assertTrue(page.isRecentHomeSalesPage(), "Recent Home Sales page is not visible");
		
		PageHeader pageHeader = new PageHeader(driver);
		boolean isLeadLoggedIn=pageHeader.isLeadLoggedIn();
		
		assertTrue(page.clickOnEmailSearchButton(), "Unable to click on email search button");
		
		assertTrue(page.getEmailSearchForm().isEmailSearchModalVisible(),"Email Listing Modal is not visible");		

		String lSenderEmail = lLeadEmail;

		if(isLeadLoggedIn) { 
			//Verify the name of the lead is in respective fields 
			lSenderEmail = lLeadEmail = EnvironmentFactory.configReader.getPropertyByName("z57_user_v2");
			assertTrue(page.getEmailSearchForm().getLeadNameRHS().isEmpty(), "Lead is logged in but No Name in Email Listing form" );
			assertTrue(page.getEmailSearchForm().getLeadEmailRHS().isEmpty(), "Lead is logged in but No Name in Email Listing form" );

		}else {

			assertTrue(page.getEmailSearchForm().typeLeadNameRHS(lLeadName),"Unable to type name in Lead Name field");

            assertTrue(page.getEmailSearchForm().typeEmailAddressRHS(lSenderEmail),"Unable to type email in Lead email field");
			if(!lLeadPhone.isEmpty()) {
				assertTrue(page.getEmailSearchForm().typePhoneNumberRHS(lLeadPhone),"Unable to type phone in Lead phone number field"); 
			} 
		}

		assertTrue(page.getEmailSearchForm().typeR1NameRHS(lR1Name),"Unable to write the name oof Recipient 1");
		assertTrue(page.getEmailSearchForm().typeR1EmailRHS(lR1Email),"Unable to write the email of Recepient 1");

		assertTrue(page.getEmailSearchForm().typeR2NameRHS(lR2Name),"Unable to write the name oof Recipient 2");
		assertTrue(page.getEmailSearchForm().typeR2EmailRHS(lR2Email),"Unable to write the email of Recepient 2");
		
		//Added this manual delay because of the findings that form is submitted before text is typed
		ActionHelper.staticWait(5);
		
		assertTrue(page.getEmailSearchForm().clickOnSendButtonRHS(),"Unable to click on Send button");
		assertTrue(page.getEmailSearchForm().isEmailSentRHS(),"After clicking Send button 'Email this Listing' modal is still visible");

		DBHelperMethods dbHelper = new DBHelperMethods(getEnvironment());
		
		assertTrue(dbHelper.verifyLeadByEmailInDB(lLeadEmail), "User is not added as Lead in PP ->" + lLeadEmail);
		assertTrue(dbHelper.verifyLeadByEmailInDB(lR1Email), "Recipient1 is not added as Lead in PP ->" + lR1Email);
		assertTrue(dbHelper.verifyLeadByEmailInDB(lR2Email), "Recipient2 is not added as Lead in PP ->" + lR2Email);

		// Verifies the email has been sent on respective email addresses.
		if(!isLeadLoggedIn) {
			//Verify 'Thanks For Connecting' email is sent to lead
            assertTrue(dbHelper.verifyEmailIsSentToLead(lLeadEmail, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Sender");
            //Verify 'You have a new lead' email is sent to Agent.
            assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lLeadEmail),"Unable to sent email to Agent for ->" + lLeadEmail);
		}

		assertTrue(dbHelper.verifyEmailIsSent(lR1Email, FrameworkConstants.CheckOutThisPropertySearch),"Unable to sent 'Checkout Property' email to Recipient1");
		assertTrue(dbHelper.verifyEmailIsSent(lR2Email, FrameworkConstants.CheckOutThisPropertySearch),"Unable to sent 'Checkout Property' email to Recipient2");
		assertTrue(dbHelper.verifyEmailIsSentToLead(lR2Email, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Recipient2");
		assertTrue(dbHelper.verifyEmailIsSentToLead(lR1Email, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Recipient1");

        if ( !lSenderEmail.equals(lLeadEmail) )
        {
            assertTrue(dbHelper.verifyEmailIsSentToLead(lSenderEmail, FrameworkConstants.ThanksForConnecting),"Unable to sent 'Thanks for connecting' email to Changed Sender");
        }
        //Verify 'You have a new lead' email is sent to Agent.
		assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lR1Email),"Unable to sent email to Agent for ->" + lR1Email);
		assertTrue(dbHelper.verifyEmailIsSentToAgent(lAgent_email, lR2Email),"Unable to sent email to Agent for ->" + lR2Email);
		
	}
	
	@Test
	public void testVerifyPaginationOnSoldListingPage() {
		getPage("/recent-home-sales");
		if(page.getPagination().isPaginationAvailableRHS()) {
			assertTrue(page.getPagination().verifyAllPaginationButtonsWorkingRHS(),"Pagination buttons not working on Listing Page");
			
		}else {
			assertTrue(true,"Pagination actions are not applicable on current page.");
		}
	}

}
