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
	
	private ActionHelper actionHelper;
	
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
		actionHelper = new ActionHelper(driver);
		PageFactory.initElements(driver, this);
	}
	public boolean isChooseAListingForm() {
		return actionHelper.waitForElementToBeVisible(chooseAListingHeading, 20);
	}
	public boolean selectListing(String pListing) {
		return selectDropDownOption(selectListing, pListing);
	}
	public boolean clickOnSelect() {
		return actionHelper.Click(select_button);
	}
	public boolean isSelectButtonDisappeared() {
		return actionHelper.waitForElementToBeDisappeared(select_button);
	}
	
	 private boolean selectDropDownOption(WebElement pElementToBeClicked, String pOptionToSelect) {
			boolean isSuccessful=false;
			List<WebElement> list_of_options = new ArrayList<WebElement>();
			 AutomationLogger.info("Clicking on button "+pElementToBeClicked);
			 if(actionHelper.Click(pElementToBeClicked)) {
				 list_of_options = pElementToBeClicked.findElements(By.tagName("option"));
				 for(WebElement element: list_of_options) {
					 System.out.println(element.getText().trim());
					 if(element.getText().trim().contains(pOptionToSelect)) {
						 isSuccessful = actionHelper.Click(element);
						 actionHelper.Click(pElementToBeClicked);
						 break;
					 }
				 }
			 }
			return isSuccessful;
		}
}
