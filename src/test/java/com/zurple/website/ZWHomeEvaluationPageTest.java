/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import java.util.Random;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

/**
 * @author adar
 *
 */
public class ZWHomeEvaluationPageTest extends PageTest{
	
	private WebDriver driver;
	private ZWHomeEvaluationPage page;
	private JSONObject dataObject;
	
	@Override
	public void testTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testBrand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page getPage() {
		if(page == null){
			driver = getDriver();
			page = new ZWHomeEvaluationPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public Page getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new ZWHomeEvaluationPage(driver);
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
	@Parameters({"dataFile"})
	public void testCaptureLeadFromHomeEvaluationPage(@Optional String pDataFile) {
		getPage("/sold-homes");
		dataObject = getDataFile(pDataFile);
		String ld_email = updateEmail(dataObject.optString("email"));
		String ld_firstName = updateName(dataObject.optString("first_name"));
		String ld_lastname = updateName(dataObject.optString("last_name"));;
		
		assertTrue(page.clickOnHomeEvaluationButton(),"Unable to click on Home Evaluation button..");
		assertTrue(page.typeSellerStreet(dataObject.optString("seller_street")),"Unable to type seller street..");
		assertTrue(page.typeSellerCity(dataObject.optString("seller_city")),"Unable to type seller city..");
		assertTrue(page.typeSellerState(dataObject.optString("seller_state")),"Unable to type seller state..");
		assertTrue(page.typeSellerZip(dataObject.optString("seller_zip")),"Unable to type seller zip..");
		assertTrue(page.selectSqFeet(dataObject.optString("sqft")),"Unable to select seller sq feet..");
		assertTrue(page.selectBeds(dataObject.optString("beds")),"Unable to select seller beds..");
		assertTrue(page.selectBaths(dataObject.optString("baths")),"Unable to select seller baths..");
		assertTrue(page.typeFirstName(ld_firstName),"Unable to type seller first name..");
		assertTrue(page.typeLastName(ld_lastname),"Unable to type seller last name..");
		assertTrue(page.typeEmail(ld_email),"Unable to type seller email..");
		assertTrue(page.isEmailCheckboxChecked(),"Unable to type seller street..");
//		assertTrue(page.isEmailDataPrivacyPolicyChecked(),"Unable to type seller street..");
		assertTrue(page.clickOnSubmitButton(),"Unable to click on submit button..");
		assertTrue(new ZWRegisterUserPage(driver).isRequestSubmittedSuccessfully(), "User is not registered..");
		
		String lLeadId = driver.getCurrentUrl().split("lead_id=")[1];
		
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(), ModuleCacheConstants.ZurpleLeadEmail, ld_email);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(),ld_email,lLeadId);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId,lLeadId);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadName,ld_firstName+" "+ld_lastname);
		
	}
	
}
