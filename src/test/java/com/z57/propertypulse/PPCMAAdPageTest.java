package com.z57.propertypulse;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.z57.site.v2.SEMIDXAndNonIDXPage;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.forms.z57.SEMRegisterForm;
import resources.utility.FrameworkConstants;

public class PPCMAAdPageTest extends PageTest{
	
	private PPCMAPage page;
	WebDriver driver;
	@Override
	public void testTopMenu() {
		// TODO Auto-generated method stub
		
	}

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
	public AbstractPage getPage() {
		if(page == null) {
			driver = getDriver();
			page = new PPCMAPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page == null) {
			driver = getDriver();
			page = new PPCMAPage(driver);
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
	public void testCaptureLeadFromSellerLandingPage(String pDataFile) {
		if(getIsProd()) {
			getPage("/homevalue/44276-aqib-dar?type=cma1&a=&z57ogi=aHR0cHM6Ly9wcm9wZXJ0eXB1bHNlLno1Ny5jb20vaW1hZ2VzL21jYy9jb21tb24vZmJfYWRfY21hMV80MDB4NzY0LnBuZw,,&fbsource=1578390902");
		}else {
			getPage("/homevalue/11675-aqib-dar?type=cma1&a=&z57ogi=aHR0cHM6Ly9wcm9wZXJ0eXB1bHNlLno1Ny5jb20vaW1hZ2VzL21jYy9jb21tb24vZmJfYWRfY21hMV80MDB4NzY0LnBuZw,,&fbsource=1578390902");

		}
		JSONObject lJsonDataObj = getDataFile(pDataFile);
		registrationFormFill(lJsonDataObj);
	}
	
	public void registrationFormFill(JSONObject lJsonDataObj) {
		String lName = updateName(lJsonDataObj.optString("user_name"));
		String lEmail = updateEmail(lJsonDataObj.optString("user_email"));
		registerLead(lName,lEmail, lJsonDataObj.optString("user_phone_number"));
		DBHelperMethods dbHelperMethods = new DBHelperMethods(getEnvironment());
    	assertTrue(dbHelperMethods.verifyLeadByEmailInDB(lEmail),"Lead not verified in DB");
    	assertTrue(dbHelperMethods.verifyEmailIsSentToLead(lEmail, FrameworkConstants.HomeValueAssesment),"Unable to sent email to Lead with subject "+FrameworkConstants.HomeValueAssesment+"\n Lead Email: "+lEmail);
    	assertTrue(dbHelperMethods.verifyEmailIsSentToAgent(EnvironmentFactory.configReader.getPropertyByName("z57_propertypulse_user_email"), lEmail, FrameworkConstants.YouHaveANewLead), "Unable to sent email to Agent with subject "+FrameworkConstants.YouHaveANewLead);

	}
	
	  //All the leads register related test cases will use this method.
    private boolean registerLead(String pName, String pUserEmail, String pPUserPhoneNumber) {
    	assertTrue(page.isSellerLandingPage(), "Seller Landing page is not displayed..");
    	if(!pName.isEmpty()) {
    		assertTrue(page.typeName(pName),"Unable to type Name in input field");
    	}
    	if(!pUserEmail.isEmpty()) {
    		assertTrue(page.typeEmail(pUserEmail),"Unable to type Email in input field");
    	}
    	if(!pPUserPhoneNumber.isEmpty()) {
    		assertTrue(page.typePhone(pPUserPhoneNumber),"Unable to type Phone Number in input field");
    	}
    	assertTrue(page.clickOnSubmitButton(),"Unable to click on Register button");
    	return page.isRegistrationSuccessful();
    }
}
