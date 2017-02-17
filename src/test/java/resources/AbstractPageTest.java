package resources;

import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import resources.classes.Asset;

import static org.testng.Assert.assertTrue;

public abstract class AbstractPageTest
{

    protected static WebDriver driver;
    protected static TestEnvironment environment;
    protected AbstractPage page;
    protected static String source_in_url="";

    public abstract AbstractPage getPage();

    public abstract void clearPage();

    public static WebDriver getDriver(){
        if(driver == null){
            driver = new ChromeDriver();
        }
        return driver;
    }

    @Parameters("source_in_url")
    @BeforeMethod
    public void globalSetUp(String source_in_url){
        this.source_in_url = source_in_url;
    }

    @BeforeClass
    public void clearingPageObject(){
        clearPage();
    }

    @BeforeTest
    public void settingUpDriver(){
        getDriver();
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
