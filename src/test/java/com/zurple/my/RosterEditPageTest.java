package com.zurple.my;

import java.util.ArrayList;
import resources.orm.hibernate.models.Site;

public class RosterEditPageTest
        extends PageTest
{

    private static RosterEditPage page;

    public RosterEditPage getPage(){
        if(page == null){
            page = new RosterEditPage();

            //TODO ArrayList<Site> sitesList = new ArrayList<Site>(getEnvironment().getAdminById(getEnvironment().getAgentToCheck()).getSites());

            page.setUrl("https://my.dev.zurple.com/agents/roster/site_id/1");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
