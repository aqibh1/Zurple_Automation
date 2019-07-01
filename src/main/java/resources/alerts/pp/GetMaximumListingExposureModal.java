package resources.alerts.pp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.alerts.AbstractAlert;

public class GetMaximumListingExposureModal extends AbstractAlert{

	String alertHeadingXpath = "//div[@id='flp_confirm_modal']/descendant::h3[text()='Get Maximum Listing Exposure']";
	
	@FindBy(xpath="//div[@id='flp_confirm_modal']/descendant::button[@class='close']")
	WebElement close_button;
	
	@FindBy(xpath="//div[@id='flp_listings_container']/descendant::a")
	WebElement featureListing_button;
	
	public GetMaximumListingExposureModal() {
		// TODO Auto-generated constructor stub
	}
	public GetMaximumListingExposureModal(WebDriver pWebDriver){
		setPageObject(pWebDriver, this);
	}
	
	public boolean isGextMaximumListingExposureAlert() {
		return actionHelper.waitForElementToBeLocated(alertHeadingXpath, 60);
	}
	
	public boolean clickOnFeatureListing() {
		return actionHelper.Click(featureListing_button);
	}
	
	public boolean closeAlert() {
		boolean close=false;
		if(isGextMaximumListingExposureAlert()) {
			close = actionHelper.Click(close_button);
		}
		return close;
	}
	
	

}
