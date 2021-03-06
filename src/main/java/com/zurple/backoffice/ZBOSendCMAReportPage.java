/**
 * 
 */
package com.zurple.backoffice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOGenericAlerts;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

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
	@FindBy(xpath="//form[@id='active-listings-form']/descendant::span[@class='select2-selection select2-selection--multiple']")
	WebElement active_listing_selected_span;
	@FindBy(xpath="//strong[@style='color: rgb(169, 68, 66);' and contains(text(),'Selected Listings')]")
	WebElement selected_listing_red;
	String active_listings_Selected_list = "//form[@id='active-listings-form']/descendant::ul[@class='select2-selection__rendered']/li[@title]";
	String remove_button_active_listings = "//form[@id='active-listings-form']/descendant::ul[@class='select2-selection__rendered']/li[@title]/span[@class='select2-selection__choice__remove']";
	@FindBy(xpath="//strong[contains(text(),'Selected Listings')]/span[text()='3']")
	WebElement activeListingsSelected3outof3;
	String active_listings_added_label = "//table[@id='propertiesGrid']/descendant::span[text()='Added']";
	@FindBy(xpath="//li[@id='propertiesGrid_next']/a")
	WebElement active_listings_pagination_next_button;
	@FindBy(xpath="//form[@id='active-listings-form']/descendant::label/input[@value='1']")
	WebElement address_radio;
	@FindBy(xpath="//form[@id='active-listings-form']/descendant::input[@id='name']")
	WebElement address_input;
	String active_props_images_list = "//table[@id='propertiesGrid']/descendant::td[@class=' property-mls']/img";
	String active_listing_radio_buttons = "//form[@id='active-listings-form']/descendant::label[contains(@class,'checkbox-inline')]/input";
	String active_listing_radio_button = "//form[@id='active-listings-form']/descendant::label[contains(@class,'checkbox-inline')]/input[@value='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	@FindBy(xpath="//form[@id='active-listings-form']/descendant::button[@id='z-properties-grid-filter-button']")
	WebElement search_button_active_listing;
	String active_listing_results = "//table[@id='propertiesGrid']/descendant::td[@class=' property-address']/span";
	String active_listings_result_mls = "//table[@id='propertiesGrid']/descendant::td[@class=' property-mls']/*/span";
	String al_results_thumbnail_images = "//table[@id='propertiesGrid']/descendant::td[@class=' property-mls']/img";
	String al_view_listing = "//table[@id='propertiesGrid']/descendant::td[@class=' property-mls']/*/span/a[text()='View Listing']";
	String al_property_Address = "//table[@id='propertiesGrid']/descendant::td[@class=' property-address']/span";
	String al_price = "//table[@id='propertiesGrid']/descendant::span[@class='properties-table-cell price-info']";
	String al_distance_miles = "//table[@id='propertiesGrid']/descendant::td[@class='property-distance sorting_1']/span";
	String al_prop_details = "//table[@id='propertiesGrid']/descendant::td[@class=' property-details']/span";
	
	//Sold Property
	@FindBy(xpath="//div[@class='panel-heading']/h3[text()='Sold Properties']")
	WebElement sold_properties_heading;
	String add_button_sold_listings = "//form[@id='sold-listings-form']/descendant::span[@class='not-added-property']";
	@FindBy(xpath="//div[@id='DataTables_Table_0_paginate']/*/*/a[text()='Next']")
	WebElement sold_props_pagination_next_button;
	@FindBy(xpath="//form[@id='sold-listings-form']/descendant::span[@class='select2-selection select2-selection--multiple']")
	WebElement sold_listing_selected_span;
	@FindBy(xpath="//strong[@style='color: rgb(169, 68, 66);' and contains(text(),'Selected Properties')]")
	WebElement sold_selected_listing_red;
	String sold_props_Selected_list = "//form[@id='sold-listings-form']/descendant::ul[@class='select2-selection__rendered']/li[@title]";
	String remove_button_Sold_props = "//form[@id='sold-listings-form']/descendant::ul[@class='select2-selection__rendered']/li[@title]/span[@class='select2-selection__choice__remove']";
	@FindBy(xpath="//strong[contains(text(),'Selected Properties')]/span[text()='3']")
	WebElement SoldPropsSelected3outof3;
	String sold_props_images_list = "//div[@id='DataTables_Table_0_wrapper']/descendant::td[@class=' property-address']";
	String sold_props_radio_buttons = "//form[@id='sold-listings-form']/descendant::label[contains(@class,'checkbox-inline')]/input";
	String sold_props_radio_button = "//form[@id='sold-listings-form']/descendant::label[contains(@class,'checkbox-inline')]/input[@value='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	@FindBy(xpath="//form[@id='sold-listings-form']/descendant::button[@id='z-properties-grid-filter-button']")
	WebElement search_button_sold_props;
	String sold_props_results = "//table[@id='DataTables_Table_0']/descendant::td[@class=' property-address']/span";
	String sold_props_result_mls = "//table[@id='DataTables_Table_0']/descendant::td[@class=' property-mls']/*/span";
	String sold_props_results_thumbnail_images = "//table[@id='DataTables_Table_0']/descendant::td[@class=' property-mls']/img";
	String sold_props_view_listing = "//table[@id='DataTables_Table_0']/descendant::td[@class=' property-mls']/*/span/a[text()='View Listing']";
	String sold_props_property_Address = "//table[@id='DataTables_Table_0']/descendant::td[@class=' property-address']/span";
	String sold_props_price = "//table[@id='DataTables_Table_0']/descendant::span[@class='properties-table-cell price-info']";
	String sold_props_distance_miles = "//table[@id='DataTables_Table_0']/descendant::td[@class='property-distance sorting_1']/span";
	String sold_props_prop_details = "//table[@id='DataTables_Table_0']/descendant::td[@class=' property-details']/span";
	@FindBy(xpath="//form[@id='sold-listings-form']/descendant::input[@id='name']")
	WebElement sold_props_address_input;

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
	@FindBy(xpath="//div[@role='alert']/strong[text()='Please enter Minimum Estimated Price']")
	WebElement min_price_alert;
	@FindBy(xpath="//div[@role='alert']/strong[text()='Please enter Maximum Estimated Price']")
	WebElement maximum_price_alert;
	
	//Step 2 CMA Report
	@FindBy(xpath="//form[@id='cmaEmailForm']/descendant::strong[text()='Subject']")
	WebElement subject_heading;
	@FindBy(id="cma-form-submit")
	WebElement step2_email_details_button;
	@FindBy(xpath="//html[@dir='ltr']/body")
	WebElement additional_comments_textarea;
	
	//Step 3 CMA Report
	@FindBy(xpath="//h3[text()='Preview Email']")
	WebElement preview_email_heading;
	String property_details = "//div[@id='email-preview-container']/table[2]/tbody/tr/td";
	String comparable_props_list_img = "//div[@id='email-preview-container']/descendant::table[@style='margin: 10px 0px;']/descendant::a/img";
	String comparable_props_list_text = "//div[@id='email-preview-container']/descendant::table[@style='margin: 10px 0px;']/descendant::a/p";
	@FindBy(xpath="//div[@id='email-preview-container']/descendant::td[contains(text(),'Comparable Properties Recently Sold')]")
	WebElement comparable_sold_props_heading;
	String comparable_sold_props_details= "//div[@id='email-preview-container']/descendant::td[@style='width:33%']/p";
	@FindBy(id="cma-form-go-back")
	WebElement cma_back_button;
	
	//Processing
	@FindBy(id="propertiesGrid_processing")
	WebElement processing_alert;
	
	//Alerts Class
	private ZBOGenericAlerts genericAlerts;
	
	public ZBOSendCMAReportPage(WebDriver pWebdriver){
		driver = pWebdriver;
		setGenericAlert();
		PageFactory.initElements(driver, this);
	}
	private void setGenericAlert() {
		genericAlerts = new ZBOGenericAlerts(driver);
	}
	public ZBOGenericAlerts getGenericAlert() {
		return genericAlerts;
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
	}public boolean isMaxPriceAlertVisible() {
		return ActionHelper.isElementVisible(driver, maximum_price_alert);
	}public boolean isMinPriceAlertVisible() {
		return ActionHelper.isElementVisible(driver, min_price_alert);
	}
	
	public boolean typeMinPrice(String pPrice) {
		return ActionHelper.Type(driver, min_price, pPrice);
	}public boolean typeMaxPrice(String pPrice) {
		return ActionHelper.Type(driver, max_price, pPrice);
	}public boolean typeAddressInput(String pStringToType) {
		return ActionHelper.Type(driver, address, pStringToType);
	}public boolean typeCityInput(String pStringToType) {
		return ActionHelper.Type(driver, city, pStringToType);
	}public boolean typeStateInput(String pStringToType) {
		return ActionHelper.Type(driver, state, pStringToType);
	}public boolean typeZipInput(String pStringToType) {
		return ActionHelper.Type(driver, zip, pStringToType);
	}
	
	public boolean isActiveListingSpanTurnsRed() {
		return ActionHelper.getCssValueOfTheElement(driver, active_listing_selected_span, "border-color").equalsIgnoreCase("rgb(169, 68, 66)");
	}
	public boolean isSelectedListingsTurnsRed() {
		return ActionHelper.isElementVisible(driver, selected_listing_red);
	}
	public boolean verifyActiveListingAlert() {
		boolean isVerified = false;
		if(genericAlerts.isActiveListingAlertVisible() && genericAlerts.clickOnOkButton()) {
			isVerified = true;
		}
		return isVerified;
	}
	
	public boolean isSoldListingSpanTurnsRed() {
		return ActionHelper.getCssValueOfTheElement(driver, sold_listing_selected_span, "border-color").equalsIgnoreCase("rgb(169, 68, 66)");
	}
	public boolean isSoldListingsTurnsRed() {
		return ActionHelper.isElementVisible(driver, sold_selected_listing_red);
	}
	public boolean verifySoldListingAlert() {
		boolean isVerified = false;
		if(genericAlerts.isSoldListingAlertVisible() && genericAlerts.clickOnOkButton()) {
			isVerified = true;
		}
		return isVerified;
	}

	public boolean clickOnNextButtonSoldProps() {
		return ActionHelper.Click(driver, sold_props_pagination_next_button);
	}
	public int getSelectedSoldPropsCount() {
		return ActionHelper.getListOfElementByXpath(driver, sold_props_Selected_list).size();
	}
	public int getRemoveSoldPropsCount() {
		return ActionHelper.getListOfElementByXpath(driver, remove_button_Sold_props).size();
	}
	public boolean is3SoldPropsSelected() {
		return ActionHelper.isElementVisible(driver, SoldPropsSelected3outof3);
	}
	public boolean removeSoldProperties() {
		boolean isPropsRemoved = true;
		List<WebElement> list_elemenets =ActionHelper.getListOfElementByXpath(driver, remove_button_Sold_props);
		while(list_elemenets.size()>0) {
			if(!ActionHelper.Click(driver, list_elemenets.get(0))) {
				isPropsRemoved = false;
				break;
			}else {
				list_elemenets =ActionHelper.getListOfElementByXpath(driver, remove_button_Sold_props);
			}
		}
		return isPropsRemoved;
	}
	
	public int getSelectedActiveListingsCount() {
		return ActionHelper.getListOfElementByXpath(driver, active_listings_Selected_list).size();
	}public int getRemoveActiveListingsCount() {
		return ActionHelper.getListOfElementByXpath(driver, remove_button_active_listings).size();
	}public boolean is3ActiveListingsSelected() {
		return ActionHelper.isElementVisible(driver, activeListingsSelected3outof3);
	}public int getActiveListingsAddedLabelCount() {
		return ActionHelper.getListOfElementByXpath(driver, active_listings_Selected_list).size();
	}public boolean verifyAddedLabelIsNotClickable() {
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, add_button_active_listings);
		return list_element.size()==0;
	}
	public boolean clickOnNextButtonActiveProps() {
		return ActionHelper.Click(driver, active_listings_pagination_next_button);
	}
	public boolean removeActiveListings() {
		boolean isPropsRemoved = true;
		List<WebElement> list_elemenets =ActionHelper.getListOfElementByXpath(driver, remove_button_active_listings);
		while(list_elemenets.size()>0) {
			if(!ActionHelper.Click(driver, list_elemenets.get(0))) {
				isPropsRemoved = false;
				break;
			}else {
				list_elemenets =ActionHelper.getListOfElementByXpath(driver, remove_button_active_listings);
			}
		}
		return isPropsRemoved;
	}
	public boolean selectAddressRadio() {
		return ActionHelper.Click(driver, address_radio);
	}
	public boolean isActiveListingsDisplayed() {
		return ActionHelper.getListOfElementByXpath(driver, active_props_images_list).size()>0;
	}public boolean isAtiveListingRadioOptionSelected() {
		boolean isSelected = false;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, active_listing_radio_buttons);
		for(int i=0;i<list_element.size();i++) {
			if(ActionHelper.isElementSelected(driver, list_element.get(i))) {
				isSelected = true;
				break;
			}
		}
		return isSelected;
	}public boolean selectAtiveListingRadioOption(String pInputType) {
		boolean isSelected = false;
		switch(pInputType) {
		case "Address":
			isSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, active_listing_radio_button, "1"));
			break;
		case "City":
			isSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, active_listing_radio_button, "2"));
			break;
		case "Zip":
			isSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, active_listing_radio_button, "3"));
			break;
		case "MLS ID":
			isSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, active_listing_radio_button, "4"));
			break;
		}
		return isSelected;
	}
	public boolean typeInputActiveListing(String pStringToType) {
		return ActionHelper.ClearAndType(driver, address_input, pStringToType);
	}public boolean clickOnSearchButtonForActiveListing() {
		return ActionHelper.Click(driver, search_button_active_listing);
	}
	public boolean waitForProcessingAlertToDisappear() {
		return ActionHelper.waitForElementToBeDisappeared(driver, processing_alert);
	}
	public boolean verifyResultsOfActiveListing(String pAddress, String pAttributeToVerify) {
		boolean isVerified = false;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, active_listing_results);
		for(WebElement element: list_element){
			switch(pAttributeToVerify) {
			case "city":
				isVerified = pAddress.contains(ActionHelper.getAttribute(element, "data-city"));
				break;
			case "state":
				isVerified = pAddress.contains(ActionHelper.getAttribute(element, "data-state"));
				break;
			case "zip":
				isVerified = pAddress.contains(ActionHelper.getAttribute(element, "data-zip_code"));
				break;
			}
			if(!isVerified) {
				break;
			}
		}
		return isVerified;
	}
	public boolean verifyMLSID(String pMLSID) {
		return verifyAttributeContains(active_listings_result_mls, "data-mls_number", pMLSID);
	}
	public boolean verifyActiveListingResultsThumbnailsImageAreDisplayed() {
		return verifyAttributeContains(al_results_thumbnail_images, "src", "photoaccesskey");
	}
	public boolean verifyActiveListingViewListingLink() {
		boolean isVerified = true;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, al_view_listing);
		for(WebElement element: list_element) {
			if(ActionHelper.getAttribute(element, "href").isEmpty()) {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}public boolean verifyActiveListingPropAddressIsDisplayed() {
		return verificationByText(al_property_Address);
	}
	public boolean verifyActiveListingPriceIsDisplayed() {
		return verificationByText(al_price);
	}
	public boolean verifyActiveListingDistanceIsDisplayed() {
		return verificationByText(al_distance_miles);
	}
	public boolean verifyActiveListingDetailIsDisplayed() {
		return verificationByText(al_prop_details);
	}
	public boolean isSoldPropsRadioOptionSelected() {
		boolean isSelected = false;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, sold_props_radio_buttons);
		for(int i=0;i<list_element.size();i++) {
			if(ActionHelper.isElementSelected(driver, list_element.get(i))) {
				isSelected = true;
				break;
			}
		}
		return isSelected;
	}
	public boolean isSoldPropsDisplayed() {
		return ActionHelper.getListOfElementByXpath(driver, sold_props_images_list).size()>0;
	}
	public boolean selectSoldPropsRadioOption(String pInputType) {
		boolean isSelected = false;
		switch(pInputType) {
		case "Address":
			isSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, sold_props_radio_button, "1"));
			break;
		case "City":
			isSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, sold_props_radio_button, "2"));
			break;
		case "Zip":
			isSelected = ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, sold_props_radio_button, "3"));
			break;
		}
		return isSelected;
	}public boolean typeInputSoldProps(String pStringToType) {
		return ActionHelper.ClearAndType(driver, sold_props_address_input, pStringToType);
	}public boolean clickOnSearchButtonForSoldProps() {
		return ActionHelper.Click(driver, search_button_sold_props);
	}
	public boolean verifyResultsOfSoldProps(String pAddress, String pAttributeToVerify) {
		boolean isVerified = false;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, sold_props_results);
		for(WebElement element: list_element){
			switch(pAttributeToVerify) {
			case "city":
				isVerified = pAddress.contains(ActionHelper.getAttribute(element, "data-city"));
				break;
			case "state":
				isVerified = pAddress.contains(ActionHelper.getAttribute(element, "data-state"));
				break;
			case "zip":
				isVerified = pAddress.contains(ActionHelper.getAttribute(element, "data-zip_code"));
				break;
			}
			if(!isVerified) {
				break;
			}
		}
		return isVerified;
	}
	public boolean verifySoldPropsPropAddressIsDisplayed() {
		return verificationByText(sold_props_property_Address);
	}
	public boolean verifySoldPropsPriceIsDisplayed() {
		return verificationByText(sold_props_price);
	}
	public boolean verifySoldPropsDistanceIsDisplayed() {
		return verificationByText(sold_props_distance_miles);
	}
	public boolean verifySoldPropsDetailIsDisplayed() {
		return verificationByText(sold_props_prop_details);
	}
	public int getSoldPropertiesAddedLabelCount() {
		return ActionHelper.getListOfElementByXpath(driver, sold_props_Selected_list).size();
	}public boolean verifyAddedLabelIsNotClickableSoldProps() {
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, add_button_sold_listings);
		return list_element.size()==0;
	}
	public boolean clickOnStep2SendEmailButton() {
		return ActionHelper.Click(driver, step2_email_details_button);
	}
	public boolean isStep2Visible() {
		return ActionHelper.isElementVisible(driver, subject_heading);
	}
	public boolean typeAdditionalComments(String pComments) {
		boolean isTyped = true;
		ActionHelper.switchToiFramebyElement(driver, driver.findElement(By.xpath("//iframe[@title='Rich Text Editor, comments']")));
		try{
			additional_comments_textarea.sendKeys(pComments);
		}catch(Exception ex) {
			isTyped = false;
		}
		return isTyped;
	}
	public boolean isPreviewEmailHeadingVisible() {
		return ActionHelper.isElementVisible(driver, preview_email_heading);
	}public boolean isPropDetailsDisplayed() {
		int count = 0;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, property_details);
		for(WebElement element: list_element) {
			if(ActionHelper.getText(driver, element).isEmpty()) {
				count++;
				
			}
		}
		return count<2;
	}
	public boolean verifyComparablePropsImagesVisible() {
		boolean isVerified = true;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, comparable_props_list_img);
		for(WebElement element: list_element) {
			if(!ActionHelper.isElementVisible(driver, element)) {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}
	public boolean verifyComparablePropsTextVisible() {
		boolean isVerified = true;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, comparable_props_list_text);
		for(WebElement element: list_element) {
			if(ActionHelper.getText(driver,element).isEmpty()) {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}
	public boolean isComparableSoldPropsHeadingVisible() {
		return ActionHelper.isElementVisible(driver, comparable_sold_props_heading);
	}
	public boolean verifyComparableSoldPropsTextVisible() {
		boolean isVerified = true;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, comparable_sold_props_details);
		for(WebElement element: list_element) {
			if(ActionHelper.getText(driver,element).isEmpty()) {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}
	public boolean isCMABackButtonVisible() {
		return ActionHelper.isElementVisible(driver, cma_back_button);
	}
	//Private methods
	private boolean verificationByText(String pXpath) {
		boolean isVerified = true;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, pXpath);
		for(WebElement element: list_element) {
			if(ActionHelper.getText(driver,element).isEmpty()) {
				isVerified = false;
				break;
			}
		}
		return isVerified;
	}private boolean verifyAttributeContains(String pXpath, String pAttribue, String pValueToVerify) {
		boolean isVerified = false;
		List<WebElement> list_element = ActionHelper.getListOfElementByXpath(driver, pXpath);
		for(WebElement element: list_element) {
			if(ActionHelper.getAttribute(element, pAttribue).contains(pValueToVerify)) {
				isVerified = true;
				break;
			}
		}
		return isVerified;
	}
}
