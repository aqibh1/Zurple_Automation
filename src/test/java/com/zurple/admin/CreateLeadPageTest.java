package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.CreateLeadPage;
import com.zurple.PageTest;

public class CreateLeadPageTest
        extends PageTest
{
    private static CreateLeadPage page;

    public CreateLeadPage getPage(){
        if(page == null){
            page = new CreateLeadPage();
            page.setUrl("https://my.dev.zurple.com/admin/createlead");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
