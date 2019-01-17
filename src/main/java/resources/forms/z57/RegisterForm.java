/**
 * 
 */
package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.forms.AbstractForm;

/**
 * @author adar
 *
 */
public class RegisterForm extends AbstractForm{

	WebDriverWait wait;

	@FindBy(xpath="//div[@class='login-links']/a[@id='widget_login_topbar']")
	WebElement alreadyregistered_link;

	public RegisterForm() {
		
	}
	
	public RegisterForm(WebDriver webDriver) {
		driver=webDriver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(driver, this);
		
	}

	public boolean clickOnAlreadyRegistered() {
		wait.until(ExpectedConditions.elementToBeClickable(alreadyregistered_link));
		if(alreadyregistered_link.isDisplayed()) {
			alreadyregistered_link.click();
			return true;
		}else {
			return false;
		}

	}
}
