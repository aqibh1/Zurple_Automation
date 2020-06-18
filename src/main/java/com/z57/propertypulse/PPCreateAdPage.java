/**
 * 
 */
package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.messages.Z57MessagesHeadingLibrary;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class PPCreateAdPage extends Page{
	
	@FindBy(xpath="//a[@id='ListngCardbtn']/div")
	WebElement promote_listing_goal_box;
	
	@FindBy(xpath="//a[@id='createCMAbtn']/div")
	WebElement cma_goal_box;
	
	@FindBy(xpath="//a[@id='openHousebtn']/div")
	WebElement open_house_goal_box;
	
	@FindBy(xpath="//a[@id='justSoldbtn']/div")
	WebElement just_sold_goal_box;
	
	@FindBy(xpath="//a[@id='priceReducedBtn']/div")
	WebElement price_reduced_goal_box;
	
	//@FindBy(xpath="//a[@id='neighborhoodExpertBtn']/div")
	@FindBy(xpath="//div[contains(@class,'goalbox_title')]/h2[contains(text(),'Be the Expert')]")
	WebElement neighborhood_expert_goal_box;
	
	@FindBy(xpath="//a[@id='incompleteGoalBtn']/div")
	WebElement incomplete_goal_box;
	
	@FindBy(xpath="//div[@id='cma_ads_cont']/h2[text()='2. Select your Ad']")
	WebElement select_your_ad_cma;
	
	String cma_customize_button = "//a[@data-lsid='"+FrameworkConstants.DYNAMIC_VARIABLE+"' and text()='Customize']";
	String cma_placeAd_button = "//a[@data-lsid='"+FrameworkConstants.DYNAMIC_VARIABLE+"' and text()='Place Ad']";
	
	String cma_thumbnails_section2 = "//div[@id='cma_ads_cont']/descendant::img[@src='/images/mcc/socialposter/fbad_thumb.jpg']";
	String cma_hot_properties_section2 = "//div[@id='cma_ads_cont']/descendant::div[text()='Hot Properties ']";
	
	String cma_pp_preview_url = "//div[@id='cma_ads_cont']/descendant::div[@class='fb_ad_preview_domain']";
	String learn_more_button_cma = "//div[@id='cma_ads_cont']/descendant::a[contains(text(),'Learn')]";
	
	@FindBy(xpath="//div[@id='cma_ads_cont']/descendant::a[contains(text(),'Is Now the Time to Sell?')]")
	WebElement cma1_heading;
	
	@FindBy(xpath="//div[@id='cma_ads_cont']/descendant::a[contains(text(),'Your Home Worth?')]")
	WebElement cma2_heading;
	
	@FindBy(xpath="//h2[text()='3. Customize your Ad']")
	WebElement section3_heading;
	
	@FindBy(xpath="//label[text()='Slideshow' and @disabled='disabled']")
	WebElement slideShow_label_disabled;
	@FindBy(xpath="//label[text()='Image']")
	WebElement image_label;
	@FindBy(xpath="//label[text()='Slideshow']")
	WebElement slideshow_label;
	
	@FindBy(id="fb_ad_title")
	WebElement headline_section3;
	
	@FindBy(id="fb_ad_details")
	WebElement description_section3;
	
	@FindBy(xpath="//div[@id='fb_ad_preview_url']/a")
	WebElement previewUrl_section3;
	
	@FindBy(xpath="//h2[text()='Ad Preview']")
	WebElement adPreview_heading_section3;
	
	@FindBy(id="fb_ad_preview_details")
	WebElement fb_ad_preview_section3;
	
	@FindBy(id="fb_ad_preview_image")
	WebElement fb_ad_preview_image_section3;
	
	@FindBy(id="fb_ad_next_step")
	WebElement next_step_button;
	
	@FindBy(xpath="//h1[text()=' Featured Advertisements on Facebook and Instagram']")
	WebElement createAd_page_heading;
	
	//Step2 Xpaths//
	///////////////
	
	@FindBy(xpath="//div[@class='statusbar_box selected ']/span[text()='Step 2: Choose Placement']")
	WebElement step2_progress_bar;
	
	@FindBy(xpath="//h1[@class='z57-theme-page-topic']")
	WebElement step2_heading;
	
	@FindBy(xpath="//div[@class='tab-content']/h2")
	WebElement step2_h2_heading;
	
	@FindBy(xpath="//li[@class='select2-selection__choice']")
	WebElement step2_ad_city;
	
	String select_target_reach = "//span[@class='ad_reach_content']/small[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	String target_reach_count = "//span[@class='ad_reach_content']/strong";
	
	@FindBy(id="facebook_platform")
	WebElement facebook_platform;
	
	@FindBy(id="instagram_platform")
	WebElement instagram_platform;
	
	String isSlideShow = "//div[@id='fb_ad_preview_slider']/descendant::div[@class='slide-image']";
	
	@FindBy(xpath="//div[@class='fb_ad_preview_details Mpreview']")
	WebElement ad_description_step2;
	
	@FindBy(xpath="//div[@class='fb_ad_preview_domain']")
	WebElement ad_domain_step2;
	
	@FindBy(xpath="//div[@class='fb_ad_preview_title']/a")
	WebElement ad_title_step2;
	
	//Step 3 xpaths
	String ad_values = "//td[@class='table_fb_ad_create_value']";
	
	@FindBy(xpath="//div[@class='statusbar_box selected']/span[text()='Step 3: Confirm Details']")
	WebElement step3_confirmdetails_progress_bar;
	
	@FindBy(xpath="//h1[@class='z57-theme-page-topic']")
	WebElement step3_heading;
	
	@FindBy(xpath="//h2[text()='Step 3 – Confirm Details']")
	WebElement step3_heading2;
	
	@FindBy(xpath="//form[@id='form_fb_ad_create']/div/h2")
	WebElement ad_heading_step3;
	
	@FindBy(xpath="//div[contains(text(),'Your ad will renew on')]")
	WebElement ad_renew_text;
	
	@FindBy(id="ad_payment_confirmation")
	WebElement termsAndCond_checkbox;
	
	@FindBy(id="ad_payment_confirmation_frame")
	WebElement termsAndCond_checkbox_text;
	
	@FindBy(xpath="//input[@name='fb_test_ad']")
	WebElement test_ad_checkbox;
	
	@FindBy(id="fb_ad_final_confirmation_modal")
	WebElement success_dialog;
	
	@FindBy(xpath="//a[text()='Go to Ads Overview']")
	WebElement ads_overview_button;
	
	//Listing Ads
	String ad_Desc_listing_ads = "//div[@id='listing_ads_cont']/descendant::span[@class='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String ad_domain_listing_ads = "//div[@id='listing_ads_cont']/descendant::div[@class='fb_ad_preview_domain']";
	String ad_title_listing_ads = "//div[@id='listing_ads_cont']/descendant::div[@class='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/a";
	String customize_button_listing_ads = "//div[@id='listing_ads_cont']/descendant::a[text()='Customize' and @data-toggle]";
	String place_Ad_button_listing_ads = "//div[@id='listing_ads_cont']/descendant::a[text()='Place Ad' and @data-toggle]";
	String customize_button_open_house = "//div[@id='listing_ads_cont']/descendant::a[text()='Customize' and contains(@class,'cust_open_house_btn')]";
	
	@FindBy(id="continue_just_sold")
	WebElement continue_button;
	
	@FindBy(xpath="//div[@id='listing_ads_cont']/h2[text()='2. Select your Ad']")
	WebElement select_your_ad_section2;
	
	//Neighborhood expert ads
	@FindBy(id="searchHomesBtn")
	WebElement searchHomes_button;
	@FindBy(id="viewSchoolReportBtn")
	WebElement viewSchoolReports_button;
	@FindBy(id="viewCommunityReportBtn")
	WebElement communityReports_button;
	@FindBy(id="viewAboutmeBtn")
	WebElement promoteYourself_button;
	@FindBy(id="viewSoldHomesBtn")
	WebElement viewSoldHomes_button;
	
	//Listing Ads
	String ad_Desc_ng_ads = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::span[contains(@class,'desc')]";
	String ad_domain_ng_ads = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::div[@class='fb_ad_preview_domain']";
	String ad_title_ng_ads = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::div[contains(@class,'fb_ad_preview_title')]/a";
	String customize_button_ng_ads = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::a[text()='Customize']";
	String place_Ad_button_ng_ads = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::a[text()='Place Ad']";
	
	@FindBy(xpath="//div[@id='neighborhoodAds_cont']/h2[text()='2. Select your Ad']")
	WebElement select_your_Ad_ng_section2;
	
	//School Reports
	String ad_Desc_vsr_cr_ng_ads = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::div[contains(@class,'fb_ad_preview_details')]";

	@FindBy(id="fb_ad_edit_url")
	WebElement edit_button;
	
	@FindBy(id="fb_ad_url")
	WebElement edit_url_input;
	
	@FindBy(xpath="//div[@id='incompleteAds_cont']/h2[text()='2. Select your Ad']")
	WebElement select_your_Ad_section2_incomplete_ads;
	
	public PPCreateAdPage() {
		
	}
	public PPCreateAdPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean selectYourGoal(String pSelectGoad) {
		boolean isSuccess = false;
		switch(pSelectGoad) {
		case "Promote Listing":
			isSuccess = ActionHelper.Click(driver, promote_listing_goal_box);
			break;
		case "Find Sellers":
			isSuccess = ActionHelper.Click(driver, cma_goal_box);
			break;
		case "Announce Open House":
			isSuccess = ActionHelper.Click(driver,open_house_goal_box);
			break;
		case "Toot Your Horn":
			isSuccess = ActionHelper.Click(driver, just_sold_goal_box);
			break;
		case "Market Price Reduction":
			isSuccess = ActionHelper.Click(driver, price_reduced_goal_box);
			break;
		case "Be the Expert":
			ActionHelper.staticWait(10);
			ActionHelper.ScrollDownByPixels(driver, "150");
			ActionHelper.staticWait(5);
			isSuccess = ActionHelper.Click(driver, neighborhood_expert_goal_box);
			break;
		case "Finish an Incomplete Ad":
			isSuccess = ActionHelper.Click(driver, incomplete_goal_box);
			break;
		}
		return isSuccess;
	}
	
	public boolean selectYourAdCMA(String pSelectCMAAd, String pCustomizeOrPlaceAd) {
		boolean isSuccess = false;
		if(ActionHelper.waitForElementToBeVisible(driver, select_your_ad_cma, 60)) {
			if(pSelectCMAAd.equalsIgnoreCase("CMA1")) {
				isSuccess = clickCustomizeOrPlace("cma1",pCustomizeOrPlaceAd);
			}else {
				isSuccess = clickCustomizeOrPlace("cma2",pCustomizeOrPlaceAd);
			}
		}
		return isSuccess;
	}

	public void verificationOfStep2CMAAds() {
		assertTrue(isCMAElementsDisplayed(cma_thumbnails_section2), "CMA Thumbnail icons are not displayed..");
		assertTrue(isCMAElementsDisplayed(cma_hot_properties_section2), "CMA hot properties heading not displayed..");
		assertTrue(verifyCMAAdPreviewUrl("propertypulse.z57.com "), "CMA ads preview URL is not correct..");
		assertTrue(isCMAElementsDisplayed(learn_more_button_cma),"Learn More button is not visible..");
		assertTrue(verifyCMALearnMoreUrl(learn_more_button_cma),"Learn More button URL is not correct..");
		assertTrue(ActionHelper.isElementVisible(driver, cma1_heading),"CMA 1 heading is not displayed");
		assertTrue(ActionHelper.isElementVisible(driver, cma2_heading),"CMA 2 heading is not displayed");
	}
	
	
	private boolean clickCustomizeOrPlace(String pSelectCMAAd, String pCustomizeOrPlaceAd) {
		boolean isSuccess = false;
		if(pCustomizeOrPlaceAd.equalsIgnoreCase("Place Ad")) {
			isSuccess = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, cma_placeAd_button, pSelectCMAAd));
		}else {
			isSuccess = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, cma_customize_button, pSelectCMAAd));
		}
		return isSuccess;
	}
	
	private boolean isCMAElementsDisplayed(String pXpath) {
		boolean isSuccess = true;
		List<WebElement> list_of_elements = ActionHelper.getListOfElementByXpath(driver, pXpath);
		if(list_of_elements.size()==2) {
			for(WebElement element: list_of_elements) {
				if(!ActionHelper.isElementVisible(driver, element)) {
					isSuccess = false;
					break;
				}
			}
		}
		return isSuccess;
	}
	private boolean verifyCMAAdPreviewUrl(String pUrl) {
		boolean isSuccess = true;
		List<WebElement> list_of_elements = ActionHelper.getListOfElementByXpath(driver, cma_pp_preview_url);
		if(list_of_elements.size()==2) {
			for(WebElement element: list_of_elements) {
				if(!ActionHelper.getText(driver, element).equalsIgnoreCase(pUrl.trim())) {
					isSuccess = false;
					break;
				}
			}
		}
		return isSuccess;
	}
	private boolean verifyCMALearnMoreUrl(String pXpath) {
		boolean isSuccess = true;
		List<WebElement> list_of_elements = ActionHelper.getListOfElementByXpath(driver, pXpath);
		if(list_of_elements.size()==2) {
			for(WebElement element: list_of_elements) {
				if(!ActionHelper.getAttribute(element, "href").contains("?type=cma")) {
					isSuccess = false;
					break;
				}
			}
		}
		return isSuccess;
	}
	
	public boolean isSlideShowButtonDisabled() {
		return ActionHelper.isElementVisible(driver, slideShow_label_disabled);
	}
	public boolean isImageButtonVisible() {
		return ActionHelper.isElementVisible(driver, image_label);
	}
	public boolean verifyAdHeading(String pHeading) {
		return ActionHelper.getTextByValue(driver, headline_section3).equalsIgnoreCase(pHeading);
	}
	public boolean verifyAdDescription(String pDesc) {
		String lText = ActionHelper.getTextByValue(driver, description_section3).replace("  ", " ");
		return lText.contains(pDesc);
	}
	public boolean verifyAdPreviewUrl(String pUrl) {
		return ActionHelper.getText(driver, previewUrl_section3).contains(pUrl.toLowerCase());
	}
	public boolean verifyAdPreviewHeadingIsVisible() {
		return ActionHelper.isElementVisible(driver, adPreview_heading_section3);
	}
	public boolean verifyAdDescriptionInAdPreviewSection3(String pDesc) {
		return ActionHelper.getText(driver, fb_ad_preview_section3).contains(pDesc);
	}
	public boolean verifyAdPreviewImageIsVisibleSection3() {
		return ActionHelper.isElementVisible(driver, fb_ad_preview_image_section3);
	}
	public boolean isCreateAdPageVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, createAd_page_heading, 30);
	}
	public boolean isSection2Step1Visible() {
		return ActionHelper.waitForElementToBeVisible(driver, select_your_ad_cma, 20);
	}
	public boolean isSection3Step1Visible() {
		return ActionHelper.waitForElementToBeVisible(driver, section3_heading, 10);
	}
	public boolean clickOnNextStepButton() {
		return ActionHelper.Click(driver, next_step_button);
	}
	
	public boolean isStep2ProgressBar() {
		return ActionHelper.waitForElementToBeVisible(driver, step2_progress_bar, 60);
	}
	public boolean isStep2HeadingDisplayed() {
		boolean isSuccess = false;
		if(ActionHelper.waitForElementToBeVisible(driver, step2_heading, 20)) {
			isSuccess = ActionHelper.getText(driver, step2_heading).equalsIgnoreCase(Z57MessagesHeadingLibrary.ad_step2_heading);
		}
		return isSuccess;
	}
	public boolean isStep2Heading2Displayed() {
		boolean isSuccess = false;
		if(ActionHelper.waitForElementToBeVisible(driver, step2_h2_heading, 20)) {
			isSuccess = ActionHelper.getText(driver, step2_h2_heading).equalsIgnoreCase(Z57MessagesHeadingLibrary.ad_step2_heading2);
		}
		return isSuccess;
	}
	public boolean isStep2AdCityDisplayed() {
		boolean isSuccess = false;
		if(ActionHelper.waitForElementToBeVisible(driver, step2_ad_city, 10)) {
			isSuccess = !ActionHelper.getText(driver, step2_ad_city).isEmpty();
		}
		return isSuccess;
	}
	public String getTargetCity() {
		String lCity = "";
		if(ActionHelper.waitForElementToBeVisible(driver, step2_ad_city, 10)) {
			lCity = ActionHelper.getText(driver, step2_ad_city);
		}
		return lCity;
	}
	public boolean selectTargetReach(String pBudget) {
		return ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, select_target_reach, pBudget));
	}
	public boolean verifyTargetReachIsDisplayed() {
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, target_reach_count);
		for(WebElement element: list) {
			if(ActionHelper.getText(driver, element).isEmpty()) {
				return false;
			}
		}
		return true;
	}
	public boolean verifyDefaultPlatformsSelected() {
		return ActionHelper.isElementSelected(driver, facebook_platform) && ActionHelper.isElementSelected(driver, instagram_platform);
	}
	public boolean selectPlatform(String pPlatform) {
		boolean isChecked = false;
		String [] lPlatforms = pPlatform.split(",");
		ActionHelper.checkUncheckInputBox(driver, instagram_platform,driver.findElement(By.id("label_instagram")), false);
		for(String lPlatformSingle: lPlatforms) {
			isChecked = false;
			if(lPlatformSingle.equalsIgnoreCase("Facebook")) {
				isChecked = ActionHelper.checkUncheckInputBox(driver, facebook_platform, true);
			}else {
				isChecked = ActionHelper.checkUncheckInputBox(driver, instagram_platform,driver.findElement(By.id("label_instagram")), true);
			}
			if(!isChecked) {
				break;
			}
		}

		return isChecked;
	}
	public boolean isSlideShowAtStep2() {
		boolean isSuccess = false;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, isSlideShow);
		if(list!=null) {
			isSuccess = list.size()>=2;
		}
		return isSuccess;
	}
	public boolean isAdDescOnStep2(String pAdDesc) {
		return ActionHelper.getText(driver, ad_description_step2).contains(pAdDesc);
	}
	public boolean isAdDomainOnStep2(String pAdDomain) {
		return true;//ActionHelper.getText(driver, ad_domain_step2).contains(pAdDomain.toUpperCase());
	}
	public boolean verifyAdTitleStep2(String pTitle) {
		return ActionHelper.getText(driver, ad_title_step2).contains(pTitle);
	}
	public boolean verifyAdSpecificationsStep3(String pSpecification) {
		boolean isFound = true;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, ad_values);
		for(WebElement element: list) {
			isFound = ActionHelper.getText(driver, element).equalsIgnoreCase(pSpecification);
			if(isFound) {
				break;
			}
		}
		return isFound;
	}
	public boolean isStep3ProgressbarDisplayed() {
		return ActionHelper.waitForElementToBeVisible(driver, step3_confirmdetails_progress_bar, 60);
	}
	public boolean isStep3Heading() {
		return ActionHelper.getText(driver, step3_heading).equalsIgnoreCase(Z57MessagesHeadingLibrary.ad_step3_heading);
	}
	public boolean isStep3Heading2Displayed() {
		return ActionHelper.waitForElementToBeVisible(driver, step3_heading2, 30);
	}
	public boolean isValidAdHeadingStep3(String pAdHeading) {
		return ActionHelper.getText(driver, ad_heading_step3).contains(pAdHeading.trim());
	}
	public boolean verifyAdRenewDate(String pDate) {
		return ActionHelper.getText(driver, ad_renew_text).contains(pDate);
	}
	public boolean clickOnTermsAndCondCheckbox() {
		boolean isClicked = false;
		if(ActionHelper.getText(driver, termsAndCond_checkbox_text).contains("I agree to the")) {
			isClicked = ActionHelper.Click(driver, termsAndCond_checkbox);
		}
		return isClicked;
	}
	public boolean clickOnTestAd() {
		return ActionHelper.Click(driver, test_ad_checkbox);
	}
	public boolean isAdPlacedSuccessfully() {
		return ActionHelper.waitForElementToBeVisible(driver, success_dialog, 20);
	}
	public boolean clickOnAdsOverviewPage() {
		return ActionHelper.Click(driver, ads_overview_button);
	}
	public int getListOfProperties(String pSelectGoal) {
		List<WebElement> list_of_elements = new ArrayList<WebElement>();
		switch(pSelectGoal) {
		case "Promote Listing":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "monthly_listing_desc"));
			break;
		case "Announce Open House":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "op_listing_desc"));
			break;
		case "Toot Your Horn":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "so_listing_desc"));
			break;
		case "Market Price Reduction":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "pr_listing_desc"));
			break;
		case "Be the Expert":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ad_Desc_ng_ads);
			break;
		case "Finish an Incomplete Ad":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_ng_ads, "incompleteAds_cont"));
			break;
		}
		return list_of_elements.size();
	}
	public String getAdsDescription(String pSelectGoal, int pListingIndex) {
		String lAdDesc = "";
		List<WebElement> list_of_elements;
		switch(pSelectGoal) {
		case "Promote Listing":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "monthly_listing_desc"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Announce Open House":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "op_listing_desc"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Toot Your Horn":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "so_listing_desc"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Market Price Reduction":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "pr_listing_desc"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Be the Expert":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ad_Desc_ng_ads);
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Finish an Incomplete Ad":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_ng_ads, "incompleteAds_cont"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		}
		return lAdDesc;
	}
	public String getAdsDomain(int pListingIndex) {
		String lAdDomain = "";
		List<WebElement> list_of_elements;
		list_of_elements = ActionHelper.getListOfElementByXpath(driver, ad_domain_listing_ads);
		lAdDomain = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
		return lAdDomain;
	}
	public String getAdsTitle(String pSelectGoal, int pListingIndex) {
		String lAdDesc = "";
		List<WebElement> list_of_elements;
		switch(pSelectGoal) {
		case "Promote Listing":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_listing_ads, "fb_ad_preview_title monthly_listing_title"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Announce Open House":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_listing_ads, "fb_ad_preview_title op_listing_title"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Toot Your Horn":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_listing_ads, "fb_ad_preview_title so_listing_title"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Market Price Reduction":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_listing_ads, "fb_ad_preview_title pr_listing_title"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Be the Expert":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ad_title_ng_ads);
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Finish an Incomplete Ad":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "incompleteAds_cont"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		}
		return lAdDesc;
	}
	public boolean clickOnCustomizeButton(int pIndex, String pCustomizeOrPlaceAd) {
		ActionHelper.staticWait(5);
		boolean isClicked = false;
		List<WebElement> list_of_elements;
		if(pCustomizeOrPlaceAd.equalsIgnoreCase("Customize")) {
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, customize_button_listing_ads);
			isClicked = ActionHelper.Click(driver, list_of_elements.get(pIndex));
		}else {
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, place_Ad_button_listing_ads);
			isClicked = ActionHelper.Click(driver, list_of_elements.get(pIndex));
		}
		return isClicked;
	}
	public boolean clickOnContinueWithoutUpdateButton() {
		boolean isClicked = true;
		if(ActionHelper.waitForElementToBeVisible(driver, continue_button, 30)) {
			isClicked = ActionHelper.Click(driver, continue_button);
		}else {
			AutomationLogger.info("The listing is already marked as sold");
			isClicked = false;
		}
		return isClicked;
	}
	public boolean clickOnImageButton() {
		return ActionHelper.Click(driver, image_label);
	}
	public boolean clickOnSlideShowButton() {
		return ActionHelper.Click(driver, slideshow_label);
	}
	public boolean isSelectYourAdSection2Visible() {
		return ActionHelper.waitForElementToBeVisible(driver, select_your_ad_section2, 30);
	}
	
	public boolean clickOnOpenHouseCustomizeButton(int pIndex, String pCustomizeOrPlaceAd) {
		ActionHelper.staticWait(5);
		boolean isClicked = false;
		List<WebElement> list_of_elements;
		if(pCustomizeOrPlaceAd.equalsIgnoreCase("Customize")) {
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, customize_button_open_house);
			isClicked = ActionHelper.Click(driver, list_of_elements.get(pIndex));
		}else {
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, place_Ad_button_listing_ads);
			isClicked = ActionHelper.Click(driver, list_of_elements.get(pIndex));
		}
		return isClicked;
	}
	public boolean selectNeighborhoodExpertsAds(String pSelectGoal) {
		boolean isSelected = false;
		ActionHelper.staticWait(5);
		switch(pSelectGoal) {
		case "Search Homes":
			isSelected = ActionHelper.Click(driver, searchHomes_button);
			break;
		case "View School Reports":
			isSelected = ActionHelper.Click(driver, viewSchoolReports_button);
			break;
		case "Community Reports":
			isSelected = ActionHelper.Click(driver, communityReports_button);
			break;
		case "Promote Yourself":
			isSelected = ActionHelper.Click(driver, promoteYourself_button);
			break;
		case "View Sold Homes":
			isSelected = ActionHelper.Click(driver, viewSoldHomes_button);
			break;
		}
		ActionHelper.staticWait(10);
		return isSelected;
	}
	public boolean verifyNeighborhoodExpertsAds(String pSelectGoal) {
		boolean isVerified= false;
		List<WebElement> list_of_elements = new ArrayList<WebElement>();
		switch(pSelectGoal) {
		case "Search Homes":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "ng_exp_idx_ads_cont"));
			isVerified = list_of_elements.size()==2;
			break;
		case "View School Reports":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "school_report_cont"));
			isVerified = list_of_elements.size()==2;
			break;
		case "Community Reports":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "community_report_cont"));
			isVerified = list_of_elements.size()==4;
			break;
		case "Promote Yourself":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "ng_about_me_ads_cont"));
			isVerified = list_of_elements.size()==2;
			break;
		case "View Sold Homes":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "ng_sold_homes_ads_cont"));
			isVerified = list_of_elements.size()==2;
			break;
		}
		return isVerified;
	}
	public Map<String,String> getNGExpertAdsDescAndTitleString (String pSelectGoal, int pIndex) {
		Map<String,String> lTitleAndDesc = new HashMap<String, String>();
		List<String> list_of_titles = new ArrayList<String>();
		List<String> list_of_desc = new ArrayList<String>();
		switch(pSelectGoal) {
		case "Search Homes":
			list_of_titles.add(0, "Search Homes for Sale");
			list_of_desc.add(0, "Get access to the hottest properties for sale");
			list_of_titles.add(1, "Search Homes for Sale");
			list_of_desc.add(1, "Get access to the hottest properties for sale");
			break;
		case "View School Reports":
			list_of_titles.add(0, "Interested in Schools in");
			list_of_desc.add(0, "See schools within");
			list_of_titles.add(1, "Interested in Schools in");
			list_of_desc.add(1, "See schools within");
			break;
		case "Community Reports":
			list_of_titles.add(0, "Thinking about moving to");
			list_of_desc.add(0, "Click to see important statistics about");
			list_of_titles.add(1, "Want to know where your city ranks?");
			list_of_desc.add(1, "Click to see free community reports about your city and more.");
			list_of_titles.add(2, "Looking for a breath of fresh air?");
			list_of_desc.add(2, "Find which communities have the lowest air pollution in your area. Click for more details!");
			list_of_titles.add(3, "Thinking about moving to");
			list_of_desc.add(3, "Click to see important statistics about");
			break;
		case "Promote Yourself":
			list_of_titles.add(0, "Premiere Real Estate Agent");
			list_of_desc.add(0, "Need an experienced agent?");
			list_of_titles.add(1, "Your Local Real Estate Expert");
			list_of_desc.add(1, "Want to know more about your local housing market? Meet");
			break;
		case "View Sold Homes":
			list_of_titles.add(0, "Home Sales!");
			list_of_desc.add(0, "See what homes are selling for in");
			list_of_titles.add(1, "Home Sales!");
			list_of_desc.add(1, "See what homes are selling for in");
			break;
		}
		lTitleAndDesc.put("title", list_of_titles.get(pIndex));
		lTitleAndDesc.put("desc", list_of_desc.get(pIndex));
		
		return lTitleAndDesc;
	}
	public boolean isSlectYourAd() {
		return ActionHelper.waitForElementToBeVisible(driver, select_your_Ad_ng_section2, 30);
	}
	public String getNGExpertAdsDomain(int pListingIndex, String pSelectGoal) {
		String lAdDomain = "";
		List<WebElement> list_of_elements;
		switch(pSelectGoal) {
		case "Search Homes":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver,ActionHelper.getDynamicElementXpath(driver, ad_domain_ng_ads, "ng_exp_idx_ads_cont") );
			lAdDomain = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));break;
		case "View School Reports":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_domain_ng_ads, "school_report_cont"));
			lAdDomain = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Community Reports":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_domain_ng_ads, "community_report_cont"));
			lAdDomain = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Promote Yourself":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_domain_ng_ads, "ng_about_me_ads_cont"));
			lAdDomain = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "View Sold Homes":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver,ActionHelper.getDynamicElementXpath(driver, ad_domain_ng_ads, "ng_sold_homes_ads_cont") );
			lAdDomain = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Finish an Incomplete Ad":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver,ActionHelper.getDynamicElementXpath(driver, ad_domain_ng_ads, "incompleteAds_cont") );
			lAdDomain = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		}
		return lAdDomain;
	}
	public boolean clickOnCustomizeButtonForNGExpert(int pIndex, String pCustomizeOrPlaceAd, String pNGExpertAdType) {
		ActionHelper.staticWait(5);
		boolean isClicked = false;
		List<WebElement> list_of_elements;

		switch(pNGExpertAdType) {
		case "Search Homes":
			customize_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, customize_button_ng_ads, "ng_exp_idx_ads_cont");
			place_Ad_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, place_Ad_button_ng_ads, "ng_exp_idx_ads_cont");
			break;
		case "View School Reports":
			customize_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, customize_button_ng_ads, "school_report_cont");
			place_Ad_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, place_Ad_button_ng_ads, "school_report_cont");
			break;
		case "Community Reports":
			customize_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, customize_button_ng_ads, "community_report_cont");
			place_Ad_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, place_Ad_button_ng_ads, "community_report_cont");
			break;
		case "Promote Yourself":
			customize_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, customize_button_ng_ads, "ng_about_me_ads_cont");
			place_Ad_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, place_Ad_button_ng_ads, "ng_about_me_ads_cont");
			break;
		case "View Sold Homes":
			customize_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, customize_button_ng_ads, "ng_sold_homes_ads_cont");
			place_Ad_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, place_Ad_button_ng_ads, "ng_sold_homes_ads_cont");
			break;
		case "Finish an Incomplete Ad":
			customize_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, customize_button_ng_ads, "incompleteAds_cont");
			place_Ad_button_ng_ads = ActionHelper.getDynamicElementXpath(driver, place_Ad_button_ng_ads, "incompleteAds_cont");
			break;
		}	
		if(pCustomizeOrPlaceAd.equalsIgnoreCase("Customize")) {
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, customize_button_ng_ads);
			isClicked = ActionHelper.Click(driver, list_of_elements.get(pIndex));
		}else {
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, place_Ad_button_ng_ads);
			isClicked = ActionHelper.Click(driver, list_of_elements.get(pIndex));
		}
		return isClicked;
	}
	public String getNGExpertAdsDescription(String pSelectGoal, int pListingIndex) {
		String lAdDesc = "";
		List<WebElement> list_of_elements;
		switch(pSelectGoal) {
		case "Search Homes":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver,ActionHelper.getDynamicElementXpath(driver, ad_Desc_ng_ads, "ng_exp_idx_ads_cont") );
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));break;
		case "View School Reports":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_vsr_cr_ng_ads, "school_report_cont"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Community Reports":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_vsr_cr_ng_ads, "community_report_cont"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Promote Yourself":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_ng_ads, "ng_about_me_ads_cont"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "View Sold Homes":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver,ActionHelper.getDynamicElementXpath(driver, ad_Desc_ng_ads, "ng_sold_homes_ads_cont") );
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		}
		return lAdDesc;
	}

	public String getNGExpertAdsTitle(String pSelectGoal, int pListingIndex) {
		String lAdDesc = "";
		List<WebElement> list_of_elements;
		switch(pSelectGoal) {
		case "Search Homes":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver,ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "ng_exp_idx_ads_cont") );
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));break;
		case "View School Reports":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "school_report_cont"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Community Reports":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "community_report_cont"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "Promote Yourself":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "ng_about_me_ads_cont"));
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		case "View Sold Homes":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver,ActionHelper.getDynamicElementXpath(driver, ad_title_ng_ads, "ng_sold_homes_ads_cont") );
			lAdDesc = ActionHelper.getText(driver, list_of_elements.get(pListingIndex));
			break;
		}
		return lAdDesc;
	}
	public int getListOfNGExpertProperties(String pSelectGoal) {
		List<WebElement> list_of_elements = new ArrayList<WebElement>();
		switch(pSelectGoal) {
		case "Search Homes":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver,ActionHelper.getDynamicElementXpath(driver, ad_Desc_ng_ads, "ng_exp_idx_ads_cont") );
			break;
		case "View School Reports":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_vsr_cr_ng_ads, "school_report_cont"));
			break;
		case "Community Reports":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_vsr_cr_ng_ads, "community_report_cont"));
			break;
		case "Promote Yourself":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_ng_ads, "ng_about_me_ads_cont"));
			break;
		case "View Sold Homes":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver,ActionHelper.getDynamicElementXpath(driver, ad_Desc_ng_ads, "ng_sold_homes_ads_cont") );
			break;
		}
		return list_of_elements.size();
	}
	public boolean editAndUpdateUrl(String pUrl) {
		boolean isUrlUpdated = false;
		if(ActionHelper.waitForElementToBeVisible(driver, edit_button, 20) && ActionHelper.Click(driver, edit_button)) {
			isUrlUpdated = ActionHelper.ClearAndType(driver, edit_url_input, pUrl);
		}
		return isUrlUpdated;
	}
	public boolean isSlectYourAdHeadingVisisbleIncompleteAds() {
		return ActionHelper.waitForElementToBeVisible(driver, select_your_Ad_section2_incomplete_ads, 30);
	}
	public String getAdPlanStep3() {
		boolean isFound = true;
		String lPlan = "Monthly";
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, ad_values);
		for(WebElement element: list) {
			isFound = ActionHelper.getText(driver, element).contains("per week");
			if(isFound) {
				lPlan = "Weekly";
				break;
			}
		}
	
		return lPlan;
	}
	
	public int getListOfProperties(String pSelectGoal, String pSlideshowOrImage) {
		List<WebElement> list_of_elements = new ArrayList<WebElement>();
		switch(pSelectGoal) {
		case "Promote Listing":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "monthly_listing_desc"));
			break;
		case "Announce Open House":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "op_listing_desc"));
			break;
		case "Toot Your Horn":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "so_listing_desc"));
			break;
		case "Market Price Reduction":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_listing_ads, "pr_listing_desc"));
			break;
		case "Be the Expert":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ad_Desc_ng_ads);
			break;
		case "Finish an Incomplete Ad":
			list_of_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_Desc_ng_ads, "incompleteAds_cont"));
			break;
		}
		return list_of_elements.size();
	}
}
