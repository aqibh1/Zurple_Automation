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
import resources.utility.ActionHelper;

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
	
	/**
	 * @param pDataFile
	 * Verify user can create 'Lead Form' Ad using the admin tool
	 * 45698
	 */
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
	
	/**
	 * @param pDataFile
	 * Verify user can create 'Conversion' Ad using the admin tool
	 * 45765
	 */
	@Test
	@Parameters({"dataFile"})
	public void testCreateAndVerifyConversionSellerLeadAd(String pDataFile) {
		getPage("/admin/create-sl-ad");
		assertTrue(page.isSellerLeadAdPage(), "Seller lead ad page is not displayed..");
		JSONObject dataObject = getDataFile(pDataFile);
		fillSellerLeadForm(dataObject);
		assertTrue(page.clickOnSubmitButton(),"Unable to click on submit button..");
		assertTrue(page.isSuccessMessageVisible(), "Success message for ad is not visible");
	}
	
	/**
	 * Verify user can create 'Conversion + Lead Form' Ad using the admin tool
	 * 45774
	 */
	@Test
	@Parameters({"dataFile"})
	public void testCreateAndVerifyLeadFormConversionSellerLeadAd(String pDataFile) {
		getPage("/admin/create-sl-ad");
		assertTrue(page.isSellerLeadAdPage(), "Seller lead ad page is not displayed..");
		JSONObject dataObject = getDataFile(pDataFile);
		fillSellerLeadForm(dataObject);
		assertTrue(page.clickOnSubmitButton(),"Unable to click on submit button..");
		assertTrue(page.isSuccessMessageVisible(), "Success message for ad is not visible");
	}
	
	/**
	 * @param pDataFile
	 * Verify the error alert is generated if Package is not provided
	 * 47362
	 */
	@Test
	public void testVerifyPackageAlertIsTriggered() {
		getPage("/admin/create-sl-ad");
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isPackageAlertVisible("please select package"),"Unable to verify alert text message..");
		
	}
	
	/**
	 * Verify the error alert is generated if Admin is not provided
	 * 47363
	 */
	@Test
	public void testVerifyAdminAlertIsTriggered() {
		getPage("/admin/create-sl-ad");
		String l_package_id = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_admin_package_id");
		assertTrue(page.typeAndSelectPackage(l_package_id), "Unable to select the package");
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isAdminAlertVisible("please select admin"),"Unable to verify alert text message..");
		
	}
	/**
	 * Verify alert is triggered if ad form type is not selected
	 * 47392
	 */
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifySLFormatAlertIsTriggered(String pDataFile) {
		getPage("/admin/create-sl-ad");
		String l_admin_id = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_default_agent_id");
		assertTrue(page.typeAndSelectAdmin(l_admin_id), "Unable to select the admin");
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isBudgetAlertVisible("please select format"),"Unable to verify alert text message..");		
	}
	

	/**
	 * Verify the error alert is generated if budget is not provided
	 * 47359
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyBudgetAlertIsTriggered(String pDataFile) {
		getPage("/admin/create-sl-ad");
		assertTrue(page.selectAdFormat("SL"), "Unable to select the Ad format");
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isBudgetAlertVisible("please enter budget"),"Unable to verify alert text message..");		
	}

	/**
	 * Verify the alert is triggered if budget provided is less than $100
	 * 47369
	 */
	@Test
	@Parameters({"dataFile"})
	public void testVerifyBudgetLessThanHundredAlertIsTriggered(String pDataFile) {
		getPage("/admin/create-sl-ad");
		assertTrue(page.typeBudget("25"), "Unable to type ad budget");
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isBudgetLessThanHundredAlertVisible("Budget needs to be between 100 and 1000"),"Unable to verify alert text message..");		
	}
	
	/**
	 * Verify the error alert is generated if Zip Code is not provided
	 * 47360
	 */
	@Test
	public void testVerifyZipCodeAlertIsTriggered() {
		getPage("/admin/create-sl-ad");
		assertTrue(page.typeBudget("100"), "Unable to type ad budget");
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isZipCodeAlertVisible("please select zip"),"Unable to verify alert text message..");		
	}
	
	/**
	 * Verify the error alert is generated if Main City is not provided
	 * 47361
	 */
	@Test
	public void testVerifyCityAlertIsTriggered() {
		getPage("/admin/create-sl-ad");
		assertTrue(page.typeZipCode("91910"), "Unable to type Zip Code");
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isCityAlertVisible("please enter city name"),"Unable to verify alert text message..");		
	}
	
	/**
	 * Verify the error alert is generated if Ad Type is not selected
	 * 47364
	 */
	@Test
	public void testVerifyAdTypeAlertIsTriggered() {
		getPage("/admin/create-sl-ad");
		assertTrue(page.typeAdCity("San Jose"), "Unable to type main city");
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isAdTypeAlertVisible("please select type"),"Unable to verify alert text message..");		
	}
	
	/**
	 * Verify the error alert is generated if CMA-Carousel is not selected
	 * 47365
	 */
	@Test
	public void testVerifyCMACarouselAlertIsTriggered() {
		getPage("/admin/create-sl-ad");
		assertTrue(page.clickAndSelectAdType("Conversion + Lead Form"), "Unable to select thead type");
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isAlertMessageVisible("Image type CMA-Carousel is required"),"Unable to verify alert text message..");		
	}
	
	/**
	 * Verify the error alert is generated if CMA-Video is not selected
	 * 47366
	 */
	@Test
	public void testVerifyCMAVideoAlertIsTriggered() {
		getPage("/admin/create-sl-ad");
//		assertTrue(page.clickAndSelectCarousel("Default Images",""), "Unable to select cma carousel image");
//		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isAlertMessageVisible("Image type CMA-Video is required"),"Unable to verify alert text message..");		
	}
	
	/**
	 * Verify the error alert is generated if Download - Carousel is not selected
	 * 47367
	 */
	@Test
	public void testVerifyCarouselDownloadAlertIsTriggered() {
		getPage("/admin/create-sl-ad");
//		assertTrue(page.clickAndSelectCMAVideo("Default Images",""), "Unable to select cma video");
//		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(page.isAlertMessageVisible("Image type Search Homes is required"),"Unable to verify alert text message..");		
	}
	
	private void fillSellerLeadForm(JSONObject pDataObject) {
		String l_package_id = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_admin_package_id");
		String l_admin_id = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_default_agent_id");
		String l_budget = "100";//String.valueOf(generateRandomInt(100,1000));
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdBudget, l_budget);
		assertTrue(page.typeAndSelectPackage(l_package_id), "Unable to select the package");
		assertTrue(page.typeAndSelectAdmin(l_admin_id), "Unable to select the admin");
		assertTrue(page.selectAdFormat("SL"), "Unable to select the Ad format");
		assertTrue(page.typeBudget(l_budget), "Unable to type ad budget");
		assertTrue(page.typeZipCode(pDataObject.optString("zip_code")), "Unable to type ad zip code");
		assertTrue(page.typeAdCity(pDataObject.optString("main_city")), "Unable to type main city");
		assertTrue(page.clickAndSelectAdType(pDataObject.optString("ad_type")), "Unable to select thead type");
		assertTrue(page.clickAndSelectCarousel(pDataObject.optString("cma_carousel"),pDataObject.optString("cma_carousel_images")), "Unable to select cma carousel image");
		ActionHelper.staticWait(2);
		assertTrue(page.clickAndSelectCMAVideo(pDataObject.optString("cma_video"),pDataObject.optString("cma_video_images")), "Unable to select cma video");
		ActionHelper.staticWait(2);
		assertTrue(page.clickAndSelectDownloadCarousel(pDataObject.optString("download_carousel"),pDataObject.optString("download_carousel_images")), "Unable to select download carousel");
		ActionHelper.staticWait(2);
		assertTrue(page.clickPausedRadioButton(), "Unable to click on Paused radio button");
	}
	
	/*
	 * @AfterTest public void closeBrowser() { closeCurrentBrowser(); }
	 */
}
