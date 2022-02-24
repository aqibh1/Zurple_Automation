package resources.orm.hibernate.dao.zurple;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import resources.orm.hibernate.models.zurple.Admin;

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
            List<Admin> leads = q.list();
            for (Iterator<Admin> iterator =
                    leads.iterator(); iterator.hasNext();){
                admin = iterator.next();
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
    
    /* Method to  READ admin by email */
    public List<Admin> getListOfSubAdmins(Integer pSiteOwner){
        List<Admin> admin_list = null;
        Admin admin = null;
        try {
//        	Criteria cr = session.createCriteria(Admin.class)
//            .add(Restrictions.disjunction()
//                .add(Restrictions.eq("owner_id", pSiteOwner))
//                .add(Restrictions.eq("admin_id", pSiteOwner))
//            );
        	
            Query q = session.createQuery("FROM Admin WHERE admin_id="+pSiteOwner+" OR owner_id="+pSiteOwner+" AND delete_flag=0");
            admin_list = q.list();
//            for (Iterator iterator =
//            		admin_list.iterator(); iterator.hasNext();){
//            	admin = (Admin) iterator.next();
//            }
            Hibernate.initialize(admin_list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (session != null && session.isOpen()) {
//                session.close();
//            }
        }
        return admin_list;
    }

}
