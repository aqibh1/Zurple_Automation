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
public class LoginForm extends AbstractForm{

	WebDriverWait wait;
	//Email Login xpath 
	@FindBy(xpath="./descendant::*[@id=\"login_user_topbar\"]")
	WebElement email_input;
	
	//Login Xpath button
	@FindBy(xpath="./descendant::*[@id=\"login_user_topbar_button\"]")
	WebElement login_button;
	
	//Signin Xpath button
	@FindBy(xpath="//div[@class='user_menu']/a[text()='Sign In']")
	WebElement signIn_button;
	
	
	public LoginForm() {
		
	}
	public LoginForm(WebDriver webDriver) {
		driver=webDriver;
		wait = new WebDriverWait(driver, 10);
		PageFactory.initElements(webDriver, this);
	}

	public boolean setEmail(String pEmail) {
		try {
			wait.until(ExpectedConditions.visibilityOf(email_input));
			email_input.sendKeys(pEmail);
			return true;
		}catch(Exception ex) {
			System.out.println(ex.toString());
			return false;
		}
		
	}
	public boolean clickLoginButton() {
		wait.until(ExpectedConditions.visibilityOf(login_button));
		wait.until(ExpectedConditions.elementToBeClickable(login_button));
		if(login_button.isDisplayed()) {
			login_button.click();
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isLoginSuccessful() {
		return wait.until(ExpectedConditions.invisibilityOf(login_button));
	}
	
	public boolean clickOnSignInButton() {
		wait.until(ExpectedConditions.visibilityOf(signIn_button));
		wait.until(ExpectedConditions.elementToBeClickable(signIn_button));
		if(signIn_button.isDisplayed()) {
			signIn_button.click();
			return true;
		}else {
			return false;
		}
	}   

}
