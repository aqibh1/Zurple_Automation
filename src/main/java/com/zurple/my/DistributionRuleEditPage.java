package com.zurple.my;

import com.zurple.my.resources.forms.DistributionRulesForm;
import com.zurple.my.resources.forms.LeadsSearchForm;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

public class DistributionRuleEditPage
        extends Page
{
    private DistributionRulesForm distributionRulesForm;

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

}
