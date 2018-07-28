package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance;

    private Properties prop = new Properties();

    private ConfigReader() {
        // Exists only to defeat instantiation.
    }

    public ConfigReader(String environment) {
        InputStream input = null;

        try {

            input = new FileInputStream("src/main/config/config."+environment+".properties");

            // load a properties file
            prop.load(input);

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

    public static ConfigReader load() {
        if (instance == null) {
            String environment = System.getProperty("environment");
            instance = new ConfigReader(environment);
        }
        return instance;
    }
}