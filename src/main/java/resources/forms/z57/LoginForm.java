/**
 * 
 */
package resources.forms.z57;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.forms.AbstractForm;
import resources.utility.AutomationLogger;

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
	@FindBy(xpath="//div[@id='login-div_topbar']/descendant::button[@id='login_user_topbar_button']")
	WebElement login_button;
	String loging_button_xpath="//div[@id='login-div_topbar']/descendant::button[@id='login_user_topbar_button']";
	
	//Signin Xpath button
	@FindBy(xpath="//div[@class='user_menu']/a[text()='Sign In']")
	WebElement signIn_button;
	
	//Sign up with Facebook
	@FindBy(xpath="//a[@id='login_with_fb']/img")
	WebElement signupFacebook_button;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement FACEBOOK_EMAIL;
	
	@FindBy(xpath="//input[@id='pass']")
	WebElement FACEBOOK_PASSWORD;
	
	@FindBy(xpath="//input[@id='u_0_0' and @type='submit']")
	WebElement FACEBOOK_LOGIN;
	
	@FindBy(xpath="//button[@id='loginbutton']")
	WebElement FACEBOOK_LOGIN_BUTTON;
	
	@FindBy(xpath="//div[@id='registerModal']/descendant::iframe[@class='login_with_fb_frame']")
	WebElement signupWithFb_frame;
	
	@FindBy(xpath="//div[@id='login-register-buttons']/a[@id='register-modal-button']")
	WebElement signin_button_idx;
	
	@FindBy(xpath="//input[@id='idx_login_lead_email']")
	WebElement email_input_idx;
	
	@FindBy(xpath="//button[@id='lead_login_send']")
	WebElement login_button_idx;

	public LoginForm() {
		
	}
	public LoginForm(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);
		wait = new WebDriverWait (driver,20);
	
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
		boolean isSuccessful=false;
		int count=0;
		do {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(loging_button_xpath)));
			wait.until(ExpectedConditions.elementToBeClickable(login_button));
			if(login_button.isDisplayed()) {
				login_button.click();
				count++;
				isSuccessful =isLoginSuccessful();
			}else {
				isSuccessful = false;
			}
		}while(!isSuccessful && count<5);
		return isSuccessful;
	}
	public boolean clickOnSignUpWithFacebookButton() {
		AutomationLogger.info("Clicking on Signup with Facebook button in Register Form");
		boolean status = false;
		if(actionHelper.waitForElementToBeVisible( signupWithFb_frame, 30)) {
			driver.switchTo().frame(signupWithFb_frame);
		}
		if(actionHelper.waitForElementToBeVisible( signupFacebook_button,30)) {
			status = actionHelper.Click( signupFacebook_button);
		}
		return status;
	}
	private boolean isLoginSuccessful() {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loging_button_xpath)));
	}
	
	public boolean clickOnSignInButton() {
		AutomationLogger.info("Clicking on Sign In button");
		return actionHelper.Click( signIn_button);
	}
	public boolean typeFacebookEmail(String pEmail) {
		actionHelper.Click( FACEBOOK_EMAIL);
		return actionHelper.ClearAndType( FACEBOOK_EMAIL, pEmail);
	}
	public boolean typeFacebookPassword(String pPassword) {
		actionHelper.Click( FACEBOOK_PASSWORD);
		return actionHelper.ClearAndType( FACEBOOK_PASSWORD, pPassword);
	}
	public boolean clickOnFacebookLoginButton() {
		return actionHelper.Click( FACEBOOK_LOGIN);
	}
	public boolean waitForLoginFormToDisappear() {
		return actionHelper.waitForElementToBeDisappeared( email_input);
	}
	public boolean clickOnIdxSigninButton() {
		return actionHelper.Click( signin_button_idx);
	}
	
	public boolean typeEmailIdx(String pEmail) {
		return actionHelper.Type( email_input_idx, pEmail);
	}
	
	public boolean clickOnIdxLoginButton() {
		return actionHelper.Click( login_button_idx);
	}
	
	public boolean isIdxLoginSuccessful() {
		return actionHelper.waitForElementToBeDisappeared( login_button_idx);
	}
	
	public boolean isFacebookLoginForm() {
		return actionHelper.waitForElementToBeVisible( FACEBOOK_EMAIL, 15);
	}
	
	public boolean clickOnFacebookLoginButton2() {
		return actionHelper.Click( FACEBOOK_LOGIN_BUTTON);
	}
}
