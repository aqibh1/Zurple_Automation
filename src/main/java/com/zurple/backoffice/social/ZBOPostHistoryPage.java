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

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class ZBOPostHistoryPage extends Page{
	@FindBy(xpath="//h3[text()='Post History']")
	WebElement post_history_heading;
	
	String post_xpath = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']";
	
	String fb_post_platform_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-facebook-network-icon')]";
	String tw_post_platform_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-twitter-network-icon')]";
	String li_post_platform_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-linkedin-network-icon')]";
	String yt_post_platform_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-youtube-network-icon')]";

	String fb_post_page_title = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::a[contains(@class,'page-title')]";
//	String tw_post_page_title = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::a[contains(@class,'page-title')]";
//	String li_post_page_title = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::a[contains(@class,'page-title')]";
//	String yt_post_page_title = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::a[contains(@class,'page-title')]";

	String fb_account_name = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::p[contains(@class,'account-name')]";
//	String tw_account_name = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::a[contains(@class,'account-name')]";
//	String li_account_name = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::a[contains(@class,'account-name')]";
//	String yt_account_name = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::a[contains(@class,'account-name')]";

	String fb_horn_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'horn-icon')]";
//	String tw_horn_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'horn-icon')]";
//	String li_horn_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'horn-icon')]";
//	String yt_horn_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'horn-icon')]";

	String fb_post_date = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-date')]";
//	String tw_post_date = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-date')]";
//	String li_post_date = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-date')]";
//	String yt_post_date = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-date')]";

	String fb_post_time = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-time')]";
//	String tw_post_time = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-time')]";
//	String _post_time = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-time')]";
//	String fb_post_time = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'post-time')]";

	String fb_manual_post_text = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[text()='Manual Status Post']";

	public ZBOPostHistoryPage() {
		
	}
	public ZBOPostHistoryPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isPostingHistoryPageIsVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, post_history_heading, 30);
	}
	public boolean verifyPlatformIconIsVisible(String pPlatform, String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		switch(pPlatform) {
		case "Facebook":
			if(ActionHelper.getDynamicElementAfterRegularIntervals(driver, fb_post_platform_icon, pPostToVerify,5)) {
				element = ActionHelper.getDynamicElement(driver, fb_post_platform_icon, pPostToVerify);
				if(element!=null) {
					isVisible = ActionHelper.isElementVisible(driver, element);
				}
			}
			break;
		case "Twitter":
			if(ActionHelper.getDynamicElementAfterRegularIntervals(driver, tw_post_platform_icon, pPostToVerify,5)) {
				element = ActionHelper.getDynamicElement(driver, tw_post_platform_icon, pPostToVerify);
				if(element!=null) {
					isVisible = ActionHelper.isElementVisible(driver, element);
				}
			}
			break;
		case "LinkedIn":
			if(ActionHelper.getDynamicElementAfterRegularIntervals(driver, li_post_platform_icon, pPostToVerify,5)) {
				element = ActionHelper.getDynamicElement(driver, li_post_platform_icon, pPostToVerify);
				if(element!=null) {
					isVisible = ActionHelper.isElementVisible(driver, element);
				}
			}
			break;
		case "YouTube":
			element = ActionHelper.getDynamicElement(driver, yt_post_platform_icon, pPostToVerify);
			if(element!=null) {
				isVisible = ActionHelper.isElementVisible(driver, element);
			}
			break;
		}
		return isVisible;
	}
	public String getPostPageTitle(String pPostToVerify) {
		String lPost_title = "";	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, fb_post_page_title, pPostToVerify);
		if(element!=null) {
			lPost_title = ActionHelper.getText(driver, element);
		}
		return lPost_title;
	}
	
	public String getPostAccountName(String pPostToVerify, String pPlatform) {
		String lPost_title = "";	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, fb_account_name, pPostToVerify);
		if(element!=null) {
			lPost_title = ActionHelper.getText(driver, element);
		}
		switch(pPlatform) {
		case "LinkedIn":
			lPost_title = "TRUE";
			break;
		case "YouTube":
			lPost_title = "TRUE";
			break;
		}
		return lPost_title;
	}
	
	public boolean isTextPostIconVisible(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, fb_horn_icon, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.isElementVisible(driver, element);
		}
		return isVisible;
	}
	public String getPostPageDate(String pPostToVerify) {
		String lPost_title = "";	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, fb_post_date, pPostToVerify);
		if(element!=null) {
			lPost_title = ActionHelper.getText(driver, element);
		}
		return lPost_title;
	}
	public String getPostPageTime(String pPostToVerify) {
		String lPost_title = "";	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, fb_post_time, pPostToVerify);
		if(element!=null) {
			lPost_title = ActionHelper.getText(driver, element);
		}
		return lPost_title;
	}
	public boolean isManualPostTextVisible(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, fb_manual_post_text, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.isElementVisible(driver, element);
		}
		return isVisible;
	}
	public boolean verifyViewPostButtonIsWorking(String pPlatform,String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, post_xpath, pPostToVerify);
		if(element!=null) {
			ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver, element.findElement(By.id("view-post")), 20, 10);
			ActionHelper.Click(driver, element.findElement(By.id("view-post")));
			ActionHelper.staticWait(5);
			ActionHelper.switchToSecondWindow(driver);
			isVisible = driver.getCurrentUrl().contains(pPlatform.toLowerCase());
			driver.close();
			ActionHelper.switchToOriginalWindow(driver);
			
		}
		return isVisible;
	}
	
}
