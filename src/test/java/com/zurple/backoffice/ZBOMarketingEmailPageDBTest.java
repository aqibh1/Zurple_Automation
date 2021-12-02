/**
 * 
 */
package com.zurple.backoffice;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.zurple.admin.ZAProcessEmailQueuesPage;
import com.zurple.backoffice.marketing.ZBOMarketingEmailMessagePage;
import com.zurple.my.PageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.ModuleCacheConstants;
import resources.ModuleCommonCache;
import resources.alerts.zurple.backoffice.ZBOSucessAlert;
import resources.orm.hibernate.models.zurple.Email;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;
import resources.utility.CacheFilePathsConstants;
import resources.utility.DBConstants;
import resources.utility.GmailEmailVerification;

/**
 * @author habibaaq
 *
 */
public class ZBOMarketingEmailPageDBTest extends PageTest{

	ZBOMarketingEmailMessagePage page;
	private WebDriver driver;
		
	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		if(page==null) {
			driver = getDriver();
			page = new ZBOMarketingEmailMessagePage(driver);
			setLoginPage(driver);
			page.setUrl("");
			page.setDriver(driver);
		}
		return page;
	}
	
	public AbstractPage getPage(String pUrl) {
		if(page==null) {
			driver = getDriver();
			page = new ZBOMarketingEmailMessagePage(driver);
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
	
	@BeforeTest
	public void backOfficeLogin() {
		getPage();
		if(!getLoginPage().doLogin(getZurpeBOUsername(), getZurpeBOPassword())) {
			throw new SkipException("Skipping the test becasuse [Login] pre-condition was failed.");
		}
	}
	
	/**
	 * C47393	Verify that agent is able to receive C1 summary emails
	 */
	@Test
	public void testAlertSummaryEmailFromDB() {
		page=null;
		getPage();
		ZAProcessEmailQueuesPage processQueue = new ZAProcessEmailQueuesPage(driver);
		if(!getIsProd()) {
			page=null;
			getPage("/admin/processemailqueue");
			processQueue.processAlertQueue();
			processQueue.processCreateC1SummaryQueue();
			processQueue.processSendC1SummaryQueue();
			processQueue.processSendC1SummaryQueue(); // this is called twice to make sure any failed attempt should be retried. 
		} 
		DBHelperMethods dbObject = new DBHelperMethods(getEnvironment());
		String lSentDateTime, lUserId = "";
		Email emailObject = dbObject.getEmailType(DBConstants.EmailTypeAlertSummary);
		lSentDateTime = emailObject.getSendDatetime().toString();
		lUserId = emailObject.getUser().toString();
		boolean isSuccessful = page.isEmailSentToday(lSentDateTime);
		if(isSuccessful) {
			AutomationLogger.info("Today "+lSentDateTime+" alert summary emails are sent..");
		}
		assertTrue(isSuccessful,"ALERT!! Alert Summary Emails are not sent today..");
	}
	
	/**
	 * C39879	Verify that campaigns should be sent out according to their order
	 */
	@Test
	public void testCampaignEmailFromDB() {
		page=null;
		getPage();
		ZAProcessEmailQueuesPage processQueue = new ZAProcessEmailQueuesPage(driver);
		if(!getIsProd()) {
			page=null;
			getPage("/admin/processemailqueue");
			processQueue.processMassEmailQueue();
		} 
		DBHelperMethods dbObject = new DBHelperMethods(getEnvironment());
		String lSentDateTime, lUserId = "";
		Email emailObject = dbObject.getEmailType(DBConstants.EmailTypeCampaign);
		lSentDateTime = emailObject.getSendDatetime().toString();
		lUserId = emailObject.getUser().toString();
		boolean isSuccessful = page.isEmailSentToday(lSentDateTime);
		if(isSuccessful) {
			AutomationLogger.info("Today campaign emails are sent to: "+lUserId);
		}
		assertTrue(isSuccessful,"ALERT!! Campaign Emails are not sent today..");
	}
	
	/**
	 * C39979	Verify that mass email is sent to leads successfully
	 */
	@Test
	public void testMassEmailFromDB() {
		page=null;
		getPage();
		ZAProcessEmailQueuesPage processQueue = new ZAProcessEmailQueuesPage(driver);
		if(!getIsProd()) {
			page=null;
			getPage("/admin/processemailqueue");
			processQueue.processMassEmailQueue();
		} 
		DBHelperMethods dbObject = new DBHelperMethods(getEnvironment());
		String lSentDateTime, lUserId = "";
		Email emailObject = dbObject.getEmailType(DBConstants.EmailTypeMassEmails);
		lSentDateTime = emailObject.getSendDatetime().toString();
		lUserId = emailObject.getUser().toString();
		boolean isSuccessful = page.isEmailSentToday(lSentDateTime);
		if(isSuccessful) {
			AutomationLogger.info("Today Mass emails are sent to: "+lUserId);
		}
		assertTrue(isSuccessful,"ALERT!! Mass Emails are not sent today..");
	}
	
	/**
	 * C47394	Verify that lead is able to receive AR emails
	 */
	@Test
	public void testAutoResponderEmailFromDB() {
		page=null;
		getPage();
		ZAProcessEmailQueuesPage processQueue = new ZAProcessEmailQueuesPage(driver);
		if(!getIsProd()) {
			page=null;
			getPage("/admin/processemailqueue");
			processQueue.processAutoResponderQueue();
		} 
		DBHelperMethods dbObject = new DBHelperMethods(getEnvironment());
		String lSentDateTime, lUserId = "";
		Email emailObject = dbObject.getEmailType(DBConstants.EmailTypeAutoResponder);
		lSentDateTime = emailObject.getSendDatetime().toString();
		lUserId = emailObject.getUser().toString();
		boolean isSuccessful = page.isEmailSentToday(lSentDateTime);
		if(isSuccessful) {
			AutomationLogger.info("Today Auto Responder emails are sent to: "+lUserId);
		}
		assertTrue(isSuccessful,"ALERT!! Auto Responder Emails are not sent today..");
	}
	
	/**
	 * Verify that PUNs emails should be received everyday at 6am
	 * 39944
	 */
	
	@Test
	public void testPUNSFromDB(){
		getPage();
		DBHelperMethods dbObject = new DBHelperMethods(getEnvironment());
		String lSentDateTime, lUserId = "";
		Email emailObject = dbObject.getEmailType(DBConstants.EmailTypePropertyUpdate);
		lSentDateTime = emailObject.getSendDatetime().toString();
		lUserId = emailObject.getUser().toString();
		boolean isSuccessful = page.isEmailSentToday(lSentDateTime);
		if(isSuccessful) {
			AutomationLogger.info("Today PUNs are sent to user_id: "+lUserId);
		}
		assertTrue(isSuccessful,"ALERT!! PUNs are not sent today..");
	}
	
	@Test
	public void testCMAEmailFromDB(){
		page=null;
		getPage();
		ZAProcessEmailQueuesPage processQueue = new ZAProcessEmailQueuesPage(driver);
		if(!getIsProd()) {
			page=null;
			getPage("/admin/processemailqueue");
			processQueue.processCMAQueue();
		} 
		DBHelperMethods dbObject = new DBHelperMethods(getEnvironment());
		String lSentDateTime, lUserId = "";
		Email emailObject = dbObject.getEmailType(DBConstants.EmailTypeCMAEmails);
		lSentDateTime = emailObject.getSendDatetime().toString();
		lUserId = emailObject.getUser().toString();
		boolean isSuccessful = page.isEmailSentToday(lSentDateTime);
		if(isSuccessful) {
			AutomationLogger.info("Today CMA email is sent to user_id: "+lUserId);
		}
		assertTrue(isSuccessful,"ALERT!! CMA emails are not sent today..");
	}
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
}
