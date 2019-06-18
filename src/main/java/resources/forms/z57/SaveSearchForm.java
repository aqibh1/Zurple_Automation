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
public class SaveSearchForm extends AbstractForm{
	WebDriver driver;

	String input_fields_xpath = "//input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";

	@FindBy(xpath="//div[@class='modal-header']/descendant::h3[text()='Save Search']")
	WebElement modal_header;

	@FindBy(xpath="//div[@class='modal-footer']/button[@data-form-id='#save_search_form']")
	WebElement save_button;

	@FindBy(xpath="//div[@id='save_search_notify']/descendant::strong[text()='Search Saved']")
	WebElement search_saved_notification;
	ActionHelper actionHelper;
	public SaveSearchForm(WebDriver pWebDriver){
		driver=pWebDriver;
		setDriver(pWebDriver);
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean isSaveSearchModalVisible() {
		return actionHelper.isElementVisible(modal_header);
	}
	public boolean typeLeadName(String pName) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "save_search_form[name]"), pName);
	}

	public boolean typeEmailAddress(String pEmail) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "save_search_form[email]"), pEmail);
	}
	
	public boolean typePhoneNumber(String pPhoneNumber) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "save_search_form[phone]"), pPhoneNumber);
	}

	public boolean typeTitle(String pTitle) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "save_search_form[title]"), pTitle);
	}

	public boolean clickOnSaveButton() {
		return actionHelper.Click(save_button);
	}
	public boolean waitForModalToBeDisappear() {
		return actionHelper.waitForElementToBeDisappeared(modal_header);
	}
	public boolean isSearchSaved() {
		boolean status =  actionHelper.waitForElementToBeDisappeared(modal_header);
		if(!status) {
			status = actionHelper.isElementVisible(search_saved_notification);
		}
		return status;
	}
	public String getLeadName() {
		return actionHelper.getText(getDynamicElement(input_fields_xpath, "save_search_form[name]"));
	}
	public String getLeadEmail() {
		return actionHelper.getText(getDynamicElement(input_fields_xpath, "save_search_form[email]"));
	}

	public String getTitle() {
		return actionHelper.getValue(getDynamicElement(input_fields_xpath, "save_search_form[saved_search_title]"));
	}


}
