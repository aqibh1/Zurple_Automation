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
	
	public ZBOSucessAlert() {
		
	}
	public ZBOSucessAlert(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isSuccessMessageVisible() {
		return ActionHelper.isElementVisible(driver, success_text);
	}
	
	public boolean clickOnOkButton() {
		return ActionHelper.Click(driver, ok_button);
	}

}
