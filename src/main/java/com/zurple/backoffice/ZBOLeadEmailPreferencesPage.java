/**
 * 
 */
package com.zurple.backoffice;

import java.nio.channels.SeekableByteChannel;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOLeadEmailPreferencesPage extends Page{
	
	@FindBy(xpath="//h1[@class='z-page-title' and contains(text(),'Update Lead')]")
	WebElement email_preferences_heading;
	
	@FindBy(id="email")
	WebElement email;
	
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
	
	String zipcode_xpath = "//div[@id='form-element-zip_code']/descendant::li";
	
	String neighborhood_xpath = "//div[@id='form-element-neighborhood']/descendant::li";
	
	String schooldistrict_xpath = "//div[@id='form-element-school_district']/descendant::li";
	
	String county_xpath = "//div[@id='form-element-county']/descendant::li";
	
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
	
	public ZBOLeadEmailPreferencesPage(){
		
	}
	public ZBOLeadEmailPreferencesPage(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isLeadUpdatePage() {
		return ActionHelper.waitForElementToBeVisible(driver, email_preferences_heading, 30);
	}
	public boolean allHeadingsDisplayed() {
		if(!ActionHelper.isElementVisible(driver, basic_information_title)) {
			AutomationLogger.error("Basic Information heading is not visible..");
			return false;
		}
		if(!ActionHelper.isElementVisible(driver, location_title)) {
			AutomationLogger.error("Location heading is not visible..");
			return false;
		}
		if(!ActionHelper.isElementVisible(driver, buyer_preferences_title)) {
			AutomationLogger.error("Buyer Preferences Information heading is not visible..");
			return false;
		}
		if(!ActionHelper.isElementVisible(driver, prop_types_title)) {
			AutomationLogger.error("Property Types heading is not visible..");
			return false;
		}
		if(!ActionHelper.isElementVisible(driver, notifications_title)) {
			AutomationLogger.error("Notifications heading is not visible..");
			return false;
		}
		return true;
	}
	
	public boolean isEmailUpdated(String pEmail) {
		return ActionHelper.getTextByValue(driver, email).equalsIgnoreCase(pEmail);
	}
	
	public boolean verifyStreet(String pStreet) {
		return ActionHelper.getTextByValue(driver, seller_street).equalsIgnoreCase(pStreet);
	}
	
	public boolean verifyCity(String pCity) {
		return ActionHelper.getTextByValue(driver, seller_city).equalsIgnoreCase(pCity);
	}
	public boolean verifyState(String pState) {
		return ActionHelper.getTextByValue(driver, seller_state).equalsIgnoreCase(pState);
	}
	public boolean verifyZip(String pZip) {
		return ActionHelper.getTextByValue(driver, seller_zip).equalsIgnoreCase(pZip);
	}
	public boolean verifyBeds(String pBeds) {
		return verifySelectedValFromDropDown(beds, pBeds);
	}
	public boolean verifyBaths(String pBaths) {
		return verifySelectedValFromDropDown(baths, pBaths);
	}
	public boolean verifySqFeet(String pSqFeet) {
		return verifySelectedValFromDropDown(sqft, pSqFeet);
	}
	public boolean verifyLocationZipCode(String pZipCode) {
		return verifyValueFromInputField(zipcode_xpath, pZipCode);
	}
	public boolean verifyLocationNeighborhood(String pNeighborhood) {
		return verifyValueFromInputField(neighborhood_xpath, pNeighborhood);
	}
	public boolean verifyLocationSchoolDistrict(String pSchoolDistrict) {
		return verifyValueFromInputField(schooldistrict_xpath, pSchoolDistrict);
	}
	public boolean verifyLocationCounty(String pCounty) {
		return verifyValueFromInputField(county_xpath, pCounty);
	}
	public boolean verifyBPMinPrice(String pMinPrice) {
		return verifySelectedValFromDropDown(user_Search_minimum_price, pMinPrice);
	}
	public boolean verifyBPMaxPrice(String pMaxPrice) {
		return verifySelectedValFromDropDown(user_search_price_maximum, pMaxPrice);
	}
	public boolean verifyBPBedsMin(String pBedsMin) {
		return verifySelectedValFromDropDown(user_search_bedrooms_minimum, pBedsMin);
	}
	public boolean verifyBPBathMin(String pBathMin) {
		return verifySelectedValFromDropDown(user_search_bathrooms_minimum, pBathMin);
	}
	public boolean verifyBPSqFeetMin(String pSqFeetMin) {
		return verifySelectedValFromDropDown(user_search_square_feet_minimum, pSqFeetMin);
	}
	public boolean verifyBPLotSizeMin(String pLotSize) {
		return verifySelectedValFromDropDown(user_search_lot_square_feet_minimum, pLotSize);
	}
	public boolean verifyBPFeatures(String pFeatures) {
		return multipleValsToVerify(features_xpath, pFeatures);
	}
	public boolean verifyBPView(String pView) {
		return multipleValsToVerify(view_xpath, pView);
	}
	public boolean verifyBPStyle(String pStyle) {
		return multipleValsToVerify(style_xpath, pStyle);
	}
	public boolean verifyPropTypes(String pPropTypes) {
		boolean isVerified = false;
		String lPropType_array[] = pPropTypes.split(",");
		for(String lPropType: lPropType_array) {
			if(lPropType.equalsIgnoreCase("Home")) {
				isVerified = type_home.isSelected()?true:false;
			}else if(lPropType.equalsIgnoreCase("Multi-Family Home")) {
				isVerified = type_multi_family.isSelected()?true:false;
			}else if(lPropType.equalsIgnoreCase("Condo or Townhome")) {
				isVerified = type_condo.isSelected()?true:false;
			}else if(lPropType.equalsIgnoreCase("Vacant Land/Lots")) {
				isVerified = type_land.isSelected()?true:false;
			}else if(lPropType.equalsIgnoreCase("Farm and Ranch")) {
				isVerified = type_farm.isSelected()?true:false;
			}
			if(!isVerified) {
				AutomationLogger.error("Property Type is not selected -> "+lPropType);
				break;
			}
		}
		return isVerified;
	}
	
	
	private boolean verifySelectedValFromDropDown(WebElement pElement, String pValue) {
		boolean isVerified = false;
		List<WebElement> list_of_options = pElement.findElements(By.tagName("option"));
		for(WebElement element: list_of_options) {
			if(element.isSelected()) {
				if(ActionHelper.getText(driver, element).equalsIgnoreCase(pValue)) {
					isVerified = true;
					break;
				}
			}
		}
		return isVerified;
	}
	
	private boolean verifyValueFromInputField(String pXpath, String pValueToVerify) {
		boolean isVerified = false;
		List<WebElement> list_of_elements = driver.findElements(By.xpath(pXpath));
		for(WebElement element: list_of_elements) {
			if(ActionHelper.getAttribute(element, "title").equalsIgnoreCase(pValueToVerify)) {
				isVerified = true;
				break;
			}
		}
		return isVerified;
	}
	
	private boolean multipleValsToVerify(String pXpath, String pVals) {
		boolean isVerified = false;
		String lFeatures_Array[] = pVals.split(",");
		for(String lFeature: lFeatures_Array) {
			isVerified = verifyValueFromInputField(pXpath, lFeature);
			if(!isVerified) {
				AutomationLogger.error("Unable to verify feature.."+lFeature);
				break;
			}
		}
		return isVerified;
	}
	
	

}
