/**
 * 
 */
package resources.alerts.zurple.backoffice;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
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
public class ZBOSelectListingAlert extends AbstractAlert{
	@FindBy(xpath="//button[text()='OK']")
	WebElement ok_button;
	
	@FindBy(xpath="//h2[text()='Select Listing']")
	WebElement select_listing_headings;
	
	@FindBy(xpath="//select[@id='property_to_post']")
	WebElement select_listing_dropdown;
	
	String listing_dropdown_options = "//select[@id='property_to_post']/option";
	
	public ZBOSelectListingAlert() {
		
	}
	public ZBOSelectListingAlert(WebDriver pWebDriver) {
		driver = pWebDriver;
		PageFactory.initElements(driver, this);
	}
	public boolean isSelectListingAlert() {
		return ActionHelper.waitForElementToBeVisible(driver, select_listing_headings, 30);
	}
	public boolean selectTheListingFromDropdown() {
		boolean isPropertySelected = false;
		List<WebElement> list_of_element = new ArrayList<WebElement>();
		if(ActionHelper.Click(driver, select_listing_dropdown)) {
			list_of_element =ActionHelper.getListOfElementByXpath(driver, listing_dropdown_options);
			int lListingIndex = generateRandomNumber(list_of_element.size());
			isPropertySelected = ActionHelper.Click(driver, list_of_element.get(lListingIndex));
			ActionHelper.Click(driver, select_listing_dropdown);
		}
		return isPropertySelected;
	}
	public boolean clickOnOkButton() {
		return ActionHelper.Click(driver, ok_button);
	}
	private int generateRandomNumber(int pUpperRange){
    	Random random = new Random();
    	int lNum = random.nextInt(pUpperRange);
    	if(lNum==pUpperRange) {
    		lNum = lNum - 1;
    	}
    	return lNum;
	}
}