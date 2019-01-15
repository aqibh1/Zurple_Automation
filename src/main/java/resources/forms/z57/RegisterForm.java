/**
 * 
 */
package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;

/**
 * @author adar
 *
 */
public class RegisterForm extends AbstractForm{

	
	WebDriver localWebDriver; 

	@FindBy(xpath="//div[@class='login-links']/a[@id='widget_login_topbar']")
	WebElement alreadyregistered_link;

	public RegisterForm() {
		
	}
	
	public RegisterForm(WebDriver webDriver) {
		PageFactory.initElements(webDriver, this);
		localWebDriver=webDriver;
	}

	public boolean clickOnAlreadyRegistered() {
		if(alreadyregistered_link.isDisplayed()) {
			alreadyregistered_link.click();
			return true;
		}else {
			return false;
		}

	}
}
