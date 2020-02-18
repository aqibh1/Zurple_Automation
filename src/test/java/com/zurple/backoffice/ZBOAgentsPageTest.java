package com.zurple.backoffice;

import static org.testng.Assert.assertEquals;
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

public class ZBOAgentsPageTest extends PageTest {
	WebDriver driver;
	ZBOAgents page;

	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAgents(driver);
			page.setUrl("");
		}
		return page;
	}

	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOAgents(driver);
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
	public void testAgentsPageLabel() {
		AutomationLogger.startTestCase("Manage Agents");
		getPage("/agents");
		assertEquals("Manage Agents", page.verifyPageTitle());
		AutomationLogger.endTestCase();
	}
}
