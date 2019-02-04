package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class LeadCaptureForm extends AbstractForm{
	String input_fileds_xpath="//form[@id='anypage_lead_capture_form']/descendant::input[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String textarea_xpath="//form[@id='anypage_lead_capture_form']/descendant::textarea[@name='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";

	@FindBy(xpath="//button[@id='anypage_lead_capture_send']")
	WebElement send_button;
	
	@FindBy(xpath="//div[@id='login_with_fb_button']/a[@id='login_with_fb']")
	WebElement login_with_fb_button;
	
	@FindBy(xpath="//div[@class='modal-header']/h3[@id='anypage_lead_capture_modal_title']")
	WebElement modal_header_title;
	
	public LeadCaptureForm(WebDriver pWebDriver){
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeName(String pName) {
		return ActionHelper.Type(driver, getDynamicElement(input_fileds_xpath, "anypage_lead_capture_form[name]"),pName);
	}
	public boolean typeEmail(String pEmail) {
		return ActionHelper.Type(driver, getDynamicElement(input_fileds_xpath, "anypage_lead_capture_form[email]"),pEmail);
	}
	public boolean typePhoneNumber(String pPhoneNumber) {
		return ActionHelper.Type(driver, getDynamicElement(input_fileds_xpath, "anypage_lead_capture_form[phone]"),pPhoneNumber);
	}
	public boolean typeComments(String pComments) {
		return ActionHelper.Type(driver, getDynamicElement(textarea_xpath, "anypage_lead_capture_form[comments]"),pComments);
	}
	public boolean clickOnSendButton() {
		return ActionHelper.Click(driver, send_button);

	}
	public boolean isCommentsSentSuccessfully() {
		return ActionHelper.waitForElementToBeDisappeared(driver,send_button);
	}
	
	public boolean isLeadCaptureFormVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, modal_header_title,20);
	}
	
}
