/**
 * 
 */
package com.zurple.backoffice.ads;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOGenericAlerts;
import resources.alerts.zurple.backoffice.ZBOSelectListingAlert;
import resources.forms.zurple.backoffice.ZBOAddCreditCardForm;
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
	@FindBy(xpath="//a[@id='promote_adtype_selector']/descendant::div[@class='goalbox_body adtype_body']")
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
	@FindBy(xpath="//div[@class='slider  Zurple_ListingAds slick-initialized slick-slider']/descendant::div[@class='fb_ad_preview_details Mpreview']")
	WebElement listing_quick_Ad_Desc;
	@FindBy(xpath="//div[@class='fb_ad_preview_frame pull-left']/descendant::div[@class='fb_ad_preview_domain']")
	WebElement quick_Ad_Domain;
	@FindBy(xpath="//div[@class='slider  Zurple_ListingAds slick-initialized slick-slider']/descendant::div[@class='fb_ad_preview_title']/a")
	WebElement quick_Ad_title;
	@FindBy(xpath="//div[@class='ad_outerbox']/a[text()='Select']")
	WebElement select_button_quick_ad_box;
	@FindBy(xpath="//div[@class='ad_outerbox']/descendant::i[@class='fa fa-play']")
	WebElement quick_Ad_play_icon;
	String quick_Ad_Slide_show_images = "//div[@class='ad_outerbox']/descendant::div[@class='slide-image']/img";
	String list_of_select_buttons = "//section[@class='zurp_ads_cont']/descendant::a[text()='Select']";
	String list_of_listing_address = "//section[@class='zurp_ads_cont']/descendant::p";
	
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
	@FindBy(xpath="//div[@class='social_logos_preview']/img[@src='img/social_ads/fb_active.png']")
	WebElement facebook_logo_step2;
	String facebook_slider_images = "//div[@id='slider_fb']/descendant::div[@class='slide-image current']/img";
	
	//Instagram Ad preview Step 2
	@FindBy(xpath="//div[@id='instagram_ad_pewview']/descendant::div[@class='post_preview_text']")
	WebElement insta_ad_preview_text;
	@FindBy(xpath="//div[@id='instagram_ad_pewview']/descendant::div/a[contains(text(),'Learn More')]")
	WebElement insta_ad_learnmore_button;
	@FindBy(xpath="//div[@id='instagram_ad_pewview']/descendant::div[@class='playicon_slide']")
	WebElement insta_ad_play_icon;
	@FindBy(xpath="//div[@class='social_logos_preview']/img[@src='img/social_ads/insta_active.png']")
	WebElement instagram_logo_step2;
	String instagram_slider_images = "//div[@id='slider_ins']/descendant::div[@class='slide-image current']/img";

	
	//Section 1 Step 2
	@FindBy(xpath="//h5[@class='inner_stephead' and text()='Select an Ad Type ']")
	WebElement selectAnAdType_section1_step2;
	@FindBy(xpath="//p[@style='margin-left: 13%;']")
	WebElement listing_title_step2_section1;
	@FindBy(xpath="//div[@id='form-submit_btn']/button[text()='Select']")
	WebElement select_section_button;
	@FindBy(xpath="//a[text()='Edit' and contains(@href,'step-one')]")
	WebElement edit_button_section1;
	@FindBy(xpath="//h5[@class='inner_stephead']/i[@class='far fa-check-circle checkicon zurp_checked']/following-sibling::strong[contains(text(), '1')]")
	WebElement step2_section1_checked;
	
	
	//Step 2 Section 2 verifications
	@FindBy(id="ad_headline_caption")
	WebElement ad_headline_caption;
	@FindBy(id="ad_description_caption")
	WebElement ad_description_caption;
	@FindBy(xpath="//p[@id='ad_url_caption']/a")
	WebElement ad_url_caption;
	@FindBy(xpath="//div[@id='propdetail']/descendant::h2[not(contains(@class,'zurple-tinted-text'))]")
	WebElement website_prop_heading;
	@FindBy(xpath="//h5[@class='inner_stephead']/i[@class='far fa-check-circle checkicon zurp_checked']/following-sibling::strong[contains(text(), '2')]")
	WebElement step2_section2_checked;
	@FindBy(xpath="//h5[@class='inner_stephead']/strong[contains(text(),'Step 2:')]")
	WebElement step2_section2;
	String headings_list = "//h5[@class='inner_stephead']";
	@FindBy(xpath="//a[text()='Edit' and contains(@href,'step-two')]")
	WebElement edit_button_section2;
	@FindBy(xpath="//div[@id='form-element-site']/label[text()='Select Destination Site:']")
	WebElement selectDestination_label;
	String step2_checkbox = "//h5[@class='inner_stephead']/i[@class='far fa-check-circle checkicon zurp_checked']/ancestor::h5";
	@FindBy(id="desc_count")
	WebElement description_count_left;
	@FindBy(id="title_count")
	WebElement headline_count_left;
	
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
	@FindBy(xpath="//div[@id='step_3_section']/h5")
	WebElement step3_heading;
	@FindBy(xpath="//ul[@class='select2-selection__rendered']/li/input")
	WebElement fb_Ad_city_input;
	@FindBy(xpath="//span[@class='select2-selection__choice__remove']")
	WebElement remove_city_button;
	@FindBy(xpath="//button[contains(@class,'btn-success') and @onclick='set_plan(3)']")
	WebElement medium_reach_selected;
	String select_city_dop_options = "//*[@id='select2-fb_ad_cities-results']/li[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	// "//button[contains(@class,'btn-success') and @onclick='set_plan(3)']"
	String select_plan_button = "//button[contains(@class,'btn btn-large ad-plan-paid') and @onclick='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String select_plan_buttons_list = "//div[@class='form-group mt-2 flex_layout']/button";
	
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
	@FindBy(xpath="//h5[@class='inner_stephead']/strong[contains(text(),'Step 4:')]")
	WebElement step4_heading;
	@FindBy(xpath="//p[@id='terms_section_copy']/a")
	WebElement terms_and_cond_link;
	@FindBy(id="testing_ad")
	WebElement testing_ad_checkbox;
	
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
	
	//Bottom Progress bar
	@FindBy(xpath="//div[@class='statusbar_box zurple selected_zurp' and @title='Step 1 of 4']")
	WebElement step14_bottom_progress;
	@FindBy(xpath="//div[@class='statusbar_box zurple selected_zurp' and @title='Step 2 of 4']")
	WebElement step24_bottom_progress;
	@FindBy(xpath="//div[contains(@class,'selected_zurp') and @title='Step 3 of 4']")
	WebElement step34_bottom_progress;
	
	//Buyer Leads Ads
	@FindBy(xpath="//a[@id='promote_adtype_selector_bl']/descendant::img[contains(@src,'BL')]")
	WebElement buyer_lead_box;
	String quick_buyer_ads_list = "//section[@class='zurp_ads_cont']/div[@class='slider  Zurp_BuyerAds slick-initialized slick-slider']/descendant::div[contains(@class,'slick-slide')]";
	@FindBy(xpath="//a[@id='promote_adtype_selector_bl']/descendant::h2[text()=' Find Buyer Leads ']")
	WebElement find_buyer_leads_heading;
	@FindBy(xpath="//a[@id='promote_adtype_selector_bl']/descendant::div[@class='goalbox_body adtype_body']")
	WebElement find_buyer_leads_text;
	@FindBy(xpath="//a[@id='promote_adtype_selector_bl']/descendant::button")
	WebElement create_custom_ad_button;
	String buyer_lead_quick_Ads_heading_list = "//div[@id='buyerleadads_cont_zurp']/descendant::h4[text()='Buyer Lead  Quick Ad']";
	String buyer_lead_quick_Ads_desc = "//div[@id='buyerleadads_cont_zurp']/descendant::div[@class='fb_ad_preview_details Mpreview']";
	String buyer_lead_quick_select_list = "//div[@id='buyerleadads_cont_zurp']/descendant::a[text()='Select']";
	@FindBy(xpath="//*[@class='goalBox mtb-8 buyerlead_zurp']")
	WebElement buyer_lead_custom_Ad_modal;
	String buyer_lead_quick_Ad_slide_show = "//div[@class='slider  Zurp_BuyerAds slick-initialized slick-slider']/descendant::div[@class='slide-image current']/img";
	@FindBy(xpath="//div[@class='slider  Zurp_BuyerAds slick-initialized slick-slider']/descendant::div[@class='playicon_slide'][1]")
	WebElement buyer_lead_quick_Ad_play_icon;
	@FindBy(xpath="//div[@id='buyerleadads_cont_zurp']/descendant::button[@class='slick-next slick-arrow']")
	WebElement quick_ad_slide_arrow;
	private ZBOSelectListingAlert selectListingAlert;
	private ZBOGenericAlerts alert;
	private String listing_address_value = "";
	
	@FindBy(id="cta_bl_ad")
	WebElement createBuyerLeadAdButton;
	
	@FindBy(id="cta_listad")
	WebElement createListingAdButton;
	
	@FindBy(id="confirm_payment_btn")
	WebElement confirm_payment_btn;
	
	String ad_slider = "//div[@class='adpreview_box']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String ad_date = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::span[@class='addate_cap']";
	String ad_price = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::span[@class='adprice_cap']";
	String ad_location = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::span[@class='adlocation_cap']";
	String ad_listingtype = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::span[@class='listing_addr']";
	String ad_headline = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::h4[@class='listad_headline']";
	String first_row_ad_starting_day = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::div[@class='ad_datebox']/descendant::span[@class='ad_date']";
	String first_row_ad_starting_month = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::div[@class='ad_datebox']/descendant::span[@class='ad_month']";
	String first_row_ad_starting_year = "//tr[@role='row']/descendant::div[@id='slider_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/ancestor::tr/descendant::div[@class='ad_datebox']/descendant::span[@class='ad_yaer']";
	
	private ZBOAddCreditCardForm addCreditCardForm;
	
	public ZBOCreateAdPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setSelectListingAlert();
		setAlert();
		PageFactory.initElements(driver, this);
	}

	public ZBOSelectListingAlert getSelectListingAlert() {
		return selectListingAlert;
	}

	public void setSelectListingAlert() {
		this.selectListingAlert = new ZBOSelectListingAlert(driver);
	}

	public void setCreditCardForm() {
		addCreditCardForm = new ZBOAddCreditCardForm(driver);
	}
	public ZBOAddCreditCardForm getCreditCardForm() {
		return addCreditCardForm;
	}
	public ZBOGenericAlerts getAlert() {
		return alert;
	}

	public void setAlert() {
		this.alert = new ZBOGenericAlerts(driver);
	}	
	public String getListingAddressValue() {
		return listing_address_value;
	}
	public boolean verifyAdDetailsByAdIdInAdsOverviewPage(String pAdId, String pVerificationType, String pData) {
		boolean isSuccess = false;		
		switch(pVerificationType){
			case "date":
				isSuccess = verifyRenewalDate(pAdId);
				break;
			case "price":
				String p = ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, ad_price, pAdId));
				isSuccess = p.contains(pData) || pData.contains(p);
				break;
			case "location":
				String loc = "";
				isSuccess = loc.contains(pData) || pData.contains(loc);
				break;
			case "duration":
				isSuccess = verifyStartingEndingDate(pAdId);
				break;
			case "status":
				isSuccess = verifyAdStatus(pAdId).contains(pData);
				break;
			case "listingType":
				isSuccess = ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, ad_listingtype, pAdId)).contains(pData);
				break;
			case "headline":
				isSuccess = ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, ad_headline, pAdId)).contains(pData);
				break;
		}
		return isSuccess;
		
	}
	private void setListingAddressValue(String pValue) {
		listing_address_value = pValue;
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
			pSite = pSite.replace("http://www.", "");
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
//		if(!ActionHelper.isElementVisible(driver, fb_ad_play_icon)) {
//			AutomationLogger.error("Facebook ad slide show play button is not displayed..");
//			return false;
//		}
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
		String lgetDesc = ActionHelper.getText(driver, ad_description_caption).replace("\n", "").replace("Just Listed!", " Just Listed!");
		return lgetDesc.contains(pDesc);
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
		return ActionHelper.getText(driver, ad_city_caption_section3).split("Ad City: ")[1].contains(pCity);
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
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = (Date) sdf.parseObject(lDate_array[0].trim());
		Date endDate = (Date) sdf.parseObject(lDate_array[1].trim());
		Date lRenewalDate = (Date) sdf.parseObject(lRenew_date);
		long diff = endDate.getTime() - startDate.getTime();
		long lDays = TimeUnit.MILLISECONDS.toDays(diff);//(diff / (1000*60*60*24));
		long lRenwalDate_time = lRenewalDate.getTime() - startDate.getTime();
		long lRenewal_days = TimeUnit.MILLISECONDS.toDays(lRenwalDate_time);//(lRenwalDate_time / (1000*60*60*24));
		return lDays==30 && lRenewal_days==31;	
	}
	public boolean clickOnTermsAndCond() {
		return ActionHelper.checkUncheckInputBox(driver, ad_payment_confirmation, true);
	}
	public boolean verifyIsTermsAndConditionCheckBoxChecked() {
		return ActionHelper.isElementSelected(driver, ad_payment_confirmation);
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
		return ActionHelper.getText(driver, listing_quick_Ad_Desc).contains("Just Listed! Click for more details and photos!");
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
	 public boolean isCustomizeAdDetailsHeadingIsVisible() {
		 boolean isHeadingVisible = false;
		 List<WebElement> list_of_headings = ActionHelper.getListOfElementByXpath(driver, headings_list);
		 for(WebElement element: list_of_headings) {
			 if(ActionHelper.getText(driver, element).contains("Customize Ad Details")) {
				 isHeadingVisible = true;
				 break;
			 }
		 }
		 return isHeadingVisible;
	 }
	 public boolean typeAdHeading(String pStringToType) {
		 return ActionHelper.Type(driver, driver.findElement(By.id("fb_ad_title")), pStringToType);
	 } public boolean clearAndTypeAdHeading(String pStringToType) {
		 return ActionHelper.ClearAndType(driver, driver.findElement(By.id("fb_ad_title")), pStringToType);
	 }
	 public boolean clickOnEditButtonStep2() {
		 return ActionHelper.Click(driver, edit_button_section2);
	 }
	 public boolean typeAdDescription(String pStringToType) {
		return ActionHelper.Type(driver, driver.findElement(By.id("fb_ad_details")), pStringToType);
	 }
	 public boolean verifyFBAdPreviewTitleIsUpdated(String pStringToVerify) {
		 boolean isPreviewUpdated = false;
		 if(ActionHelper.getText(driver, fb_ad_preview_title).contains(pStringToVerify.trim())) {
			 isPreviewUpdated = true;
		 }
		 return isPreviewUpdated;
	 }
	 public boolean verifyFBAdPreviewDescIsUpdated(String pStringToVerify) {
		 boolean isPreviewUpdated = false;
		 if(ActionHelper.getText(driver, fb_ad_preview_desc).contains(pStringToVerify.trim())) {
			 isPreviewUpdated = true;
		 }
		 return isPreviewUpdated;
	 }
	 public boolean verifyInstaAdPreviewDescIsUpdated(String pStringToVerify) {
		 boolean isPreviewUpdated = false;
		 if(ActionHelper.getText(driver, insta_ad_preview_text).contains(pStringToVerify.trim())) {
			 isPreviewUpdated = true;
		 }
		 return isPreviewUpdated;
	 }
	 public boolean isStep14BottomProgressBarIsChecked() {
		 return ActionHelper.isElementVisible(driver, step14_bottom_progress);
	 }
	 public boolean verifyInstaAndFacebookLogoAreDisplayed() {
		 boolean isVisible = false;
		 if(ActionHelper.waitForElementToBeVisible(driver, facebook_logo_step2, 0)) {
			 isVisible = ActionHelper.isElementVisible(driver, instagram_logo_step2);
		 }
		 return isVisible;
	 }
	 public boolean verifyFacebookPreviewSlideShowIsWorkingOnStep2() {
		 return verifyAdSlideShowIsWorkingOnStep2AdsPreview(facebook_slider_images, fb_ad_play_icon);
	 }
	 public boolean verifyInstaPreviewSlideShowIsWorkingOnStep2() {
		 return verifyAdSlideShowIsWorkingOnStep2AdsPreview(instagram_slider_images, insta_ad_play_icon);
	 }
	 public boolean isSelectDestinationLabelVisible() {
		 return ActionHelper.isElementVisible(driver, selectDestination_label);
	 }
	 public boolean verifyUrlOnStep2Section2AfterClickingSelect(String pUrl) {
		 return ActionHelper.getText(driver, ad_url_caption).contains(pUrl);
	 }
	 public boolean verifyStep3Heading(String pHeading) {
		 return ActionHelper.getText(driver, step3_heading).contains(pHeading);
	 }
	 public boolean isStep24BottomProgressBarIsChecked() {
		 return ActionHelper.isElementVisible(driver, step24_bottom_progress);
	 }
	 public boolean isStepCheckBoxIsChecked(String pStep) {
		 boolean isFound= false;
		 List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, "//h5[@class='inner_stephead']/i[@class='far fa-check-circle checkicon zurp_checked']/ancestor::h5");
		 for(WebElement element: list_element) {
			 if(ActionHelper.getText(driver, element).contains(pStep)) {
				 isFound = true;
				 break;
			 }
		 }
		 return isFound;
	 }
	 public boolean typeAndSelectCity(String pCity) {
		 return ActionHelper.typeAndSelect(driver, fb_Ad_city_input, select_city_dop_options, pCity);
	 }
	 public boolean clickOnRemoveCityButton() {
		 return ActionHelper.Click(driver, remove_city_button);
	 }
	 public boolean isMediumReachSelectedByDefault() {
		 return ActionHelper.isElementVisible(driver, medium_reach_selected);
	 }
	 public boolean selectPlan(String pPlan) {
		 boolean isPlanSelected = false;
		 switch(pPlan) {
		 case "$120":
			 isPlanSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, select_plan_button,"set_plan(1)"));
			 break;
		 case "$160":
			 isPlanSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, select_plan_button,"set_plan(2)"));
			 break;
		 case "$240":
			 isPlanSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, select_plan_button,"set_plan(3)"));
			 break;
		 case "$320":
			 isPlanSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, select_plan_button,"set_plan(4)"));
			 break;
		 case "$400":
			 isPlanSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, select_plan_button,"set_plan(5)"));
			 break;
		 }
		 return isPlanSelected;
	 }
	 public boolean isStep3of4IsEnabled() {
		 return ActionHelper.waitForElementToBeVisible(driver, step34_bottom_progress, 10);
	 }
	 public boolean allThePlansAreDisplayed() {
		 boolean allButtonsAreDisplayed = false;
		 List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver,select_plan_buttons_list );
		 if(list_element.size()==5) {
			 for(WebElement element: list_element) {
				 allButtonsAreDisplayed = ActionHelper.isElementVisible(driver, element);
				 if(!allButtonsAreDisplayed) {
					 break;
				 }
			 }
		 }
		 return allButtonsAreDisplayed;
	 }
	 public boolean verifyStep4IsVisible() {
		 return ActionHelper.isElementVisible(driver, step4_heading);
	 }
	 public boolean clickOnStep1EditButton() {
		 return ActionHelper.Click(driver, edit_button_section1);
	 }
	 public boolean clickOnTermsAndCondLink() {
		 boolean isClicked = false;
		 if(ActionHelper.Click(driver, terms_and_cond_link)) {
			ActionHelper.switchToSecondWindow(driver);
			isClicked = driver.getCurrentUrl().contains("social-ad-terms-and-conditions");
		 }
		 return isClicked;
	 }
	 public boolean clickOnStep3EditButton() {
		 return ActionHelper.Click(driver, edit_button_section3);
	 }
	 public boolean isTestingAdAlreadyChecked() {
		 return ActionHelper.isElementSelected(driver, testing_ad_checkbox);
	 }
	 public boolean clickOnTestingAdCheckBox() {
		 return ActionHelper.checkUncheckInputBox(driver, testing_ad_checkbox, true);
	 }
	 public boolean clickOnQuickAdsSelectButton() {
		 List<WebElement> list_select_button = ActionHelper.getListOfElementByXpath(driver, list_of_select_buttons);
		 List<WebElement> list_of_Address = ActionHelper.getListOfElementByXpath(driver, list_of_listing_address);
		 int index = 0;
		 if(list_select_button.size()>3) {
			 index =  generateRandomInt(3);
		 }else {
			 index = generateRandomInt(list_select_button.size());
		 }
		 setListingAddressValue(ActionHelper.getText(driver, list_of_Address.get(index)));
		 return ActionHelper.Click(driver, list_select_button.get(index));
	 }
	 public String getAdHeadlineStep2() {
		 return ActionHelper.getText(driver, ad_headline_caption);
	 }
	 public String getAdDescStep2() {
		 String lgetDesc = ActionHelper.getText(driver, ad_description_caption);
		 return lgetDesc;
	 }
	 public boolean clickOnPlaceAdButtonAndVerifyButtonGetsDisabled() {
			boolean isDisabled = false;
			if(ActionHelper.sendSpecialKeys(driver, Keys.ENTER)) {
				isDisabled = ActionHelper.waitForElementToBeLocated(driver, "//button[@id='fb_placead_callout' and @disabled='disabled']", 10);
			}
			return isDisabled; 
		}
	 public boolean isBuyerLeadAdBoxVisible() {
		 return ActionHelper.waitForElementToBeVisible(driver, buyer_lead_box,30);
	 }
	 public boolean isBuyerLeadAdsHeadingVisible() {
		 return ActionHelper.isElementVisible(driver, find_buyer_leads_heading);
	 }
	 public boolean isBuyerLeadCustomAdTextVisible() {
		 return ActionHelper.getText(driver, find_buyer_leads_text).contains("Target");
	 }
	 public boolean isCustomAdButtonVisible() {
		 return ActionHelper.isElementVisible(driver, create_custom_ad_button);
	 }
	 public boolean verifyBuyerLeadsAdHeadingCount() {
		 return ActionHelper.getListOfElementByXpath(driver, buyer_lead_quick_Ads_heading_list).size()==4;
	 }
	 public boolean verifyBuyerLeadQuickAdsDesc(String pDesc) {
		 boolean isFound = false;
		 List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, buyer_lead_quick_Ads_desc);
		 for(WebElement element: list) {
			 if(ActionHelper.getText(driver, element).contains(pDesc)) {
				 isFound = true;
				 break;
			 }
		 }
		 return isFound;
	 }
	 public boolean verifySelectButton() {
		 return ActionHelper.getListOfElementByXpath(driver, buyer_lead_quick_select_list).size()==4;
	 }
	 public boolean verifySelectButtonIsVisible() {
		 boolean isVisible = true;
		 List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, buyer_lead_quick_select_list);
		 for(int i=0;i<3;i++) {
			 if(!ActionHelper.isElementVisible(driver, list.get(i))) {
				 isVisible = false;
				 break;
			 }
		 }
		 return isVisible;
	 }
	 //Checking slider ads are displayed and 4th one invisible
	 public boolean isQuickBuyerAdsDisplayed() {
		 boolean isDisplayed = true;
		 int counter = 0;
		 List<WebElement> list_quick = ActionHelper.getListOfElementByXpath(driver, quick_buyer_ads_list);
		 if(list_quick.size()==4) {
			 for(WebElement element: list_quick) {
				 if(!ActionHelper.isElementVisible(driver, element)) {
					 isDisplayed = false;
				 }
			 }
		 }
		 return isDisplayed;
	 }
	 public boolean isBuyerLeadCreateCustomBoxBouncing() {
		 ActionHelper.MouseHoverOnElement(driver, buyer_lead_custom_Ad_modal);
		 return buyer_lead_custom_Ad_modal.getCssValue("animation-name").equalsIgnoreCase("bounce-1");
	 }
	 public boolean verifyBuyerLeadQuickAdSlideShowIsWorking() {
		 return verifyAdSlideShowIsWorkingOnStep2AdsPreview(buyer_lead_quick_Ad_slide_show, buyer_lead_quick_Ad_play_icon); 
	 }
	 public boolean clickCustomAdButtonBuyerLeadAd() {
		 return ActionHelper.Click(driver, create_custom_ad_button);
	 }	
	 public boolean isSection1Checked() {
			return ActionHelper.isElementVisible(driver, step2_section1_checked);
	}
	 public boolean isBuyerLeadHeadingVisible() {
		 return !ActionHelper.getText(driver, listing_title_step2_section1).split("Buyer Lead Ad:")[1].isEmpty();
	 }
	 public String getDescriptionLeftCount() {
		 return ActionHelper.getText(driver, description_count_left);
	 }
	 public String geHeadlineCharacterLeftCount() {
		 return ActionHelper.getText(driver, headline_count_left);
	 }
	 public boolean clickSelectButtonForQuickAds() {
		 int quick_Ad = generateRandomInt(4);
		 boolean isClicked = false;
		 if(quick_Ad==3) {
			 if(ActionHelper.Click(driver, quick_ad_slide_arrow)) {
				 isClicked = ActionHelper.Click(driver, ActionHelper.getListOfElementByXpath(driver, buyer_lead_quick_select_list).get(quick_Ad-1));
			 }
		 }else {
			 isClicked = ActionHelper.Click(driver, ActionHelper.getListOfElementByXpath(driver, buyer_lead_quick_select_list).get(quick_Ad));
		 }
		 return isClicked;
	 }
	
	 private boolean verifyAdSlideShowIsWorkingOnStep2AdsPreview(String pSlideShowImagesPath, WebElement pPlayIcon) {
		 ActionHelper.staticWait(5);
		 boolean isSlideShowWorking = false;
		 List<WebElement> elements_list2 = ActionHelper.getListOfElementByXpath(driver, pSlideShowImagesPath);
		 if(elements_list2.size()>0) {
			 isSlideShowWorking = ActionHelper.MouseHoverOnElement(driver, pPlayIcon);
			 if(isSlideShowWorking) {
				String image_path_01 = ActionHelper.getAttribute(elements_list2.get(0), "src");
				ActionHelper.staticWait(2);
				elements_list2 = ActionHelper.getListOfElementByXpath(driver, pSlideShowImagesPath);
				String image_path_02 = ActionHelper.getAttribute(elements_list2.get(0), "src");
				isSlideShowWorking = !image_path_01.equalsIgnoreCase(image_path_02);
			 }
		 }
		 return isSlideShowWorking; 
	 }
	 
	 private String getStartingEndingDate(boolean pIsStarting, String pAdId){
		 String l_consolidatedStartingDate = "";
		 if(pIsStarting) {
			String lDay = ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, "//tr[@role='row']/descendant::div[@id='slider_"+pAdId+"']/ancestor::tr/descendant::div[@class='ad_datebox']/descendant::span[@class='ad_date']").get(0));
			String lMonth = ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, "//tr[@role='row']/descendant::div[@id='slider_"+pAdId+"']/ancestor::tr/descendant::div[@class='ad_datebox']/descendant::span[@class='ad_month']").get(0));
			String lYear = ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, "//tr[@role='row']/descendant::div[@id='slider_"+pAdId+"']/ancestor::tr/descendant::div[@class='ad_datebox']/descendant::span[@class='ad_yaer']").get(0));
			l_consolidatedStartingDate = getMonth(lMonth)+"/"+lDay+"/"+lYear;
		 } else {
				String lDay = ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, "//tr[@role='row']/descendant::div[@id='slider_"+pAdId+"']/ancestor::tr/descendant::div[@class='ad_datebox']/descendant::span[@class='ad_date']").get(1));
				String lMonth = ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, "//tr[@role='row']/descendant::div[@id='slider_"+pAdId+"']/ancestor::tr/descendant::div[@class='ad_datebox']/descendant::span[@class='ad_month']").get(1));
				String lYear = ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, "//tr[@role='row']/descendant::div[@id='slider_"+pAdId+"']/ancestor::tr/descendant::div[@class='ad_datebox']/descendant::span[@class='ad_yaer']").get(1));
				l_consolidatedStartingDate = getMonth(lMonth)+"/"+lDay+"/"+lYear;
		 }
		 
//		 String lDay = pIsStarting?ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, first_row_ad_starting_day, pAdId)):ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, first_row_ad_starting_day, pAdId));
//		 String lMonth = pIsStarting?ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, first_row_ad_starting_month, pAdId)):ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, first_row_ad_starting_month, pAdId));
//		 String lYear = pIsStarting?ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, first_row_ad_starting_year, pAdId)):ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, first_row_ad_starting_year, pAdId));
		 return l_consolidatedStartingDate;
	 }
	 private int getMonth(String pMonth){
		 Date date = new Date();
		 try {
			 date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(pMonth);
		 } catch (ParseException e) {
			AutomationLogger.error("Error in date format");
			AutomationLogger.error(e.getLocalizedMessage());
		 }
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 int month = cal.get(Calendar.MONTH)+1;
		 return month;
	 }
	 
	 public boolean verifyStartingEndingDate(String pAdId){
		 String l_consolidatedStartingDate = getStartingEndingDate(true, pAdId);
		 String l_consolidatedEndingDate = getStartingEndingDate(false, pAdId);

		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 Date startDate = null;
		 Date endDate = null;
		 try {
			 startDate = (Date) sdf.parseObject(l_consolidatedStartingDate);
			 endDate = (Date) sdf.parseObject(l_consolidatedEndingDate);
		 } catch (ParseException e) {
			 // TODO Auto-generated catch block
			 AutomationLogger.error("Error in date format");
			 AutomationLogger.error(e.getLocalizedMessage());
		 }
		 long diff = endDate.getTime() - startDate.getTime();
		 long lDays = TimeUnit.MILLISECONDS.toDays(diff);//(diff / (1000*60*60*24));
		 return lDays==30;	
	 }
	 public boolean verifyRenewalDate(String pAdId){
		 String l_consolidatedStartingDate = getStartingEndingDate(true, pAdId);
		 SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		 Date renewalDate = null, startDate = null;
		 try {
			 renewalDate = (Date) sdf.parseObject(getRenewalDate(pAdId));
			 startDate = (Date) sdf.parseObject(l_consolidatedStartingDate);
		 } catch (ParseException e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 }
		 long diff = renewalDate.getTime() - startDate.getTime();
		 long lDays = TimeUnit.MILLISECONDS.toDays(diff);//(diff / (1000*60*60*24));
		 return lDays==31;
	 }
	 public boolean isBuyerLeadAdButtonDisplayed() {
		 return ActionHelper.isElementDisplayed(driver, createBuyerLeadAdButton);
	 }
	 public boolean clickOnCreateBuyerLeadButton() {
		 return ActionHelper.Click(driver, createBuyerLeadAdButton);
	 }
	 public boolean isCreateCustomAdButtonDisplayed() {
		 return ActionHelper.isElementDisplayed(driver, customize_button);
	 }
	 public boolean isListingAdButtonDisplayed() {
		 return ActionHelper.isElementDisplayed(driver, createListingAdButton);
	 }
	 public boolean clickOnCreateListingAdButton() {
		 return ActionHelper.Click(driver, createListingAdButton);
	 }
	 public boolean isScrolledSuccessful(String pButtonType) {
		 long l_old_value = ActionHelper.getVerticlePixels(driver);
		 if(pButtonType.equalsIgnoreCase("Buyer")) {
			 clickOnCreateBuyerLeadButton();
		 }else {
			 clickOnCreateListingAdButton();
		 }
		 long l_new_value = ActionHelper.getVerticlePixels(driver);
		 return l_old_value!=l_new_value;
		 
	 }
	 public boolean clickOnConfirmPaymentButton() {
		 return ActionHelper.Click(driver, confirm_payment_btn);
	 }
	 private String getRenewalDate(String pAdId) {
		 String l_renewalDATE = "";
		 l_renewalDATE = ActionHelper.getText(driver, ActionHelper.getDynamicElement(driver, ad_date, pAdId));
		 return l_renewalDATE;
	 }
	 private String verifyAdStatus(String pAdId) {
		 return ActionHelper.getText(driver, ActionHelper.getListOfElementByXpath(driver, "//tr[@role='row']/descendant::div[@id='slider_"+pAdId+"']/ancestor::tr/descendant::td[@class='td_center']").get(3));
	 }
	 
}
