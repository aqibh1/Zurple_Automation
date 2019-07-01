/**
 * 
 */
package resources.forms.pp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import resources.forms.AbstractForm;

/**
 * @author adar
 *
 */
public class PPAddListingForm extends AbstractForm{
	
	@FindBy(xpath="//input[@id='address_input']")
	WebElement address_input;

	@FindBy(xpath="//input[@id='city_input']")
	WebElement city_input;
	
	@FindBy(xpath="//input[@id='zip_input']")
	WebElement zip_input;
	
	@FindBy(xpath="//input[@id='county']")
	WebElement county_input;
	
	@FindBy(xpath="//select[@id='status_select']")
	WebElement status_dropdown;
	
	String status_options ="//select[@id='status_select']/option";
	
	@FindBy(xpath="//select[@id='state_select']")
	WebElement state_dropdown;
	
	String state_options ="//select[@id='state_select']/option";
	
	@FindBy(xpath="//button[@id='add_new_listing_confirm_button']")
	WebElement continue_button;
	
	public PPAddListingForm() {
		// TODO Auto-generated constructor stub
	}
	public PPAddListingForm(WebDriver pWebDriver) {
		setPageObject(pWebDriver, this);	
	}
	
	public boolean typeAddress(String pAddress) {
		return actionHelper.ClearAndType(address_input, pAddress);
	}
	public boolean typeCity(String pCity) {
		return actionHelper.ClearAndType(city_input, pCity);
	}
	public boolean typeZip(String pZip) {
		return actionHelper.ClearAndType(zip_input, pZip);
	}
	public boolean typeCounty(String pCounty) {
		return actionHelper.ClearAndType(county_input, pCounty);
	}
	public boolean selectStatus(String pStatus) {
		return actionHelper.selectDropDownOption(status_dropdown, status_options, pStatus);	
	}
	public boolean selectState(String pState) {
		return actionHelper.selectDropDownOption(state_dropdown, state_options, pState);
	}
	public boolean clickOnContinueButton() {
		return actionHelper.Click(continue_button);
	}
}
