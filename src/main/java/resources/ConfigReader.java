package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import resources.utility.AutomationLogger;

public class ConfigReader {
    private static ConfigReader instance;

    private Properties prop = new Properties();
    private static Properties testRailMapping = new Properties();

    private ConfigReader() {
        // Exists only to defeat instantiation.
    }

    public ConfigReader(String environment) {
        InputStream input = null;

        try {

            input = new FileInputStream("src/main/config/config."+environment+".properties");

            // load a properties file
            prop.load(input);

//            prop.setProperty("ssh_user",System.getProperty("ssh_user"));
//            prop.setProperty("ssh_key",System.getProperty("ssh_key"));
//            prop.setProperty("ssh_pass",System.getProperty("ssh_pass"));

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getPropertyByName(String propertyName){
        return prop.getProperty(propertyName);
    }
    public String getTestRailMapping(String propertyName){
        return testRailMapping.getProperty(propertyName);
    }
    public static ConfigReader load() {
        if (instance == null) {
            String environment = System.getProperty("environment");
            Object testRailMapping = System.getProperty("testrail_testrun_id");
            if(testRailMapping!=null) {
            	loadTestRailMapping();
            }
            instance = new ConfigReader(environment);
            AutomationLogger.setLog4jPopFile();
        }
        return instance;
    }
    
    private static void loadTestRailMapping() {
    	InputStream input = null;
        try {
            input = new FileInputStream("src/main/config/config.testrail.mapping.properties");
            // load a properties file
            testRailMapping.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
}
