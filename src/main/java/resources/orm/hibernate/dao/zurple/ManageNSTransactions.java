package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.models.zurple.NSTransaction;

public class ManageNSTransactions
{

    private Session session;

    public ManageNSTransactions(Session session) {this.session = session;}

    /* Method to  READ products list by admin id */
    public List<NSTransaction> getTransactionsListByCustomerId(Integer customer_id ){

        List<NSTransaction> transactionList = new ArrayList<NSTransaction>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM Transaction WHERE customer_id = " + customer_id).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                NSTransaction transaction = (NSTransaction) iterator.next();
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

    public List<NSTransaction> getListOfNSTransactionsByDate(String pTransactionDate){

        List<NSTransaction> transactionList = new ArrayList<NSTransaction>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //Query q = session.createQuery("FROM AdminDashboardStats WHERE admin_id='"+pAdminId+"'");
            List records = session.createQuery("FROM NSTransaction WHERE tran_date >='"+pTransactionDate+"'").list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                NSTransaction transaction = (NSTransaction) iterator.next();
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
