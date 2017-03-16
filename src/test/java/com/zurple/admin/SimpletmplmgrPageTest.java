package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.SimpletmplmgrPage;
import com.zurple.PageTest;

public class SimpletmplmgrPageTest
        extends PageTest
{
    private static SimpletmplmgrPage page;

    public SimpletmplmgrPage getPage(){
        if(page == null){
            page = new SimpletmplmgrPage();
            page.setUrl("https://my.dev.zurple.com/simpletemplmgr/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
