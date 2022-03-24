package resources;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import resources.orm.hibernate.models.AbstractLead;
import resources.orm.hibernate.models.pp.Posts;
import resources.orm.hibernate.models.z57.IdxLeadSearches;
import resources.orm.hibernate.models.z57.ListingImages;
import resources.orm.hibernate.models.z57.NotificationEmails;
import resources.orm.hibernate.models.z57.NotificationMailgun;
import resources.orm.hibernate.models.z57.Notifications;
import resources.orm.hibernate.models.z57.Sites;
import resources.orm.hibernate.models.zurple.Admin;
import resources.orm.hibernate.models.zurple.AdminDashboardStats;
import resources.orm.hibernate.models.zurple.AlertRule;
import resources.orm.hibernate.models.zurple.Email;
import resources.orm.hibernate.models.zurple.NSTransaction;
import resources.orm.hibernate.models.zurple.NetSuiteSyncTasks;
import resources.orm.hibernate.models.zurple.NetsuiteSyncQueue;
import resources.orm.hibernate.models.zurple.User;
import resources.orm.hibernate.models.zurple.UserAlert;
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

	public List<Integer> getNotificationsList(String pEmail) {

		List<Integer> notificationIdList = new ArrayList<>();

		try {
			AutomationLogger.info("Getting Notification ID from DB for the email -> "+pEmail);
			List<NotificationEmails> not_email_obj_list = testEnvironment.getNotificationEmailsObjectsList(pEmail);

			for (NotificationEmails not_email_obj: not_email_obj_list) {
				System.out.println(not_email_obj.getNotificationId());
				AutomationLogger.info("Notification ID from DB for the email -> "+pEmail+" is"+not_email_obj.getNotificationId());
				notificationIdList.add(not_email_obj.getNotificationId());
			}
		}catch(Exception ex) {
			AutomationLogger.error("Notification ID from DB not found for the email -> "+pEmail);
			AutomationLogger.error(ex.toString());
		}

		return notificationIdList;
	}
	
	public boolean verifyEmailIsSent(String pAgentEmail, String pEmailSubjectToVerify) {

		List<Integer> notificationsList=getNotificationsList(pAgentEmail);
		for (Integer notificationId: notificationsList) {
			Notifications notificationsObj = getNotifications(notificationId);
			if ( notificationsObj.getEmail_subject().equalsIgnoreCase(pEmailSubjectToVerify) )
			{
				return true;
			}
		}
		return false;
	}

	public boolean verifySearchIsSaved(String pLeadEmail, String pSearchTitle) {
		try {
			AutomationLogger.info("Lead ID: "+pLeadEmail);
			AbstractLead lead = testEnvironment.getLeadObject(pLeadEmail);
			IdxLeadSearches idx_lead_search = testEnvironment.getIdxLeadSavedSearch(lead.getId(), pSearchTitle);
			return pSearchTitle.equalsIgnoreCase(idx_lead_search.getTitle());
		}
		catch(NullPointerException ex) {

			AutomationLogger.error("Saved Search Object is null for Lead ID: "+pLeadEmail+" Title:"+pSearchTitle);
			return false;
		}
	}

	public boolean verifyLeadInDB(String pEmailToVeirfy,Integer pLeadId) {
		//Checking created lead source
		//Checking DB record body
		try {
			AutomationLogger.info("Lead ID: "+pLeadId);
			AbstractLead newLead = testEnvironment.getLeadObject(pLeadId);
			return pEmailToVeirfy.equalsIgnoreCase(newLead.getEmail());
		}
		catch(NullPointerException ex) {

			AutomationLogger.error("Lead Object is null for Lead ID: "+pLeadId);
			return false;
		}
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
	
	public boolean verifyLeadByEmailInDB(String pEmailToVeirfy) {

		//Checking created lead source
		//Checking DB record body
		try {
			AbstractLead newLead = testEnvironment.getLeadObject(pEmailToVeirfy);
			return pEmailToVeirfy.equalsIgnoreCase(newLead.getEmail());
		}
		catch(Exception ex) {
			AutomationLogger.error("No Lead found in Lead Table for email ->"+pEmailToVeirfy);
			return false;
		}
	}
	
	public boolean verifyEmailIsSentToAgent(String pAgentEmail,String pLeadEmail) {
		boolean result = false;
		try {
			List<NotificationEmails> notificationEmailsList = new ArrayList<NotificationEmails>();
			notificationEmailsList = testEnvironment.getNotificationEmailsObject(pAgentEmail, 100);
			for(NotificationEmails notficationEmails: notificationEmailsList) {
				int notification_id = notficationEmails.getNotificationId();
				Notifications notification = getNotifications(notification_id);
				String lEmail_Body = notification.getEmail_body();
//				System.out.println(lEmail_Body);
				if(lEmail_Body.contains(pLeadEmail)) {
					result = true;
					break;
				}
			}
		}catch (Exception ex) {
			result = false;
			AutomationLogger.error("Notification Emails List not found for Agent Email");
			AutomationLogger.error(ex.toString());
		}
		return result;
	}
	
	public boolean verifyEmailIsSentToAgent(String pAgentEmail,String pLeadEmail, String pSubjectToVerify) {
		boolean result = false;
		try {
			List<NotificationEmails> notificationEmailsList = new ArrayList<NotificationEmails>();
			notificationEmailsList = testEnvironment.getNotificationEmailsObject(pAgentEmail, 150);
			for(NotificationEmails notficationEmails: notificationEmailsList) {
				int notification_id = notficationEmails.getNotificationId();
				Notifications notification = getNotifications(notification_id);
				String lEmail_Subject =notification.getEmail_subject();
				String lEmail_Body = notification.getEmail_body();
				if(lEmail_Subject!=null && lEmail_Subject.equalsIgnoreCase(pSubjectToVerify)) {
					//				System.out.println(lEmail_Body);
					if(lEmail_Body.contains(pLeadEmail)) {
						result = true;
						break;
					}
				}
			}
		}catch (Exception ex) {
			result = false;
			AutomationLogger.error("Notification Emails List not found for Agent Email");
			AutomationLogger.error(ex.toString());
		}
		return result;
	}
	
	public boolean verifyEmailIsSentToLead(String pLeadEmail, String pSubjectToVerify) {
		boolean result = false;
		try {
			List<NotificationEmails> notificationEmailsList = new ArrayList<NotificationEmails>();
			notificationEmailsList = testEnvironment.getNotificationEmailsObject(pLeadEmail, 100);
			for(NotificationEmails notficationEmails: notificationEmailsList) {
				int notification_id = notficationEmails.getNotificationId();
				Notifications notification = getNotifications(notification_id);
				String lEmail_Subject =notification.getEmail_subject();
//				String lEmail_Body = notification.getEmail_body();
				if(lEmail_Subject.equalsIgnoreCase(pSubjectToVerify)) {
					//				System.out.println(lEmail_Body);
					//					if(lEmail_Body.contains(pLeadEmail)) {
					result = true;
					break;
					//					}
				}
			}
		}catch (Exception ex) {
			result = false;
			AutomationLogger.error("Notification Emails List not found for Agent Email");
			AutomationLogger.error(ex.toString());
		}
		return result;
	}
	public resources.orm.hibernate.models.zurple.Lead getLeadObject(String pEmailToVeirfy) {
		//Fetching Lead object by Email
		try {
			return testEnvironment.getNewLeadsObject(pEmailToVeirfy);
		}
		catch(Exception ex) {
			AutomationLogger.error("No Lead found in Lead Table for email ->"+pEmailToVeirfy);
			ex.printStackTrace();
			return null;
		}
	}
	public Email getEmailType(String pEmailToVerify) {
		//Fetching Email Type
		try {
			return testEnvironment.getNewEmailTypeObject(pEmailToVerify);
		}
		catch(Exception ex) {
			AutomationLogger.error("No Lead found in Lead Table for email ->"+pEmailToVerify);
			ex.printStackTrace();
			return null;
		}
	}
	
	public UserAlert getAlertType(Integer pAlertRuleToVerify) {
		try {
			return testEnvironment.getUserAlertObject(pAlertRuleToVerify);
		}
		catch(Exception ex) {
			AutomationLogger.error("No Lead found in User Alerts Table for alert -> "+pAlertRuleToVerify);
			ex.printStackTrace();
			return null;
		}
	}
	
	public AlertRule getAlertRuleType(Integer pAlertRuleToVerify) {
		try {
			return testEnvironment.getAlertRuleObject(pAlertRuleToVerify);
		}
		catch(Exception ex) {
			AutomationLogger.error("No Lead found in Alert Rule Table for alert -> "+pAlertRuleToVerify);
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean isWebSiteHTTPSEnables(String pWebSite) {
		boolean isHttpsEnabled = false;
		try {
			Sites siteObj = testEnvironment.getSitesByUrl(pWebSite);
			if(siteObj!=null && siteObj.getHttpsEnabled()!=0) {
				isHttpsEnabled = true;
			}
		}catch(Exception ex) {
			AutomationLogger.info("No site found for the URL: "+pWebSite);
		}
		return isHttpsEnabled;
	}
	
	//Social Posts helper methods
	public boolean isPostSuccessful(Posts pPostsObject) {
		boolean isSuccess = false;
		AutomationLogger.info("Verifying Post is successful or not");
		if(pPostsObject.getStatus()!=null && pPostsObject.getStatus()==1) {
			if(pPostsObject.getErrors()==null) {
				AutomationLogger.info("Post is successful for ID "+pPostsObject.getPostID());
				isSuccess = isStatusPostedOnTime(pPostsObject);
			}else {
				AutomationLogger.error("Post is unsuccessful for ID "+pPostsObject.getPostID());
				AutomationLogger.error("Error Message : "+pPostsObject.getErrors());
			}
		}
		return isSuccess;
	}
	
	public boolean isStatusPostedOnTime(Posts pPostsObject) {
		LocalDate lCurrentDate = LocalDate.now();
		LocalDate lPostDate = LocalDate.parse(pPostsObject.getDateAdded().toString().split(" ")[0]);
		boolean isDateVerified = lCurrentDate.equals(lPostDate);
		if(!isDateVerified) {
			AutomationLogger.error("Unable to verify post date..");
			AutomationLogger.error("Current Date :: "+lCurrentDate);
			AutomationLogger.error("Post Date :: "+lPostDate);
		}
		return isDateVerified;
	}
	
//	public boolean isEmailSentToLead(String pLeadEmail, String pEmailSubject) {
//		resources.orm.hibernate.models.zurple.Lead leadObject = (resources.orm.hibernate.models.zurple.Lead) testEnvironment.getLeadObject(pLeadEmail);
//		
//		 return leadObject.getEmail()!=null;
//	}
//	public List<Notifications> getListOfNotfications(){
//		return testEnvironment.getListOfNotificationObject();
//	}
	public List<Posts> getPostsByListingId(Integer pListingId){
		return testEnvironment.getPostsByListingId(pListingId);
	}
	public List<Posts> getTwitterAcceleratorLink(){
		return testEnvironment.getTwitterAcceleratorLinkPosts();
	}
	
	public List<Posts> getFacebookAcceleratorLink(){
		return testEnvironment.getFacebookAcceleratorLinkPosts();
	}
	
	public List<Posts> getFacebookAcceleratorVideo(){
		return testEnvironment.getFacebookAcceleratorVideoPosts();
	}

	public List<Posts> getYoutubeAcceleratorVideo() {
		return testEnvironment.getYoutubeAcceleratorVideoPosts();
	}
	
	public List<NotificationMailgun> getMailgunNotifications() {
		return testEnvironment.getMailgunNotifications();
	}
	public Integer getZurpleLeadId(String pUserName) {
		return testEnvironment.getUserByUserName(pUserName).getId();
	}
	public AdminDashboardStats getAdminStatsByAdminId(int pAdminId) {
		return testEnvironment.getAdminDashBoardStats(pAdminId);
	}
	public List<Admin> getListOfSubAdmins(int pAdminId) {
		return testEnvironment.getListOfSubAdmins(pAdminId);
	}
	public List<User> getListOfUsers(int pAdminId, String pSource, Date pCreateTime) {
		return testEnvironment.getListOfUsersByLeadSource(pAdminId, pSource, pCreateTime);
	}
	public List<NSTransaction> getListOfNSTransactionsByDate(String pCreateDateTime){
		return testEnvironment.getListOfNSTransactionsByDate(pCreateDateTime);
	}
	public List<NetSuiteSyncTasks> getListOfFailedNetsuiteSyncTaskTransactions(String pDateProcessed){
		return testEnvironment.getListOfFailedNetsuiteSyncTaskTransactions(pDateProcessed);
	}
	public List<NetsuiteSyncQueue> getListOfFailedNetsuiteSyncQueueItems(String pDateProcessed){
		return testEnvironment.getListOfFailedNetsuiteSyncQueueItems(pDateProcessed);
	}
	public List<User> getListOfUsersWithAdminId0(String pDateCreated){
		return testEnvironment.getListOfUsersWithAdminId0(pDateCreated);
	}
	public Email getEmailBySubject(String pEmailSubject){
		return testEnvironment.getEmailBySubject(pEmailSubject);
	}
	public List<Admin> getAllAPAdminsWithPhone(String pLastName){
		return testEnvironment.getAllAPAdminsWithPhone(pLastName);
	}
	
}
