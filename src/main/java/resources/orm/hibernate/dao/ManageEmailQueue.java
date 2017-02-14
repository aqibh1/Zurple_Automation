package resources.orm.hibernate.dao;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import resources.orm.hibernate.models.EmailQueue;
import resources.orm.hibernate.models.Product;

public class ManageEmailQueue
{

    private SessionFactory factory;

    public ManageEmailQueue(SessionFactory factory) {this.factory = factory;}

    /* Method to  READ email queue entry by id */
    public EmailQueue getEmailQueueById( Integer email_queue_id ){
        Session session = factory.openSession();
        EmailQueue emailQueue = null;
        try {
            emailQueue = (EmailQueue) session.get(EmailQueue.class, email_queue_id);
            Hibernate.initialize(emailQueue);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return emailQueue;
    }

    /* Method to  READ last email queue entry */
    public EmailQueue getLastEmailQueue(){
        Session session = factory.openSession();
        EmailQueue emailQueue = null;
        try {
            Query q = session.createQuery("FROM EmailQueue ORDER BY email_queue_id DESC");
            q.setMaxResults(1);
            List leads = q.list();
            for (Iterator iterator =
                    leads.iterator(); iterator.hasNext();){
                    emailQueue = (EmailQueue) iterator.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return emailQueue;
    }

}
