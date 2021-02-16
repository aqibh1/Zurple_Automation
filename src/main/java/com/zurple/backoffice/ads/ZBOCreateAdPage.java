/**
 * 
 */
package com.zurple.backoffice.ads;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOSelectListingAlert;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class ZBOCreateAdPage extends Page{
	
	@FindBy(xpath="//h2[text()='Create Ad']")
	WebElement createAd_heading;
	
	@FindBy(xpath="//strong[contains(text(),'Step 1:')]")
	WebElement step1_strong;
	
	@FindBy(xpath="//a[@id='promote_adtype_selector']/descendant::button[contains(text(),'Create Custom Ad')]")
	WebElement customize_button;
	
	@FindBy(xpath="//h2[text()='Create Ad - Step 2']")
	WebElement createAd_step2_heading;
	
	@FindBy(xpath="//h5[@class='inner_stephead']/i[@class='far fa-check-circle checkicon zurp_checked']")
	WebElement step1_checked;
	
	@FindBy(xpath="//strong[contains(text(),'Step 2:')]")
	WebElement step2_strong;
	
	@FindBy(id="ad_title_full")
	WebElement ad_title_input;;
	
	@FindBy(id="fb_ad_details")
	WebElement fb_ad_desc;;
	
	@FindBy(id="site")
	WebElement site_dropdown;
	
	@FindBy(xpath="//div[@id='form-submit_btn']/a")
	WebElement select_button;
	
	@FindBy(xpath="//div[@id='form-submit_btn']/a")
	WebElement fb_Active_logo;
	
	@FindBy(xpath="//div[@id='form-submit_btn']/a")
	WebElement insta_fb_logo;
	
	//Step 2 Section 1
	@FindBy(xpath="//p[@style]")
	WebElement listing_address_section1_step2;
	@FindBy(xpath="//a[@class='btn ad_edit_btn' and text()='Edit']")
	WebElement edit_button_section1_step2;
	
	//Custom Ads
	@FindBy(xpath="//h5[@class='bold_center' and text()=' Create a Custom Ad']")
	WebElement custom_Ads_heading;
	@FindBy(xpath="//div[@class='adpurplebg goalbox_title']/h2")
	WebElement promote_listing_heading;
	@FindBy(xpath="//div[@class='goalbox_body adtype_body']")
	WebElement create_Ad_box_text;
	@FindBy(xpath="//div[@class='goalbox_body adtype_body']/i[@class='fa fa-home']")
	WebElement home_icon_create_Ad_box;
	@FindBy(xpath="//*[@class='goalBox mtb-8 adpurplebg_light callout_modal']")
	WebElement create_custom_Ad_modal;
	
	//Quick Ad
	@FindBy(xpath="//h5[@class='bold_center' and contains(text(),' Select a Quick Ad')]")
	WebElement quick_Ads_heading;
	@FindBy(xpath="//div[@class='ad_outerbox']/h4[text()='Listing Quick Ad']")
	WebElement listing_quick_ad_heading;
	@FindBy(xpath="//div[@class='ad_outerbox']/h4[text()='Listing Quick Ad']/following-sibling::p")
	WebElement listing_address;
	@FindBy(xpath="//div[@class='fb_ad_preview_frame pull-left']/descendant::div[@class='ad_page_title']")
	WebElement hot_properties_heading;
	@FindBy(xpath="//div[@class='fb_ad_preview_frame pull-left']/descendant::div[@class='ad_page_dp']/img")
	WebElement img_thumb_quick_Ad;
	@FindBy(xpath="//div[@class='fb_ad_preview_frame pull-left']/descendant::div[@class='fb_ad_preview_details Mpreview']")
	WebElement quick_Ad_Desc;
	@FindBy(xpath="//div[@class='fb_ad_preview_frame pull-left']/descendant::div[@class='fb_ad_preview_domain']")
	WebElement quick_Ad_Domain;
	@FindBy(xpath="//div[@class='fb_ad_preview_frame pull-left']/descendant::div[@class='fb_ad_preview_title']/a")
	WebElement quick_Ad_title;
	@FindBy(xpath="//div[@class='ad_outerbox']/a[text()='Select']")
	WebElement select_button_quick_ad_box;
	@FindBy(xpath="//div[@class='ad_outerbox']/descendant::i[@class='fa fa-play']")
	WebElement quick_Ad_play_icon;
	String quick_Ad_Slide_show_images = "//div[@class='ad_outerbox']/descendant::div[@class='slide-image']/img";
	
	//FB Ad Preview Step 2
	@FindBy(xpath="//div[@id='facebook_ad_pewview']/descendant::span[@class='ad_preview_title']")
	WebElement fb_ad_preview_desc;
	@FindBy(xpath="//div[@id='facebook_ad_pewview']/descendant::div[@class='playicon_slide']")
	WebElement fb_ad_play_icon;
	@FindBy(xpath="//div[@id='facebook_ad_pewview']/descendant::div[@class='fb_ad_preview_domain']")
	WebElement fb_ad_preview_domain;
	@FindBy(xpath="//div[@id='facebook_ad_pewview']/descendant::div[@class='fb_ad_preview_title']/a")
	WebElement fb_ad_preview_title;
	@FindBy(xpath="//div[@id='facebook_ad_pewview']/descendant::div/a[text()='Learn More']")
	WebElement fb_ad_learnmore_button;
	
	//Instagram Ad preview Step 2
	@FindBy(xpath="//div[@id='instagram_ad_pewview']/descendant::div[@class='post_preview_text']")
	WebElement insta_ad_preview_text;
	@FindBy(xpath="//div[@id='instagram_ad_pewview']/descendant::div/a[contains(text(),'Learn More')]")
	WebElement insta_ad_learnmore_button;
	@FindBy(xpath="//div[@id='facebook_ad_pewview']/descendant::div[@class='playicon_slide']")
	WebElement insta_ad_play_icon;
	
	//Section 1 Step 2
	@FindBy(xpath="//h5[@class='inner_stephead' and text()='Select an Ad Type ']")
	WebElement selectAnAdType_section1_step2;
	@FindBy(xpath="//p[@style='margin-left: 13%;']")
	WebElement listing_title_step2_section1;
	@FindBy(xpath="//div[@id='form-submit_btn']/button[text()='Select']")
	WebElement select_section_button;
	
	//Step 2 Section 2 verifications
	@FindBy(id="ad_headline_caption")
	WebElement ad_headline_caption;
	@FindBy(id="ad_description_caption")
	WebElement ad_description_caption;
	@FindBy(xpath="//[@id='ad_url_caption']/a")
	WebElement ad_url_caption;
	@FindBy(xpath="//div[@id='propdetail']/descendant::h2[not(contains(@class,'zurple-tinted-text'))]")
	WebElement website_prop_heading;
	@FindBy(xpath="//h5[@class='inner_stephead']/i[@class='far fa-check-circle checkicon zurp_checked']/following-sibling::strong[contains(text(), '2')]")
	WebElement step2_section2_checked;
	
	//Section 3
	@FindBy(xpath="//select[@id='fb_ad_cities']/option")
	WebElement fb_ad_cities;
	String ad_plan_amount = "//button[@onclick]/descendant::strong[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	@FindBy(xpath="//div[@id='form-submit_btn']/button[text()='Next Step']")
	WebElement next_button_section3;
	@FindBy(xpath="//div[@id='step_3_section']/*/p[@id='ad_headline_caption']")
	WebElement ad_city_caption_section3;
	@FindBy(xpath="//div[@id='step_3_section']/*/p[@id='ad_description_caption']")
	WebElement ad_reach_caption_section3;
	@FindBy(xpath="//div[@id='step_3_section']/*/i[@class='far fa-check-circle checkicon zurp_checked']")
	WebElement section3_checked;
	@FindBy(xpath="//div[@id='step_3_section']/*/a[text()='Edit']")
	WebElement edit_button_section3;
	
	//Section 4
	@FindBy(xpath="//div[@class='step_4_section']/*/strong[contains(text(),'Step 4:')]")
	WebElement section4_heading;
	@FindBy(xpath="//form[@class='step_4_form']/descendant::p[@id='ad_headline_caption']")
	WebElement start_end_date_section4;
	@FindBy(xpath="//form[@class='step_4_form']/*/span")
	WebElement renew_text;
	@FindBy(id="ad_payment_confirmation")
	WebElement ad_payment_confirmation;
	@FindBy(id="fb_placead_callout")
	WebElement fb_placead_callout;
	
	//Custom Ad Step 1
	@FindBy(xpath="//h5[@class='bold_center' and text()=' Select a Quick Ad']")
	WebElement select_quick_Ad_heading;
	@FindBy(xpath="//div[@class='fb_ad_preview_slideshow']")
	WebElement slideshow_Ad;
	@FindBy(xpath="//div[@class='ad_outerbox']/a")
	WebElement select_button_step1;
	@FindBy(xpath="//div[@class='fb_ad_preview_title']/a")
	WebElement fb_Ad_preview_title_step1;
	@FindBy(xpath="//div[@class='fb_ad_preview_details Mpreview']")
	WebElement fb_Ad_preview_desc_step1;
	
	
	
	private ZBOSelectListingAlert selectListingAlert;
	
	public ZBOCreateAdPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setSelectListingAlert();
		PageFactory.initElements(driver, this);
	}

	public ZBOSelectListingAlert getSelectListingAlert() {
		return selectListingAlert;
	}

	public void setSelectListingAlert() {
		this.selectListingAlert = new ZBOSelectListingAlert(driver);
	}

	public boolean isCreateAdPage() {
		return ActionHelper.waitForElementToBeVisible(driver, createAd_heading, 30);
	}
	public boolean isCreateAdStep1Visible() {
		return ActionHelper.waitForElementToBeVisible(driver, step1_strong, 5);
	}
	public boolean clickOnCustomAdButton() {
		return ActionHelper.Click(driver, customize_button);
	}
	public boolean isCreateAdStep2Visible() {
		return ActionHelper.waitForElementToBeVisible(driver, createAd_step2_heading, 30);
	}
	public boolean isStep1Checked() {
		return ActionHelper.waitForElementToBeVisible(driver, step1_checked, 2);
	}
	public boolean isStep2Section2HeadingVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, step2_strong, 10);
	}
	public String isAdHeadingPopulated() {
		return ActionHelper.getValue(driver, driver.findElement(By.id("fb_ad_title")));
	}
	public String isAdDescPopulated() {
		return ActionHelper.getText(driver, driver.findElement(By.id("fb_ad_details")));
	}
	public boolean typeHeading(String pHeading) {
		return ActionHelper.ClearAndType(driver, ad_title_input, pHeading);
	}
	public boolean typeDesc(String pDesc) {
		return ActionHelper.ClearAndType(driver, fb_ad_desc, pDesc);
	}
	public boolean selectSite(String pSite) {
		boolean isSlected = true;
		if(!pSite.isEmpty()) {
			pSite = pSite.contains("stage01.")?pSite.replace("stage01.", ""):pSite;
			isSlected = ActionHelper.selectDropDownOption(driver, site_dropdown, "", pSite);
		}
		return isSlected;
	}
	public boolean clickOnSelectButton() {
		return ActionHelper.Click(driver, select_section_button);
	}
	public boolean verifyFbAdPreviewDetails() {
		if(ActionHelper.getText(driver, fb_ad_preview_desc).isEmpty()) {
			AutomationLogger.error("Facebook ad description is not populated..");
			return false;
		}
		if(ActionHelper.getText(driver, fb_ad_preview_domain).isEmpty()) {
			AutomationLogger.error("Facebook ad preview domain is not populated..");
			return false;
		}
		if(ActionHelper.getText(driver, fb_ad_preview_title).isEmpty()) {
			AutomationLogger.error("Facebook ad preview title is not populated..");
			return false;
		}
		if(!ActionHelper.isElementVisible(driver, fb_ad_play_icon)) {
			AutomationLogger.error("Facebook ad slide show play button is not displayed..");
			return false;
		}
		if(!ActionHelper.isElementVisible(driver, fb_ad_learnmore_button)) {
			AutomationLogger.error("Facebook ad Learn More button is not displayed..");
			return false;
		}
		return true;
	}
	public boolean verifyInstagramAdPreviewDetails() {
		if(ActionHelper.getText(driver, insta_ad_preview_text).isEmpty()) {
			AutomationLogger.error("Instagram ad preview description is not populated..");
			return false;
		}
		if(!ActionHelper.isElementVisible(driver, insta_ad_play_icon)) {
			AutomationLogger.error("Instagram ad slide show play button is not displayed..");
			return false;
		}
		if(!ActionHelper.isElementVisible(driver, insta_ad_learnmore_button)) {
			AutomationLogger.error("Instagram ad Learn More button is not displayed..");
			return false;
		}
		return true;
	}
	public boolean isStep2Section1SelectAnAdHeadingVisible() {
		return ActionHelper.isElementVisible(driver, selectAnAdType_section1_step2);
	}
	public boolean isListingHeadingVisible() {
		return !ActionHelper.getText(driver, listing_title_step2_section1).split("Listing Ad:")[1].isEmpty();
	}
	public boolean verifyAdHeadline(String pHeading) {
		return ActionHelper.getText(driver, ad_headline_caption).contains(pHeading);
	}
	public boolean verifyAdDesc(String pDesc) {
		return ActionHelper.getText(driver, ad_description_caption).contains(pDesc);
	}
	public boolean verifyAdURLIsCorrect() {
		String l_propUrl = ActionHelper.getAttribute(driver.findElement(By.xpath("//p[@id='ad_url_caption']/a")), "href");
//		ActionHelper.openUrlInNewTab(driver, l_propUrl);
//		boolean isValidUrl = ActionHelper.waitForElementToBeVisible(driver, website_prop_heading, 30);
//		ActionHelper.switchToOriginalWindow(driver);
		return !l_propUrl.isEmpty();
	}
	public boolean isSection2Checked() {
		return ActionHelper.isElementVisible(driver, step2_section2_checked);
	}
	public boolean isDefaultCitySelected() {
		return ActionHelper.isElementSelected(driver, fb_ad_cities);
	}
	public String getDefaultCity() {
		return ActionHelper.getText(driver, fb_ad_cities);
	}
	public boolean selectAdTaregetReach(String pAmount) {
		return ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, ad_plan_amount, pAmount));
	}
	public boolean clickOnNextStepButton() {
		return ActionHelper.Click(driver, next_button_section3);
	}
	public boolean verifyAdCitySection3(String pCity) {
		return pCity.contains(ActionHelper.getText(driver, ad_city_caption_section3).split("Ad City: ")[1]);
	}
	public boolean verifyAdReachSection3(String pAdAmount) {
		return ActionHelper.getText(driver, ad_reach_caption_section3).contains(pAdAmount+" per month");
	}
	public boolean isSection3Checked() {
		return ActionHelper.waitForElementToBeVisible(driver, section3_checked, 5);
	}
	public boolean isSection3EditButtonEnabled() {
		return ActionHelper.waitForElementToBeVisible(driver, edit_button_section3, 5);
	}
	public boolean isSection4Visible() {
		return ActionHelper.isElementVisible(driver, section4_heading);
	}
	public boolean verifyStartEndAndRenewalDate() throws ParseException {
		String lDate = ActionHelper.getText(driver, start_end_date_section4);
		String lRenew_date = ActionHelper.getText(driver, renew_text).split(". To disable")[0].split("Your ad will renew on ")[1];
		String[] lDate_array = lDate.split("Ad Duration: ")[1].split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date startDate = (Date) sdf.parseObject(lDate_array[0].trim());
		Date endDate = (Date) sdf.parseObject(lDate_array[1].trim());
		Date lRenewalDate = (Date) sdf.parseObject(lRenew_date);
		long diff = endDate.getTime() - startDate.getTime();
		long lDays = (diff / (1000*60*60))/24;
		long lRenwalDate_time = lRenewalDate.getTime() - startDate.getTime();
		long lRenewal_days = (lRenwalDate_time / (1000*60*60))/24;
		return lDays==30 && lRenewal_days==31;	
	}
	public boolean clickOnTermsAndCond() {
		return ActionHelper.Click(driver, ad_payment_confirmation);
	}
	public boolean clickOnPlaceAdButton() {
		return ActionHelper.Click(driver, fb_placead_callout);
	}
	public boolean isStep1SelectQuickAdHeading() {
		return ActionHelper.waitForElementToBeVisible(driver, select_quick_Ad_heading, 5);
	}
	public boolean isSlideShowVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, slideshow_Ad, 5);
	}
	public boolean clickOnSelectButtonStep1() {
		return ActionHelper.Click(driver, select_button_step1);
	}
	public String getFBAdTitleStep1() {
		return ActionHelper.getText(driver, fb_Ad_preview_title_step1);
	}
	public String getFBAdDescStep1() {
		return ActionHelper.getText(driver, fb_Ad_preview_desc_step1);
	}
	public boolean isCustomAdsHeadingDisplayed() {
		return ActionHelper.waitForElementToBeVisible(driver, custom_Ads_heading, 5);
	}
	public boolean isPromoteListingHeadingIsVisible() {
		boolean isVisible = false;
		if(ActionHelper.isElementVisible(driver, promote_listing_heading)) {
			isVisible = ActionHelper.getText(driver, promote_listing_heading).equalsIgnoreCase("Promote a Listing");
		}
		return isVisible;
	}
	public boolean isCorrectTextDisplayed() {
		boolean isDisplayed = false;
		if(ActionHelper.isElementVisible(driver, create_Ad_box_text)) {
			isDisplayed = ActionHelper.getText(driver, create_Ad_box_text).replace("\n", " ").contains("Get more exposure, interest, and engagement for your listing");
		}
		return isDisplayed;
	}
	public boolean isHomeIconDisplayedInCreateAdBox() {
		return ActionHelper.waitForElementToBeVisible(driver, home_icon_create_Ad_box, 5);
	}
	public boolean isCreateCustomAdButtonIsDisplayed() {
		return ActionHelper.isElementVisible(driver, customize_button);
	}
	public boolean isCreateCustomBoxBouncing() {
		ActionHelper.MouseHoverOnElement(driver, create_custom_Ad_modal);
		return create_custom_Ad_modal.getCssValue("animation-name").equalsIgnoreCase("bounce-1");
	}
	public boolean isSelectQuickAdsHeadingDisplayed() {
		return ActionHelper.waitForElementToBeVisible(driver, quick_Ads_heading, 5);
	}
	public boolean isListingQuickAdsHeadingDisplayed() {
		return ActionHelper.waitForElementToBeVisible(driver, listing_quick_ad_heading, 5);
	}
	public boolean isListingAddressIsDisplayedInQuickAdBox() {
		return !ActionHelper.getText(driver, listing_address).isEmpty();
	}
	public boolean isHotPropertyHeadingVisible() {
		return ActionHelper.getText(driver, hot_properties_heading).contains("Hot Properties");
	}
	public boolean isQuickAdThumbnailVisible() {
		return ActionHelper.getAttribute(img_thumb_quick_Ad, "src").contains("icon.jpg");
	}
	public boolean isQuickAdsDescriptionDisplayed() {
		return ActionHelper.getText(driver, quick_Ad_Desc).contains("Just Listed! Click for more details and photos!");
	}
	public String getQuickAdDomain() {
		return ActionHelper.getText(driver, quick_Ad_Domain).toLowerCase();
	}
	public boolean isQuickAdTitleVisible() {
		return ActionHelper.getText(driver, quick_Ad_title).replace("\n", " ").contains("Hot Property In");
	}
	public boolean isQuickAdSelectButtonVisible() {
		return ActionHelper.isElementVisible(driver, select_button_quick_ad_box);
	}

	 public boolean verifyAdSlideShowIsWorking() {
		 ActionHelper.RefreshPage(driver);
		 ActionHelper.staticWait(5);
		 boolean isSlideShowWorking = false;
		 List<WebElement> elements_list2 = ActionHelper.getListOfElementByXpath(driver, quick_Ad_Slide_show_images);
		 if(elements_list2.size()>0) {
			 isSlideShowWorking = ActionHelper.MouseHoverOnElement(driver, quick_Ad_play_icon);
			 if(isSlideShowWorking) {
				String image_path_01 = ActionHelper.getAttribute(elements_list2.get(0), "src");
				ActionHelper.staticWait(2);
				elements_list2 = ActionHelper.getListOfElementByXpath(driver, quick_Ad_Slide_show_images);
				String image_path_02 = ActionHelper.getAttribute(elements_list2.get(0), "src");
				isSlideShowWorking = !image_path_01.equalsIgnoreCase(image_path_02);
			 }
		 }
		 return isSlideShowWorking; 
	 }
	 public String getListingAddress() {
		 return ActionHelper.getText(driver, listing_address_section1_step2);
	 }
	 public boolean clickOnEditButtonStep2Section1() {
		 return ActionHelper.Click(driver, edit_button_section1_step2);
	 }
}
