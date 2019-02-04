package resources.orm.hibernate.dao.z57;



import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import resources.orm.hibernate.models.z57.Notifications;

public class ManageNotifications {
	
	   private Session session;

	    public ManageNotifications(Session session) {this.session = session;}
	    
	/* Method to  READ all the leads */
    public Notifications getAllNotifications(Integer pNotificationId ){
        Notifications notifications_list = null;
        try{
          
            notifications_list = (Notifications) session.get(Notifications.class, pNotificationId);
            Hibernate.initialize(notifications_list);
        }catch (HibernateException e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
		return notifications_list;
    }

}
