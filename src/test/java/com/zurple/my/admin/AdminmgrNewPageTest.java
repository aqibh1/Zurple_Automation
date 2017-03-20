package com.zurple.my.admin;

import com.zurple.my.Admin.AdminmgrNewPage;
import com.zurple.my.PageTest;

import static org.testng.Assert.assertTrue;

public class AdminmgrNewPageTest
        extends PageTest
{
    private static AdminmgrNewPage page;

    public AdminmgrNewPage getPage(){
        if(page == null){
            page = new AdminmgrNewPage();
            page.setUrl("https://my.dev.zurple.com/adminmgr/new");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
