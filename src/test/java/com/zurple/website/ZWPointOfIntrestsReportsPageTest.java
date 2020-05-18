/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
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
	public void testSearchSchoolsReports() {
		getPage("/points-of-interest");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurplePOIReportsZip, "91910");
		assertTrue(page.isPOIReportsPage(), "Community reports page is not displayed");
		assertTrue(page.typeZip("91910"), "Unable to type Zip");
		assertTrue(page.clickOnSearchButton(), "Unable to click on search button..");
	}

}
