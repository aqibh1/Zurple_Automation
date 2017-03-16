package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.AlertmgrPage;
import com.zurple.PageTest;

public class AlertmgrPageTest
        extends PageTest
{
    private static AlertmgrPage page;

    public AlertmgrPage getPage(){
        if(page == null){
            page = new AlertmgrPage();
            page.setUrl("https://my.dev.zurple.com/alertmgr/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
