package resources.orm.hibernate.dao.zurple;

import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import resources.orm.hibernate.models.zurple.EmailQueue;

public class ManageEmailQueue
{

    private Session session;

    public ManageEmailQueue(Session session) {this.session = session;}

    /* Method to  READ email queue entry by id */
    public EmailQueue getEmailQueueById( Integer email_queue_id ){

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
