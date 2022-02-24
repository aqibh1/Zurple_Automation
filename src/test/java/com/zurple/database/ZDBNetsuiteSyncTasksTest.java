/**
 * 
 */
package com.zurple.database;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.Test;

import com.zurple.my.DBPageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.orm.hibernate.models.zurple.NetSuiteSyncTasks;
import resources.utility.AutomationLogger;

/**
 * @author darrraqi
 *
 */
public class ZDBNetsuiteSyncTasksTest extends DBPageTest{


	@Test
	public void testVerifyNetsuiteSyncTaskTransactionsAreWorking() {
		DBHelperMethods dbmethods = new DBHelperMethods(getEnvironment());
		String l_processed_date = getDateAfterSubtractingNumberOfDays(-1,"yyyy-MM-dd");
		List<NetSuiteSyncTasks> list_of_transactions = dbmethods.getListOfFailedNetsuiteSyncTaskTransactions(l_processed_date);
		boolean isNumberOfFailedTranGreaterThanThree = list_of_transactions.size()>=3;
		if(isNumberOfFailedTranGreaterThanThree) {
			for(NetSuiteSyncTasks nssyncObject: list_of_transactions) {
				AutomationLogger.error("NetSuite Sync Task ID :: "+nssyncObject.getNetsuite_sync_task_id());
				AutomationLogger.error("ADMIN ID :: "+nssyncObject.getAdmin_id());
				AutomationLogger.error("ACTION :: "+nssyncObject.getAction());
			}
		}
		assertTrue(!isNumberOfFailedTranGreaterThanThree,"Failed transactions count is more than 3 for date: "+l_processed_date+" in netsuite_sync_tasks table");
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
