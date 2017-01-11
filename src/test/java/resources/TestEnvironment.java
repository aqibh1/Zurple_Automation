package resources;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import resources.orm.hibernate.HibernateUtil;
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

        ManageLead ME = new ManageLead(factory);

      /* Add few employee records in database */
        Integer empID1 = ME.addLead( "azara@test.com","Ali","Baba","1234567890","","");
        Integer empID2 = ME.addLead("ddas@test.com","Das", "Karabas","", "","");
        Integer empID3 = ME.addLead( "jpaul@test.com", "Paul", "Anderson", "", "","");

      /* List down all the employees */
        ME.listLeads();

      /* Update employee's records */
        ME.updateLead(empID1, "azara_new@test.com");

      /* Delete an employee from the database */
        ME.deleteLead(empID2);

      /* List down new list of the employees */
        ME.listLeads();


    }
}
