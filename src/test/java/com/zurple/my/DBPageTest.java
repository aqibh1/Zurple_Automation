package com.zurple.my;

import static org.testng.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import resources.AbstractPageTest;
import resources.DBHelperMethods;
import resources.orm.hibernate.models.zurple.AlertRule;
import resources.orm.hibernate.models.zurple.Email;
import resources.orm.hibernate.models.zurple.UserAlert;
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
			String yesterdaysDate = getYesterdaysDate("YYYY/MM/dd").toString();
			isDateVerified = puns_sentdate.equalsIgnoreCase(todaysDate) || puns_sentdate.equalsIgnoreCase(yesterdaysDate);
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
	
	public void dbVerification(Integer alertType) {
		DBHelperMethods dbObject = new DBHelperMethods(getEnvironment());
		String lUserAlertData, lUserId, lAlertName, lAlertTrigger = "";
		UserAlert alertObject = dbObject.getAlertType(alertType);
		lAlertTrigger = alertObject.getUserAlertTriggered().toString();
		lUserId = alertObject.getUser().toString();
		lUserAlertData = alertObject.getUserAlertData().toString();
		lUserAlertData = lUserAlertData.split(",")[0]+"}";
		AlertRule alertRuleObj = dbObject.getAlertRuleType(alertType);
		lAlertName = alertRuleObj.getAlertName().toString();
		boolean isSuccessful = isEmailSentToday(lAlertTrigger);
		if(isSuccessful) {
			AutomationLogger.info(lAlertName+" alert is Triggered on: "+lAlertTrigger+" with id: "+lUserAlertData+" for user_id: "+lUserId);
		}
		assertTrue(isSuccessful,"ALERT!! Alert_Type "+lAlertName+" Emails are not sent today. They were last sent on: "+lAlertTrigger);
	}
	
}
