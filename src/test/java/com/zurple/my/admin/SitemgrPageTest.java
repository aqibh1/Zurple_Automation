package com.zurple.my.admin;

import com.zurple.my.Admin.SitemgrPage;
import com.zurple.my.PageTest;

public class SitemgrPageTest
        extends PageTest
{
    private static SitemgrPage page;

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
