package com.zurple.database;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.zurple.my.DBPageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.orm.hibernate.models.zurple.NetSuiteSyncTasks;
import resources.orm.hibernate.models.zurple.NetsuiteSyncQueue;
import resources.utility.AutomationLogger;

/**
 * 
 * @author habibaaq
 *
 */

public class ZDBNetsuiteAPSyncingTest extends DBPageTest{
	
	@Test
	public void testVerifyNetsuiteAPSyncingIsWorking() {
		DBHelperMethods dbmethods = new DBHelperMethods(getEnvironment());
		String l_processed_date = getDateAfterSubtractingNumberOfDays(-1,"yyyy-MM-dd");
		List<NetsuiteSyncQueue> list_of_transactions = dbmethods.getListOfFailedNetsuiteSyncQueueItems(l_processed_date);
		boolean isNumberOfFailedTranGreaterThanThree = list_of_transactions.size()>=3;
		if(isNumberOfFailedTranGreaterThanThree) {
			for(NetsuiteSyncQueue nssyncObject: list_of_transactions) {
				AutomationLogger.error("NetSuite Sync Queue JOB ID :: "+nssyncObject.getJob_id());
				AutomationLogger.error("PayLoad :: "+nssyncObject.getPayload());
				AutomationLogger.error("ACTION :: "+nssyncObject.getAction());
			}
		}
		assertTrue(!isNumberOfFailedTranGreaterThanThree,"Failed transactions count is more than 3 for date: "+l_processed_date+" in netsuite_sync_queue table");
	}

	@Override
	public void clearPage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AbstractPage getPage() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
