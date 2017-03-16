package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.EmailRestrictionsPage;
import com.zurple.PageTest;

public class EmailRestrictionsPageTest
        extends PageTest
{
    private static EmailRestrictionsPage page;

    public EmailRestrictionsPage getPage(){
        if(page == null){
            page = new EmailRestrictionsPage();
            page.setUrl("https://my.dev.zurple.com/admin/emailrestrictions");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
