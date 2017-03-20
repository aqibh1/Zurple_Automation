package com.zurple.my;

/**
 * todo
 *
 * @author Vladimir
 */
public class AgentCreatePageTest
        extends PageTest
{
    private static AgentCreatePage page;

    public AgentCreatePage getPage(){
        if(page == null){
            page = new AgentCreatePage();
            page.setUrl("https://my.dev.zurple.com/agent/create");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };
}
