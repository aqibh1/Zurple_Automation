package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.alerts.pp.GetMaximumListingExposureModal;
import resources.forms.pp.PPAddListingForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class PPListingPage extends Page{
	PPAddListingForm addListingForm;
	GetMaximumListingExposureModal getMaxListingExposure;
	
	@FindBy(xpath="//h1[@class='z57-theme-page-topic' and text()='Listings']")
	WebElement listing_heading;
	
	@FindBy(xpath="//a/i[@class='icon-plus']")
	WebElement addimport_button;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/descendant::a[text()='Manual Entry']")
	WebElement manualEntry_dropDownOption;
	
	String createAd_button = "//tr[@id='row_"+FrameworkConstants.DYNAMIC_VARIABLE+"']/descendant::button[text()='Create Ad']";
	
	public PPListingPage() {
		// TODO Auto-generated constructor stub
	}
	public PPListingPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		setAddListingForm();
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
	public boolean isListingPage() {
		return ActionHelper.waitForElementToBeVisible(driver, listing_heading, 15);
	}
	
	public boolean clickOnManualEntry() {
		return ActionHelper.clickAndSelectFromMenuButton(driver, addimport_button, manualEntry_dropDownOption);
	}
	public boolean clickOnCreateAd(String pListingId) {
		return ActionHelper.Click(driver, ActionHelper.getDynamicElement(driver, createAd_button, pListingId));
	}
	

}
