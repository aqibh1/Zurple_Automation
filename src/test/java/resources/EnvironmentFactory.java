package resources;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.CapabilityType;

public class EnvironmentFactory {

    static Map<Long,WebDriver> webDrivers = new HashMap<Long,WebDriver>();
    static Map<Long,TestEnvironment> environments = new HashMap<Long,TestEnvironment>();
    public static ConfigReader configReader = ConfigReader.load();
    private WebDriver driver;

    //TODO
    //Add more coverage for the Microsoft Edge and Safari
    
    public WebDriver getDriver(long thread_id){
    	if ( webDrivers.containsKey(thread_id) ){
    		driver = webDrivers.get(thread_id);
    	}else{
    		String l_browser = configReader.getPropertyByName("base_browser");
    		if (l_browser.equals("firefox") ){
    			FirefoxOptions options = new FirefoxOptions();
    			if(System.getProperty("driver")!=null && !System.getProperty("driver").isEmpty()) {
    				System.setProperty("webdriver.gecko.driver", System.getProperty("driver"));
    			}
    			if (Boolean.parseBoolean(System.getProperty("headless"))){
    				options.addArguments("headless");
    				options.addArguments("window-size=1920x1080");
    				options.addArguments("--no-sandbox");
    				options.addArguments("disable-gpu");
    			}
    			else{
    				options.addArguments("--start-maximized");
    			}
    			driver = new FirefoxDriver(options);

    		}else if (l_browser.equals("phantomjs") ) {
    			if(System.getProperty("driver")!=null && !System.getProperty("driver").isEmpty()) {
    				System.setProperty("phantomjs.binary.path", System.getProperty("driver"));
    			}
    			driver = new PhantomJSDriver();
    			driver.manage().window().setSize(new Dimension(1920, 1080));
    		}
    		else{
    			ChromeOptions options = new ChromeOptions();
    			if(System.getProperty("driver")!=null && !System.getProperty("driver").isEmpty()) {
    				System.setProperty("webdriver.chrome.driver", System.getProperty("driver"));
    			}
    			if (Boolean.parseBoolean(System.getProperty("headless"))){
    				options.addArguments("headless");
    				options.addArguments("start-maximized");
    				options.addArguments("--no-sandbox");
    				options.addArguments("disable-gpu");
    			}
    			else{
    				//Create a map to store  preferences 
    				Map<String, Object> prefs = new HashMap<String, Object>();
    				//add key and value to map as follow to switch off browser notification
    				//Pass the argument 1 to allow and 2 to block
    				prefs.put("profile.default_content_setting_values.notifications", 2);
    				options.setExperimentalOption("prefs", prefs);
    				options.addArguments("--start-maximized");
    				options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
    			}
    			driver = new ChromeDriver(options);
    			driver.manage().window().maximize();
    		}
    		webDrivers.put(thread_id,  driver);   
    	}
    	return driver;
    }

    public static void closeDriver(long thread_id){
        if ( webDrivers.containsKey(thread_id) ){
            WebDriver driver = webDrivers.get(thread_id);
            driver.close();
            webDrivers.remove(thread_id);
        }
    }
    public static void quitDriver(long thread_id){
        if ( webDrivers.containsKey(thread_id) ){
            WebDriver driver = webDrivers.get(thread_id);
            driver.quit();
            webDrivers.remove(thread_id);
        }
    }
    public static TestEnvironment getEnvironment(long thread_id){
        TestEnvironment environment;
        if(  environments.containsKey(thread_id) ){
            environment = environments.get(thread_id);
        }
        else{
            environment = new TestEnvironment();
            environment.setAgentToCheck(Integer.parseInt(configReader.getPropertyByName("zurple_bo_default_agent_id")));
            environment.setCurrentAgentId(Integer.parseInt(configReader.getPropertyByName("zurple_bo_default_agent_id")));
            environments.put(thread_id, environment);
        }
        return environment;
    }

}
