package com.zurple.my;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.zurple.backoffice.ZBOLeadCRMPage;
import com.zurple.backoffice.ZBOLeadPage;
import com.zurple.backoffice.ZBOLoginPage;

import resources.AbstractPageTest;
import resources.DBHelperMethods;
import resources.EnvironmentFactory;
import resources.orm.hibernate.models.zurple.Email;
import resources.utility.ActionHelper;
import resources.utility.AutomationLogger;

public abstract class DBPageTest extends AbstractPageTest
{
	public boolean isEmailSentToday(String sentDate){
		boolean isDateVerified = false;
		Date dateValue = null;
		if(!sentDate.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dateValue = sdf.parse(sentDate);
			} catch (ParseException e) {
				AutomationLogger.error("Unable to Parse sent date");
				e.printStackTrace();
			}
			SimpleDateFormat output = new SimpleDateFormat("YYYY/MM/dd");
			String puns_sentdate = output.format(dateValue).toString();
			String todaysDate = getTodaysDate("YYYY/MM/dd").toString();
			isDateVerified = puns_sentdate.equalsIgnoreCase(todaysDate);
		}
		return isDateVerified;
	}
	
	public void dbVerification(String emailType) {
		DBHelperMethods dbObject = new DBHelperMethods(getEnvironment());
		String lSentDateTime, lUserId = "";
		Email emailObject = dbObject.getEmailType(emailType);
		lSentDateTime = emailObject.getSendDatetime().toString();
		lUserId = emailObject.getUser().toString();
		boolean isSuccessful = isEmailSentToday(lSentDateTime);
		if(isSuccessful) {
			AutomationLogger.info("Today "+lSentDateTime+" "+emailType+"emails are sent: "+lUserId);
		}
		assertTrue(isSuccessful,"ALERT!! "+emailType+" Emails are not sent today..");
	}

}
