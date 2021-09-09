/**
 * 
 */
package com.zurple.backoffice.admin;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.zurple.admin.ZAViewSellerLeadsAdPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;

/**
 * @author darrraqi
 *
 */
public class ZAViewSellerLeadsAdPageTest extends PageTest{
	private WebDriver driver;
	private ZAViewSellerLeadsAdPage page;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZAViewSellerLeadsAdPage(driver);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZAViewSellerLeadsAdPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
		
	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
//	@BeforeTest
//	public void backOfficeLogin() {
//		getPage();
//		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
//			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
//		}
//	}
	
	/**
	 * @param pDataFile
	 * 
	 * Pre Requisite test for all the verifications
	 * Verify 'Lead Form' seller lead ad date from View ads page
	 * 45758
	 */
	@Test
	public void testVerifyDateLeadFormSellerLeadAd() {
		getPage("/admin/view-sl-ads?status=test");
		assertTrue(page.isViewAdsPageDisplayed(), "View Seller lead ad page is not displayed..");
		String ads_count = String.valueOf(page.getAdsCount());
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.SellerAdsCount, ads_count);
		assertTrue(page.verifyAdDate(getTodaysDate(), ads_count), "Unable to verify ad date..");
	}
	
	/**
	 * Verify the ad type is 'Lead Form' on view seller lead ads page
	 * 45759
	 */
	@Test
	public void testVerifyAdTypeSellerLeadFormAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		assertTrue(page.verifyAdType("Lead_form", l_ad_count), "Unable to verify the ad type");
	}
	
	/**
	 * Verify the Seller lead form ad status is 'paused' on view seller lead ads page
	 * 45760
	 */
	@Test
	public void testVerifyAdStatusSellerLeadFormAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		assertTrue(page.verifyAdStatus("paused", l_ad_count), "Ad status is not paused..");	}
	
	/**
	 * Verify the Seller lead form ad budget is correct view seller lead ads page.
	 * 45761
	 */
	@Test
	public void testVerifyBudgetSellerLeadFormAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		String l_ad_budget = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdBudget);
		assertTrue(page.verifyAdBudget("$"+l_ad_budget+".00", l_ad_count), "Unable to verify Ad Budget");
	}
	
	/**
	 * Verify the Seller lead form carousel status is correct view seller lead ads page.
	 * 45762
	 */
	@Test
	public void testVerifyCarouselAdStatusSellerLeadFormAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		String l_current_url = driver.getCurrentUrl();
		assertTrue(page.refreshStatusButton(l_ad_count), "Unable to click on refresh status button");
		driver.navigate().to(l_current_url);
		ActionHelper.staticWait(5);
		assertTrue(page.verifyCarouselStatus("Pending_review","Disapproved", l_ad_count), "Unable to verify ad carousel status");
	}
	
	/**
	 * Verify the Seller lead form carousel video status is correct view seller lead ads page.
	 * 45763
	 */
	@Test
	public void testVerifyCarouselDownloadAdStatusSellerLeadFormAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		assertTrue(page.verifyCarouselDownloadStatus("Pending_review","Disapproved", l_ad_count), "Unable to verify ad carousel download status");
	}
	
	/**
	 * Verify the Seller lead form carousel download status is correct view seller lead ads page.
	 * 45764
	 */
	@Test
	public void testVerifyCarouselVideoAdStatusSellerLeadFormAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		assertTrue(page.verifyCarouselVideoStatus("Pending_review","Disapproved", l_ad_count), "Unable to verify ad carousel video status");
	}
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
