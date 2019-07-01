/**
 * 
 */
package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
	
	@FindBy(xpath="//div[@class='wp-menu-name' and text()='Appearance']")
	WebElement appearanceSideMenu;
	
	@FindBy(xpath="//ul[@class='wp-submenu wp-submenu-wrap']/descendant::a[@href='widgets.php']")
	WebElement widgetSubmenu;
		
	public PPWebsitePage() {
		
	}
	public PPWebsitePage(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);
	}
	public boolean isWebSitePage() {
		return actionHelper.waitForElementToBeVisible(page_heading, 30);
	}
	public boolean clickOnAddNewButton() {
		return actionHelper.Click(addNew_button);
	}
	public boolean typeInSearchField(String pStringToSearch) {
		return actionHelper.Type(searchPage_input, pStringToSearch);
	}
	public boolean clickOnSearchButton() {
		return actionHelper.Click(search_button);
	}
	public boolean clickOnRowPage(String pPageName) {
		boolean isSuccessful = false;
		String lDynamicElement_xpath = actionHelper.getDynamicElementXpath(pageRow, pPageName);
		if(actionHelper.waitForElementToBeLocated(lDynamicElement_xpath, 30)) {
			WebElement lDynamicElement =  actionHelper.getDynamicElement(pageRow, pPageName);
			isSuccessful = actionHelper.Click(lDynamicElement);
		}
		return isSuccessful;
		
	}
	public boolean isPageDeletedSuccessfully() {
		return actionHelper.waitForElementToBeVisible(message_label, 30);
	}
	public boolean clickOnWidget() {
		boolean result = false;
		if(actionHelper.MouseHoverOnElement(appearanceSideMenu)) {
			result = actionHelper.Click(widgetSubmenu);
		}
		return result;
	}
	

}
