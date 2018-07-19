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

public abstract class AbstractTest
{
    
    protected static TestEnvironment environment;
    
    public static TestEnvironment getEnvironment(){
        if(environment == null){
            environment = new TestEnvironment();
            environment.setAgentToCheck(4);
            environment.setCurrentAgentId(4);
        }
        return environment;
    }

    public static void setEnvironment( TestEnvironment object){
        environment = object;
    }
    
}
