package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.forms.AbstractForm;
import resources.utility.FrameworkConstants;

public class ScheduleListingForm extends AbstractForm{
	
	String input_fields_xpath = "//input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	String textArea_xpath = "//textarea[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	@FindBy(xpath="//div[@class='modal-header']/descendant::h3[text()='Schedule a Showing']")
	WebElement modal_header;
	
	@FindBy(xpath="//button[@id='schedule_showing_send']")
	WebElement save_button;
	
	@FindBy(xpath="//div[@id='schedule_showing_modal_notify']/descendant::strong[text()='Your request has been sent']")
	WebElement success_notification;
	
	public ScheduleListingForm(WebDriver pWebDriver){
		setPageObject(pWebDriver, this);
	}
	
	public boolean isScheduleListingModalVisible() {
		return actionHelper.isElementVisible(modal_header);
	}
	public boolean typeLeadName(String pName) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "schedule_showing_form[name]"), pName);
	}
	public boolean typeLeadEmail(String pEmail) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "schedule_showing_form[email]"), pEmail);
	}
	public boolean typeLeadPhoneNumber(String pPhone) {
		return actionHelper.ClearAndType(getDynamicElement(input_fields_xpath, "schedule_showing_form[phone]"), pPhone);
	}
	public boolean typeComments(String pComments) {
		return actionHelper.ClearAndType(getDynamicElement(textArea_xpath, "schedule_showing_form[comments]"), pComments);
	}
	public boolean clickOnSaveButton() {
		return actionHelper.Click(save_button);
	}
	public boolean isScheduleShowingRequestSent() {
		boolean status =  actionHelper.waitForElementToBeDisappeared(modal_header);
		if(!status) {
			status = actionHelper.isElementVisible(success_notification);
		}
		return status;
	}

}
