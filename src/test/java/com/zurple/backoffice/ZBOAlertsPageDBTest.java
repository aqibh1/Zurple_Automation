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
public class ZBOAlertsPageDBTest extends DBPageTest{
	@BeforeTest
	public void testProcessEmailsQueues() {
		processEmailQueues(DBConstants.EmailTypeAlerts);
	}	
	
	@Test
	public void testScheduleAlertFromDB() {
		dbVerification(DBConstants.AlertTypeSchedule);
	}
	
	@Test
	public void testAgentInquiryAlertFromDB() {
		dbVerification(DBConstants.AlertTypeAgentInquiry);
	}
	
	@Test
	public void testCheckinAlertFromDB() {
		dbVerification(DBConstants.AlertTypeCheckin);
	}
	
	@Test
	public void testCMAAlertFromDB() {
		dbVerification(DBConstants.AlertTypeCMA);
	}
	
	@Test
	public void testCMAFollowUpAlertFromDB() {
		dbVerification(DBConstants.AlertTypeCMAFollowUp);
	}
	
	@Test
	public void testCommunitySearchAlertFromDB() {
		dbVerification(DBConstants.AlertTypeCommunitySearch);
	}
	
	@Test
	public void testDownloadSellerAlertFromDB() {
		dbVerification(DBConstants.AlertTypeDownloadSeller);
	}
	
	@Test
	public void testFavoritesAlertFromDB() {
		dbVerification(DBConstants.AlertTypeFavorites);
	}
	
	@Test
	public void testHighActivityAlertFromDB() {
		dbVerification(DBConstants.AlertTypeHighActivity);
	}

	@Test
	public void testHighAppreciationFromDB() {
		dbVerification(DBConstants.AlertTypeHighAppreciation);
	}

	@Test
	public void testHighReturnAlertFromDB() {
		dbVerification(DBConstants.AlertTypeHighReturn);
	}

	@Test
	public void testHiighValueAlertFromDB() {
		dbVerification(DBConstants.AlertTypeHighValue);
	}

	@Test
	public void testImportedLeadAlertFromDB() {
		dbVerification(DBConstants.AlertTypeImportedLead);
	}
	
	@Test
	public void testNewSignUpLeadAlertFromDB() {
		dbVerification(DBConstants.AlertTypeNewSignup);
	}
	
	@Test
	public void testNextDayFollowUpLeadAlertFromDB() {
		dbVerification(DBConstants.AlertTypeNextDayFollowUp);
	}
	
	@Test
	public void testPOILeadAlertFromDB() {
		dbVerification(DBConstants.AlertTypePOI);
	}
	
	@Test
	public void testPreferredPropertyLeadAlertFromDB() {
		dbVerification(DBConstants.AlertTypePreferredProperty);
	}
	
	@Test
	public void testPropertyInquiryLeadAlertFromDB() {
		dbVerification(DBConstants.AlertTypePropertyInquiry);
	}

	
	@Test
	public void testSchoolReportAlertFromDB() {
		dbVerification(DBConstants.AlertTypeSchoolReports);
	}
	
	@Test
	public void testUserReplyAlertFromDB() {
		dbVerification(DBConstants.AlertTypeUserReply);
	}
	
	@Test
	public void testSellerInquiryAlertFromDB() {
		dbVerification(DBConstants.AlertTypeSellerInquiry);
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
