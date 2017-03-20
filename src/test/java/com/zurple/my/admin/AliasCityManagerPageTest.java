package com.zurple.my.admin;

import com.zurple.my.Admin.AliasCityManagerPage;
import com.zurple.my.PageTest;

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
