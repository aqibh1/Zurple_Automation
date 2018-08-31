package resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;

import java.util.HashMap;
import java.util.Map;

public class EnvironmentFactory {

    static Map<Long,WebDriver> webDrivers = new HashMap<Long,WebDriver>();
    static Map<Long,TestEnvironment> environments = new HashMap<Long,TestEnvironment>();

    public static WebDriver getDriver(long thread_id)
    {

        WebDriver driver;

        if ( webDrivers.containsKey(thread_id) )
        {
            driver = webDrivers.get(thread_id);
        }else
        {
            driver = new ChromeDriver(new ChromeDriverService.Builder().withSilent(true).build());
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
            ConfigReader configReader = ConfigReader.load();
            environment = new TestEnvironment();
            environment.setAgentToCheck(Integer.parseInt(configReader.getPropertyByName("bo_default_agent_id")));
            environment.setCurrentAgentId(Integer.parseInt(configReader.getPropertyByName("bo_default_agent_id")));
            environments.put(thread_id, environment);
        }
        return environment;
    }

}
