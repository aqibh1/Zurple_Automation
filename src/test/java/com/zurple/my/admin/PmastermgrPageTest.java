package com.zurple.my.admin;

import com.zurple.my.Admin.PmastermgrPage;
import com.zurple.my.PageTest;

public class PmastermgrPageTest
        extends PageTest
{
    private static PmastermgrPage page;

    public PmastermgrPage getPage(){
        if(page == null){
            page = new PmastermgrPage();
            page.setUrl("https://my.dev.zurple.com/pmastermgr/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
