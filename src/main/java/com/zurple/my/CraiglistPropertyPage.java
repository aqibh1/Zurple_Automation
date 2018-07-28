package com.zurple.my;

import com.zurple.my.resources.blocks.BouncedEmailAlertBlock;
import com.zurple.my.resources.blocks.RemindersBlock;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import resources.alerts.SweetAlertNotification;

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
