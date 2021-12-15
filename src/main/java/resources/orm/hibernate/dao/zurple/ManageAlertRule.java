package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.models.zurple.AlertRule;
import resources.orm.hibernate.models.zurple.Email;
import resources.orm.hibernate.models.zurple.User;
import resources.orm.hibernate.models.zurple.UserAlert;
import resources.utility.AutomationLogger;

public class ManageAlertRule
{
    private Session session;

    public ManageAlertRule(Session session) {this.session = session;}
    
    @SuppressWarnings("unchecked")
	public AlertRule getAlertNameById(Integer pAlertId){
    	List<AlertRule> alerts = new ArrayList<AlertRule>();
    	Transaction tx = null;
         try{
        	 alerts = session.createQuery("FROM AlertRule where arule_id=:alertRuleId").setParameter("alertRuleId", pAlertId).setMaxResults(1).list();
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
