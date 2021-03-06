package com.zurple.my;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class CraigslistPageTest
        extends PageTest
{

    private CraigslistPage page;

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
