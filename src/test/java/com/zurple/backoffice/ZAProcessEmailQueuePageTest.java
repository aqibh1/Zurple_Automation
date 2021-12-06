package com.zurple.backoffice;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.zurple.admin.ZACreateActivityAlertPage;
import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;

public class ZAProcessEmailQueuePageTest extends PageTest{
	private WebDriver driver;
	private ZAProcessEmailQueuesPage page;
	
	@Override
	public AbstractPage getPage() {
		if(page==null) {
			driver = getDriver();
			page = new ZAProcessEmailQueuesPage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZAProcessEmailQueuesPage(driver);
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
	
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	
	@Test
	public void testProcessEmailsQueues(String pEmailType) {
		backOfficeLogin();
		page=null;
		getPage("/admin/processemailqueue");
		page.processEmailsQueues(pEmailType);
	}

}
