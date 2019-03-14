package resources.orm.hibernate;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import resources.ConfigReader;

public class HibernateUtil {

    private static SessionFactory sessionFactory = null;

    private static SessionFactory buildSessionFactory() {
        try {

            //#5549
            String project = System.getProperty("project");

            ConfigReader configReader = ConfigReader.load();

            Configuration cfg = new AnnotationConfiguration();
            cfg.setProperties(System.getProperties());

            if (project.equals("z57"))
            {
                cfg.getProperties().setProperty("hibernate.connection.url","jdbc:mysql://"+configReader.getPropertyByName("z57_mysql_host")+":"+configReader.getPropertyByName("z57_mysql_port")+"/"+configReader.getPropertyByName("z57_mysql_db")+"?zeroDateTimeBehavior=convertToNull");
                cfg.getProperties().setProperty("hibernate.connection.username",configReader.getPropertyByName("z57_mysql_user"));
                cfg.getProperties().setProperty("hibernate.connection.password",configReader.getPropertyByName("z57_mysql_pass"));
                cfg.configure("/z57.hibernate.cfg.xml");
            }
            else
            {
                cfg.getProperties().setProperty("hibernate.connection.url","jdbc:mysql://"+configReader.getPropertyByName("zurple_mysql_host")+":"+configReader.getPropertyByName("zurple_mysql_port")+"/"+configReader.getPropertyByName("zurple_mysql_db")+"?zeroDateTimeBehavior=convertToNull");
                cfg.getProperties().setProperty("hibernate.connection.username",configReader.getPropertyByName("zurple_mysql_user"));
                cfg.getProperties().setProperty("hibernate.connection.password",configReader.getPropertyByName("zurple_mysql_pass"));
                cfg.configure("/zurple.hibernate.cfg.xml");
            }

            SessionFactory sessionFactory = cfg.buildSessionFactory();
            return sessionFactory;

        }
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if ( sessionFactory == null )
        {
            sessionFactory = buildSessionFactory();
        }

        return sessionFactory;
    }
    public static void setSessionFactoryEmpty() {
    	sessionFactory = null;
    }

}
