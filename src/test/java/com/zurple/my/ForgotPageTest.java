package com.zurple.my;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertFalse;
import static org.testng.AssertJUnit.assertTrue;

public class ForgotPageTest
        extends PageTest
{

    private static ForgotPage page;

    public ForgotPage getPage(){
        if(page == null){
            page = new ForgotPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test(priority=10)
    public void testTitle() {
        assertEquals(getPage().getTitle(),"Zurple Inc.");
    }

    @Test(priority=10)
    public void testBrand() {
        assertFalse(getPage().getBrand() == null);
    }

}