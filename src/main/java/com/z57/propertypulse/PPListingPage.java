package com.z57.propertypulse;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.forms.pp.PPAddListingForm;
import resources.utility.ActionHelper;

public class PPListingPage extends Page{
	PPAddListingForm addListingForm;
	
	@FindBy(xpath="//h1[@class='z57-theme-page-topic' and text()='Listings']")
	WebElement listing_heading;
	
	@FindBy(xpath="//a/i[@class='icon-plus']")
	WebElement addimport_button;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/descendant::a[text()='Manual Entry']")
	WebElement manualEntry_dropDownOption;
	
	public PPListingPage() {
		// TODO Auto-generated constructor stub
	}
	public PPListingPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		addListingForm = new PPAddListingForm(driver);
		PageFactory.initElements(driver, this);
		
	}
	
	public boolean isListingPage() {
		return ActionHelper.waitForElementToBeVisible(driver, listing_heading, 15);
	}
	
	public boolean clickOnManualEntry() {
		return ActionHelper.clickAndSelectFromMenuButton(driver, addimport_button, manualEntry_dropDownOption);
	}
	

}
