/**
 * 
 */
package com.zurple.backoffice.ads;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOSelectListingAlert;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOCreateAdPage extends Page{
	
	@FindBy(xpath="//h2[text()='Create Ad']")
	WebElement createAd_heading;
	
	@FindBy(xpath="//strong[contains(text(),'Step 1:')]")
	WebElement step1_strong;
	
	@FindBy(xpath="//a[@id='promote_adtype_selector']/descendant::button")
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
	public boolean isStep2HeadingVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, step2_strong, 5);
	}
	public boolean isAdHeadingPopulated() {
		return !ActionHelper.getText(driver, ad_title_input).isEmpty();
	}
	public boolean isAdDescPopulated() {
		return !ActionHelper.getText(driver, fb_ad_desc).isEmpty();
	}
	public boolean selectSite(String pSite) {
		boolean isSlected = true;
		if(!pSite.isEmpty()) {
			isSlected = ActionHelper.selectDropDownOption(driver, site_dropdown, "", pSite);
		}
		return isSlected;
	}
	public boolean clickOnSelectButton() {
		return ActionHelper.Click(driver, select_button);
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
}
