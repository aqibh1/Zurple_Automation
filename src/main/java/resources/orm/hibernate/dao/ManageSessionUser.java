package resources.orm.hibernate.dao;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import resources.orm.hibernate.models.SessionUser;
import resources.orm.hibernate.models.UserActivity;

public class ManageSessionUser
{

    private Session session;

    public ManageSessionUser(Session session) {this.session = session;}

    /* Method to  READ session user by id */
    public SessionUser getSessionUser( Integer session_user_id ){

        SessionUser session_user = null;
        try {
            session_user = (SessionUser) session.get(SessionUser.class, session_user_id);
            Hibernate.initialize(session_user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return session_user;
    }

    /* Method to save session user to database */
    public Integer updateSessionUser(
            SessionUser session_user
    ){

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(session_user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        
        return session_user.getId();
        
    }
    
}
