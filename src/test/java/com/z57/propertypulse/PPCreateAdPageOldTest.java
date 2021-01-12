/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.pp.AdCreatedSuccessAlert;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class PPCreateAdPageOldTest extends PageTest{

	private WebDriver driver;
	private PPCreateAdOldPage page;

	@Override
	public AbstractPage getPage() {
		if(page == null){
			driver = getDriver();
			page = new PPCreateAdOldPage(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new PPCreateAdOldPage(driver);
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
	public void testCreateAnAd() {
		String lAdTitle="";
		String lAdDesc="";
		String lAdTargetedZip="";
		String lProvideZipOrCity = "";	
		String lAdFormat = "";
		String lAdLink = "";
		
		getPage();
		
		assertTrue(page.isCreateAdPage(),"Create Ad Page is not displayed");
//		assertTrue(page.isValidPreviewLink(EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url")), "The preview link is not valid");
		
		if(lAdTitle.isEmpty()) {
			assertTrue(page.isValidTitle(),"Ad Title is Empty");
		}else {
			assertTrue(page.typeAdTitle(lAdTitle),"Unable to type Ad Title in the field");
		}
		
		if(lAdDesc.isEmpty()) {
			assertTrue(page.isValidDescription(),"Ad Desc is Empty");
		}else {
			assertTrue(page.typeAdDescription(lAdDesc),"Unable to type Ad Description in the field");
		}
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Next step button");
		assertTrue(page.isSelectAdVisibilityOptionsPage(),"Step 2 page is not visible");
		
//		if(lAdTargetedZip.isEmpty()) {
//			assertTrue(page.isValidZip(),"Ad targeted Zip is Empty");
//		}else {
//			assertTrue(page.typeAdZipCode(lAdTargetedZip),"Unable to type Zip in the field");
//		}
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Next step button");
		
		assertTrue(page.isStep3PlaceOrderPage(),"Step 3 page is not visible");
		
		assertTrue(page.clickOnPaymentCheckBox(), "Unable to click on Payment checkbox");
		assertTrue(page.clickOnFBTestAdCheckBox(), "Unable to click on FB test ad checkkbox");
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Place Order button");
		
		AdCreatedSuccessAlert acsa = new AdCreatedSuccessAlert(driver);
		assertTrue(acsa.isAdCreatedAlert(), "Ads Created Alert is not visible");
		assertTrue(acsa.clickOnAdsOverviewButton(), "Unable to click on Ads Overview button");
		
		PPAdsOverviewPageOld adsOverviewPage = new PPAdsOverviewPageOld(driver);
		assertTrue(adsOverviewPage.isAdsOverviewPage(), "Ads Overview page is not visible");
		assertTrue(adsOverviewPage.isAdPlacedSuccessfully(ModuleCommonCache.getModuleCommonCache(ModuleCacheConstants.ListingsAddress).toString()), "Verification failed from Ads overview page.");
		
	}
	
	@Test
	@Parameters({"createAdDataFile"})
	public void testCreateCustomizeAd() {
		SoftAssert softAssert= new SoftAssert(); 
		getPage("/content/marketing/create-ad");
		
		String lAdTitle="";
		String lAdDesc="";
		String lAdTargetedZip="";
		String lListingAddress =ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.ListingsAddress);
		
		assertTrue(page.isCreateAdPage(),"Create Ad Page is not displayed");
		assertTrue(page.selectListingFromDropDown(lListingAddress),"Unable to select a listing from dropdown.");
		
		softAssert.assertTrue(page.isValidPreviewLink(getTheValidUrlToVerify()), "The preview link is not valid");
		
		if(lAdTitle.isEmpty()) {
			assertTrue(page.isValidTitle(),"Ad Title is Empty");
		}else {
			assertTrue(page.typeAdTitle(lAdTitle),"Unable to type Ad Title in the field");
		}
		
//		if(lAdDesc.isEmpty()) {
//			assertTrue(page.isValidDescription(),"Ad Desc is Empty");
//		}else {
//			assertTrue(page.typeAdDescription(lAdDesc),"Unable to type Ad Description in the field");
//		}
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Next step button");
		assertTrue(page.isSelectAdVisibilityOptionsPage(),"Step 2 page is not visible");
		
//		if(lAdTargetedZip.isEmpty()) {
//			assertTrue(page.isValidZip(),"Ad targeted Zip is Empty");
//		}else {
//			assertTrue(page.typeAdZipCode(lAdTargetedZip),"Unable to type Zip in the field");
//		}
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Next step button");
		
		assertTrue(page.isStep3PlaceOrderPage(),"Step 3 page is not visible");
		
		assertTrue(page.clickOnPaymentCheckBox(), "Unable to click on Payment checkbox");
		assertTrue(page.clickOnFBTestAdCheckBox(), "Unable to click on FB test ad checkkbox");
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Place Order button");
		
		AdCreatedSuccessAlert acsa = new AdCreatedSuccessAlert(driver);
		assertTrue(acsa.isAdCreatedAlert(), "Ads Created Alert is not visible");
		assertTrue(acsa.clickOnAdsOverviewButton(), "Unable to click on Ads Overview button");
		
		PPAdsOverviewPageOld adsOverviewPage = new PPAdsOverviewPageOld(driver);
		assertTrue(adsOverviewPage.isAdsOverviewPage(), "Ads Overview page is not visible");
		assertTrue(adsOverviewPage.isAdPlacedSuccessfully(ModuleCommonCache.getModuleCommonCache(ModuleCacheConstants.ListingsAddress).toString()), "Unable to Post an Ad..Ad Status Failed");
		
	}
	
	@Test
	public void testCreateCMAAd() {
		getPage("/content/marketing/create-ad");
		String lAdTargetedZip="";
		
		assertTrue(page.isCreateAdPage(),"Create Ad Page is not displayed");
		assertTrue(page.clickOnPlaceAdButton(), "Unable to click on Place Ad button");
		
//		if(lAdTargetedZip.isEmpty()) {
//			assertTrue(page.isValidZip(),"Ad targeted Zip is Empty");
//		}else {
//			assertTrue(page.typeAdZipCode(lAdTargetedZip),"Unable to type Zip in the field");
//		}
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Next step button");
		
		assertTrue(page.isStep3PlaceOrderPage(),"Step 3 page is not visible");
		
		assertTrue(page.clickOnPaymentCheckBox(), "Unable to click on Payment checkbox");
		assertTrue(page.clickOnFBTestAdCheckBox(), "Unable to click on FB test ad checkkbox");
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Place Order button");
		
		AdCreatedSuccessAlert acsa = new AdCreatedSuccessAlert(driver);
		assertTrue(acsa.isAdCreatedAlert(), "Ads Created Alert is not visible");
		assertTrue(acsa.clickOnAdsOverviewButton(), "Unable to click on Ads Overview button");
		
		PPAdsOverviewPageOld adsOverviewPage = new PPAdsOverviewPageOld(driver);
		assertTrue(adsOverviewPage.isAdsOverviewPage(), "Ads Overview page is not visible");
		assertTrue(adsOverviewPage.isAdPlacedSuccessfully(FrameworkConstants.IsTimeToSell), "Unable to find the ad status 'Live (Paused)'..");

	}
	
	@Test
	public void testDisAbleAllTheAds() {
		getPage("/content/marketing/ads");
		assertTrue(new PPAdsOverviewPageOld(driver).disableAllTheAds(), "Unable to disable all the ads");
		
	}
	

	@Test
	@Parameters({"dataFile2"})
	public void testCreateAnAdForFacebookAndInsta(String pDataFile) {
		JSONObject lDataObject = getDataFile(pDataFile);
		getPage();
		step1CreateAdFlow(lDataObject);
		step2CreateAdFlow(lDataObject);
		step3CreateAdFlow();
		
		AdCreatedSuccessAlert acsa = new AdCreatedSuccessAlert(driver);
		assertTrue(acsa.isAdCreatedAlert(), "Ads Created Alert is not visible");
		assertTrue(acsa.clickOnAdsOverviewButton(), "Unable to click on Ads Overview button");
		
		PPAdsOverviewPageOld adsOverviewPage = new PPAdsOverviewPageOld(driver);
		assertTrue(adsOverviewPage.isAdsOverviewPage(), "Ads Overview page is not visible");
		assertTrue(adsOverviewPage.isAdPlacedSuccessfully(ModuleCommonCache.getModuleCommonCache(ModuleCacheConstants.ListingsAddress).toString()), "Verification failed from Ads overview page.");
		
	}
	
	private String getTheValidUrlToVerify() {
		DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
		String v2BaseUrl = EnvironmentFactory.configReader.getPropertyByName("z57_site_v2_base_url").split("://")[1];
		if(dbHelperMethods.isWebSiteHTTPSEnables(v2BaseUrl)) {
			v2BaseUrl = "https://"+v2BaseUrl;
		}else {
			v2BaseUrl = "http://"+v2BaseUrl;
		}
		return v2BaseUrl;
	}

	public void step1CreateAdFlow(JSONObject pDataObject) {
		String lAdTitle=pDataObject.optString("ad_title");
		String lAdDesc=pDataObject.optString("ad_description");
		String lAdFormat=pDataObject.optString("ad_format");
		String lAdLink = pDataObject.optString("ad_link");
		String lAdType = pDataObject.optString("ad_type");
		String lListingAddress =ModuleCommonCache.getElement(getThreadId().toString(), ModuleCacheConstants.ListingsAddress);
		SoftAssert softAssert= new SoftAssert(); 
		
		assertTrue(page.isCreateAdPage(),"Create Ad Page is not displayed");
		if(lAdType.equalsIgnoreCase("CMA")) {
			assertTrue(page.selectListingFromDropDown(lListingAddress),"Unable to select a listing from dropdown.");
		}
		softAssert.assertTrue(page.isValidPreviewLink(getTheValidUrlToVerify()), "The preview link is not valid");
		
		if(lAdTitle.isEmpty()) {
			assertTrue(page.isValidTitle(),"Ad Title is Empty");
		}else {
			assertTrue(page.typeAdTitle(lAdTitle),"Unable to type Ad Title in the field");
		}	
		if(lAdDesc.isEmpty()) {
			assertTrue(page.isValidDescription(),"Ad Desc is Empty");
		}else {
			assertTrue(page.typeAdDescription(lAdDesc),"Unable to type Ad Description in the field");
		}
		if(!lAdFormat.isEmpty()) {
			assertTrue(page.selectAdFormat(lAdFormat),"Unable to select Ad format ..");

		}
		if(!lAdLink.isEmpty()) {
			assertTrue(page.typeListingUrl(lAdLink),"Unable to type Ad listing url..");

		}
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Next step button");
		
	}
	
	public void step2CreateAdFlow(JSONObject pDataObject) {
		String lAdTargetedZip=pDataObject.optString("ad_targeted_zip");
		String lAdAmount=pDataObject.optString("ad_amount");
		String lPlatforms = pDataObject.optString("platforms");
		
		assertTrue(page.isSelectAdVisibilityOptionsPage(),"Step 2 page is not visible");
		
//		if(lAdTargetedZip.isEmpty()) {
//			assertTrue(page.isValidZip(),"Ad targeted Zip is Empty");
//		}else {
//			assertTrue(page.typeAdZipCode(lAdTargetedZip),"Unable to type Zip in the field");
//		}
		assertTrue(page.selectPlatforms(lPlatforms), "Unable to select the platforms.");
		assertTrue(page.selectAdAmount(lAdAmount), "Unable to select the Ad reach plan..");
		
		assertTrue(page.clickOnNextButton(), "Unable to click on Next step button");
	}
	
	public void step3CreateAdFlow() {
		assertTrue(page.isStep3PlaceOrderPage(),"Step 3 page is not visible");

		assertTrue(page.clickOnPaymentCheckBox(), "Unable to click on Payment checkbox");
		assertTrue(page.clickOnFBTestAdCheckBox(), "Unable to click on FB test ad checkkbox");

		assertTrue(page.clickOnNextButton(), "Unable to click on Place Order button");
	}
}
