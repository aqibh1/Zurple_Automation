package com.zurple.my;

public class CraiglistPropertyPage
        extends Page
{

    private Integer propertyId;

    public CraiglistPropertyPage(){
        url = "/property/craigslist/prop_id/";
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public String getFullUrl()
    {
        return getBaseUrl() + url + getPropertyId();
    }
}
