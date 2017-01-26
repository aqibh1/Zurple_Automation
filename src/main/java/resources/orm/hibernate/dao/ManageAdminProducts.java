package resources.orm.hibernate.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.models.AdminProduct;
import resources.orm.hibernate.models.Product;

public class ManageAdminProducts
{

    private SessionFactory factory;

    public ManageAdminProducts(SessionFactory factory) {this.factory = factory;}

    public Boolean addAdminProduct(
            Integer admin_id,
            Product product,
            Date nextbill_date,
            BigDecimal fee
    ){
        Session session = factory.openSession();
        Transaction tx = null;
        Boolean result = false;
        try{
            tx = session.beginTransaction();
            AdminProduct adminProduct = new AdminProduct(
                    admin_id,
                    product,
                    nextbill_date,
                    fee
            );
            result = (Boolean) session.save(adminProduct);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    /* Method to  READ produts list by admin id */
    public List<AdminProduct> getAdminProductsList( Admin admin ){
        Session session = factory.openSession();
        List<AdminProduct> adminProducts = new ArrayList<AdminProduct>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM "+AdminProduct.class.getName()+" WHERE admin_id = " + admin.getNetsuiteId()).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                AdminProduct adminProduct = (AdminProduct) iterator.next();
                adminProducts.add(adminProduct);
            }
            tx.commit();
            //Hibernate.initialize(lead);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return adminProducts;
    }

}
