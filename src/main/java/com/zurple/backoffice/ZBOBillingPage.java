package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.forms.zurple.backoffice.ZBOAddCreditCardForm;
import resources.utility.ActionHelper;

public class ZBOBillingPage extends Page{

	@FindBy(xpath="//h3[text()=' Billing']")
	WebElement billing_heading;
	
	@FindBy(xpath="//div[@id='cc-grid']/descendant::a[@class='update-cc paymentbtn btn']")
	WebElement add_payment_button;
	
	private ZBOAddCreditCardForm creditCardForm;
	
	public ZBOBillingPage() {
		
	}
	public ZBOAddCreditCardForm getCreditCardForm() {
		return creditCardForm;
	}
	public void setCreditCardForm() {
		this.creditCardForm = new ZBOAddCreditCardForm(driver);
	}

	public ZBOBillingPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
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
}
