package com.zurple.my.admin;

import com.zurple.my.Admin.SimpletmplmgrEditPage;
import com.zurple.my.PageTest;

public class SimpletmplmgrEditPageTest
        extends PageTest
{
    private SimpletmplmgrEditPage page;

    public SimpletmplmgrEditPage getPage(){
        if(page == null){
            page = new SimpletmplmgrEditPage();
            page.setTemplateId(getEnvironment().getTemplateToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
