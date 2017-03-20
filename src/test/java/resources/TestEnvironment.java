package resources;

import java.util.List;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.hibernate.SessionFactory;
import resources.orm.hibernate.dao.ManageAdmin;
import resources.orm.hibernate.dao.ManageEmailQueue;
import resources.orm.hibernate.dao.ManageUser;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.dao.ManageAdminProducts;
import resources.orm.hibernate.models.AdminProduct;
import resources.orm.hibernate.models.EmailQueue;
import resources.orm.hibernate.models.Lead;
import resources.orm.hibernate.dao.ManageLead;
import resources.orm.hibernate.models.User;

public class TestEnvironment
{

    private Integer leadToCheck;
    private Integer propertyToCheck;
    private Integer agentToCheck;
    private Integer templateToCheck;

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

    public void setTemplateToCheck(Integer templateToCheck)
    {
        this.templateToCheck = templateToCheck;
    }

    public void setPropertyToCheck(Integer propertyToCheck)
    {
        this.propertyToCheck = propertyToCheck;
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

    public User getUserById(Integer user_id)
    {
        factory = new HibernateUtil().getSessionFactory();

        ManageUser mu = new ManageUser(factory);

        return mu.getUser(user_id);
    }

    public Admin getAdminById(Integer admin_id)
    {
        factory = new HibernateUtil().getSessionFactory();

        ManageAdmin ma = new ManageAdmin(factory);

        return ma.getAdmin(admin_id);
    }

    public void updateUser(User user)
    {
        factory = new HibernateUtil().getSessionFactory();

        ManageUser mu = new ManageUser(factory);

        mu.updateUser(user);
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

    public Integer getTemplateToCheck()
    {
        return templateToCheck;
    }

    public Integer getPropertyToCheck()
    {
        return propertyToCheck;
    }
}
