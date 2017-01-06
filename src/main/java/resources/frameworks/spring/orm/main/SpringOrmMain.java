package resources.frameworks.spring.orm.main;

import java.util.Arrays;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import resources.frameworks.spring.orm.model.Lead;
import resources.frameworks.spring.orm.service.LeadService;

public class SpringOrmMain {

    public static void main(String[] args) {

        //Create Spring application context
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");

        //Get service from context. (service's dependency (ProductDAO) is autowired in ProductService)
        LeadService leadService = ctx.getBean(LeadService.class);

        //Do some data operation

        leadService.add(new Lead(1, "Bulb"));
        leadService.add(new Lead(2, "Dijone mustard"));

        System.out.println("listAll: " + leadService.listAll());

        //Test transaction rollback (duplicated key)

        try {
            leadService.addAll(Arrays.asList(new Lead(3, "Book"), new Lead(4, "Soap"), new Lead(1, "Computer")));
        } catch (DataAccessException dataAccessException) {
        }

        //Test element list after rollback
        System.out.println("listAll: " + leadService.listAll());

        ctx.close();

    }
}
