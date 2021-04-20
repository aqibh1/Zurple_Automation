/**
 * 
 */
package com.zurple.backoffice.social;

import java.util.List;

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
	String post_processing_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::span[contains(@class,'post-processing-icon')]";
	String camera_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'camera-icon')]";
	String photo_post_text = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[text()='Manual Photo Post']";
	String home_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'home-icon')]";
	String listing_post_text = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[text()='Manual Listing Post']";
	String listing_video_post_text = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[text()='Manual Listing Video Post']";
	String video_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'listing-video-icon')]";
	String computer_icon = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[contains(@class,'computer-icon')]";
	String link_post_text = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::div[text()='Manual Link Post']";
	String listing_URL = "//p[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]/ancestor::div[@class='post-container col-md-12']/descendant::a[contains(@class,'link-preview-hostname')]";
	String iFrame_post_text = "//div[@id='app']/descendant::span[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";	
	String post_text = "post-text";
	
	@FindBy(className="post-text")
	WebElement post_text_element;
		
	private int postIndex = 0;

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
			if(ActionHelper.getDynamicElementAfterRegularIntervals(driver, fb_post_platform_icon, pPostToVerify,10)) {
				element = ActionHelper.getDynamicElement(driver, fb_post_platform_icon, pPostToVerify);
				if(element!=null) {
					isVisible = ActionHelper.isElementVisible(driver, element);
				}
			}
			break;
		case "Twitter":
			if(ActionHelper.getDynamicElementAfterRegularIntervals(driver, tw_post_platform_icon, pPostToVerify,10)) {
				element = ActionHelper.getDynamicElement(driver, tw_post_platform_icon, pPostToVerify);
				if(element!=null) {
					isVisible = ActionHelper.isElementVisible(driver, element);
				}
			}
			break;
		case "LinkedIn":
			if(ActionHelper.getDynamicElementAfterRegularIntervals(driver, li_post_platform_icon, pPostToVerify,10)) {
				element = ActionHelper.getDynamicElement(driver, li_post_platform_icon, pPostToVerify);
				if(element!=null) {
					isVisible = ActionHelper.isElementVisible(driver, element);
				}
			}
			break;
		case "YouTube":
			if(ActionHelper.getDynamicElementAfterRegularIntervals(driver, yt_post_platform_icon, pPostToVerify,10)) {
				element = ActionHelper.getDynamicElement(driver, yt_post_platform_icon, pPostToVerify);
				if(element!=null) {
					isVisible = ActionHelper.isElementVisible(driver, element);
				}
			}
		}
		return isVisible;
	}
	
	public boolean getPostPageTitle(String pPostToVerify) {
		boolean isPostFound = false;
		String actualPostText = "";
		ActionHelper.waitForElementToBeVisible(driver, post_text_element,30);
		List<WebElement> postData = ActionHelper.getListOfElementByClassName(driver, post_text);
		for(WebElement elem:postData) {
			actualPostText = ActionHelper.getText(driver, elem);
			if(actualPostText.contains(pPostToVerify)) {
				postIndex = postData.indexOf(elem);
				isPostFound = true;
				break;
			}				
		}
		return isPostFound; 
		
		
//		String lPost_title = "";	
//		boolean element = ActionHelper.getDynamicElementAfterRegularIntervals(driver, fb_post_page_title, pPostToVerify,10);
//		if(element) {
//			return true;
//		} else {
//			return false;
//		}
		
//		if(element!=null) {
//			lPost_title = ActionHelper.getText(driver, element);
//		}
	//	return isElementFound;
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
	public boolean verifyDuplicatePostButtonIsWorking(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, post_xpath, pPostToVerify);
		if(element!=null) {
			ActionHelper.waitForElementToBeVisible(driver, element.findElement(By.id("duplicate-post")), 30);
			ActionHelper.Click(driver, element.findElement(By.id("duplicate-post")));
			ActionHelper.staticWait(5);
			isVisible = driver.getCurrentUrl().contains("duplicatepost");
			
		}
		return isVisible;
	}
	public boolean isPostProcessingiconVisible(String pPostToVerify) {
		boolean isDisappeared = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, post_processing_icon, pPostToVerify);
		if(element!=null) {
			isDisappeared = ActionHelper.waitforElementToBeDisappearedByRegularIntervals(driver, element, 20, 10);
		}
		return isDisappeared;
	}
	public boolean isPhotoPostIconVisible(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, camera_icon, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.isElementVisible(driver, element);
		}
		return isVisible;
	}
	public boolean isImageDisplaying(String pPlatform, String pPostToVerify) {
		boolean isVisible = false;
		String postSource = "";
		WebElement element,e;
		switch(pPlatform) {
		case "Facebook":
			element = ActionHelper.getDynamicElement(driver, post_xpath, pPostToVerify);
			if(element!=null) {
				e = element.findElement(By.xpath("/descendant::div[@class='post-image']/descendant::img[contains(@src,'fbcdn')]"));
				postSource = ActionHelper.getAttribute(e, "src");
				if(!postSource.isEmpty()) {
					isVisible = true;
				} else {
					isVisible = false;
				}
			}
			break;
		case "Twitter":
			element = ActionHelper.getDynamicElement(driver, post_xpath, pPostToVerify);
			if(element!=null) {
				isVisible = ActionHelper.getAttribute(element.findElement(By.xpath("/descendant::div/img")), "src").contains("s3.amazonaws");
			}
			break;
		case "LinkedIn":
			element = ActionHelper.getDynamicElement(driver, post_xpath, pPostToVerify);
			if(element!=null) {
				isVisible = ActionHelper.getAttribute(element.findElement(By.xpath("/descendant::div/img")), "src").contains("s3.amazonaws");
			}
			break;
		}
		return isVisible;
	}
	public boolean isManualPhotPostTextVisible(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, photo_post_text, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.isElementVisible(driver, element);
		}
		return isVisible;
	}
	public boolean isHomePostIconVisible(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, home_icon, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.isElementVisible(driver, element);
		}
		return isVisible;
	}
	public boolean isManualListingPostTextVisible(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, listing_post_text, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.isElementVisible(driver, element);
		}
		return isVisible;
	}
	public boolean isListingWebsiteUrlDisplaying(String pPostToVerify, String pDomainToVerify) {
		String isVisible = "";	
		WebElement element;
		ActionHelper.staticWait(3);
		element = ActionHelper.getDynamicElement(driver, listing_URL, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.getText(driver,element);
		}
		return pDomainToVerify.contains(isVisible.toLowerCase());
	}
	public boolean isListingHeadingVisible(String pPostToVerify) {
		String isVisible = "";	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, post_xpath, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.getText(driver, element.findElement(By.xpath("/descendant::p[contains(@class,'link-preview-title')]")));
		}
		return isVisible.equalsIgnoreCase("New Listing on the Market");
	}
	public boolean isListingDescVisible(String pPostToVerify) {
		String isVisible = "";	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, post_xpath, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.getText(driver, element.findElement(By.xpath("/descendant::p[contains(@class,'link-preview-description')]")));
		}
		return isVisible.contains("Check out this listing");
	}
	public boolean isHomePostListingVideoIconVisible(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, video_icon, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.isElementVisible(driver, element);
		}
		return isVisible;
	}
	public boolean isManualListingVideoPostTextVisible(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, listing_video_post_text, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.isElementVisible(driver, element);
		}
		return isVisible;
	}
	public boolean isPostComputerIconVisible(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, computer_icon, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.isElementVisible(driver, element);
		}
		return isVisible;
	}
	public boolean isManualLinkPostTextVisible(String pPostToVerify) {
		boolean isVisible = false;	
		WebElement element;
		element = ActionHelper.getDynamicElement(driver, link_post_text, pPostToVerify);
		if(element!=null) {
			isVisible = ActionHelper.isElementVisible(driver, element);
		}
		return isVisible;
	}
	public boolean isTwitterVideoTextVisible(String pTextToVerify) {
		boolean isFound = false;
		List<WebElement> frames_list = ActionHelper.getListOfElementByXpath(driver, "//iframe[@id]");
		for(int i = 0;i<5;i++) {
			frames_list = ActionHelper.getListOfElementByXpath(driver, "//iframe[@id]");
			//Because latest one will always be on index 0;
			String l_frameId = ActionHelper.getAttribute(frames_list.get(i), "id");
			ActionHelper.switchToiFrame(driver, l_frameId);
			if(ActionHelper.getDynamicElementAfterRegularIntervals(driver, iFrame_post_text, pTextToVerify, 5)) {
				isFound = true;
				break;
			}
		}
		return isFound;
	}
}
