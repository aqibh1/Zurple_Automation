package com.zurple;

/**
 * todo
 *
 * @author Vladimir
 */
public class TransactionsPageTest
        extends PageTest
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
