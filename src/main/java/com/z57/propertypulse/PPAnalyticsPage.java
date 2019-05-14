/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPAnalyticsPage extends Page{
	
	@FindBy(xpath="//select[@id='fbpage']")
	WebElement facebookPageDropDown;
	
	@FindBy(id="graph-container")
	WebElement graphContainer;
	
	@FindBy(id="posts_filter")
	WebElement postFilter;
	
	@FindBy(id="posts-header-date")
	WebElement postHeaderDate;
	
	@FindBy(id="posts-header-message")
	WebElement postHeaderMessage;
	
	@FindBy(id="posts-header-post_engaged_users")
	WebElement postHeaderEngageUser;
	
	@FindBy(id="posts-header-post_impressions_unique")
	WebElement postHeaderReached;
	
	@FindBy(id="posts-header-post_storytellers")
	WebElement postHeaderTalkingAboutThis;
	
	@FindBy(id="posts-header-virality")
	WebElement postHeaderVirality;
	
	@FindBy(id="posts-filter-link-status")
	WebElement statusTab_button;
	
	@FindBy(id="posts-filter-link-link")
	WebElement filterTab_button;
	
	@FindBy(id="posts-filter-link-photo")
	WebElement photosTab_button;
	
	@FindBy(id="posts-filter-link-video")
	WebElement videosTab_button;
	
	@FindBy(xpath="//h1[text()='Analytics Dashboard']")
	WebElement analyticsDashboard_heading;
	
	public PPAnalyticsPage() {
		
	}
	public PPAnalyticsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
		
	}
	public boolean selectFacebookPage(String pPage) {
		return ActionHelper.selectDropDownOption(driver, facebookPageDropDown, "", pPage);
	}
	public boolean isFacebookGraphVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, graphContainer, 30);
	}
	
	public boolean isPostFilterVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, postFilter, 30);
	}
	public boolean isDashboardAnalyticsPage() {
		return ActionHelper.waitForElementToBeVisible(driver, analyticsDashboard_heading, 10);
	}
	public boolean clickAndVerifyStatusResults() {
		boolean result = false;
		if(ActionHelper.Click(driver, statusTab_button)) {
			result = isDetailTableVisible();
		}
		return result;
	}
	public boolean verifyAllTabStats() {
		return isDetailTableVisible();
	}
	
	public boolean clickAndVerifyLinksResults() {
		boolean result = false;
		if(ActionHelper.Click(driver, filterTab_button)) {
			result = isDetailTableVisible();
		}
		return result;
	}
	
	public boolean clickAndVerifyPhotosResults() {
		boolean result = false;
		if(ActionHelper.Click(driver, photosTab_button)) {
			result = isDetailTableVisible();
		}
		return result;
	}
	
	public boolean clickAndVerifyVideosResults() {
		boolean result = false;
		if(ActionHelper.Click(driver, videosTab_button)) {
			result = isDetailTableVisible();
		}
		return result;
	}
	
	private boolean isDetailTableVisible() {
		boolean isDateDisplayed = ActionHelper.waitForElementToBeVisible(driver, postHeaderDate, 10);
		boolean isMessageDisplayed= ActionHelper.waitForElementToBeVisible(driver, postHeaderDate, 10);
		boolean isEngageUserDisplayed = ActionHelper.waitForElementToBeVisible(driver, postHeaderDate, 10);
		boolean isReachedDisplayed = ActionHelper.waitForElementToBeVisible(driver, postHeaderDate, 10);
		boolean isTalkingAboutThisDisplayed = ActionHelper.waitForElementToBeVisible(driver, postHeaderDate, 10);
		boolean isViralityDisplayed = ActionHelper.waitForElementToBeVisible(driver, postHeaderDate, 10);
		return (isDateDisplayed && isMessageDisplayed && isEngageUserDisplayed && isReachedDisplayed && isTalkingAboutThisDisplayed && isViralityDisplayed);
	}

}
