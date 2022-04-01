/**
 * 
 */
package com.zurple.backoffice;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.zurple.my.DBPageTest;

import resources.AbstractPage;
import resources.utility.DBConstants;

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
