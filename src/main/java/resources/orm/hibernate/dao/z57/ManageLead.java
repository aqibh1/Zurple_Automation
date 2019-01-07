package resources.orm.hibernate.dao.z57;

import org.hibernate.*;
import resources.orm.hibernate.models.z57.Lead;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ManageLead {

    private Session session;

    public ManageLead(Session session) {this.session = session;}

    /* Method to CREATE an employee in the database */
    public Integer addLead(
            String email,
            String name_full,
            String phone
    ){

        Transaction tx = null;
        Integer leadID = null;
        try{
            tx = session.beginTransaction();
            Lead lead = new Lead(
                    email,
                    name_full,
                    phone
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

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List leads = session.createQuery("FROM Leads").list();
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
