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
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOAdsOverviewPageTest extends PageTest{

	private WebDriver driver;
	private JSONObject dataObject;
	private ZBOAdsOverviewPage page;
	private String ZurpleAdId;
	
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
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAdsOverviewPage(driver);
			setLoginPage(driver);
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
		assertTrue(page.getHeader().verifyAdsManagerDropdownOptions(), "Ads Manager dropdown options not visible..");
	}
	
	@Test
	public void testClickAdsOverviewFromAdsManager() {
		getPage();
		assertTrue(page.getHeader().clickOnAdsOverviewButton(), "Unable to click on Ads overview dropdown button..");
		assertTrue(page.isAdsOverviewPage(), "Ads Overview page is not displayed..");
		assertTrue(page.verifyAdsAreDisplayed(), "Ads are not showing on Ads overview page..");
	}
	
	@Test
	public void testVerifyAdTypeColumnIsDisplayedOnAdsOverviewPage() {
		getPage("/ads/overview");
		assertTrue(page.isTypeColumnVisible(), "Type Column is not visible on Ads overview page");
		assertTrue(page.isListingAdHeadingVisible(), "Listing Ad Heading is not visible on Ads overview page");
	}
	
	@Test
	public void testVerifyAdIconIsDisplayedOnAdsOverviewPage() {
		getPage("/ads/overview");
		assertTrue(page.isTypeColumnVisible(), "Type Column is not visible on Ads overview page");
		assertTrue(page.isListingAdIconIsVisible(), "Listing Ad Icon is not visible on Ads overview page");
	}
	
	@Test
	public void testVerifyListingAddressIsDisplayedOnAdsOverviewPage() {
		getPage("/ads/overview");
		assertTrue(page.isListingAdIconIsVisible(), "Listing Address is not visible on Ads overview page");
	}
	
	@Test
	public void testVerifyAdStartingAndEndingIsDisplayed() {
		getPage("/ads/overview");
		assertTrue(page.isStartEndDateVisible(), "Listing Ad date is not visible on Ads overview page");
	}
	
	@Test
	public void testVerifyAdClickViewStatsAreDisplayed() {
		getPage("/ads/overview");
//		assertTrue(page.isAdViewCountVisible(), "Ads View Count is not visible on Ads overview page");
		assertTrue(page.isAdViewIconVisible(), "Ads View Icon is not visible on Ads overview page");
//		assertTrue(page.isAdClickCountVisible(), "Ads Click Count is not visible on Ads overview page");
		assertTrue(page.isAdClickIconVisible(), "Ads Click Icon is not visible on Ads overview page");
	}
	
	@Test
	public void testVerifyAdPricesAreDisplayed() {
		getPage("/ads/overview");
		assertTrue(page.verifyAdPriceIsDisplayed(), "Ads Price is not visible on Ads overview page");
	}
	
	@Test
	public void testVerifyAdLocationIsDisplayed() {
		getPage("/ads/overview");
		assertTrue(page.verifyAdLocationIsDisplayed(), "Ads Location is not visible on Ads overview page");
	}
	@Test
	public void testVerifyAdRecurringDateIsDisplayed() {
		getPage("/ads/overview");
		assertTrue(page.verifyAdRecurringDateIsDisplayed(), "Ads Recurring date is not visible on Ads overview page");
	}
	@Test
	public void testVerifyAdStatusIsDisplayed() {
		getPage("/ads/overview");
		assertTrue(page.verifyAdStatusIsDisplayed(), "Ads Status is not visible on Ads overview page");
	}
	@Test
	public void testVerifyAdPreviewGetsDisplayedDisplayed() {
		getPage("/ads/overview");
		assertTrue(page.isPreviewDisplayed(), "Ads preview is not displayed after clicking on Ads overview page");
	}
	@Test
	public void testVerifyAdPreviewSlideShowIsWorking() {
		getPage("/ads/overview");
		ActionHelper.RefreshPage(driver);
		if(!page.isPreviewDisplayed()) {
			throw new SkipException("Skipping the test becasuse Preview button is not clicked..");
		}
		assertTrue(page.verifyAdSlideShowIsWorking(), "Ads preview slide show is not working after clickings on preview button..");
	}
	
	/**
	 * Verify clicking on Learn More button on ads overview page takes user to Agent website
	 * 47698
	 */
	@Test
	public void testVerifyLearnMoreButtonRedirectsUserToAgentWebsite() {
		loginIntoBackOffice();
		page = null;
		getPage("/ads/overview");
		assertTrue(page.clickOnLearnMoreButton(), "Unable to click on learn more button");
		ActionHelper.staticWait(5);
		ActionHelper.switchToSecondWindow(driver);
		String l_current_url = driver.getCurrentUrl();
		assertTrue(l_current_url.contains("source=paid_social"), "Paid Social is not appended to URL");
		assertTrue(l_current_url.contains(EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url")));
	}
	
	private String getAdId() {
		String l_ad_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAdId);
		l_ad_id = l_ad_id.split("ad=")[1];
		return l_ad_id;
	}
	public void loginIntoBackOffice() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}


}
