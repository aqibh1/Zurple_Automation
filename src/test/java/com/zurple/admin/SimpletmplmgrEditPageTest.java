package com.zurple.admin;

import com.zurple.Admin.SimpletmplmgrAddPage;
import com.zurple.Admin.SimpletmplmgrEditPage;
import com.zurple.PageTest;

public class SimpletmplmgrEditPageTest
        extends PageTest
{
    private static SimpletmplmgrEditPage page;

    public SimpletmplmgrEditPage getPage(){
        if(page == null){
            page = new SimpletmplmgrEditPage();
            page.setUrl("https://my.dev.zurple.com/simpletemplmgr/edit");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
