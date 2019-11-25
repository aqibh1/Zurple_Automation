/**
 * 
 */
package com.zurple.website;

import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import us.zengtest1.Page;
import us.zengtest1.PageTest;

/**
 * @author adar
 *
 */
public class ZWRegisterUserPageTest extends PageTest{
	
	WebDriver driver;
	ZWRegisterUserPage page;
	private JSONObject lDataObject;
	@Override
	public void testTitle() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testHeader() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void testBrand() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Page getPage() {
		if(page == null){
			driver = getDriver();
			page = new ZWRegisterUserPage(driver);
		}
		return page;
	}
	public Page getPage(String pUrl) {
		if(page == null){
			driver = getDriver();
			page = new ZWRegisterUserPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}
		return page;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
	
	@Test
	@Parameters({"registerUserDataFile"})
	public void testRegisterUser(String pDataFile) {
		AutomationLogger.startTestCase("Register User");
		getPage("/register");
		lDataObject = getDataFile(pDataFile);
		String lName = updateName(lDataObject.optString("name"));
		String lEmail = updateEmail(lDataObject.optString("email"));
	
		registerUser(lName,lEmail);
		
		String lLeadId = driver.getCurrentUrl().split("lead_id=")[1];
		
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(), ModuleCacheConstants.RegisterFormLeadEmail, lEmail);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(),lEmail,lLeadId);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId,lLeadId);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadName,lName);

		ActionHelper.staticWait(30);
		AutomationLogger.endTestCase();
		
	}
	
	@Test(priority=15)
	@Parameters({"registerUserDataFile"})
	public void testRegisterUserScheduleShowing(String pDataFile) {
		AutomationLogger.startTestCase("Register User from Schedule Showing");
		getPage();
		lDataObject = getDataFile(pDataFile);
		String lName = updateName(lDataObject.optString("name"));
		String lEmail = updateEmail(lDataObject.optString("email"));
		
		registerUser(lName,lEmail);
		String lLeadId = driver.getCurrentUrl().split("lead_id=")[1];
		
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(), ModuleCacheConstants.RegisterFormLeadEmail, lEmail);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(),lEmail,lLeadId);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId,lLeadId);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadName,lName);

		AutomationLogger.endTestCase();
		
	}
	
	@Test
	@Parameters({"registerUserDataFile"})
	public void testRegisterUserFromLoginPage(String pDataFile) {
	AutomationLogger.startTestCase("Register User from Schedule Showing");
	getPage();
	lDataObject = getDataFile(pDataFile);
	String lName = updateName(lDataObject.optString("name"));
	String lEmail = updateEmail(lDataObject.optString("email"));
	
	ZWLoginPage loginPage = new ZWLoginPage(driver);
	assertTrue(loginPage.isLoginPage(), "Login Page is not displayed");
	assertTrue(loginPage.clickOnSignUpLink(),"Unable click on sign up link..");
	
	registerUser(lName,lEmail);
	
	String lLeadId = driver.getCurrentUrl().split("lead_id=")[1];
	
	ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(), ModuleCacheConstants.ZurpleLeadEmail, lEmail);
	ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(),lEmail,lLeadId);
	ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(),ModuleCacheConstants.ZurpleLeadId,lLeadId);
	ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadName,lName);

	ActionHelper.staticWait(30);
}


	private void registerUser(String pName, String pEmail) {
		assertTrue(page.isRegisterPage(),"Register page is not opened..");
		assertTrue(page.typeName(pName),"Unable to type name..");
		assertTrue(page.typeEmail(pEmail),"Unable to type email..");
		if(!lDataObject.optString("phone").isEmpty()) {
			assertTrue(page.typePhone(lDataObject.optString("phone")),"Unable to type phone..");
		}
		assertTrue(page.isTermsAndCondCheckboxChecked(),"Terms and conditions checkbox is not checked..");
		assertTrue(page.clickRegisterButton(),"Unable to click on register button..");
//		ActionHelper.staticWait(5);
//		page.handleAlert();
		assertTrue(page.isRegisterSuccessfully(),"Registration of user is unsuccessful..");
		
	}
	
	

}
