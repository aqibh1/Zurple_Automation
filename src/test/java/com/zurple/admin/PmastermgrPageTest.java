package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.PmastermgrPage;
import com.zurple.PageTest;

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
