package com.torchx;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
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
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyRecentVisitorsPanelHeader(String pDataFile) {
		getPage();
		dataObject = getDataFile(pDataFile);
		if(!getLoginPage().doLogin(getTXBOUsername(), getTXBOPassword())) {
			throw new SkipException("Skipping the test because [Login] pre-condition was failed.");
		}
		assertEquals(page.getPanelHeaderText(),dataObject.optString("panel_header"));
	}
	
	@Test
	@Parameters({"dataFile"})
	public void testVerifyRecentVisitorsPanelTooltip(String pDataFile) {
		getPage();
		dataObject = getDataFile(pDataFile);
		assertEquals(page.getToolTipText(),dataObject.optString("tooltip_text"));
	}
	
	@Test
	@Parameters({"registerUserDataFile"})
	public void testVerifyLeadFormat(String pDataFile) {
		getPage();
		lDataObject = getDataFile(pDataFile);
		String txWebsite = getIsProd()?getTXProdWebsite():getTXStageWebsite();
		ZWRegisterUserPage registerPage = new ZWRegisterUserPage(driver);
		String lName = updateName(lDataObject.optString("tx_name"));
		String lEmail = updateEmail(lDataObject.optString("email"));
		// Pre-condition
		try {
			ActionHelper.openUrlInCurrentTab(driver, txWebsite+"/register");
			assertTrue(registerPage.isRegisterPage(),"Register page is not opened..");
			assertTrue(registerPage.typeName(lName),"Unable to type name..");
			assertTrue(registerPage.typeEmail(lEmail),"Unable to type email..");
			if(!lDataObject.optString("tx_phone").isEmpty()) {
				assertTrue(registerPage.typePhone(lDataObject.optString("tx_phone")),"Unable to type phone..");
			}
			assertTrue(registerPage.isTermsAndCondCheckboxChecked(),"Terms and conditions checkbox is not checked..");
			assertTrue(registerPage.clickRegisterButton(),"Unable to click on register button..");
			assertTrue(registerPage.isRegisterSuccessfully(),"Registration of user is unsuccessful..");
		} catch(Exception e) {
			throw new SkipException("Skipping the test: Unable to register lead..");
		}
		// Actual Test
		page=null;
		getPage();
		page.getLeadInfo();
		assertEquals(page.leadInfoList.get(0).trim(),lName);
		assertTrue(page.leadInfoList.get(1).trim().contains(lDataObject.optString("tx_phone")));
	}
	
	@Test
	public void testVerifyLeadVisitDate() {
		page=null;
		getPage();
		page.getLeadInfo();		
		assertTrue(page.leadInfoList.get(2).trim().contains(getTodaysDate(0,"MM/dd/YY")+" at"));
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
}
