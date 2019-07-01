/**
 * 
 */
package resources.alerts.pp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.alerts.AbstractAlert;

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
		setPageObject(pWebDriver, this);
	}
	
	private boolean isFacebookAuthorizationAlert() {
		return actionHelper.waitForElementToBeVisible(continueAs_fbButton, 15);
	}
	
	public void authorizeFacebook() {
		if(isFacebookAuthorizationAlert()) {
			actionHelper.Click(continueAs_fbButton);
			actionHelper.Click(next_fbButton);
			actionHelper.Click( done_fbButton);
			actionHelper.Click(ok_fbButton);
		}
	}
	

}
