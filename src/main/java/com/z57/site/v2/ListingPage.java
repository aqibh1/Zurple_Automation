/**
 * 
 */
package com.z57.site.v2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.blocks.z57.Pagination;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class ListingPage extends Page{
	Pagination pagination;
	@FindBy(xpath="//div[@id='googleMap']")
	WebElement googleMap;
	
	@FindBy(xpath="//h1[@class='entry-title title_prop']")
	WebElement pageTitle;
	
	public ListingPage(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);
		setPagination();
	}

	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination() {
		pagination = new Pagination(driver);
	}
	public boolean isGoogleMapDisplayed() {
		return actionHelper.waitForElementToBeVisible(googleMap, 20);
	}
	
	public boolean isListingPage() {
		boolean result = actionHelper.getText(pageTitle).equalsIgnoreCase(FrameworkConstants.FeaturedPropertiesPageTitle)?true:false;
		return result;
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

}
