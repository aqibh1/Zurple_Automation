/**
 * 
 */
package resources.orm.hibernate.dao.z57;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import resources.orm.hibernate.models.z57.Sites;

/**
 * @author adar
 *
 */
public class ManageSites {

	  private Session session;

	    public ManageSites(Session session) {
	    	this.session = session;
	    }
	    

	public Sites getSitesByWPUrl(String pUrl){

		Sites sites = null;
		try {
			Query q = session.createQuery("FROM Sites WHERE webSiteURL='"+pUrl+"'");
			List sites_list = q.list();
			for (Iterator iterator =
				 sites_list.iterator(); iterator.hasNext();){
				sites = (Sites) iterator.next();
			}
			Hibernate.initialize(sites);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return sites;
	}
}
