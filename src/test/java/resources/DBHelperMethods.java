package resources;

import java.util.List;
import resources.orm.hibernate.models.AbstractLead;
import resources.orm.hibernate.models.z57.ListingImages;
import resources.orm.hibernate.models.z57.NotificationEmails;
import resources.orm.hibernate.models.z57.NotificationMailgun;
import resources.orm.hibernate.models.z57.Notifications;
import resources.utility.AutomationLogger;

public class DBHelperMethods {
	private TestEnvironment testEnvironment;

	public DBHelperMethods(TestEnvironment pTestEnvironment){
		testEnvironment = pTestEnvironment;

	}

	public Notifications getNotifications(Integer pNotificationId) {
		Notifications notification_object = testEnvironment.getNotificationObject(pNotificationId);
		System.out.println(notification_object.getEmail_subject()+"  "+notification_object.getSentDate());
		return notification_object;
	}

	public int getMailgunStatus(Integer pNotificationId) {
		int status=0;
		try {
			AutomationLogger.info("Getting mailgun status from DB for the notification id -> "+pNotificationId);
			NotificationMailgun notification_mailgun_object = testEnvironment.getNotificationMailgunObject(pNotificationId);
			System.out.println(notification_mailgun_object.getStatus());
			status = notification_mailgun_object.getStatus();
		}catch(Exception ex) {
			AutomationLogger.error("No Mailgun status found for Notification ID -> "+pNotificationId);
			AutomationLogger.error(ex.toString());		
		}
		return status;
	}
	public Integer getNotificationId(String pEmail) {
		try {
			AutomationLogger.info("Getting Notification ID from DB for the email -> "+pEmail);
			NotificationEmails not_email_obj = testEnvironment.getNotificationEmailsObject(pEmail);
			System.out.println(not_email_obj.getNotificationId());
			AutomationLogger.info("Notification ID from DB for the email -> "+pEmail+" is"+not_email_obj.getNotificationId());
			return not_email_obj.getNotificationId();
		}catch(Exception ex) {
			AutomationLogger.error("Notification ID from DB not found for the email -> "+pEmail);
			AutomationLogger.error(ex.toString());
			return 0;

		}
	}
	
	public boolean verifyEmailIsSent(String pAgentEmail, String pEmailSubjectToVerify) {
		boolean status=false;
		Integer notificationId=getNotificationId(pAgentEmail);
		if(notificationId>0) {
			Notifications notificationsObj = getNotifications(notificationId);
			status=notificationsObj.getEmail_subject().equalsIgnoreCase(pEmailSubjectToVerify)?true:false;
			
		}
		return status;
	}
	
	public boolean verifyLeadInDB(String pEmailToVeirfy,Integer pLeadId) {
		
         //Checking created lead source
         //Checking DB record body
         AbstractLead newLead = testEnvironment.getLeadObject(pLeadId);
         return pEmailToVeirfy.equalsIgnoreCase(newLead.getEmail());
   }
	
	public List<ListingImages> getListingImages(Integer pListingId){
		try {
			AutomationLogger.info("Getting images for the listing id -> "+pListingId);
			List<ListingImages> listOfListingImages= testEnvironment.getListOfListingImages(pListingId);
			return listOfListingImages;
		}catch(Exception ex) {
			AutomationLogger.error("No Images exist for the Listing Id -> "+pListingId);
			AutomationLogger.error(ex.toString());
			return null;

		}
	}
}
