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
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class PPSocialAutoPoster extends Page{

	@FindBy(xpath="//h1[text()='Social Auto-Poster']")
	WebElement socialAutoPoster_heading;
	
	@FindBy(xpath="//label[@class='radio inline']/input[@value='promote_website']")
	WebElement promoteWebsite_radio;
	
	@FindBy(xpath="//label[@class='radio inline']/input[@value='promote_fbtab']")
	WebElement promoteTab_radio;
	
	@FindBy(xpath="//label[@class='radio inline']/input[@value='rss_feed']")
	WebElement rssFeed_radio;
	
	@FindBy(xpath="//input[@name='new_post_form[post_link_name]']")
	WebElement websiteName_input;
	
	@FindBy(xpath="//input[@name='new_post_form[post_link]']")
	WebElement websiteUrl_input;
	
	@FindBy(xpath="//input[@name='new_post_form[message]']")
	WebElement message_input;
	
	@FindBy(id="s2id_new_post_select_fb_page")
	WebElement fb_page_input;
	
	String fb_page_dd = "//div[@id='select2-drop']/descendant::div[@class='select2-result-label']";
	
	@FindBy(xpath="//select[@name='new_post_form[select_weekly_monthly]']")
	WebElement weeklyMonthly_select;
	
	String weekly_days = "//label[@class='z57-theme-no-margin-mobile checkbox inline weekday']/input[@value='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	String selectTime = "//label/following-sibling::table[@class='table span11 time-table z57-theme-table-responsive ']/descendant::td[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(id="new_post_image")
	WebElement post_image_upload;
	
	@FindBy(id="new_post_button")
	WebElement new_Post_button;
	
	@FindBy(id="save_new_post")
	WebElement save_button;
	
	String post_xpath = "//div[@class='slider-container closed row-fluid']/descendant::p[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	   
	@FindBy(xpath="//li[@class='next']/a")
	WebElement next_button;
	
	public PPSocialAutoPoster() {
		// TODO Auto-generated constructor stub
	}
	public PPSocialAutoPoster(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);		
	}
	public boolean isSocialAutoPosterPage() {
		return ActionHelper.waitForElementToBeVisible(driver, socialAutoPoster_heading, 30);
	}
	
	public boolean clickOnInputRadioButton(String pInputRadio) {
		boolean isSuccess = false;
		switch(pInputRadio) {
		case "Promote Website":
			isSuccess = ActionHelper.Click(driver, promoteWebsite_radio);
			break;

		case "Promote Tab":
			isSuccess = ActionHelper.Click(driver, promoteTab_radio);
			break;

		case "RSS Feed":
			isSuccess = ActionHelper.Click(driver, rssFeed_radio);
			break;

		default:
			isSuccess = ActionHelper.Click(driver, promoteWebsite_radio);
			break;
		}
		return isSuccess;
	}
	public boolean typeWebSiteName(String pWebsiteName) {
		return ActionHelper.Type(driver, websiteName_input, pWebsiteName);
	}
	public boolean typeWebSiteUrl(String pWebsiteUrl) {
		return ActionHelper.Type(driver, websiteUrl_input, pWebsiteUrl);
	}
	public boolean typeWebsiteMessage(String pMessage) {
		return ActionHelper.Type(driver, message_input, pMessage);
	}
	public boolean selectFacebookPage(String pFacebookPage) {
		return selectDropDownOption(driver, fb_page_input, fb_page_dd, pFacebookPage);
	}
	public boolean selectPostingSchedule(String pOption) {
		return selectDropDownOption(driver, weeklyMonthly_select, "", pOption);
	}
	public boolean selectDays(String[] pDays) {
		boolean isSuccess = true;
		for(String day: pDays) {
			isSuccess = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, weekly_days, day));
			if(!isSuccess) {
				return isSuccess;
			}
		}
		return isSuccess;
	}
	public boolean selectTime(String[] pTime) {
		boolean isSuccess = false;
		for(String time: pTime) {
			List<WebElement> list_elements = ActionHelper.getListOfElementByXpath(driver, ActionHelper.getDynamicElementXpath(driver, selectTime, time));
			isSuccess = ActionHelper.Click(driver, list_elements.get(0));
		}
		return isSuccess;
	}
	public boolean uploadImage(String pImagePath) {
		boolean isSuccess = true;
		pImagePath = "\\resources\\images\\img_service_luxuryhome.jpg";
		try {
			pImagePath = System.getProperty("user.dir")+pImagePath;
			post_image_upload.sendKeys(pImagePath);
		}catch(Exception ex) {
			isSuccess = false;
		}
		return isSuccess;//ActionHelper.Type(driver, post_image_upload, pImagePath);
	}
	
	public boolean clickOnNewPostButton() {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, new_Post_button)) {
			isSuccess = ActionHelper.waitForElementToBeVisible(driver, websiteName_input, 15);
		}
		return isSuccess;
	}
	public boolean clickSaveButton() {
		return ActionHelper.Click(driver, save_button);
	}
	
	public boolean verifyAutoPost(String pPostTitle) {
		boolean isFound = false;
		do {
			isFound = ActionHelper.isElementVisible(driver, ActionHelper.getDynamicElement(driver, post_xpath, pPostTitle));
			if(isFound) {
				break;
			}
		}while(ActionHelper.isElementToBeClickAble(driver, next_button));
		return isFound;
	}
	public static boolean selectDropDownOption(WebDriver pWebDriver, WebElement pElementToBeClicked,String pDropdownOptionsXpath, String pOptionToSelect) {
		boolean isSuccessful=false;
		List<WebElement> list_of_options = new ArrayList<WebElement>();
		 AutomationLogger.info("Clicking on button "+pElementToBeClicked);
		if(ActionHelper.Click(pWebDriver, pElementToBeClicked)) {
			if(pDropdownOptionsXpath.isEmpty()) {
				 list_of_options = pElementToBeClicked.findElements(By.tagName("option"));
			}else {
				 list_of_options = pWebDriver.findElements(By.xpath(pDropdownOptionsXpath));
			}	
			AutomationLogger.info("Selecting a option from Dropdown "+pDropdownOptionsXpath);
			for(WebElement element: list_of_options) {
				System.out.println(element.getText().trim());
				if(element.getText().trim().equalsIgnoreCase(pOptionToSelect)) {
					isSuccessful = ActionHelper.Click(pWebDriver, element);
					break;
				}
			}
		}
		return isSuccessful;
	}
}
