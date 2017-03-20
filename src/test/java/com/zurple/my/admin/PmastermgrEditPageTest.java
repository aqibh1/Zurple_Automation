package com.zurple.my.admin;

import com.zurple.my.Admin.PmastermgrEditPage;
import com.zurple.my.PageTest;

public class PmastermgrEditPageTest
        extends PageTest
{
    private static PmastermgrEditPage page;

    public PmastermgrEditPage getPage(){
        if(page == null){
            page = new PmastermgrEditPage();
            page.setUrl("https://my.dev.zurple.com/pmastermgr/edit");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
