package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class OurCommunitySearchForm extends AbstractForm{

	@FindBy(xpath="//input[@id='community_address']")
	WebElement address_input;
	
	@FindBy(xpath="//input[@id='community_city']")
	WebElement city_input;
	
	@FindBy(xpath="//select[@id='community_state']")
	WebElement state_dropdown;
	
	@FindBy(xpath="//input[@id='community_zip']")
	WebElement zip_input;

	@FindBy(xpath="//button[@id='community_submit']")
	WebElement submit_button;
	
	String select_state_option="//select[@id='community_state']/option[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	
	public OurCommunitySearchForm(WebDriver pWebDriver) {
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeAddress(String pAddress) {
		return ActionHelper.Type(driver, address_input, pAddress);
	}
	
	public boolean typeCity(String pCity) {
		return ActionHelper.ClearAndType(driver, city_input, pCity);
	}
	
	public boolean selectState(String pState) {
		return ActionHelper.clickAndSelect(driver, state_dropdown, getDynamicElement(select_state_option, pState));
	}
	
	public boolean typeZip(String pZip) {
		return ActionHelper.ClearAndType(driver, zip_input, pZip);
	}
	
	public boolean clickSubmitButton() {
		return ActionHelper.Click(driver, submit_button);
	}
	
	public boolean isSearchSuccessful() {
		return ActionHelper.waitForElementToBeClickAble(driver, submit_button);
	}
	
	
	


}
