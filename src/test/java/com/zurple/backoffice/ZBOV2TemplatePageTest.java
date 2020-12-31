package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.utility.AutomationLogger;

public class ZBOV2TemplatePageTest extends PageTest{

	private WebDriver driver;
	private JSONObject dataObject;
	ZBOV2TemplatePage page;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOV2TemplatePage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOV2TemplatePage(driver);
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
	public void testVerifyV2Settings(String pDataFile) {
		AutomationLogger.startTestCase("Verify V2 site settings");
		dataObject = getDataFile(pDataFile);
		getPage("/pagemgr/index/site_id/"+dataObject.optString("site_id"));
		assertEquals(page.getDomainName(),dataObject.optString("domain_name"));
		assertTrue(page.addSingleAdditionalCity(),"Unable to select additional city..");
		assertTrue(page.clickAddCity(), "Unable to click add city..");
		assertTrue(page.clickV2Checkbox(),"Unable to click v2 checkbox..");
		assertTrue(page.clickUpdate(),"Unable to click update settings..");
		assertEquals(page.getValidationMessage().trim(),dataObject.optString("validation_message").trim());
		assertTrue(page.clickAdditionalCityForAdd(),"Unable to select additional city..");
		assertTrue(page.clickUpdate(),"Unable to click update settings..");
		AutomationLogger.endTestCase();
	}
	
	@Test(dependsOnMethods = { "testVerifyV2Settings" })
	@Parameters({"dataFile"})
	public void testVerifyV2ZSettingsDisabled(String pDataFile) {
		AutomationLogger.startTestCase("Verify V2 site settings are turned off");
		dataObject = getDataFile(pDataFile);
		page=null;
		getPage("/pagemgr/index/site_id/"+dataObject.optString("site_id"));
		assertEquals(page.getDomainName(),dataObject.optString("domain_name"));
		assertTrue(page.clickV2UnCheck(),"Unable to click v2 checkbox..");
		assertTrue(page.clickAdditionalCityForDel(),"Unable to remove additional city..");
		assertTrue(page.clickUpdate(),"Unable to click update settings..");
		AutomationLogger.endTestCase();
	} 

	
}
