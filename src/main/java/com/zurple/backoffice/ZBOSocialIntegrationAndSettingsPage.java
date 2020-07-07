/**
 * 
 */
package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.zurple.my.Page;

import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOSocialIntegrationAndSettingsPage extends Page{

	@FindBy(xpath="//h3[text()='Integrations & Settings']")
	WebElement integrationAndSettings_heading;

	@FindBy(xpath="//div[@class='row row-center']/descendant::img[@src='/img/zurple/Facebook_connected_200.png']")
	WebElement facebookConnected_image;

	@FindBy(xpath="//button[@data-social-network='facebook' and text()='Disconnect']")
	WebElement facebookDisConnect_button;

	@FindBy(xpath="//button[text()='OK']")
	WebElement successOk_button;

	@FindBy(xpath="//button[@data-social-network='facebook' and text()='Connect']")
	WebElement facebookConnect_button;

	//Facebook Elements
	@FindBy(id="email")
	WebElement email;

	@FindBy(id="pass")
	WebElement password;

	@FindBy(id="loginbutton")
	WebElement loginButton;

	@FindBy(xpath="//span[contains(text(),'Continue as') and @dir='auto']")
	WebElement continueAsButton;

	@FindBy(xpath="//div[@class='row row-center']/descendant::img[@src='/img/zurple/Twitter_connected_200.png']")
	WebElement twitterConnected_image;
	@FindBy(xpath="//button[@data-social-network='twitter' and text()='Disconnect']")
	WebElement twitterDisConnect_button;
	@FindBy(xpath="//button[@data-social-network='twitter' and text()='Connect']")
	WebElement twitterConnect_button;
	//Twitter login xpaths
	@FindBy(id="username_or_email")
	WebElement twitter_username_input;
	@FindBy(id="password")
	WebElement twitter_password_input;
	@FindBy(id="allow")
	WebElement twitter_signin_button;

	//Linkedin xpaths
	@FindBy(id="username")
	WebElement linkedin_username;
	@FindBy(id="password")
	WebElement linkedin_password;
	@FindBy(xpath="//button[@aria-label='Sign in']")
	WebElement linkedin_signin_button;
	@FindBy(id="oauth__auth-form__submit-btn")
	WebElement linkedin_allow_button;
	@FindBy(xpath="//div[@class='row row-center']/descendant::img[@src='/img/zurple/Linkedin_connected_200.png']")
	WebElement linkedinConnected_image;
	@FindBy(xpath="//button[@data-social-network='linkedin' and text()='Disconnect']")
	WebElement linkedinDisConnect_button;
	@FindBy(xpath="//button[@data-social-network='linkedin' and text()='Connect']")
	WebElement linkedinConnect_button;

	//Linkedin xpaths
	@FindBy(id="identifierId")
	WebElement youtube_username;
	@FindBy(xpath="//input[@name='password']")
	WebElement youtube_password;
	@FindBy(xpath="//button[@aria-label='Sign in']")
	WebElement youtube_signin_button;
	@FindBy(id="oauth__auth-form__submit-btn")
	WebElement youtube_allow_button;
	@FindBy(xpath="//div[@class='row row-center']/descendant::img[@src='/img/zurple/Youtube_connected_200.png']")
	WebElement youtubeConnected_image;
	@FindBy(xpath="//button[@data-social-network='youtube' and text()='Disconnect']")
	WebElement youtubeDisConnect_button;
	@FindBy(xpath="//button[@data-social-network='youtube' and text()='Connect']")
	WebElement youtubeConnect_button;
	@FindBy(xpath="//div[@id='identifierNext']/descendant::span[text()='Next']")
	WebElement youtubeNext_button;
	@FindBy(xpath="//div[@id='passwordNext']/descendant::span[text()='Next']")
	WebElement youtubePasswordNext_button;
	@FindBy(xpath="//div[@id='oauthScopeDialog']/descendant::span[text()='Allow'][2]")
	WebElement youtube_grantpermission_allow_button;
	@FindBy(xpath="//div[@id='submit_approve_access']/descendant::span[text()='Allow']")
	WebElement youtube_confirmyourchoice_allow_button;
	@FindBy(xpath="//a[text()='Advanced']")
	WebElement advanced_button;
	@FindBy(xpath="//a[text()='Go to thesocialapi.com (unsafe)']")
	WebElement gotoSocialApi_button;
	
	@FindBy(xpath="//input[@id='allAssetsInput']")
	WebElement check_box_All_fb;
	@FindBy(xpath="//div[@role='heading' and contains(text(),'What Pages')]")
	WebElement heading_fb;
	
	@FindBy(xpath="//span[text()='Next']")
	WebElement next_button;
	@FindBy(xpath="//span[text()='OK']")
	WebElement ok_button;
	
	@FindBy(xpath="//span[text()='Done']")
	WebElement done_button;
	
	@FindBy(id="default_fb_page")
	WebElement default_fb_page;


	public ZBOSocialIntegrationAndSettingsPage() {

	}
	public ZBOSocialIntegrationAndSettingsPage(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isSocialIntegrationPage() {
		return ActionHelper.isElementVisible(driver, integrationAndSettings_heading);
	}

	public boolean connectToLinkedIn(boolean pForceConnection) {
		boolean isConnected = false;
		if(ActionHelper.isElementVisible(driver, linkedinConnected_image)) {
			if(pForceConnection) {
				disconnectLinkedin();
				isConnected = connectToLinkedin();
			}else {
				AutomationLogger.info("Twitter is already connected..");
			}
		}else {
			isConnected = connectToLinkedin();
		}
		return isConnected;
	}

	public boolean connectToTwitter(boolean pForceConnection) {
		boolean isConnected = false;
		if(ActionHelper.isElementVisible(driver, twitterConnected_image)) {
			if(pForceConnection) {
				disconnectTwitter();
				isConnected = connectToTwitter();
			}else {
				AutomationLogger.info("Twitter is already connected..");
			}
		}else {
			isConnected = connectToTwitter();
		}
		return isConnected;
	}
	public boolean connectToFacebook(boolean pForceConnection) {
		boolean isConnected = false;
		if(ActionHelper.isElementVisible(driver, facebookConnected_image)) {
			if(pForceConnection) {
				disconnectFacebook();
				isConnected = connectToFacebook();
			}else {
				AutomationLogger.info("Facebook is already connected..");
			}
		}else {
			isConnected = connectToFacebook();
		}
		return isConnected;
	}

	public boolean disconnectLinkedin() {
		boolean isLinkedinDisconnected = false;
		if(ActionHelper.isElementVisible(driver, linkedinConnected_image)) {
			ActionHelper.Click(driver, linkedinDisConnect_button);
			if(ActionHelper.waitForElementToBeVisible(driver, successOk_button, 30)) {
				isLinkedinDisconnected = ActionHelper.Click(driver, successOk_button);
			}
		}else {
			AutomationLogger.info("Linkedin is already disconnected..");
			isLinkedinDisconnected = true;
		}
		return isLinkedinDisconnected;
	}

	public boolean disconnectFacebook() {
		boolean isFacebookDisconnected = false;
		if(ActionHelper.isElementVisible(driver, facebookConnected_image)) {
			ActionHelper.Click(driver, facebookDisConnect_button);
			if(ActionHelper.waitForElementToBeVisible(driver, successOk_button, 30)) {
				isFacebookDisconnected = ActionHelper.Click(driver, successOk_button);
			}
		}else {
			AutomationLogger.info("Facebook is already disconnected..");
			isFacebookDisconnected = true;
		}
		return isFacebookDisconnected;
	}

	public boolean disconnectTwitter() {
		boolean isTwitterDisconnected = false;
		if(ActionHelper.isElementVisible(driver, twitterConnected_image)) {
			ActionHelper.Click(driver, twitterDisConnect_button);
			if(ActionHelper.waitForElementToBeVisible(driver, successOk_button, 30)) {
				isTwitterDisconnected = ActionHelper.Click(driver, successOk_button);
			}
		}else {
			AutomationLogger.info("Twitter is already disconnected..");
			isTwitterDisconnected = true;
		}
		return isTwitterDisconnected;
	}

	private boolean connectToTwitter() {
		boolean isConnected = false;
		if(ActionHelper.Click(driver, twitterConnect_button)) {
			isConnected = filloutTwitterForm();
		}
		return isConnected;
	}
	private boolean connectToFacebook() {
		boolean isConnected = false;
		if(ActionHelper.Click(driver, facebookConnect_button)) {
			isConnected = filloutFacebookForm();
		}
		return isConnected;
	}
	private boolean connectToFacebookSecond() {
		boolean isConnected = false;
		if(ActionHelper.Click(driver, facebookConnect_button)) {
			isConnected = filloutFacebookFormSecond();
		}
		return isConnected;
	}

	private boolean connectToLinkedin() {
		boolean isConnected = false;
		if(ActionHelper.Click(driver, linkedinConnect_button)) {
			isConnected = filloutLinkedinForm();
		}
		return isConnected;
	}
	private boolean filloutTwitterForm() {
		boolean isSuccess = true;
		String lUsername = "z57testuser@gmail.com";
		String lPassword = "Bcsf08m020@";
		if(ActionHelper.waitForElementToBeVisible(driver, twitter_signin_button, 30)) {
			if(!ActionHelper.ClearAndType(driver, twitter_username_input, lUsername)) {
				return false;
			}
			if(!ActionHelper.ClearAndType(driver, twitter_password_input, lPassword)) {
				return false;
			}
			if(!ActionHelper.Click(driver, twitter_signin_button)) {
				return false;
			}
			if(ActionHelper.waitForElementToBeVisible(driver, successOk_button,30)) {
				isSuccess = ActionHelper.Click(driver, successOk_button);
			}

		}else {
			return false;
		}
		return isSuccess;
	}
	private boolean filloutFacebookForm() {
		boolean isSuccess = true;
		String lUsername = "z57testuser@gmail.com";
		String lPassword = "Bcsf08m020@";
		if(ActionHelper.waitForElementToBeVisible(driver, loginButton, 30)) {
			if(!ActionHelper.ClearAndType(driver, email, lUsername)) {
				return false;
			}
			if(!ActionHelper.ClearAndType(driver, password, lPassword)) {
				return false;
			}
			if(!ActionHelper.Click(driver, loginButton)) {
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, continueAsButton, 30)) {
				return false;
			}
			if(!ActionHelper.Click(driver, continueAsButton)) {
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, heading_fb, 20)){
				return false;
			}
			if(!ActionHelper.ClickWithStaticWait(driver, check_box_All_fb)) {
				return false;
			}
			if(!ActionHelper.Click(driver, next_button)) {
				return false;
			}
			if(!ActionHelper.Click(driver, done_button)) {
				return false;
			}
			if(!ActionHelper.isElementVisible(driver, ok_button)) {
				return false;
			}
			if(!ActionHelper.Click(driver, ok_button)) {
				return false;
			}
			if(ActionHelper.waitForElementToBeVisible(driver, default_fb_page, 20)) {
				if(ActionHelper.selectDropDownOption(driver, default_fb_page, "", "Aqib Automated Testing")) {
					isSuccess = new ZBOSucessAlert(driver).clickOnOkButton();
				}
			}
		}else {
			return false;
		}
		return isSuccess;
	}
	private boolean filloutLinkedinForm() {
		boolean isSuccess = true;
		String lUsername = "z57testuser@gmail.com";
		String lPassword = "Bcsf08m020@";
		if(ActionHelper.waitForElementToBeVisible(driver, linkedin_signin_button, 30)) {
			if(!ActionHelper.ClearAndType(driver, linkedin_username, lUsername)) {
				return false;
			}
			if(!ActionHelper.ClearAndType(driver, linkedin_password, lPassword)) {
				return false;
			}
			if(!ActionHelper.Click(driver, linkedin_signin_button)) {
				return false;
			}
			if(ActionHelper.waitForElementToBeVisible(driver, linkedin_allow_button, 30)) {
				isSuccess = ActionHelper.Click(driver, linkedin_allow_button);
			}else {
				isSuccess = true;
			}

			if(isSuccess && ActionHelper.isElementVisible(driver, successOk_button)) {
				isSuccess = ActionHelper.Click(driver, successOk_button);
			}

		}else {
			return false;
		}
		return isSuccess;
	}
	
	public boolean connectToYoutube(boolean pForceConnection) {
		boolean isConnected = false;
		if(ActionHelper.isElementVisible(driver, youtubeConnected_image)) {
			if(pForceConnection) {
				disconnectYoutube();
				isConnected = connectToYoutube();
			}else {
				AutomationLogger.info("Youtube is already connected..");
			}
		}else {
			isConnected = connectToYoutube();
		}
		return isConnected;
	}

	public boolean disconnectYoutube() {
		boolean isYoutubeDisconnected = false;
		if(ActionHelper.isElementVisible(driver, youtubeConnected_image)) {
			ActionHelper.Click(driver, youtubeDisConnect_button);
			if(ActionHelper.waitForElementToBeVisible(driver, successOk_button, 30)) {
				isYoutubeDisconnected = ActionHelper.Click(driver, successOk_button);
			}
		}else {
			AutomationLogger.info("Youtube is already disconnected..");
			isYoutubeDisconnected = true;
		}
		return isYoutubeDisconnected;
	}
	
	private boolean connectToYoutube() {
		boolean isConnected = false;
		if(ActionHelper.Click(driver, youtubeConnect_button)) {
			isConnected = filloutYoutubeForm();
		}
		return isConnected;
	}
	
	private boolean filloutYoutubeForm() {
		boolean isSuccess = true;
		String lUsername = "z57testuser@gmail.com";
		String lPassword = "Bcsf08m020@";
		if(ActionHelper.waitForElementToBeVisible(driver, youtubeNext_button, 30)) {
			if(!ActionHelper.ClearAndType(driver, youtube_username, lUsername)) {
				return false;
			}
			if(!ActionHelper.Click(driver, youtubeNext_button)) {
				return false;
			}
			if(!ActionHelper.ClearAndType(driver, youtube_password, lPassword)) {
				return false;
			}
			if(!ActionHelper.Click(driver, youtubePasswordNext_button)) {
				return false;
			}
			if(ActionHelper.isElementVisible(driver, advanced_button)) {
				ActionHelper.Click(driver, advanced_button);
				ActionHelper.staticWait(2);
				ActionHelper.Click(driver, gotoSocialApi_button);
			}
			if(ActionHelper.waitForElementToBeVisible(driver, youtube_confirmyourchoice_allow_button, 30)) {
				isSuccess = ActionHelper.Click(driver, youtube_confirmyourchoice_allow_button);
			}else {
				isSuccess = true;
			}

			if(isSuccess && ActionHelper.isElementVisible(driver, successOk_button)) {
				isSuccess = ActionHelper.Click(driver, successOk_button);
			}

		}else {
			return false;
		}
		return isSuccess;
	}
	
	private boolean filloutFacebookFormSecond() {
		boolean isSuccess = true;
		String lUsername = "z57testuser@gmail.com";
		String lPassword = "Bcsf08m020@";
		if(ActionHelper.waitForElementToBeVisible(driver, loginButton, 30)) {
			if(!ActionHelper.ClearAndType(driver, email, lUsername)) {
				return false;
			}
			if(!ActionHelper.ClearAndType(driver, password, lPassword)) {
				return false;
			}
			if(!ActionHelper.Click(driver, loginButton)) {
				return false;
			}
			if(!ActionHelper.waitForElementToBeVisible(driver, continueAsButton, 30)) {
				return false;
			}else {
				isSuccess = ActionHelper.Click(driver, continueAsButton);
				if(isSuccess && ActionHelper.isElementVisible(driver, successOk_button)) {
					isSuccess = ActionHelper.Click(driver, successOk_button);
				}
			}
		}else {
			return false;
		}
		return isSuccess;
	}
}
