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

    public TestEnvironment getEnvironment()
    {
        Long thread_id = Thread.currentThread().getId();
        TestEnvironment environment = EnvironmentFactory.getEnvironment(thread_id);
        return environment;
    }
    
}
