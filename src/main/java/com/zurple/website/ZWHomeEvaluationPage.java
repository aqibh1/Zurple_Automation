package com.zurple.website;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;
import us.zengtest1.Page;

public class ZWHomeEvaluationPage extends Page{
	
	@FindBy(xpath="//button[text()='Get a Home Valuation' and contains(@class,'mobile')]")
	WebElement home_evaluation_button;
	
	//Change Email Form
	@FindBy(id="seller_street")
	WebElement seller_street;
	
	@FindBy(id="seller_city")
	WebElement seller_city;
	
	@FindBy(id="seller_state")
	WebElement seller_state;
	
	@FindBy(id="seller_zip")
	WebElement seller_zip;
	
	@FindBy(id="sqft")
	WebElement sqft;
	
	@FindBy(id="beds")
	WebElement beds;
	
	@FindBy(id="baths")
	WebElement baths;
	
	@FindBy(id="first_name")
	WebElement first_name;
	
	@FindBy(id="last_name")
	WebElement last_name;
	
	@FindBy(id="email")
	WebElement email;
	
	@FindBy(id="subscribe")
	WebElement subscribe;
	
	@FindBy(id="hv_lead_capture_display_privacy")
	WebElement hv_lead_capture_display_privacy;
	
	@FindBy(id="submitButton")
	WebElement submitButton;
	
	
	public ZWHomeEvaluationPage() {
		// TODO Auto-generated constructor stub
	}
	
	public ZWHomeEvaluationPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public boolean clickOnHomeEvaluationButton() {
		boolean isClicked = false;
		if(ActionHelper.waitForElementToBeVisible(driver, home_evaluation_button,30)){
			isClicked = ActionHelper.Click(driver, home_evaluation_button);
		}
		return isClicked;
	}
	public boolean typeSellerStreet(String pStreet) {
		boolean isTyped = false;
		if(ActionHelper.waitForElementToBeVisible(driver, seller_street, 30)) {
			isTyped = ActionHelper.ClearAndType(driver, seller_street, pStreet);
		}
		return isTyped; 
	}
	public boolean typeSellerCity(String pCity) {
		return ActionHelper.ClearAndType(driver, seller_city, pCity);
	}
	public boolean typeSellerState(String pStreet) {
		return ActionHelper.ClearAndType(driver, seller_state, pStreet);
	}
	public boolean typeSellerZip(String pStreet) {
		return ActionHelper.ClearAndType(driver, seller_zip, pStreet);
	}
	public boolean selectSqFeet(String pSqFeet) {
		return ActionHelper.selectDropDownOption(driver, sqft, "", pSqFeet);
	}
	public boolean selectBeds(String pBeds) {
		return ActionHelper.selectDropDownOption(driver, beds, "", pBeds);
	}
	public boolean selectBaths(String pBaths) {
		return ActionHelper.selectDropDownOption(driver, baths, "", pBaths);
	}
	public boolean typeFirstName(String pFirstName) {
		return ActionHelper.ClearAndType(driver, first_name, pFirstName);
	}
	public boolean typeLastName(String pLastName) {
		return ActionHelper.ClearAndType(driver, last_name, pLastName);
	}
	public boolean typeEmail(String pEmail) {
		return ActionHelper.ClearAndType(driver, email, pEmail);
	}
	public boolean isEmailCheckboxChecked() {
		return ActionHelper.isElementSelected(driver, subscribe);
	}
	public boolean isEmailDataPrivacyPolicyChecked() {
		return ActionHelper.isElementSelected(driver, hv_lead_capture_display_privacy);
	}
	public boolean clickOnSubmitButton() {
		return ActionHelper.Click(driver, submitButton);
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
