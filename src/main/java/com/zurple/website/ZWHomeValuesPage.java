/**
 * 
 */
package com.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import us.zengtest1.Page;

/**
 * @author darrraqi
 *
 */
public class ZWHomeValuesPage extends Page{

	@FindBy(xpath="//h3[@class='gold' and text()='Tell us about your property']")
	WebElement tell_us_heading;
	@FindBy(xpath="//h3[@class='gold' and text()='Tell us where to send your report']")
	WebElement tell_us_report_heading;
	@FindBy(id="street_address")
	WebElement street_address;
	@FindBy(id="city")
	WebElement city;
	@FindBy(id="zip_code")
	WebElement zip_code;
	@FindBy(id="state")
	WebElement state;
	@FindBy(id="beds")
	WebElement beds;
	@FindBy(id="baths")
	WebElement baths;
	@FindBy(id="sqft")
	WebElement sqft;
	@FindBy(id="first_name")
	WebElement first_name;
	@FindBy(id="last_name")
	WebElement last_name;
	@FindBy(id="email_address")
	WebElement email_address;
	@FindBy(id="phone_number")
	WebElement phone_number;
	@FindBy(id="pun")
	WebElement pun;
	@FindBy(id="submit")
	WebElement submit;
	@FindBy(id="privacy_accepted")
	WebElement ccpaCheckbox;
	
	@FindBy(xpath="//div[@class='alert alert-danger' and text()='Please accept the Data Privacy Policy']")
	WebElement dataPrivacy_error;
	
	public ZWHomeValuesPage(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isTellUsAboutYourPropHeadingVisible() {
		return ActionHelper.isElementVisible(driver, tell_us_heading);
	}public boolean isTellUsReportHeadingVisible() {
		return ActionHelper.isElementVisible(driver, tell_us_report_heading);
	}public boolean isStreetAddressInputVisible() {
		return ActionHelper.isElementVisible(driver, street_address);
	}public boolean isCityInputVisible() {
		return ActionHelper.isElementVisible(driver, city);
	}public boolean isZipCodeInputVisible() {
		return ActionHelper.isElementVisible(driver, zip_code);
	}public boolean isStateInputVisible() {
		return ActionHelper.isElementVisible(driver, state);
	}public boolean isBedsInputVisible() {
		return ActionHelper.isElementVisible(driver, beds);
	}public boolean isBathsInputVisible() {
		return ActionHelper.isElementVisible(driver, baths);
	}public boolean isSqFeetDropDownVisible() {
		return ActionHelper.isElementVisible(driver, sqft);
	}public boolean isFirstnameInputVisible() {
		return ActionHelper.isElementVisible(driver, first_name);
	}public boolean isLastNameInputVisible() {
		return ActionHelper.isElementVisible(driver, last_name);
	}public boolean ispHONEnUMBERVisible() {
		return ActionHelper.isElementVisible(driver, phone_number);
	}public boolean isPUNCheckboxVisible() {
		return ActionHelper.isElementVisible(driver, pun);
	}public boolean isSubmitButtonVisible() {
		return ActionHelper.isElementVisible(driver, submit);
	}
	public boolean typeStreetAddress(String pAddress) {
		return ActionHelper.ClearAndType(driver, street_address, pAddress);
	}public boolean typeCity(String pCity) {
		return ActionHelper.ClearAndType(driver, city, pCity);
	}public boolean typeZipCode(String pZip) {
		return ActionHelper.ClearAndType(driver, zip_code, pZip);
	}public boolean selectState(String pState) {
		return ActionHelper.selectDropDownOption(driver, state, "", pState);
	}public boolean selectBeds(String pBeds) {
		return ActionHelper.selectDropDownOption(driver, beds, "", pBeds);
	}public boolean selectBath(String pBath) {
		return ActionHelper.selectDropDownOption(driver, baths, "", pBath);
	}public boolean selectSqFeet(String pSqFeet) {
		return ActionHelper.selectDropDownOption(driver, sqft, "", pSqFeet);
	}public boolean typeFirstName(String pFirstName) {
		return ActionHelper.ClearAndType(driver, first_name, pFirstName);
	}public boolean typeLastName(String pLastName) {
		return ActionHelper.ClearAndType(driver, last_name, pLastName);
	}public boolean typeEmail(String pEmail) {
		return ActionHelper.ClearAndType(driver, email_address, pEmail);
	}public boolean typePhone(String pPhone) {
		return ActionHelper.ClearAndType(driver, phone_number, pPhone);
	}public boolean clickOnPUNCheckbox() {
		return ActionHelper.Click(driver, pun);
	}public boolean clickOnSubmitButton() {
		return ActionHelper.Click(driver, submit);
	}
	
	public String getStreetValidationMessage() {
		return ActionHelper.getValidationMessage(driver, street_address);
	}public String getCityValidationMessage() {
		return ActionHelper.getValidationMessage(driver, city);
	}public String getZipCodeValidationMessage() {
		return ActionHelper.getValidationMessage(driver, zip_code);
	}public String getFirstNameValidationMessage() {
		return ActionHelper.getValidationMessage(driver, first_name);
	}public String getLastNameValidationMessage() {
		return ActionHelper.getValidationMessage(driver, last_name);
	}public String getEmailValidationMessage() {
		return ActionHelper.getValidationMessage(driver, email_address);
	}public String getStateValidationMessage() {
		return ActionHelper.getValidationMessage(driver, state);
	}
	
	public boolean isCCPACheckboxChecked() {
		return ActionHelper.isElementSelected(driver, ccpaCheckbox);
	}
	public boolean checkedUnCheckedCCPA(boolean pCheck) {
		return ActionHelper.checkUncheckInputBox(driver, ccpaCheckbox, pCheck);
	}
	public boolean isCCPAErrorDisplayed() {
		return ActionHelper.waitForElementToBeVisible(driver, dataPrivacy_error, 10);
	}
	@Override
	public WebElement getHeader() {
		// TODO Auto-generated method stub
		return null;
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
