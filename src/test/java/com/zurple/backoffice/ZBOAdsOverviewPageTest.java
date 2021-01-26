/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.zurple.backoffice.ads.ZBOAdsOverviewPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * @author adar
 *
 */
public class ZBOAdsOverviewPageTest extends PageTest{

	private WebDriver driver;
	private JSONObject dataObject;
	private ZBOAdsOverviewPage page;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAdsOverviewPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	public void testVerifyAdsManagerIsVisibleInHeader() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
		assertTrue(page.getHeader().isAdsManagerHeadingVisible(), "Ads Manager heading is not visible.");
	}
	
	@Test
	public void testVerifyAdsManagerDropdownOptionsVisible() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
		assertTrue(page.getHeader().verifyAdsManagerDropdownOptions(), "Ads Manager dropdown options not visible..");
	}
	
	@Test
	public void testClickAdsOverviewFromAdsManager() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
		assertTrue(page.getHeader().clickOnAdsOverviewButton(), "Unable to click on Ads overview dropdown button..");
		assertTrue(page.isAdsOverviewPage(), "Ads Overview page is not displayed..");
		assertTrue(page.verifyAdsAreDisplayed(), "Ads are not showing on Ads overview page..");
	}
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}

}
