package resources;

import java.util.Iterator;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import resources.orm.hibernate.Admin;
import resources.orm.hibernate.HibernateUtil;
import resources.orm.hibernate.Lead;
import resources.orm.hibernate.ManageLead;

public class TestEnvironment
{

    private Integer leadToCheck;
    private static SessionFactory factory;

    public Integer getLeadToCheck()
    {
        return leadToCheck;
    }

    public void setLeadToCheck(Integer leadToCheck)
    {
        this.leadToCheck = leadToCheck;
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
}
