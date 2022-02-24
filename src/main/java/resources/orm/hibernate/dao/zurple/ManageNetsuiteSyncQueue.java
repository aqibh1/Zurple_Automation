package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.models.zurple.NetsuiteSyncQueue;

public class ManageNetsuiteSyncQueue {
	private Session session;

	public ManageNetsuiteSyncQueue(Session session) {
		this.session = session;
	}
	
	public List<NetsuiteSyncQueue> getListOfFailedNetsuiteSyncQueueTransactions(String pCreateDateTime){
		List<NetsuiteSyncQueue> transactionList = new ArrayList<NetsuiteSyncQueue>();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List records = session.createQuery("FROM NetsuiteSyncQueue WHERE ERROR IS NOT NULL AND create_datetime>='"+pCreateDateTime+"'").list();

			for (Iterator iterator = records.iterator(); iterator.hasNext();)
			{
				NetsuiteSyncQueue transaction = (NetsuiteSyncQueue) iterator.next();
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
