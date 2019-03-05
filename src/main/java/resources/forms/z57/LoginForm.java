/**
 * 
 */
package resources.forms.z57;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;

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
	@FindBy(xpath="//a[@id='login_with_fb']")
	WebElement signupFacebook_button;
	
	@FindBy(xpath="//input[@id='email']")
	WebElement FACEBOOK_EMAIL;
	
	@FindBy(xpath="//input[@id='pass']")
	WebElement FACEBOOK_PASSWORD;
	
	@FindBy(xpath="//input[@id='u_0_0' and @type='submit']")
	WebElement FACEBOOK_LOGIN;
	
	@FindBy(xpath="//div[@id='registerModal']/descendant::iframe[@class='login_with_fb_frame']")
	WebElement signupWithFb_frame;
	
	public LoginForm() {
		
	}
	public LoginForm(WebDriver webDriver) {
		driver=webDriver;
		wait = new WebDriverWait(driver, 20);
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
		boolean status = false;
		driver.switchTo().frame(signupWithFb_frame);
		if(ActionHelper.waitForElementToBeVisible(driver, signupFacebook_button,30)) {
			status = ActionHelper.Click(driver, signupFacebook_button);
		}
		return status;
	}
	private boolean isLoginSuccessful() {
		return wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loging_button_xpath)));
	}
	
	public boolean clickOnSignInButton() {
		return ActionHelper.Click(driver, signIn_button);
	}
	public boolean typeFacebookEmail(String pEmail) {
		ActionHelper.Click(driver, FACEBOOK_EMAIL);
		return ActionHelper.Type(driver, FACEBOOK_EMAIL, pEmail);
	}
	public boolean typeFacebookPassword(String pPassword) {
		ActionHelper.Click(driver, FACEBOOK_PASSWORD);
		return ActionHelper.Type(driver, FACEBOOK_PASSWORD, pPassword);
	}
	public boolean clickOnFacbookLoginButton() {
		return ActionHelper.Click(driver, FACEBOOK_LOGIN);
	}
	public boolean waitForLoginFormToDisappear() {
		return ActionHelper.waitForElementToBeDisappeared(driver, email_input);
	}
	
	public void switchToFrame() {
		
	}

}
