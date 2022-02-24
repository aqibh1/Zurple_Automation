package com.torchx;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.AbstractPage;
import resources.utility.ActionHelper;

import com.zurple.website.*;

/**
 * 
 * @author ahabib
 *
 */
public class TXBORecentVisitorsPanelTest extends PageTest {
	private WebDriver driver;
	TXBORecentVisitorsPanel page;
	private JSONObject dataObject;
	private JSONObject lDataObject;	
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new TXBORecentVisitorsPanel(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new TXBORecentVisitorsPanel(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}
	
	@BeforeTest
	public void loginToBO() {
		page=null;
		getPage();
		if(!getLoginPage().doLogin(getTXBOUsername(), getTXBOPassword())) {
			throw new SkipException("Skipping the test because [Login] pre-condition was failed.");
		}
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyRecentVisitorsPanelHeader(String pDataFile) {
		page=null;
		getPage();
		dataObject = getDataFile(pDataFile);
		assertEquals(page.getPanelHeaderText(),dataObject.optString("panel_header"),"Panel Header is not found..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyRecentVisitorsPanelTooltip(String pDataFile) {
		page=null;
		getPage();
		dataObject = getDataFile(pDataFile);
		assertEquals(page.getToolTipText(),dataObject.optString("tooltip_text"),"Tooltip text is not valid..");
	}
	
	@Test
	@Parameters({"registerUserDataFile"})
	public void testVerifyLeadFormat(String pDataFile) {
		page=null;
		getPage();
		lDataObject = getDataFile(pDataFile);
		String lName = updateName(lDataObject.optString("tx_name"));
		String lEmail = updateEmail(lDataObject.optString("email"));
		String lPhone = lDataObject.optString("tx_phone");
		registerUser(lName, lEmail, lPhone, false);
		page=null;
		getPage();
		page.getLeadInfo();
		assertEquals(page.leadInfoList.get(0).trim(),lName);
		assertTrue(page.leadInfoList.get(1).trim().contains(lDataObject.optString("tx_phone")),"Unable to verify lead format..");
	}
	
	@Test
	public void testVerifyLeadVisitDate() {
		page=null;
		getPage();
		page.getLeadInfo();
		assertTrue(page.leadInfoList.get(2).trim().contains(getTodaysDateInPST(0,"MM/dd/YY")+" at"),"Unable to verify lead visit date..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyActionButtons(String pDataFile) {
		page=null;
		getPage();
		lDataObject = getDataFile(pDataFile);
		assertEquals(page.getActionHeader(),lDataObject.optString("action_column"));
		assertTrue(page.noteActionButton(),"Unable to verify notes action button..");
		assertTrue(page.emailActionButton(),"Unable to verify emails action button..");
		assertTrue(page.reminderActionButton(),"Unable to verify reminder action button..");
		assertTrue(page.smsActionButton(),"Unable to verify sms action button..");
	}
	
	@Test
	@Parameters({"registerUserDataFile"})
	public void testVerifySMSActionButtonDisabled(String pDataFile) {
		page=null;
		getPage();
		lDataObject = getDataFile(pDataFile);
		String lName = updateName(lDataObject.optString("tx_name"));
		String lEmail = updateEmail(lDataObject.optString("email"));
		// String lPhone = lDataObject.optString("tx_phone");
		registerUser(lName, lEmail, "", true);
		page=null;
		getPage();
		assertEquals(page.smsActionDisabled(),"true");
	}
	
	@Test
	@Parameters({"registerUserDataFile"})
	public void testVerifyEmailDisabledWithInvalidEmail(String pDataFile) {
		page=null;
		getPage();
		lDataObject = getDataFile(pDataFile);
		String lName = updateName(lDataObject.optString("tx_name"));
		String lEmail = updateEmail(lDataObject.optString("invalid_email"));
		// String lPhone = lDataObject.optString("tx_phone");
		registerUser(lName, lEmail, "", true);
		page=null;
		getPage();
		String lURL = page.clickFirstLead();
		ActionHelper.openUrlInCurrentTab(driver, lURL);
		if(page.isEmailVerified()) {
			page=null;
			getPage();
			assertEquals(page.emailActionDisabled(),"true");
		}
}

	public void registerUser(String lName, String lEmail, String lPhone, boolean isLogout) {
		try {
			String txWebsite = getIsProd()?getTXProdWebsite():getTXStageWebsite();
			//if is logout=true then logout first before registering
			if(isLogout) {
				ActionHelper.openUrlInCurrentTab(driver, txWebsite+"/logout");
			} 
			// Pre-condition
			ActionHelper.openUrlInCurrentTab(driver, txWebsite+"/register");
			ZWRegisterUserPage registerPage = new ZWRegisterUserPage(driver);
			assertTrue(registerPage.isRegisterPage(),"Register page is not opened..");
			assertTrue(registerPage.typeName(lName),"Unable to type name..");
			assertTrue(registerPage.typeEmail(lEmail),"Unable to type email..");
			if(!lPhone.isEmpty()) {
				assertTrue(registerPage.typePhone(lDataObject.optString("tx_phone")),"Unable to type phone..");
			}
			assertTrue(registerPage.isTermsAndCondCheckboxChecked(),"Terms and conditions checkbox is not checked..");
			assertTrue(registerPage.clickRegisterButton(),"Unable to click on register button..");
			assertTrue(registerPage.isRegisterSuccessfully(),"Registration of user is unsuccessful..");
		} catch(Exception e) {
			throw new SkipException("Skipping the test: Unable to register lead..");
		}
	}
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
