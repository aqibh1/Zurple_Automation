package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.models.zurple.NetSuiteSyncTasks;

public class ManageNetsuiteSyncTasks{

	private Session session;

	public ManageNetsuiteSyncTasks(Session session) {
		this.session = session;
	}
	
	public List<NetSuiteSyncTasks> getListOfFailedNetsuiteSyncTaskTransactions(String pDateProcessed){
		List<NetSuiteSyncTasks> transactionList = new ArrayList<NetSuiteSyncTasks>();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			List records = session.createQuery("FROM NetSuiteSyncTasks WHERE results='FAIL' AND admin_id!=15129 AND date_processed>='"+pDateProcessed+"'").list();

			for (Iterator iterator =
					records.iterator(); iterator.hasNext();){
				NetSuiteSyncTasks transaction = (NetSuiteSyncTasks) iterator.next();
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
