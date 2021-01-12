package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.CreateActivityAlertPage;

public class CreateActivityAlertPageTest
        extends PageTest
{
    private CreateActivityAlertPage page;

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
