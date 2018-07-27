package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static ConfigReader instance = null;

    private Properties prop = null;

    private ConfigReader() {
        // Exists only to defeat instantiation.
    }

    private ConfigReader(String environment) {
        InputStream input = null;

        try {

            input = new FileInputStream("config."+environment+".properties");

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

    public static ConfigReader load(String environment) {
        if (instance == null) {
            instance = new ConfigReader(environment);
        }
        return instance;
    }
}
