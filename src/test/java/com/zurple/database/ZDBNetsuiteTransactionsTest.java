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
import resources.orm.hibernate.models.zurple.NSTransaction;

/**
 * @author darrraqi
 *
 */
public class ZDBNetsuiteTransactionsTest extends DBPageTest{


	@Test
	public void testVerifyNetsuiteTransactions() {
		DBHelperMethods dbmethods = new DBHelperMethods(getEnvironment());
		String l_processed_date = getDateAfterSubtractingNumberOfDays(-1,"yyyy-MM-dd");
		List<NSTransaction> list_of_transactions = dbmethods.getListOfNSTransactionsByDate(l_processed_date);
		assertTrue(list_of_transactions.size()>0,"No records were found for date: "+l_processed_date+" in ns_transactions table");
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
