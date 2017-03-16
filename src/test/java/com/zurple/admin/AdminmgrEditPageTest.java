package com.zurple.admin;

import com.zurple.Admin.AdminmgrEditPage;
import com.zurple.Admin.AdminmgrPage;
import com.zurple.PageTest;

public class AdminmgrEditPageTest
        extends PageTest
{
    private static AdminmgrEditPage page;

    public AdminmgrEditPage getPage(){
        if(page == null){
            page = new AdminmgrEditPage();
            page.setUrl("https://my.dev.zurple.com/adminmgr/edit");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
