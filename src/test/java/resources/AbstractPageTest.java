package resources;

import java.util.regex.Pattern;
import junit.framework.TestCase;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import resources.classes.Asset;

public abstract class AbstractPageTest extends TestCase
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
        }
        return environment;
    }

    public static void setEnvironment( TestEnvironment object){
        environment = object;
    }

    public void testAsssetsVerions() {
        for (Asset asset: getPage().getAssets()) {
            assertTrue(Pattern.matches("\\?v=\\d{4}\\.\\d{2}\\.\\d$",asset.getUrl()));
        }
    }


    @AfterClass
    public static void cleanup(){
        getDriver().quit();
    }

}
