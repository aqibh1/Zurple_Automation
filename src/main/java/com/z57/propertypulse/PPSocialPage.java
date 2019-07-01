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

import resources.forms.pp.PPPromoteAListingForm;
import resources.forms.pp.PPUploadImagesForm;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.ValueMapper;

/**
 * @author adar
 *
 */
public class PPSocialPage extends Page{
	PPUploadImagesForm ppUploadImagesForm;
	PPPromoteAListingForm ppPromoteListingForm;
	
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
	
	@FindBy(xpath="//div[@id='scheduled_upcoming_posts_length']/descendant::select[@name='scheduled_upcoming_posts_length']")
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
	
	@FindBy(xpath="//div[@class='marketing_submit_results' and text()='Created Post Schedule']")
	WebElement postScheduledSuccess_message;
	
	@FindBy(xpath="//div[@id='ajax_working' and @style='display: block;']")
	WebElement ajaxLoader;
	
	@FindBy(id="post_file")
	WebElement image_input;
	
	@FindBy(id="type_status")
	WebElement post_status_message;
	
	@FindBy(id="twitter_char_count")
	WebElement twitter_message;
	
	public PPSocialPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setPpUploadImagesForm();
		setPpPromoteListingForm();
		PageFactory.initElements(driver, this);
	}
	
	public PPPromoteAListingForm getPpPromoteListingForm() {
		return ppPromoteListingForm;
	}

	public void setPpPromoteListingForm() {
		this.ppPromoteListingForm = new PPPromoteAListingForm(driver);
	}

	public PPUploadImagesForm getPpUploadImagesForm() {
		return ppUploadImagesForm;
	}

	public void setPpUploadImagesForm() {
		this.ppUploadImagesForm = new PPUploadImagesForm(driver);
	}

	public boolean isSocialPage() {
		return ActionHelper.waitForElementToBeLocated(driver, social_heading_xpath, 30);
	}
	public boolean checkFacebookOption() {
		boolean isFacebookChecked = false;
		if(!ActionHelper.isElementVisible(driver, post_status_message)) {
			isFacebookChecked = ActionHelper.Click(driver, facebook_checkbox);
		}else {
			isFacebookChecked = true;
		}
		return isFacebookChecked;
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
	public boolean isUpcomingPostsSuccessful(String pPostTitle, String pImage, String pDate, String pTime) {	
		return isPostSuccessful(upcomingPostsRow_xpath, pPostTitle, pImage, pDate, pTime);
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
		return ActionHelper.waitForElementsToBeFound(driver, "//div[@id='multi_post_schedule_frame']/descendant::input[@name='post_multi[]']");
//		return ActionHelper.waitForElementToBeVisible(driver, addedTime_input, 10);
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
	public boolean isScheduleLaterPostCompleted() {
		return ActionHelper.waitForElementToBeVisible(driver, postScheduledSuccess_message, 30);
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
	
	private boolean isPostSuccessful(String pElement,String pPostTitle, String pPlatformImage, String pDate, String pTime) {
		boolean result = false;
		boolean post_found = false;
		boolean platform_image_verified= false;
		boolean date_verified = false;
		boolean time_verified = false;
		ActionHelper.waitForElementsToBeFound(driver, pElement);
		List<WebElement> list_of_rows = ActionHelper.getListOfElementByXpath(driver, pElement);
		AutomationLogger.info("Size of element list "+list_of_rows.size());
		
		try {
			for(WebElement element: list_of_rows) {
				List<WebElement> list_of_div = element.findElements(By.tagName("div"));
				//Div tag will look for post title
				for(WebElement divElement: list_of_div) {
					if(divElement.getText().trim().equalsIgnoreCase(pPostTitle.trim())) {
						AutomationLogger.info("Post title verified");
						post_found = true;
						break;
					}	
				}
				//Img tag will look for platform icon
				if(post_found) {
					List<WebElement> list_of_img = element.findElements(By.tagName("img"));
					for(WebElement imgElement: list_of_img) {
						if(imgElement.getAttribute("src").contains(pPlatformImage)) {
							AutomationLogger.info("Platform Image verified");
							platform_image_verified = true;
							break;
						}
					}
					if(platform_image_verified) {
						List<WebElement> list_of_td = element.findElements(By.tagName("td"));
						for(WebElement tdElement: list_of_td) {
							if(tdElement.getText().equalsIgnoreCase(pDate)) {
								AutomationLogger.info("Post Date verified");
								date_verified = true;
							}
						}
						if(date_verified) {
							list_of_td = element.findElements(By.tagName("td"));
							for(WebElement tdElement: list_of_td) {
								if(tdElement.getText().equalsIgnoreCase(pTime)) {
									AutomationLogger.info("Post Time verified");
									time_verified = true;
								}
							}
						}
					}
				}
				if(post_found && platform_image_verified && date_verified && time_verified) {//Success case
					result = true;
					break;
				}else {
					post_found = false;
					platform_image_verified= false;
					date_verified = false;
					time_verified = false;
				}
				
			}
		}catch(Exception ex) {
//			if(counter<3) {
//				counter++;
//				isPostSuccessful(pElement,pPostTitle, pPlatformImage, pDate, pTime);
//			}
			AutomationLogger.error("Unable to verify post..");
			result = false;
		}
		
		return result;

	}
	public boolean isUpcomingRecurringPostsSuccessful(String pStatus, String pImageIcon, String pDate,String pTime, String pEndingDate, String pRepeatOnDays) {
		return isPostRecurringSuccessful(upcomingPostsRow_xpath,pStatus,pImageIcon,pDate,pTime,pEndingDate,pRepeatOnDays);
	}
	
	private boolean isPostRecurringSuccessful(String pElement,String pPostTitle, String pPlatformImage, String pDate, String pTime,String pEndDate, String pRepeatDays) {
		boolean result = false;
		boolean post_found = false;
		boolean platform_image_verified= false;
		boolean date_verified = false;
		boolean time_verified = false;
		boolean endDate_verified = false;
		boolean isRecurring = false;
		boolean repeatOnDays = false;
		
		ActionHelper.waitForElementsToBeFound(driver, pElement);
		List<WebElement> list_of_rows = ActionHelper.getListOfElementByXpath(driver, pElement);
		AutomationLogger.info("Size of element list "+list_of_rows.size());
		
		try {
			for(WebElement element: list_of_rows) {
				List<WebElement> list_of_div = element.findElements(By.tagName("div"));
				//Div tag will look for post title
				for(WebElement divElement: list_of_div) {
					if(divElement.getText().trim().equalsIgnoreCase(pPostTitle.trim())) {
						AutomationLogger.info("Post title verified");
						post_found = true;
						break;
					}	
				}
				//Img tag will look for platform icon
				if(post_found) {
					List<WebElement> list_of_img = element.findElements(By.tagName("img"));
					for(WebElement imgElement: list_of_img) {
						if(imgElement.getAttribute("src").contains(pPlatformImage)) {
							AutomationLogger.info("Platform Image verified");
							platform_image_verified = true;
							break;
						}
					}
					if(platform_image_verified) {
						List<WebElement> list_of_td = element.findElements(By.tagName("td"));
						for(WebElement tdElement: list_of_td) {
							if(tdElement.getText().equalsIgnoreCase(pDate)) {
								AutomationLogger.info("Post Date verified");
								date_verified = true;
							}
						}
						if(date_verified) {
							list_of_td = element.findElements(By.tagName("td"));
							for(WebElement tdElement: list_of_td) {
								if(tdElement.getText().equalsIgnoreCase(pTime)) {
									AutomationLogger.info("Post Time verified");
									time_verified = true;
								}
								if(tdElement.getText().equalsIgnoreCase(pEndDate)) {
									AutomationLogger.info("Post End Date verified");
									endDate_verified = true;
								}
								if(tdElement.getText().equalsIgnoreCase("Recurring")) {
									AutomationLogger.info("Post is recurring verified");
									isRecurring = true;
								}
								for(String days: pRepeatDays.split(",")) {
									if(tdElement.getText().contains(new ValueMapper().getDays(days))){
										AutomationLogger.info("Day verified: "+days);
										repeatOnDays = true;
									}
								}
							}
						}
						
					}
				}
				if(post_found && platform_image_verified && date_verified && time_verified && endDate_verified && isRecurring && repeatOnDays) {//Success case
					result = true;
					break;
				}else {
					post_found = false;
					platform_image_verified= false;
					date_verified = false;
					time_verified = false;
				}
			}
		}catch(Exception ex) {
			AutomationLogger.error("Unable to verify recurring post..");
			return result;
		}
		
		return result;

	}
	public boolean isLoaderDisappeared() {
		return ActionHelper.waitForAjaxToBeCompleted(driver);
	}
	public boolean uploadImage(String pImagePath) {
		return ActionHelper.Type(driver, image_input, pImagePath);
	}
	
	public boolean unCheckFacebookOption() {
		boolean isFacebookUnChecked = false;
		if(ActionHelper.isElementVisible(driver, post_status_message)) {
			isFacebookUnChecked = ActionHelper.Click(driver, facebook_checkbox);
		}else {
			isFacebookUnChecked = true;
		}
		return isFacebookUnChecked;
	}
	public boolean checkTwitterOption(boolean pCheck) {
		boolean isSuccess = false;
		if(ActionHelper.isElementVisible(driver, twitter_message)) {
			if(!pCheck) {
				isSuccess = ActionHelper.Click(driver, twitter_checkbox);
			}else {
				isSuccess = true;
			}
		}else {
			if(pCheck) {
				isSuccess = ActionHelper.Click(driver, twitter_checkbox);
			}else {
				isSuccess = true;
			}
		}
		return isSuccess;
	}
}
