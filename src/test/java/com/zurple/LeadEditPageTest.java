package com.zurple;

/**
 * todo
 *
 * @author Vladimir
 */
public class LeadEditPageTest  extends PageTest
{
    private static LeadEditPage page;

    public LeadEditPage getPage(){
        if(page == null){
            page = new LeadEditPage();
            page.setUrl("https://my.dev.zurple.com/lead/edit/user_id/"+getEnvironment().getLeadToCheck());
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
