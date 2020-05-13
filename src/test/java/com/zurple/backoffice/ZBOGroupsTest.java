package com.zurple.backoffice;

import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.utility.AutomationLogger;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class ZBOGroupsTest extends PageTest{
WebDriver driver;
ZBOGroups page;

@Override
public AbstractPage getPage() {
	// TODO Auto-generated method stub
	if(page==null) {
		driver = getDriver();
		page = new ZBOGroups(driver);
		page.setUrl("");
	}
	return page;
}

public AbstractPage getPage(String pUrl) {
	if(page==null) {
		driver = getDriver();
		page = new ZBOGroups(driver);
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
public void testGroupTags() {
	AutomationLogger.startTestCase("Manage Agent");
	getPage("/leads/crm");
	//assertTrue(page.verifyPageTitle(), "Agents page title not found..");
	page.justTesting1();
	page.justTesting2();
	page.justTesting3();
	page.justTesting4();
	AutomationLogger.endTestCase();
}

}
