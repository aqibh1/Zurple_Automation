package resources.forms.zurple.website;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;

public class ZWContactAgentForm extends AbstractForm{
	
	@FindBy(id="name")
	WebElement name_input;
	
	@FindBy(id="email")
	WebElement email_input;

	@FindBy(id="phone")
	WebElement phone_input;
	
	@FindBy(id="comment")
	WebElement comment_input;
	
	@FindBy(id="schedule")
	WebElement schedule_checkbox;
	
	@FindBy(xpath="//button[@type='submit']")
	WebElement contactAgent_button;
	
	@FindBy(id="contact-agent-frame")
	WebElement contactAgent_frame;
	
	@FindBy(xpath="//button[@class='confirm']")
	WebElement ok_button;
	
	public ZWContactAgentForm() {
		
	}
	public ZWContactAgentForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isContactAgentFormVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, contactAgent_frame, 30);
	}
	public boolean typeName(String pName) {
		return ActionHelper.Type(driver, name_input, pName);
	}
	public boolean typeEmail(String pEmail) {
		return ActionHelper.Type(driver, email_input, pEmail);
	}
	public boolean typePhone(String pPhone) {
		if(!pPhone.isEmpty()) {
			return ActionHelper.Type(driver, phone_input, pPhone);
		}else {
			return true;
		}
	}
	public boolean typeComments(String pComments) {
		if(!pComments.isEmpty()) {
			return ActionHelper.ClearAndType(driver, comment_input, pComments);
		}else {
			return true;
		}
	}
	public boolean isScheduleShowingChecked() {
		return !ActionHelper.isElementSelected(driver, schedule_checkbox);
	}
	public boolean clickContactAgentButton() {
		return ActionHelper.Click(driver, contactAgent_button);
	}
	public boolean isContactSuccessful() {
		boolean isSuccessful = false;
		if(ActionHelper.waitForElementToBeVisible(driver, ok_button, 30)) {
			isSuccessful = ActionHelper.Click(driver, ok_button);
		}
		return isSuccessful;
	}
	public boolean verifyLeadName(String pName) {
		return ActionHelper.getAttribute(name_input, "value").equalsIgnoreCase(pName);
	}
}
