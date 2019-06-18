package com.zurple.my.admin;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.ProcessEmailQueuePage;

public class ProcessEmailQueuePageTest
        extends PageTest
{
    private ProcessEmailQueuePage page;

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
