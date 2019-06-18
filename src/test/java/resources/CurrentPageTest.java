package resources;

import java.io.IOException;

import org.testng.annotations.Test;

import resources.utility.AutomationLogger;

public class CurrentPageTest
        extends AbstractPageTest
{

    public AbstractPage getPage(){
        AbstractPage currentPage = getPage();
        return currentPage;
    };

    @Test(priority=5)
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
