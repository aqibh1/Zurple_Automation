package com.zurple.backoffice.admin;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZAAdminManagerPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * 
 * @author habibaaq
 *
 */

public class ZAAdminManagerPageTest extends PageTest{

	private WebDriver driver;
	private ZAAdminManagerPage page;
	JSONObject dataIdObject;
	JSONObject dataObject;
	String lAdminId = "";

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZAAdminManagerPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZAAdminManagerPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;

	}
	@Override
	public void clearPage() {
		// TODO Auto-generated method stub

	}

	@BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}

	@Test(priority=241)
	@Parameters({"adminIdFile","adminDataFile"})
	public void testSetup(String pAdminIdDataFile, String pAdminDataFile) {
		dataIdObject = getDataFile(pAdminIdDataFile);
		dataObject = getDataFile(pAdminDataFile);
		lAdminId = dataIdObject.optString("admin_id");
		page=null;
		getPage("/adminmgr/edit/admin_id/"+lAdminId);
		// emptyFile(pAdminIdDataFile,"");
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyFirstName() {
		assertTrue(page.verifyFirstName(dataObject.getJSONObject("first_name").optString("content")),"Unable to verify admin first name "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyLastName() {
		assertTrue(page.verifyLastName(dataObject.getJSONObject("last_name").optString("content")),"Unable to verify admin last name "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyPhone() {
		assertTrue(page.verifyPhone(dataObject.getJSONObject("phone").optString("content")),"Unable to verify admin phone "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyAliasPhone() {
		assertTrue(page.verifyAliasPhone(),"Unable to verify alias phone "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyAgentCode() {
		assertTrue(page.verifyAgentCode("2455"),"Unable to verify agent id "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyFeed() {
		assertTrue(page.verifyFeed("1"),"Unable to verify feed "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyCMSFlag() {
		assertTrue(page.verifyCMSFlag(),"Unable to verify CMS flag "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyBillingFlag() {
		assertTrue(page.verifyBillingFlag(),"Unable to verify billing flag "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyPropFlag() {
		assertTrue(page.verifyPropFlag(),"Unable to verify prop flag "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyLoginEmail() {
		assertTrue(page.verifyLoginEmail(dataObject.getJSONObject("email").optString("content")),"Unable to verify admin login email "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyAltEmail() {
		assertTrue(page.verifyAltEmail(dataObject.getJSONObject("alt_email").optString("content")),"Unable to verify admin alt email "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyForwardEmail() {
		assertTrue(page.verifyFowardEmail(dataObject.getJSONObject("forward1").optString("content")),"Unable to verify admin forward email "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyTimeZone() {
		assertTrue(page.verifyTimeZone("Pacific Time Zone"),"Unable to verify admin time zone "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyOfficeName() {
		assertTrue(page.verifyOfficeName(dataObject.getJSONObject("office_name").optString("content")),"Unable to verify admin office name "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyOfficeAddress() {
		assertTrue(page.verifyOfficeAddress(dataObject.getJSONObject("office_address").optString("content")),"Unable to verify admin office address "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyPackageId() {
		assertTrue(page.verifyPackageId(dataObject.getJSONObject("package_id").optString("content")),"Unable to verify admin package id "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifySMSFlag() {
		assertTrue(page.verifySMSFlag(),"Unable to verify sms flag "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifySMSNotificationFlag() {
		assertTrue(page.verifySMSNotification(),"Unable to verify sms notifications flag "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyOwnerId() {
		assertTrue(page.verifyOwnerId("1"),"Unable to verify owner id "+lAdminId); 
	}

	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}

}
