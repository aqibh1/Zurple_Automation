/**
 * 
 */
package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class EmailSearchForm extends AbstractForm{
	WebDriver driver;
	
	String input_fields_xpath = "//input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@class='modal-header']/descendant::h3[text()='Email This Search']")
	WebElement modal_header;
	
	@FindBy(xpath="//div[@class='modal-footer']/button[@data-form-id='#email_search_form']")
	WebElement send_button;
	
	@FindBy(xpath="//div[@id='email_search_notify']/descendant::strong[text()='Email will be sent shortly']")
	WebElement email_sent_shortly_notification;
	private ActionHelper actionHelper;
	
	public EmailSearchForm(WebDriver pWebDriver){
		driver=pWebDriver;
		setDriver(pWebDriver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean isEmailSearchModalVisible() {
		return actionHelper.isElementVisible( modal_header);
	}
	public boolean typeLeadName(String pName) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "email_search_form[name]"), pName);
	}

	public boolean typeEmailAddress(String pEmail) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "email_search_form[email]"), pEmail);
	}
	
	public boolean typePhoneNumber(String pPhoneNumber) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "email_search_form[phone]"), pPhoneNumber);
	}
	
	public boolean typeR1Name(String pName) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "email_search_form[name_r1]"), pName);
	}
	
	public boolean typeR1Email(String pEmail) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "email_search_form[email_r1]"), pEmail);
	}
	
	public boolean typeR2Name(String pName) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "email_search_form[name_r2]"), pName);
	}
	public boolean typeR2Email(String pEmail) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "email_search_form[email_r2]"), pEmail);
	}
	public boolean clickOnSendButton() {
		return actionHelper.Click( send_button);
	}
	public boolean waitForModalToBeDisappear() {
		return actionHelper.waitForElementToBeDisappeared( modal_header);
	}
	public boolean isEmailSent() {
		boolean status =  actionHelper.waitForElementToBeDisappeared( modal_header);
		if(!status) {
			status = actionHelper.isElementVisible( email_sent_shortly_notification);
		}
		return status;
	}
	public String getLeadName() {
		return actionHelper.getText(getDynamicElement(input_fields_xpath, "email_search_form[name]"));
	}
	public String getLeadEmail() {
		return actionHelper.getText(getDynamicElement(input_fields_xpath, "email_search_form[email]"));
	}


}
