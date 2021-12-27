package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.models.zurple.Email;
import resources.orm.hibernate.models.zurple.User;
import resources.orm.hibernate.models.zurple.UserAlert;
import resources.utility.AutomationLogger;

public class ManageUserAlert
{

    private Session session;

    public ManageUserAlert(Session session) {this.session = session;}

    /* Method to  READ user activity by id */
    public UserAlert getUserAlert( Integer user_alert_id ){

        UserAlert user_alert = null;
        try {
            user_alert = (UserAlert) session.get(UserAlert.class, user_alert_id);
            Hibernate.initialize(user_alert);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user_alert;
    }

    /* Method to save user alert to database */
    public Integer updateUserAlert(UserAlert user_alert){

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(user_alert);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        
        return user_alert.getId();
    }
    
    @SuppressWarnings("unchecked")
	public UserAlert getAlertByType(Integer pAlertType){
    	List<UserAlert> alerts = new ArrayList<UserAlert>();
    	Transaction tx = null;
         try{
//SELECT alert_name,user_id,user_alert_triggered FROM alert_rules as ar inner JOIN user_alert as ua 
 //       	 ON ar.arule_id=ua.alert_rule_id where ar.arule_id=4 ORDER BY user_alert_triggered desc

            // tx = session.beginTransaction();
        	// Query query = session.createQuery("FROM UserAlert u inner join u.alertrule where arule_id=:alertRuleId"+" Order by user_alert_triggered desc").setParameter("alertRuleId", 4).setMaxResults(1);
        	alerts = session.createQuery("FROM UserAlert where alert_rule_id=:alertRuleId Order by user_alert_triggered desc").setParameter("alertRuleId", pAlertType).setMaxResults(1).list();
        	 AutomationLogger.info("QUERY RESULTS:"+alerts.size());
             tx.commit();
         }catch (HibernateException e) {
             if (tx!=null) tx.rollback();
             e.printStackTrace();
         }finally {
        	 if (session != null && session.isOpen()) {
                 session.close();
             }
             return alerts.get(0);
         }
         
    }
        
}
