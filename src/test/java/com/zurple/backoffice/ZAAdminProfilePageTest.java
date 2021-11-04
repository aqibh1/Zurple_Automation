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
	getPage("/agent/edit/admin_id/"+lAdminId);
	emptyFile(pAdminIdDataFile,"");
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyFirstName() {
	assertTrue(page.verifyFirstName(splitString(dataObject.optString("first_name"),"content",":","}")),"Unable to verify admin first name "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyLastName() {
	assertTrue(page.verifyLastName(splitString(dataObject.optString("last_name"),"content",":","}")),"Unable to verify admin last name "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyDisplayName() {
	assertTrue(page.verifyDisplayName(splitString(dataObject.optString("first_name"),"content",":","}")),"Unable to verify admin first name "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyPhone() {
	assertTrue(page.verifyPhone(splitString(dataObject.optString("phone"),"content",":","}")),"Unable to verify admin phone "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyCMSFlag() {
	assertTrue(page.verifySuggestedTaskFlag(),"Unable to verify Suggested Tasks flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyBillingFlag() {
	assertTrue(page.verifySMSReplyFlag(),"Unable to verify SMS Reply flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyUniqueSignOff() {
	assertTrue(page.verifyUniqueSignOff("Respectfully,"),"Unable to verify SMS Reply flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyPropFlag() {
	assertTrue(page.verifyDisplayProfileImageFlag(),"Unable to verify profile image flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyBrokerageLogoFlag() {
	assertTrue(page.verifyDisplayBrokerageLogoFlag(),"Unable to verify profile image flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyLicense() {
	assertTrue(page.verifyLicenseNumber(),"Unable to verify profile image flag "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyLoginEmail() {
	assertTrue(page.verifyLoginEmail(splitString(dataObject.optString("email"),"content",":","}")),"Unable to verify admin login email "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyAltEmail() {
	assertTrue(page.verifyAliasEmail(splitString(dataObject.optString("alt_email"),"content",":","}")),"Unable to verify admin alias email "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyTimeZone() {
	assertTrue(page.verifyTimeZone("Pacific Time Zone"),"Unable to verify admin time zone "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyBrokerageName() {
	assertTrue(page.verifyBrokerageName(splitString(dataObject.optString("office_name"),"content",":","}")),"Unable to verify admin office name "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyBrokeragePhone() {
	assertTrue(page.verifyBrokeragePhone(splitString(dataObject.optString("office_name"),"content",":","}")),"Unable to verify admin office name "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyBrokerageAddress() {
	assertTrue(page.verifyBrokerageAddress(splitString(dataObject.optString("office_address"),"content",":","}")),"Unable to verify admin office address "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyZillowConnection() {
	assertTrue(page.verifyZillowConnection("Disabled"),"Unable to verify admin time zone "+lAdminId);
}

@Test(dependsOnMethods = { "testSetup" })
public void testVerifyZillowEmail() {
	assertTrue(page.verifyZillowEmail(splitString(dataObject.optString("email"),"content",":","}")),"Unable to verify admin login email "+lAdminId);
}

public String splitString(String str, String firstSplit, String secondSplit, String thirdSplit) {
	return str.split(firstSplit)[1].split(secondSplit)[1].split(thirdSplit)[0];
}

@AfterTest
public void closeBrowser() {
	closeCurrentBrowser();
}

}
