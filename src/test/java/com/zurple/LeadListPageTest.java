package com.zurple;

import static org.junit.Assert.*;

/**
 * todo
 *
 * @author Vladimir
 */
public class LeadListPageTest  extends PageTest
{
    private static LeadListPage page;

    public LeadListPage getPage(){
        if(page == null){
            page = new LeadListPage();
            page.setDriver(getDriver());
        }
        return page;
    }
}
