package resources.orm.hibernate.dao.zurple;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import resources.orm.hibernate.models.zurple.SessionUser;
import resources.orm.hibernate.models.zurple.User;
import resources.orm.hibernate.models.zurple.UserActivity;

public class ManageUser
{

    private Session session;

    public ManageUser(Session session) {this.session = session;}

    /* Method to  READ user by id */
    public User getUser( Integer user_id ){

        User user = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            user = (User) session.get(User.class, user_id);
            Hibernate.initialize(user);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    /* Get last registered user */
    public User getLastRegisteredUser(){

        User user = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM User ORDER BY user_id DESC").list();
            user = (User) records.get(0);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

    /* Method to saves user to database */
    public void updateUser(
            User user
    ){

        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        }catch (HibernateException e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    /* Method to READ all leads owned by the admin */
    public List<User> getNewLeadsAssignedToAdminById ( Integer admin_id ){

        List<User> users = new ArrayList<User>();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM User WHERE admin_id="+admin_id).list();

            for (Iterator iterator =
                    records.iterator(); iterator.hasNext();){
                User user = (User) iterator.next();
                users.add(user);
            }
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return users;

    }

    /* Method to READ number of leads by status */
    public Integer getNumberAssignedToAdminOfLeadsByStatus( Integer admin_id, String status ){

        Integer numberOfLeads = 0;
        try {
            Query q = session.createQuery("select count(*) FROM User WHERE admin_id=:admin_id and user_status=:status");
            q.setInteger("admin_id", admin_id);
            q.setString("status", status);
            Long longValue = (Long)q.uniqueResult();
            numberOfLeads = longValue.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return numberOfLeads;
    }
    
    /* Method creates user session*/
    public Integer createSession(
            User user
    ){
        SessionUser session_user = new SessionUser();
        session_user.setUserId(user);
        session_user.setSiteId(user.getSiteId());
        ManageSessionUser msu = new ManageSessionUser(session);
        return msu.updateSessionUser(session_user);
    }

    /* Method creates user session*/
    public Integer createActivity(
            User user,
            SessionUser session_user,
            Date user_activity_time,
            String user_activity_type,
            Integer user_activity_target
    ){
        UserActivity user_activity = new UserActivity(
                user,
                user.getSiteId(),
                session_user,
                user_activity_time,
                user_activity_type,
                user_activity_target
        );
        ManageUserActivity mua = new ManageUserActivity(session);
        return mua.updateUserActivity(user_activity);
    }
    
    public User getUserIdByUserName(String pUserName){

        User user = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List records = session.createQuery("FROM User WHERE user_name='"+pUserName+"'").list();
            user = (User) records.get(0);
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }
    
    public List<User> getListOfUsers(Integer pAdminId, String pTrafficSource, Date pCreateDateTime){

        List<User> list_of_users = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            list_of_users = session.createQuery("FROM User WHERE admin_id='"+pAdminId+"' AND traffic_source='"+pTrafficSource+"' and create_datetime='"+pCreateDateTime+"'").list();
            tx.commit();
        } catch (Exception e) {
            if (tx!=null) tx.rollback();
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list_of_users;
    }

}
