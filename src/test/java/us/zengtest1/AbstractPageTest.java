package us.zengtest1;

import junit.framework.TestCase;
import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractPageTest extends TestCase implements UsingPage, TestHavingHeader
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

    public void testTopMenu() {
        assertEquals("",getPage().getTopMenu().findElement(By.xpath("//li[1]/a")).getText());
        assertEquals("SEARCH",getPage().getTopMenu().findElement(By.xpath("//li[2]/a")).getText());
        assertEquals("REAL ESTATE NOTES",getPage().getTopMenu().findElement(By.xpath("//li[3]/a")).getText());
        assertEquals("SOLD HOMES",getPage().getTopMenu().findElement(By.xpath("//li[4]/a")).getText());
        assertEquals("LOG IN",getPage().getTopMenu().findElement(By.xpath("//li[5]/a")).getText().trim());
    }

}
