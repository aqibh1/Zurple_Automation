package com.zurple.my.admin;

import com.zurple.my.Admin.AdminmgrPage;
import com.zurple.my.PageTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class AdminmgrPageTest
        extends PageTest
{
    private static AdminmgrPage page;

    public AdminmgrPage getPage(){
        if(page == null){
            page = new AdminmgrPage();
            page.setUrl("https://my.dev.zurple.com/adminmgr/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

    @Test(groups = {"init"})
    public void testAdminsList(){
        assertTrue(getPage().checkAdminListBlockExists());
        assertTrue(getPage().getAdminsListBlock().getAdminsList().size()>0);
        Integer n = getPage().getAdminsListBlock().getAdminsList().get(0).getId();

        getEnvironment().setAgentToCheck(n);
    }

}
