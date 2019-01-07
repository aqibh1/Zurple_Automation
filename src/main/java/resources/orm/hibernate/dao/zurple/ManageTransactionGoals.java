package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import resources.orm.hibernate.models.zurple.TransactionGoal;

public class ManageTransactionGoals
{

    private Session session;

    public ManageTransactionGoals(Session session) {this.session = session;}

    /* Method to  READ products list by admin id */
    public List<TransactionGoal> getTransactionGoalsListByUserId(Integer user_id ){

        List<TransactionGoal> transactionGoalsList = new ArrayList<TransactionGoal>();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM TransactionGoal WHERE user_id = " + user_id).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                TransactionGoal transactionGoal = (TransactionGoal) iterator.next();
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
