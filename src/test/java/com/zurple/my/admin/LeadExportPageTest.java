package com.zurple.my.admin;

import com.zurple.my.Admin.LeadExportPage;
import com.zurple.my.PageTest;

public class LeadExportPageTest
        extends PageTest
{
    private static LeadExportPage page;

    public LeadExportPage getPage(){
        if(page == null){
            page = new LeadExportPage();
            page.setUrl("https://my.dev.zurple.com/leadmgr/export");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
