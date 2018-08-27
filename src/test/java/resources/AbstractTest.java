package resources;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import resources.classes.Asset;

import javax.persistence.criteria.CriteriaBuilder;

public abstract class AbstractTest
{
    
    protected TestEnvironment environment;
    
    public TestEnvironment getEnvironment(){
        if(environment == null){
            ConfigReader configReader = ConfigReader.load();
            environment = new TestEnvironment();
            environment.setAgentToCheck(Integer.parseInt(configReader.getPropertyByName("bo_default_agent_id")));
            environment.setCurrentAgentId(Integer.parseInt(configReader.getPropertyByName("bo_default_agent_id")));
        }
        return environment;
    }
    
}
