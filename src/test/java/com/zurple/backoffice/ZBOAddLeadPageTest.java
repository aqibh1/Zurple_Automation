/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZBOAddLeadPageTest extends PageTest{

	private WebDriver driver;
	private JSONObject dataObject;
	private ZBOAddLeadPage page;
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAddLeadPage(driver);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAddLeadPage(driver);
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
	@Parameters({"addLeadData"})
	public void testAddLead(String pDataFile) {
		AutomationLogger.startTestCase("Add lead /lead/create");
		getPage("/lead/create");
		dataObject = getDataFile(pDataFile);
		String  lLeadEmail= updateEmail(dataObject.optString("email"));
		String  lLeadName = updateName(dataObject.optString("first_name"));
		assertTrue(page.typeEmail(lLeadEmail), "Unable to type email address..");
		assertTrue(page.typeFirstName(lLeadName), "Unable to type first name..");
		if(!dataObject.optString("city_criteria").isEmpty()) {
			assertTrue(page.selectCity(dataObject.optString("city_criteria")), "Unable to select city");
		}
		if(!dataObject.optString("zip_criteria").isEmpty()) {
			assertTrue(page.selectZip (dataObject.optString("zip_criteria")), "Unable to select city");
		}
		
		if(!getIsProd()) {
			lLeadEmail = lLeadEmail.replace("@", "_ZurpleQA@");
		}
		
		boolean isWelcomeEmail = dataObject.optString("welcome_Email")!=null?true:false;
		if(isWelcomeEmail) {
			assertTrue(page.clickWelcomeEmailToggle(), "Unable to click on welcome email toggle button..");
		}
		
		assertTrue(page.clickSaveButton(), "Unable click on save button..");
		
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail, lLeadEmail);
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadName, lLeadName);
		String lLeadId = driver.getCurrentUrl().split("user_id/")[1];
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), lLeadEmail, lLeadId);
		
//		int lLead_id = new DBHelperMethods(getEnvironment()).getZurpleLeadId(lLeadEmail);
//		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), lLeadEmail, lLead_id);

		AutomationLogger.endTestCase();
	}

	

}