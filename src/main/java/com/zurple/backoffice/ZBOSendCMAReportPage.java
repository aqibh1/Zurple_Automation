/**
 * 
 */
package com.zurple.backoffice;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

/**
 * @author darrraqi
 *
 */
public class ZBOSendCMAReportPage extends Page{
	
	@FindBy(xpath="//h1[text()='CMA Report']")
	WebElement CMA_Report_title;
	
	//Property Section
	@FindBy(xpath="//h3[text()='Property']")
	WebElement property_heading;
	String lead_name = "//div[@class='panel-body']/div[@class='row']/div[@class='col-md-12']";
	@FindBy(id="street")
	WebElement address;
	@FindBy(id="city")
	WebElement city;
	@FindBy(id="state")
	WebElement state;
	@FindBy(id="zip")
	WebElement zip;
	@FindBy(id="beds")
	WebElement beds;
	@FindBy(id="baths")
	WebElement baths;
	@FindBy(id="min_price")
	WebElement min_price;
	@FindBy(id="max_price")
	WebElement max_price;
	
	public ZBOSendCMAReportPage(WebDriver pWebdriver){
		driver = pWebdriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isCMAReportHeadingVisible() {
		return ActionHelper.isElementVisible(driver, CMA_Report_title);
	}
	public boolean isLeadNameVisible(String pLeadName) {
		boolean isLeadNameFound = false;
		List<WebElement> list = ActionHelper.getListOfElementByXpath(driver, lead_name);
		for(WebElement element: list) {
			if(ActionHelper.getText(driver, element).contains(pLeadName)) {
				isLeadNameFound = true;
				break;
			}
		}
		return isLeadNameFound;
	}
	public boolean isAddressInputVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, address, 5);
	}public boolean isCityInputVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, city, 5);
	}public boolean isStateInputVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, state, 5);
	}public boolean isZipInputVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, zip, 5);
	}public boolean isBedsInputVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, beds, 5);
	}public boolean isBathsInputVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, baths, 5);
	}public boolean isMinPriceInputVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, min_price, 5);
	}public boolean isMaxPriceInputVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, max_price, 5);
	}
	public boolean verifyPropertyHeadingIsVisible() {
		return ActionHelper.isElementVisible(driver, property_heading);
	}
}
