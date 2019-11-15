/**
 * 
 */
package com.zurple.backoffice;

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
public class ZBOSendEmailPage extends Page{
	
	@FindBy(xpath="//div[@class='container page-container']/descendant::h1[text()='Email']")
	WebElement email_heading;
	
	@FindBy(id="preview_button")
	WebElement preview_button;
	
	@FindBy(id="preview_box")
	WebElement preview_box;
	
	public ZBOSendEmailPage() {
		
	}
	public ZBOSendEmailPage(WebDriver pWebDriver) {
		driver =pWebDriver;
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean isSendEmailPage() {
		return ActionHelper.waitForElementToBeVisible(driver, email_heading, 30);
	}
	public boolean clickOnPreviewButton() {
		return ActionHelper.Click(driver, preview_button);
	}
	public boolean isPreviewBoxVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, preview_box, 15);
	}
}
