/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.backoffice.ZBOLeadCRMPage;
import com.zurple.backoffice.ZBOLeadDetailPage;
import com.zurple.my.PageTest;

import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
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
			setLoginPage(driver);
		}
		return page;
	}
	public Page getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new ZWHomeValuesPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
			setLoginPage(driver);
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
	
	@Test//40422
	@Parameters({"dataFile"})
	public void testUserIsRedirectedToThankYouPage(String pDataFile) {
		getPage("/homevalues");
		
		dataObject = getDataFile(pDataFile);
		setData();
		fillInHomeValueForm(l_streetAddress, l_city,l_ZipCode, l_state, l_beds, l_baths, l_sqfeet,l_firstname, l_lastname, l_email,l_phone, l_pun);
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
//		assertTrue(new ZWRegisterUserPage(driver).isRegisterSuccessfully(),"Registration of user is unsuccessful..");
//		String lLeadId = driver.getCurrentUrl().split("lead_id=")[1];
		ZWHomesForSalePage homesForSale = new ZWHomesForSalePage(driver);
		assertTrue(homesForSale.isHomeForSalePage(), "User is not redirected to Homes for Sale Page..");
	}
	
	@Test //40429
	public void testVerifyLeadIsAddedInBackOffice() {
		loginPreCondition();
		String lLeadId = serachAndSelectLeadPreCond();
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId,lLeadId);
		assertTrue(!lLeadId.isEmpty(), "Lead ID is not added in the back office "+lLeadId);
	}
	@Test//C40426
	public void testVerifyLeadSource() {
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		String l_currentUrl = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+lLeadId;
		driver.navigate().to(l_currentUrl);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.getLeadSource().equalsIgnoreCase("Seller Campaign"), "Lead Source value is not Seller Campaign");
		processEmailQueuesAndAlerts(leadDetailPage);
	}
	
	@Test//C40425
	public void testVerifyHomeValueNote() {
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		String l_currentUrl =EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+lLeadId;
		driver.navigate().to(l_currentUrl);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.verifyNoteAndTime("from Zurple Seller Campaign"), "Notes is not added in lead details for Seller lead campaign..");
	}
	
	@Test //C40424
	public void testVerifyHomeVlauationEmailIsTriggered() {
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		String l_currentUrl =EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+lLeadId;
		driver.navigate().to(l_currentUrl);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.verifyEmailInZurpleMesssagesTab("per your valuation request"), "Unable to find the email "+"'per your valuation request'");
	}
	
	@Test //C40423
	public void testVerifyHomeVlauationAlertIsTriggered() {
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		String l_currentUrl =EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+lLeadId;
		driver.navigate().to(l_currentUrl);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.verifyHomeEvaluationAlert("Homeowner Asked for a CMA"), "Unable to find the alert "+"'Homeowner Asked for a CMA'");
	}

	@Test //C40423
	public void testVerifyPropertUpdateIsSetToNo() {
		String lLeadId = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		String l_currentUrl =EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+lLeadId;
		driver.navigate().to(l_currentUrl);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.verifyEmailPreferences("Property Updates", "No"), "Property Updates value is not NO ");
	}
	
	@Test//40422
	@Parameters({"dataFile"})
	public void testVerifyPropertUpdateIsSetToYes(String pDataFile) {
		getPage("/homevalues");		
		dataObject = getDataFile(pDataFile);
		setData();
		fillInHomeValueForm(l_streetAddress, l_city,l_ZipCode, l_state, l_beds, l_baths, l_sqfeet,l_firstname, l_lastname, l_email,l_phone, l_pun);
		assertTrue(page.clickOnSubmitButton(), "Unable to click on submit button");
		loginPreCondition();
		String lLeadId = serachAndSelectLeadPreCond();
		String l_currentUrl =EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url")+"/lead/"+lLeadId;
		driver.navigate().to(l_currentUrl);
		ZBOLeadDetailPage leadDetailPage = new ZBOLeadDetailPage(driver);
		assertTrue(leadDetailPage.verifyEmailPreferences("Property Updates", "Yes"), "Property Updates value is not NO ");
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
	private void loginPreCondition() {
		driver.navigate().to(EnvironmentFactory.configReader.getPropertyByName("zurple_bo_base_url"));
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	public String serachAndSelectLeadPreCond() {
		String l_currentUrl = driver.getCurrentUrl().replace("/dashboard", "")+"/leads/crm";
		driver.navigate().to(l_currentUrl);
		ZBOLeadCRMPage leadcrmpage = new ZBOLeadCRMPage(driver);
		return leadcrmpage.searchAndGetLeadId(l_firstname);
	}
	private void processEmailQueuesAndAlerts(ZBOLeadDetailPage pLeadDetailPage ) {
		//Triggers the alerts and email
		if(!getIsProd()) {
			if(pLeadDetailPage.isEmailVerified()) {
				page=null;
				getPage("/admin/processemailqueue");
				ZAProcessEmailQueuesPage processQueue = new ZAProcessEmailQueuesPage(driver);
				processQueue.processAlertQueue();
				processQueue.processImmediateResponderQueue();
				processQueue.processMassEmailQueue();
				processQueue.processNextDayResponderQueue();
			}
		}

	}
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
