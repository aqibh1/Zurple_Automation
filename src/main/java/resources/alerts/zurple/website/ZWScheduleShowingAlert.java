/**
 * 
 */
package resources.alerts.zurple.website;

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
public class ZWScheduleShowingAlert extends AbstractAlert{
	
	@FindBy(xpath="//div[@class='sweet-alert showSweetAlert visible']/descendant::div[@class='sa-icon sa-info']")
	WebElement lScheduleAlert;
	
	@FindBy(xpath="//div[@class='sweet-alert showSweetAlert visible']/descendant::button[@class='confirm']")
	WebElement yes_button;
	
	@FindBy(xpath="//div[@class='sweet-alert showSweetAlert visible']/descendant::h2[text()='Success!']")
	WebElement success_message;
	
	public ZWScheduleShowingAlert() {
		
	}
	
	public ZWScheduleShowingAlert(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isShceduleAlertShowing() {
		return ActionHelper.waitForElementToBeVisible(driver, lScheduleAlert, 30);
	}
	
	public boolean clickOnYesButton() {
		return ActionHelper.Click(driver, yes_button);
	}
	public boolean isScheduleAlertDisappeared() {
		return ActionHelper.waitForElementToBeDisappeared(driver, lScheduleAlert, 30);
	}
	public boolean isSuccessDisplayed() {
		return ActionHelper.waitForElementToBeVisible(driver, success_message, 15);
	}
}
