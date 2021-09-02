/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zurple.backoffice.ads.ZBOAdsOverviewPage;
import com.zurple.backoffice.ads.ZBOCreateAdPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.CacheFilePathsConstants;

/**
 * @author adar
 *
 */
/**
 * @author darrraqi
 *
 */
public class ZBOCreateAdPageTest extends PageTest{

	private JSONObject dataObject;
	private ZBOCreateAdPage page;
	private WebDriver driver;
	private String listing_Address = "";
	private String lAd_budget = "";
	private String lDefaultCity = "";
	private String bl_ad_budget = "";
	private String bl_quick_ad_budget;
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreateAdPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOCreateAdPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl, boolean pSetupForcefully) {
		if(page==null && !pSetupForcefully) {
			driver = getDriver();
			page = new ZBOCreateAdPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}else if(page!=null && pSetupForcefully){
			driver = getDriver();
			page = new ZBOCreateAdPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
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
	public void testVerifyCustomAdSectionIsDisplayed()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCreateAdPage(), "Create Ad Page is not visible..");
		assertTrue(page.isCreateAdStep1Visible(), "Create Ad Page Step 1 is not visible..");
		assertTrue(page.isCustomAdsHeadingDisplayed(), "Create Custom Ads heading is not displayed..");
	}
	@Test
	public void testVerifyPromoteListingHeadingIsDisplayed()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isPromoteListingHeadingIsVisible(), "Promote Listing heading is not displayed..");
	}
	@Test
	public void testVerifyCorrectTextIsDisplayed()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCorrectTextDisplayed(), " 'Get more exposure, interest and engagement for your listing' is not displayed..");
	}
	@Test
	public void testVerifyHomeIconIsDisplayed()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isHomeIconDisplayedInCreateAdBox(), "Home Icon inside Create Custom Ad box is not displayed..");
	}
	@Test
	public void testVerifyCustomAdButtonIsDisplayed()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCreateCustomAdButtonIsDisplayed(), "Create Custom Ad button is not displayed..");
	}
	@Test
	public void testVerifyCreateAdBoxIsBouncing()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCreateCustomBoxBouncing(), "Create Custom Ad box is not bouncing on mouse hover..");
	}
	@Test
	public void testSelectQuickAdsHeadingDisplayed()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isSelectQuickAdsHeadingDisplayed(), "Select a Quick Ad heading is not visible..");
	}
	@Test
	public void testListingQuickAdsHeadingDisplayed()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isListingQuickAdsHeadingDisplayed(), "Listing Quick Ad heading is not visible..");
	}
	@Test
	public void testListingAddressIsDisplayedInQuickAdBox()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isListingAddressIsDisplayedInQuickAdBox(), "Listing Address is not visible in Quick Ad box..");
	}
	@Test
	public void testHotPropertyHeadingVisible()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isHotPropertyHeadingVisible(), "Hot Properties heading is not visible in Quick Ad box..");
	}
	@Test
	public void testQuickAdThumbnailVisible()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isQuickAdThumbnailVisible(), "Listing Thumbnail is not visible in Quick Ad box..");
	}
	@Test
	public void testQuickAdsDescriptionDisplayed()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isQuickAdsDescriptionDisplayed(), "Listing description is not visible in Quick Ad box..");
	}
	@Test
	public void testQuickAdDomainDomainIsCorrect()  {
		getPage("/create-ad/step-one",true);
		String l_domain = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url").split("www.")[1].trim();
		assertTrue(page.getQuickAdDomain().contains(l_domain), "Correct domain is not visible in Quick Ad box..");
	}
	@Test
	public void testQuickAdTitleVisible()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isQuickAdTitleVisible(), "Quick Ad title 'Hot Proerty' is not visible..");
	}
	@Test
	public void testQuickAdSelectButtonVisible()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isQuickAdSelectButtonVisible(), "Select Button is not visible in Quick Ad box..");
	}
	@Test
	public void testQuickAdSlideShowIsWorking()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.verifyAdSlideShowIsWorking(), "Slide show is not working for Quick Ads..");
	}
	@Test
	public void testVerifyListingPopUpAppearsOnClickingCreateCustomButton()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.clickOnCustomAdButton(), "Unable to click on Create Custom Ad Button..");
		assertTrue(page.getSelectListingAlert().isSelectListingAlert(), "Listing Alert is not visible..");
	}
	@Test
	public void testVerifyCorrectTextIsDisplayedOnSelectListingPopUp()  {
		getPage("/create-ad/step-one",true);
		assertTrue(page.getSelectListingAlert().isSelectListingAlert(), "Listing Alert is not visible..");
		assertTrue(page.getSelectListingAlert().verifyText("Please Select the listing you"), "Crrect text is not displayed on listing pop up");
	}

	@Test
	public void testCancelButtonClosesThePopup()  {
		getPage("/create-ad/step-one",true);
		if(page.clickOnCustomAdButton()) {
			AutomationLogger.info("Select Listing Pop Up is already opened..");
		}
		assertTrue(page.getSelectListingAlert().clickOnCancelButton(), "Unable to click on cancel button..");
		ActionHelper.staticWait(10);
		assertFalse(page.getSelectListingAlert().isSelectListingAlert(), "Select Listing Alert is not closed after clicking on Cancel button");
	}

	@Test
	public void testVerifyUserLandsToStep2AfterClickingOk()  {
		getPage("/create-ad/step-one",true);
		if(!page.clickOnCustomAdButton()) {
			throw new SkipException("Skipping the test becasuse [Click on Custom Ad Button] pre-condition was failed.");
		}
		if(!page.getSelectListingAlert().isSelectListingAlert()) {
			throw new SkipException("Skipping the test becasuse [Select Listing Alert dialog was not opened] pre-condition was failed.");
		}
		assertTrue(page.getSelectListingAlert().clickOnOkButton(), "Unable to click on OK button..");
		assertTrue(page.isCreateAdStep2Visible(), "Create Ad - Step 2 heading is not visible..");
	}

	@Test
	public void testVerifyListingAddressIsSameOnStep1AndStep2()  {
		getPage("/create-ad/step-one",true);
		if(!page.clickOnCustomAdButton()) {
			throw new SkipException("Skipping the test becasuse [Click on Custom Ad Button] pre-condition was failed.");
		}
		if(!page.getSelectListingAlert().isSelectListingAlert()) {
			throw new SkipException("Skipping the test becasuse [Select Listing Alert dialog was not opened] pre-condition was failed.");
		}
		String lListing_address = page.getSelectListingAlert().getTheListingAddress();
		assertTrue(page.getSelectListingAlert().clickOnOkButton(), "Unable to click on OK button..");
		assertTrue(page.isCreateAdStep2Visible(), "Create Ad - Step 2 heading is not visible..");
		assertTrue(page.getListingAddress().contains(lListing_address.split(",")[0]), "Listing address on step2 section 1 is not same as step 1");
	}

	@Test
	public void testVerifyEditButtonRedirectsUserOnStep1()  {
		getPage("/create-ad/step-one",true);
		if(!page.clickOnCustomAdButton()) {
			throw new SkipException("Skipping the test becasuse [Click on Custom Ad Button] pre-condition was failed.");
		}
		if(!page.getSelectListingAlert().isSelectListingAlert() || !page.getSelectListingAlert().clickOnOkButton()) {
			throw new SkipException("Skipping the test becasuse [Select Listing Alert dialog was not opened] pre-condition was failed.");
		}
		assertTrue(page.isCreateAdStep2Visible(), "Create Ad - Step 2 heading is not visible..");
		assertTrue(page.clickOnEditButtonStep2Section1(), "Unable to click on Edit button..");
		assertTrue(page.isCustomAdsHeadingDisplayed(), "Create Custom Ads heading is not displayed..");		
	}
	@Test
	public void testSection1IsCheckedOnStep2()  {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		assertTrue(page.isCreateAdStep2Visible(), "Create Ad - Step 2 heading is not visible..");
		assertTrue(page.isStep1Checked(), "Section 1 is not checked on Step 2..");		
	}
	@Test
	public void testSection1AdHeadingIsVisible()  {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		assertTrue(page.isCreateAdStep2Visible(), "Create Ad - Step 2 heading is not visible..");
		assertTrue(page.isStep1Checked(), "Section 1 is not checked on Step 2..");		
	}

	//This test will verify the head line and description on section 2 step 2 is pre populated
	@Test
	public void testVerifyHeadlineDescriptionIsPrepopulated() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		assertTrue(!page.isAdHeadingPopulated().isEmpty(), "Ad Heading is not populated..");
		assertTrue(!page.isAdDescPopulated().isEmpty(), "Ad Heading is not populated..");
	}

	//Verifies Customize Ad Details heading is visible
	@Test
	public void testVerifySection2StepHeadingIsVisible() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		assertTrue(page.isCustomizeAdDetailsHeadingIsVisible(), "Step2: Customize Ad Details heading is not visible..");
		assertTrue(page.isStep2Section2HeadingVisible(), "Step2: heading is not visible..");
	}
	//Verifies the heading is under 40 characters
	@Test
	public void testVerifyHeadingDoesntExceedCharacters() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		String lAdHeading = page.isAdHeadingPopulated();
		if(!page.typeAdHeading(lAdHeading)) {
			throw new SkipException("Skipping the test becasuse [Type in Heading] pre-condition was failed.");
		}
		if(!page.clickOnSelectButton()) {
			throw new SkipException("Skipping the test becasuse [Click Select] pre-condition was failed.");
		}
		ActionHelper.staticWait(5);
		if(!page.clickOnEditButtonStep2()) {
			throw new SkipException("Skipping the test becasuse [Edit button] pre-condition was failed.");
		}
		assertTrue(page.isAdHeadingPopulated().length()==40, "Ad heading exceeds 40 characters length");
	}

	//Verifies the heading is under 125 characters
	@Test
	public void testVerifyDescriptionDoesntExceedCharacters() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		String lAdDescription = page.isAdDescPopulated();
		if(!page.typeAdDescription(lAdDescription)) {
			throw new SkipException("Skipping the test becasuse [Type in Desc] pre-condition was failed.");
		}
		if(!page.clickOnSelectButton()) {
			throw new SkipException("Skipping the test becasuse [Click Select] pre-condition was failed.");
		}
		ActionHelper.staticWait(5);
		if(!page.clickOnEditButtonStep2()) {
			throw new SkipException("Skipping the test becasuse [Edit button] pre-condition was failed.");
		}
		assertTrue(page.isAdDescPopulated().length()==125, "Ad heading exceeds 40 characters length");
	}

	@Test
	public void testVerifyAdTitleDescIsUpdatedInPreview() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		String lAdDescription = updateName(" ");
		if(!page.typeAdDescription(lAdDescription)) {
			throw new SkipException("Skipping the test becasuse [Type in Desc] pre-condition was failed.");
		}
		String lAdTitle = updateName("Hot Property ");
		if(!page.clearAndTypeAdHeading(lAdTitle)) {
			throw new SkipException("Skipping the test becasuse [Type in Desc] pre-condition was failed.");
		}
		assertTrue(page.verifyFBAdPreviewDescIsUpdated(lAdDescription), "The ad description is not updated in the preview for Facebook");
		assertTrue(page.verifyInstaAdPreviewDescIsUpdated(lAdDescription), "The ad description is not updated in the preview for Instagram");
		assertTrue(page.verifyFBAdPreviewTitleIsUpdated(lAdTitle), "Unable to verify ad preview title..");
	}
	
	@Test
	public void testVerifyBottomProgressBarStep1Of4IsChecked() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		assertTrue(page.isStep14BottomProgressBarIsChecked(), "Step 1 of 4 bottom progress bar is not checked..");
	}
	
	@Test
	public void testVerifyFacebookInstLogoAreDisplayedOnStep2() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		assertTrue(page.verifyInstaAndFacebookLogoAreDisplayed(), "Facebook and Instagram logo are not displayed..");
	}
	
	@Test
	public void testVerifySliderISWorkingInAdsPreview() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		assertTrue(page.verifyInstaPreviewSlideShowIsWorkingOnStep2(), "Instagram slide show is not working in ads preview..");
		assertTrue(page.verifyFacebookPreviewSlideShowIsWorkingOnStep2(), "Facebbok slide show is not working in ads preview..");
	}
	
	@Test
	public void testVerifyDomainFromAdsPreviewStep2() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		String l_domain = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url");
		assertTrue(page.getQuickAdDomain().contains(l_domain), "Correct domain is not displayed under Facebook ads preview..");		
	}
	
	@Test
	public void testVerifySelectSiteDropDown() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		String l_domain = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url");
		if(!getIsProd()) {
		assertTrue(page.isSelectDestinationLabelVisible(), "Select destination site label is not visible..");	
		assertTrue(page.selectSite(l_domain), "Unable to select site from dropdown");
		}else {
			assertFalse(page.isSelectDestinationLabelVisible(), "Select destination site label is not visible..");
		}
	}
	
	@Test
	public void testVerifyDataWhenSelectIsClickedOnStep2() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		String l_domain = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url");
		String lAdHeading = page.isAdHeadingPopulated();
		String lAdDescription = page.isAdDescPopulated();
		lAdDescription = lAdDescription.replace("<br/>", "");
		clickOnSelectButton();
		assertTrue(page.verifyStep3Heading("Choose Audience & Reach"), "Unable to verify step3 heading..");
		assertTrue(page.verifyAdHeadline(lAdHeading), "Unable to verify ad head line..");
		assertTrue(page.verifyAdDesc(lAdDescription), "Unable to verify ad description..");
		assertTrue(page.verifyUrlOnStep2Section2AfterClickingSelect(l_domain), "WebSite domain is not displayed..");
	}	
	@Test
	public void testStep2AndProgressBarIsChecked() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		assertTrue(page.isStep24BottomProgressBarIsChecked(), "Bottom progress bar is not checked for step 2 pf 4");
		assertTrue(page.isStepCheckBoxIsChecked("Step 2:"), "Step 2 check box is not checked..");
	}
	@Test
	public void testCityIsPrePopulatedOnStep3() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		assertTrue(page.isDefaultCitySelected(), "Default City is not selected on step 3");
	}
	@Test
	public void testSelectMultipleCities() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		assertTrue(page.typeAndSelectCity("San Diego, CA"), "Unable to select multiple cities on step 3");
		assertTrue(page.clickOnNextStepButton(), "Unable to click on next step button..");
		assertTrue(page.verifyAdCitySection3("San Diego"), "City is not correct on step 3..");
	}
	@Test
	public void testVerifyErrorIsTriggeredWhenNoCityIsSelected() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		assertTrue(page.clickOnRemoveCityButton(), "Unable to remove city on Step 3");
		assertTrue(page.clickOnNextStepButton(), "Unable to click on next step button..");
		assertTrue(page.getAlert().getErrorMessage().equalsIgnoreCase("You must enter at least one city for the ad to target."), "Correct error message is not displayed..");
		assertTrue(page.getAlert().clickOnCloseButton(), "Unable to click on Close button..");
	}
	@Test
	public void testIsMediumReachSelectedByDefault() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		assertTrue(page.isMediumReachSelectedByDefault(), "Medium reach is not selected by default on step 3");
		assertTrue(page.selectPlan("$120"), "Unable to click on select plan button");
	}
	
	@Test
	public void testVerifyAllThePlansAreDisplayed() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		assertTrue(page.allThePlansAreDisplayed(), "All the plan buttons are not displayed");
	}

	@Test
	public void testVerifyCorrectDataIsDisplayedWhenNextStepIsClicked() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		ActionHelper.staticWait(5);
		String l_city = page.getDefaultCity().split(",")[0];
		selectTestPlanPreCond();
		clickOnNextStepPreCond();
		assertTrue(page.verifyAdCitySection3(l_city), "City is not correct on step 3..");
		assertTrue(page.verifyAdReachSection3("$120"), "Unable to click on select plan button");
		assertTrue(page.isSection3EditButtonEnabled(),"Edit button is not enabled for step 3..");
	}
	
	@Test
	public void testVerifyUserCanEditStep2FromStep3() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		assertTrue(page.clickOnEditButtonStep2(), "Unable to click on Step 2 button..");
		//Confirm URL is changed to step 2
		assertTrue(driver.getCurrentUrl().contains("/create-ad/step-two"), "URL did not change to step 2..");
		assertFalse(page.verifyStep3Heading("Choose Audience & Reach"), "Step 3 heading is still displayed..");
	}
	
	@Test
	public void testVerifyUserCanEditStep1FromStep3() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		assertTrue(page.clickOnStep1EditButton(), "Unable to click on Step 2 button..");
		//Confirm URL is changed to step 2
		assertTrue(driver.getCurrentUrl().contains("/create-ad/step-one"), "URL did not change to step 2..");
		assertTrue(page.isCustomAdsHeadingDisplayed(), "Custom heading button is not displayed..");
	}
	
	@Test
	public void testVerifyUserLandsToStep4() {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		ActionHelper.staticWait(5);
		selectTestPlanPreCond();
		clickOnNextStepPreCond();
		assertTrue(page.isStep3of4IsEnabled(), "Step 3 of 4 is enabled..");
		assertTrue(page.isStepCheckBoxIsChecked("3"), "Step 3 checkbox is not checked..");
		assertTrue(page.verifyStep4IsVisible(), "Step 4 is not visible..");
		assertTrue(page.isStep24BottomProgressBarIsChecked(), "Bottom progress bar is not checked for step 2 pf 4");
		assertTrue(page.isStepCheckBoxIsChecked("Step 2:"), "Step 2 check box is not checked..");
	}
	
	@Test
	public void testAdDuration() throws ParseException  {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		ActionHelper.staticWait(2);
		selectTestPlanPreCond();
		clickOnNextStepPreCond();
		assertTrue(page.verifyStartEndAndRenewalDate(), "Step 3 of 4 is enabled..");
	}
	
	@Test
	public void testVerifyTermsAndConditionDisplayed()  {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		ActionHelper.staticWait(2);
		selectTestPlanPreCond();
		clickOnNextStepPreCond();
		assertFalse(page.verifyIsTermsAndConditionCheckBoxChecked(), "Terms and Condition checkbox is not checked by default..");
		assertTrue(page.clickOnTermsAndCond(), "Unable to click on terms and condition checkbox..");
	}
	
	@Test
	public void testVerifyTermsAndConditionLinkWorking()  {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		ActionHelper.staticWait(2);
		selectTestPlanPreCond();
		clickOnNextStepPreCond();
		assertTrue(page.clickOnTermsAndCondLink(), "Terms and Condition Link is not working..");
	}
	
	@Test
	public void testVerifyUserCanEditAllStepsFromStep4()  {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		ActionHelper.staticWait(2);
		selectTestPlanPreCond();
		clickOnNextStepPreCond();
		assertTrue(page.clickOnStep3EditButton(), "Unable to click on Step 3 button..");
		//Confirm URL is changed to step 3
		assertTrue(driver.getCurrentUrl().contains("/create-ad/step-three"), "URL did not change to step 2..");
		assertFalse(page.isSection4Visible(), "Step 4 heading is still displayed..");
		//Step 2
		assertTrue(page.clickOnEditButtonStep2(), "Unable to click on Step 2 button..");
		//Confirm URL is changed to step 2
		assertTrue(driver.getCurrentUrl().contains("/create-ad/step-two"), "URL did not change to step 2..");
		assertFalse(page.verifyStep3Heading("Choose Audience & Reach"), "Step 3 heading is still displayed..");
		//Step1
		assertTrue(page.clickOnStep1EditButton(), "Unable to click on Step 2 button..");
		//Confirm URL is changed to step 1
		assertTrue(driver.getCurrentUrl().contains("/create-ad/step-one"), "URL did not change to step 2..");
		assertTrue(page.isCustomAdsHeadingDisplayed(), "Custom heading button is not displayed..");
	}
	
	@Test
	public void testVerifyTestingAdChecboxIsChecked()  {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		ActionHelper.staticWait(2);
		selectTestPlanPreCond();
		clickOnNextStepPreCond();
		assertTrue(page.isTestingAdAlreadyChecked(), "Testing ad checkbox is not checked by default..");
		assertTrue(page.clickOnTestingAdCheckBox(), "Unable to check testing ad checkbox..");
	}
	
	//Quick ads test cases
	@Test
	public void testVerifyUserLandsToStep3WhenQuickAdIsSelected(){
		getPage("/create-ad/step-one",true);
		String l_domain = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url");
		assertTrue(page.clickOnQuickAdsSelectButton(), "Unable to click on select button");
		assertTrue(page.verifyStep3Heading("Choose Audience & Reach"), "Unable to verify step3 heading..");
		assertTrue(!page.getListingAddress().isEmpty(), "Listing address on step2 section 1 is not same as step 1");
		assertTrue(!page.getAdHeadlineStep2().isEmpty(), "Ad Heading is not populated..");
		assertTrue(!page.getAdDescStep2().isEmpty(), "Ad Heading is not populated..");
		assertTrue(page.verifyUrlOnStep2Section2AfterClickingSelect(l_domain), "WebSite domain is not displayed..");
		assertTrue(page.isStep1Checked(), "Step1 is not checked on Step 2..");	
		assertTrue(page.isStepCheckBoxIsChecked("2"), "Step 2 checkbox is not checked..");
	}
	
	@Test
	public void testVerifyAdUrlChangesToStep3WhenQuickAdIsSelected(){
		getPage("/create-ad/step-one",true);
		clickOnQuickAdsSelectAButton();
		assertTrue(driver.getCurrentUrl().contains("step-three?listing="), "AD URL is not changed to Step 3..");
	}
	
	@Test
	public void testVerifySlideShowWorkingOnStep3WhenQuickAdIsSelected()  {
		getPage("/create-ad/step-one",true);
		clickOnQuickAdsSelectAButton();
		assertTrue(page.verifyInstaPreviewSlideShowIsWorkingOnStep2(), "Instagram slide show is not working in ads preview..");
		assertTrue(page.verifyFacebookPreviewSlideShowIsWorkingOnStep2(), "Facebbok slide show is not working in ads preview..");
	}
	
	@Test
	public void testCreateAndVerifyQuickAdsListingAddress() {
		getPage("/create-ad/step-one",true);
		clickOnQuickAdsSelectAButton();
		listing_Address = page.getListingAddressValue();
		lDefaultCity = page.getDefaultCity();
		lAd_budget = page.isMediumReachSelectedByDefault()?"$240":"";
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleAdId, driver.getCurrentUrl());
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleQLADefaultCity, lDefaultCity);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleQLABudget, lAd_budget);
		clickOnNextStepPreCond();
		clickOnTermsAndConditionCheckbox();
		clickOnPausedAdCheckbox();
		clickOnPlaceAdAButton();
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.getListingAddressFirstRow().contains(listing_Address), "Unable to verify listing address on ads overview page.."+"["+listing_Address+"]");	
	}
	
	@Test
	public void testCreateAndVerifyMultipleCitiesQuickAd()  {
		getPage("/create-ad/step-one",true);
		String lAddedCity = "San Diego, CA";
		clickOnQuickAdsSelectAButton();
		listing_Address = page.getListingAddressValue();
		lDefaultCity = page.getDefaultCity();
		lAd_budget = page.isMediumReachSelectedByDefault()?"$240":"";
		assertTrue(page.typeAndSelectCity(lAddedCity), "Unable to select multiple cities on step 3");
		clickOnNextStepPreCond();
		ActionHelper.staticWait(3);
		clickOnTermsAndConditionCheckbox();
		clickOnPausedAdCheckbox();
		clickOnPlaceAdAButton();
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.getAdLocation().contains(lDefaultCity.split(",")[0].trim()), "Unable to verify ad location on ads overview page.."+"["+lDefaultCity.split(",")[0]+"]");	
		assertTrue(adsOverviewPage.getAdLocation().contains(lAddedCity.split(",")[0].trim()), "Unable to verify ad location on ads overview page.."+"["+lAddedCity.split(",")[0]+"]");	
	}
	
	@Test
	public void testCreateAndVerifyQuickAdsCity() {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(lDefaultCity.contains(adsOverviewPage.getAdLocation()), "Unable to verify ad location on ads overview page.."+"["+lDefaultCity+"]");	
	}
	
	@Test
	public void testCreateAndVerifyQuickAdBudget(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyAdPriceIsDisplayed(lAd_budget), "Unable to verify ad budget on ads overview page.."+"["+lAd_budget+"]");	
	}
	
	@Test
	public void testCreateAndVerifyQuickAdDuration(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyStartingEndingDate(), "Unable to verify starting and ending date of the ad");	
	}
	
	@Test
	public void testCreateAndVerifyQuickAdRenewalDate(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyRenewalDate(), "Unable to verify renewal date of quick ad");	
	}
	@Test
	public void testCreateAndVerifyQuickAdStatus()  {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertEquals(adsOverviewPage.getAdStatus(),"Paused", "Unable to verify AD Status of quick ad");	
	}
	
	@Test
	public void testCreateAndVerifyCustomAdsListingAddress(){
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		listing_Address = page.getListingAddressValue();
		lDefaultCity = page.getDefaultCity();
		lAd_budget = page.selectPlan("$120")?"$120":"$240";
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleAdId, driver.getCurrentUrl());
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleQLADefaultCity, lDefaultCity);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleQLABudget, lAd_budget);
		clickOnNextStepPreCond();
		clickOnTermsAndConditionCheckbox();
		clickOnPausedAdCheckbox();
		clickOnPlaceAdAButton();
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.getListingAddressFirstRow().contains(listing_Address), "Unable to verify listing address on ads overview page.."+"["+listing_Address+"]");	
	}
	
	@Test
	public void testCreateAndVerifyMultipleCitiesCustomAd(){
		getPage("/create-ad/step-one",true);
		String lAddedCity = "San Diego, CA";
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		listing_Address = page.getListingAddressValue();
		lDefaultCity = page.getDefaultCity();
		lAd_budget = page.isMediumReachSelectedByDefault()?"$240":"";
		assertTrue(page.typeAndSelectCity(lAddedCity), "Unable to select multiple cities on step 3");
		clickOnNextStepPreCond();
		clickOnTermsAndConditionCheckbox();
		clickOnPausedAdCheckbox();
		clickOnPlaceAdAButton();
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.getAdLocation().contains(lDefaultCity.split(",")[0]), "Unable to verify ad location on ads overview page.."+"["+lDefaultCity+"]");	
		assertTrue(adsOverviewPage.getAdLocation().contains(lAddedCity.split(",")[0]), "Unable to verify ad location on ads overview page.."+"["+lDefaultCity+"]");	
	}
	
	@Test
	public void testVerifyPlaceAdButtonGetsDisabledAfterClick()  {
		getPage("/create-ad/step-one",true);
		clickOnQuickAdsSelectAButton();
		clickOnNextStepPreCond();
		clickOnTermsAndConditionCheckbox();
		clickOnPausedAdCheckbox();
		assertTrue(page.clickOnPlaceAdButtonAndVerifyButtonGetsDisabled(), "Place ad button is not disabled after first click");	
	}
	
	@Test
	public void testCreateAndVerifyCustomAdsCity() {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(lDefaultCity.contains(adsOverviewPage.getAdLocation()), "Unable to verify ad location on ads overview page.."+"["+lDefaultCity+"]");	
	}
	
	@Test
	public void testCreateAndVerifyCustomAdBudget(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyAdPriceIsDisplayed(lAd_budget), "Unable to verify ad budget on ads overview page.."+"["+lAd_budget+"]");	
	}
	
	@Test
	public void testCreateAndVerifyCustomAdDuration(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyStartingEndingDate(), "Unable to verify starting and ending date of the ad");	
	}
	
	@Test
	public void testCreateAndVerifyCustomAdRenewalDate(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyRenewalDate(), "Unable to verify renewal date of quick ad");	
	}
	@Test
	public void testCreateAndVerifyCustomAdStatus()  {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertEquals(adsOverviewPage.getAdStatus(),"Paused", "Unable to verify AD Status of quick ad");	
	}
	@Test
	public void testVerifyQuickAdStartDateIsCorrect()  {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyStartDate(), "Start date is not correct...");	
	}
	@Test
	public void testVerifyUserIsRedirectedToAdsOverviewPage()  {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.isAdsOverviewPage(), "Ads overview page is not opened after clicking on Place Ad button...");	
		assertTrue(driver.getCurrentUrl().contains("/ads/overview"), "URL is not changed");
	}
	
	@Test //40446
	public void testVerifyQuickAndCustomBuyerAdsAreDisplayed() {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isBuyerLeadAdBoxVisible(), "Buyer custom ads box is not visible");
		assertTrue(page.isQuickBuyerAdsDisplayed(), "Buyer quick ads box is not visible");
	}
	
	@Test //40447
	public void testVerifyCorrectTextForQuickAndCustomBuyerAdsIsDisplayed() {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isBuyerLeadCustomAdTextVisible(), "Buyer custom ads box text is not visible");
		
		assertTrue(page.verifyBuyerLeadsAdHeadingCount(), "Buyer quick ads count is not 4");
		assertTrue(page.verifyBuyerLeadQuickAdsDesc("Are you having trouble"), "Quick Ad 2 desc is not correct");
		assertTrue(page.verifyBuyerLeadQuickAdsDesc("Interested in available"), "Quick Ad 3 desc is not correct");
		assertTrue(page.verifyBuyerLeadQuickAdsDesc("Get access to the hottest properties"), "Quick Ad 1 desc is not correct");
		assertTrue(page.verifySelectButton(), "Buyer quick ads Select button count is not 4");
	}
	@Test //40448
	public void testVerifyCustomBuyerAdsBoxBounceOnMouseHover() {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isBuyerLeadCreateCustomBoxBouncing(), "Buyer Lead Custom Box does'nt bounce on mouse hover");
	}
	@Test //40449
	public void testVerifyQuickBuyerAdsSlideShowIsWorking() {
		getPage("/create-ad/step-one",true);
		assertTrue(page.verifyBuyerLeadQuickAdSlideShowIsWorking(), "Slide show is not working for Buyer lead quick ads");
	}
	@Test //40450
	public void testVerifyCustomAndSelectButtonAreVisible() {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCustomAdButtonVisible(), "Buyer quick ads custom ad button is not visible");
		assertTrue(page.verifySelectButtonIsVisible(), "Buyer quick ads Select button is not visible");
	}
	
	@Test //40467
	public void testVerifyUserIsRedirectedToStep2OnClickingCreateCustomButtonBuyerLeadsAd() {
		getPage("/create-ad/step-one",true);
		assertTrue(page.clickCustomAdButtonBuyerLeadAd(), "Unable to click on Create Custom Ad Button for Buyer Leads Ad..");
		assertTrue(page.isCreateAdStep2Visible(), "Create Ad - Step 2 heading is not visible..");
	}
	
	@Test //40469
	public void testVerifySlideShowIsWorkingStep2OfBuyerLeadsAd() {
		assertTrue(page.verifyInstaPreviewSlideShowIsWorkingOnStep2(), "Instagram slide show is not working..");
		assertTrue(page.verifyFacebookPreviewSlideShowIsWorkingOnStep2(), "Facebook slide show is not working");
	}	
	@Test //40470
	public void testVerifyUserCanSeeAdPreviewOnStep2OfBuyerLeadsAd() {
		assertTrue(page.verifyFbAdPreviewDetails(), "Facebook ad preview details are not displayed");
		assertTrue(page.verifyInstagramAdPreviewDetails(), "Instagram ad preview details are not displayed");
	}
	@Test //40474
	public void testVerifySection1IsCheckedOnStep2BuyerLeadsAd() {
		assertTrue(page.isSection1Checked(), "Section 1 checkbox is not checked on Step 2");
	}	
	@Test //40475
	public void testVerifySelectAdTypeAndBuyerLeadsAdHeadingIsDisplayedOnStep2BuyerLeadsAd() {
		assertTrue(page.isStep2Section1SelectAnAdHeadingVisible(), "Select an Ad heading is not displayed");
		assertTrue(page.isBuyerLeadHeadingVisible(), "Buyer Lead ad heading is not visible");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleAdId, driver.getCurrentUrl());
	}
	@Test //40476
	public void testVerifyHeadlineDescriptionIsPrepopulatedForCustomBuyerLeadAds() {
		assertTrue(!page.isAdHeadingPopulated().isEmpty(), "Ad Heading is not populated for Buyer Leads Ad..");
		assertTrue(!page.isAdDescPopulated().isEmpty(), "Ad Description is not populated for Buyer Leads Ad..");
	}
	@Test //40477
	public void testVerifySection2StepHeadingIsVisibleForCustomBuyerLeadAds() {
		assertTrue(page.isCustomizeAdDetailsHeadingIsVisible(), "Step2: Customize Ad Details heading is not visible..");
		assertTrue(page.isStep2Section2HeadingVisible(), "Step2: heading is not visible..");
	}
	
	@Test //40505
	public void testVerifyDescriptionDoesntExceedCharactersForBuyerLeadsCustomAds() {
		String lAdDescription = page.isAdDescPopulated();
		if(!page.typeAdDescription(lAdDescription)) {
			throw new SkipException("Skipping the test becasuse [Type in Desc] pre-condition was failed.");
		}
//		assertTrue(page.isAdDescPopulated().length()==125, "Ad heading exceeds 40 characters length");
		assertTrue(page.getDescriptionLeftCount().equalsIgnoreCase("0"), "Remaining character count didn't change");
	}
	@Test //40506
	public void testVerifyHeadingDoesntExceedCharactersForBuyerLeadsCustomAds() {
	
		String lAdHeading = page.isAdHeadingPopulated();
		if(!page.typeAdHeading(lAdHeading)) {
			throw new SkipException("Skipping the test becasuse [Type in Heading] pre-condition was failed.");
		}
//		assertTrue(page.isAdHeadingPopulated().length()==40, "Ad heading exceeds 40 characters length");
		assertTrue(page.geHeadlineCharacterLeftCount().equalsIgnoreCase("0"), "Remaining character count didn't change");
	}
	
	@Test //40508
	public void testVerifyDomainFromAdsPreviewStep2ForBuyerLeadAds() {
		String l_domain = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url");
		assertTrue(page.getQuickAdDomain().contains(l_domain), "Correct domain is not displayed under Facebook ads preview..");		
	}
	@Test //40509
	public void testVerifyFacebookAndInstaLogoAreDisplayedOnStep2ForBuyerLeadAds() {
		assertTrue(page.verifyInstaAndFacebookLogoAreDisplayed(), "Correct domain is not displayed under Facebook ads preview..");		
	}
	
	@Test //40510
	public void testVerifyDataWhenSelectIsClickedOnStep2ForBuyerLeadsAd() {
		String l_domain = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url");
		String lAdHeading = page.isAdHeadingPopulated();
		String lAdDescription = page.isAdDescPopulated();
		lAdDescription = lAdDescription.replace("<br/>", "");
		clickOnSelectButton();
		assertTrue(page.verifyStep3Heading("Choose Audience & Reach"), "Unable to verify step3 heading..");
		assertTrue(page.verifyAdHeadline(lAdHeading), "Unable to verify ad head line..");
		assertTrue(page.verifyAdDesc(lAdDescription), "Unable to verify ad description..");
		assertTrue(page.verifyUrlOnStep2Section2AfterClickingSelect(l_domain), "WebSite domain is not displayed..");
	}
	
	@Test //40511
	public void testIsMediumReachSelectedByDefaultForBuyerLeadsCutomAd() {
		assertTrue(page.isMediumReachSelectedByDefault(), "Medium reach is not selected by default on step 3");
		bl_ad_budget = "$160";
		assertTrue(page.selectPlan(bl_ad_budget), "Unable to click on select plan button");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleQLABudget, bl_ad_budget);
	}

	@Test //40512
	public void testVerifyAllThePlansAreDisplayedForByerLeadsAd() {
		assertTrue(page.allThePlansAreDisplayed(), "All the plan buttons are not displayed");
	}
	
	@Test //40514
	public void testCityIsPrePopulatedOnStep3BuyerLeadAds() {
		assertTrue(page.isDefaultCitySelected(), "Default City is not selected on step 3");
	}
	@Test //40513
	public void testVerifyErrorIsTriggeredWhenNoCityIsSelectedForBuyerLeadsAd() {
		assertTrue(page.clickOnRemoveCityButton(), "Unable to remove city on Step 3");
		assertTrue(page.clickOnNextStepButton(), "Unable to click on next step button..");
		assertTrue(page.getAlert().getErrorMessage().equalsIgnoreCase("You must enter at least one city for the ad to target."), "Correct error message is not displayed..");
		assertTrue(page.getAlert().clickOnCloseButton(), "Unable to click on Close button..");
		ActionHelper.staticWait(5);
	}
	
	@Test //40543
	public void testSelectCityForBuyerLeadsCustomAds() {
		assertTrue(page.typeAndSelectCity("San Diego, CA"), "Unable to select multiple cities on step 3");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleQLADefaultCity, "San Diego, CA");
	}
	@Test //40535
	public void testVerifyCorrectDataIsDisplayedWhenNextButtonIsClickedStep3BuyerLeadAds() {
		String l_city = page.getDefaultCity().split(",")[0];
		clickOnNextStepPreCond();
		assertTrue(page.verifyAdCitySection3(l_city), "City is not correct on step 3..");
		assertTrue(page.verifyAdReachSection3("$160"), "Unable to click on select plan button");
		assertTrue(page.isSection3EditButtonEnabled(),"Edit button is not enabled for step 3..");
	}
	
	@Test //40538
	public void testVerifyUserLandsToStep4CustomBuyerLeadsAd() {
		assertTrue(page.isStep3of4IsEnabled(), "Step 3 of 4 is enabled..");
		assertTrue(page.isStepCheckBoxIsChecked("3"), "Step 3 checkbox is not checked..");
		assertTrue(page.verifyStep4IsVisible(), "Step 4 is not visible..");
		assertTrue(page.isStep24BottomProgressBarIsChecked(), "Bottom progress bar is not checked for step 2 pf 4");
		assertTrue(page.isStepCheckBoxIsChecked("Step 2:"), "Step 2 check box is not checked..");
	}
	
	@Test //40539
	public void testVerifyAdDurationStep4BuyerLeadAds() throws ParseException  {
		assertTrue(page.verifyStartEndAndRenewalDate(), "Unable to verify ad duration and renewal date.");
	}
	
	@Test //40540
	public void testVerifyTermsAndConditionDisplayedBuyerLeadsAd()  {
		assertFalse(page.verifyIsTermsAndConditionCheckBoxChecked(), "Terms and Condition checkbox is not checked by default..");
		assertTrue(page.clickOnTermsAndCond(), "Unable to click on terms and condition checkbox..");
	}
	
	@Test //40550
	public void testVerifyTestAdChecboxIsCheckedForBuyerLeadsAd()  {
		assertTrue(page.isTestingAdAlreadyChecked(), "Testing ad checkbox is not checked by default..");
		assertTrue(page.clickOnTestingAdCheckBox(), "Unable to check testing ad checkbox..");
	}
	
	@Test //40551
	public void testVerifyPlaceAdButtonRedirectsUserToAdsOverviewForBuyerLeadsAd()  {
		assertTrue(page.clickOnPlaceAdButton(), "Unable to click on place ad button..");
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.isAdsOverviewPage(), "User is not redirected to ads overview page..");
		assertTrue(driver.getCurrentUrl().contains("/ads/overview"), "URL is not changed");
	}
	
	@Test //40544
	public void testCreateAndVerifyBuyerLeadAdDurationOnAdsOverviewPage(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyStartingEndingDate(), "Unable to verify starting and ending date of the ad");	
	}
	
	@Test //40545
	public void testCreateAndVerifyBuyerLeadAdRenewalDate(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyRenewalDate(), "Unable to verify renewal date of quick ad");	
	}
	
	@Test //40546
	public void testVerifyBuyerLeadsCustomAdStatus()  {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertEquals(adsOverviewPage.getAdStatus(),"Paused", "Unable to verify AD Status of quick ad");	
	}
	
	@Test //40548
	public void testVerifyCityForBuyerLeadsAdOnAdsOverviewPage() {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue("San Diego, CA".contains(adsOverviewPage.getAdLocation()), "Unable to verify ad location on ads overview page.."+"["+lDefaultCity+"]");	
	}
	
	@Test //40549
	public void testVerifyBudgetForBuyerLeadsAdOnAdsOverviewPage(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyAdPriceIsDisplayed(bl_ad_budget), "Unable to verify ad budget on ads overview page.."+"["+lAd_budget+"]");	
	}
	
	@Test //40547
	public void testVerifyBuyerLeadsHeadingAndTypeAdOnAdsOverviewPage(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.getAdType().equalsIgnoreCase("Buyer Lead Ad"), "Buyer Lead heading is not displayed on ads overview page.."+"[Buyer Lead Ad]");	
		assertTrue(adsOverviewPage.getListingAddressFirstRow().equalsIgnoreCase("Custom Ad"), "Buyer Lead heading is not displayed on ads overview page.."+"[Buyer Lead Ad]");	
		assertTrue(adsOverviewPage.verifyBuyerLeadAdIcon(), "Unable to verify buer lead ad icon");	
	}
	
	/**
	 * Buyer leads Quick Ads Tests
	 */
	@Test //40552
	public void testVerifyUserIsRedirectedToStep3OnClickingSelectButtonQuickBuyerLeadsAd() {
		getPage("/create-ad/step-one",true);
		assertTrue(page.clickSelectButtonForQuickAds(), "Unable to click Select button for Quick Ads..");
		assertTrue(page.verifyStep3Heading("Choose Audience & Reach"), "Unable to verify step3 heading..");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleAdId, driver.getCurrentUrl());
	}
	@Test //40553
	public void testVerifySlideShowIsWorkingStep2OfBuyerLeadQuickAds() {
		assertTrue(page.verifyInstaPreviewSlideShowIsWorkingOnStep2(), "Instagram slide show is not working..");
		assertTrue(page.verifyFacebookPreviewSlideShowIsWorkingOnStep2(), "Facebook slide show is not working");
	}
	
	@Test //40554
	public void testVerifyUserCanSeeAdPreviewOnStep3OfBuyerLeadsQuickAd() {
		assertTrue(page.verifyFbAdPreviewDetails(), "Facebook ad preview details are not displayed");
		assertTrue(page.verifyInstagramAdPreviewDetails(), "Instagram ad preview details are not displayed");
	}
	
	@Test //40555
	public void testVerifySection1And2AreCheckedOnStep3BuyerLeadsQuickAd() {
		assertTrue(page.isSection1Checked(), "Section 1 checkbox is not checked on Step 3");
		assertTrue(page.isSection2Checked(), "Section 2 checkbox is not checked on Step 3");
	}	
	@Test //40557
	public void testVerifyDomainFromAdsPreviewOnStep3ForBuyerLeadAds() {
		String l_domain = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url");
		assertTrue(page.getQuickAdDomain().contains(l_domain), "Correct domain is not displayed under Facebook ads preview..");		
	}
	@Test //40558
	public void testVerifyFacebookInstLogoAreDisplayedOnStep3BuyerLeadQuickAd() {
		assertTrue(page.verifyInstaAndFacebookLogoAreDisplayed(), "Facebook and Instagram logo are not displayed..");
	}
	@Test //40559
	public void testVerifyRecapOfStep2FromStep3BuyerLeadQuickAds(){
		String l_domain = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url");
//		assertTrue(page.clickOnQuickAdsSelectButton(), "Unable to click on select button");
		assertTrue(page.verifyStep3Heading("Choose Audience & Reach"), "Unable to verify step3 heading..");
		assertTrue(!page.getListingAddress().isEmpty(), "Listing address on step2 section 1 is not same as step 1");
		assertTrue(!page.getAdHeadlineStep2().isEmpty(), "Ad Heading is not populated..");
		assertTrue(!page.getAdDescStep2().isEmpty(), "Ad Heading is not populated..");
		assertTrue(page.verifyUrlOnStep2Section2AfterClickingSelect(l_domain), "WebSite domain is not displayed..");
	}
	@Test //40560
	public void testIsMediumReachSelectedByDefaultForBuyerLeadsQuickAd() {
		assertTrue(page.isMediumReachSelectedByDefault(), "Medium reach is not selected by default on step 3");
		bl_quick_ad_budget = "$400";
		assertTrue(page.selectPlan(bl_quick_ad_budget), "Unable to click on select plan button");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleQLABudget, bl_quick_ad_budget);
	}
	@Test //40561
	public void testVerifyAllThePlansAreDisplayedForByerLeadsQuikcAd() {
		assertTrue(page.allThePlansAreDisplayed(), "All the plan buttons are not displayed");
	}
	
	@Test //40562
	public void testCityIsPrePopulatedOnStep3BuyerLeadQuickAds() {
		String l_default_city = page.getDefaultCity();
		assertTrue(page.isDefaultCitySelected(), "Default City is not selected on step 3");
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleQLADefaultCity, l_default_city);
	}
	@Test //40563
	public void testVerifyCorrectDataIsDisplayedOnStep3WhenUserLandsOnStep4BuyerLeadQuickAds() {
		String l_city = page.getDefaultCity().split(",")[0];
		clickOnNextStepPreCond();
		assertTrue(page.verifyAdCitySection3(l_city), "City is not correct on step 3..");
		assertTrue(page.verifyAdReachSection3(bl_quick_ad_budget), "Unable to click on select plan button");
		assertTrue(page.isSection3EditButtonEnabled(),"Edit button is not enabled for step 3..");
	}
	
	@Test //40564
	public void testVerifyUserLandsToStep4QuickBuyerLeadsAd() {
		assertTrue(page.isStep3of4IsEnabled(), "Step 3 of 4 is enabled..");
		assertTrue(page.isStepCheckBoxIsChecked("3"), "Step 3 checkbox is not checked..");
		assertTrue(page.verifyStep4IsVisible(), "Step 4 is not visible..");
		assertTrue(page.isStep24BottomProgressBarIsChecked(), "Bottom progress bar is not checked for step 2 pf 4");
		assertTrue(page.isStepCheckBoxIsChecked("Step 2:"), "Step 2 check box is not checked..");
	}
	
	@Test //40565
	public void testVerifyAdDurationStep4BuyerLeadQuickAds() throws ParseException  {
		assertTrue(page.verifyStartEndAndRenewalDate(), "Unable to verify ad duration and renewal date.");
	}
	@Test //40566
	public void testVerifyTermsAndConditionDisplayedBuyerLeadsQuickAd()  {
		assertFalse(page.verifyIsTermsAndConditionCheckBoxChecked(), "Terms and Condition checkbox is not checked by default..");
		assertTrue(page.clickOnTermsAndCond(), "Unable to click on terms and condition checkbox..");
	}
	
	@Test //40573
	public void testVerifyTestAdChecboxIsCheckedForBuyerLeadsQuickAd()  {
		assertTrue(page.isTestingAdAlreadyChecked(), "Testing ad checkbox is not checked by default..");
		assertTrue(page.clickOnTestingAdCheckBox(), "Unable to check testing ad checkbox..");
	}
	
	@Test //40574
	public void testVerifyPlaceAdButtonRedirectsUserToAdsOverviewForBuyerLeadsQuickAd()  {
		assertTrue(page.clickOnPlaceAdButton(), "Unable to click on place ad button..");
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.isAdsOverviewPage(), "User is not redirected to ads overview page..");
		assertTrue(driver.getCurrentUrl().contains("/ads/overview"), "URL is not changed");
	}
	@Test //40567
	public void testCreateAndVerifyQuickAdBuyerLeadAdDurationOnAdsOverviewPage(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyStartingEndingDate(), "Unable to verify starting and ending date of the ad");	
	}
	
	@Test //40568
	public void testCreateAndVerifyBuyerLeadQuickAdRenewalDate(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyRenewalDate(), "Unable to verify renewal date of quick ad");	
	}
	
	@Test //40569
	public void testVerifyBuyerLeadsQuickAdStatus()  {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertEquals(adsOverviewPage.getAdStatus(),"Paused", "Unable to verify AD Status of quick ad");	
	}
	
	@Test //40571
	public void testVerifyCityForBuyerLeadsQuickAdOnAdsOverviewPage() {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue("San Diego, CA".contains(adsOverviewPage.getAdLocation()), "Unable to verify ad location on ads overview page.."+"["+lDefaultCity+"]");	
	}
	
	@Test //40572
	public void testVerifyBudgetForQuickBuyerLeadsAdOnAdsOverviewPage(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.verifyAdPriceIsDisplayed(bl_quick_ad_budget), "Unable to verify ad budget on ads overview page.."+"["+lAd_budget+"]");	
	}
	
	@Test //40570
	public void testVerifyQuickBuyerLeadsHeadingAndTypeAdOnAdsOverviewPage(){
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.getAdType().equalsIgnoreCase("Buyer Lead Ad"), "Buyer Lead heading is not displayed on ads overview page.."+"[Buyer Lead Ad]");	
		assertTrue(adsOverviewPage.getListingAddressFirstRow().contains("Quick Ad"), "Buyer Lead heading is not displayed on ads overview page.."+"[Buyer Lead Ad]");	
		assertTrue(adsOverviewPage.verifyBuyerLeadAdIcon(), "Unable to verify buer lead ad icon");	
	}
	//Pre Condition verification method
	public void clickOnCustomAdButtonAndSelectListing() {
		if(!page.clickOnCustomAdButton()) {
			throw new SkipException("Skipping the test becasuse [Click on Custom Ad Button] pre-condition was failed.");
		}
		if(!page.getSelectListingAlert().isSelectListingAlert() || !page.getSelectListingAlert().clickOnOkButton()) {
			throw new SkipException("Skipping the test becasuse [Select Listing Alert dialog was not opened] pre-condition was failed.");
		}
	}
	public void clickOnSelectButton() {
		if(!page.clickOnSelectButton()) {
			throw new SkipException("Skipping the test becasuse [Click on SELECT Button] pre-condition was failed.");
		}
	}
	public void selectTestPlanPreCond() {
		if(!page.selectPlan("$120")) {
			throw new SkipException("Skipping the test becasuse [Click on Plan Button] pre-condition was failed.");
		}
	}
	public void clickOnNextStepPreCond() {
		if(!page.clickOnNextStepButton()) {
			throw new SkipException("Skipping the test becasuse [Click on NEXT Step Button] pre-condition was failed.");
		}
	}
	public void clickOnPlaceAdAButton() {
		String l_current_url = driver.getCurrentUrl();
		l_current_url = l_current_url.split("ad=")[1];
		saveData(l_current_url,CacheFilePathsConstants.SelfServeAds);
		if(!page.clickOnPlaceAdButton()) {
			throw new SkipException("Skipping the test becasuse [Click on Place Ad Button] pre-condition was failed.");
		}
	}
	public void clickOnTermsAndConditionCheckbox() {
		if(!page.clickOnTermsAndCond()) {
			throw new SkipException("Skipping the test becasuse [Click on Terms and Condition checkbox] pre-condition was failed.");
		}
	}
	public void clickOnPausedAdCheckbox() {
		if(!page.clickOnTestingAdCheckBox()) {
			throw new SkipException("Skipping the test becasuse [Click on Paused Ad checkbox] pre-condition was failed.");
		}
	}
	public void clickOnQuickAdsSelectAButton() {
		if(!page.clickOnQuickAdsSelectButton()) {
			throw new SkipException("Skipping the test becasuse [Click on Quick Ad Button] pre-condition was failed.");
		}
	}
	
	public void saveData(String dataToWrite,String fileToWrite) {
		emptyFile(fileToWrite, "");
		JSONObject jObject = new JSONObject();
		jObject.put("zurple_ad_id", dataToWrite);
		writeJsonToFile(fileToWrite,jObject);
	}

	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}

}
