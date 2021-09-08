/**
 * 
 */
package com.zurple.backoffice.admin;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZACreateSellerLeadsAdPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;

/**
 * @author darrraqi
 *
 */
public class ZACreateSellerLeadsAdPageTest extends PageTest{
	private WebDriver driver;
	private ZACreateSellerLeadsAdPage page;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZACreateSellerLeadsAdPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		driver = getDriver();
		page = new ZACreateSellerLeadsAdPage(driver);
		page.setUrl(pUrl);
		page.setDriver(driver);
		return page;

	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testCreateAndVerifyLeadFormSellerLeadAd(String pDataFile) {
		getPage("/admin/create-sl-ad");
		assertTrue(page.isSellerLeadAdPage(), "Seller lead ad page is not displayed..");
		JSONObject dataObject = getDataFile(pDataFile);
		fillSellerLeadForm(dataObject);
		assertTrue(page.clickOnSubmitButton(),"Unable to click on submit button..");
		assertTrue(page.isSuccessMessageVisible(), "Success message for ad is not visible");
	}
	
	private void fillSellerLeadForm(JSONObject pDataObject) {
		String l_package_id = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_admin_package_id");
		String l_admin_id = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_default_agent_id");
		String l_budget = String.valueOf(generateRandomInt(100,1000));
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdBudget, l_budget);
		assertTrue(page.typeAndSelectPackage(l_package_id), "Unable to select the package");
		assertTrue(page.typeAndSelectAdmin(l_admin_id), "Unable to select the admin");
		assertTrue(page.typeBudget(l_budget), "Unable to type ad budget");
		assertTrue(page.typeZipCode(pDataObject.optString("zip_code")), "Unable to type ad zip code");
		assertTrue(page.typeAdCity(pDataObject.optString("main_city")), "Unable to type main city");
		assertTrue(page.clickAndSelectAdType(pDataObject.optString("ad_type")), "Unable to select thead type");
		assertTrue(page.clickAndSelectCarousel(pDataObject.optString("cma_carousel")), "Unable to select cma carousel image");
		assertTrue(page.clickAndSelectCMAVideo(pDataObject.optString("cma_video")), "Unable to select cma video");
		assertTrue(page.clickAndSelectDownloadCarousel(pDataObject.optString("download_carousel")), "Unable to select download carousel");
		assertTrue(page.clickPausedRadioButton(), "Unable to click on Paused radio button");
	}
	
	/*
	 * @AfterTest public void closeBrowser() { closeCurrentBrowser(); }
	 */
}
