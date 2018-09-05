package com.zurple.my.admin;

import com.zurple.my.Admin.IndexPage;
import com.zurple.my.PageTest;

public class IndexPageTest
        extends PageTest
{
    private IndexPage page;

    public IndexPage getPage(){
        if(page == null){
            page = new IndexPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
