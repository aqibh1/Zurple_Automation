package resources.orm.hibernate.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.models.Lead;
import resources.orm.hibernate.models.User;

public class ManageUser
{

    private SessionFactory factory;

    public ManageUser(SessionFactory factory) {this.factory = factory;}

    /* Method to  READ user by id */
    public User getUser( Integer user_id ){
        Session session = factory.openSession();
        User user = null;
        try {
            user = (User) session.get(User.class, user_id);
            Hibernate.initialize(user);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return user;
    }

}
