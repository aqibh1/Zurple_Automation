package resources.orm.hibernate.dao.pp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import resources.orm.hibernate.models.pp.Posts;

public class ManagePosts {
	private Session session;

	public ManagePosts(Session session){
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public Posts getPostsByParentPostId(String pParentPostId){

		List<Posts> postObjList = new ArrayList<Posts>();
		try {
			Query q = session.createQuery("FROM Posts WHERE parentPostID='"+pParentPostId+"' ORDER BY dateAdded DESC LIMIT 1");
			postObjList = q.list();
			Hibernate.initialize(postObjList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return postObjList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Posts getPostsByFacebookStatus(String pFacebookStatus,String pFacebookPage){

		List<Posts> postObjList = new ArrayList<Posts>();
		try {
//			Query q = session.createQuery("FROM Posts WHERE postFacebook='"+pFacebookStatus+"' ORDER BY dateAdded DESC LIMIT 1");
			Query q = session.createQuery("FROM Posts WHERE facebookPageName='"+pFacebookPage+"' AND postFacebook LIKE '%"+pFacebookStatus+"%' ORDER BY dateAdded DESC LIMIT 1");
			postObjList = q.list();
			Hibernate.initialize(postObjList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return postObjList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Posts getPostsByTwitterStatus(String pTwitterStatus){

		List<Posts> postObjList = new ArrayList<Posts>();
		try {
//			Query q = session.createQuery("FROM Posts WHERE postFacebook='"+pFacebookStatus+"' ORDER BY dateAdded DESC LIMIT 1");
			Query q = session.createQuery("FROM Posts WHERE postTwitter LIKE '%"+pTwitterStatus+"%' ORDER BY dateAdded DESC LIMIT 1");
			postObjList = q.list();
			Hibernate.initialize(postObjList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return postObjList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public Posts getPostsByLinkedInStatus(String pLinkedInStatus){

		List<Posts> postObjList = new ArrayList<Posts>();
		try {
//			Query q = session.createQuery("FROM Posts WHERE postFacebook='"+pFacebookStatus+"' ORDER BY dateAdded DESC LIMIT 1");
			Query q = session.createQuery("FROM Posts WHERE postLinkedIn LIKE '%"+pLinkedInStatus+"%' ORDER BY dateAdded DESC").setMaxResults(1);
			postObjList = q.list();
			Hibernate.initialize(postObjList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return postObjList.get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Posts> getPostsByListingId(Integer pListingId){
	
		List<Posts> postObjList = new ArrayList<Posts>();
		try {
			Query q = session.createQuery("FROM Posts WHERE listingId='"+pListingId+"'");
			
			postObjList = q.list();
			Hibernate.initialize(postObjList);
			session.evict(q);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.getSessionFactory().evictQueries();
				session.clear();
				session.close();		
			}
		}
		return postObjList;
	}
	@SuppressWarnings("unchecked")
	public List<Posts> getTwitterAcceleratorLinkPosts(){
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		List<Posts> postObjList = new ArrayList<Posts>();
		try {
			Query q = session.createQuery("FROM Posts WHERE source='accelerator' AND postType='link' AND postTwitter IS NOT NULL AND status='1' AND dateAdded>='"+date+"'");
			
			postObjList = q.list();
			Hibernate.initialize(postObjList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.getSessionFactory().evictQueries();
				session.clear();
				session.close();		
			}
		}
		return postObjList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Posts> getFacebookAcceleratorLinkPosts(){
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		List<Posts> postObjList = new ArrayList<Posts>();
		try {
			Query q = session.createQuery("FROM Posts WHERE source='accelerator' AND postType='link' AND postFacebook IS NOT NULL AND status='1' AND dateAdded>='"+date+"'");
			
			postObjList = q.list();
			Hibernate.initialize(postObjList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.getSessionFactory().evictQueries();
				session.clear();
				session.close();		
			}
		}
		return postObjList;
	}
	
	@SuppressWarnings("unchecked")
	public List<Posts> getFacebookAcceleratorVideoPosts(){
	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		List<Posts> postObjList = new ArrayList<Posts>();
		try {
			Query q = session.createQuery("FROM Posts WHERE source='accelerator' AND postType='facebook_listing_video_post' AND postFacebook IS NOT NULL AND status='1' AND dateAdded>='"+date+"'");
			
			postObjList = q.list();
			Hibernate.initialize(postObjList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.getSessionFactory().evictQueries();
				session.clear();
				session.close();		
			}
		}
		return postObjList;
	}

	public List<Posts> getYoutubeAcceleratorVideoPosts() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf.format(new Date());
		List<Posts> postObjList = new ArrayList<Posts>();
		try {
			Query q = session.createQuery("FROM Posts WHERE source='accelerator' AND postType='youtube_listing_post' AND status='1' AND dateAdded>='"+date+"'");
			
			postObjList = q.list();
			Hibernate.initialize(postObjList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null && session.isOpen()) {
				session.getSessionFactory().evictQueries();
				session.clear();
				session.close();		
			}
		}
		return postObjList;
	}
}
