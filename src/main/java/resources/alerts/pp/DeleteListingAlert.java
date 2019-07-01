package resources.alerts.pp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.alerts.AbstractAlert;

public class DeleteListingAlert extends AbstractAlert{

	@FindBy(xpath="//div[@class='modal-header']/descendant::h3[text()='Delete a Listing(s)']")
	WebElement deleteListing_header;
	
	@FindBy(id="confirm_delete_listing_button")
	WebElement confirm_button;
	
	public DeleteListingAlert() {
		// TODO Auto-generated constructor stub
	}
	public DeleteListingAlert(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);
	}
	
	public boolean isDeleteListingAlert() {
		return actionHelper.waitForElementToBeVisible(deleteListing_header, 30);
	}
	public boolean clickOnConfirmButton() {
		return actionHelper.Click(confirm_button);
	}
	public boolean isDeleted() {
		return actionHelper.waitForElementToBeDisappeared(deleteListing_header);
	}
}
