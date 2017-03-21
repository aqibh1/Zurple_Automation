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


    @Test(groups = "init")
    public void testPropertiesList(){
        assertTrue(getPage().checkLeadsListBlockExists());
        assertTrue(getPage().getLeadsListBlock().getCraigsList().size()>0);

        Integer n = getPage().getLeadsListBlock().getCraigsList().get(0).getId();
        getEnvironment().setPropertyToCheck(n);

    }

}
