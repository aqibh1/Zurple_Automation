package com.zurple.admin;

import com.zurple.Admin.IndexPage;
import com.zurple.LeadEditPage;
import com.zurple.PageTest;

public class IndexPageTest
        extends PageTest
{
    private static IndexPage page;

    public IndexPage getPage(){
        if(page == null){
            page = new IndexPage();
            page.setUrl("https://my.dev.zurple.com/admin/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
