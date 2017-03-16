package com.zurple.admin;

import com.zurple.Admin.AdmgrPage;
import com.zurple.Admin.AgentmgrPage;
import com.zurple.PageTest;

public class AgentmgrPageTest
        extends PageTest
{
    private static AgentmgrPage page;

    public AgentmgrPage getPage(){
        if(page == null){
            page = new AgentmgrPage();
            page.setUrl("https://my.dev.zurple.com/agentmgr/");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
