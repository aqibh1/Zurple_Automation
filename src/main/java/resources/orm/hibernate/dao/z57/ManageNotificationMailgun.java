/**
 * 
 */
package resources.orm.hibernate.dao.z57;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import resources.orm.hibernate.models.z57.NotificationMailgun;

/**
 * @author adar
 *
 */
public class ManageNotificationMailgun {
	
	  private Session session;

	    public ManageNotificationMailgun(Session session) {
	    	this.session = session;
//	    	new NotificationMailgun().save();
	    	}
	    
	    public NotificationMailgun getMailgunStatus(Integer pNotificationId){

	    	NotificationMailgun notification_mailgun = null;
	        try {
	        	 Query q = session.createQuery("FROM NotificationMailgun WHERE notficationId='"+pNotificationId+"'");
	        	   List notification_mailgun_list = q.list();
	               for (Iterator iterator =
	            		   notification_mailgun_list.iterator(); iterator.hasNext();){
	            	   notification_mailgun = (NotificationMailgun) iterator.next();
	               }
	            Hibernate.initialize(notification_mailgun);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        return notification_mailgun;
	    }

	    @SuppressWarnings("unchecked")
		public List<NotificationMailgun> getNotificationMailgunByDate(){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			List<NotificationMailgun> notificationMailgunList = new ArrayList<NotificationMailgun>();
			try {
				Query q = session.createQuery("FROM NotificationMailgun WHERE dateAdded>='"+date+"' AND status='1'");
				notificationMailgunList = q.list();
				Hibernate.initialize(notificationMailgunList);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
			return notificationMailgunList;
		}
}
