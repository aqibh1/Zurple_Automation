package com.zurple.my.admin;

import com.zurple.my.Admin.AgentmgrPage;
import com.zurple.my.PageTest;

public class AgentmgrPageTest
        extends PageTest
{
    private AgentmgrPage page;

    public AgentmgrPage getPage(){
        if(page == null){
            page = new AgentmgrPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
