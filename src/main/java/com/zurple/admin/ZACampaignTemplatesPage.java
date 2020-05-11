/**
 * 
 */
package com.zurple.admin;

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
public class ZACampaignTemplatesPage extends Page{
	
	@FindBy(xpath="//h3[text()='Campaign Templates']")
	WebElement campaignTemplates_heading;
	
	@FindBy(xpath="//select[@name='campaign_template']")
	WebElement selectTemplate_dropdown;
	
	@FindBy(id="active_flag")
	WebElement active_checkbox;
	
	@FindBy(id="save")
	WebElement save_button;
	
	public ZACampaignTemplatesPage() {
		
	}
	public ZACampaignTemplatesPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isCampaignTemplatesPage() {
		return ActionHelper.waitForElementToBeVisible(driver, campaignTemplates_heading, 15);
	}
	public boolean selectTemplateToDeActive(String pOptionToSelect) {
		return ActionHelper.selectDropDownOption(driver, selectTemplate_dropdown, "", pOptionToSelect);
	}
	public boolean toggleCheckbox(boolean pToggle) {
		return ActionHelper.checkUncheckInputBox(driver, active_checkbox, pToggle);
	}
	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, save_button);
	}
}
