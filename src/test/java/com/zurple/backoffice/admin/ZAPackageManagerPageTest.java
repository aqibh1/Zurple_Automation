package com.zurple.backoffice.admin;

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
import com.zurple.admin.ZAPackageManagerPage;
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

public class ZAPackageManagerPageTest extends PageTest{

private WebDriver driver;
private ZAPackageManagerPage page;

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
public void testVerifyAutoProvisionedPackage(String pPackageIdDataFile, String pPackageDataFile) {
	JSONObject dataIdObject = getDataFile(pPackageIdDataFile);
	JSONObject dataObject = getDataFile(pPackageDataFile);
	String lPackageId = dataIdObject.optString("package_id");
	page=null;
	getPage("/packagemgr/edit/package_id/"+lPackageId); 
	boolean test1 = page.verifyFullName(splitString(dataObject.optString("name"),"content",":","}"));
	boolean test2 = page.verifyEmail(splitString(dataObject.optString("email"),"content",":","}"));
	boolean test3 = page.verifyPhone(splitString(dataObject.optString("phone"),"content",":","}"));
	boolean test4 = page.verifyCompany(splitString(dataObject.optString("subsidiary"),"content",":","}"));
	boolean test5 = page.verifySetupFee();
	boolean test6 = page.verifyURLPath(splitString(dataObject.optString("path"),"content",":","}"));
	boolean test7 = page.verifyZRMClientId();
	boolean test8 = page.verifySubscriptionDate(setScheduledPostDate().split(" ")[0]);
	boolean test9 = page.verifyPayers(splitString(dataObject.optString("payers"),"content",":","}"));
	boolean test10 = page.verifyAdditionalAdmins(splitString(dataObject.optString("additional_admins"),"content",":","}"));
	boolean test11 = page.verifyFeatureBundles("PriorityRanking",2); //To be Improved later
	boolean test12 = page.verifyFeatureBundles("MassSMS",3); //To be Improved later
}

public String splitString(String str, String firstSplit, String secondSplit, String thirdSplit) {
	return str.split(firstSplit)[1].split(secondSplit)[1].split(thirdSplit)[0].replaceAll("^\"|\"$", "");
}

@AfterTest
public void closeBrowser() {
	closeCurrentBrowser();
}


}
