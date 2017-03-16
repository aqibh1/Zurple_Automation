package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.ViewARAlertsPage;
import com.zurple.PageTest;

public class ViewARAlertsPageTest
        extends PageTest
{
    private static ViewARAlertsPage page;

    public ViewARAlertsPage getPage(){
        if(page == null){
            page = new ViewARAlertsPage();
            page.setUrl("https://my.dev.zurple.com/admin/viewaralerts");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
