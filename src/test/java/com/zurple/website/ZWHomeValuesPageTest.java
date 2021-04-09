/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import us.zengtest1.Page;

/**
 * @author darrraqi
 *
 */
public class ZWHomeValuesPageTest extends PageTest{

	private WebDriver driver;
	private ZWHomeValuesPage page;
	private JSONObject dataObject;
	String l_streetAddress = "";
	String l_city = "";
	String l_ZipCode = "";
	String l_state = "";
	String l_firstname = "";
	String l_lastname = "";
	String l_email = "";
	String l_phone = "";
	String l_beds = "";
	String l_baths = "";
	String l_sqfeet = "";
	boolean l_pun = false;
	
	@Override
	public Page getPage() {
		page = null;
		if(page == null){
			driver = getDriver();
			page = new ZWHomeValuesPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public Page getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new ZWHomeValuesPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}

	@Test
	public void testVerifyHomeValuePageIsPopulated() {
		getPage("/homevalues");
		assertTrue(page.isTellUsAboutYourPropHeadingVisible(), "Tell us about your property heading is not visible..");
		assertTrue(page.isTellUsReportHeadingVisible(), "Tell us report heading is not visible..");
		assertTrue(page.isStreetAddressInputVisible(), "Street Address input is not visible..");
		assertTrue(page.isStateInputVisible(), "State Input is not visible..");
		assertTrue(page.isZipCodeInputVisible(), "Zip Code Input is not visible..");
		assertTrue(page.isCityInputVisible(), "City input is not visible..");
		assertTrue(page.isBedsInputVisible(), "Beds dropdown is not visible..");
		assertTrue(page.isBathsInputVisible(), "Baths dropdown is not visible..");
		assertTrue(page.isSqFeetDropDownVisible(), "Square Feet dropdown is not visible..");
		assertTrue(page.isFirstnameInputVisible(), "First Name input is not visible..");
		assertTrue(page.isLastNameInputVisible(), "Last Name input is not visible..");
		assertTrue(page.ispHONEnUMBERVisible(), "Phone Number input is not visible..");
		assertTrue(page.isPUNCheckboxVisible(), "PUN Checkbox is not visible..");
		assertTrue(page.isSubmitButtonVisible(), "Submit button is not visible..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyEmptyCityTriggersValidationMessage(String pDataFile) {
		getPage("/homevalues");
		dataObject = getDataFile(pDataFile);
		setData();
		fillInHomeValueForm(l_streetAddress, "",l_ZipCode, l_state, l_beds, l_baths, l_sqfeet,l_firstname, l_lastname, l_email,l_phone, l_pun);
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(!page.getCityValidationMessage().isEmpty(), "City Validation message is not displayed..");

	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyEmptyZipTriggersValidationMessage(String pDataFile) {
		getPage("/homevalues");
		dataObject = getDataFile(pDataFile);
		setData();
		fillInHomeValueForm(l_streetAddress, l_city,"", l_state, l_beds, l_baths, l_sqfeet,l_firstname, l_lastname, l_email,l_phone, l_pun);
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(!page.getZipCodeValidationMessage().isEmpty(), "Zip Code Validation message is not displayed..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyEmptyStateTriggersValidationMessage(String pDataFile) {
		getPage("/homevalues");
		dataObject = getDataFile(pDataFile);
		setData();
		fillInHomeValueForm(l_streetAddress, l_city,l_ZipCode, "", l_beds, l_baths, l_sqfeet,l_firstname, l_lastname, l_email,l_phone, l_pun);
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(!page.getStateValidationMessage().isEmpty(), "State Validation message is not displayed..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyEmptyFirstNameTriggersValidationMessage(String pDataFile) {
		getPage("/homevalues");
		dataObject = getDataFile(pDataFile);
		setData();
		fillInHomeValueForm(l_streetAddress, l_city,l_ZipCode, l_state, l_beds, l_baths, l_sqfeet,"", l_lastname, l_email,l_phone, l_pun);
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(!page.getFirstNameValidationMessage().isEmpty(), "First Name Validation message is not displayed..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyEmptyLastNameTriggersValidationMessage(String pDataFile) {
		getPage("/homevalues");
		dataObject = getDataFile(pDataFile);
		setData();
		fillInHomeValueForm(l_streetAddress, l_city,l_ZipCode, l_state, l_beds, l_baths, l_sqfeet,l_firstname, "", l_email,l_phone, l_pun);
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(!page.getLastNameValidationMessage().isEmpty(), "Last Name Validation message is not displayed..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyEmptyEmailTriggersValidationMessage(String pDataFile) {
		getPage("/homevalues");
		dataObject = getDataFile(pDataFile);
		setData();
		fillInHomeValueForm(l_streetAddress, l_city,l_ZipCode, l_state, l_beds, l_baths, l_sqfeet,l_firstname, l_lastname, "",l_phone, l_pun);
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(!page.getEmailValidationMessage().isEmpty(), "Email Validation message is not displayed..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyEmptyStreetInputTriggersValidationMessage(String pDataFile) {
		getPage("/homevalues");
		dataObject = getDataFile(pDataFile);
		setData();
		fillInHomeValueForm("", l_city,l_ZipCode, l_state, l_beds, l_baths, l_sqfeet,l_firstname, l_lastname, l_email,l_phone, l_pun);
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		assertTrue(!page.getStreetValidationMessage().isEmpty(), "Address Validation message is not displayed..");
	}
	
	
	private void fillInHomeValueForm(String pAddress, String pCity, String pZip, String pState, String pBeds, String pBaths, String pSqFeet,
			String pFirstName, String pLastName, String pEmail, String pPhone, boolean pPun) {
		assertTrue(page.typeStreetAddress(pAddress), "Unable to type address..");
		assertTrue(page.typeCity(pCity), "Unable to type address..");
		assertTrue(page.typeZipCode(pZip), "Unable to type address..");
		assertTrue(page.selectState(pState), "Unable to type address..");
		assertTrue(page.selectBath(pBaths), "Unable to type address..");
		assertTrue(page.selectBeds(pBeds), "Unable to type address..");
		assertTrue(page.selectSqFeet(pSqFeet), "Unable to type address..");
		assertTrue(page.typeFirstName(pFirstName), "Unable to type address..");
		assertTrue(page.typeLastName(pLastName), "Unable to type address..");
		assertTrue(page.typeEmail(pEmail), "Unable to type email..");
		assertTrue(page.typePhone(pPhone), "Unable to type phone..");
		if(pPun) {
			assertTrue(page.clickOnPUNCheckbox(), "Unable to click on PUN checkbox..");
		}	
	}
	private void setData() {
		l_streetAddress = dataObject.optString("street");
		l_city = dataObject.optString("city");
		l_ZipCode = dataObject.optString("zip_code");
		l_state = dataObject.optString("state");
		l_firstname = updateName(dataObject.optString("first_name"));
		l_lastname = dataObject.optString("last_name");
		l_email = updateEmail(dataObject.optString("email"));
		l_phone = dataObject.optString("phone");
		l_beds = dataObject.optString("beds");
		l_baths = dataObject.optString("baths");
		l_sqfeet = dataObject.optString("sqfeet");
		l_pun = dataObject.optBoolean("pun");
	}
}
