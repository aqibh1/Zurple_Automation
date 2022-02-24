/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

/**
 * @author adar
 *
 */
public class ZWCommunityReportsPageTest extends PageTest{
	private WebDriver driver;
	private ZWCommunityReportsPage page;
	
	@Override
	public void testTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testBrand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page getPage() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Page getPage(String pUrl) {
		page = null;
		if(page == null){
			driver = getDriver();
			page = new ZWCommunityReportsPage(driver);
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
	public void testSearchCommunityReports() {
		getPage("/community");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleCommunityReportsZip, "91910");
		assertTrue(page.isCommunityReportsPage(), "Community reports page is not displayed");
		assertTrue(page.typeZip("91910"), "Unable to type Zip");
		assertTrue(page.clickOnSearchButton(), "Unable to click on search button..");
		ActionHelper.staticWait(10);
	}
	
	@Test//39731
	public void testVerifyLocalInfoCommunityLinkIsWorking() {
		websiteLoginPreCond();
		ActionHelper.staticWait(5);
		getPage("/sold-homes");
		ActionHelper.staticWait(5);
		assertTrue(page.goToCommunityReportsFromHeaders(), "Unable to click on Community dropdown..");
		assertTrue(page.isCommunityReportsPage(), "Community reports page is not visible..");
	}
	
	@Test//39734
	public void testVerifyNoCommunityResultsFound() {
		getPage("/community");
		assertTrue(page.isCommunityReportsPage(), "Community reports page is not displayed");
		assertTrue(page.typeZip("88888"), "Unable to type Zip");
		assertTrue(page.clickOnSearchButton(), "Unable to click on search button..");
		assertTrue(page.isNoResultsFoundVisible(), "No Results found message is not displayed..");
	}
	
	private void websiteLoginPreCond() {
		ZWLoginPageTest loginPageTest = new ZWLoginPageTest();
		loginPageTest.testSignIn();
	}

}
