package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.models.zurple.SessionUser;
import resources.orm.hibernate.models.zurple.User;

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

    /* Method to  READ session by user */
    public List<SessionUser> getSessionByUser( User user ){

        List<SessionUser> sessions = new ArrayList<SessionUser>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM SessionUser WHERE user_id="+user.getId()).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                SessionUser sess = (SessionUser) iterator.next();
                sessions.add(sess);
            }
            tx.commit();

        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return sessions;
        
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
