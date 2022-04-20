/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.backoffice.marketing.ZBOMarketingEmailMessagePage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.orm.hibernate.models.zurple.Email;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.CacheFilePathsConstants;
import resources.utility.GmailEmailVerification;

/**
 * @author adar
 *
 */
public class ZBOMarketingEmailPageTest extends PageTest{

	ZBOMarketingEmailMessagePage page;
	private WebDriver driver;
	ZBOLeadDetailPage leadDetailPage;
	GmailEmailVerification gmailObject = new GmailEmailVerification();
	String lToEmail;
	String flyerSubject;
	String emailSubject;
	String bulkEmailSubject;
	String leadReplySubject;
	String mlsID;
	long lWaitTime = 0;
	
	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		if(page==null) {
			driver = getDriver();
			page = new ZBOMarketingEmailMessagePage(driver);
			page.setUrl("");
			page.setDriver(driver);
			leadDetailPage = new ZBOLeadDetailPage(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOMarketingEmailMessagePage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
			leadDetailPage = new ZBOLeadDetailPage(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	@Parameters({"listingEmailFlyerData"})
	public void testSendEmailListingFlyer(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		page=null;
		getPage("/marketing/massemail");
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.selectRecipients(lDataObject.optString("recipients")), "Unable to select the recipients...");
		verifyEmailListingFlyer(lDataObject);
		if(!getIsProd()) {
			page=null;
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
		} 
		JSONObject cacheObject = new JSONObject();
		cacheObject.put("email_subject", flyerSubject);
		emptyFile(CacheFilePathsConstants.EmailListingFlyerCache, "");
		writeJsonToFile(CacheFilePathsConstants.EmailListingFlyerCache, cacheObject);
	}
	@Test
	@Parameters({"dataFile"})
	public void testVerifyEmailListingFlyer(String pDataFile) {
		page=null;
		getPage();
		JSONObject lCacheObject = getDataFile(CacheFilePathsConstants.EmailListingFlyerCache);
		String l_email_subject = lCacheObject.optString("email_subject");
		int lLeadId = new DBHelperMethods(getEnvironment()).getEmailBySubject(lCacheObject.optString("email_subject")).getUser();	
		assertTrue(testVerifyEmailInMyMessages(l_email_subject,String.valueOf(lLeadId)), "Unable to verify listing flyer email");
	}
	@Test
	@Parameters({"standardEmailData"})
	public void testSendStandardEmail(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		page=null;
		getPage("/marketing/massemail");
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.selectRecipients(lDataObject.optString("recipients")), "Unable to select the recipients...");
		fillStandardEmailForm(lDataObject);
		System.out.println("This is email subject: "+emailSubject);
		if(!getIsProd()) {
			page=null;
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
		} 
		JSONObject cacheObject = new JSONObject();
		cacheObject.put("email_subject", emailSubject);
		emptyFile(CacheFilePathsConstants.StandardEmailCache, "");
		writeJsonToFile(CacheFilePathsConstants.StandardEmailCache, cacheObject);
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyStandardEmail(String pDataFile) {
		page=null;
		getPage();
		JSONObject lDataObject = getDataFile(pDataFile);
		JSONObject lCacheObject = getDataFile(CacheFilePathsConstants.StandardEmailCache);
		assertTrue(testVerifyEmailInMyMessages(lDataObject, lCacheObject.optString("email_subject")), "Unable to verify standard email in my messages ["+lCacheObject.optString("email_subject")+"]");
	}
	
	@Test
	@Parameters({"standardEmailData"})
	public void testSendBulkEmail(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		redirectToLeadsPage(lDataObject);
		leadStatus(lDataObject, 0);
		page = null;
		getPage("/marketing/massemail");
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.selectRecipients(lDataObject.optString("recipient_bulkemail")), "Unable to select the recipients...");
		fillStandardEmailForm(lDataObject);
		
		JSONObject cacheObject = new JSONObject();
		cacheObject.put("email_subject", bulkEmailSubject);
		emptyFile(CacheFilePathsConstants.BulkEmailCache, "");
		writeJsonToFile(CacheFilePathsConstants.BulkEmailCache, cacheObject);
		if(!getIsProd()) {
			page=null;
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
		} 
//		System.out.println("This is bulk email subject: "+bulkEmailSubject);
//		testVerifyEmailInMyMessages(lDataObject, bulkEmailSubject);
		
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyBulkEmail(String pDataFile) {
		page=null;
		getPage();
		JSONObject lDataObject = getDataFile(pDataFile);
		JSONObject lCacheObject = getDataFile(CacheFilePathsConstants.BulkEmailCache);
		assertTrue(testVerifyEmailInMyMessages(lDataObject, lCacheObject.optString("email_subject")), "Unable to verify standard email in my messages ["+lCacheObject.optString("email_subject")+"]");
		assertTrue(leadStatus(lDataObject, 1), "Unable to change the lead status ");
	}
	@Test
	@Parameters({"standardEmailData"})
	public void testSendScheduledStandardEmail(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		getPage("/marketing/massemail");
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.selectRecipients(lDataObject.optString("recipients")), "Unable to select the recipients...");
		fillStandardEmailForm(lDataObject);
		System.out.println("This is email subject: "+emailSubject);
		JSONObject cacheObject = new JSONObject();
		cacheObject.put("subject", emailSubject);
		emptyFile(CacheFilePathsConstants.ScheduledEmailCache, "");
		writeJsonToFile(CacheFilePathsConstants.ScheduledEmailCache, cacheObject);
		
		assertTrue(isEmailShowingInScheduledEmails(lDataObject, emailSubject), "Email is not showing up under scheduled email section..");
//		testVerifyScheduledEmailInMyMessages(lDataObject, emailSubject);
	}
	@Test
	@Parameters({"dataFile"})
	public void testVerifyScheduledEmailFromMyMessages(String pDataFile) {
		page=null;
		getPage();
		JSONObject lDataObject = getDataFile(pDataFile);
		JSONObject lCacheObject = getDataFile(CacheFilePathsConstants.ScheduledEmailCache);
		testVerifyScheduledEmailInMyMessages(lDataObject, lCacheObject.optString("subject"));
	}
	@Test
	@Parameters({"emailReplyData"})
	public void testPUNS(String pDataFile) {
//		JSONObject lDataObject = getDataFile(pDataFile);
		new ZBOMarketingEmailPageDBTest().testPUNSFromDB();
//    	boolean isSuccessful = gmailObject.isPUNSEmailPresent("auto.zurpleqa@gmail.com", "djfbxtfkdnlczaec", 
//    			"New Listing Updates", "aqibstagetesting_zurpleqa@stage01.zengtest6.us", true);
//    	assertTrue(isSuccessful, "PUNS email not sent");
	}
	
	@Test
	public void testVerifyLeadReplies() {
		String subject = getIsProd()?"Quick Question":"[stage01] Quick Question";
		boolean isSuccessful = gmailObject.isEmailPresentAndReply("z57testuser.zurpleqa@gmail.com", "uznhhalkthskjpyx", subject, "aqib.zurple.production@zengtest2.us",true);
    	assertFalse(isSuccessful, "Lead reply email not sent");
	}
	
	@Test(dependsOnGroups = {"com.zurple.backoffice.ZBOCreateTemplatePageTest.testCreateTemplate"})
	public void testVerifyTemplateExists() {
		getPage("/marketing/massemail");
		String lTemplateName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleTemplateName);
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.isTemplateExists(lTemplateName), "Template does not exist in Mass email drop down..");	
	}
	
	/**
	 * Verify "Email listing Flyer" Button name is now updated to "Send Listings"
	 * 48815
	 */
	@Test
	public void testVerifySendListingButtonIsVisible() {
		getPage("/marketing/massemail");
		assertTrue(page.isEmailListingFlyerButtonVisible(), "Send Listing Button is not visible");
	}
	
	/**
	 * Verify Subject field is visible once Send Listings Button is clicked
	 * 48816
	 */
	@Test
	public void testVerifyListingEmailSubjectIsVisibleWhenSendListingIsClciked() {
		getPage("/marketing/massemail");
		assertTrue(page.clickOnEmailListingFlyer(), "Unable to click on 'Send Listings' button");
		assertTrue(page.isListingSubjectVisible(), "Subject input field is not visble");
	}
	
	/**
	 * Verify Search By options are visible and clickable
	 * 48817
	 */
	@Test
	public void testVerifySearchOptionsAreVisibleForListingEmail() {
		getPage("/marketing/massemail");
		assertTrue(page.isAddressRadioButtonVisible(), "Address radio button is not visible..");
		assertTrue(page.isCityRadioButtonVisible(), "City radio button is not visible..");
		assertTrue(page.isMLSRadioButtonVisible(), "MLS radio button is not visible..");
		assertTrue(page.isZipRadioButtonVisible(), "Zip radio button is not visible..");
	}
	
	/**
	 * Verify correct Search option is visible when respective filter is clicked
	 * 48818
	 */
	@Test
	public void testSearchAndVerifyListingsByMLSID() {
		getPage("/marketing/massemail");
		assertTrue(page.clickOnMLSRadioButton(), "Unable to click on MLS ID radio button");
		String mls_id = EnvironmentFactory.configReader.getPropertyByName("zurple_mls_id").toString();
		assertTrue(page.typeInputField(mls_id), "Unable to type MLS ID");
		assertTrue(page.clickOnSearchButton(), "Unable to click on search button");
		ActionHelper.staticWait(10);
		assertTrue(page.isListingWithMLSIDPresent(mls_id), "Listing is not present in search results");
	}
	
	/**
	 * Verify validation message is displayed if no Subject is provided and Next button is clicked
	 * 48853
	 */
	@Test
	public void testVerifyValidationMessageIsDisplayedIfNoSubjectIsProvided() {
		getPage("/marketing/massemail");
		assertTrue(page.clickOnNextButton(), "Unable to click on next button..");
		assertTrue(page.isSubjectValidationMessageVisible(), "Subject Validation message is not visble");
	}
	
	/**
	 * Verify Validation message is displayed if no listing is added and Next button is clicked
	 * 48843
	 */
	@Test
	public void testVerifyValidationMessageIsDisplayedIfNoListingIsSelected() {
		getPage("/marketing/massemail");
		String l_email_subject = updateName("Listing Email");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject, l_email_subject);
		assertTrue(page.typeListingEmailSubject(l_email_subject), "Unable to type email subject");
		assertTrue(page.clickOnNextButton(), "Unable to click on next button..");
		assertTrue(page.isSelectListingValidationMessageVisible(), "Select listing Validation message is not visble");
	}
	
	/**
	 * Verify added listings can be removed by clicking on "x" button
	 * 48844
	 */
	@Test
	public void testVerifyUserCanRemoveAddedListing() {
		getPage("/marketing/massemail");
		assertTrue(page.clickOnAddListingButton(), "Unable to click on Add listing button");
		assertTrue(page.clickOnRemoveButton(), "Unable to click on remove listing button");
	}
	
	/**
	 * Verify Calendar button pops Time selector once clicked on
	 * 48845
	 */
	@Test
	public void testVerifyDatePickerOpenWhenCalendarButtonIsClicked() {
		getPage("/marketing/massemail");
		assertTrue(page.clickOnCalendarButton(), "Unable to click on calendar button");
		assertTrue(page.getDatePicker().isDatePickerVisible(), "Date Picker is not visible");
	}
	
	/**
	 * Verify Time Zone is set as Admin Time zone once calendar button is clicked
	 * 48847
	 */
	@Test
	public void testVerifyAdminTimeZone() {
		getPage("/marketing/massemail");
		int l_admin_id =Integer.valueOf(EnvironmentFactory.configReader.getPropertyByName("zurple_bo_default_agent_id"));
		double admin_time_zone = getEnvironment().getAdmin(l_admin_id).getTimeZone();
		String l_Agent_time_zone = admin_time_zone==-8.0?"Pacific":"Central";
		assertTrue(page.getDatePicker().getTimeZone().contains(l_Agent_time_zone), "Incorrect time zone on date picker..Agent Time Zone "+l_Agent_time_zone);
	}
	
	/**
	 * Verify Scheduled date and time is displayed once Done button is clicked
	 * 48850
	 */
	@Test
	public void testVerifyScheduleLabelIsDisplayed() {
		getPage("/marketing/massemail");
		assertTrue(page.getDatePicker().clickOnNowButton(), "Unable to click on now button");
		assertTrue(page.getDatePicker().clickOnDoneButton(), "Unable to click on Done button");
		assertTrue(!page.getScheduleLabel().isEmpty(), "Schedule label is empty");
		
	}
	
	/**
	 * Verify Calender button is removed once time is selected and done button is clicked
	 * 48857
	 */
	@Test
	public void testVerifyCalendarButtonIsRemovedOnceEmailHasBeenScheduled() {
		getPage("/marketing/massemail");
		assertTrue(page.isScheduleButtonVisible(), "Schedule label is not removed.");
	}
	
	/**
	 * Verify Scheduled date and time is removed once "x" is clicked
	 * 48851
	 */
	@Test
	public void testVerifyRemoveScheduleLabelButton() {
		getPage("/marketing/massemail");
		assertTrue(page.clickOnRemoveScheduleButton(), "Unable to click on remove schedule label button");
		assertTrue(page.isScheduleButtonVisible(), "Schedule label is not removed.");
	}
	
	/**
	 * Verify Preview Email page is opened once Next button is clicked after selecting listings
	 * 48852
	 */
	@Test
	public void testVerifyPreviewEmailPageIsOpenedWhenNextButtonIsClicked() {
		getPage("/marketing/massemail");
		assertTrue(page.clickOnAddListingButton(), "Unable to click on Add listing button");
		assertTrue(page.clickOnNextButton(), "Unable to click on next button");
		assertTrue(page.isPreviewHeadingVisibleForSendListingEmail(), "Preview heading is not visible");
		assertTrue(page.isSeeListingButtonVisible(), "See Listing button is not visible..");
	}
	
	/**
	 * Verify Success message is displayed when user clicks Send Now button
	 * 48893
	 */
	@Test
	public void testVerifySuccessMessageIsDisplayedWhenSendNowButtonIsClicked() {
		getPage("/marketing/massemail");
		assertTrue(page.clickOnSendNowButtonListingPreview(), "Unable to click on Send Now button");
		assertTrue(page.isSuccessMessage(), "Success message is not displayed");
		String l_email_subject = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject);
		processEmailQueue("mass_email",l_email_subject, CacheFilePathsConstants.EmailListingFlyerCache);
	}
	
	/**
	 * Verify 'Email has been scheduled' message is displayed when schedule Listing email is sent
	 * 48898
	 */
	@Test
	public void testSendScheduleListingEmail() {
		getPage("/marketing/massemail");
		assertTrue(page.clickOnEmailListingFlyer(), "Unable to click on 'Send Listings' button");
		String l_email_subject = updateName("Scheduled Listing Email");
		assertTrue(page.typeListingEmailSubject(l_email_subject), "Unable to type email subject");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject, l_email_subject);
		assertTrue(page.clickOnAddListingButton(), "Unable to click on Add listing button");
		assertTrue(page.clickOnCalendarButton(), "Unable to click on calendar button");
		assertTrue(page.getDatePicker().clickOnNowButton(), "Unable to click on now button");
		assertTrue(page.getDatePicker().clickOnDoneButton(), "Unable to click on Done button");
		assertTrue(page.clickOnNextButton(), "Unable to click on next button");
		assertTrue(page.clickOnSendNowButtonListingPreview(), "Unable to click on Send Now button");
		assertTrue(page.isScheduledMessageDisplayed(), "Success message is not displayed");
		processEmailQueue("mass_email",l_email_subject, CacheFilePathsConstants.ScheduledEmailListingFlyerCache);
	}
	
	/**
	 * Verify Leads are able to receive listings when sent in Bulk from Mass email page
	 * 48896
	 */
	@Test
	public void testVerifyScheduledEmailListingFlyerIsSent() {
		getPage();
		boolean isEmailSent = false;
		JSONObject lCacheObject = getDataFile(CacheFilePathsConstants.ScheduledEmailListingFlyerCache);
		String l_email_subject = lCacheObject.optString("email_subject");
		Email email = new DBHelperMethods(getEnvironment()).getEmailBySubject(l_email_subject);
		int lLeadId = 0;
		if(email!=null) {
			lLeadId = email.getUser();
			if(lLeadId>0) {
				ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadId, lLeadId);
				isEmailSent = true;
			}
		}	
		assertTrue(isEmailSent, "Unable to verify listing flyer email from DB");
	}
	
	/**
	 * Verify Listing email is visible in Lead detail page once sent
	 * 48897
	 */
	@Test
	public void testVerifyScheduledEmailListingFlyerFromMyMessages() {
		getPage();
		JSONObject lCacheObject = getDataFile(CacheFilePathsConstants.ScheduledEmailListingFlyerCache);
		String l_email_subject = lCacheObject.optString("email_subject");
		int lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		String lc_lead_detail_url = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+lLeadId;
		driver.navigate().to(lc_lead_detail_url);
		assertTrue(testVerifyEmailInMyMessages(l_email_subject,String.valueOf(lLeadId)), "Unable to verify listing flyer email from lead details my messages section");
	}
	
	/**
	 * Verify current machine time is displayed by default once calendar button is clicked on
	 * 48846
	 */
	@Test
	public void testVerifyCurrentTimeIsDsiplayedInCalendar() {
		getPage("/marketing/massemail");
		assertTrue(page.clickOnEmailListingFlyer(), "Unable to click on 'Send Listings' button");
		assertTrue(page.clickOnCalendarButton(), "Unable to click on calendar button");
		String l_time = page.getCurrentTime();
		long difference = getDifference(l_time);
		assertTrue(difference==1, "Machine time and calendar time are not same");	
	}
	
	/**
	 * Verify Email listings are not sent to individual lead from mass email page
	 * 48855
	 */
	@Test
	public void testVerifyListingEmailIsNotSentToIndividualLead() {
		getPage("/marketing/massemail");
		assertTrue(page.selectRecipients("Individual Lead with any Status other than Inactive"), "Unable to select individual recipient");
		ActionHelper.staticWait(5);
		assertTrue(page.isInputLeadEmailIsVisible(), "Email Input field is not visible");
		assertTrue(page.clickOnEmailListingFlyer(), "Unable to click on 'Send Listings' button");
		ActionHelper.staticWait(5);
		assertFalse(page.isInputLeadEmailIsVisible(), "Email Input field is not visible");
	}
	private void verifyEmailListingFlyer(JSONObject pDataObject) {
		lToEmail = pDataObject.optString("toemail");
		assertTrue(page.clickOnEmailListingFlyer(), "Unable to click on email listing flyer button..");
		ActionHelper.staticWait(2);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleFlyerMLSId, pDataObject.optString("mls_id"));
		mlsID = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleFlyerMLSId);
		assertTrue(page.typeMLSId(mlsID), "Unable to type MLS id..");
		ActionHelper.staticWait(2);
		assertTrue(page.clickOnFindListingButton(), "Unable to click on find listing button...");
		//assertTrue(page.waitForFlyerHeadingToAppear(), "Unable to find flyer heading...");
		if(page.waitForFlyerHeadingToAppear()==false) {
			ActionHelper.RefreshPage(driver);
			assertTrue(page.clickOnEmailListingFlyer(), "Unable to click on email listing flyer button..");
			assertTrue(page.typeMLSId(mlsID), "Unable to type MLS id..");
			assertTrue(page.clickOnFindListingButton(), "Unable to click on find listing button...");
		}
		ActionHelper.staticWait(2);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject, updateName(pDataObject.optString("subject")));
		flyerSubject = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject);
		assertTrue(page.typeToSubject(flyerSubject), "Unable to type subject..");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject, pDataObject.optString("subject"));
		ActionHelper.staticWait(2);
		assertTrue(page.typeToEmail(lToEmail), "Unable to type subject..");
		ActionHelper.staticWait(2);
		assertTrue(page.clickOnPreviewButton(), "Unable to click on preview button..");
		ActionHelper.staticWait(2);
		assertTrue(page.isPreviewHeadingVisible(), "Preview is not visible..");
		ActionHelper.staticWait(2);
		assertTrue(page.closePreviewWindow(), "Unable to close Preview window..");
		ActionHelper.staticWait(2);
		
		assertTrue(page.clickOnSendButton(), "Unable to click on Send button...");
		ActionHelper.staticWait(2);
		assertTrue(page.isSuccessMessage(), "Unable to send email, success message is not displayed...");
			
	}
	
	private void fillStandardEmailForm(JSONObject pDataObject) {
		boolean isScheduledEmail = false;
		lToEmail = pDataObject.optString("toemail");
		assertTrue(page.clickOnSendStandardEmailButton(), "Unable to click on standard email button..");
		ActionHelper.staticWait(2);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleStandardEmailSubject, updateName(pDataObject.optString("subject")));
		emailSubject = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleStandardEmailSubject);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleBulkEmailSubject, updateName(pDataObject.optString("bulkEmailSubject")));
		bulkEmailSubject = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleBulkEmailSubject);
		
		if(page.checkSelectedRecipient()) { //Recipient is Individual lead
			assertTrue(page.typeToSubject(emailSubject), "Unable to type subject..");
		} else {
			assertTrue(page.typeToSubject(bulkEmailSubject), "Unable to type bulk email subject..");
		}
		ActionHelper.staticWait(2);
		if(page.checkSelectedRecipient()) { //Recipient is Individual lead
			assertTrue(page.typeToEmail(lToEmail), "Unable to type lead email..");
		}
		ActionHelper.staticWait(2);
//		if(pDataObject.optString("file_path")!=null && !pDataObject.optString("file_path").isEmpty()) {
//			assertTrue(page.clickOnAttachFileButton(), "Unable to click on attach file button..");
//			ActionHelper.staticWait(2);
//			page.getAttachFileForm().switchToBrowserToNewWindow();
//			ActionHelper.staticWait(10);
////			assertTrue(page.getAttachFileForm().isUploadFileFormVisible(), "Upload file form is not visible..");
//			assertTrue(page.getAttachFileForm().clickAndSelectFile(), "Unable to select the file from upload form ..");
//			ActionHelper.staticWait(5);
//			page.getAttachFileForm().switchToOriginalWindow();
//			ActionHelper.staticWait(5);
//			assertTrue(page.isAttachmentRemoveButtonVisible(), "Remove button after attaching file is not visible..");
//			
//			assertTrue(page.clickOnPreviewButton(), "Unable to click on preview button..");
//			ActionHelper.staticWait(2);
//			assertTrue(page.isAttachmentLabelVisible(), "Attachment file is not visible in preview..");
//			ActionHelper.staticWait(2);
//			assertTrue(page.closePreviewWindow(), "Unable to close Preview window..");
//			ActionHelper.staticWait(2);
//			
//		}
		if(pDataObject.optString("schedule_email")!=null && !pDataObject.optString("schedule_email").isEmpty()) {
			assertTrue(page.selectSchedule(), "Unable to schedule the email..");
			isScheduledEmail = true;
		}
		assertTrue(page.clickOnSendButton(), "Unable to click on Send button...");
		ActionHelper.staticWait(2);
		if(isScheduledEmail) {
			ActionHelper.staticWait(10);
			assertTrue(page.isScheduledMessageDisplayed(), "Unable to send email, scheduled email message is not displayed...");
			String scheduledLabel = page.getScheduledLabel().split(" ")[4];
			lWaitTime = getDifference(scheduledLabel);
		}else {
			ActionHelper.staticWait(10);
			assertTrue(page.isSuccessMessage(), "Unable to send email, success message is not displayed...");
		}
	}
	
	public boolean testVerifyEmailInMyMessages(JSONObject pDataObject, String pEmailSubject) {
		boolean isEmailSentSuccessfully = false;
		page=null;
		getPage();
		String lLeadId = null;		
			if(!getIsProd()) {
				lLeadId = pDataObject.optString("leadidstage");	
			} else {
				lLeadId = pDataObject.optString("leadid");
			}
			page = null;
			getPage("/lead/"+lLeadId);
//			assertTrue(leadDetailPage.clickOnMyMessagesTab(), "Unable to click on my messages tab..");
			isEmailSentSuccessfully = leadDetailPage.verifyMyMessagesEmails(pEmailSubject);
			return isEmailSentSuccessfully;
}
	public void testVerifyScheduledEmailInMyMessages(JSONObject pDataObject, String pEmailSubject) {
		getPage();
		String lLeadId = null;	
		AutomationLogger.info("Waiting for minutes "+lWaitTime);	
			if(!getIsProd()) {
				lLeadId = pDataObject.optString("leadidstage");
//				page = null;
//				getPage("/lead/"+lLeadId);
//				assertTrue(leadDetailPage.verifyScheduledEmail(pEmailSubject), "Unable to verify scheduled messages..");
//				ActionHelper.staticWait(lWaitTime*60);	
//			//	Process email queue
//				page=null;
//				getPage("/admin/processemailqueue");
//				new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
//				page = null;
//				getPage("/lead/"+lLeadId);
//				
				
			} else {
				lLeadId = pDataObject.optString("leadid");
//				page = null;
//				getPage("/lead/"+lLeadId);
//				assertTrue(leadDetailPage.verifyScheduledEmail(pEmailSubject), "Unable to verify scheduled messages..");
//				ActionHelper.staticWait(lWaitTime*60);	
//				page = null;
//				getPage("/lead/"+lLeadId);	
			}
			page = null;
			getPage("/lead/"+lLeadId);	
//			assertTrue(leadDetailPage.clickOnMyMessagesTab(), "Unable to click on my messages tab..");
			assertTrue(leadDetailPage.verifyMyMessagesEmails(pEmailSubject), "Unable to verify scheduled email under my messages..");
}
	private boolean isEmailShowingInScheduledEmails(JSONObject pDataObject, String pEmailSubject) {
		String lLeadId = null;
		boolean isEmailShoowing = false;
		if(!getIsProd()) {
			lLeadId = pDataObject.optString("leadidstage");
			page = null;
			getPage("/lead/"+lLeadId);
			isEmailShoowing = leadDetailPage.verifyScheduledEmail(pEmailSubject);
			//	Process email queue
			page=null;
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
		} else {
			lLeadId = pDataObject.optString("leadid");
			
		}
		page = null;
		getPage("/lead/"+lLeadId);
		isEmailShoowing = leadDetailPage.verifyScheduledEmail(pEmailSubject);
		return isEmailShoowing;
	}
	
	public void redirectToLeadsPage(JSONObject pDataObject) {
		getPage();
		String lLeadId = null;		
		ActionHelper.staticWait(2);
		if(!getIsProd()) {
			lLeadId = pDataObject.optString("leadidstage");
			page = null;
			getPage("/lead/"+lLeadId);
		} else {
			lLeadId = pDataObject.optString("leadid");
			page = null;
			getPage("/lead/"+lLeadId);
			page = null;
		}
	}
	
	public boolean leadStatus(JSONObject pDataObject, int index) {
			ActionHelper.staticWait(2);
			String lead_prospects = pDataObject.optString("lead_prospect").split(",")[index];
			ZBOSucessAlert successAlert = new ZBOSucessAlert(driver);
			assertTrue(leadDetailPage.isLeadDetailPage(), "Lead detail page is not visible..");
			assertTrue(leadDetailPage.clickAndSelectLeadProspect(lead_prospects), "Unable to select the status -> "+lead_prospects);
			return successAlert.clickOnTemporaryButton();
	}

	public void testVerifyLeadMessages(JSONObject pDataObject) {
		getPage();
		String lLeadId = null;		
		if(!getIsProd()) {
			lLeadId = pDataObject.optString("leadidstage_replies");
			page = null;
			getPage("/lead/"+lLeadId);
			ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadMessageSubject, pDataObject.optString("leadMessageSubjectStage"));
			leadReplySubject = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadMessageSubject);
		} else {
			lLeadId = pDataObject.optString("leadid");
			page = null;
			getPage("/lead/"+lLeadId);
			page = null;
			ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadMessageSubject, pDataObject.optString("leadMessageSubjectProd"));
			leadReplySubject = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadMessageSubject);
		}		
		ActionHelper.staticWait(5);
		assertTrue(leadDetailPage.clickOnLeadMessagesTab(), "Unable to click on lead messages tab..");
		assertTrue(leadDetailPage.verifyLeadMessagesEmailsAndDate(leadReplySubject), "Unable to verify lead reply under lead messages..");
//		assertTrue(leadDetailPage.verifyLeadMessagesDateTime(), "Unable to verify PUNs..");
//		assertTrue(page.clickOnLeadMessagesPreview(), "Unable to click on preview button..");
//		assertEquals(page.getPreviewText().trim(), "Updates for San Diego");			
	}
	
	private long getDifference(String pEndTime) {
		long duration = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
		Date startTime;
		try {
			startTime = sdf.parse(pEndTime);
			Date now = sdf.parse(getCuurentTime());
			duration = startTime.getTime() -now.getTime();
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		duration =TimeUnit.MILLISECONDS.toMinutes(duration);
		return duration+1;
	}
	
	private void processEmailQueue(String pEmailToProcess,String pEmailSubject, String pFileToWrite) {
		if(!getIsProd()) {
			page=null;
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processEmailsQueues(pEmailToProcess);
		} 
		JSONObject cacheObject = new JSONObject();
		cacheObject.put("email_subject", pEmailSubject);
		emptyFile(pFileToWrite, "");
		writeJsonToFile(pFileToWrite, cacheObject);
	}
	
	public boolean testVerifyEmailInMyMessages(String pEmailSubject, String pLeadId) {
		boolean isEmailSentSuccessfully = false;
		page=null;
		getPage();	
		getPage("/lead/"+pLeadId);
		isEmailSentSuccessfully = leadDetailPage.verifyMyMessagesEmails(pEmailSubject);
		return isEmailSentSuccessfully;
}

}
