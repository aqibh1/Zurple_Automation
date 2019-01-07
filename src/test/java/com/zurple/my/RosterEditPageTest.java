package com.zurple.my;

public class RosterEditPageTest
        extends PageTest
{

    private RosterEditPage page;

    public RosterEditPage getPage(){
        if(page == null){
            page = new RosterEditPage();

            //TODO ArrayList<Site> sitesList = new ArrayList<Site>(getEnvironment().getAdminById(getEnvironment().getAgentToCheck()).getSites());

            page.setSiteId(1);
            page.setDriver(getDriver());
        }
        return page;
    }

    public void clearPage(){
        page=null;
    };

}
