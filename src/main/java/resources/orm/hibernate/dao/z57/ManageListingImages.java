package resources.orm.hibernate.dao.z57;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import resources.orm.hibernate.models.z57.ListingImages;


public class ManageListingImages {


	  private Session session;

	  public ManageListingImages(Session session) {
		  this.session = session;
//		  new ListingImages().save();
	  }
	    
	    public List<ListingImages> getListingImages(Integer pListingId){
	    	List<ListingImages> listingImages_list = null;
	        try {
	        	 Query q = session.createQuery("FROM ListingImages WHERE listingId='"+pListingId+"'");
	        	 listingImages_list  = q.list();
//	               for (Iterator iterator =
//	            		   notificationEmails_list.iterator(); iterator.hasNext();){
//	            	   notification_emails = (NotificationEmails) iterator.next();
//	               }
//	            Hibernate.initialize(notification_emails);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        return listingImages_list;
	    }


}
