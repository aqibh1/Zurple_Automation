package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.forms.zurple.backoffice.ZBOAddCreditCardForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class ZBOBillingPage extends Page{

	@FindBy(xpath="//h3[text()=' Billing']")
	WebElement billing_heading;
	
	@FindBy(xpath="//div[@id='cc-grid']/descendant::a[@class='update-cc paymentbtn btn']")
	WebElement add_payment_button;
	
	String billing_info_xpath = "//div[@id='cc-grid']/descendant::td[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	@FindBy(xpath="//a[@class='update-cc' and text()='Click Here To Update']")
	WebElement clickToUpdateButton;
	
	private ZBOAddCreditCardForm creditCardForm;
	
	public ZBOBillingPage() {
	}
	public ZBOBillingPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
		setCreditCardForm();
	}
	public ZBOAddCreditCardForm getCreditCardForm() {
		return creditCardForm;
	}
	public void setCreditCardForm() {
		this.creditCardForm = new ZBOAddCreditCardForm(driver);
	}

	public boolean isBilingPage() {
		return ActionHelper.isElementVisible(driver, billing_heading);
	}
	public boolean isAddPaymentPlanButtonVisible() {
		return ActionHelper.isElementVisible(driver, add_payment_button);
	}
	public boolean clickOnAddPaymentPlan() {
		return ActionHelper.Click(driver, add_payment_button);
	}
	public boolean isCardHolderNameDisplayed(String pName) {
		return isInformationDisplayed(pName);
	}
	public boolean isCardHolderAddressDisplayed(String pAddress) {
		return isInformationDisplayed(pAddress);
	}
	public boolean isCardNumberDisplayed(String pNumber) {
		return isInformationDisplayed(pNumber);
	}
	public boolean isCardExpirationDateDisplayed(String pDate) {
		return isInformationDisplayed(pDate);
	}
	public boolean isClickHereToUpdateButtonVisible() {
		return ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver, clickToUpdateButton, 30, 15);
	}
	private boolean isInformationDisplayed(String pInfo) {
		return ActionHelper.getDynamicElement(driver, billing_info_xpath, pInfo)!=null;
	}
}
