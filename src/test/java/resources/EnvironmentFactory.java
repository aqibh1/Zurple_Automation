package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.lang.ref.PhantomReference;
import java.util.HashMap;
import java.util.Map;

public class EnvironmentFactory {

    static Map<Long,WebDriver> webDrivers = new HashMap<Long,WebDriver>();
    static Map<Long,TestEnvironment> environments = new HashMap<Long,TestEnvironment>();
    public static ConfigReader configReader = ConfigReader.load();

    //TODO
    //Add more coverage for the Microsoft Edge and Safari
    
    public static WebDriver getDriver(long thread_id)
    {

        WebDriver driver;

        if ( webDrivers.containsKey(thread_id) )
        {
            driver = webDrivers.get(thread_id);
        }else
        {
            if ( configReader.getPropertyByName("base_browser").equals("firefox") )
            {
                FirefoxOptions options = new FirefoxOptions();

                if (Boolean.parseBoolean(System.getProperty("headless")))
                {
                    options.addArguments("headless");
                    options.addArguments("window-size=1200x600");
                }
                else
                {
                    options.addArguments("--start-maximized");
                }

                driver = new FirefoxDriver(options);

            }
            else
            {
                ChromeOptions options = new ChromeOptions();
                if(System.getProperty("driver")!=null && System.getProperty("driver").isEmpty()) {
                	System.setProperty("webdriver.chrome.driver", System.getProperty("driver"));
                }
                if (Boolean.parseBoolean(System.getProperty("headless")))
                {
                    options.addArguments("headless");
                    options.addArguments("window-size=1200x600");
//                    options.addArguments("--headless");
//                    options.addArguments("--no-sandbox");
//                    options.addArguments("start-maximized");
//                    options.addArguments("disable-infobars");
//                    options.addArguments("--disable-extensions");
        
                }
                else
                {
                    options.addArguments("--start-maximized");
                }
                
                
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();

            }

            webDrivers.put(thread_id,  driver);
        }

        return driver;
    }

    public static void closeDriver(long thread_id){
        if ( webDrivers.containsKey(thread_id) )
        {
            WebDriver driver = webDrivers.get(thread_id);
            driver.close();
            webDrivers.remove(thread_id);
        }
    }

    public static TestEnvironment getEnvironment(long thread_id){

        TestEnvironment environment;

        if(  environments.containsKey(thread_id) )
        {
            environment = environments.get(thread_id);
        }
        else
        {
            environment = new TestEnvironment();
            environment.setAgentToCheck(Integer.parseInt(configReader.getPropertyByName("zurple_bo_default_agent_id")));
            environment.setCurrentAgentId(Integer.parseInt(configReader.getPropertyByName("zurple_bo_default_agent_id")));
            environments.put(thread_id, environment);
        }
        return environment;
    }

}
