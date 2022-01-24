package resources.orm.hibernate.dao.zurple;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import resources.orm.hibernate.models.zurple.AdminDashboardStats;

public class ManageAdminDashboardStats
{

    private Session session;

    public ManageAdminDashboardStats(Session session) {this.session = session;}


    /* Method to  get admin stats by id*/
    public AdminDashboardStats getAdminStatsById(Integer pAdminId){
    	AdminDashboardStats adminDashboardStats = null;
        try {
            Query q = session.createQuery("FROM AdminDashboardStats WHERE admin_id='"+pAdminId+"'");
            List leads = q.list();
            for (Iterator iterator =
                    leads.iterator(); iterator.hasNext();){
            	adminDashboardStats = (AdminDashboardStats) iterator.next();
            }

            Hibernate.initialize(adminDashboardStats);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return adminDashboardStats;
    }

}
