package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.SitemgrPage;
import com.zurple.PageTest;

public class SitemgrPageTest
        extends PageTest
{
    private static SitemgrPage page;

    public SitemgrPage getPage(){
        if(page == null){
            page = new SitemgrPage();
            page.setUrl("https://my.dev.zurple.com/sitemgr/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
