/**
 * 
 */
package com.z57.propertypulse;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class PPCreateAdPage extends Page{
	
	@FindBy(xpath="//h1[@class='z57-theme-page-topic']")
	WebElement pageTitle;
	
	@FindBy(xpath="//div[@id='fb_ad_preview_url']/a")
	WebElement fbPreviewLink;
	
	@FindBy(id="fb_ad_title")
	WebElement fbAdTitle;
	
	@FindBy(id="fb_ad_details")
	WebElement fbAdDetails;
	
	@FindBy(id="fb_ad_next_step")
	WebElement next_button;
	
	@FindBy(id="fb_ad_zip_codes")
	WebElement fbAdZipCodes;
	
	@FindBy(id="ad_payment_confirmation")
	WebElement adPaymentConfirmation_checkbox;
	
	String step2_heading = "//h2[text()='Step 2 - Select Ad Visibility Options & Placement']";
	String step3_heading = "//h2[text()='Step 3 - Place Order']";
	
	@FindBy(xpath="//input[@type='checkbox' and @name='fb_test_ad']")
	WebElement fbTestAd_checkbox;
	
	@FindBy(id="fb_ad_select_listing")
	WebElement select_listing_dropdown;
	
	@FindBy(xpath="//a[text()='Place Ad' and @data-lsid='cma1']")
	WebElement place_ad_button_1;
	
	@FindBy(id="fb_ad_select_ad_format")
	WebElement adFormat_dropDown;
	
	@FindBy(id="fb_ad_edit_url")
	WebElement editUrl_button;
	
	@FindBy(id="fb_ad_url")
	WebElement url_input;
	
	@FindBy(id="fb_ad_select_ad_format")
	WebElement adformat_dropdown;
	
	@FindBy(id="fb_ad_reach_30")
	WebElement adAmount25;
	
	@FindBy(id="fb_ad_reach_31")
	WebElement adAmount45;
	
	@FindBy(id="fb_ad_reach_32")
	WebElement adAmount60;
	
	@FindBy(xpath="//input[@id='facebook_platform']")
	WebElement faceBookPlatform_input;
	
	@FindBy(xpath="//input[@id='instagram_platform']")
	WebElement instagramPlatform_input;
	
	@FindBy(xpath="//label[@for='instagram_platform']")
	WebElement instagram_label;
	
	@FindBy(xpath="//label[@for='facebook_platform']")
	WebElement facebook_label;
	
	public PPCreateAdPage() {
		
	}
	public PPCreateAdPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean isCreateAdPage() {
		boolean isPageVisible=false;
		if(ActionHelper.waitForElementToBeVisible(driver, pageTitle, 15)) {
			isPageVisible = ActionHelper.getText(driver, pageTitle).equalsIgnoreCase("Featured Listings on Facebook and Instagram");
		}
		return isPageVisible;
	}
	
	public boolean isValidPreviewLink(String pDomain) {
		//Currently not comparing https
		pDomain = pDomain.split(":")[1];
		String lPreviewLink = ActionHelper.getText(driver,fbPreviewLink).split(":")[1];
//		return ActionHelper.getText(driver,fbPreviewLink).contains(pDomain);	
		return pDomain.equalsIgnoreCase(lPreviewLink);
	}
	
	public boolean typeAdTitle(String pTitle) {
		return ActionHelper.ClearAndType(driver, fbAdTitle, pTitle);
	}
	public boolean isValidTitle() {
		return !ActionHelper.getTextByValue(driver, fbAdTitle).isEmpty();
	}
	
	public boolean typeAdDescription(String pDescription) {
		return ActionHelper.ClearAndType(driver, fbAdDetails, pDescription);
	}
	public boolean isValidDescription() {
		return !ActionHelper.getTextByValue(driver, fbAdDetails).isEmpty();
	}
	public boolean clickOnNextButton() {
		return ActionHelper.Click(driver, next_button);
	}
	public boolean isSelectAdVisibilityOptionsPage() {
		return ActionHelper.waitForElementToBeLocated(driver, step2_heading, 60);
	}
	public boolean isValidZip() {
		return !ActionHelper.getTextByValue(driver, fbAdZipCodes).isEmpty();
	}
	public boolean typeAdZipCode(String pZip) {
		return ActionHelper.ClearAndType(driver, fbAdZipCodes, pZip);
	}	
	public boolean isStep3PlaceOrderPage() {
		return ActionHelper.waitForElementToBeLocated(driver, step3_heading, 60);
	}
	public boolean clickOnPaymentCheckBox() {
		return ActionHelper.Click(driver, adPaymentConfirmation_checkbox);
	}
	public boolean clickOnFBTestAdCheckBox() {
		return ActionHelper.Click(driver, fbTestAd_checkbox);
	}
	public boolean selectListingFromDropDown(String pListing) {
		boolean isSuccessful=false;
		List<WebElement> list_of_options = new ArrayList<WebElement>();
		 if(ActionHelper.Click(driver, select_listing_dropdown)) {
			 list_of_options = select_listing_dropdown.findElements(By.tagName("option"));
			 for(WebElement element: list_of_options) {
				 System.out.println(element.getText().trim());
				 if(element.getText().trim().contains(pListing)) {
					 isSuccessful = ActionHelper.Click(driver, element);
					 ActionHelper.Click(driver,select_listing_dropdown);
					 break;
				 }
			 }
		 }
		return isSuccessful;	
	}
	public boolean clickOnPlaceAdButton() {
		AutomationLogger.info("Clicking on Place Ad button 01");
		return ActionHelper.Click(driver, place_ad_button_1);
	}
	public boolean selectAdFormat(String pAdFormat) {
		return ActionHelper.selectDropDownOption(driver, adFormat_dropDown, "", pAdFormat);
	}
	public boolean typeListingUrl(String pUrl) {
		boolean isUrlTyped = false;
		if(ActionHelper.Click(driver, editUrl_button)) {
			isUrlTyped = ActionHelper.Type(driver, url_input, pUrl);
		}
		return isUrlTyped;
	}
	public boolean selectAdAmount(String pAmount) {
		boolean isClicked = false;
		if(pAmount.equalsIgnoreCase("25")) {
			isClicked = ActionHelper.Click(driver, adAmount25);
		}else if(pAmount.equalsIgnoreCase("45")) {
			isClicked = ActionHelper.Click(driver, adAmount45);
		}else {
			isClicked = ActionHelper.Click(driver, adAmount60);
		}
		return isClicked;
	}
	public boolean selectPlatforms(String pPlatForms) {
		boolean isChecked = false;
		String[] lPlatforms = pPlatForms.split(",");
		for(String lPlatform: lPlatforms) {
			if(lPlatform.equalsIgnoreCase("Facebook")) {
				isChecked = checkUncheckInputBox(driver,facebook_label, faceBookPlatform_input, true);
			}else {
				isChecked = checkUncheckInputBox(driver,instagram_label,instagramPlatform_input, true);
			}
			if(!isChecked) {
				break;
			}
			
		}
		return isChecked;
	}
	
	 public static boolean checkUncheckInputBox(WebDriver pWebDriver, WebElement pElementToClicked,WebElement pElementToCheck, boolean pSelect) {
		   boolean isSuccess = false;
		   boolean lElementVal = pElementToCheck.isSelected();
		   if(lElementVal && !pSelect) {
			   isSuccess =  ActionHelper.ClickForAds(pWebDriver, pElementToClicked);
		   }else if(!lElementVal && pSelect) {
			   isSuccess = ActionHelper.ClickForAds(pWebDriver, pElementToClicked);
		   }else if(lElementVal && pSelect) {
			   isSuccess = true;
		   }else if (!lElementVal && !pSelect) {
			   isSuccess = true;
		   }
		   return isSuccess;
	   }
}
