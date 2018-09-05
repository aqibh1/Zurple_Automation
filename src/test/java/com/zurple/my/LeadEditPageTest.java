package com.zurple.my;

/**
 * todo
 *
 * @author Vladimir
 */
public class LeadEditPageTest  extends PageTest
{
    private LeadEditPage page;

    public LeadEditPage getPage(){
        if(page == null){
            page = new LeadEditPage();
            page.setLeadId(getEnvironment().getLeadToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
