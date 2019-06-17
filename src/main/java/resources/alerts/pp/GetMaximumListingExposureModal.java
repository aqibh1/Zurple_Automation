package resources.alerts.pp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.utility.ActionHelper;

public class GetMaximumListingExposureModal {
	WebDriver driver;
	String alertHeadingXpath = "//div[@id='flp_confirm_modal']/descendant::h3[text()='Get Maximum Listing Exposure']";
	
	@FindBy(xpath="//div[@id='flp_confirm_modal']/descendant::button[@class='close']")
	WebElement close_button;
	
	@FindBy(xpath="//div[@id='flp_listings_container']/descendant::a")
	WebElement featureListing_button;
	
	public GetMaximumListingExposureModal() {
		// TODO Auto-generated constructor stub
	}
	public GetMaximumListingExposureModal(WebDriver pWebDriver){
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean isGextMaximumListingExposureAlert() {
		return new ActionHelper(driver).waitForElementToBeLocated(alertHeadingXpath, 60);
	}
	
	public boolean clickOnFeatureListing() {
		return new ActionHelper(driver).Click(featureListing_button);
	}
	
	public boolean closeAlert() {
		boolean close=false;
		if(isGextMaximumListingExposureAlert()) {
			close = new ActionHelper(driver).Click(close_button);
		}
		return close;
	}
	
	

}
