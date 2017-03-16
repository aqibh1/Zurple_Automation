package com.zurple.admin;

import com.zurple.Admin.SimpletmplmgrAddPage;
import com.zurple.Admin.SimpletmplmgrPage;
import com.zurple.PageTest;

public class SimpletmplmgrAddPageTest
        extends PageTest
{
    private static SimpletmplmgrAddPage page;

    public SimpletmplmgrAddPage getPage(){
        if(page == null){
            page = new SimpletmplmgrAddPage();
            page.setUrl("https://my.dev.zurple.com/simpletemplmgr/add");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
