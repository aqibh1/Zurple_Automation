package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.EmailRestrictionsPage;

public class EmailRestrictionsPageTest
        extends PageTest
{
    private EmailRestrictionsPage page;

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
