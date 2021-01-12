package com.zurple.my.admin;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.LeadStatusAutomationPage;

public class LeadStatusAutomationPageTest
        extends PageTest
{
    private LeadStatusAutomationPage page;

    public LeadStatusAutomationPage getPage(){
        if(page == null){
            page = new LeadStatusAutomationPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
    
    @Test
    public void testLeadStatusAutomationProcessing()
    {
        assertTrue(getPage().checkLeadsStatusAutomationProcessingButtonExists());
        assertTrue(getPage().runLeadStatusAutomationProcessing());
    }
}
