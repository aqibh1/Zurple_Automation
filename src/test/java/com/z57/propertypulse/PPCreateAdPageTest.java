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
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPCreateAdPageTest extends PageTest{
	private PPCreateAdPage page;
	WebDriver driver;
	JSONObject dataObject;
	String lTargetCity = "";
	String lSelectGoal = "";
	String lCustomizeOrPlaceAd = "";
	String lSlideShowOrImage = "";
	String lTitle = "";
	String lDesc = "";
	String lDomain = "";
	
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
			page = new PPCreateAdPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page == null) {
			driver = getDriver();
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.MINUTES);
			page = new PPCreateAdPage(driver);
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
	public void testCreateCMAAd(String pDataFile) {
		getPage("/content/marketing/create-ad");
		dataObject = getDataFile(pDataFile);
		
		lCustomizeOrPlaceAd = dataObject.optString("customize_place");
		lSlideShowOrImage = dataObject.optString("slideshow_or_image");
		lTitle = dataObject.optString("ad_title");
		lDesc = dataObject.optString("ad_description");
		lDomain = dataObject.optString("ad_link");
		
		assertTrue(page.isCreateAdPageVisible(), "Create ad page is not visible..");
		assertTrue(page.selectYourGoal("Find Sellers"), "Unable to select the goal.. Section1 Step 1");
		assertTrue(page.isSection2Step1Visible(), "Section 2 step 1 is not visible..");
		//Verfications of section 2 for CMA ads
		page.verificationOfStep2CMAAds();
		ActionHelper.staticWait(3);
		assertTrue(page.selectYourAdCMA(dataObject.optString("ad_type"), dataObject.optString("customize_place")), "Unable to select CMA Ad");
		if(dataObject.optString("customize_place").equalsIgnoreCase("customize")) {
			assertTrue(page.isSection3Step1Visible(), "Section 3 Step1 is not visible..");
			//Check slide show button is disabled because of CMA ad
			assertTrue(page.isSlideShowButtonDisabled(), "Slide show button is not disabled for CMA Ads");
			assertTrue(page.verifyAdHeading(lTitle), "Unable to verify ad headline..");
			assertTrue(page.verifyAdDescription(lDesc), "Unable to verify ad description..");
			assertTrue(page.verifyAdPreviewUrl(lDomain), "Unable to verify ad preview URL..");
			assertTrue(page.verifyAdPreviewHeadingIsVisible(), "Ad preview heading is not visible in section 3 step 1..");
			assertTrue(page.verifyAdDescriptionInAdPreviewSection3(dataObject.optString("ad_description")), "Ad preview description is not visible in section 3 step 1..");
			assertTrue(page.verifyAdPreviewImageIsVisibleSection3(), "Ad preview Image is not visible in section 3 step 1..");
			assertTrue(page.clickOnNextStepButton(), "Unable to click on Next button step 1");
		}
		adCreationStep2(dataObject.optString("ad_amount"),dataObject.optString("platforms"));
		assertTrue(page.clickOnNextStepButton(), "Unable to click on Next button step 2");
		adCreationStep3();
		String lAdId = driver.getCurrentUrl().split("=")[1];
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.PPADID, lAdId);
		assertTrue(page.clickOnNextStepButton(), "Unable to click on Place button step 3");
	}
	@Test
	@Parameters({"dataFile"})
	public void testCreateListingAds(String pDataFile) {
		getPage("/content/marketing/create-ad");
		dataObject = getDataFile(pDataFile);
		int lListing_count = 0;
		lSelectGoal = dataObject.optString("goal");
		lCustomizeOrPlaceAd = dataObject.optString("customize_place");
		lSlideShowOrImage = dataObject.optString("slideshow_or_image");
		lTitle = "";
		lDesc = "";
		lDomain = "";
		
		assertTrue(page.isCreateAdPageVisible(), "Create ad page is not visible..");
		
		switch(lSelectGoal) {
		case "Promote Listing":
			
			break;
		case "Announce Open House":
		
			break;
		case "Toot Your Horn":
			assertTrue(page.selectYourGoal(lSelectGoal), "Unable to select the goal.. Section1 Step 1");
			assertTrue(page.isSelectYourAdSection2Visible(), "Section 2 step 1 is not visible..");
			lListing_count = generateRandomInt(page.getListOfProperties(lSelectGoal));
			lTitle = page.getAdsTitle(lSelectGoal, lListing_count);
			lDesc = page.getAdsDescription(lSelectGoal, lListing_count);
			lDomain = page.getAdsDomain(lListing_count);
			assertTrue(lTitle.contains("Just Sold in"), "Title doesn't contain Just Sold in keywords..");
			assertTrue(lDesc.contains("Listing just sold in"), "Description doesn't contain Listing Just Sold in keywords..");
			assertTrue(page.clickOnCustomizeButton(lListing_count, lCustomizeOrPlaceAd), "Unable to click on Customize button");
			assertTrue(page.clickOnContinueWithoutUpdateButton(), "Unable to click on Continue without updating button..");

			break;
		case "Market Price Reduction":
			
			break;
		case "Be the Expert":
			
			break;
		case "Finish an Incomplete Ad":
			break;
		}
		//SECTION 3 STEP 1
		if(lCustomizeOrPlaceAd.equalsIgnoreCase("customize")) {
			assertTrue(page.isSection3Step1Visible(), "Section 3 Step1 is not visible..");
			//Check slide show button is disabled because of CMA ad
			if(lSlideShowOrImage.equalsIgnoreCase("slideshow")) {
				assertTrue(page.clickOnSlideShowButton(), "Unable to click on slideshow button..");
			}else {
				assertTrue(page.clickOnImageButton(), "Unable to click on slideshow button..");
			}
			ActionHelper.staticWait(10);
			
			assertTrue(page.verifyAdHeading(lTitle), "Unable to verify ad headline..");
			assertTrue(page.verifyAdDescription(lDesc), "Unable to verify ad description..");
			assertTrue(page.verifyAdPreviewUrl(lDomain), "Unable to verify ad preview URL..");
			assertTrue(page.verifyAdPreviewHeadingIsVisible(), "Ad preview heading is not visible in section 3 step 1..");
			assertTrue(page.verifyAdDescriptionInAdPreviewSection3(lDomain), "Ad preview description is not visible in section 3 step 1..");
			assertTrue(page.verifyAdPreviewImageIsVisibleSection3(), "Ad preview Image is not visible in section 3 step 1..");
			assertTrue(page.clickOnNextStepButton(), "Unable to click on Next button step 1");
		}
		
		adCreationStep2(dataObject.optString("ad_amount"),dataObject.optString("platforms"));
		assertTrue(page.clickOnNextStepButton(), "Unable to click on Next button step 2");
		adCreationStep3();
		String lAdId = driver.getCurrentUrl().split("=")[1];
		
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.PPADID, lAdId);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.PPADTitle, lTitle);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.PPADDesc, lDesc);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.PPADDomain, lDomain);

		assertTrue(page.clickOnNextStepButton(), "Unable to click on Place button step 3");
		assertTrue(page.isAdPlacedSuccessfully(), "Ads overview dialog is not displayed..");
		assertTrue(page.clickOnAdsOverviewPage(), "Unable to click on ads overview page button..");
	}
	//Step 2 Method for verification 
	private void adCreationStep2(String pBudget, String pPlatforms) {
		assertTrue(page.isStep2ProgressBar(), "Step 2 progress bar is not diplayed");
		assertTrue(page.isStep2HeadingDisplayed(), "Step 2 heading is not displayed..");
		assertTrue(page.isStep2Heading2Displayed(), "Step 2 h2 heading is not displayed..");
		assertTrue(page.isStep2AdCityDisplayed(), "Target City is not populated on Step 2..");
		
		lTargetCity = page.getTargetCity().split("\n")[1];
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdCity, lTargetCity);
		
		assertTrue(page.selectTargetReach(pBudget), "Unable to select target reach..");
		assertTrue(page.verifyTargetReachIsDisplayed(), "Estimated reach numbers are not populated..");
		assertTrue(page.verifyDefaultPlatformsSelected(), "Instagram and Facebook default platforms are not selected..");
		assertTrue(page.selectPlatform(pPlatforms), "Unable to select the platforms..");
		if(dataObject.optString("slideshow_or_image").equalsIgnoreCase("slideshow")) {
			assertTrue(page.isSlideShowAtStep2(), "Slid Show is not displayed at step 2 of ad craetion");
		}else {
			assertFalse(page.isSlideShowAtStep2(), "Image selection is shown as slide show in the step 2");
		}

		assertTrue(page.isAdDescOnStep2(lDesc), "Step 2 ad description does not match..");
		assertTrue(page.isAdDomainOnStep2(lDomain), "Step 2 ad domain does not match");
		assertTrue(page.verifyAdTitleStep2(lTitle), "Step 2 ad title does not match..");

	}
	
	//Step 3 verifications
	private void adCreationStep3() {
		String lAdDuration = dataObject.optString("ad_duration");
		int lAdBudget = dataObject.optString("ad_duration").equalsIgnoreCase("Monthly")?Integer.parseInt(dataObject.optString("ad_amount"))*4:Integer.parseInt(dataObject.optString("ad_amount"));
		String lAdStartDate = "";
		String lAdEndDate ="";
		
		assertTrue(page.isStep3ProgressbarDisplayed(), "Step 3 progress bar is not displayed..");
		assertTrue(page.isStep3Heading(), "Step 3 heading is not displayed..");
		assertTrue(page.isStep3Heading2Displayed(), "Step 3 heading2 is not displayed..");
		assertTrue(page.isValidAdHeadingStep3(dataObject.optString("ad_heading_step3")), "Ad heading title is not displayed.");
		if(dataObject.optString("slideshow_or_image").equalsIgnoreCase("slideshow")) {
			assertTrue(page.isSlideShowAtStep2(), "Slid Show is not displayed at step 3 of ad craetion");
		}else {
			assertFalse(page.isSlideShowAtStep2(), "Image selection is shown as slide show in the step 3");
		}

		assertTrue(page.isAdDescOnStep2(lDesc), "Step 3 ad description does not match..");
		assertTrue(page.isAdDomainOnStep2(lDomain), "Step 3 ad domain does not match");
		assertTrue(page.verifyAdTitleStep2(lTitle), "Step 3 ad title does not match..");
		assertTrue(page.verifyAdSpecificationsStep3(lTargetCity), "Unable to verify target city on step 3..");
		String lPlan = "";
		String lRenewalDate = "";
		if(lAdDuration.equalsIgnoreCase("Monthly")) {
			lAdStartDate = getTodaysDate(0);
			lAdEndDate = getTodaysDate(29);
			lPlan ="$"+String.valueOf(lAdBudget)+".00 per month";
			assertTrue(page.verifyAdSpecificationsStep3(lPlan), "Unable to verify target city on step 3..");
			assertTrue(page.verifyAdSpecificationsStep3(lAdDuration), "Unable to verify Frequency on step 3..");
			assertTrue(page.verifyAdSpecificationsStep3(lAdStartDate+" - "+lAdEndDate), "Unable to verify Schedule start date on step 3..");
			lRenewalDate = getTodaysDate(30);
		}else {
			lPlan ="$"+dataObject.optString("ad_amount")+".00 per week";
			lAdStartDate = getTodaysDate(0);
			lAdEndDate = getTodaysDate(6);
			assertTrue(page.verifyAdSpecificationsStep3(lPlan), "Unable to verify target city on step 3..");
			assertTrue(page.verifyAdSpecificationsStep3(lAdDuration), "Unable to verify Frequency on step 3..");
			assertTrue(page.verifyAdSpecificationsStep3(lAdStartDate+" - "+lAdEndDate), "Unable to verify Schedule start date on step 3..");
			lRenewalDate = getTodaysDate(7);
		}
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdRenwalDate, lRenewalDate);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdStartDate, lAdStartDate);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdEndDate, lAdEndDate);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.AdBudget, lAdBudget);
		
		String lPlatforms = dataObject.optString("platforms").contains(",")?dataObject.optString("platforms").replace(",", " &"):dataObject.optString("platforms");
		assertTrue(page.verifyAdSpecificationsStep3(lPlatforms), "Unable to verify platforms on step 3..");
		assertTrue(page.verifyAdRenewDate(lRenewalDate), "Unable to verify ad renewal date on step 3..");
		assertTrue(page.clickOnTermsAndCondCheckbox(), "Unable to click on terms and conditions checkbox on step 3..");
		assertTrue(page.clickOnTestAd(), "Unable to click on test ad checkbox on step 3..");

	}

}
