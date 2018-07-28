package com.zurple.my.admin;

import com.zurple.my.Admin.CreateActivityAlertPage;
import com.zurple.my.PageTest;

public class CreateActivityAlertPageTest
        extends PageTest
{
    private static CreateActivityAlertPage page;

    public CreateActivityAlertPage getPage(){
        if(page == null){
            page = new CreateActivityAlertPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
