package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.models.zurple.Admin;
import resources.orm.hibernate.models.zurple.Lead;
import resources.utility.AutomationLogger;

public class ManageLead {

    private Session session;

    public ManageLead(Session session) {this.session = session;}

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
            lead.setCreateDatetime(new Date());
            lead.setUpdateDatetime(new Date());
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
    
    public Lead getLeadByEmail(String pLeadEmail ){
    	List<Lead> leads = new ArrayList<>();
    	 Transaction tx = null;
         try{
//             tx = session.beginTransaction();
             leads = session.createQuery("FROM Lead WHERE email='"+pLeadEmail+"'").list();
             AutomationLogger.info("QUERY RESULTS:"+leads.size());
             tx.commit();
         }catch (HibernateException e) {
             if (tx!=null) tx.rollback();
             e.printStackTrace();
         }finally {
             session.close();
             return leads.get(0);
         }
    }
    /*
        Method returns list of hot behavior flags by lead id
     */
    public List<String> getFlags( Integer lead_id ){

        List<String> flagList = new ArrayList<String>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            Query query = session.createSQLQuery("select "
                    + " cast(ar.alert_type as CHAR(50)) as  alert_type "
                    + " from "
                    + " user_alert ua join "
                    + " alert_rules ar on ar.arule_id=ua.alert_rule_id "
                    + " where "
                    + " ar.alert_type in ('activity','return','price','preferred','favorite', 'favorite_high_appreciation') and "
                    + " ua.user_id = "+Integer.toString(lead_id)
                    + " group by ar.alert_type "
            );
            List<String> flags = query.list();

            for(String flagCode: flags){
                if(flagCode.equals("activity")) {
                    flagList.add("browsing");
                }
                if(flagCode.equals("return")) {
                    flagList.add("return");
                }
                if(flagCode.equals("price")) {
                    flagList.add("expensive");
                }
                if(flagCode.equals("preferred")) {
                    flagList.add("preferred");
                }
                if(flagCode.equals("favorite")) {
                    flagList.add("favorites");
                }
                if(flagCode.equals("favorite_high_appreciation")) {
                    flagList.add("favorites");
                }
            }
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return flagList;
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
