/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.backoffice.marketing.ZBOMarketingEmailMessagePage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.ZurpleListingConstants;

/**
 * @author adar
 *
 */
public class ZBOMarketingEmailPageTest extends PageTest{

	ZBOMarketingEmailMessagePage page;
	private WebDriver driver;
	ZBOLeadDetailPage leadDetailPage;
	String lToEmail;
	String flyerSubject;
	String emailSubject;
	long lWaitTime = 0;
	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		return null;
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
		getPage("/marketing/massemail");
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.selectRecipients(lDataObject.optString("recipients")), "Unable to select the recipients...");
		verifyEmailListingFlyer(lDataObject);
		testVerifyEmailInMyMessages(lDataObject, flyerSubject);
	}
	
	@Test
	@Parameters({"standardEmailData"})
	public void testSendStandardEmail(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		getPage("/marketing/massemail");
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.selectRecipients(lDataObject.optString("recipients")), "Unable to select the recipients...");
		fillStandardEmailForm(lDataObject);
		System.out.println("This is email subject: "+emailSubject);
		testVerifyEmailInMyMessages(lDataObject, emailSubject);
	}
	
	@Test(dependsOnGroups = {"com.zurple.backoffice.ZBOCreateTemplatePageTest.testCreateTemplate"})
	public void testVerifyTemplateExists() {
		getPage("/marketing/massemail");
		String lTemplateName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleTemplateName);
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.isTemplateExists(lTemplateName), "Template does not exist in Mass email drop down..");	
	}
	
	
	private void verifyEmailListingFlyer(JSONObject pDataObject) {
		lToEmail = pDataObject.optString("toemail");
		assertTrue(page.clickOnEmailListingFlyer(), "Unable to click on email listing flyer button..");
		ActionHelper.staticWait(2);
		assertTrue(page.typeMLSId(ZurpleListingConstants.zurple_mls_listing_id), "Unable to type MLS id..");
		ActionHelper.staticWait(2);
		assertTrue(page.clickOnFindListingButton(), "Unable to click on find listing button...");
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
	
	@Test
	@Parameters({"standardEmailData"})
	public void testSendScheduledStandardEmail(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		getPage("/marketing/massemail");
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.selectRecipients(lDataObject.optString("recipients")), "Unable to select the recipients...");
		fillStandardEmailForm(lDataObject);
		System.out.println("This is email subject: "+emailSubject);
		testVerifyScheduledEmailInMyMessages(lDataObject, emailSubject);
	}
	
	private void fillStandardEmailForm(JSONObject pDataObject) {
		boolean isScheduledEmail = false;
		lToEmail = pDataObject.optString("toemail");
		assertTrue(page.clickOnSendStandardEmailButton(), "Unable to click on standard email button..");
		ActionHelper.staticWait(2);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject, updateName(pDataObject.optString("subject")));
		emailSubject = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject);
		assertTrue(page.typeToSubject(emailSubject), "Unable to type subject..");
		ActionHelper.staticWait(2);
		assertTrue(page.typeToEmail(lToEmail), "Unable to type subject..");
		ActionHelper.staticWait(2);
		
		if(pDataObject.optString("file_path")!=null && !pDataObject.optString("file_path").isEmpty()) {
			assertTrue(page.clickOnAttachFileButton(), "Unable to click on attach file button..");
			ActionHelper.staticWait(2);
			page.getAttachFileForm().switchToBrowserToNewWindow();
			ActionHelper.staticWait(10);
//			assertTrue(page.getAttachFileForm().isUploadFileFormVisible(), "Upload file form is not visible..");
			assertTrue(page.getAttachFileForm().clickAndSelectFile(), "Unable to select the file from upload form ..");
			ActionHelper.staticWait(5);
			page.getAttachFileForm().switchToOriginalWindow();
			ActionHelper.staticWait(5);
			assertTrue(page.isAttachmentRemoveButtonVisible(), "Remove button after attaching file is not visible..");
			
			assertTrue(page.clickOnPreviewButton(), "Unable to click on preview button..");
			ActionHelper.staticWait(2);
			assertTrue(page.isAttachmentLabelVisible(), "Attachment file is not visible in preview..");
			ActionHelper.staticWait(2);
			assertTrue(page.closePreviewWindow(), "Unable to close Preview window..");
			ActionHelper.staticWait(2);
			
		}
		if(pDataObject.optString("schedule_email")!=null && !pDataObject.optString("schedule_email").isEmpty()) {
			assertTrue(page.selectSchedule(), "Unable to schedule the email..");
			isScheduledEmail = true;
		}
		assertTrue(page.clickOnSendButton(), "Unable to click on Send button...");
		ActionHelper.staticWait(2);
		if(isScheduledEmail) {
			assertTrue(page.isScheduledMessageDisplayed(), "Unable to send email, scheduled email message is not displayed...");
			String scheduledLabel = page.getScheduledLabel().split(" ")[4];
			lWaitTime = getDifference(scheduledLabel);
		}else {
			assertTrue(page.isSuccessMessage(), "Unable to send email, success message is not displayed...");
		}
		
	}
	
	public void testVerifyEmailInMyMessages(JSONObject pDataObject, String pEmailSubject) {
		getPage();
		String lLeadId = null;	
		if(!getIsProd()) {
			lLeadId = pDataObject.optString("leadidstage");
			//Process email queue
			page=null;
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
			page = null;
			getPage("/lead/"+lLeadId);
		} else {
			lLeadId = pDataObject.optString("leadid");
			page = null;
			getPage("/lead/"+lLeadId);
			page = null;
		}
		assertTrue(leadDetailPage.clickOnMyMessagesTab(), "Unable to click on my messages tab..");
		assertTrue(leadDetailPage.verifyMyMessagesEmails(pEmailSubject));
	}
	
	public void testVerifyScheduledEmailInMyMessages(JSONObject pDataObject, String pEmailSubject) {
		getPage();
		String lLeadId = null;	
		AutomationLogger.info("Waiting for minutes "+lWaitTime);
		ActionHelper.staticWait(lWaitTime*60);	
		if(!getIsProd()) {
			lLeadId = pDataObject.optString("leadidstage");
			//Process email queue
			page=null;
			getPage("/admin/processemailqueue");
			new ZAProcessEmailQueuesPage(driver).processMassEmailQueue();
			page = null;
			getPage("/lead/"+lLeadId);
		} else {
			lLeadId = pDataObject.optString("leadid");
			page = null;
			getPage("/lead/"+lLeadId);
			page = null;
		}
		assertTrue(leadDetailPage.clickOnMyMessagesTab(), "Unable to click on my messages tab..");
		assertTrue(leadDetailPage.verifyScheduledEmail(pEmailSubject), "Unable to verify scheduled messages..");
		assertTrue(leadDetailPage.verifyMyMessagesEmails(pEmailSubject), "Unable to verify scheduled email under my messages..");
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
}
