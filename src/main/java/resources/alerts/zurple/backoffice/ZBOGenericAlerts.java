/**
 * 
 */
package resources.alerts.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.alerts.AbstractAlert;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOGenericAlerts extends AbstractAlert{
	
	@FindBy(xpath="//div[@class='modal-body']/p")
	WebElement alert_message;
	@FindBy(xpath="//button[@class='btn z57-theme-btn-cancel']")
	WebElement close_button;
	@FindBy(xpath="//h2[@id='swal2-title' and text()='You must select at least 1 Active Listings to be added to the Report']")
	WebElement active_listing_error; //Sold Properties
	@FindBy(xpath="//h2[@id='swal2-title' and text()='You must select at least 1  Sold Properties to be added to the Report']")
	WebElement sold_listing_error;
	@FindBy(xpath="//h2[@id='swal2-title' and text()='Only 3 Sold Properties can be added to the Report']")
	WebElement only_3_sold_listing_error;
	@FindBy(xpath="//h2[@id='swal2-title' and text()='Only 3 Active Listings can be added to the Report']")
	WebElement only_3_active_listing_error;
	@FindBy(xpath="//button[text()='OK']")
	WebElement ok_button;
	@FindBy(className="swal2-success-ring")
	WebElement success_ring;
	
	public ZBOGenericAlerts(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}

	public String getErrorMessage() {
		return ActionHelper.getText(driver, alert_message);
	}
	public boolean clickOnCloseButton() {
		return ActionHelper.Click(driver, close_button);
	}
	public boolean isActiveListingAlertVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, active_listing_error, 30);
	}
	public boolean isSoldListingAlertVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, sold_listing_error, 30);
	}
	public boolean isOnly3SoldListingAlertVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, only_3_sold_listing_error, 30);
	}
	public boolean isOnly3ActiveListingAlertVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, only_3_active_listing_error, 30);
	}
	public boolean clickOnOkButton() {
		return ActionHelper.Click(driver, ok_button);
	}
	public boolean waitForSuccessRingToBeVisible() {
		return ActionHelper.waitForElementToBeVisible(driver, success_ring, 20);
	}
}
