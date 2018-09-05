package com.zurple.my.admin;

import com.zurple.my.Admin.LeadScoreConfigPage;
import com.zurple.my.PageTest;

public class LeadScoreConfigPageTest
        extends PageTest
{
    private LeadScoreConfigPage page;

    public LeadScoreConfigPage getPage(){
        if(page == null){
            page = new LeadScoreConfigPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
