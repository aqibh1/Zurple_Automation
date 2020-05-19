/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.marketing.ZBOMarketingEmailMessagePage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.ZurpleListingConstants;

/**
 * @author adar
 *
 */
public class ZBOMarketingEmailPageTest extends PageTest{

	ZBOMarketingEmailMessagePage page;
	private WebDriver driver;
	
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
	}
	
	@Test
	@Parameters({"standardEmailData"})
	public void testSendStandardEmail(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		getPage("/marketing/massemail");
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.selectRecipients(lDataObject.optString("recipients")), "Unable to select the recipients...");
		fillStandardEmailForm(lDataObject);
	}
	
	@Test(dependsOnGroups = {"com.zurple.backoffice.ZBOCreateTemplatePageTest.testCreateTemplate"})
	public void testVerifyTemplateExists() {
		getPage("/marketing/massemail");
		String lTemplateName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleTemplateName);
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.isTemplateExists(lTemplateName), "Template does not exist in Mass email drop down..");
		
	}
	private void verifyEmailListingFlyer(JSONObject pDataObject) {
		String lToEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		assertTrue(page.clickOnEmailListingFlyer(), "Unable to click on email listing flyer button..");
		ActionHelper.staticWait(2);
		assertTrue(page.typeMLSId(ZurpleListingConstants.zurple_mls_listing_id), "Unable to type MLS id..");
		ActionHelper.staticWait(2);
		assertTrue(page.clickOnFindListingButton(), "Unable to click on find listing button...");
		ActionHelper.staticWait(2);
		assertTrue(page.typeToSubject(pDataObject.optString("subject")), "Unable to type subject..");
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
		String lToEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		assertTrue(page.clickOnSendStandardEmailButton(), "Unable to click on standard email button..");
		ActionHelper.staticWait(2);
		assertTrue(page.typeToSubject(pDataObject.optString("subject")), "Unable to type subject..");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleEmailFlyerSubject, pDataObject.optString("subject"));
		ActionHelper.staticWait(2);
		assertTrue(page.typeToEmail(lToEmail), "Unable to type subject..");
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

}
