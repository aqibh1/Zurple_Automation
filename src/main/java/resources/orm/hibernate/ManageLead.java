package resources.orm.hibernate;

import java.util.List;
import java.util.Iterator;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;

public class ManageLead {

    private SessionFactory factory;

    public ManageLead(SessionFactory factory) {this.factory = factory;}

    /* Method to CREATE an employee in the database */
    public Integer addLead(
            String email,
            String first_name,
            String last_name,
            String phone,
            String cell,
            String memo,
            Admin admin
    ){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer leadID = null;
        try{
            tx = session.beginTransaction();
            Lead lead = new Lead(
                    email,
                    first_name,
                    last_name,
                    phone,
                    cell,
                    memo,
                    admin
            );
            leadID = (Integer) session.save(lead);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return leadID;
    }
    /* Method to  READ all the leads */
    public void listLeads( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List leads = session.createQuery("FROM Lead").list();
            for (Iterator iterator =
                    leads.iterator(); iterator.hasNext();){
                Lead lead = (Lead) iterator.next();
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to  READ lead by id */
    public Lead getLead( Integer lead_id ){
        Session session = factory.openSession();
        Lead lead = null;
        try {
            lead = (Lead) session.get(Lead.class, lead_id);
            Hibernate.initialize(lead);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return lead;
    }
    /* Method to UPDATE salary for an employee */
    public void updateLead(Integer LeadID, String email ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Lead lead =
                    (Lead)session.get(Lead.class, LeadID);
            lead.setEmail( email );
            session.update(lead);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to DELETE an employee from the records */
    public void deleteLead(Integer LeadID){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Lead lead =
                    (Lead)session.get(Lead.class, LeadID);
            session.delete(lead);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
