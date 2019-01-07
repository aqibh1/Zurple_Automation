package resources.orm.hibernate.dao.zurple;

import org.hibernate.*;
import resources.orm.hibernate.models.zurple.SessionAnonymous;

import java.util.Iterator;
import java.util.List;

public class ManageSessionAnonymous
{

    private Session session;

    public ManageSessionAnonymous(Session session) {this.session = session;}

    /* Method to  READ session user by id */
    public SessionAnonymous getSessionAnonymous( String session_id ){

        SessionAnonymous session_anonymous = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM "+SessionAnonymous.class.getName()+" WHERE session_id='"+session_id+"'");
            List sessions = q.list();
            for (Iterator iterator =
                sessions.iterator(); iterator.hasNext();){
                session_anonymous = (SessionAnonymous) iterator.next();
            }

            Hibernate.initialize(session_anonymous);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return session_anonymous;
    }
    
}
