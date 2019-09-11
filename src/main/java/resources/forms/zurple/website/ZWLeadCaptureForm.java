package resources.forms.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;

public class ZWLeadCaptureForm extends AbstractForm{
	
	@FindBy(xpath="//h4[@class='modal-title' and text()='Register Below']")
	WebElement registerBelow_heading;
	
	@FindBy(id="first_name")
	WebElement name_input;
	
	@FindBy(id="email")
	WebElement email_input;
	
	@FindBy(id="phone")
	WebElement phone_input;
	
	@FindBy(id="subscribe")
	WebElement subscribe_checkbox;
	
	@FindBy(xpath="//a[@href='/login']")
	WebElement alreadyRegistered;
	
	@FindBy(xpath="//input[@name='register_button']")
	WebElement register_button;
	
	public ZWLeadCaptureForm() {
		
	}
	
	public ZWLeadCaptureForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isLeadCaptureFormIsVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, registerBelow_heading, 30);
	}
	
	public boolean typeName(String pName) {
		return ActionHelper.Type(driver, name_input, pName);
	}
	
	public boolean typeEmail(String pEmail) {
		return ActionHelper.Type(driver, email_input, pEmail);
	}
	
	public boolean typePhone(String pPhone) {
		if(!pPhone.isEmpty()) {
			return ActionHelper.Type(driver, phone_input, pPhone);
		}else {
			return true;
		}
	}
	public boolean clickOnRegisterButton() {
		return ActionHelper.Click(driver, register_button);
	}
	
	public boolean isSubscribed() {
		return ActionHelper.getAttribute(subscribe_checkbox, "value").equalsIgnoreCase("1");
	}
	public boolean isAlreadyRegisteredLinkVisible() {
		return ActionHelper.isElementVisible(driver, alreadyRegistered);
	}

}
