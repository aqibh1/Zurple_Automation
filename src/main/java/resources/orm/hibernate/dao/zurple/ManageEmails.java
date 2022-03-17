package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.models.zurple.Email;
import resources.orm.hibernate.models.zurple.Lead;
import resources.utility.AutomationLogger;

public class ManageEmails {

    private Session session;

    public ManageEmails(Session session) {this.session = session;}
//    /* Method to CREATE an employee in the database */
//    public Integer emailType(
//            String email,
//            String first_name,
//            String last_name,
//            String phone,
//            String cell,
//            String memo,
//            Admin admin
//    ){

        Transaction tx = null;
//        Integer leadID = null;
//        try{
//            tx = session.beginTransaction();
//            Lead lead = new Lead(
//                    email,
//                    first_name,
//                    last_name,
//                    phone,
//                    cell,
//                    memo,
//                    admin
//            );
//            lead.setCreateDatetime(new Date());
//            lead.setUpdateDatetime(new Date());
//            leadID = (Integer) session.save(lead);
//            tx.commit();
//        }catch (HibernateException e) {
//            if (tx!=null) tx.rollback();
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//        return leadID;
//    }
    /* Method to  READ all the emails */
    public void listEmails(){

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List emails = session.createQuery("FROM Email").list();
            for (Iterator iterator =
            		emails.iterator(); iterator.hasNext();){
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
    /* Method to  READ emails by email type */
    public Email getEmailData(String email_type){

        Email emails = null;
        try {
        	emails = (Email) session.get(Email.class, email_type);
            Hibernate.initialize(emails);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return emails;
    }
    
    public Email getEmailByType(String pEmailType){
    	List<Email> emails = new ArrayList<>();
    	 Transaction tx = null;
         try{
            // tx = session.beginTransaction();
             emails = session.createQuery("FROM Email WHERE email_type='"+pEmailType+"' Order by sent_datetime desc").setMaxResults(1).list();
             AutomationLogger.info("QUERY RESULTS:"+emails.size());
             tx.commit();
         }catch (HibernateException e) {
             if (tx!=null) tx.rollback();
             e.printStackTrace();
         }finally {
        	 if (session != null && session.isOpen()) {
                 session.close();
             }
             return emails.get(0);
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
    public Email getEmailBySubject(String pEmailSubject){
    	List<Email> emails = new ArrayList<>();
    	 Transaction tx = null;
         try{
            // tx = session.beginTransaction();
             emails = session.createQuery("FROM Email WHERE subject='"+pEmailSubject+"' Order by sent_datetime desc").setMaxResults(1).list();
             AutomationLogger.info("QUERY RESULTS:"+emails.size());
             tx.commit();
         }catch (HibernateException e) {
             if (tx!=null) tx.rollback();
             e.printStackTrace();
         }finally {
        	 if (session != null && session.isOpen()) {
                 session.close();
             }
             return emails.get(0);
         }
         
    }
}
