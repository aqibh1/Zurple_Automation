package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.AliasCityManagerPage;
import com.zurple.PageTest;

public class AliasCityManagerPageTest
        extends PageTest
{
    private static AliasCityManagerPage page;

    public AliasCityManagerPage getPage(){
        if(page == null){
            page = new AliasCityManagerPage();
            page.setUrl("https://my.dev.zurple.com/aliascitymgr/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
