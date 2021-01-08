package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.PmastermgrPage;

public class PmastermgrPageTest
        extends PageTest
{
    private PmastermgrPage page;

    public PmastermgrPage getPage(){
        if(page == null){
            page = new PmastermgrPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
