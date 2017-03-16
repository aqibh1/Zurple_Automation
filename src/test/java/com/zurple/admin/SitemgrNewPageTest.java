package com.zurple.admin;

import com.zurple.Admin.SitemgrNewPage;
import com.zurple.Admin.SitemgrPage;
import com.zurple.PageTest;

public class SitemgrNewPageTest
        extends PageTest
{
    private static SitemgrNewPage page;

    public SitemgrNewPage getPage(){
        if(page == null){
            page = new SitemgrNewPage();
            page.setUrl("https://my.dev.zurple.com/sitemgr/new/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
