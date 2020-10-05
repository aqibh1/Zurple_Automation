/**
 * 
 */
package resources.forms.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

/**
 * @author adar
 *
 */
public class ZBOAddReminderForm extends AbstractForm{
	@FindBy(xpath="//div[@id='add-reminder-lead-modal']/descendant::h4[text()='Add Reminder']")
	WebElement addReminder_heading;
	
	@FindBy(id="text_area_reminder")
	WebElement add_reminder_input;
	
	@FindBy(id="task_reminder_date_1")
	WebElement taskReminder_date_input;
	
	@FindBy(xpath="//button[text()='Done']")
	WebElement done_date_button;

	@FindBy(xpath="//button[text()='Now']")
	WebElement now_date_button;

	@FindBy(id="submit_save_reminder")
	WebElement add_reminder_button;
	
	String existing_crm_xpath="//div[@id='z-lead-reminders']/descendant::div[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String existing_crm_date_xpath="//div[@id='z-lead-reminders']/descendant::div[contains(text(),'"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	private ZBOSucessAlert successAlert;
	
	public ZBOAddReminderForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		setSuccessAlert();
		PageFactory.initElements(driver, this);
	}
	public ZBOSucessAlert getSuccessAlert() {
		return successAlert;
	}
	public void setSuccessAlert() {
		this.successAlert = new ZBOSucessAlert(driver);
	}
	public boolean isAddReminderForm() {
		return ActionHelper.waitForElementToBeVisible(driver, addReminder_heading, 30);
	}
	public boolean typeComment(String pComment) {
		return ActionHelper.ClearAndType(driver, add_reminder_input, pComment);
	}
	public boolean clickOnDateReminder() {
		return ActionHelper.Click(driver, taskReminder_date_input);
	}
	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, add_reminder_button);
	}
	public boolean clickOnDateDoneButton() {
		boolean isTimeSelected = false;
		if(ActionHelper.Click(driver, now_date_button)) {
			ActionHelper.staticWait(2);
			isTimeSelected = ActionHelper.Click(driver, done_date_button);
		}
		return isTimeSelected;
	}
	public boolean isCommentAddedSuccessfully(String pComment) {
		ActionHelper.staticWait(15);
		return ActionHelper.waitForElementToBeVisible(driver, ActionHelper.getDynamicElement(driver, existing_crm_xpath, pComment), 30);
	}
	public boolean isDateCorrect(String pDate) {
		ActionHelper.staticWait(15);
		return ActionHelper.waitForElementToBeVisible(driver, ActionHelper.getDynamicElement(driver, existing_crm_date_xpath, pDate), 30);
	}
	public boolean confirmDeleteAlert() {
		boolean isDeleted = false;
		if(ActionHelper.isAlertPresent(driver)) {
			isDeleted = ActionHelper.handleDisableAdAlert(driver);
		}
		return isDeleted;
	}
	
}
