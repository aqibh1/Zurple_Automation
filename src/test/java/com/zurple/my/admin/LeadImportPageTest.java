package com.zurple.my.admin;

import com.zurple.my.Admin.LeadImportPage;
import com.zurple.my.PageTest;

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
