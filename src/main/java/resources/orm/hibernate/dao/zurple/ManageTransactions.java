package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ManageTransactions
{

    private Session session;

    public ManageTransactions(Session session) {this.session = session;}

    /* Method to  READ products list by admin id */
    public List<resources.orm.hibernate.models.zurple.Transaction> getTransactionsListByCustomerId(Integer customer_id ){

        List<resources.orm.hibernate.models.zurple.Transaction> transactionList = new ArrayList<resources.orm.hibernate.models.zurple.Transaction>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM Transaction WHERE customer_id = " + customer_id).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                resources.orm.hibernate.models.zurple.Transaction transaction = (resources.orm.hibernate.models.zurple.Transaction) iterator.next();
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
