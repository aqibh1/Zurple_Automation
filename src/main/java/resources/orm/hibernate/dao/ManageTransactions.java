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

public class ManageTransactions
{

    private SessionFactory factory;

    public ManageTransactions(SessionFactory factory) {this.factory = factory;}

    /* Method to  READ products list by admin id */
    public List<resources.orm.hibernate.models.Transaction> getTransactionsListByCustomerId( Integer customer_id ){
        Session session = factory.openSession();
        List<resources.orm.hibernate.models.Transaction> transactionList = new ArrayList<resources.orm.hibernate.models.Transaction>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM Transaction WHERE customer_id = " + customer_id).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                resources.orm.hibernate.models.Transaction transaction = (resources.orm.hibernate.models.Transaction) iterator.next();
                transactionList.add(transaction);
            }
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return transactionList;
    }

}
