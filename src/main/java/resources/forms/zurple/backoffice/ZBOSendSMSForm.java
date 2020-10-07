/**
 * 
 */
package resources.forms.zurple.backoffice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOSendSMSForm extends AbstractForm{
	
	@FindBy(xpath="//h4[text()='Send Text Message']")
	WebElement send_text_heading;
	
	@FindBy(id="tonumber")
	WebElement number_input;
	
	@FindBy(id="send_button")
	WebElement send_button;
	
	@FindBy(id="text-send-alert")
	WebElement success_message;
	
	public ZBOSendSMSForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isSendTextMessageForm() {
		return ActionHelper.waitForElementToBeVisible(driver, send_text_heading, 30);
	}
	public boolean getPhoneNumber() {
		return !ActionHelper.getTextByValue(driver, driver.findElement(By.id("tonumber"))).isEmpty();
	}
	public boolean clickOnSendButton() {
		return ActionHelper.Click(driver, send_button);
	}
	public boolean isSuccessMessageVisible() {
		boolean isSuccessful = false;
		if(ActionHelper.waitForElementToBeVisible(driver, success_message, 30)) {
			isSuccessful = ActionHelper.getText(driver, success_message).isEmpty();
		}
		return !isSuccessful;
	}
}
