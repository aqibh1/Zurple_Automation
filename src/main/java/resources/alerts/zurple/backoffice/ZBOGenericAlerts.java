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
public class ZBOGenericAlerts extends AbstractAlert{
	
	@FindBy(xpath="//div[@class='modal-body']/p")
	WebElement alert_message;
	@FindBy(xpath="//button[@class='btn z57-theme-btn-cancel']")
	WebElement close_button;
	
	public ZBOGenericAlerts(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public String getErrorMessage() {
		return ActionHelper.getText(driver, alert_message);
	}
	public boolean clickOnCloseButton() {
		return ActionHelper.Click(driver, close_button);
	}
}
