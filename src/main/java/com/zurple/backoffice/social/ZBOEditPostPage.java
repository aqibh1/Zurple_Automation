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

/**
 * @author adar
 *
 */

////////////NOT USED ANYMORE/////////////////

public class ZBOEditPostPage extends Page{
	
//	@FindBy(xpath="//h3[text()='Edit post']")
//	WebElement editPost_heading;
	
	@FindBy(className="post-text")
	WebElement post_text_area;
	
//	@FindBy(className="link-preview-hostname")
//	WebElement linkURL;
	
	String linkURL = "link-preview-hostname";
	
	String edit_button = "edit-button";
	String post_text = "post-text";
	int postIndex = 0;
	
	public ZBOEditPostPage() {
		
	}
	public ZBOEditPostPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isEditPostPage() {
		ActionHelper.staticWait(2);
		if(ActionHelper.ClickByIndex(driver, edit_button, postIndex)) {
			return true;
		} else {
			return false;
		}
	}
	public boolean verifyPost(String pPostText) {
		boolean isPostFound = false;
		String actualPostText = "";
		List<WebElement> postData = ActionHelper.getListOfElementByClassName(driver, post_text);
		for(WebElement elem:postData) {
			actualPostText = ActionHelper.getText(driver, elem);
			if(actualPostText.contains(pPostText)) {
				postIndex = postData.indexOf(elem);
				isPostFound = true;
				break;
			}				
		}
		return isPostFound; 
	}
	
	public boolean isListingWebsiteUrlDisplaying(String pPostToVerify, String pExpectedURL) {
		String actualURL = ActionHelper.getTextByIndex(driver, linkURL, postIndex);
		if(pExpectedURL.contains(actualURL.toLowerCase())) {
			return true;
		} else {
			return false;
		}
	}
}
