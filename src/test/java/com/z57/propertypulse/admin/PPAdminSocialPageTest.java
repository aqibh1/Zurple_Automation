/**
 * 
 */
package com.z57.propertypulse.admin;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.z57.propertypulse.PPCreateAdPage;
import com.z57.propertypulse.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

/**
 * @author adar
 *
 */
public class PPAdminSocialPageTest extends PageTest {
	private WebDriver driver;
	private PPAdminSocialPage page;
	JSONObject dataObject;
	
	@Override
	public void testTopMenu() {
		// TODO Auto-generated method stub
		
	}

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
	public AbstractPage getPage() {
		if(page == null) {
			driver = getDriver();
			page = new PPAdminSocialPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page == null) {
			driver = getDriver();
			page = new PPAdminSocialPage(driver);
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
	@Parameters({"dataFile"})
	public void testVerifyAdDetails(String pDataFile) {
		getPage();
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdRenwalDate, "07/10/2020");
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdStartDate, "06/10/2020");
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdEndDate, "07/09/2020");
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.PPADID, "2322");
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdBudget, 240);
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdCity, "San Diego, CA");
	
		
		String lAdId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.PPADID);
		String lUrl = "/admin/social?account=&ad_id="+lAdId+"&num_billed=&ad_type=0&ad_billed=0&ui_step=4&ad_state=0&api_status=0&test_ads=0&date_start=06%2F01%2F2020&date_end=&limit=100";
		page = null;
		getPage(lUrl);
		dataObject = getDataFile(pDataFile);
		String lAdFrequencey = dataObject.optString("ad_duration");
		String lAdType = dataObject.optString("ad_type");
		String lAdFormat = dataObject.optString("slideshow_or_image");
		String lPlatforms = dataObject.optString("platforms");
		String lTitle = dataObject.optString("ad_title").isEmpty()?ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.PPADTitle):dataObject.optString("ad_title");
		String lDesc = dataObject.optString("ad_description").isEmpty()?ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.PPADDesc):dataObject.optString("ad_description");
		String lBudget = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdBudget).toString();
		String lLocation = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdCity);
		String lFBAd_Start_Date = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdStartDate);
		String lFBAd_End_Date = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdEndDate);
		
		assertTrue(page.isAdminSocialPage(), "Admin Social Page is not visible..");
		assertTrue(page.verifyAdId(lAdId), "Unable to verify ad id..");
		assertTrue(page.verifyAdFrequency(lAdFrequencey), "Unable to verify ad frequency..");
		assertTrue(page.verifyAdType(lAdType), "Unable to verify ad type..");
		assertTrue(page.verifyAdFormat(lAdFormat), "Unable to verify ad format..");
		assertTrue(page.verifyAdPlatforms(lPlatforms), "Unable to verify ad platforms..");
		assertTrue(page.verifyThumbnail(), "Unable to verify ad thumbnail image..");
		assertTrue(page.verifyAdTitle(lTitle), "Unable to verify ad title..");
		assertTrue(page.verifyAdDescription(lDesc), "Unable to verify ad desc..");
		assertTrue(page.verifyAdLocation(lLocation), "Unable to verify ad location..");
		assertTrue(page.verifyBudget(lBudget), "Unable to verify ad desc..");
		assertTrue(page.verifyAdFBStartDate(lFBAd_Start_Date), "Unable to verify ad start date..");
		assertTrue(page.verifyFBAdStatus(), "Unable to verify ad status..");
		assertTrue(page.verifyAdFBEndDate(lFBAd_End_Date), "Unable to verify ad end date..");
		
	}
}
