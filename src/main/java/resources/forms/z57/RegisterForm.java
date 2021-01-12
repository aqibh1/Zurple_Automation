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
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class RegisterForm extends AbstractForm{


	@FindBy(id="widget_login_topbar")
	WebElement alreadyregistered_link;
	
	@FindBy(xpath="//input[@id='top_bar_lead_reg_name']")
	WebElement name_input;
	
	@FindBy(xpath="//input[@id='top_bar_lead_reg_email']")
	WebElement email_input;
	
	@FindBy(xpath="//input[@id='top_bar_lead_reg_phone']")
	WebElement phone_input;
	
	@FindBy(xpath="//button[@id='wp-submit-register_topbar']")
	WebElement register_button;
	
	@FindBy(xpath="//div[@class='controls']/descendant::a[@id='login-modal-register-button']")
	WebElement alreadyregistered_link_idx;

	public RegisterForm() {
		
	}
	
	public RegisterForm(WebDriver webDriver) {
		setDriver(webDriver);
		PageFactory.initElements(driver, this);
		
	}

	public boolean clickOnAlreadyRegistered() {
		boolean isSuccess = false;
		if(ActionHelper.waitForElementToBeVisible(driver, alreadyregistered_link, 15)) {
			isSuccess = ActionHelper.Click(driver, alreadyregistered_link);
			ActionHelper.staticWait(3);
		}
		return isSuccess;
	}
	public boolean setName(String pName) {
		return type(name_input, pName);
	}
	public boolean setEmail(String pEmail) {
		return type(email_input, pEmail);
	}
	public boolean setPhoneNumber(String pPhoneNumber) {
		return type(phone_input,pPhoneNumber);
	}
	public boolean clickOnRegisterButton() {
		return click(register_button);
	}
	public boolean isUserSuccessfullyRegistered() {
		return waitForElementToBeDisappeared(register_button);
	}
	public boolean isRegisterFormDisplayed() {
		AutomationLogger.info("Waiting for Register Form to be displayed");
		return ActionHelper.isElementVisible(driver, alreadyregistered_link);
	}
	public boolean clickOnAlreadyRegisterIdx() {
		return ActionHelper.Click(driver, alreadyregistered_link_idx);
	}
	
	
}
