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
		getPage("/register");
		lDataObject = getDataFile(pDataFile);
		String lName = updateName(lDataObject.optString("name"));
		String lEmail = updateEmail(lDataObject.optString("email"));
		
		AutomationLogger.startTestCase("Register User");
		assertTrue(page.isRegisterPage(),"Register page is not opened..");
		assertTrue(page.typeName(lName),"Unable to type name..");
		assertTrue(page.typeEmail(lEmail),"Unable to type email..");
		if(!lDataObject.optString("phone").isEmpty()) {
			assertTrue(page.typePhone(lDataObject.optString("phone")),"Unable to type phone..");
		}
		assertTrue(page.isTermsAndCondCheckboxChecked(),"Terms and conditions checkbox is not checked..");
		assertTrue(page.clickRegisterButton(),"Unable to click on register button..");
		
		assertTrue(page.isRegisterSuccessfully(),"Registration of user is unsuccessful..");
		
		String lLeadId = driver.getCurrentUrl().split("lead_id=")[1];
		
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(), ModuleCacheConstants.RegisterFormLeadEmail, lEmail);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId().toString(),lEmail,lLeadId);

		AutomationLogger.endTestCase();
		
	}

}