package com.zurple.my;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CraigslistPageTest
        extends PageTest
{

    private static CraigslistPage page;

    public CraigslistPage getPage(){
        if(page == null){
            page = new CraigslistPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };


    @Test
    public void testLeadList(){
        assertTrue(getPage().checkLeadsListBlockExists());
        assertEquals(getPage().getLeadsListBlock().getCraigsList().size(),15);

        Integer n = getPage().getLeadsListBlock().getCraigsList().get(0).getId();
        getEnvironment().setPropertyToCheck(n);

    }

}
