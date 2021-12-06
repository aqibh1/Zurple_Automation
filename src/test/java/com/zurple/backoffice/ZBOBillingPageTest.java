/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.blocks.zurple.ZBOHeadersBlock;

/**
 * @author adar
 *
 */
public class ZBOBillingPageTest extends PageTest{

	private ZBOBillingPage page;
	private WebDriver driver;
	private ZBOAgentsPageTest agentPageTest;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOBillingPage(driver);
			agentPageTest = new ZBOAgentsPageTest();
			page.setUrl("");
			page.setDriver(driver);
			setLoginPage(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOBillingPage(driver);
			agentPageTest = new ZBOAgentsPageTest();
			page.setUrl(pUrl);
			page.setDriver(driver);
			setLoginPage(driver);
		}
		return page;		
	}
	public AbstractPage getPage(String pUrl, boolean pForcefully) {
		if(page!=null && pForcefully) {
			driver = getDriver();
			page = new ZBOBillingPage(driver);
			agentPageTest = new ZBOAgentsPageTest();
			page.setUrl(pUrl);
			page.setDriver(driver);
			setLoginPage(driver);
		}
		return page;
	}

	
	@BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	
	/**
	 * Verify Add Payment Plan button is visible on Billing Page
	 * 47375
	 */
	@Test
	@Parameters({"dataFile"})
	public void testIsAddPaymentPlanButtonVisibleOnBillingPage(String pDataFile) {
		//PreCondition
		preConditionForAddBilling(pDataFile);
		getPage("/billing",true);
		assertTrue(page.isBilingPage(), "Billing Page is not visible..");
		assertTrue(page.isAddPaymentPlanButtonVisible(), "Billing Page is not visible..");
	}
	
	
	/**
	 * Verify click on 'Add Payment Plan' button takes user to add credit card form
	 * 47376
	 */
	@Test
	public void testVerifyAddPaymentPlanButtonTakesUserToCreditCardForm() {
		getPage("/billing");
		assertTrue(page.clickOnAddPaymentPlan(), "Unable to click on Add payment plan button..");
		assertTrue(page.getCreditCardForm().isAddCreditCardForm(), "Credit Card form is not opened..");
	}
	
	/**
	 * @param pDataFile
	 * Verify Credit Card form contains all the fields
	 * 47402
	 */
	@Test
	@Parameters({"dataFile1"})
	public void testAddPaymentPlan(String pDataFile) {
		getPage("/billing");
		JSONObject dataObject = getDataFile(pDataFile);
		addCreditCardPaymentPlan(dataObject);		
	}
	
	/**
	 * 47378
	 * Verify after clicking Submit button on credit card form success message is displayed
	 */
	@Test
	public void testVerifySuccessMessageIsDisplayedAfterClickingCCSubmitButton() {
		assertTrue(page.getCreditCardForm().clickOnSaveButton(), "Unable to click on save button");
		assertTrue(page.getCreditCardForm().isCardAddedSuccessfully(), "Card added successfully message is not displayed");
	}
	
	/**
	 * @param pDataFile
	 * Verify Credit Card is added in the Payment plan successfully
	 * 47379
	 */
	@Test
	@Parameters({"dataFile1"})
	public void testVerifyCardInfoIsDsiplayedOnBillingInfoPage(String pDataFile) {
		getPage();
		JSONObject dataObject = getDataFile(pDataFile);
		String l_expiryDate = dataObject.optString("cc_expiry_month")+"/"+dataObject.optString("cc_expiry_year");
		assertTrue(page.isClickHereToUpdateButtonVisible(), "Click here to update button is not visble after 5 minutes");
		assertTrue(page.isCardHolderNameDisplayed(dataObject.optString("cc_holder_name")), "Card Holder name is not displayed");
		assertTrue(page.isCardNumberDisplayed("****"), "Card Name is not displayed");
		assertTrue(page.isCardExpirationDateDisplayed(l_expiryDate), "Card expiry date is not displayed");
//		assertTrue(page.isCardHolderAddressDisplayed(dataObject.optString("street")), "Card Holder address is not displayed");
		
	}
	public void preConditionForAddBilling(String pDataFile) {
		addAgent(pDataFile);
		//Logout
		ZBOHeadersBlock headers = new ZBOHeadersBlock(driver);
		assertTrue(headers.logoutFromBackOffice(), "Unable to logout from back office.");
		//Login with agent email
		String l_agent_email = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAgentEmail);
		String l_agent_password = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAgentPassword);
		assertTrue(getLoginPage().doLogin(l_agent_email, l_agent_password), "Unable to login");
	}
	
	public void postConditionsForBilling() {
		ZBOHeadersBlock headers = new ZBOHeadersBlock(driver);
		assertTrue(headers.logoutFromBackOffice(), "Unable to logout from back office.");
		assertTrue(getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword()), "Unable to login");
		String l_agent_Details_url = ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleAgentIdUrl);
		agentPageTest.deleteAgentFromAgentDetailsPage(l_agent_Details_url);
	}
	
	private void addAgent(String pDataFile) {
		agentPageTest.testCreateAgents(pDataFile);
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	@AfterTest
	public void closeBrowser() {
		postConditionsForBilling();
		closeCurrentBrowser();
	}
	
	public void addCreditCardPaymentPlan(JSONObject pDataObject) {
		assertTrue(page.getCreditCardForm().typeStreet(pDataObject.optString("street")), "Unable to type street");
		assertTrue(page.getCreditCardForm().typeCity(pDataObject.optString("city")), "Unable to type City");
		assertTrue(page.getCreditCardForm().clickAndSelectState(pDataObject.optString("state")), "Unable to type state");
		assertTrue(page.getCreditCardForm().typeZip(pDataObject.optString("zip_code")), "Unable to type Zip Code");
		assertTrue(page.getCreditCardForm().typeCCName(pDataObject.optString("cc_holder_name")), "Unable to type Card Holder Name");
		assertTrue(page.getCreditCardForm().clickAndSelectCardType(pDataObject.optString("cc_type")), "Unable to select  Card Type");
		assertTrue(page.getCreditCardForm().typeCCNumber(pDataObject.optString("cc_number")), "Unable to type credit card number");
		assertTrue(page.getCreditCardForm().clickAndSelectCardExpiryMonth(pDataObject.optString("cc_expiry_month")), "Unable to select expiry month");
		assertTrue(page.getCreditCardForm().clickAndSelectCardExpiryYear(pDataObject.optString("cc_expiry_year")), "Unable to select expiry year");
		
	}

}
