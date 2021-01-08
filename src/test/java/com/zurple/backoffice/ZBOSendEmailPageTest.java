/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOSendEmailPageTest extends PageTest{
	
	private ZBOSendEmailPage page;
	private WebDriver driver;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOSendEmailPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOSendEmailPage(driver);
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
	public void testVerifyPreview() {
		//https://my.stage01.zurple.com/marketing/massemail/lead/3015880
		AutomationLogger.startTestCase("Verify Preview Button");
		getPage();
		page =null;
		String lLeadEmail = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail);
		String lListing_id = ModuleCommonCache.getElement(getThreadId(),lLeadEmail );
		getPage("/marketing/massemail/lead/"+lListing_id);
		assertTrue(page.isSendEmailPage(), "Send email page is not visible");
		assertTrue(page.clickOnPreviewButton(), "Unable to click on prevoiew button..");
		assertTrue(page.isPreviewBoxVisible(), "Preview box is not opened..");
		AutomationLogger.endTestCase();
	}

}
