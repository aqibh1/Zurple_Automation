package resources.orm.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.testng.annotations.Test;
import resources.ConfigReader;
import resources.orm.hibernate.models.pp.Posts;


public class ZurpleDBConnection {

    private static SessionFactory sessionFactory = null;

    @Test
    public void testDBConnection() {
    	BuildSessionFactory();
    }
    
    public static SessionFactory BuildSessionFactory() {
        try {        
            ConfigReader configReader = ConfigReader.load();
            Configuration cfg = new AnnotationConfiguration();
            cfg.setProperties(System.getProperties());
            String conString = "jdbc:mysql://"+configReader.getPropertyByName("zurple_mysql_host")+":"+configReader.getPropertyByName("zurple_mysql_port")+"/"+configReader.getPropertyByName("zurple_mysql_db");
            System.out.println(conString);
            cfg.getProperties().setProperty("hibernate.connection.url",conString);
            cfg.getProperties().setProperty("hibernate.connection.username",configReader.getPropertyByName("zurple_mysql_user"));
            cfg.getProperties().setProperty("hibernate.connection.password",configReader.getPropertyByName("zurple_mysql_pass"));
            cfg.configure("/zurple.hibernate.cfg.xml");
            SessionFactory sessionFactory = cfg.buildSessionFactory();
            return sessionFactory;
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if ( sessionFactory == null )
        {
            sessionFactory = BuildSessionFactory();
        }

        return sessionFactory;
    }
    public static void setSessionFactoryEmpty() {
    	if(sessionFactory!=null) {
    		sessionFactory.close();
    		sessionFactory = null;
    	}
    }

}
