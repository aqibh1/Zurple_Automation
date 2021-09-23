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
		ActionHelper.staticWait(30);
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
	//Conversion ad
	
	/**
	 * Verify 'Conversion' seller lead ad date from View ads page
	 * 45772
	 */
	@Test
	public void testVerifyDateConversionSellerLeadAd() {
		getPage("/admin/view-sl-ads?status=test");
		assertTrue(page.isViewAdsPageDisplayed(), "View Seller lead ad page is not displayed..");
		String ads_count = String.valueOf(page.getAdsCount());
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.SellerAdsCount, ads_count);
		assertTrue(page.verifyAdDate(getTodaysDate(), ads_count), "Unable to verify ad date..");
	}
	
	/**
	 * Verify the ad type is 'Conversion' on view seller lead ads page
	 * 45766
	 */
	@Test
	public void testVerifyAdTypeSellerLeadConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		assertTrue(page.verifyAdType("Conversion", l_ad_count), "Unable to verify the ad type");
	}
	
	/**
	 * Verify the Seller lead Conversion ad status is 'paused' on view seller lead ads page
	 * 45771
	 */
	@Test
	public void testVerifyAdStatusSellerLeadConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		assertTrue(page.verifyAdStatus("paused", l_ad_count), "Ad status is not paused..");	}
	
	/**
	 * Verify the Seller lead Conversion ad budget is correct view seller lead ads page.
	 * 45770
	 */
	@Test
	public void testVerifyBudgetSellerLeadConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		String l_ad_budget = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdBudget);
		assertTrue(page.verifyAdBudget("$"+l_ad_budget+".00", l_ad_count), "Unable to verify Ad Budget");
	}
	
	/**
	 * Verify the Seller lead Conversion carousel status is correct view seller lead ads page.
	 * 45769
	 */
	@Test
	public void testVerifyCarouselAdStatusSellerLeadConversionAd() {
		ActionHelper.staticWait(45);
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		String l_current_url = driver.getCurrentUrl();
		assertTrue(page.refreshStatusButton(l_ad_count), "Unable to click on refresh status button");
		driver.navigate().to(l_current_url);
		ActionHelper.staticWait(5);
		assertTrue(page.verifyCarouselStatus("Pending_review","Disapproved", l_ad_count), "Unable to verify ad carousel status");
	}
	
	/**
	 * Verify the Seller lead Conversion carousel download status is correct view seller lead ads page.
	 * 45767
	 */
	@Test
	public void testVerifyCarouselDownloadAdStatusSellerLeadConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		assertTrue(page.verifyCarouselDownloadStatus("Pending_review","Disapproved", l_ad_count), "Unable to verify ad carousel download status");
	}
	
	/**
	 * Verify the Seller lead Conversion carousel video status is correct view seller lead ads page.
	 * 45768
	 */
	@Test
	public void testVerifyCarouselVideoAdStatusSellerLeadConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		assertTrue(page.verifyCarouselVideoStatus("Pending_review","Disapproved", l_ad_count), "Unable to verify ad carousel video status");
	}
	
	/**
	 * Verify the ads date is correct for 'Conversion + Lead Form' ads on view seller lead ads page
	 * 45781
	 */
	@Test
	public void testVerifyDateLeadFormPlusConversionSellerLeadAd() {
		getPage("/admin/view-sl-ads?status=test");
		assertTrue(page.isViewAdsPageDisplayed(), "View Seller lead ad page is not displayed..");
		String ads_count = String.valueOf(page.getAdsCount());
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.SellerAdsCount, ads_count);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.SellerConversionCount, "2");
		assertTrue(page.verifyAdDate(getTodaysDate(), ads_count), "Unable to verify ad date..");
		assertTrue(page.verifyAdDate(getTodaysDate(), "2"), "Unable to verify ad date..");
	}

	/**
	 * Verify the ad type is 'Conversion + Lead Form' on view seller lead ads page
	 * 45775
	 */
	@Test
	public void testVerifyAdTypeSellerLeadLeadFormPlusConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		String l_ad_con_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerConversionCount);
		assertTrue(page.verifyAdType("Lead_form", l_ad_count), "Unable to verify the ad type");
		assertTrue(page.verifyAdType("Conversion", l_ad_con_count), "Unable to verify the ad type");
	}
	
	/**
	 * Verify the Seller lead Conversion + Lead Form ad status is 'paused' on view seller lead ads page
	 * 45777
	 */
	@Test
	public void testVerifyAdStatusSellerLeadFormPlusConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		String l_ad_con_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerConversionCount);
		assertTrue(page.verifyAdStatus("paused", l_ad_count), "Ad status is not paused..");	
		assertTrue(page.verifyAdStatus("paused", l_ad_con_count), "Conversion Ad status is not paused..");		
	}
	

	/**
	 * Verify the Seller lead Conversion + Lead Form ad budget is correct view seller lead ads page.
	 * 45776
	 */
	@Test
	public void testVerifyBudgetSellerLeadFormPlusConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		String l_ad_budget = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdBudget);
		String l_ad_con_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerConversionCount);
		assertTrue(page.verifyAdBudget("$"+l_ad_budget+".00", l_ad_count), "Unable to verify lead form Ad Budget");
		assertTrue(page.verifyAdBudget("$"+l_ad_budget+".00", l_ad_con_count), "Unable to verify conversion Ad Budget");
	}
	

	/**
	 * Verify the Seller lead Conversion + Lead Form carousel status is correct view seller lead ads page.
	 * 45780
	 */
	@Test
	public void testVerifyCarouselAdStatusSellerLeadFormPlusConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		String l_ad_con_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerConversionCount);
		String l_current_url = driver.getCurrentUrl();
		assertTrue(page.refreshStatusButton(l_ad_count), "Unable to click on refresh status button");
		driver.navigate().to(l_current_url);
		ActionHelper.staticWait(5);
		assertTrue(page.verifyCarouselStatus("Pending_review","Disapproved", l_ad_count), "Unable to verify ad carousel status");
		assertTrue(page.verifyCarouselStatus("Pending_review","Disapproved", l_ad_con_count), "Unable to verify conversion ad carousel status");
	}
	
	/**
	 * Verify the Seller lead Conversion + Lead Form carousel download status is correct view seller lead ads page.
	 * 45778
	 */
	@Test
	public void testVerifyCarouselDownloadAdStatusSellerLeadFormPlusConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		String l_ad_con_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerConversionCount);
		assertTrue(page.verifyCarouselDownloadStatus("Pending_review","Disapproved", l_ad_count), "Unable to verify ad carousel download status");
		assertTrue(page.verifyCarouselDownloadStatus("Pending_review","Disapproved", l_ad_con_count), "Unable to verify conversion ad carousel download status");

	}
	
	/**
	 * Verify the Seller lead Conversion + Lead Form carousel video status is correct view seller lead ads page.
	 * 45779
	 */
	@Test
	public void testVerifyCarouselVideoAdStatusSellerLeadFormPlusConversionAd() {
		String l_ad_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerAdsCount);
		String l_ad_con_count = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.SellerConversionCount);
		assertTrue(page.verifyCarouselVideoStatus("Pending_review","Disapproved", l_ad_count), "Unable to verify ad carousel video status");
		assertTrue(page.verifyCarouselVideoStatus("Pending_review","Disapproved", l_ad_con_count), "Unable to verify conversio ad carousel video status");

	}
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
