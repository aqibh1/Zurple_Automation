/**
 * 
 */
package resources.orm.hibernate.dao.z57;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import resources.orm.hibernate.models.z57.IdxLeadSearches;

/**
 * @author adar
 *
 */
public class ManageIdxLeadSearches {

	  private Session session;

	    public ManageIdxLeadSearches(Session session) {
	    	this.session = session;
//	    	new NotificationEmails().save();
	    }
	    
	    public List<IdxLeadSearches> getIdxLeadSearchesList(Integer pLeadId, Integer pNumOfRecords){

	    	List<IdxLeadSearches> idx_lead_searches = new ArrayList<IdxLeadSearches>();
	        try {
	        	 Query q = session.createQuery("FROM IdxLeadSearches WHERE leadId="+pLeadId+" ORDER BY dateAdded DESC").setMaxResults(pNumOfRecords);
				idx_lead_searches = q.list();
	               
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        return idx_lead_searches;
	    }

	public IdxLeadSearches getIdxLeadSearcheByTitle(Integer pLeadId, String pTitle){

		IdxLeadSearches idx_lead_searches = null;
		try {
			Query q = session.createQuery("FROM IdxLeadSearches WHERE leadId="+pLeadId+" AND title='"+pTitle+"'");
			List idx_lead_searches_list = q.list();
			for (Iterator iterator =
				 idx_lead_searches_list.iterator(); iterator.hasNext();){
				idx_lead_searches = (IdxLeadSearches) iterator.next();
			}
			Hibernate.initialize(idx_lead_searches);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return idx_lead_searches;
	}
}
