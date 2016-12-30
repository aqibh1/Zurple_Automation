package resources;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import resources.interfaces.TestHavingHeader;
import resources.interfaces.UsingPage;

public abstract class AbstractPageTest extends TestCase
{

    protected static WebDriver driver;
    protected AbstractPage page;

    public abstract AbstractPage getPage();

    protected static WebDriver getDriver(){
        if(driver == null){
            driver = new FirefoxDriver();
        }
        return driver;
    }

    @AfterClass
    public static void cleanup(){
        getDriver().quit();
    }

}
