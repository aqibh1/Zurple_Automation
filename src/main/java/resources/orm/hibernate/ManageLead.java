package resources.orm.hibernate;

import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ManageLead {
    private static SessionFactory factory;
    public static void main(String[] args) {

    }
    /* Method to CREATE an employee in the database */
    public Integer addEmployee(String fname, String lname, String email){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer leadID = null;
        try{
            tx = session.beginTransaction();
            Lead employee = new Lead(lname, email);
            leadID = (Integer) session.save(employee);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return leadID;
    }
    /* Method to  READ all the employees */
    public void listLeads( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List leads = session.createQuery("FROM Lead").list();
            for (Iterator iterator =
                    leads.iterator(); iterator.hasNext();){
                Lead lead = (Lead) iterator.next();
                System.out.print("First Name: " + lead.getLeadName());
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    /* Method to UPDATE salary for an employee */
    public void updateLead(Integer LeadID, String email ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            Lead lead =
                    (Lead)session.get(Lead.class, LeadID);
            lead.setLeadEmail( email );
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
