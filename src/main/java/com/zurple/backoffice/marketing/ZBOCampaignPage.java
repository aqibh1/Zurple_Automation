/**
 * 
 */
package com.zurple.backoffice.marketing;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.forms.zurple.backoffice.ZBOAddTemplateForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class ZBOCampaignPage extends Page{
	
	@FindBy(xpath="//h3[text()='Campaign Manager']")
	WebElement campaign_manager_heading;
	
	@FindBy(id="campaign-create-button")
	WebElement create_button;
	
	public ZBOCampaignPage() {
		
	}
	public ZBOCampaignPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isCampaignPage() {
		return ActionHelper.waitForElementToBeVisible(driver, campaign_manager_heading, 15);
	}
	public boolean clickOnCreateCampaignButton() {
		return ActionHelper.Click(driver, create_button);
	}
}
