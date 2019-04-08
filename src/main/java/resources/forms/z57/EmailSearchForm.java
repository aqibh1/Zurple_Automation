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
	
	public EmailSearchForm(WebDriver pWebDriver){
		driver=pWebDriver;
		setDriver(pWebDriver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean isEmailSearchModalVisible() {
		return ActionHelper.isElementVisible(driver, modal_header);
	}
	public boolean typeLeadName(String pName) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "email_search_form[name]"), pName);
	}

	public boolean typeEmailAddress(String pEmail) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "email_search_form[email]"), pEmail);
	}
	
	public boolean typePhoneNumber(String pPhoneNumber) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "email_search_form[phone]"), pPhoneNumber);
	}
	
	public boolean typeR1Name(String pName) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "email_search_form[name_r1]"), pName);
	}
	
	public boolean typeR1Email(String pEmail) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "email_search_form[email_r1]"), pEmail);
	}
	
	public boolean typeR2Name(String pName) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "email_search_form[name_r2]"), pName);
	}
	public boolean typeR2Email(String pEmail) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "email_search_form[email_r2]"), pEmail);
	}
	public boolean clickOnSendButton() {
		return ActionHelper.Click(driver, send_button);
	}
	public boolean waitForModalToBeDisappear() {
		return ActionHelper.waitForElementToBeDisappeared(driver, modal_header);
	}
	public boolean isEmailSent() {
		boolean status =  ActionHelper.waitForElementToBeDisappeared(driver, modal_header);
		if(!status) {
			status = ActionHelper.isElementVisible(driver, email_sent_shortly_notification);
		}
		return status;
	}
	public String getLeadName() {
		return ActionHelper.getText(driver,getDynamicElement(input_fields_xpath, "email_search_form[name]"));
	}
	public String getLeadEmail() {
		return ActionHelper.getText(driver,getDynamicElement(input_fields_xpath, "email_search_form[email]"));
	}


}