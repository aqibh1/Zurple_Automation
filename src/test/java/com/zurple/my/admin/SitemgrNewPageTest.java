package com.zurple.my.admin;

import com.zurple.my.Admin.SitemgrNewPage;
import com.zurple.my.PageTest;

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
