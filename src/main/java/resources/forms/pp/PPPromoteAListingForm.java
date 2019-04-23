package resources.forms.pp;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

public class PPPromoteAListingForm extends AbstractForm{
	
	@FindBy(xpath="//div[@class='modal-header']/h3[text()='Choose a Listing to Promote']")
	WebElement chooseAListingHeading;
	
	@FindBy(id="all_listings_select")
	WebElement selectListing;
	
	@FindBy(xpath="//div[@id='promote_listing_modal']/descendant::button[text()='Select']")
	WebElement select_button;
	
	PPPromoteAListingForm(){
		
	}
	public PPPromoteAListingForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isChooseAListingForm() {
		return ActionHelper.waitForElementToBeVisible(driver, chooseAListingHeading, 20);
	}
	public boolean selectListing(String pListing) {
		return selectDropDownOption(driver, selectListing, pListing);
	}
	public boolean clickOnSelect() {
		return ActionHelper.Click(driver, select_button);
	}
	public boolean isSelectButtonDisappeared() {
		return ActionHelper.waitForElementToBeDisappeared(driver, select_button);
	}
	
	 private static boolean selectDropDownOption(WebDriver pWebDriver, WebElement pElementToBeClicked, String pOptionToSelect) {
			boolean isSuccessful=false;
			List<WebElement> list_of_options = new ArrayList<WebElement>();
			 AutomationLogger.info("Clicking on button "+pElementToBeClicked);
			 if(ActionHelper.Click(pWebDriver, pElementToBeClicked)) {
				 list_of_options = pElementToBeClicked.findElements(By.tagName("option"));
				 for(WebElement element: list_of_options) {
					 System.out.println(element.getText().trim());
					 if(element.getText().trim().contains(pOptionToSelect)) {
						 isSuccessful = ActionHelper.Click(pWebDriver, element);
						 ActionHelper.Click(pWebDriver,pElementToBeClicked);
						 break;
					 }
				 }
			 }
			return isSuccessful;
		}
}
