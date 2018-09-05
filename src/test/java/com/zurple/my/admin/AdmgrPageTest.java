package com.zurple.my.admin;

import com.zurple.my.Admin.AdmgrPage;
import com.zurple.my.PageTest;

public class AdmgrPageTest
        extends PageTest
{
    private AdmgrPage page;

    public AdmgrPage getPage(){
        if(page == null){
            page = new AdmgrPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
