/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.my.PageTest;
import com.zurple.website.ZWRegisterUserPageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.Mailinator;

/**
 * @author adar
 *
 */
public class ZBOLeadCRMPageTest extends PageTest{

	private ZBOLeadCRMPage page;
	private WebDriver driver;
	
	public AbstractPage getPage() {
    	page=null;
    	if(page == null){
        	driver = getDriver();
			page = new ZBOLeadCRMPage(driver);
			page.setUrl("");
			page.setDriver(driver);
        }
        return page;
	}
    
    public AbstractPage getPage(String pUrl){
        if(page == null){
        	driver = getDriver();
			page = new ZBOLeadCRMPage(driver);
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
	public void testAddAndVerifyReminder() {
		getPage("/leads/crm");
		Mailinator mailinatorObj = new Mailinator(driver);
		if(getIsProd()) {
			mailinatorObj.activateProductionInbox();
		}else {
			mailinatorObj.activateStagingInbox();
		}
		
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
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
		
		assertTrue(mailinatorObj.verifyEmail(lAgentEmail, lSubjectToVerify, 15), "Unable to verify reminder email");
	}
	
	@Test
	public void testSendAndVerifyEmail() {
		getPage("/leads/crm");
		
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
		String lead_name_id = page.getLeadName();
		String lead_Email_phone = page.getEmail();
		String l_leadName = lead_name_id.split(",")[0].trim();
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
		assertTrue(page.getSendEmailForm().selectRandomTemplate(), "Unable to select template from drop down..");
		ActionHelper.staticWait(10);
		String l_subject = page.getSendEmailForm().getSubject();
		assertTrue(page.getSendEmailForm().clickOnSendEmailButton(), "Unable to click on send button....");
		testVerifyEmailInMyMessages(l_leadId, l_subject);
		assertTrue(mailinatorObj.verifyEmail(l_lead_email.split("@")[0], l_subject, 15), "Unable to verify reminder email");
		page = null;
		ZBOLeadCRMPage leadCRMPage = new ZBOLeadCRMPage(driver);
		getPage("/leads/crm");
		assertTrue(leadCRMPage.isLeadCRMPage(), "Lead CRM page is not visible..");
		assertTrue(leadCRMPage.searchLead(l_leadName), "Unable to search lead");
		assertTrue(leadCRMPage.verifyMassEmailCount(), "Unable to verify mass email count..");
	}
	
	@Test
	public void testSendAndVerifySendSMS() {
		getPage("/leads/crm");
		assertTrue(page.isLeadCRMPage(), "Lead CRM page is not visible..");
		ActionHelper.staticWait(15);
		assertTrue(page.clickOnSMSButton(), "Unable to click on SMS button on CRM page..");
		assertTrue(page.getSendSMSForm().isSendTextMessageForm(), "Send Text message form is not visible..");
		assertTrue(page.getSendSMSForm().getPhoneNumber(), "Unable to select template from drop down..");
		assertTrue(page.getSendSMSForm().clickOnSendButton(), "Unable to select template from drop down..");
		assertTrue(page.getSendSMSForm().isSuccessMessageVisible(), "Unable to select template from drop down..");
	}
	public void testVerifyEmailInMyMessages(String pLeadId, String pEmailSubject) {
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		String lLeadId = pLeadId;		
			if(!getIsProd()) {
			//	Process email queue
				page=null;
				getPage("/admin/processemailqueue");
				new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
				page = null;
				getPage("/lead/"+lLeadId);
			} else {
				page = null;
				getPage("/lead/"+lLeadId);
				page = null;
			}
			assertTrue(leadDetailPage.clickOnMyMessagesTab(), "Unable to click on my messages tab..");
			assertTrue(leadDetailPage.verifyMyMessagesEmails(pEmailSubject));
}
}
