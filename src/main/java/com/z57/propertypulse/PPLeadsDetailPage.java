package com.z57.propertypulse;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class PPLeadsDetailPage extends Page{
	
	@FindBy(xpath="//div[@class='tab-content']/h1[text()='Lead Details']")
	WebElement leadDetail_header;
	
	String input_fields = "//input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	String states = "//select[@id='states']";
	String all_states = "//select[@id='states']/option";
	
	@FindBy(xpath="//select[@name='lead[status_id]']")
	WebElement leadStatus_dropdown_button;
	String lead_Status = "//select[@name='lead[status_id]']/option";
	
	@FindBy(xpath="//select[@id='associate_listing_select']")
	WebElement associate_listing_button;
	String associate_listing="//select[@id='associate_listing_select']/option";
	
	@FindBy(xpath="//button[@id='saveButton']")
	WebElement saveButton;
	
	@FindBy(xpath="//div[@class='alert alert-success' and text()='Lead Updated']")
	WebElement leadUpdated_notification;

	private ActionHelper actionHelper;
	
	public PPLeadsDetailPage() {
		// TODO Auto-generated constructor stub
	}
	
	public PPLeadsDetailPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver,this);
	}

	public boolean isLeadDetailsPage() {
		return actionHelper.waitForElementToBeLocated("//div[@class='tab-content']/h1[text()='Lead Details']", 30);
	}
	
	public boolean typeLeadName(String pLeadName) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "lead[name_full]"), pLeadName);
	}
	
	public boolean typeLeadPhone(String pLeadPhone) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "lead[phone]"), pLeadPhone);
	}
	
	public boolean typeLeadEmail(String pLeadEmail) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "lead[email]"),pLeadEmail);
	}
	
	public boolean typeLeadDOB(String pLeadDOB) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "lead[dob]"), pLeadDOB);
	}
	
	public boolean typeLeadAddress(String pLeadAddress) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "lead[address]"), pLeadAddress);
	}
	
	public boolean typeLeadCity(String pLeadCity) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "lead[city]"), pLeadCity);
	}
	
	public boolean typeLeadZip(String pLeadZip) {
		return actionHelper.ClearAndType(actionHelper.getDynamicElement(input_fields, "lead[zip]"), pLeadZip);
	}
	
	public boolean selectState(String pState) {
		return clickAndSelect(driver.findElement(By.xpath(states)), all_states, pState);
	}
	public boolean selectLeadStatus(String pStatus) {
		return clickAndSelect(leadStatus_dropdown_button, lead_Status, pStatus);
	}
	
	public boolean selectAssociateListing(String pListing) {
		return clickAndSelect(associate_listing_button, associate_listing, pListing);
	}
	
	private boolean clickAndSelect(WebElement pElementToClick, String pAllOptions, String pOptionToSelect) {
		boolean isSuccessful = false;
		if(actionHelper.Click(pElementToClick)) {
			List<WebElement> list_of_options = actionHelper.getListOfElementByXpath(pAllOptions);
			for(WebElement element: list_of_options) {
				if(element.getText().equalsIgnoreCase(pOptionToSelect)) {
					isSuccessful = actionHelper.Click(element);
				}
			}
		}
		return isSuccessful;
	}
	public boolean clickOnSaveButton() {
		return actionHelper.Click(saveButton);
	}
	
	public boolean isLeadUpdatedSuccessfully() {
		return actionHelper.waitForElementToBeVisible(leadUpdated_notification, 30);
	}
}
