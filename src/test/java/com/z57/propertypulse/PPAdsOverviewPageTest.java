/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

/**
 * @author adar
 *
 */
public class PPAdsOverviewPageTest extends PageTest{

	private WebDriver driver;
	private PPAdsOverviewPage page;
	private JSONObject dataObject;
	
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
		if(page == null){
			driver = getDriver();
			page = new PPAdsOverviewPage(driver);
			page.setUrl("");
			page.setDriver(driver);

		}
		return page;
	}
	public AbstractPage getPage(String purl) {
		if(page == null){
			driver = getDriver();
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MINUTES);
			page = new PPAdsOverviewPage(driver);
			page.setUrl(purl);
			page.setDriver(driver);

		}
		return page;
	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	@Test(dependsOnGroups = { "AD_DETAILS" })
	@Parameters({"dataFile"})
	public void testVerifyAdDetailsOnAdsOverviewPage(String pDataFile) {
		getPage("/content/marketing/ads");
		dataObject = getDataFile(pDataFile);
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdRenwalDate, "06/19/2020");
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdStartDate, "06/12/2020");
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdEndDate, "06/18/2020");
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.PPADID, "2342");
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdBudget, 25);
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdCity, "La Jolla, CA");
		
		String lAdId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.PPADID);
		String lTargetCity = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdCity);
		String lRenewalDate = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdRenwalDate);
		String lBudget = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdBudget).toString();
		String lSlideshowOrImage = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.PPADImageSlideshow);
		String lDomain = dataObject.optString("ad_type_ao").equalsIgnoreCase("CMA")?EnvironmentFactory.configReader.getPropertyByName("z57_pp_base_url"):EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url");
		lDomain = lDomain.replace("https://", "");
		String lAdType = dataObject.optString("ad_type_ao");
		
		assertTrue(page.isAdsOverviewPage(), "Ads Overview page is not displayed..");
		if(!lAdType.isEmpty()) {
			assertTrue(page.verifyAdType(lAdType, lAdId), "Unable to verify ad type.");
		}
		assertTrue(page.isAdDisableButtonDisplayed(lAdId), "Disable ad button is not visible.");
		assertTrue(page.verifyPlatforms(dataObject.optString("platforms"),lAdId), "Unable to verify the platforms..");
		assertTrue(page.verifyAdCity(lTargetCity,lAdId), "Unable to verify the platforms..");
		assertTrue(page.verifyAdPlanAndRenewalDate(lBudget,lAdId), "Unable to verify ad plan..");
		assertTrue(page.verifyAdPlanAndRenewalDate(lRenewalDate,lAdId), "Unable to verify ad renewal date..");
		if(lSlideshowOrImage.equalsIgnoreCase("slideshow")) {
			assertTrue(page.isSlideShowAtStep2(lAdId), "Unable to verify slide show..");
		}else {
			assertFalse(page.isSlideShowAtStep2(lAdId), "Unable to verify slide show..");
			assertTrue(page.isPreviewImageDisplayed(lAdId), "Unable to verify ad preview image..");
		}
		assertTrue(page.isAdDomainOnStep2(lDomain,lAdId), "Unable to verify ad domain..");
		assertTrue(page.verifyAdTitleStep2(dataObject.optString("ad_title"),lAdId), "Unable to verify ad title..");
		assertTrue(page.verifyAdStatus(lAdId, "Live (Paused)"), "Unable to verify ad status.");

	}
}
