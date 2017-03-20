package com.zurple.my.admin;

import com.zurple.my.Admin.AlertmgrEditPage;
import com.zurple.my.PageTest;

public class AlertmgrEditPageTest
        extends PageTest
{
    private static AlertmgrEditPage page;

    public AlertmgrEditPage getPage(){
        if(page == null){
            page = new AlertmgrEditPage();
            page.setUrl("https://my.dev.zurple.com/alertmgr/edit");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
