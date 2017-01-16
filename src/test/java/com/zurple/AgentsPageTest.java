package com.zurple;

import static org.junit.Assert.*;

/**
 * todo
 *
 * @author Vladimir
 */
public class AgentsPageTest  extends PageTest
{
    private static AgentsPage page;

    public AgentsPage getPage(){
        if(page == null){
            page = new AgentsPage();
            page.setDriver(getDriver());
        }
        return page;
    }
}
