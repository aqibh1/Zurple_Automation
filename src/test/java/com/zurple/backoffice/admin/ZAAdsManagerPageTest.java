/**
 * 
 */
package com.zurple.backoffice.admin;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.zurple.admin.ZAAdsManagerPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author darrraqi
 *
 */
public class ZAAdsManagerPageTest extends PageTest{
	private WebDriver driver;
	private JSONObject dataObject;
	private ZAAdsManagerPage page;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZAAdsManagerPage(driver);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZAAdsManagerPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
		
	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Verify the status of Quick Listing Ad from admin manager
	 * 42276
	 */
	@Test
	public void testVerifyStatusOfTheQuickListingAdFromAdsManager() {
		getPage("/admin/ads");
		searchAdPreCondition();
		
		assertTrue(verifytSUPStatusOfTheAd("PAUSED"), "Unable to verify SUP ad status");
		assertTrue(verifytStatusOfTheAd("PAUSED"), "AD Status is not PAUSED");
	}
	
	/**
	 * Verify the budget of Quick Listing Ads from ads Admin Manger
	 * 42777
	 */
	@Test
	public void testVerifyBudgetOfTheQuickListingAdFromAdsManager() {
		String l_budget = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleQLABudget);;
		assertTrue(verifyBudgetOfTheAd(l_budget), "AD Status is not PAUSED");
	}
	
	/**
	 * Verify the ad duration of Quick Listing Ads from ads Admin Manger
	 * 42778
	 */
	@Test
	public void testVerifyAdDurationOfTheQuickListingAdFromAdsManager() {
		int l_days_to_verify = 30;
		assertTrue(verifyAdDuration(l_days_to_verify), "Unable to verify the ad duration");
	}
	
	/**
	 * Verify the location of Quick Listing Ads from ads Admin Manger
	 * 42779
	 */
	@Test
	public void testVerifyAdLocationOfTheQuickListingAdFromAdsManger() {
		String l_ad_location = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleQLADefaultCity);
		assertTrue(verifyAdLocation(l_ad_location), "Unable to verify ad location");
	}
	
	private boolean verifyAdLocation(String pLocation) {
		return page.getAdLocation().contains(pLocation);
	}
	
	private boolean verifyAdDuration(int pDays) {
		boolean isVerifed = false;
		String l_start_date = page.getAdStartingDate();
		String l_end_date = page.getAdEndingDate();
		if(!l_start_date.isEmpty() && !l_end_date.isEmpty()) {
			l_start_date = l_start_date.split(" ")[0];
			l_end_date = l_end_date.split(" ")[0];
			isVerifed = verifyTheDates(l_start_date, l_end_date,pDays);
		}
		
		return isVerifed;
	}
	private boolean verifyTheDates(String pStartDate, String pEndDate, int pDays) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = (Date) sdf.parseObject(pStartDate);
			endDate = (Date) sdf.parseObject(pEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			AutomationLogger.error("Error in date format");
			AutomationLogger.error(e.getLocalizedMessage());
		}
		long diff = endDate.getTime() - startDate.getTime();
		long lDays = TimeUnit.MILLISECONDS.toDays(diff);//(diff / (1000*60*60*24));
		return lDays==pDays;	

	}
	private boolean verifytStatusOfTheAd(String pStatus) {
		return page.getAdStatus().equalsIgnoreCase(pStatus);
	}
	private boolean verifytSUPStatusOfTheAd(String pStatus) {
		return page.getAdSUPStatus().equalsIgnoreCase(pStatus);
	}
	private boolean verifyBudgetOfTheAd(String pBudget) {
		return page.getClientFee().contains(pBudget);
	}
	private void searchAdPreCondition() {
		String l_admin_id = getAdmin();
		String l_ad_id = getAdId();
		if(page.typeAdminId(l_admin_id) && page.typeAdId(l_ad_id) && page.clickOnSearchButton()) {
			ActionHelper.staticWait(5);
			page.refreshFBAPI();
		}else {
			throw new SkipException("Skipping the test becasuse [Search Ad] pre-condition was failed.");
		}
	}
	private String getAdId() {
		String l_ad_id = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAdId);
		l_ad_id = l_ad_id.split("ad=")[1];
		return l_ad_id;
	}
	private String getAdmin() {
		String l_admin_id = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_default_agent_id");
		return l_admin_id;
}
}
