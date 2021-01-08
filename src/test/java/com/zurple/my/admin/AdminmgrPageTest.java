package com.zurple.my.admin;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.AdminmgrPage;

public class AdminmgrPageTest
        extends PageTest
{
    private AdminmgrPage page;

    public AdminmgrPage getPage(){
        if(page == null){
            page = new AdminmgrPage();
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
