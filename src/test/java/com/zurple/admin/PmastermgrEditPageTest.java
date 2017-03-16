package com.zurple.admin;

import com.zurple.Admin.PmastermgrEditPage;
import com.zurple.Admin.PmastermgrPage;
import com.zurple.PageTest;

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
