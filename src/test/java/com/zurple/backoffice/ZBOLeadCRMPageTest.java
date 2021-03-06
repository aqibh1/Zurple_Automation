/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.CurrentPageTest;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.CacheFilePathsConstants;
import resources.utility.Mailinator;

/**
 * @author adar
 *
 */
public class ZBOLeadCRMPageTest extends PageTest{

	private ZBOLeadCRMPage page;
	private WebDriver driver;
	private JSONObject dataObject;
	
	public AbstractPage getPage() {
    	page=null;
    	if(page == null){
        	driver = getDriver();
			page = new ZBOLeadCRMPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
        }
        return page;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBOLeadCRMPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
        }
        return page;
    }
    public AbstractPage getPage(String pUrl, boolean pForcefully){
        if(pForcefully){
        	driver = getDriver();
			page = new ZBOLeadCRMPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
        }
        return page;
    }

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
	}
	
	@Test
	public void testAddAndDeleteNote() {
		getPage("/leads/crm");
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
		String lead_name_id = page.getLeadName();
		String l_leadName = lead_name_id.split(",")[0].trim();
		String l_leadId = lead_name_id.split(",")[1].trim();
		AutomationLogger.info("Lead ID::"+l_leadId);
		AutomationLogger.info("Lead ID::"+l_leadName);
		String l_comment = updateName("This is automated generated note");
		assertTrue(page.searchLead(l_leadName), "Unable to search lead..");
		assertTrue(page.clickOnNoteButton(), "Unable to click on Note button on CRM page..");
		assertTrue(page.getAddNoteForm().isAddNoteForm(), "Add Note form is not visible..");
		assertTrue(page.getAddNoteForm().typeComment(l_comment), "Unable to type not in comment section..");
		assertTrue(page.getAddNoteForm().clickOnSaveButton(), "Unable to click on Save button..");
		assertTrue(page.getAddNoteForm().getSuccessAlert().clickOnOkButton(), "Unable to click on Ok button..");
		ActionHelper.staticWait(5);
		assertTrue(page.clickOnNoteButton(), "Unable to click on Note button on CRM page..");
		assertTrue(page.getAddNoteForm().isCommentAddedSuccessfully(l_comment), "Unable to verify comment..");
		
		page = null;
		getPage("/lead/"+l_leadId);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.isLeadDetailPage(), "Lead Detail page is not visible..");
		ActionHelper.staticWait(5);
		assertTrue(leadDetailPage.verifyNoteAndTime(l_comment), "Unable to verify note and time in lead details page..");
		
		page = null;
		getPage("/leads/crm");
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
		ActionHelper.staticWait(5);
		assertTrue(page.searchLead(l_leadName), "Unable to search lead..");
		assertTrue(page.clickOnNoteButton(), "Unable to click on Note button on CRM page..");
		assertTrue(page.getAddNoteForm().isAddNoteForm(), "Add Note form is not visible..");
		assertTrue(page.getAddNoteForm().clickOnDeleteButton(), "Unable to click on delete button..");
		assertTrue(page.getAddNoteForm().confirmDeleteAlert(), "Unable to close the alert..");
		assertFalse(page.getAddNoteForm().isCommentAddedSuccessfully(l_comment), "Note is not deleted successfully..");
	}
	
	@Test
	public void testAddAndVerifyReminder() throws ParseException {
		getPage("/leads/crm");
		Mailinator mailinatorObj = new Mailinator(driver);
		if(getIsProd()) {
			mailinatorObj.activateProductionInbox();
		}else {
			mailinatorObj.activateStagingInbox();
		}
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
		
//		AutomationLogger.info("Applying filter to get valid lead");
//		String lFilterName = "By Agent,By Email Verification";
//		String lFilterValue = getIsProd()?"Aqib Production Testing,Valid Emails":"Aqib Site Owner,Valid Emails";
//		applyMultipleFilter(lFilterName, lFilterValue);
		
		String lead_name_id = page.getLeadName();
		String l_leadName = lead_name_id.split(",")[0].trim();
		String l_leadId = lead_name_id.split(",")[1].trim();
		AutomationLogger.info("Lead ID::"+l_leadId);
		AutomationLogger.info("Lead ID::"+l_leadName);
		String l_comment = updateName("Call the lead");
		assertTrue(page.searchLead(l_leadName), "Unable to search lead..");
		
		assertTrue(page.clickOnReminderButton(), "Unable to click on Note button on CRM page..");
		assertTrue(page.getAddReminderForm().isAddReminderForm(), "Add Reminder form is not visible..");
		assertTrue(page.getAddReminderForm().typeComment(l_comment), "Unable to type reminder in comment section..");
		assertTrue(page.getAddReminderForm().clickOnDateReminder(), "Unable to click on date Reminder button..");
		assertTrue(page.getAddReminderForm().clickOnDateDoneButton(), "Unable to click on date done button..");
		assertTrue(page.getAddReminderForm().clickOnSaveButton(), "Unable to click on Add Reminder button..");
		assertTrue(page.getAddReminderForm().getSuccessAlert().clickOnOkButton(), "Unable to click on Ok button..");
		ActionHelper.staticWait(5);
		ActionHelper.RefreshPage(driver);
		assertTrue(page.searchLead(l_leadName), "Unable to search lead..");
		assertTrue(page.verifyReminderNotification(1), "Unable to verify notification count on CRM page..");
		assertTrue(page.clickOnReminderButton(), "Unable to click on Note button on CRM page..");
		assertTrue(page.getAddReminderForm().isCommentAddedSuccessfully(l_comment), "Unable to verify comment..");
		
		page = null;
		getPage("/lead/"+l_leadId);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.isLeadDetailPage(), "Lead Detail page is not visible..");
		ActionHelper.staticWait(5);
		assertTrue(page.getAddReminderForm().isCommentAddedSuccessfully(l_comment), "Unable to verify comment..");
		
		String lAgentEmail = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_user").split("@")[0];
		String lSubjectToVerify = "Task Reminder - "+l_leadName;
		
		JSONObject lCacheObject = new JSONObject();
		lCacheObject.put("agent_email", lAgentEmail);
		lCacheObject.put("subject_to_verify", lSubjectToVerify);
		
		emptyFile(CacheFilePathsConstants.ReminderEmailCache, "");
		writeJsonToFile(CacheFilePathsConstants.ReminderEmailCache, lCacheObject);
		
//		assertTrue(mailinatorObj.verifyEmail(lAgentEmail, lSubjectToVerify, 15), "Unable to verify reminder email");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyReminderEmail(String pDataFile) {
		getPage();
		Mailinator mailinatorObj = new Mailinator(driver);
		dataObject = getDataFile(CacheFilePathsConstants.ReminderEmailCache);
		String ld_AgentEmail = dataObject.optString("agent_email");
		String ld_subject_to_verify = dataObject.optString("subject_to_verify");
		assertTrue(mailinatorObj.verifyEmail(ld_AgentEmail, ld_subject_to_verify, 5), "Unable to verify reminder email ["+ld_subject_to_verify+"]");
	}
	
	@Test
	public void testSendAndVerifyEmail() throws ParseException {
		page=null;
		getPage("/leads/crm");
		
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
		String lFilterName = "By Agent,By Email Verification,By Contact Preference";
		String lFilterValue = getIsProd()?"Aqib Production Testing,Valid Emails,Mass Emails: Yes":"Aqib Site Owner,Valid Emails,Mass Emails: Yes";
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		assertTrue(leadCRMPage.typeLeadEmailOnly("mailinator.com"), "Unable to type lead email..");
		applyMultipleFilters(lFilterName, lFilterValue);
		ActionHelper.staticWait(20);
		String lead_name_id = page.getLeadName();
		String lead_Email_phone = page.getEmail();
		String l_leadName = lead_name_id.split(",")[0].trim();
//		String crm_lead_id = getIsProd()?dataObject.optString("lead_id"):dataObject.optString("lead_id_stage");
//		String l_leadId = crm_lead_id;
		 String l_leadId = lead_name_id.split(",")[1].trim();
		 String l_lead_email = lead_Email_phone.contains("|")?lead_Email_phone.split(" ")[0]:lead_Email_phone.trim();
		String l_lead_phone = lead_Email_phone.contains("|")?lead_Email_phone.split(" ")[2]:"";
		
		AutomationLogger.info("Lead ID::"+l_leadId);
		AutomationLogger.info("Lead Name::"+l_leadName);
		AutomationLogger.info("Lead Email ::"+l_lead_email);
		AutomationLogger.info("Lead Phone::"+l_lead_phone);
		
		Mailinator mailinatorObj = new Mailinator(driver);
		mailinatorObj.activateLeadInbox(l_lead_email);
		
		ActionHelper.staticWait(10);
		
		assertTrue(page.searchLead(l_leadName), "Unable to search lead..");
		assertTrue(page.clickOnEmailButton(), "Unable to click on Note button on CRM page..");
		assertTrue(page.getSendEmailForm().isSendEmailForm(), "Send Email form is not visible..");
		assertTrue(page.getSendEmailForm().selectTemplate("Test Template"), "Unable to select template from drop down..");
		ActionHelper.staticWait(10);
		String l_subject = page.getSendEmailForm().getSubject();
		assertTrue(page.getSendEmailForm().clickOnSendEmailButton(), "Unable to click on send button....");
		ActionHelper.staticWait(10);
//		testVerifyEmailInMyMessages(l_leadId, l_subject);
//		assertTrue(mailinatorObj.verifyEmail(l_lead_email.split("@")[0], l_subject, 15), "Unable to verify reminder email");
//		ActionHelper.RefreshPage(driver);
//		ActionHelper.staticWait(10);
//		page = null;
//		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
//		getPage("/leads/crm");
//		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not visible..");
//		assertTrue(leadCRMPage.searchLead(l_leadName), "Unable to search lead");
//		assertTrue(leadCRMPage.verifyMassEmailCount(), "Unable to verify mass email count..");
		
		JSONObject cacheObject = new JSONObject();
		cacheObject.put("lead_id", l_leadId);
		cacheObject.put("email_subject", l_subject);
		cacheObject.put("lead_email", l_lead_email);
		cacheObject.put("lead_name", l_leadName);
		
		emptyFile(CacheFilePathsConstants.CRMEmailCache, "");
		writeJsonToFile(CacheFilePathsConstants.CRMEmailCache, cacheObject);
	}
	
	@Test
	public void testVerifyEmailIsSent() {
		page=null;
		getPage("/leads/crm");
		
		dataObject = getDataFile(CacheFilePathsConstants.CRMEmailCache);
		Mailinator mailinatorObj = new Mailinator(driver);
		
		// String crm_lead_id = getIsProd()?dataObject.optString("lead_id"):dataObject.optString("lead_id_stage");
		String ld_lead_id = dataObject.optString("lead_id");
		String ld_email_subject = dataObject.optString("email_subject");
		String ld_lead_email = dataObject.optString("lead_email");
		String ld_lead_name = dataObject.optString("lead_name");
		
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		testVerifyEmailInMyMessages(ld_lead_id, ld_email_subject);
		page=null;
		getPage("/leads/crm");
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not visible..");
		assertTrue(leadCRMPage.searchLead(ld_lead_name), "Unable to search lead");
		ActionHelper.staticWait(10);
		assertTrue(leadCRMPage.verifyMassEmailCount(), "Unable to verify mass email count..");
		
		assertTrue(mailinatorObj.verifyEmail(ld_lead_email.split("@")[0], ld_email_subject, 5), "Unable to verify email\n Lead ID::"+ld_lead_id+"\n Email Subject::"+ld_email_subject+"\n Lead Email::"+ld_lead_email);;
	}
	@Test
	public void testSendAndVerifySendSMS() throws ParseException {
		getPage("/leads/crm");
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
		applyFilter("By Phone Number Provided","All");
		ActionHelper.staticWait(25);
		assertTrue(page.clickOnSMSButton(), "Unable to click on SMS button on CRM page..");
		assertTrue(page.getSendSMSForm().isSendTextMessageForm(), "Send Text message form is not visible..");
		assertTrue(page.getSendSMSForm().getPhoneNumber(), "Unable to select template from drop down..");
		assertTrue(page.getSendSMSForm().clickOnSendButton(), "Unable to select template from drop down..");
		assertTrue(page.getSendSMSForm().isSuccessMessageVisible(), "Unable to select template from drop down..");
	}
	public void testVerifyEmailInMyMessages(String pLeadId, String pEmailSubject) {
		String lLeadId = pLeadId;		
		if(!getIsProd()) {
			//	Process email queue
			page=null;
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
		} 
		page = null;
		getPage("/lead/"+lLeadId);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.clickOnMyMessagesTab(), "Unable to click on my messages tab..");
		ActionHelper.staticWait(3);
		assertTrue(leadDetailPage.verifyMyMessagesEmails(pEmailSubject), "Email +["+pEmailSubject+ "]+ not found in my messages in lead details page");
}
	@Test
	@Parameters({"dataFile"})
	public void testVerifyPriorityRanking(String pDataFile) {
		getPage("/leads/crm");
		dataObject = getDataFile(pDataFile);
		//searching for lead who has contacted the agent
		String lLeadName = "Contact";//ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		String ld_priorityToVerify = dataObject.optString("priority_ranking");
		
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
		ActionHelper.staticWait(10);
		assertTrue(page.searchLeadContains(lLeadName), "Unable to search lead..");
		assertTrue(page.priorityRankingToVeify(ld_priorityToVerify), "Unable to verify the priority ranking");
		assertTrue(page.clickSearchedLeadName(), "Unable to click on lead name..");
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		ActionHelper.switchToSecondWindow(driver);
		ActionHelper.staticWait(3);
		assertTrue(leadDetailPage.verifyLeadPriorityRanking(ld_priorityToVerify), "Unable to verify lead priority ranking from lead details page");
	}
	
	/**
	 * @param pDataFile
	 * Verify that if lead phone number is not provided then phone icon is disabled
	 * 39792
	 */
	@Test
	@Parameters({"dataFile"})
	public void testPhoneIconIsDisabledIfPhoneNumberIsNotProvided(String pDataFile) {
		getPage();
		doLogin();
		ZBOAddLeadPageTest addLead = new ZBOAddLeadPageTest();
		addLead.testAddLead(pDataFile);
		getPage("/leads/crm",true);
		String l_lead_email = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		page.searchLeadByEmail(l_lead_email);
		assertTrue(page.verifyPhoneIconIsDisabled(), "Lead Phone Icon on CRM page is not disabled");
		new CurrentPageTest().closeBrowser();;
//		close.closeBrowser();
	}
	
	/**
	 * Verify that lead phone number icon is appearing on crm leads list
	 * 39791
	 */
	@Test
	public void testPhoneIconIsVisibleForLeads() {
		getPage();
		doLogin();
		getPage("/leads/crm",true);
		assertTrue(page.isPhoneIconVisibleForLeads(), "Lead Phone Icon on CRM page is not visible");
		new CurrentPageTest().closeBrowser();;
//		close.closeBrowser();
	}

	public void applyFilter(String pFilterName, String pFilterValue){
		ZBOLeadPage leadPage = new ZBOLeadPage(driver);
		assertTrue(leadPage.clickAndSelectFilterName(pFilterName),"Unable to select the filter type "+pFilterName);
		ActionHelper.staticWait(10);
		assertTrue(leadPage.clickAndSelectFilterValue(pFilterValue),"Unable to select the filter value "+pFilterValue);
		assertTrue(leadPage.clickOnSearchButton(),"Unable to click on search button..");
	}
	private void applyMultipleFilters(String pFilterName, String pFilterValue){
		ZBOLeadPage leadPage = new ZBOLeadPage(driver);
		String[] lFilterNameList = pFilterName.split(",");
		String[] lFilterValueList = pFilterValue.split(",");
		for(int i=0;i<lFilterNameList.length;i++) {
			assertTrue(leadPage.clickAndSelectFilterNameMultiple(lFilterNameList[i],Integer.toString(i+1)),"Unable to select the filter type "+lFilterNameList[i]);
			ActionHelper.staticWait(10);
			assertTrue(leadPage.clickAndSelectFilterValueMultiple(lFilterValueList[i],Integer.toString(i+1)),"Unable to select the filter value "+lFilterValueList[i]);
			if(i!=2) {
				assertTrue(page.clickOnAddFilterButton());
			}
		}
		assertTrue(leadPage.clickOnSearchButton(),"Unable to click on search button..");
	}
	private void doLogin() {
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
}
