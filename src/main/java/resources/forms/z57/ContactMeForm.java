package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class ContactMeForm extends AbstractForm{
	
	String input_fileds_xpath="//li[@class='widget-container Contact_Me_Widget']/descendant::input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String textarea_xpath="//li[@class='widget-container Contact_Me_Widget']/descendant::textarea[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//li[@class='widget-container Contact_Me_Widget']/descendant::button[@value='Submit']")
	WebElement send_button;
	
	@FindBy(xpath="//li[@class='widget-container Contact_Me_Widget']/descendant::strong[text()='Thank you!']")
	WebElement thankyou_alert;
	
	public ContactMeForm(WebDriver pWebDriver){
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeName(String pName) {
		return ActionHelper.Type(driver, getDynamicElement(input_fileds_xpath, "contact_me[name]"),pName);
	}
	public boolean typeEmail(String pEmail) {
		return ActionHelper.Type(driver, getDynamicElement(input_fileds_xpath, "contact_me[email]"),pEmail);
	}
	public boolean typePhoneNumber(String pPhoneNumber) {
		return ActionHelper.Type(driver, getDynamicElement(input_fileds_xpath, "contact_me[phone]"),pPhoneNumber);
	}
	public boolean typeComments(String pComments) {
		return ActionHelper.Type(driver, getDynamicElement(textarea_xpath, "contact_me[comments]"),pComments);
	}
	public boolean isThankyouAlertVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, thankyou_alert, 20);
	}
	public boolean clickOnSendButton(){
		return ActionHelper.Click(driver, send_button);

	}
}
