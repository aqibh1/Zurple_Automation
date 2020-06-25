/**
 * 
 */
package com.zurple.backoffice.social;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOSelectListingAlert;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class ZBOCreatePostPage extends Page{
	
	@FindBy(xpath="//h3[text()='Create post']")
	WebElement create_post_heading;
	
	@FindBy(xpath="//p[text()='Click on a social network to get started.']")
	WebElement clickOnSocialNetwork_heading;
	
	String platforms_icons = "//div[@id='social-network-buttons-container-top']/div[@title='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";

	@FindBy(xpath="//div[contains(@class,'post-facebook-network-icon')]/ancestor::div[@id='new-post-template']")
	WebElement fb_new_post_template_element;
	@FindBy(xpath="//div[contains(@class,'post-twitter-network-icon')]/ancestor::div[@id='new-post-template']")
	WebElement tw_new_post_template_element;
	@FindBy(xpath="//div[contains(@class,'post-linkedin-network-icon')]/ancestor::div[@id='new-post-template']")
	WebElement li_new_post_template_element;
	@FindBy(xpath="//div[contains(@class,'post-youtube-network-icon')]/ancestor::div[@id='new-post-template']")
	WebElement yt_new_post_template_element;
	
	@FindBy(id="submit-new-post")
	WebElement post_button;
	
	@FindBy(xpath="//div[@id='ui-datepicker-div']/descendant::button[text()='Now']")
	WebElement datePicker_now_button;
	
	@FindBy(xpath="//div[@id='ui-datepicker-div']/descendant::button[text()='Done']")
	WebElement datePicker_done_button;
	
	@FindBy(xpath="//span[@class='schedule-label']")
	WebElement scheduled_label;
	
	@FindBy(id="post_image")
	WebElement post_image;
	
	private ZBOSelectListingAlert selectListingAlert;
	
	public ZBOCreatePostPage() {
		
	}
	public ZBOCreatePostPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setSelectListingAlert();
		PageFactory.initElements(driver, this);
	}
	
	public ZBOSelectListingAlert getSelectListingAlert() {
		return selectListingAlert;
	}
	private void setSelectListingAlert() {
		this.selectListingAlert = new ZBOSelectListingAlert(driver);
	}
	
	public boolean isCreatePostPage() {
		return ActionHelper.waitForElementToBeVisible(driver, create_post_heading, 30);
	}
	public boolean isClickOnSocialNetworkHeadingVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, clickOnSocialNetwork_heading, 10);
	}
	public boolean verifyIfPlatformIsConnected(String pPlatform) {
		ActionHelper.staticWait(10);
		boolean isConnected = false;
		WebElement element;
		switch(pPlatform) {
		case "Facebook":
			element = ActionHelper.getDynamicElement(driver, platforms_icons, "Add a post to Facebook");
			if(element!=null) {
				isConnected = ActionHelper.isElementVisible(driver, element);
			}
			break;
		case "Twitter":
			element = ActionHelper.getDynamicElement(driver, platforms_icons, "Add a post to Twitter");
			if(element!=null) {
				isConnected = ActionHelper.isElementVisible(driver, element);
			}
			break;
		case "LinkedIn":
			element = ActionHelper.getDynamicElement(driver, platforms_icons, "Add a post to Linkedin");
			if(element!=null) {
				isConnected = ActionHelper.isElementVisible(driver, element);
			}
			break;
		case "YouTube":
			element = ActionHelper.getDynamicElement(driver, platforms_icons, "Add a post to Youtube");
			if(element!=null) {
				isConnected = ActionHelper.isElementVisible(driver, element);
			}
			break;
		}
		return isConnected;
	}
	public boolean clickOnPlatformIcon(String pPlatform) {
		boolean isClicked = false;
		WebElement element;
		switch(pPlatform) {
		case "Facebook":
			element = ActionHelper.getDynamicElement(driver, platforms_icons, "Add a post to Facebook");
			if(element!=null) {
				isClicked = ActionHelper.Click(driver, element);
			}
			break;
		case "Twitter":
			element = ActionHelper.getDynamicElement(driver, platforms_icons, "Add a post to Twitter");
			if(element!=null) {
				isClicked = ActionHelper.Click(driver, element);
			}
			break;
		case "LinkedIn":
			element = ActionHelper.getDynamicElement(driver, platforms_icons, "Add a post to Linkedin");
			if(element!=null) {
				isClicked = ActionHelper.Click(driver, element);
			}
			break;
		case "YouTube":
			element = ActionHelper.getDynamicElement(driver, platforms_icons, "Add a post to Youtube");
			if(element!=null) {
				isClicked = ActionHelper.Click(driver, element);
			}
			break;
		}
		return isClicked;
	}
	
	public boolean typeTextPost(String pPlatform, String pTextTtoType) {
		boolean isTyped = false;	
		switch(pPlatform) {
		case "Facebook":
			if(ActionHelper.waitForElementToBeVisible(driver, fb_new_post_template_element, 15)) {
				isTyped = ActionHelper.ClearAndType(driver, fb_new_post_template_element.findElement(By.id("post_text")), pTextTtoType);
			}
			break;
		case "Twitter":
			if(ActionHelper.waitForElementToBeVisible(driver, tw_new_post_template_element, 15)) {
				isTyped = ActionHelper.ClearAndType(driver, tw_new_post_template_element.findElement(By.id("post_text")), pTextTtoType);
			}
			break;
		case "LinkedIn":
			if(ActionHelper.waitForElementToBeVisible(driver, li_new_post_template_element, 15)) {
				isTyped = ActionHelper.ClearAndType(driver, li_new_post_template_element.findElement(By.id("post_text")), pTextTtoType);
			}
			break;
		case "YouTube":
			if(ActionHelper.waitForElementToBeVisible(driver, yt_new_post_template_element, 15)) {
				isTyped = ActionHelper.ClearAndType(driver, yt_new_post_template_element.findElement(By.id("post_text")), pTextTtoType);
			}
			break;
		}
		return isTyped;
	}
	public boolean selectSchedule(String pPlatform) {
		boolean isClicked = false;
		boolean isScheduleSelected = false;
		switch(pPlatform) {
		case "Facebook":
			if(ActionHelper.waitForElementToBeVisible(driver, fb_new_post_template_element, 15)) {
				isClicked = ActionHelper.Click(driver, fb_new_post_template_element.findElement(By.id("new-post-schedule")));
			}
			break;
		case "Twitter":
			if(ActionHelper.waitForElementToBeVisible(driver, tw_new_post_template_element, 15)) {
				isClicked = ActionHelper.Click(driver, tw_new_post_template_element.findElement(By.id("new-post-schedule")));
			}
			break;
		case "LinkedIn":
			if(ActionHelper.waitForElementToBeVisible(driver, li_new_post_template_element, 15)) {
				isClicked = ActionHelper.Click(driver, li_new_post_template_element.findElement(By.id("new-post-schedule")));
			}
			break;
		case "YouTube":
			if(ActionHelper.waitForElementToBeVisible(driver, yt_new_post_template_element, 15)) {
				isClicked = ActionHelper.Click(driver, yt_new_post_template_element.findElement(By.id("new-post-schedule")));
			}
			break;
		}
		if(isClicked && ActionHelper.Click(driver, datePicker_now_button)) {
			isScheduleSelected = ActionHelper.Click(driver, datePicker_done_button);
		}
		
		return isScheduleSelected;
	}
	public boolean clickOnPostButton() {
		return ActionHelper.Click(driver, post_button);
	}
	public String getTitle(String pPlatform) {
		String lTitle = "";
		switch(pPlatform) {
		case "Facebook":
			if(ActionHelper.waitForElementToBeVisible(driver, fb_new_post_template_element, 15)) {
				lTitle = ActionHelper.getText(driver, fb_new_post_template_element.findElement(By.id("facebook-page-title")));
			}
			break;
		case "Twitter":
			if(ActionHelper.waitForElementToBeVisible(driver, tw_new_post_template_element, 15)) {
				lTitle = ActionHelper.getText(driver, tw_new_post_template_element.findElement(By.id("target-page-title")));
			}
			break;
		default:
			lTitle ="Linkedin or Youtube";
			break;
		}
		
		return lTitle;
	}
	public String getUsername(String pPlatform) {
		String lTitle = "";
		switch(pPlatform) {
		case "Facebook":
			if(ActionHelper.waitForElementToBeVisible(driver, fb_new_post_template_element, 15)) {
				lTitle = ActionHelper.getText(driver, fb_new_post_template_element.findElement(By.id("user-name")));
			}
			break;
		case "Twitter":
			if(ActionHelper.waitForElementToBeVisible(driver, tw_new_post_template_element, 15)) {
				lTitle = ActionHelper.getText(driver, tw_new_post_template_element.findElement(By.id("user-name")));
			}
			break;
		case "LinkedIn":
			if(ActionHelper.waitForElementToBeVisible(driver, li_new_post_template_element, 15)) {
				lTitle = ActionHelper.getText(driver, li_new_post_template_element.findElement(By.id("user-name")));
			}
			break;
		default:
			lTitle ="Youtube";
			break;
		}
		
		return lTitle;
	}
	public boolean verifyPlatformProfilePicsAreVisible(String pPlatform) {
		String lTitle = "";
		boolean isVerified = false;
		switch(pPlatform) {
		case "Facebook":
			if(ActionHelper.waitForElementToBeVisible(driver, fb_new_post_template_element, 15)) {
				lTitle = ActionHelper.getAttribute(fb_new_post_template_element.findElement(By.xpath("/descendant::div/img")),"src");
				isVerified = lTitle.toLowerCase().contains("facebook");
			}
			break;
		case "Twitter":
			if(ActionHelper.waitForElementToBeVisible(driver, tw_new_post_template_element, 15)) {
				lTitle = ActionHelper.getAttribute(tw_new_post_template_element.findElement(By.xpath("/descendant::div/img")),"src");
				isVerified = lTitle.toLowerCase().contains("twimg");
			}
			break;
		case "LinkedIn":
			if(ActionHelper.waitForElementToBeVisible(driver, li_new_post_template_element, 15)) {
				lTitle = ActionHelper.getAttribute(li_new_post_template_element.findElement(By.xpath("/descendant::div/img")),"src");
				isVerified = lTitle.toLowerCase().contains("linkedin");
			}
			break;
		default:
			isVerified =true;
			break;
		}
		
		return isVerified;
	}
	public boolean isScheduled() {
		return ActionHelper.waitForElementToBeVisible(driver, scheduled_label, 5);
	}

	public boolean uploadPhoto(String pPlatform, String pPhotoPath) {
		boolean isTyped = false;
		String lImage_path = "";
		switch(pPlatform) {
		case "Facebook":
			if(ActionHelper.waitForElementToBeVisible(driver, fb_new_post_template_element, 15)) {
				isTyped = ActionHelper.TypeForUploadImage(driver, fb_new_post_template_element.findElement(By.id("post_image")),pPhotoPath);
				if(isTyped) {
					ActionHelper.staticWait(30);
					lImage_path = ActionHelper.getAttribute(fb_new_post_template_element.findElement(By.xpath("/descendant::div[@class='post-image']/img")),"src");
					isTyped = lImage_path.isEmpty()?false:true;
				}
			}
			break;
		case "Twitter":
			if(ActionHelper.waitForElementToBeVisible(driver, tw_new_post_template_element, 15)) {
				isTyped = ActionHelper.TypeForUploadImage(driver, tw_new_post_template_element.findElement(By.id("post_image")),pPhotoPath);
				if(isTyped) {
					ActionHelper.staticWait(30);
					lImage_path = ActionHelper.getAttribute(tw_new_post_template_element.findElement(By.xpath("/descendant::div[@class='post-image']/img")),"src");
					isTyped = lImage_path.isEmpty()?false:true;
				}
			}
			break;
		case "LinkedIn":
			if(ActionHelper.waitForElementToBeVisible(driver, li_new_post_template_element, 15)) {
				isTyped = ActionHelper.TypeForUploadImage(driver, li_new_post_template_element.findElement(By.id("post_image")),pPhotoPath);
				if(isTyped) {
					ActionHelper.staticWait(30);
					lImage_path = ActionHelper.getAttribute(li_new_post_template_element.findElement(By.xpath("/descendant::div[@class='post-image']/img")),"src");
					isTyped = lImage_path.isEmpty()?false:true;
				}
			}
			break;
		}
		return isTyped;
	}
	public boolean clickOnPostListingButton(String pPlatform) {
		boolean isClicked = false;
		switch(pPlatform) {
		case "Facebook":
			if(ActionHelper.waitForElementToBeVisible(driver, fb_new_post_template_element, 15)) {
				isClicked = ActionHelper.Click(driver, fb_new_post_template_element.findElement(By.xpath("/descendant::button[text()='Post Listing']")));
			}
			break;
		case "Twitter":
			if(ActionHelper.waitForElementToBeVisible(driver, tw_new_post_template_element, 15)) {
				isClicked = ActionHelper.Click(driver, tw_new_post_template_element.findElement(By.xpath("/descendant::button[text()='Post Listing']")));
				
			}
			break;
		case "LinkedIn":
			if(ActionHelper.waitForElementToBeVisible(driver, li_new_post_template_element, 15)) {
				isClicked = ActionHelper.Click(driver, li_new_post_template_element.findElement(By.xpath("/descendant::button[text()='Post Listing']")));
			}
			break;
		case "YouTube":
			if(ActionHelper.waitForElementToBeVisible(driver, yt_new_post_template_element, 15)) {
				isClicked = ActionHelper.Click(driver, yt_new_post_template_element.findElement(By.xpath("/descendant::button[text()='Post Listing']")));
			}
			break;
		}
		return isClicked;
	}
	public boolean selectTheListing() {
		boolean isListingSelected = false;
		if(selectListingAlert.isSelectListingAlert()) {
			isListingSelected = selectListingAlert.selectTheListingFromDropdown();
			if(isListingSelected) {
				isListingSelected = selectListingAlert.clickOnOkButton();
			}
		}
		return isListingSelected;
	}
	public String getTextFromTextArea(String pPlatform) {
		String listing_text = "";	
		switch(pPlatform) {
		case "Facebook":
			if(ActionHelper.waitForElementToBeVisible(driver, fb_new_post_template_element, 15)) {
				listing_text = ActionHelper.getTextByValue(driver, fb_new_post_template_element.findElement(By.id("post_text")));
			}
			break;
		case "Twitter":
			if(ActionHelper.waitForElementToBeVisible(driver, tw_new_post_template_element, 15)) {
				listing_text = ActionHelper.getTextByValue(driver, tw_new_post_template_element.findElement(By.id("post_text")));
			}
			break;
		case "LinkedIn":
			if(ActionHelper.waitForElementToBeVisible(driver, li_new_post_template_element, 15)) {
				listing_text = ActionHelper.getTextByValue(driver, li_new_post_template_element.findElement(By.id("post_text")));
			}
			break;
		}
		return listing_text;
	}
}
