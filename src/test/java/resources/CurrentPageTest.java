package resources;

import org.testng.annotations.Test;

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
        Long thread_id = Thread.currentThread().getId();
        EnvironmentFactory.closeDriver(thread_id);
    }

    @Test
    public void refreshPage(){
        getDriver().navigate().refresh();
    }

    public void clearPage(){
        page=null;
    };

}
