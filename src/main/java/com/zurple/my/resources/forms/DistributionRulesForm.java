package com.zurple.my.resources.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.zurple.my.resources.alerts.DistributionRulesUpdateWarning;

public class DistributionRulesForm
        extends resources.forms.LoginForm
{

    DistributionRulesUpdateWarning distributionRulesUpdateWarning;

    public boolean checkDistributionRulesUpdateWarningExists(){
        try{
            getDistributionRulesUpdateWarning();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public boolean checkDistributionRulesUpdateWarningVisible(){
        try{
            getDistributionRulesUpdateWarning();
            return distributionRulesUpdateWarning.isVisible();
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public DistributionRulesUpdateWarning getDistributionRulesUpdateWarning(){
        distributionRulesUpdateWarning = new DistributionRulesUpdateWarning();
        distributionRulesUpdateWarning.setAlert(driver.findElement(By.xpath("/html/body/div[6]")));
        distributionRulesUpdateWarning.setOkButton(driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/button[1]")));
        return distributionRulesUpdateWarning;
    }

}
