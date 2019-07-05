package com.z57.site.v2;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.testng.annotations.Test;

import resources.AbstractPageTest;
import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.alerts.BootstrapModal;
import resources.forms.z57.ContactMeForm;
import resources.forms.z57.LeadCaptureForm;
import resources.forms.z57.OurCommunitySearchForm;
import resources.interfaces.TestHavingHeader;
import resources.interfaces.UsingPage;
import resources.orm.hibernate.models.z57.ListingImages;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

public abstract class PageTest extends AbstractPageTest  implements UsingPage, TestHavingHeader
{

    @Test(groups = { "asset" })
    public void testAssetsVersions() {
        assertTrue(checkAssetsVersion(getPage().getAssets()));
    }

    public abstract Page getPage();

    @Test
    public void testTopMenu() {
        assertEquals("Home",getPage().getTopMenu().findElement(By.xpath("//li[1]/a")).getText());
        assertEquals("Listings",getPage().getTopMenu().findElement(By.xpath("//li[2]/a")).getText());
        assertEquals("Home Search",getPage().getTopMenu().findElement(By.xpath("//li[3]/a")).getText());
        assertEquals("Our Community",getPage().getTopMenu().findElement(By.xpath("//li[4]/a")).getText());
        assertEquals("Services",getPage().getTopMenu().findElement(By.xpath("//li[5]/a")).getText().trim());
        assertEquals("Real Estate Updates",getPage().getTopMenu().findElement(By.xpath("//li[6]/a")).getText().trim());
    }
    
    public void captureLead(String pName,String pEmail, String pPhone, String pComments) {
    		PageHeader pageHeader = new PageHeader(getDriver());
    		LeadCaptureForm leadCaptureForm = new LeadCaptureForm(getDriver());
    		
    		assertTrue(leadCaptureForm.isLeadCaptureFormVisible(), "Lead Capture Form was not visible for ");
        	
    		pName=updateName(pName);
        	assertTrue(leadCaptureForm.typeName(pName), "Name input field not visible. Unable to type");
        	
        	pEmail=updateEmail(pEmail);
        	assertTrue(leadCaptureForm.typeEmail(pEmail), "Email input field not visible. Unable to type");
        	
        	if(!pPhone.isEmpty()) {
        		assertTrue(leadCaptureForm.typePhoneNumber(pPhone), "Phone input field not visible. Unable to type");
        	}
        	if(!pComments.isEmpty()) {
        		assertTrue(leadCaptureForm.typeComments(pComments), "Comments input field not visible. Unable to type");
        	}
        	
        	assertTrue(leadCaptureForm.clickOnSendButton(),"Unable to click on Send button.");
        	
        	assertTrue(pageHeader.isLeadLoggedIn(), "Lead is not logged in.");	
        	
        	
        	DBHelperMethods dbHelperObject = new DBHelperMethods(getEnvironment());
        	//Email Verification
        	 Cookie cks = getDriver().manage().getCookieNamed("zfs_lead_id");
             Integer lead_id = Integer.parseInt(cks.getValue());
             
        	assertTrue(dbHelperObject.verifyLeadInDB(pEmail,lead_id),"Unable to verify Lead in DB. Lead ID: "+lead_id+"\n Lead Email: "+pEmail);
        	
//        	assertTrue(dbHelperObject.verifyEmailIsSent(pEmail, FrameworkConstants.ThanksForConnecting), "Unable to sent email to Lead with subject "+FrameworkConstants.ThanksForConnecting);
        	assertTrue(dbHelperObject.verifyEmailIsSentToLead(pEmail, FrameworkConstants.ThanksForConnecting),"Unable to sent email to Lead with subject "+FrameworkConstants.ThanksForConnecting+"\n Lead Email: "+pEmail);
//        	assertTrue(dbHelperObject.verifyEmailIsSent(EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email"), FrameworkConstants.YouHaveANewLead), "Unable to sent email to Agent with subject "+FrameworkConstants.YouHaveANewLead);
        	assertTrue(dbHelperObject.verifyEmailIsSentToAgent(EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email"), pEmail, FrameworkConstants.YouHaveANewLead), "Unable to sent email to Agent with subject "+FrameworkConstants.YouHaveANewLead);

        	    	
    }
    
    public void contactMeCaptureLeadForm(String pName,String pEmail, String pPhone, String pComments) throws InterruptedException {

		PageHeader pageHeader = new PageHeader(getDriver());
		ContactMeForm contactMeForm = new ContactMeForm(getDriver());
		
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
         
     	assertTrue(dbHelperObject.verifyLeadInDB(pEmail,lead_id),"Unable to verify Lead in DB. Lead ID: "+lead_id+"\n Lead Email: "+pEmail);
    	
//    	assertTrue(dbHelperObject.verifyEmailIsSent(pEmail, FrameworkConstants.ThanksForConnecting), "Unable to sent email to Lead with subject "+FrameworkConstants.ThanksForConnecting);
    	assertTrue(dbHelperObject.verifyEmailIsSentToLead(pEmail, FrameworkConstants.ThanksForConnecting),"Unable to sent email to Lead with subject "+FrameworkConstants.ThanksForConnecting+"\n Lead Email: "+pEmail);
//    	assertTrue(dbHelperObject.verifyEmailIsSent(EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email"), FrameworkConstants.YouHaveANewLead), "Unable to sent email to Agent with subject "+FrameworkConstants.YouHaveANewLead);
    	assertTrue(dbHelperObject.verifyEmailIsSentToAgent(EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email"), pEmail, FrameworkConstants.YouHaveANewLead), "Unable to sent email to Agent with subject "+FrameworkConstants.YouHaveANewLead);
    	        	
    	assertTrue(pageHeader.isLeadLoggedIn(),"Lead is not logged in");
	}
    
    public void searchResultsFromCommunityPages(String pAddress, String pCity, String pState,String pZip) {
    	
    	OurCommunitySearchForm ourCommunitySearchForm = new OurCommunitySearchForm(getDriver());
		
		if(!pAddress.isEmpty()) {
			assertTrue(ourCommunitySearchForm.typeAddress(pAddress), "Unable to type address.");
		}
		if(!pCity.isEmpty()) {
			assertTrue(ourCommunitySearchForm.typeCity(pCity), "Unable to type City.");
		}
		if(!pZip.isEmpty()) {
			assertTrue(ourCommunitySearchForm.typeZip(pZip), "Unable to type Zip.");
		}
		if(!pState.isEmpty()) {
			assertTrue(ourCommunitySearchForm.selectState(pState), "Unable to select state.");
		}
		
		assertTrue(ourCommunitySearchForm.clickSubmitButton(),"Unable to Click on submit button");
		assertTrue(ourCommunitySearchForm.isSearchSuccessful(),"Search is not successful");
    }
    public void closeBootStrapModal() {
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getPage().getWebDriver());

    	if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        	bootstrapModalObj.clearBootstrapModal();
        }
    }
    
    public int getLeadId() {
  	  Cookie cks = getDriver().manage().getCookieNamed("zfs_lead_id");
        Integer lead_id = Integer.parseInt(cks.getValue());

        return lead_id;
  }
    public boolean verifyImagesFromDB(List<String> pListOfListingIds) {
    	boolean lIsSuccessful=true;
    	if(!pListOfListingIds.isEmpty()) {
    		for(String listingId: pListOfListingIds) {
    			DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
    			List<ListingImages> list_of_listing_images = dbHelperMethods.getListingImages(Integer.parseInt(listingId));
    			if(list_of_listing_images!=null && !list_of_listing_images.isEmpty()) {
    				AutomationLogger.error("Images found in Listing_Images table for Listing Id -> "+listingId);
    				lIsSuccessful = false;
    				break;
    			}
    		}
    	}
    	return lIsSuccessful;
    }
    public boolean closeBootStrapModal(WebDriver pWebDriver) {
    	return new BootstrapModal(pWebDriver).closeBootstrapModal();
    }
}
