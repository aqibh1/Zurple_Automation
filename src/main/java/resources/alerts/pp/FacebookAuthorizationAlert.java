/**
 * 
 */
package resources.alerts.pp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.alerts.AbstractAlert;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class FacebookAuthorizationAlert extends AbstractAlert{
	
	@FindBy(xpath="//div[contains(text(),'Continue as')]")
	WebElement continueAs_fbButton;
	
	@FindBy(xpath="//div[text()='Next']")
	WebElement next_fbButton;
	
	@FindBy(xpath="//div[text()='Done']")
	WebElement done_fbButton;
	
	@FindBy(xpath="//div[text()='OK']")
	WebElement ok_fbButton;
	
	public FacebookAuthorizationAlert() {
		// TODO Auto-generated constructor stub
	}
	public FacebookAuthorizationAlert(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	private boolean isFacebookAuthorizationAlert() {
		return new ActionHelper(driver).waitForElementToBeVisible(continueAs_fbButton, 15);
	}
	
	public void authorizeFacebook() {
		if(isFacebookAuthorizationAlert()) {
			new ActionHelper(driver).Click(continueAs_fbButton);
			new ActionHelper(driver).Click(next_fbButton);
			new ActionHelper(driver).Click( done_fbButton);
			new ActionHelper(driver).Click(ok_fbButton);
		}
	}
	

}
