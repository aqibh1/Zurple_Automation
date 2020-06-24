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

}
