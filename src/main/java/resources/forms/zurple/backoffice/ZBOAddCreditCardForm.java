/**
 * 
 */
package resources.forms.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.forms.AbstractForm;
import resources.utility.ActionHelper;

/**
 * @author adar
 *
 */
public class ZBOAddCreditCardForm extends AbstractForm{
	@FindBy(xpath="//h2[text()='Add Credit Card']")
	WebElement addCreditCard_heading;
	
	@FindBy(id="cc_street")
	WebElement cc_street;
	
	@FindBy(id="cc_city")
	WebElement cc_city;
	
	@FindBy(id="cc_state")
	WebElement cc_state;
	
	@FindBy(id="cc_zip")
	WebElement cc_zip;
	
	@FindBy(id="cc_name")
	WebElement cc_name;
	
	@FindBy(id="cc_type")
	WebElement cc_type;
	
	@FindBy(id="cc_number")
	WebElement cc_number;
	
	@FindBy(id="cc_exp_month")
	WebElement cc_exp_month;
	
	@FindBy(id="cc_exp_year")
	WebElement cc_exp_year;
	
	@FindBy(id="update-cc-button")
	WebElement update_cc_button;
	
	
	private ZBOSucessAlert successAlert;
	
	public ZBOAddCreditCardForm(WebDriver pWebDriver) {
		driver = pWebDriver;
		setSuccessAlert();
		PageFactory.initElements(driver, this);
	}
	public ZBOSucessAlert getSuccessAlert() {
		return successAlert;
	}
	public void setSuccessAlert() {
		this.successAlert = new ZBOSucessAlert(driver);
	}
	public boolean isAddCreditCardForm() {
		return ActionHelper.waitForElementToBeVisible(driver, addCreditCard_heading, 30);
	}
	public boolean typeStreet(String pStreet) {
		return ActionHelper.ClearAndType(driver, cc_street, pStreet);
	}
	public boolean typeCity(String pCity) {
		return ActionHelper.ClearAndType(driver, cc_city, pCity);
	}
	public boolean typeZip(String pZip) {
		return ActionHelper.ClearAndType(driver, cc_zip, pZip);
	}
	public boolean typeCCName(String pName) {
		return ActionHelper.ClearAndType(driver, cc_name, pName);
	}
	public boolean typeCCNumber(String pNumber) {
		return ActionHelper.ClearAndType(driver, cc_number, pNumber);
	}
	public boolean typeComment(String pExpMonth) {
		return ActionHelper.ClearAndType(driver, cc_exp_month, pExpMonth);
	}
	public boolean clickAndSelectState(String pState) {
		return ActionHelper.selectDropDownOption(driver, cc_state, "", pState);
	}
	public boolean clickAndSelectCardType(String pCardType) {
		return ActionHelper.selectDropDownOption(driver, cc_type, "", pCardType);
	}
	public boolean clickAndSelectCardExpiryMonth(String pExpMonth) {
		return ActionHelper.selectDropDownOption(driver, cc_exp_month, "", pExpMonth);
	}
	public boolean clickAndSelectCardExpiryYear(String pExpYear) {
		return ActionHelper.selectDropDownOption(driver, cc_exp_year, "", pExpYear);
	}
	public boolean clickOnSaveButton() {
		return ActionHelper.Click(driver, update_cc_button);
	}
	
	public boolean isCardAddedSuccessfully() {
		boolean isAdded = false;
		if(getSuccessAlert().isSuccessMessageVisible()) {
			isAdded = getSuccessAlert().clickOnOkButton();
		}
		return isAdded;
	}
}
