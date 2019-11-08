package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;

public class ZBOAddLeadPage extends Page{
	
	@FindBy(xpath="//form[@id='leadUpdateForm']/descendant::h1[text()='Create New Lead']")
	WebElement createNewLead_heading;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="first_name")
	WebElement first_name;
	
	@FindBy(id="last_name")
	WebElement last_name;
	
	@FindBy(id="phone")
	WebElement phone;
	
	@FindBy(id="cell")
	WebElement cell;
	
	@FindBy(id="seller_street")
	WebElement seller_street;
	
	@FindBy(id="seller_city")
	WebElement seller_city;
	
	@FindBy(id="seller_state")
	WebElement seller_state;
	
	@FindBy(id="seller_zip")
	WebElement seller_zip;
	
	@FindBy(id="beds")
	WebElement beds;
	
	@FindBy(id="baths")
	WebElement baths;
	
	@FindBy(id="sqft")
	WebElement sqft;
	
	@FindBy(xpath="//div[@id='form-element-city_id_0']/descendant::input")
	WebElement city_input;
	String city_xpath = "//ul[@id='select2-city_id_0-results']/li";
	
	@FindBy(xpath="//div[@id='form-element-zip_code']/descendant::input")
	WebElement zip_input;
	String zip_xpath = "//ul[@id='select2-zip_code-results']/li";	
	
	@FindBy(id="user_search_price_minimum")
	WebElement user_Search_minimum_price;
	
	@FindBy(id="user_search_price_maximum")
	WebElement user_search_price_maximum;
	
	@FindBy(id="user_search_bedrooms_minimum")
	WebElement user_search_bedrooms_minimum;
	
	@FindBy(id="user_search_bathrooms_minimum")
	WebElement user_search_bathrooms_minimum;
	
	@FindBy(id="user_search_square_feet_minimum")
	WebElement user_search_square_feet_minimum;
	
	@FindBy(id="user_search_lot_square_feet_minimum")
	WebElement user_search_lot_square_feet_minimum;
	
	String features_xpath = "//div[@id='form-element-features']/descendant::li";
	
	String view_xpath = "//div[@id='form-element-view']/descendant::li";
	
	String style_xpath = "//div[@id='form-element-style']/descendant::li";
	
	@FindBy(id="type_home")
	WebElement type_home;
	
	@FindBy(id="type_multi_family")
	WebElement type_multi_family;
	
	@FindBy(id="type_condo")
	WebElement type_condo;
	
	@FindBy(id="type_land")
	WebElement type_land;
	
	@FindBy(id="type_farm")
	WebElement type_farm;
	
	@FindBy(xpath="//h3[text()='Basic Information']")
	WebElement basic_information_title;
	
	@FindBy(xpath="//h3[text()='Location']")
	WebElement location_title;
	
	@FindBy(xpath="//h3[text()='Buyer Preferences']")
	WebElement buyer_preferences_title;
	
	@FindBy(xpath="//h3[text()='Property Types']")
	WebElement prop_types_title;
	
	@FindBy(xpath="//h3[text()='Notifications']")
	WebElement notifications_title;
	
	@FindBy(id="add-agent-button")
	WebElement save_button;
	
	public ZBOAddLeadPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean typeEmail(String pEmail) {
		return ActionHelper.ClearAndType(driver, email, pEmail);
	}
	public boolean typeFirstName(String pFirstName) {
		return ActionHelper.ClearAndType(driver, first_name, pFirstName);
	}
	public boolean typeLastName(String pLastName) {
		return ActionHelper.ClearAndType(driver, first_name, pLastName);
	}
	public boolean typePhone(String pPhone) {
		return ActionHelper.ClearAndType(driver, phone, pPhone);
	}
	public boolean typeCell(String pCell) {
		return ActionHelper.ClearAndType(driver, cell, pCell);
	}
	public boolean typeSellerStreet(String pStreet) {
		return ActionHelper.ClearAndType(driver, cell, pStreet);
	}
	public boolean typeCity(String pCity) {
		return ActionHelper.ClearAndType(driver, seller_city, pCity);
	}
	public boolean typeState(String pState) {
		return ActionHelper.ClearAndType(driver, seller_state, pState);
	}
	public boolean typeZip(String pZip) {
		return ActionHelper.ClearAndType(driver, seller_zip, pZip);
	}
	public boolean selectBed(String pBed) {
		return ActionHelper.selectDropDownOption(driver, beds, "", pBed);
	}
	public boolean selectBath(String pBaths) {
		return ActionHelper.selectDropDownOption(driver, baths, "", pBaths);
	}
	public boolean selectSqFeet(String pSqFeet) {
		return ActionHelper.selectDropDownOption(driver, sqft, "", pSqFeet);
	}
	
	public boolean selectCity(String pCity) {
		return ActionHelper.ClickAndSelect(driver, city_input, city_xpath, pCity);
	}
	public boolean selectZip(String pZip) {
		return ActionHelper.ClickAndSelect(driver, zip_input, zip_xpath, pZip);
	}
	public boolean clickSaveButton() {
		return ActionHelper.Click(driver, save_button);
	}
}
