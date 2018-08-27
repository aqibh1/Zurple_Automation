package resources;

import java.util.regex.Pattern;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import resources.classes.Asset;

import static org.testng.Assert.assertTrue;

public class CurrentPageTest
        extends AbstractPageTest
{

    public AbstractPage getPage(){
        AbstractPage currentPage = getPage();
        return currentPage;
    };

    @Test
    public void closeBrowser(){
        getEnvironment().closeDriver();
    }

    @Test
    public void refreshPage(){
        getDriver().navigate().refresh();
    }

    public void clearPage(){
        page=null;
    };

}
