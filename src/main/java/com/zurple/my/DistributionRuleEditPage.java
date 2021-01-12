package com.zurple.my;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.zurple.my.resources.forms.DistributionRulesForm;

public class DistributionRuleEditPage
        extends Page
{
    private DistributionRulesForm distributionRulesForm;
    private Integer siteId;

    public DistributionRuleEditPage(){
        url = "/agents/distribution/site_id/";
    }

    public boolean checkDistributionRulesFormExists(){
        try{
            getDistributionRulesForm();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public DistributionRulesForm getDistributionRulesForm(){
        distributionRulesForm = new DistributionRulesForm();
        distributionRulesForm.setForm(driver.findElement(By.xpath("//*[@id=\"distribution_form\"]")));
        distributionRulesForm.setDriver(driver);
        distributionRulesForm.setSubmitButton(driver.findElement(By.xpath("//*[@id=\"distribution_form\"]/descendant::button[@id=\"saveDist\"]")));
        return distributionRulesForm;
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
