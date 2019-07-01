/**
 * 
 */
package resources.orm.hibernate.dao.pp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import resources.orm.hibernate.models.pp.Posts;

/**
 * @author adar
 *
 */
public class ManageSchedule {
	private Session session;

	public ManageSchedule(Session session){
		this.session = session;
	}

	@SuppressWarnings("unchecked")
	public Posts getPostsByParentPostId(String pPostId){

		List<Posts> postObjList = new ArrayList<Posts>();
		try {
			Query q = session.createQuery("FROM Schedule WHERE postID='"+pPostId+"' ORDER BY dateAdded DESC LIMIT 1");
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
