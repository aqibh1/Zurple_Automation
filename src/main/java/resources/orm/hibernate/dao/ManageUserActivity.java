package resources.orm.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import resources.orm.hibernate.models.User;
import resources.orm.hibernate.models.UserActivity;

public class ManageUserActivity
{

    private Session session;

    public ManageUserActivity(Session session) {this.session = session;}

    /* Method to  READ user activity by id */
    public UserActivity getUserActivity( Integer user_activity_id ){

        UserActivity user_activity = null;
        try {
            user_activity = (UserActivity) session.get(UserActivity.class, user_activity_id);
            Hibernate.initialize(user_activity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user_activity;
    }

    /* Method to save user activity to database */
    public Integer updateUserActivity(
            UserActivity user_activity
    ){

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(user_activity);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        
        return user_activity.getId();
    }
    
}
