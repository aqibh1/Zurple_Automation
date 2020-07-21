/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.backoffice.marketing.ZBOMarketingEmailMessagePage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
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
	ZBOLeadDetailPage p;
	String lToEmail;
	String flyerSubject;
	String emailSubject;
	String bulkEmailSubject;
	String mlsID;
	
	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		if(page==null) {
			driver = getDriver();
			page = new ZBOMarketingEmailMessagePage(driver);
			page.setUrl("");
			page.setDriver(driver);
			p = new ZBOLeadDetailPage(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOMarketingEmailMessagePage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
			p = new ZBOLeadDetailPage(driver);
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
	
	@Test
	@Parameters({"standardEmailData"})
	public void testSendBulkEmail(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		leadStatus(lDataObject, 0);
		page = null;
		getPage("/marketing/massemail");
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.selectRecipients(lDataObject.optString("recipient_bulkemail")), "Unable to select the recipients...");
		fillStandardEmailForm(lDataObject);
		System.out.println("This is bulk email subject: "+bulkEmailSubject);
		testVerifyEmailInMyMessages(lDataObject, bulkEmailSubject);
		leadStatus(lDataObject, 1);
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
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject, pDataObject.optString("mls_id"));
		mlsID = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject);
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
		if(pDataObject.optString("file_path")!=null && !pDataObject.optString("file_path").isEmpty()) {
			assertTrue(page.clickOnAttachFileButton(), "Unable to click on attach file button..");
			ActionHelper.staticWait(2);
			page.getAttachFileForm().switchToBrowserToNewWindow();
			assertTrue(page.getAttachFileForm().isUploadFileFormVisible(), "Upload file form is not visible..");
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
		assertTrue(page.clickOnSendButton(), "Unable to click on Send button...");
		ActionHelper.staticWait(2);
		assertTrue(page.isSuccessMessage(), "Unable to send email, success message is not displayed...");
	}
	
	public void testVerifyEmailInMyMessages(JSONObject pDataObject, String pEmailSubject) {
		getPage();
		String lLeadId = null;		
			if(!getIsProd()) {
				lLeadId = pDataObject.optString("leadidstage");
			//	Process email queue
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
			assertTrue(p.clickOnMyMessagesTab(), "Unable to click on my messages tab..");
			assertTrue(p.verifyMyMessagesEmails(pEmailSubject));
}
	
	public void leadStatus(JSONObject pDataObject, int index) {
		getPage();
		String lLeadId = null;		
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
		String lead_prospects = pDataObject.optString("lead_prospect").split(",")[index];
		ZBOSucessAlert successAlert = new ZBOSucessAlert(driver);
		assertTrue(p.isLeadDetailPage(), "Lead detail page is not visible..");
		assertTrue(p.clickAndSelectLeadProspect(lead_prospects), "Unable to select the status -> "+lead_prospects);
		assertTrue(successAlert.clickOnTemporaryButton(), "Unable to click on Temporary button..");
	}
}
//}
