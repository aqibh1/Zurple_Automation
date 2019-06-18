/**
 * 
 */
package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class RegisterForm extends AbstractForm{


	@FindBy(xpath="//div[@class='login-links']/a[@id='widget_login_topbar']")
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
	
	private ActionHelper actionHelper;
	
	public RegisterForm() {
		
	}
	
	public RegisterForm(WebDriver webDriver) {
		setDriver(webDriver);
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver, this);
		
	}

	public boolean clickOnAlreadyRegistered() {
		wait.until(ExpectedConditions.elementToBeClickable(alreadyregistered_link));
		if(alreadyregistered_link.isDisplayed()) {
			alreadyregistered_link.click();
			return true;
		}else {
			return false;
		}

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
		return actionHelper.isElementVisible(alreadyregistered_link);
	}
	public boolean clickOnAlreadyRegisterIdx() {
		return actionHelper.Click(alreadyregistered_link_idx);
	}
}
