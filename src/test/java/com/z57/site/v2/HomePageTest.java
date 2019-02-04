package com.z57.site.v2;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.z57.site.v2.HomePage;
import com.z57.site.v2.Page;
import com.z57.site.v2.PageTest;

import resources.ConfigReader;
import resources.alerts.BootstrapModal;
import resources.data.z57.RegisterUserData;
import resources.data.z57.SearchFormData;
import resources.forms.z57.LeadCaptureForm;
import resources.forms.z57.LoginForm;
import resources.forms.z57.RegisterForm;
import resources.orm.hibernate.models.AbstractLead;
import resources.orm.hibernate.models.z57.NotificationEmails;
import resources.orm.hibernate.models.z57.NotificationMailgun;
import resources.orm.hibernate.models.z57.Notifications;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.constraints.AssertTrue;

import org.openqa.selenium.Cookie;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class HomePageTest extends PageTest
{
	private RegisterUserData registerUserData;
    private HomePage page;

    public void clearPage(){
        page=null;
    };

    public Page getPage(){
    	ConfigReader configReader = ConfigReader.load();
        if(page == null){
            page = new HomePage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void testTitle() {
        assertEquals("San Diego Homes for Sale | zengtest1.us", getPage().getTitle());
    }

    public void testHeader() {
        Pattern pattern = Pattern.compile("\\d,\\d{3} HOMES FOR SALE IN SAN DIEGO, CA AND NEARBY");
        Matcher matcher = pattern.matcher(getPage().getHeader().getText());
        assertTrue(matcher.find());
    }


    public void testBrand() {
    	assertEquals("ZENG TEST PROPERTIES", getPage().getBrand().getText());    
    	}
    
//    @BeforeClass(groups="RegisterUser")
//	@Parameters({"dataFile"})
//	public void beforeClass(String pFolderLocation) throws JsonParseException, JsonMappingException, IOException {
//    	registerUserData = new RegisterUserData();
//    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);
//	}
   
    @Test
    public void testSignInWithValidEmail() {
    	ConfigReader configReader = ConfigReader.load();
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getPage().getWebDriver());
        
    	if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        	bootstrapModalObj.clearBootstrapModal();
        }

    	
    	LoginForm loginFormObj = new LoginForm(page.getWebDriver());
    	assertEquals("Sign In",getPage().getUserMenu().getText());
    	assertTrue(loginFormObj.clickOnSignInButton(),"Sign In button not visible on Home Page");
    	
    	RegisterForm registerFormObj = new RegisterForm(page.getWebDriver());
    	//Clicks on Already Registered
    	assertTrue(registerFormObj.clickOnAlreadyRegistered(),"Already registered link is not visible");

    	assertTrue(loginFormObj.setEmail(configReader.getPropertyByName("z57_user_v2")),"Unable to type email address");
    	assertTrue(loginFormObj.clickLoginButton(),"Unable to click on Login button");
    
    }
    @Parameters({"dataFile"})
    @Test
    public void testRegisterNewUser(String pFolderLocation) {
    	registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);
    	
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getPage().getWebDriver());

    	if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        	bootstrapModalObj.clearBootstrapModal();
        }
    	
    	LoginForm loginFormObj = new LoginForm(page.getWebDriver());
    	assertEquals("Sign In",getPage().getUserMenu().getText());
    	assertTrue(loginFormObj.clickOnSignInButton(),"Sign In button not visible on Home Page");
    	
    	assertTrue(registerLead(registerUserData.getUserName(),registerUserData.getUserEmail(),registerUserData.getUserPhoneNumber()), "The dialog didn't disappear after clicking Register button");
    	assertTrue(verifyLeadInDB(),"Lead not verified in DB");
      
    }
    @Parameters({"dataFile"})
    @Test
    public void testCaptureLead(String pFolderLocation) {
    	registerUserData = new RegisterUserData();
    	registerUserData = registerUserData.setRegisterUserData(pFolderLocation);
    	String captureLeadFrom = registerUserData.getCaptureLeadFrom();
    	String lName=registerUserData.getUserName();
    	String lEmail=registerUserData.getUserEmail();
    	String lPhone=registerUserData.getUserPhoneNumber();
    	String lComments=registerUserData.getComments();
    	
    	closeBootStrapModal();
    	
    	PageHeader pageHeader = new PageHeader(getDriver());
    	
    	assertFalse(pageHeader.isLeadLoggedIn(), "Lead is already logged in.");
    	
    	switch(captureLeadFrom){

    	case "Featured Properties":
    		assertTrue(pageHeader.clickOnFeaturedProperties(), "Unable to click on Feature Properties");
    		break;
    		
    	case "Sold Listings":
    		assertTrue(pageHeader.clickOnSoldListings(), "Unable to click on Sold Listings");
    		break;
    		
    	case "Search Homes":
    		assertTrue(pageHeader.clickOnSearchHomes(), "Unable to click on Search Homes");
    		break;
    		
    	case "Local Home Values":
    		assertTrue(pageHeader.clickOnLocalHomeValues(), "Unable to click on Local Home Values");
    		break;
    		
    	case "Community Reports":
    		assertTrue(pageHeader.clickOnCommunityReports(), "Unable to click on Community Reports");
    		break;
    		
    	case "School Reports":
    		assertTrue(pageHeader.clickOnSchoolReports(), "Unable to click on School Reports");
    		break;
    		
    	case "Whats Nearby":
    		assertTrue(pageHeader.clickOnWhatsNearby(), "Unable to click on What's Nearyby");
    		break;
    		
    	case "Real Estate Updates":
    		assertTrue(pageHeader.clickOnRealEstateUpdates(), "Unable to click on Real Estate updates");
    		break;
    		
    	case "Contact":
    		assertTrue(pageHeader.clickOnContact(), "Unable to click on Contact");
    		break;
    		
    	case "About":
    		assertTrue(pageHeader.clickOnAbout(), "Unable to click on About");
    		break;
    		
    	case "Buyers":
    		assertTrue(pageHeader.clickOnBuyers(), "Unable to click on Buyers");
    		break;
    		
    	case "Sellers":
    		assertTrue(pageHeader.clickOnSellers(), "Unable to click on Sellers");
    		break;
    		
    	default:
    		assertTrue(pageHeader.clickOnRealEstateUpdates(), "Unable to click on Real Estate updates");
    		break;
    	}
    	LeadCaptureForm leadCaptureForm = new LeadCaptureForm(getDriver());
    	
    	if(captureLeadFrom.equalsIgnoreCase("Search Homes") || captureLeadFrom.equalsIgnoreCase("Real Estate Updates") || captureLeadFrom.equalsIgnoreCase("Contact")
    			|| captureLeadFrom.equalsIgnoreCase("About") || captureLeadFrom.equalsIgnoreCase("Buyers") || captureLeadFrom.equalsIgnoreCase("Sellers")) {
    		
    		assertFalse(leadCaptureForm.isLeadCaptureFormVisible(), "Lead Capture Form is visible for "+captureLeadFrom);
    	}else {
    		assertTrue(leadCaptureForm.isLeadCaptureFormVisible(), "Lead Capture Form was not visible for "+captureLeadFrom);
        	
        	assertTrue(leadCaptureForm.typeName(lName), "Name input field not visible. Unable to type");
        	assertTrue(leadCaptureForm.typeEmail(lEmail), "Email input field not visible. Unable to type");
        	
        	if(!lPhone.isEmpty()) {
        		assertTrue(leadCaptureForm.typePhoneNumber(lPhone), "Phone input field not visible. Unable to type");
        	}
        	if(!lComments.isEmpty()) {
        		assertTrue(leadCaptureForm.typeComments(lComments), "Comments input field not visible. Unable to type");
        	}
        	
        	assertTrue(leadCaptureForm.clickOnSendButton(),"Unable to click on Send button.");
        	
        	assertTrue(pageHeader.isLeadLoggedIn(), "Lead is not logged in.");	
    		Integer notificationId=getNotificationId(lEmail);
    		getMailgunStatus(notificationId);
    		getNotifications(notificationId);
    		assertTrue(verifyLeadInDB(),"Unable to verify Lead in DB");
        	
    	}
    }
    
    
    //All the leads register related test cases will use this method.
    private boolean registerLead(String pName, String pUserEmail, String pPUserPhoneNumber) {
    	RegisterForm registerFormObj = new RegisterForm(page.getWebDriver());
    	if(!pName.isEmpty()) {
    		assertTrue(registerFormObj.setName(pName),"Unable to type Name in input field");
    	}
    	if(!pUserEmail.isEmpty()) {
    		assertTrue(registerFormObj.setEmail(pUserEmail),"Unable to type Email in input field");
    	}
    	if(!pPUserPhoneNumber.isEmpty()) {
    		assertTrue(registerFormObj.setPhoneNumber(pPUserPhoneNumber),"Unable to type Phone Number in input field");
    	}
    	assertTrue(registerFormObj.clickOnRegisterButton(),"Unable to click on Register button");
    	return registerFormObj.isUserSuccessfullyRegistered();
    }
    
    private boolean verifyLeadInDB() {
    	  Cookie cks = getDriver().manage().getCookieNamed("zfs_lead_id");
          Integer lead_id = Integer.parseInt(cks.getValue());

          //Checking created lead source
          //Checking DB record body
          AbstractLead newLead = getEnvironment().getLeadObject(lead_id);
          return registerUserData.getUserEmail().equalsIgnoreCase(newLead.getEmail());
    }
    
    private void getNotifications(Integer pNotificationId) {
    	Notifications notification_object = getEnvironment().getNotificationObject(pNotificationId);
    	System.out.println(notification_object.getEmail_subject()+"  "+notification_object.getSentDate());
    }
    
    private void getMailgunStatus(Integer pNotificationId) {
    	NotificationMailgun notification_mailgun_object = getEnvironment().getNotificationMailgunObject(pNotificationId);
    	System.out.println(notification_mailgun_object.getStatus());
    }
    private Integer getNotificationId(String pEmail) {
    	NotificationEmails not_email_obj = getEnvironment().getNotificationEmailsObject(pEmail);
    	System.out.println(not_email_obj.getNotificationId());
    	return not_email_obj.getNotificationId();
    }
    private void closeBootStrapModal() {
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getPage().getWebDriver());

    	if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        	bootstrapModalObj.clearBootstrapModal();
        }
    }
}
