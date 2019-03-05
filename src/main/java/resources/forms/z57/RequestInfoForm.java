package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class RequestInfoForm extends AbstractForm{
	WebDriver driver;
	
	String input_fields_xpath = "//input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	String textArea_xpath = "//textarea[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@class='modal-header']/descendant::h3[text()='Contact Agent About Listing']")
	WebElement modal_header;
	
	@FindBy(xpath="//button[@id='rq_info_listing_finish']")
	WebElement save_button;
	
	@FindBy(xpath="//div[@id='rq_info_listing_form_notify']/descendant::strong[text()='Your request has been sent']")
	WebElement success_notification;

	public RequestInfoForm(WebDriver pWebDriver){
		driver=pWebDriver;
		setDriver(pWebDriver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean isListingEmailModalVisible() {
		return ActionHelper.isElementVisible(driver, modal_header);
	}
	public boolean typeLeadName(String pName) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "rq_info_listing_form[name]"), pName);
	}
	public boolean typeLeadEmail(String pEmail) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "rq_info_listing_form[email]"), pEmail);
	}
	public boolean typeLeadPhoneNumber(String pPhone) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(input_fields_xpath, "rq_info_listing_form[phone]"), pPhone);
	}
	public boolean typeComments(String pComments) {
		return ActionHelper.ClearAndType(driver,getDynamicElement(textArea_xpath, "rq_info_listing_form[comments]"), pComments);
	}
	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, save_button);
	}
	public boolean isRequestInfoSent() {
		boolean status =  ActionHelper.waitForElementToBeDisappeared(driver, modal_header);
		if(!status) {
			status = ActionHelper.isElementVisible(driver, success_notification);
		}
		return status;
	}
	public String getLeadName() {
		return ActionHelper.getText(driver,getDynamicElement(input_fields_xpath, "rq_info_listing_form[name]"));
	}
	public String getLeadEmail() {
		return ActionHelper.getText(driver,getDynamicElement(input_fields_xpath, "rq_info_listing_form[email]"));
	}
}