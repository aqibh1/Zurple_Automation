package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;

public class ZBOLeadPageTest extends PageTest{
	WebDriver driver;
	ZBOLeadPage page;
	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		return null;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOLeadPage(driver);
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
	public void testSearchAndVerifyLead() {
		AutomationLogger.startTestCase("Search and Verify lead");
		getPage("/leads");
		String lLeadName = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadName);
		assertTrue(page.isLeadPage(), "Lead page is not visible..");
		assertTrue(page.isLeadExist(lLeadName), "Unable to find lead on lead page..");
		AutomationLogger.endTestCase();
		
	}

}
