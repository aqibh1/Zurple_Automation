package resources.forms.z57;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.forms.AbstractForm;
import resources.utility.ActionHelper;
import resources.utility.FrameworkConstants;

public class SearchSoldHomesForm extends AbstractForm{

	@FindBy(xpath="//input[@id='ic_address']")
	WebElement address_input;
	
	@FindBy(xpath="//input[@id='ic_city']")
	WebElement city_input;
	
	@FindBy(xpath="//select[@id='ic_state']")
	WebElement state_dropdown;
	
	@FindBy(xpath="//input[@id='ic_zip']")
	WebElement zip_input;
	
	@FindBy(xpath="//select[@id='ic_radius']")
	WebElement radius_dropdown;
	
	@FindBy(xpath="//button[@id='ic_submit']")
	WebElement submit_button;
	
	String select_state_option="//select[@id='ic_state']/option[text()='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	String select_radius_option="//select[@id='ic_state']/option[@value='"+FrameworkConstants.DYNAMIC_VARIABLE+"']";
	
	public SearchSoldHomesForm(WebDriver pWebDriver) {
		driver=pWebDriver;
		PageFactory.initElements(driver, this);
	}
	
	public boolean typeAddress(String pAddress) {
		return ActionHelper.Type(driver, address_input, pAddress);
	}
	
	public boolean typeCity(String pCity) {
		return ActionHelper.Type(driver, city_input, pCity);
	}
	
	public boolean selectState(String pState) {
		return ActionHelper.clickAndSelect(driver, state_dropdown, getDynamicElement(select_state_option, pState));
	}
	
	public boolean typeZip(String pZip) {
		return ActionHelper.ClearAndType(driver, zip_input, pZip);
	}
	
	public boolean selectRadius(String pRadius) {
		return ActionHelper.clickAndSelect(driver, radius_dropdown, getDynamicElement(select_radius_option, pRadius));
	}
	
	public boolean clickSubmitButton() {
		return ActionHelper.Click(driver, submit_button);
	}
	
	
	
}
