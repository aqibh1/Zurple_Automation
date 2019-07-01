package resources.orm.hibernate.models;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.HibernateUtil;

public abstract class Abstract
        implements java.io.Serializable {

    /* Method to update model in database */
    public void save(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.merge(this);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    /* Method to create model in database */
    public void create(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.persist(this);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
    
}
