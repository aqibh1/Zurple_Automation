/**
 * 
 */
package resources.alerts.pp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ConfirmNewDefaultFacebookPageAlert {
	private WebDriver driver;
	@FindBy(xpath="//div[@class='modal-header']/h3[text()='Confirm New Default Facebook Page']")
	WebElement confirm_def_fb_page_heading;
	
	@FindBy(id="confirm_fb_page_change_confirm")
	WebElement confirm_button;
	
	public ConfirmNewDefaultFacebookPageAlert() {
		// TODO Auto-generated constructor stub
	}
	public ConfirmNewDefaultFacebookPageAlert(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isConfirmFbPageAlert() {
		return ActionHelper.waitForElementToBeVisible(driver, confirm_def_fb_page_heading, 10);
	}
	public boolean clickOnConfirmButton() {
		return ActionHelper.Click(driver, confirm_button);
	}

}
