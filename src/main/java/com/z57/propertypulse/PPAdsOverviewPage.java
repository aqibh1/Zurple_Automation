/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	String ad_description_overview = "//div[@id='21419']/descendant::div[@class='fb_ad_preview_details Mpreview']";
	
	@FindBy(xpath="//div[@class='fb_ad_preview_domain']")
	WebElement ad_domain_step2;
	
	@FindBy(xpath="//div[@class='fb_ad_preview_title']/a")
	WebElement ad_title_step2;
	
	public PPAdsOverviewPage(){
		
	}
	public PPAdsOverviewPage(WebDriver pWebDriver){
		
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
		WebElement element = ActionHelper.getDynamicElement(driver, listing_address, lAdId);
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
	public boolean verifyAdStatus(String lAdId) {
		return ActionHelper.getDynamicElementAfterRegularIntervals(driver,ad_status,lAdId,20);
	}
	public boolean verifyPlatforms(String pPlatforms, String pAdId) {
		boolean isVerified = false;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, ad_platforms, pAdId));
		String [] lPlatforms = pPlatforms.split(",");
		for(String lPlat: lPlatforms) {
			for(WebElement element: list) {
				if(ActionHelper.getAttribute(element, "alt").equalsIgnoreCase(lPlat)) {
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
			isVerified = ActionHelper.getText(driver, element).equalsIgnoreCase(pAdCity);
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
	
	public boolean isSlideShowAtStep2() {
		boolean isSuccess = false;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, isSlideShow);
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
}
