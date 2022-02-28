package com.zurple.database;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.zurple.my.DBPageTest;

import resources.AbstractPage;
import resources.DBHelperMethods;
import resources.orm.hibernate.models.zurple.User;
import resources.utility.AutomationLogger;

/**
 * 
 * @author habibaaq
 *
 */

public class ZDBAdminIdTest extends DBPageTest {

	@Test
	public void testVerifyAdminIdFromUsersTable() {
		DBHelperMethods dbmethods = new DBHelperMethods(getEnvironment());
		String l_create_date = getDateAfterSubtractingNumberOfDays(-10,"yyyy-MM-dd");
		List<User> list_of_users_with_adminid_0 = dbmethods.getListOfUsersWithAdminId0(l_create_date);
		boolean isNumberOfFailedUsersGreaterThanOne = list_of_users_with_adminid_0.size()>=1;
		if(isNumberOfFailedUsersGreaterThanOne) {
			for(User usersObject: list_of_users_with_adminid_0) {
				AutomationLogger.error("LEAD ID for user is :: "+usersObject.getLeadId().getId());
				AutomationLogger.error("SITE Id :: "+usersObject.getSiteId().getId());
				AutomationLogger.error("TRAFFIC Source :: "+usersObject.getTrafficSource());
				AutomationLogger.error("Create_Date_Time :: "+usersObject.getCreateDatetime());
			}
		}
		assertTrue(!isNumberOfFailedUsersGreaterThanOne,"ATTENTION!! User Is Created with ADMIN_ID 0 - Total users are "+ list_of_users_with_adminid_0.size());
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
	
	@AfterTest
	public void closeBrowser() {
		closeCurrentBrowser();
	}
	
}
