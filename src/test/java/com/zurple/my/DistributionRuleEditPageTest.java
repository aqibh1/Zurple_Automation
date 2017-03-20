package com.zurple.my;

import java.util.ArrayList;
import resources.orm.hibernate.models.Site;

public class DistributionRuleEditPageTest
        extends PageTest
{

    private static DistributionRuleEditPage page;

    public DistributionRuleEditPage getPage(){
        if(page == null){
            page = new DistributionRuleEditPage();

            //TODO ArrayList<Site> sitesList = new ArrayList<Site>(getEnvironment().getAdminById(getEnvironment().getAgentToCheck()).getSites());

            page.setUrl("https://my.dev.zurple.com/agents/distribution/site_id/1");
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
