/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.text.ParseException;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.backoffice.ads.ZBOAdsOverviewPage;
import com.zurple.backoffice.ads.ZBOCreateAdPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOCreateAdPageTest extends PageTest{

	private JSONObject dataObject;
	private ZBOCreateAdPage page;
	private WebDriver driver;
	private String lAd_Type = "";
	private String l_heading = "";
	private String l_desc = "";
	private String listing_Address = "";
	private String lAd_budget = "";
	private String lDefaultCity = "";
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
	public void testVerifyCustomAdSectionIsDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCreateAdPage(), "Create Ad Page is not visible..");
		assertTrue(page.isCreateAdStep1Visible(), "Create Ad Page Step 1 is not visible..");
		assertTrue(page.isCustomAdsHeadingDisplayed(), "Create Custom Ads heading is not displayed..");
	}
	@Test
	public void testVerifyPromoteListingHeadingIsDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isPromoteListingHeadingIsVisible(), "Promote Listing heading is not displayed..");
	}
	@Test
	public void testVerifyCorrectTextIsDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCorrectTextDisplayed(), " 'Get more exposure, interest and engagement for your listing' is not displayed..");
	}
	@Test
	public void testVerifyHomeIconIsDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isHomeIconDisplayedInCreateAdBox(), "Home Icon inside Create Custom Ad box is not displayed..");
	}
	@Test
	public void testVerifyCustomAdButtonIsDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCreateCustomAdButtonIsDisplayed(), "Create Custom Ad button is not displayed..");
	}
	@Test
	public void testVerifyCreateAdBoxIsBouncing() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isCreateCustomBoxBouncing(), "Create Custom Ad box is not bouncing on mouse hover..");
	}
	@Test
	public void testSelectQuickAdsHeadingDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isSelectQuickAdsHeadingDisplayed(), "Select a Quick Ad heading is not visible..");
	}
	@Test
	public void testListingQuickAdsHeadingDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isListingQuickAdsHeadingDisplayed(), "Listing Quick Ad heading is not visible..");
	}
	@Test
	public void testListingAddressIsDisplayedInQuickAdBox() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isListingAddressIsDisplayedInQuickAdBox(), "Listing Address is not visible in Quick Ad box..");
	}
	@Test
	public void testHotPropertyHeadingVisible() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isHotPropertyHeadingVisible(), "Hot Properties heading is not visible in Quick Ad box..");
	}
	@Test
	public void testQuickAdThumbnailVisible() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isQuickAdThumbnailVisible(), "Listing Thumbnail is not visible in Quick Ad box..");
	}
	@Test
	public void testQuickAdsDescriptionDisplayed() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isQuickAdsDescriptionDisplayed(), "Listing description is not visible in Quick Ad box..");
	}
	@Test
	public void testQuickAdDomainDomainIsCorrect() throws ParseException {
		getPage("/create-ad/step-one",true);
		String l_domain = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url");
		assertTrue(page.getQuickAdDomain().contains(l_domain), "Correct domain is not visible in Quick Ad box..");
	}
	@Test
	public void testQuickAdTitleVisible() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isQuickAdTitleVisible(), "Quick Ad title 'Hot Proerty' is not visible..");
	}
	@Test
	public void testQuickAdSelectButtonVisible() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.isQuickAdSelectButtonVisible(), "Select Button is not visible in Quick Ad box..");
	}
	@Test
	public void testQuickAdSlideShowIsWorking() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.verifyAdSlideShowIsWorking(), "Slide show is not working for Quick Ads..");
	}
	@Test
	public void testVerifyListingPopUpAppearsOnClickingCreateCustomButton() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.clickOnCustomAdButton(), "Unable to click on Create Custom Ad Button..");
		assertTrue(page.getSelectListingAlert().isSelectListingAlert(), "Listing Alert is not visible..");
	}
	@Test
	public void testVerifyCorrectTextIsDisplayedOnSelectListingPopUp() throws ParseException {
		getPage("/create-ad/step-one",true);
		assertTrue(page.getSelectListingAlert().isSelectListingAlert(), "Listing Alert is not visible..");
		assertTrue(page.getSelectListingAlert().verifyText("Please Select the listing you"), "Crrect text is not displayed on listing pop up");
	}

	@Test
	public void testCancelButtonClosesThePopup() throws ParseException {
		getPage("/create-ad/step-one",true);
		if(page.clickOnCustomAdButton()) {
			AutomationLogger.info("Select Listing Pop Up is already opened..");
		}
		assertTrue(page.getSelectListingAlert().clickOnCancelButton(), "Unable to click on cancel button..");
		ActionHelper.staticWait(10);
		assertFalse(page.getSelectListingAlert().isSelectListingAlert(), "Select Listing Alert is not closed after clicking on Cancel button");
	}

	@Test
	public void testVerifyUserLandsToStep2AfterClickingOk() throws ParseException {
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
	public void testVerifyListingAddressIsSameOnStep1AndStep2() throws ParseException {
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
	public void testVerifyEditButtonRedirectsUserOnStep1() throws ParseException {
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
	public void testSection1IsCheckedOnStep2() throws ParseException {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		assertTrue(page.isCreateAdStep2Visible(), "Create Ad - Step 2 heading is not visible..");
		assertTrue(page.isStep1Checked(), "Section 1 is not checked on Step 2..");		
	}
	@Test
	public void testSection1AdHeadingIsVisible() throws ParseException {
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
		String lAdTitle = updateName("");
		if(!page.typeAdHeading(lAdTitle)) {
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
		assertTrue(page.isSelectDestinationLabelVisible(), "Select destination site label is not visible..");	
		assertTrue(page.selectSite(l_domain), "Unable to select site from dropdown");
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
	public void testAdDuration() throws ParseException {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		ActionHelper.staticWait(2);
		selectTestPlanPreCond();
		clickOnNextStepPreCond();
		assertTrue(page.verifyStartEndAndRenewalDate(), "Step 3 of 4 is enabled..");
	}
	
	@Test
	public void testVerifyTermsAndConditionDisplayed() throws ParseException {
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
	public void testVerifyTermsAndConditionLinkWorking() throws ParseException {
		getPage("/create-ad/step-one",true);
		clickOnCustomAdButtonAndSelectListing();
		clickOnSelectButton();
		ActionHelper.staticWait(2);
		selectTestPlanPreCond();
		clickOnNextStepPreCond();
		assertTrue(page.clickOnTermsAndCondLink(), "Terms and Condition Link is not working..");
	}
	
	@Test
	public void testVerifyUserCanEditAllStepsFromStep4() throws ParseException {
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
	public void testVerifyTestingAdChecboxIsChecked() throws ParseException {
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
	public void testVerifyUserLandsToStep3WhenQuickAdIsSelected() throws ParseException {
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
	public void testVerifyAdUrlChangesToStep3WhenQuickAdIsSelected() throws ParseException {
		getPage("/create-ad/step-one",true);
		clickOnQuickAdsSelectAButton();
		assertTrue(driver.getCurrentUrl().contains("step-three?listing="), "AD URL is not changed to Step 3..");
	}
	
	@Test
	public void testVerifySlideShowWorkingOnStep3WhenQuickAdIsSelected() throws ParseException {
		getPage("/create-ad/step-one",true);
		clickOnQuickAdsSelectAButton();
		assertTrue(page.verifyInstaPreviewSlideShowIsWorkingOnStep2(), "Instagram slide show is not working in ads preview..");
		assertTrue(page.verifyFacebookPreviewSlideShowIsWorkingOnStep2(), "Facebbok slide show is not working in ads preview..");
	}
	
	@Test
	public void testCreateAndVerifyQuickAdsListingAddress() throws ParseException {
		getPage("/create-ad/step-one",true);
		clickOnQuickAdsSelectAButton();
		listing_Address = page.getListingAddressValue();
		lDefaultCity = page.getDefaultCity();
		lAd_budget = page.isMediumReachSelectedByDefault()?"$240":"";
		clickOnNextStepPreCond();
		clickOnTermsAndConditionCheckbox();
		clickOnPausedAdCheckbox();
		clickOnPlaceAdAButton();
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(adsOverviewPage.getListingAddressFirstRow().contains(listing_Address), "Unable to verify listing address on ads overview page.."+"["+listing_Address+"]");	
	}
	
	@Test
	public void testCreateAndVerifyMultipleCitiesQuickAd() throws ParseException {
		getPage("/create-ad/step-one",true);
		String lAddedCity = "San Diego, CA";
		clickOnQuickAdsSelectAButton();
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
	public void testCreateAndVerifyQuickAdsCity() throws ParseException {
		ZBOAdsOverviewPage adsOverviewPage = new ZBOAdsOverviewPage(driver);
		assertTrue(lDefaultCity.contains(adsOverviewPage.getAdLocation()), "Unable to verify ad location on ads overview page.."+"["+lDefaultCity+"]");	
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
	@Test
	@Parameters({"dataFile"})
	public void testCreateAd(String pDataFile) throws ParseException {
		dataObject = getDataFile(pDataFile);
		lAd_Type = dataObject.optString("ad_type");

		getPage("/create-ad/step-one");
		assertTrue(page.isCreateAdPage(), "Create Ad Page is not visible..");
		assertTrue(page.isCreateAdStep1Visible(), "Create Ad Page Step 1 is not visible..");
		switch(lAd_Type) {
		case "Custom":
			assertTrue(page.clickOnCustomAdButton(), "Unable to click on Custom Ad button..");
			assertTrue(page.getSelectListingAlert().isSelectListingAlert(), "Select Listing Alert is not visible..");
			assertTrue(!page.getSelectListingAlert().selectTheListingFromDropdown().isEmpty(), "Unable to select listing from drop down");
			assertTrue(page.getSelectListingAlert().clickOnOkButton(), "Unable to click on OK button");
			break;
		case "Quick":
			assertTrue(page.isStep1SelectQuickAdHeading(), "Step1 quick heading visible ..");
			assertTrue(page.isSlideShowVisible(), "Step1 slide show ad is not visible ..");
			l_heading = page.getFBAdTitleStep1();
			l_desc = page.getFBAdDescStep1().split("\n")[0].split(",")[0];
			assertTrue(page.clickOnSelectButtonStep1(), "Unable to click on select button on step 1 ..");

			break;
		}
		createAdStep2Section1();
		createAdStep2Section2();
		createAdStep2Section3();
		createAdStep2Section4();

	}
	private void createAdStep2Section1() {
		//		assertTrue(page.isCreateAdStep2Visible(), "Step2 is not visible..");
		assertTrue(page.isStep2Section1SelectAnAdHeadingVisible(), "Step 2 Section 1 heading is not visible..");
		assertTrue(page.isListingHeadingVisible(), "Listing title is not visible..");
		assertTrue(page.isStep1Checked(), "Step1 is not checked..");
		assertTrue(page.isStep2Section2HeadingVisible(), "Step2 heading is not visible..");
	}
	private void createAdStep2Section2() {
		if(lAd_Type.equalsIgnoreCase("Custom")) {
			String lSite = EnvironmentFactory.configReader.getPropertyByName("zurple_site_base_url").split("www.")[1];

			l_heading = page.isAdHeadingPopulated();
			l_desc = page.isAdDescPopulated();
			String ld_heading = dataObject.optString("ad_heading");
			String ld_desc = dataObject.optString("ad_desc");;

			assertTrue(!l_heading.isEmpty(), "Step2 ad heading is not populated..");
			assertTrue(!l_desc.isEmpty(), "Step2 ad description is not visible..");

			if(!ld_heading.isEmpty()) {
				assertTrue(page.typeHeading(ld_heading), "Unable to type heading ..");
			}else {
				ld_heading = l_heading;
			}
			if(!ld_desc.isEmpty()) {
				assertTrue(page.typeDesc(ld_desc), "Unable to type description ..");
			}else {
				ld_desc = l_desc;
			}
			assertTrue(page.selectSite(lSite), "Unable to select the site from dropdown..");		
		}

		//Verifications of section 2
		assertTrue(page.verifyAdHeadline(l_heading), "Unable to verify ad heading..");
		assertTrue(page.verifyAdDesc(l_desc), "Unable to verify ad description..");
		assertTrue(page.verifyFbAdPreviewDetails(), "Unable to verify Facebook ad preview details..");
		assertTrue(page.verifyInstagramAdPreviewDetails(), "Unable to verify Instagram ad preview details..");
		if(lAd_Type.equalsIgnoreCase("Custom")) {
			assertTrue(page.clickOnSelectButton(), "Unable to click on select button..");
		}

		assertTrue(page.verifyAdURLIsCorrect(), "Unable to verify ad URL..");
		assertTrue(page.isSection2Checked(), "Step 2 Section 2 is not checkedL..");
	}
	private void createAdStep2Section3() {
		String ld_taregetReachAmount = dataObject.optString("ad_amount");
		assertTrue(page.isDefaultCitySelected(), "Default city is not selected for the ad ..");
		String l_defaultCity = page.getDefaultCity();
		assertTrue(page.selectAdTaregetReach(ld_taregetReachAmount), "Unable to select target reach amount ..");
		assertTrue(page.clickOnNextStepButton(), "Unable to click on Next button ..");
		assertTrue(page.verifyAdCitySection3(l_defaultCity), "Unable to select target reach amount ..");
		assertTrue(page.verifyAdReachSection3(ld_taregetReachAmount), "Unable to select target reach amount ..");
		assertTrue(page.isSection3Checked(), "Section 3 is not checked ..");
		assertTrue(page.isSection3EditButtonEnabled(), "Section 3 edit button is not enabled ..");

	}
	private void createAdStep2Section4() throws ParseException {
		assertTrue(page.isSection4Visible(), "Section 4 heading is not visible  ..");
		assertTrue(page.verifyStartEndAndRenewalDate(), "Unable to verify start, end and renewal date  ..");
		assertTrue(page.clickOnTermsAndCond(), "Unable to click on terms and condition ..");
		assertTrue(page.clickOnPlaceAdButton(), "Unable to click on Place Ad button ..");
	}

	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}

}
