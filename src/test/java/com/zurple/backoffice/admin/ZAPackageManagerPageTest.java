package com.zurple.backoffice.admin;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZAPackageManagerPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;

/**
 * 
 * @author habibaaq
 *
 */

public class ZAPackageManagerPageTest extends PageTest{

	private WebDriver driver;
	private ZAPackageManagerPage page;
	JSONObject dataIdObject;
	JSONObject dataObject;
	String lPackageId = "";
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZAPackageManagerPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZAPackageManagerPage(driver);
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

	@Test(priority=240)
	@Parameters({"packageIdFile","packageDataFile"})
	public void testSetup(String pPackageIdDataFile, String pPackageDataFile) {
		dataIdObject = getDataFile(pPackageIdDataFile);
		dataObject = getDataFile(pPackageDataFile);
		lPackageId = dataIdObject.optString("package_id");
		page=null;
		getPage("/packagemgr/edit/package_id/"+lPackageId); 
		emptyFile(pPackageIdDataFile,"");
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyFullName() {
		assertTrue(page.verifyFullName(dataObject.getJSONObject("name").optString("content")),"Unable to verify full name "+lPackageId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyEmail() {
		assertTrue(page.verifyEmail(dataObject.getJSONObject("email").optString("content")),"Unable to verify email "+lPackageId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyPhone() {
		assertTrue(page.verifyPhone(dataObject.getJSONObject("phone").optString("content")),"Unable to verify phone "+lPackageId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyCompany() {
		assertTrue(page.verifyCompany(dataObject.getJSONObject("subsidiary").optString("content")),"Unable to verify company "+lPackageId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifySetupFee() {
		assertTrue(page.verifySetupFee(),"Unable to verify setup fee "+lPackageId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyURLPath() {
		assertTrue(page.verifyURLPath(dataObject.getJSONObject("path").optString("content")),"Unable to verify url path "+lPackageId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyZurpleClientId() {
		assertTrue(page.verifyZRMClientId(),"Unable to verify client id "+lPackageId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifySubscriptionDate() {
		assertTrue(page.verifySubscriptionDate(setScheduledPostDate().split(" ")[0]),"Unable to verify subscription date "+lPackageId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyPayers() {
		assertTrue(page.verifyPayers(dataObject.getJSONObject("payers").optString("content")),"Unable to verify payers "+lPackageId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyAdditionalAdmins() {
		assertTrue(page.verifyAdditionalAdmins(dataObject.getJSONObject("additional_admins").optString("content")),"Unable to verify additional admins "+lPackageId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyPackageBundles() { //same as feature bundle
		assertTrue(page.verifyFeatureBundles("PriorityRanking",2),"Unable to verify feature bundle 1 "+lPackageId); //To be Improved later
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testVerifyFeatureBundles() {
		assertTrue(page.verifyFeatureBundles("MassSMS",3),"Unable to verify feature bundle 2 "+lPackageId); //To be Improved later
	}

	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}


}
