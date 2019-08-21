/**
 * 
 */
package com.zurple.website;

import java.util.List;

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
public class ZWHomesForSalePage extends Page{

	@FindBy(xpath="//h2[contains(text(),'Homes for Sale')]")
	WebElement homesForSale_heading;
	
	@FindBy(xpath="//div[@class='col-md-12 col-sm-12 col-xs-12']")
	WebElement total_listings;
	
	@FindBy(xpath="//form/div[contains(text(),'Filter By:')]")
	WebElement filterBy;
	
	String nav_tabs = "//ul[@class='nav nav-pills']/descendant::a";
	
	@FindBy(xpath="//p[@class='top-listing-counter-block no-margin-bottom']")
	WebElement showingListingCount;
	
	String allListings_xpath = "//div[@class='row property-photo-grid']/descendant::a";
	
	public ZWHomesForSalePage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean isHomeForSalePage() {
		return ActionHelper.waitForElementToBeVisible(driver, homesForSale_heading, 20);
	}
	
	public int getTotalListings() {
		String lTemp = ActionHelper.getText(driver, total_listings).split("\\(")[1].split(" ")[0];
		return Integer.parseInt(lTemp.trim());
	}
	
	public boolean verifyNavigationTabs() {
		boolean isFound = false;
		String [] navigation_array = {"Price","My Favorites","Popularity","Sq. Ft.","Bdrms","Newest"};
		List<WebElement> list_of_element = ActionHelper.getListOfElementByXpath(driver, nav_tabs);
		for(String nav: navigation_array) {
			isFound = false;
			for(WebElement element: list_of_element) {
				if(nav.contains(element.getText().trim())) {
					isFound = true;
					break;
				}
			}
			if(!isFound) {
				break;
			}
		}
		return isFound;
	}
	public int getPageNumOfProps() {
		String lText =ActionHelper.getText(driver, showingListingCount).split(" ")[3];
		return Integer.parseInt(lText);
	}
	
	public boolean clickOnListing(int pIndex) {
		List<WebElement> list_of_props = ActionHelper.getListOfElementByXpath(driver, allListings_xpath);
		return ActionHelper.Click(driver, list_of_props.get(pIndex));
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
