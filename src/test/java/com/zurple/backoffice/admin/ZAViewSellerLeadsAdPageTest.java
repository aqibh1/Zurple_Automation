/**
 * 
 */
package com.zurple.backoffice.admin;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
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
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyLeadFormSellerLeadAd(String pDataFile) {
		getPage("/admin/view-sl-ads?status=test");
		String l_current_url = driver.getCurrentUrl();
		String l_ad_budget = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdBudget);
		assertTrue(page.isViewAdsPageDisplayed(), "View Seller lead ad page is not displayed..");
		String ads_count = String.valueOf(page.getAdsCount());
		
		assertTrue(page.verifyAdDate(getTodaysDate(), ads_count), "Unable to verify ad date..");
		assertTrue(page.verifyAdType("Lead_form", ads_count), "Unable to verify the ad type");
		assertTrue(page.verifyAdStatus("paused", ads_count), "Ad status is not paused..");
		assertTrue(page.verifyAdBudget("$"+l_ad_budget+".00", ads_count), "Unable to verify Ad Budget");
		
		assertTrue(page.refreshStatusButton(ads_count), "Unable to click on refresh status button");
		driver.navigate().to(l_current_url);
		ActionHelper.staticWait(5);
		
		assertTrue(page.verifyCarouselStatus("Pending_review","Disapproved", ads_count), "Unable to veridy ad carousel status");
		assertTrue(page.verifyCarouselDownloadStatus("Pending_review","Disapproved", ads_count), "Unable to veridy ad carousel status");
		assertTrue(page.verifyCarouselVideoStatus("Pending_review","Disapproved", ads_count), "Unable to veridy ad carousel status");
	}
	
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
