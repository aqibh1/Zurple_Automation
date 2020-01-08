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

/**
 * @author adar
 *
 */
public class SEMRegisterForm extends AbstractForm {

	@FindBy(id="anypage_lead_capture_modal_title")
	WebElement register_heading;
	
	@FindBy(xpath="//input[@name='anypage_lead_capture_form[name]']")
	WebElement name_input;
	
	@FindBy(xpath="//input[@name='anypage_lead_capture_form[email]']")
	WebElement email_input;
	
	@FindBy(xpath="//input[@name='anypage_lead_capture_form[phone]']")
	WebElement phone_input;
	
	@FindBy(xpath="//button[@id='anypage_lead_capture_send']")
	WebElement send_button;
	
	public SEMRegisterForm() {
		
	}
	public SEMRegisterForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isRegisterFormVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, register_heading, 20);
	}
	public boolean typeName(String pName) {
		return ActionHelper.Type(driver, name_input, pName);
	}
	public boolean typeEmail(String pEmail) {
		return ActionHelper.Type(driver, email_input, pEmail);
	}
	public boolean typePhone(String pPhoneNum) {
		return ActionHelper.Type(driver, phone_input, pPhoneNum);
	}
	public boolean clickOnSendButton() {
		return ActionHelper.Click(driver, send_button);
	}
	public boolean isLeadRegisteredSuccessfully() {
		ActionHelper.staticWait(10);
		return !ActionHelper.isElementVisible(driver, send_button);
	}
	
	
}
