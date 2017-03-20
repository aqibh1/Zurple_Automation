package com.zurple.my.admin;

import com.zurple.my.Admin.AdminmgrEditPage;
import com.zurple.my.PageTest;

public class AdminmgrEditPageTest
        extends PageTest
{
    private static AdminmgrEditPage page;

    public AdminmgrEditPage getPage(){
        if(page == null){
            page = new AdminmgrEditPage();
            page.setUrl("https://my.dev.zurple.com/adminmgr/edit/admin_id/"+getEnvironment().getAgentToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
