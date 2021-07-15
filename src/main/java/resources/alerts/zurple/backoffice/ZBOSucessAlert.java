/**
 * 
 */
package resources.alerts.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.alerts.AbstractAlert;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOSucessAlert extends AbstractAlert{
	
	@FindBy(xpath="//button[text()='OK']")
	WebElement ok_button;
	
	@FindBy(xpath="//h2[text()='Success']")
	WebElement success_text;
	
	@FindBy(xpath="//h2[@id='swal2-title' and text()='New reminder has been added.']")
	WebElement reminder_success_message;
	
	@FindBy(xpath="//button[text()='Post History']")
	WebElement post_history_button;
	
	@FindBy(xpath="//button[text()='Confirm']")
	WebElement confirm_button;
	
	@FindBy(xpath="//button[text()='Scheduled Posts']")
	WebElement scheduled_posts_button;
	
	@FindBy(xpath="//button[text()='Temporary']")
	WebElement temporary_button;
	
	@FindBy(xpath="leads-table_processing")
	WebElement processing;
	
	@FindBy(xpath="//h2[text()='Status has been updated.']")
	WebElement status_updated_text;

	@FindBy(xpath="//button[text()='Assign']")
	WebElement assign_button;

	@FindBy(xpath="//h2[text()='Unenroll from Campaign']")
	WebElement unenroll_from_campaign;;
	@FindBy(xpath="//button[text()='Unenroll']")
	WebElement Unenroll_button;
	
	@FindBy(className="swal2-cancel")
	WebElement permanent_update;
	
	@FindBy(xpath="//button[text()='Override']")
	WebElement override_button;
	
	@FindBy(id="swal2-content")
	WebElement override_modal_text;
	
	
	public ZBOSucessAlert() {
		
	}
	public ZBOSucessAlert(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isSuccessMessageVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, success_text, 30);
	}
	
	public boolean clickOnOkButton() {
		return ActionHelper.Click(driver, ok_button);
	}
	public boolean isReminderSuccessAlertVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, reminder_success_message, 30);
	}
	public boolean clickOnPostHistoryButton() {
		return ActionHelper.Click(driver, post_history_button);
	}
	public boolean clickOnConfirmButton() {
		boolean isClicked = false;
		if(ActionHelper.waitForElementToBeVisible(driver, confirm_button, 30)) {
			isClicked = ActionHelper.Click(driver, confirm_button);
		}
		return isClicked;
	}
	public boolean clickOnScheduledPostButton() {
		return ActionHelper.Click(driver, scheduled_posts_button);
	}

	public boolean clickOnTemporaryButton() {
		return ActionHelper.Click(driver, temporary_button);
	}
	public boolean clickOnPermanentStatusUpdate() {
		return ActionHelper.Click(driver, permanent_update);
	}
	public boolean waitForProcessing() {
		return ActionHelper.waitforElementToBeDisappearedByRegularIntervals(driver, processing, 5,20);
	}
	public boolean isStatusUpdatedMessageVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, status_updated_text, 30);
	}

	public boolean clickOnAssignButton() {
		boolean isClicked = false;
		if(ActionHelper.waitForElementToBeVisible(driver, assign_button, 30)) {
			isClicked = ActionHelper.Click(driver, assign_button);
		}
		return isClicked;
	}
	public boolean isUnenrollCampaignAlert() {
		return ActionHelper.waitForElementToBeVisible(driver, unenroll_from_campaign, 15);
	}
	public boolean clickOnUnEnrollButton() {
		boolean isClicked = false;
		if(ActionHelper.waitForElementToBeVisible(driver, Unenroll_button, 30)) {
			isClicked = ActionHelper.Click(driver, Unenroll_button) && clickOnOkButton();
		}
		return isClicked;
	}
	public boolean clickOnOverrideButton() {
		boolean isClicked = false;
		if(ActionHelper.waitForElementToBeVisible(driver, override_button, 30)) {
			isClicked = ActionHelper.Click(driver, override_button);
		}
		return isClicked;
	}
	public boolean waitForOverrideButton() {
		return ActionHelper.waitForElementToBeVisible(driver, override_button, 30);
	}
	public String getOverrideModalText() {
		return ActionHelper.getText(driver, override_modal_text);
	}
}
