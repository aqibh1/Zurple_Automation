package resources;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
        try{
            factory = new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        ManageLead ME = new ManageLead();

      /* Add few employee records in database */
        Integer empID1 = ME.addEmployee("Zara", "Ali", "azara@test.com");
        Integer empID2 = ME.addEmployee("Daisy", "Das", "ddas@test.com");
        Integer empID3 = ME.addEmployee("John", "Paul", "jpaul@test.com");

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
