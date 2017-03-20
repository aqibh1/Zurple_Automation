package com.zurple.my.admin;

import com.zurple.my.Admin.EmailmgrPage;
import com.zurple.my.PageTest;

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
