/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.util.HashMap;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.PageTest;
import com.zurple.admin.ZACreateActivityAlertPage;

import resources.AbstractPage;
import resources.EnvironmentFactory;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

/**
 * @author adar
 *
 */
public class ZACreateActivityAlert extends PageTest{
	private WebDriver driver;
	private JSONObject dataObject;
	private ZACreateActivityAlertPage page;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZACreateActivityAlertPage(driver);
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZACreateActivityAlertPage(driver);
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
	@Parameters({"dataFile"})
	public void testCreateAlert(String pDataFile) {
		dataObject = getDataFile(pDataFile);
		//2020-09-15 00:00
		String lTodaysDate = getTodaysDate(0,"YYYY-MM-dd")+" 00:00";
		String lAdminId = EnvironmentFactory.configReader.getPropertyByName("zurple_bo_default_agent_id");
		String lAlert_Type = dataObject.optString("alert_type");
		getPage("/admin/createactivityalert");
		boolean isProd = getIsProd();
		String lc_user_id =  ModuleCommonCache.getElement(getThreadId(), ModuleCacheConstants.ZurpleLeadId);
		assertTrue(page.isActivityAlertPage(), "Activity alert page is not displayed..");
		assertTrue(page.selectAlertType(dataObject.optString("alert_type")), "Unable to select Alert Type...");
		if(!isProd) {
			assertTrue(page.selectPackage(dataObject.optString("package_id")), "Activity alert page is not displayed..");
		}
		assertTrue(page.selectAdmin(lAdminId), "Activity alert page is not displayed..");
		assertTrue(page.selectUser(lc_user_id), "Activity alert page is not displayed..");
		AutomationLogger.info("Lead selected");
		if(lAlert_Type.equalsIgnoreCase("Saved to Favorites Alert")) {
			HashMap<String,String> propKeyValuePair = page.selectAndGetPropertyIndexAndValues();
			if(propKeyValuePair!=null) {
				ModuleCommonCache.updateCacheForModuleObject(getThreadId(), ModuleCacheConstants.ZurpleProp, propKeyValuePair);
			}else {
				assertTrue(false,"Unable to select the property..");
			}
		}else if(lAlert_Type.equalsIgnoreCase("High Value")) {
			assertTrue(page.selectCity(dataObject.optString("city")), "Unable to select city from dropdown");
			AutomationLogger.info("City selected");
		}
		assertTrue(page.typeTriggerDate(lTodaysDate), "Activity alert page is not displayed..");
		ActionHelper.staticWait(5);
		assertTrue(page.clickOnCreateAlertButton(), "Unable to click on Create Alert button..");
		ActionHelper.staticWait(5);
		assertTrue(page.isSuccessMessageDisplayed(), "Activity alert is not created..");
		
	}
}
