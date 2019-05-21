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
public class PPWebsitePage extends Page{
	
	@FindBy(xpath="//div[@class='wrap']/descendant::h1[text()='Pages ']")
	WebElement page_heading;
	
	@FindBy(xpath="//h1/a[text()='Add New']")
	WebElement addNew_button;
	
	@FindBy(id="post-search-input")
	WebElement searchPage_input;
	
	@FindBy(id="search-submit")
	WebElement search_button;
	
	String pageRow = "//strong/a[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(id="message")
	WebElement message_label;
	
	public PPWebsitePage() {
		
	}
	public PPWebsitePage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isWebSitePage() {
		return ActionHelper.waitForElementToBeVisible(driver, page_heading, 30);
	}
	public boolean clickOnAddNewButton() {
		return ActionHelper.Click(driver, addNew_button);
	}
	public boolean typeInSearchField(String pStringToSearch) {
		return ActionHelper.Type(driver, searchPage_input, pStringToSearch);
	}
	public boolean clickOnSearchButton() {
		return ActionHelper.Click(driver, search_button);
	}
	public boolean clickOnRowPage(String pPageName) {
		boolean isSuccessful = false;
		String lDynamicElement_xpath = ActionHelper.getDynamicElementXpath(driver, pageRow, pPageName);
		if(ActionHelper.waitForElementToBeLocated(driver, lDynamicElement_xpath, 30)) {
			WebElement lDynamicElement =  ActionHelper.getDynamicElement(driver, pageRow, pPageName);
			isSuccessful = ActionHelper.Click(driver, lDynamicElement);
		}
		return isSuccessful;
		
	}
	public boolean isPageDeletedSuccessfully() {
		return ActionHelper.waitForElementToBeVisible(driver, message_label, 30);
	}

}
