package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.alerts.pp.GetMaximumListingExposureModal;
import resources.forms.pp.PPUploadImagesForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class PPListingDetailPage extends Page{
	PPUploadImagesForm ppUploadImagesForm;
	GetMaximumListingExposureModal getMaxListingExposure;
	
	@FindBy(xpath="//div[@class='tab-content']/h1[text()='Listing Details']")
	String listingDetail_heading="//div[@class='tab-content']/h1[text()='Listing Details']";
	
	String input_fields = "//input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String textArea_fields = "//textarea[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
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
	
	@FindBy(id="save_button")
	WebElement save_button;
	
	@FindBy(id="myModalLabel")
	WebElement uploadImages_title;
	
	@FindBy(xpath="//div[@class='alert alert-success' and text()='Listing Updated']")
	WebElement listingUpdated_notification;
	String listingUpdatedXpath="//div[@class='alert alert-success' and text()='Listing Updated']";
	private ActionHelper actionHelper;
	
	public PPListingDetailPage() {
		
	}
	public PPListingDetailPage(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);
		setPpUploadImagesForm();
		setGetMaxListingExposure();
	}
	
	public PPUploadImagesForm getPpUploadImagesForm() {
		return ppUploadImagesForm;
	}
	public void setPpUploadImagesForm() {
		ppUploadImagesForm = new PPUploadImagesForm(driver);
	}
	
	public GetMaximumListingExposureModal getGetMaxListingExposure() {
		return getMaxListingExposure;
	}
	public void setGetMaxListingExposure() {
		getMaxListingExposure = new GetMaximumListingExposureModal(driver);
	}
	public boolean isListingDetailPage() {
		return actionHelper.waitForElementToBeLocated(listingDetail_heading, 15);
	}
	
	public boolean typeTitle(String pTitle) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[title]"), pTitle);
	}
	public boolean typeListingDescription(String pListingDescription) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(textArea_fields, "listing[description]"), pListingDescription);
	}
	public boolean typeAccelatorCaption(String pAccelatorCaption) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(textArea_fields, "listing[accelerator_caption]"), pAccelatorCaption);
	}
	public boolean typeListingEmbeded(String pListingEmbeded) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(textArea_fields, "listing[embeded]"), pListingEmbeded);
	}
	public boolean typeListingCustomLink(String pListingCustomLink) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[custom_link]"), pListingCustomLink);
	}
	public boolean typePrice(String pPrice) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[price]"), pPrice);
	}
	public boolean selectStatus(String pStatus) {
		return actionHelper.selectDropDownOption(listing_status_dropdown, "", pStatus);
	}
	public boolean typeAddress(String pAddress) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[address]"), pAddress);
	}
	public boolean typeCounty(String pCounty) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[county]"), pCounty);
	}
	public boolean typeCity(String pCity) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[city]"), pCity);
	}
	public boolean selectState(String pState) {
		return actionHelper.selectDropDownOption(states, "", pState);
	}
	public boolean typeZip(String pZip) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[zip]"), pZip);
	}
	public boolean typeMLS(String pMLS) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[mls]"), pMLS);
	}
	public boolean selectPropertytype(String pPropertyType) {
		return actionHelper.selectDropDownOption(propertyType, "", pPropertyType);
	}
	public boolean selectSaleType(String pSaleType) {
		return actionHelper.selectDropDownOption(sale_type, "", pSaleType);
	}
	public boolean typeAgentName(String pAgentName) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[agent_name]"), pAgentName);
	}
	public boolean typeBrokarage(String pBrokerage) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[brokerage]"), pBrokerage);
	}
	public boolean typeBed(String pBed) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[bed]"), pBed);
	}
	public boolean typeBath(String pBath) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[bath]"), pBath);
	}
	public boolean typeSquareFootage(String pSqFootage) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[sq_footage]"), pSqFootage);
	}
	public boolean typeLotSize(String pLotSize) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[lot_size]"), pLotSize);
	}
	public boolean typeYearBuilt(String pYearBuilt) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "listing[year_built]"), pYearBuilt);
	}
	public boolean selectInterior(String[] pInteriorOptions) {
		return actionHelper.selectDropDownOptions(interior_input, interior_options_xpath, pInteriorOptions);
		
	}
	public boolean selectExterior(String[] pExteriorOptions) {
		return actionHelper.selectDropDownOptions(exterior_input, interior_options_xpath, pExteriorOptions);
		
	}
	public boolean selectLotDetails(String[] pLotDetails) {
		return actionHelper.selectDropDownOptions(lot_details_input, interior_options_xpath, pLotDetails);
		
	}
	public boolean selectSaleInfo(String[] pSaleInfo) {
		return actionHelper.selectDropDownOptions(sale_info_input, interior_options_xpath, pSaleInfo);
		
	}
	
	public boolean clickOnUploadIamgesButton() {
		return actionHelper.Click(upload_images_button);
	}
	public boolean clickOnSaveButton() {
		if(actionHelper.waitForElementToBeDisappeared(uploadImages_title)) {
			actionHelper.waitForElementToBeClickAble(save_button);
			waitForSaveButtonToBeClickable();
			return actionHelper.Click(save_button);
		}
		return false;
	}
	public boolean isListingEditedSuccessfully() {
		return actionHelper.waitForElementToBeLocated(listingUpdatedXpath, 15);
	}
	private boolean waitForSaveButtonToBeClickable() {
		List<WebElement> list_of_elements = driver.findElements(By.id("save_button"));
		int count=0;
		while(!list_of_elements.get(0).getText().trim().equalsIgnoreCase("Save") || count<100){
			list_of_elements = driver.findElements(By.id("save_button"));
			count++;
		}
		
		return list_of_elements.get(0).getText().trim().equalsIgnoreCase("Save");
	}
}
