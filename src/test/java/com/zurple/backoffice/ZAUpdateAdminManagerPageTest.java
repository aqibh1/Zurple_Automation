package com.zurple.backoffice;

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
import resources.EnvironmentFactory;

/**
 * 
 * @author habibaaq
 *
 */
public class ZAUpdateAdminManagerPageTest extends PageTest{
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

	@Test(priority=243)
	@Parameters({"adminIdFile","adminDataFile"})
	public void testSetup(String pAdminIdDataFile, String pAdminDataFile) {
		dataIdObject = getDataFile(pAdminIdDataFile);
		dataObject = getDataFile(pAdminDataFile);
		lAdminId = EnvironmentFactory.configReader.getPropertyByName("ap_admin_id");
		page=null;
		getPage("/adminmgr/edit/admin_id/"+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdateFirstName() {
		assertTrue(page.updateFirstName(),"Unable to update admin first name "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdateLastName() {
		assertTrue(page.updateLastName(),"Unable to update admin last name "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdateEmail() {
		assertTrue(page.updateEmail(),"Unable to update admin email "+lAdminId);
	}

	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdatePhone() {
		assertTrue(page.updatePhone(),"Unable to update admin phone "+lAdminId);
	}
	
	@Test(dependsOnMethods = { "testSetup" })
	public void testUpdateOfficeName() {
		assertTrue(page.updateOfficeName(),"Unable to update admin office name "+lAdminId);
	}
	
	@Test(priority=244,dependsOnMethods = { "testSetup" })
	public void testSubmitUpdates() {
		assertTrue(page.clickUpdateButton(),"Unable to submit admin updates  "+lAdminId);
	}

	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
