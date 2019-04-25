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

	public SaveSearchForm(WebDriver pWebDriver){
		driver=pWebDriver;
		setDriver(pWebDriver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean isSaveSearchModalVisible() {
		return ActionHelper.isElementVisible(driver, modal_header);
	}
	public boolean typeLeadName(String pName) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "save_search_form[name]"), pName);
	}

	public boolean typeEmailAddress(String pEmail) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "save_search_form[email]"), pEmail);
	}
	
	public boolean typePhoneNumber(String pPhoneNumber) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "save_search_form[phone]"), pPhoneNumber);
	}

	public boolean typeTitle(String pTitle) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "save_search_form[title]"), pTitle);
	}

	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, save_button);
	}
	public boolean waitForModalToBeDisappear() {
		return ActionHelper.waitForElementToBeDisappeared(driver, modal_header);
	}
	public boolean isSearchSaved() {
		boolean status =  ActionHelper.waitForElementToBeDisappeared(driver, modal_header);
		if(!status) {
			status = ActionHelper.isElementVisible(driver, search_saved_notification);
		}
		return status;
	}
	public String getLeadName() {
		return ActionHelper.getText(driver,getDynamicElement(input_fields_xpath, "save_search_form[name]"));
	}
	public String getLeadEmail() {
		return ActionHelper.getText(driver,getDynamicElement(input_fields_xpath, "save_search_form[email]"));
	}

	public String getTitle() {
		return ActionHelper.getValue(driver,getDynamicElement(input_fields_xpath, "save_search_form[saved_search_title]"));
	}


}
