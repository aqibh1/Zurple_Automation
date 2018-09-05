package com.zurple.my.admin;

import com.zurple.my.Admin.PmastermgrPage;
import com.zurple.my.PageTest;

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
