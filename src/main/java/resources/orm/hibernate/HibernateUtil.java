package resources.orm.hibernate;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import resources.ConfigReader;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            ConfigReader configReader = ConfigReader.load();
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration cfg = new AnnotationConfiguration().configure();
            cfg.setProperties(System.getProperties());
            cfg.getProperties().setProperty("hibernate.connection.url","jdbc:mysql://"+configReader.getPropertyByName("mysql_host")+":"+configReader.getPropertyByName("mysql_port")+"/"+configReader.getPropertyByName("mysql_db")+"?zeroDateTimeBehavior=convertToNull");
            cfg.getProperties().setProperty("hibernate.connection.username",configReader.getPropertyByName("mysql_user"));
            cfg.getProperties().setProperty("hibernate.connection.password",configReader.getPropertyByName("mysql_pass"));
            cfg.addResource("hibernate.cfg.xml");
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
        return sessionFactory;
    }

}
