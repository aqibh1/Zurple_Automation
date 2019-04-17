/**
 * 
 */
package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class PPSocialPage extends Page{
	
	String social_heading_xpath = "//h1[@class='z57-theme-page-topic' and text()='Social Posting ']";
	
	@FindBy(id="post_facebook_toggle")
	WebElement facebook_checkbox;
	
	@FindBy(id="post_twitter_toggle")
	WebElement twitter_checkbox;
	
	@FindBy(id="post_youtube_toggle")
	WebElement youtube_checkbox;
	
	@FindBy(id="post_tab_status")
	WebElement status_tab;
	
	@FindBy(id="post_tab_photo")
	WebElement photo_tab;
	
	@FindBy(id="post_tab_link")
	WebElement link_to_url_tab;
	
	@FindBy(id="post_message")
	WebElement status_textarea;
	
	@FindBy(id="post_file")
	WebElement choose_file_button;
	
	@FindBy(id="post_tab_listing")
	WebElement promote_listing_tab;
	
	@FindBy(id="post_link")
	WebElement url_to_post_input;
	
	@FindBy(id="default_post_schedule")
	WebElement now_radio_button;
	
	@FindBy(xpath="//span[@id='radio_opt_post_later']/descendant::input[@type='radio']")
	WebElement later_radio_button;
	
	@FindBy(xpath="//span[@id='radio_opt_post_recurring']/descendant::input[@type='radio']")
	WebElement recurring_radio_button;
	
	@FindBy(id="fb_page_select")
	WebElement fb_page_dropdown;
	
	@FindBy(xpath="//a[text()='Post Now']")
	WebElement postNow_button;
	
	String previousPostsRow_xpath="//table[@id='scheduled_previous_posts']/descendant::tr";
	
	@FindBy(xpath="//a/h3[text()='Previous Posts']")
	WebElement previousPosts_tab;
	
	String upcomingPostsRow_xpath="//table[@id='scheduled_upcoming_posts']/descendant::tr";
	
	@FindBy(id="scheduled_upcoming_posts_length")
	WebElement number_of_records_per_page;

	@FindBy(xpath="//div[@id='scheduled_upcoming_posts_length']/descendant::select[@name='scheduled_upcoming_posts_length']/option")
	WebElement number_of_records_per_page_options;
	String number_of_records_per_page_options_xpath ="//div[@id='scheduled_upcoming_posts_length']/descendant::select[@name='scheduled_upcoming_posts_length']/option";
	
	@FindBy(id="date_start")
	WebElement dateStart_input;
	
	@FindBy(id="post_time")
	WebElement time_dropdown;
//	String time_dropdown = "//select[@id='post_time']";
	
	@FindBy(id="multi_post_schedule_add")
	WebElement addPost_button;;
	
	@FindBy(xpath="//div[@id='multi_post_schedule_frame']/descendant::input[@name='post_multi[]']")
	WebElement addedTime_input;
	
	@FindBy(id="date_end")
	WebElement dateEnd_input;
	
	String repeatOnDays_xpath="//div[@class='row-fluid schedule_days']/descendant::input[@value]";
	
	@FindBy(xpath="//a[text()='Schedule a Later Post']")
	WebElement postLater_button;
	
	@FindBy(xpath="//a[text()='Schedule a Recurring Post']")
	WebElement postRecurring_button;
	
	@FindBy(xpath="//div[@class='marketing_submit_results' and text()='Post Completed']")
	WebElement postCompletedSuccess_message;
	
	public PPSocialPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isSocialPage() {
		return ActionHelper.waitForElementToBeLocated(driver, social_heading_xpath, 30);
	}
	public boolean checkFacebookOption() {
		return ActionHelper.Click(driver, facebook_checkbox);
	}
	public boolean checkTwitterOption() {
		return ActionHelper.Click(driver, twitter_checkbox);
	}
	public boolean checkYoutubeOption() {
		return ActionHelper.Click(driver, youtube_checkbox);
	}
	public boolean clickOnStatus() {
		return ActionHelper.Click(driver, status_tab);
	}
	public boolean clickOnPhoto() {
		return ActionHelper.Click(driver, photo_tab);
	}
	public boolean clickOnLinkToURL() {
		return ActionHelper.Click(driver, link_to_url_tab);
	}
	public boolean typeStatus(String pStatus) {
		return ActionHelper.ClearAndType(driver, status_textarea, pStatus);
	}
	public boolean clickOnLinkChooseFileButton() {
		return ActionHelper.Click(driver, choose_file_button);
	}
	public boolean clickOnPromoteListingTab() {
		return ActionHelper.Click(driver, promote_listing_tab);
	}
	public boolean selectFacebookPage(String pPageName) {
		return ActionHelper.selectDropDownOption(driver, fb_page_dropdown,"", pPageName);
	}
	public boolean setLinkToURL(String pUrl) {
		return ActionHelper.ClearAndType(driver, url_to_post_input, pUrl);
	}
	public boolean clickOnScheduleNow() {
		return ActionHelper.Click(driver, now_radio_button);
	}
	public boolean clickOnScheduleLater() {
		return ActionHelper.Click(driver, later_radio_button);
	}
	public boolean clickOnScheduleRecurring() {
		return ActionHelper.Click(driver, recurring_radio_button);
	}
	public boolean clickOnPreviousPosts() {
		return ActionHelper.Click(driver, previousPosts_tab);
	}
	public boolean isPreviousPostsSuccessful(String pPostTitle, String pPostStatusToVerify) {	
		return isPostSuccessful(previousPostsRow_xpath, pPostTitle, pPostStatusToVerify);
	}
	public boolean isUpcomingPostsSuccessful(String pPostTitle, String pPostStatusToVerify) {	
		return isPostSuccessful(upcomingPostsRow_xpath, pPostTitle, pPostStatusToVerify);
	}
	public boolean selectNumberOfRecords(String pNumRecords) {
		return ActionHelper.selectDropDownOption(driver, number_of_records_per_page, number_of_records_per_page_options_xpath, pNumRecords);
	}
	public boolean clickOnPostNowButton() {
		return ActionHelper.Click(driver, postNow_button);
	}
	public boolean typeDate(String pDate) {
		return ActionHelper.Type(driver, dateStart_input, pDate);
	}
	public boolean selectTime(String pTime) {
		return ActionHelper.selectDropDownOption(driver, time_dropdown, "", pTime);
	}
	public boolean clickOnAddButton() {
		return ActionHelper.Click(driver, addPost_button);
	}
	public boolean isDateTimeAdded() {
		return ActionHelper.waitForElementToBeVisible(driver, addedTime_input, 15);
	}
	public boolean typeEndingDate(String pEndingDate) {
		return ActionHelper.ClearAndType(driver, dateEnd_input, pEndingDate);
	}
	public boolean selectRepeatDays(String pWeekdays) {
		int counter = 0;
		String[] lWeekdaysArray = pWeekdays.split(",");
		List<WebElement> repeat_days_list = ActionHelper.getListOfElementByXpath(driver, repeatOnDays_xpath);
		for(WebElement element: repeat_days_list) {
			for(String lDay: lWeekdaysArray) {
				if(element.getAttribute("value").equalsIgnoreCase(lDay)) {
					ActionHelper.Click(driver, element);
					counter++;
					break;
				}
			}
		}
		return counter==lWeekdaysArray.length;
	}
	public boolean clickOnPostLaterButton() {
		return ActionHelper.Click(driver, postLater_button);
	}
	public boolean clickOnPostRecurringButton() {
		return ActionHelper.Click(driver, postRecurring_button);
	}
	public boolean isPostCompleted() {
		return ActionHelper.waitForElementToBeVisible(driver, postCompletedSuccess_message, 30);
	}
	private boolean isPostSuccessful(String pElement,String pPostTitle, String pPostStatusToVerify) {
		boolean post_found = false;
		boolean status_verified= false;
		List<WebElement> list_of_rows = ActionHelper.getListOfElementByXpath(driver, pElement);
		for(WebElement element: list_of_rows) {
			List<WebElement> list_of_td = element.findElements(By.tagName("td"));
			for(WebElement td: list_of_td) {
				if(td.findElement(By.tagName("div"))!=null && td.findElement(By.tagName("div")).getText().trim().equalsIgnoreCase(pPostTitle)) {
					post_found = true;
				}
				if(td.getText()!=null && td.getText().equalsIgnoreCase(pPostStatusToVerify)) {
					status_verified = true;
				}
			}
			if(post_found && status_verified) {
				break;
			}else {
				post_found = false;
				status_verified= false;
			}
		}
		return (post_found && status_verified);

	}
}
