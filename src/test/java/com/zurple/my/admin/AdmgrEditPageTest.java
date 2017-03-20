package com.zurple.my.admin;

import com.zurple.my.Admin.AdmgrEditPage;
import com.zurple.my.PageTest;

public class AdmgrEditPageTest
        extends PageTest
{
    private static AdmgrEditPage page;

    public AdmgrEditPage getPage(){
        if(page == null){
            page = new AdmgrEditPage();
            page.setUrl("https://my.dev.zurple.com/admgr/edit/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
