package com.zurple.backoffice;

import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.utility.FrameworkConstants;

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
	public AbstractPage getPage(String pUrl, boolean pForcefully) {
		if(pForcefully) {
			driver = getDriver();
			page = new ZAProcessEmailQueuesPage(driver);
			setLoginPage(driver);
			page.setUrl(pUrl);
			page.setDriver(driver);
		}else if(page==null) {
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
		getPage("/admin/processemailqueue",true);
		page.processEmailsQueues(pEmailType);
	}

	@Test
	public void testProcessImmediateResponderQueue() {
		backOfficeLogin();
		skipIfProd();
		getPage("/admin/processemailqueue",true);
		page.processEmailsQueues(FrameworkConstants.ImmediateResponderQueue);
	}
	
	@Test
	public void testProcessAutoResponderQueue() {
		skipIfProd();
		getPage("/admin/processemailqueue",true);
		page.processEmailsQueues(FrameworkConstants.AutoResponderQueue);
	}
	
	@Test
	public void testProcessNextDayResponderQueue() {
		skipIfProd();
		getPage("/admin/processemailqueue",true);
		page.processEmailsQueues(FrameworkConstants.NextDayResponderQueue);
	}
	
	@Test
	public void testProcessMassEmailQueue() {
		skipIfProd();
		getPage("/admin/processemailqueue",true);
		page.processEmailsQueues(FrameworkConstants.MassEmailQueue);
	}
	
	@Test
	public void testProcessReminderQueue() {
		skipIfProd();
		getPage("/admin/processemailqueue",true);
		page.processEmailsQueues(FrameworkConstants.ReminderQueue);
	}
	
	@Test
	public void testProcessAlertQueue() {
		skipIfProd();
		getPage("/admin/processemailqueue",true);
		page.processEmailsQueues(FrameworkConstants.AlertQueue);
	}
	
	@Test
	public void testProcessCMAEmailQueue() {
		skipIfProd();
		getPage("/admin/processemailqueue",true);
		page.processEmailsQueues(FrameworkConstants.CMAEmailQueue);
	}
	
	@Test
	public void testCreateAndSendSummaryEmail() {
		skipIfProd();
		getPage("/admin/processemailqueue",true);
		page.processEmailsQueues(FrameworkConstants.CreateAndSendSummaryEmail);
	}
	
	private void skipIfProd() {
		if(getIsProd()) {
			throw new SkipException("Skipping the test on Production.");
		}
	}
}
