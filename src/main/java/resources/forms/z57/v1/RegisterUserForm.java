package resources.forms.z57.v1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;

public class RegisterUserForm extends AbstractForm{
	
	@FindBy(xpath="//input[@name='lead_reg_form[name]']")
	WebElement name_input;
	
	@FindBy(xpath="//input[@name='lead_reg_form[email]']")
	WebElement email_input;
	
	@FindBy(xpath="//input[@name='lead_reg_form[phone]']")
	WebElement phone_input;
	
	@FindBy(id="lead_reg_send")
	WebElement register_button;
	
	@FindBy(xpath="//div[@id='registerModal']/descendant::h3[text()='Sign In']")
	WebElement signIn_heading;
	
	public RegisterUserForm(){
		
	}
	public RegisterUserForm(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
		
	}
	public boolean typeLeadName(String pName) {
		return ActionHelper.Type(driver, name_input, pName);
	}
	
	public boolean typeEmailAddress(String pEmail) {
		return ActionHelper.Type(driver, email_input, pEmail);
	}
	
	public boolean typePhoneNumber(String pPhone) {
		return ActionHelper.Type(driver, phone_input, pPhone);
	}
	
	public boolean clickOnRegisterButton() {
		return ActionHelper.Click(driver, register_button);
	}
	public boolean isRegisterForm() {
		return ActionHelper.waitForElementToBeVisible(driver, signIn_heading, 10);
	}
	public boolean isLeadRegistered() {
		return ActionHelper.waitForElementToBeDisappeared(driver, "//div[@id='registerModal']/descendant::h3[text()='Sign In']");
	}

}
