package resources;

import java.util.List;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.hibernate.SessionFactory;
import resources.orm.hibernate.dao.ManageAdmin;
import resources.orm.hibernate.dao.ManageEmailQueue;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.dao.ManageAdminProducts;
import resources.orm.hibernate.models.AdminProduct;
import resources.orm.hibernate.models.EmailQueue;
import resources.orm.hibernate.models.Lead;
import resources.orm.hibernate.dao.ManageLead;

public class TestEnvironment
{

    private Integer leadToCheck;
    private Integer agentToCheck;
    private List<AdminProduct> adminProducts;

    private static SessionFactory factory;

    public Integer getLeadToCheck()
    {
        return leadToCheck;
    }

    public void setLeadToCheck(Integer leadToCheck)
    {
        this.leadToCheck = leadToCheck;
    }

    public List<AdminProduct> getProductsList( )
    {
        if(adminProducts == null){
            factory = new HibernateUtil().getSessionFactory();

            ManageAdmin ma = new ManageAdmin(factory);

            ManageAdminProducts map = new ManageAdminProducts(factory);

            adminProducts = map.getAdminProductsList(ma.getAdmin(agentToCheck));

        }

        return adminProducts;
    }

    public Lead getLeadObject(Integer lead_id)
    {
        factory = new HibernateUtil().getSessionFactory();

        ManageLead ml = new ManageLead(factory);

        return ml.getLead(lead_id);
    }

    public EmailQueue getLastEmailQueueEntry()
    {
        factory = new HibernateUtil().getSessionFactory();

        ManageEmailQueue m = new ManageEmailQueue(factory);

        return m.getLastEmailQueue();
    }

    public List<String> getLeadFlags(Integer lead_id)
    {
        factory = new HibernateUtil().getSessionFactory();

        ManageLead ml = new ManageLead(factory);

        return ml.getFlags(lead_id);
    }

    public Integer getAgentToCheck()
    {
        return agentToCheck;
    }

    public void setAgentToCheck(Integer agentToCheck)
    {
        this.agentToCheck = agentToCheck;
    }
}
