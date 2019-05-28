/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class PPEmailCampaignManagerPage extends Page{
	
	@FindBy(xpath="//h1[text()='Email Campaign Manager']")
	WebElement emailCampaignManagerHeading;
	
	@FindBy(xpath="//a[@class='btn dropdown-toggle btn-success']/i[@class='icon-plus']")
	WebElement createNewCampaign_button;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/li/a[text()='Listing']")
	WebElement dropDownMenuListing;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/li/a[text()='Custom Message']")
	WebElement dropDownMenuCustomMessage;
	
	@FindBy(xpath="//div[@id='scheduled_campaigns_table_filter']/descendant::input")
	WebElement search_input;
	
	String tableGrid_xpath = "//table[@id='scheduled_campaigns_table']/descendant::a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	
	public PPEmailCampaignManagerPage(){
		
	}
	public PPEmailCampaignManagerPage(WebDriver pWebDriver) {
		driver =  pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isEmailCampaignManagerPage() {
		return ActionHelper.waitForElementToBeVisible(driver, emailCampaignManagerHeading, 30);
	}
	public boolean clickOnListingSubMenu() {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, createNewCampaign_button)) {
			isSuccess = ActionHelper.Click(driver, dropDownMenuListing);
		}
		return isSuccess;
	}
	public boolean clickOnCustomSubMenu() {
		boolean isSuccess = false;
		if(ActionHelper.Click(driver, createNewCampaign_button)) {
			isSuccess = ActionHelper.Click(driver, dropDownMenuCustomMessage);
		}
		return isSuccess;
	}
	public boolean typeInSearch(String pSearchProp) {
		return ActionHelper.Type(driver, search_input, pSearchProp);
	}
	public boolean isListingScheduled(String pSearchProp) {
		return ActionHelper.waitForElementToBeLocated(driver, ActionHelper.getDynamicElementXpath(driver, tableGrid_xpath, pSearchProp), 15);
	}

}
