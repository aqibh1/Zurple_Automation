/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class PPAdsOverviewPage extends Page{
	
	String listing_address = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::h2";
	
	@FindBy(xpath="//h2[text()='Active Ads']")
	WebElement active_Ads_heading;
	
	String adType = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::p/strong";
	
	String toggle_button = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::span[@class='ad_toggle_input']";
	
	String ad_status = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::span[@class='ad_status']";
	
	String ad_platforms = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::aside[@class='platform_icons']/img";
	
	String ad_City = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::span[@class='adcity']";
	
	String ad_plan = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::div[@class='further_details']/span";
	
	String isSlideShow = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::div[@class='slide-image']";
	
	String ad_description_overview = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::div[@class='fb_ad_preview_details Mpreview']";
	
	String ad_image_preview = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::div[@class='fb_ad_preview_info']/img";
	
	String ad_domain_step2 = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::div[@class='fb_ad_preview_domain']";
	
	String ad_title_step2 = "//div[@id='"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::div[@class='fb_ad_preview_title']/a";
	
	public PPAdsOverviewPage(){
		
	}
	public PPAdsOverviewPage(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyListingAddress(String pAddress, String lAdId) {
		boolean isVerified = false;
		WebElement element = ActionHelper.getDynamicElement(driver, listing_address, lAdId);
		if(element!=null) {
			isVerified = ActionHelper.getText(driver, element).equalsIgnoreCase(pAddress);
		}
		return isVerified;
	}
	public boolean isAdsOverviewPage() {
		return ActionHelper.waitForElementToBeVisible(driver, active_Ads_heading, 60);
	}
	public boolean verifyAdType(String pAdType, String lAdId) {
		boolean isVerified = false;
		WebElement element = ActionHelper.getDynamicElement(driver, adType, lAdId);
		if(element!=null) {
			isVerified = ActionHelper.getText(driver, element).equalsIgnoreCase(pAdType);
		}
		return isVerified;
	}
	public boolean isAdDisableButtonDisplayed(String lAdId) {
		boolean isVerified = false;
		WebElement element = ActionHelper.getDynamicElement(driver, toggle_button, lAdId);
		if(element!=null) {
			isVerified = true;
		}
		return isVerified;
	}
	public boolean verifyAdStatus(String lAdId, String pAdStatusToVerify) {
		boolean isVerified = false;
		WebElement element = ActionHelper.getDynamicElement(driver, ad_status, lAdId);
		if(element!=null) {
			isVerified = ActionHelper.verifyTextAfterRegularIntervals(driver, element, pAdStatusToVerify, 20);
		}
		return isVerified;
	}
	public boolean verifyPlatforms(String pPlatforms, String pAdId) {
		boolean isVerified = false;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_platforms, pAdId));
		String [] lPlatforms = pPlatforms.split(",");
		for(String lPlat: lPlatforms) {
			isVerified = false;
			for(WebElement element: list) {
				if(ActionHelper.getAttribute(element, "alt").trim().equalsIgnoreCase(lPlat.trim())) {
					isVerified = true;
					break;
				}
			}
			if(!isVerified) {
				break;
			}
		}
		return isVerified;
	}
	public boolean verifyAdCity(String pAdCity, String pAdId) {
		boolean isVerified = false;
		WebElement element = ActionHelper.getDynamicElement(driver, ad_City, pAdId);
		if(element!=null) {
			isVerified = ActionHelper.getText(driver, element).contains(pAdCity);
		}
		return isVerified;
	}
	public boolean verifyAdPlanAndRenewalDate(String pPlan, String pAdId) {
		boolean isVerified = false;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_plan, pAdId));
		for(WebElement element: list) {
			if(ActionHelper.getText(driver, element).contains(pPlan)) {
				isVerified = true;
				break;
			}
		}
		return isVerified;
	}
	
	public boolean isSlideShowAtStep2(String pAdId) {
		boolean isSuccess = false;
		String element = ActionHelper.getDynamicElementXpath(driver, isSlideShow, pAdId);
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, element);
		if(list!=null) {
			isSuccess = list.size()>=3;
		}
		return isSuccess;
	}
	public boolean isAdDescOnStep2(String pAdDesc, String pAdId) {
		boolean isVerified = false;
		WebElement element = ActionHelper.getDynamicElement(driver, ad_description_overview, pAdId);
		if(element!=null) {
			isVerified = ActionHelper.getText(driver, element).contains(pAdDesc);
		}
		return isVerified;
	}
	public boolean isPreviewImageDisplayed(String pAdId) {
		boolean isVerified = false;
		WebElement element = ActionHelper.getDynamicElement(driver, ad_image_preview, pAdId);
		if(element!=null) {
			isVerified = ActionHelper.isElementVisible(driver, element);
		}
		return isVerified;
	}
	public boolean isAdDomainOnStep2(String pAdDomain, String pAdId) {
		boolean isVerified = false;
		WebElement element = ActionHelper.getDynamicElement(driver, ad_domain_step2, pAdId);
		if(element!=null) {
			isVerified = ActionHelper.getText(driver, element).contains(pAdDomain.toUpperCase());
		}
		return isVerified;
	}
	public boolean verifyAdTitleStep2(String pTitle, String pAdId) {
		boolean isVerified = false;
		WebElement element = ActionHelper.getDynamicElement(driver, ad_title_step2, pAdId);
		if(element!=null) {
			isVerified = ActionHelper.getText(driver, element).contains(pTitle);
		}
		return isVerified;
	}
}
