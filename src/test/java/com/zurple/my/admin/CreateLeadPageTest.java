package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.CreateLeadPage;

public class CreateLeadPageTest
        extends PageTest
{
    private CreateLeadPage page;

    public CreateLeadPage getPage(){
        if(page == null){
            page = new CreateLeadPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
