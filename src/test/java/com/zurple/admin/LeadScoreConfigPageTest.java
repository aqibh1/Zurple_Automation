package com.zurple.admin;

import com.zurple.Admin.LeadExportPage;
import com.zurple.Admin.LeadScoreConfigPage;
import com.zurple.PageTest;

public class LeadScoreConfigPageTest
        extends PageTest
{
    private static LeadScoreConfigPage page;

    public LeadScoreConfigPage getPage(){
        if(page == null){
            page = new LeadScoreConfigPage();
            page.setUrl("https://my.dev.zurple.com/admin/leadscoreconfig");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
