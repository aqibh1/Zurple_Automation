/**
 * 
 */
package resources.orm.hibernate.dao.z57;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import resources.orm.hibernate.models.z57.Listings;

/**
 * @author adar
 *
 */
public class ManageListings {
	private Session session;

	public ManageListings(Session session) {
		this.session = session;
	}
	public Listings getListing(Integer pListingId){

		Listings listing = null;
	        try {
	        	listing = (Listings) session.get(Listings.class, pListingId);
	            Hibernate.initialize(listing);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        return listing;
    }
}
