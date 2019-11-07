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
		assertTrue(page.selectCity(dataObject.optString("city_criteria")), "Unable to select city");
		assertTrue(page.clickSaveButton(), "Unable click on save button..");
		
		if(getIsProd()) {
			ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail, lLeadEmail);
		}else {
			ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadEmail, lLeadEmail.replace("@", "_ZurpleQA@"));
		}
		ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleLeadName, lLeadName);

		AutomationLogger.endTestCase();
	}

	

}