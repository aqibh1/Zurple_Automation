package com.zurple.my;

/**
 * todo
 *
 * @author Vladimir
 */
public class AgentsPageTest  extends PageTest
{
    private AgentsPage page;

    public AgentsPage getPage(){
        if(page == null){
            page = new AgentsPage();
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
