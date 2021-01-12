package com.zurple.my.admin;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.SimpletmplmgrPage;

public class SimpletmplmgrPageTest
        extends PageTest
{
    private SimpletmplmgrPage page;

    public SimpletmplmgrPage getPage(){
        if(page == null){
            page = new SimpletmplmgrPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test(groups = {"init"})
    public void testTemplatesList(){
        assertTrue(getPage().checkTemplatesListBlockExists());
        assertTrue(getPage().getTemlatesListBlock().getTemlatesList().size()>0);
        Integer n = getPage().getTemlatesListBlock().getTemlatesList().get(0).getId();

        getEnvironment().setTemplateToCheck(n);
    }
}
