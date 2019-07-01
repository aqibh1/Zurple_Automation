/**
 * 
 */
package resources.alerts.pp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.alerts.AbstractAlert;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class AdCreatedSuccessAlert extends AbstractAlert{
	
	@FindBy(xpath="//h3[text()='Success - Ad Created']")
	WebElement adCreated_heading;
	String adCreated_heading_xpath="//h3[text()='Success - Ad Created']";
	
	@FindBy(xpath="//a[text()='Go to Ads Overview']")
	WebElement adsOverview_button;
	
	AdCreatedSuccessAlert(){
		
	}
	public AdCreatedSuccessAlert(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);
	}
	
	public boolean isAdCreatedAlert() {
		return actionHelper.waitForElementToBeLocated(adCreated_heading_xpath, 30);
	}
	
	public boolean clickOnAdsOverviewButton() {
		return new ActionHelper(driver).Click(adsOverview_button);
	}

}
