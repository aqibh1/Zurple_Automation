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
		getPage();
		if(!getLoginPage().doLogin(getTXBOUsername(), getTXBOPassword())) {
			throw new SkipException("Skipping the test because [Login] pre-condition was failed.");
		}
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyRecentVisitorsPanelHeader(String pDataFile) {
		getPage();
		dataObject = getDataFile(pDataFile);
		assertEquals(page.getPanelHeaderText(),dataObject.optString("panel_header"),"Panel Header is not found..");
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyRecentVisitorsPanelTooltip(String pDataFile) {
		getPage();
		dataObject = getDataFile(pDataFile);
		assertEquals(page.getToolTipText(),dataObject.optString("tooltip_text"),"Tooltip text is not valid..");
	}
	
	@Test
	@Parameters({"registerUserDataFile"})
	public void testVerifyLeadFormat(String pDataFile) {
		getPage();
		lDataObject = getDataFile(pDataFile);
		String lName = updateName(lDataObject.optString("tx_name"));
		String lEmail = updateEmail(lDataObject.optString("email"));
		String lPhone = lDataObject.optString("tx_phone");
		registerUser(lName, lEmail, lPhone);
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
	
	public void registerUser(String lName, String lEmail, String lPhone) {
		try {
			String txWebsite = getIsProd()?getTXProdWebsite():getTXStageWebsite();
			ZWRegisterUserPage registerPage = new ZWRegisterUserPage(driver);
			// Pre-condition
			ActionHelper.openUrlInCurrentTab(driver, txWebsite+"/register");
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
