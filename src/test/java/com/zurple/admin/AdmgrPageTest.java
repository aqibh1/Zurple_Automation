package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.IndexPage;
import com.zurple.PageTest;

public class AdmgrPageTest
        extends PageTest
{
    private static AdmgrPage page;

    public AdmgrPage getPage(){
        if(page == null){
            page = new AdmgrPage();
            page.setUrl("https://my.dev.zurple.com/admgr/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
