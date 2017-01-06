package resources;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import resources.orm.Lead;
import resources.orm.LeadBo;

public class TestEnvironment
{

    private Integer leadToCheck;

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
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("config/BeanLocations.xml");

        LeadBo leadBo = (LeadBo)appContext.getBean("leadBo");

        Lead lead2 = leadBo.findByCode("7668");
        System.out.println(lead2);

    }
}
