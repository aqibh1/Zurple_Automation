package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.SitemgrPage;

public class SitemgrPageTest
        extends PageTest
{
    private SitemgrPage page;

    public SitemgrPage getPage(){
        if(page == null){
            page = new SitemgrPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
