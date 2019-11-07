package resources.orm.hibernate;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import resources.ConfigReader;
import resources.SSHConnector;

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
            	JSch jsch=new JSch();
            	jsch.addIdentity(System.getProperty("user.dir")+"\\resources\\aqib_openssh",configReader.getPropertyByName("zurple_ssh_password"));
            	jsch.setConfig("StrictHostKeyChecking", "no");
            	//enter your own EC2 instance IP here
            	Session session=jsch.getSession(configReader.getPropertyByName("zurple_ssh_username"), configReader.getPropertyByName("zurple_ssh_host"), 33322);
            	session.setPassword(configReader.getPropertyByName("zurple_ssh_password"));
            	session.getPort();
            	session.connect();
//            	
//            	
//            	int nLocalPort = 3399;                                // local port number use to bind SSH tunnel
//                int nRemotePort = 3306;  
//                int assigned_port = session.setPortForwardingL(nLocalPort, configReader.getPropertyByName("zurple_mysql_host"),  nRemotePort);
//            	session.getPortForwardingL();
//            	session.connect();
//            	
            	
//            	Session secondSession = jsch.getSession("adar", "localhost", nLocalPort);
//            	secondSession.connect();
            	
//            	 ChannelExec channelExec = (ChannelExec)session.openChannel("exec");
//            	 InputStream in = channelExec.getInputStream();
//            	 channelExec.setCommand("mysql -V");
//                 channelExec.connect();
//
//                 BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                 String line;
//                 int index = 0;
//
//                 while ((line = reader.readLine()) != null)
//                 {
//                     System.out.println(++index + " : " + line);
//                 }

//                
//            	 
//            	Channel channel = session.openChannel("shell");
//            	int assinged_port=session.setPortForwardingL(3307, configReader.getPropertyByName("zurple_ssh_host"), 33326);
//            	session.getServerVersion();
//            	session.getUserName();
            	String conString = "jdbc:mysql://"+configReader.getPropertyByName("zurple_mysql_host")+":"+configReader.getPropertyByName("zurple_mysql_port")+"/"+configReader.getPropertyByName("zurple_mysql_db");
            	System.out.println(conString);
                cfg.getProperties().setProperty("hibernate.connection.url",conString);
                cfg.getProperties().setProperty("hibernate.connection.username",configReader.getPropertyByName("zurple_mysql_user"));
                cfg.getProperties().setProperty("hibernate.connection.password",configReader.getPropertyByName("zurple_mysql_pass"));
                cfg.configure("/zurple.hibernate.cfg.xml");
                
//                String driverName="com.mysql.jdbc.Driver";
//            	Class.forName(driverName).newInstance();
//                Connection conn = DriverManager.getConnection (cfg.getProperty("hibernate.connection.url"), cfg.getProperty("hibernate.connection.username"), cfg.getProperty("hibernate.connection.password"));
//                System.out.println ("Database connection established");
//                System.out.println("DONE");
                
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
    	if(sessionFactory!=null) {
    		sessionFactory.close();
    		sessionFactory = null;
    	}
    }

}
