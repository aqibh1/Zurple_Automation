package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.CreateActivityAlertPage;
import com.zurple.PageTest;

public class CreateActivityAlertPageTest
        extends PageTest
{
    private static CreateActivityAlertPage page;

    public CreateActivityAlertPage getPage(){
        if(page == null){
            page = new CreateActivityAlertPage();
            page.setUrl("https://my.dev.zurple.com/admin/createactivityalert");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
