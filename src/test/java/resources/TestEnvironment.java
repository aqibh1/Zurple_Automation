package resources;

import java.util.List;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.hibernate.SessionFactory;
import resources.orm.hibernate.dao.ManageAdmin;
import resources.orm.hibernate.models.Admin;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.dao.ManageAdminProducts;
import resources.orm.hibernate.models.AdminProduct;
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

    public void getLeadObject( )
    {
        factory = new HibernateUtil().getSessionFactory();

        ManageLead ml = new ManageLead(factory);

        Lead lead = ml.getLead(100001);
        System.out.print(lead.getFirstName());
        System.out.print(lead.getLastName());
        System.out.print(lead.getEmail());
        Admin admin = lead.getOwnerId();
        System.out.print(admin.getFirstName());
        System.out.print(admin.getLastName());
        System.out.print(admin.getEmail());
        //System.out.println(lead.listLeads());
      /* Add few employee records in database */
        Integer leadID1 = ml.addLead( "azara@test.com","Ali","Baba","1234567890","","", admin);
        /*Integer empID2 = ME.addLead("ddas@test.com","Das", "Karabas","", "","");
        Integer empID3 = ME.addLead( "jpaul@test.com", "Paul", "Anderson", "", "","");*/

      /* List down all the employees */
        //ME.listLeads();


        ml.updateLead(lead.getId(), "azara_new@test.com");

      /* Delete an employee from the database */
        //ME.deleteLead(empID2);

      /* List down new list of the employees */
        //ME.listLeads();


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
