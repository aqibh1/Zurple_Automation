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
	@Parameters({"addLeadData"})
	public void testSendEmailListingFlyer(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		getPage("/marketing/massemail");
		assertTrue(page.isMarketingEmailPage(), "Marketing email page is not displayed...");
		assertTrue(page.selectRecipients(lDataObject.optString("recipients")), "Unable to select the recipients...");
		verifyEmailListingFlyer(lDataObject);
	}
	
	private void verifyEmailListingFlyer(JSONObject pDataObject) {
		String lToEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		assertTrue(page.clickOnEmailListingFlyer(), "Unable to click on email listing flyer button..");
		assertTrue(page.typeMLSId(ZurpleListingConstants.zurple_mls_listing_id), "Unable to type MLS id..");
		assertTrue(page.clickOnFindListingButton(), "Unable to click on find listing button...");
		assertTrue(page.typeToSubject(pDataObject.optString("subject")), "Unable to type subject..");
		assertTrue(page.typeToEmail(lToEmail), "Unable to type subject..");
		
		assertTrue(page.clickOnPreviewButton(), "Unable to click on preview button..");
		assertTrue(page.isPreviewHeadingVisible(), "Preview is not visible..");
		assertTrue(page.closePreviewWindow(), "Unable to close Preview window..");
		
		assertTrue(page.clickOnSendButton(), "Unable to click on Send button...");
		assertTrue(page.isSuccessMessage(), "Unable to send email, success message is not displayed...");
			
	}

}
