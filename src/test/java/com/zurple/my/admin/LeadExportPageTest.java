package com.zurple.my.admin;

import com.zurple.my.PageTest;
import com.zurple.my.Admin.LeadExportPage;

public class LeadExportPageTest
        extends PageTest
{
    private LeadExportPage page;

    public LeadExportPage getPage(){
        if(page == null){
            page = new LeadExportPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
