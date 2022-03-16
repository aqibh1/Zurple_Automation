package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.restapi.Part;
import com.restapi.Part.PartType;
import com.zurple.admin.ZAAdminManagerPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * 
 * @author habibaaq
 *
 */

public class ZAAdminProfilePageTest extends PageTest{

private WebDriver driver;
private ZAAdminProfilePage page;
JSONObject dataIdObject;
JSONObject dataObject;
String lAdminId = "";
String defaultPassword="Welcome123";

@Override
public AbstractPage getPage() {
	if(page==null) {
		driver = getDriver();
		page = new ZAAdminProfilePage(driver);
		setLoginPage(driver);
		page.setUrl("");
		page.setDriver(driver);
	}
	return page;
}
public AbstractPage getPage(String pUrl) {
	if(page==null) {
		driver = getDriver();
		page = new ZAAdminProfilePage(driver);
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

@Test(priority=242)
@Parameters({"adminIdFile","adminDataFile"})
public void testSetup(String pAdminIdDataFile, String pAdminDataFile) {
	getPage();
	dataIdObject = getDataFile(pAdminIdDataFile);
	dataObject = getDataFile(pAdminDataFile);
	page.setUserName(dataObject.getJSONObject("email").optString("content"));
	page.setPassword(defaultPassword);
	if(!getLoginPage().doLogin(page.getAPAdminUsername().replace("\"", ""), page.getAPAdminPassword())) {
		throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
	}
	lAdminId = dataIdObject.optString("admin_id");
	page=null;
	getPage("/agent/edit/admin_id/"+lAdminId);
	emptyFile(pAdminIdDataFile,"");
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
public void testVerifyDisplayName() {
	assertTrue(page.verifyDisplayName(dataObject.getJSONObject("first_name").optString("content")),"Unable to verify admin display name "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyPhone() {
	AutomationLogger.info("Skipping");
	//assertTrue(page.verifyPhone(dataObject.getJSONObject("phone").optString("content")),"Unable to verify admin phone "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifySuggestedTaskFlag() {
	assertTrue(page.verifySuggestedTaskFlag(),"Unable to verify Suggested Tasks flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifySMSFlag() {
	assertTrue(page.verifySMSReplyFlag(),"Unable to verify SMS Reply flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyUniqueSignOff() {
	assertTrue(page.verifyUniqueSignOff("Respectfully,"),"Unable to verify unique signoff flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyProfileImageFlag() {
	assertTrue(page.verifyDisplayProfileImageFlag(),"Unable to verify profile image flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyBrokerageLogoFlag() {
	assertTrue(page.verifyDisplayBrokerageLogoFlag(),"Unable to verify brokerage Logo flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyLicense() {
	assertTrue(page.verifyLicenseNumber(dataObject.getJSONObject("dre").optString("content")),"Unable to verify agents DRE "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyLoginEmail() {
	assertTrue(page.verifyLoginEmail(dataObject.getJSONObject("email").optString("content")),"Unable to verify admin login email "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyAltEmail() {
	assertTrue(page.verifyAliasEmail(dataObject.getJSONObject("alt_email").optString("content")),"Unable to verify admin alias email "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyTimeZone() {
	assertTrue(page.verifyTimeZone("Pacific Time Zone"),"Unable to verify admin time zone "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyBrokerageName() {
	assertTrue(page.verifyBrokerageName(dataObject.getJSONObject("office_name").optString("content")),"Unable to verify admin brokerage name "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyBrokeragePhone() {
	assertTrue(page.verifyBrokeragePhone(dataObject.getJSONObject("office_phone").optString("content")),"Unable to verify admin brokerage phone "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyBrokerageAddress() {
	assertTrue(page.verifyBrokerageAddress(dataObject.getJSONObject("office_address").optString("content")),"Unable to verify admin brokerage address "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyZillowConnection() {
	assertTrue(page.verifyZillowConnection("Disabled"),"Unable to verify zillow connection "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyZillowEmail() {
	assertTrue(page.verifyZillowEmail(dataObject.getJSONObject("email").optString("content")),"Unable to verify zillow email "+lAdminId);
}

@AfterTest
public void closeBrowser() {
	closeCurrentBrowser();
}

}
