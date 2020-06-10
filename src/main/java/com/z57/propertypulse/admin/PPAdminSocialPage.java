/**
 * 
 */
package com.z57.propertypulse.admin;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.z57.propertypulse.Page;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPAdminSocialPage extends Page{
	
	@FindBy(xpath="//a[text()='PropertyPulse Admin Tools v7.3.4']")
	WebElement pp_heading;
	
	@FindBy(xpath="//table[@id='ads_list']/descendant::td[@class='sorting_1']")
	WebElement pp_data;
	
	@FindBy(xpath="//table[@id='ads_list']/descendant::td/a/img")
	WebElement thumb;
	
	String details_location_dates = "//table[@id='ads_list']/descendant::td/div";
	
	String fb_start_end = "//table[@id='ads_list']/descendant::td[@align='center']";
	
	@FindBy(xpath="//table[@id='ads_list']/descendant::td[@align='left']/span[text()='TEST AD (PAUSED)']")
	WebElement test_Ad_paused;
	
	String fb_status = "//table[@id='ads_list']/descendant::td[@align='left']";
	
	public PPAdminSocialPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isAdminSocialPage() {
		return ActionHelper.waitForElementToBeVisible(driver, pp_heading, 30);
	}
	//Verification of PP Data column values
	public boolean verifyAdId(String pAdId) {
		return ActionHelper.getText(driver, pp_data).contains(pAdId);
	}
	public boolean verifyAdFrequency(String pAdFrequence) {
		return ActionHelper.getText(driver, pp_data).contains(pAdFrequence.toLowerCase());
	}
	public boolean verifyAdType(String pAdType) {
		return ActionHelper.getText(driver, pp_data).contains(pAdType.toLowerCase());
	}
	public boolean verifyAdFormat(String pFormat) {
		return ActionHelper.getText(driver, pp_data).contains(pFormat.toLowerCase());
	}
	public boolean verifyAdPlatforms(String pPlatforms) {
		boolean isFound = false;
		String[] lPlatforms = pPlatforms.split(",");
		for(String lPlatform: lPlatforms) {
			if(lPlatform.equalsIgnoreCase("Facebook")) {
				isFound = ActionHelper.getText(driver, pp_data).contains("FB");
			}else {
				isFound = ActionHelper.getText(driver, pp_data).contains("IG");
			}
		if(!isFound) {
			break;
		}
		}
		return isFound;
	}
	//Verification of Thumbnail Column
	public boolean verifyThumbnail() {
		return ActionHelper.isElementVisible(driver, thumb);
	}
	//Verification of Details column
	public boolean verifyAdTitle(String pTitle) {
		return ActionHelper.findTextInListOfElements(driver, details_location_dates, pTitle);
	}
	public boolean verifyAdDescription(String pDesc) {
		return ActionHelper.findTextInListOfElements(driver, details_location_dates, pDesc);
	}
	//Verification of Location column
	public boolean verifyAdLocation(String pLocation) {
		return ActionHelper.findTextInListOfElements(driver, details_location_dates, pLocation);
	}
	//Verification of PP Dates column
	public boolean verifyAdSubmissionDate(String pDate) {
		return ActionHelper.findTextInListOfElements(driver, details_location_dates, pDate);
	}
	//Verification of fb start and end date
	public boolean verifyAdFBStartDate(String pStartDate) {
		return ActionHelper.findTextInListOfElements(driver, fb_start_end, pStartDate);
	}
	public boolean verifyAdFBEndDate(String pEndDate) {
		return ActionHelper.findTextInListOfElements(driver, fb_start_end, pEndDate);
	}
	public boolean verifyFBAdStatus() {
		boolean verifyFBStatus = false;
		boolean isTestPausedAd = false;
		if(ActionHelper.waitForElementToBeFoundAfterRegularIntervals(driver, fb_status, "PP: LIVE", 20)) {
			isTestPausedAd = ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver, test_Ad_paused, 60, 20);
			verifyFBStatus = ActionHelper.waitForElementToBeFoundAfterRegularIntervals(driver, fb_status, "FB: PAUSED", 20);
		}
		return verifyFBStatus && isTestPausedAd;
	}
	public boolean verifyBudget(String pBudget) {
		return ActionHelper.findTextInListOfElements(driver, fb_start_end, pBudget);
		
	}
}
