package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.AdmgrEditPage;

public class AdmgrEditPageTest
        extends PageTest
{
    private AdmgrEditPage page;

    public AdmgrEditPage getPage(){
        if(page == null){
            page = new AdmgrEditPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
