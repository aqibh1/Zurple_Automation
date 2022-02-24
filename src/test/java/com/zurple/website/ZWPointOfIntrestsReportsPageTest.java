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
public class ZWPointOfIntrestsReportsPageTest extends PageTest{
	private WebDriver driver;
	private ZWPointsOfIntrestPage page;
	
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
			page = new ZWPointsOfIntrestPage(driver);
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
	public void testSearchPOIReports() {
		getPage("/points-of-interest");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurplePOIReportsZip, "91910");
		assertTrue(page.isPOIReportsPage(), "Community reports page is not displayed");
		assertTrue(page.typeZip("91910"), "Unable to type Zip");
		assertTrue(page.clickOnSearchButton(), "Unable to click on search button..");
		ActionHelper.staticWait(10);
	}

	@Test//39739
	public void testVerifyLocalInfoPOILinkIsWorking() {
		websiteLoginPreCond();
		getPage("/sold-homes");
		assertTrue(page.goToPOIReportsFromHeaders(), "Unable to click on POI dropdown..");
		assertTrue(page.isPOIReportsPage(), "POI reports page is not visible..");
	}
	
	@Test//39742
	public void testVerifyNoPOIResultsFound() {
		getPage("/points-of-interest");
		assertTrue(page.isPOIReportsPage(), "POI reports page is not displayed");
		assertTrue(page.typeZip("54456654"), "Unable to type Zip");
		assertTrue(page.clickOnSearchButton(), "Unable to click on search button..");
		assertTrue(page.isNoResultsFoundVisible(), "No Results found message is not displayed..");
	}
	
	private void websiteLoginPreCond() {
		ZWLoginPageTest loginPageTest = new ZWLoginPageTest();
		loginPageTest.testSignIn();
	}
}
