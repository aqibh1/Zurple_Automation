/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.utility.ActionHelper;
import us.zengtest1.Page;

/**
 * @author darrraqi
 *
 */
public class ZWHomeValuesPageTest extends PageTest{

	private WebDriver driver;
	private ZWHomeValuesPage page;
	private JSONObject dataObject;
	
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
		page.clickOnSubmitButton();
		boolean l_alert = ActionHelper.getAlertText(driver);
		System.out.println(l_alert);
	}
	
	private void fillInHomeValueForm(String pAddress, String pCity, String pZip, String pState, String pBeds, String pBaths, String pSqFeet,
			String pFirstName, String pLastName, String pPhone, String pEmail, boolean pPun) {
		assertTrue(page.typeStreetAddress(pAddress), "Unable to type address..");
		assertTrue(page.typeCity(pCity), "Unable to type address..");
		assertTrue(page.typeZipCode(pZip), "Unable to type address..");
		assertTrue(page.selectState(pState), "Unable to type address..");
		assertTrue(page.selectBath(pBaths), "Unable to type address..");
		assertTrue(page.selectBeds(pBeds), "Unable to type address..");
		assertTrue(page.selectSqFeet(pSqFeet), "Unable to type address..");
		assertTrue(page.typeFirstName(pFirstName), "Unable to type address..");
		assertTrue(page.typeLastName(pLastName), "Unable to type address..");
		assertTrue(page.typePhone(pPhone), "Unable to type address..");
		if(pPun) {
			assertTrue(page.clickOnPUNCheckbox(), "Unable to click on PUN checkbox..");
		}
		
	}
}
