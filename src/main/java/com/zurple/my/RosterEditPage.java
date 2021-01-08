package com.zurple.my;

public class RosterEditPage
        extends Page
{

    private Integer siteId;

    public RosterEditPage(){
        url = "/agents/roster/site_id/";
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    public String getFullUrl()
    {
        return getBaseUrl() + url + getSiteId();
    }

}
