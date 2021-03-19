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
	
	//Active Property
	@FindBy(xpath="//div[@class='panel-heading']/h3[text()='Active Listings']")
	WebElement active_listing_heading;
	@FindBy(id="name")
	WebElement address_Active_leads;
	@FindBy(xpath="//form[@id='active-listings-form']/descendant::select[@id='beds']")
	WebElement al_beds;
	@FindBy(xpath="//form[@id='active-listings-form']/descendant::select[@id='baths']")
	WebElement al_baths;
	@FindBy(xpath="//form[@id='active-listings-form']/descendant::strong[text()='Address']/parent::label/input")
	WebElement active_listing_address_checkbox;
	@FindBy(xpath="//form[@id='active-listings-form']/descendant::strong[text()='City']/parent::label/input")
	WebElement active_listing_city_checkbox;
	String add_button_active_listings = "//table[@id='propertiesGrid']/descendant::span[@class='not-added-property']";
	
	//Sold Property
	@FindBy(xpath="//div[@class='panel-heading']/h3[text()='Sold Properties']")
	WebElement sold_properties_heading;
	String add_button_sold_listings = "//form[@id='sold-listings-form']/descendant::span[@class='not-added-property']";
	
	@FindBy(id="cma-form-submit")
	WebElement submit__button;

	//ALerts
	@FindBy(xpath="//div[@role='alert']/strong[text()='Please enter Leads Address']")
	WebElement lead_address_alert;
	@FindBy(xpath="//div[@role='alert']/strong[text()='Please enter Leads state']")
	WebElement lead_state_alert;
	@FindBy(xpath="//div[@role='alert']/strong[text()='Please enter Leads Zip Code']")
	WebElement lead_zip_code_alert;
	@FindBy(xpath="//div[@role='alert']/strong[text()='Please enter Leads City']")
	WebElement lead_city_alert;
	
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
	}public boolean verifyActivePropertyHeadingIsVisible() {
		return ActionHelper.isElementVisible(driver, active_listing_heading);
	}public boolean verifySoldPropertyHeadingIsVisible() {
		return ActionHelper.isElementVisible(driver, sold_properties_heading);
	}
	
	public boolean verifyAddress(String pAddress) {
		return ActionHelper.getValue(driver, address).contains(pAddress);
	}public boolean verifyCity(String pCity) {
		return ActionHelper.getValue(driver, city).contains(pCity);
	}public boolean verifyState(String pState) {
		return ActionHelper.getValue(driver, state).contains(pState);
	}public boolean verifyZip(String pZip) {
		return ActionHelper.getValue(driver, zip).contains(pZip);
	}public boolean verifyBeds(String pBed) {
		return ActionHelper.getValue(driver, beds).contains(pBed);
	}public boolean verifyBaths(String pBaths) {
		return ActionHelper.getValue(driver, baths).contains(pBaths);
	}
	
	public String getActiveListingAddress() {
		return ActionHelper.getValue(driver, address_Active_leads);
	}
	public String getActiveListingBeds() {
		return ActionHelper.getSelectedOption(driver, al_beds, "//form[@id='active-listings-form']/descendant::select[@id='beds']/option");
	}public String getActiveListingBaths() {
		return ActionHelper.getSelectedOption(driver, al_baths, "//form[@id='active-listings-form']/descendant::select[@id='baths']/option");
	}public boolean isALAddressCheckboxChecked() {
		return ActionHelper.isElementSelected(driver, active_listing_address_checkbox);
	}public boolean isALCityCheckboxChecked() {
		return ActionHelper.isElementSelected(driver, active_listing_city_checkbox);
	}
	
	public boolean clickAddButtonFor3ActiveListing() {
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, add_button_active_listings);
		for(WebElement element: list_element) {
			if(!ActionHelper.Click(driver, element)) {
				return false;
			}
		}
		return true;
	}public boolean clickAddButtonFor3SoldListing() {
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, add_button_sold_listings);
		for(WebElement element: list_element) {
			if(!ActionHelper.Click(driver, element)) {
				return false;
			}
		}
		return true;
	}
	public boolean clickOnSubmitButton() {
		return ActionHelper.Click(driver, submit__button);
	}
	public boolean isAddressAlertVisible() {
		return ActionHelper.isElementVisible(driver, lead_address_alert);
	}public boolean isStateAlertVisible() {
		return ActionHelper.isElementVisible(driver, lead_state_alert);
	}public boolean isZipAlertVisible() {
		return ActionHelper.isElementVisible(driver, lead_zip_code_alert);
	}public boolean isCityAlertVisible() {
		return ActionHelper.isElementVisible(driver, lead_city_alert);
	}
	
	public boolean typeMinPrice(String pPrice) {
		return ActionHelper.Type(driver, min_price, pPrice);
	}public boolean typeMaxPrice(String pPrice) {
		return ActionHelper.Type(driver, max_price, pPrice);
	}
}
