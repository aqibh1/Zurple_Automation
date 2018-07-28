package com.zurple.my.admin;

import com.zurple.my.Admin.AlertmgrPage;
import com.zurple.my.PageTest;

public class AlertmgrPageTest
        extends PageTest
{
    private static AlertmgrPage page;

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
