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
public class AdCreatedSuccessAlert {
	WebDriver driver;
	
	@FindBy(xpath="//h3[text()='Success - Ad Created']")
	WebElement adCreated_heading;
	String adCreated_heading_xpath="//h3[text()='Success - Ad Created']";
	
	@FindBy(xpath="//a[text()='Go to Ads Overview']")
	WebElement adsOverview_button;
	
	AdCreatedSuccessAlert(){
		
	}
	public AdCreatedSuccessAlert(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isAdCreatedAlert() {
		return ActionHelper.waitForElementToBeLocated(driver, adCreated_heading_xpath, 30);
	}
	
	public boolean clickOnAdsOverviewButton() {
		return ActionHelper.Click(driver, adsOverview_button);
	}

}
