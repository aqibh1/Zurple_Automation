package resources.orm.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class BaseModel implements java.io.Serializable
{

    private static SessionFactory factory;



    public BaseModel() {}

    protected abstract BaseModel me();

    public Integer save(){
        Session session = factory.openSession();
        Transaction tx = null;
        Integer record_id = null;
        try{
            tx = session.beginTransaction();
            record_id = (Integer) session.save(me());
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return record_id;
    }

    public static void list( ){
        Session session = factory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            List models = session.createQuery("FROM "+caller().getClass()).list();
            for (Iterator iterator =
                    models.iterator(); iterator.hasNext();){
                BaseModel model = (BaseModel) iterator.next();
            }
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    private static Object caller(){
        return Thread.currentThread().getStackTrace()[1];
    }

    public static BaseModel get( Integer id ){
        Session session = factory.openSession();
        BaseModel entity = null;
        try {
            entity = (BaseModel) session.get(caller().getClass(), id);
            //lead = (Lead) session.get(Lead.class, lead_id);
            Hibernate.initialize(entity);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entity;
    }

    public static void setFactory(SessionFactory factory)
    {
        BaseModel.factory = factory;
    }
}
