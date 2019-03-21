package resources.forms.pp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;

public class PPAddLeadForm extends AbstractForm{
	
	@FindBy(xpath="//div[@id='add_new_lead_modal']/descendant::h3[text()='Add New Lead']")
	WebElement addNewLead_header;
	
	@FindBy(xpath="//input[@id='new_lead_name']")
	WebElement new_lead_name;
	
	@FindBy(xpath="//input[@id='new_lead_email']")
	WebElement new_lead_email;
	
	@FindBy(xpath="//input[@id='new_lead_phone']")
	WebElement new_lead_phone;
	
	@FindBy(xpath="//button[@id='new_lead_confirm']")
	WebElement continue_button;
	
	public PPAddLeadForm() {
		// TODO Auto-generated constructor stub
	}
	public PPAddLeadForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isAddNewLeadFormVisible() {
		return ActionHelper.isElementVisible(driver, addNewLead_header);
	}
	
	public boolean typeLeadName(String pNewLeadName) {
		return ActionHelper.Type(driver, new_lead_name, pNewLeadName);
	}
	
	public boolean typeLeadEmail(String pNewLeadEmail) {
		return ActionHelper.Type(driver, new_lead_email, pNewLeadEmail);
	}
	
	public boolean typeLeadPhone(String pPhoneNumber) {
		return ActionHelper.Type(driver, new_lead_phone, pPhoneNumber);
	}
	
	public boolean clickOnContinueButton() {
		return ActionHelper.Click(driver, continue_button);
	}
	public boolean isLeadAddedSuccessfully() {
		return ActionHelper.waitForElementToBeDisappeared(driver, addNewLead_header);
	}

}
