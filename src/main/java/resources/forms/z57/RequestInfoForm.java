package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.forms.AbstractForm;
import resources.utility.FrameworkConstants;

public class RequestInfoForm extends AbstractForm{
	
	String input_fields_xpath = "//input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	String textArea_xpath = "//textarea[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@class='modal-header']/descendant::h3[text()='Contact Agent About Listing']")
	WebElement modal_header;
	
	@FindBy(xpath="//button[@id='rq_info_listing_finish']")
	WebElement save_button;
	
	@FindBy(xpath="//div[@id='rq_info_listing_form_notify']/descendant::strong[text()='Your request has been sent']")
	WebElement success_notification;

	public RequestInfoForm(WebDriver pWebDriver){
		setPageObject(pWebDriver, this);
	}
	
	public boolean isListingEmailModalVisible() {
		return actionHelper.isElementVisible(modal_header);
	}
	public boolean typeLeadName(String pName) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "rq_info_listing_form[name]"), pName);
	}
	public boolean typeLeadEmail(String pEmail) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "rq_info_listing_form[email]"), pEmail);
	}
	public boolean typeLeadPhoneNumber(String pPhone) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "rq_info_listing_form[phone]"), pPhone);
	}
	public boolean typeComments(String pComments) {
		return actionHelper.ClearAndType(getDynamicElement(textArea_xpath, "rq_info_listing_form[comments]"), pComments);
	}
	public boolean clickOnSaveButton() {
		return actionHelper.Click(save_button);
	}
	public boolean isRequestInfoSent() {
		boolean status = actionHelper.isElementVisible(success_notification);
		if(!status) {
			status =  actionHelper.waitForElementToBeDisappeared(modal_header);
		}
		return status;
	}
	public String getLeadName() {
		return actionHelper.getText(getDynamicElement(input_fields_xpath, "rq_info_listing_form[name]"));
	}
	public String getLeadEmail() {
		return actionHelper.getText(getDynamicElement(input_fields_xpath, "rq_info_listing_form[email]"));
	}
}
