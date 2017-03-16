package com.zurple.admin;

import com.zurple.Admin.AdmgrEditPage;
import com.zurple.Admin.AdmgrPage;
import com.zurple.PageTest;

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
