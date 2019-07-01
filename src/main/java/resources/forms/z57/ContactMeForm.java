package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.forms.AbstractForm;
import resources.utility.FrameworkConstants;

public class ContactMeForm extends AbstractForm{
	
	String input_fileds_xpath="//li[@class='widget-container Contact_Me_Widget']/descendant::input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String textarea_xpath="//li[@class='widget-container Contact_Me_Widget']/descendant::textarea[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//li[@class='widget-container Contact_Me_Widget']/descendant::button[@value='Submit']")
	WebElement send_button;
	
	@FindBy(xpath="//li[@class='widget-container Contact_Me_Widget']/descendant::strong[text()='Thank you!']")
	WebElement thankyou_alert;
	
	public ContactMeForm(WebDriver pWebDriver){
		setPageObject(pWebDriver, this);
	}
	
	public boolean typeName(String pName) {
		return actionHelper.Type(getDynamicElement(input_fileds_xpath, "contact_me[name]"),pName);
	}
	public boolean typeEmail(String pEmail) {
		return actionHelper.Type(getDynamicElement(input_fileds_xpath, "contact_me[email]"),pEmail);
	}
	public boolean typePhoneNumber(String pPhoneNumber) {
		return actionHelper.Type(getDynamicElement(input_fileds_xpath, "contact_me[phone]"),pPhoneNumber);
	}
	public boolean typeComments(String pComments) {
		return actionHelper.Type(getDynamicElement(textarea_xpath, "contact_me[comments]"),pComments);
	}
	public boolean isThankyouAlertVisible() {
		return actionHelper.waitForElementToBeVisible(thankyou_alert, 20);
	}
	public boolean clickOnSendButton(){
		return actionHelper.Click(send_button);

	}
}
