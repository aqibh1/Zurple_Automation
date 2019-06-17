/**
 * 
 */
package resources.forms.z57.v1;

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
public class SaveSearchForm extends AbstractForm{
	
	@FindBy(xpath="//input[@name='save_search_form[name]']")
	WebElement name_input;
	
	@FindBy(xpath="//input[@name='save_search_form[email]']")
	WebElement email_input;
	
	@FindBy(xpath="//input[@name='save_search_form[phone]']")
	WebElement phone_input;
	
	@FindBy(id="saved_search_title")
	WebElement savedSearchTitle_input;
	
	@FindBy(id="email_alerts_checkbox")
	WebElement emailAlerts_checkbox;
	
	@FindBy(id="save_search_send")
	WebElement saveSearchSend_button;
	
	@FindBy(xpath="//div[@id='saveSearchModal']/descendant::h3[text()='Save Search']")
	WebElement saveSearchFormHeader;
	
	private ActionHelper actionHelper;
	
	public SaveSearchForm() {
		
	}
	public SaveSearchForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver, this);
	}
	public boolean typeLeadName(String pName) {
		return actionHelper.Type(name_input, pName);
	}
	
	public boolean typeEmailAddress(String pEmail) {
		return actionHelper.Type(email_input, pEmail);
	}
	
	public boolean typePhoneNumber(String pPhone) {
		return actionHelper.Type(phone_input, pPhone);
	}
	
	public boolean typeSearchTitle(String pSearchTitle) {
		return actionHelper.ClearAndType(savedSearchTitle_input, pSearchTitle);
	}
	
	public boolean selectEmailAlertsCheckbox() {
		boolean isSelected = false;
		if(!emailAlerts_checkbox.isSelected()) {
			isSelected = actionHelper.Click(emailAlerts_checkbox);
		}else {
			isSelected = true;
		}
		return isSelected;
	}
	
	public boolean clickOnSaveButton() {
		return actionHelper.Click(saveSearchSend_button);
	}
	
	public boolean isSaveSearchForm() {
		return actionHelper.waitForElementToBeVisible(saveSearchFormHeader, 15);
	}
	
	public String getLeadName() {
		return actionHelper.getText(name_input);
	}
	
	public String getLeadEmail() {
		return actionHelper.getText(email_input);
	}
	
	public String getTitle() {
		return actionHelper.getText(savedSearchTitle_input);
	}
	public boolean isSearchSaved() {
		return actionHelper.waitForElementToBeDisappeared(saveSearchFormHeader);
	}
	public boolean isLeadEmailInputVisible() {
		return actionHelper.waitForElementToBeVisible(email_input, 5);
	}
}
