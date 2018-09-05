package com.zurple.my.admin;

import com.zurple.my.Admin.AlertmgrEditPage;
import com.zurple.my.PageTest;

public class AlertmgrEditPageTest
        extends PageTest
{
    private AlertmgrEditPage page;

    public AlertmgrEditPage getPage(){
        if(page == null){
            page = new AlertmgrEditPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
