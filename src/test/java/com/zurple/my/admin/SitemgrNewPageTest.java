package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.SitemgrNewPage;

public class SitemgrNewPageTest
        extends PageTest
{
    private SitemgrNewPage page;

    public SitemgrNewPage getPage(){
        if(page == null){
            page = new SitemgrNewPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
