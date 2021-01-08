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
		return ActionHelper.waitForElementToBeVisible(driver, continueAs_fbButton, 15);
	}
	
	public void authorizeFacebook() {
		if(isFacebookAuthorizationAlert()) {
			ActionHelper.Click(driver, continueAs_fbButton);
			ActionHelper.Click(driver, next_fbButton);
			ActionHelper.Click(driver, done_fbButton);
			ActionHelper.Click(driver, ok_fbButton);
		}
	}
	

}
