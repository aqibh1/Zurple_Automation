package com.zurple.my.resources.forms;

import com.zurple.my.resources.alerts.AgentCreateWarning;
import com.zurple.my.resources.alerts.DistributionRulesUpdateWarning;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import resources.elements.CheckBox;

public class AgentCreateForm
        extends resources.forms.AbstractForm
{
    AgentCreateWarning agentCreateWarning;

    public boolean checkAgentCreateWarningExists(){
        try{
            getAgentCreateWarning();
            return true;
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public boolean checkAgentCreateWarningVisible(){
        try{
            getAgentCreateWarning();
            return agentCreateWarning.isVisible();
        }catch(StaleElementReferenceException e){
            return false;
        }
    }

    public AgentCreateWarning getAgentCreateWarning(){
        agentCreateWarning = new AgentCreateWarning();
        agentCreateWarning.setAlert(driver.findElement(By.xpath("/html/body/div[6]")));
        agentCreateWarning.setOkButton(driver.findElement(By.xpath("/html/body/div[6]/div[3]/div/button[1]")));
        return agentCreateWarning;
    }
}
