package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.alerts.pp.DeleteListingAlert;
import resources.alerts.pp.GetMaximumListingExposureModal;
import resources.forms.pp.PPAddListingForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class PPListingPage extends Page{
	PPAddListingForm addListingForm;
	GetMaximumListingExposureModal getMaxListingExposure;
	DeleteListingAlert deleteListingAlert;
	
	@FindBy(xpath="//h1[@class='z57-theme-page-topic' and text()='Listings']")
	WebElement listing_heading;
	
	@FindBy(xpath="//a/i[@class='icon-plus']")
	WebElement addimport_button;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/descendant::a[text()='Manual Entry']")
	WebElement manualEntry_dropDownOption;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/descendant::a[text()='Easy Import From MLS']")
	WebElement easyImportFromMLS_dropDownOption;
	
	@FindBy(xpath="//a[@title='Delete' and @data-pk='"+FrameworkConstants.DYNAMIC_VARIABLE+"']")
	String delete_button_xpath="//a[@title='Delete' and @data-pk='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@id='listingsTable_filter']/descendant::input[@type='search']")
	WebElement search_input;
	
	String createAd_button = "//tr[@id='row_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::button[text()='Create Ad']";
	
	public PPListingPage() {
		// TODO Auto-generated constructor stub
	}
	public PPListingPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setAddListingForm();
		setGetMaxListingExposure();
		setDeleteListingAlert();
		PageFactory.initElements(driver, this);
		
	}
	
	public PPAddListingForm getAddListingForm() {
		return addListingForm;
	}
	public void setAddListingForm() {
		 addListingForm = new PPAddListingForm(driver);
	}
	public GetMaximumListingExposureModal getGetMaxListingExposure() {
		return getMaxListingExposure;
	}
	public void setGetMaxListingExposure() {
		getMaxListingExposure = new GetMaximumListingExposureModal(driver);
	}
	
	public DeleteListingAlert getDeleteListingAlert() {
		return deleteListingAlert;
	}
	public void setDeleteListingAlert() {
		deleteListingAlert = new DeleteListingAlert(driver);
	}
	public boolean isListingPage() {
		return ActionHelper.waitForElementToBeVisible(driver, listing_heading, 15);
	}
	
	public boolean clickOnManualEntry() {
		return ActionHelper.clickAndSelectFromMenuButton(driver, addimport_button, manualEntry_dropDownOption);
	}
	public boolean clickOnCreateAd(String pListingId) {
		return ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, createAd_button, pListingId));
	}
	public boolean clickOnEasyImportFromMLS() {
		return ActionHelper.clickAndSelectFromMenuButton(driver, addimport_button, easyImportFromMLS_dropDownOption);
	}
	public boolean deleteListing(String pListingId) {
		boolean deleted = false;
		ActionHelper.waitForElementToBeLocated(driver, ActionHelper.getDynamicElementXpath(driver, delete_button_xpath, pListingId), 15);
		if(ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, delete_button_xpath, pListingId))) {
			if(getDeleteListingAlert().isDeleteListingAlert()) {
				deleted = getDeleteListingAlert().clickOnConfirmButton();
			}
			
		}
		return deleted;
	}
	public boolean typeInpurSearch(String pInput) {
		return ActionHelper.ClearAndType(driver, search_input, pInput);
	}

}
