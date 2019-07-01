package resources.orm.hibernate.dao.pp;

import java.util.ArrayList;
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
}
