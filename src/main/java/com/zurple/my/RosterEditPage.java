package com.zurple.my;

import com.zurple.my.resources.blocks.CraigsListBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

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
