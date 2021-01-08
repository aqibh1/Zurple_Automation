package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.AlertmgrPage;

public class AlertmgrPageTest
        extends PageTest
{
    private AlertmgrPage page;

    public AlertmgrPage getPage(){
        if(page == null){
            page = new AlertmgrPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
