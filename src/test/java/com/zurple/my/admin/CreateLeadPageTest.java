package com.zurple.my.admin;

import com.zurple.my.Admin.CreateLeadPage;
import com.zurple.my.PageTest;

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
