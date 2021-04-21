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
public class ZWPointsOfIntrestPage extends Page{
	
	@FindBy(xpath="//h2[contains(text(),'Points of Interest')]")
	WebElement POI_heading;
	
	@FindBy(id="community")
	WebElement community_input;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement search_button;

	@FindBy(xpath="//div/h3[text()='No Results Found']")
	WebElement no_Results_found;
	
	public ZWPointsOfIntrestPage() {
		
	}
	public ZWPointsOfIntrestPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isPOIReportsPage() {
		return ActionHelper.waitForElementToBeVisible(driver, POI_heading, 20);
	}
	public boolean typeZip(String pZipCode) {
		return ActionHelper.ClearAndType(driver, community_input, pZipCode);
	}
	public boolean clickOnSearchButton() {
		return ActionHelper.Click(driver, search_button);
	}
	public boolean goToPOIReportsFromHeaders() {
		ZurpleWebsiteHeader webHeader = new ZurpleWebsiteHeader(driver);
		return webHeader.goToPOIlReportsPage();
	}
	public boolean isNoResultsFoundVisible() {
		return ActionHelper.isElementVisible(driver, no_Results_found);
	}
	
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

}
