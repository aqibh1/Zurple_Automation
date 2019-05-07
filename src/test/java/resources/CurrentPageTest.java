package resources;

import org.testng.annotations.Test;

import resources.utility.AutomationLogger;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

public class CurrentPageTest
        extends AbstractPageTest
{

    public AbstractPage getPage(){
        AbstractPage currentPage = getPage();
        return currentPage;
    };

    @Test
    public void closeBrowser() throws IOException{
        Long thread_id = Thread.currentThread().getId();
        AutomationLogger.info("Thread ID "+thread_id);
        EnvironmentFactory.closeDriver(thread_id);
        AutomationLogger.info("Browser closed Successfully");
    }

    @Test
    public void refreshPage(){
        getDriver().navigate().refresh();
    }

    public void clearPage(){
        page=null;
    };

}
