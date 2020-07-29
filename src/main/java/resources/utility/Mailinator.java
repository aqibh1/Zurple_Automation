/**
 * 
 */
package resources.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.ConfigReader;

/**
 * @author adar
 * This will verify the email in mailinator inbox
 *
 */
public class Mailinator {

	private WebDriver driver;
	
	@FindBy(id="inbox_field")
	WebElement input_field;
	
	@FindBy(xpath="//a[contains(text(), 'Task Reminder -')]")
	WebElement email_subject_xpath;
	
	String dynamic_xpath = "//a[contains(text(), '"+FrameworkConstants.DYNAMIC_VARIABLE+"')]";
	
	public WebDriver getDriver() {
		return driver;
	}


	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}


	public Mailinator(WebDriver pWebDriver) {
		setDriver(pWebDriver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyEmail(String pMailinatorInbox, String pEmailSubject, int pAttempts) {
		boolean isEmailVerified = false;
		String lCurrentUrl = driver.getCurrentUrl();
		String lMailinatorUrl = "https://www.mailinator.com/v3/index.jsp?zone=public&query="+pMailinatorInbox+"#/#inboxpane";
		driver.navigate().to(lMailinatorUrl);
		if(ActionHelper.waitForElementToBeVisible(driver, input_field, 30)) {
//			isEmailVerified = ActionHelper.waitForElementToVisibleAfterRegularIntervals(driver, email_subject_xpath, 60, pAttempts);
			isEmailVerified = ActionHelper.getDynamicElementAfterRegularIntervals(driver, dynamic_xpath, pEmailSubject, 20);

		}
		driver.navigate().to(lCurrentUrl);
		return isEmailVerified;
	}
	
	public void activateStagingInbox() {
		String lCurrentUrl = driver.getCurrentUrl();
		String lMailinatorUrl = "";
		String lAdmin_email = "";
		if(System.getProperty("project").equalsIgnoreCase("zurple")) {
			lAdmin_email = ConfigReader.load().getPropertyByName("zurple_bo_user").split("@")[0];
			lMailinatorUrl = "https://www.mailinator.com/v3/index.jsp?zone=public&query="+lAdmin_email+"#/#inboxpane";
		}else {
			lAdmin_email = ConfigReader.load().getPropertyByName("torchx_bo_user").split("@")[0];
			lMailinatorUrl = "https://www.mailinator.com/v3/index.jsp?zone=public&query="+lAdmin_email+"#/#inboxpane";
		}
		driver.navigate().to(lMailinatorUrl);
		ActionHelper.staticWait(10);
		driver.navigate().to(lCurrentUrl);
	}
	public void activateProductionInbox() {
		
		String lCurrentUrl = driver.getCurrentUrl();
		String lMailinatorUrl = "";
		String lAdmin_email = "";
		if(System.getProperty("project").equalsIgnoreCase("zurple")) {
			lAdmin_email = ConfigReader.load().getPropertyByName("zurple_bo_user").split("@")[0];
			lMailinatorUrl = "https://www.mailinator.com/v3/index.jsp?zone=public&query="+lAdmin_email+"#/#inboxpane";
		}else {
			lAdmin_email = ConfigReader.load().getPropertyByName("torchx_bo_user").split("@")[0];
			lMailinatorUrl = "https://www.mailinator.com/v3/index.jsp?zone=public&query="+lAdmin_email+"#/#inboxpane";
		}
		driver.navigate().to(lMailinatorUrl);
		ActionHelper.staticWait(10);
		driver.navigate().to(lCurrentUrl);
	}
}
