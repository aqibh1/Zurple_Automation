package com.zurple.my.admin;

import com.zurple.my.Admin.ProcessEmailQueuePage;
import com.zurple.my.PageTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class ProcessEmailQueuePageTest
        extends PageTest
{
    private static ProcessEmailQueuePage page;

    public ProcessEmailQueuePage getPage(){
        if(page == null){
            page = new ProcessEmailQueuePage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test
    public void testLeadAlertsQueueProcessing()
    {
        assertTrue(getPage().runLeadAlertsQueueProcessing());
    }

    @Test
    public void testIRQueueProcessing()
    {
        assertTrue(getPage().runIRQueueProcessing());
    }

    @Test
    public void testMassEmailQueueProcessing()
    {
        assertTrue(getPage().runMassEmailQueueProcessing());
    }
}
