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
	@Test
	@Parameters({"dataFile"})
	public void testVerifyAdDetailsOnAdsOverviewPage(String pDataFile) {
		getPage("/content/marketing/ads");
		dataObject = getDataFile(pDataFile);
		
		String lAdId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.PPADID);
		String lTargetCity = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdCity);
		String lRenewalDate = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdRenwalDate);
		String lBudget = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.AdBudget).toString();
		String lDomain = dataObject.optString("ad_type_ao").equalsIgnoreCase("CMA")?EnvironmentFactory.configReader.getPropertyByName("z57_pp_base_url"):EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url");
		lDomain = lDomain.replace("https://", "");
		
		assertTrue(page.isAdsOverviewPage(), "Ads Overview page is not displayed..");
		assertTrue(page.verifyAdType(dataObject.optString("ad_type_ao"), lAdId), "Unable to verify ad type.");
		assertTrue(page.isAdDisableButtonDisplayed(lAdId), "Disable ad button is not visible.");
		assertTrue(page.verifyPlatforms(dataObject.optString("platforms"),lAdId), "Unable to verify the platforms..");
		assertTrue(page.verifyAdCity(lTargetCity,lAdId), "Unable to verify the platforms..");
		assertTrue(page.verifyAdPlanAndRenewalDate(lBudget,lAdId), "Unable to verify ad plan..");
		assertTrue(page.verifyAdPlanAndRenewalDate(lRenewalDate,lAdId), "Unable to verify ad renewal date..");
		if(dataObject.optString("slideshow_or_image").equalsIgnoreCase("slideshow")) {
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
