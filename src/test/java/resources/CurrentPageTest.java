package resources;

import org.testng.annotations.Test;

import resources.utility.AutomationLogger;

public class CurrentPageTest extends AbstractPageTest
{
    public AbstractPage getPage(){
        AbstractPage currentPage = getPage();
        return currentPage;
    };

    @Test(priority=500)
    public void closeBrowser(){
        Long thread_id = Thread.currentThread().getId();
        AutomationLogger.info("Thread ID "+thread_id);
        EnvironmentFactory.quitDriver(thread_id);
        AutomationLogger.info("Browser closed Successfully");
    }
    
    @Test(priority=501)
    public void refreshPage(){
        getDriver().navigate().refresh();
    }

    public void clearPage(){
        page=null;
    };

}
