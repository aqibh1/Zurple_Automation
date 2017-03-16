package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.AdminmgrPage;
import com.zurple.PageTest;

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
}
