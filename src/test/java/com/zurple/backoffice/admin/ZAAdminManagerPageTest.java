package com.zurple.backoffice.admin;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
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

public class ZAAdminManagerPageTest extends PageTest{

private WebDriver driver;
private ZAAdminManagerPage page;

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

@Test(priority=240)
@Parameters({"adminIdFile","adminDataFile"})
public void testVerifyAutoProvisionedAdmin(String pAdminIdDataFile, String pAdminDataFile) {
	JSONObject dataIdObject = getDataFile(pAdminIdDataFile);
	JSONObject dataObject = getDataFile(pAdminDataFile);
	String lAdminId = dataIdObject.optString("admin_id");
	page=null;
	getPage("/adminmgr/edit/admin_id/"+12828); //.replaceAll("^\"|\"$", "")
	page.verifyFirstName(splitString(dataObject.optString("first_name"),"content",":","}"));
	page.verifyLastName(splitString(dataObject.optString("last_name"),"content",":","}"));
	page.verifyPhone(splitString(dataObject.optString("phone"),"content",":","}"));
	page.verifyAliasPhone();
	page.verifyAgentCode("2080");
	page.verifyFeed("1");
	page.verifyCMSFlag();
	page.verifyLeadFlag();
	page.verifyBillingFlag();
	page.verifyPropFlag();
	page.verifyLoginEmail(splitString(dataObject.optString("email"),"content",":","}"));
	page.verifyAltEmail(splitString(dataObject.optString("alt_email"),"content",":","}"));
	page.verifyFowardEmail(splitString(dataObject.optString("forward1"),"content",":","}"));
	page.verifyTimeZone(splitString(dataObject.optString("time_zone"),"content",":","}"));
	page.verifyOfficeName(splitString(dataObject.optString("office_name"),"content",":","}"));
	page.verifyOfficePhone(splitString(dataObject.optString("office_phone"),"content",":","}"));
	page.verifyOfficeAddress(splitString(dataObject.optString("office_address"),"content",":","}"));
	page.verifyPackageId(splitString(dataObject.optString("package_id"),"content",":","}"));
	page.verifySMSFlag();
	page.verifySMSNotification();
	page.verifyOwnerId("1"); 
}

public String splitString(String str, String firstSplit, String secondSplit, String thirdSplit) {
	return str.split(firstSplit)[1].split(secondSplit)[1].split(thirdSplit)[0];
}

}
