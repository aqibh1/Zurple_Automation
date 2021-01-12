package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.ViewARAlertsPage;

public class ViewARAlertsPageTest
        extends PageTest
{
    private ViewARAlertsPage page;

    public ViewARAlertsPage getPage(){
        if(page == null){
            page = new ViewARAlertsPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
