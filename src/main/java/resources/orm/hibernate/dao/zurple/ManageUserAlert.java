package resources.orm.hibernate.dao.zurple;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import resources.orm.hibernate.models.zurple.UserAlert;

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
    public Integer updateUserAlert(
            UserAlert user_alert
    ){

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
        
}
