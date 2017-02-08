package resources;

import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import resources.classes.Asset;

import static org.testng.Assert.assertTrue;

public abstract class AbstractPageTest
{

    protected static WebDriver driver;
    protected static TestEnvironment environment;
    protected AbstractPage page;

    public abstract AbstractPage getPage();

    public static WebDriver getDriver(){
        if(driver == null){
            driver = new FirefoxDriver();
        }
        return driver;
    }

    public static TestEnvironment getEnvironment(){
        if(environment == null){
            environment = new TestEnvironment();
            environment.setAgentToCheck(4);
        }
        return environment;
    }

    public static void setEnvironment( TestEnvironment object){
        environment = object;
    }

    @Test
    public void testAsssetsVerions() {
        for (Asset asset: getPage().getAssets()) {
            assertTrue(Pattern.matches("\\?v=\\d{4}\\.\\d{2}\\.\\d$",asset.getUrl()));
        }
    }

}
