package resources.alerts.pp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

public class DeleteListingAlert {
	WebDriver driver;
	@FindBy(xpath="//div[@class='modal-header']/descendant::h3[text()='Delete a Listing(s)']")
	WebElement deleteListing_header;
	
	@FindBy(id="confirm_delete_listing_button")
	WebElement confirm_button;
	
	public DeleteListingAlert() {
		// TODO Auto-generated constructor stub
	}
	public DeleteListingAlert(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isDeleteListingAlert() {
		return new ActionHelper(driver).waitForElementToBeVisible(deleteListing_header, 30);
	}
	public boolean clickOnConfirmButton() {
		return new ActionHelper(driver).Click(confirm_button);
	}
	public boolean isDeleted() {
		return new ActionHelper(driver).waitForElementToBeDisappeared(deleteListing_header);
	}
}
