package resources.orm.hibernate.dao;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.models.AdminProduct;
import resources.orm.hibernate.models.Lead;

public class ManageAdmin
{

    private SessionFactory factory;

    public ManageAdmin(SessionFactory factory) {this.factory = factory;}

    /* Method to  READ admin by id */
    public Admin getAdmin( Integer admin_id ){
        Session session = factory.openSession();
        Admin admin = null;
        try {
            admin = (Admin) session.get(Admin.class, admin_id);
            Hibernate.initialize(admin);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return admin;
    }

}
