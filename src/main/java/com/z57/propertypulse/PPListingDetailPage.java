package com.z57.propertypulse;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.zurple.my.Page;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class PPListingDetailPage extends Page{
	@FindBy(xpath="//div[@class='tab-content']/h1[text()='Listing Details']")
	String listingDetail_heading="//div[@class='tab-content']/h1[text()='Listing Details']";
	
	String input_fields = "//input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(id="nameField")
	WebElement price_input;
	
	@FindBy(id="address")
	WebElement address_input;
	
	@FindBy(id="county")
	WebElement county_input;
	
	@FindBy(id="city")
	WebElement city_input;
	
	@FindBy(id="zip")
	WebElement zip_input;
	
	@FindBy(id="listing_status")
	WebElement listing_status_dropdown;
	
	@FindBy(name="listing[state]")
	WebElement states;
	
	@FindBy(name="listing[property_type]")
	WebElement propertyType;
	
	@FindBy(id="sale_type")
	WebElement sale_type;
	
	@FindBy(xpath="//div[@id='s2id_interior_select']")
	WebElement interior_input;
	String interior_options_xpath = "//div[@id='select2-drop']/descendant::div";
	
	@FindBy(xpath="//div[@id='s2id_exterior_select']")
	WebElement exterior_input;

	@FindBy(xpath="//div[@id='s2id_lot_details_select']")
	WebElement lot_details_input;
	
	@FindBy(xpath="//div[@id='s2id_sale_info_select']")
	WebElement sale_info_input;
	
	@FindBy(id="upload_media_button")
	WebElement upload_images_button;
	
	
	public boolean isListingDetailPage() {
		return ActionHelper.waitForElementToBeLocated(driver, listingDetail_heading, 15);
	}
	
	public boolean typeTitle(String pTitle) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[title]"), pTitle);
	}
	public boolean typeListingDescription(String pListingDescription) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[description]"), pListingDescription);
	}
	public boolean typeAccelatorCaption(String pAccelatorCaption) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[accelerator_caption]"), pAccelatorCaption);
	}
	public boolean typeListingEmbeded(String pListingEmbeded) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[embeded]"), pListingEmbeded);
	}
	public boolean typeListingCustomLink(String pListingCustomLink) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[custom_link]"), pListingCustomLink);
	}
	public boolean selectStatus(String pStatus) {
		return ActionHelper.selectDropDownOption(driver, listing_status_dropdown, "", pStatus);
	}
	public boolean selectState(String pState) {
		return ActionHelper.selectDropDownOption(driver, states, "", pState);
	}
	public boolean typeMLS(String pMLS) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[mls]"), pMLS);
	}
	public boolean selectPropertytype(String pPropertyType) {
		return ActionHelper.selectDropDownOption(driver, propertyType, "", pPropertyType);
	}
	public boolean selectSaleType(String pSaleType) {
		return ActionHelper.selectDropDownOption(driver, sale_type, "", pSaleType);
	}
	public boolean typeAgentName(String pAgentName) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[agent_name]"), pAgentName);
	}
	public boolean typeBrokarage(String pBrokerage) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[brokerage]"), pBrokerage);
	}
	public boolean typeBed(String pBed) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[bed]"), pBed);
	}
	public boolean typeBath(String pBath) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[bath]"), pBath);
	}
	public boolean typeSquareFootage(String pSqFootage) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[sq_footage]"), pSqFootage);
	}
	public boolean typeLotSize(String pLotSize) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[lot_size]"), pLotSize);
	}
	public boolean typeYearBuilt(String pYearBuilt) {
		return ActionHelper.ClearAndType(driver, ActionHelper.getDynamicElement(driver, input_fields, "listing[year_built]"), pYearBuilt);
	}
	public boolean selectInterior(String[] pInteriorOptions) {
		boolean isSuccessful = true;
		for(String lInterior: pInteriorOptions) {
			if(!ActionHelper.selectDropDownOption(driver, interior_input, interior_options_xpath, lInterior)) {
				isSuccessful = false;
				break;
			}
		}
		ActionHelper.Click(driver, interior_input);
		return isSuccessful;
		
	}
	public boolean selectExterior(String[] pExteriorOptions) {
		boolean isSuccessful = true;
		for(String lExterior: pExteriorOptions) {
			if(!ActionHelper.selectDropDownOption(driver, exterior_input, interior_options_xpath, lExterior)) {
				isSuccessful = false;
				break;
			}
		}
		ActionHelper.Click(driver, exterior_input);
		return isSuccessful;
		
	}
	public boolean selectLotDetails(String[] pLotDetails) {
		boolean isSuccessful = true;
		for(String lLotDetail: pLotDetails) {
			if(!ActionHelper.selectDropDownOption(driver, lot_details_input, interior_options_xpath, lLotDetail)) {
				isSuccessful = false;
				break;
			}
		}
		ActionHelper.Click(driver, lot_details_input);
		return isSuccessful;
		
	}
	
	public boolean selectSaleInfo(String[] pSaleInfo) {
		boolean isSuccessful = true;
		for(String lSaleInfo: pSaleInfo) {
			if(!ActionHelper.selectDropDownOption(driver, sale_info_input, interior_options_xpath, lSaleInfo)) {
				isSuccessful = false;
				break;
			}
		}
		ActionHelper.Click(driver, sale_info_input);
		return isSuccessful;
		
	}
	
	public boolean clickOnUploadIamgesButton() {
		return ActionHelper.Click(driver, upload_images_button);
	}
}
