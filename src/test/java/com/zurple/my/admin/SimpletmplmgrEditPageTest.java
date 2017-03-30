package com.zurple.my.admin;

import com.zurple.my.Admin.SimpletmplmgrEditPage;
import com.zurple.my.PageTest;

public class SimpletmplmgrEditPageTest
        extends PageTest
{
    private static SimpletmplmgrEditPage page;

    public SimpletmplmgrEditPage getPage(){
        if(page == null){
            page = new SimpletmplmgrEditPage();
            page.setUrl("https://my.dev.zurple.com/simpletemplmgr/edit/id/"+getEnvironment().getTemplateToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}