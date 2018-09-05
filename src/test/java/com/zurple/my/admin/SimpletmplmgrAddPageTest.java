package com.zurple.my.admin;

import com.zurple.my.Admin.SimpletmplmgrAddPage;
import com.zurple.my.PageTest;

public class SimpletmplmgrAddPageTest
        extends PageTest
{
    private SimpletmplmgrAddPage page;

    public SimpletmplmgrAddPage getPage(){
        if(page == null){
            page = new SimpletmplmgrAddPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
