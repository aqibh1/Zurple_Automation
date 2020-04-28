/**
 * 
 */
package com.z57.site.v2;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.DBHelperMethods;
import resources.forms.z57.RegisterForm;
import resources.forms.z57.SEMRegisterForm;
import resources.utility.AutomationLogger;


/**
 * @author adar
 *
 */
public class SEMIDXAndNonIDXPageTest extends PageTest{
	
	private SEMIDXAndNonIDXPage page;
	WebDriver driver;
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
		if(page == null) {
			driver = getDriver();
			page = new SEMIDXAndNonIDXPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public Page getPage(String pUrl) {
		if(page == null) {
			driver = getDriver();
			page = new SEMIDXAndNonIDXPage(driver);
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
	public void testCaptureLeadFromSEMNonIDXPage(String pDataFile) {
		AutomationLogger.startTestCase("");
		getPage("/search-homes");
		JSONObject lJsonDataObj = getDataFile(pDataFile);
		assertTrue(page.isSEMNonIDXPageIsVisible(), "SEM non IDX page is not visible..");
		assertTrue(page.clickOnViewButton(), "Unable to click on view button..");
		registrationFormFill(lJsonDataObj);		
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testCaptureLeadFromSEMIDXPage(String pDataFile) {
		AutomationLogger.startTestCase("");
		getPage("/idx/sem-idx");
		JSONObject lJsonDataObj = getDataFile(pDataFile);
		assertTrue(page.isSEMIDXPageIsVisible(), "SEM IDX page is not visible..");
		assertTrue(page.clickOnViewButton(), "Unable to click on view button..");
		registrationFormFill(lJsonDataObj);
		
	}
	public void registrationFormFill(JSONObject lJsonDataObj) {
		String lName = updateName(lJsonDataObj.optString("user_name"));
		String lEmail = updateEmail(lJsonDataObj.optString("user_email"));
		registerLead(lName,lEmail, lJsonDataObj.optString("user_phone_number"));
		DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
    	assertTrue(dbHelperMethods.verifyLeadInDB(lEmail,getLeadId()),"Lead not verified in DB");
	}
	
	  //All the leads register related test cases will use this method.
    private boolean registerLead(String pName, String pUserEmail, String pPUserPhoneNumber) {
    	SEMRegisterForm registerFormObj = new SEMRegisterForm(driver);
    	assertTrue(registerFormObj.isRegisterFormVisible(), "Register form is not displayed..");
    	if(!pName.isEmpty()) {
    		assertTrue(registerFormObj.typeName(pName),"Unable to type Name in input field");
    	}
    	if(!pUserEmail.isEmpty()) {
    		assertTrue(registerFormObj.typeEmail(pUserEmail),"Unable to type Email in input field");
    	}
    	if(!pPUserPhoneNumber.isEmpty()) {
    		assertTrue(registerFormObj.typePhone(pPUserPhoneNumber),"Unable to type Phone Number in input field");
    	}
    	assertTrue(registerFormObj.clickOnSendButton(),"Unable to click on Register button");
    	return registerFormObj.isLeadRegisteredSuccessfully();
    }
}
