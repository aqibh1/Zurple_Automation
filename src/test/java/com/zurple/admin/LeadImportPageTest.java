package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.LeadImportPage;
import com.zurple.PageTest;

public class LeadImportPageTest
        extends PageTest
{
    private static LeadImportPage page;

    public LeadImportPage getPage(){
        if(page == null){
            page = new LeadImportPage();
            page.setUrl("https://my.dev.zurple.com/leadmgr/import");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
