package com.zurple.website;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.FrameworkConstants;
import us.zengtest1.Page;

public class ZWAccountSettingsPage extends Page{
	
	@FindBy(xpath="//div[@id='my']/descendant::h1[text()='My Account']")
	WebElement myAccount_heading;
	
	//Change Email Form
	@FindBy(id="change_email")
	WebElement changeEmail_linkButton;
	
	@FindBy(id="email")
	WebElement email_input;
	
	@FindBy(xpath="//div[@id='change_email_form']/descendant::button")
	WebElement email_update_button;
	
	@FindBy(id="change_email_text")
	WebElement changed_email_text;
	
	//Change Address Form
	@FindBy(id="change_address")
	WebElement change_address_link_button;
	
	@FindBy(id="seller_street")
	WebElement seller_street_input;
	
	@FindBy(id="seller_city")
	WebElement seller_city_input;
	
	@FindBy(id="seller_state")
	WebElement seller_state_input;
	
	@FindBy(id="seller_zip")
	WebElement seller_zip_input;
	
	@FindBy(id="beds")
	WebElement beds_dropdown;
	
	@FindBy(id="baths")
	WebElement baths_dropdown;
	
	@FindBy(id="sqft")
	WebElement sqft_dropdown;
	
	@FindBy(xpath="//div[@id='change_address_form']/descendant::button")
	WebElement address_update_button;
	
	@FindBy(id="change_address_text")
	WebElement changed_address_text;
	
	//Change criteria
	@FindBy(id="change_criteria")
	WebElement change_criteria_link_button;
	
	@FindBy(xpath="//input[@class='select2-search__field' and @placeholder='Zip Code']")
	WebElement zipCode_input;
	
	String selectZipCode = "//ul[@id='select2-zip_code-results']/li[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//input[@class='select2-search__field' and @placeholder='Neighborhood']")
	WebElement neighborhood_input;
	
	String selectNeighborhood= "//ul[@id='select2-neighborhood-results']/li[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//input[@class='select2-search__field' and @placeholder='School District']")
	WebElement school_district_input;
	
	String selectSchoolDistrict= "//ul[@id='select2-school_district-results']/li[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//button[@data-polygon-id='1']")
	WebElement drawArea1_button;
	
	@FindBy(xpath="//button[@data-polygon-id='2']")
	WebElement drawArea2_button;
	
	@FindBy(xpath="//button[@data-polygon-id='3']")
	WebElement drawArea3_button;
	
	@FindBy(xpath="//input[@class='select2-search__field' and @placeholder='County']")
	WebElement county_input;
	
	String selectCounty= "//ul[@id='select2-county-results']/li[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(id="user_search_price_minimum")
	WebElement minimum_price_dropdown;
	
	@FindBy(id="user_search_price_maximum")
	WebElement maximum_price_dropdown;
	
	@FindBy(id="user_search_bedrooms_minimum")
	WebElement minimum_bedrooms_dropdown;
	
	@FindBy(id="user_search_bathrooms_minimum")
	WebElement minimum_bathrooms_dropdown;
	
	@FindBy(id="user_search_square_feet_minimum")
	WebElement user_search_square_dropdown;
	
	@FindBy(id="user_search_lot_square_feet_minimum")
	WebElement user_search_lot_square_dropdown;
	
	@FindBy(xpath="//input[@class='select2-search__field' and @placeholder='Any Feature']")
	WebElement features_dropdown;
	
	String selectFeatures = "//ul[@id='select2-features-results']/li";
	
	@FindBy(xpath="//input[@class='select2-search__field' and @placeholder='Any View']")
	WebElement view_dropdown;
	
	String selectViews = "//ul[@id='select2-view-results']/li";
	
	@FindBy(xpath="//input[@class='select2-search__field' and @placeholder='Any Style']")
	WebElement style_dropdown;
	
	String selectStyle = "//ul[@id='select2-style-results']/li";
	
	@FindBy(id="type_home")
	WebElement type_home_checkbox;
	
	@FindBy(id="type_multi_family")
	WebElement type_multi_family_checkbox;
	
	@FindBy(id="type_condo")
	WebElement type_condo_checkbox;
	
	@FindBy(id="type_land")
	WebElement type_land_checkbox;
	
	@FindBy(id="type_farm")
	WebElement type_farm_checkbox;
	
	@FindBy(xpath="//div[@id='change_criteria_form']/descendant::button[@type='submit'][1]")
	WebElement criteria_update_button;
	
	@FindBy(xpath="//div[@id='change_criteria_form']/descendant::button[@type='submit'][2]")
	WebElement property_type_update_button;
	
	String changeCriteria_text_xpath="//div[@id='change_criteria_text']/descendant::div";
	
	public ZWAccountSettingsPage() {
		// TODO Auto-generated constructor stub
	}
	
	public ZWAccountSettingsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isMyAccountPage() {
		return ActionHelper.waitForElementToBeVisible(driver, myAccount_heading, 30);
	}
	
	public boolean clickOnChangeEmail() {
		return ActionHelper.Click(driver, changeEmail_linkButton);
	}
	
	public boolean typeEmailAddrss(String pEmail) {
		return ActionHelper.ClearAndType(driver, email_input, pEmail);
	}
	
	public boolean clickOnUpdateEmailButton() {
		return ActionHelper.Click(driver, email_update_button);
	}
	public boolean isChangedEmailSuccessful(String pChangedEmail) {
		boolean isSuccess = false;
		if(ActionHelper.waitForElementToBeVisible(driver, changed_email_text, 30)) {
			isSuccess = ActionHelper.getText(driver, changed_email_text).contains(pChangedEmail);
		}
		return isSuccess;
	}
	
	//Address Methods
	
	public boolean clickOnChangeAddress() {
		return ActionHelper.Click(driver, change_address_link_button);
	}
	public boolean typeStreet(String pStreet) {
		return ActionHelper.ClearAndType(driver, seller_street_input, pStreet);
	}
	
	public boolean typeCity(String pCity) {
		return ActionHelper.ClearAndType(driver, seller_city_input, pCity);
	}
	
	public boolean typeState(String pState) {
		return ActionHelper.ClearAndType(driver, seller_state_input, pState);
	}
	
	public boolean typeZip(String pState) {
		return ActionHelper.ClearAndType(driver, seller_zip_input, pState);
	}
	
	public boolean selectBeds(String pBeds) {
		return ActionHelper.selectDropDownOption(driver, beds_dropdown, "", pBeds);
	}
	public boolean selectBath(String pBath) {
		return ActionHelper.selectDropDownOption(driver, baths_dropdown, "", pBath);
	}
	public boolean selectSquareFeet(String pSquareFeet) {
		return ActionHelper.selectDropDownOption(driver, sqft_dropdown, "", pSquareFeet);
	}
	public boolean clickOnUpdateAddressButton() {
		return ActionHelper.Click(driver, address_update_button);
	}
	
	public boolean isChangedAddressSuccessful(String pStreet, String pCity, String pState, String pZip) {
		boolean isSuccess = true;
		if(ActionHelper.waitForElementToBeVisible(driver, changed_address_text, 30)) {
			String lAddress = ActionHelper.getText(driver, changed_address_text);
			String lStreet = lAddress.split(" ")[0].trim();
			String lCity = lAddress.split(",")[0].replace(lStreet, "").trim();
			String lState = lAddress.split(",")[1].trim();
			String lZip = lAddress.split(",")[2].trim().split("\n")[0].trim();
			if(!lStreet.equalsIgnoreCase(pStreet)) {
				AutomationLogger.error("Street mismatched - Expected:"+pStreet+"\n Found:"+lStreet);
				isSuccess = false;
			}
			if(!lCity.equalsIgnoreCase(pCity)) {
				AutomationLogger.error("Street mismatched - Expected:"+pCity+"\n Found:"+lCity);
				isSuccess = false;
			}
			if(!lState.equalsIgnoreCase(pState)) {
				AutomationLogger.error("Street mismatched - Expected:"+pState+"\n Found:"+lState);
				isSuccess = false;
			}
			if(!lZip.equalsIgnoreCase(pZip)) {
				AutomationLogger.error("Street mismatched - Expected:"+pZip+"\n Found:"+lZip);
				isSuccess = false;
			}
			
		}else {
			isSuccess = false;
		}
		return isSuccess;
	}
	
	//Change Criteria methods
	public boolean clickOnChangeCriteriaLink() {
		return ActionHelper.Click(driver, change_criteria_link_button);
	}
	public boolean typeAndSelectZip(String pZip) {
		return ActionHelper.typeAndSelect(driver, zipCode_input,selectZipCode, pZip);			
	}
	public boolean typeAndSelectNeighborhood(String pNeighborhood) {
		return ActionHelper.typeAndSelect(driver, neighborhood_input, selectNeighborhood, pNeighborhood);		
	}
	public boolean typeAndSelectSchoolDistrict(String pSchoolDistrict) {
		return ActionHelper.typeAndSelect(driver, school_district_input, selectSchoolDistrict, pSchoolDistrict);		
	}
	public boolean isDrawButtonsVisible() {
		return ActionHelper.isElementVisible(driver, drawArea1_button) && ActionHelper.isElementVisible(driver, drawArea3_button) && ActionHelper.isElementVisible(driver, drawArea2_button);
	}
	public boolean typeAndSelectCounty(String pCounty) {
		return ActionHelper.typeAndSelect(driver, county_input,selectCounty, pCounty);		
	}
	public boolean selectMinPrice(String pPrice) {
		return ActionHelper.selectDropDownOption(driver, minimum_price_dropdown, "", pPrice);
	}
	public boolean selectMaxPrice(String pPrice) {
		return ActionHelper.selectDropDownOption(driver, maximum_price_dropdown, "", pPrice);
	}
	public boolean selectMinBedrooms(String pBedrooms) {
		return ActionHelper.selectDropDownOption(driver, minimum_bedrooms_dropdown, "", pBedrooms);
	}
	public boolean selectMinBathrooms(String pMinBathrooms) {
		return ActionHelper.selectDropDownOption(driver, minimum_bathrooms_dropdown, "", pMinBathrooms);
	}
	public boolean selectSquareFeetCriteria(String pSquareFeet) {
		return ActionHelper.selectDropDownOption(driver, user_search_square_dropdown, "", pSquareFeet);
	}
	public boolean selectLotSize(String pLotSize) {
		return ActionHelper.selectDropDownOption(driver, user_search_lot_square_dropdown, "", pLotSize);
	}
	public boolean selectFeatures(String[] pFeatures) {
		return ActionHelper.selectDropDownOptions2(driver, features_dropdown, selectFeatures, pFeatures);
	}
	public boolean selectView(String[] pView) {
		return ActionHelper.selectDropDownOptions2(driver, view_dropdown, selectViews, pView);
	}
	public boolean selectStyle(String[] pStyle) {
		return ActionHelper.selectDropDownOptions2(driver, style_dropdown, selectStyle, pStyle);
	}
	public boolean clickCriteriaUpdateButton() {
		return ActionHelper.Click(driver, criteria_update_button);
	}
	public boolean selectPropertyType(String[] pPropType) {
		boolean isSuccessful = false;
		uncheckAllPropTypes();
		for(String lPropType: pPropType) {
			if(lPropType.equalsIgnoreCase("Home")) {
				isSuccessful = ActionHelper.checkUncheckInputBox(driver, type_home_checkbox, true);
			}else if(lPropType.equalsIgnoreCase("Multi-Family Home")) {
				isSuccessful = ActionHelper.checkUncheckInputBox(driver, type_multi_family_checkbox, true);
			}else if(lPropType.equalsIgnoreCase("Condo or Townhome")) {
				isSuccessful = ActionHelper.checkUncheckInputBox(driver, type_condo_checkbox, true);
			}else if(lPropType.equalsIgnoreCase("Vacant Land/Lots")) {
				isSuccessful = ActionHelper.checkUncheckInputBox(driver, type_land_checkbox, true);
			}else if(lPropType.equalsIgnoreCase("Farm and Ranch")){
				isSuccessful = ActionHelper.checkUncheckInputBox(driver, type_farm_checkbox, true);
			}
			if(!isSuccessful) {
				return isSuccessful;
			}
		}
		return isSuccessful;
	}
	
	public boolean clickPropertyTypeUpdateButton() {
		return ActionHelper.Click(driver, property_type_update_button);
	}
	
	private void uncheckAllPropTypes() {
		ActionHelper.checkUncheckInputBox(driver, type_home_checkbox, false);
		ActionHelper.checkUncheckInputBox(driver, type_multi_family_checkbox, false);
		ActionHelper.checkUncheckInputBox(driver, type_condo_checkbox, false);
		ActionHelper.checkUncheckInputBox(driver, type_land_checkbox, false);
		ActionHelper.checkUncheckInputBox(driver, type_farm_checkbox, false);
	}
	@Override
	public WebElement getHeader() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public boolean verifyChangeCriteriaText(String pCriteriaToVerify, String pCriteriaToVerifyVal) {
		boolean isVerified = false;
		try {
			List<WebElement> list_of_div = ActionHelper.getListOfElementByXpath(driver, changeCriteria_text_xpath);
			for(WebElement element: list_of_div) {
				element.getText();
				List<WebElement> list_of_labels = element.findElements(By.tagName("label"));
				for(WebElement labelElement: list_of_labels) {
					if(labelElement.getText().trim().contains(pCriteriaToVerify)) {
						if(element.getText().trim().contains(pCriteriaToVerifyVal)) {
							isVerified = true;
							break;
						}
					}
				}
				if(isVerified) {
					break;
				}
			}}catch(Exception ex) {
				isVerified = false;
			}
		return isVerified;
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
