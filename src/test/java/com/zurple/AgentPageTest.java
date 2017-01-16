package com.zurple;

import static org.junit.Assert.*;

/**
 * todo
 *
 * @author Vladimir
 */
public class AgentPageTest  extends PageTest
{
    private static LeadEditPage page;

    public LeadEditPage getPage(){
        if(page == null){
            page = new LeadEditPage();
            page.setUrl("https://my.dev.zurple.com/agent/edit/admin_id/"+getEnvironment().getAgentToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }
}
