package resources.orm.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ManageTransactionGoals
{

    private SessionFactory factory;

    public ManageTransactionGoals(SessionFactory factory) {this.factory = factory;}

    /* Method to  READ products list by admin id */
    public List<resources.orm.hibernate.models.TransactionGoal> getTransactionGoalsListByUserId( Integer user_id ){
        Session session = factory.openSession();
        List<resources.orm.hibernate.models.TransactionGoal> transactionGoalsList = new ArrayList<resources.orm.hibernate.models.TransactionGoal>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM TransactionGoal WHERE user_id = " + user_id).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                resources.orm.hibernate.models.TransactionGoal transactionGoal = (resources.orm.hibernate.models.TransactionGoal) iterator.next();
                transactionGoalsList.add(transactionGoal);
            }
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return transactionGoalsList;
    }

}
