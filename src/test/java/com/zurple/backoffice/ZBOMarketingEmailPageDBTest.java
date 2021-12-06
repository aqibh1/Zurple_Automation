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
import com.zurple.my.DBPageTest;
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
public class ZBOMarketingEmailPageDBTest extends DBPageTest{
	
	/**
	 * C47393	Verify that agent is able to receive C1 summary emails
	 */
	@Test
	public void testAlertSummaryEmailFromDB() {
		processEmailQueues(DBConstants.EmailTypeAlertSummary);
		dbVerification(DBConstants.EmailTypeAlertSummary);
	}
	
	/**
	 * C39879	Verify that campaigns should be sent out according to their order
	 */
	@Test
	public void testCampaignEmailFromDB() {
		processEmailQueues(DBConstants.EmailTypeCampaign);
		dbVerification(DBConstants.EmailTypeCampaign);
	}
	
	/**
	 * C39979	Verify that mass email is sent to leads successfully
	 */
	@Test
	public void testMassEmailFromDB() {
		processEmailQueues(DBConstants.EmailTypeMassEmails);
		dbVerification(DBConstants.EmailTypeMassEmails);
	}
	
	/**
	 * C47394	Verify that lead is able to receive AR emails
	 */
	@Test
	public void testAutoResponderEmailFromDB() {
		processEmailQueues(DBConstants.EmailTypeAutoResponder);
		dbVerification(DBConstants.EmailTypeAutoResponder);
	}
	
	/**
	 * Verify that PUNs emails should be received everyday at 6am
	 * 39944
	 */
	
	@Test
	public void testPUNSFromDB(){
		dbVerification(DBConstants.EmailTypePropertyUpdate);
	}
	
	@Test
	public void testCMAEmailFromDB(){
		processEmailQueues(DBConstants.EmailTypeCMAEmails);
		dbVerification(DBConstants.EmailTypeCMAEmails);
	}
	
	private void processEmailQueues(String pEmailType) {
		ZAProcessEmailQueuePageTest processEmailQueuePageTest = new ZAProcessEmailQueuePageTest();
		if(!getIsProd()) {
			processEmailQueuePageTest.testProcessEmailsQueues(pEmailType);
		}
	}
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}

	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}
}
