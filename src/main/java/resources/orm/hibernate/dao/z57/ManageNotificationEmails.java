/**
 * 
 */
package resources.orm.hibernate.dao.z57;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import resources.orm.hibernate.models.z57.Lead;
import resources.orm.hibernate.models.z57.NotificationEmails;
import resources.orm.hibernate.models.zurple.Admin;

/**
 * @author adar
 *
 */
public class ManageNotificationEmails {

	  private Session session;

	    public ManageNotificationEmails(Session session) {
	    	this.session = session;
//	    	new NotificationEmails().save();
	    }
	    
	    public NotificationEmails getNotificationId(String pEmail){

	    	NotificationEmails notification_emails = null;
	        try {
	        	 Query q = session.createQuery("FROM NotificationEmails WHERE email='"+pEmail+"'");
	        	   List notificationEmails_list = q.list();
	               for (Iterator iterator =
	            		   notificationEmails_list.iterator(); iterator.hasNext();){
	            	   notification_emails = (NotificationEmails) iterator.next();
	               }
	            Hibernate.initialize(notification_emails);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        return notification_emails;
	    }
	    
	    public List<NotificationEmails> getNotificationIdList(String pEmail,Integer pNumOfRecords){

	    	List<NotificationEmails> notification_emails = new ArrayList<NotificationEmails>();
	        try {
	        	 Query q = session.createQuery("FROM NotificationEmails WHERE email='"+pEmail+"' ORDER BY dateAdded DESC").setMaxResults(pNumOfRecords);
	        	 notification_emails = q.list();
	               
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        return notification_emails;
	    }
}
