package com.z57.site.v2;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import resources.AbstractPageTest;
import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.alerts.BootstrapModal;
import resources.forms.z57.ContactMeForm;
import resources.forms.z57.LeadCaptureForm;
import resources.forms.z57.OurCommunitySearchForm;
import resources.interfaces.TestHavingHeader;
import resources.interfaces.UsingPage;
import resources.orm.hibernate.models.AbstractLead;
import resources.orm.hibernate.models.z57.Lead;
import resources.utility.FrameworkConstants;

import java.util.Date;
import java.util.UUID;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

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

    @Test
    public void testRegisterNewRegularLead() {
    	//TODO
    	//If bootstrap model doesn't exist than it should be handled properly
    	
//        if(getPage().checkBootsrapModalIsShown()){
//            getPage().getBootstrapModal().close();
//            getPage().clearBootstrapModal();
//        }
    	BootstrapModal bootstrapModalObj = new BootstrapModal(getPage().getWebDriver());
        if(bootstrapModalObj.checkBootsrapModalIsShown()){
        	bootstrapModalObj.getBootstrapModal().close();
        	bootstrapModalObj.clearBootstrapModal();
        }

        assertEquals("Sign In",getPage().getUserMenu().getText());

        getPage().getUserMenu().click();
        assertTrue(bootstrapModalObj.checkBootsrapModalIsShown());

        String username = "test_regular_lead_" + UUID.randomUUID().toString();
        String email = username + "@test.com";
        String phone = "(212) 435-8762";

        getPage().getRegisterForm().setInputValue("top_bar_lead_reg_name",username);
        getPage().getRegisterForm().setInputValue("top_bar_lead_reg_email",email);
        getPage().getRegisterForm().setInputValue("top_bar_lead_reg_phone",phone);
        getPage().getRegisterForm().submit();
        //Browser will be redirected
        // And user info will be shown
        Wait<WebDriver> wait = new WebDriverWait(getDriver(), 10, 1000);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"user_menu_u\"]/div[contains(concat(\" \",normalize-space(@class),\" \"),\" menu_user_picture \")]")));

        Cookie cks = getDriver().manage().getCookieNamed("zfs_lead_id");
        Integer lead_id = Integer.parseInt(cks.getValue());

        //Checking created lead source
        //Checking DB record body
        AbstractLead newLead = getEnvironment().getLeadObject(lead_id);
        assertEquals(email,newLead.getEmail());

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
             
        	assertTrue(dbHelperObject.verifyLeadInDB(pEmail,lead_id),"Unable to verify Lead in DB");
        	
        	assertTrue(dbHelperObject.verifyEmailIsSent(pEmail, FrameworkConstants.ThanksForConnecting), "Unable to sent email to Lead");

        	assertTrue(dbHelperObject.verifyEmailIsSent(EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email"), FrameworkConstants.YouHaveANewLead), "Unable to sent email to Agent");
        	        	
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
         
    	assertTrue(dbHelperObject.verifyLeadInDB(pEmail,lead_id),"Unable to verify Lead in DB");
    	
    	assertTrue(dbHelperObject.verifyEmailIsSent(pEmail, FrameworkConstants.ThanksForConnecting), "Unable to sent email to Lead");

    	assertTrue(dbHelperObject.verifyEmailIsSent(EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email"), FrameworkConstants.YouHaveANewLead), "Unable to sent email to Agent");
    	        	
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
    protected String updateEmail(String pEmail) {
    	Date dateObj = new Date();
		long date_to_append=dateObj.getTime()/3600;
		int at = pEmail.indexOf('@');
		String firstPart = pEmail.substring(0, at);
		String lastPart = pEmail.substring(at);
		pEmail=firstPart+"_"+Long.toString(date_to_append)+lastPart;
		return pEmail;
    }
    
    protected String updateName(String pName) {
    	Date dateObj = new Date();
		long date_to_append=dateObj.getTime()/3600;
		pName=pName+" "+Long.toString(date_to_append);
		return pName;
    }
   
}
