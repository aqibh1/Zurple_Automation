package resources.orm.hibernate.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.jws.soap.SOAPBinding.Use;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.models.Lead;
import resources.orm.hibernate.models.PackageProduct;
import resources.orm.hibernate.models.User;

public class ManageAdmin
{

    private Session session;

    public ManageAdmin(Session session) {this.session = session;}

    /* Method to  READ admin by id */
    public Admin getAdmin( Integer admin_id ){
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

    /* Method to  READ admin by email */
    public Admin getAdminByEmail( String email ){
        Admin admin = null;
        try {
            Query q = session.createQuery("FROM Admin WHERE email='"+email+"'");
            List leads = q.list();
            for (Iterator iterator =
                    leads.iterator(); iterator.hasNext();){
                admin = (Admin) iterator.next();
            }

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
