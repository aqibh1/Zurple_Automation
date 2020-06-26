/**
 * 
 */
package com.zurple.backoffice.social;

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
public class ZBOEditPostPage extends Page{
	
	@FindBy(xpath="//h3[text()='Edit post']")
	WebElement editPost_heading;

	@FindBy(id="post_text")
	WebElement post_text_area;
	
	public ZBOEditPostPage() {
		
	}
	public ZBOEditPostPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isEditPostPage() {
		return ActionHelper.waitForElementToBeVisible(driver, editPost_heading, 30);
	}
	public boolean verifyPost(String pPostText) {
		boolean isVerified = false;
		if(ActionHelper.getTextByValue(driver, post_text_area).contains(pPostText)) {
			isVerified = true;
		}
		return isVerified;
	}
}
