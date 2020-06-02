/**
 * 
 */
package com.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import us.zengtest1.Page;

/**
 * @author adar
 *
 */
public class ZWCommunityReportsPage extends Page{

	@Override
	public WebElement getHeader() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement getBrand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebElement getTopMenu() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@FindBy(xpath="//h2[contains(text(),'Community Reports')]")
	WebElement communityReports_heading;
	
	@FindBy(id="community")
	WebElement community_input;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement search_button;
	
	public ZWCommunityReportsPage() {
		
	}
	public ZWCommunityReportsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isCommunityReportsPage() {
		return ActionHelper.waitForElementToBeVisible(driver, communityReports_heading, 20);
	}
	public boolean typeZip(String pZipCode) {
		return ActionHelper.ClearAndType(driver, community_input, pZipCode);
	}
	public boolean clickOnSearchButton() {
		return ActionHelper.Click(driver, search_button);
	}
}
