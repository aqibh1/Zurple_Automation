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

	private ActionHelper actionHelper;
	
	public PPAnalyticsPage() {
		
	}
	public PPAnalyticsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver,this);
		
	}
	public boolean selectFacebookPage(String pPage) {
		return actionHelper.selectDropDownOption( facebookPageDropDown, "", pPage);
	}
	public boolean isFacebookGraphVisible() {
		return actionHelper.waitForElementToBeVisible( graphContainer, 30);
	}
	
	public boolean isPostFilterVisible() {
		return actionHelper.waitForElementToBeVisible( postFilter, 30);
	}
	public boolean isDashboardAnalyticsPage() {
		return actionHelper.waitForElementToBeVisible( analyticsDashboard_heading, 10);
	}
	public boolean clickAndVerifyStatusResults() {
		boolean result = false;
		if(actionHelper.Click( statusTab_button)) {
			result = isDetailTableVisible();
		}
		return result;
	}
	public boolean verifyAllTabStats() {
		return isDetailTableVisible();
	}
	
	public boolean clickAndVerifyLinksResults() {
		boolean result = false;
		if(actionHelper.Click( filterTab_button)) {
			result = isDetailTableVisible();
		}
		return result;
	}
	
	public boolean clickAndVerifyPhotosResults() {
		boolean result = false;
		if(actionHelper.Click( photosTab_button)) {
			result = isDetailTableVisible();
		}
		return result;
	}
	
	public boolean clickAndVerifyVideosResults() {
		boolean result = false;
		if(actionHelper.Click( videosTab_button)) {
			result = isDetailTableVisible();
		}
		return result;
	}
	
	private boolean isDetailTableVisible() {
		boolean isDateDisplayed = actionHelper.waitForElementToBeVisible( postHeaderDate, 10);
		boolean isMessageDisplayed= actionHelper.waitForElementToBeVisible( postHeaderDate, 10);
		boolean isEngageUserDisplayed = actionHelper.waitForElementToBeVisible( postHeaderDate, 10);
		boolean isReachedDisplayed = actionHelper.waitForElementToBeVisible( postHeaderDate, 10);
		boolean isTalkingAboutThisDisplayed = actionHelper.waitForElementToBeVisible( postHeaderDate, 10);
		boolean isViralityDisplayed = actionHelper.waitForElementToBeVisible( postHeaderDate, 10);
		return (isDateDisplayed && isMessageDisplayed && isEngageUserDisplayed && isReachedDisplayed && isTalkingAboutThisDisplayed && isViralityDisplayed);
	}

}
