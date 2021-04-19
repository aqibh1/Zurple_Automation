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
public class ZBODuplicatePage extends Page{
	
	@FindBy(xpath="//h3[text()='Duplicate post']")
	WebElement duplicatePost_heading;

	@FindBy(className="link-preview-description")
	WebElement post_text_area;
	
	public ZBODuplicatePage() {
		
	}
	public ZBODuplicatePage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isDuplicatePostPage() {
		return ActionHelper.waitForElementToBeVisible(driver, duplicatePost_heading, 30);
	}
	public boolean verifyPost(String pPostText) {
		boolean isVerified = false;
		if(ActionHelper.getText(driver, post_text_area).contains(pPostText)) {
			isVerified = true;
		}
		return isVerified;
	}
}
