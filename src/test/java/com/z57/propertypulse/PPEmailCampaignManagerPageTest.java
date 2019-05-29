package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import java.io.FileReader;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.utility.AutomationLogger;
import resources.utility.DataConstants;

public class PPEmailCampaignManagerPageTest extends PageTest{

	private WebDriver driver;
	private PPEmailCampaignManagerPage page;
	private PPEmailCampaignEditorListing emailCampaignEditorListing;
	private PPEmailCampaignEditorCustom emailCMCustom;;
	private JSONObject dataObject;
	
	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPEmailCampaignManagerPage(driver);
			emailCampaignEditorListing = new PPEmailCampaignEditorListing(driver);
			emailCMCustom = new PPEmailCampaignEditorCustom(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPEmailCampaignManagerPage(driver);
			emailCampaignEditorListing = new PPEmailCampaignEditorListing(driver);
			emailCMCustom = new PPEmailCampaignEditorCustom(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	@Parameters({"dataFileMarketing"})
	@Test
	public void testEmailCampaignManagerListing(String pDataFile) {
		
		AutomationLogger.startTestCase("testEmailCampaignManagerListing");
		
		dataObject = getDataFile(pDataFile);
		String lEmailSubjectLine = updateName(dataObject.optString(DataConstants.EmailSubjectLine));
		String lSelectListing = dataObject.optString(DataConstants.SelectListing);
		String lTitleInHeader = dataObject.optString(DataConstants.TitleHeader);
		String lGroup = dataObject.optString(DataConstants.Group);
		String lSelectIndividualLead = dataObject.optString(DataConstants.IndividualLead);
		String lSendTestEmailTo = dataObject.optString(DataConstants.SendTestEmailTo);
		String lDate = dataObject.optString(DataConstants.ScheduleDatePick);
		
		getPage("/marketing/campaigns/manager");
		
		//Go to Campaign Manager
		assertTrue(page.clickOnListingSubMenu(), "Unable to click on 'Create a new campaign' > Listing");
		assertTrue(emailCampaignEditorListing.isEmailCampaignManagerPage(), "Email Campaign Editor-Listing page is not visible..");
		assertTrue(emailCampaignEditorListing.selectListing(lSelectListing), "Unable to select the listing..");
		assertTrue(emailCampaignEditorListing.selectTitleInHeader(lTitleInHeader), "Unable to select Title in Header..");
		assertTrue(emailCampaignEditorListing.typeEmailSubject(lEmailSubjectLine), "Unable to type Email Subject Line..");
		
		setRecipientScheduleAndTest(lGroup, lSelectIndividualLead, lDate, lSendTestEmailTo,false);
		
		DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
		assertTrue(dbHelperMethods.verifyEmailIsSentToLead(lSendTestEmailTo, lEmailSubjectLine), "Test email is not sent to test email .."+lSendTestEmailTo);
		
		driver.navigate().to(EnvironmentFactory.configReader.getPropertyByName("z57_pp_base_url")+"/marketing/campaigns/scheduled");
	
		assertTrue(page.isEmailCampaignManagerPage(), "Email Campaign Manager is not visible..");
		assertTrue(page.typeInSearch(lEmailSubjectLine), "Unable to type in search input..");
		assertTrue(page.isListingScheduled(lEmailSubjectLine), "Listing is not scheduled..");
		
		AutomationLogger.endTestCase("testEmailCampaignManagerListing");
	}
	
	@Parameters({"dataFileMarketing"})
	@Test
	public void testEmailCampaignManagerCustom(String pDataFile) {
		AutomationLogger.startTestCase("testEmailCampaignManagerListing");
		
		dataObject = getDataFile(pDataFile);
		String lEmailSubjectLine = updateName(dataObject.optString(DataConstants.EmailSubjectLine));
		String lSelectTemplate = dataObject.optString(DataConstants.SelectTemplate);
		String lGroup = dataObject.optString(DataConstants.Group);
		String lSelectIndividualLead = dataObject.optString(DataConstants.IndividualLead);
		String lSendTestEmailTo = dataObject.optString(DataConstants.SendTestEmailTo);
		String lDate = dataObject.optString(DataConstants.ScheduleDatePick);
		
		getPage("/marketing/campaigns/manager");
		
		//Go to Campaign Manager
		assertTrue(page.clickOnCustomSubMenu(), "Unable to click on 'Create a new campaign' > Listing");
		
		assertTrue(emailCMCustom.isEmailCampaignManagerPage(), "Email Campaign Manager Custom page is not visible..");
		assertTrue(emailCMCustom.selectTemplate(lSelectTemplate), "Unable to select template from dropdown menu..");
		assertTrue(emailCMCustom.typeEmailSubject(lEmailSubjectLine), "Unable to type email subject line..");
		
		setRecipientScheduleAndTest(lGroup, lSelectIndividualLead, lDate, lSendTestEmailTo,true);
		
		DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
		assertTrue(dbHelperMethods.verifyEmailIsSentToLead(lSendTestEmailTo, lEmailSubjectLine), "Test email is not sent to test email .."+lSendTestEmailTo);
		
		driver.navigate().to(EnvironmentFactory.configReader.getPropertyByName("z57_pp_base_url")+"/marketing/campaigns/scheduled");
	
		assertTrue(page.isEmailCampaignManagerPage(), "Email Campaign Manager is not visible..");
		assertTrue(page.typeInSearch(lEmailSubjectLine), "Unable to type in search input..");
		assertTrue(page.isListingScheduled(lEmailSubjectLine), "Listing is not scheduled..");
		
		AutomationLogger.endTestCase("testEmailCampaignManagerListing");
	}
	
	/////////////////////////////////////////////////////////////
	//////////////////////HELPER METHODS/////////////////////////
	/////////////////////////////////////////////////////////////
	
	private void setRecipientScheduleAndTest(String pGroup, String pLead,String pDate, String pTestEmail, boolean pCustomCampaign) {
		if(pGroup!=null && !pGroup.isEmpty()) {
			
		}
		if(pLead!=null && !pLead.isEmpty()) {
			if(pCustomCampaign) {
				assertTrue(emailCMCustom.typeIndividualLead(pLead), "Unable to type lead email..");
			}else {
				assertTrue(emailCampaignEditorListing.typeIndividualLead(pLead), "Unable to type lead email..");
			}
			
		}
		assertTrue(emailCampaignEditorListing.typeAndAddDate(pDate), "Unable to type date and click add button..");
		assertTrue(emailCampaignEditorListing.typeTestEmail(pTestEmail), "Unable to type test email..");
		assertTrue(emailCampaignEditorListing.clickOnSendButton(), "Unable to click send button..");
	}

	
}
