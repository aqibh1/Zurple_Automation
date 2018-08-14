package com.zurple.my;

import com.zurple.my.resources.forms.LeadEditForm;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class LeadCreatePage
        extends Page
{

    private LeadEditForm leadCreateForm;

    public LeadCreatePage(){
        url = "/lead/create";
    }

    public String getFullUrl()
    {
        return getBaseUrl() + url;
    }

    public LeadEditForm getLeadCreateForm() {
        if (leadCreateForm == null)
        {
            leadCreateForm = new LeadEditForm();
            leadCreateForm.setForm(driver.findElement(By.xpath("//*[@id=\"leadUpdateForm\"]")));
        }
        return leadCreateForm;
    }

}
