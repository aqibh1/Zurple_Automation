package com.zurple.my.admin;

import com.zurple.my.Admin.EmailRestrictionsPage;
import com.zurple.my.PageTest;

public class EmailRestrictionsPageTest
        extends PageTest
{
    private static EmailRestrictionsPage page;

    public EmailRestrictionsPage getPage(){
        if(page == null){
            page = new EmailRestrictionsPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
