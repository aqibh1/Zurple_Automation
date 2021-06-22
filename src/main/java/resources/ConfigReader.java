package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

import resources.utility.AutomationLogger;

public class ConfigReader {
    private static ConfigReader instance;

    private Properties prop = new Properties();
    private static Properties testRailMapping = new Properties();
    public static HashMap<String,String> mappingHash = new HashMap<String,String>();

    private ConfigReader() {
        // Exists only to defeat instantiation.
    }

    public ConfigReader(String environment) {
    	InputStream input = null;
    	InputStream mapping_file = null;
    	try {

    		input = new FileInputStream("src/main/config/config."+environment+".properties");
    		mapping_file = new FileInputStream("src/main/config/config.testrail.mapping.properties");
    		// load a properties file

    		// load a properties file
    		prop.load(input);
    		testRailMapping.load(mapping_file);
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
    		}if (mapping_file != null) {
    			try {
    				mapping_file.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }

    public String getPropertyByName(String propertyName){
        return prop.getProperty(propertyName);
    }
//    public String  getTestRailMapping(String propertyName){
//        return testRailMapping.getProperty(propertyName);
//    }
    public String getTestRailMapping(String propertyName){
        return mappingHash.get(propertyName);
    }
    public static ConfigReader load() {
        if (instance == null) {
            setVMArguments(); //will setup VM arguments if none provided
            String environment = System.getProperty("environment");
//            Object testRailMapping = System.getProperty("testrail_testrun_id");
//            if(testRailMapping!=null) {
//            	loadTestRailMapping();
//            }
            instance = new ConfigReader(environment);
            populateMapHash();
            AutomationLogger.setLog4jPopFile();
        }
        return instance;
    }
    private static void populateMapHash() {
    	for (String key : testRailMapping.stringPropertyNames()) {
    	    String value = testRailMapping.getProperty(key);
    	    mappingHash.put(key, value);
    	}
    }
    
    private static void setVMArguments() {
    	 if(System.getProperty("project")==null) {
       		System.getProperties().setProperty("project","zurple");
       		System.getProperties().setProperty("threads","1");
       		System.getProperties().setProperty("environment","stage01");
       		System.getProperties().setProperty("ssh_user","adar");
       		System.getProperties().setProperty("ssh_pass","bYbuAn3bP8VM");
       	} 
    }
//    private static void loadTestRailMapping() {
//    	InputStream input = null;
//        try {
//            input = new FileInputStream("src/main/config/config.testrail.mapping.properties");
//            // load a properties file
//            testRailMapping.load(input);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            if (input != null) {
//                try {
//                    input.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
    
}
