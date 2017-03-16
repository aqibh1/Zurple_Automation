package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.EmailmgrPage;
import com.zurple.PageTest;

public class EmailmgrPageTest
        extends PageTest
{
    private static EmailmgrPage page;

    public EmailmgrPage getPage(){
        if(page == null){
            page = new EmailmgrPage();
            page.setUrl("https://my.dev.zurple.com/emailmgr/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
